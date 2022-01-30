package dev.benswift.back_drug_auth.service;

import dev.benswift.back_drug_auth.model.*;
import dev.benswift.back_drug_auth.model.view_model.TransactionViewModel;
import dev.benswift.back_drug_auth.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FormePharmaceutiqueRepository formePharmaceutiqueRepository;
    @Autowired
    BoiteRepository boiteRepository;
    @Autowired
    DistributeurRepository distributeurRepository;

    public ResponseEntity<?> addCommande(TransactionViewModel viewModel,
                                         double longitude,
                                         double lattitude,
                                         Long distributeurId,
                                         Principal principal)
    {
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        if(user == null)
            return ResponseEntity.badRequest().body(new RuntimeException("'erreur': aucun utilisateur"));
        Transaction t = new Transaction();
        List<BoiteMedicament> boites = new ArrayList<>();
        viewModel.getBoites().forEach(e ->{
            BoiteMedicament b = new BoiteMedicament();
            b.setCode(e.getCode());
            b.setDateExpiration(LocalDate.parse(e.getDateExpiration()));
            b.setDateFabrication(LocalDate.parse(e.getDateFabrication()));
            b.setForme(formePharmaceutiqueRepository.getById(e.getForme()));
            boites.add(b);
        });
        if(!boites.isEmpty())
        {
            Localisation l = new Localisation();
            l.setLongitude(longitude);
            l.setLatitude(lattitude);
            t.setBoiteMedicaments(boites);
            t.setStatus(false);
            t.setDate(LocalDate.now());
            t.setUser(user);
            t.setDistributeur(distributeurRepository.getById(distributeurId));
            t.setLocalisation(l);
            t.setType("Commande");
            System.out.println(t);
        }
        return ResponseEntity.ok().body(transactionRepository.save(t));
    }

    public Transaction add(Transaction transaction, Principal principal)
    {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + principal.getName()));;
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    public Transaction validate(Transaction transaction)
    {
        Transaction existingTransaction = transactionRepository.findById(transaction.getId()).orElse(null);
        if (existingTransaction != null && !existingTransaction.getStatus()) {
            existingTransaction.setStatus(!transaction.getStatus());
            return transactionRepository.save(existingTransaction);
        }
        return null;
    }
    public List<Transaction> getAllByDistributeur(Distributeur distributeur)
    {
        return transactionRepository.findAllByDistributeur(distributeur);
    }
    public List<Transaction> getAllByDistributeur(User user)
    {
        return transactionRepository.findAllByUser(user);
    }


    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }


    public List<Transaction> remove(Transaction transaction) {
        transactionRepository.delete(transaction);
        return transactionRepository.findAll();
    }
}

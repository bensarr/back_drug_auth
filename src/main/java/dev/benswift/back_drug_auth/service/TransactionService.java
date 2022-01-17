package dev.benswift.back_drug_auth.service;

import dev.benswift.back_drug_auth.model.*;
import dev.benswift.back_drug_auth.repository.TransactionRepository;
import dev.benswift.back_drug_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

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
    public List<Transaction> getAllByBoite(BoiteMedicament boiteMedicament)
    {
        return transactionRepository.findAllByBoiteMedicament(boiteMedicament);
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

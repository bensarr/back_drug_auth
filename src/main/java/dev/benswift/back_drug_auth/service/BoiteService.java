package dev.benswift.back_drug_auth.service;

import dev.benswift.back_drug_auth.model.BoiteMedicament;
import dev.benswift.back_drug_auth.model.FormePharmaceutique;
import dev.benswift.back_drug_auth.model.Transaction;
import dev.benswift.back_drug_auth.repository.BoiteRepository;
import dev.benswift.back_drug_auth.repository.FormePharmaceutiqueRepository;
import dev.benswift.back_drug_auth.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BoiteService {
    @Autowired
    BoiteRepository boiteRepository;
    @Autowired
    FormePharmaceutiqueRepository formePharmaceutiqueRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity<List<BoiteMedicament>> allByTransactionCode(String code)
    {
        Transaction transaction = transactionRepository.findByCode(code);
        return ResponseEntity.ok().body(transaction.getBoiteMedicaments());
    }

    public BoiteMedicament add(BoiteMedicament boiteMedicament) {
        return boiteRepository.save(boiteMedicament);
    }

    public ResponseEntity<List<BoiteMedicament>> getAll() {
        return ResponseEntity.ok(boiteRepository.findAll());
    }

    public ResponseEntity<BoiteMedicament> getById(Long id) {
        return ResponseEntity.ok(boiteRepository.findById(id).orElse(null));
    }

    public ResponseEntity<String> isSure(String code){
        String response = "";
        BoiteMedicament bm = this.isSureCode(code);
        if(bm != null){
            if(isSureNotExpired(bm))
                response += "1";
            else
                response += "0";
            if(isSureForme(bm))
                response += "1";
            else
                response += "0";
        }
        return ResponseEntity.ok(response);
    }

    private BoiteMedicament isSureCode(String code) {
        return boiteRepository.findByCode(code);
    }

    private boolean isSureNotExpired(BoiteMedicament boiteMedicament) {
        return boiteMedicament.getDateExpiration().isAfter(LocalDate.now());
    }

    private boolean isSureForme(BoiteMedicament boiteMedicament) {
        return boiteMedicament.getForme().getStatus();
    }

    public List<BoiteMedicament> remove(BoiteMedicament boiteMedicament) {
        boiteRepository.delete(boiteMedicament);
        return boiteRepository.findAll();
    }
}

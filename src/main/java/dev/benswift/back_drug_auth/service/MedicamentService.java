package dev.benswift.back_drug_auth.service;

import dev.benswift.back_drug_auth.model.FormePharmaceutique;
import dev.benswift.back_drug_auth.model.Medicament;
import dev.benswift.back_drug_auth.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class MedicamentService {
    @Autowired
    MedicamentRepository medicamentRepository;

    public ResponseEntity<Medicament> add(Medicament medicament) {
        return ResponseEntity.ok(medicamentRepository.save(medicament));
    }

    public Medicament changeEtat(Medicament medicament)
    {
        Medicament existingMedicament = medicamentRepository.findById(medicament.getId()).orElse(null);
        if (existingMedicament != null) {
            existingMedicament.setStatus(!medicament.getStatus());
            return medicamentRepository.save(existingMedicament);
        }
        return null;
    }

    public ResponseEntity<List<Medicament>> getAll() {
        List<Medicament> list = medicamentRepository.findAll();
        list.forEach(e ->{
            e.setStatus(e.getFormePharmaceutiques().stream().noneMatch(FormePharmaceutique::getStatus));
            System.out.println(e);
        } );
        return ResponseEntity.ok(list);
    }

    public Medicament getById(Long id) {
        return medicamentRepository.findById(id).orElse(null);
    }


    public List<Medicament> remove(Medicament medicament) {
        medicamentRepository.delete(medicament);
        return medicamentRepository.findAll();
    }
}

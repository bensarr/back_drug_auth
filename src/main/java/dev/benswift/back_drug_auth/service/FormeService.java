package dev.benswift.back_drug_auth.service;

import dev.benswift.back_drug_auth.model.FormePharmaceutique;
import dev.benswift.back_drug_auth.model.Medicament;
import dev.benswift.back_drug_auth.repository.FormePharmaceutiqueRepository;
import dev.benswift.back_drug_auth.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormeService {
    @Autowired
    FormePharmaceutiqueRepository formePharmaceutiqueRepository;
    @Autowired
    MedicamentRepository medicamentRepository;

    public ResponseEntity<FormePharmaceutique> add(FormePharmaceutique formePharmaceutique, Long medicamentId) {
        Medicament medicament = medicamentRepository.findById(medicamentId).orElse(null);
        formePharmaceutique.setMedicament(medicament);
        return ResponseEntity.ok(formePharmaceutiqueRepository.save(formePharmaceutique));
    }

    public FormePharmaceutique changeEtat(FormePharmaceutique formePharmaceutique)
    {
        FormePharmaceutique existingMedicament = formePharmaceutiqueRepository.findById(formePharmaceutique.getId()).orElse(null);
        if (existingMedicament != null) {
            existingMedicament.setStatus(!formePharmaceutique.getStatus());
            return formePharmaceutiqueRepository.save(existingMedicament);
        }
        return null;
    }

    public ResponseEntity<List<FormePharmaceutique>> getAll() {
        return ResponseEntity.ok(formePharmaceutiqueRepository.findAll());
    }

    public ResponseEntity<List<FormePharmaceutique>> getAllByMedecin(Long medicamentId) {
        Medicament medicament = medicamentRepository.findById(medicamentId).orElse(null);
        return ResponseEntity.ok(formePharmaceutiqueRepository.findAllByMedicament(medicament));
    }

    public FormePharmaceutique getById(Long id) {
        return formePharmaceutiqueRepository.findById(id).orElse(null);
    }


    public List<FormePharmaceutique> remove(FormePharmaceutique formePharmaceutique) {
        formePharmaceutiqueRepository.delete(formePharmaceutique);
        return formePharmaceutiqueRepository.findAll();
    }
}

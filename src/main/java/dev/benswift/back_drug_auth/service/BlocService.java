package dev.benswift.back_drug_auth.service;

import dev.benswift.back_drug_auth.model.BlocMedicament;
import dev.benswift.back_drug_auth.repository.BlocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlocService {
    @Autowired
    BlocRepository blocRepository;

    public BlocMedicament add(BlocMedicament blocMedicament) {
        return blocRepository.save(blocMedicament);
    }

    public List<BlocMedicament> getAll() {
        return blocRepository.findAll();
    }

    public BlocMedicament getById(Long id) {
        return blocRepository.findById(id).orElse(null);
    }


    public List<BlocMedicament> remove(BlocMedicament blocMedicament) {
        blocRepository.delete(blocMedicament);
        return blocRepository.findAll();
    }
}

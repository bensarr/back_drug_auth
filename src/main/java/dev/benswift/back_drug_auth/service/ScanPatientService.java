package dev.benswift.back_drug_auth.service;

import dev.benswift.back_drug_auth.model.*;
import dev.benswift.back_drug_auth.repository.ScanPatientRepository;
import dev.benswift.back_drug_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ScanPatientService {
    @Autowired
    ScanPatientRepository scanPatientRepository;
    @Autowired
    UserRepository userRepository;

    public ScanPatient add(ScanPatient scanPatient, Principal principal)
    {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + principal.getName()));;
        scanPatient.setUser(user);
        return scanPatientRepository.save(scanPatient);
    }
    public List<ScanPatient> getAllByUser(User user)
    {
        return scanPatientRepository.findAllByUser(user);
    }
    public List<ScanPatient> getAllByBoite(BoiteMedicament boiteMedicament)
    {
        return scanPatientRepository.findAllByBoiteMedicament(boiteMedicament);
    }
}

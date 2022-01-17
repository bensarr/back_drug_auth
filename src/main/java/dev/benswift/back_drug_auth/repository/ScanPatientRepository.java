package dev.benswift.back_drug_auth.repository;

import dev.benswift.back_drug_auth.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScanPatientRepository extends JpaRepository<ScanPatient,Long> {
    List<ScanPatient> findAllByUser(User utilisateur);
    List<ScanPatient> findAllByBoiteMedicament(BoiteMedicament boiteMedicament);

}

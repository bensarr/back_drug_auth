package dev.benswift.back_drug_auth.repository;

import dev.benswift.back_drug_auth.model.FormePharmaceutique;
import dev.benswift.back_drug_auth.model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormePharmaceutiqueRepository extends JpaRepository<FormePharmaceutique,Long> {
    List<FormePharmaceutique> findAllByMedicament(Medicament medicament);
}

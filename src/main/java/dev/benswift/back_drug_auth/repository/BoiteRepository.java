package dev.benswift.back_drug_auth.repository;

import dev.benswift.back_drug_auth.model.BoiteMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoiteRepository extends JpaRepository<BoiteMedicament,Long> {
    BoiteMedicament findByCode(String code);
}

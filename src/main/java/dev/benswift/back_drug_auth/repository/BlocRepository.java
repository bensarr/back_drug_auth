package dev.benswift.back_drug_auth.repository;

import dev.benswift.back_drug_auth.model.BlocMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocRepository extends JpaRepository<BlocMedicament,Long> {
}

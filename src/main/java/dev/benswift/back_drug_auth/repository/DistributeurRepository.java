package dev.benswift.back_drug_auth.repository;

import dev.benswift.back_drug_auth.model.Distributeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributeurRepository extends JpaRepository<Distributeur,Long> {
    Boolean existsByEmail(String email);
    Boolean existsByDenomination(String denomination);
    List<Distributeur> findAllByType(String type);
    List<Distributeur> findAllByPaysAndTypeAndEnabled(String pays,String type, boolean enabled);
}

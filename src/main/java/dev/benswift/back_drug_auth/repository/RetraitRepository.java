package dev.benswift.back_drug_auth.repository;

import dev.benswift.back_drug_auth.model.Retrait;
import dev.benswift.back_drug_auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetraitRepository extends JpaRepository<Retrait,Long> {
    List<Retrait> findAllByUser(User utilisateur);

}

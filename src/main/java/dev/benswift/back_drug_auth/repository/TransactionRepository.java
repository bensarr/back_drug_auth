package dev.benswift.back_drug_auth.repository;

import dev.benswift.back_drug_auth.model.BoiteMedicament;
import dev.benswift.back_drug_auth.model.Distributeur;
import dev.benswift.back_drug_auth.model.Transaction;
import dev.benswift.back_drug_auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByDistributeur(Distributeur distributeur);
    List<Transaction> findAllByUser(User utilisateur);
    List<Transaction> findAllByBoiteMedicament(BoiteMedicament boiteMedicament);

}

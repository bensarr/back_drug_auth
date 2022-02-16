package dev.benswift.back_drug_auth.repository;

import dev.benswift.back_drug_auth.model.Transaction;
import dev.benswift.back_drug_auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("select t from Transaction t where t.distributeur.id = ?1")
    List<Transaction> findAllByDistributeurId(Long id);
    List<Transaction> findAllByUser(User utilisateur);
    Transaction findByCode(String code);

}

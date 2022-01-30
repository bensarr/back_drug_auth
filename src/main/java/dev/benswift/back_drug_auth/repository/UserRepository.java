package dev.benswift.back_drug_auth.repository;
import java.util.List;
import java.util.Optional;

import dev.benswift.back_drug_auth.model.Distributeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.benswift.back_drug_auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	List<User> findAllByDistributeur(Distributeur distributeur);
}

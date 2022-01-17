package dev.benswift.back_drug_auth.repository;
import java.util.List;
import java.util.Optional;

import dev.benswift.back_drug_auth.model.Distributeur;
import dev.benswift.back_drug_auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.benswift.back_drug_auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query("select u from User u where u.distributeur = ?1 and u.roles = ?2")
	List<User> findAllByDistributeurAndRoles(Distributeur distributeur, List<Role> roles);
}

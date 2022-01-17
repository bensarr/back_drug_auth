package dev.benswift.back_drug_auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.benswift.back_drug_auth.model.enumerations.ERole;
import dev.benswift.back_drug_auth.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}

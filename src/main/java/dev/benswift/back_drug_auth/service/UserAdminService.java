package dev.benswift.back_drug_auth.service;

import dev.benswift.back_drug_auth.model.Role;
import dev.benswift.back_drug_auth.model.User;
import dev.benswift.back_drug_auth.model.enumerations.ERole;
import dev.benswift.back_drug_auth.payload.response.JwtResponse;
import dev.benswift.back_drug_auth.repository.RoleRepository;
import dev.benswift.back_drug_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAdminService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    private JwtResponse getAdmin(Principal principal)
    {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(r ->
                roles.add(r.getName().name())
        );
        return new JwtResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled(),
                user.getNom(),
                user.getPrenom(),
                user.getPays(),
                user.getDateInscribtion(),
                user.getDistributeur(),
                roles);
    }

    public ResponseEntity<List<JwtResponse>> getAll(Principal principal)
    {
        JwtResponse admin = getAdmin(principal);
        List<JwtResponse> list = new ArrayList<>();
        if(admin.hasRole("ROLE_ADMIN_PLATEFORME"))
        {
            System.out.println("xxx");
        }
        if(admin.hasRole("ROLE_ADMIN"))
        {
            List<User> users = userRepository.findAllByDistributeurAndRoles(
                    admin.getDistributeur(), this.getRolesByERoleName(ERole.ROLE_AGENT)
            );
            if(!users.isEmpty())
                users.forEach(u ->
                        list.add(new JwtResponse(
                                u.getId(), u.getUsername(),
                                u.getEmail(), u.isEnabled())
                        ));
        }
        return ResponseEntity.ok(list);
    }
    private List<Role> getRolesByERoleName(ERole r)
    {
        Role role = roleRepository.findByName(r)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }
}

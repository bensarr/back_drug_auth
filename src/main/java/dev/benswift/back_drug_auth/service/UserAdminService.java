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
import java.util.stream.Collectors;

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
            List<User> users = userRepository.findAllByDistributeur(admin.getDistributeur());
            if(!users.isEmpty())
                users.forEach(u -> {
                    List<String> stringRoles = new ArrayList<>();
                    Role r = u.getRoles().stream().findFirst()
                            .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                    stringRoles.add(r.getName().name());
                    list.add(new JwtResponse(
                            u.getId(),
                            u.getUsername(),
                            u.getEmail(),
                            u.isEnabled(),
                            u.getNom(),
                            u.getPrenom(),
                            u.getPays(),
                            u.getDateInscribtion(),
                            u.getDistributeur(),
                            stringRoles)
                    );
                });
        }
        if(admin.hasRole("ROLE_ADMIN"))
        {
            List<String> stringRoles = new ArrayList<>();
            List<User> users = userRepository.findAllByDistributeur(admin.getDistributeur())
                    .stream().filter(e -> {
                        Role r =e.getRoles().stream().findFirst()
                                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                        String roleName = r.getName().name();
                        //stringRoles.add(roleName);
                        return roleName.compareTo(ERole.ROLE_AGENT.name()) == 0;
                    }).collect(Collectors.toList());
            stringRoles.add("ROLE_AGENT");
            if(!users.isEmpty())
                users.forEach(u ->
                        list.add(new JwtResponse(
                                u.getId(),
                                u.getUsername(),
                                u.getEmail(),
                                u.isEnabled(),
                                u.getNom(),
                                u.getPrenom(),
                                u.getPays(),
                                u.getDateInscribtion(),
                                u.getDistributeur(),
                                stringRoles)
                        ));
        }
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<?> lock(Long userId)
    {
        User user = userRepository.getById(userId);
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
        return ResponseEntity.ok("User Edit successed");
    }

}

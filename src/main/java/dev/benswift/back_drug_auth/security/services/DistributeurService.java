package dev.benswift.back_drug_auth.security.services;

import dev.benswift.back_drug_auth.model.Distributeur;
import dev.benswift.back_drug_auth.model.Role;
import dev.benswift.back_drug_auth.model.User;
import dev.benswift.back_drug_auth.model.enumerations.ERole;
import dev.benswift.back_drug_auth.model.enumerations.ETypeDistributeur;
import dev.benswift.back_drug_auth.payload.request.RegisterDistributeurRequest;
import dev.benswift.back_drug_auth.payload.response.MessageResponse;
import dev.benswift.back_drug_auth.repository.DistributeurRepository;
import dev.benswift.back_drug_auth.repository.RoleRepository;
import dev.benswift.back_drug_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class DistributeurService {
    @Autowired
    DistributeurRepository distributeurRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    //Destination upload
    private static String UPLOADED_IMAGES = "C://Tools//drug_auth//licences";

    /**
     * Registration for distributeur by creating at the same time the Administrator
     * @param registerDistributeurRequest
     * @return
     */
    public ResponseEntity<?> add(RegisterDistributeurRequest registerDistributeurRequest)
    {
        byte[] bytes = {};
        Path path = null;
        if (userRepository.existsByUsername(registerDistributeurRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username for Admin Account is already taken!"));
        }

        if (userRepository.existsByEmail(registerDistributeurRequest.getEmail()) || distributeurRepository.existsByEmail(registerDistributeurRequest.getEmailDistributeur())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error:One of Emails are already in use!"));
        }
        if (distributeurRepository.existsByDenomination(registerDistributeurRequest.getDenomination())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: denomination is already taken!"));
        }
        /*// Upload process
        try {

            if(!registerDistributeurRequest.getFiles()[0].getName().equals("")){
                MultipartFile file = registerDistributeurRequest.getFiles()[0];
                bytes = file.getBytes();
                path = Paths.get(UPLOADED_IMAGES +registerDistributeurRequest.getDenomination()+ file.getOriginalFilename());
                registerDistributeurRequest.setLicence(file.getOriginalFilename());
            }
            else{
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Licence upload can't be done!"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
        Distributeur distributeur = distributeurRepository.save(
                Distributeur.builder()
                        .denomination(registerDistributeurRequest.getDenomination())
                        .email(registerDistributeurRequest.getEmailDistributeur())
                        .adresse(registerDistributeurRequest.getAdresseDistributeur())
                        .telephone(registerDistributeurRequest.getTelephoneDistributeur())
                        .type(registerDistributeurRequest.getTypeDistributeur())
                        .pays(registerDistributeurRequest.getPaysDistributeur())
                        .enabled(false)
                        //.licence(registerDistributeurRequest.getLicence())
                        .build()
        );

        Set<Role> roles = this.initRole(ERole.ROLE_ADMIN);

        User user = new User(registerDistributeurRequest.getUsername(),
                registerDistributeurRequest.getEmail(),
                encoder.encode(registerDistributeurRequest.getPassword()),
                false,
                registerDistributeurRequest.getNom(),
                registerDistributeurRequest.getPrenom(),
                registerDistributeurRequest.getPays(),
                distributeur
        );
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Distribution registered successfully with Admin Account!"));
    }
    public ResponseEntity<List<Distributeur>> getAll()
    {
        return ResponseEntity.ok(distributeurRepository.findAll());
    }
    public ResponseEntity<List<Distributeur>> getAllByPaysTypeDistributeurAndEnabled(String pays, String type)
    {
        return ResponseEntity.ok(distributeurRepository.findAllByPaysAndTypeAndEnabled(pays, type, true));
    }
    public ResponseEntity<List<Distributeur>> getAllDistributeurs()
    {
        return ResponseEntity.ok(distributeurRepository.findAll());
    }
    private Set<Role> initRole(ERole eRole)
    {
        Set<Role> roles = new HashSet<>();
        roles.add(
                roleRepository.findByName(eRole)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."))
        );
        return roles;
    }
    public ResponseEntity<List<ETypeDistributeur>> listTypeDistributeur(){
        return ResponseEntity.ok(Arrays.asList(new ETypeDistributeur[]{ETypeDistributeur.Grossiste,ETypeDistributeur.Officine}));
    }

    public ResponseEntity<List<String>> listCountries(){
        List<String> list = new ArrayList<>();
        List<String> countries = Arrays.asList(Locale.getISOCountries());
        countries.forEach(c -> list.add(new Locale("fr",c).getDisplayCountry()));
        return ResponseEntity.ok(list);
        //return ResponseEntity.ok(Arrays.asList("Sénégal", "Gambie"));
    }
}

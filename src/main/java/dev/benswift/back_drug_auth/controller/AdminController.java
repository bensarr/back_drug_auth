package dev.benswift.back_drug_auth.controller;

import dev.benswift.back_drug_auth.model.Distributeur;
import dev.benswift.back_drug_auth.model.FormePharmaceutique;
import dev.benswift.back_drug_auth.model.Medicament;
import dev.benswift.back_drug_auth.payload.response.JwtResponse;
import dev.benswift.back_drug_auth.security.services.DistributeurService;
import dev.benswift.back_drug_auth.service.FormeService;
import dev.benswift.back_drug_auth.service.MedicamentService;
import dev.benswift.back_drug_auth.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@PreAuthorize("hasAuthority('ROLE_ADMIN_PLATEFORME')")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    DistributeurService distributeurService;
    @Autowired
    MedicamentService medicamentService;
    @Autowired
    FormeService formeService;
    @Autowired
    UserAdminService userAdminService;

    @GetMapping("/distributeurs")
    public ResponseEntity<List<Distributeur>> distributeurs()
    {
        return distributeurService.getAllDistributeurs();
    }

    @PreAuthorize("hasAnyRole('ROLE_AGENT','ROLE_ADMIN','ROLE_ADMIN_PLATEFORME')")
    @GetMapping("/medicaments")
    public ResponseEntity<List<Medicament>> medicaments(){
        return medicamentService.getAll();
    }

    @PostMapping("/medicaments/add")
    public ResponseEntity<Medicament> addMedicament(@Valid @RequestBody Medicament medicament)
    {
        return medicamentService.add(medicament);
    }

    @PreAuthorize("hasAnyRole('ROLE_AGENT','ROLE_ADMIN','ROLE_ADMIN_PLATEFORME')")
    @GetMapping("/formes")
    public ResponseEntity<List<FormePharmaceutique>> formes(@RequestParam("medicament") String medicamentId){
        Long id = Long.valueOf(medicamentId);
        return formeService.getAllByMedecin(id);
    }
    @PostMapping("/formes/add")
    public ResponseEntity<FormePharmaceutique> addForme(@Valid @RequestBody FormePharmaceutique formePharmaceutique,
                                                        @RequestParam("medicament") String medicamentId)
    {
        Long id = Long.valueOf(medicamentId);
        return formeService.add(formePharmaceutique,id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN_PLATEFORME','ROLE_ADMIN')")
    @GetMapping("/utilisateurs")
    public ResponseEntity<List<JwtResponse>> utilisateurs(Principal principal)
    {
        return userAdminService.getAll(principal);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN_PLATEFORME','ROLE_ADMIN')")
    @PostMapping("/utilisateurs")
    public ResponseEntity<?> lock(@RequestParam("user") String userId)
    {
        Long id = Long.valueOf(userId);
        return userAdminService.lock(id);
    }

}

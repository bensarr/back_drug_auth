package dev.benswift.back_drug_auth.controller;

import dev.benswift.back_drug_auth.model.Distributeur;
import dev.benswift.back_drug_auth.model.enumerations.ETypeDistributeur;
import dev.benswift.back_drug_auth.payload.request.RegisterDistributeurRequest;
import dev.benswift.back_drug_auth.security.services.DistributeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@PreAuthorize("hasAuthority('ROLE_ADMIN_PLATEFORME')")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/distributeur")
public class DistributeurController {

    @Autowired
    DistributeurService distributeurService;

    @PostMapping("/register")
    public ResponseEntity<?> registerDistributeur(@Valid @RequestBody RegisterDistributeurRequest registerDistributeurRequest) {
        return distributeurService.add(registerDistributeurRequest);
    }
    @GetMapping("/types")
    public ResponseEntity<List<ETypeDistributeur>> typeDistributeur()
    {
        return distributeurService.listTypeDistributeur();
    }
    @GetMapping("/countries")
    public ResponseEntity<List<String>> countries()
    {
        return distributeurService.listCountries();
    }

    @GetMapping("")
    public ResponseEntity<List<Distributeur>> distributeursListForRegisterUser(@RequestParam("pays") String pays, @RequestParam("type") String type)
    {
        return distributeurService.getAllByPaysTypeDistributeurAndEnabled(pays, type);
    }
}

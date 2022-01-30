package dev.benswift.back_drug_auth.controller;

import dev.benswift.back_drug_auth.model.view_model.TransactionViewModel;
import dev.benswift.back_drug_auth.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@PreAuthorize("hasAuthority('ROLE_AGENT')")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AgentController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/add-commande")
    public ResponseEntity<?> addCommande(@RequestBody TransactionViewModel viewModel,
                                         @RequestParam("longitude") String longitude,
                                         @RequestParam("lattitude") String lattitude,
                                         @RequestParam("distributeur") String distributeur,
                                         Principal principal)
    {
        double longi = Double.parseDouble(longitude);
        double lat = Double.parseDouble(lattitude);
        Long distributeurId = Long.valueOf(distributeur);
        System.out.println(viewModel);
        return transactionService.addCommande(viewModel, longi, lat, distributeurId, principal);
    }
}

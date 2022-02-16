package dev.benswift.back_drug_auth.controller;
import dev.benswift.back_drug_auth.model.Transaction;
import dev.benswift.back_drug_auth.service.BoiteService;
import dev.benswift.back_drug_auth.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@PreAuthorize("hasAuthority('ROLE_USER')")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    BoiteService boiteService;
    @Autowired
    TransactionService transactionService;
}

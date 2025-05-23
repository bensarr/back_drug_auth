package dev.benswift.back_drug_auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('AGENT') or hasRole('ADMINPLATEFORME') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/agent")
	@PreAuthorize("hasRole('AGENT')")
	public String vendeurAccess() {
		return "Agent Board.";
	}
	
	@GetMapping("/adminplateforme")
	@PreAuthorize("hasRole('ADMINPLATEFORME')")
	public String pointRelaisAccess() {
		return "Admin Plateforme Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
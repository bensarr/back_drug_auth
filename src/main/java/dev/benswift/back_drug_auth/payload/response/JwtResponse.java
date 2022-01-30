package dev.benswift.back_drug_auth.payload.response;

import dev.benswift.back_drug_auth.model.Distributeur;

import java.time.LocalDateTime;
import java.util.List;

public class JwtResponse{
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private String nom;
	private String prenom;
	private String pays;
	private boolean enabled;
	private LocalDateTime dateInscription;
	private Distributeur distributeur;

	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, String email,
					   boolean enabled,String nom, String prenom, String pays,
					   LocalDateTime dateInscription,Distributeur distributeur, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.enabled = enabled;
		this.nom = nom;
		this.prenom = prenom;
		this.pays = pays;
		this.dateInscription = dateInscription;
		this.distributeur = distributeur;
		this.roles = roles;
	}

	public JwtResponse(Long id, String username, String email,
					   boolean enabled,String nom, String prenom, String pays,
					   LocalDateTime dateInscription,Distributeur distributeur, List<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.enabled = enabled;
		this.nom = nom;
		this.prenom = prenom;
		this.pays = pays;
		this.dateInscription = dateInscription;
		this.distributeur = distributeur;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPays() {
		return pays;
	}

	public LocalDateTime getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDateTime dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Distributeur getDistributeur() {
		return distributeur;
	}

	public void setDistributeur(Distributeur distributeur) {
		this.distributeur = distributeur;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getRoles() {
		return roles;
	}

	public boolean hasRole(String roleName)
	{
		if(this.roles.contains(roleName))
			return true;
		return false;
	}
}

package dev.benswift.back_drug_auth.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 50)
	private String nom;

	@NotBlank
	@Size(max = 50)
	private String prenom;

	@NotBlank
	@Size(max = 50)
	private String pays;

	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dateInscribtion;

	@ManyToOne
	@JoinColumn(name = "distributeur_id", nullable = true)
	private Distributeur distributeur;

	@OneToMany(orphanRemoval = true)
	@JoinColumn
	private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn
    private List<ScanPatient> scanPatients = new ArrayList<>();

	@OneToMany(orphanRemoval = true)
	@JoinColumn
	private List<Retrait> retraits = new ArrayList<>();

	public List<Retrait> getRetraits() {
		return retraits;
	}

	public void setRetraits(List<Retrait> retraits) {
		this.retraits = retraits;
	}

	public List<ScanPatient> getScanPatients() {
        return scanPatients;
    }

    public void setScanPatients(List<ScanPatient> scanPatients) {
        this.scanPatients = scanPatients;
    }

    public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Distributeur getDistributeur() {
		return distributeur;
	}

	public void setDistributeur(Distributeur distributeur) {
		this.distributeur = distributeur;
	}

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User(String username, String email, String password, boolean enabled, String nom, String prenom, String pays) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.pays = pays;
		this.enabled = enabled;
		this.dateInscribtion = LocalDateTime.now();
	}
	public User(String username, String email, String password, boolean enabled, String nom, String prenom,String pays,Distributeur distributeur) {
		this(username, email, password,enabled, nom, prenom, pays);
		this.distributeur = distributeur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
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

	public LocalDateTime getDateInscribtion() {
		return this.dateInscribtion;
	}

	public void setDateInscribtion(LocalDateTime dateInscribtion) {
		this.dateInscribtion = dateInscribtion;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}

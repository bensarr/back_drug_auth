package dev.benswift.back_drug_auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoiteMedicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String code;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFabrication;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateExpiration;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "forme_id", nullable = false)
    private FormePharmaceutique forme;

    @OneToMany(orphanRemoval = true)
    @JoinColumn
    private List<ScanPatient> scanPatients = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn
    private List<Retrait> retraits = new ArrayList<>();

    @ManyToMany(mappedBy = "boiteMedicaments")
    private Collection<Transaction> transactions;

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<Transaction> transactions) {
        this.transactions = transactions;
    }
}

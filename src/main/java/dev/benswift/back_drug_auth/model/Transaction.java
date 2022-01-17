package dev.benswift.back_drug_auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "boite_id", nullable = false)
    private BoiteMedicament boiteMedicament;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "distributeur_id")
    private Distributeur distributeur;

    @JsonIgnore
    @OneToOne(mappedBy = "transaction")
    private Localisation localisation;

}

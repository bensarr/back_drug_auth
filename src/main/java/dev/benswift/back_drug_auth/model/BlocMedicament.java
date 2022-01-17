package dev.benswift.back_drug_auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlocMedicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ToString.Exclude
    private List<BoiteMedicament> boiteMedicaments = new ArrayList<>();

}

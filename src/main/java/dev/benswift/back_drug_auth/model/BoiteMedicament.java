package dev.benswift.back_drug_auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
public class BoiteMedicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String code;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bloc_id", nullable = false)
    private BlocMedicament bloc;

    @OneToMany(orphanRemoval = true)
    @JoinColumn
    @ToString.Exclude
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn
    private List<ScanPatient> scanPatients = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn
    private List<Retrait> retraits = new ArrayList<>();

}

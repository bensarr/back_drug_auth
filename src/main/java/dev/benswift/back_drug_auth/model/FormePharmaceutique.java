package dev.benswift.back_drug_auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormePharmaceutique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String forme;

    @NotBlank
    @Size(max = 100)
    private String dosage;

    @NotBlank
    @Size(max = 100)
    private String uniteDosage;

    @NotBlank
    @Size(max = 100)
    private String contenance;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "medicament_id", nullable = false)
    private Medicament medicament;

    @OneToMany(orphanRemoval = true)
    @JoinColumn
    private List<BlocMedicament> blocMedicaments = new ArrayList<>();

}

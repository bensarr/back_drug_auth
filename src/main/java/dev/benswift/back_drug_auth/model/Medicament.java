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
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String denomination;

    @NotBlank
    @Size(max = 100)
    private String laboratoire;

    @Transient
    private Boolean status;

    @JsonIgnore
    @OneToMany(orphanRemoval = true)
    @JoinColumn
    @ToString.Exclude
    private List<FormePharmaceutique> formePharmaceutiques = new ArrayList<>();

}

package dev.benswift.back_drug_auth.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Localisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "localisation_scanpatient",
            joinColumns = @JoinColumn(name = "localisation_null"),
            inverseJoinColumns = @JoinColumn(name = "scanpatient_id"))
    private ScanPatient scanpatient;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "localisation_retrait",
            joinColumns = @JoinColumn(name = "localisation_null"),
            inverseJoinColumns = @JoinColumn(name = "retrait_id"))
    private Retrait retrait;

}

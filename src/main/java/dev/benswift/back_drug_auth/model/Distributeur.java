package dev.benswift.back_drug_auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Distributeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String denomination;

    @NotBlank
    @Size(max = 100)
    private String adresse;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 50)
    private String telephone;

    @NotBlank
    @Size(max = 50)
    private String type;

    @NotBlank
    @Size(max = 50)
    private String pays;

    private Boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "distributeur")
    @ToString.Exclude
    private List<User> users;

    @OneToMany(orphanRemoval = true)
    @JoinColumn
    private List<Transaction> transactions = new ArrayList<>();

    /*
    @Column(length = 50)
    private String licence;
    @Transient
    private MultipartFile[] files;*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Distributeur that = (Distributeur) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

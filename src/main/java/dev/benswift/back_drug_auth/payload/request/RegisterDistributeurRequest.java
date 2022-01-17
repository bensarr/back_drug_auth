package dev.benswift.back_drug_auth.payload.request;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.*;

public class RegisterDistributeurRequest extends SignupRequest {
    @NotBlank
    @Size(min = 3, max = 100)
    private String denomination;

    @NotBlank
    @Size(max = 100)
    private String adresseDistributeur;

    @NotBlank
    @Size(max = 50)
    private String telephoneDistributeur;

    @NotBlank
    @Size(max = 50)
    @Email
    private String emailDistributeur;

    @NotBlank
    @Size(max = 50)
    private String typeDistributeur;

    @NotBlank
    @Size(max = 50)
    private String paysDistributeur;
    /*
    @Column(length = 50)
    private String licence;
    @Transient
    private MultipartFile[] files;*/

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getAdresseDistributeur() {
        return adresseDistributeur;
    }

    public void setAdresseDistributeur(String adresseDistributeur) {
        this.adresseDistributeur = adresseDistributeur;
    }

    public String getTelephoneDistributeur() {
        return telephoneDistributeur;
    }

    public void setTelephoneDistributeur(String telephoneDistributeur) {
        this.telephoneDistributeur = telephoneDistributeur;
    }

    public String getEmailDistributeur() {
        return emailDistributeur;
    }

    public void setEmailDistributeur(String emailDistributeur) {
        this.emailDistributeur = emailDistributeur;
    }

    public String getTypeDistributeur() {
        return typeDistributeur;
    }

    public void setTypeDistributeur(String typeDistributeur) {
        this.typeDistributeur = typeDistributeur;
    }

    public String getPaysDistributeur() {
        return paysDistributeur;
    }

    public void setPaysDistributeur(String paysDistributeur) {
        this.paysDistributeur = paysDistributeur;
    }
    /*
    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }*/
}

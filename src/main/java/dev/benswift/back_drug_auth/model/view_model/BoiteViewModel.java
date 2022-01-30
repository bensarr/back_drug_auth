package dev.benswift.back_drug_auth.model.view_model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoiteViewModel {
    private String code;
    private Long forme;
    private String dateFabrication;
    private String dateExpiration;
}

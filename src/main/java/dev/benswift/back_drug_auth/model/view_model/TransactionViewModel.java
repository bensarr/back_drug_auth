package dev.benswift.back_drug_auth.model.view_model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionViewModel {
    private List<BoiteViewModel> boites;
}

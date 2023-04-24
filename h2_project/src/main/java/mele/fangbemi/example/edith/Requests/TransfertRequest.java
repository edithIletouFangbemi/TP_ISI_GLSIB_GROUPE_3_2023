package mele.fangbemi.example.edith.Requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class TransfertRequest {
    @NotEmpty(message = "le numéro du compte emetteur ne doit pas être vide ou nul, verifiez!")
    private String compteEmetteur;
    @NotEmpty(message = "le numéro du compte recepteur ne doit pas être vide ou nul, verifiez!")
    private String compteRecepteur;
    @NotEmpty(message = "le montant ne peut pas vide ou nul, verifiez svp!")
    private double montant;
}

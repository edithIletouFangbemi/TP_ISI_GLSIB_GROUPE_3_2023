package mele.fangbemi.example.edith.Requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class depotRequest {
    @NotEmpty(message = "le numéro de compte ne peut pas être nul ou vide, verifiez!")
    private String numeroCompte;
    //@NotEmpty(message = "fournissez le montant svp!")
    private double montant;
}

package mele.fangbemi.example.edith.Requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class compteRequest {
    @NotEmpty(message = "le type de compte ne peut pas être nul, verifiez svp!")
    private String typeCompte;
    //@NotEmpty(message = "l'identifiant du client ne peut pas être nul, verifiez svp!")
    private Long idClient;
}

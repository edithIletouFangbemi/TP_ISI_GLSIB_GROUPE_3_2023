package mele.fangbemi.example.edith.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "vous devez entrer le nom")
    private String nom;
    @NotBlank(message = "vous devez entrer vos prenoms")
    private String prenom;
    @NotBlank(message = "vous devez entrer votre nationalité")
    private String nationalite;
    private LocalDate dateNaiss;
    @NotBlank(message = "vous devez entrer une adresse")
    private String adresse;
    @NotBlank(message = "vous devez entrer une adresse email")
    @Column(unique = true)
    private String courriel;
    @NotBlank(message ="entrer un numéro de téléphone" )
   // @Min(value = 8,message = "le nombre de chiffre doit atteindre au moins 8")
    private String telephone;
    private String sexe;
     @JsonIgnoreProperties
     @OneToMany(targetEntity = Compte.class, cascade = CascadeType.ALL)
     @JoinColumn(name = "idClient",referencedColumnName = "id")
     private List<Compte> comptes;
     @JsonIgnoreProperties
     private boolean status = true;

}

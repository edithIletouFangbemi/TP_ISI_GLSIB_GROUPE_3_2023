package mele.fangbemi.example.edith.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;

import static java.time.LocalDateTime.now;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numeroCompte;

    private Double solde;

    private TypeCompte typeCompte;
    private LocalDateTime dateCreation;

    private Long idClient;
    private boolean status;







}

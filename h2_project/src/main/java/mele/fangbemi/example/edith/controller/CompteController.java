package mele.fangbemi.example.edith.controller;

import jakarta.validation.Valid;
import mele.fangbemi.example.edith.Requests.TransfertRequest;
import mele.fangbemi.example.edith.Requests.compteRequest;
import mele.fangbemi.example.edith.Requests.depotRequest;
import mele.fangbemi.example.edith.entity.Compte;
import mele.fangbemi.example.edith.entity.Response;
import mele.fangbemi.example.edith.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/ega")
public class CompteController {
    private CompteService compteService;
    @Autowired
    public CompteController(CompteService compteService){this.compteService = compteService;}
    @PostMapping("/creer-compte")
    public ResponseEntity<Compte> saveCompte(@RequestBody @Valid Compte compte) throws IOException {
        return ResponseEntity.status(CREATED).body(compteService.createCompte(compte));
    }

    @GetMapping("/compte/{id}")
    public ResponseEntity<Map<String,Compte>> getOne(@PathVariable("id") Long id){
        return ResponseEntity.status(OK).body(Map.of("Compte",compteService.getOneCompte(id)));
    }

    @GetMapping("/comptes")
    public ResponseEntity<Map<String,List<Compte>>> AllCompte(){
        return ResponseEntity.status(OK).body(Map.of("Comptes",compteService.getAllCompte()));
    }

    @GetMapping("/comptesDeleted")
    public ResponseEntity<Map<String,List<Compte>>> AllCompteDeleted(){
        return ResponseEntity.status(OK).body(Map.of("Comptes",compteService.getAllCompteDeleted()));
    }

    @PutMapping("/update-compte/{id}")
    public ResponseEntity<Compte> update(@PathVariable("id") Long id, @RequestBody @Valid compteRequest request){
        Compte response = compteService.updateCompte(id, request.getTypeCompte(),request.getIdClient());
        return ResponseEntity.status(OK).body(response);
    }

    @DeleteMapping("/supprimer-compte/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        compteService.deleteCompte(id);
        return ResponseEntity.status(OK).body("suppression du compte ayant pour identifiant "+ id +" fait avec succès");
    }

    @PostMapping("/versement")
    public ResponseEntity<Compte> depot(@RequestBody @Valid depotRequest depotBody) throws IOException{
        Compte response = compteService.depot(depotBody.getNumeroCompte(),depotBody.getMontant());
        return ResponseEntity.status(OK).body(response);
    }

    @PostMapping("/retrait")
    public ResponseEntity<Compte> retrait(@RequestBody depotRequest retraitBody) throws IOException{
        Compte response = compteService.retrait(retraitBody.getNumeroCompte(),retraitBody.getMontant());
        return ResponseEntity.status(OK).body(response);
    }

    @PostMapping("/virement")
    public ResponseEntity<String> transfert(@RequestBody TransfertRequest transfertBody) throws IOException{
        compteService.transfert(transfertBody.getCompteEmetteur(),transfertBody.getCompteRecepteur(), transfertBody.getMontant());
        return ResponseEntity.status(OK).body("virement fait avec succès");
    }


}

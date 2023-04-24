package mele.fangbemi.example.edith.service;
import jakarta.transaction.Transactional;
import mele.fangbemi.example.edith.entity.Client;
import mele.fangbemi.example.edith.entity.Compte;
import mele.fangbemi.example.edith.entity.TypeCompte;
import mele.fangbemi.example.edith.exceptions.ClientNotFoundException;
import mele.fangbemi.example.edith.exceptions.CompteNotFoundException;
import mele.fangbemi.example.edith.exceptions.NbrCompteSuffisant;
import mele.fangbemi.example.edith.exceptions.SoldeInsuffisantException;
import mele.fangbemi.example.edith.repository.ClientRepository;
import mele.fangbemi.example.edith.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class CompteService {
    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;

    public  String getAccountNumber(Compte compte){
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringbuild = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            int index = new Random().nextInt(alphaNumericString.length());
            stringbuild.append(alphaNumericString.charAt(index));
        }
        stringbuild.append(compte.getDateCreation().getYear());

        return stringbuild.toString().toUpperCase();
    }


    @Autowired
    public CompteService(CompteRepository compteRepository, ClientRepository clientRepository){this.compteRepository = compteRepository;this.clientRepository = clientRepository;}

    public Compte createCompte(Compte compte)  {
        Optional<Client> client = clientRepository.findById(compte.getIdClient());
        if (client.isEmpty()){
            throw new ClientNotFoundException("le client avec l'identifiant "+ compte.getIdClient()+" n'existe pas");
        }

        List<Compte> comptes = compteRepository.findAllByIdClientAndStatus(compte.getIdClient(),true).get();

        if(comptes.size() >= 3) throw new NbrCompteSuffisant("le client avec l'identifiant "+ compte.getIdClient()+" a dejà 3 comptes actifs donc il ne peut éffectuer des actions que sur ces comptes");
        else {
            compte.setSolde(0.0);
            compte.setDateCreation(LocalDateTime.now());
            compte.setNumeroCompte(getAccountNumber(compte));
            compte.setStatus(true);
            return compteRepository.save(compte);
        }

    }

    public List<Compte> getAllCompte(){
        return compteRepository.findAllByStatus(true);
    }

    public List<Compte> getAllCompteDeleted(){
        return compteRepository.findAllByStatus(false);
    }

    public Compte updateCompte(Long id,String typeCompte,Long idClient){
        Optional<Compte> compteOptionnel = compteRepository.findByIdAndStatus(id, true);
        if(compteOptionnel.isEmpty()) throw new CompteNotFoundException("Aucun compte "+ typeCompte+" n'existe avec le client d'identifiant "+ idClient);
        Compte compte = compteOptionnel.get();
        compte.setTypeCompte(TypeCompte.valueOf(typeCompte));
        compte.setIdClient(idClient);
        return compteRepository.save(compte);
    }

    public void deleteCompte(Long id){
        Optional<Compte> compteOptional = compteRepository.findByIdAndStatus(id, true);
        if(compteOptional.isEmpty()) throw new CompteNotFoundException("Aucun compte n'existe avec l'identifiant "+ id);
        compteOptional.get().setStatus(false);
        compteRepository.save(compteOptional.get());
    }

    public Compte getOneCompte(Long id){
       Optional<Compte> compte =  compteRepository.findByIdAndStatus(id,true) ;
       if(compte.isEmpty()) throw new CompteNotFoundException("Aucun compte n'existe avec l'identifiant "+ id);
       return compte.get();
    }

    public Compte depot(String numeroCompte, double montant){
        Optional<Compte> compteOptionnel = compteRepository.findByNumeroCompteAndStatus(numeroCompte, true);
        if(compteOptionnel.isEmpty()) throw new CompteNotFoundException("Aucun compte n'existe avec le numéro de compte "+ numeroCompte);
        else{
            Compte compte = compteOptionnel.get();
            compte.setSolde(compte.getSolde() + montant);

            return compteRepository.save(compte);
        }

    }

    public Compte retrait(String numeroCompte, double montant){
        Optional<Compte> compteOptionnel = compteRepository.findByNumeroCompteAndStatus(numeroCompte, true);
        if(compteOptionnel.isEmpty()) throw new CompteNotFoundException("Aucun compte n'existe avec le numéro de compte "+ numeroCompte);
        else{
            Compte compte = compteOptionnel.get();
            if(compte.getSolde() < montant) throw new SoldeInsuffisantException("le solde du compte "+ numeroCompte+" est insuffisant. Il n'ya que " + compte.getSolde()+" sur ce compte");
            else {
                compte.setSolde(compte.getSolde() - montant);
                return compteRepository.save(compte);
            }

        }

    }

    @Transactional
    public void transfert(String numeroEmetteur, String numeroRecepteur, double montant){
        retrait(numeroEmetteur,montant);
        depot(numeroRecepteur, montant);
    }



}

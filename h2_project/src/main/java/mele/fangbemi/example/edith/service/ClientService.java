package mele.fangbemi.example.edith.service;

import mele.fangbemi.example.edith.entity.Client;
import mele.fangbemi.example.edith.entity.Compte;
import mele.fangbemi.example.edith.exceptions.ClientNotFoundException;
import mele.fangbemi.example.edith.exceptions.EmailException;
import mele.fangbemi.example.edith.exceptions.TelException;
import mele.fangbemi.example.edith.repository.ClientRepository;
import mele.fangbemi.example.edith.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private  final CompteRepository compteRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository, CompteRepository compteRepository){
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
    }

    public Client CreateClient(Client client){
        Optional<Client> clientByCourriel = clientRepository.findByCourrielAndStatus(client.getCourriel(), true);
        Optional<Client> clientByTelephone = clientRepository.findByTelephoneAndStatus(client.getTelephone(), true);

        if(clientByCourriel.isPresent()) throw new EmailException("l'adresse email "+client.getCourriel() +" est deja utilisée");
        else{
            if(clientByTelephone.isPresent()) throw new TelException("le numéro de téléphone "+client.getTelephone() +" est deja utilisée");
        }

        return clientRepository.save(client);
    }

    public List<Client> getClients(){
        return clientRepository.findAllByStatus(true);
    }

    public List<Client> getClientsDeleted(){
        return clientRepository.findAllByStatus(false);
    }

    public Client getClient(Long id){
        Optional<Client> client =  clientRepository.findByIdAndStatus(id,true);
        if(client.isEmpty()) throw new ClientNotFoundException("Aucun client avec l'identifiant "+ id);
        return client.get();
    }

    public Client update(Long id,Client client){
        Optional<Client> client2 = clientRepository.findByIdAndStatus(id, true);
        if(client2.isPresent() ){
            Client client3 = client2.get();
            client3.setAdresse(client.getAdresse());
            client3.setCourriel(client.getCourriel());
            client3.setNom(client.getNom());
            client3.setPrenom(client.getPrenom());
            client3.setDateNaiss(client.getDateNaiss());
            client3.setNationalite(client.getNationalite());
            client3.setSexe(client.getSexe());
            client3.setTelephone(client.getTelephone());

            return clientRepository.save(client3);
        }
        else
           throw new ClientNotFoundException("Aucun client avec l'identifiant "+ id);
    }

    public void delete(Long id){
        Optional<Client> client = clientRepository.findById(id);

        if(client.isEmpty())  throw new ClientNotFoundException("Aucun client avec l'identifiant "+ id);

        Optional<List<Compte>> comptes = compteRepository.findAllByIdClientAndStatus(id,true);
        client.get().setStatus(false);
        if(comptes.isPresent()){
           comptes.get().forEach((x)->{
               x.setStatus(false);
               compteRepository.save(x);
           });
        }
        clientRepository.save(client.get());

    }


}

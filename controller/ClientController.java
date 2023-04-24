package mele.fangbemi.example.edith.controller;

import jakarta.validation.Valid;
import mele.fangbemi.example.edith.entity.Client;
import mele.fangbemi.example.edith.entity.Response;
import mele.fangbemi.example.edith.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/ega")
public class ClientController {
    private ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService){this.clientService = clientService;}


    @PostMapping("/creer-client")
    public ResponseEntity<Client> save(@RequestBody @Valid Client client) throws IOException{
      return  ResponseEntity.status(CREATED).body(clientService.CreateClient(client));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getOne(@PathVariable("id") Long id) throws IOException{
        return ResponseEntity.status(OK).body(clientService.getClient(id));
    }

    @GetMapping("/clients")
    public ResponseEntity<Map<String, List<Client>>> getAll(){
        return ResponseEntity.status(OK).body(Map.of("Clients",clientService.getClients()));
    }

    @GetMapping("/clientDeleted")
    public ResponseEntity<Map<String, List<Client>>> getAllDeleted(){
        return ResponseEntity.status(OK).body(Map.of("Clients",clientService.getClientsDeleted()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody @Valid Client client) throws IOException{
        return ResponseEntity.status(OK).body(clientService.update(id,client));
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id){
        clientService.delete(id);
        return  ResponseEntity.status(OK).body("suppression fait avec succès");
    }
}

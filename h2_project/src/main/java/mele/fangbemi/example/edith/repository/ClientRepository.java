package mele.fangbemi.example.edith.repository;

import mele.fangbemi.example.edith.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByCourrielAndStatus(String courriel, boolean status);
    Optional<Client> findByTelephoneAndStatus(String courriel, boolean status);
    List<Client> findAllByStatus(boolean status);

    Optional<Client> findByIdAndStatus(Long id,boolean status);
}

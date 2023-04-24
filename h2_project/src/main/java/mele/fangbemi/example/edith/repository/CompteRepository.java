package mele.fangbemi.example.edith.repository;

import mele.fangbemi.example.edith.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Long> {
    List<Compte> findAllByStatus(boolean status);
    Optional<List<Compte>> findAllByIdClientAndStatus(Long id,boolean status);
    Optional<Compte> findByIdAndStatus(Long id,boolean status);
    Optional<Compte> findByNumeroCompteAndStatus(String numeroCompte, boolean status);
}

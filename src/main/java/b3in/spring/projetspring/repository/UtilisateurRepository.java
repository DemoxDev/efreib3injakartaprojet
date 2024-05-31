package b3in.spring.projetspring.repository;

import b3in.spring.projetspring.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
    public boolean existsByEmail(String email);

    public Optional<Utilisateur> findByEmail(String email);

    public Optional<Utilisateur> findByUsername(String username);

    @Query(value = "SELECT MAX(u.id) FROM Utilisateur u")
    public Integer findMaxId();
}

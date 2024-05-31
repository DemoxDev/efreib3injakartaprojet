package b3in.spring.projetspring.repository;

import b3in.spring.projetspring.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {
    public boolean existsByName(String name);

    public List<Anime> findByName(String name);

    @Query(value = "SELECT MAX(a.id) FROM Anime a")
    public Integer findMaxId();

}

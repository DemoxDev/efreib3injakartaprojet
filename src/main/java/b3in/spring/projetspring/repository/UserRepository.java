package b3in.spring.projetspring.repository;

import b3in.spring.projetspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    public boolean existsByEmail(String email);

    public List<User> findByEmail(String email);

    @Query(value = "SELECT MAX(u.id) FROM User u")
    public Integer findMaxId();
}

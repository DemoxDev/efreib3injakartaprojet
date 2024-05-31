package b3in.spring.projetspring.service;

import b3in.spring.projetspring.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import b3in.spring.projetspring.repository.UtilisateurRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Utilisateur> login(String username, String password) {
        Optional<Utilisateur> userOpt = utilisateurRepository.findByUsername(username);
        return Optional.empty();
    }

    @Transactional
    public String createUser(Utilisateur user){
        try {
            if(utilisateurRepository.existsByEmail(user.getEmail())){
                return "Email existe deja";
            }

            Integer maxId = utilisateurRepository.findMaxId();
            if (maxId == null) {
                maxId = 0;
            }

            user.setId(maxId + 1);
            utilisateurRepository.save(user);
            return "Utilisateur cree avec succes";
        } catch (Exception e){
            throw e;
        }
    }

    public List<Utilisateur> getAllUsers(){
        return utilisateurRepository.findAll();
    }

    @Transactional
    public String updateUser(Utilisateur user){
        Optional<Utilisateur> optionalUser = utilisateurRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()){
            try {
                Utilisateur userToUpdate = optionalUser.get();
                userToUpdate.setUsername(user.getUsername());
                userToUpdate.setPassword(user.getPassword());
                userToUpdate.setEmail(user.getEmail());
                utilisateurRepository.save(userToUpdate);
                return "Utilisateur mis a jour avec succes";
            } catch (Exception e){
                throw e;
            }
        } else {
            return "Utilisateur non trouve";
        }
    }

    @Transactional
    public String updateUserRole(Utilisateur user){
        Optional<Utilisateur> optionalUser = utilisateurRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()){
            try {
                Utilisateur userToUpdate = optionalUser.get();
                userToUpdate.setRole(user.getRole());
                utilisateurRepository.save(userToUpdate);
                return "Rôle d'utilisateur mis a jour avec succes";
            } catch (Exception e){
                throw e;
            }
        } else {
            return "Utilisateur non trouve";
        }
    }

    @Transactional
    public String deleteUser(Utilisateur user){
        Optional<Utilisateur> optionalUser = utilisateurRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()){
            try {
                utilisateurRepository.delete(optionalUser.get());
                return "User deleted successfully";
            } catch (Exception e){
                throw e;
            }
        } else {
            return "User not found";
        }
    }

    public Utilisateur authenticateUser(String email, String password){
        Optional<Utilisateur> optionalUser = utilisateurRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            Utilisateur existingUser = optionalUser.get();
            if (existingUser.getPassword().equals(password)) {
                return existingUser;
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

}

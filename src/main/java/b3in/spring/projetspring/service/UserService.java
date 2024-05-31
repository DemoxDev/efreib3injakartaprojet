package b3in.spring.projetspring.service;

import b3in.spring.projetspring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import b3in.spring.projetspring.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return Optional.empty();
    }

    @Transactional
    public String createUser(User user){
        try {
            if(userRepository.existsByEmail(user.getEmail())){
                return "Email existe deja";
            }

            Integer maxId = userRepository.findMaxId();
            if (maxId == null) {
                maxId = 0;
            }

            user.setId(maxId + 1);
            userRepository.save(user);
            return "Utilisateur cree avec succes";
        } catch (Exception e){
            throw e;
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public String updateUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            try {
                List<User> users = userRepository.findByEmail(user.getEmail());
                users.forEach(u -> {
                    Optional<User> optionalUserToUpdate = userRepository.findById(u.getId());
                    if (optionalUserToUpdate.isPresent()) {
                        User userToUpdate = optionalUserToUpdate.get();
                        userToUpdate.setUsername(user.getUsername());
                        userToUpdate.setPassword(user.getPassword());
                        userToUpdate.setEmail(user.getEmail());
                        userRepository.save(userToUpdate);
                    } else {
                        throw new IllegalStateException("Utilisateur non trouve");
                    }
                });
                return "Utilisateur mis a jour avec succes";
            } catch (Exception e){
                throw e;
            }
        } else {
            return "Utilisateur non trouve";
        }
    }

    @Transactional
    public String updateUserRole(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            try {
                List<User> users = userRepository.findByEmail(user.getEmail());
                users.forEach(u -> {
                    Optional<User> optionalUserToUpdate = userRepository.findById(u.getId());
                    if (optionalUserToUpdate.isPresent()) {
                        User userToUpdate = optionalUserToUpdate.get();
                        userToUpdate.setRole(user.getRole());
                        userRepository.save(userToUpdate);
                    } else {
                        throw new IllegalStateException("Utilisateur non trouve");
                    }
                });
                return "Role mis a jour avec succes";
            } catch (Exception e){
                throw e;
            }
        } else {
            return "Utilisateur non trouve";
        }
    }

    @Transactional
    public String deleteUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            try {
                List<User> users = userRepository.findByEmail(user.getEmail());
                users.forEach(u -> {
                    System.out.println(u.toString());
                    userRepository.deleteById(u.getId());
                });
                return "Utilisateur supprime avec succes";
            } catch (Exception e){
                throw e;
            }
        } else {
            return "Utilisateur non trouve";
        }
    }

    public User authenticateUser(String email, String password) {
        List<User> users = userRepository.findByEmail(email);
        if (!users.isEmpty()) {
            User user = users.get(0);
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

}

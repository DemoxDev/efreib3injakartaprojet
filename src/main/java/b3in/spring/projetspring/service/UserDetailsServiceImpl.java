package b3in.spring.projetspring.service;

import b3in.spring.projetspring.entity.User;
import b3in.spring.projetspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email); // Use Optional for safety

        if (userOptional.isPresent()) {
            User customUser = userOptional.get();
            // Create Spring Security User
            String role = customUser.getRole();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));

            return new org.springframework.security.core.userdetails.User(
                    customUser.getEmail(),
                    customUser.getPassword(),
                    authorities
            );
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}

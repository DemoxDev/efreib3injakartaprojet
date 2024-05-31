package b3in.spring.projetspring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        return https
                // .securityMatcher("/static/**")
                .authorizeHttpRequests( auth ->  {
                    auth.requestMatchers("/css/**", "/js/**", "/img/**", "connexion", "inscription", "/").permitAll();
                    auth.anyRequest().authenticated();
                })

                .oauth2Login(Customizer.withDefaults())
                .formLogin(formLogin ->
                formLogin
                        .loginPage("/connexion")
                        .permitAll()
                )

                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/connexion")
                                .defaultSuccessUrl("/mainpage", true)
                )
                .logout(logout ->logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/connexion")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")
                        )
                .build();

    }
}
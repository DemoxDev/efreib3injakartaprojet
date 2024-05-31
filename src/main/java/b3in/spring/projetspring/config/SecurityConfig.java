package b3in.spring.projetspring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // .securityMatcher("/static/**")
                .authorizeHttpRequests( auth ->  {
                    auth.requestMatchers(new AntPathRequestMatcher("/connexion")).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher("/inscription")).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher("/")).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher("/createUser")).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher("/login")).permitAll();
                    // auth.requestMatchers(new AntPathRequestMatcher("/mainpage")).permitAll();
                    // auth.requestMatchers(new AntPathRequestMatcher("/infoUser")).permitAll();
                    // auth.requestMatchers(new AntPathRequestMatcher("/createUser")).permitAll();
                    auth.requestMatchers("/css/**", "/js/**", "/img/**").permitAll();
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
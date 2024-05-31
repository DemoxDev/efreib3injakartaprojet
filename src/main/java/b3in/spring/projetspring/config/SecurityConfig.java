package b3in.spring.projetspring.config;


import b3in.spring.projetspring.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/connexion", "/inscription").anonymous()
                        .requestMatchers("/mainpage").hasAnyAuthority("USER", "ADMIN") // Protect mainpage
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/connexion") // Use your custom login page
                        .loginProcessingUrl("/login") // Handle login form submission
                        .defaultSuccessUrl("/mainpage") // Redirect after successful login
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /*
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // .securityMatcher("/static/**")
                .authorizeHttpRequests( auth ->  {
                    auth.requestMatchers(new AntPathRequestMatcher("/connexion")).anonymous();
                    auth.requestMatchers(new AntPathRequestMatcher("/inscription")).anonymous();
                    auth.requestMatchers(new AntPathRequestMatcher("/")).anonymous();
                    auth.requestMatchers(new AntPathRequestMatcher("/createUser")).anonymous();
                    auth.requestMatchers(new AntPathRequestMatcher("/login")).anonymous();
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
    } */
}
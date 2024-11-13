package be.neoo.security;

import be.neoo.repository.RoleRepository;
import be.neoo.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    AuthenticationManager authMgr;
    RoleRepository roleRepo;
    EmployeeService employeeService;

    @Lazy
    public SecurityConfig(AuthenticationManager authMgr, RoleRepository roleRepo, EmployeeService employeeService) {
        this.authMgr = authMgr;
        this.roleRepo = roleRepo;
        this.employeeService = employeeService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest
                                                                          request) {
                        CorsConfiguration cors = new CorsConfiguration();

                        cors.setAllowedOrigins(Collections.singletonList("*"));
                        cors.setAllowedMethods(Collections.singletonList("*"));
                        cors.setAllowedHeaders(Collections.singletonList("*"));
                        cors.setExposedHeaders(Collections.singletonList("Authorization"));

//                        // Autoriser uniquement les origines spécifiques (Angular en dev ici)
//                        cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//
//                        // Spécifier les méthodes autorisées au lieu d'utiliser *
//                        cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//
//                        // Autoriser tous les headers envoyés par le frontend Angular
//                        cors.setAllowedHeaders(Collections.singletonList("*"));
//
//                        // Exposer les headers spécifiques si nécessaires (par exemple Authorization)
//                        cors.setExposedHeaders(Collections.singletonList("Authorization"));
//                        // Permettre les credentials si nécessaire (cookies, authentification)
//                        cors.setAllowCredentials(true);

//                        cors.setAllowedOrigins(Collections.singletonList("*"));
//                        cors.setAllowedMethods(Collections.singletonList("*"));
//                        cors.setAllowedHeaders(Collections.singletonList("*"));

                        return cors;
                    }
                }))

                .authorizeHttpRequests(requests -> requests
                                // Autorise les liens dans AUTH_WHITE_LIST sans authentification
                                .requestMatchers(AUTH_WHITE_LIST).permitAll()

                                // Toutes les routes concernant les employés nécessitent le rôle ADMIN
                                .requestMatchers(HttpMethod.PUT, "/employees/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/employees/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/employees/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/employees/**").hasAuthority("ADMIN")

                                .requestMatchers(HttpMethod.PUT, "/categories/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/categories/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/categories/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/categories/**").hasAuthority("ADMIN")

                                .requestMatchers(HttpMethod.PUT, "/roles/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/roles/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/roles/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/roles/**").hasAuthority("ADMIN")


                                .requestMatchers(HttpMethod.PUT, "/permissions/**").hasAuthority("GET_ROLES")
                                .requestMatchers(HttpMethod.POST, "/permissions/**").hasAuthority("GET_ROLES")
                                .requestMatchers(HttpMethod.GET, "/permissions/**").hasAuthority("GET_ROLES")
                                .requestMatchers(HttpMethod.DELETE, "/permissions/**").hasAuthority("GET_ROLES")


//                                .requestMatchers(HttpMethod.PUT, "/roles/**").hasAuthority("ADMIN")
//                                .requestMatchers(HttpMethod.POST, "/roles/**").hasAuthority("ADMIN")
//                                .requestMatchers(HttpMethod.GET, "/roles/**").hasAuthority("ADMIN")
//                                .requestMatchers(HttpMethod.DELETE, "/roles/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )

                .addFilterBefore(new JWTAuthenticationFilter(authMgr, roleRepo, employeeService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //            "/roles/**",
//                    "/roles/roles",
    private static final String[] AUTH_WHITE_LIST = {
            "/login",
            "/employees/**  ",
            "/employees/logout  ",
            "/employees/currentUser",
            "/roles/**",
            "/roles/roles",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "javainuse-openapi/**",

    };
}

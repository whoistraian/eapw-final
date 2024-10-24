package ro.traian.eapw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import lombok.AllArgsConstructor;
import ro.traian.eapw.service.auth.IAuthService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
        @Bean
        AuthenticationManager authenticationManager(HttpSecurity http,
                        PasswordEncoder passwordEncoder,
                        IAuthService authService) throws Exception {
                AuthenticationManagerBuilder authManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);

                authManagerBuilder
                                .authenticationProvider(authService);

                return authManagerBuilder.build();
        }

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

                http.csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(
                                                (request) -> {
                                                        request.anyRequest().permitAll();
                                                })
                                .securityContext((context) -> context
                                                .securityContextRepository(securityContextRepository))
                                .sessionManagement(
                                                (session) -> {
                                                        session.maximumSessions(1).maxSessionsPreventsLogin(true);
                                                        session.sessionFixation(
                                                                        SessionManagementConfigurer.SessionFixationConfigurer::newSession);
                                                        session.sessionCreationPolicy(
                                                                        SessionCreationPolicy.IF_REQUIRED);
                                                });

                return http.build();
        }
}

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
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

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
                HttpStatusReturningLogoutSuccessHandler httpStatusReturningLogoutSuccessHandler = new HttpStatusReturningLogoutSuccessHandler();

                http.csrf(AbstractHttpConfigurer::disable)
                                .securityContext((context) -> context
                                                .securityContextRepository(securityContextRepository))
                                .sessionManagement(
                                                (session) -> {
                                                        session.sessionFixation(
                                                                        SessionManagementConfigurer.SessionFixationConfigurer::newSession);
                                                        session.sessionCreationPolicy(
                                                                        SessionCreationPolicy.IF_REQUIRED);
                                                })
                                .logout((logout) -> {
                                        logout.logoutUrl("/api/auth/logout");
                                        logout.addLogoutHandler(
                                                        new HeaderWriterLogoutHandler(
                                                                        new ClearSiteDataHeaderWriter(
                                                                                        ClearSiteDataHeaderWriter.Directive.COOKIES)));
                                        logout.deleteCookies("JSESSIONID");
                                        logout.logoutSuccessHandler(httpStatusReturningLogoutSuccessHandler);
                                });

                return http.build();
        }
}

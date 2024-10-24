package ro.traian.eapw.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.LoginRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
        private final AuthenticationManager authenticationManager;
        private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
                        .getContextHolderStrategy();

        @PermitAll
        @PostMapping("/login")
        public void login(
                        @RequestBody LoginRequest loginRequest,
                        HttpServletRequest request,
                        HttpServletResponse response) {
                UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
                                .unauthenticated(
                                                loginRequest.getEmail(), loginRequest.getPassword());

                Authentication authentication = authenticationManager.authenticate(token);
                SecurityContext context = securityContextHolderStrategy.createEmptyContext();

                context.setAuthentication(authentication);
                securityContextHolderStrategy.setContext(context);

                securityContextRepository.saveContext(context, request, response);
        }
}

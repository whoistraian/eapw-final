package ro.traian.eapw.service.auth;

import org.springframework.security.core.userdetails.UserDetailsService;

import ro.traian.eapw.dao.auth.AuthRequest;
import ro.traian.eapw.dao.auth.AuthResponse;

public interface IAuthService extends UserDetailsService {
    AuthResponse signup(AuthRequest authRequest);

    AuthResponse signin(AuthRequest authRequest);
}

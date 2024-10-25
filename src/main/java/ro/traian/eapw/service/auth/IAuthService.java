package ro.traian.eapw.service.auth;

import org.springframework.security.authentication.AuthenticationProvider;

import ro.traian.eapw.dao.auth.RegisterRequest;
import ro.traian.eapw.entity.AppUser;

public interface IAuthService extends AuthenticationProvider {
    AppUser register(RegisterRequest registerRequest);
}

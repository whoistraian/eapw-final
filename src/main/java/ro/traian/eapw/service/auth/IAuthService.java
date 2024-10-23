package ro.traian.eapw.service.auth;

import org.springframework.security.core.userdetails.UserDetailsService;

import ro.traian.eapw.entity.AppUser;

public interface IAuthService extends UserDetailsService {
    void signup(AppUser appUser);

    void signin(AppUser appUser);
}

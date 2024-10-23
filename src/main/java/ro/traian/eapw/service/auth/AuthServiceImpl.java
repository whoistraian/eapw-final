package ro.traian.eapw.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.auth.AuthRequest;
import ro.traian.eapw.dao.auth.AuthResponse;
import ro.traian.eapw.service.appuser.AppUserServiceImpl;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final AppUserServiceImpl appUserService;

    @Override
    public AuthResponse signup(AuthRequest authRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
    }

    @Override
    public AuthResponse signin(AuthRequest authRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signin'");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }
}

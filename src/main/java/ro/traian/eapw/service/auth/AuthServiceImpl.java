package ro.traian.eapw.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.service.appuser.AppUserServiceImpl;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final AppUserServiceImpl appUserService;

    @Override
    public void signup(AppUser appUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
    }

    @Override
    public void signin(AppUser appUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signin'");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }
}

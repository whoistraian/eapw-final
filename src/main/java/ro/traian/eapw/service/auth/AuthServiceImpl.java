package ro.traian.eapw.service.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.traian.eapw.constant.AppRoleConstant;
import ro.traian.eapw.dao.appuser.AppUserSave;
import ro.traian.eapw.dao.auth.RegisterRequest;
import ro.traian.eapw.entity.AppRole;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.service.approle.IAppRoleService;
import ro.traian.eapw.service.appuser.IAppUserService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final IAppUserService appUserService;
    private final IAppRoleService appRoleService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser register(RegisterRequest registerRequest) {
        AppUserSave appUserSave = AppUserSave.fromRegisterRequest(registerRequest);

        AppRole appRole = appRoleService.findByName(AppRoleConstant.ROLE_USER.name());
        appUserSave.setRoleId(appRole.getId());

        return appUserService.save(appUserSave);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

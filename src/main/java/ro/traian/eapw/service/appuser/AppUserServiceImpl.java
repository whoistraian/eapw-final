package ro.traian.eapw.service.appuser;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.appuser.AppUserSave;
import ro.traian.eapw.dao.appuser.AppUserUpdate;
import ro.traian.eapw.entity.AppRole;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.repository.AppUserRepository;
import ro.traian.eapw.service.approle.IAppRoleService;
import ro.traian.eapw.service.session.ISessionService;

import java.util.stream.Collectors;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements IAppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final IAppRoleService appRoleService;
    private final ISessionService sessionService;

    @Override
    public Set<AppUser> findAll() {
        return appUserRepository
                .findAll()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public AppUser findById(Long id) {
        return appUserRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id " + id + " not found"));
    }

    @Override
    public AppUser findByEmail(String email) {
        return appUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User with email " + email + " not found"));
    }

    @Override
    public AppUser save(AppUserSave appUserSave) {
        try {
            this.findByEmail(appUserSave.getEmail());
            throw new IllegalStateException("Email already taken");
        } catch (IllegalStateException e) {
            //
        }

        String encodedPassword = passwordEncoder.encode(appUserSave.getPassword());
        appUserSave.setPassword(encodedPassword);

        AppUser newAppUser = AppUser.fromAppUserSave(appUserSave);

        AppRole appRole = appRoleService.findById(appUserSave.getRoleId());
        newAppUser.setRole(appRole);

        return appUserRepository.save(newAppUser);
    }

    @Override
    public AppUser update(Long id, AppUserUpdate appUserUpdate) {
        AppUser myAppUser = this.findById(id);

        Optional.ofNullable(appUserUpdate.getEmail())
                .ifPresent(email -> {
                    email.ifPresent(nullableEmail -> {
                        try {
                            this.findByEmail(nullableEmail);
                            throw new IllegalStateException("Email already taken");
                        } catch (IllegalStateException e) {
                            //
                        }

                        myAppUser.setEmail(nullableEmail);
                    });
                });

        Optional.ofNullable(appUserUpdate.getPassword())
                .ifPresent(password -> {
                    password.ifPresent(nullablePassword -> {
                        String encodedPassword = passwordEncoder.encode(nullablePassword);
                        myAppUser.setPassword(encodedPassword);
                    });
                });

        Optional.ofNullable(appUserUpdate.getRoleId())
                .ifPresent(roleId -> {
                    roleId.ifPresent(nullableRoleId -> {
                        AppRole appRole = appRoleService.findById(nullableRoleId);
                        myAppUser.setRole(appRole);
                    });
                });

        return appUserRepository.save(myAppUser);
    }

    @Override
    public AppUser updateAndDestroySessions(Long id, AppUserUpdate appUserUpdate) {
        AppUser appUser = this.update(id, appUserUpdate);

        sessionService.findByPrincipalName(appUser.getEmail())
                .forEach(session -> {
                    sessionService.delete(session.getId());
                });

        return appUser;
    }

    @Override
    public boolean delete(Long id) {
        AppUser appUser = this.findById(id);

        sessionService.findByPrincipalName(appUser.getEmail())
                .forEach(session -> {
                    sessionService.delete(session.getId());
                });

        appUserRepository.deleteById(id);

        return true;
    }

}

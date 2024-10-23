package ro.traian.eapw.service.appuser;

import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.AppUserSave;
import ro.traian.eapw.dao.AppUserUpdate;
import ro.traian.eapw.entity.AppRole;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.repository.AppUserRepository;
import ro.traian.eapw.repository.AppRoleRepository;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements IAppUserService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Set<AppUser> findAll() {
        return appUserRepository
                .findAll()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public AppUser find(Long id) {
        return appUserRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id " + id + " not found"));
    }

    @Override
    public AppUser save(AppUserSave appUserSave) {
        AppUser myAppUser = appUserRepository.findByEmail(appUserSave.getEmail());

        if (myAppUser != null) {
            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = passwordEncoder.encode(appUserSave.getPassword());
        appUserSave.setPassword(encodedPassword);

        AppUser newAppUser = AppUser.fromAppUserSave(appUserSave);
        AppRole appRole = appRoleRepository.findById(appUserSave.getRoleId())
                .orElseThrow(
                        () -> new IllegalStateException("App Role with id " + appUserSave.getRoleId() + " not found"));

        newAppUser.setRole(appRole);

        return appUserRepository.save(newAppUser);
    }

    @Override
    public AppUser update(Long id, AppUserUpdate appUserUpdate) {
        AppUser myAppUser = this.find(id);

        appUserUpdate.getEmail().ifPresent(email -> {
            AppUser appUser = appUserRepository.findByEmail(email);

            if (appUser != null) {
                throw new IllegalStateException("Email already taken");
            }

            myAppUser.setEmail(email);
        });

        appUserUpdate.getPassword().ifPresent(password -> {
            String encodedPassword = passwordEncoder.encode(password);
            myAppUser.setPassword(encodedPassword);
        });

        appUserUpdate.getRoleId().ifPresent(roleId -> {
            AppRole appRole = appRoleRepository.findById(roleId)
                    .orElseThrow(
                            () -> new IllegalStateException("App Role with id " + roleId + " not found"));

            myAppUser.setRole(appRole);
        });

        return appUserRepository.save(myAppUser);
    }

    @Override
    public boolean delete(Long id) {
        this.find(id);

        appUserRepository.deleteById(id);
        return true;
    }
}

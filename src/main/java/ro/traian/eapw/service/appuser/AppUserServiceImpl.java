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
import ro.traian.eapw.service.approle.AppRoleServiceImpl;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements IAppUserService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AppRoleServiceImpl appRoleService;

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

        appUserUpdate.getEmail().ifPresent(email -> {
            try {
                this.findByEmail(email);
                throw new IllegalStateException("Email already taken");
            } catch (IllegalStateException e) {
                //
            }

            myAppUser.setEmail(email);
        });

        appUserUpdate.getPassword().ifPresent(password -> {
            String encodedPassword = passwordEncoder.encode(password);
            myAppUser.setPassword(encodedPassword);
        });

        appUserUpdate.getRoleId().ifPresent(roleId -> {
            AppRole appRole = appRoleService.findById(roleId);
            myAppUser.setRole(appRole);
        });

        return appUserRepository.save(myAppUser);
    }

    @Override
    public boolean delete(Long id) {
        this.findById(id);

        appUserRepository.deleteById(id);
        return true;
    }
}

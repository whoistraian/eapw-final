package ro.traian.eapw.service.appuser;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.appuser.AppUserRequest;
import ro.traian.eapw.dao.appuser.AppUserResponse;
import ro.traian.eapw.dao.appuser.CreateAppUserRequest;
import ro.traian.eapw.dao.appuser.UpdateAppUserRequest;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.repository.AppUserRepository;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements IAppUserService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;

    @Override
    public Set<AppUserResponse> findAllAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return AppUserResponse.fromAppUsers(appUsers);
    }

    @Override
    public AppUserResponse findAppUserById(AppUserRequest appUserRequest) {
        AppUser appUser = appUserRepository
                .findById(appUserRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("AppUser not found"));

        return AppUserResponse.fromAppUser(appUser);
    }

    @Override
    public AppUserResponse saveAppUser(CreateAppUserRequest createAppUserRequest) {
        String encodedPassword = bCryptpasswordEncoder.encode(createAppUserRequest.password());

        AppUser appUser = new AppUser(
                createAppUserRequest.email(),
                encodedPassword);

        AppUser newAppUser = appUserRepository.save(appUser);
        return AppUserResponse.fromAppUser(newAppUser);
    }

    @Override
    public AppUserResponse updateAppUser(AppUserRequest appUserRequest, UpdateAppUserRequest updateAppUserRequest) {
        AppUser appUser = appUserRepository
                .findById(appUserRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("AppUser not found"));

        updateAppUserRequest.email().ifPresent(appUser::setEmail);
        updateAppUserRequest.password()
                .ifPresent(password -> appUser.setPassword(bCryptpasswordEncoder.encode(password)));
        updateAppUserRequest.role().ifPresent(appUser::setRole);

        AppUser updatedAppUser = appUserRepository.save(appUser);
        return AppUserResponse.fromAppUser(updatedAppUser);
    }

    @Override
    public boolean deleteAppUser(AppUserRequest appUserRequest) {
        AppUser appUser = appUserRepository
                .findById(appUserRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("AppUser not found"));

        appUserRepository.delete(appUser);
        return true;
    }
}

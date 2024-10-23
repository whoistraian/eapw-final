package ro.traian.eapw.service.appuser;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.repository.AppUserRepository;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements IAppUserService {
    private final AppUserRepository appUserRepository;

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findById(Long id) {
        return appUserRepository
                .findById(id)
                .orElseThrow();
    }

    @Override
    public AppUser createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public boolean deleteAppUser(Long id) {
        appUserRepository.deleteById(id);
        return true;
    }
}

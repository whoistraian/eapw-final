package ro.traian.eapw.service.appuser;

import java.util.List;

import ro.traian.eapw.entity.AppUser;

public interface IAppUserService {
    List<AppUser> findAll();

    AppUser findById(Long id);

    AppUser createAppUser(AppUser appUser);

    AppUser updateAppUser(AppUser appUser);

    boolean deleteAppUser(Long id);
}

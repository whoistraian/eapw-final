package ro.traian.eapw.service.appuser;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import ro.traian.eapw.dao.appuser.AppUserSave;
import ro.traian.eapw.dao.appuser.AppUserUpdate;
import ro.traian.eapw.entity.AppUser;

public interface IAppUserService extends UserDetailsService {
    Set<AppUser> findAll();

    AppUser findById(Long id);

    AppUser findByEmail(String email);

    AppUser save(AppUserSave appUserSave);

    AppUser update(Long id, AppUserUpdate appUserUpdate);

    boolean delete(Long id);
}

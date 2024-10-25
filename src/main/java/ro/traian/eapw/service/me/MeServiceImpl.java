package ro.traian.eapw.service.me;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.appuser.AppUserUpdate;
import ro.traian.eapw.dao.me.MeUpdate;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.service.appuser.IAppUserService;

@Service
@AllArgsConstructor
public class MeServiceImpl implements IMeService {
    private final IAppUserService appUserService;

    @Override
    public AppUser find(String email) {
        return appUserService.findByEmail(email);
    }

    @Override
    public AppUser update(String email, MeUpdate meUpdate) {
        AppUser appUser = appUserService.findByEmail(email);
        AppUserUpdate appUserUpdate = AppUserUpdate.fromMeUpdate(meUpdate);

        return appUserService.update(appUser.getId(), appUserUpdate);
    }

    @Override
    public boolean delete(String email) {
        AppUser appUser = appUserService.findByEmail(email);
        return appUserService.delete(appUser.getId());
    }

}

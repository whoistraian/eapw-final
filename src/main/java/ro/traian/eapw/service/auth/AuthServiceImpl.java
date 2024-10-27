package ro.traian.eapw.service.auth;

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

    @Override
    public AppUser register(RegisterRequest registerRequest) {
        AppUserSave appUserSave = AppUserSave.fromRegisterRequest(registerRequest);

        AppRole appRole = appRoleService.findByName(AppRoleConstant.ROLE_USER.name());
        appUserSave.setRoleId(appRole.getId());

        return appUserService.save(appUserSave);
    }
}

package ro.traian.eapw.service.appuser;

import java.util.Set;

import ro.traian.eapw.dao.appuser.AppUserRequest;
import ro.traian.eapw.dao.appuser.AppUserResponse;
import ro.traian.eapw.dao.appuser.CreateAppUserRequest;
import ro.traian.eapw.dao.appuser.UpdateAppUserRequest;

public interface IAppUserService {
    Set<AppUserResponse> findAllAppUsers();

    AppUserResponse findAppUserById(AppUserRequest appUserRequest);

    AppUserResponse saveAppUser(CreateAppUserRequest createAppUserRequest);

    AppUserResponse updateAppUser(AppUserRequest appUserRequest, UpdateAppUserRequest updateAppUserRequest);

    boolean deleteAppUser(AppUserRequest appUserRequest);
}

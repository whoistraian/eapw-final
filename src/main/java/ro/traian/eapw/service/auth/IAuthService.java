package ro.traian.eapw.service.auth;

import ro.traian.eapw.dao.auth.RegisterRequest;
import ro.traian.eapw.entity.AppUser;

public interface IAuthService {
    AppUser register(RegisterRequest registerRequest);
}

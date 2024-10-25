package ro.traian.eapw.service.me;

import ro.traian.eapw.dao.me.MeUpdate;
import ro.traian.eapw.entity.AppUser;

public interface IMeService {
    AppUser find(String email);

    AppUser update(String email, MeUpdate meUpdate);

    boolean delete(String email);
}

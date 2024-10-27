package ro.traian.eapw.service.session;

import java.util.Set;
import org.springframework.session.Session;

public interface ISessionService {
    Set<Session> findByPrincipalName(String principalName);

    boolean delete(String id);
}

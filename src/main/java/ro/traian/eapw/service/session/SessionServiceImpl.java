package ro.traian.eapw.service.session;

import java.util.Set;

import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements ISessionService {
    private final FindByIndexNameSessionRepository<? extends Session> sessions;

    @Override
    public Set<Session> findByPrincipalName(String principalName) {
        return sessions
                .findByPrincipalName(principalName)
                .values()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public boolean delete(String id) {
        sessions.deleteById(id);
        return true;
    }

}

package ro.traian.eapw.dao.appuser;

import java.util.Optional;

import ro.traian.eapw.entity.Role;

public record UpdateAppUserRequest(
        Optional<String> email,
        Optional<String> password,
        Optional<Role> role) {

}

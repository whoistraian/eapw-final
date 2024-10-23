package ro.traian.eapw.dao.appuser;

import ro.traian.eapw.entity.AppUser;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public record AppUserResponse(Long id) {
    public static AppUserResponse fromAppUser(AppUser appUser) {
        return new AppUserResponse(appUser.getId());
    }

    public static Set<AppUserResponse> fromAppUsers(List<AppUser> appUsers) {
        return appUsers.stream()
                .map(appUser -> new AppUserResponse(appUser.getId()))
                .collect(Collectors.toSet());
    }
}

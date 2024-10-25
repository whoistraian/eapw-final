package ro.traian.eapw.dao.appuser;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.traian.eapw.dao.me.MeUpdate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserUpdate {
    private Optional<String> email;
    private Optional<String> password;
    @JsonProperty("role_id")
    private Optional<Long> roleId;

    public AppUserUpdate(Optional<String> email, Optional<String> password) {
        this.email = email;
        this.password = password;
    }

    public static AppUserUpdate fromMeUpdate(MeUpdate meUpdate) {
        return new AppUserUpdate(
                meUpdate.getEmail(),
                meUpdate.getPassword());
    }
}

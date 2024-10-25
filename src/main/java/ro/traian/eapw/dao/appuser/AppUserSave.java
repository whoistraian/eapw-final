package ro.traian.eapw.dao.appuser;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.traian.eapw.dao.auth.RegisterRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserSave {
    private String email;
    private String password;
    @JsonProperty("role_id")
    private Long roleId;

    public AppUserSave(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static AppUserSave fromRegisterRequest(RegisterRequest registerRequest) {
        return new AppUserSave(
                registerRequest.getEmail(),
                registerRequest.getPassword());
    }
}

package ro.traian.eapw.dao.appuser;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserSave {
    private String email;
    private String password;
    @JsonProperty("role_id")
    private Long roleId;
}

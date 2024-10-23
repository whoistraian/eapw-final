package ro.traian.eapw.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserSave {
    private String email;
    private String password;
    private Long roleId;
}

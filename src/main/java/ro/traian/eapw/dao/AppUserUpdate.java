package ro.traian.eapw.dao;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserUpdate {
    private Optional<String> email;
    private Optional<String> password;
    private Optional<Long> roleId;
}

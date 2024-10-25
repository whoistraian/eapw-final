package ro.traian.eapw.dao.me;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeUpdate {
    private Optional<String> email;
    private Optional<String> password;
}

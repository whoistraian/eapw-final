package ro.traian.eapw.entity;

import static jakarta.persistence.GenerationType.SEQUENCE;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.traian.eapw.dao.approle.AppRoleSave;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AppRole")
@Table(name = "roles")
public class AppRole implements GrantedAuthority {
    @Id
    @JsonProperty("id")
    @SequenceGenerator(name = "roles_sequence", sequenceName = "roles_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "roles_sequence")
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "text")
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    public static AppRole fromAppRoleSave(AppRoleSave appRoleSave) {
        return new AppRole(appRoleSave.getName());
    }

    public AppRole(String name) {
        this.name = name;
    }

}
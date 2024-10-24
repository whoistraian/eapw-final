package ro.traian.eapw.entity;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.traian.eapw.dao.appuser.AppUserSave;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AppUser")
@Table(name = "users")
public class AppUser implements UserDetails {
    @Id
    @JsonProperty("id")
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "users_sequence")
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @JsonProperty("email")
    @Column(name = "email", columnDefinition = "text", nullable = false, unique = true)
    private String email;

    @JsonProperty("password")
    @Column(name = "password", nullable = false, columnDefinition = "text")
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private AppRole role;

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> this.role.getAuthority());
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    public static AppUser fromAppUserSave(AppUserSave appUserSave) {
        return new AppUser(
                appUserSave.getEmail(),
                appUserSave.getPassword());
    }
}
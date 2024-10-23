package ro.traian.eapw.entity;

import static jakarta.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Role")
@Table(name = "roles")
public class Role {
    @Id
    @JsonProperty("id")
    @SequenceGenerator(name = "roles_sequence", sequenceName = "roles_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "roles_sequence")
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false, columnDefinition = "text")
    private String name;

    @ManyToMany
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    public Role(String name, Set<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }
}
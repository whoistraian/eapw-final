package ro.traian.eapw.entity;

import static jakarta.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Entity(name = "Permission")
@Table(name = "permissions")
public class Permission {
    @Id
    @JsonProperty("id")
    @SequenceGenerator(name = "permissions_sequence", sequenceName = "permissions_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "permissions_sequence")
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false, columnDefinition = "text")
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    public Permission(String name, Set<Role> roles) {
        this.name = name;
        this.roles = roles;
    }
}
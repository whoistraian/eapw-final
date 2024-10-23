package ro.traian.eapw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.traian.eapw.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}

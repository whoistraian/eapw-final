package ro.traian.eapw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.traian.eapw.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}

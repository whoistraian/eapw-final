package ro.traian.eapw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.traian.eapw.entity.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByName(String name);
}

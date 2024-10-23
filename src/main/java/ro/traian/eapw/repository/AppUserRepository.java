package ro.traian.eapw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.traian.eapw.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}

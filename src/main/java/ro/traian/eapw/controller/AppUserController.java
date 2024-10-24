package ro.traian.eapw.controller;

import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.AppUserSave;
import ro.traian.eapw.dao.AppUserUpdate;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.service.appuser.IAppUserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AppUserController {
    private final IAppUserService appUserService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public Set<AppUser> findAppUsers() {
        return appUserService.findAll();
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AppUser findAppUserById(@PathVariable Long id) {
        return appUserService.findById(id);
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public AppUser saveUser(@RequestBody AppUserSave appUserSave) {
        return appUserService.save(appUserSave);
    }

    @PatchMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AppUser updateUser(
            @PathVariable Long id,
            @RequestBody AppUserUpdate appUserUpdate) {
        return appUserService.update(id, appUserUpdate);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteUser(@PathVariable Long id) {
        return appUserService.delete(id);
    }
}

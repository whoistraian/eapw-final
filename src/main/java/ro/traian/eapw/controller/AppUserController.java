package ro.traian.eapw.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
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
import ro.traian.eapw.dao.appuser.AppUserSave;
import ro.traian.eapw.dao.appuser.AppUserUpdate;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.service.appuser.IAppUserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AppUserController {
    private final IAppUserService appUserService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<AppUser>> findAppUsers() {
        Set<AppUser> appUsers = appUserService.findAll();
        return ResponseEntity.ok(appUsers);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AppUser> findAppUserById(@PathVariable Long id) {
        AppUser appUser = appUserService.findById(id);
        return ResponseEntity.ok(appUser);
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUserSave appUserSave) {
        AppUser appUser = appUserService.save(appUserSave);
        return ResponseEntity.ok(appUser);
    }

    @PatchMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AppUser> updateUser(
            @PathVariable Long id,
            @RequestBody AppUserUpdate appUserUpdate) {
        AppUser appUser = appUserService.update(id, appUserUpdate);
        return ResponseEntity.ok(appUser);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        boolean result = appUserService.delete(id);
        return ResponseEntity.ok(result);
    }
}

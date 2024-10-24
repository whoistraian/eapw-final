package ro.traian.eapw.controller;

import java.util.Set;

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
import ro.traian.eapw.service.appuser.AppUserServiceImpl;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AppUserController {
    private final AppUserServiceImpl appUserService;

    @GetMapping("/users")
    public Set<AppUser> findAppUsers() {
        return appUserService.findAll();
    }

    @GetMapping("/user/{id}")
    public AppUser findAppUserById(@PathVariable Long id) {
        return appUserService.findById(id);
    }

    @PostMapping("/user")
    public AppUser saveUser(@RequestBody AppUserSave appUserSave) {
        return appUserService.save(appUserSave);
    }

    @PatchMapping("/user/{id}")
    public AppUser updateUser(
            @PathVariable Long id,
            @RequestBody AppUserUpdate appUserUpdate) {
        return appUserService.update(id, appUserUpdate);
    }

    @DeleteMapping("/user/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return appUserService.delete(id);
    }
}

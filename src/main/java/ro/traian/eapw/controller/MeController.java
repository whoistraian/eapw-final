package ro.traian.eapw.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.me.MeUpdate;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.service.me.IMeService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MeController {
    private final IMeService meService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AppUser> me(Principal principal) {
        AppUser appUser = meService.find(principal.getName());
        return ResponseEntity.ok(appUser);
    }

    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AppUser> updateMe(
            @RequestBody MeUpdate meUpdate,
            Principal principal) {
        AppUser updatedMe = meService.update(principal.getName(), meUpdate);
        return ResponseEntity.ok(updatedMe);
    }

    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> deleteMe(Principal principal) {
        boolean result = meService.delete(principal.getName());
        return ResponseEntity.ok(result);
    }
}

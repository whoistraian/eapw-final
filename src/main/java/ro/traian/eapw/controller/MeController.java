package ro.traian.eapw.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public AppUser me(Principal principal) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getPrincipal().toString();

        return meService.find(email);
    }

    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public AppUser updateMe(@RequestBody MeUpdate meUpdate) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getPrincipal().toString();

        return meService.update(email, meUpdate);
    }

    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public boolean deleteMe() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getPrincipal().toString();

        return meService.delete(email);
    }
}

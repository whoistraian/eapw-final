package ro.traian.eapw.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.service.appuser.IAppUserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MeController {
    private final IAppUserService appUserService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public AppUser me() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getPrincipal().toString();

        return appUserService.findByEmail(email);
    }
}

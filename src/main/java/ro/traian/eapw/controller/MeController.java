package ro.traian.eapw.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.me.MeUpdate;
import ro.traian.eapw.entity.AppUser;
import ro.traian.eapw.service.me.IMeService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MeController {
    private final IMeService meService;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

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
            Authentication authentication,
            HttpServletRequest request,
            HttpServletResponse response) {
        String email = authentication.getName();
        AppUser updatedMe = meService.update(email, meUpdate);

        request.getSession().invalidate();
        request.getSession(true);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                updatedMe,
                updatedMe.getPassword(),
                authentication.getAuthorities());

        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(newAuth);

        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        return ResponseEntity.ok(updatedMe);
    }

    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> deleteMe(Principal principal) {
        boolean result = meService.delete(principal.getName());
        return ResponseEntity.ok(result);
    }
}

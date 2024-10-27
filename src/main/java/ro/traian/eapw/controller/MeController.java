package ro.traian.eapw.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
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
    public AppUser me(Principal principal) {
        return meService.find(principal.getName());
    }

    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public AppUser updateMe(
            @RequestBody MeUpdate meUpdate,
            Principal principal,
            HttpServletRequest request,
            HttpServletResponse response) {
        AppUser updatedMe = meService.update(principal.getName(), meUpdate);

        if (!updatedMe.getEmail().equals(principal.getName())) {
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);
        }

        return updatedMe;
    }

    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public boolean deleteMe(
            Principal principal,
            HttpServletRequest request,
            HttpServletResponse response) {
        boolean result = meService.delete(principal.getName());

        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        return result;
    }
}

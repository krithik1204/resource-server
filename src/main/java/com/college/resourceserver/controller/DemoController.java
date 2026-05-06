package com.college.resourceserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.college.resourceserver.dto.UserProfileResponse;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping({"/public/hello", "/profile/public/hello"})
    public String publicApi() {
        return "Public API";
    }
    
    @GetMapping("/debug/auth")
    public Object debug(Authentication auth) {
        return auth;
    }

    @GetMapping("/profile")
    public UserProfileResponse profile(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Long userId = Long.parseLong(jwt.getSubject());
        String email = jwt.getClaimAsString("email");

        return new UserProfileResponse(
            userId,
            email,
            "User profile retrieved successfully"
        );
    }
    
    @GetMapping("/teacher")
    public UserProfileResponse teacher(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Long userId = Long.parseLong(jwt.getSubject());
        String email = jwt.getClaimAsString("email");

        return new UserProfileResponse(
            userId,
            email,
            "User profile retrieved successfully"
        );
    }
    
    @GetMapping("/both")
    public UserProfileResponse both(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Long userId = Long.parseLong(jwt.getSubject());
        String email = jwt.getClaimAsString("email");

        return new UserProfileResponse(
            userId,
            email,
            "User profile retrieved successfully"
        );
    }
}

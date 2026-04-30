package com.hospital.resourceserver.controller;

import com.hospital.resourceserver.dto.UserProfileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping({"/public/hello", "/profile/public/hello"})
    public String publicApi() {
        return "Public API";
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
}

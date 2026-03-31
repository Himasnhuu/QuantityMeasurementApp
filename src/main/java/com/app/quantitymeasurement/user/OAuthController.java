package com.app.quantitymeasurement.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.config.JwtUtil;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public OAuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/success")
    public AuthResponse loginSuccess(@AuthenticationPrincipal OAuth2User oauthUser) {

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        // Save user if not exists
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(
                        new User(name, email, "", "GOOGLE")
                ));

        // Generate JWT
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
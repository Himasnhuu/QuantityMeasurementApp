package com.app.authservice.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.authservice.dto.AuthProvider;
import com.app.authservice.entity.User;
import com.app.authservice.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

	private final UserRepository userRepository;
	private final JwtUtil jwtService;

	public OAuth2SuccessHandler(UserRepository userRepository, JwtUtil jwtService) {
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

		String email = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");

		Optional<User> existingUser = userRepository.findByEmail(email);

		User user;
		if (existingUser.isPresent()) {
			user = existingUser.get();
		} else {
			user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setPassword(null);
			user.setProvider(AuthProvider.GOOGLE);
			userRepository.save(user);
		}

		String token = jwtService.generateToken(email);

		response.sendRedirect("http://localhost:5173/oauth-success?token=" + token);
	}
}
package com.app.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {

	private final OAuth2SuccessHandler oAuth2SuccessHandler;

	public SecurityConfig(OAuth2SuccessHandler oAuth2SuccessHandler) {
		this.oAuth2SuccessHandler = oAuth2SuccessHandler;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.formLogin(form -> form.disable()).httpBasic(httpBasic -> httpBasic.disable())

				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**", "/oauth2/**", "/h2-console/**")
						.permitAll().anyRequest().authenticated())

				.oauth2Login(oauth -> oauth.successHandler(oAuth2SuccessHandler))

				.headers(headers -> headers.frameOptions(frame -> frame.disable()))

				.exceptionHandling(
						ex -> ex.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

		return http.build();
	}
}
package fr.elbarbary.p_jee_s_o.services;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	JwtEncoder encoder;
	
	JwtDecoder decoder;
	
	public AuthService(JwtEncoder encoder, JwtDecoder decoder) {
		this.encoder = encoder;
		this.decoder = decoder;
	}
	
	/**
	 * Crée un token JWT basé sur l'authentication avec sa durée et les rôles
	 * @param auth 
	 * @return le token JWT en format String.
	 */
	public String authenticate(Authentication auth) {
		Instant now = Instant.now();
		long expiry = 36000L;
		StringBuilder scopeBuilder = new StringBuilder();
		scopeBuilder.append(auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" ")));
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiry))
				.subject(auth.getName())
				.claim("scope", scopeBuilder.toString())
				.build();
		return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
	
}

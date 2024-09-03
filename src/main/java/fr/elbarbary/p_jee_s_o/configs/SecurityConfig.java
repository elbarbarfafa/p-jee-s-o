package fr.elbarbary.p_jee_s_o.configs;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Value("${jwt.public.key}")
	RSAPublicKey key;

	@Value("${jwt.private.key}")
	RSAPrivateKey priv;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http
		.csrf((cs) -> cs.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		.cors(Customizer.withDefaults())
		.authorizeHttpRequests((authorize) -> 
			{
				authorize.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
				authorize.anyRequest().authenticated();
			}
		)
		.oauth2ResourceServer(t->t.jwt(Customizer.withDefaults()))
		.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.exceptionHandling((exceptions) -> exceptions
				.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
				.accessDeniedHandler(new BearerTokenAccessDeniedHandler())
				)
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
 	UserDetailsService userDetailsService() {
 		UserDetails user = User.withDefaultPasswordEncoder()
 			.username("farid")
 			.password("test")
 			.roles("USER")
 			.build();
 		UserDetails admin = User.withDefaultPasswordEncoder()
 				.username("admin")
 				.password("test")
 				.roles("ADMIN")
 				.build();
 		return new InMemoryUserDetailsManager(user, admin);
 	}
	

	@Bean
	static RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl d = new RoleHierarchyImpl();
		d.setHierarchy("ROLE_ADMIN > ROLE_USER");
		return d; 
	}
	
	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(this.key).build();
	}

	@Bean
	JwtEncoder jwtEncoder() {
		RSAKey jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	
}

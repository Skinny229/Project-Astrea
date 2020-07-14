package com.echovrprotocol.astrea.security;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

/**
 * Configures our application with Spring Security to restrict access to our API endpoints.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("https://my-echo-server")
    private String audience;

    @Value("https://echotest.auth0.com/")
    private String issuer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */
        http.authorizeRequests()
                //LFG
                .mvcMatchers("/api/lfg/lobbies").authenticated()
                .mvcMatchers("/api/lfg/joinLobby").authenticated()
                .mvcMatchers("/api/lfg/leaveLobby").authenticated()
                .mvcMatchers("/api/lfg/delLobby").authenticated()
                .mvcMatchers("/api/lfg/createLobby").authenticated()
                .mvcMatchers("/api/lfg/updateLobbyStatus").authenticated()
                .mvcMatchers("/api/lfg/updateEchoSessionStatus").authenticated()
                //Group Maker
                //Ranked
                //Statistics
                .and()
                .oauth2ResourceServer().jwt();
        
    }

    @Bean
    JwtDecoder jwtDecoder() {
        /*
        By default, Spring Security does not validate the "aud" claim of the token, to ensure that this token is
        indeed intended for our app. Adding our own validator is easy to do:
        */

        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }
}
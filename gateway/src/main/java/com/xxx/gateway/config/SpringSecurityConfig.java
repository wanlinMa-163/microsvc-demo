package com.xxx.gateway.config;

import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(v -> v.disable());
        http.formLogin(v -> v.disable());
        http.csrf(v -> v.disable());
        http.requestCache(v -> v.disable());
        http.securityContext(v -> v.disable());
        http.sessionManagement(v -> v.disable());
        http.logout(v -> v.disable());
        http.anonymous(v -> v.disable());

        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.requestMatchers("/sso/**").permitAll()
                    .anyRequest().authenticated(); // 其他请求需要认证
        });


        http.oauth2ResourceServer(configurer ->
                configurer.jwt(jwtConfigurer ->
                        jwtConfigurer.decoder(NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build())
                )
        );
        // http.addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter.class);
        return http.build();
    }

    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
        PolicyEnforcerConfig config;

        try {
            config = JsonSerialization.readValue(getClass().getResourceAsStream("/policy-enforcer.json"), PolicyEnforcerConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
            @Override
            public PolicyEnforcerConfig resolve(HttpRequest request) {
                return config;
            }
        });
    }

}

package br.com.fiap.techchallenge.pedido.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtDecoder jwtDecoder() {

        SecretKey key = new SecretKeySpec(
                secret.getBytes(),
                "HmacSHA384"
        );

        return NimbusJwtDecoder
                .withSecretKey(key)
                .macAlgorithm(org.springframework.security.oauth2.jose.jws.MacAlgorithm.HS384)
                .build();
    }
}
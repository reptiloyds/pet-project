package ru.naumov.bloghub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JWTUtil {
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.lifetime-in-minutes}")
    private Integer lifetimeInMinutes;

    public String generateTokenBySubject(String username) {
        Instant expiration = Instant.now().plus(lifetimeInMinutes, ChronoUnit.MINUTES);

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(Instant.now())
                .withIssuer(issuer)
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndGetSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        if(decodedJWT.getExpiresAtAsInstant().isBefore(Instant.now()))
            throw new JWTVerificationException("Token has expired");

        return decodedJWT.getSubject();
    }
}

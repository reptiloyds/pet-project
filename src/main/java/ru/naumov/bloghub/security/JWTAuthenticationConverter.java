package ru.naumov.bloghub.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationConverter implements AuthenticationConverter {
    private final JWTUtil jwtUtil;

    @Override
    public Authentication convert(HttpServletRequest request) {
        String jwtToken = null;
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer "))
            jwtToken = authHeader.substring(7);
        if(jwtToken == null || jwtToken.isBlank()){
            return null;
        }

        try {
            String username = jwtUtil.validateTokenAndGetSubject(jwtToken);
            return new UsernamePasswordAuthenticationToken(username, null);
        }
        catch (JWTVerificationException exception){
            return null;
        }
    }
}

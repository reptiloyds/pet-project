package ru.naumov.bloghub.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserDetailsService detailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = extractTokenFromRequest(request);
        if (jwtToken == null || jwtToken.isBlank()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token is empty");
        } else {
            try {
                String username = jwtUtil.validateTokenAndGetSubject(jwtToken);
                UserDetails userDetails = detailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContext newContext = SecurityContextHolder.createEmptyContext();
                    newContext.setAuthentication(authToken);
                    SecurityContextHolder.setContext(newContext);
                }

            } catch (JWTVerificationException exception) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "JWT Token is invalid");
            } catch (UsernameNotFoundException usernameNotFoundException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Username not found");
            }
        }

        filterChain.doFilter(request, response);
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer "))
            return authHeader.substring(7);
        return null;
    }
}

package ru.pablo.Spring.Security.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.pablo.Spring.Security.ApplicationUser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JWTFIlter extends OncePerRequestFilter {

    @Autowired
    JWTService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(getTokenFromRequest(request) != null) {
            JWTToken token = new JWTToken.JWTBuilder().decodeFromString(getTokenFromRequest(request));
            Map<String, String> tokenPayload = token.getPayload();
            if (tokenService.isAccessTokenValid(token)) {
                SecurityContext ctx = SecurityContextHolder.createEmptyContext();
                JWTAuthentification authentication = new JWTAuthentification((String) tokenPayload.get("userId"), List.of(new SimpleGrantedAuthority("USER")));
                authentication.setAuthenticated(true);
                ctx.setAuthentication(authentication);
                SecurityContextHolder.setContext(ctx);
            }

        }
        filterChain.doFilter(request,response);
    }

    private String getTokenFromRequest(HttpServletRequest request){
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}

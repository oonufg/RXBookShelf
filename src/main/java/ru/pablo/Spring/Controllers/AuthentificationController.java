package ru.pablo.Spring.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pablo.Spring.Security.ApplicationUser;
import ru.pablo.Spring.Security.CUserDetailsService;
import ru.pablo.Spring.Security.JWT.AuthService;
import ru.pablo.Spring.Security.JWT.JWTToken;
import ru.pablo.Spring.Security.JWT.RefreshToken;

import java.net.URI;

@RestController
public class AuthentificationController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private AuthService authService;
    @Autowired
    private CUserDetailsService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> auth(@ModelAttribute ApplicationUser user, HttpServletResponse response){
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(),user.getPassword());
        authentication = authManager.authenticate(authentication);
        if(authentication.isAuthenticated()){
            ApplicationUser authUser = (ApplicationUser) userService.loadUserByUsername(user.getUsername());
            JWTToken accessToken = authService.generateAccessToken(authUser);
            RefreshToken refreshToken = authService.generateRefreshToken(authUser);
            Cookie refreshTokenCookie = new Cookie("RefreshToken",refreshToken.getValue());
            response.addCookie(refreshTokenCookie);
            return ResponseEntity.ok(accessToken.toString());
        }
        return ResponseEntity.status(403).build();
    }
}

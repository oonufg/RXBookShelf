package ru.pablo.Spring.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.pablo.Spring.Security.ApplicationUser;
import ru.pablo.Spring.Security.CUserDetailsService;
import ru.pablo.Spring.Security.JWT.JWTService;
import ru.pablo.Spring.Security.JWT.JWTToken;

import java.net.URI;

@RestController
@RequestMapping()
public class UserController {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private CUserDetailsService userService;
    @Autowired
    private AuthenticationManager authManager;
    @PostMapping("/signup")
    public void registration(@ModelAttribute ApplicationUser user){
        userService.save(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> auth(@ModelAttribute ApplicationUser user, HttpServletResponse response){
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(),user.getPassword());
        authentication = authManager.authenticate(authentication);
        if(authentication.isAuthenticated()){
            ApplicationUser authUser = (ApplicationUser) userService.loadUserByUsername(user.getUsername());
            JWTToken accessToken = jwtService.generateAccessToken(authUser);
            String refreshToken = jwtService.generateRefreshToken(authUser);
            Cookie accessTokenCookie = new Cookie("AccessToken",accessToken.toString());
            Cookie refreshTokenCookie = new Cookie("RefreshToken",refreshToken);
            response.addCookie(accessTokenCookie);
            response.addCookie(refreshTokenCookie);
            System.out.println(accessToken);
            try {
                return ResponseEntity.status(302).location(new URI("/bookshelf")).build();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
            return ResponseEntity.status(403).build();
    }

}

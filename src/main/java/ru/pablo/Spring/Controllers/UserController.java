package ru.pablo.Spring.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import ru.pablo.Spring.Security.ApplicationUser;
import ru.pablo.Spring.Security.CUserDetailsService;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private CUserDetailsService userService;
    @PostMapping("/signup")
    public void registration(@ModelAttribute ApplicationUser user){

        userService.save(user);
    }
    @PostMapping("/signin")
    public void login(@ModelAttribute ApplicationUser user, HttpServletRequest req, HttpServletResponse resp){

    }


}

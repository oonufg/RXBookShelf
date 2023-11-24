package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;
import ru.pablo.Spring.Security.ApplicationUser;
import ru.pablo.Spring.Security.CUserDetailsService;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private CUserDetailsService userService;
    @Autowired
    private AuthenticationManager authManager;
    @PostMapping("/signup")
    public void registration(@ModelAttribute ApplicationUser user){

        userService.save(user);
    }

}

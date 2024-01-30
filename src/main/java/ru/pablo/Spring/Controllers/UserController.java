package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pablo.Spring.Security.ApplicationUser;
import ru.pablo.Spring.Security.CUserDetailsService;
import ru.pablo.Spring.Security.JWT.AuthService;

@RestController
@RequestMapping()
public class UserController {
    @Autowired
    private AuthService authService;
    @Autowired
    private CUserDetailsService userService;

    @PostMapping("/signup")
    public void registration(@ModelAttribute ApplicationUser user){
        userService.save(user);
    }



}

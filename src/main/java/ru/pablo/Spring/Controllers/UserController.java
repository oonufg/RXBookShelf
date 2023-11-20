package ru.pablo.Spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pablo.Domain.Entities.User;
import ru.pablo.Spring.Security.CUserDetailsService;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private CUserDetailsService userService;

    @PostMapping("/signup")
    public void registration(@ModelAttribute User user){
        userService.save(user);
    }


}

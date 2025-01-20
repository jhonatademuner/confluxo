package com.bizflow.controller.user;

import com.bizflow.domain.user.User;
import com.bizflow.domain.user.dto.UserLoginDTO;
import com.bizflow.domain.user.dto.UserRegisterDTO;
import com.bizflow.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserRegisterDTO user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO user){
        return userService.verify(user);
    }

}

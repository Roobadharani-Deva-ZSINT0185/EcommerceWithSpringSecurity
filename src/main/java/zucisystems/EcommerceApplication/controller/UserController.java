package zucisystems.EcommerceApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zucisystems.EcommerceApplication.entity.User;
import zucisystems.EcommerceApplication.service.UserService;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User registerNewUser(User user){
        return userService.registerNewUser(user);
    }
    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "this url is only accessible for admin";
    }
    @GetMapping("/forUser")
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "this url is only accessible for user";
    }

}

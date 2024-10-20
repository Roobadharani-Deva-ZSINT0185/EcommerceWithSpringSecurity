package zucisystems.EcommerceApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import zucisystems.EcommerceApplication.entity.User;
import zucisystems.EcommerceApplication.service.UserService;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User registerNewUser(@RequestBody User user){

        return userService.registerNewUser(user);
    }
    @PutMapping("/forBoth")
    @Secured("hasAnyRole('ADMIN','USER')")
    public String putMethod(){
        return "this is put method for both admin and user";
    }
    @GetMapping("/forUser")
    @Secured("hasRole('USER')")
    public String GetMethod(){
        return "this is get method for user";
    }
    @DeleteMapping("/forAdmin")
    @Secured("hasRole('ADMIN')")
    public String deleteMethod(){return "this is delete method only for admin"; }
    @PostMapping("forBoth")
    @Secured("hasAnyRole('ADMIN','USER')")
    public String postMethod(){return "this is post method for both";}


}

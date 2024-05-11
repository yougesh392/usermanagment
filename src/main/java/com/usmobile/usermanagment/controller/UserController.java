package com.usmobile.usermanagment.controller;

import com.usmobile.usermanagment.model.User;
import com.usmobile.usermanagment.service.UserManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "User management API")
public class UserController {

    private UserManagementService userManagementService;

    @Autowired
    public UserController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @RequestMapping(value="/hello" , method = RequestMethod.GET)
    @Operation(summary = "hello endpoint", description = "This is a hello endpoint")
    public String hello() {
        return "Hello World!";
    }


    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user")
    public User createUser(@RequestBody User user) {
        return userManagementService.createUser(user);
    }

}

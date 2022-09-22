package com.kulturservice.controller;

import com.kulturservice.model.User;
import com.kulturservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/allUsers")
    public ResponseEntity<Set<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getUserByName")
    public ResponseEntity<List<User>>getUserByName(@RequestParam String name){
        return new ResponseEntity<>(userService.findUserByName(name),HttpStatus.OK);}
}
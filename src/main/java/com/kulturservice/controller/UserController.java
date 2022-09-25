package com.kulturservice.controller;

import com.kulturservice.model.User;
import com.kulturservice.model.Venue;
import com.kulturservice.service.UserService;
import com.kulturservice.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {

    public UserController(UserService userService, VenueService venueService) {
        this.userService = userService;
        this.venueService = venueService;
    }

    private UserService userService;
    private VenueService venueService;


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
    public ResponseEntity<List<User>> getUserByName(@RequestParam String name) {
        return new ResponseEntity<>(userService.findUserByName(name), HttpStatus.OK);
    }


    @PostMapping("/createLike")
    public ResponseEntity<String> createLike(@RequestParam Long uId, @RequestParam Long vId) {
        Optional<User> user_ = userService.findById(uId);
        Optional<Venue> venue_ = venueService.findById(vId);
        if ((user_.isPresent()) && (venue_.isPresent())) {
            User user = user_.get();
            Venue venue = venue_.get();
            user.getVenueLiked().add(venue);
            userService.save(user);
            return new ResponseEntity<>((venue.getVenueName() + " er tilføjet til " + user.getName() + " likes"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("kunne ikke oprette like", HttpStatus.BAD_REQUEST);
        }
    }
}
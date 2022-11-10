package com.kulturservice.controller;

import com.kulturservice.model.User;
import com.kulturservice.model.Venue;
import com.kulturservice.security.JwtUserDetailsService;
import com.kulturservice.security.TokenManager;
import com.kulturservice.security.models.JwtRequestModel;
import com.kulturservice.security.models.JwtResponseModel;
import com.kulturservice.service.RoleService;
import com.kulturservice.service.UserService;
import com.kulturservice.service.VenueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
//@AllArgsConstructor
public class UserController {


    private  JwtUserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private  TokenManager tokenManager;
    private  UserService userService;
    private  VenueService venueService;
    private  RoleService roleService;
    private  JwtUserDetailsService jwtUserDetailsService;

    public UserController(JwtUserDetailsService userDetailsService, AuthenticationManager authenticationManager, TokenManager tokenManager, UserService userService, VenueService venueService, RoleService roleService, JwtUserDetailsService jwtUserDetailsService) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userService = userService;
        this.venueService = venueService;
        this.roleService = roleService;
        this.jwtUserDetailsService = jwtUserDetailsService;
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
    public ResponseEntity<List<User>> getUserByName(@RequestParam String name) {
        return new ResponseEntity<>(userService.findUserByUserName(name), HttpStatus.OK);
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
            return new ResponseEntity<>((venue.getVenueName() + " er tilføjet til " + user.getUserName() + "s likes"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("kunne ikke oprette like", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<JwtResponseModel> signup(@RequestBody JwtRequestModel request) {
        System.out.println("signup: username:" + request.getUsername() + " password: " + request.getPassword());
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        User user = new User(request.getUsername(), encryptedPassword);
        if (userService.findUserByUserName(user.getUserName()).size() == 0) {
            if (userService.save(user) != null) {
                return ResponseEntity.ok(new JwtResponseModel("created user: " + user.getUserName() + " pw: " + user.getPassword(), null));
            } else {
                return ResponseEntity.ok(new JwtResponseModel("error creating user: " + user.getUserName(), null));
            }
        } else {
            return ResponseEntity.ok(new JwtResponseModel("error: user exists: " + user.getUserName(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel request) throws Exception {
        // HttpServletRequest servletRequest is available from Spring, if needed.
        System.out.println(" JwtController createToken Call: 4" + request.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new JwtResponseModel("bad credentials", null));
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken, userDetails.getAuthorities()));
    }


}
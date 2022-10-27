package com.kulturservice.controller;

import com.kulturservice.model.Band;
import com.kulturservice.model.User;
import com.kulturservice.service.BandService;
import com.kulturservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class BandController {

    private final BandService bandService;
    private final UserService userService;

    public BandController(BandService bandService, UserService userService) {
        this.bandService = bandService;
        this.userService = userService;
    }

    @PostMapping("/createBand")
    public ResponseEntity<Band> createBand(@RequestBody Band band) {
        bandService.save(band);
        return new ResponseEntity<>(band, HttpStatus.OK);
    }

    @GetMapping("/allBands")
    public ResponseEntity<Set<Band>> getAllBands() {
        return new ResponseEntity<>(bandService.findAll(), HttpStatus.OK);
    }

    @PostMapping("createBandLike")

    public ResponseEntity<String> createLike(@RequestParam Long uId, @RequestParam Long bId) {
        Optional<User> user_ = userService.findById(uId);
        Optional<Band> band_ = bandService.findById(bId);
        if ((user_.isPresent()) && (band_.isPresent())) {
            Band band = band_.get();
            User user = user_.get();
            user.getBandLiked().add(band);
            userService.save(user);
            return new ResponseEntity<>((band.getBandName() + " er tilføjet til " + user.getUserName() + "s likes"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("kunne ikke oprette like", HttpStatus.BAD_REQUEST);
        }
    }
}
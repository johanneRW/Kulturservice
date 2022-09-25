package com.kulturservice.controller;

import com.kulturservice.model.Venue;
import com.kulturservice.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping("/createVenue")
    public ResponseEntity<Venue> createBand(@RequestBody Venue venue) {
        venueService.save(venue);
        return new ResponseEntity<>(venue, HttpStatus.OK);
    }

    @GetMapping("/allVenues")
    public ResponseEntity<Set<Venue>> getAllBands() {
        return new ResponseEntity<>(venueService.findAll(), HttpStatus.OK);
    }
}


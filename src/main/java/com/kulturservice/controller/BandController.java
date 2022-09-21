package com.kulturservice.controller;

import com.kulturservice.model.Band;
import com.kulturservice.service.BandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class BandController {

    private BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @PostMapping("/createBand")
    public ResponseEntity<Band> createBand(Band band) {
        bandService.save(band);
        return new ResponseEntity<>(band, HttpStatus.OK);
    }

    @GetMapping("/allBands")
    public ResponseEntity<Set<Band>> getAllBands() {
        return new ResponseEntity<>(bandService.findAll(), HttpStatus.OK);
    }
}
package com.kulturservice.controller;

import com.kulturservice.model.Band;
import com.kulturservice.model.Event;
import com.kulturservice.model.Venue;
import com.kulturservice.service.BandService;
import com.kulturservice.service.EventService;
import com.kulturservice.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class EventController {

    private final EventService eventService;
    private final BandService bandService;
    private final VenueService venueService;

    public EventController(EventService eventService, BandService bandService, VenueService venueService) {
        this.eventService = eventService;
        this.bandService = bandService;
        this.venueService = venueService;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<Event> createEvent(@RequestParam Long bId, @RequestParam Long vId, @RequestBody Event event) {
        Optional<Band> band_ = bandService.findById(bId);
        Optional<Venue> venue_ = venueService.findById(vId);
        if ((band_.isPresent()) && (venue_.isPresent())) {
            Band band = band_.get();
            Venue venue = venue_.get();
            event.setBand(band);
            event.setVenue(venue);
            eventService.save(event);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/getEvents")
    public ResponseEntity<Set<Event>> getEvent() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getEventsSorted")
    public ResponseEntity<List<Event>> getEventSorted() {
        return new ResponseEntity<>(eventService.findAllByOrderByEventDateAsc(), HttpStatus.OK);

    }

@PreAuthorize("hasAuthority('user')")
    @GetMapping("/getFutureEvents")
    public ResponseEntity<List<Event>> getFutureEvents() {
        Date toDay = new Date();
        return new ResponseEntity<>(eventService.findAllByEventDateAfterOrderByEventDateAsc(toDay), HttpStatus.OK);
    }

    @GetMapping("/allEvents")
    public ResponseEntity<Set<Event>> getAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }
}
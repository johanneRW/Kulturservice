package com.kulturservice.controller;

import com.kulturservice.model.Band;
import com.kulturservice.model.Event;
import com.kulturservice.service.BandService;
import com.kulturservice.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class EventController {

    private EventService eventService;
    private BandService bandService;

    public EventController(EventService eventService, BandService bandService) {
        this.eventService = eventService;
        this.bandService = bandService;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<Event> createEvent(@RequestParam Long bId, @RequestParam Date date, @RequestParam String venue, Event event) {
        Optional<Band> band_ = bandService.findById(bId);
        if (band_.isPresent()) {
            Band band = band_.get();
            event.setBand(band);
            event.setEventDate(date);
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

    @GetMapping("/allEvents")
    public ResponseEntity<Set<Event>> getAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }
}
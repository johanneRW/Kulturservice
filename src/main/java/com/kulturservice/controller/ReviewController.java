package com.kulturservice.controller;


import com.kulturservice.model.Event;
import com.kulturservice.model.Review;
import com.kulturservice.model.User;
import com.kulturservice.service.EventService;
import com.kulturservice.service.ReviewService;
import com.kulturservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final EventService eventService;
    private final UserService userService;

    public ReviewController(ReviewService reviewService, EventService eventService, UserService userService) {
        this.reviewService = reviewService;
        this.eventService = eventService;
        this.userService = userService;
    }

    @PostMapping("/createReview")
    public ResponseEntity<Review> createEvent(@RequestParam Long eId, @RequestParam Long uId, @RequestBody Review review) {
        Optional<User> user_ = userService.findById(uId);
        Optional<Event> event_ = eventService.findById(eId);
        if (user_.isPresent() && (event_.isPresent())) {
            User user = user_.get();
            Event event = event_.get();
            review.setEvent(event);
            review.setUser(user);
            reviewService.save(review);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getReview")
    public ResponseEntity<Set<Review>> getReview(Long eId) {
        Optional<Event> event_ = eventService.findById(eId);
        if (event_.isPresent()) {
            Event event = event_.get();

            return new ResponseEntity<>(event.getReviews(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allReviews")
    public ResponseEntity<Set<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }
}
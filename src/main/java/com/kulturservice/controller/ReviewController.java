package com.kulturservice.controller;


import com.kulturservice.model.Event;
import com.kulturservice.model.Review;
import com.kulturservice.model.User;
import com.kulturservice.service.EventService;
import com.kulturservice.service.ReviewService;
import com.kulturservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
public class ReviewController {

    private ReviewService reviewService;
    private EventService eventService;
    private UserService userService;

    public ReviewController(ReviewService reviewService, EventService eventService, UserService userService) {
        this.reviewService = reviewService;
        this.eventService = eventService;
        this.userService = userService;
    }

    @PostMapping("/createReview")
    public ResponseEntity<Review> createEvent(@RequestParam Long eId, @RequestParam Long uId, @RequestParam String reviewText, @RequestParam String rating, Review review) {
        Optional<User> user_ = userService.findById(uId);
        if (user_.isPresent()) {
            User user = user_.get();

            Optional<Event> event_ = eventService.findById(eId);
            if (event_.isPresent()) {
                Event event = event_.get();
                review.setEvent(event);
                review.setUser(user);
                review.setText(reviewText);
                review.setRating(rating);
                reviewService.save(review);
                return new ResponseEntity<>(review, HttpStatus.OK);

            } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

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
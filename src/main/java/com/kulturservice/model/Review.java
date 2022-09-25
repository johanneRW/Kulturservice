package com.kulturservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JsonBackReference("Event")
    @EqualsAndHashCode.Exclude
    private Event event;

    private String text;

    private String rating;

    @ManyToOne
    @JsonBackReference("User")
    @EqualsAndHashCode.Exclude

    private User user;


}

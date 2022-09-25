package com.kulturservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Bestemmer hvilket dato- og tids-format der bliver udskrevet
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date eventDate;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @ManyToOne
    @JsonBackReference("Band")
    @EqualsAndHashCode.Exclude
    private Band band;


    //Udskriver navnet på det band der spiller
    @JsonManagedReference
    public String getBandName() {
        if (this.band != null) {
            return band.getBandName();
        } else {
            return null;
        }
    }

    @OneToMany(mappedBy = "event")
    private Set<Review> reviews = new HashSet<>();

}

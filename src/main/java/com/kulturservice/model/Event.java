package com.kulturservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date eventDate;

    private String venue;

    @ManyToOne
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Band band;

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

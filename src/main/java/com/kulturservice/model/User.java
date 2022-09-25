package com.kulturservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToMany
    @JoinTable(
            name = "venue_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "venue_id")
    )
    @JsonBackReference("venues")
    private Set<Venue> venueLiked = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "band_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id")
    )
    @JsonBackReference("bands")
    private Set<Band> bandLiked = new HashSet<>();


    @JsonManagedReference
    public Set<String> getBandsLiked() {
        if (this.bandLiked != null) {
            Set<String> bandNames = new HashSet<>();
            for (Band currentBand : bandLiked) {
                String bandName = currentBand.getBandName();
                bandNames.add(bandName);
            }
            return bandNames;
        } else {
            return null;
        }
    }

    @JsonManagedReference
    public Set<String> getVenuesLiked() {
        if (this.venueLiked != null) {
            Set<String> venuesNames = new HashSet<>();
            for (Venue currentVenue : venueLiked) {
                String venueName = currentVenue.getVenueName();
                venuesNames.add(venueName);
            }
            return venuesNames;
        } else {
            return null;
        }
    }

}

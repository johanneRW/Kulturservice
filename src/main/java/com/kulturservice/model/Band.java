package com.kulturservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bandName;

    @OneToMany(mappedBy="band")
    private Set<Event> events= new HashSet<>();

    @ManyToMany (mappedBy = "bandLiked")
    @JsonBackReference("bandLiked")
    private Set<User> userLikes=new HashSet<>();

}

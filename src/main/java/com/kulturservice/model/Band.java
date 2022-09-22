package com.kulturservice.model;


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
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bandName;

    @OneToMany(mappedBy="band")
    private Set<Event> events= new HashSet<>();

}

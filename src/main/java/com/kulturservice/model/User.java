package com.kulturservice.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Data
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


}

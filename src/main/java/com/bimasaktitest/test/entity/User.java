package com.bimasaktitest.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name =  "users")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long idUser;
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;
    @Column(name = "AGE",  nullable = false)
    private Integer age;
    @Column(name = "CITY", nullable = false)
    private String city;
}




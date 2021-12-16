package com.springbootcrud.springbootcrud.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employees")
public class Employee {


    @Id //It defines the following variable as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //It defines the strategies for the generation of value of the primary key
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

}

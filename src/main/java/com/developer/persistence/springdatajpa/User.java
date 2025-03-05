package com.developer.persistence.springdatajpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

// @Entity in Spring Data JPA marks a Java class as a database entity (a table).
// It tells Hibernate that this class should be mapped to a database table.
@Entity
@Table(name = "USERS")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NonNull
    private String username;
    
    @NonNull
    private LocalDate registrationDate;
}

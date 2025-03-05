package com.developer.persistence.springdatajpa;

import java.time.LocalDate;
import java.time.Month;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringdatajpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringdatajpaApplication.class, args);
  }

  @Bean
  public ApplicationRunner configure(UserRepository userRepository) {
    return args -> {
      // Create and save some users
      User user1 = new User("beth", LocalDate.of(2020, Month.AUGUST, 3));
      User user2 = new User("mike", LocalDate.of(2020, Month.JANUARY, 18));

      userRepository.save(user1);
      userRepository.save(user2);

      // Print all users
      userRepository.findAll().forEach(user -> System.out.println(user));
    };
  }
}

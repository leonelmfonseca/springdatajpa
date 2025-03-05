package com.developer.persistence.springdatajpa;

import org.springframework.data.repository.CrudRepository;

// CrudRepository<User, Long> provides basic CRUD operations (save, find, delete, etc.).
// No need for @Repository because Spring automatically detects it.
// Spring injects it as a bean, so you can use @Autowired or constructor injection to use it.
public interface UserRepository extends CrudRepository<User, Long> {}

/*
Spring dynamically generates a runtime proxy for repository interfaces instead of creating actual Java classes at compile-time.

This proxy intercepts method calls and delegates them to Hibernate’s EntityManager, handling all CRUD operations transparently.

JDK dynamic proxies are used exclusively for interfaces.

If the repository is defined as a concrete class rather than an interface, Spring automatically switches to CGLIB, which generates a subclass-based proxy instead.
 */
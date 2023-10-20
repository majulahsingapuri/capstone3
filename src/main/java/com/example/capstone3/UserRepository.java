package com.example.capstone3;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


// @Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}

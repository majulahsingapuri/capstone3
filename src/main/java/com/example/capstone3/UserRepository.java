package com.example.capstone3;

import org.springframework.data.jpa.repository.JpaRepository;


// @Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}

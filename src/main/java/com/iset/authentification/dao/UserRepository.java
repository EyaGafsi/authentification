package com.iset.authentification.dao;

import com.iset.authentification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        @Query(" select u from User u where u.username = ?1")
        Optional<User> findUserByUsername(String username);

}
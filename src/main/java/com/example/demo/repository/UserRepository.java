package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for User entity operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a User by their email address.
     * 
     * @param email the email address of the user
     * @return the User entity with the given email or null if none found
     */
    User findByEmail(String email);

    /**
     * Find the top 10 users ordered by their score in descending order.
     * 
     * @return a list of top 10 users with the highest scores
     */
    List<User> findTop10ByOrderByScoreDesc();

    /**
     * Find the user with the highest ID.
     * 
     * @return the User entity with the highest ID
     */
    User findTopByOrderByIdDesc();

    /**
     * Debugging method to fetch all users.
     * 
     * @return a list of all users
     */
    List<User> findAll();
    

}

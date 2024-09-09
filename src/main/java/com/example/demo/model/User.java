package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class representing a User in the system.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L; // Unique identifier for serialization

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int score;

    @Transient
    private String rank;


    @Transient
    private String passFailStatus; // Add this field
    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.score = 0; // Initialize score to default value
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    

    public String getPassFailStatus() {
        return passFailStatus;
    }

    public void setPassFailStatus(String passFailStatus) {
        this.passFailStatus = passFailStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                ", rank='" + rank + '\'' +
                ", passFailStatus='" + passFailStatus + '\'' +
                '}';
    }
}

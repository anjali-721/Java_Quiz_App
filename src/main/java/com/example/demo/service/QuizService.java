package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private UserRepository userRepository;

    public int calculateScore(String q1, String q2, String q3, String q4, String q5, 
                              String q6, String q7, String q8, String q9, String q10) {
        int score = 0;

        if ("b".equals(q1)) score++;
        if ("c".equals(q2)) score++;
        if ("a".equals(q3)) score++;
        if ("b".equals(q4)) score++;
        if ("b".equals(q5)) score++;
        if ("d".equals(q6)) score++;
        if ("d".equals(q7)) score++;
        if ("a".equals(q8)) score++;
        if ("a".equals(q9)) score++;
        if ("a".equals(q10)) score++;

        System.out.println("Calculated score: " + score);

        return score;
    }

    public int calculateRank(String email, int score) {
        List<User> allUsers = userRepository.findAll();

        System.out.println("All users and their scores:");
        for (User user : allUsers) {
            System.out.println(user);
        }

        List<User> sortedUsers = allUsers.stream()
            .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
            .collect(Collectors.toList());

        System.out.println("Sorted users and their scores:");
        for (User user : sortedUsers) {
            System.out.println(user);
        }

        int rank = 1;
        for (User user : sortedUsers) {
            if (user.getEmail().equals(email)) {
                System.out.println("Calculated rank for " + email + ": " + rank);
                return rank;
            }
            rank++;
        }

        System.out.println("User not found. Returning rank: " + (sortedUsers.size() + 1));
        return sortedUsers.size() + 1;
    }

 
}

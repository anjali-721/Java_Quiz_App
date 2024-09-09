package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ResultController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/leaderboard")
    public String getTop10Rankers(@RequestParam("name") String name,
                                  @RequestParam("email") String email,
                                  Model model) {
        // Fetch top 10 rankers from the database
        List<User> topRankers = userRepository.findTop10ByOrderByScoreDesc();

        // Add attributes to the model
        model.addAttribute("topRankers", topRankers);

        return "all-results";
    }
}

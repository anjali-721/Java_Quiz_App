package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/take-quiz")
    public String showQuizPage(
            @RequestParam("name") String name, 
            @RequestParam("email") String email, 
            Model model) {
        
        // Add name and email to the model to be used in the quiz.html
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        
        return "Quiz";  // Returns the quiz HTML page
    }

    @PostMapping("/submit")
    // @Transactional
    public String processQuizResults(
    		@RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("q1") String q1,
            @RequestParam("q2") String q2,
            @RequestParam("q3") String q3,
            @RequestParam("q4") String q4,
            @RequestParam("q5") String q5,
            @RequestParam("q6") String q6,
            @RequestParam("q7") String q7,
            @RequestParam("q8") String q8,
            @RequestParam("q9") String q9,
            @RequestParam("q10") String q10,
            
            RedirectAttributes redirectAttributes) {


        System.out.println("Received name: " + name);
        System.out.println("Received email: " + email);

        // Calculate score
        int score = quizService.calculateScore(q1, q2, q3, q4, q5, q6, q7, q8, q9, q10);

 

        // Check if user exists
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            // User exists, update score
            existingUser.setScore(score);
            userRepository.save(existingUser);
        } else {
            //User does not exist, create new user
            User user = new User(name, email);
            user.setScore(score);
            userRepository.save(user);
        
        }
        // Calculate rank
        int rank = quizService.calculateRank(email, score);

        // Add attributes for redirect
        redirectAttributes.addFlashAttribute("score", score);
        redirectAttributes.addFlashAttribute("name", name);
        redirectAttributes.addFlashAttribute("email", email);
        redirectAttributes.addFlashAttribute("rank", rank);

        return "redirect:/result";  // Redirect to the result page
    }
    
 
    @GetMapping("/result")
    public String showResultPage() {
        return "result";  // Returns the result HTML page
    }
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.QuizService;



import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;

@Controller

public class HomeController {

    @Autowired
    private UserRepository userRepository;
    
   

    @GetMapping("/")
    public String home() {
        return "home";
    }
    

    @PostMapping("/register")
    public String registerUser(@RequestParam("name") String name,
                               @RequestParam("email") String email,
                               RedirectAttributes redirectAttributes) {
        // Check if the user already exists based on email
        User existingUser = userRepository.findByEmail(email);

        if (existingUser == null) {
            // If user doesn't exist, create a new one
            User user = new User(name, email);
            userRepository.save(user);
        } else {
            // If the user exists, 
          return "redirect:/result";            
        }

        // Store the name and email in redirect attributes
        redirectAttributes.addFlashAttribute("name", name);
        redirectAttributes.addFlashAttribute("email", email);

        return "redirect:/quiz";
    }




    @GetMapping("/quiz")
    public ModelAndView showQuizPage(ModelAndView modelAndView) {
        modelAndView.setViewName("Quiz"); // View name
        return modelAndView;
    }
}

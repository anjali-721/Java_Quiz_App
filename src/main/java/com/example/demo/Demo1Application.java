package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        try {
            String url = "http://localhost:8081"; // Change this if your application runs on a different port or URL
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

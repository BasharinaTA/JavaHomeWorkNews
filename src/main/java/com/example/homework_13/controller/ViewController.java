package com.example.homework_13.controller;

import com.example.homework_13.model.News;
import com.example.homework_13.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ViewController {
    private UserService userService;

    @GetMapping
    public String home() {
        return "pages/home";
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("registration", false);
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("errorAuth", true);
        }
        return "pages/login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("registration", true);
        return "pages/login";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("user", userService.getByUsername(username));
        return "pages/profile";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("news", new News());
        return "pages/addNews";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "pages/searchNews";
    }
}

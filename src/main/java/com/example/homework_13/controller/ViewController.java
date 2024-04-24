package com.example.homework_13.controller;

import com.example.homework_13.model.News;
import com.example.homework_13.model.User;
import com.example.homework_13.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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
            return "pages/login";
        }
        return "pages/login";
    }

    @GetMapping("/registration")
    public String registration(Model model, Authentication authentication) {
        model.addAttribute("registration", true);
        return "pages/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.getByUsername(username));
        return "pages/profile";
    }

    @GetMapping("/add")
    public String pageAdd(Model model) {
        model.addAttribute("news", new News());
        return "pages/addNews";
    }

    @GetMapping("/search")
    public String pageSearch() {
        return "pages/searchNews";
    }
}

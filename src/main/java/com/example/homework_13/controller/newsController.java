package com.example.homework_13.controller;

import com.example.homework_13.model.News;
import com.example.homework_13.service.NewsService;
import com.example.homework_13.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
@AllArgsConstructor
@RequestMapping("/news")
public class newsController {
    private NewsService newsService;
    private UserService userService;

    @GetMapping
    public String news(Model model) {
        model.addAttribute("news", newsService.getAll());
        return "pages/news";
    }

    @PostMapping("/add")
    public String add(News news, Principal principal) {
        String username = principal.getName();
        news.setUser(userService.getByUsername(username));
        news.setDate(new Date());
        newsService.add(news);
        return "redirect:/news";
    }

    @PostMapping("/search/header")
    public String header(Model model, @RequestParam String header) {
        model.addAttribute("news", newsService.getByHeader(header));
        return "pages/news";
    }

    @PostMapping("/search/text")
    public String text(Model model, @RequestParam String text) {
        model.addAttribute("news", newsService.getByText(text));
        return "pages/news";
    }

    @PostMapping("/search/date")
    public String date(Model model, @RequestParam String strDate)  {
        model.addAttribute("news", newsService.getByDate(strDate));
        return "pages/news";
    }

    @GetMapping("/user")
    public String newsUser(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("news", newsService.getByUser(userService.getByUsername(username)));
        return "pages/news";
    }
}

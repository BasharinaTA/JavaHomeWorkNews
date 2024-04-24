package com.example.homework_13.controller;

import com.example.homework_13.model.Role;
import com.example.homework_13.model.Status;
import com.example.homework_13.model.User;
import com.example.homework_13.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class userController {
    private UserService userService;

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", Role.values());
        model.addAttribute("statuses", Status.values());
        return "pages/admin";
    }

    @PostMapping("/add")
    public String add(@RequestParam String username,
                      @RequestParam String fio,
                      @RequestParam String password,
                      @RequestParam String repeatPassword,
                      RedirectAttributes ra) {
        if (userService.getByUsername(username) != null) {
            ra.addFlashAttribute("error", "login");
            ra.addFlashAttribute("name", username);
            ra.addFlashAttribute("fio", fio);
            return "redirect:/registration";
        }
        if (!password.equals(repeatPassword)) {
            ra.addFlashAttribute("error", "password");
            ra.addFlashAttribute("name", username);
            ra.addFlashAttribute("fio", fio);
            return "redirect:/registration";
        }
        userService.add(User.builder()
                .username(username)
                .fio(fio)
                .password(new BCryptPasswordEncoder(12).encode(password))
                .role(Role.ROLE_USER)
                .status(Status.ACTIVE)
                .build());
        return "redirect:/profile";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id,
                         @RequestParam String strRole,
                         @RequestParam String strStatus) {
        User user = userService.getById(id);
        Role role = Role.valueOf(strRole);
        Status status = Status.valueOf(strStatus);
        user.setRole(role);
        user.setStatus(status);
        userService.update(user);
        return "redirect:/users/all";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/users/all";
    }
}

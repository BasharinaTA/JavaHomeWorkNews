package com.example.homework_13.service;

import com.example.homework_13.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getById(Integer id);

    User getByUsername(String username);

    List<User> getAll();

    User add(User user);

    User update(User user);

    void delete(Integer id);
}

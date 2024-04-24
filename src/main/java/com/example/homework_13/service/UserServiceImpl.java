package com.example.homework_13.service;

import com.example.homework_13.model.User;
import com.example.homework_13.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAllByOrderByFio();
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> userToUpdateOptional =  userRepository.findById(user.getId());
        if (userToUpdateOptional.isPresent()) {
            User userToUpdate = userToUpdateOptional.get();
            userToUpdate.setRole(user.getRole());
            userToUpdate.setStatus(user.getStatus());
            return userRepository.save(userToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}

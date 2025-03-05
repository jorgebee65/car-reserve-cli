package com.amigos.code.service;

import com.amigos.code.model.User;
import com.amigos.code.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserbyId(String id) {
        return userRepository.getUserById(id);
    }
}

package com.amigos.code.service;

import com.amigos.code.model.User;
import com.amigos.code.repository.UserArrayDataAccessService;
import com.amigos.code.repository.UserFileDataAccessService;

import java.util.List;

public class UserService {
    //private final UserArrayDataAccessService userRepository = new UserArrayDataAccessService();
    private final UserFileDataAccessService userRepository = new UserFileDataAccessService();

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserbyId(String id) {
        return userRepository.getUserById(id);
    }
}

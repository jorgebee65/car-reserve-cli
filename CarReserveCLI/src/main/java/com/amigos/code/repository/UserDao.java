package com.amigos.code.repository;

import com.amigos.code.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    User getUserById(String id);
}

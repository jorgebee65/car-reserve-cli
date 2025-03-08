package com.amigos.code.repository;

import com.amigos.code.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserArrayDataAccessService implements UserDao {

    private static final List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User("1234", "Juan Perez"));
        users.add(new User("1235", "Jaime Lopez"));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}

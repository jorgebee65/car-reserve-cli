package com.amigos.code.repository;

import com.amigos.code.model.User;
import com.opencsv.CSVReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserFileDataAccessService implements UserDao{

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        String resourcePath = "users.csv";
        try (InputStream inputStream = CSVReader.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",\\s*"); // Divide por coma y elimina espacios
                if (data.length == 2) {
                    users.add(new User(data[0], data[1]));
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserById(String id) {
        return getUsers().stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(RuntimeException::new);
    }
}

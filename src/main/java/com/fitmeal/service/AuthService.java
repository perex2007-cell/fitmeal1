/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.service;


import com.fitmeal.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yesid Ocampo
 */


public class AuthService {

    private static AuthService instance;
    private User loggedUser;

    private List<User> users = new ArrayList<>();

    // Constructor privado
    private AuthService() {
        // Usuario de prueba inicial
        users.add(new User(
                "Juan Pérez",
                "juan@fitmeal.com",
                "1234"
        ));
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    /* =========================
       LOGIN
       ========================= */
    public boolean login(User user) {
        return login(user.getEmail(), user.getPassword());
    }

    public boolean login(String email, String password) {

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password)) {

                loggedUser = user;
                return true;
            }
        }
        return false;
    }

    /* =========================
       REGISTER
       ========================= */
    public boolean register(String name, String email, String password) {

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return false; // Email ya existe
            }
        }

        users.add(new User(name, email, password));
        return true;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isUserLoggedIn() {
        return loggedUser != null;
    }

    public void logout() {
        loggedUser = null;
    }
}




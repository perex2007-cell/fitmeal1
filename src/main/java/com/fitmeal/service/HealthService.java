/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.service;

import com.fitmeal.model.UserProfile;
/**
 *
 * @author Yesid Ocampo
 */

public class HealthService {

    public double calculateBMI(UserProfile user) {
        return user.getWeight() /
               (user.getHeight() * user.getHeight());
    }

    public String classifyBMI(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}


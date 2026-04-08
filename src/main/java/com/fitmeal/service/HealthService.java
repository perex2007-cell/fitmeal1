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

public class HealthService implements IBiometricCalculator {

    @Override
    public double calculateBMI(UserProfile user) {
        return calculateBMI(user.getWeight(), user.getHeight());
    }

    @Override
    public double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    @Override
    public String classifyBMI(double bmi) {
        if (bmi < 18.5) {
            return "Bajo peso";
        } else if (bmi < 25) {
            return "Peso normal";
        } else if (bmi < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }
}


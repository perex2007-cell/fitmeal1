package com.fitmeal.service;

import com.fitmeal.model.UserProfile;

public interface IBiometricCalculator {
    // Calculo usando el objeto UserProfile
    double calculateBMI(UserProfile user);
    
    // Sobrecarga: Calculo usando directamente peso y altura
    double calculateBMI(double weight, double height);
    
    // Clasificacion del índice de masa corporal
    String classifyBMI(double bmi);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.model;

/**
 *
 * @author Yesid Ocampo
 */

public class UserProfile {

    private double weight;
    private double height;
    private int age;
    private Goal goal;

    public UserProfile(double weight, double height, int age, Goal goal) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.goal = goal;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public Goal getGoal() {
        return goal;
    }
}


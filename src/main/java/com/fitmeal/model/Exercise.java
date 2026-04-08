/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.model;

/**
 *
 * @author Yesid Ocampo
 */

public class Exercise {

    private String name;
    private String description;
    private Goal goal;
    private String imageUrl;

    public Exercise(String name, String description, Goal goal, String imageUrl) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.imageUrl = imageUrl;
    }

    public Exercise(String name, String description, Goal goal) {
        this(name, description, goal, "");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Goal getGoal() {
        return goal;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

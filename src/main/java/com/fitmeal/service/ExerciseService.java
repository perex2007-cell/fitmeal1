/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.service;


import com.fitmeal.model.Exercise;
import com.fitmeal.model.Goal;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Yesid Ocampo
 */

public class ExerciseService {

    private List<Exercise> exercises = new ArrayList<>();

    public ExerciseService() {

        // Ejercicios para bajar de peso
        exercises.add(new Exercise(
                "Correr",
                "Actividad cardiovascular ideal para quemar calorías.",
                Goal.WEIGHT_LOSS
        ));

        exercises.add(new Exercise(
                "Saltar la cuerda",
                "Mejora la resistencia y ayuda a reducir grasa corporal.",
                Goal.WEIGHT_LOSS
        ));

        // Ejercicios para subir de peso
        exercises.add(new Exercise(
                "Sentadillas",
                "Ejercicio de fuerza para desarrollar masa muscular.",
                Goal.WEIGHT_GAIN
        ));

        exercises.add(new Exercise(
                "Flexiones",
                "Fortalece el tren superior y aumenta masa muscular.",
                Goal.WEIGHT_GAIN
        ));

        exercises.add(new Exercise(
                "Yoga dinámico",
                "Mejora la flexibilidad y relaja el cuerpo después de entrenar.",
                Goal.WEIGHT_LOSS
        ));

        exercises.add(new Exercise(
                "Estiramientos profundos",
                "Aumenta el rango de movimiento y reduce el riesgo de lesiones.",
                Goal.WEIGHT_LOSS
        ));
    }

    public List<Exercise> getExercisesByGoal(Goal goal) {

        List<Exercise> result = new ArrayList<>();

        for (Exercise exercise : exercises) {
            if (exercise.getGoal() == goal) {
                result.add(exercise);
            }
        }

        return result;
    }

    public List<Exercise> getExercisesByCategory(String category) {
        List<Exercise> result = new ArrayList<>();

        switch (category) {
            case "fuerza-musculo" -> {
                for (Exercise exercise : exercises) {
                    if (exercise.getGoal() == Goal.WEIGHT_GAIN) {
                        result.add(exercise);
                    }
                }
            }
            case "yoga-estiramiento" -> {
                for (Exercise exercise : exercises) {
                    if (exercise.getName().toLowerCase().contains("yoga") || exercise.getName().toLowerCase().contains("estiramiento")) {
                        result.add(exercise);
                    }
                }
            }
            default -> {
                for (Exercise exercise : exercises) {
                    if (exercise.getGoal() == Goal.WEIGHT_LOSS && !exercise.getName().toLowerCase().contains("yoga") && !exercise.getName().toLowerCase().contains("estiramiento")) {
                        result.add(exercise);
                    }
                }
            }
        }

        return result;
    }
}


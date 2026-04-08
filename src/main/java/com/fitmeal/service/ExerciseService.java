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
                Goal.WEIGHT_LOSS,
                "https://www.palabraderunner.com/wp-content/uploads/2014/06/correr-wallpaper.jpg"
        ));

        exercises.add(new Exercise(
                "Saltar la cuerda",
                "Mejora la resistencia y ayuda a reducir grasa corporal.",
                Goal.WEIGHT_LOSS,
                "https://uploads-ssl.webflow.com/609aa41bb752e648eb4cb693/60f03dac703f11361bb1fe7c_saltar%20cuerda.jpg"
        ));

        exercises.add(new Exercise(
                "Burpees",
                "Ejercicio total que combina fuerza y cardio para quemar calorías rápido.",
                Goal.WEIGHT_LOSS,
                "https://images.unsplash.com/photo-1571019613914-85f342c1fbd4?auto=format&fit=crop&w=800&q=80"
        ));

        exercises.add(new Exercise(
                "Escaladores",
                "Aumenta tu resistencia y trabajo cardiovascular con cada repetición.",
                Goal.WEIGHT_LOSS,
                "https://images.unsplash.com/photo-1517964603305-9ca04d82fe31?auto=format&fit=crop&w=800&q=80"
        ));

        // Ejercicios para subir de peso
        exercises.add(new Exercise(
                "Sentadillas",
                "Ejercicio de fuerza para desarrollar masa muscular.",
                Goal.WEIGHT_GAIN,
                "https://images.unsplash.com/photo-1558611848-73f7eb4001f7?auto=format&fit=crop&w=800&q=80"
        ));

        exercises.add(new Exercise(
                "Flexiones",
                "Fortalece el tren superior y aumenta masa muscular.",
                Goal.WEIGHT_GAIN,
                "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?auto=format&fit=crop&w=800&q=80"
        ));

        exercises.add(new Exercise(
                "Press de banca",
                "Potencia pecho, hombros y tríceps con un movimiento clásico de fuerza.",
                Goal.WEIGHT_GAIN,
                "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?auto=format&fit=crop&w=800&q=80"
        ));

        exercises.add(new Exercise(
                "Peso muerto",
                "Construye una base fuerte de cuerpo entero con este levantamiento clave.",
                Goal.WEIGHT_GAIN,
                "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?auto=format&fit=crop&w=800&q=80"
        ));

        exercises.add(new Exercise(
                "Yoga dinámico",
                "Mejora la flexibilidad y relaja el cuerpo después de entrenar.",
                Goal.WEIGHT_LOSS,
                "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?auto=format&fit=crop&w=800&q=80"
        ));

        exercises.add(new Exercise(
                "Estiramientos profundos",
                "Aumenta el rango de movimiento y reduce el riesgo de lesiones.",
                Goal.WEIGHT_LOSS,
                "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?auto=format&fit=crop&w=800&q=80"
        ));

        exercises.add(new Exercise(
                "Posturas de recuperación",
                "Rutina suave para recuperar el cuerpo entre entrenamientos intensos.",
                Goal.WEIGHT_LOSS,
                "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?auto=format&fit=crop&w=800&q=80"
        ));

        exercises.add(new Exercise(
                "Movilidad articular",
                "Aumenta el rango articular con movimientos controlados y conscientes.",
                Goal.WEIGHT_LOSS,
                "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?auto=format&fit=crop&w=800&q=80"
        ));

        // Ejercicios para mantener peso
        exercises.add(new Exercise(
                "Natación recreativa",
                "Mano a mano entre cardio y resistencia sin impacto articular.",
                Goal.MAINTAIN_WEIGHT,
                "https://images.unsplash.com/photo-1530549387631-afb16881966a?auto=format&fit=crop&w=800&q=80"
        ));

        exercises.add(new Exercise(
                "Pilates mat",
                "Control central y tonificación equilibrada para un cuerpo funcional.",
                Goal.MAINTAIN_WEIGHT,
                "https://images.unsplash.com/photo-1518611012118-296072bb4f4a?auto=format&fit=crop&w=800&q=80"
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
                    if (exercise.getName().toLowerCase().contains("yoga") 
                            || exercise.getName().toLowerCase().contains("estiramiento")
                            || exercise.getName().toLowerCase().contains("pilates")) {
                        result.add(exercise);
                    }
                }
            }
            case "mantenimiento" -> {
                for (Exercise exercise : exercises) {
                    if (exercise.getGoal() == Goal.MAINTAIN_WEIGHT) {
                        result.add(exercise);
                    }
                }
            }
            default -> { // Cardio / Perdida de peso
                for (Exercise exercise : exercises) {
                    if (exercise.getGoal() == Goal.WEIGHT_LOSS 
                            && !exercise.getName().toLowerCase().contains("yoga") 
                            && !exercise.getName().toLowerCase().contains("estiramiento")) {
                        result.add(exercise);
                    }
                }
            }
        }

        return result;
    }
}


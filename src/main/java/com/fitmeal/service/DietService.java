/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.service;

import com.fitmeal.model.Goal;

/**
 *
 * @author Yesid Ocampo
 */

public class DietService {

    public String getRecommendation(Goal goal) {

        if (goal == Goal.WEIGHT_LOSS) {
            return """
                Dieta para perder peso:
                - Vegetales y proteinas
                - Proteinas limpias
                - Toma suficiente agua 
                - Evita el azucar y comidas fritas
                """;
        } else {
            return """
                Dieta para ganar peso:
                - Proteinas (Pollo, Pescado, huevos)
                - carboidratos 
                - Harinas limpias 
                - Come 5 comidas al dia
                """;
        }
    }
}


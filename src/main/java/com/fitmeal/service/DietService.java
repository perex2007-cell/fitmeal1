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
        return switch (goal) {
            case WEIGHT_LOSS -> """
                Dieta para Perder Peso:
                - Prioriza vegetales y proteínas magras
                - Proteínas limpias: pollo a la plancha, pescado, clara de huevo
                - Bebe al menos 2.5 litros de agua al día
                - Evita el azúcar refinada y comidas fritas
                - Déficit calórico moderado del 15-20%
                """;
            case WEIGHT_GAIN -> """
                Dieta para Ganar Masa Muscular:
                - Proteínas de alto valor biológico: pollo, pescado, huevos
                - Carbohidratos complejos: arroz integral, avena, batata
                - Harinas limpias y grasas saludables (aguacate, frutos secos)
                - Come 5-6 comidas al día con superávit calórico del 10-15%
                """;
            case MAINTAIN_WEIGHT -> """
                Dieta para Mantener el Peso:
                - Balance equilibrado de macronutrientes
                - Proteínas, carbohidratos complejos y grasas saludables
                - 3 comidas principales y 2 meriendas nutritivas al día
                - Mantén constancia en las calorías totales diarias
                """;
        };
    }
}


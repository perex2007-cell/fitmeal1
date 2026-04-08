/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.view;

import com.fitmeal.model.Exercise;
import com.fitmeal.model.Goal;
import com.fitmeal.model.UserProfile;
import com.fitmeal.service.ExerciseService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.Route;
import java.util.List;


/**
 *
 * @author Yesid Ocampo
 */

@Route(value = "exercises", layout = MainLayout.class)

public class ExerciseView extends ProtectedView {

    private ExerciseService exerciseService = new ExerciseService();

    public ExerciseView() {

        setSpacing(true);
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        /* ===== DATOS DE EJEMPLO ===== */
        UserProfile user = new UserProfile(
                70,
                1.70,
                25,
                Goal.WEIGHT_LOSS
        );

        H2 title = new H2("Ejercicios recomendados ");

        String goalText = user.getGoal() == Goal.WEIGHT_LOSS
                ? "Bajar de peso"
                : "Subir de peso";

        H3 subtitle = new H3("Objetivo: " + goalText);

        add(title, subtitle);

        List<Exercise> exercises =
                exerciseService.getExercisesByGoal(user.getGoal());

        for (Exercise exercise : exercises) {

            VerticalLayout card = new VerticalLayout(
                    new H3(exercise.getName()),
                    new Paragraph(exercise.getDescription())
            );

            styleCard(card, "#F3E5F5"); // morado suave
            add(card);
        }

        /* ===== BOTÓN VOLVER ===== */
        Button backButton = new Button("Volver al dashboard");
        backButton.addClickListener(e ->
                getUI().ifPresent(ui -> ui.navigate("dashboard"))
        );

        add(backButton);
        add(new FooterComponent());
    }

    /* ===== ESTILO REUTILIZABLE ===== */
    private void styleCard(VerticalLayout card, String backgroundColor) {
        card.getStyle()
                .set("background", backgroundColor)
                .set("border-radius", "14px")
                .set("padding", "20px")
                .set("box-shadow", "0 10px 25px rgba(0,0,0,0.08)")
                .set("max-width", "400px")
                .set("margin-bottom", "12px");
    }
}


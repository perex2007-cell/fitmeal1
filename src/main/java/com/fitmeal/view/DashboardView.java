/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.view;

import com.fitmeal.model.Goal;
import com.fitmeal.model.UserProfile;
import com.fitmeal.service.AuthService;
import com.fitmeal.service.DietService;
import com.fitmeal.service.HealthService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

/**
 *
 * @author Yesid Ocampo
 */


@Route(value = "dashboard", layout = MainLayout.class)

public class DashboardView extends ProtectedView {

    private AuthService authService = AuthService.getInstance();
    private HealthService healthService = new HealthService();
    private DietService dietService = new DietService();

    public DashboardView() {

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

        double bmi = healthService.calculateBMI(user);
        String classification = healthService.classifyBMI(bmi);
        String diet = dietService.getRecommendation(user.getGoal());

        /* ===== TÍTULO ===== */
        H2 title;

        if (authService.isUserLoggedIn()) {
            title = new H2("Bienvenido, "
                    + authService.getLoggedUser().getName()
                    );
        } else {
            title = new H2("Bienvenido a FitMeal 🍎");
        }

        H3 subtitle = new H3("Resumen de tu estado de salud");

        /* ===== LOGOUT ===== */
        Button logoutButton = new Button("Cerrar sesión");
        logoutButton.addClickListener(e -> {
            authService.logout();
            getUI().ifPresent(ui -> ui.navigate("login"));
        });

        HorizontalLayout header = new HorizontalLayout(title, logoutButton);
        header.setWidthFull();
        header.setAlignItems(Alignment.CENTER);
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.getStyle().set("margin-bottom", "20px");

        /* ===== TARJETAS ===== */
        VerticalLayout bmiCard = new VerticalLayout(
                new H3("IMC"),
                new Paragraph(String.format("%.2f", bmi)),
                new Paragraph(classification)
        );
        styleCard(bmiCard, "#E8F5E9");

        VerticalLayout goalCard = new VerticalLayout(
                new H3("Objetivo"),
                new Paragraph(
                        user.getGoal() == Goal.WEIGHT_LOSS
                                ? "Bajar de peso"
                                : "Subir de peso"
                )
        );
        styleCard(goalCard, "#E3F2FD");

        VerticalLayout dietCard = new VerticalLayout(
                new H3("Dieta recomendada"),
                new Paragraph(diet)
        );
        styleCard(dietCard, "#FFFDE7");

        HorizontalLayout cards = new HorizontalLayout(
                bmiCard,
                goalCard,
                dietCard
        );
        cards.setSpacing(true);
        cards.setJustifyContentMode(JustifyContentMode.CENTER);

        /* ===== BOTONES ===== */
        Button exercisesButton = new Button("Ejercicios ");
        Button infoButton = new Button("Datos de interés ℹ️");

        exercisesButton.getStyle()
                .set("background", "#1976D2")
                .set("color", "white")
                .set("border-radius", "10px");

        infoButton.getStyle()
                .set("background", "#388E3C")
                .set("color", "white")
                .set("border-radius", "10px");

        exercisesButton.addClickListener(e ->
                getUI().ifPresent(ui -> ui.navigate("exercises"))
        );

        infoButton.addClickListener(e ->
                getUI().ifPresent(ui -> ui.navigate("info"))
        );

        HorizontalLayout actions = new HorizontalLayout(
                exercisesButton,
                infoButton
        );

        add(
                header,
                subtitle,
                cards,
                actions,
                new FooterComponent()
        );
    }

    /* ===== ESTILO REUTILIZABLE ===== */
    private void styleCard(VerticalLayout card, String backgroundColor) {
        card.getStyle()
                .set("background", backgroundColor)
                .set("border-radius", "14px")
                .set("padding", "20px")
                .set("box-shadow", "0 10px 25px rgba(0,0,0,0.08)")
                .set("max-width", "360px");
    }
}




package com.fitmeal.view;

import com.fitmeal.model.Goal;
import com.fitmeal.model.User;
import com.fitmeal.model.UserProfile;
import com.fitmeal.service.AuthService;
import com.fitmeal.service.DietService;
import com.fitmeal.service.HealthService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends ProtectedView {

    private AuthService authService = AuthService.getInstance();
    private HealthService healthService = new HealthService();
    private DietService dietService = new DietService();

    public DashboardView() {
        setSpacing(false);
        setPadding(false);
        setAlignItems(Alignment.CENTER);
        getStyle().set("background-color", "#f8fdfd");

        User loggedUser = authService.getLoggedUser();
        UserProfile profile = (loggedUser != null) ? loggedUser.getProfile() : null;

        double bmi;
        String classification;
        String diet;
        String goalLabel;

        if (profile != null) {
            bmi = healthService.calculateBMI(profile);
            classification = healthService.classifyBMI(bmi);
            diet = dietService.getRecommendation(profile.getGoal());
            goalLabel = profile.getGoal().getLabel();
        } else {
            bmi = 0;
            classification = "Sin datos";
            diet = "Usa la calculadora en el Inicio para obtener tu plan personalizado.";
            goalLabel = "No definido";
        }

        VerticalLayout container = new VerticalLayout();
        container.setMaxWidth("1000px");
        container.setPadding(true);
        container.getStyle().set("margin-top", "40px").set("margin-bottom", "40px");

        H2 title;
        if (loggedUser != null) {
            title = new H2("Hola, " + loggedUser.getName());
        } else {
            title = new H2("Tu Dashboard FitMeal");
        }
        title.getStyle().set("color", "#0A6D75").set("margin-bottom", "5px");
        
        Paragraph subtitle = new Paragraph("Aquí tienes el resumen de tu estado actual");
        subtitle.getStyle().set("color", "#666").set("margin-top", "0").set("margin-bottom", "30px");

        HorizontalLayout header = new HorizontalLayout(new VerticalLayout(title, subtitle));
        header.setWidthFull();
        header.setAlignItems(Alignment.CENTER);
        header.setJustifyContentMode(JustifyContentMode.START);
        ((VerticalLayout) header.getComponentAt(0)).setPadding(false);

        // Tarjetas
        Div cardsGrid = new Div();
        cardsGrid.getStyle()
                 .set("display", "grid")
                 .set("grid-template-columns", "repeat(auto-fit, minmax(280px, 1fr))")
                 .set("gap", "20px")
                 .set("width", "100%");

        cardsGrid.add(
            createCard("Tu Índice de Masa Corporal", bmi > 0 ? String.format("%.2f", bmi) + " (" + classification + ")" : classification, "#E0F2F1"),
            createCard("Objetivo Activo", goalLabel, "#FFF3E0"),
            createCard("Plan Asignado", diet, "#F3E5F5")
        );

        // Botones de acción inferior
        Button exercisesButton = new Button("Ver Ejercicios");
        exercisesButton.getStyle().set("background-color", "#0A6D75").set("color", "white").set("padding", "10px 20px");
        exercisesButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("exercises")));

        Button infoButton = new Button("Blog / Tips");
        infoButton.getStyle().set("background-color", "transparent").set("color", "#0A6D75").set("border", "1px solid #0A6D75").set("padding", "10px 20px");
        infoButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("info")));

        HorizontalLayout actions = new HorizontalLayout(exercisesButton, infoButton);
        actions.getStyle().set("margin-top", "30px");

        container.add(header, cardsGrid, actions);
        add(container, new FooterComponent());
    }

    private VerticalLayout createCard(String titleText, String contentText, String bgColor) {
        VerticalLayout card = new VerticalLayout();
        card.getStyle()
            .set("background-color", "white")
            .set("border-top", "4px solid " + bgColor)
            .set("border-radius", "10px")
            .set("padding", "20px")
            .set("box-shadow", "0 5px 15px rgba(0,0,0,0.05)");

        H3 t = new H3(titleText);
        t.getStyle().set("margin", "0 0 10px 0").set("font-size", "1.1rem").set("color", "#333");
        Paragraph p = new Paragraph(contentText);
        p.getStyle().set("margin", "0").set("color", "#666").set("line-height", "1.5");
        
        card.add(t, p);
        return card;
    }
}

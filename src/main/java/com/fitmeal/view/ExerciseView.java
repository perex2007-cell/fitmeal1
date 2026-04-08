package com.fitmeal.view;

import com.fitmeal.model.Exercise;
import com.fitmeal.model.Goal;
import com.fitmeal.model.UserProfile;
import com.fitmeal.service.ExerciseService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route(value = "exercises", layout = MainLayout.class)
public class ExerciseView extends ProtectedView implements BeforeEnterObserver {

    private ExerciseService exerciseService = new ExerciseService();
    private Goal selectedGoal = Goal.WEIGHT_LOSS;

    private VerticalLayout container;
    private H2 title;
    private Paragraph subtitle;
    private Div cardsGrid;

    public ExerciseView() {
        setSpacing(false);
        setPadding(false);
        setAlignItems(Alignment.CENTER);
        getStyle().set("background-color", "#f8fdfd");

        container = new VerticalLayout();
        container.setMaxWidth("1000px");
        container.setPadding(true);
        container.getStyle().set("margin-top", "40px").set("margin-bottom", "40px");

        title = new H2();
        title.getStyle().set("color", "#0A6D75").set("margin-bottom", "5px");

        subtitle = new Paragraph();
        subtitle.getStyle().set("color", "#666").set("margin-top", "0").set("margin-bottom", "30px");

        cardsGrid = new Div();
        cardsGrid.getStyle()
                 .set("display", "grid")
                 .set("grid-template-columns", "repeat(auto-fit, minmax(300px, 1fr))")
                 .set("gap", "20px")
                 .set("width", "100%");

        Button backButton = new Button("Volver al Dashboard");
        backButton.getStyle().set("background-color", "transparent").set("color", "#0A6D75").set("border", "1px solid #0A6D75").set("margin-top", "30px").set("cursor", "pointer");
        backButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("dashboard")));

        container.add(title, subtitle, cardsGrid, backButton);
        add(container, new FooterComponent());

        updateExercises("cardio-intenso");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String category = event.getLocation().getQueryParameters().getParameters().getOrDefault("category", java.util.Collections.singletonList("cardio-intenso")).get(0);
        if ("fuerza-musculo".equals(category)) {
            selectedGoal = Goal.WEIGHT_GAIN;
        } else {
            selectedGoal = Goal.WEIGHT_LOSS;
        }
        updateExercises(category);
    }

    private void updateExercises(String category) {
        cardsGrid.removeAll();

        UserProfile user = new UserProfile(70, 1.70, 25, selectedGoal);

        String categoryTitle;
        switch (category) {
            case "fuerza-musculo" -> {
                categoryTitle = "Ejercicios de Fuerza y Músculo";
            }
            case "yoga-estiramiento" -> {
                categoryTitle = "Ejercicios de Yoga y Estiramiento";
            }
            default -> {
                categoryTitle = "Ejercicios de Cardio Intenso";
            }
        }

        title.setText(categoryTitle);
        String goalText = selectedGoal == Goal.WEIGHT_LOSS ? "Enfoque: Pérdida de grasa y tonificación" : "Enfoque: Fuerza e hipertrofia";
        subtitle.setText(goalText);

        List<Exercise> exercises = exerciseService.getExercisesByCategory(category);

        for (Exercise exercise : exercises) {
            VerticalLayout card = new VerticalLayout();
            card.getStyle()
                .set("background-color", "white")
                .set("border-radius", "15px")
                .set("padding", "0")
                .set("box-shadow", "0 5px 15px rgba(0,0,0,0.05)");
            
            Div imageContainer = new Div();
            imageContainer.getStyle()
                .set("width", "100%")
                .set("height", "180px")
                .set("border-top-left-radius", "15px")
                .set("border-top-right-radius", "15px")
                .set("background-color", "#f2f7f7")
                .set("background-position", "center")
                .set("background-size", "cover")
                .set("overflow", "hidden");

            String imageUrl = exercise.getImageUrl();
            if (imageUrl != null && !imageUrl.isBlank()) {
                imageContainer.getStyle().set("background-image", "url('" + imageUrl + "')");
            } else {
                Paragraph placeholder = new Paragraph("Espacio para imagen de ejemplo");
                placeholder.getStyle().set("margin", "0").set("color", "#7a8a8f").set("font-size", "0.95rem");
                imageContainer.add(placeholder);
                imageContainer.getStyle().set("display", "flex").set("align-items", "center").set("justify-content", "center");
            }

            VerticalLayout cardBody = new VerticalLayout();
            cardBody.setPadding(true);
            cardBody.setSpacing(false);
            cardBody.getStyle().set("padding", "20px");

            H3 eTitle = new H3(exercise.getName());
            eTitle.getStyle().set("margin", "0 0 10px 0").set("color", "#F77B15");
            Paragraph eDesc = new Paragraph(exercise.getDescription());
            eDesc.getStyle().set("margin", "0").set("color", "#555").set("line-height", "1.6");

            cardBody.add(eTitle, eDesc);
            card.add(imageContainer, cardBody);
            cardsGrid.add(card);
        }
    }
}

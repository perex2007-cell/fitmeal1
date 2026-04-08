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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route(value = "exercises", layout = MainLayout.class)
public class ExerciseView extends ProtectedView {

    private ExerciseService exerciseService = new ExerciseService();

    public ExerciseView() {
        setSpacing(false);
        setPadding(false);
        setAlignItems(Alignment.CENTER);
        getStyle().set("background-color", "#f8fdfd");

        UserProfile user = new UserProfile(70, 1.70, 25, Goal.WEIGHT_LOSS);

        VerticalLayout container = new VerticalLayout();
        container.setMaxWidth("1000px");
        container.setPadding(true);
        container.getStyle().set("margin-top", "40px").set("margin-bottom", "40px");

        H2 title = new H2("Ejercicios Recomendados");
        title.getStyle().set("color", "#0A6D75").set("margin-bottom", "5px");
        
        String goalText = user.getGoal() == Goal.WEIGHT_LOSS ? "Enfoque: Pérdida de grasa y tonificación" : "Enfoque: Fuerza e hipertrofia";
        Paragraph subtitle = new Paragraph(goalText);
        subtitle.getStyle().set("color", "#666").set("margin-top", "0").set("margin-bottom", "30px");

        Div cardsGrid = new Div();
        cardsGrid.getStyle()
                 .set("display", "grid")
                 .set("grid-template-columns", "repeat(auto-fit, minmax(300px, 1fr))")
                 .set("gap", "20px")
                 .set("width", "100%");

        List<Exercise> exercises = exerciseService.getExercisesByGoal(user.getGoal());

        for (Exercise exercise : exercises) {
            VerticalLayout card = new VerticalLayout();
            card.getStyle()
                .set("background-color", "white")
                .set("border-radius", "15px")
                .set("padding", "25px")
                .set("box-shadow", "0 5px 15px rgba(0,0,0,0.05)");
            
            H3 eTitle = new H3(exercise.getName());
            eTitle.getStyle().set("margin", "0 0 10px 0").set("color", "#F77B15");
            Paragraph eDesc = new Paragraph(exercise.getDescription());
            eDesc.getStyle().set("margin", "0").set("color", "#555").set("line-height", "1.6");
            
            card.add(eTitle, eDesc);
            cardsGrid.add(card);
        }

        Button backButton = new Button("Volver al Dashboard");
        backButton.getStyle().set("background-color", "transparent").set("color", "#0A6D75").set("border", "1px solid #0A6D75").set("margin-top", "30px").set("cursor", "pointer");
        backButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("dashboard")));

        container.add(title, subtitle, cardsGrid, backButton);
        add(container, new FooterComponent());
    }
}

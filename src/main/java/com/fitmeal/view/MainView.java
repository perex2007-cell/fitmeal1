/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.view;


import com.fitmeal.model.Goal;
import com.fitmeal.model.UserProfile;
import com.fitmeal.service.DietService;
import com.fitmeal.service.HealthService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;


/**
 *
 * @author Yesid Ocampo
 */

@Route("")
public class MainView extends VerticalLayout {

    private NumberField weightField = new NumberField("Peso (kg)");
    private NumberField heightField = new NumberField("Estatura (m)");
    private IntegerField ageField = new IntegerField("Edad");

    private ComboBox<Goal> goalField = new ComboBox<>("Objetivo");

    private Button calculateButton = new Button("Calcular");

    private Span bmiResult = new Span();
    private Span bmiClassification = new Span();
    private Div dietRecommendation = new Div();

    private HealthService healthService = new HealthService();
    private DietService dietService = new DietService();

    public MainView() {

        setAlignItems(Alignment.CENTER);
        setSpacing(true);
        setPadding(true);

        H2 title = new H2("FitMeal ");

        goalField.setItems(Goal.values());

        calculateButton.addClickListener(e -> calculate());

        VerticalLayout card = new VerticalLayout(
                title,
                weightField,
                heightField,
                ageField,
                goalField,
                calculateButton,
                bmiResult,
                bmiClassification,
                dietRecommendation
        );

        card.setWidth("350px");
        card.getStyle()
                .set("border", "1px solid #ddd")
                .set("border-radius", "10px")
                .set("padding", "20px");

        add(card);
    }

    private void calculate() {

        if (weightField.isEmpty() || heightField.isEmpty()
                || ageField.isEmpty() || goalField.isEmpty()) {

            bmiResult.setText("⚠ Please complete all fields.");
            bmiClassification.setText("");
            dietRecommendation.setText("");
            return;
        }

        UserProfile user = new UserProfile(
                weightField.getValue(),
                heightField.getValue(),
                ageField.getValue(),
                goalField.getValue()
        );

        double bmi = healthService.calculateBMI(user);
        String classification = healthService.classifyBMI(bmi);
        String diet = dietService.getRecommendation(user.getGoal());

        bmiResult.setText("BMI: " + String.format("%.2f", bmi));
        bmiClassification.setText("Classification: " + classification);
        dietRecommendation.setText(diet);
    }
}

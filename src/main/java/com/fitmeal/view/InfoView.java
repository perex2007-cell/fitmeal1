package com.fitmeal.view;

import com.fitmeal.model.InfoItem;
import com.fitmeal.service.HealthService;
import com.fitmeal.service.InfoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route(value = "info", layout = MainLayout.class)
public class InfoView extends ProtectedView {

    private InfoService infoService = new InfoService();
    private HealthService healthService = new HealthService();
    private NumberField bmiWeightField = new NumberField("Peso (kg)");
    private NumberField bmiHeightField = new NumberField("Altura (cm)");
    private Button bmiCalculateButton = new Button("Calcular IMC");
    private Span bmiResult = new Span();
    private Span bmiClassification = new Span();

    public InfoView() {
        setSpacing(false);
        setPadding(false);
        setAlignItems(Alignment.CENTER);
        getStyle().set("background-color", "#f8fdfd");

        VerticalLayout container = new VerticalLayout();
        container.setMaxWidth("1000px");
        container.setPadding(true);
        container.getStyle().set("margin-top", "40px").set("margin-bottom", "40px");

        H2 title = new H2("Datos de Interés y Salud");
        title.getStyle().set("color", "#0A6D75").set("margin-bottom", "5px");
        
        Paragraph subtitle = new Paragraph("Aprende todo sobre el estilo de vida FitMeal");
        subtitle.getStyle().set("color", "#666").set("margin-top", "0").set("margin-bottom", "30px");

        VerticalLayout bmiCard = new VerticalLayout();
        bmiCard.setId("imc-calculator");
        bmiCard.getStyle()
               .set("background-color", "white")
               .set("border-left", "5px solid #0A6D75")
               .set("border-radius", "10px")
               .set("padding", "25px")
               .set("box-shadow", "0 5px 15px rgba(0,0,0,0.05)")
               .set("margin-bottom", "30px");

        H3 bmiTitle = new H3("Calculadora de IMC");
        bmiTitle.getStyle().set("margin", "0 0 10px 0").set("color", "#0A6D75");

        Paragraph bmiDescription = new Paragraph("Introduce tu peso y altura para calcular tu índice de masa corporal y saber en qué rango te encuentras.");
        bmiDescription.getStyle().set("margin", "0 0 20px 0").set("color", "#555").set("line-height", "1.6");

        bmiWeightField.setWidthFull();
        bmiWeightField.setMin(20);
        bmiWeightField.setMax(300);
        bmiHeightField.setWidthFull();
        bmiHeightField.setMin(80);
        bmiHeightField.setMax(250);

        HorizontalLayout bmiInputs = new HorizontalLayout(bmiWeightField, bmiHeightField);
        bmiInputs.setWidthFull();
        bmiInputs.setPadding(false);
        bmiInputs.setSpacing(true);
        bmiWeightField.setWidth("50%");
        bmiHeightField.setWidth("50%");

        bmiCalculateButton.getStyle()
                .set("background-color", "#F77A14")
                .set("color", "white")
                .set("border-radius", "8px")
                .set("padding", "14px 22px")
                .set("font-weight", "700")
                .set("cursor", "pointer");
        bmiCalculateButton.addClickListener(e -> calculateBMI());

        bmiResult.getStyle().set("margin-top", "20px").set("font-weight", "700").set("color", "#222");
        bmiClassification.getStyle().set("color", "#0A6D75").set("font-weight", "700");

        bmiCard.add(bmiTitle, bmiDescription, bmiInputs, bmiCalculateButton, bmiResult, bmiClassification);

        Div cardsGrid = new Div();
        cardsGrid.getStyle()
                 .set("display", "grid")
                 .set("grid-template-columns", "repeat(auto-fit, minmax(300px, 1fr))")
                 .set("gap", "20px")
                 .set("width", "100%");

        List<InfoItem> infoList = infoService.getAllInfo();

        for (InfoItem item : infoList) {
            VerticalLayout card = new VerticalLayout();
            card.getStyle()
                .set("background-color", "white")
                .set("border-left", "5px solid #0A6D75")
                .set("border-radius", "10px")
                .set("padding", "20px")
                .set("box-shadow", "0 5px 15px rgba(0,0,0,0.05)");
            
            H3 iTitle = new H3(item.getTitle());
            iTitle.getStyle().set("margin", "0 0 10px 0").set("color", "#333");
            Paragraph iDesc = new Paragraph(item.getDescription());
            iDesc.getStyle().set("margin", "0").set("color", "#555").set("line-height", "1.6");
            
            card.add(iTitle, iDesc);
            cardsGrid.add(card);
        }

        Button backButton = new Button("Volver al Dashboard");
        backButton.getStyle().set("background-color", "transparent").set("color", "#0A6D75").set("border", "1px solid #0A6D75").set("margin-top", "30px").set("cursor", "pointer");
        backButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("dashboard")));

        container.add(title, subtitle, bmiCard, cardsGrid, backButton);
        add(container, new FooterComponent());
    }

    private void calculateBMI() {
        if (bmiWeightField.isEmpty() || bmiHeightField.isEmpty()) {
            bmiResult.setText("Por favor ingresa peso y altura.");
            bmiClassification.setText("");
            return;
        }

        double weight = bmiWeightField.getValue();
        double heightCentimeters = bmiHeightField.getValue();
        if (weight <= 0 || heightCentimeters <= 0) {
            bmiResult.setText("Los valores deben ser mayores a cero.");
            bmiClassification.setText("");
            return;
        }

        double heightMeters = heightCentimeters > 3 ? heightCentimeters / 100.0 : heightCentimeters;
        double bmi = healthService.calculateBMI(weight, heightMeters);
        String classification = healthService.classifyBMI(bmi);

        bmiResult.setText("Tu IMC es: " + String.format("%.2f", bmi));
        bmiClassification.setText("Clasificación: " + classification);
    }
}

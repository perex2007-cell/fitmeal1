package com.fitmeal.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "privacy", layout = MainLayout.class)
@RouteAlias(value = "privacidad", layout = MainLayout.class)
public class PrivacyView extends VerticalLayout {

    public PrivacyView() {
        setSpacing(false);
        setPadding(false);
        setMargin(false);
        setSizeFull();
        getStyle().set("background-color", "#f8fdfd");

        // Cuadro superior
        VerticalLayout topBox = new VerticalLayout();
        topBox.setWidthFull();
        topBox.setHeight("50%");
        topBox.setAlignItems(Alignment.CENTER);
        topBox.setJustifyContentMode(JustifyContentMode.CENTER);
        topBox.getStyle()
                .set("background-color", "#0A6D75")
                .set("color", "white")
                .set("padding", "40px");

        H2 privacyTitle = new H2("Política de Privacidad");
        privacyTitle.getStyle().set("color", "white");

        Div privacyContent = new Div();
        privacyContent.getStyle().set("text-align", "center");

        privacyContent.add(
                new Paragraph("En FitMeal valoramos tu privacidad y protegemos tus datos personales."),
                new Paragraph("Recopilamos la información necesaria para ofrecerte una experiencia personalizada y mejoras continuas."),
                new Paragraph("No compartimos tus datos con terceros sin tu consentimiento, excepto cuando sea necesario para cumplir con la ley."),
                new Paragraph("Puedes solicitar la eliminación de tus datos en cualquier momento a través de nuestro contacto.")
        );

        topBox.add(privacyTitle, privacyContent);

        // Cuadro inferior
        VerticalLayout bottomBox = new VerticalLayout();
        bottomBox.setWidthFull();
        bottomBox.setHeight("50%");
        bottomBox.setAlignItems(Alignment.CENTER);
        bottomBox.setJustifyContentMode(JustifyContentMode.CENTER);
        bottomBox.getStyle()
                .set("background-color", "#f0f8f8")
                .set("padding", "40px");

        H2 bottomTitle = new H2("Datos que gestionamos");
        bottomTitle.getStyle().set("color", "#0A6D75");

        Div bottomInfo = new Div();
        bottomInfo.getStyle().set("text-align", "center");

        bottomInfo.add(
                new Paragraph("- Información de registro y perfil."),
                new Paragraph("- Datos de salud y preferencias de nutrición."),
                new Paragraph("- Información de uso para mejorar nuestros servicios.")
        );

        bottomBox.add(bottomTitle, bottomInfo);

        add(topBox, bottomBox, new FooterComponent());
    }
}

package com.fitmeal.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "contact", layout = MainLayout.class)
public class ContactView extends VerticalLayout {

    public ContactView() {
        setSpacing(false);
        setPadding(false);
        setMargin(false);
        setSizeFull();

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

        H2 contactTitle = new H2("Información de Contacto");
        contactTitle.getStyle().set("color", "white");

        Div contactInfo = new Div();
        contactInfo.getStyle().set("text-align", "center");

        contactInfo.add(
                new Paragraph("📧 Email: Santiagofitmeal@gmail.com"),
                new Paragraph("📞 Teléfono: 305 2648602"),
                new Paragraph("📍 Dirección: El Salado, Copacabana "),
                new Paragraph("🕒 Horarios: Lunes a Viernes, 9:00 AM - 6:00 PM")
        );

        topBox.add(contactTitle, contactInfo);

        // Cuadro inferior
        VerticalLayout bottomBox = new VerticalLayout();
        bottomBox.setWidthFull();
        bottomBox.setHeight("50%");
        bottomBox.setAlignItems(Alignment.CENTER);
        bottomBox.setJustifyContentMode(JustifyContentMode.CENTER);
        bottomBox.getStyle()
                .set("background-color", "#f0f8f8")
                .set("padding", "40px");

        H2 socialTitle = new H2("Síguenos en Redes Sociales");
        socialTitle.getStyle().set("color", "#0A6D75");

        Div socialMedia = new Div();
        socialMedia.getStyle().set("text-align", "center");

        socialMedia.add(
                new Paragraph("📘 Facebook: @FitMealOficial"),
                new Paragraph("📷 Instagram: @fitmeal_nutricion"),
                new Paragraph("🐦 Twitter: @FitMealApp"),
                new Paragraph("📺 YouTube: FitMeal Canal")
        );

        bottomBox.add(socialTitle, socialMedia);

        add(topBox, bottomBox);
        expand(topBox, bottomBox);
    }
}
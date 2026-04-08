package com.fitmeal.view;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class FooterComponent extends VerticalLayout {
    public FooterComponent() {
        setWidthFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background-color", "#074B51")
                  .set("color", "white")
                  .set("padding", "30px 20px")
                  .set("margin-top", "50px");

        HorizontalLayout links = new HorizontalLayout(
                createFooterLink("Inicio"),
                createFooterLink("Ejercicios"),
                createFooterLink("Blog"),
                createFooterLink("Contacto")
        );
        links.setSpacing(true);
        links.getStyle().set("margin-bottom", "15px");

        Span copyright = new Span("© 2026 FitMeal. Todos los derechos reservados.");
        copyright.getStyle().set("font-size", "0.9em").set("color", "#ccc");

        add(links, copyright);
    }

    private Span createFooterLink(String text) {
        Span link = new Span(text);
        link.getStyle().set("cursor", "pointer")
                       .set("padding", "0 10px")
                       .set("font-weight", "500");
        return link;
    }
}

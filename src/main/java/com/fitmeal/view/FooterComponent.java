package com.fitmeal.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class FooterComponent extends VerticalLayout {

    public FooterComponent() {
        setWidthFull();
        setPadding(false);
        setSpacing(false);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background-color", "#04444a")
                  .set("color", "white")
                  .set("margin-top", "50px");

        // --- COLUMNS WRAPPER ---
        Div columnsWrapper = new Div();
        columnsWrapper.getStyle()
                .set("display", "flex")
                .set("flex-wrap", "wrap")
                .set("width", "100%")
                .set("max-width", "1200px")
                .set("justify-content", "space-between")
                .set("padding", "60px 20px")
                .set("box-sizing", "border-box")
                .set("gap", "40px");

        // Column 1
        VerticalLayout col1 = createFooterCol("Enlaces Rápidos", "Inicio", "Ejercicios", "Crear Plan", "Ingresar");
        
        // Column 2 
        VerticalLayout col2 = createFooterCol("Nutrición y Salud", "Calculadora IMC", "Dietas Sugeridas", "Nivel de Actividad", "Recetas");
        
        // Column 3
        VerticalLayout col3 = createFooterCol("FitMeal", "Comentarios", "Contacto", "Blog", "Privacidad", "Términos");

        // Column 4 (Social Media)
        VerticalLayout col4 = new VerticalLayout();
        col4.setPadding(false);
        col4.setSpacing(true);
        col4.getStyle().set("flex", "1 1 200px").set("min-width", "180px");
        H4 title4 = new H4("Redes Sociales");
        title4.getStyle().set("margin-top", "0").set("color", "white").set("font-weight", "bold").set("font-size", "1.1rem");
        
        HorizontalLayout iconsLayout = new HorizontalLayout();
        iconsLayout.setSpacing(true);
        iconsLayout.add(
            createSocialIcon("tw", "#1DA1F2"),
            createSocialIcon("fb", "#4267B2"),
            createSocialIcon("ig", "#E1306C"),
            createSocialIcon("yt", "#FF0000")
        );
        col4.add(title4, iconsLayout);

        columnsWrapper.add(col1, col2, col3, col4);

        // --- DIVIDER STRIP ---
        Div divider = new Div();
        divider.getStyle()
               .set("width", "100%")
               .set("max-width", "1200px")
               .set("height", "1px")
               .set("background-color", "rgba(255, 255, 255, 0.1)")
               .set("margin", "0 auto");

        // --- BOTTOM COPYRIGHT ---
        HorizontalLayout bottomBar = new HorizontalLayout();
        bottomBar.setWidthFull();
        bottomBar.getStyle().set("max-width", "1200px").set("padding", "20px");
        bottomBar.setJustifyContentMode(JustifyContentMode.BETWEEN);
        
        Span copyright = new Span("Copyright 2026 - FitMeal / Open Sans");
        copyright.getStyle().set("color", "#ccc").set("font-size", "0.85rem");
        
        Anchor domainLink = new Anchor("/", "www.fitmeal.com");
        styleFooterLink(domainLink);
        domainLink.getStyle().set("color", "#ccc").set("font-size", "0.85rem").set("padding", "0");

        bottomBar.add(copyright, domainLink);

        // Append to Component
        add(columnsWrapper, divider, bottomBar);
    }

    private VerticalLayout createFooterCol(String titleStr, String... links) {
        VerticalLayout col = new VerticalLayout();
        col.setPadding(false);
        col.setSpacing(false);
        col.getStyle().set("flex", "1 1 200px").set("min-width", "180px");

        H4 title = new H4(titleStr);
        title.getStyle().set("margin-top", "0").set("margin-bottom", "15px").set("color", "white").set("font-weight", "bold").set("font-size", "1.1rem");
        col.add(title);

        for (String linkStr : links) {
            Anchor anchor = new Anchor(getFooterHref(linkStr), linkStr);
            styleFooterLink(anchor);
            col.add(anchor);
        }
        return col;
    }

    private String getFooterHref(String text) {
        String normalized = text.toLowerCase().trim();

        return switch (normalized) {
            case "home", "inicio" -> "/";
            case "exercises", "ejercicios" -> "/exercises";
            case "create diet", "crear plan" -> "/#diet-calculator";
            case "login signup", "login / signup", "ingresar" -> "/login";
            case "calculadora imc" -> "/info#imc-calculator";
            case "dietas sugeridas" -> "/dashboard";
            case "nivel de actividad" -> "/dashboard";
            case "recetas" -> "/info";
            case "comentarios" -> "/feedback";
            case "contacto" -> "/contact";
            case "blog" -> "/blog";
            case "privacidad" -> "/privacy";
            case "terminos", "términos" -> "/terms";
            default -> "#";
        };
    }

    private void styleFooterLink(Component link) {
        link.getElement().getStyle()
                .set("color", "#a6c9cc")
                .set("font-size", "0.95rem")
                .set("margin-bottom", "10px")
                .set("cursor", "pointer")
                .set("transition", "color 0.2s")
                .set("display", "block")
                .set("text-decoration", "none")
                .set("padding", "4px 0");
    }

    private Anchor createSocialIcon(String text, String bgColor) {
        String url = switch(text) {
            case "tw" -> "https://x.com/?lang=es";
            case "fb" -> "https://www.facebook.com/?stype=lo&flo=1&deoia=1&jlou=AfiTBIXCpY7CGMoeQ6nYKhwuTlBTtv93pFYXuLbDnpjki19oK9M2vF44lN-jOx8DfXh0pnY4MEOUlqFPRXJ1aB2vsuIYC3RNQHdYVCuquV0kyg&smuh=6269&lh=AdBI96-NkPhaW8GbgDE";
            case "ig" -> "https://www.instagram.com/?hl=es";
            case "yt" -> "https://www.youtube.com/?app=desktop&hl=es";
            default -> "#";
        };
        Anchor icon = new Anchor(url, text);
        icon.setTarget("_blank");
        icon.getStyle()
            .set("width", "35px")
            .set("height", "35px")
            .set("background-color", bgColor)
            .set("color", "white")
            .set("border-radius", "50%")
            .set("display", "flex")
            .set("justify-content", "center")
            .set("align-items", "center")
            .set("font-weight", "bold")
            .set("font-size", "0.9rem")
            .set("text-decoration", "none");
        return icon;
    }
}
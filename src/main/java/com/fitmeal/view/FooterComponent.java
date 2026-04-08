package com.fitmeal.view;

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
        getStyle().set("background-color", "#04444a") // Color oscuro como en la foto
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
        VerticalLayout col1 = createFooterCol("Enlaces Rápidos", "Home", "Exercises", "Create Diet", "Login / Signup");
        
        // Column 2 
        VerticalLayout col2 = createFooterCol("Nutrición y Salud", "Calculadora IMC", "Dietas Sugeridas", "Nivel de Actividad", "Recetas");
        
        // Column 3
        VerticalLayout col3 = createFooterCol("FitMeal", "Contacto", "Blog", "Privacidad", "Términos");

        // Column 4 (Social Media)
        VerticalLayout col4 = new VerticalLayout();
        col4.setPadding(false);
        col4.setSpacing(true);
        col4.getStyle().set("flex", "1 1 200px").set("min-width", "180px");
        H4 title4 = new H4("Social Media");
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
        
        Span domain = new Span("www.fitmeal.com");
        domain.getStyle().set("color", "#ccc").set("font-size", "0.85rem");

        bottomBar.add(copyright, domain);

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
            Span linkSpan = new Span(linkStr);
            linkSpan.getStyle()
                    .set("color", "#a6c9cc")
                    .set("font-size", "0.95rem")
                    .set("margin-bottom", "10px")
                    .set("cursor", "pointer")
                    .set("transition", "color 0.2s");
            // Simple hover effect simulated conceptually, though inline style lacks active pseudoclass
            col.add(linkSpan);
        }
        return col;
    }

    private Div createSocialIcon(String text, String bgColor) {
        Div icon = new Div();
        icon.setText(text);
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
            .set("cursor", "pointer");
        return icon;
    }
}

package com.fitmeal.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "terms", layout = MainLayout.class)
@RouteAlias(value = "terminos", layout = MainLayout.class)
public class TermsView extends VerticalLayout {

    public TermsView() {
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

        H2 termsTitle = new H2("Términos y Condiciones");
        termsTitle.getStyle().set("color", "white");

        Div termsContent = new Div();
        termsContent.getStyle().set("text-align", "center");

        termsContent.add(
                new Paragraph("Estos términos detallan cómo puedes usar FitMeal, tus responsabilidades y los límites de nuestro servicio."),
                new Paragraph("Al acceder a la plataforma, aceptas cumplir con estas condiciones y utilizar la información de forma responsable."),
                new Paragraph("FitMeal se reserva el derecho de actualizar estos términos cuando sea necesario; los cambios entrarán en vigor al publicarse.")
        );

        topBox.add(termsTitle, termsContent);

        // Cuadro inferior
        VerticalLayout bottomBox = new VerticalLayout();
        bottomBox.setWidthFull();
        bottomBox.setHeight("50%");
        bottomBox.setAlignItems(Alignment.CENTER);
        bottomBox.setJustifyContentMode(JustifyContentMode.CENTER);
        bottomBox.getStyle()
                .set("background-color", "#f0f8f8")
                .set("padding", "40px");

        H2 bottomTitle = new H2("Responsabilidades y Uso");
        bottomTitle.getStyle().set("color", "#0A6D75");

        Div bottomInfo = new Div();
        bottomInfo.getStyle().set("text-align", "center");

        bottomInfo.add(
                new Paragraph("- Utiliza FitMeal solo para fines personales y saludables."),
                new Paragraph("- No publiques contenidos que infrinjan derechos de autor ni sean discriminatorios."),
                new Paragraph("- FitMeal no garantiza resultados específicos; los resultados dependen de cada persona."),
                new Paragraph("- Nos reservamos el derecho de suspender el acceso si se incumplen estos términos.")
        );

        bottomBox.add(bottomTitle, bottomInfo);

        add(topBox, bottomBox, new FooterComponent());
    }
}

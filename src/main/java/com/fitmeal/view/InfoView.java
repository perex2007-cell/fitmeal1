package com.fitmeal.view;

import com.fitmeal.model.InfoItem;
import com.fitmeal.service.InfoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route(value = "info", layout = MainLayout.class)
public class InfoView extends ProtectedView {

    private InfoService infoService = new InfoService();

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

        container.add(title, subtitle, cardsGrid, backButton);
        add(container, new FooterComponent());
    }
}

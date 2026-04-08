/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.view;

import com.fitmeal.model.InfoItem;
import com.fitmeal.service.InfoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.Route;
import java.util.List;


/**
 *
 * @author Yesid Ocampo
 */

@Route(value = "info", layout = MainLayout.class)

public class InfoView extends ProtectedView {

    private InfoService infoService = new InfoService();

    public InfoView() {

        setSpacing(true);
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        H2 title = new H2("Datos de interés sobre salud ");
        add(title);

        List<InfoItem> infoList = infoService.getAllInfo();

        for (InfoItem item : infoList) {

            VerticalLayout card = new VerticalLayout(
                    new H3(item.getTitle()),
                    new Paragraph(item.getDescription())
            );

            styleCard(card, "#FAFAFA");
            add(card);
        }

        Button backButton = new Button("Volver al dashboard");
        backButton.addClickListener(e ->
                getUI().ifPresent(ui -> ui.navigate("dashboard"))
        );

        add(backButton);
        add(new FooterComponent());
    }

    private void styleCard(VerticalLayout card, String backgroundColor) {
        card.getStyle()
                .set("background", backgroundColor)
                .set("border-radius", "14px")
                .set("padding", "20px")
                .set("box-shadow", "0 10px 25px rgba(0,0,0,0.08)")
                .set("max-width", "450px")
                .set("margin-bottom", "12px");
    }
}

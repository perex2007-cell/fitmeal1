package com.fitmeal.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.fitmeal.model.Feedback;
import com.fitmeal.service.FeedbackService;
import java.time.format.DateTimeFormatter;

@Route(value = "feedback", layout = MainLayout.class)
@PageTitle("Comentarios y Sugerencias - FitMeal")
public class FeedbackView extends VerticalLayout {

    public FeedbackView() {
        setSpacing(false);
        setPadding(false);
        setAlignItems(Alignment.STRETCH);
        setSizeFull();
        getStyle().set("background-color", "#f8fdfd");

        // Header
        VerticalLayout header = new VerticalLayout();
        header.setPadding(true);
        header.setAlignItems(Alignment.CENTER);
        header.getStyle().set("background-color", "#0A6D75").set("color", "white").set("padding", "40px 20px");

        H2 title = new H2("Comentarios y Sugerencias");
        title.getStyle().set("margin", "0").set("color", "white");

        Paragraph subtitle = new Paragraph("¿Tienes una sugerencia o encontraste un error? ¡Nos encantaría escucharte!");
        subtitle.getStyle().set("margin", "10px 0 0 0").set("color", "#a6c9cc").set("text-align", "center");

        header.add(title, subtitle);

        // Main content wrapper
        Div mainContent = new Div();
        mainContent.getStyle()
                .set("width", "100%")
                .set("max-width", "800px")
                .set("margin", "40px auto")
                .set("padding", "0 20px")
                .set("display", "flex")
                .set("flex-direction", "column")
                .set("align-items", "center");

        // Form section
        Div formWrapper = new Div();
        formWrapper.getStyle()
                .set("background-color", "white")
                .set("border-radius", "8px")
                .set("padding", "30px")
                .set("margin-bottom", "40px")
                .set("width", "100%")
                .set("display", "flex")
                .set("justify-content", "center");
        formWrapper.add(new FeedbackFormComponent());

        // Comments section
        Div commentsWrapper = new Div();
        commentsWrapper.getStyle()
                .set("background-color", "white")
                .set("border-radius", "8px")
                .set("padding", "30px")
                .set("width", "100%");

        H2 commentsTitle = new H2("Comentarios Recientes");
        commentsTitle.getStyle().set("color", "#0A6D75").set("margin-top", "0");
        commentsWrapper.add(commentsTitle);

        // Display saved comments
        FeedbackService service = FeedbackService.getInstance();
        if (service.getAllFeedbacks().isEmpty()) {
            Paragraph noComments = new Paragraph("Aún no hay comentarios. ¡Sé el primero en compartir!");
            noComments.getStyle().set("color", "#999").set("text-align", "center");
            commentsWrapper.add(noComments);
        } else {
            for (Feedback feedback : service.getAllFeedbacks()) {
                commentsWrapper.add(createFeedbackItem(feedback));
            }
        }

        mainContent.add(formWrapper, commentsWrapper);

        add(header, mainContent, new FooterComponent());
    }

    private Div createFeedbackItem(Feedback feedback) {
        Div item = new Div();
        item.getStyle()
                .set("border-left", "4px solid #0A6D75")
                .set("padding", "15px")
                .set("margin-bottom", "15px")
                .set("background-color", "#f9f9f9")
                .set("border-radius", "4px");

        H2 name = new H2(feedback.getUserName());
        name.getStyle().set("margin", "0 0 5px 0").set("font-size", "1.1em").set("color", "#0A6D75");

        String typeDisplay = feedback.getType().equals("suggestion") ? "Sugerencia" : 
                            feedback.getType().equals("bug") ? "Reporte de Error" : "General";
        
        Paragraph meta = new Paragraph(
            typeDisplay + " | ⭐ " + feedback.getRating() + "/5 | " + 
            feedback.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        );
        meta.getStyle().set("margin", "0 0 10px 0").set("color", "#999").set("font-size", "0.9em");

        Paragraph content = new Paragraph(feedback.getContent());
        content.getStyle().set("margin", "0").set("color", "#333").set("line-height", "1.6");

        item.add(name, meta, content);
        return item;
    }
}

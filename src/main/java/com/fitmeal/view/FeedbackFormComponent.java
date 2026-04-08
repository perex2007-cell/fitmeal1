package com.fitmeal.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.fitmeal.model.Feedback;
import com.fitmeal.service.AuthService;
import com.fitmeal.service.FeedbackService;

public class FeedbackFormComponent extends VerticalLayout {
    private TextField nameField;
    private EmailField emailField;
    private TextArea contentField;
    private ComboBox<String> typeCombo;
    private ComboBox<Integer> ratingCombo;

    public FeedbackFormComponent() {
        setWidth("100%");
        setMaxWidth("600px");
        setPadding(true);
        setSpacing(true);

        // Name field
        nameField = new TextField("Nombre");
        nameField.setWidth("100%");
        AuthService authService = AuthService.getInstance();
        if (authService.isUserLoggedIn()) {
            nameField.setValue(authService.getLoggedUser().getName());
            nameField.setReadOnly(true);
        }

        // Email field
        emailField = new EmailField("Correo");
        emailField.setWidth("100%");
        if (authService.isUserLoggedIn()) {
            emailField.setValue(authService.getLoggedUser().getEmail());
            emailField.setReadOnly(true);
        }

        // Type selector
        typeCombo = new ComboBox<>("Tipo de comentario");
        typeCombo.setItems("Sugerencia", "Reporte de error", "General");
        typeCombo.setWidth("100%");
        typeCombo.setValue("General");

        // Rating selector
        ratingCombo = new ComboBox<>("Calificación");
        ratingCombo.setItems(1, 2, 3, 4, 5);
        ratingCombo.setWidth("100%");
        ratingCombo.setValue(5);

        // Content area
        contentField = new TextArea("Tu comentario o sugerencia");
        contentField.setWidth("100%");
        contentField.setHeight("200px");

        // Submit button
        Button submitBtn = new Button("Enviar Comentario");
        submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitBtn.setWidth("100%");
        submitBtn.addClickListener(e -> submitFeedback());

        add(nameField, emailField, typeCombo, ratingCombo, contentField, submitBtn);
    }

    private void submitFeedback() {
        String name = nameField.getValue();
        String email = emailField.getValue();
        String content = contentField.getValue();

        if (name == null || name.trim().isEmpty() || 
            email == null || email.trim().isEmpty() || 
            content == null || content.trim().isEmpty()) {
            Notification.show("Por favor completa todos los campos", 3000, Notification.Position.TOP_CENTER);
            return;
        }

        String type = getTypeValue(typeCombo.getValue());
        int rating = ratingCombo.getValue() != null ? ratingCombo.getValue() : 5;

        Feedback feedback = new Feedback(name, email, content, rating, type);
        FeedbackService.getInstance().addFeedback(feedback);

        Notification.show("¡Gracias! Tu comentario ha sido guardado", 3000, Notification.Position.TOP_CENTER);
        
        // Limpiar formulario
        if (!AuthService.getInstance().isUserLoggedIn()) {
            nameField.clear();
            emailField.clear();
        }
        contentField.clear();
        typeCombo.setValue("General");
        ratingCombo.setValue(5);
    }

    private String getTypeValue(String displayValue) {
        if (displayValue == null) {
            return "general";
        }
        return switch (displayValue) {
            case "Sugerencia" -> "suggestion";
            case "Reporte de error" -> "bug";
            default -> "general";
        };
    }
}


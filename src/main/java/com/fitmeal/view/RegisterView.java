package com.fitmeal.view;

import com.fitmeal.service.AuthService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "register", layout = MainLayout.class)
public class RegisterView extends VerticalLayout {

    private AuthService authService = AuthService.getInstance();

    private TextField nameField = new TextField("Nombre completo");
    private TextField emailField = new TextField("Correo electrónico");
    private PasswordField passwordField = new PasswordField("Contraseña");

    public RegisterView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background-color", "#f8fdfd");

        VerticalLayout card = new VerticalLayout();
        card.getStyle()
                .set("background-color", "white")
                .set("border-radius", "15px")
                .set("box-shadow", "0 10px 30px rgba(0,0,0,0.1)")
                .set("padding", "40px")
                .set("max-width", "400px")
                .set("width", "100%");
        card.setAlignItems(Alignment.CENTER);

        H2 title = new H2("Únete a FitMeal");
        title.getStyle().set("color", "#0A6D75").set("margin-top", "0").set("font-weight", "800");

        Paragraph subtitle = new Paragraph("Crea tu cuenta gratis");
        subtitle.getStyle().set("color", "#555").set("margin-bottom", "20px");

        nameField.setWidthFull();
        emailField.setWidthFull();
        passwordField.setWidthFull();

        Button registerButton = new Button("Crear cuenta");
        registerButton.setWidthFull();
        registerButton.getStyle()
                .set("background-color", "#F77B15")
                .set("color", "white")
                .set("font-weight", "bold")
                .set("margin-top", "20px")
                .set("padding", "15px 0")
                .set("border-radius", "8px")
                .set("cursor", "pointer");

        Button loginButton = new Button("¿Ya tienes cuenta? Inicia sesión");
        loginButton.getStyle()
                .set("background-color", "transparent")
                .set("color", "#0A6D75")
                .set("margin-top", "10px")
                .set("cursor", "pointer")
                .set("font-weight", "500");

        registerButton.addClickListener(e -> register());
        loginButton.addClickListener(e ->
                getUI().ifPresent(ui -> ui.navigate("login"))
        );

        card.add(title, subtitle, nameField, emailField, passwordField, registerButton, loginButton);
        add(card);
    }

    private void register() {
        if (nameField.isEmpty()
                || emailField.isEmpty()
                || passwordField.isEmpty()) {

            Notification.show("Todos los campos son obligatorios");
            return;
        }

        boolean success = authService.register(
                nameField.getValue(),
                emailField.getValue(),
                passwordField.getValue()
        );

        if (success) {
            Notification.show("Registro exitoso");
            getUI().ifPresent(ui -> ui.navigate("login"));
        } else {
            Notification.show("El correo ya está registrado");
        }
    }
}

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

@Route(value = "login", layout = MainLayout.class)
public class LoginView extends VerticalLayout {

    private TextField emailField = new TextField("Correo electrónico");
    private PasswordField passwordField = new PasswordField("Contraseña");
    private Button loginButton = new Button("Iniciar sesión");
    private AuthService authService = AuthService.getInstance();

    public LoginView() {
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

        H2 title = new H2("Bienvenido de nuevo");
        title.getStyle().set("color", "#0A6D75").set("margin-top", "0").set("font-weight", "800");

        Paragraph subtitle = new Paragraph("Inicia sesión en tu cuenta de FitMeal");
        subtitle.getStyle().set("color", "#555").set("margin-bottom", "20px");

        emailField.setWidthFull();
        passwordField.setWidthFull();

        loginButton.setWidthFull();
        loginButton.getStyle()
                .set("background-color", "#F77B15")
                .set("color", "white")
                .set("font-weight", "bold")
                .set("margin-top", "20px")
                .set("padding", "15px 0")
                .set("border-radius", "8px")
                .set("cursor", "pointer");

        loginButton.addClickListener(e -> login());

        Button registerButton = new Button("¿No tienes cuenta? Regístrate");
        registerButton.getStyle()
                .set("background-color", "transparent")
                .set("color", "#0A6D75")
                .set("margin-top", "10px")
                .set("cursor", "pointer")
                .set("font-weight", "500");

        registerButton.addClickListener(e ->
                getUI().ifPresent(ui -> ui.navigate("register"))
        );

        card.add(title, subtitle, emailField, passwordField, loginButton, registerButton);
        add(card);
    }

    private void login() {
        String email = emailField.getValue();
        String password = passwordField.getValue();

        if (email.isEmpty() || password.isEmpty()) {
            Notification.show("Todos los campos son obligatorios");
            return;
        }

        if (authService.login(email, password)) {
            Notification.show("Inicio de sesión exitoso");
            getUI().ifPresent(ui -> ui.navigate("dashboard"));
        } else {
            Notification.show("Credenciales incorrectas");
        }
    }
}

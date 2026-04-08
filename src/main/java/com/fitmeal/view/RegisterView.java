/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitmeal.view;


import com.fitmeal.service.AuthService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Yesid Ocampo
 */

@Route("register")
public class RegisterView extends VerticalLayout {

    private AuthService authService = AuthService.getInstance();

    private TextField nameField = new TextField("Nombre completo");
    private TextField emailField = new TextField("Correo electrónico");
    private PasswordField passwordField = new PasswordField("Contraseña");

    public RegisterView() {

        setSpacing(true);
        setAlignItems(Alignment.CENTER);

        H2 title = new H2("Registro de usuario ");

        Button registerButton = new Button("Crear cuenta");
        Button loginButton = new Button("Volver al login");

        registerButton.addClickListener(e -> register());
        loginButton.addClickListener(e ->
                getUI().ifPresent(ui -> ui.navigate("login"))
        );

        add(
                title,
                nameField,
                emailField,
                passwordField,
                registerButton,
                loginButton
        );
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
            Notification.show("El correo ya está registrado ");
        }
    }
}

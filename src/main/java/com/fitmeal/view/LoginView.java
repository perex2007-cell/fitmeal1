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

@Route("login")
public class LoginView extends VerticalLayout {

    private TextField emailField = new TextField("Correo electrónico");
    private PasswordField passwordField = new PasswordField("Contraseña");
    private Button loginButton = new Button("Iniciar sesión");
    private AuthService authService = AuthService.getInstance();

    public LoginView() {

        setSpacing(true);
        setAlignItems(Alignment.CENTER);

        H2 title = new H2("FitMeal ");
        H2 subtitle = new H2("Iniciar sesión");

        loginButton.addClickListener(e -> login());
        
        Button registerButton = new Button("Crear cuenta");
        registerButton.addClickListener(e ->
                getUI().ifPresent(ui -> ui.navigate("register"))
        );


        add(    
             title,
             subtitle,
             emailField,
             passwordField,
             loginButton,
             registerButton
        );
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
        }

         else {
            Notification.show("Credenciales incorrectas ");
        }
    }
}


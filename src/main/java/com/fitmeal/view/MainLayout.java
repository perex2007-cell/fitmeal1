package com.fitmeal.view;

import com.fitmeal.service.AuthService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout implements AfterNavigationObserver {

    private final AuthService authService = AuthService.getInstance();
    private final HorizontalLayout nav = new HorizontalLayout();
    private Component authButton;

    public MainLayout() {
        createHeader();
    }

    private void createHeader() {
       RouterLink logoLink = new RouterLink("FitMeal", MainView.class);
logoLink.getStyle()
        .set("font-size", "1.5em")
        .set("margin", "0")
        .set("color", "white")
        .set("font-weight", "bold")
        .set("text-decoration", "none");

HorizontalLayout logoLayout = new HorizontalLayout(logoLink);
logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        RouterLink homeLink = new RouterLink("Inicio", MainView.class);
        RouterLink exercisesLink = new RouterLink("Ejercicios", ExerciseView.class);
        RouterLink dashboardLink = new RouterLink("Panel", DashboardView.class);

        RouterLink[] links = {homeLink, exercisesLink, dashboardLink};
        for (RouterLink link : links) {
            link.getStyle()
                    .set("color", "white")
                    .set("text-decoration", "none")
                    .set("font-weight", "500")
                    .set("padding", "10px 15px");
        }

        nav.setAlignItems(FlexComponent.Alignment.CENTER);
        nav.setSpacing(true);
        nav.add(homeLink, exercisesLink, dashboardLink);
        updateAuthButton();

        HorizontalLayout header = new HorizontalLayout(logoLayout, nav);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        header.setWidthFull();
        header.getStyle()
                .set("padding", "10px 20px")
                .set("background-color", "#0A6D75");

        addToNavbar(header);
    }

    private void updateAuthButton() {
        if (authButton != null) {
            nav.remove(authButton);
        }
        authButton = createAuthComponent();
        nav.add(authButton);
    }

    private Component createAuthComponent() {
        if (authService.isUserLoggedIn()) {
            Button logoutButton = new Button("Cerrar sesión", e -> {
                authService.logout();
                getUI().ifPresent(ui -> ui.navigate("login"));
            });
            logoutButton.getStyle()
                    .set("background-color", "#F77B15")
                    .set("color", "white")
                    .set("border-radius", "4px")
                    .set("padding", "8px 16px")
                    .set("margin-left", "10px")
                    .set("cursor", "pointer");
            return logoutButton;
        }

        RouterLink loginLink = new RouterLink("Ingresar", LoginView.class);
        loginLink.getStyle()
                .set("color", "white")
                .set("text-decoration", "none")
                .set("font-weight", "500")
                .set("padding", "10px 15px")
                .set("background-color", "#F77B15")
                .set("border-radius", "4px")
                .set("margin-left", "10px");
        return loginLink;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        updateAuthButton();
    }
}

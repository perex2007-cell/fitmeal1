package com.fitmeal.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("FitMeal");
        logo.getStyle()
                .set("font-size", "1.5em")
                .set("margin", "0")
                .set("color", "white")
                .set("font-weight", "bold");

        HorizontalLayout logoLayout = new HorizontalLayout(logo);
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        RouterLink homeLink = new RouterLink("Home", MainView.class);
        RouterLink exercisesLink = new RouterLink("Exercises", ExerciseView.class);
        RouterLink dashboardLink = new RouterLink("Dashboard", DashboardView.class);
        RouterLink loginLink = new RouterLink("Login/Signup", LoginView.class);

        RouterLink[] links = {homeLink, exercisesLink, dashboardLink, loginLink};
        for (RouterLink link : links) {
            link.getStyle()
                    .set("color", "white")
                    .set("text-decoration", "none")
                    .set("font-weight", "500")
                    .set("padding", "10px 15px");
        }

        // Add special styling for Login/Signup button
        loginLink.getStyle()
                .set("background-color", "#F77B15")
                .set("border-radius", "4px")
                .set("padding", "8px 16px")
                .set("margin-left", "10px");

        HorizontalLayout nav = new HorizontalLayout(homeLink, exercisesLink, dashboardLink, loginLink);
        nav.setAlignItems(FlexComponent.Alignment.CENTER);
        nav.setSpacing(true);

        HorizontalLayout header = new HorizontalLayout(logoLayout, nav);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        header.setWidthFull();
        header.getStyle()
                .set("padding", "10px 20px")
                .set("background-color", "#0A6D75");

        addToNavbar(header);
    }
}

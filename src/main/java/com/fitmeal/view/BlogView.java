package com.fitmeal.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "blog", layout = MainLayout.class)
public class BlogView extends VerticalLayout {

    public BlogView() {
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

        H2 title = new H2("Blog de FitMeal");
        title.getStyle().set("margin", "0").set("color", "white");

        Paragraph subtitle = new Paragraph("Consejos, recetas y artículos sobre nutrición y fitness");
        subtitle.getStyle().set("margin", "10px 0 0 0").set("color", "#a6c9cc").set("text-align", "center");

        header.add(title, subtitle);

        // Main content - Two columns
        HorizontalLayout mainContent = new HorizontalLayout();
        mainContent.setWidthFull();
        mainContent.setHeight("60vh");
        mainContent.setSpacing(false);
        mainContent.setPadding(false);

        // Left column - Latest Posts
        VerticalLayout postsColumn = new VerticalLayout();
        postsColumn.setWidth("50%");
        postsColumn.setHeightFull();
        postsColumn.setAlignItems(Alignment.CENTER);
        postsColumn.setJustifyContentMode(JustifyContentMode.START);
        postsColumn.getStyle().set("background-color", "white").set("padding", "40px").set("overflow-y", "auto");

        Div latestPosts = new Div();
        latestPosts.getStyle().set("width", "100%");

        H2 postsTitle = new H2("Últimos Artículos");
        postsTitle.getStyle().set("color", "#0A6D75").set("margin-bottom", "20px").set("text-align", "center");

        latestPosts.add(
            postsTitle,
            createBlogPost("Los Beneficios de una Alimentación Balanceada", "Descubre cómo una dieta equilibrada puede transformar tu salud..."),
            createBlogPost("Rutinas de Ejercicio para Principiantes", "Guía completa para comenzar tu viaje fitness..."),
            createBlogPost("Recetas Saludables y Rápidas", "Ideas deliciosas que no requieren mucho tiempo...")
        );

        postsColumn.add(latestPosts);

        // Right column - Categories
        VerticalLayout categoriesColumn = new VerticalLayout();
        categoriesColumn.setWidth("50%");
        categoriesColumn.setHeightFull();
        categoriesColumn.setAlignItems(Alignment.CENTER);
        categoriesColumn.setJustifyContentMode(JustifyContentMode.START);
        categoriesColumn.getStyle().set("background-color", "#f0f8f8").set("padding", "40px").set("overflow-y", "auto");

        Div categories = new Div();
        categories.getStyle().set("width", "100%");

        H2 categoriesTitle = new H2("Categorías");
        categoriesTitle.getStyle().set("color", "#0A6D75").set("margin-bottom", "20px").set("text-align", "center");

        categories.add(
            categoriesTitle,
            createCategory("🍎 Nutrición", "Artículos sobre alimentación saludable"),
            createCategory("💪 Fitness", "Consejos de ejercicio y entrenamiento"),
            createCategory("🥗 Recetas", "Recetas saludables y deliciosas"),
            createCategory("🧠 Bienestar", "Salud mental y hábitos saludables")
        );

        categoriesColumn.add(categories);

        mainContent.add(postsColumn, categoriesColumn);

        add(header, mainContent, new FooterComponent());
    }

    private VerticalLayout createBlogPost(String title, String excerpt) {
        VerticalLayout post = new VerticalLayout();
        post.setPadding(true);
        post.setSpacing(false);
        post.getStyle()
            .set("background-color", "#f9f9f9")
            .set("border-radius", "10px")
            .set("margin-bottom", "15px")
            .set("box-shadow", "0 2px 5px rgba(0,0,0,0.1)");

        H3 postTitle = new H3(title);
        postTitle.getStyle().set("margin", "0 0 10px 0").set("color", "#0A6D75").set("font-size", "1.2rem");

        Paragraph postExcerpt = new Paragraph(excerpt);
        postExcerpt.getStyle().set("margin", "0").set("color", "#555").set("line-height", "1.5");

        post.add(postTitle, postExcerpt);
        return post;
    }

    private VerticalLayout createCategory(String title, String description) {
        VerticalLayout category = new VerticalLayout();
        category.setPadding(true);
        category.setSpacing(false);
        category.getStyle()
            .set("background-color", "white")
            .set("border-radius", "10px")
            .set("margin-bottom", "15px")
            .set("box-shadow", "0 2px 5px rgba(0,0,0,0.1)")
            .set("border-left", "4px solid #0A6D75");

        H3 categoryTitle = new H3(title);
        categoryTitle.getStyle().set("margin", "0 0 5px 0").set("color", "#0A6D75").set("font-size", "1.1rem");

        Paragraph categoryDesc = new Paragraph(description);
        categoryDesc.getStyle().set("margin", "0").set("color", "#666");

        category.add(categoryTitle, categoryDesc);
        return category;
    }
}
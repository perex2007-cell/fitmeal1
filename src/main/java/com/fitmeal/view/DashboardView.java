package com.fitmeal.view;

import com.fitmeal.model.Goal;
import com.fitmeal.model.User;
import com.fitmeal.model.UserProfile;
import com.fitmeal.service.AuthService;
import com.fitmeal.service.DietService;
import com.fitmeal.service.HealthService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends ProtectedView {

    private AuthService authService = AuthService.getInstance();
    private HealthService healthService = new HealthService();
    private DietService dietService = new DietService();

    public DashboardView() {
        setSpacing(false);
        setPadding(false);
        setAlignItems(Alignment.CENTER);
        getStyle().set("background-color", "#f8fdfd");

        User loggedUser = authService.getLoggedUser();
        UserProfile profile = (loggedUser != null) ? loggedUser.getProfile() : null;

        double bmi;
        String classification;
        String diet;
        String goalLabel;

        if (profile != null) {
            bmi = healthService.calculateBMI(profile);
            classification = healthService.classifyBMI(bmi);
            diet = dietService.getRecommendation(profile.getGoal());
            goalLabel = profile.getGoal().getLabel();
        } else {
            bmi = 0;
            classification = "Sin datos";
            diet = "Usa la calculadora en el Inicio para obtener tu plan personalizado.";
            goalLabel = "No definido";
        }

        VerticalLayout container = new VerticalLayout();
        container.setMaxWidth("1000px");
        container.setPadding(true);
        container.getStyle().set("margin-top", "40px").set("margin-bottom", "40px");

        H2 title;
        if (loggedUser != null) {
            title = new H2("Hola, " + loggedUser.getName());
        } else {
            title = new H2("Tu Dashboard FitMeal");
        }
        title.getStyle().set("color", "#0A6D75").set("margin-bottom", "5px");
        
        Paragraph subtitle = new Paragraph("Aquí tienes el resumen de tu estado actual");
        subtitle.getStyle().set("color", "#666").set("margin-top", "0").set("margin-bottom", "30px");

        HorizontalLayout header = new HorizontalLayout(new VerticalLayout(title, subtitle));
        header.setWidthFull();
        header.setAlignItems(Alignment.CENTER);
        header.setJustifyContentMode(JustifyContentMode.START);
        ((VerticalLayout) header.getComponentAt(0)).setPadding(false);

        // Tarjetas
        Div cardsGrid = new Div();
        cardsGrid.getStyle()
                 .set("display", "grid")
                 .set("grid-template-columns", "repeat(auto-fit, minmax(280px, 1fr))")
                 .set("gap", "20px")
                 .set("width", "100%");

        cardsGrid.add(
            createCard("Tu Índice de Masa Corporal", bmi > 0 ? String.format("%.2f", bmi) + " (" + classification + ")" : classification, "#E0F2F1"),
            createCard("Objetivo Activo", goalLabel, "#FFF3E0"),
            createCard("Plan Asignado", diet, "#F3E5F5")
        );

        // Botones de acción inferior
        Button exercisesButton = new Button("Ver Ejercicios");
        exercisesButton.getStyle().set("background-color", "#0A6D75").set("color", "white").set("padding", "10px 20px");
        exercisesButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("exercises")));

        Button infoButton = new Button("Blog / Tips");
        infoButton.getStyle().set("background-color", "transparent").set("color", "#0A6D75").set("border", "1px solid #0A6D75").set("padding", "10px 20px");
        infoButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("info")));

        HorizontalLayout actions = new HorizontalLayout(exercisesButton, infoButton);
        actions.getStyle().set("margin-top", "30px");

        // --- NEW SECTIONS ---
        HorizontalLayout secondaryGrid = new HorizontalLayout();
        secondaryGrid.setWidthFull();
        secondaryGrid.setSpacing(true);
        secondaryGrid.getStyle().set("margin-top", "30px");

        // 1. Daily Goals
        VerticalLayout dailyGoals = new VerticalLayout();
        dailyGoals.getStyle()
            .set("background-color", "white")
            .set("border-radius", "15px")
            .set("padding", "25px")
            .set("box-shadow", "0 10px 25px rgba(0,0,0,0.05)")
            .set("flex", "1");
        
        H3 goalsTitle = new H3("Metas de Hoy");
        goalsTitle.getStyle().set("color", "#0A6D75").set("margin-top", "0");
        
        dailyGoals.add(goalsTitle);
        
        String[] goals = getDynamicGoals();
        for (String goal : goals) {
            Checkbox cb = new Checkbox(goal);
            cb.getStyle().set("color", "#555");
            dailyGoals.add(cb);
        }

        // 2. Macronutrients estimation (based on goal)
        VerticalLayout macrosSection = new VerticalLayout();
        macrosSection.getStyle()
            .set("background-color", "#2a2c2b")
            .set("border-radius", "15px")
            .set("padding", "25px")
            .set("color", "white")
            .set("flex", "1");
        
        H3 macrosTitle = new H3("Distribución Nutricional");
        macrosTitle.getStyle().set("color", "white").set("margin-top", "0");
        
        macrosSection.add(macrosTitle);
        
        String pLabel = "Proteínas (30%)";
        String cLabel = "Carbohidratos (50%)";
        String fLabel = "Grasas (20%)";
        
        if (profile != null && profile.getGoal() == Goal.WEIGHT_GAIN) {
            pLabel = "Proteínas (35%)"; cLabel = "Carbs (45%)";
        }

        macrosSection.add(createMacroBar(pLabel, 0.35, "#F77A14"));
        macrosSection.add(createMacroBar(cLabel, 0.50, "#0A6D75"));
        macrosSection.add(createMacroBar(fLabel, 0.15, "#FFC107"));

        secondaryGrid.add(dailyGoals, macrosSection);

        // 3. Tip of the day
        VerticalLayout tipSection = new VerticalLayout();
        tipSection.getStyle()
            .set("background-color", "#E0F2F1")
            .set("border-radius", "10px")
            .set("padding", "20px")
            .set("margin-top", "30px");
        
        H3 tipTitle = new H3("💡 Consejo del día");
        tipTitle.getStyle().set("margin-top", "0").set("color", "#004D40").set("font-size", "1.1rem");
        
        String tip = getDynamicTip(profile != null ? profile.getGoal() : Goal.MAINTAIN_WEIGHT);
        
        Paragraph tipText = new Paragraph(tip);
        tipText.getStyle().set("color", "#00695C").set("margin-bottom", "0");
        tipSection.add(tipTitle, tipText);

        container.add(header, cardsGrid, secondaryGrid, tipSection, actions);
        add(container, new FooterComponent());
    }

    private String getDynamicTip(Goal goal) {
        String[] generalTips = {
            "La constancia supera a la perfección; sigue adelante incluso en días difíciles.",
            "Pequeños cambios diarios se traducen en grandes resultados a largo plazo.",
            "Escucha a tu cuerpo, el descanso es tan vital como el propio entrenamiento.",
            "La hidratación constante mejora tu concentración y rendimiento físico.",
            "Varía tus fuentes de alimentos para obtener un perfil completo de micronutrientes."
        };
        
        String[] lossTips = {
            "Beber un vaso de agua antes de comer puede ayudar a controlar el apetito.",
            "Prioriza los vegetales en cada comida para aumentar la saciedad sin muchas calorías.",
            "Añade caminatas cortas después de las comidas para mejorar la digestión.",
            "Dormir bien regula las hormonas del hambre (grelina y leptina).",
            "Registra tus comidas; te ayudará a ser consciente de lo que realmente consumes."
        };

        String[] gainTips = {
            "Asegúrate de consumir suficiente proteína para reparar las fibras musculares.",
            "El entrenamiento de fuerza debe ser progresivo; intenta subir el peso gradualmente.",
            "No temas a las grasas saludables; son esenciales para la producción de testosterona.",
            "Un batido de proteínas después de entrenar acelera la recuperación.",
            "Come lo suficiente; para ganar músculo necesitas estar en un ligero superávit."
        };

        int dayOfYear = java.time.LocalDate.now().getDayOfYear();
        
        return switch(goal) {
            case WEIGHT_LOSS -> lossTips[dayOfYear % lossTips.length];
            case WEIGHT_GAIN -> gainTips[dayOfYear % gainTips.length];
            default -> generalTips[dayOfYear % generalTips.length];
        };
    }

    private String[] getDynamicGoals() {
        String[][] pools = {
            {"Beber 2.5L de Agua", "Completar rutina diaria", "Dormir 7-8 horas", "Comer 3 frutas"},
            {"Evitar azúcares procesados", "Entrenamiento de fuerza", "15 min de meditación", "Subir por las escaleras"},
            {"Caminar 30 minutos", "Preparar comida saludable", "Estirar 10 minutos", "No picar entre horas"},
            {"Hacer 50 sentadillas", "Cero gaseosas hoy", "Desayuno alto en proteína", "Llamar a un amigo fitness"},
            {"Probar una receta nueva", "Organizar el bolso de gym", "Evitar el móvil antes de dormir", "Beber té verde"}
        };
        int dayOfWeek = java.time.LocalDate.now().getDayOfWeek().getValue() - 1; 
        return pools[dayOfWeek % pools.length];
    }

    private VerticalLayout createCard(String titleText, String contentText, String bgColor) {
        VerticalLayout card = new VerticalLayout();
        card.getStyle()
            .set("background-color", "white")
            .set("border-top", "4px solid " + bgColor)
            .set("border-radius", "10px")
            .set("padding", "20px")
            .set("box-shadow", "0 5px 15px rgba(0,0,0,0.05)");

        H3 t = new H3(titleText);
        t.getStyle().set("margin", "0 0 10px 0").set("font-size", "1.1rem").set("color", "#333");
        Paragraph p = new Paragraph(contentText);
        p.getStyle()
            .set("margin", "0")
            .set("color", "#666")
            .set("line-height", "1.5")
            .set("white-space", "pre-wrap");
        
        card.add(t, p);
        return card;
    }

    private VerticalLayout createMacroBar(String label, double value, String color) {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        Span s = new Span(label);
        s.getStyle().set("font-size", "0.85rem").set("margin-bottom", "4px");
        
        ProgressBar bar = new ProgressBar();
        bar.setValue(value);
        bar.getStyle().set("border-radius", "4px");
        bar.getElement().getStyle().set("--vaadin-progress-bar-value-color", color);
        
        layout.add(s, bar);
        return layout;
    }
}

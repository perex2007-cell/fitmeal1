package com.fitmeal.view;

import com.fitmeal.model.Goal;
import com.fitmeal.model.UserProfile;
import com.fitmeal.service.DietService;
import com.fitmeal.service.HealthService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    private ComboBox<String> genderField = new ComboBox<>("Género", "Hombre", "Mujer");
    private IntegerField ageField = new IntegerField("Edad");
    private NumberField weightField = new NumberField("Peso (kg)");
    private NumberField heightField = new NumberField("Estatura (cm)");
    private ComboBox<String> activityField = new ComboBox<>("Nivel de Actividad", "Sedentario", "Ligero", "Moderado", "Muy Activo");
    private ComboBox<Goal> goalField = new ComboBox<>("Objetivo");

    private Button calculateButton = new Button("GENERAR MI DIETA PERSONALIZADA");

    private Span bmiResult = new Span();
    private Span bmiClassification = new Span();
    private Div dietRecommendation = new Div();

    private HealthService healthService = new HealthService();
    private DietService dietService = new DietService();

    public MainView() {
        setPadding(false);
        setSpacing(false);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.START);
        getStyle().set("background-color", "#ffffff");

        // --- 1. HERO SECTION ---
        VerticalLayout mainHero = new VerticalLayout();
        mainHero.setWidthFull();
        mainHero.setAlignItems(Alignment.START);
        mainHero.setJustifyContentMode(JustifyContentMode.CENTER);
        mainHero.getStyle()
                .set("height", "550px")
                .set("background", "linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)), url('https://images.unsplash.com/photo-1534438327276-14e5300c3a48?ixlib=rb-4.0.3') center/cover no-repeat")
                .set("color", "white")
                .set("padding", "0 10%");

        H1 heroTitle = new H1("TRANSFORMA TU VIDA: EJERCICIO Y NUTRICIÓN A TU MEDIDA");
        heroTitle.getStyle()

                .set("color", "white")
                 .set("font-size", "3.8rem")
                 .set("font-weight", "900")
                 .set("max-width", "850px")
                 .set("text-transform", "uppercase")
                 .set("line-height", "1.1");
                 
                 
        Paragraph heroSub = new Paragraph("Descubre el balance perfecto entre entrenamientos efectivos y un régimen alimenticio diseñado estratégicamente para tu tipo de cuerpo.");
        heroSub.getStyle().set("font-size", "1.4rem").set("max-width", "700px").set("line-height", "1.5");

        mainHero.add(heroTitle, heroSub);


        // --- 2. TEXTO ABUNDANTE SOBRE FITNESS ---
        VerticalLayout textContainer = new VerticalLayout();
        textContainer.setWidthFull();
        textContainer.setAlignItems(Alignment.CENTER);
        textContainer.getStyle()
                .set("background-color", "#f8fdfd")
                .set("padding", "80px 20px");

        Div textWrapper = new Div();
        textWrapper.getStyle()
                .set("width", "100%")
                .set("max-width", "1200px")
                .set("display", "flex")
                .set("flex-direction", "column")
                .set("gap", "40px");

        textWrapper.add(
            createArticleBlock("La Verdad sobre la Alimentación Saludable", 
                "Llevar un régimen nutricional adecuado va muchísimo más allá de la simple pérdida de peso; es la piedra angular para lograr la vitalidad a largo plazo, prevenir enfermedades crónicas, estabilizar tu salud mental y maximizar tu esperanza y calidad de vida. Cuando nutres tu organismo con los macronutrientes correctos (proteínas magras, carbohidratos complejos de lenta absorción y grasas saturadas e insaturadas equilibradas), estás encendiendo un motor biológico perfecto. Es indispensable romper el mito de que 'comer sano es comer aburrido o comer menos'. Se trata de comer con inteligencia, entendiendo qué alimentos provocan picos de insulina y cuáles mantienen a tu cuerpo saciado y repleto de energía durante arduas jornadas laborales y entrenamientos devastadores."),
            
            createArticleBlock("Por Qué Levantar Pesas y Hacer Ejercicio Cardiovascular", 
                "Si la nutrición es el combustible, el ejercicio es el catalizador que determina hacia dónde se dirige ese combustible. Realizar ejercicios cardiovasculares continuos garantiza la salud del músculo más importante que tienes: el corazón. Correr, nadar, o rutinas HIIT expanden tu capacidad aeróbica y te protegen de infartos. Por otra parte, el entrenamiento de fuerza y resistencia (levantamiento de pesas o calistenia) somete a las fibras musculares a micro-desgarros controlados que, al repararse, generan tejido muscular denso. Esta formación muscular eleva tu tasa metabólica basal, lo que significa que a medida que creas musculatura, tu cuerpo se vuelve una caldera que quema grasa incluso en estado de reposo absoluto. ¡Combina ambos y verás una transformación total!"),

            createArticleBlock("El Factor Invisible: Descanso y Sistema Nervioso", 
                "Existe una lamentable epidemia en el fitness moderno: el sobreentrenamiento sin recuperación. En el gimnasio destruimos músculo, pero es en la cama, en sueño profundo y fase REM, donde tu sistema endocrino produce niveles astronómicos de la Hormona de Crecimiento Humano (HGH) y repara tejidos. Reducir los niveles crónicos de cortisol (estrés) resulta tan vital como la propia dieta. Sin una correcta hidratación (mínimo de 2.5 a 3 litros al día para un atleta), estiramientos matutinos o sesiones de meditación para equilibrar tu sistema nervioso simpático, todos los esfuerzos en las pesas se verán truncados por la inflamación sistémica. El éxito en el fitness es un triángulo equilátero formado por Ejercicio, Nutrición y Recupoeración.")
        );
        textContainer.add(textWrapper);


        // --- 3. RUTINAS Y NUTRICIÓN (Middle Section from previous layout) ---
        Div middleContainer = new Div();
        middleContainer.getStyle()
                .set("display", "flex")
                .set("flex-wrap", "wrap")
                .set("width", "100%")
                .set("max-width", "1200px")
                .set("justify-content", "space-between")
                .set("padding", "60px 20px")
                .set("box-sizing", "border-box")
                .set("gap", "40px");

        // Left Column: Rutinas
        VerticalLayout rutinasCol = new VerticalLayout();
        rutinasCol.setPadding(false);
        rutinasCol.getStyle().set("flex", "1 1 500px");
        H2 rutinasTitle = new H2("NUESTRAS RUTINAS");
        rutinasTitle.getStyle().set("font-size", "1.5rem").set("font-weight", "800").set("color", "#222");

        Div rutinasGrid = new Div();
        rutinasGrid.getStyle()
                   .set("display", "grid")
                   .set("grid-template-columns", "repeat(auto-fit, minmax(180px, 1fr))")
                   .set("gap", "20px")
                   .set("width", "100%");

        rutinasGrid.add(
            createRoutineCard("Cardio Intenso", "Quema calorías con nuestras sesiones guiadas y rápidas", "https://images.unsplash.com/photo-1434596922112-19c563067271?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&q=80", "cardio-intenso"),
            createRoutineCard("Fuerza y Músculo", "Construye masa magra y densa con rutinas pesadas", "https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&q=80", "fuerza-musculo"),
            createRoutineCard("Yoga y Estiramiento", "Mejora tu elasticidad corporal, mente y articulaciones", "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&q=80", "yoga-estiramiento")
        );

        Button verMasBtn = new Button("Ver más en rutinas");
        verMasBtn.getStyle().set("background-color", "#F77A14").set("color", "white").set("border-radius", "8px").set("align-self", "center").set("margin-top", "20px").set("font-weight", "bold").set("padding", "12px 24px");
        verMasBtn.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("exercises")));
        
        rutinasCol.add(rutinasTitle, rutinasGrid, verMasBtn);
        rutinasCol.setHorizontalComponentAlignment(Alignment.CENTER, verMasBtn);


        // Right Column: Nutrición
        VerticalLayout nutricionCol = new VerticalLayout();
        nutricionCol.setPadding(false);
        nutricionCol.getStyle().set("flex", "1 1 350px");
        H2 nutTitle = new H2("NUTRICIÓN SALUDABLE");
        nutTitle.getStyle().set("font-size", "1.5rem").set("font-weight", "800").set("color", "#222");

        nutricionCol.add(
            nutTitle,
            createNutritionBand("Planes de Comidas", "Planes enfocados para las comidas diarias que nutren tu cuerpo al instante.", "https://images.unsplash.com/photo-1490645935967-10de6ba17061?ixlib=rb-4.0.3&auto=format&fit=crop&w=300&q=80"),
            createNutritionBand("Recetas Saludables", "Recetas de alto valor biológico completamente sanas, fáciles de preparar para ti.", "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&auto=format&fit=crop&w=300&q=80"),
            createNutritionBand("Consejos Nutricionales", "Consejos clave, guía de suplementación proteica y hábitos indispensables.", "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixlib=rb-4.0.3&auto=format&fit=crop&w=300&q=80")
        );

        middleContainer.add(rutinasCol, nutricionCol);


        // --- 4. FORM SECTION (Diet Calculator) ---
        Div dietFormSection = new Div();
        dietFormSection.getStyle()
                .set("display", "flex")
                .set("flex-wrap", "wrap")
                .set("width", "100%")
                .set("max-width", "1200px")
                .set("justify-content", "space-between")
                .set("align-items", "center")
                .set("padding", "80px 20px")
                .set("margin", "0 auto")
                .set("box-sizing", "border-box");

        // Form Left Text
        VerticalLayout formLeftSide = new VerticalLayout();
        formLeftSide.setSpacing(true);
        formLeftSide.setPadding(false);
        formLeftSide.getStyle()
                .set("flex", "1 1 400px")
                .set("min-width", "300px")
                .set("margin-right", "40px")
                .set("margin-bottom", "40px");

        H2 formMainTitle = new H2("CREA TU PLAN DE DIETA PERSONALIZADO");
        formMainTitle.getStyle().set("font-size", "3rem").set("font-family", "sans-serif").set("font-weight", "900").set("line-height", "1.1").set("color", "#222");

        Paragraph p1 = new Paragraph("¡Tu camino hacia un estilo de vida saludable comienza aquí!");
        p1.getStyle().set("font-size", "1.4rem").set("color", "#444").set("font-weight", "600");

        Paragraph p2 = new Paragraph("Obtén un plan de comidas detallado y automatizado, basado explícitamente en tu perfil físico único y tus objetivos personales. Rellena los datos adjuntos y da el primer paso hacia la transformación total.");
        p2.getStyle().set("font-size", "1.2rem").set("color", "#555").set("line-height", "1.6");

        Div formImagePlaceholder = new Div();
        formImagePlaceholder.getStyle().set("height", "300px")
                .set("width", "100%")
                .set("background", "url('https://images.unsplash.com/photo-1498837167922-41c14434b422?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80') center/cover no-repeat")
                .set("border-radius", "15px")
                .set("box-shadow", "0 10px 20px rgba(0,0,0,0.1)")
                .set("margin-top", "20px");

        formLeftSide.add(formMainTitle, p1, p2, formImagePlaceholder);

        // Form Right Panel
        VerticalLayout formRightSide = new VerticalLayout();
        formRightSide.getStyle()
                .set("flex", "1 1 400px")
                .set("min-width", "300px")
                .set("background-color", "white")
                .set("border-radius", "15px")
                .set("box-shadow", "0 15px 40px rgba(0,0,0,0.1)")
                .set("padding", "40px");

        H3 formTitle = new H3("Ingresa tus datos para generar tu plan nutricional gratuito");
        formTitle.getStyle().set("text-align", "center").set("width", "100%").set("font-size", "1.2rem").set("color", "#222").set("margin-top", "0");

        HorizontalLayout row1 = new HorizontalLayout(genderField, ageField);
        row1.setWidthFull();
        genderField.setWidth("50%");
        ageField.setWidth("50%");

        HorizontalLayout row2 = new HorizontalLayout(weightField, heightField);
        row2.setWidthFull();
        weightField.setWidth("50%");
        heightField.setWidth("50%");

        activityField.setWidthFull();
        goalField.setItems(Goal.values());
        goalField.setWidthFull();

        calculateButton.setWidthFull();
        calculateButton.getStyle()
                .set("background-color", "#F77A14")
                .set("color", "white")
                .set("font-weight", "bold")
                .set("font-size", "1.1rem")
                .set("padding", "20px 0")
                .set("border-radius", "8px")
                .set("margin-top", "20px")
                .set("cursor", "pointer");

        calculateButton.addClickListener(e -> calculate());

        VerticalLayout resultsPanel = new VerticalLayout(bmiResult, bmiClassification, dietRecommendation);
        resultsPanel.setPadding(false);
        resultsPanel.setSpacing(false);
        resultsPanel.getStyle().set("margin-top", "20px");

        formRightSide.add(formTitle, row1, row2, activityField, goalField, calculateButton, resultsPanel);
        dietFormSection.add(formLeftSide, formRightSide);

        VerticalLayout formContainer = new VerticalLayout(dietFormSection);
        formContainer.setPadding(false);
        formContainer.setSpacing(false);
        formContainer.setWidthFull();
        formContainer.setAlignItems(Alignment.CENTER);
        formContainer.getStyle().set("background-color", "#f0f8f8").set("padding-bottom", "60px");


        // --- ADD EVERYTHING TO VIEW ---
        add(mainHero, textContainer, middleContainer, formContainer, new FooterComponent());
    }

    private VerticalLayout createArticleBlock(String titleStr, String textStr) {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        H3 ttl = new H3(titleStr);
        ttl.getStyle().set("color", "#0A6D75").set("font-size", "2rem").set("margin-bottom", "10px").set("font-weight", "800");
        
        Paragraph txt = new Paragraph(textStr);
        txt.getStyle().set("color", "#555").set("font-size", "1.1rem").set("line-height", "1.7").set("text-align", "justify");
        
        layout.add(ttl, txt);
        return layout;
    }

    private VerticalLayout createRoutineCard(String titleStr, String descStr, String bgUrl, String goalParam) {
        VerticalLayout card = new VerticalLayout();
        card.setPadding(true);
        card.setSpacing(false);
        card.getStyle()
            .set("background-color", "white")
            .set("border-radius", "15px")
            .set("border", "1px solid #eee")
            .set("box-shadow", "0 6px 15px rgba(0,0,0,0.03)")
            .set("overflow", "hidden")
            .set("padding", "0")
            .set("padding-bottom", "15px");

        Div img = new Div();
        img.getStyle()
           .set("width", "100%")
           .set("height", "180px")
           .set("background", "url('" + bgUrl + "') center/cover no-repeat");

        H4 t = new H4(titleStr);
        t.getStyle().set("margin", "15px 15px 5px").set("font-size", "1.2rem").set("color", "#2b2929");

        Paragraph d = new Paragraph(descStr);
        d.getStyle().set("margin", "0 15px 15px").set("font-size", "0.95rem").set("color", "#666").set("line-height", "1.4");

        Button link = new Button("Comienza >");
        link.getStyle()
            .set("background-color", "transparent")
            .set("color", "#F77A14")
            .set("border", "none")
            .set("margin-left", "15px")
            .set("font-weight", "bold")
            .set("font-size", "1rem")
            .set("cursor", "pointer")
            .set("padding", "0");
        link.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("exercises?category=" + goalParam)));

        card.add(img, t, d, link);
        return card;
    }

    private HorizontalLayout createNutritionBand(String titleStr, String descStr, String imgUrl) {
        HorizontalLayout band = new HorizontalLayout();
        band.setWidthFull();
        band.setAlignItems(Alignment.CENTER);
        band.getStyle()
            .set("background-color", "#2a2c2b")
            .set("border-radius", "15px")
            .set("margin-bottom", "15px")
            .set("color", "white")
            .set("overflow", "hidden");

        VerticalLayout text = new VerticalLayout();
        H4 t = new H4(titleStr);
        t.getStyle().set("margin", "0 O 5px 0").set("font-size", "1.2rem").set("color", "#fff");
        Paragraph d = new Paragraph(descStr);
        d.getStyle().set("margin", "0").set("font-size", "0.95rem").set("color", "#ccc").set("line-height", "1.3");
        text.add(t, d);
        text.setPadding(true);

        Div img = new Div();
        img.getStyle()
           .set("width", "180px")
           .set("height", "120px")
           .set("flex-shrink", "0")
           .set("background", "url('" + imgUrl + "') center/cover no-repeat");

        band.add(text, img);
        band.setFlexGrow(1, text);
        return band;
    }

    private void calculate() {
        if (weightField.isEmpty() || heightField.isEmpty()
                || ageField.isEmpty() || goalField.isEmpty()) {
            bmiResult.setText("⚠ Por favor completa todos los campos.");
            bmiClassification.setText("");
            dietRecommendation.setText("");
            return;
        }

        double h = heightField.getValue();
        if (h > 3.0) h = h / 100.0;

        UserProfile user = new UserProfile(
                weightField.getValue(), h,
                ageField.getValue(), goalField.getValue()
        );

        double bmi = healthService.calculateBMI(user);
        String classification = healthService.classifyBMI(bmi);
        String diet = dietService.getRecommendation(user.getGoal());

        bmiResult.setText("IMC: " + String.format("%.2f", bmi));
        bmiResult.getStyle().set("font-weight", "bold").set("font-size", "1.2rem").set("color", "#222");

        bmiClassification.setText("Clasificación: " + classification);
        bmiClassification.getStyle().set("color", "#0A6D75").set("font-weight", "900");

        dietRecommendation.setText(diet);
        dietRecommendation.getStyle().set("margin-top", "15px").set("color", "#555").set("font-size", "1.1rem").set("border-left", "4px solid #0A6D75").set("padding-left", "10px");
    }
}

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
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    private ComboBox<String> genderField = new ComboBox<>("Género", "Hombre", "Mujer", "Otro");
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
        getStyle().set("background-color", "#f8fdfd");

        // --- NEW HERO SECTION ---
        VerticalLayout mainHero = new VerticalLayout();
        mainHero.setWidthFull();
        mainHero.setAlignItems(Alignment.CENTER);
        mainHero.setJustifyContentMode(JustifyContentMode.CENTER);
        mainHero.getStyle()
                .set("min-height", "80vh")
                .set("background", "linear-gradient(rgba(10, 109, 117, 0.7), rgba(0, 0, 0, 0.7)), url('https://images.unsplash.com/photo-1534438327276-14e5300c3a48?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80') center/cover no-repeat")
                .set("color", "white")
                .set("text-align", "center")
                .set("padding", "20px");

        H1 heroTitle = new H1("CONQUISTA TU MEJOR VERSIÓN");
        heroTitle.getStyle().set("font-size", "4.5rem").set("font-weight", "900").set("text-transform", "uppercase").set("letter-spacing", "2px").set("margin-bottom", "10px");

        Paragraph heroText = new Paragraph("Entrenamiento, nutrición y disciplina. Todo lo que necesitas para alcanzar tus metas fitness en un solo nivel.");
        heroText.getStyle().set("font-size", "1.5rem").set("max-width", "800px").set("line-height", "1.6").set("margin-top", "0").set("font-weight", "300");

        mainHero.add(heroTitle, heroText);


        // --- INFOMATIONAL SECTION 1 (Nutrición) ---
        VerticalLayout infoSection1 = createInfoSection(
                "La Nutrición como Combustible",
                "Para lograr cambios reales, el entrenamiento debe estar respaldado por la nutrición adecuada. Hablamos de macronutrientes: proteínas para reparar los músculos, carbohidratos complejos para tener energía explosiva, y grasas saludables para el equilibrio hormonal. No se trata de comer menos, se trata de comer lo que tu cuerpo demanda. Cada bocado que ingieres es una oportunidad para maximizar tu rendimiento deportivo.",
                "https://images.unsplash.com/photo-1490645935967-10de6ba17061?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80",
                false
        );

        // --- INFOMATIONAL SECTION 2 (Fitness / Fuerza / Cardio) ---
        VerticalLayout infoSection2 = createInfoSection(
                "Fuerza, Hipertrofia y Resistencia",
                "El verdadero 'fitness' requiere un enfoque híbrido. Levantar pesas pesadas no solo esculpe visualmente el cuerpo; incrementa notablemente tu densidad ósea, acelera tu metabolismo basal y fortalece tus articulaciones. Por otro lado, el entrenamiento cardiovascular (sea HIIT o LISS) mejora la vida de tu corazón, aumenta tu volumen de oxígeno máximo y favorece la quema de grasa abdominal. ¡Combinarlos te vuelve imparable!",
                "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80",
                true
        );

        // --- INFOMATIONAL SECTION 3 (Disciplina y Recuperación) ---
        VerticalLayout infoSection3 = createInfoSection(
                "El Arte del Descanso y la Recuperación",
                "¿Sabías que el músculo no crece mientras estás en el gimnasio, sino mientras duermes? La recuperación es el eslabón perdido en la mayoría de rutinas. Dormir entre 7 y 8 horas diarias es obligatorio para regular el cortisol (la hormona del estrés) y liberar hormona de crecimiento. Además, el fitness es un juego mental: la verdadera disciplina aparece cuando vas a entrenar esos días en los que tu mente te pide excusas.",
                "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80",
                false
        );


        // --- FORM SECTION (Moved to bottom) ---
        Div dietFormSection = new Div();
        dietFormSection.getStyle()
                .set("display", "flex")
                .set("flex-wrap", "wrap")
                .set("width", "100%")
                .set("max-width", "1200px")
                .set("justify-content", "space-between")
                .set("align-items", "flex-start")
                .set("padding", "80px 20px")
                .set("margin", "0 auto")
                .set("box-sizing", "border-box");

        // Form Left Side Text
        VerticalLayout formLeftSide = new VerticalLayout();
        formLeftSide.setSpacing(true);
        formLeftSide.setPadding(false);
        formLeftSide.getStyle()
                .set("flex", "1 1 400px")
                .set("min-width", "300px")
                .set("margin-right", "40px")
                .set("margin-bottom", "40px");

        H2 formMainTitle = new H2("CREA TU PLAN DE DIETA PERSONALIZADO");
        formMainTitle.getStyle().set("font-size", "2.8rem").set("font-family", "sans-serif").set("font-weight", "900").set("line-height", "1.1").set("color", "#0A6D75");

        Paragraph p1 = new Paragraph("Después de conocer los pilares fundamentales del fitness, ¡es hora de tomar el control! El camino hacia tu cambio físico empieza analizando qué necesita tu cuerpo.");
        p1.getStyle().set("font-size", "1.2rem").set("color", "#555");

        Paragraph p2 = new Paragraph("Ingresa tus datos biométricos en nuestro sistema y FitMeal calculará inmediatamente tus requerimientos, asignándote el plan que mejor se adapte a tus rutinas y vida diaria.");
        p2.getStyle().set("font-size", "1.2rem").set("color", "#555");

        Div formImagePlaceholder = new Div();
        formImagePlaceholder.getStyle().set("height", "250px")
                .set("width", "100%")
                .set("background", "url('https://images.unsplash.com/photo-1498837167922-41c14434b422?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80') center/cover no-repeat")
                .set("border-radius", "15px")
                .set("margin-top", "20px");

        formLeftSide.add(formMainTitle, p1, p2, formImagePlaceholder);

        // Form Right Side Layout
        VerticalLayout formRightSide = new VerticalLayout();
        formRightSide.getStyle()
                .set("flex", "1 1 400px")
                .set("min-width", "300px")
                .set("background-color", "white")
                .set("border-radius", "15px")
                .set("box-shadow", "0 10px 30px rgba(0,0,0,0.1)")
                .set("padding", "30px");

        H3 formTitle = new H3("Inicia tu nueva etapa hoy");
        formTitle.getStyle().set("text-align", "center").set("width", "100%");

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
                .set("background-color", "#F77B15")
                .set("color", "white")
                .set("font-weight", "bold")
                .set("font-size", "1.1rem")
                .set("padding", "20px 0")
                .set("border-radius", "8px")
                .set("margin-top", "15px")
                .set("cursor", "pointer");

        calculateButton.addClickListener(e -> calculate());

        VerticalLayout resultsPanel = new VerticalLayout(bmiResult, bmiClassification, dietRecommendation);
        resultsPanel.setPadding(false);
        resultsPanel.setSpacing(false);
        resultsPanel.getStyle().set("margin-top", "15px");

        formRightSide.add(formTitle, row1, row2, activityField, goalField, calculateButton, resultsPanel);

        dietFormSection.add(formLeftSide, formRightSide);

        VerticalLayout formContainer = new VerticalLayout(dietFormSection);
        formContainer.setPadding(false);
        formContainer.setSpacing(false);
        formContainer.setWidthFull();
        formContainer.setAlignItems(Alignment.CENTER);
        formContainer.getStyle().set("background-color", "#f0f8f8");


        // --- ADD ALL SECTIONS TO VIEW ---
        add(mainHero, infoSection1, infoSection2, infoSection3, formContainer, new FooterComponent());
    }

    private VerticalLayout createInfoSection(String titleStr, String textStr, String imageUrl, boolean reverseLayout) {
        VerticalLayout section = new VerticalLayout();
        section.setWidthFull();
        section.setAlignItems(Alignment.CENTER);
        section.getStyle()
                .set("background-color", reverseLayout ? "#ffffff" : "#f8fdfd")
                .set("padding", "80px 20px");

        Div container = new Div();
        container.getStyle()
                .set("display", "flex")
                .set("flex-wrap", reverseLayout ? "wrap-reverse" : "wrap")
                .set("width", "100%")
                .set("max-width", "1200px")
                .set("justify-content", "space-between")
                .set("align-items", "center");

        // Text content
        VerticalLayout textLayout = new VerticalLayout();
        textLayout.getStyle().set("flex", "1 1 500px").set("min-width", "300px");
        H2 title = new H2(titleStr);
        title.getStyle().set("color", "#0A6D75").set("font-size", "2.5rem").set("margin-top", "0");
        Paragraph text = new Paragraph(textStr);
        text.getStyle().set("font-size", "1.15rem").set("color", "#444").set("line-height", "1.8");
        textLayout.add(title, text);

        // Image content
        Div imageLayout = new Div();
        imageLayout.getStyle()
                .set("flex", "1 1 500px")
                .set("min-width", "300px")
                .set("height", "400px")
                .set("background", "url('" + imageUrl + "') center/cover no-repeat")
                .set("border-radius", "20px")
                .set("box-shadow", "0 10px 20px rgba(0,0,0,0.1)");

        if (reverseLayout) {
            container.add(imageLayout, textLayout);
            textLayout.getStyle().set("padding-left", "40px");
            imageLayout.getStyle().set("margin-bottom", "20px");
        } else {
            container.add(textLayout, imageLayout);
            textLayout.getStyle().set("padding-right", "40px");
            imageLayout.getStyle().set("margin-bottom", "20px");
        }

        section.add(container);
        return section;
    }

    private void calculate() {
        if (weightField.isEmpty() || heightField.isEmpty()
                || ageField.isEmpty() || goalField.isEmpty()) {

            bmiResult.setText("⚠ Por favor completa todos los campos.");
            bmiClassification.setText("");
            dietRecommendation.setText("");
            return;
        }

        double heightInMeters = heightField.getValue();
        if (heightInMeters > 3.0) {
            heightInMeters = heightInMeters / 100.0;
        }

        UserProfile user = new UserProfile(
                weightField.getValue(),
                heightInMeters,
                ageField.getValue(),
                goalField.getValue()
        );

        double bmi = healthService.calculateBMI(user);
        String classification = healthService.classifyBMI(bmi);
        String diet = dietService.getRecommendation(user.getGoal());

        bmiResult.setText("IMC: " + String.format("%.2f", bmi));
        bmiResult.getStyle().set("font-weight", "bold").set("font-size", "1.1rem").set("color", "#333");

        bmiClassification.setText("Clasificación: " + classification);
        bmiClassification.getStyle().set("color", "#0A6D75").set("font-weight", "bold");

        dietRecommendation.setText(diet);
        dietRecommendation.getStyle().set("margin-top", "10px").set("color", "#555");
    }
}

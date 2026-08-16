package project.ui.user;

import project.app.AppNavigator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * RoadGuardian - AI Vehicle Breakdown Diagnosis
 *
 * UI-only page.
 * Existing navigation is intentionally not changed here.
 */
public class AIDiagnosisPage {

    // ============================================================
    // HOMEPAGE THEME
    // ============================================================

    private static final String PAGE_BG = "#D9F3F6";
    private static final String CARD_BG = "#F3FBFC";
    private static final String BORDER = "#C5DDE1";
    private static final String HEADING = "#0B1F3A";
    private static final String SECONDARY = "#64748B";
    private static final String BLUE = "#2864E8";
    private static final String LIGHT_BLUE = "#DDEAFF";
    private static final String SUCCESS = "#17A95B";

    private final VBox root = new VBox();

    private final VBox resultCard = new VBox(12);

    private final Button diagnoseButton = new Button("✣   Diagnose");

    private final Button resetButton = new Button("↻   Reset");

    private final java.util.List<Button> symptomButtons = new java.util.ArrayList<>();

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public AIDiagnosisPage() {
        buildUI();
    }

    // ============================================================
    // SCENE
    // ============================================================

    public Scene getAIDiagnosisScene() {

        Scene scene = new Scene(
                root,
                1280,
                760);

        return scene;
    }

    // ============================================================
    // MAIN UI
    // ============================================================

    private void buildUI() {

        root.setSpacing(0);
        root.setFillWidth(true);
        root.setStyle(
                "-fx-background-color: " + PAGE_BG + ";");

        HBox header = createHeader();

        HBox body = new HBox();
        body.setFillHeight(true);
        HBox.setHgrow(body, Priority.ALWAYS);

        VBox sidebar = createSidebar();
        VBox page = createPageContent();

        body.getChildren().addAll(
                sidebar,
                page);

        VBox.setVgrow(body, Priority.ALWAYS);

        root.getChildren().addAll(
                header,
                body);
    }

    // ============================================================
    // HEADER
    // ============================================================

    private HBox createHeader() {

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(
                new Insets(14, 28, 14, 28));
        header.setSpacing(18);
        header.setPrefHeight(76);

        header.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #D6E5E8;" +
                        "-fx-border-width: 0 0 1 0;");

        StackPane logoCircle = new StackPane();

        Circle circle = new Circle(
                20,
                Color.web(BLUE));

        Label check = new Label("✓");
        check.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24));
        check.setTextFill(Color.WHITE);

        logoCircle.getChildren().addAll(
                circle,
                check);

        Label brand = new Label("Road");
        brand.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23));
        brand.setTextFill(Color.web(HEADING));

        Label guardian = new Label("Guardian");
        guardian.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23));
        guardian.setTextFill(Color.web(BLUE));

        HBox logo = new HBox(0);
        logo.setAlignment(Pos.CENTER_LEFT);
        logo.getChildren().addAll(
                brand,
                guardian);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox search = createSearchBox();

        StackPane notification = createNotification();

        VBox profile = createProfile();

        header.getChildren().addAll(
                logoCircle,
                logo,
                spacer,
                search,
                notification,
                profile);

        return header;
    }

    private HBox createSearchBox() {

        HBox search = new HBox(10);
        search.setAlignment(Pos.CENTER_LEFT);
        search.setPrefWidth(410);
        search.setMinWidth(410);
        search.setPrefHeight(48);
        search.setPadding(
                new Insets(0, 16, 0, 16));

        search.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #C9DDE1;" +
                        "-fx-border-radius: 24;" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-width: 1;");

        Label icon = new Label("⌕");
        icon.setFont(
                Font.font("Arial", 26));
        icon.setTextFill(Color.web(SECONDARY));

        Label text = new Label(
                "Search mechanics, invoices, vehicles...");
        text.setFont(
                Font.font("Arial", 15));
        text.setTextFill(Color.web(SECONDARY));

        search.getChildren().addAll(
                icon,
                text);

        return search;
    }

    private StackPane createNotification() {

        StackPane notification = new StackPane();
        notification.setPrefSize(42, 42);

        Label bell = new Label("♧");
        bell.setFont(
                Font.font("Arial", 22));
        bell.setTextFill(Color.web(HEADING));

        Circle dot = new Circle(
                4,
                Color.web("#E52B2B"));

        StackPane.setAlignment(
                dot,
                Pos.TOP_RIGHT);

        StackPane.setMargin(
                dot,
                new Insets(5, 6, 0, 0));

        notification.getChildren().addAll(
                bell,
                dot);

        return notification;
    }

    private VBox createProfile() {

        VBox profile = new VBox(1);
        profile.setAlignment(Pos.CENTER_LEFT);
        profile.setPadding(
                new Insets(7, 14, 7, 10));

        profile.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #C9DDE1;" +
                        "-fx-border-radius: 25;" +
                        "-fx-background-radius: 25;" +
                        "-fx-border-width: 1;");

        Label name = new Label("Aarav Nair");
        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));
        name.setTextFill(Color.web(HEADING));

        Label role = new Label("Customer · Pune");
        role.setFont(
                Font.font("Arial", 12));
        role.setTextFill(Color.web(SECONDARY));

        profile.getChildren().addAll(
                name,
                role);

        return profile;
    }

    // ============================================================
    // SIDEBAR
    // ============================================================

    private VBox createSidebar() {

        VBox sidebar = new VBox(6);

        sidebar.setPrefWidth(280);
        sidebar.setMinWidth(280);
        sidebar.setPadding(
                new Insets(26, 14, 20, 14));

        sidebar.setStyle(
                "-fx-background-color: #CFE4E8;" +
                        "-fx-border-color: #C0D9DE;" +
                        "-fx-border-width: 0 1 0 0;");

        Label overview = sectionTitle("OVERVIEW");
        Label assistance = sectionTitle("ASSISTANCE");
        Label garage = sectionTitle("GARAGE");
        Label account = sectionTitle("ACCOUNT");

        Button dashboardButton =
                sidebarButton("≡", "Dashboard", false);

        Button sosButton =
                sidebarButton("♙", "Emergency SOS", false);

        Button liveMapButton =
                sidebarButton("◇", "Live Map", false);

        Button aiButton =
                sidebarButton("♧", "AI Diagnosis", true);

        Button mechanicsButton =
                sidebarButton("⚒", "Mechanics", false);

        Button towTruckButton =
                sidebarButton("▱", "Tow Truck", false);

        Button costEstimatorButton =
                sidebarButton("▤", "Cost Estimator", false);

        Button myVehiclesButton =
                sidebarButton("▱", "My Vehicles", false);

        Button serviceHistoryButton =
                sidebarButton("◷", "Service History", false);

        Button documentsButton =
                sidebarButton("▤", "Documents", false);

        Button womenSafetyButton =
                sidebarButton("♙", "Women Safety", false);

        Button notificationsButton =
                sidebarButton("♧", "Notifications", false);

        Button settingsButton =
                sidebarButton("⚙", "Settings", false);

        sidebar.getChildren().addAll(
                overview,

                dashboardButton,
                sosButton,
                liveMapButton,

                assistance,

                aiButton,
                mechanicsButton,
                towTruckButton,
                costEstimatorButton,

                garage,

                myVehiclesButton,
                serviceHistoryButton,
                documentsButton,

                account,

                womenSafetyButton,
                notificationsButton,
                settingsButton);

        // =========================
        // NAVIGATION
        // =========================

        dashboardButton.setOnAction(e -> navigateTo(dashboardButton, "Dashboard"));
        sosButton.setOnAction(e -> navigateTo(sosButton, "Emergency SOS"));
        liveMapButton.setOnAction(e -> navigateTo(liveMapButton, "Live Map"));
        aiButton.setOnAction(e -> navigateTo(aiButton, "AI Diagnosis"));
        mechanicsButton.setOnAction(e -> navigateTo(mechanicsButton, "Mechanics"));
        towTruckButton.setOnAction(e -> navigateTo(towTruckButton, "Tow Truck"));
        costEstimatorButton.setOnAction(e -> navigateTo(costEstimatorButton, "Cost Estimator"));
        myVehiclesButton.setOnAction(e -> navigateTo(myVehiclesButton, "My Vehicles"));
        serviceHistoryButton.setOnAction(e -> navigateTo(serviceHistoryButton, "Service History"));
        documentsButton.setOnAction(e -> navigateTo(documentsButton, "Documents"));
        womenSafetyButton.setOnAction(e -> navigateTo(womenSafetyButton, "Women Safety"));
        notificationsButton.setOnAction(e -> navigateTo(notificationsButton, "Notifications"));
        settingsButton.setOnAction(e -> navigateTo(settingsButton, "Settings"));

        return sidebar;
    }

    private Label sectionTitle(String text) {

        Label label = new Label(text);
        label.setPadding(
                new Insets(14, 12, 7, 12));

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        label.setTextFill(Color.web("#617987"));

        return label;
    }

    private Button sidebarButton(
            String icon,
            String text,
            boolean selected) {

        Button button = new Button(
                icon + "    " + text);

        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPrefHeight(46);
        button.setPadding(
                new Insets(0, 14, 0, 12));

        button.setFont(
                Font.font(
                        "Arial",
                        selected
                                ? FontWeight.BOLD
                                : FontWeight.NORMAL,
                        16));

        if (selected) {

            button.setTextFill(Color.WHITE);

            button.setStyle(
                    "-fx-background-color: " + BLUE + ";" +
                            "-fx-background-radius: 25;");

        } else {

            button.setTextFill(Color.web(HEADING));

            button.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-background-radius: 25;");

            button.setOnMouseEntered(e -> button.setStyle(
                    "-fx-background-color: #DCEEF1;" +
                            "-fx-background-radius: 25;"));

            button.setOnMouseExited(e -> button.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-background-radius: 25;"));
        }

        return button;
    }

    // ============================================================
    // NAVIGATION METHODS
    // ============================================================

    private void navigateTo(Button sourceButton, String pageName) {
        Stage stage = (Stage) sourceButton.getScene().getWindow();
        AppNavigator.navigate(stage, pageName);
    }

    private void openDashboard(Button sourceButton) {

        try {

            UserDashboard userDashboard =
                    new UserDashboard();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle(
                    "RoadGuardian - Customer Dashboard"
            );

            stage.setScene(
                    userDashboard.getDashboardScene()
            );

            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openSOSPage(Button sourceButton) {

        try {

            SOSPage sosPage =
                    new SOSPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle(
                    "RoadGuardian - Emergency SOS"
            );

            stage.setScene(
                    sosPage.getSOSScene()
            );

            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openLiveMapPage(Button sourceButton) {

        try {

            LiveMapPage liveMapPage =
                    new LiveMapPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle(
                    "RoadGuardian - Live Tracking"
            );

            stage.setScene(
                    liveMapPage.getLiveMapScene()
            );

            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openAIDiagnosisPage(Button sourceButton) {

        try {

            AIDiagnosisPage aiDiagnosisPage =
                    new AIDiagnosisPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle(
                    "RoadGuardian - AI Diagnosis"
            );

            stage.setScene(
                    aiDiagnosisPage.getAIDiagnosisScene()
            );

            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openMechanicsPage(Button sourceButton) {

        try {

            MechanicsPage mechanicsPage =
                    new MechanicsPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle(
                    "RoadGuardian - Mechanics"
            );

            stage.setScene(
                    mechanicsPage.getMechanicsScene()
            );

            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openTowTruckPage(Button sourceButton) {

        try {

            TowTruckPage towTruckPage =
                    new TowTruckPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle(
                    "RoadGuardian - Tow Truck"
            );

            stage.setScene(
                    towTruckPage.getTowTruckScene()
            );

            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openCostEstimatorPage(Button sourceButton) {

        try {

            CostEstimatorPage costEstimatorPage =
                    new CostEstimatorPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle(
                    "RoadGuardian - Cost Estimator"
            );

            stage.setScene(
                    costEstimatorPage.getCostEstimatorScene()
            );

            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ============================================================
    // PAGE CONTENT
    // ============================================================

    private VBox createPageContent() {

        VBox page = new VBox(24);

        page.setPadding(
                new Insets(30, 34, 40, 34));

        page.setFillWidth(true);
        page.setMinWidth(0);
        page.setMaxWidth(Double.MAX_VALUE);

        VBox.setVgrow(page, Priority.ALWAYS);

        // Heading
        VBox headingBox = new VBox(4);

        Label heading = new Label(
                "AI Vehicle Breakdown Diagnosis");

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32));

        heading.setTextFill(
                Color.web(HEADING));

        Label subtitle = new Label(
                "Tell us what you're seeing, hearing or smelling — we'll narrow it down");

        subtitle.setFont(
                Font.font("Arial", 16));

        subtitle.setTextFill(
                Color.web(SECONDARY));

        headingBox.getChildren().addAll(
                heading,
                subtitle);

        // Main cards
        HBox cards = new HBox(22);
        cards.setFillHeight(true);
        cards.setMinWidth(0);
        cards.setPrefHeight(340);

        VBox symptomCard = createSymptomsCard();

        resultCard.setPadding(
                new Insets(28));

        resultCard.setAlignment(
                Pos.TOP_LEFT);

        resultCard.setMinWidth(0);
        resultCard.setPrefWidth(470);
        resultCard.setMaxWidth(Double.MAX_VALUE);

        resultCard.setStyle(
                "-fx-background-color: " + CARD_BG + ";" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 24;" +
                        "-fx-border-width: 1;");

        HBox.setHgrow(symptomCard, Priority.ALWAYS);
        HBox.setHgrow(resultCard, Priority.ALWAYS);

        cards.getChildren().addAll(
                symptomCard,
                resultCard);

        showEmptyResult();

        page.getChildren().addAll(
                headingBox,
                cards);

        return page;
    }

    // ============================================================
    // SYMPTOMS CARD
    // ============================================================

    private VBox createSymptomsCard() {

        VBox card = new VBox(18);

        card.setPadding(
                new Insets(28));

        card.setMinWidth(0);
        card.setPrefWidth(650);

        card.setStyle(
                "-fx-background-color: " + CARD_BG + ";" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 24;" +
                        "-fx-border-width: 1;");

        Label title = new Label("Select symptoms");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20));

        title.setTextFill(
                Color.web(HEADING));

        Label description = new Label(
                "Choose all that apply. More signals means a sharper result.");

        description.setFont(
                Font.font("Arial", 15));

        description.setTextFill(
                Color.web(SECONDARY));

        FlowPane symptoms = new FlowPane();
        symptoms.setHgap(10);
        symptoms.setVgap(10);
        symptoms.setPrefWrapLength(700);

        String[] symptomNames = {
                "Engine won't start",
                "Battery light ON",
                "Smoke from bonnet",
                "Fuel empty",
                "Overheating",
                "Flat tyre",
                "Brake noise",
                "Strange vibration",
                "AC not cooling",
                "Warning lights flashing"
        };

        for (String symptom : symptomNames) {

            Button button = createSymptomButton(
                    symptom);

            symptoms.getChildren().add(button);
            symptomButtons.add(button);
        }

        HBox actions = new HBox(18);
        actions.setAlignment(Pos.CENTER_LEFT);

        diagnoseButton.setPrefWidth(174);
        diagnoseButton.setPrefHeight(44);

        diagnoseButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15));

        diagnoseButton.setTextFill(Color.WHITE);

        diagnoseButton.setStyle(
                "-fx-background-color: #8BADEF;" +
                        "-fx-background-radius: 24;");

        resetButton.setPrefHeight(44);
        resetButton.setPrefWidth(90);

        resetButton.setFont(
                Font.font("Arial", 15));

        resetButton.setTextFill(
                Color.web(HEADING));

        resetButton.setStyle(
                "-fx-background-color: transparent;");

        diagnoseButton.setOnAction(
                e -> diagnose());

        resetButton.setOnAction(
                e -> reset());

        actions.getChildren().addAll(
                diagnoseButton,
                resetButton);

        card.getChildren().addAll(
                title,
                description,
                symptoms,
                actions);

        VBox.setVgrow(symptoms, Priority.ALWAYS);

        return card;
    }

    // ============================================================
    // SYMPTOM BUTTON
    // ============================================================

    private Button createSymptomButton(
            String text) {

        Button button = new Button(text);

        button.setPrefHeight(42);
        button.setPadding(
                new Insets(0, 17, 0, 17));

        button.setFont(
                Font.font("Arial", 15));

        button.setTextFill(
                Color.web(HEADING));

        button.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #D9E2E7;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 22;" +
                        "-fx-background-radius: 22;");

        button.setOnAction(
                e -> toggleSymptom(button));

        return button;
    }

    private void toggleSymptom(
            Button button) {

        Object selected = button.getProperties().get("selected");

        boolean isSelected = selected instanceof Boolean
                && (Boolean) selected;

        if (!isSelected) {

            button.getProperties().put(
                    "selected",
                    true);

            button.setTextFill(Color.WHITE);

            button.setStyle(
                    "-fx-background-color: " + BLUE + ";" +
                            "-fx-border-color: " + BLUE + ";" +
                            "-fx-border-width: 1;" +
                            "-fx-border-radius: 22;" +
                            "-fx-background-radius: 22;");

        } else {

            button.getProperties().put(
                    "selected",
                    false);

            button.setTextFill(
                    Color.web(HEADING));

            button.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: #D9E2E7;" +
                            "-fx-border-width: 1;" +
                            "-fx-border-radius: 22;" +
                            "-fx-background-radius: 22;");
        }

        updateDiagnoseButton();
    }

    private void updateDiagnoseButton() {

        boolean selected = false;

        for (Button button : symptomButtons) {

            Object value = button.getProperties().get("selected");

            if (value instanceof Boolean
                    && (Boolean) value) {

                selected = true;
                break;
            }
        }

        if (selected) {

            diagnoseButton.setStyle(
                    "-fx-background-color: " + BLUE + ";" +
                            "-fx-background-radius: 24;");

        } else {

            diagnoseButton.setStyle(
                    "-fx-background-color: #8BADEF;" +
                            "-fx-background-radius: 24;");
        }
    }

    // ============================================================
    // EMPTY RESULT
    // ============================================================

    private void showEmptyResult() {

        resultCard.getChildren().clear();
        resultCard.setAlignment(Pos.CENTER);

        StackPane iconBox = new StackPane();
        Circle iconCircle = new Circle(
                31,
                Color.web(LIGHT_BLUE));

        Label icon = new Label("✣");

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27));

        icon.setTextFill(
                Color.web(BLUE));

        iconBox.getChildren().addAll(
                iconCircle,
                icon);

        Label title = new Label(
                "No diagnosis yet");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17));

        title.setTextFill(
                Color.web(HEADING));

        Label text = new Label(
                "Select at least one symptom and tap Diagnose to see the\n" +
                        "probable fault and cost band.");

        text.setFont(
                Font.font("Arial", 15));

        text.setTextFill(
                Color.web(SECONDARY));

        text.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER);

        text.setAlignment(Pos.CENTER);

        resultCard.getChildren().addAll(
                iconBox,
                title,
                text);
    }

    // ============================================================
    // DIAGNOSIS
    // ============================================================

    private void diagnose() {

        boolean selected = false;

        for (Button button : symptomButtons) {

            Object value = button.getProperties().get("selected");

            if (value instanceof Boolean
                    && (Boolean) value) {

                selected = true;
                break;
            }
        }

        if (!selected) {
            showEmptyResult();
            return;
        }

        resultCard.getChildren().clear();
        resultCard.setAlignment(
                Pos.TOP_LEFT);

        Label title = new Label(
                "Probable diagnosis");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20));

        title.setTextFill(
                Color.web(HEADING));

        Label fault = new Label(
                "Electrical / battery-related issue");

        fault.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));

        fault.setTextFill(
                Color.web(BLUE));

        Label description = new Label(
                "Based on the selected symptoms, the battery, " +
                        "starter circuit or charging system may need inspection.");

        description.setWrapText(true);
        description.setFont(
                Font.font("Arial", 14));
        description.setTextFill(
                Color.web(SECONDARY));

        VBox costBox = new VBox(4);

        costBox.setPadding(
                new Insets(14));

        costBox.setStyle(
                "-fx-background-color: #E6F7EE;" +
                        "-fx-background-radius: 14;");

        Label costTitle = new Label(
                "Estimated cost band");

        costTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));

        costTitle.setTextFill(
                Color.web(SECONDARY));

        Label cost = new Label(
                "₹800 – ₹3,500");

        cost.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));

        cost.setTextFill(
                Color.web(SUCCESS));

        costBox.getChildren().addAll(
                costTitle,
                cost);

        resultCard.getChildren().addAll(
                title,
                fault,
                description,
                costBox);
    }

    // ============================================================
    // RESET
    // ============================================================

    private void reset() {

        for (Button button : symptomButtons) {

            button.getProperties().put(
                    "selected",
                    false);

            button.setTextFill(
                    Color.web(HEADING));

            button.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: #D9E2E7;" +
                            "-fx-border-width: 1;" +
                            "-fx-border-radius: 22;" +
                            "-fx-background-radius: 22;");
        }

        updateDiagnoseButton();
        showEmptyResult();
    }
}

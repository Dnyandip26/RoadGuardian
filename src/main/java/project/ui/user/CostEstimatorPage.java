package project.ui.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Cursor;
import javafx.stage.Stage;

public class CostEstimatorPage {

    private static final String BG = "#FFFFFF";
    private static final String CARD = "#F7FAFC";
    private static final String BLUE = "#2864E8";
    private static final String DARK = "#142033";
    private static final String GREY = "#667085";
    private static final String BORDER = "#DCE4EA";
    private static final String LIGHT_BLUE = "#EAF2FF";
    private static final String ORANGE = "#FF7A18";

    private Scene scene;

    private ComboBox<String> vehicleBox;
    private ComboBox<String> problemBox;

    private Label costLabel;
    private Label repairTimeLabel;
    private Label partsLabel;

    public CostEstimatorPage() {
        buildUI();
    }

    public Scene getCostEstimatorScene() {
        return scene;
    }

    private void buildUI() {

        // ================= HEADER =================

        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 28, 15, 28));
        header.setPrefHeight(78);
        header.setBackground(bg(BG, CornerRadii.EMPTY));

        Circle logoCircle = new Circle(21, Color.web(BLUE));

        Label check = new Label("✓");
        check.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        check.setTextFill(Color.WHITE);

        HBox logo = new HBox(check);
        logo.setAlignment(Pos.CENTER);
        logo.setPrefSize(42, 42);
        logo.setBackground(bg(BLUE, new CornerRadii(25)));

        Label road = new Label("Road");
        road.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        road.setTextFill(Color.web(DARK));

        Label guardian = new Label("Guardian");
        guardian.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        guardian.setTextFill(Color.web(BLUE));

        HBox brand = new HBox(road, guardian);
        brand.setAlignment(Pos.CENTER_LEFT);

        HBox brandArea = new HBox(10, logo, brand);
        brandArea.setAlignment(Pos.CENTER_LEFT);

        Region headerSpace = new Region();
        HBox.setHgrow(headerSpace, Priority.ALWAYS);

        HBox search = new HBox(10);
        search.setAlignment(Pos.CENTER_LEFT);
        search.setPrefWidth(390);
        search.setPrefHeight(48);
        search.setPadding(new Insets(0, 18, 0, 18));
        search.setBackground(bg(WHITE(), new CornerRadii(25)));
        search.setBorder(border(BORDER, 25, 1));

        Label searchIcon = new Label("⌕");
        searchIcon.setFont(Font.font("Arial", 24));
        searchIcon.setTextFill(Color.web(GREY));

        Label searchText = new Label(
                "Search mechanics, invoices, vehicles..."
        );
        searchText.setFont(Font.font("Arial", 15));
        searchText.setTextFill(Color.web(GREY));

        search.getChildren().addAll(searchIcon, searchText);

        Label notification = new Label("♧");
        notification.setFont(Font.font("Arial", 23));
        notification.setTextFill(Color.web(DARK));

        Circle notificationDot = new Circle(4, Color.web("#EF233C"));

        javafx.scene.layout.StackPane notificationBox =
                new javafx.scene.layout.StackPane(
                        notification,
                        notificationDot
                );

        javafx.scene.layout.StackPane.setAlignment(
                notificationDot,
                Pos.TOP_RIGHT
        );

        Circle avatarCircle = new Circle(21, Color.web("#E7EEFF"));

        Label avatarText = new Label("AN");
        avatarText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        avatarText.setTextFill(Color.web(BLUE));

        javafx.scene.layout.StackPane avatar =
                new javafx.scene.layout.StackPane(
                        avatarCircle,
                        avatarText
                );

        Label userName = new Label("Aarav Nair");
        userName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        userName.setTextFill(Color.web(DARK));

        Label userRole = new Label("Customer · Pune");
        userRole.setFont(Font.font("Arial", 12));
        userRole.setTextFill(Color.web(GREY));

        VBox userDetails = new VBox(2, userName, userRole);

        HBox profile = new HBox(10, avatar, userDetails);
        profile.setAlignment(Pos.CENTER_LEFT);
        profile.setPadding(new Insets(6, 14, 6, 8));
        profile.setBackground(bg(WHITE(), new CornerRadii(25)));
        profile.setBorder(border(BORDER, 25, 1));

        header.getChildren().addAll(
                brandArea,
                headerSpace,
                search,
                notificationBox,
                profile
        );

        // ================= SIDEBAR =================

        VBox sidebar = new VBox(7);
        sidebar.setPadding(new Insets(22, 12, 20, 12));
        sidebar.setPrefWidth(275);
        sidebar.setBackground(bg(WHITE(), CornerRadii.EMPTY));

        sidebar.getChildren().add(section("OVERVIEW"));

        Button dashboardButton =
                sideButton("▦", "Dashboard", false);

        Button sosButton =
                sideButton("♙", "Emergency SOS", false);

        Button liveMapButton =
                sideButton("◇", "Live Map", false);

        sidebar.getChildren().addAll(
                dashboardButton,
                sosButton,
                liveMapButton
        );

        sidebar.getChildren().add(section("ASSISTANCE"));

        Button aiButton =
                sideButton("♧", "AI Diagnosis", false);

        Button mechanicsButton =
                sideButton("⚒", "Mechanics", false);

        Button towTruckButton =
                sideButton("▱", "Tow Truck", false);

        Button costEstimatorButton =
                sideButton("▤", "Cost Estimator", true);

        sidebar.getChildren().addAll(
                aiButton,
                mechanicsButton,
                towTruckButton,
                costEstimatorButton
        );

        sidebar.getChildren().add(section("GARAGE"));

        Button myVehiclesButton =
                sideButton("▱", "My Vehicles", false);

        Button serviceHistoryButton =
                sideButton("◷", "Service History", false);

        Button documentsButton =
                sideButton("▤", "Documents", false);

        sidebar.getChildren().addAll(
                myVehiclesButton,
                serviceHistoryButton,
                documentsButton
        );

        sidebar.getChildren().add(section("ACCOUNT"));

        Button womenSafetyButton =
                sideButton("♙", "Women Safety", false);

        Button notificationsButton =
                sideButton("♧", "Notifications", false);

        Button settingsButton =
                sideButton("⚙", "Settings", false);

        sidebar.getChildren().addAll(
                womenSafetyButton,
                notificationsButton,
                settingsButton
        );

        // ================= NAVIGATION =================

        dashboardButton.setOnMouseClicked(
                e -> openDashboard(dashboardButton)
        );

        sosButton.setOnMouseClicked(
                e -> openSOSPage(sosButton)
        );

        liveMapButton.setOnMouseClicked(
                e -> openLiveMapPage(liveMapButton)
        );

        aiButton.setOnMouseClicked(
                e -> openAIDiagnosisPage(aiButton)
        );

        mechanicsButton.setOnMouseClicked(
                e -> openMechanicsPage(mechanicsButton)
        );

        towTruckButton.setOnMouseClicked(
                e -> openTowTruckPage(towTruckButton)
        );

        costEstimatorButton.setOnMouseClicked(
                e -> openCostEstimatorPage(costEstimatorButton)
        );

        ScrollPane sidebarScroll = new ScrollPane(sidebar);
        sidebarScroll.setPrefWidth(290);
        sidebarScroll.setMinWidth(290);
        sidebarScroll.setFitToWidth(true);
        sidebarScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sidebarScroll.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: transparent;"
        );

        // ================= MAIN CONTENT =================

        VBox content = new VBox(22);
        content.setPadding(new Insets(28, 30, 35, 30));
        content.setBackground(bg(WHITE(), CornerRadii.EMPTY));

        Label title = new Label("Repair Cost Estimator");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 34));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label(
                "Real pricing bands sourced from 18,400 partner garages"
        );
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextFill(Color.web(GREY));

        VBox heading = new VBox(4, title, subtitle);

        HBox main = new HBox(22);
        main.setAlignment(Pos.TOP_LEFT);

        VBox formCard = createFormCard();
        HBox.setHgrow(formCard, Priority.ALWAYS);

        VBox rightColumn = new VBox(20);
        rightColumn.setPrefWidth(800);
        HBox.setHgrow(rightColumn, Priority.ALWAYS);

        VBox estimateCard = createEstimateCard();
        VBox centersCard = createCentersCard();

        rightColumn.getChildren().addAll(
                estimateCard,
                centersCard
        );

        main.getChildren().addAll(
                formCard,
                rightColumn
        );

        content.getChildren().addAll(
                heading,
                main
        );

        HBox body = new HBox(
                sidebarScroll,
                content
        );

        HBox.setHgrow(content, Priority.ALWAYS);

        VBox root = new VBox(
                header,
                body
        );

        VBox.setVgrow(body, Priority.ALWAYS);

        scene = new Scene(root, 1500, 900);
    }

    // =========================
    // NAVIGATION METHODS
    // =========================

    private void openDashboard(Button sourceButton) {
        try {
            UserDashboard page = new UserDashboard();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle("RoadGuardian - Customer Dashboard");
            stage.setScene(page.getDashboardScene());
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            showNavigationError(ex);
        }
    }

    private void openSOSPage(Button sourceButton) {
        try {
            SOSPage page = new SOSPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle("RoadGuardian - Emergency SOS");
            stage.setScene(page.getSOSScene());
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            showNavigationError(ex);
        }
    }

    private void openLiveMapPage(Button sourceButton) {
        try {
            LiveMapPage page = new LiveMapPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle("RoadGuardian - Live Tracking");
            stage.setScene(page.getLiveMapScene());
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            showNavigationError(ex);
        }
    }

    private void openAIDiagnosisPage(Button sourceButton) {
        try {
            AIDiagnosisPage page = new AIDiagnosisPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle("RoadGuardian - AI Diagnosis");
            stage.setScene(page.getAIDiagnosisScene());
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            showNavigationError(ex);
        }
    }

    private void openMechanicsPage(Button sourceButton) {
        try {
            MechanicsPage page = new MechanicsPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle("RoadGuardian - Mechanics");
            stage.setScene(page.getMechanicsScene());
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            showNavigationError(ex);
        }
    }

    private void openTowTruckPage(Button sourceButton) {
        try {
            TowTruckPage page = new TowTruckPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle("RoadGuardian - Tow Truck");
            stage.setScene(page.getTowTruckScene());
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            showNavigationError(ex);
        }
    }

    private void openCostEstimatorPage(Button sourceButton) {
        try {
            CostEstimatorPage page = new CostEstimatorPage();

            Stage stage =
                    (Stage) sourceButton
                            .getScene()
                            .getWindow();

            stage.setTitle("RoadGuardian - Cost Estimator");
            stage.setScene(page.getCostEstimatorScene());
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            showNavigationError(ex);
        }
    }

    private void showNavigationError(Exception ex) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.ERROR
        );
        alert.setTitle("RoadGuardian - Navigation Error");
        alert.setHeaderText("Page could not be opened");
        alert.setContentText(
                ex.getClass().getSimpleName() + ": " +
                (ex.getMessage() == null ? "Unknown error" : ex.getMessage())
        );
        alert.showAndWait();
    }

    // ================= FORM CARD =================

    private VBox createFormCard() {

        VBox card = new VBox(22);
        card.setPadding(new Insets(30));
        card.setPrefWidth(520);
        card.setMinWidth(450);
        card.setPrefHeight(650);

        card.setBackground(
                bg(CARD, new CornerRadii(24))
        );

        card.setBorder(
                border(BORDER, 24, 1)
        );

        Label title = new Label("Tell us about the job");
        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );
        title.setTextFill(Color.web(DARK));

        Label vehicleLabel = fieldLabel("Vehicle type");

        vehicleBox = new ComboBox<>();
        vehicleBox.getItems().addAll(
                "Sedan",
                "Hatchback",
                "SUV",
                "MUV",
                "Pickup Truck",
                "Luxury Car"
        );
        vehicleBox.setValue("Sedan");
        styleCombo(vehicleBox);

        Label problemLabel = fieldLabel("Problem");

        problemBox = new ComboBox<>();
        problemBox.getItems().addAll(
                "Battery replacement",
                "Engine repair",
                "Brake repair",
                "Flat tyre",
                "AC repair",
                "Clutch repair",
                "Electrical issue"
        );
        problemBox.setValue("Battery replacement");
        styleCombo(problemBox);

        Button recalculate = new Button(
                "▤    Recalculate estimate"
        );

        recalculate.setMaxWidth(
                Double.MAX_VALUE
        );
        recalculate.setPrefHeight(46);
        recalculate.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );
        recalculate.setTextFill(Color.WHITE);
        recalculate.setBackground(
                bg(BLUE, new CornerRadii(24))
        );
        recalculate.setCursor(Cursor.HAND);

        recalculate.setOnAction(
                e -> calculateEstimate()
        );

        card.getChildren().addAll(
                title,
                vehicleLabel,
                vehicleBox,
                problemLabel,
                problemBox,
                recalculate
        );

        return card;
    }

    // ================= ESTIMATE =================

    private VBox createEstimateCard() {

        VBox card = new VBox(20);
        card.setPadding(new Insets(28));
        card.setPrefHeight(275);

        card.setBackground(
                bg(CARD, new CornerRadii(24))
        );

        card.setBorder(
                border(BORDER, 24, 1)
        );

        Label smallTitle = new Label(
                "ESTIMATED COST RANGE"
        );
        smallTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );
        smallTitle.setTextFill(Color.web(GREY));

        costLabel = new Label(
                "₹5,200 – ₹9,500"
        );
        costLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        40
                )
        );
        costLabel.setTextFill(Color.web(DARK));

        Region progressBackground = new Region();
        progressBackground.setPrefHeight(8);
        progressBackground.setMaxWidth(Double.MAX_VALUE);
        progressBackground.setBackground(
                bg("#EDF1F5", new CornerRadii(8))
        );

        Region progress = new Region();
        progress.setPrefHeight(8);
        progress.setPrefWidth(435);
        progress.setBackground(
                bg("#4B8CF5", new CornerRadii(8))
        );

        javafx.scene.layout.StackPane progressBar =
                new javafx.scene.layout.StackPane(
                        progressBackground,
                        progress
                );

        javafx.scene.layout.StackPane.setAlignment(
                progress,
                Pos.CENTER_LEFT
        );

        HBox details = new HBox(16);

        repairTimeLabel = new Label("45 min");
        styleDetailValueLabel(repairTimeLabel);

        partsLabel = new Label(
                "Battery 12V 45Ah, Terminal clamps"
        );
        styleDetailValueLabel(partsLabel);

        VBox repairTime = detailBox(
                "◷",
                "REPAIR TIME",
                repairTimeLabel,
                BLUE
        );

        VBox spareParts = detailBox(
                "□",
                "COMMON SPARE PARTS",
                partsLabel,
                ORANGE
        );

        HBox.setHgrow(repairTime, Priority.ALWAYS);
        HBox.setHgrow(spareParts, Priority.ALWAYS);

        details.getChildren().addAll(
                repairTime,
                spareParts
        );

        card.getChildren().addAll(
                smallTitle,
                costLabel,
                progressBar,
                details
        );

        return card;
    }

    private VBox detailBox(
            String icon,
            String title,
            Label valueLabel,
            String iconColor
    ) {

        VBox box = new VBox(6);
        box.setPadding(new Insets(15));
        box.setPrefHeight(100);

        box.setBackground(
                bg(WHITE(), new CornerRadii(22))
        );

        box.setBorder(
                border(BORDER, 22, 1)
        );

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("Arial", 18));
        iconLabel.setTextFill(Color.web(iconColor));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 12));
        titleLabel.setTextFill(Color.web(GREY));

        box.getChildren().addAll(
                iconLabel,
                titleLabel,
                valueLabel
        );

        return box;
    }

    private void styleDetailValueLabel(Label label) {
        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );
        label.setTextFill(Color.web(DARK));
        label.setWrapText(true);
    }

    // ================= SERVICE CENTERS =================

    private VBox createCentersCard() {

        VBox card = new VBox(15);
        card.setPadding(new Insets(28));

        card.setBackground(
                bg(CARD, new CornerRadii(24))
        );

        card.setBorder(
                border(BORDER, 24, 1)
        );

        Label title = new Label(
                "▥  Nearby service centers"
        );
        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );
        title.setTextFill(Color.web(DARK));

        card.getChildren().add(title);

        card.getChildren().add(
                serviceCenter(
                        "SpeedFix Auto Care",
                        "1.2 km",
                        "4.9"
                )
        );

        card.getChildren().add(
                serviceCenter(
                        "Highway Motors",
                        "2.4 km",
                        "4.7"
                )
        );

        card.getChildren().add(
                serviceCenter(
                        "ElectroDrive Garage",
                        "3.1 km",
                        "4.8"
                )
        );

        return card;
    }

    private HBox serviceCenter(
            String name,
            String distance,
            String rating
    ) {

        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(
                new Insets(13, 14, 13, 14)
        );
        row.setPrefHeight(72);

        row.setBackground(
                bg(WHITE(), new CornerRadii(22))
        );

        row.setBorder(
                border(BORDER, 22, 1)
        );

        Label nameLabel = new Label(name);
        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );
        nameLabel.setTextFill(Color.web(DARK));

        Label info = new Label(
                "⌖ " + distance + "     " +
                "★ " + rating
        );
        info.setFont(
                Font.font("Arial", 13)
        );
        info.setTextFill(Color.web(GREY));

        VBox details = new VBox(
                5,
                nameLabel,
                info
        );

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        Button book = new Button("Book slot");
        book.setPrefSize(88, 38);
        book.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );
        book.setTextFill(Color.web(DARK));
        book.setBackground(
                bg(WHITE(), new CornerRadii(20))
        );
        book.setBorder(
                border(BORDER, 20, 1)
        );
        book.setCursor(Cursor.HAND);

        book.setOnMouseEntered(e ->
                book.setBackground(
                        bg(
                                LIGHT_BLUE,
                                new CornerRadii(20)
                        )
                )
        );

        book.setOnMouseExited(e ->
                book.setBackground(
                        bg(
                                WHITE(),
                                new CornerRadii(20)
                        )
                )
        );

        row.getChildren().addAll(
                details,
                space,
                book
        );

        return row;
    }

    // ================= CALCULATION =================

    private void calculateEstimate() {

        String vehicle = vehicleBox.getValue();
        String problem = problemBox.getValue();

        if (vehicle == null || problem == null) {
            return;
        }

        int min = 5200;
        int max = 9500;
        String time = "45 min";
        String parts =
                "Battery 12V 45Ah, Terminal clamps";

        if (problem.equals("Engine repair")) {
            min = 8500;
            max = 18000;
            time = "2–4 hrs";
            parts = "Spark plugs, filters, engine oil";
        }
        else if (problem.equals("Brake repair")) {
            min = 3200;
            max = 7800;
            time = "1–2 hrs";
            parts = "Brake pads, discs, brake fluid";
        }
        else if (problem.equals("Flat tyre")) {
            min = 700;
            max = 2200;
            time = "20–40 min";
            parts = "Tyre, valve, puncture kit";
        }
        else if (problem.equals("AC repair")) {
            min = 2500;
            max = 7000;
            time = "1–2 hrs";
            parts = "AC gas, filter, compressor parts";
        }
        else if (problem.equals("Clutch repair")) {
            min = 6500;
            max = 14000;
            time = "3–5 hrs";
            parts = "Clutch plate, pressure plate";
        }
        else if (problem.equals("Electrical issue")) {
            min = 1800;
            max = 5500;
            time = "1–2 hrs";
            parts = "Fuse, wiring, battery terminals";
        }

        // Slight vehicle adjustment.
        if (vehicle.equals("SUV") ||
            vehicle.equals("MUV")) {

            min += 800;
            max += 1500;
        }

        costLabel.setText(
                "₹" + format(min) +
                " – ₹" + format(max)
        );

        if (repairTimeLabel != null) {
            repairTimeLabel.setText(time);
        }

        if (partsLabel != null) {
            partsLabel.setText(parts);
        }
    }

    private String format(int value) {
        return String.format(
                java.util.Locale.US,
                "%,d",
                value
        );
    }

    /*
     * These methods locate the current labels from the estimate card.
     * They keep the UI code simple and avoid exposing extra controls.
     */
    // ================= SIDEBAR =================

    private Button sideButton(
            String icon,
            String text,
            boolean selected
    ) {

        Label iconLabel = new Label(icon);
        iconLabel.setFont(
                Font.font("Arial", 16)
        );

        Label textLabel = new Label(text);
        textLabel.setFont(
                Font.font(
                        "Arial",
                        selected
                                ? FontWeight.BOLD
                                : FontWeight.NORMAL,
                        16
                )
        );

        HBox row = new HBox(
                14,
                iconLabel,
                textLabel
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        // Let the Button receive the mouse click, not its graphic children.
        iconLabel.setMouseTransparent(true);
        textLabel.setMouseTransparent(true);
        row.setMouseTransparent(true);

        Button button = new Button();
        button.setGraphic(row);
        button.setMaxWidth(
                Double.MAX_VALUE
        );
        button.setPrefHeight(47);
        button.setAlignment(
                Pos.CENTER_LEFT
        );
        button.setPadding(
                new Insets(0, 14, 0, 14)
        );
        button.setCursor(Cursor.HAND);

        if (selected) {

            button.setBackground(
                    bg(
                            BLUE,
                            new CornerRadii(25)
                    )
            );

            iconLabel.setTextFill(
                    Color.WHITE
            );

            textLabel.setTextFill(
                    Color.WHITE
            );

        } else {

            button.setBackground(
                    bg(
                            Color.TRANSPARENT,
                            new CornerRadii(25)
                    )
            );

            iconLabel.setTextFill(
                    Color.web(DARK)
            );

            textLabel.setTextFill(
                    Color.web(DARK)
            );

            button.setOnMouseEntered(e ->
                    button.setBackground(
                            bg(
                                    "#EEF4FF",
                                    new CornerRadii(25)
                            )
                    )
            );

            button.setOnMouseExited(e ->
                    button.setBackground(
                            bg(
                                    Color.TRANSPARENT,
                                    new CornerRadii(25)
                            )
                    )
            );
        }

        return button;
    }

    private Label section(String text) {

        Label label = new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        label.setTextFill(
                Color.web(GREY)
        );

        label.setPadding(
                new Insets(12, 14, 5, 14)
        );

        return label;
    }

    private Label fieldLabel(String text) {

        Label label = new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        16
                )
        );

        label.setTextFill(
                Color.web(DARK)
        );

        return label;
    }

    private void styleCombo(
            ComboBox<String> combo
    ) {

        combo.setPrefHeight(50);
        combo.setMaxWidth(
                Double.MAX_VALUE
        );

        combo.setStyle(
                "-fx-background-color: #F8FAFC;" +
                "-fx-background-radius: 25;" +
                "-fx-border-color: #DCE4EA;" +
                "-fx-border-radius: 25;" +
                "-fx-font-size: 15px;" +
                "-fx-padding: 0 12 0 12;"
        );
    }

    // ================= HELPERS =================

    private Background bg(
            String color,
            CornerRadii radius
    ) {

        return new Background(
                new BackgroundFill(
                        Color.web(color),
                        radius,
                        Insets.EMPTY
                )
        );
    }

    private Background bg(
            Color color,
            CornerRadii radius
    ) {

        return new Background(
                new BackgroundFill(
                        color,
                        radius,
                        Insets.EMPTY
                )
        );
    }

    private Border border(
            String color,
            double radius,
            double width
    ) {

        return new Border(
                new BorderStroke(
                        Color.web(color),
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(radius),
                        new BorderWidths(width)
                )
        );
    }

    private Color WHITE() {
        return Color.WHITE;
    }
}

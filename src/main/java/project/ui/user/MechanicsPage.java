package project.ui.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

public class MechanicsPage {

    // =========================
    // COLORS - SAME HOMEPAGE THEME
    // =========================
    private static final String BG = "#DDF5F7";
    private static final String WHITE = "#FFFFFF";
    private static final String CARD = "#F1FAFB";
    private static final String BLUE = "#2864E8";
    private static final String DARK = "#142033";
    private static final String GREY = "#667085";
    private static final String BORDER = "#C9E2E6";
    private static final String GREEN = "#12B76A";
    private static final String ORANGE = "#FF7A18";

    private Scene scene;

    public MechanicsPage() {
        createPage();
    }

    public Scene getMechanicsScene() {
        return scene;
    }

    private void createPage() {

        // =========================
        // HEADER
        // =========================
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(16, 28, 16, 28));
        header.setSpacing(20);
        header.setPrefHeight(82);
        header.setBackground(new Background(
                new BackgroundFill(Color.web(WHITE), CornerRadii.EMPTY, Insets.EMPTY)));
        header.setBorder(new Border(new BorderStroke(
                Color.web(BORDER),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));

        Circle logoCircle = new Circle(21, Color.web(BLUE));

        Label check = new Label("✓");
        check.setTextFill(Color.WHITE);
        check.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        StackPane logo = new StackPane(logoCircle, check);

        Label road = new Label("Road");
        road.setTextFill(Color.web(DARK));
        road.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Label guardian = new Label("Guardian");
        guardian.setTextFill(Color.web(BLUE));
        guardian.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        HBox logoText = new HBox(road, guardian);
        logoText.setAlignment(Pos.CENTER_LEFT);

        HBox brand = new HBox(10, logo, logoText);
        brand.setAlignment(Pos.CENTER_LEFT);

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        HBox searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setPadding(new Insets(0, 18, 0, 18));
        searchBox.setPrefWidth(390);
        searchBox.setPrefHeight(48);
        searchBox.setBackground(new Background(
                new BackgroundFill(Color.web(WHITE), new CornerRadii(25), Insets.EMPTY)));
        searchBox.setBorder(new Border(new BorderStroke(
                Color.web(BORDER),
                BorderStrokeStyle.SOLID,
                new CornerRadii(25),
                BorderWidths.DEFAULT)));

        Label searchIcon = new Label("⌕");
        searchIcon.setFont(Font.font(25));
        searchIcon.setTextFill(Color.web(GREY));

        Label searchText = new Label("Search mechanics, invoices, vehicles...");
        searchText.setFont(Font.font("Arial", 15));
        searchText.setTextFill(Color.web(GREY));

        searchBox.getChildren().addAll(searchIcon, searchText);

        Label notification = new Label("♧");
        notification.setFont(Font.font("Arial", 24));
        notification.setTextFill(Color.web(DARK));

        StackPane notificationBox = new StackPane(notification);
        notificationBox.setPrefWidth(38);

        Circle dot = new Circle(4, Color.web("#EF233C"));
        StackPane.setAlignment(dot, Pos.TOP_RIGHT);
        notificationBox.getChildren().add(dot);

        Circle avatarCircle = new Circle(21, Color.web("#E5EEFF"));
        Label avatarText = new Label("AN");
        avatarText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        avatarText.setTextFill(Color.web(BLUE));

        StackPane avatar = new StackPane(avatarCircle, avatarText);

        Label userName = new Label("Aarav Nair");
        userName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        userName.setTextFill(Color.web(DARK));

        Label userType = new Label("Customer · Pune");
        userType.setFont(Font.font("Arial", 12));
        userType.setTextFill(Color.web(GREY));

        VBox userInfo = new VBox(2, userName, userType);

        HBox profile = new HBox(10, avatar, userInfo);
        profile.setAlignment(Pos.CENTER_LEFT);
        profile.setPadding(new Insets(7, 14, 7, 8));
        profile.setBackground(new Background(
                new BackgroundFill(Color.web(WHITE), new CornerRadii(25), Insets.EMPTY)));
        profile.setBorder(new Border(new BorderStroke(
                Color.web(BORDER),
                BorderStrokeStyle.SOLID,
                new CornerRadii(25),
                BorderWidths.DEFAULT)));

        header.getChildren().addAll(
                brand,
                headerSpacer,
                searchBox,
                notificationBox,
                profile);

        // =========================
        // SIDEBAR
        // =========================
        VBox sidebar = new VBox(8);
        sidebar.setPadding(new Insets(25, 12, 20, 12));
        sidebar.setPrefWidth(275);
        sidebar.setMinWidth(275);
        sidebar.setBackground(new Background(
                new BackgroundFill(Color.web(WHITE), CornerRadii.EMPTY, Insets.EMPTY)));

        Label overview = sectionLabel("OVERVIEW");
        sidebar.getChildren().add(overview);

        Button dashboard = createSidebarButton("▦", "Dashboard", false);
        Button sos = createSidebarButton("♙", "Emergency SOS", false);
        Button liveMap = createSidebarButton("◇", "Live Map", false);

        sidebar.getChildren().addAll(dashboard, sos, liveMap);

        sidebar.getChildren().add(sectionLabel("ASSISTANCE"));

        Button ai = createSidebarButton("♧", "AI Diagnosis", false);
        Button mechanics = createSidebarButton("⚒", "Mechanics", true);
        Button tow = createSidebarButton("▱", "Tow Truck", false);
        Button cost = createSidebarButton("▤", "Cost Estimator", false);

        sidebar.getChildren().addAll(ai, mechanics, tow, cost);

        sidebar.getChildren().add(sectionLabel("GARAGE"));

        Button vehicles = createSidebarButton("▱", "My Vehicles", false);
        Button history = createSidebarButton("◷", "Service History", false);
        Button documents = createSidebarButton("▤", "Documents", false);

        sidebar.getChildren().addAll(vehicles, history, documents);

        sidebar.getChildren().add(sectionLabel("ACCOUNT"));

        Button women = createSidebarButton("♙", "Women Safety", false);
        Button notifications = createSidebarButton("♧", "Notifications", false);
        Button settings = createSidebarButton("⚙", "Settings", false);

        sidebar.getChildren().addAll(women, notifications, settings);

        // =========================
        // NAVIGATION
        // =========================

        dashboard.setOnAction(
                e -> openDashboard(dashboard));

        sos.setOnAction(
                e -> openSOSPage(sos));

        liveMap.setOnAction(
                e -> openLiveMapPage(liveMap));

        ai.setOnAction(
                e -> openAIDiagnosisPage(ai));

        mechanics.setOnAction(
                e -> openMechanicsPage(mechanics));

        tow.setOnAction(
                e -> openTowTruckPage(tow));

        cost.setOnAction(
                e -> openCostEstimatorPage(cost));

        ScrollPane sidebarScroll = new ScrollPane(sidebar);
        sidebarScroll.setFitToWidth(true);
        sidebarScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sidebarScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sidebarScroll.setPrefWidth(290);
        sidebarScroll.setMinWidth(290);
        sidebarScroll.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background: white;" +
                        "-fx-border-color: transparent;");

        // =========================
        // PAGE CONTENT
        // =========================
        VBox content = new VBox(18);
        content.setPadding(new Insets(28, 30, 35, 30));
        content.setBackground(new Background(
                new BackgroundFill(Color.web(BG), CornerRadii.EMPTY, Insets.EMPTY)));

        Label title = new Label("Smart Mechanic Dispatch");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 34));
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label(
                "4 verified mechanics within 5 km of NH-48, Exit 12B");
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextFill(Color.web(GREY));

        VBox heading = new VBox(4, title, subtitle);

        // =========================
        // MAIN AREA
        // =========================
        HBox mainArea = new HBox(20);
        mainArea.setAlignment(Pos.TOP_LEFT);

        // MAP BOX
        VBox mapBox = createMapBox();
        HBox.setHgrow(mapBox, Priority.ALWAYS);

        // MECHANICS LIST
        VBox mechanicsList = new VBox(16);
        mechanicsList.setPrefWidth(520);
        mechanicsList.setMinWidth(470);

        mechanicsList.getChildren().addAll(
                createMechanicCard(
                        "Arjun Mehta",
                        "SpeedFix Auto Care",
                        "0.2 km",
                        "6 min ETA",
                        "1,284 jobs",
                        "Petrol",
                        "Hybrid",
                        "Battery",
                        "4.9",
                        true),
                createMechanicCard(
                        "Kabir Sharma",
                        "Highway Motors",
                        "2.4 km",
                        "11 min ETA",
                        "903 jobs",
                        "Diesel",
                        "SUV",
                        "",
                        "4.7",
                        false),
                createMechanicCard(
                        "Neha Rao",
                        "ElectroDrive Garage",
                        "3.1 km",
                        "14 min ETA",
                        "654 jobs",
                        "EV",
                        "Electrical",
                        "",
                        "4.8",
                        false),
                createMechanicCard(
                        "Imran Khan",
                        "RoadCare Service",
                        "4.2 km",
                        "19 min ETA",
                        "521 jobs",
                        "Petrol",
                        "Battery",
                        "",
                        "4.6",
                        false));

        mainArea.getChildren().addAll(mapBox, mechanicsList);

        content.getChildren().addAll(heading, mainArea);

        HBox body = new HBox();
        body.setBackground(new Background(
                new BackgroundFill(Color.web(BG), CornerRadii.EMPTY, Insets.EMPTY)));

        body.getChildren().addAll(sidebarScroll, content);

        VBox root = new VBox(header, body);
        VBox.setVgrow(body, Priority.ALWAYS);

        scene = new Scene(root, 1500, 900);
    }

    // =========================
    // MAP BOX
    // =========================
    private VBox createMapBox() {

        VBox outer = new VBox();
        outer.setPrefWidth(760);
        outer.setMinWidth(600);
        outer.setPrefHeight(465);

        outer.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#E8F5F6"),
                        new CornerRadii(24),
                        Insets.EMPTY)));

        outer.setBorder(new Border(new BorderStroke(
                Color.web(BORDER),
                BorderStrokeStyle.SOLID,
                new CornerRadii(24),
                new BorderWidths(1.5))));

        outer.setPadding(new Insets(10));

        StackPane map = new StackPane();
        VBox.setVgrow(map, Priority.ALWAYS);

        map.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#E7E7E5"),
                        new CornerRadii(20),
                        Insets.EMPTY)));

        map.setBorder(new Border(new BorderStroke(
                Color.web("#D1D8DA"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(20),
                BorderWidths.DEFAULT)));

        // Light road lines - placeholder only.
        Line road1 = new Line(20, 350, 720, 90);
        road1.setStroke(Color.WHITE);
        road1.setStrokeWidth(12);

        Line road2 = new Line(80, 70, 680, 390);
        road2.setStroke(Color.WHITE);
        road2.setStrokeWidth(10);

        Line road3 = new Line(300, 10, 430, 450);
        road3.setStroke(Color.WHITE);
        road3.setStrokeWidth(8);

        Line route = new Line(300, 260, 470, 190);
        route.setStroke(Color.web(BLUE));
        route.setStrokeWidth(3);
        route.getStrokeDashArray().addAll(8.0, 6.0);

        StackPane roads = new StackPane(
                road1, road2, road3, route);

        // You marker
        Circle youCircle = new Circle(22, Color.web(BLUE));
        Label youIcon = new Label("▰");
        youIcon.setTextFill(Color.WHITE);
        youIcon.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        StackPane youMarker = new StackPane(youCircle, youIcon);
        StackPane.setMargin(youMarker, new Insets(70, 0, 0, 0));
        StackPane.setAlignment(youMarker, Pos.CENTER);

        Label youLabel = markerLabel("You");
        VBox youBox = new VBox(4, youMarker, youLabel);
        youBox.setAlignment(Pos.CENTER);
        StackPane.setAlignment(youBox, Pos.CENTER);
        StackPane.setMargin(youBox, new Insets(90, 0, 0, 0));

        // Mechanics markers
        StackPane arjun = mechanicMarker("⚒");
        StackPane kabir = mechanicMarker("⚒");
        StackPane neha = mechanicMarker("⚒");
        StackPane imran = mechanicMarker("⚒");

        StackPane.setAlignment(arjun, Pos.TOP_LEFT);
        StackPane.setMargin(arjun, new Insets(120, 0, 0, 250));

        StackPane.setAlignment(kabir, Pos.TOP_RIGHT);
        StackPane.setMargin(kabir, new Insets(85, 100, 0, 0));

        StackPane.setAlignment(neha, Pos.BOTTOM_LEFT);
        StackPane.setMargin(neha, new Insets(0, 0, 95, 120));

        StackPane.setAlignment(imran, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(imran, new Insets(0, 120, 110, 0));

        Label radius = new Label("Live dispatch radius · 5 km");
        radius.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        radius.setTextFill(Color.web(DARK));
        radius.setPadding(new Insets(10, 16, 10, 16));
        radius.setBackground(new Background(
                new BackgroundFill(Color.web(WHITE), new CornerRadii(22), Insets.EMPTY)));

        StackPane.setAlignment(radius, Pos.TOP_LEFT);
        StackPane.setMargin(radius, new Insets(18));

        Label note = new Label("Real-time map will be connected here later");
        note.setFont(Font.font("Arial", 13));
        note.setTextFill(Color.web(GREY));
        note.setPadding(new Insets(8, 13, 8, 13));
        note.setBackground(new Background(
                new BackgroundFill(
                        Color.web(WHITE),
                        new CornerRadii(18),
                        Insets.EMPTY)));

        StackPane.setAlignment(note, Pos.BOTTOM_LEFT);
        StackPane.setMargin(note, new Insets(18));

        map.getChildren().addAll(
                roads,
                youBox,
                arjun,
                kabir,
                neha,
                imran,
                radius,
                note);

        outer.getChildren().add(map);

        return outer;
    }

    private StackPane mechanicMarker(String icon) {

        Circle circle = new Circle(23, Color.web(GREEN));

        Label label = new Label(icon);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        label.setTextFill(Color.WHITE);

        StackPane marker = new StackPane(circle, label);

        Circle ring = new Circle(28);
        ring.setFill(Color.TRANSPARENT);
        ring.setStroke(Color.WHITE);
        ring.setStrokeWidth(4);

        marker.getChildren().add(0, ring);

        return marker;
    }

    private Label markerLabel(String text) {

        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        label.setTextFill(Color.web(DARK));
        label.setPadding(new Insets(7, 13, 7, 13));
        label.setBackground(new Background(
                new BackgroundFill(
                        Color.web(WHITE),
                        new CornerRadii(16),
                        Insets.EMPTY)));

        return label;
    }

    // =========================
    // MECHANIC CARD
    // =========================
    private VBox createMechanicCard(
            String name,
            String garage,
            String distance,
            String eta,
            String jobs,
            String tag1,
            String tag2,
            String tag3,
            String rating,
            boolean bestMatch) {

        VBox card = new VBox(12);
        card.setPadding(new Insets(20));
        card.setPrefHeight(205);

        card.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#F8FCFD"),
                        new CornerRadii(22),
                        Insets.EMPTY)));

        card.setBorder(new Border(new BorderStroke(
                bestMatch ? Color.web(BLUE) : Color.web(BORDER),
                BorderStrokeStyle.SOLID,
                new CornerRadii(22),
                new BorderWidths(bestMatch ? 2.5 : 1))));

        HBox top = new HBox(10);
        top.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.web(DARK));

        top.getChildren().add(nameLabel);

        if (bestMatch) {
            Label best = new Label("⚡ Best match");
            best.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            best.setTextFill(Color.WHITE);
            best.setPadding(new Insets(5, 10, 5, 10));
            best.setBackground(new Background(
                    new BackgroundFill(
                            Color.web(BLUE),
                            new CornerRadii(14),
                            Insets.EMPTY)));
            top.getChildren().add(best);
        }

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label star = new Label("★ " + rating);
        star.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        star.setTextFill(Color.web(ORANGE));

        top.getChildren().addAll(spacer, star);

        Label garageLabel = new Label(garage);
        garageLabel.setFont(Font.font("Arial", 14));
        garageLabel.setTextFill(Color.web(GREY));

        HBox details = new HBox(18);
        details.getChildren().addAll(
                smallInfo("⌖", distance),
                smallInfo("◷", eta),
                smallInfo("", jobs));

        HBox tags = new HBox(8);

        if (!tag1.isEmpty())
            tags.getChildren().add(tag(tag1));
        if (!tag2.isEmpty())
            tags.getChildren().add(tag(tag2));
        if (!tag3.isEmpty())
            tags.getChildren().add(tag(tag3));

        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        Label available = new Label("• Available now");
        available.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        available.setTextFill(Color.web(GREEN));

        Button assign = new Button("Assign mechanic");
        assign.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        assign.setTextFill(Color.WHITE);
        assign.setPadding(new Insets(10, 17, 10, 17));
        assign.setBackground(new Background(
                new BackgroundFill(
                        Color.web(BLUE),
                        new CornerRadii(20),
                        Insets.EMPTY)));
        assign.setCursor(javafx.scene.Cursor.HAND);

        HBox bottom = new HBox(10, available, bottomSpacer, assign);
        bottom.setAlignment(Pos.CENTER_LEFT);

        card.getChildren().addAll(
                top,
                garageLabel,
                details,
                tags,
                bottom);

        return card;
    }

    private HBox smallInfo(String icon, String value) {

        Label label = new Label(
                icon.isEmpty() ? value : icon + "  " + value);

        label.setFont(Font.font("Arial", 13));
        label.setTextFill(Color.web(GREY));

        HBox box = new HBox(label);
        box.setAlignment(Pos.CENTER_LEFT);

        return box;
    }

    private Label tag(String text) {

        Label tag = new Label(text);
        tag.setFont(Font.font("Arial", 12));
        tag.setTextFill(Color.web(DARK));
        tag.setPadding(new Insets(6, 10, 6, 10));

        tag.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#F0F4F7"),
                        new CornerRadii(12),
                        Insets.EMPTY)));

        return tag;
    }

    // =========================
    // NAVIGATION METHODS
    // =========================

    private void openDashboard(Button sourceButton) {

        UserDashboard page = new UserDashboard();

        Stage stage = (Stage) sourceButton
                .getScene()
                .getWindow();

        stage.setScene(
                page.getDashboardScene());

        stage.show();
    }

    private void openSOSPage(Button sourceButton) {

        SOSPage page = new SOSPage();

        Stage stage = (Stage) sourceButton
                .getScene()
                .getWindow();

        stage.setScene(
                page.getSOSScene());

        stage.show();
    }

    private void openLiveMapPage(Button sourceButton) {

        LiveMapPage page = new LiveMapPage();

        Stage stage = (Stage) sourceButton
                .getScene()
                .getWindow();

        stage.setScene(
                page.getLiveMapScene());

        stage.show();
    }

    private void openAIDiagnosisPage(Button sourceButton) {

        AIDiagnosisPage page = new AIDiagnosisPage();

        Stage stage = (Stage) sourceButton
                .getScene()
                .getWindow();

        stage.setScene(
                page.getAIDiagnosisScene());

        stage.show();
    }

    private void openMechanicsPage(Button sourceButton) {

        MechanicsPage page = new MechanicsPage();

        Stage stage = (Stage) sourceButton
                .getScene()
                .getWindow();

        stage.setScene(
                page.getMechanicsScene());

        stage.show();
    }

    private void openTowTruckPage(Button sourceButton) {

        TowTruckPage page = new TowTruckPage();

        Stage stage = (Stage) sourceButton
                .getScene()
                .getWindow();

        stage.setScene(
                page.getTowTruckScene());

        stage.show();
    }

    private void openCostEstimatorPage(Button sourceButton) {

        CostEstimatorPage page = new CostEstimatorPage();

        Stage stage = (Stage) sourceButton
                .getScene()
                .getWindow();

        stage.setScene(
                page.getCostEstimatorScene());

        stage.show();
    }

    // =========================
    // SIDEBAR BUTTON
    // =========================
    private Button createSidebarButton(
            String icon,
            String text,
            boolean selected) {

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("Arial", 17));

        Label textLabel = new Label(text);
        textLabel.setFont(
                Font.font(
                        "Arial",
                        selected ? FontWeight.BOLD : FontWeight.NORMAL,
                        16));

        HBox content = new HBox(14, iconLabel, textLabel);
        content.setAlignment(Pos.CENTER_LEFT);

        Button button = new Button();
        button.setGraphic(content);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(48);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(0, 14, 0, 14));
        button.setCursor(javafx.scene.Cursor.HAND);

        if (selected) {
            button.setBackground(new Background(
                    new BackgroundFill(
                            Color.web(BLUE),
                            new CornerRadii(28),
                            Insets.EMPTY)));

            iconLabel.setTextFill(Color.WHITE);
            textLabel.setTextFill(Color.WHITE);

        } else {

            button.setBackground(new Background(
                    new BackgroundFill(
                            Color.TRANSPARENT,
                            new CornerRadii(28),
                            Insets.EMPTY)));

            iconLabel.setTextFill(Color.web(DARK));
            textLabel.setTextFill(Color.web(DARK));

            button.setOnMouseEntered(e -> {
                button.setBackground(new Background(
                        new BackgroundFill(
                                Color.web("#EEF4FF"),
                                new CornerRadii(28),
                                Insets.EMPTY)));
            });

            button.setOnMouseExited(e -> {
                button.setBackground(new Background(
                        new BackgroundFill(
                                Color.TRANSPARENT,
                                new CornerRadii(28),
                                Insets.EMPTY)));
            });
        }

        return button;
    }

    private Label sectionLabel(String text) {

        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        label.setTextFill(Color.web(GREY));
        label.setPadding(new Insets(12, 14, 5, 14));

        return label;
    }
}

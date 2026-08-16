package project.ui.user;

import project.app.AppNavigator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LiveMapPage {

    // ============================================================
    // COLORS
    // ============================================================

    private static final String MAIN_BACKGROUND = "#D6F0F3";
    private static final String SIDEBAR = "#C7DDE4";
    private static final String CARD_SURFACE = "#EEF9FA";
    private static final String HEADER_BACKGROUND = "#D6F0F3";
    private static final String SECONDARY_SURFACE = "#DFF3F5";

    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";

    private static final String HEADING = "#172033";
    private static final String SECONDARY_TEXT = "#526274";

    private static final String NAV_BLUE = "#2563EB";
    private static final String SUCCESS = "#16A34A";
    private static final String EMERGENCY = "#DC2626";

    private static final String BORDER = "#B7D5DB";

    private Scene liveMapScene;

    // ============================================================
    // SIDEBAR BUTTONS
    // ============================================================

    private Button dashboardButton;
    private Button sosButton;
    private Button liveMapButton;
    private Button diagnosisButton;
    private Button mechanicsButton;
    private Button towButton;
    private Button costButton;
    private Button vehiclesButton;
    private Button historyButton;
    private Button documentsButton;
    private Button womenButton;
    private Button notificationsButton;
    private Button settingsButton;

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public LiveMapPage() {
    }

    // ============================================================
    // GET SCENE
    // ============================================================

    public Scene getLiveMapScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                MAIN_BACKGROUND + ";"
        );

        // --------------------------------------------------------
        // HEADER
        // --------------------------------------------------------

        HBox header = createHeader();

        // --------------------------------------------------------
        // SIDEBAR
        // --------------------------------------------------------

        ScrollPane sidebar = createSidebar();

        // --------------------------------------------------------
        // CONTENT
        // --------------------------------------------------------

        VBox content = createLiveMapContent();

        ScrollPane contentScroll =
                new ScrollPane(content);

        contentScroll.setFitToWidth(true);
        contentScroll.setFitToHeight(true);

        contentScroll.setPannable(true);

        contentScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        contentScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        contentScroll.setStyle(
                "-fx-background-color: " + MAIN_BACKGROUND + ";" +
                "-fx-background: " + MAIN_BACKGROUND + ";" +
                "-fx-border-color: transparent;"
        );

        root.setTop(header);
        root.setLeft(sidebar);
        root.setCenter(contentScroll);

        // Keep the current Stage size while switching scenes.
        // Do NOT use fixed 1200x700 here because Dashboard/SOS
        // already controls the main application window size.
        root.setMinWidth(1000);
        root.setMinHeight(650);

        liveMapScene = new Scene(root);

        return liveMapScene;
    }

    // ============================================================
    // HEADER
    // ============================================================

    private HBox createHeader() {

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(
                        16,
                        26,
                        16,
                        26
                )
        );

        header.setSpacing(18);

        header.setMinHeight(78);

        header.setStyle(
                "-fx-background-color: " +
                HEADER_BACKGROUND + ";" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        // Logo circle
        StackPane logo =
                new StackPane();

        Circle logoCircle =
                new Circle(
                        20,
                        Color.web(NAV_BLUE)
                );

        Label logoIcon =
                new Label("✓");

        logoIcon.setTextFill(
                Color.WHITE
        );

        logoIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        logo.getChildren().addAll(
                logoCircle,
                logoIcon
        );

        // Brand
        HBox brand =
                new HBox(8);

        brand.setAlignment(
                Pos.CENTER_LEFT
        );

        Label road =
                new Label("Road");

        road.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        road.setTextFill(
                Color.web(HEADING)
        );

        Label guardian =
                new Label("Guardian");

        guardian.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        guardian.setTextFill(
                Color.web(NAV_BLUE)
        );

        brand.getChildren().addAll(
                road,
                guardian
        );

        HBox logoBrand =
                new HBox(
                        12
                );

        logoBrand.setAlignment(
                Pos.CENTER_LEFT
        );

        logoBrand.getChildren().addAll(
                logo,
                brand
        );

        // Spacer
        Region headerSpacer =
                new Region();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS
        );

        // Search
        HBox searchBox =
                new HBox();

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.setPrefWidth(390);
        searchBox.setMaxWidth(390);

        searchBox.setPadding(
                new Insets(
                        10,
                        16,
                        10,
                        16
                )
        );

        searchBox.setSpacing(10);

        searchBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 24;" +
                "-fx-background-radius: 24;"
        );

        Label searchIcon =
                new Label("⌕");

        searchIcon.setFont(
                Font.font(
                        "Arial",
                        24
                )
        );

        searchIcon.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Label searchText =
                new Label(
                        "Search mechanics, invoices, vehicles..."
                );

        searchText.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        searchText.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        searchBox.getChildren().addAll(
                searchIcon,
                searchText
        );

        // Notification
        StackPane notification =
                new StackPane();

        Label bell =
                new Label("♧");

        bell.setFont(
                Font.font(
                        "Arial",
                        25
                )
        );

        bell.setTextFill(
                Color.web(HEADING)
        );

        Circle notificationDot =
                new Circle(
                        4,
                        Color.web(EMERGENCY)
                );

        StackPane.setAlignment(
                notificationDot,
                Pos.TOP_RIGHT
        );

        notificationDot.setTranslateX(
                -1
        );

        notificationDot.setTranslateY(
                2
        );

        notification.getChildren().addAll(
                bell,
                notificationDot
        );

        notification.setPrefWidth(40);

        // Profile
        HBox profile =
                new HBox(10);

        profile.setAlignment(
                Pos.CENTER_LEFT
        );

        profile.setPadding(
                new Insets(
                        7,
                        12,
                        7,
                        8
                )
        );

        profile.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 24;" +
                "-fx-background-radius: 24;"
        );

        Circle avatar =
                new Circle(
                        19,
                        Color.web("#E1EEFF")
                );

        StackPane avatarPane =
                new StackPane();

        Label initials =
                new Label("AN");

        initials.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        initials.setTextFill(
                Color.web(NAV_BLUE)
        );

        avatarPane.getChildren().addAll(
                avatar,
                initials
        );

        VBox profileText =
                new VBox(1);

        Label name =
                new Label("Aarav Nair");

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        name.setTextFill(
                Color.web(HEADING)
        );

        Label role =
                new Label(
                        "Customer · Pune"
                );

        role.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        role.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        profileText.getChildren().addAll(
                name,
                role
        );

        profile.getChildren().addAll(
                avatarPane,
                profileText
        );

        header.getChildren().addAll(
                logoBrand,
                headerSpacer,
                searchBox,
                notification,
                profile
        );

        return header;
    }

    // ============================================================
    // SIDEBAR
    // ============================================================

    private ScrollPane createSidebar() {

        VBox sidebarContent =
                new VBox(5);

        sidebarContent.setPadding(
                new Insets(
                        18,
                        12,
                        20,
                        12
                )
        );

        sidebarContent.setPrefWidth(280);

        sidebarContent.setStyle(
                "-fx-background-color: " +
                SIDEBAR + ";"
        );

        Label overview =
                sectionTitle("OVERVIEW");

        dashboardButton =
                navigationButton(
                        "▦",
                        "Dashboard"
                );

        sosButton =
                navigationButton(
                        "♙",
                        "Emergency SOS"
                );

        liveMapButton =
                navigationButton(
                        "◇",
                        "Live Map"
                );

        Label assistance =
                sectionTitle("ASSISTANCE");

        diagnosisButton =
                navigationButton(
                        "♧",
                        "AI Diagnosis"
                );

        mechanicsButton =
                navigationButton(
                        "⚒",
                        "Mechanics"
                );

        towButton =
                navigationButton(
                        "▱",
                        "Tow Truck"
                );

        costButton =
                navigationButton(
                        "▤",
                        "Cost Estimator"
                );

        Label garage =
                sectionTitle("GARAGE");

        vehiclesButton =
                navigationButton(
                        "▱",
                        "My Vehicles"
                );

        historyButton =
                navigationButton(
                        "◷",
                        "Service History"
                );

        documentsButton =
                navigationButton(
                        "▤",
                        "Documents"
                );

        Label account =
                sectionTitle("ACCOUNT");

        womenButton =
                navigationButton(
                        "♙",
                        "Women Safety"
                );

        notificationsButton =
                navigationButton(
                        "♧",
                        "Notifications"
                );

        settingsButton =
                navigationButton(
                        "⚙",
                        "Settings"
                );

        // Live Map active
        setActiveButton(
                liveMapButton
        );

        // --------------------------------------------------------
        // DASHBOARD
        // --------------------------------------------------------

        dashboardButton.setOnAction(e -> navigateTo("Dashboard"));
        sosButton.setOnAction(e -> navigateTo("Emergency SOS"));
        liveMapButton.setOnAction(e -> navigateTo("Live Map"));
        diagnosisButton.setOnAction(e -> navigateTo("AI Diagnosis"));
        mechanicsButton.setOnAction(e -> navigateTo("Mechanics"));
        towButton.setOnAction(e -> navigateTo("Tow Truck"));
        costButton.setOnAction(e -> navigateTo("Cost Estimator"));
        vehiclesButton.setOnAction(e -> navigateTo("My Vehicles"));
        historyButton.setOnAction(e -> navigateTo("Service History"));
        documentsButton.setOnAction(e -> navigateTo("Documents"));
        womenButton.setOnAction(e -> navigateTo("Women Safety"));
        notificationsButton.setOnAction(e -> navigateTo("Notifications"));
        settingsButton.setOnAction(e -> navigateTo("Settings"));

        sidebarContent.getChildren().addAll(
                overview,
                dashboardButton,
                sosButton,
                liveMapButton,

                assistance,
                diagnosisButton,
                mechanicsButton,
                towButton,
                costButton,

                garage,
                vehiclesButton,
                historyButton,
                documentsButton,

                account,
                womenButton,
                notificationsButton,
                settingsButton
        );

        ScrollPane sidebar =
                new ScrollPane(
                        sidebarContent
                );

        sidebar.setFitToWidth(true);

        sidebar.setPrefWidth(280);
        sidebar.setMinWidth(280);
        sidebar.setMaxWidth(280);

        sidebar.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        sidebar.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        sidebar.setStyle(
                "-fx-background-color: " +
                SIDEBAR + ";" +
                "-fx-border-color: transparent;"
        );

        return sidebar;
    }

    // ============================================================
    // SECTION TITLE
    // ============================================================

    private Label sectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setPadding(
                new Insets(
                        12,
                        12,
                        8,
                        12
                )
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        label.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        return label;
    }

    // ============================================================
    // NAVIGATION BUTTON
    // ============================================================

    private Button navigationButton(
            String icon,
            String text
    ) {

        Button button =
                new Button();

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(46);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setPrefWidth(28);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        17
                )
        );

        Label textLabel =
                new Label(text);

        textLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        15
                )
        );

        textLabel.setTextFill(
                Color.web(HEADING)
        );

        HBox content =
                new HBox(8);

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        content.getChildren().addAll(
                iconLabel,
                textLabel
        );

        button.setGraphic(
                content
        );

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 24;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0 14;"
        );

        // Hover = blue
        button.setOnMouseEntered(
                e -> {

                    if (!button.getStyleClass()
                            .contains("active")) {

                        button.setStyle(
                                "-fx-background-color: " +
                                NAV_BLUE + ";" +
                                "-fx-background-radius: 24;" +
                                "-fx-padding: 0 14;"
                        );

                        textLabel.setTextFill(
                                Color.WHITE
                        );

                        iconLabel.setTextFill(
                                Color.WHITE
                        );
                    }
                }
        );

        button.setOnMouseExited(
                e -> {

                    if (!button.getStyleClass()
                            .contains("active")) {

                        button.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-background-radius: 24;" +
                                "-fx-padding: 0 14;"
                        );

                        textLabel.setTextFill(
                                Color.web(HEADING)
                        );

                        iconLabel.setTextFill(
                                Color.web(HEADING)
                        );
                    }
                }
        );

        return button;
    }

    // ============================================================
    // ACTIVE SIDEBAR BUTTON
    // ============================================================

    private void setActiveButton(
            Button active
    ) {

        Button[] buttons = {
                dashboardButton,
                sosButton,
                liveMapButton,
                diagnosisButton,
                mechanicsButton,
                towButton,
                costButton,
                vehiclesButton,
                historyButton,
                documentsButton,
                womenButton,
                notificationsButton,
                settingsButton
        };

        for (Button button : buttons) {

            if (button != null) {

                button.getStyleClass()
                        .remove("active");

                button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-background-radius: 24;" +
                        "-fx-padding: 0 14;"
                );
            }
        }

        if (active != null) {

            active.getStyleClass()
                    .add("active");

            active.setStyle(
                    "-fx-background-color: " +
                    NAV_BLUE + ";" +
                    "-fx-background-radius: 24;" +
                    "-fx-padding: 0 14;"
            );

            if (active.getGraphic()
                    instanceof HBox) {

                HBox box =
                        (HBox) active.getGraphic();

                for (
                        javafx.scene.Node node :
                        box.getChildren()
                ) {

                    if (node instanceof Label) {

                        ((Label) node)
                                .setTextFill(
                                        Color.WHITE
                                );
                    }
                }
            }
        }
    }

    // ============================================================
    // LIVE MAP CONTENT
    // ============================================================

    private VBox createLiveMapContent() {

        VBox content = new VBox(20);

        content.setPadding(new Insets(24, 24, 28, 24));
        content.setFillWidth(true);
        content.setMinWidth(0);
        content.setMaxWidth(Double.MAX_VALUE);

        // --------------------------------------------------------
        // PAGE TITLE
        // --------------------------------------------------------

        VBox titleBox = new VBox(4);

        Label title = new Label("Live Tracking");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setTextFill(Color.web(HEADING));

        Label subtitle = new Label(
                "Arjun Mehta is en route · updated 4 seconds ago"
        );
        subtitle.setFont(Font.font("Arial", 15));
        subtitle.setTextFill(Color.web(SECONDARY_TEXT));

        titleBox.getChildren().addAll(title, subtitle);

        // --------------------------------------------------------
        // MAIN ROW
        // --------------------------------------------------------

        HBox mainRow = new HBox(20);

        mainRow.setFillHeight(true);
        mainRow.setMinWidth(0);
        mainRow.setMaxWidth(Double.MAX_VALUE);
        mainRow.setPrefHeight(555);
        mainRow.setMinHeight(555);
        mainRow.setMaxHeight(555);

        VBox mapCard = createMapCard();

        VBox infoColumn = new VBox(20);
        infoColumn.setMinWidth(300);
        infoColumn.setPrefWidth(310);
        infoColumn.setMaxWidth(310);

        VBox mechanicCard = createTrackingInfoCard();
        VBox legendCard = createLegendCard();

        infoColumn.getChildren().addAll(
                mechanicCard,
                legendCard
        );

        HBox.setHgrow(mapCard, Priority.ALWAYS);

        mainRow.getChildren().addAll(
                mapCard,
                infoColumn
        );

        content.getChildren().addAll(
                titleBox,
                mainRow
        );

        return content;
    }

    // ============================================================
    // MAP CARD
    // ============================================================

    private VBox createMapCard() {

        VBox card = new VBox();

        card.setPadding(new Insets(10));
        card.setFillWidth(true);
        card.setMinWidth(0);
        card.setPrefHeight(555);
        card.setMinHeight(555);
        card.setMaxHeight(555);
        card.setMaxWidth(Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color: " + CARD_SURFACE + ";" +
                "-fx-background-radius: 24;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 24;" +
                "-fx-border-width: 1;"
        );

        StackPane map = createMap();

        map.setMaxWidth(Double.MAX_VALUE);
        map.setMaxHeight(Double.MAX_VALUE);

        VBox.setVgrow(map, Priority.ALWAYS);

        card.getChildren().add(map);

        return card;
    }

    // ============================================================
    // MAP
    // ============================================================

    private StackPane createMap() {

        StackPane map =
                new StackPane();

        map.setPrefHeight(535);
        map.setMinHeight(535);
        map.setMaxHeight(Double.MAX_VALUE);
        map.setMinWidth(0);
        map.setMaxWidth(Double.MAX_VALUE);

        map.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#E8E8E5"),
                                new CornerRadii(22),
                                Insets.EMPTY
                        )
                )
        );

        map.setBorder(
                new Border(
                        new BorderStroke(
                                Color.web(BORDER),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(22),
                                new BorderWidths(1)
                        )
                )
        );

        // ========================================================
        // REAL MAP BACKGROUND
        // This rectangle is intentionally added as the first child.
        // It prevents the StackPane/ScrollPane white viewport from
        // appearing inside the map area.
        // ========================================================

        Rectangle mapBackground = new Rectangle();

        mapBackground.setFill(
                Color.web("#E7E7E4")
        );

        mapBackground.setArcWidth(44);
        mapBackground.setArcHeight(44);

        mapBackground.widthProperty().bind(
                map.widthProperty().subtract(2)
        );

        mapBackground.heightProperty().bind(
                map.heightProperty().subtract(2)
        );

        StackPane.setAlignment(
                mapBackground,
                Pos.CENTER
        );

        // Clip everything inside the rounded map boundary.
        Rectangle mapClip = new Rectangle();

        mapClip.setArcWidth(44);
        mapClip.setArcHeight(44);

        mapClip.widthProperty().bind(
                map.widthProperty().subtract(2)
        );

        mapClip.heightProperty().bind(
                map.heightProperty().subtract(2)
        );

        map.setClip(mapClip);

        map.getChildren().add(
                mapBackground
        );

        // ========================================================
        // ROADS
        // ========================================================

        Line road1 =
                road(
                        0,
                        400,
                        900,
                        120,
                        12
                );

        Line road2 =
                road(
                        80,
                        100,
                        760,
                        500,
                        11
                );

        Line road3 =
                road(
                        0,
                        250,
                        950,
                        310,
                        9
                );

        Line road4 =
                road(
                        300,
                        0,
                        510,
                        560,
                        7
                );

        Line road5 =
                road(
                        620,
                        0,
                        900,
                        530,
                        8
                );

        Line road6 =
                road(
                        0,
                        520,
                        900,
                        180,
                        10
                );

        map.getChildren().addAll(
                road1,
                road2,
                road3,
                road4,
                road5,
                road6
        );

        // ========================================================
        // RIVER
        // ========================================================

        Line river1 =
                new Line(
                        530,
                        560,
                        650,
                        500
                );

        river1.setStroke(
                Color.web("#73BDE8")
        );

        river1.setStrokeWidth(7);

        Line river2 =
                new Line(
                        650,
                        500,
                        820,
                        490
                );

        river2.setStroke(
                Color.web("#73BDE8")
        );

        river2.setStrokeWidth(7);

        map.getChildren().addAll(
                river1,
                river2
        );

        // ========================================================
        // ARRIVING BADGE
        // ========================================================

        VBox arriving =
                new VBox(0);

        arriving.setAlignment(
                Pos.CENTER_LEFT
        );

        arriving.setPadding(
                new Insets(
                        10,
                        18,
                        10,
                        18
                )
        );

        arriving.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 22;"
        );

        Label arrivingTitle =
                new Label(
                        "ARRIVING IN"
                );

        arrivingTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        arrivingTitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Label arrivingTime =
                new Label(
                        "6 min"
                );

        arrivingTime.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        arrivingTime.setTextFill(
                Color.web(HEADING)
        );

        arriving.getChildren().addAll(
                arrivingTitle,
                arrivingTime
        );

        StackPane.setAlignment(
                arriving,
                Pos.TOP_LEFT
        );

        StackPane.setMargin(
                arriving,
                new Insets(
                        18
                )
        );

        // ========================================================
        // ROUTE
        // ========================================================

        Line route =
                new Line(
                        320,
                        180,
                        520,
                        330
                );

        route.setStroke(
                Color.web(NAV_BLUE)
        );

        route.setStrokeWidth(2);

        route.getStrokeDashArray()
                .addAll(
                        6.0,
                        6.0
                );

        // ========================================================
        // YOU MARKER
        // ========================================================

        StackPane you =
                createMarker(
                        NAV_BLUE,
                        "▰"
                );

        StackPane.setAlignment(
                you,
                Pos.CENTER
        );

        StackPane.setMargin(
                you,
                new Insets(
                        100,
                        0,
                        0,
                        220
                )
        );

        Label youLabel =
                markerLabel(
                        "You"
                );

        StackPane.setAlignment(
                youLabel,
                Pos.CENTER
        );

        StackPane.setMargin(
                youLabel,
                new Insets(
                        175,
                        0,
                        0,
                        220
                )
        );

        // ========================================================
        // MECHANIC MARKER
        // ========================================================

        StackPane mechanic =
                createMarker(
                        SUCCESS,
                        "⚒"
                );

        StackPane.setAlignment(
                mechanic,
                Pos.CENTER
        );

        StackPane.setMargin(
                mechanic,
                new Insets(
                        0,
                        0,
                        100,
                        0
                )
        );

        Label mechanicLabel =
                markerLabel(
                        "Arjun · 6 min"
                );

        StackPane.setAlignment(
                mechanicLabel,
                Pos.CENTER
        );

        StackPane.setMargin(
                mechanicLabel,
                new Insets(
                        60,
                        0,
                        0,
                        0
                )
        );

        // ========================================================
        // OTHER MECHANICS
        // ========================================================

        StackPane mechanic2 =
                createMarker(
                        SUCCESS,
                        "⚒"
                );

        StackPane.setAlignment(
                mechanic2,
                Pos.CENTER
        );

        StackPane.setMargin(
                mechanic2,
                new Insets(
                        0,
                        0,
                        0,
                        350
                )
        );

        StackPane mechanic3 =
                createMarker(
                        SUCCESS,
                        "⚒"
                );

        StackPane.setAlignment(
                mechanic3,
                Pos.CENTER
        );

        StackPane.setMargin(
                mechanic3,
                new Insets(
                        100,
                        0,
                        0,
                        430
                )
        );

        // ========================================================
        // TOW TRUCKS
        // ========================================================

        StackPane tow1 =
                createMarker(
                        ORANGE,
                        "▣"
                );

        StackPane.setAlignment(
                tow1,
                Pos.CENTER
        );

        StackPane.setMargin(
                tow1,
                new Insets(
                        0,
                        0,
                        190,
                        150
                )
        );

        StackPane tow2 =
                createMarker(
                        ORANGE,
                        "▣"
                );

        StackPane.setAlignment(
                tow2,
                Pos.CENTER
        );

        StackPane.setMargin(
                tow2,
                new Insets(
                        100,
                        0,
                        0,
                        500
                )
        );

        // ========================================================
        // GPS BADGE
        // ========================================================

        HBox gps =
                new HBox(8);

        gps.setAlignment(
                Pos.CENTER_LEFT
        );

        gps.setPadding(
                new Insets(
                        8,
                        14,
                        8,
                        14
                )
        );

        gps.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;"
        );

        Circle gpsDot =
                new Circle(
                        5,
                        Color.web(SUCCESS)
                );

        Label gpsText =
                new Label(
                        "Live GPS · 3.1 km remaining"
                );

        gpsText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        gpsText.setTextFill(
                Color.web(HEADING)
        );

        gps.getChildren().addAll(
                gpsDot,
                gpsText
        );

        StackPane.setAlignment(
                gps,
                Pos.BOTTOM_LEFT
        );

        StackPane.setMargin(
                gps,
                new Insets(
                        0,
                        0,
                        18,
                        18
                )
        );

        map.getChildren().addAll(
                route,
                arriving,
                you,
                youLabel,
                mechanic,
                mechanicLabel,
                mechanic2,
                mechanic3,
                tow1,
                tow2,
                gps
        );

        return map;
    }

    // ============================================================
    // ROAD
    // ============================================================

    private Line road(
            double startX,
            double startY,
            double endX,
            double endY,
            double width
    ) {

        Line line =
                new Line(
                        startX,
                        startY,
                        endX,
                        endY
                );

        line.setStroke(
                Color.WHITE
        );

        line.setStrokeWidth(
                width
        );

        return line;
    }

    // ============================================================
    // MARKER
    // ============================================================

    private StackPane createMarker(
            String color,
            String icon
    ) {

        StackPane marker =
                new StackPane();

        Circle outer =
                new Circle(
                        28,
                        Color.WHITE
                );

        Circle inner =
                new Circle(
                        22,
                        Color.web(color)
                );

        Label label =
                new Label(icon);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        label.setTextFill(
                Color.WHITE
        );

        marker.getChildren().addAll(
                outer,
                inner,
                label
        );

        return marker;
    }

    // ============================================================
    // MARKER LABEL
    // ============================================================

    private Label markerLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setPadding(
                new Insets(
                        6,
                        11,
                        6,
                        11
                )
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        label.setTextFill(
                Color.web(HEADING)
        );

        label.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15;"
        );

        return label;
    }

    // ============================================================
    // TRACKING INFO CARD
    // ============================================================

    private VBox createTrackingInfoCard() {

        VBox card =
                createCard();

        card.setPadding(
                new Insets(22)
        );

        Label status =
                new Label(
                        "En route"
                );

        status.setPadding(
                new Insets(
                        5,
                        12,
                        5,
                        12
                )
        );

        status.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        status.setTextFill(
                Color.web(SUCCESS)
        );

        status.setStyle(
                "-fx-background-color: " +
                SECONDARY_SURFACE + ";" +
                "-fx-background-radius: 18;"
        );

        HBox mechanic =
                new HBox(12);

        mechanic.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle avatar =
                new Circle(
                        24,
                        Color.web("#E1ECFF")
                );

        Label initials =
                new Label("AM");

        initials.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        initials.setTextFill(
                Color.web(NAV_BLUE)
        );

        StackPane avatarPane =
                new StackPane(
                        avatar,
                        initials
                );

        VBox mechanicText =
                new VBox(2);

        Label name =
                new Label(
                        "Arjun Mehta"
                );

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        name.setTextFill(
                Color.web(HEADING)
        );

        Label shop =
                new Label(
                        "SpeedFix Auto Care · 4.9 ★"
                );

        shop.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        shop.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        mechanicText.getChildren().addAll(
                name,
                shop
        );

        mechanic.getChildren().addAll(
                avatarPane,
                mechanicText
        );

        VBox details =
                new VBox(10);

        details.getChildren().addAll(
                infoRow(
                        "◷",
                        "ETA",
                        "6 minutes"
                ),
                infoRow(
                        "⌘",
                        "Distance left",
                        "3.1 km"
                ),
                infoRow(
                        "➤",
                        "Current road",
                        "Baner Road,\nnorthbound"
                )
        );

        Button call =
                new Button(
                        "☎   Call mechanic"
                );

        call.setMaxWidth(
                Double.MAX_VALUE
        );

        call.setPrefHeight(42);

        call.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        call.setTextFill(
                Color.WHITE
        );

        call.setStyle(
                "-fx-background-color: " +
                NAV_BLUE + ";" +
                "-fx-background-radius: 22;"
        );

        call.setOnMouseEntered(
                e -> call.setStyle(
                        "-fx-background-color: #1D4ED8;" +
                        "-fx-background-radius: 22;"
                )
        );

        call.setOnMouseExited(
                e -> call.setStyle(
                        "-fx-background-color: " +
                        NAV_BLUE + ";" +
                        "-fx-background-radius: 22;"
                )
        );

        Button share =
                new Button(
                        "Share trip"
                );

        share.setMaxWidth(
                Double.MAX_VALUE
        );

        share.setPrefHeight(42);

        share.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        share.setTextFill(
                Color.web(HEADING)
        );

        share.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 22;" +
                "-fx-background-radius: 22;"
        );

        card.getChildren().addAll(
                status,
                mechanic,
                details,
                call,
                share
        );

        return card;
    }

    // ============================================================
    // INFO ROW
    // ============================================================

    private HBox infoRow(
            String icon,
            String title,
            String value
    ) {

        HBox row =
                new HBox(10);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        10,
                        12,
                        10,
                        12
                )
        );

        row.setStyle(
                "-fx-background-color: " +
                SECONDARY_SURFACE + ";" +
                "-fx-background-radius: 18;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        18
                )
        );

        iconLabel.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        titleLabel.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        valueLabel.setTextFill(
                Color.web(HEADING)
        );

        row.getChildren().addAll(
                iconLabel,
                titleLabel,
                spacer,
                valueLabel
        );

        return row;
    }

    // ============================================================
    // LEGEND
    // ============================================================

    private VBox createLegendCard() {

        VBox card =
                createCard();

        card.setPadding(
                new Insets(22)
        );

        Label title =
                new Label(
                        "LEGEND"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        title.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        VBox items =
                new VBox(12);

        items.getChildren().addAll(
                legendItem(
                        NAV_BLUE,
                        "You"
                ),
                legendItem(
                        "#16B982",
                        "Mechanics"
                ),
                legendItem(
                        ORANGE,
                        "Tow trucks"
                ),
                legendItem(
                        EMERGENCY,
                        "Police / emergency"
                )
        );

        card.getChildren().addAll(
                title,
                items
        );

        return card;
    }

    // ============================================================
    // LEGEND ITEM
    // ============================================================

    private HBox legendItem(
            String color,
            String text
    ) {

        HBox row =
                new HBox(10);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle dot =
                new Circle(
                        6,
                        Color.web(color)
                );

        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        label.setTextFill(
                Color.web(HEADING)
        );

        row.getChildren().addAll(
                dot,
                label
        );

        return row;
    }

    // ============================================================
    // GENERIC CARD
    // ============================================================

    private VBox createCard() {

        VBox card =
                new VBox(16);

        card.setMinWidth(0);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: " +
                CARD_SURFACE + ";" +
                "-fx-border-color: " +
                BORDER + ";" +
                "-fx-border-radius: 22;" +
                "-fx-background-radius: 22;"
        );

        return card;
    }

    private void navigateTo(String pageName) {
        Stage stage = (Stage) liveMapButton.getScene().getWindow();
        AppNavigator.navigate(stage, pageName);
    }

    // ============================================================
    // DASHBOARD NAVIGATION
    // ============================================================

    private void openDashboard() {

        Stage stage =
                (Stage) dashboardButton
                        .getScene()
                        .getWindow();

        UserDashboard dashboard =
                new UserDashboard();

        Scene dashboardScene =
                dashboard.getDashboardScene();

        stage.setTitle(
                "RoadGuardian - Customer Dashboard"
        );

        stage.setScene(
                dashboardScene
        );
    }

    // ============================================================
    // SOS NAVIGATION
    // ============================================================

    private void openSOS() {

        try {

            SOSPage sosPage =
                    new SOSPage();

            Stage stage =
                    (Stage) sosButton
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

    // ============================================================
    // MAIN TEST
    // ============================================================

    public static void main(
            String[] args
    ) {

        javafx.application.Application.launch(
                LiveMapApplication.class,
                args
        );
    }

    // ============================================================
    // TEST APPLICATION
    // ============================================================

    public static class LiveMapApplication
            extends javafx.application.Application {

        @Override
        public void start(
                Stage stage
        ) {

            LiveMapPage page =
                    new LiveMapPage();

            stage.setTitle(
                    "RoadGuardian - Live Tracking"
            );

            stage.setMinWidth(1000);
            stage.setMinHeight(650);

            stage.setScene(
                    page.getLiveMapScene()
            );

            stage.show();
        }
    }
}

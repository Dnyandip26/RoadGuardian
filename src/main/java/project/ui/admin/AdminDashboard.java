package project.ui.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.google.cloud.firestore.Firestore;

import project.controller.admin.DashboardController;
import project.firebase.FirebaseConfig;

public class AdminDashboard {

    // =========================================================
    // AQUA MIST THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SIDEBAR = "#C4D9E0";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";

    private static final String BLUE = "#2563EB";
    private static final String GREEN = "#16A34A";
    private static final String RED = "#DC2626";

    private static final String BORDER = "#B8D4D9";

    // =========================================================
    // FIELDS
    // =========================================================

    private final Stage stage;

    private BorderPane root;
    private VBox sidebar;
    private VBox contentArea;

    private Button dashboardButton;
    private Button customersButton;
    private Button mechanicsButton;
    private Button vehiclesButton;
    private Button serviceRequestButton;
    private Button sosButton;
    private Button servicesButton;
    private Button reviewsButton;
    private Button complaintsButton;
    private Button notificationsButton;
    private Button reportsButton;
    private Button settingsButton;

    private DashboardController dashboardController;

    private Map<String, Integer> dashboardData;

    private Firestore firestore;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AdminDashboard(Stage stage) {

        this.stage = stage;

        try {

            firestore =
                    FirebaseConfig.getFirestore();

        } catch (Exception e) {

            e.printStackTrace();
        }

        dashboardController =
                new DashboardController(
                        firestore
                );
    }

    // =========================================================
    // GET VIEW
    // =========================================================

    public BorderPane getView() {

        root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        sidebar =
                createSidebar();

        contentArea =
                new VBox();

        contentArea.setFillWidth(
                true
        );

        contentArea.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        root.setLeft(
                sidebar
        );

        root.setCenter(
                contentArea
        );

        showDashboard();

        return root;
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox createSidebar() {

        VBox side =
                new VBox();

        side.setPrefWidth(
                265
        );

        side.setMinWidth(
                265
        );

        side.setMaxWidth(
                265
        );

        side.setPadding(
                new Insets(
                        28,
                        17,
                        20,
                        17
                )
        );

        side.setSpacing(
                8
        );

        side.setStyle(
                "-fx-background-color: " +
                SIDEBAR +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        // =====================================================
        // BRAND
        // =====================================================

        VBox brand =
                new VBox(5);

        brand.setPadding(
                new Insets(
                        4,
                        11,
                        25,
                        11
                )
        );

        Label logo =
                new Label("◈");

        logo.setTextFill(
                Color.web(BLUE)
        );

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        Label title =
                new Label(
                        "ROADGUARDIAN"
                );

        title.setTextFill(
                Color.web(HEADING)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        Label subtitle =
                new Label(
                        "ADMIN PANEL"
                );

        subtitle.setTextFill(
                Color.web(GREEN)
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        brand.getChildren().addAll(
                logo,
                title,
                subtitle
        );

        side.getChildren().add(
                brand
        );

        // =====================================================
        // NAVIGATION BUTTONS
        // =====================================================

        dashboardButton =
                createNavButton(
                        "▣",
                        "Dashboard"
                );

        customersButton =
                createNavButton(
                        "♙",
                        "Customers"
                );

        mechanicsButton =
                createNavButton(
                        "⚒",
                        "Mechanics"
                );

        vehiclesButton =
                createNavButton(
                        "▰",
                        "Vehicles"
                );

        serviceRequestButton =
                createNavButton(
                        "▤",
                        "Service Requests"
                );

        sosButton =
                createNavButton(
                        "SOS",
                        "SOS Requests"
                );

        servicesButton =
                createNavButton(
                        "⚙",
                        "Services"
                );

        reviewsButton =
                createNavButton(
                        "☆",
                        "Reviews"
                );

        complaintsButton =
                createNavButton(
                        "!",
                        "Complaints"
                );

        notificationsButton =
                createNavButton(
                        "♢",
                        "Notifications"
                );

        reportsButton =
                createNavButton(
                        "▥",
                        "Reports"
                );

        settingsButton =
                createNavButton(
                        "⚙",
                        "Settings"
                );

        side.getChildren().addAll(
                dashboardButton,
                customersButton,
                mechanicsButton,
                vehiclesButton,
                serviceRequestButton,
                sosButton,
                servicesButton,
                reviewsButton,
                complaintsButton,
                notificationsButton,
                reportsButton,
                settingsButton
        );

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        side.getChildren().add(
                spacer
        );

        // =====================================================
        // DATE BOX
        // =====================================================

        VBox dateBox =
                createDateBox();

        Button logoutButton =
                createLogoutButton();

        side.getChildren().addAll(
                dateBox,
                logoutButton
        );

        // =====================================================
        // NAVIGATION EVENTS
        // =====================================================

        dashboardButton.setOnAction(
                event ->
                        showDashboard()
        );

        customersButton.setOnAction(
                event ->
                        showCustomers()
        );

        mechanicsButton.setOnAction(
            event -> showMechanics()
        );

        vehiclesButton.setOnAction(
            event -> showVehicles()
        );

        serviceRequestButton.setOnAction(
            event -> showServiceRequests()
        );

        sosButton.setOnAction(
                event ->
                        showPlaceholder(
                                "SOS Requests",
                                "Monitor and manage emergency requests."
                        )
        );

        servicesButton.setOnAction(
                event ->
                        showPlaceholder(
                                "Services",
                                "Manage RoadGuardian services."
                        )
        );

        reviewsButton.setOnAction(
                event ->
                        showPlaceholder(
                                "Reviews",
                                "Manage customer reviews."
                        )
        );

        complaintsButton.setOnAction(
                event ->
                        showPlaceholder(
                                "Complaints",
                                "Manage customer complaints."
                        )
        );

        notificationsButton.setOnAction(
                event ->
                        showPlaceholder(
                                "Notifications",
                                "Manage system notifications."
                        )
        );

        reportsButton.setOnAction(
                event ->
                        showPlaceholder(
                                "Reports",
                                "View RoadGuardian reports."
                        )
        );

        settingsButton.setOnAction(
                event ->
                        showPlaceholder(
                                "Settings",
                                "Manage administrator settings."
                        )
        );

        return side;
    }
    private void showServiceRequests() {

    setActive(
        serviceRequestButton
    );

    ServiceRequestManagementPage page =
            new ServiceRequestManagementPage();

    contentArea.getChildren().setAll(
        page.getView()
    );
}
    private void showVehicles() {

        setActive(
            vehiclesButton
        );

        VehicleManagementPage page =
                new VehicleManagementPage();

        contentArea.getChildren().setAll(
            page.getView()
        );
    }
    private void showMechanics() {

    setActive(
        mechanicsButton
    );

    MechanicManagementPage page =
            new MechanicManagementPage();

    contentArea.getChildren().setAll(
        page.getView()
    );
}

    // =========================================================
    // NAVIGATION BUTTON
    // =========================================================

    private Button createNavButton(
            String icon,
            String text
    ) {

        Button button =
                new Button();

        Label iconLabel =
                new Label(icon);

        iconLabel.setMinWidth(
                27
        );

        iconLabel.setTextFill(
                Color.web(TEXT)
        );

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        Label textLabel =
                new Label(text);

        textLabel.setTextFill(
                Color.web(HEADING)
        );

        textLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        HBox content =
                new HBox(
                        11,
                        iconLabel,
                        textLabel
                );

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setGraphic(
                content
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                48
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(
                        0,
                        16,
                        0,
                        16
                )
        );

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 9;"
        );

        button.setOnMouseEntered(
                event -> {

                    if (
                            !button.getStyleClass()
                                    .contains(
                                            "active-nav"
                                    )
                    ) {

                        button.setStyle(
                                "-fx-background-color: " +
                                SECONDARY +
                                ";" +
                                "-fx-background-radius: 9;"
                        );
                    }
                }
        );

        button.setOnMouseExited(
                event -> {

                    if (
                            !button.getStyleClass()
                                    .contains(
                                            "active-nav"
                                    )
                    ) {

                        button.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-background-radius: 9;"
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // ACTIVE NAVIGATION
    // =========================================================

    private void setActive(
            Button active
    ) {

        Button[] buttons = {

                dashboardButton,
                customersButton,
                mechanicsButton,
                vehiclesButton,
                serviceRequestButton,
                sosButton,
                servicesButton,
                reviewsButton,
                complaintsButton,
                notificationsButton,
                reportsButton,
                settingsButton

        };

        for (
                Button button :
                buttons
        ) {

            button.getStyleClass()
                    .remove(
                            "active-nav"
                    );

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-background-radius: 9;"
            );
        }

        active.getStyleClass()
                .add(
                        "active-nav"
                );

        active.setStyle(
                "-fx-background-color: " +
                ORANGE +
                ";" +
                "-fx-background-radius: 9;"
        );
    }

    // =========================================================
    // DATE BOX
    // =========================================================

    private VBox createDateBox() {

        VBox box =
                new VBox(4);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(15)
        );

        box.setStyle(
                "-fx-background-color: " +
                SECONDARY +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        Label date =
                new Label(
                        LocalDate.now()
                                .format(
                                        DateTimeFormatter.ofPattern(
                                                "dd MMM yyyy"
                                        )
                                )
                );

        date.setTextFill(
                Color.web(TEXT)
        );

        date.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        Label time =
                new Label(
                        LocalTime.now()
                                .format(
                                        DateTimeFormatter.ofPattern(
                                                "hh:mm a"
                                        )
                                )
                );

        time.setTextFill(
                Color.web(HEADING)
        );

        time.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        box.getChildren().addAll(
                date,
                time
        );

        return box;
    }

    // =========================================================
    // LOGOUT
    // =========================================================

    private Button createLogoutButton() {

        Button button =
                new Button(
                        "↪  Logout"
                );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                44
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(
                        0,
                        16,
                        0,
                        16
                )
        );

        button.setTextFill(
                Color.web(RED)
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        button.setStyle(
                "-fx-background-color: transparent;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                RED +
                                "18;" +
                                "-fx-background-radius: 9;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: transparent;"
                        )
        );

        button.setOnAction(
                event -> {

                    Alert alert =
                            new Alert(
                                    Alert.AlertType.CONFIRMATION
                            );

                    alert.setTitle(
                            "Logout"
                    );

                    alert.setHeaderText(
                            "Logout from Admin Panel?"
                    );

                    alert.setContentText(
                            "You will be returned to the previous screen."
                    );

                    alert.showAndWait()
                            .ifPresent(
                                    result -> {

                                        if (
                                                result ==
                                                ButtonType.OK
                                        ) {

                                            stage.close();
                                        }
                                    }
                            );
                }
        );

        return button;
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private void showDashboard() {

        setActive(
                dashboardButton
        );

        dashboardData =
                dashboardController
                        .getDashboardStatistics();

        contentArea.getChildren().setAll(
                createDashboard()
        );
    }

    // =========================================================
    // DASHBOARD PAGE
    // =========================================================

    private VBox createDashboard() {

        VBox page =
                new VBox(24);

        page.setPadding(
                new Insets(
                        32
                )
        );

        page.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        // HEADER

        HBox header =
                createHeader(
                        "Dashboard",
                        "Welcome back, Admin! Here's what's happening with RoadGuardian."
                );

        // STATS

        HBox stats =
                createStats();

        // MIDDLE

        HBox middle =
                new HBox(20);

        VBox requestCard =
                createRequestOverview();

        VBox sosCard =
                createSOSCard();

        HBox.setHgrow(
                requestCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                sosCard,
                Priority.ALWAYS
        );

        middle.getChildren().addAll(
                requestCard,
                sosCard
        );

        // BOTTOM

        HBox bottom =
                new HBox(20);

        VBox recent =
                createRecentRequests();

        VBox quick =
                createQuickActions();

        HBox.setHgrow(
                recent,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                quick,
                Priority.ALWAYS
        );

        bottom.getChildren().addAll(
                recent,
                quick
        );

        page.getChildren().addAll(
                header,
                stats,
                middle,
                bottom
        );

        VBox.setVgrow(
                middle,
                Priority.ALWAYS
        );

        VBox.setVgrow(
                bottom,
                Priority.ALWAYS
        );

        return page;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private HBox createHeader(
            String title,
            String subtitle
    ) {

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox textBox =
                new VBox(6);

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web(HEADING)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        Label subtitleLabel =
                new Label(subtitle);

        subtitleLabel.setTextFill(
                Color.web(TEXT)
        );

        subtitleLabel.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        textBox.getChildren().addAll(
                titleLabel,
                subtitleLabel
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Circle avatar =
                new Circle(
                        25,
                        Color.web(ORANGE)
                );

        Label initial =
                new Label("A");

        initial.setTextFill(
                Color.WHITE
        );

        initial.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        StackPane avatarBox =
                new StackPane(
                        avatar,
                        initial
                );

        VBox adminInfo =
                new VBox(3);

        Label admin =
                new Label(
                        "Admin"
                );

        admin.setTextFill(
                Color.web(HEADING)
        );

        admin.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Label role =
                new Label(
                        "Super Administrator"
                );

        role.setTextFill(
                Color.web(TEXT)
        );

        role.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        adminInfo.getChildren().addAll(
                admin,
                role
        );

        header.getChildren().addAll(
                textBox,
                spacer,
                avatarBox,
                adminInfo
        );

        return header;
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private HBox createStats() {

        HBox box =
                new HBox(16);

        int customers =
                dashboardController.getValue(
                        dashboardData,
                        "customers"
                );

        int mechanics =
                dashboardController.getValue(
                        dashboardData,
                        "mechanics"
                );

        int vehicles =
                dashboardController.getValue(
                        dashboardData,
                        "vehicles"
                );

        int serviceRequests =
                dashboardController.getValue(
                        dashboardData,
                        "serviceRequests"
                );

        int sosRequests =
                dashboardController.getValue(
                        dashboardData,
                        "sosRequests"
                );

        box.getChildren().addAll(

                createStatCard(
                        "♙",
                        String.valueOf(
                                customers
                        ),
                        "Total Customers",
                        "Registered customers",
                        BLUE
                ),

                createStatCard(
                        "⚒",
                        String.valueOf(
                                mechanics
                        ),
                        "Total Mechanics",
                        "Registered mechanics",
                        GREEN
                ),

                createStatCard(
                        "▰",
                        String.valueOf(
                                vehicles
                        ),
                        "Total Vehicles",
                        "Registered vehicles",
                        ORANGE
                ),

                createStatCard(
                        "▤",
                        String.valueOf(
                                serviceRequests
                        ),
                        "Service Requests",
                        "Total requests",
                        BLUE
                ),

                createStatCard(
                        "SOS",
                        String.valueOf(
                                sosRequests
                        ),
                        "SOS Requests",
                        "Emergency requests",
                        RED
                )
        );

        return box;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String icon,
            String value,
            String title,
            String footer,
            String color
    ) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(
                        20
                )
        );

        card.setPrefHeight(
                135
        );

        card.setMinWidth(
                180
        );

        card.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;"
        );

        HBox top =
                new HBox(13);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane iconBox =
                new StackPane();

        iconBox.setPrefSize(
                52,
                52
        );

        iconBox.setMinSize(
                52,
                52
        );

        iconBox.setStyle(
                "-fx-background-color: " +
                color +
                ";" +
                "-fx-background-radius: 11;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setTextFill(
                Color.WHITE
        );

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        iconBox.getChildren().add(
                iconLabel
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setTextFill(
                Color.web(HEADING)
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        top.getChildren().addAll(
                iconBox,
                valueLabel
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web(TEXT)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Label footerLabel =
                new Label(footer);

        footerLabel.setTextFill(
                Color.web(GREEN)
        );

        footerLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        card.getChildren().addAll(
                top,
                titleLabel,
                footerLabel
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        return card;
    }

    // =========================================================
    // SERVICE REQUEST OVERVIEW
    // =========================================================

    private VBox createRequestOverview() {

        VBox card =
                createCard();

        HBox titleRow =
                new HBox();

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                sectionTitle(
                        "Service Request Overview"
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        ComboBox<String> period =
                new ComboBox<>();

        period.getItems().addAll(
                "This Month",
                "This Week",
                "This Year"
        );

        period.setValue(
                "This Month"
        );

        period.setPrefWidth(
                140
        );

        period.setPrefHeight(
                38
        );

        period.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";"
        );

        titleRow.getChildren().addAll(
                title,
                spacer,
                period
        );

        HBox chartArea =
                new HBox();

        chartArea.setAlignment(
                Pos.CENTER
        );

        StackPane chart =
                createDonutChart();

        VBox legend =
                new VBox(14);

        legend.setPadding(
                new Insets(
                        10,
                        20,
                        10,
                        30
                )
        );

        legend.getChildren().addAll(
                legendRow(
                        BLUE,
                        "Completed",
                        "70",
                        "56%"
                ),

                legendRow(
                        GREEN,
                        "In Progress",
                        "25",
                        "20%"
                ),

                legendRow(
                        ORANGE,
                        "Pending",
                        "20",
                        "16%"
                ),

                legendRow(
                        RED,
                        "Cancelled",
                        "10",
                        "8%"
                )
        );

        chartArea.getChildren().addAll(
                chart,
                legend
        );

        VBox.setVgrow(
                chartArea,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                titleRow,
                chartArea
        );

        return card;
    }

    // =========================================================
    // DONUT CHART
    // =========================================================

    private StackPane createDonutChart() {

        StackPane chart =
                new StackPane();

        chart.setPrefSize(
                230,
                210
        );

        javafx.scene.shape.Arc completed =
                new javafx.scene.shape.Arc(
                        115,
                        105,
                        75,
                        75,
                        90,
                        -201.6
                );

        completed.setType(
                javafx.scene.shape.ArcType.ROUND
        );

        completed.setFill(
                Color.web(BLUE)
        );

        javafx.scene.shape.Arc progress =
                new javafx.scene.shape.Arc(
                        115,
                        105,
                        75,
                        75,
                        -111.6,
                        -72
                );

        progress.setType(
                javafx.scene.shape.ArcType.ROUND
        );

        progress.setFill(
                Color.web(GREEN)
        );

        javafx.scene.shape.Arc pending =
                new javafx.scene.shape.Arc(
                        115,
                        105,
                        75,
                        75,
                        -183.6,
                        -57.6
                );

        pending.setType(
                javafx.scene.shape.ArcType.ROUND
        );

        pending.setFill(
                Color.web(ORANGE)
        );

        javafx.scene.shape.Arc cancelled =
                new javafx.scene.shape.Arc(
                        115,
                        105,
                        75,
                        75,
                        -241.2,
                        -28.8
                );

        cancelled.setType(
                javafx.scene.shape.ArcType.ROUND
        );

        cancelled.setFill(
                Color.web(RED)
        );

        javafx.scene.shape.Circle center =
                new javafx.scene.shape.Circle(
                        115,
                        105,
                        43,
                        Color.web(SURFACE)
                );

        VBox total =
                new VBox(1);

        total.setAlignment(
                Pos.CENTER
        );

        Label totalText =
                new Label(
                        "Total"
                );

        totalText.setTextFill(
                Color.web(TEXT)
        );

        totalText.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        Label totalValue =
                new Label(
                        "125"
                );

        totalValue.setTextFill(
                Color.web(HEADING)
        );

        totalValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        total.getChildren().addAll(
                totalText,
                totalValue
        );

        chart.getChildren().addAll(
                completed,
                progress,
                pending,
                cancelled,
                center,
                total
        );

        return chart;
    }

    // =========================================================
    // LEGEND
    // =========================================================

    private HBox legendRow(
            String color,
            String name,
            String count,
            String percentage
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

        Label nameLabel =
                new Label(
                        name +
                        " (" +
                        count +
                        ")"
                );

        nameLabel.setTextFill(
                Color.web(HEADING)
        );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label percent =
                new Label(
                        percentage
                );

        percent.setTextFill(
                Color.web(HEADING)
        );

        percent.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        row.getChildren().addAll(
                dot,
                nameLabel,
                spacer,
                percent
        );

        return row;
    }

    // =========================================================
    // SOS CARD
    // =========================================================

    private VBox createSOSCard() {

        VBox card =
                createCard();

        HBox titleRow =
                new HBox();

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                sectionTitle(
                        "SOS Requests"
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button viewAll =
                new Button(
                        "View All"
                );

        viewAll.setTextFill(
                Color.WHITE
        );

        viewAll.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        viewAll.setPrefHeight(
                36
        );

        viewAll.setPadding(
                new Insets(
                        0,
                        14,
                        0,
                        14
                )
        );

        viewAll.setStyle(
                "-fx-background-color: " +
                BLUE +
                ";" +
                "-fx-background-radius: 7;"
        );

        titleRow.getChildren().addAll(
                title,
                spacer,
                viewAll
        );

        VBox list =
                new VBox();

        list.getChildren().addAll(

                sosRow(
                        "Rahul Sharma",
                        "Pune, Maharashtra",
                        "01:35 PM"
                ),

                sosRow(
                        "Akash Patil",
                        "Mumbai, Maharashtra",
                        "01:20 PM"
                ),

                sosRow(
                        "Rohit S. N.",
                        "Nagpur, Maharashtra",
                        "01:05 PM"
                ),

                sosRow(
                        "Suresh Jadhav",
                        "Nashik, Maharashtra",
                        "12:50 PM"
                )
        );

        VBox.setVgrow(
                list,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                titleRow,
                list
        );

        return card;
    }

    // =========================================================
    // SOS ROW
    // =========================================================

    private HBox sosRow(
            String name,
            String location,
            String time
    ) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        12,
                        5,
                        12,
                        5
                )
        );

        row.setStyle(
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        Circle circle =
                new Circle(
                        19,
                        Color.web(
                                RED +
                                "20"
                        )
                );

        Label sos =
                new Label(
                        "SOS"
                );

        sos.setTextFill(
                Color.web(RED)
        );

        sos.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        StackPane badge =
                new StackPane(
                        circle,
                        sos
                );

        VBox info =
                new VBox(3);

        Label nameLabel =
                new Label(
                        name
                );

        nameLabel.setTextFill(
                Color.web(HEADING)
        );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Label locationLabel =
                new Label(
                        location
                );

        locationLabel.setTextFill(
                Color.web(TEXT)
        );

        locationLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        info.getChildren().addAll(
                nameLabel,
                locationLabel
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        VBox timeBox =
                new VBox(3);

        timeBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label timeLabel =
                new Label(
                        time
                );

        timeLabel.setTextFill(
                Color.web(HEADING)
        );

        timeLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        Label active =
                new Label(
                        "Active"
                );

        active.setTextFill(
                Color.web(RED)
        );

        active.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        timeBox.getChildren().addAll(
                timeLabel,
                active
        );

        row.getChildren().addAll(
                badge,
                info,
                spacer,
                timeBox
        );

        return row;
    }

    // =========================================================
    // RECENT REQUESTS
    // =========================================================

    private VBox createRecentRequests() {

        VBox card =
                createCard();

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                sectionTitle(
                        "Recent Service Requests"
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button view =
                new Button(
                        "View All"
                );

        view.setTextFill(
                Color.WHITE
        );

        view.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        view.setPrefHeight(
                36
        );

        view.setStyle(
                "-fx-background-color: " +
                BLUE +
                ";" +
                "-fx-background-radius: 7;"
        );

        header.getChildren().addAll(
                title,
                spacer,
                view
        );

        TableView<String[]> table =
                new TableView<>();

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        String[] headers = {
                "Request ID",
                "Customer",
                "Vehicle",
                "Mechanic",
                "Status",
                "Date"
        };

        for (
                int i = 0;
                i < headers.length;
                i++
        ) {

            final int index = i;

            TableColumn<String[], String> column =
                    new TableColumn<>(
                            headers[i]
                    );

            column.setCellValueFactory(
                    data ->
                            new javafx.beans.property
                                    .SimpleStringProperty(
                                            data.getValue()[index]
                                    )
            );

            table.getColumns().add(
                    column
            );
        }

        table.getItems().addAll(

                new String[]{
                        "SR1256",
                        "Rahul Sharma",
                        "MH12AB1234",
                        "Ganesh Pawar",
                        "Pending",
                        "12 Aug 2026"
                },

                new String[]{
                        "SR1255",
                        "Akash Patil",
                        "MH14CD5678",
                        "Rohit S. N.",
                        "In Progress",
                        "12 Aug 2026"
                },

                new String[]{
                        "SR1254",
                        "Pooja Mehta",
                        "MH12EF9012",
                        "Sameer Khan",
                        "Completed",
                        "12 Aug 2026"
                },

                new String[]{
                        "SR1253",
                        "Vikram Joshi",
                        "MH15GH3456",
                        "Amol Kamble",
                        "Pending",
                        "11 Aug 2026"
                }
        );

        table.setPrefHeight(
                250
        );

        table.setFixedCellSize(
                46
        );

        table.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-control-inner-background: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";"
        );

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                header,
                table
        );

        return card;
    }

    // =========================================================
    // QUICK ACTIONS
    // =========================================================

    private VBox createQuickActions() {

        VBox card =
                createCard();

        Label title =
                sectionTitle(
                        "Quick Actions"
                );

        GridPane grid =
                new GridPane();

        grid.setHgap(
                11
        );

        grid.setVgap(
                11
        );

        grid.add(
                quickAction(
                        "♙",
                        "Add Customer",
                        BLUE
                ),
                0,
                0
        );

        grid.add(
                quickAction(
                        "⚒",
                        "Add Mechanic",
                        GREEN
                ),
                1,
                0
        );

        grid.add(
                quickAction(
                        "▰",
                        "Add Vehicle",
                        ORANGE
                ),
                2,
                0
        );

        grid.add(
                quickAction(
                        "▤",
                        "New Service",
                        BLUE
                ),
                0,
                1
        );

        grid.add(
                quickAction(
                        "SOS",
                        "New SOS",
                        RED
                ),
                1,
                1
        );

        grid.add(
                quickAction(
                        "♢",
                        "Notification",
                        GREEN
                ),
                2,
                1
        );

        card.getChildren().addAll(
                title,
                grid
        );

        return card;
    }

    // =========================================================
    // QUICK ACTION BOX
    // =========================================================

    private VBox quickAction(
            String icon,
            String text,
            String color
    ) {

        VBox box =
                new VBox(9);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPrefSize(
                120,
                100
        );

        box.setStyle(
                "-fx-background-color: " +
                SECONDARY +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;"
        );

        box.setOnMouseEntered(
                event ->
                        box.setStyle(
                                "-fx-background-color: " +
                                SURFACE +
                                ";" +
                                "-fx-border-color: " +
                                color +
                                ";" +
                                "-fx-border-radius: 9;" +
                                "-fx-background-radius: 9;"
                        )
        );

        box.setOnMouseExited(
                event ->
                        box.setStyle(
                                "-fx-background-color: " +
                                SECONDARY +
                                ";" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 9;" +
                                "-fx-background-radius: 9;"
                        )
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setTextFill(
                Color.web(color)
        );

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        Label textLabel =
                new Label(text);

        textLabel.setTextFill(
                Color.web(HEADING)
        );

        textLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        box.getChildren().addAll(
                iconLabel,
                textLabel
        );

        return box;
    }

    // =========================================================
    // COMMON CARD
    // =========================================================

    private VBox createCard() {

        VBox card =
                new VBox(16);

        card.setPadding(
                new Insets(
                        20
                )
        );

        card.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;"
        );

        return card;
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private Label sectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setTextFill(
                Color.web(HEADING)
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        return label;
    }

    // =========================================================
    // CUSTOMER PAGE
    // =========================================================

    private void showCustomers() {

        setActive(
                customersButton
        );

        CustomerManagementPage page =
                new CustomerManagementPage();

        contentArea.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // PLACEHOLDER PAGE
    // =========================================================

    private void showPlaceholder(
            String title,
            String subtitle
    ) {

        Button activeButton =
                getButtonForTitle(
                        title
                );

        if (
                activeButton != null
        ) {

            setActive(
                    activeButton
            );
        }

        VBox page =
                new VBox(14);

        page.setAlignment(
                Pos.TOP_LEFT
        );

        page.setPadding(
                new Insets(
                        32
                )
        );

        page.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        Label heading =
                new Label(title);

        heading.setTextFill(
                Color.web(HEADING)
        );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        Label description =
                new Label(subtitle);

        description.setTextFill(
                Color.web(TEXT)
        );

        description.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        VBox card =
                createCard();

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        Label message =
                new Label(
                        "This section is ready for integration."
                );

        message.setTextFill(
                Color.web(TEXT)
        );

        message.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        card.getChildren().add(
                message
        );

        page.getChildren().addAll(
                heading,
                description,
                card
        );

        contentArea.getChildren().setAll(
                page
        );
    }

    // =========================================================
    // BUTTON MAPPING
    // =========================================================

    private Button getButtonForTitle(
            String title
    ) {

        if (
                title.equals(
                        "Mechanic Management"
                )
        ) {
            return mechanicsButton;
        }

        if (
                title.equals(
                        "Vehicle Management"
                )
        ) {
            return vehiclesButton;
        }

        if (
                title.equals(
                        "Service Requests"
                )
        ) {
            return serviceRequestButton;
        }

        if (
                title.equals(
                        "SOS Requests"
                )
        ) {
            return sosButton;
        }

        if (
                title.equals(
                        "Services"
                )
        ) {
            return servicesButton;
        }

        if (
                title.equals(
                        "Reviews"
                )
        ) {
            return reviewsButton;
        }

        if (
                title.equals(
                        "Complaints"
                )
        ) {
            return complaintsButton;
        }

        if (
                title.equals(
                        "Notifications"
                )
        ) {
            return notificationsButton;
        }

        if (
                title.equals(
                        "Reports"
                )
        ) {
            return reportsButton;
        }

        if (
                title.equals(
                        "Settings"
                )
        ) {
            return settingsButton;
        }

        return null;
    }
}
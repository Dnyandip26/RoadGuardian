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

 
//THeme 

    private static final String BG = "#D6F0F3";
    private static final String SIDEBAR = "#C4D9E0";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    // Blue
    private static final String ORANGE = "#2563EB";
    private static final String ORANGE_HOVER = "#1D4ED8";

    // Orange
    private static final String BLUE = "#F97316";
    private static final String BLUE_HOVER = "#EA580C";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";

    private static final String GREEN = "#16A34A";
    private static final String GREEN_HOVER = "#15803D";

    private static final String RED = "#a73232";
    private static final String RED_HOVER = "#B91C1C";

    private static final String BORDER = "#B8D4D9";

     // FIELDS
     

    private final Stage stage;

    private BorderPane root;

    private VBox sidebar;

    private VBox contentArea;

    private ScrollPane contentScroll;

    private boolean sidebarCollapsed = false;

    // Navigation buttons

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

    // Sidebar toggle

    private Button sidebarToggleButton;

    // Brand labels

    private Label brandTitle;
    private Label brandSubtitle;

    // Dashboard

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

    root = new BorderPane();

    root.setStyle(
            "-fx-background-color: " +
            BG +
            ";"
    );

    sidebar = createSidebar();

    // Main content
    VBox mainArea =
            new VBox();

    mainArea.setFillWidth(true);

    // Top bar
    HBox topBar =
            createMainTopBar();

    // Scrollable content
    contentArea =
            new VBox();

    contentArea.setFillWidth(true);

    contentScroll =
            new ScrollPane();

    contentScroll.setContent(
            contentArea
    );

    contentScroll.setFitToWidth(true);

    contentScroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
    );

    contentScroll.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
    );

    mainArea.getChildren().addAll(
            topBar,
            contentScroll
    );

    VBox.setVgrow(
            contentScroll,
            Priority.ALWAYS
    );

    root.setLeft(
            sidebar
    );

    root.setCenter(
            mainArea
    );

    showDashboard();

    return root;
}

private HBox createMainTopBar() {

    HBox topBar =
            new HBox(15);

    topBar.setAlignment(
            Pos.CENTER_LEFT
    );

    topBar.setPadding(
            new Insets(
                    14,
                    24,
                    14,
                    24
            )
    );

    topBar.setStyle(
            "-fx-background-color: " +
            SURFACE +
            ";" +
            "-fx-border-color: " +
            BORDER +
            ";" +
            "-fx-border-width: 0 0 1 0;"
    );

    sidebarToggleButton =
            new Button("=");

    sidebarToggleButton.setPrefSize(
            42,
            42
    );

    sidebarToggleButton.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    22
            )
    );

    sidebarToggleButton.setTextFill(
            Color.web(HEADING)
    );

    sidebarToggleButton.setStyle(
            "-fx-background-color: " +
            SECONDARY +
            ";" +
            "-fx-background-radius: 9;" +
            "-fx-cursor: hand;"
    );

    addButtonHover(
            sidebarToggleButton,
            SECONDARY,
            SURFACE
    );

    sidebarToggleButton.setOnAction(
            event ->
                    toggleSidebar()
    );

    Label title =
            new Label(
                    "Dashboard"
            );

    title.setTextFill(
            Color.web(HEADING)
    );

    title.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    22
            )
    );

    Region spacer =
            new Region();

    HBox.setHgrow(
            spacer,
            Priority.ALWAYS
    );

    // Admin profile
    HBox profile =
            createAdminProfile();

    topBar.getChildren().addAll(
            sidebarToggleButton,
            title,
            spacer,
            profile
    );

    return topBar;
}

private HBox createAdminProfile() {

    HBox profile = new HBox(10);

    profile.setAlignment(
            Pos.CENTER_LEFT
    );

    profile.setPadding(new Insets(7, 16, 7, 8));

    profile.setStyle(
            "-fx-background-color: " +
            SURFACE +
            ";" +
            "-fx-border-color: " +
            BORDER +
            ";" +
            "-fx-border-radius: 25;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;"
    );

    Circle avatar =
            new Circle(
                    20,
                    Color.web("#2563EB")
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
                    16
            )
    );

    StackPane avatarBox =
            new StackPane(
                    avatar,
                    initial
            );

    avatarBox.setPrefSize(
            40,
            40
    );

    VBox adminInfo =
            new VBox(1);

    Label adminName =
            new Label(
                    "Admin"
            );

    adminName.setTextFill(
            Color.web(HEADING)
    );

    adminName.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    15
            )
    );

    Label adminRole =
            new Label(
                    "Super Administrator"
            );

    adminRole.setTextFill(
            Color.web(TEXT)
    );

    adminRole.setFont(
            Font.font(
                    "Arial",
                    12
            )
    );

    adminInfo.getChildren().addAll(
            adminName,
            adminRole
    );

    profile.getChildren().addAll(
            avatarBox,
            adminInfo
    );

    // =====================================================
    // HOVER EFFECT
    // =====================================================

    profile.setOnMouseEntered(
            event ->
                    profile.setStyle(
                            "-fx-background-color: white;" +
                            "-fx-border-color: " +
                            ORANGE +
                            ";" +
                            "-fx-border-radius: 25;" +
                            "-fx-background-radius: 25;" +
                            "-fx-cursor: hand;"
                    )
    );

    profile.setOnMouseExited(
            event ->
                    profile.setStyle(
                            "-fx-background-color: " +
                            SURFACE +
                            ";" +
                            "-fx-border-color: " +
                            BORDER +
                            ";" +
                            "-fx-border-radius: 25;" +
                            "-fx-background-radius: 25;" +
                            "-fx-cursor: hand;"
                    )
    );

    return profile;
}

    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox createSidebar() {

        VBox side =
                new VBox();

        side.setPrefWidth(
                270
        );

        side.setMinWidth(
                270
        );

        side.setMaxWidth(
                270
        );

        side.setPadding(
                new Insets(
                        16,
                        12,
                        25,
                        12
                )
        );

        side.setSpacing(
                5
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

        brand.setAlignment(
                Pos.CENTER_LEFT
        );

        brand.setPadding(
                new Insets(
                        4,
                        11,
                        22,
                        11
                )
        );

        Label logo =
                new Label("✓");

        logo.setTextFill(
                Color.WHITE
        );

        logo.setAlignment(
                Pos.CENTER
        );

        logo.setPrefSize(
                46,
                46
        );

        logo.setStyle(
                "-fx-background-color: #2563EB;" +
                "-fx-background-radius: 50%;"
        );

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        brandTitle =
                new Label(
                        "ROADGUARDIAN"
                );

        brandTitle.setTextFill(
                Color.web(HEADING)
        );

        brandTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        brandSubtitle =
                new Label(
                        "ADMIN PANEL"
                );

        brandSubtitle.setTextFill(
                Color.web(GREEN)
        );

        brandSubtitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        brand.getChildren().addAll(
                logo,
                brandTitle,
                brandSubtitle
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
                event ->
                        showMechanics()
        );

        vehiclesButton.setOnAction(
                event ->
                        showVehicles()
        );

        serviceRequestButton.setOnAction(
                event ->
                        showServiceRequests()
        );

        sosButton.setOnAction(
                event ->
                        showSOSRequests()
        );

        servicesButton.setOnAction(
                event ->
                        showServices()
        );

        reviewsButton.setOnAction(
                event ->
                        showReviews()
        );

        complaintsButton.setOnAction(
                event ->
                        showComplaints()
        );

        notificationsButton.setOnAction(
                event ->
                        showNotifications()
        );

        reportsButton.setOnAction(
                event ->
                        showReports()
        );

        settingsButton.setOnAction(
                event ->
                        showSettings()
        );

        return side;
    }

    // =========================================================
    // SIDEBAR TOGGLE
    // The single top-bar "=" button hides/shows the sidebar.
    // =========================================================

   private void toggleSidebar() {

    sidebarCollapsed = !sidebarCollapsed;

    if (sidebarCollapsed) {

        // Hide complete sidebar
        sidebar.setVisible(false);
        sidebar.setManaged(false);

    } else {

        // Show complete sidebar
        sidebar.setVisible(true);
        sidebar.setManaged(true);
    }
}

    // =========================================================
    // NAVIGATION TEXT VISIBILITY
    // =========================================================

    private void setNavigationTextVisible(
            boolean visible
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

            if (
                    button.getGraphic()
                            instanceof HBox
            ) {

                HBox content =
                        (HBox)
                        button.getGraphic();

                if (
                        content.getChildren()
                                .size() > 1
                ) {

                    javafx.scene.Node textNode =
                            content.getChildren()
                                    .get(1);

                    textNode.setVisible(
                            visible
                    );

                    textNode.setManaged(
                            visible
                    );
                }
            }

            if (visible) {

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

            } else {

                button.setAlignment(
                        Pos.CENTER
                );

                button.setPadding(
                        new Insets(
                                0
                        )
                );
            }
        }
    }

    // =========================================================
    // SHOW SERVICE REQUESTS
    // =========================================================

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

    // =========================================================
    // SHOW SOS REQUESTS
    // =========================================================

    private void showSOSRequests() {

        setActive(
                sosButton
        );

        SOSRequestManagementPage page =
                new SOSRequestManagementPage();

        contentArea.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // SHOW MECHANICS
    // =========================================================

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
    // SHOW VEHICLES
    // =========================================================

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

    // =========================================================
    // SHOW SERVICES
    // =========================================================

    private void showServices() {

        setActive(
                servicesButton
        );

        ServiceManagementPage page =
                new ServiceManagementPage();

        contentArea.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // SHOW REVIEWS
    // =========================================================

    private void showReviews() {

        setActive(
                reviewsButton
        );

        ReviewManagementPage page =
                new ReviewManagementPage();

        contentArea.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // SHOW COMPLAINTS
    // =========================================================

    private void showComplaints() {

        setActive(
                complaintsButton
        );

        ComplaintManagementPage page =
                new ComplaintManagementPage();

        contentArea.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // SHOW NOTIFICATIONS
    // =========================================================

    private void showNotifications() {

        setActive(
                notificationsButton
        );

        NotificationManagementPage page =
                new NotificationManagementPage();

        contentArea.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // SHOW REPORTS
    // =========================================================

    private void showReports() {

        setActive(
                reportsButton
        );

        ReportManagementPage page =
                new ReportManagementPage();

        contentArea.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // SHOW SETTINGS
    // =========================================================

    private void showSettings() {

        setActive(
                settingsButton
        );

        SettingsPage page =
                new SettingsPage();

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
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
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
                                "-fx-background-radius: 9;" +
                                "-fx-cursor: hand;"
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
                                "-fx-background-radius: 9;" +
                                "-fx-cursor: hand;"
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
                    "-fx-background-radius: 9;" +
                    "-fx-cursor: hand;"
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
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
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
                new Insets(
                        15
                )
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
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                RED +
                                "18;" +
                                "-fx-background-radius: 9;" +
                                "-fx-cursor: hand;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-cursor: hand;"
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

        if (contentScroll != null) {

            contentScroll.setVvalue(
                    0
            );
        }
    }

    // =========================================================
    // DASHBOARD PAGE
    // =========================================================

    private VBox createDashboard() {

        VBox page =
                new VBox(24);

        page.setPadding(
                new Insets(
                        28,
                        32,
                        45,
                        32
                )
        );

        page.setFillWidth(
                true
        );

        page.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                createHeader(
                "RoadGuardian Overview",
                "Here's a quick overview of what's happening across your roadside assistance platform."
        );

        // =====================================================
        // STATS
        // =====================================================

        HBox stats =
                createStats();

        // =====================================================
        // MIDDLE
        // =====================================================

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

        // =====================================================
        // BOTTOM
        // =====================================================

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

        header.setPadding(
                new Insets(
                        0,
                        0,
                        8,
                        0
                )
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
        header.getChildren().addAll(textBox, spacer);

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
                        ORANGE
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
                        BLUE
                ),

                createStatCard(
                        "▤",
                        String.valueOf(
                                serviceRequests
                        ),
                        "Service Requests",
                        "Total requests",
                        ORANGE
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

        Label live =
                new Label(
                        "Live Overview"
                );

        live.setTextFill(
                Color.web(GREEN)
        );

        live.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        titleRow.getChildren().addAll(
                title,
                spacer,
                live
        );

        int completed =
                dashboardController.getValue(
                        dashboardData,
                        "completedRequests"
                );

        int progress =
                dashboardController.getValue(
                        dashboardData,
                        "inProgressRequests"
                );

        int pending =
                dashboardController.getValue(
                        dashboardData,
                        "pendingRequests"
                );

        int cancelled =
                dashboardController.getValue(
                        dashboardData,
                        "cancelledRequests"
                );

        int total =
                dashboardController.getValue(
                        dashboardData,
                        "serviceRequests"
                );

        if (total == 0) {

            total =
                    completed +
                    progress +
                    pending +
                    cancelled;
        }

        HBox content =
                new HBox(24);

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane chart =
                createDonutChart();

        chart.setPrefWidth(
                210
        );

        VBox statusBox =
                new VBox(12);

        statusBox.setPrefWidth(
                190
        );

        statusBox.getChildren().addAll(

                requestStatusRow(
                        "Pending",
                        pending,
                        BLUE
                ),

                requestStatusRow(
                        "In Progress",
                        progress,
                        GREEN
                ),

                requestStatusRow(
                        "Completed",
                        completed,
                        ORANGE
                ),

                requestStatusRow(
                        "Cancelled",
                        cancelled,
                        RED
                )
        );

        VBox actionBox =
                new VBox(9);

        actionBox.setAlignment(
                Pos.CENTER
        );

        Label totalLabel =
                new Label(
                        "Total Requests"
                );

        totalLabel.setTextFill(
                Color.web(TEXT)
        );

        totalLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Label totalValue =
                new Label(
                        String.valueOf(total)
                );

        totalValue.setTextFill(
                Color.web(HEADING)
        );

        totalValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        Label info =
                new Label(
                        "Monitor and manage\nservice requests."
                );

        info.setWrapText(
                true
        );

        info.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        info.setTextFill(
                Color.web(TEXT)
        );

        info.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        Button manageButton =
                new Button(
                        "Manage Requests  →"
                );

        stylePrimaryButton(
                manageButton,
                ORANGE,
                ORANGE_HOVER
        );

        manageButton.setOnAction(
                event ->
                        showServiceRequests()
        );

        addButtonHover(
                manageButton,
                ORANGE,
                ORANGE_HOVER
        );

        actionBox.getChildren().addAll(
                totalLabel,
                totalValue,
                info,
                manageButton
        );

        content.getChildren().addAll(
                chart,
                statusBox,
                actionBox
        );

        HBox.setHgrow(
                chart,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                statusBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                actionBox,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                titleRow,
                content
        );

        return card;
    }

    // =========================================================
    // REQUEST STATUS ROW
    // =========================================================

    private HBox requestStatusRow(
            String title,
            int value,
            String color
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

        Label name =
                new Label(
                        title
                );

        name.setTextFill(
                Color.web(HEADING)
        );

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label count =
                new Label(
                        String.valueOf(value)
                );

        count.setTextFill(
                Color.web(HEADING)
        );

        count.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        row.getChildren().addAll(
                dot,
                name,
                spacer,
                count
        );

        return row;
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

        int completed =
                dashboardController.getValue(
                        dashboardData,
                        "completedRequests"
                );

        int progress =
                dashboardController.getValue(
                        dashboardData,
                        "inProgressRequests"
                );

        int pending =
                dashboardController.getValue(
                        dashboardData,
                        "pendingRequests"
                );

        int cancelled =
                dashboardController.getValue(
                        dashboardData,
                        "cancelledRequests"
                );

        int total =
                completed +
                progress +
                pending +
                cancelled;

        if (total == 0) {

            Circle outer =
                    new Circle(
                            75,
                            Color.web(BORDER)
                    );

            Circle inner =
                    new Circle(
                            43,
                            Color.web(SURFACE)
                    );

            VBox center =
                    new VBox(1);

            center.setAlignment(
                    Pos.CENTER
            );

            Label text =
                    new Label(
                            "Total"
                    );

            text.setTextFill(
                    Color.web(TEXT)
            );

            text.setFont(
                    Font.font(
                            "Arial",
                            13
                    )
            );

            Label value =
                    new Label(
                            "0"
                    );

            value.setTextFill(
                    Color.web(HEADING)
            );

            value.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            22
                    )
            );

            center.getChildren().addAll(
                    text,
                    value
            );

            chart.getChildren().addAll(
                    outer,
                    inner,
                    center
            );

            return chart;
        }

        double completedAngle =
                ((double) completed / total) * 360;

        double progressAngle =
                ((double) progress / total) * 360;

        double pendingAngle =
                ((double) pending / total) * 360;

        double cancelledAngle =
                ((double) cancelled / total) * 360;

        double startAngle =
                90;

        javafx.scene.shape.Arc completedArc =
                new javafx.scene.shape.Arc(
                        0,
                        0,
                        75,
                        75,
                        startAngle,
                        -completedAngle
                );

        completedArc.setType(
                javafx.scene.shape.ArcType.ROUND
        );

        completedArc.setFill(
                Color.web(ORANGE)
        );

        startAngle -=
                completedAngle;

        javafx.scene.shape.Arc progressArc =
                new javafx.scene.shape.Arc(
                        0,
                        0,
                        75,
                        75,
                        startAngle,
                        -progressAngle
                );

        progressArc.setType(
                javafx.scene.shape.ArcType.ROUND
        );

        progressArc.setFill(
                Color.web(GREEN)
        );

        startAngle -=
                progressAngle;

        javafx.scene.shape.Arc pendingArc =
                new javafx.scene.shape.Arc(
                        0,
                        0,
                        75,
                        75,
                        startAngle,
                        -pendingAngle
                );

        pendingArc.setType(
                javafx.scene.shape.ArcType.ROUND
        );

        pendingArc.setFill(
                Color.web(BLUE)
        );

        startAngle -=
                pendingAngle;

        javafx.scene.shape.Arc cancelledArc =
                new javafx.scene.shape.Arc(
                        0,
                        0,
                        75,
                        75,
                        startAngle,
                        -cancelledAngle
                );

        cancelledArc.setType(
                javafx.scene.shape.ArcType.ROUND
        );

        cancelledArc.setFill(
                Color.web(RED)
        );

        Circle inner =
                new Circle(
                        43,
                        Color.web(SURFACE)
                );

        VBox center =
                new VBox(1);

        center.setAlignment(
                Pos.CENTER
        );

        Label text =
                new Label(
                        "Total"
                );

        text.setTextFill(
                Color.web(TEXT)
        );

        text.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        Label value =
                new Label(
                        String.valueOf(total)
                );

        value.setTextFill(
                Color.web(HEADING)
        );

        value.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        center.getChildren().addAll(
                text,
                value
        );

        chart.getChildren().addAll(
                completedArc,
                progressArc,
                pendingArc,
                cancelledArc,
                inner,
                center
        );

        return chart;
    }

    // =========================================================
    // SOS CARD
    // =========================================================

    private VBox createSOSCard() {

        VBox card =
                createCard();

        int totalSOS =
                dashboardController.getValue(
                        dashboardData,
                        "sosRequests"
                );

        int activeSOS =
                dashboardController.getValue(
                        dashboardData,
                        "activeSOS"
                );

        // =====================================================
        // HEADER
        // =====================================================

        HBox titleRow =
                new HBox();

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                sectionTitle(
                        "SOS Emergency Requests"
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label emergency =
                new Label(
                        "● Emergency"
                );

        emergency.setTextFill(
                Color.web(RED)
        );

        emergency.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        titleRow.getChildren().addAll(
                title,
                spacer,
                emergency
        );

        // =====================================================
        // TOTAL
        // =====================================================

        VBox totalBox =
                new VBox(3);

        Label totalTitle =
                new Label(
                        "Total SOS Requests"
                );

        totalTitle.setTextFill(
                Color.web(TEXT)
        );

        totalTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Label totalValue =
                new Label(
                        String.valueOf(
                                totalSOS
                        )
                );

        totalValue.setTextFill(
                Color.web(HEADING)
        );

        totalValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        totalBox.getChildren().addAll(
                totalTitle,
                totalValue
        );

        // =====================================================
        // ACTIVE SOS
        // =====================================================

        VBox activeBox =
                new VBox(3);

        Label activeTitle =
                new Label(
                        "Active SOS"
                );

        activeTitle.setTextFill(
                Color.web(TEXT)
        );

        activeTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Label activeValue =
                new Label(
                        String.valueOf(
                                activeSOS
                        )
                );

        activeValue.setTextFill(
                Color.web(
                        activeSOS > 0
                                ? RED
                                : GREEN
                )
        );

        activeValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        activeBox.getChildren().addAll(
                activeTitle,
                activeValue
        );

        // =====================================================
        // STATUS MESSAGE
        // =====================================================

        VBox infoBox =
                new VBox(6);

        infoBox.setAlignment(
                Pos.CENTER
        );

        Label statusIcon =
                new Label(
                        activeSOS > 0
                                ? "!"
                                : "✓"
                );

        statusIcon.setTextFill(
                Color.WHITE
        );

        statusIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        statusIcon.setAlignment(
                Pos.CENTER
        );

        statusIcon.setPrefSize(
                44,
                44
        );

        statusIcon.setStyle(
                "-fx-background-color: " +
                (
                        activeSOS > 0
                                ? RED
                                : GREEN
                ) +
                ";" +
                "-fx-background-radius: 50%;"
        );

        Label message =
                new Label(
                        activeSOS > 0
                                ? "Emergency requests need attention."
                                : "No active emergency requests."
                );

        message.setWrapText(
                true
        );

        message.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        message.setTextFill(
                Color.web(
                        activeSOS > 0
                                ? RED
                                : TEXT
                )
        );

        message.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        infoBox.getChildren().addAll(
                statusIcon,
                message
        );

        // =====================================================
        // BUTTON
        // =====================================================

        Button openButton =
                new Button(
                        "Open SOS Requests  →"
                );

        stylePrimaryButton(
                openButton,
                RED,
                RED_HOVER
        );

        addButtonHover(
                openButton,
                RED,
                RED_HOVER
        );

        openButton.setOnAction(
                event ->
                        showSOSRequests()
        );

        // =====================================================
        // CONTENT
        // =====================================================

        HBox content =
                new HBox(20);

        content.setAlignment(
                Pos.CENTER
        );

        content.getChildren().addAll(
                totalBox,
                activeBox,
                infoBox,
                openButton
        );

        HBox.setHgrow(
                totalBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                activeBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                infoBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                openButton,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                titleRow,
                content
        );

        // =====================================================
        // ACTIVE SOS HIGHLIGHT
        // =====================================================

        if (
                activeSOS > 0
        ) {

            card.setStyle(
                    "-fx-background-color: #FFF7F7;" +
                    "-fx-border-color: #FCA5A5;" +
                    "-fx-border-width: 1.5;" +
                    "-fx-border-radius: 13;" +
                    "-fx-background-radius: 13;"
            );
        }

        return card;
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
                        "View All  →"
                );

        stylePrimaryButton(
                view,
                ORANGE,
                ORANGE_HOVER
        );

        addButtonHover(
                view,
                ORANGE,
                ORANGE_HOVER
        );

        view.setOnAction(
                event ->
                        showServiceRequests()
        );

        header.getChildren().addAll(
                title,
                spacer,
                view
        );

        // =====================================================
        // REQUEST LIST
        // =====================================================

        VBox list = new VBox(10);

        list.getChildren().addAll(

                recentRequestRow(
                        "SR1256",
                        "Rahul Sharma",
                        "MH12AB1234",
                        "Pending",
                        BLUE
                ),

                recentRequestRow(
                        "SR1255",
                        "Akash Patil",
                        "MH14CD5678",
                        "In Progress",
                        GREEN
                ),

                recentRequestRow(
                        "SR1254",
                        "Pooja Mehta",
                        "MH12EF9012",
                        "Completed",
                        ORANGE
                ),

                recentRequestRow(
                        "SR1253",
                        "Vikram Joshi",
                        "MH15GH3456",
                        "Pending",
                        BLUE
                )
        );

        card.getChildren().addAll(
                header,
                list
        );

        return card;
    }

    // =========================================================
    // RECENT REQUEST ROW
    // =========================================================

    private HBox recentRequestRow(
            String requestId,
            String customer,
            String vehicle,
            String status,
            String statusColor
    ) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        11,
                        10,
                        11,
                        10
                )
        );

        row.setStyle(
                "-fx-background-color: " +
                SECONDARY +
                ";" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );

        VBox idBox =
                new VBox(2);

        Label id =
                new Label(
                        requestId
                );

        id.setTextFill(
                Color.web(HEADING)
        );

        id.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Label customerLabel =
                new Label(
                        customer
                );

        customerLabel.setTextFill(
                Color.web(TEXT)
        );

        customerLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        idBox.getChildren().addAll(
                id,
                customerLabel
        );

        VBox vehicleBox =
                new VBox(2);

        Label vehicleTitle =
                new Label(
                        "Vehicle"
                );

        vehicleTitle.setTextFill(
                Color.web(TEXT)
        );

        vehicleTitle.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        Label vehicleLabel =
                new Label(
                        vehicle
                );

        vehicleLabel.setTextFill(
                Color.web(HEADING)
        );

        vehicleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        vehicleBox.getChildren().addAll(
                vehicleTitle,
                vehicleLabel
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label statusLabel =
                new Label(
                        status
                );

        statusLabel.setTextFill(
                Color.WHITE
        );

        statusLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        statusLabel.setPadding(
                new Insets(
                        6,
                        10,
                        6,
                        10
                )
        );

        statusLabel.setStyle(
                "-fx-background-color: " +
                statusColor +
                ";" +
                "-fx-background-radius: 12;"
        );

        row.getChildren().addAll(
                idBox,
                vehicleBox,
                spacer,
                statusLabel
        );

        row.setOnMouseEntered(
                event ->
                        row.setStyle(
                                "-fx-background-color: " +
                                SURFACE +
                                ";" +
                                "-fx-background-radius: 9;" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 9;" +
                                "-fx-cursor: hand;"
                        )
        );

        row.setOnMouseExited(
                event ->
                        row.setStyle(
                                "-fx-background-color: " +
                                SECONDARY +
                                ";" +
                                "-fx-background-radius: 9;" +
                                "-fx-cursor: hand;"
                        )
        );

        return row;
    }

    // =========================================================
    // QUICK ACTIONS
    // =========================================================

    private VBox createQuickActions() {
        VBox card = createCard();

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = sectionTitle("Quick Actions");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label hint = new Label("Common admin tasks");
        hint.setTextFill(Color.web(TEXT));
        hint.setFont(Font.font("Arial", 13));

        header.getChildren().addAll(title, spacer, hint);

        GridPane grid = new GridPane();
        grid.setHgap(14);
        grid.setVgap(14);
        grid.setMaxWidth(Double.MAX_VALUE);

        VBox addCustomer = quickAction("♙", "Add Customer", ORANGE);
        VBox addMechanic = quickAction("⚒", "Add Mechanic", GREEN);
        VBox addVehicle = quickAction("▰", "Add Vehicle", BLUE);
        VBox newService = quickAction("▤", "Service Requests", ORANGE);
        VBox newSOS = quickAction("SOS", "SOS Requests", RED);
        VBox notification = quickAction("♢", "Notifications", GREEN);

        addCustomer.setOnMouseClicked(event -> showCustomers());
        addMechanic.setOnMouseClicked(event -> showMechanics());
        addVehicle.setOnMouseClicked(event -> showVehicles());
        newService.setOnMouseClicked(event -> showServiceRequests());
        newSOS.setOnMouseClicked(event -> showSOSRequests());
        notification.setOnMouseClicked(event -> showNotifications());

        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        ColumnConstraints c3 = new ColumnConstraints();

        c1.setPercentWidth(33.33);
        c2.setPercentWidth(33.33);
        c3.setPercentWidth(33.34);

        c1.setHgrow(Priority.ALWAYS);
        c2.setHgrow(Priority.ALWAYS);
        c3.setHgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(c1, c2, c3);

        grid.add(addCustomer, 0, 0);
        grid.add(addMechanic, 1, 0);
        grid.add(addVehicle, 2, 0);
        grid.add(newService, 0, 1);
        grid.add(newSOS, 1, 1);
        grid.add(notification, 2, 1);

        card.getChildren().addAll(header, grid);
        VBox.setVgrow(grid, Priority.ALWAYS);

        return card;
    }

    // =========================================================
    // QUICK ACTION
    // =========================================================

    private VBox quickAction(String icon, String text, String color) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setMaxWidth(Double.MAX_VALUE);
        box.setPrefHeight(122);
        box.setMinHeight(112);
        box.setPadding(new Insets(16));

        box.setStyle(
                "-fx-background-color: " + SECONDARY + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;" +
                "-fx-cursor: hand;"
        );

        Label iconLabel = new Label(icon);
        iconLabel.setTextFill(Color.web(color));
        iconLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        Label textLabel = new Label(text);
        textLabel.setTextFill(Color.web(HEADING));
        textLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        textLabel.setWrapText(true);
        textLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Label arrow = new Label("→");
        arrow.setTextFill(Color.web(TEXT));
        arrow.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        box.getChildren().addAll(iconLabel, textLabel, arrow);

        box.setOnMouseEntered(event -> {
            box.setStyle(
                    "-fx-background-color: " + SURFACE + ";" +
                    "-fx-border-color: " + color + ";" +
                    "-fx-border-width: 1.5;" +
                    "-fx-border-radius: 12;" +
                    "-fx-background-radius: 12;" +
                    "-fx-cursor: hand;"
            );
            box.setScaleX(1.03);
            box.setScaleY(1.03);
        });

        box.setOnMouseExited(event -> {
            box.setStyle(
                    "-fx-background-color: " + SECONDARY + ";" +
                    "-fx-border-color: " + BORDER + ";" +
                    "-fx-border-radius: 12;" +
                    "-fx-background-radius: 12;" +
                    "-fx-cursor: hand;"
            );
            box.setScaleX(1.0);
            box.setScaleY(1.0);
        });

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
    // PRIMARY BUTTON STYLE
    // =========================================================

    private void stylePrimaryButton(
            Button button,
            String normalColor,
            String hoverColor
    ) {

        button.setTextFill(
                Color.WHITE
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setPrefHeight(
                40
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
                "-fx-background-color: " +
                normalColor +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // BUTTON HOVER
    // =========================================================

    private void addButtonHover(
            Button button,
            String normalColor,
            String hoverColor
    ) {

        button.setOnMouseEntered(
                event -> {

                    button.setStyle(
                            "-fx-background-color: " +
                            hoverColor +
                            ";" +
                            "-fx-background-radius: 8;" +
                            "-fx-cursor: hand;"
                    );

                    button.setScaleX(
                            1.03
                    );

                    button.setScaleY(
                            1.03
                    );
                }
        );

        button.setOnMouseExited(
                event -> {

                    button.setStyle(
                            "-fx-background-color: " +
                            normalColor +
                            ";" +
                            "-fx-background-radius: 8;" +
                            "-fx-cursor: hand;"
                    );

                    button.setScaleX(
                            1.0
                    );

                    button.setScaleY(
                            1.0
                    );
                }
        );
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
}
package project.ui.user.DashBoard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.ui.user.Assistance.AIDiagnosisPage;
import project.ui.user.Assistance.CostEstimatorPage;
import project.ui.user.Assistance.MechanicsPage;
import project.ui.user.Assistance.SOSPage;
import project.ui.user.Assistance.TowTruckPage;
import project.ui.user.Tracking.LiveMapPage;

import java.lang.reflect.Method;

/**
 * RoadGuardian - User Dashboard
 *
 * IMPORTANT:
 * UserDashboard is NOT an Application anymore.
 *
 * It behaves like AdminDashboard:
 *
 *     UserDashboard
 *          |
 *          +---- Sidebar
 *          |
 *          +---- Top Bar
 *          |
 *          +---- Content Area
 *                    |
 *                    +---- Dashboard
 *                    +---- Assistance pages
 *                    +---- Garage pages
 *                    +---- Account pages
 *
 * Existing page UI is NOT redesigned here.
 */
public class UserDashboard {

        // ============================================================
        // ROADGUARDIAN COLOR PALETTE
        // ============================================================

        private static final String MAIN_BACKGROUND = "#D6F0F3";
        private static final String SIDEBAR = "#C4D9E0";
        private static final String CARD_SURFACE = "#EEF9FA";
        private static final String SECONDARY_SURFACE = "#DFF3F5";

        private static final String PRIMARY_ORANGE = "#F97316";
        private static final String ORANGE_HOVER = "#EA580C";

        private static final String HEADING = "#172033";
        private static final String SECONDARY_TEXT = "#526274";

        private static final String NAV_BLUE = "#2563EB";
        private static final String SUCCESS = "#16A34A";
        private static final String EMERGENCY = "#DC2626";

        private static final String BORDER = "#B8D4D9";

        // ============================================================
        // MAIN ROOT
        // ============================================================

        private BorderPane root;
        private final Runnable logoutAction;

        // ============================================================
        // SIDEBAR
        // ============================================================

        private VBox sidebar;

        private boolean sidebarCollapsed = false;

        // ============================================================
        // CONTENT
        // ============================================================

        private VBox contentArea;

        private ScrollPane contentScroll;

        // ============================================================
        // NAVIGATION BUTTONS
        // ============================================================

        private Button dashboardButton;

        private Button sosButton;
        private Button liveMapButton;

        private Button aiDiagnosisButton;
        private Button mechanicsButton;
        private Button towTruckButton;
        private Button costEstimatorButton;

        private Button vehiclesButton;
        private Button serviceHistoryButton;
        private Button documentsButton;

        private Button womenSafetyButton;
        private Button notificationsButton;
        private Button settingsButton;

        // ============================================================
        // TOP BAR
        // ============================================================

        private Button sidebarToggleButton;

        // ============================================================
        // CONSTRUCTOR
        // ============================================================


        public UserDashboard() {
                this(() -> {});
        }

        public UserDashboard(Runnable logoutAction) {
                this.logoutAction = logoutAction;
        }

        // ============================================================
        // GET VIEW
        // Same concept as AdminDashboard.getView()
        // ============================================================

        public BorderPane getView() {

                root = new BorderPane();

                root.setStyle(
                        "-fx-background-color: " +
                                MAIN_BACKGROUND +
                                ";"
                );

                // ---------------- SIDEBAR ----------------

                sidebar = createSidebar();

                // ---------------- MAIN AREA ----------------

                VBox mainArea = new VBox();

                mainArea.setFillWidth(true);

                // ---------------- TOP BAR ----------------

                HBox topBar = createTopBar();

                // ---------------- CONTENT AREA ----------------

                contentArea = new VBox();

                contentArea.setFillWidth(true);

                contentScroll = new ScrollPane();

                contentScroll.setContent(contentArea);

                contentScroll.setFitToWidth(true);

                contentScroll.setHbarPolicy(
                        ScrollPane.ScrollBarPolicy.NEVER
                );

                contentScroll.setVbarPolicy(
                        ScrollPane.ScrollBarPolicy.AS_NEEDED
                );

                contentScroll.setStyle(
                        "-fx-background-color: " +
                                MAIN_BACKGROUND +
                                ";" +
                                "-fx-border-color: transparent;"
                );

                mainArea.getChildren().addAll(
                        topBar,
                        contentScroll
                );

                VBox.setVgrow(
                        contentScroll,
                        Priority.ALWAYS
                );

                // ---------------- ROOT ----------------

                root.setLeft(sidebar);

                root.setCenter(mainArea);

                // ---------------- DEFAULT PAGE ----------------

                showDashboard();

                return root;
        }

        // ============================================================
        // TOP BAR
        // ============================================================

        private HBox createTopBar() {

                HBox topBar = new HBox(15);

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
                                CARD_SURFACE +
                                ";" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-width: 0 0 1 0;"
                );

                // ========================================================
                // SIDEBAR TOGGLE
                // ========================================================

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
                                SECONDARY_SURFACE +
                                ";" +
                                "-fx-background-radius: 9;" +
                                "-fx-cursor: hand;"
                );

                sidebarToggleButton.setOnMouseEntered(
                        e ->
                                sidebarToggleButton.setStyle(
                                        "-fx-background-color: white;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-cursor: hand;"
                                )
                );

                sidebarToggleButton.setOnMouseExited(
                        e ->
                                sidebarToggleButton.setStyle(
                                        "-fx-background-color: " +
                                                SECONDARY_SURFACE +
                                                ";" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-cursor: hand;"
                                )
                );

                sidebarToggleButton.setOnAction(
                        e -> toggleSidebar()
                );

                // ========================================================
                // PAGE TITLE
                // ========================================================

                Label title =
                        new Label("Dashboard");

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

                // ========================================================
                // USER PROFILE
                // ========================================================

                HBox profile =
                        createUserProfile();

                topBar.getChildren().addAll(
                        sidebarToggleButton,
                        title,
                        spacer,
                        profile
                );

                return topBar;
        }

        // ============================================================
        // USER PROFILE
        // ============================================================

        private HBox createUserProfile() {

                HBox profile =
                        new HBox(10);

                profile.setAlignment(
                        Pos.CENTER_LEFT
                );

                profile.setPadding(
                        new Insets(
                                7,
                                16,
                                7,
                                8
                        )
                );

                profile.setStyle(
                        "-fx-background-color: " +
                                CARD_SURFACE +
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
                                Color.web(NAV_BLUE)
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

                VBox userInfo =
                        new VBox(1);

                Label userName =
                        new Label("Aarav Nair");

                userName.setTextFill(
                        Color.web(HEADING)
                );

                userName.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                15
                        )
                );

                Label userRole =
                        new Label(
                                "Customer · Pune"
                        );

                userRole.setTextFill(
                        Color.web(SECONDARY_TEXT)
                );

                userRole.setFont(
                        Font.font(
                                "Arial",
                                12
                        )
                );

                userInfo.getChildren().addAll(
                        userName,
                        userRole
                );

                profile.getChildren().addAll(
                        avatarBox,
                        userInfo
                );

                profile.setOnMouseEntered(
                        e ->
                                profile.setStyle(
                                        "-fx-background-color: white;" +
                                                "-fx-border-color: " +
                                                NAV_BLUE +
                                                ";" +
                                                "-fx-border-radius: 25;" +
                                                "-fx-background-radius: 25;" +
                                                "-fx-cursor: hand;"
                                )
                );

                profile.setOnMouseExited(
                        e ->
                                profile.setStyle(
                                        "-fx-background-color: " +
                                                CARD_SURFACE +
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

        // ============================================================
        // SIDEBAR
        // ============================================================

        private VBox createSidebar() {

                VBox side =
                        new VBox();

                side.setPrefWidth(270);

                side.setMinWidth(270);

                side.setMaxWidth(270);

                side.setPadding(
                        new Insets(
                                16,
                                12,
                                25,
                                12
                        )
                );

                side.setSpacing(5);

                side.setStyle(
                        "-fx-background-color: " +
                                SIDEBAR +
                                ";" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-width: 0 1 0 0;"
                );

                // ========================================================
                // BRAND
                // ========================================================

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
                        "-fx-background-color: " +
                                NAV_BLUE +
                                ";" +
                                "-fx-background-radius: 50%;"
                );

                logo.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                27
                        )
                );

                Label brandTitle =
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

                Label brandSubtitle =
                        new Label(
                                "CUSTOMER PANEL"
                        );

                brandSubtitle.setTextFill(
                        Color.web(PRIMARY_ORANGE)
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

                // ========================================================
                // OVERVIEW
                // ========================================================

                Label overview =
                        createSidebarSection(
                                "OVERVIEW"
                        );

                side.getChildren().add(
                        overview
                );

                dashboardButton =
                        createNavButton(
                                "▣",
                                "Dashboard"
                        );

                sosButton =
                        createNavButton(
                                "SOS",
                                "Emergency SOS"
                        );

                liveMapButton =
                        createNavButton(
                                "◇",
                                "Live Map"
                        );

                side.getChildren().addAll(
                        dashboardButton,
                        sosButton,
                        liveMapButton
                );

                // ========================================================
                // ASSISTANCE
                // ========================================================

                side.getChildren().add(
                        createSidebarSection(
                                "ASSISTANCE"
                        )
                );

                aiDiagnosisButton =
                        createNavButton(
                                "♧",
                                "AI Diagnosis"
                        );

                mechanicsButton =
                        createNavButton(
                                "⚒",
                                "Mechanics"
                        );

                towTruckButton =
                        createNavButton(
                                "▱",
                                "Tow Truck"
                        );

                costEstimatorButton =
                        createNavButton(
                                "▤",
                                "Cost Estimator"
                        );

                side.getChildren().addAll(
                        aiDiagnosisButton,
                        mechanicsButton,
                        towTruckButton,
                        costEstimatorButton
                );

                // ========================================================
                // GARAGE
                // ========================================================

                side.getChildren().add(
                        createSidebarSection(
                                "GARAGE"
                        )
                );

                vehiclesButton =
                        createNavButton(
                                "▱",
                                "My Vehicles"
                        );

                serviceHistoryButton =
                        createNavButton(
                                "◷",
                                "Service History"
                        );

                documentsButton =
                        createNavButton(
                                "▧",
                                "Documents"
                        );

                side.getChildren().addAll(
                        vehiclesButton,
                        serviceHistoryButton,
                        documentsButton
                );

                // ========================================================
                // ACCOUNT
                // ========================================================

                side.getChildren().add(
                        createSidebarSection(
                                "ACCOUNT"
                        )
                );

                womenSafetyButton =
                        createNavButton(
                                "♙",
                                "Women Safety"
                        );

                notificationsButton =
                        createNavButton(
                                "♧",
                                "Notifications"
                        );

                settingsButton =
                        createNavButton(
                                "⚙",
                                "Settings"
                        );

                side.getChildren().addAll(
                        womenSafetyButton,
                        notificationsButton,
                        settingsButton
                );

                // ========================================================
                // SPACER
                // ========================================================

                Region spacer =
                        new Region();

                VBox.setVgrow(
                        spacer,
                        Priority.ALWAYS
                );

                side.getChildren().add(
                        spacer
                );

                // ========================================================
                // LOGOUT
                // ========================================================

                Button logout =
                        createLogoutButton();

                side.getChildren().add(
                        logout
                );

                // ========================================================
                // EVENTS
                // ========================================================

                dashboardButton.setOnAction(
                        e -> showDashboard()
                );

                sosButton.setOnAction(
                        e -> showSOS()
                );

                liveMapButton.setOnAction(
                        e -> showLiveMap()
                );

                aiDiagnosisButton.setOnAction(
                        e -> showAIDiagnosis()
                );

                mechanicsButton.setOnAction(
                        e -> showMechanics()
                );

                towTruckButton.setOnAction(
                        e -> showTowTruck()
                );

                costEstimatorButton.setOnAction(
                        e -> showCostEstimator()
                );

                /*
                 * Garage / Account pages:
                 *
                 * These methods intentionally use the existing page classes.
                 * Their UI does NOT need to be redesigned.
                 */

                vehiclesButton.setOnAction(
                        e -> showExistingUserPage(
                                "project.ui.user.Garage.VehiclePage"
                        )
                );

                serviceHistoryButton.setOnAction(
                        e -> showExistingUserPage(
                                "project.ui.user.Garage.ServiceHistoryPage"
                        )
                );

                documentsButton.setOnAction(
                        e -> showExistingUserPage(
                                "project.ui.user.Garage.DocumentsPage"
                        )
                );

                womenSafetyButton.setOnAction(
                        e -> showExistingUserPage(
                                "project.ui.user.Account.WomenSafety"
                        )
                );

                notificationsButton.setOnAction(
                        e -> showExistingUserPage(
                                "project.ui.user.Account.NotificationsPage"
                        )
                );

                settingsButton.setOnAction(
                        e -> showExistingUserPage(
                                "project.ui.user.Account.SettingsPage"
                        )
                );

                return side;
        }

        // ============================================================
        // SIDEBAR SECTION
        // ============================================================

        private Label createSidebarSection(
                String text
        ) {

                Label section =
                        new Label(text);

                section.setPadding(
                        new Insets(
                                12,
                                12,
                                7,
                                12
                        )
                );

                section.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                12
                        )
                );

                section.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                return section;
        }

        // ============================================================
        // NAVIGATION BUTTON
        // ============================================================

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
                        Color.web(
                                SECONDARY_TEXT
                        )
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
                        Color.web(
                                HEADING
                        )
                );

                textLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                15
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
                        e -> {

                                if (
                                        !button
                                                .getStyleClass()
                                                .contains("active-nav")
                                ) {

                                        button.setStyle(
                                                "-fx-background-color: " +
                                                        SECONDARY_SURFACE +
                                                        ";" +
                                                        "-fx-background-radius: 9;" +
                                                        "-fx-cursor: hand;"
                                        );
                                }
                        }
                );

                button.setOnMouseExited(
                        e -> {

                                if (
                                        !button
                                                .getStyleClass()
                                                .contains("active-nav")
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

        // ============================================================
        // SIDEBAR TOGGLE
        // ============================================================

        private void toggleSidebar() {

                sidebarCollapsed =
                        !sidebarCollapsed;

                if (sidebarCollapsed) {

                        sidebar.setVisible(false);

                        sidebar.setManaged(false);

                } else {

                        sidebar.setVisible(true);

                        sidebar.setManaged(true);
                }
        }

        // ============================================================
        // ACTIVE NAVIGATION
        // ============================================================

        private void setActive(
                Button active
        ) {

                Button[] buttons = {

                        dashboardButton,

                        sosButton,
                        liveMapButton,

                        aiDiagnosisButton,
                        mechanicsButton,
                        towTruckButton,
                        costEstimatorButton,

                        vehiclesButton,
                        serviceHistoryButton,
                        documentsButton,

                        womenSafetyButton,
                        notificationsButton,
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
                                NAV_BLUE +
                                ";" +
                                "-fx-background-radius: 9;" +
                                "-fx-cursor: hand;"
                );
        }

        // ============================================================
        // DASHBOARD
        // ============================================================

        private void showDashboard() {

                setActive(
                        dashboardButton
                );

                contentArea.getChildren().setAll(
                        createDashboardContent()
                );

                if (contentScroll != null) {

                        contentScroll.setVvalue(0);
                }
        }

        // ============================================================
        // EXISTING ASSISTANCE PAGES
        // ============================================================

        private void showSOS() {

                setActive(sosButton);

                try {

                        SOSPage page =
                                new SOSPage();

                        loadPageView(page);

                } catch (Exception ex) {

                        ex.printStackTrace();

                        showError(
                                "Unable to open Emergency SOS."
                        );
                }
        }

        // ------------------------------------------------------------

        private void showLiveMap() {

                setActive(liveMapButton);

                try {

                        /*
                         * LiveMapPage has been converted to the
                         * UserSectionPage pattern.
                         *
                         * Therefore we load its View directly.
                         * No Stage and no Scene switching here.
                         */
                        LiveMapPage page =
                                new LiveMapPage();

                        loadPageView(page);

                } catch (Exception ex) {

                        ex.printStackTrace();

                        showError(
                                "Unable to open Live Map."
                        );
                }
        }

        // ------------------------------------------------------------

        private void showAIDiagnosis() {

                setActive(aiDiagnosisButton);

                try {

                        AIDiagnosisPage page =
                                new AIDiagnosisPage();

                        loadPageView(page);

                } catch (Exception ex) {

                        ex.printStackTrace();

                        showError(
                                "Unable to open AI Diagnosis."
                        );
                }
        }

        // ------------------------------------------------------------

        private void showMechanics() {

                setActive(mechanicsButton);

                try {

                        MechanicsPage page =
                                new MechanicsPage();

                        loadPageView(page);

                } catch (Exception ex) {

                        ex.printStackTrace();

                        showError(
                                "Unable to open Mechanics."
                        );
                }
        }

        // ------------------------------------------------------------

        private void showTowTruck() {

                setActive(towTruckButton);

                try {

                        TowTruckPage page =
                                new TowTruckPage();

                        loadPageView(page);

                } catch (Exception ex) {

                        ex.printStackTrace();

                        showError(
                                "Unable to open Tow Truck."
                        );
                }
        }

        // ------------------------------------------------------------

        private void showCostEstimator() {

                setActive(costEstimatorButton);

                try {

                        CostEstimatorPage page =
                                new CostEstimatorPage();

                        loadPageView(page);

                } catch (Exception ex) {

                        ex.printStackTrace();

                        showError(
                                "Unable to open Cost Estimator."
                        );
                }
        }

        // ============================================================
        // LOAD USER SECTION PAGE
        // ============================================================
        //
        // New user pages should follow:
        //
        //     extends UserSectionPage
        //     public VBox getView()
        //
        // Older pages that still expose a Scene are also supported
        // temporarily so the dashboard can be migrated page-by-page.
        //
        // ============================================================

        private void loadPageView(
                Object page
        ) throws Exception {

                if (page == null) {

                        throw new IllegalArgumentException(
                                "Page cannot be null."
                        );
                }

                // ----------------------------------------------------
                // NEW PATTERN: getView()
                // ----------------------------------------------------

                try {

                        Method getView =
                                page.getClass().getMethod(
                                        "getView"
                                );

                        Object result =
                                getView.invoke(page);

                        if (result instanceof Node) {

                                Node pageRoot =
                                        (Node) result;

                                pageRoot.setStyle(
                                        "-fx-background-color: " +
                                                MAIN_BACKGROUND +
                                                ";"
                                );

                                contentArea
                                        .getChildren()
                                        .setAll(
                                                pageRoot
                                        );

                                contentScroll.setVvalue(0);

                                return;
                        }

                } catch (NoSuchMethodException ignored) {

                        // Try the old Scene pattern below.
                }

                // ----------------------------------------------------
                // OLD PATTERN: get*Scene()
                // ----------------------------------------------------

                for (
                        Method method :
                        page.getClass().getMethods()
                ) {

                        if (
                                method.getName().startsWith("get")
                                        &&
                                        method.getName().endsWith("Scene")
                                        &&
                                        method.getParameterCount() == 0
                        ) {

                                Object result =
                                        method.invoke(page);

                                if (result instanceof Scene) {

                                        replaceContentWithScene(
                                                (Scene) result
                                        );

                                        return;
                                }
                        }
                }

                throw new IllegalStateException(
                        "Page must provide getView() or get*Scene(): " +
                                page.getClass().getName()
                );
        }

        // ============================================================
        // EXISTING USER PAGE SUPPORT
        // ============================================================
        //
        // Existing pages can be migrated one-by-one.
        // New pages should use UserSectionPage + getView().
        // Older Scene-based pages are supported temporarily.
        //
        // ============================================================

        private void showExistingUserPage(
                String className
        ) {

                try {

                        Class<?> clazz =
                                Class.forName(
                                        className
                                );

                        Object page =
                                clazz
                                        .getDeclaredConstructor()
                                        .newInstance();

                        setActiveByClass(
                                className
                        );

                        loadPageView(page);

                } catch (Exception ex) {

                        ex.printStackTrace();

                        showError(
                                "Unable to open:\n" +
                                        className +
                                        "\n\n" +
                                        ex.getMessage()
                        );
                }
        }

        // ============================================================
        // SCENE -> CONTENT
        // ============================================================

        private void replaceContentWithScene(
                Scene scene
        ) {

                if (scene == null) {

                        return;
                }

                Node pageRoot =
                        scene.getRoot();

                pageRoot.setStyle(
                        "-fx-background-color: " +
                                MAIN_BACKGROUND +
                                ";"
                );

                contentArea
                        .getChildren()
                        .setAll(
                                pageRoot
                        );

                if (contentScroll != null) {

                        contentScroll.setVvalue(0);
                }
        }

        // ============================================================
        // ACTIVE BUTTON FOR GARAGE / ACCOUNT
        // ============================================================

        private void setActiveByClass(
                String className
        ) {

                if (
                        className.endsWith(
                                "VehiclePage"
                        )
                ) {

                        setActive(
                                vehiclesButton
                        );

                } else if (
                        className.endsWith(
                                "ServiceHistoryPage"
                        )
                ) {

                        setActive(
                                serviceHistoryButton
                        );

                } else if (
                        className.endsWith(
                                "DocumentsPage"
                        )
                ) {

                        setActive(
                                documentsButton
                        );

                } else if (
                        className.endsWith(
                                "WomenSafety"
                        )
                ) {

                        setActive(
                                womenSafetyButton
                        );

                } else if (
                        className.endsWith(
                                "NotificationsPage"
                        )
                ) {

                        setActive(
                                notificationsButton
                        );

                } else if (
                        className.endsWith(
                                "SettingsPage"
                        )
                ) {

                        setActive(
                                settingsButton
                        );
                }
        }

        // ============================================================
        // ERROR MESSAGE
        // ============================================================

        private void showError(
                String message
        ) {

                Label error =
                        new Label(
                                message
                        );

                error.setWrapText(true);

                error.setTextFill(
                        Color.web(EMERGENCY)
                );

                error.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                16
                        )
                );

                VBox box =
                        new VBox(
                                15,
                                error
                        );

                box.setAlignment(
                        Pos.CENTER
                );

                box.setPadding(
                        new Insets(40)
                );

                box.setStyle(
                        "-fx-background-color: " +
                                MAIN_BACKGROUND +
                                ";"
                );

                contentArea
                        .getChildren()
                        .setAll(
                                box
                        );
        }

        // ============================================================
        // LOGOUT
        // ============================================================

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
                        Color.web(
                                EMERGENCY
                        )
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

                // =====================================================
                // MOUSE ENTERED
                // =====================================================

                button.setOnMouseEntered(
                        e ->
                                button.setStyle(
                                        "-fx-background-color: #DC262618;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-cursor: hand;"
                                )
                );

                // =====================================================
                // MOUSE EXITED
                // =====================================================

                button.setOnMouseExited(
                        e ->
                                button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;"
                                )
                );

                // =====================================================
                // LOGOUT ACTION
                // =====================================================

                button.setOnAction(
                        e -> {

                                Alert alert =
                                        new Alert(
                                                Alert.AlertType.CONFIRMATION
                                        );

                                alert.setTitle(
                                        "Logout"
                                );

                                alert.setHeaderText(
                                        "Logout from Customer Panel?"
                                );

                                alert.setContentText(
                                        "You will be returned to the login page."
                                );

                                alert.showAndWait()
                                        .ifPresent(
                                                result -> {

                                                        if (
                                                                result ==
                                                                        ButtonType.OK
                                                        ) {

                                                                System.out.println(
                                                                        "Customer logout confirmed."
                                                                );

                                                                // ---------------------------------
                                                                // ACTUAL LOGOUT
                                                                // ---------------------------------

                                                                logoutAction.run();
                                                        }
                                                }
                                        );
                        }
                );

                return button;
        }

        // ============================================================
        // DASHBOARD CONTENT
        // ============================================================

        /*
         * IMPORTANT:
         *
         * इथून खाली तुझं EXISTING dashboard UI आहे.
         *
         * Design बदललेलं नाही.
         *
         * फक्त createDashboardContent() आता contentArea मध्ये
         * load होतं.
         */

        private VBox createDashboardContent() {

                VBox content =
                        new VBox(22);

                content.setPadding(
                        new Insets(
                                28,
                                28,
                                35,
                                28
                        )
                );

                content.setMinWidth(0);

                content.setMaxWidth(
                        Double.MAX_VALUE
                );

                content.setMinHeight(900);

                content.setStyle(
                        "-fx-background-color: " +
                                MAIN_BACKGROUND +
                                ";"
                );

                // ========================================================
                // GREETING
                // ========================================================

                HBox greetingRow =
                        new HBox();

                greetingRow.setAlignment(
                        Pos.CENTER_LEFT
                );

                VBox greeting =
                        new VBox(3);

                Label title =
                        new Label(
                                "Good evening, Aarav"
                        );

                title.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                31
                        )
                );

                title.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                Label subtitle =
                        new Label(
                                "Honda City · MH 12 QR 4410 · Last synced 2 minutes ago"
                        );

                subtitle.setFont(
                        Font.font(15)
                );

                subtitle.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                greeting.getChildren().addAll(
                        title,
                        subtitle
                );

                Region greetingSpacer =
                        new Region();

                HBox.setHgrow(
                        greetingSpacer,
                        Priority.ALWAYS
                );

                Button sosButtonDashboard =
                        new Button(
                                "♧   Emergency SOS"
                        );

                sosButtonDashboard.setPrefHeight(
                        40
                );

                sosButtonDashboard.setPadding(
                        new Insets(
                                0,
                                20,
                                0,
                                20
                        )
                );

                sosButtonDashboard.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                14
                        )
                );

                sosButtonDashboard.setStyle(
                        "-fx-background-color: " +
                                EMERGENCY +
                                ";" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 22;"
                );

                sosButtonDashboard.setOnMouseEntered(
                        e ->
                                sosButtonDashboard.setStyle(
                                        "-fx-background-color: " +
                                                ORANGE_HOVER +
                                                ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-background-radius: 22;"
                                )
                );

                sosButtonDashboard.setOnMouseExited(
                        e ->
                                sosButtonDashboard.setStyle(
                                        "-fx-background-color: " +
                                                EMERGENCY +
                                                ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-background-radius: 22;"
                                )
                );

                sosButtonDashboard.setOnAction(
                        e -> showSOS()
                );

                greetingRow.getChildren().addAll(
                        greeting,
                        greetingSpacer,
                        sosButtonDashboard
                );

                content.getChildren().add(
                        greetingRow
                );

                // ========================================================
                // STATUS CARDS
                // ========================================================

                content.getChildren().add(
                        createStatusCards()
                );

                // ========================================================
                // MAIN ROW
                // ========================================================

                HBox mainRow =
                        new HBox(20);

                mainRow.setAlignment(
                        Pos.TOP_LEFT
                );

                mainRow.setMinWidth(0);

                mainRow.setMaxWidth(
                        Double.MAX_VALUE
                );

                VBox emergencyCard =
                        createEmergencyCard();

                VBox quickActions =
                        createQuickActions();

                emergencyCard.setMinWidth(0);

                emergencyCard.setMaxWidth(
                        Double.MAX_VALUE
                );

                quickActions.setMinWidth(320);

                quickActions.setPrefWidth(360);

                quickActions.setMaxWidth(390);

                HBox.setHgrow(
                        emergencyCard,
                        Priority.ALWAYS
                );

                mainRow.getChildren().addAll(
                        emergencyCard,
                        quickActions
                );

                content.getChildren().add(
                        mainRow
                );

                // ========================================================
                // BOTTOM ROW
                // ========================================================

                GridPane bottomGrid =
                        new GridPane();

                bottomGrid.setHgap(24);

                bottomGrid.setMinWidth(0);

                bottomGrid.setMaxWidth(
                        Double.MAX_VALUE
                );

                ColumnConstraints c1 =
                        new ColumnConstraints();

                ColumnConstraints c2 =
                        new ColumnConstraints();

                ColumnConstraints c3 =
                        new ColumnConstraints();

                c1.setPercentWidth(33.3333);

                c2.setPercentWidth(33.3333);

                c3.setPercentWidth(33.3334);

                c1.setHgrow(
                        Priority.ALWAYS
                );

                c2.setHgrow(
                        Priority.ALWAYS
                );

                c3.setHgrow(
                        Priority.ALWAYS
                );

                bottomGrid
                        .getColumnConstraints()
                        .addAll(
                                c1,
                                c2,
                                c3
                        );

                VBox health =
                        createHealthBreakdown();

                VBox repairs =
                        createRecentRepairs();

                VBox mechanics =
                        createTopMechanicsCard();

                health.setMaxWidth(
                        Double.MAX_VALUE
                );

                repairs.setMaxWidth(
                        Double.MAX_VALUE
                );

                mechanics.setMaxWidth(
                        Double.MAX_VALUE
                );

                GridPane.setHgrow(
                        health,
                        Priority.ALWAYS
                );

                GridPane.setHgrow(
                        repairs,
                        Priority.ALWAYS
                );

                GridPane.setHgrow(
                        mechanics,
                        Priority.ALWAYS
                );

                bottomGrid.add(
                        health,
                        0,
                        0
                );

                bottomGrid.add(
                        repairs,
                        1,
                        0
                );

                bottomGrid.add(
                        mechanics,
                        2,
                        0
                );

                content.getChildren().add(
                        bottomGrid
                );

                return content;
        }

        // ============================================================
        // STATUS CARDS
        // ============================================================

        private GridPane createStatusCards() {

                GridPane grid =
                        new GridPane();

                grid.setHgap(20);

                grid.setMinWidth(0);

                grid.setMaxWidth(
                        Double.MAX_VALUE
                );

                ColumnConstraints c1 =
                        createColumn();

                ColumnConstraints c2 =
                        createColumn();

                ColumnConstraints c3 =
                        createColumn();

                ColumnConstraints c4 =
                        createColumn();

                grid.getColumnConstraints().addAll(
                        c1,
                        c2,
                        c3,
                        c4
                );

                grid.add(
                        createStatusCard(
                                "VEHICLE HEALTH",
                                "86 / 100",
                                "Good · 2 advisories",
                                "◔",
                                SECONDARY_SURFACE,
                                SUCCESS
                        ),
                        0,
                        0
                );

                grid.add(
                        createStatusCard(
                                "NEXT SERVICE DUE",
                                "480 km",
                                "Approx. 12 Sep 2026",
                                "◫",
                                SECONDARY_SURFACE,
                                NAV_BLUE
                        ),
                        1,
                        0
                );

                grid.add(
                        createStatusCard(
                                "INSURANCE EXPIRY",
                                "15 days",
                                "Renew to stay covered",
                                "♢",
                                "#FFF0E7",
                                PRIMARY_ORANGE
                        ),
                        2,
                        0
                );

                grid.add(
                        createStatusCard(
                                "ACTIVE REQUEST",
                                "In progress",
                                "Mechanic 6 min away",
                                "⌁",
                                "#FDEBEC",
                                EMERGENCY
                        ),
                        3,
                        0
                );

                return grid;
        }

        private ColumnConstraints createColumn() {

                ColumnConstraints column =
                        new ColumnConstraints();

                column.setPercentWidth(
                        25
                );

                column.setHgrow(
                        Priority.ALWAYS
                );

                return column;
        }

        private VBox createStatusCard(
                String heading,
                String value,
                String subText,
                String icon,
                String iconBackground,
                String iconColor
        ) {

                VBox card =
                        new VBox(6);

                card.setPadding(
                        new Insets(19)
                );

                card.setMinWidth(0);

                card.setMaxWidth(
                        Double.MAX_VALUE
                );

                card.setMinHeight(128);

                card.setStyle(
                        "-fx-background-color: " +
                                CARD_SURFACE +
                                ";" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 20;" +
                                "-fx-background-radius: 20;"
                );

                HBox top =
                        new HBox();

                top.setAlignment(
                        Pos.CENTER_LEFT
                );

                Label headingLabel =
                        new Label(heading);

                headingLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.NORMAL,
                                12
                        )
                );

                headingLabel.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                StackPane iconCircle =
                        createIconCircle(
                                icon,
                                iconBackground,
                                iconColor
                        );

                top.getChildren().addAll(
                        headingLabel,
                        spacer,
                        iconCircle
                );

                Label valueLabel =
                        new Label(value);

                valueLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                27
                        )
                );

                valueLabel.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                Label subLabel =
                        new Label(subText);

                subLabel.setFont(
                        Font.font(13)
                );

                subLabel.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                card.getChildren().addAll(
                        top,
                        valueLabel,
                        subLabel
                );

                return card;
        }

        // ============================================================
        // ICON CIRCLE
        // ============================================================

        private StackPane createIconCircle(
                String icon,
                String background,
                String iconColor
        ) {

                StackPane box =
                        new StackPane();

                Circle circle =
                        new Circle(
                                22
                        );

                circle.setFill(
                        Color.web(
                                background
                        )
                );

                Label iconLabel =
                        new Label(icon);

                iconLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                17
                        )
                );

                iconLabel.setTextFill(
                        Color.web(
                                iconColor
                        )
                );

                box.getChildren().addAll(
                        circle,
                        iconLabel
                );

                return box;
        }

        // ============================================================
        // ACTIVE EMERGENCY CARD
        // ============================================================

        private VBox createEmergencyCard() {

                VBox card =
                        new VBox(14);

                card.setPadding(
                        new Insets(22)
                );

                card.setMinHeight(450);

                card.setStyle(
                        "-fx-background-color: " +
                                CARD_SURFACE +
                                ";" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 22;" +
                                "-fx-background-radius: 22;"
                );

                HBox heading =
                        new HBox();

                heading.setAlignment(
                        Pos.CENTER_LEFT
                );

                VBox titles =
                        new VBox(3);

                Label title =
                        new Label(
                                "Active emergency request"
                        );

                title.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                20
                        )
                );

                title.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                Label sub =
                        new Label(
                                "Battery jump start · NH-48, Exit 12B"
                        );

                sub.setFont(
                        Font.font(14)
                );

                sub.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                titles.getChildren().addAll(
                        title,
                        sub
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Label route =
                        new Label(
                                "En route"
                        );

                route.setPadding(
                        new Insets(
                                5,
                                13,
                                5,
                                13
                        )
                );

                route.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                12
                        )
                );

                route.setTextFill(
                        Color.web(
                                SUCCESS
                        )
                );

                route.setStyle(
                        "-fx-background-color: " +
                                SECONDARY_SURFACE +
                                ";" +
                                "-fx-background-radius: 16;"
                );

                heading.getChildren().addAll(
                        titles,
                        spacer,
                        route
                );

                StackPane map =
                        createMap();

                HBox mechanic =
                        createMechanicInfo();

                card.getChildren().addAll(
                        heading,
                        map,
                        mechanic
                );

                return card;
        }

        // ============================================================
        // MAP
        // ============================================================

        private StackPane createMap() {

                StackPane map =
                        new StackPane();

                map.setMinHeight(275);

                map.setStyle(
                        "-fx-background-color: #E7E7E4;" +
                                "-fx-background-radius: 22;" +
                                "-fx-border-radius: 22;" +
                                "-fx-border-color: " +
                                BORDER +
                                ";"
                );

                Line road1 =
                        new Line(
                                20,
                                210,
                                780,
                                40
                        );

                road1.setStroke(
                        Color.WHITE
                );

                road1.setStrokeWidth(11);

                Line road2 =
                        new Line(
                                160,
                                20,
                                620,
                                275
                        );

                road2.setStroke(
                        Color.WHITE
                );

                road2.setStrokeWidth(9);

                Line road3 =
                        new Line(
                                10,
                                100,
                                800,
                                175
                        );

                road3.setStroke(
                        Color.WHITE
                );

                road3.setStrokeWidth(8);

                Line road4 =
                        new Line(
                                420,
                                0,
                                500,
                                275
                        );

                road4.setStroke(
                        Color.WHITE
                );

                road4.setStrokeWidth(6);

                Line river =
                        new Line(
                                560,
                                275,
                                790,
                                215
                        );

                river.setStroke(
                        Color.web("#73BDE8")
                );

                river.setStrokeWidth(5);

                map.getChildren().addAll(
                        road1,
                        road2,
                        road3,
                        road4,
                        river
                );

                StackPane mechanicPin =
                        createMapMarker(
                                "⚒",
                                SUCCESS
                        );

                StackPane.setAlignment(
                        mechanicPin,
                        Pos.CENTER
                );

                StackPane.setMargin(
                        mechanicPin,
                        new Insets(
                                0,
                                0,
                                125,
                                220
                        )
                );

                StackPane userPin =
                        createMapMarker(
                                "▰",
                                NAV_BLUE
                        );

                StackPane.setAlignment(
                        userPin,
                        Pos.CENTER
                );

                StackPane.setMargin(
                        userPin,
                        new Insets(
                                95,
                                0,
                                0,
                                360
                        )
                );

                Line route =
                        new Line(
                                240,
                                125,
                                380,
                                175
                        );

                route.setStroke(
                        Color.web(
                                NAV_BLUE
                        )
                );

                route.setStrokeWidth(2);

                route.getStrokeDashArray().addAll(
                        6.0,
                        6.0
                );

                map.getChildren().add(
                        route
                );

                Label mechanicLabel =
                        new Label(
                                "Arjun · 6 min"
                        );

                mechanicLabel.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-text-fill: " +
                                HEADING +
                                ";" +
                                "-fx-padding: 6 11;" +
                                "-fx-background-radius: 14;" +
                                "-fx-font-weight: bold;"
                );

                StackPane.setAlignment(
                        mechanicLabel,
                        Pos.CENTER
                );

                StackPane.setMargin(
                        mechanicLabel,
                        new Insets(
                                55,
                                0,
                                0,
                                220
                        )
                );

                Label you =
                        new Label("You");

                you.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-text-fill: " +
                                HEADING +
                                ";" +
                                "-fx-padding: 5 11;" +
                                "-fx-background-radius: 14;" +
                                "-fx-font-weight: bold;"
                );

                StackPane.setAlignment(
                        you,
                        Pos.CENTER
                );

                StackPane.setMargin(
                        you,
                        new Insets(
                                175,
                                0,
                                0,
                                360
                        )
                );

                map.getChildren().addAll(
                        mechanicPin,
                        userPin,
                        mechanicLabel,
                        you
                );

                return map;
        }

        private StackPane createMapMarker(
                String icon,
                String color
        ) {

                StackPane marker =
                        new StackPane();

                Circle circle =
                        new Circle(
                                22
                        );

                circle.setFill(
                        Color.web(color)
                );

                circle.setStroke(
                        Color.WHITE
                );

                circle.setStrokeWidth(3);

                Label iconLabel =
                        new Label(icon);

                iconLabel.setTextFill(
                        Color.WHITE
                );

                iconLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                14
                        )
                );

                marker.getChildren().addAll(
                        circle,
                        iconLabel
                );

                return marker;
        }

        // ============================================================
        // MECHANIC INFORMATION
        // ============================================================

        private HBox createMechanicInfo() {

                HBox box =
                        new HBox(12);

                box.setAlignment(
                        Pos.CENTER_LEFT
                );

                StackPane avatar =
                        new StackPane();

                Circle circle =
                        new Circle(
                                22
                        );

                circle.setFill(
                        Color.web(
                                SECONDARY_SURFACE
                        )
                );

                Label initials =
                        new Label("AM");

                initials.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                15
                        )
                );

                initials.setTextFill(
                        Color.web(
                                NAV_BLUE
                        )
                );

                avatar.getChildren().addAll(
                        circle,
                        initials
                );

                VBox details =
                        new VBox(2);

                Label name =
                        new Label(
                                "Arjun Mehta · 4.9 ★"
                        );

                name.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                15
                        )
                );

                name.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                Label info =
                        new Label(
                                "SpeedFix Auto Care · Petrol, Battery"
                        );

                info.setFont(
                        Font.font(12)
                );

                info.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                details.getChildren().addAll(
                        name,
                        info
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Button track =
                        new Button(
                                "Track live   →"
                        );

                track.setPadding(
                        new Insets(
                                9,
                                17,
                                9,
                                17
                        )
                );

                track.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                13
                        )
                );

                track.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-text-fill: " +
                                HEADING +
                                ";" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 20;" +
                                "-fx-background-radius: 20;"
                );

                track.setOnAction(
                        e -> showLiveMap()
                );

                box.getChildren().addAll(
                        avatar,
                        details,
                        spacer,
                        track
                );

                return box;
        }

        // ============================================================
        // QUICK ACTIONS
        // ============================================================

        private VBox createQuickActions() {

                VBox card =
                        new VBox(10);

                card.setPadding(
                        new Insets(22)
                );

                card.setMinHeight(450);

                card.setStyle(
                        "-fx-background-color: " +
                                CARD_SURFACE +
                                ";" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 22;" +
                                "-fx-background-radius: 22;"
                );

                Label title =
                        new Label(
                                "Quick actions"
                        );

                title.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                20
                        )
                );

                title.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                card.getChildren().add(
                        title
                );

                card.getChildren().addAll(

                        createActionButton(
                                "⚒",
                                "Request Mechanic",
                                SECONDARY_SURFACE,
                                NAV_BLUE,
                                e -> showMechanics()
                        ),

                        createActionButton(
                                "▱",
                                "Request Tow Truck",
                                "#FFF0E7",
                                PRIMARY_ORANGE,
                                e -> showTowTruck()
                        ),

                        createActionButton(
                                "♧",
                                "Emergency SOS",
                                "#FDEBEC",
                                EMERGENCY,
                                e -> showSOS()
                        ),

                        createActionButton(
                                "▧",
                                "Upload Documents",
                                SECONDARY_SURFACE,
                                SUCCESS,
                                e -> showExistingUserPage(
                                        "project.ui.user.Garage.DocumentsPage"
                                )
                        ),

                        createActionButton(
                                "◇",
                                "Nearby Mechanics",
                                SECONDARY_SURFACE,
                                NAV_BLUE,
                                e -> showMechanics()
                        )
                );

                return card;
        }

        private HBox createActionButton(
                String icon,
                String text,
                String iconBackground,
                String iconColor,
                javafx.event.EventHandler<javafx.event.ActionEvent> action
        ) {

                Button row =
                        new Button();

                row.setMaxWidth(
                        Double.MAX_VALUE
                );

                row.setPrefHeight(58);

                row.setPadding(
                        new Insets(
                                8,
                                13,
                                8,
                                12
                        )
                );

                StackPane iconCircle =
                        createIconCircle(
                                icon,
                                iconBackground,
                                iconColor
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
                        Color.web(
                                HEADING
                        )
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Label arrow =
                        new Label("→");

                arrow.setFont(
                        Font.font(
                                "Arial",
                                20
                        )
                );

                arrow.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                HBox content =
                        new HBox(
                                13,
                                iconCircle,
                                textLabel,
                                spacer,
                                arrow
                        );

                content.setAlignment(
                        Pos.CENTER_LEFT
                );

                row.setGraphic(
                        content
                );

                row.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 29;" +
                                "-fx-background-radius: 29;" +
                                "-fx-cursor: hand;"
                );

                row.setOnMouseEntered(
                        e ->
                                row.setStyle(
                                        "-fx-background-color: " +
                                                SECONDARY_SURFACE +
                                                ";" +
                                                "-fx-border-color: " +
                                                BORDER +
                                                ";" +
                                                "-fx-border-radius: 29;" +
                                                "-fx-background-radius: 29;" +
                                                "-fx-cursor: hand;"
                                )
                );

                row.setOnMouseExited(
                        e ->
                                row.setStyle(
                                        "-fx-background-color: white;" +
                                                "-fx-border-color: " +
                                                BORDER +
                                                ";" +
                                                "-fx-border-radius: 29;" +
                                                "-fx-background-radius: 29;" +
                                                "-fx-cursor: hand;"
                                )
                );

                row.setOnAction(
                        action
                );

                return createButtonWrapper(
                        row
                );
        }

        private HBox createButtonWrapper(
                Button button
        ) {

                HBox wrapper =
                        new HBox();

                wrapper.getChildren().add(
                        button
                );

                HBox.setHgrow(
                        button,
                        Priority.ALWAYS
                );

                return wrapper;
        }

        // ============================================================
        // HEALTH BREAKDOWN
        // ============================================================

        private VBox createHealthBreakdown() {

                VBox card =
                        createBottomCard(
                                "Health breakdown"
                        );

                card.getChildren().add(
                        createHealthProgressRow(
                                "Battery",
                                "42%",
                                0.42,
                                EMERGENCY
                        )
                );

                card.getChildren().add(
                        createHealthProgressRow(
                                "Tyres",
                                "78%",
                                0.78,
                                "#F59E0B"
                        )
                );

                card.getChildren().add(
                        createHealthProgressRow(
                                "Brakes",
                                "91%",
                                0.91,
                                "#10B981"
                        )
                );

                card.getChildren().add(
                        createHealthProgressRow(
                                "Engine oil",
                                "64%",
                                0.64,
                                NAV_BLUE
                        )
                );

                return card;
        }

        private VBox createHealthProgressRow(
                String labelText,
                String percentage,
                double progress,
                String progressColor
        ) {

                VBox row =
                        new VBox(7);

                row.setMaxWidth(
                        Double.MAX_VALUE
                );

                HBox top =
                        new HBox();

                top.setAlignment(
                        Pos.CENTER_LEFT
                );

                Label label =
                        new Label(labelText);

                label.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.NORMAL,
                                15
                        )
                );

                label.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Label value =
                        new Label(
                                percentage
                        );

                value.setFont(
                        Font.font(14)
                );

                value.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                top.getChildren().addAll(
                        label,
                        spacer,
                        value
                );

                StackPane track =
                        new StackPane();

                track.setPrefHeight(10);

                track.setMinHeight(10);

                track.setMaxHeight(10);

                track.setMaxWidth(
                        Double.MAX_VALUE
                );

                track.setStyle(
                        "-fx-background-color: #E6EEF1;" +
                                "-fx-background-radius: 8;"
                );

                Region fill =
                        new Region();

                fill.setPrefHeight(10);

                fill.setMinHeight(10);

                fill.setMaxHeight(10);

                fill.setMaxWidth(
                        Double.MAX_VALUE
                );

                fill.setStyle(
                        "-fx-background-color: " +
                                progressColor +
                                ";" +
                                "-fx-background-radius: 8;"
                );

                StackPane.setAlignment(
                        fill,
                        Pos.CENTER_LEFT
                );

                fill.prefWidthProperty().bind(
                        track.widthProperty()
                                .multiply(progress)
                );

                track.getChildren().add(
                        fill
                );

                row.getChildren().addAll(
                        top,
                        track
                );

                return row;
        }

        // ============================================================
        // RECENT REPAIRS
        // ============================================================

        private VBox createRecentRepairs() {

                VBox card =
                        createBottomCard(
                                "Recent repairs"
                        );

                card.getChildren().addAll(

                        createRepairRow(
                                "Full periodic service",
                                "12 Jul 2026 · SpeedFix Auto Care",
                                "₹6,480"
                        ),

                        createRepairRow(
                                "Front brake pad replacement",
                                "28 Apr 2026 · Highway Motors",
                                "₹4,150"
                        ),

                        createRepairRow(
                                "Battery jump start (roadside)",
                                "05 Feb 2026 · PitStop 24×7",
                                "₹700"
                        ),

                        createRepairRow(
                                "AC gas refill & cabin filter",
                                "19 Nov 2025 · SpeedFix Auto Care",
                                "₹3,260"
                        )
                );

                Region spacer =
                        new Region();

                VBox.setVgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Button history =
                        new Button(
                                "View full history"
                        );

                history.setMaxWidth(
                        Double.MAX_VALUE
                );

                history.setPrefHeight(46);

                history.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                16
                        )
                );

                history.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                history.setStyle(
                        "-fx-background-color: transparent;" +
                                "-fx-background-radius: 24;" +
                                "-fx-border-color: transparent;" +
                                "-fx-padding: 0 18 0 18;"
                );

                history.setOnMouseEntered(
                        e -> {

                                history.setStyle(
                                        "-fx-background-color: " +
                                                PRIMARY_ORANGE +
                                                ";" +
                                                "-fx-background-radius: 24;"
                                );

                                history.setTextFill(
                                        Color.WHITE
                                );
                        }
                );

                history.setOnMouseExited(
                        e -> {

                                history.setStyle(
                                        "-fx-background-color: transparent;" +
                                                "-fx-background-radius: 24;" +
                                                "-fx-border-color: transparent;"
                                );

                                history.setTextFill(
                                        Color.web(
                                                HEADING
                                        )
                                );
                        }
                );

                history.setOnAction(
                        e ->
                                showExistingUserPage(
                                        "project.ui.user.Garage.ServiceHistoryPage"
                                )
                );

                card.getChildren().addAll(
                        spacer,
                        history
                );

                return card;
        }

        private HBox createRepairRow(
                String titleText,
                String subText,
                String amountText
        ) {

                HBox row =
                        new HBox();

                row.setAlignment(
                        Pos.TOP_LEFT
                );

                row.setPadding(
                        new Insets(
                                0,
                                0,
                                13,
                                0
                        )
                );

                VBox details =
                        new VBox(2);

                Label title =
                        new Label(
                                titleText
                        );

                title.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.NORMAL,
                                15
                        )
                );

                title.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                Label sub =
                        new Label(
                                subText
                        );

                sub.setFont(
                        Font.font(13)
                );

                sub.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                details.getChildren().addAll(
                        title,
                        sub
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Label amount =
                        new Label(
                                amountText
                        );

                amount.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                15
                        )
                );

                amount.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                row.getChildren().addAll(
                        details,
                        spacer,
                        amount
                );

                return row;
        }

        // ============================================================
        // TOP MECHANICS
        // ============================================================

        private VBox createTopMechanicsCard() {

                VBox card =
                        createBottomCard(
                                "Top mechanics near you"
                        );

                card.getChildren().addAll(

                        createMechanicRow(
                                "AM",
                                "Arjun Mehta",
                                "1.2 km · 6 min · 4.9 ★",
                                "Best"
                        ),

                        createMechanicRow(
                                "KS",
                                "Kabir Sharma",
                                "2.4 km · 11 min · 4.7 ★",
                                ""
                        ),

                        createMechanicRow(
                                "NR",
                                "Neha Rao",
                                "3.1 km · 14 min · 4.8 ★",
                                ""
                        )
                );

                VBox coverage =
                        new VBox(8);

                coverage.setPadding(
                        new Insets(
                                14,
                                14,
                                12,
                                14
                        )
                );

                coverage.setStyle(
                        "-fx-background-color: " +
                                SECONDARY_SURFACE +
                                ";" +
                                "-fx-background-radius: 20;"
                );

                Label coverageTitle =
                        new Label(
                                "COVERAGE THIS MONTH"
                        );

                coverageTitle.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                13
                        )
                );

                coverageTitle.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                StackPane coverageBar =
                        new StackPane();

                coverageBar.setPrefHeight(10);

                coverageBar.setMinHeight(10);

                coverageBar.setMaxHeight(10);

                coverageBar.setMaxWidth(
                        Double.MAX_VALUE
                );

                coverageBar.setStyle(
                        "-fx-background-color: #C7D9F6;" +
                                "-fx-background-radius: 8;"
                );

                Region used =
                        new Region();

                used.setPrefHeight(10);

                used.setMinHeight(10);

                used.setMaxHeight(10);

                used.prefWidthProperty().bind(
                        coverageBar
                                .widthProperty()
                                .multiply(0.75)
                );

                used.setStyle(
                        "-fx-background-color: " +
                                NAV_BLUE +
                                ";" +
                                "-fx-background-radius: 8;"
                );

                StackPane.setAlignment(
                        used,
                        Pos.CENTER_LEFT
                );

                coverageBar.getChildren().add(
                        used
                );

                Label remaining =
                        new Label(
                                "3 of 4 free assists remaining"
                        );

                remaining.setFont(
                        Font.font(13)
                );

                remaining.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                coverage.getChildren().addAll(
                        coverageTitle,
                        coverageBar,
                        remaining
                );

                Region coverageSpacer =
                        new Region();

                VBox.setVgrow(
                        coverageSpacer,
                        Priority.ALWAYS
                );

                card.getChildren().addAll(
                        coverageSpacer,
                        coverage
                );

                return card;
        }

        private VBox createBottomCard(
                String title
        ) {

                VBox card =
                        new VBox(14);

                card.setPadding(
                        new Insets(22)
                );

                card.setMinWidth(0);

                card.setPrefWidth(0);

                card.setMaxWidth(
                        Double.MAX_VALUE
                );

                card.setMinHeight(450);

                card.setPrefHeight(450);

                card.setMaxHeight(450);

                card.setFillWidth(true);

                card.setStyle(
                        "-fx-background-color: " +
                                CARD_SURFACE +
                                ";" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 22;" +
                                "-fx-background-radius: 22;"
                );

                Label titleLabel =
                        new Label(title);

                titleLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.NORMAL,
                                24
                        )
                );

                titleLabel.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                card.getChildren().add(
                        titleLabel
                );

                return card;
        }

        private HBox createMechanicRow(
                String initials,
                String name,
                String details,
                String badge
        ) {

                HBox row =
                        new HBox(10);

                row.setAlignment(
                        Pos.CENTER_LEFT
                );

                row.setPrefHeight(50);

                StackPane avatar =
                        new StackPane();

                Circle circle =
                        new Circle(22);

                circle.setFill(
                        Color.web(
                                SECONDARY_SURFACE
                        )
                );

                Label initial =
                        new Label(
                                initials
                        );

                initial.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                12
                        )
                );

                initial.setTextFill(
                        Color.web(
                                NAV_BLUE
                        )
                );

                avatar.getChildren().addAll(
                        circle,
                        initial
                );

                VBox detailsBox =
                        new VBox(1);

                Label nameLabel =
                        new Label(name);

                nameLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.NORMAL,
                                16
                        )
                );

                nameLabel.setTextFill(
                        Color.web(
                                HEADING
                        )
                );

                Label detailsLabel =
                        new Label(details);

                detailsLabel.setFont(
                        Font.font(13)
                );

                detailsLabel.setTextFill(
                        Color.web(
                                SECONDARY_TEXT
                        )
                );

                detailsBox.getChildren().addAll(
                        nameLabel,
                        detailsLabel
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                row.getChildren().addAll(
                        avatar,
                        detailsBox,
                        spacer
                );

                if (
                        !badge.isEmpty()
                ) {

                        Label badgeLabel =
                                new Label(badge);

                        badgeLabel.setPadding(
                                new Insets(
                                        4,
                                        12,
                                        4,
                                        12
                                )
                        );

                        badgeLabel.setFont(
                                Font.font(
                                        "Arial",
                                        FontWeight.NORMAL,
                                        12
                                )
                        );

                        badgeLabel.setTextFill(
                                Color.web(
                                        NAV_BLUE
                                )
                        );

                        badgeLabel.setStyle(
                                "-fx-background-color: " +
                                        SECONDARY_SURFACE +
                                        ";" +
                                        "-fx-background-radius: 14;"
                        );

                        row.getChildren().add(
                                badgeLabel
                        );
                }

                return row;
        }
}
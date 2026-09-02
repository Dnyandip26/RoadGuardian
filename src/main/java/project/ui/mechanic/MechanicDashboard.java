package project.ui.mechanic;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import project.controller.mechanic.RequestsController;
import project.firebase.FirebaseConfig;
import project.model.ServiceRequest;
import project.ui.landing.LandingPage;
import project.util.Theme;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MechanicDashboard {

    // =========================================================
    // MAIN UI
    // =========================================================

    private BorderPane root;

    private RequestsPage requestsPage;
    private ActiveJobPage activeJobPage;

    private RequestsController controller;

    private Button dashboardButton;
    private Button requestsButton;
    private Button navigationButton;
    private Button activeJobButton;
    private Button historyButton;

    // =========================================================
    // LOGGED IN MECHANIC
    // =========================================================

    private String mechanicId;
    private String mechanicName;

    // =========================================================
    // CURRENT ACTIVE REQUEST
    // =========================================================

    private ServiceRequest currentActiveRequest;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MechanicDashboard(
            String mechanicId,
            String mechanicName
    ) {

        this.mechanicId = mechanicId;
        this.mechanicName = mechanicName;

        initializeFirebase();
    }


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================

    public MechanicDashboard() {

        this.mechanicId = "mechanic001";
        this.mechanicName = "Rajesh Patil";

        initializeFirebase();
    }


    // =========================================================
    // FIREBASE INITIALIZATION
    // =========================================================

    private void initializeFirebase() {

        try {

            Firestore firestore =
                    FirebaseConfig.getFirestore();

            controller =
                    new RequestsController(
                            firestore
                    );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to initialize Firebase",
                    e
            );
        }
    }


    // =========================================================
    // DASHBOARD SCENE
    // =========================================================

    public Scene getDashboardScene() {

        root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND +
                ";"
        );

        activeJobPage =
                new ActiveJobPage();

        requestsPage =
                new RequestsPage(
                        activeJobPage,
                        this::showActiveJob,
                        this::showDashboard
                );

        requestsPage.preloadRequests();

        root.setLeft(
                createSidebar()
        );

        showDashboard();

        return new Scene(
                root,
                LandingPage.appMainStage.getWidth(),
                LandingPage.appMainStage.getHeight()
        );
    }


    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox createSidebar() {

        VBox sidebar =
                new VBox();

        sidebar.setPrefWidth(245);
        sidebar.setSpacing(10);

        sidebar.setPadding(
                new Insets(
                        28,
                        18,
                        25,
                        18
                )
        );

        sidebar.setStyle(
                "-fx-background-color: " +
                Theme.SIDEBAR +
                ";"
        );


        // -----------------------------------------------------
        // LOGO
        // -----------------------------------------------------

        HBox logoBox =
                new HBox(10);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        logoBox.setPadding(
                new Insets(
                        0,
                        8,
                        28,
                        8
                )
        );

        Circle logoCircle =
                new Circle(20);

        logoCircle.setFill(
                Color.web(
                        Theme.PRIMARY
                )
        );

        Label logoText =
                new Label(
                        "RoadGuardian"
                );

        logoText.setTextFill(
                Color.web(
                        Theme.TEXT
                )
        );

        logoText.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        logoBox.getChildren().addAll(
                logoCircle,
                logoText
        );


        // -----------------------------------------------------
        // ROLE
        // -----------------------------------------------------

        Label roleLabel =
                new Label(
                        "MECHANIC PORTAL"
                );

        roleLabel.setTextFill(
                Color.web(
                        Theme.SECONDARY_TEXT
                )
        );

        roleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        roleLabel.setPadding(
                new Insets(
                        0,
                        8,
                        8,
                        8
                )
        );


        // -----------------------------------------------------
        // MENU BUTTONS
        // -----------------------------------------------------

        dashboardButton =
                createMenuButton(
                        "⌂   Dashboard"
                );

        requestsButton =
                createMenuButton(
                        "▣   Service Requests"
                );

        navigationButton =
                createMenuButton(
                        "➤   Navigation"
                );

        activeJobButton =
                createMenuButton(
                        "⚙   Active Job"
                );

        historyButton =
                createMenuButton(
                        "◷   Job History"
                );


        dashboardButton.setOnAction(
                e -> showDashboard()
        );

        requestsButton.setOnAction(
                e -> showRequests()
        );

        navigationButton.setOnAction(
                e -> showNavigation()
        );

        activeJobButton.setOnAction(
                e -> showActiveJob()
        );

        historyButton.setOnAction(
                e -> showHistory()
        );


        // -----------------------------------------------------
        // SPACER
        // -----------------------------------------------------

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );


        // -----------------------------------------------------
        // PROFILE
        // -----------------------------------------------------

        VBox profileBox =
                new VBox(4);

        profileBox.setPadding(
                new Insets(14)
        );

        profileBox.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-background-radius: 12;"
        );

        Label mechanicNameLabel =
                new Label(
                        safe(mechanicName)
                );

        mechanicNameLabel.setTextFill(
                Color.web(
                        Theme.TEXT
                )
        );

        mechanicNameLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        Label mechanicRole =
                new Label(
                        "Verified Mechanic"
                );

        mechanicRole.setTextFill(
                Color.web(
                        Theme.SECONDARY_TEXT
                )
        );

        mechanicRole.setStyle(
                "-fx-font-size: 11px;"
        );

        profileBox.getChildren().addAll(
                mechanicNameLabel,
                mechanicRole
        );


        // -----------------------------------------------------
        // LOGOUT
        // -----------------------------------------------------

        Button logoutButton =
                createMenuButton(
                        "↪   Logout"
                );

        logoutButton.setOnAction(
                e -> showLogout()
        );


        sidebar.getChildren().addAll(
                logoBox,
                roleLabel,
                dashboardButton,
                requestsButton,
                navigationButton,
                activeJobButton,
                historyButton,
                spacer,
                profileBox,
                logoutButton
        );

        return sidebar;
    }


    // =========================================================
    // MENU BUTTON
    // =========================================================

    private Button createMenuButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(
                        13,
                        15,
                        13,
                        15
                )
        );

        setNormalStyle(button);

        button.setOnMouseEntered(
                e -> {

                    if (!isActive(button)) {
                        setHoverStyle(button);
                    }
                }
        );

        button.setOnMouseExited(
                e -> {

                    if (!isActive(button)) {
                        setNormalStyle(button);
                    }
                }
        );

        return button;
    }


    // =========================================================
    // NORMAL STYLE
    // =========================================================

    private void setNormalStyle(
            Button button
    ) {

        if (button == null) {
            return;
        }

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        );
    }


    // =========================================================
    // HOVER STYLE
    // =========================================================

    private void setHoverStyle(
            Button button
    ) {

        if (button == null) {
            return;
        }

        button.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        );
    }


    // =========================================================
    // ACTIVE STYLE
    // =========================================================

    private void setActiveStyle(
            Button button
    ) {

        if (button == null) {
            return;
        }

        button.setStyle(
                "-fx-background-color: " +
                Theme.INFO +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
    }


    // =========================================================
    // CHECK ACTIVE
    // =========================================================

    private boolean isActive(
            Button button
    ) {

        return button != null &&
                button.getStyle().contains(
                        Theme.INFO
                );
    }


    // =========================================================
    // SET ACTIVE BUTTON
    // =========================================================

    private void setActiveButton(
            Button activeButton
    ) {

        setNormalStyle(dashboardButton);
        setNormalStyle(requestsButton);
        setNormalStyle(navigationButton);
        setNormalStyle(activeJobButton);
        setNormalStyle(historyButton);

        if (activeButton != null) {
            setActiveStyle(activeButton);
        }
    }


    // =========================================================
    // SHOW DASHBOARD
    // =========================================================

    private void showDashboard() {

        setActiveButton(
                dashboardButton
        );

        loadCurrentActiveRequest();

        root.setCenter(
                createDashboard()
        );
    }


    // =========================================================
    // SHOW REQUESTS
    // =========================================================

    private void showRequests() {

        setActiveButton(
                requestsButton
        );

        root.setCenter(
                requestsPage.getContent()
        );
    }


    // =========================================================
    // SHOW NAVIGATION
    // =========================================================

    private void showNavigation() {

        setActiveButton(
                navigationButton
        );

        loadCurrentActiveRequest();

        root.setCenter(
                createNavigationPage()
        );
    }


    // =========================================================
    // SHOW ACTIVE JOB
    // =========================================================

    private void showActiveJob() {

        setActiveButton(
                activeJobButton
        );

        if (currentActiveRequest != null) {

            activeJobPage.setJob(
                    currentActiveRequest
            );
        }

        root.setCenter(
                activeJobPage.getContent()
        );
    }


    // =========================================================
    // SHOW HISTORY
    // =========================================================

    private void showHistory() {

        setActiveButton(
                historyButton
        );

        root.setCenter(
                createHistoryPage()
        );
    }


    // =========================================================
    // DASHBOARD
    // =========================================================

    private ScrollPane createDashboard() {

        VBox content =
                new VBox(25);

        content.setPadding(
                new Insets(
                        32,
                        35,
                        35,
                        35
                )
        );

        HBox header =
                createHeader();

        List<ServiceRequest> allRequests =
                getMechanicRequests();


        long pendingCount =
                allRequests
                        .stream()
                        .filter(
                                r ->
                                        "Pending"
                                                .equalsIgnoreCase(
                                                        safe(
                                                                r.getStatus()
                                                        )
                                                )
                        )
                        .count();


        long activeCount =
                allRequests
                        .stream()
                        .filter(
                                r ->
                                        "Accepted"
                                                .equalsIgnoreCase(
                                                        safe(
                                                                r.getStatus()
                                                        )
                                                )
                                        ||
                                        "In Progress"
                                                .equalsIgnoreCase(
                                                        safe(
                                                                r.getStatus()
                                                        )
                                                )
                        )
                        .count();


        long completedCount =
                allRequests
                        .stream()
                        .filter(
                                r ->
                                        "Completed"
                                                .equalsIgnoreCase(
                                                        safe(
                                                                r.getStatus()
                                                        )
                                                )
                        )
                        .count();


        HBox statCards =
                new HBox(18);

        statCards.getChildren().addAll(

                createStatCard(
                        "Pending Requests",
                        String.valueOf(
                                pendingCount
                        ),
                        "Waiting for response",
                        Theme.PRIMARY
                ),

                createStatCard(
                        "Active Jobs",
                        String.valueOf(
                                activeCount
                        ),
                        "Currently handling",
                        Theme.INFO
                ),

                createStatCard(
                        "Completed",
                        String.valueOf(
                                completedCount
                        ),
                        "Total services",
                        Theme.SUCCESS
                )
        );


        VBox recentRequests =
                createRecentRequests(
                        allRequests
                );

        VBox activeJob =
                createActiveJob(
                        allRequests
                );


        HBox lowerSection =
                new HBox(20);

        lowerSection.getChildren().addAll(
                recentRequests,
                activeJob
        );

        HBox.setHgrow(
                recentRequests,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                activeJob,
                Priority.ALWAYS
        );


        content.getChildren().addAll(
                header,
                statCards,
                lowerSection
        );


        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        return scrollPane;
    }


    // =========================================================
    // DASHBOARD HEADER
    // =========================================================

    private HBox createHeader() {

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox titleBox =
                new VBox(5);


        String name =
                safe(mechanicName);

        if (name.equals("-")) {
            name = "Mechanic";
        }


        Label welcome =
                new Label(
                        "Welcome, " + name
                );

        welcome.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label subtitle =
                new Label(
                        "Here's what's happening with your services today."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        titleBox.getChildren().addAll(
                welcome,
                subtitle
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        HBox statusBox =
                new HBox(9);

        statusBox.setAlignment(
                Pos.CENTER
        );

        statusBox.setPadding(
                new Insets(
                        10,
                        16,
                        10,
                        16
                )
        );

        statusBox.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS_BG +
                ";" +
                "-fx-background-radius: 20;"
        );


        Circle statusCircle =
                new Circle(5);

        statusCircle.setFill(
                Color.web(
                        Theme.SUCCESS
                )
        );


        Label statusText =
                new Label(
                        "You're Online"
                );

        statusText.setStyle(
                "-fx-text-fill: " +
                Theme.SUCCESS +
                ";" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );


        statusBox.getChildren().addAll(
                statusCircle,
                statusText
        );


        header.getChildren().addAll(
                titleBox,
                spacer,
                statusBox
        );

        return header;
    }


    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String title,
            String value,
            String subtitle,
            String accent
    ) {

        VBox card =
                new VBox(8);

        card.setPrefHeight(125);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );


        HBox titleRow =
                new HBox();

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";" +
                "-fx-font-weight: bold;"
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        Circle icon =
                new Circle(17);

        icon.setFill(
                Color.web(accent)
        );


        titleRow.getChildren().addAll(
                titleLabel,
                spacer,
                icon
        );


        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label subtitleLabel =
                new Label(subtitle);

        subtitleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        card.getChildren().addAll(
                titleRow,
                valueLabel,
                subtitleLabel
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        return card;
    }


    // =========================================================
    // RECENT REQUESTS
    // =========================================================

    private VBox createRecentRequests(
            List<ServiceRequest> allRequests
    ) {

        VBox container =
                new VBox(15);

        container.setPadding(
                new Insets(22)
        );

        container.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );


        Label title =
                new Label(
                        "Recent Service Requests"
                );

        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label description =
                new Label(
                        "Latest requests from Firebase"
                );

        description.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        VBox requests =
                new VBox(10);


        List<ServiceRequest> recent =
                allRequests
                        .stream()
                        .filter(
                                r ->
                                        "Pending"
                                                .equalsIgnoreCase(
                                                        safe(
                                                                r.getStatus()
                                                        )
                                                )
                        )
                        .limit(5)
                        .collect(
                                Collectors.toList()
                        );


        for (
                ServiceRequest request :
                recent
        ) {

            requests.getChildren().add(
                    createRequestRow(
                            request
                    )
            );
        }


        if (recent.isEmpty()) {

            Label empty =
                    new Label(
                            "No pending requests."
                    );

            empty.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: " +
                    Theme.SECONDARY_TEXT +
                    ";"
            );

            requests.getChildren().add(
                    empty
            );
        }


        container.getChildren().addAll(
                title,
                description,
                requests
        );

        return container;
    }


    // =========================================================
    // REQUEST ROW
    // =========================================================

    private HBox createRequestRow(
            ServiceRequest request
    ) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(12)
        );

        row.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-background-radius: 10;"
        );


        Image vehicleImage;

        try {

            vehicleImage =
                    new Image(
                            getClass()
                                    .getResourceAsStream(
                                            "/images/default_vehicle.png"
                                    )
                    );

        } catch (Exception e) {

            vehicleImage = null;
        }


        ImageView vehicleImageView =
                new ImageView();

        if (vehicleImage != null) {

            vehicleImageView.setImage(
                    vehicleImage
            );
        }

        vehicleImageView.setFitWidth(70);
        vehicleImageView.setFitHeight(45);
        vehicleImageView.setPreserveRatio(true);


        VBox customerBox =
                new VBox(4);


        Label customerLabel =
                new Label(
                        safe(
                                request.getCustomerName()
                        )
                );

        customerLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label vehicleLabel =
                new Label(
                        safe(
                                request.getVehicleNumber()
                        ) +
                        " • " +
                        safe(
                                request.getDescription()
                        )
                );

        vehicleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        customerBox.getChildren().addAll(
                customerLabel,
                vehicleLabel
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        VBox infoBox =
                new VBox(3);

        infoBox.setAlignment(
                Pos.CENTER_RIGHT
        );


        Label locationLabel =
                new Label(
                        safe(
                                request.getLocation()
                        )
                );

        locationLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label timeLabel =
                new Label(
                        formatDate(
                                request.getRequestDate()
                        )
                );

        timeLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        infoBox.getChildren().addAll(
                locationLabel,
                timeLabel
        );


        row.getChildren().addAll(
                vehicleImageView,
                customerBox,
                spacer,
                infoBox
        );

        return row;
    }


    // =========================================================
    // ACTIVE JOB
    // =========================================================

    private VBox createActiveJob(
            List<ServiceRequest> allRequests
    ) {

        VBox container =
                new VBox(15);

        container.setPadding(
                new Insets(22)
        );

        container.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );


        Label title =
                new Label(
                        "Active Job"
                );

        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        ServiceRequest active =
                findActiveRequest(
                        allRequests
                );


        if (active == null) {

            Label empty =
                    new Label(
                            "No active job currently."
                    );

            empty.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: " +
                    Theme.SECONDARY_TEXT +
                    ";"
            );

            container.getChildren().addAll(
                    title,
                    empty
            );

            return container;
        }


        HBox statusBox =
                new HBox(8);

        statusBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Circle dot =
                new Circle(5);

        dot.setFill(
                Color.web(
                        Theme.INFO
                )
        );


        Label status =
                new Label(
                        safe(
                                active.getStatus()
                        )
                );

        status.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.INFO +
                ";"
        );


        statusBox.getChildren().addAll(
                dot,
                status
        );


        VBox customerBox =
                new VBox(5);


        Label customer =
                new Label(
                        safe(
                                active.getCustomerName()
                        )
                );

        customer.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label vehicle =
                new Label(
                        safe(
                                active.getVehicleNumber()
                        ) +
                        " • " +
                        safe(
                                active.getDescription()
                        )
                );

        vehicle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        customerBox.getChildren().addAll(
                customer,
                vehicle
        );


        VBox locationBox =
                new VBox(4);


        Label locationTitle =
                new Label(
                        "LOCATION"
                );

        locationTitle.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        Label location =
                new Label(
                        safe(
                                active.getLocation()
                        )
                );

        location.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        locationBox.getChildren().addAll(
                locationTitle,
                location
        );


        Button viewButton =
                new Button(
                        "View Active Job"
                );

        viewButton.setMaxWidth(
                Double.MAX_VALUE
        );

        viewButton.setPadding(
                new Insets(11)
        );

        viewButton.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );


        viewButton.setOnAction(
                e -> {

                    activeJobPage.setJob(
                            active
                    );

                    showActiveJob();
                }
        );


        container.getChildren().addAll(
                title,
                statusBox,
                customerBox,
                locationBox,
                viewButton
        );

        return container;
    }


    // =========================================================
    // NAVIGATION PAGE
    // =========================================================
private ScrollPane createNavigationPage() {

    StackPane navigationRoot = new StackPane();

    Image backgroundImage = null;

    try {
        backgroundImage = new Image(
                getClass().getResourceAsStream(
                        "/images/navigation_background.png"
                )
        );
    } catch (Exception ignored) {
    }

    ImageView backgroundView = new ImageView();

    if (backgroundImage != null) {
        backgroundView.setImage(backgroundImage);
    }

    backgroundView.setPreserveRatio(false);

    backgroundView.fitWidthProperty().bind(
            navigationRoot.widthProperty()
    );

    backgroundView.fitHeightProperty().bind(
            navigationRoot.heightProperty()
    );

    Region overlay = new Region();

    overlay.setStyle(
            "-fx-background-color: rgba(0,0,0,0.10);"
    );

    overlay.setMouseTransparent(true);

    VBox content = new VBox(15);

    content.setPadding(
            new Insets(30, 30, 30, 30)
    );

    content.setAlignment(Pos.TOP_CENTER);

    Label title = new Label("Navigation");

    title.setStyle(
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
    );

    Label subtitle = new Label(
            "Route to the customer's location"
    );

    subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: white;"
    );

    ServiceRequest active =
            loadCurrentActiveRequest();

    double[] mechanicLocation =
            getMechanicLocation();

    double[] customerLocation =
            getCustomerLocation(active);

    WebView mapView =
            createRouteMap(
                    mechanicLocation[0],
                    mechanicLocation[1],
                    customerLocation[0],
                    customerLocation[1],
                    active
            );

    mapView.setPrefWidth(780);
    mapView.setPrefHeight(430);
    mapView.setMinWidth(700);
    mapView.setMinHeight(400);
    mapView.setMaxWidth(780);
    mapView.setMaxHeight(430);

    VBox locationCard =
            createLocationCard(
                    active,
                    mechanicLocation,
                    customerLocation
            );

    StackPane mapContainer = new StackPane();

    mapContainer.setPrefWidth(780);
    mapContainer.setPrefHeight(430);

    mapContainer.setMinWidth(700);
    mapContainer.setMinHeight(400);

    mapContainer.setMaxWidth(780);
    mapContainer.setMaxHeight(430);

    mapContainer.setStyle(
            "-fx-background-radius: 15;" +
            "-fx-border-radius: 15;" +
            "-fx-border-color: rgba(255,255,255,0.7);" +
            "-fx-effect: dropshadow(" +
            "gaussian, rgba(0,0,0,0.25), 15, 0.2, 0, 4);"
    );

    mapContainer.getChildren().add(mapView);

    StackPane.setAlignment(
            locationCard,
            Pos.TOP_LEFT
    );

    StackPane.setMargin(
            locationCard,
            new Insets(18)
    );

    mapContainer.getChildren().add(
            locationCard
    );

    content.getChildren().addAll(
            title,
            subtitle,
            mapContainer
    );

    StackPane.setAlignment(
            content,
            Pos.TOP_CENTER
    );

    navigationRoot.getChildren().addAll(
            backgroundView,
            overlay,
            content
    );

    ScrollPane scrollPane =
            new ScrollPane(navigationRoot);

    scrollPane.setFitToWidth(true);

    scrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
    );

    scrollPane.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
    );

    scrollPane.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: transparent;"
    );

    return scrollPane;
}
   // =========================================================
    // CREATE ROUTE MAP
    // =========================================================

    private WebView createRouteMap(
            double mechanicLat,
            double mechanicLon,
            double customerLat,
            double customerLon,
            ServiceRequest active
    ) {

        WebView webView =
                new WebView();

        WebEngine engine =
                webView.getEngine();

        engine.setJavaScriptEnabled(
                true
        );


        String customerName =
                active == null
                        ? "Customer"
                        : safe(
                                active.getCustomerName()
                        );


        String safeMechanicName =
                safeForJs(
                        mechanicName
                );

        String safeCustomerName =
                safeForJs(
                        customerName
                );


        String html =
                "<!DOCTYPE html>" +
                "<html>" +

                "<head>" +

                "<meta charset='UTF-8'>" +

                "<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>" +

                "<link rel='stylesheet' " +
                "href='https://unpkg.com/leaflet@1.9.4/dist/leaflet.css'>" +

                "<script " +
                "src='https://unpkg.com/leaflet@1.9.4/dist/leaflet.js'>" +
                "</script>" +

                "<style>" +

                "html, body, #map {" +
                "height:100%;" +
                "width:100%;" +
                "margin:0;" +
                "padding:0;" +
                "}" +

                ".info {" +
                "background:white;" +
                "padding:12px 15px;" +
                "border-radius:10px;" +
                "font-family:Arial;" +
                "font-size:13px;" +
                "line-height:1.6;" +
                "box-shadow:0 2px 12px rgba(0,0,0,0.25);" +
                "}" +

                ".title {" +
                "font-size:14px;" +
                "font-weight:bold;" +
                "margin-bottom:5px;" +
                "}" +

                "</style>" +

                "</head>" +

                "<body>" +

                "<div id='map'></div>" +

                "<script>" +

                // -------------------------------------------------
                // Coordinates
                // -------------------------------------------------

                "var mechanicLat = " +
                mechanicLat +
                ";" +

                "var mechanicLon = " +
                mechanicLon +
                ";" +

                "var customerLat = " +
                customerLat +
                ";" +

                "var customerLon = " +
                customerLon +
                ";" +


                // -------------------------------------------------
                // Map
                // -------------------------------------------------

                "var map = L.map('map').setView([" +
                mechanicLat +
                "," +
                mechanicLon +
                "], 13);" +


                // -------------------------------------------------
                // OpenStreetMap
                // -------------------------------------------------

                "L.tileLayer(" +
                "'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'," +
                "{" +
                "maxZoom:19," +
                "attribution:'© OpenStreetMap contributors'" +
                "}" +
                ").addTo(map);" +


                // -------------------------------------------------
                // Mechanic Marker
                // -------------------------------------------------

                "var mechanicMarker = L.marker([" +
                mechanicLat +
                "," +
                mechanicLon +
                "]).addTo(map);" +

                "mechanicMarker.bindPopup(" +
                "'<b>Mechanic</b><br>" +
                safeMechanicName +
                "'" +
                ");" +


                // -------------------------------------------------
                // Customer Marker
                // -------------------------------------------------

                "var customerMarker = L.marker([" +
                customerLat +
                "," +
                customerLon +
                "]).addTo(map);" +

                "customerMarker.bindPopup(" +
                "'<b>Customer</b><br>" +
                safeCustomerName +
                "'" +
                ");" +


                // -------------------------------------------------
                // Route
                // -------------------------------------------------

                "var routeUrl = " +

                "'https://router.project-osrm.org/route/v1/driving/' +" +

                "mechanicLon + ',' + mechanicLat +" +

                "';' +" +

                "customerLon + ',' + customerLat +" +

                "'?overview=full&geometries=geojson';" +


                "fetch(routeUrl)" +

                ".then(function(response) {" +

                "return response.json();" +

                "})" +

                ".then(function(data) {" +

                "if(data.routes && data.routes.length > 0) {" +


                // -------------------------------------------------
                // Route data
                // -------------------------------------------------

                "var route = data.routes[0];" +

                "var coordinates = " +

                "route.geometry.coordinates.map(" +

                "function(coord) {" +

                "return [coord[1], coord[0]];" +

                "}" +

                ");" +


                // -------------------------------------------------
                // Draw route
                // -------------------------------------------------

                "var routeLine = " +

                "L.polyline(" +

                "coordinates," +

                "{" +

                "color:'#2563eb'," +
                "weight:6," +
                "opacity:0.90," +
                "lineJoin:'round'" +

                "}" +

                ").addTo(map);" +


                // -------------------------------------------------
                // Fit route
                // -------------------------------------------------

                "map.fitBounds(" +

                "routeLine.getBounds()," +

                "{padding:[60,60]}" +

                ");" +


                // -------------------------------------------------
                // Distance
                // -------------------------------------------------

                "var distanceKm =" +

                "(route.distance / 1000).toFixed(1);" +


                // -------------------------------------------------
                // Duration
                // -------------------------------------------------

                "var durationMinutes =" +

                "Math.round(route.duration / 60);" +


                // -------------------------------------------------
                // Hours / Minutes
                // -------------------------------------------------

                "var hours =" +

                "Math.floor(durationMinutes / 60);" +

                "var minutes =" +

                "durationMinutes % 60;" +


                "var timeText = '';" +

                "if(hours > 0) {" +

                "timeText = hours + ' hr ' + minutes + ' min';" +

                "} else {" +

                "timeText = minutes + ' min';" +

                "}" +


                // -------------------------------------------------
                // Route info
                // -------------------------------------------------

                "var info = " +

                "L.control({position:'bottomleft'});" +


                "info.onAdd = function(map) {" +

                "var div = " +

                "L.DomUtil.create('div','info');" +


                "div.innerHTML =" +

                "'<div class=\"title\">Route Information</div>' +" +

                "'Distance: <b>' + " +
                "distanceKm + " +
                "' km</b><br>' +" +

                "'Estimated Time: <b>' + " +
                "timeText + " +
                "'</b>';" +


                "return div;" +

                "};" +


                "info.addTo(map);" +


                "} else {" +

                "console.log('No route found');" +

                "}" +

                "})" +


                ".catch(function(error) {" +

                "console.log('Route error:', error);" +

                "});" +


                "</script>" +

                "</body>" +

                "</html>";


        engine.loadContent(
                html
        );

        return webView;
    }


    // =========================================================
    // LOCATION CARD
    // =========================================================

    private VBox createLocationCard(
            ServiceRequest active,
            double[] mechanicLocation,
            double[] customerLocation
    ) {

        VBox card =
                new VBox(10);

        card.setPrefWidth(285);
        card.setMaxWidth(285);

        card.setPadding(
                new Insets(16)
        );

        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.96);" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: rgba(255,255,255,0.8);" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 12, 0.2, 0, 3);"
        );


        Label heading =
                new Label(
                        "LOCATION"
                );

        heading.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        // -----------------------------------------------------
        // MECHANIC
        // -----------------------------------------------------

        HBox mechanicRow =
                new HBox(8);

        mechanicRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Circle mechanicDot =
                new Circle(7);

        mechanicDot.setFill(
                Color.web(
                        Theme.PRIMARY
                )
        );


        VBox mechanicInfo =
                new VBox(2);


        Label mechanicTitle =
                new Label(
                        "Mechanic"
                );

        mechanicTitle.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        Label mechanicValue =
                new Label(
                        safe(
                                mechanicName
                        )
                );

        mechanicValue.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label mechanicCoordinates =
                new Label(
                        String.format(
                                "%.5f, %.5f",
                                mechanicLocation[0],
                                mechanicLocation[1]
                        )
                );

        mechanicCoordinates.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        mechanicInfo.getChildren().addAll(
                mechanicTitle,
                mechanicValue,
                mechanicCoordinates
        );


        mechanicRow.getChildren().addAll(
                mechanicDot,
                mechanicInfo
        );


        // -----------------------------------------------------
        // CUSTOMER
        // -----------------------------------------------------

        HBox customerRow =
                new HBox(8);

        customerRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Circle customerDot =
                new Circle(7);

        customerDot.setFill(
                Color.web(
                        Theme.SUCCESS
                )
        );


        VBox customerInfo =
                new VBox(2);


        Label customerTitle =
                new Label(
                        "Customer"
                );

        customerTitle.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        Label customerValue =
                new Label(
                        active == null
                                ? "No active customer"
                                : safe(
                                        active.getCustomerName()
                                )
                );

        customerValue.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label customerCoordinates =
                new Label(
                        String.format(
                                "%.5f, %.5f",
                                customerLocation[0],
                                customerLocation[1]
                        )
                );

        customerCoordinates.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        customerInfo.getChildren().addAll(
                customerTitle,
                customerValue,
                customerCoordinates
        );


        customerRow.getChildren().addAll(
                customerDot,
                customerInfo
        );


        // -----------------------------------------------------
        // STATUS
        // -----------------------------------------------------

        Label status =
                new Label(
                        active == null
                                ? "No Active Job"
                                : safe(
                                        active.getStatus()
                                )
                );

        status.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS_BG +
                ";" +
                "-fx-text-fill: " +
                Theme.SUCCESS +
                ";" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 5 10 5 10;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;"
        );


        card.getChildren().addAll(
                heading,
                mechanicRow,
                customerRow,
                status
        );

        return card;
    }


    // =========================================================
    // GET MECHANIC LOCATION
    // =========================================================

    private double[] getMechanicLocation() {

        // Your Firebase mechanic coordinates
        double defaultLat = 18.5011;
        double defaultLon = 73.8627;


        try {

            Firestore firestore =
                    FirebaseConfig.getFirestore();


            DocumentSnapshot document =
                    firestore
                            .collection("mechanics")
                            .document(mechanicId)
                            .get()
                            .get();


            if (!document.exists()) {

                System.out.println(
                        "MECHANIC LOCATION NOT FOUND: " +
                        mechanicId
                );

                return new double[]{
                        defaultLat,
                        defaultLon
                };
            }


            Double latitude =
                    document.getDouble(
                            "latitude"
                    );

            Double longitude =
                    document.getDouble(
                            "longitude"
                    );


            if (
                    latitude == null ||
                    longitude == null
            ) {

                System.out.println(
                        "MECHANIC LAT/LONG NOT FOUND"
                );

                return new double[]{
                        defaultLat,
                        defaultLon
                };
            }


            System.out.println(
                    "MECHANIC LOCATION = " +
                    latitude +
                    ", " +
                    longitude
            );


            return new double[]{
                    latitude,
                    longitude
            };


        } catch (Exception e) {

            System.out.println(
                    "ERROR GETTING MECHANIC LOCATION"
            );

            e.printStackTrace();


            return new double[]{
                    defaultLat,
                    defaultLon
            };
        }
    }
// =====================================================
// GET CUSTOMER LOCATION FROM FIRESTORE
// =====================================================

private double[] getCustomerLocation(ServiceRequest active) {

    // Default location
    double defaultLat = 18.466921;
    double defaultLon = 73.826561;

    try {

        // No active request
        if (active == null) {

            System.out.println(
                    "CUSTOMER LOCATION: No active request"
            );

            return new double[]{
                    defaultLat,
                    defaultLon
            };
        }

        // Get customer ID from ServiceRequest
        String customerId =
                active.getCustomerId();

        if (
                customerId == null ||
                customerId.trim().isEmpty()
        ) {

            System.out.println(
                    "CUSTOMER LOCATION: Customer ID not found"
            );

            return new double[]{
                    defaultLat,
                    defaultLon
            };
        }

        System.out.println(
                "CUSTOMER ID = " +
                customerId
        );

        // Firebase
        Firestore firestore =
                FirebaseConfig.getFirestore();

        // Find customer directly using document ID
        DocumentSnapshot document =
                firestore
                        .collection("customers")
                        .document(customerId)
                        .get()
                        .get();

        // Customer not found
        if (!document.exists()) {

            System.out.println(
                    "CUSTOMER DOCUMENT NOT FOUND: " +
                    customerId
            );

            return new double[]{
                    defaultLat,
                    defaultLon
            };
        }

        // Get latitude
        Double latitude =
                document.getDouble("latitude");

        // Get longitude
        Double longitude =
                document.getDouble("longitude");

        // Location not available
        if (
                latitude == null ||
                longitude == null
        ) {

            System.out.println(
                    "CUSTOMER LATITUDE/LONGITUDE NOT FOUND"
            );

            return new double[]{
                    defaultLat,
                    defaultLon
            };
        }

        System.out.println(
                "CUSTOMER LOCATION = " +
                latitude +
                ", " +
                longitude
        );

        return new double[]{
                latitude,
                longitude
        };

    } catch (Exception e) {

        System.out.println(
                "ERROR GETTING CUSTOMER LOCATION"
        );

        e.printStackTrace();

        return new double[]{
                defaultLat,
                defaultLon
        };
    }
}
    // =========================================================
    // SAFE JAVASCRIPT STRING
    // =========================================================

    private String safeForJs(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {

            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\"", "\\\"");
    }


    // =========================================================
    // JOB HISTORY
    // =========================================================

    private ScrollPane createHistoryPage() {

        VBox page =
                new VBox(20);

        page.setPadding(
                new Insets(
                        32,
                        35,
                        35,
                        35
                )
        );


        Label title =
                new Label(
                        "Job History"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label subtitle =
                new Label(
                        "View your completed service jobs."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        VBox historyCard =
                new VBox(10);

        historyCard.setPadding(
                new Insets(22)
        );

        historyCard.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );


        List<ServiceRequest> completed =
                getMechanicRequests()
                        .stream()
                        .filter(
                                r ->
                                        "Completed"
                                                .equalsIgnoreCase(
                                                        safe(
                                                                r.getStatus()
                                                        )
                                                )
                        )
                        .collect(
                                Collectors.toList()
                        );


        for (
                ServiceRequest request :
                completed
        ) {

            historyCard.getChildren().add(
                    createHistoryRow(
                            request
                    )
            );
        }


        if (completed.isEmpty()) {

            Label empty =
                    new Label(
                            "No completed jobs found."
                    );

            empty.setStyle(
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: " +
                    Theme.SECONDARY_TEXT +
                    ";"
            );

            historyCard.getChildren().add(
                    empty
            );
        }


        page.getChildren().addAll(
                title,
                subtitle,
                historyCard
        );


        ScrollPane scrollPane =
                new ScrollPane(page);

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        return scrollPane;
    }


    // =========================================================
    // HISTORY ROW
    // =========================================================

    private HBox createHistoryRow(
            ServiceRequest request
    ) {

        HBox row =
                new HBox(15);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(15)
        );

        row.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-background-radius: 10;"
        );


        VBox info =
                new VBox(4);


        Label customerLabel =
                new Label(
                        safe(
                                request.getCustomerName()
                        )
                );

        customerLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label vehicleLabel =
                new Label(
                        safe(
                                request.getVehicleNumber()
                        ) +
                        " • " +
                        safe(
                                request.getDescription()
                        )
                );

        vehicleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        Label dateLabel =
                new Label(
                        formatDate(
                                request.getCompletedDate()
                        )
                );

        dateLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        info.getChildren().addAll(
                customerLabel,
                vehicleLabel,
                dateLabel
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        Label amountLabel =
                new Label(
                        "₹" +
                        String.format(
                                "%.0f",
                                request.getTotalAmount()
                        )
                );

        amountLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label statusLabel =
                new Label(
                        safe(
                                request.getStatus()
                        )
                );

        statusLabel.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS_BG +
                ";" +
                "-fx-text-fill: " +
                Theme.SUCCESS +
                ";" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 5 10 5 10;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;"
        );


        row.getChildren().addAll(
                info,
                spacer,
                amountLabel,
                statusLabel
        );

        return row;
    }


    // =========================================================
    // LOGOUT
    // =========================================================

    private void showLogout() {

        setActiveButton(
                null
        );

        root.setCenter(
                createLogoutPage()
        );
    }


    // =========================================================
    // LOGOUT PAGE
    // =========================================================

    private VBox createLogoutPage() {

        VBox page =
                new VBox(15);

        page.setAlignment(
                Pos.CENTER
        );

        page.setPadding(
                new Insets(35)
        );

        page.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND +
                ";"
        );


        Label title =
                new Label(
                        "Logged Out"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );


        Label message =
                new Label(
                        "Mechanic session has been closed."
                );

        message.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );


        Button closeButton =
                new Button(
                        "Close"
                );

        closeButton.setPadding(
                new Insets(
                        11,
                        20,
                        11,
                        20
                )
        );

        closeButton.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );


        closeButton.setOnAction(
                e -> {

                    if (
                            LandingPage.appMainStage != null
                    ) {

                        LandingPage.appMainStage.close();
                    }
                }
        );


        page.getChildren().addAll(
                title,
                message,
                closeButton
        );

        return page;
    }


    // =========================================================
    // OPEN ACCEPTED JOB
    // =========================================================

    public void openAcceptedJob(
            ServiceRequest request
    ) {

        if (request == null) {
            return;
        }

        currentActiveRequest =
                request;


        if (activeJobPage != null) {

            activeJobPage.setJob(
                    request
            );
        }


        showActiveJob();
    }


    // =========================================================
    // GET MECHANIC REQUESTS
    // =========================================================

    private List<ServiceRequest> getMechanicRequests() {

        if (
                controller == null ||
                mechanicId == null ||
                mechanicId.isBlank()
        ) {

            System.out.println(
                    "MECHANIC DEBUG: mechanicId is EMPTY"
            );

            return Collections.emptyList();
        }


        System.out.println(
                "MECHANIC DEBUG: mechanicId = " +
                mechanicId
        );


        List<ServiceRequest> requests =
                controller.getRequestsByMechanicId(
                        mechanicId
                );


        if (requests == null) {

            return Collections.emptyList();
        }


        System.out.println(
                "MECHANIC DEBUG: requests found = " +
                requests.size()
        );


        for (
                ServiceRequest request :
                requests
        ) {

            System.out.println(
                    "REQUEST = " +
                    request.getRequestId() +
                    " | CUSTOMER = " +
                    request.getCustomerName() +
                    " | STATUS = " +
                    request.getStatus() +
                    " | MECHANIC = " +
                    request.getMechanicId()
            );
        }


        return requests;
    }


    // =========================================================
    // LOAD CURRENT ACTIVE REQUEST
    // =========================================================

    private ServiceRequest loadCurrentActiveRequest() {

        List<ServiceRequest> requests =
                getMechanicRequests();


        currentActiveRequest =
                findActiveRequest(
                        requests
                );


        if (
                currentActiveRequest != null &&
                activeJobPage != null
        ) {

            activeJobPage.setJob(
                    currentActiveRequest
            );
        }


        return currentActiveRequest;
    }


    // =========================================================
    // FIND ACTIVE REQUEST
    // =========================================================

    private ServiceRequest findActiveRequest(
            List<ServiceRequest> requests
    ) {

        if (requests == null) {

            return null;
        }


        for (
                ServiceRequest request :
                requests
        ) {

            if (request == null) {
                continue;
            }


            String status =
                    safe(
                            request.getStatus()
                    );


            if (
                    "Accepted"
                            .equalsIgnoreCase(status)
                    ||
                    "In Progress"
                            .equalsIgnoreCase(status)
            ) {

                return request;
            }
        }


        return null;
    }


    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {

            return "-";
        }

        return value;
    }


    // =========================================================
    // FORMAT DATE
    // =========================================================

    private String formatDate(
            String date
    ) {

        if (
                date == null ||
                date.isBlank()
        ) {

            return "-";
        }


        try {

            long timestamp =
                    Long.parseLong(
                            date
                    );


            LocalDateTime dateTime =
                    LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(
                                    timestamp
                            ),
                            ZoneId.systemDefault()
                    );


            return dateTime.format(
                    DateTimeFormatter.ofPattern(
                            "dd MMM yyyy, hh:mm a"
                    )
            );


        } catch (Exception e) {

            return date;
        }
    }
}
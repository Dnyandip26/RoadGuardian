package project.ui.mechanic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import project.ui.landing.LandingPage;
import project.util.Theme;

public class MechanicDashboard {

    private BorderPane root;

    private RequestsPage requestsPage;
    private ActiveJobPage activeJobPage;

    private Button dashboardButton;
    private Button requestsButton;
    private Button navigationButton;
    private Button activeJobButton;
    private Button historyButton;

    // =========================
    // DASHBOARD
    // =========================

    public Scene getDashboardScene() {

        root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND +
                ";"
        );
        activeJobPage = new ActiveJobPage();

        requestsPage = new RequestsPage(
                activeJobPage,
                this::showActiveJob
        );

        root.setLeft(createSidebar());

        showDashboard();

        return new Scene(root, LandingPage.appMainStage.getWidth(), LandingPage.appMainStage.getHeight());
    }

    // =========================
    // SIDEBAR
    // =========================

    private VBox createSidebar() {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(245);
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(28, 18, 25, 18));

        sidebar.setStyle(
                "-fx-background-color: " +
                Theme.SIDEBAR +
                ";"
        );

        HBox logoBox = new HBox(10);

        logoBox.setAlignment(Pos.CENTER_LEFT);
        logoBox.setPadding(new Insets(0, 8, 28, 8));

        Circle logoCircle = new Circle(20);

        logoCircle.setFill(
                Color.web(Theme.PRIMARY)
        );

        Label logoText = new Label("RoadGuardian");

        logoText.setTextFill(
                Color.web(Theme.TEXT)
        );

        logoText.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        logoBox.getChildren().addAll(
                logoCircle,
                logoText
        );

        Label roleLabel = new Label(
                "MECHANIC PORTAL"
        );

        roleLabel.setTextFill(
                Color.web(Theme.SECONDARY_TEXT)
        );

        roleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        roleLabel.setPadding(
                new Insets(0, 8, 8, 8)
        );

        dashboardButton =
                createMenuButton("⌂   Dashboard");

        requestsButton =
                createMenuButton("▣   Service Requests");

        navigationButton =
                createMenuButton("➤   Navigation");

        activeJobButton =
                createMenuButton("⚙   Active Job");

        historyButton =
                createMenuButton("◷   Job History");

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

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        VBox profileBox = new VBox(4);

        profileBox.setPadding(
                new Insets(14)
        );

        profileBox.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-background-radius: 12;"
        );

        Label mechanicName =
                new Label("Rajesh Patil");

        mechanicName.setTextFill(
                Color.web(Theme.TEXT)
        );

        mechanicName.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        Label mechanicRole =
                new Label("Verified Mechanic");

        mechanicRole.setTextFill(
                Color.web(Theme.SECONDARY_TEXT)
        );

        mechanicRole.setStyle(
                "-fx-font-size: 11px;"
        );

        profileBox.getChildren().addAll(
                mechanicName,
                mechanicRole
        );

        Button logoutButton =
                createMenuButton("↪   Logout");

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

    // =========================
    // MENU BUTTON
    // =========================

    private Button createMenuButton(String text) {

        Button button = new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(13, 15, 13, 15)
        );

        setNormalStyle(button);

        button.setOnMouseEntered(e -> {

            if (!isActive(button)) {
                setHoverStyle(button);
            }
        });

        button.setOnMouseExited(e -> {

            if (!isActive(button)) {
                setNormalStyle(button);
            }
        });

        return button;
    }

    private void setNormalStyle(Button button) {

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

    private void setHoverStyle(Button button) {

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

    private void setActiveStyle(Button button) {

        button.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY +
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

    private boolean isActive(Button button) {

        return button.getStyle().contains(
                Theme.PRIMARY
        );
    }

    private void setActiveButton(Button activeButton) {

        setNormalStyle(dashboardButton);
        setNormalStyle(requestsButton);
        setNormalStyle(navigationButton);
        setNormalStyle(activeJobButton);
        setNormalStyle(historyButton);

        setActiveStyle(activeButton);
    }

    // =========================
    // PAGE NAVIGATION
    // =========================

    private void showDashboard() {

        setActiveButton(
                dashboardButton
        );

        root.setCenter(
                createDashboard()
        );
    }

    private void showRequests() {

        setActiveButton(
                requestsButton
        );

        root.setCenter(
                requestsPage.getContent()
        );
    }

    private void showNavigation() {

        setActiveButton(
                navigationButton
        );

        root.setCenter(
                createNavigationPage()
        );
    }

    private void showActiveJob() {

        setActiveButton(
                activeJobButton
        );

        root.setCenter(
                activeJobPage.getContent()
        );
    }

    private void showHistory() {

        setActiveButton(
                historyButton
        );

        root.setCenter(
                createHistoryPage()
        );
    }

    private void showLogout() {

        setActiveButton(null);

        root.setCenter(
                createLogoutPage()
        );
    }

    // =========================
    // DASHBOARD PAGE
    // =========================

    private ScrollPane createDashboard() {

        VBox content = new VBox(25);

        content.setPadding(
                new Insets(32, 35, 35, 35)
        );

        HBox header =
                createHeader();

        HBox statCards =
                new HBox(18);

        statCards.getChildren().addAll(

                createStatCard(
                        "Pending Requests",
                        "05",
                        "Waiting for response",
                        Theme.PRIMARY
                ),

                createStatCard(
                        "Active Jobs",
                        "02",
                        "Currently handling",
                        Theme.INFO
                ),

                createStatCard(
                        "Completed",
                        "128",
                        "Total services",
                        Theme.SUCCESS
                ),

                createStatCard(
                        "Rating",
                        "4.8",
                        "★★★★★",
                        "#CA8A04"
                )
        );

        VBox recentRequests =
                createRecentRequests();

        VBox activeJob =
                createActiveJob();

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

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        return scrollPane;
    }

    // =========================
    // DASHBOARD HEADER
    // =========================

    private HBox createHeader() {

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(5);

        Label welcome =
                new Label(
                        "Good Evening, Rajesh 👋"
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

        Region spacer = new Region();

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
                new Insets(10, 16, 10, 16)
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
                Color.web(Theme.SUCCESS)
        );

        Label statusText =
                new Label("You're Online");

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

    // =========================
    // STAT CARD
    // =========================

    private VBox createStatCard(
            String title,
            String value,
            String subtitle,
            String accent
    ) {

        VBox card = new VBox(8);

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

        HBox titleRow = new HBox();

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

        Region spacer = new Region();

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

    // =========================
    // RECENT REQUESTS
    // =========================

    private VBox createRecentRequests() {

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
                        "Latest requests waiting for your action"
                );

        description.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        VBox requests =
                new VBox(10);

        requests.getChildren().addAll(

                createRequestRow(
                        "Rohit Najan",
                        "Maruti Swift",
                        "Battery Issue",
                        "2.4 km",
                        "5 min ago"
                ),

                createRequestRow(
                        "Dnyandip Vadane",
                        "Hyundai i20",
                        "Flat Tyre",
                        "4.1 km",
                        "12 min ago"
                ),

                createRequestRow(
                        "Akash Patil",
                        "Honda City",
                        "Engine Problem",
                        "6.8 km",
                        "18 min ago"
                )
        );

        container.getChildren().addAll(
                title,
                description,
                requests
        );

        return container;
    }

    private HBox createRequestRow(
            String customer,
            String vehicle,
            String problem,
            String distance,
            String time
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

        Circle avatar =
                new Circle(20);

        avatar.setFill(
                Color.web("#FFF7ED")
        );

        VBox customerBox =
                new VBox(4);

        Label customerLabel =
                new Label(customer);

        customerLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label vehicleLabel =
                new Label(
                        vehicle +
                        " • " +
                        problem
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

        VBox distanceBox =
                new VBox(3);

        distanceBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label distanceLabel =
                new Label(distance);

        distanceLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label timeLabel =
                new Label(time);

        timeLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        distanceBox.getChildren().addAll(
                distanceLabel,
                timeLabel
        );

        row.getChildren().addAll(
                avatar,
                customerBox,
                spacer,
                distanceBox
        );

        return row;
    }

    // =========================
    // ACTIVE JOB CARD
    // =========================

    private VBox createActiveJob() {

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
                new Label("Active Job");

        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        HBox statusBox =
                new HBox(8);

        statusBox.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle dot =
                new Circle(5);

        dot.setFill(
                Color.web(Theme.INFO)
        );

        Label status =
                new Label("In Progress");

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
                        "Vishal Vadane"
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
                        "Toyota Innova • Engine Issue"
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
                new Label("LOCATION");

        locationTitle.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Label location =
                new Label("Kothrud, Pune");

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
                new Button("View Active Job");

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
                e -> showActiveJob()
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

    // =========================
    // NAVIGATION PAGE
    // =========================

    private ScrollPane createNavigationPage() {

        VBox page =
                new VBox(20);

        page.setPadding(
                new Insets(32, 35, 35, 35)
        );

        Label title =
                new Label("Navigation");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label subtitle =
                new Label(
                        "Navigate to the customer's location."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        VBox card =
                new VBox(15);

        card.setPadding(
                new Insets(25)
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

        Label destinationTitle =
                new Label("Current Destination");

        destinationTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Label destination =
                new Label("Kothrud, Pune");

        destination.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label distance =
                new Label("Distance: 2.4 km");

        distance.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Button startNavigation =
                new Button("Start Navigation");

        startNavigation.setPadding(
                new Insets(12, 20, 12, 20)
        );

        startNavigation.setStyle(
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

        startNavigation.setOnAction(e -> {

            startNavigation.setText(
                    "✓ Navigation Started"
            );

            startNavigation.setDisable(true);

            startNavigation.setStyle(
                    "-fx-background-color: " +
                    Theme.SUCCESS_BG +
                    ";" +
                    "-fx-text-fill: " +
                    Theme.SUCCESS +
                    ";" +
                    "-fx-background-radius: 8;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;"
            );
        });

        card.getChildren().addAll(
                destinationTitle,
                destination,
                distance,
                startNavigation
        );

        page.getChildren().addAll(
                title,
                subtitle,
                card
        );

        ScrollPane scrollPane =
                new ScrollPane(page);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        return scrollPane;
    }

    // =========================
    // HISTORY PAGE
    // =========================

    private ScrollPane createHistoryPage() {

        VBox page =
                new VBox(20);

        page.setPadding(
                new Insets(32, 35, 35, 35)
        );

        Label title =
                new Label("Job History");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label subtitle =
                new Label(
                        "View your recently completed service jobs."
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

        historyCard.getChildren().addAll(

                createHistoryRow(
                        "Ganesh Tupe",
                        "Toyota Innova",
                        "Engine Issue",
                        "₹1,850",
                        "Completed"
                ),

                createHistoryRow(
                        "Kartik Shinde",
                        "Tata Nexon",
                        "Car Not Starting",
                        "₹750",
                        "Completed"
                ),

                createHistoryRow(
                        "Sachin Garudkar",
                        "Maruti Swift",
                        "Battery Issue",
                        "₹600",
                        "Completed"
                )
        );

        page.getChildren().addAll(
                title,
                subtitle,
                historyCard
        );

        ScrollPane scrollPane =
                new ScrollPane(page);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        return scrollPane;
    }

    private HBox createHistoryRow(
            String customer,
            String vehicle,
            String problem,
            String amount,
            String status
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
                new Label(customer);

        customerLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label vehicleLabel =
                new Label(
                        vehicle +
                        " • " +
                        problem
                );

        vehicleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        info.getChildren().addAll(
                customerLabel,
                vehicleLabel
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label amountLabel =
                new Label(amount);

        amountLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label statusLabel =
                new Label(status);

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

    // =========================
    // LOGOUT PAGE
    // =========================

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
                new Label("Logout");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label message =
                new Label(
                        "You have selected Logout."
                );

        message.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Button backButton =
                new Button("Back to Dashboard");

        backButton.setPadding(
                new Insets(11, 20, 11, 20)
        );

        backButton.setStyle(
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

        backButton.setOnAction(
                e -> showDashboard()
        );

        page.getChildren().addAll(
                title,
                message,
                backButton
        );

        return page;
    }

    // =========================
    // ACCEPTED JOB
    // =========================

    public void openAcceptedJob(
            String customer,
            String vehicle,
            String problem,
            String type,
            String distance,
            String amount
    ) {

        activeJobPage.setJob(
                customer,
                vehicle,
                problem,
                type,
                distance,
                amount
        );

        showActiveJob();
    }
}
package project.ui.mechanic;

import javafx.application.Application;
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
import javafx.stage.Stage;
import project.util.Theme;

public class MechanicDashboard extends Application {

    private BorderPane root;
    private VBox contentArea;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();
        root.setStyle("-fx-background-color: " + Theme.BACKGROUND + ";");

        root.setLeft(createSidebar());
        root.setCenter(createDashboard());

        Scene scene = new Scene(root, 1280, 760);

        stage.setTitle("RoadGuardian - Mechanic Dashboard");
        stage.setScene(scene);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(245);
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(28, 18, 25, 18));
        sidebar.setStyle("-fx-background-color: " + Theme.SIDEBAR + ";");

        HBox logoBox = new HBox(10);
        logoBox.setAlignment(Pos.CENTER_LEFT);
        logoBox.setPadding(new Insets(0, 8, 28, 8));

        Circle logoCircle = new Circle(20);
        logoCircle.setFill(Color.web(Theme.PRIMARY));

        Label logoText = new Label("RoadGuardian");
        logoText.setTextFill(Color.web(Theme.TEXT));
        logoText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        logoBox.getChildren().addAll(logoCircle, logoText);

        Label roleLabel = new Label("MECHANIC PORTAL");
        roleLabel.setTextFill(Color.web(Theme.SECONDARY_TEXT));
        roleLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");
        roleLabel.setPadding(new Insets(0, 8, 8, 8));

        Button dashboardButton = createMenuButton("⌂   Dashboard", true);
        Button requestsButton = createMenuButton("▣   Service Requests", false);
        Button navigationButton = createMenuButton("➤   Navigation", false);
        Button activeJobButton = createMenuButton("⚙   Active Job", false);
        Button historyButton = createMenuButton("◷   Job History", false);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox profileBox = new VBox(4);
        profileBox.setPadding(new Insets(14));
        profileBox.setStyle(
                "-fx-background-color: " + Theme.SURFACE + ";" +
                "-fx-background-radius: 12;"
        );

        Label mechanicName = new Label("Rajesh Patil");
        mechanicName.setTextFill(Color.web(Theme.TEXT));
        mechanicName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label mechanicRole = new Label("Verified Mechanic");
        mechanicRole.setTextFill(Color.web(Theme.SECONDARY_TEXT));
        mechanicRole.setStyle("-fx-font-size: 11px;");

        profileBox.getChildren().addAll(mechanicName, mechanicRole);

        Button logoutButton = createMenuButton("↪   Logout", false);

        dashboardButton.setOnAction(e -> root.setCenter(createDashboard()));
        requestsButton.setOnAction(e -> root.setCenter(createPlaceholder("Service Requests")));
        navigationButton.setOnAction(e -> root.setCenter(createPlaceholder("Navigation")));
        activeJobButton.setOnAction(e -> root.setCenter(createPlaceholder("Active Job")));
        historyButton.setOnAction(e -> root.setCenter(createPlaceholder("Job History")));

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

    private Button createMenuButton(String text, boolean active) {
        Button button = new Button(text);

        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(13, 15, 13, 15));

        if (active) {
            button.setStyle(
                    "-fx-background-color: " + Theme.PRIMARY + ";" +
                    "-fx-text-fill: " + Theme.WHITE + ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;"
            );
        } else {
            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " + Theme.TEXT + ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 13px;" +
                    "-fx-cursor: hand;"
            );
        }

        button.setOnMouseEntered(e -> {
            if (!active) {
                button.setStyle(
                        "-fx-background-color: " + Theme.SURFACE + ";" +
                        "-fx-text-fill: " + Theme.TEXT + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 13px;" +
                        "-fx-cursor: hand;"
                );
            }
        });

        button.setOnMouseExited(e -> {
            if (!active) {
                button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: " + Theme.TEXT + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 13px;" +
                        "-fx-cursor: hand;"
                );
            }
        });

        return button;
    }

    private ScrollPane createDashboard() {
        contentArea = new VBox(25);
        contentArea.setPadding(new Insets(32, 35, 35, 35));

        HBox header = createHeader();

        HBox statCards = new HBox(18);
        statCards.getChildren().addAll(
                createStatCard("Pending Requests", "05", "Waiting for response", Theme.PRIMARY),
                createStatCard("Active Jobs", "02", "Currently handling", Theme.INFO),
                createStatCard("Completed", "128", "Total services", Theme.SUCCESS),
                createStatCard("Rating", "4.8", "★★★★★", "#CA8A04")
        );

        HBox.setHgrow(statCards, Priority.ALWAYS);

        VBox recentRequests = createRecentRequests();
        VBox activeJob = createActiveJob();

        HBox lowerSection = new HBox(20);
        lowerSection.getChildren().addAll(recentRequests, activeJob);

        HBox.setHgrow(recentRequests, Priority.ALWAYS);
        HBox.setHgrow(activeJob, Priority.ALWAYS);

        contentArea.getChildren().addAll(
                header,
                statCards,
                lowerSection
        );

        ScrollPane scrollPane = new ScrollPane(contentArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        return scrollPane;
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        VBox titleBox = new VBox(5);

        Label welcome = new Label("Good Evening, Rajesh 👋");
        welcome.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.TEXT + ";"
        );

        Label subtitle = new Label("Here's what's happening with your services today.");
        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";"
        );

        titleBox.getChildren().addAll(welcome, subtitle);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox statusBox = new HBox(9);
        statusBox.setAlignment(Pos.CENTER);
        statusBox.setPadding(new Insets(10, 16, 10, 16));
        statusBox.setStyle(
                "-fx-background-color: " + Theme.SUCCESS_BG + ";" +
                "-fx-background-radius: 20;"
        );

        Circle statusCircle = new Circle(5);
        statusCircle.setFill(Color.web(Theme.SUCCESS));

        Label statusText = new Label("You're Online");
        statusText.setStyle(
                "-fx-text-fill: " + Theme.SUCCESS + ";" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );

        statusBox.getChildren().addAll(statusCircle, statusText);

        header.getChildren().addAll(titleBox, spacer, statusBox);

        return header;
    }

    private VBox createStatCard(
            String title,
            String value,
            String subtitle,
            String accent
    ) {
        VBox card = new VBox(8);
        card.setPrefHeight(125);
        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color: " + Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " + Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        HBox titleRow = new HBox();
        titleRow.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label(title);
        titleLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";" +
                "-fx-font-weight: bold;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Circle icon = new Circle(17);
        icon.setFill(Color.web(accent));

        titleRow.getChildren().addAll(titleLabel, spacer, icon);

        Label valueLabel = new Label(value);
        valueLabel.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.TEXT + ";"
        );

        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";"
        );

        card.getChildren().addAll(
                titleRow,
                valueLabel,
                subtitleLabel
        );

        HBox.setHgrow(card, Priority.ALWAYS);

        return card;
    }

    private VBox createRecentRequests() {
        VBox container = new VBox(15);
        container.setPadding(new Insets(22));
        container.setStyle(
                "-fx-background-color: " + Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " + Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        Label title = new Label("Recent Service Requests");
        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.TEXT + ";"
        );

        Label description = new Label("Latest requests waiting for your action");
        description.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";"
        );

        VBox requests = new VBox(10);

        requests.getChildren().addAll(
                createRequestRow(
                        "Amit Sharma",
                        "Maruti Swift",
                        "Battery Issue",
                        "2.4 km",
                        "5 min ago"
                ),
                createRequestRow(
                        "Priya Joshi",
                        "Hyundai i20",
                        "Flat Tyre",
                        "4.1 km",
                        "12 min ago"
                ),
                createRequestRow(
                        "Rahul Deshmukh",
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
        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(12));
        row.setStyle(
                "-fx-background-color: " + Theme.SURFACE + ";" +
                "-fx-background-radius: 10;"
        );

        Circle avatar = new Circle(20);
        avatar.setFill(Color.web("#FFF7ED"));

        VBox customerBox = new VBox(4);

        Label customerLabel = new Label(customer);
        customerLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.TEXT + ";"
        );

        Label vehicleLabel = new Label(vehicle + " • " + problem);
        vehicleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";"
        );

        customerBox.getChildren().addAll(
                customerLabel,
                vehicleLabel
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        VBox distanceBox = new VBox(3);
        distanceBox.setAlignment(Pos.CENTER_RIGHT);

        Label distanceLabel = new Label(distance);
        distanceLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.TEXT + ";"
        );

        Label timeLabel = new Label(time);
        timeLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";"
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

    private VBox createActiveJob() {
        VBox container = new VBox(15);
        container.setPadding(new Insets(22));
        container.setStyle(
                "-fx-background-color: " + Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " + Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        Label title = new Label("Active Job");
        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.TEXT + ";"
        );

        HBox jobStatus = new HBox(8);
        jobStatus.setAlignment(Pos.CENTER_LEFT);

        Circle dot = new Circle(5);
        dot.setFill(Color.web(Theme.INFO));

        Label status = new Label("In Progress");
        status.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.INFO + ";"
        );

        jobStatus.getChildren().addAll(dot, status);

        VBox customerBox = new VBox(5);

        Label customer = new Label("Vikram Kulkarni");
        customer.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.TEXT + ";"
        );

        Label vehicle = new Label("Toyota Innova • Engine Issue");
        vehicle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";"
        );

        customerBox.getChildren().addAll(customer, vehicle);

        VBox locationBox = new VBox(4);

        Label locationTitle = new Label("LOCATION");
        locationTitle.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";"
        );

        Label location = new Label("Kothrud, Pune");
        location.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + Theme.TEXT + ";"
        );

        locationBox.getChildren().addAll(
                locationTitle,
                location
        );

        Button viewButton = new Button("View Active Job");
        viewButton.setMaxWidth(Double.MAX_VALUE);
        viewButton.setPadding(new Insets(11));
        viewButton.setStyle(
                "-fx-background-color: " + Theme.PRIMARY + ";" +
                "-fx-text-fill: " + Theme.WHITE + ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        viewButton.setOnAction(e ->
                root.setCenter(createPlaceholder("Active Job"))
        );

        container.getChildren().addAll(
                title,
                jobStatus,
                customerBox,
                locationBox,
                viewButton
        );

        return container;
    }

    private VBox createPlaceholder(String pageName) {
        VBox page = new VBox(15);
        page.setAlignment(Pos.TOP_LEFT);
        page.setPadding(new Insets(35));
        page.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND +
                ";"
        );

        Label title = new Label(pageName);
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + Theme.TEXT + ";"
        );

        Label message = new Label(
                pageName + " UI will be implemented as a separate page."
        );
        message.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + Theme.SECONDARY_TEXT + ";"
        );

        page.getChildren().addAll(title, message);

        return page;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package project.ui.mechanic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import project.util.Theme;

public class RequestsPage extends Application {

    private BorderPane root;
    private VBox requestsContainer;
    private TextField searchField;

    @Override
    public void start(Stage stage) {

        root = new BorderPane();
        root.setStyle(
                "-fx-background-color: " + Theme.BACKGROUND + ";"
        );

        root.setLeft(createSidebar());
        root.setCenter(createContent());

        Scene scene = new Scene(root, 1280, 800);

        stage.setTitle("RoadGuardian - Service Requests");
        stage.setScene(scene);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.show();
    }

    private VBox createSidebar() {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(245);
        sidebar.setSpacing(10);
        sidebar.setPadding(
                new Insets(28, 18, 25, 18)
        );

        sidebar.setStyle(
                "-fx-background-color: " +
                Theme.SIDEBAR + ";"
        );

        HBox logoBox = new HBox(10);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        logoBox.setPadding(
                new Insets(0, 8, 28, 8)
        );

        Circle logoCircle =
                new Circle(20);

        logoCircle.setFill(
                Color.web(Theme.PRIMARY)
        );

        Label logoText =
                new Label("RoadGuardian");

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

        Label roleLabel =
                new Label("MECHANIC PORTAL");

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

        Button dashboardButton =
                createMenuButton(
                        "⌂   Dashboard",
                        false
                );

        Button requestsButton =
                createMenuButton(
                        "▣   Service Requests",
                        true
                );

        Button navigationButton =
                createMenuButton(
                        "➤   Navigation",
                        false
                );

        Button activeJobButton =
                createMenuButton(
                        "⚙   Active Job",
                        false
                );

        Button historyButton =
                createMenuButton(
                        "◷   Job History",
                        false
                );

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        VBox profileBox =
                new VBox(4);

        profileBox.setPadding(
                new Insets(14)
        );

        profileBox.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE + ";" +
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
                createMenuButton(
                        "↪   Logout",
                        false
                );

        dashboardButton.setOnAction(
                e -> root.setCenter(
                        createPlaceholder("Dashboard")
                )
        );

        navigationButton.setOnAction(
                e -> root.setCenter(
                        createPlaceholder("Navigation")
                )
        );

        activeJobButton.setOnAction(
                e -> root.setCenter(
                        createPlaceholder("Active Job")
                )
        );

        historyButton.setOnAction(
                e -> root.setCenter(
                        createPlaceholder("Job History")
                )
        );

        requestsButton.setOnAction(
                e -> root.setCenter(
                        createContent()
                )
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

    private Button createMenuButton(
            String text,
            boolean active
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
                new Insets(13, 15, 13, 15)
        );

        if (active) {

            button.setStyle(
                    "-fx-background-color: " +
                    Theme.PRIMARY + ";" +
                    "-fx-text-fill: " +
                    Theme.WHITE + ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " +
                    Theme.TEXT + ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 13px;" +
                    "-fx-cursor: hand;"
            );
        }

        button.setOnMouseEntered(e -> {

            if (!active) {

                button.setStyle(
                        "-fx-background-color: " +
                        Theme.SURFACE + ";" +
                        "-fx-text-fill: " +
                        Theme.TEXT + ";" +
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
                        "-fx-text-fill: " +
                        Theme.TEXT + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 13px;" +
                        "-fx-cursor: hand;"
                );
            }
        });

        return button;
    }

    private ScrollPane createContent() {

        VBox content =
                new VBox(20);

        content.setPadding(
                new Insets(30, 35, 35, 35)
        );

        VBox header =
                new VBox(5);

        Label title =
                new Label("Service Requests");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Review and manage roadside assistance requests."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        HBox stats =
                new HBox(18);

        stats.getChildren().addAll(
                createStatCard(
                        "Total Requests",
                        "05",
                        Theme.INFO
                ),
                createStatCard(
                        "Emergency",
                        "02",
                        Theme.ERROR
                ),
                createStatCard(
                        "Normal",
                        "03",
                        Theme.SUCCESS
                )
        );

        HBox filters =
                createFilters();

        VBox requestsCard =
                createRequestsCard();

        content.getChildren().addAll(
                header,
                stats,
                filters,
                requestsCard
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        return scrollPane;
    }

    private VBox createStatCard(
            String title,
            String value,
            String accent
    ) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(18)
        );

        card.setPrefHeight(110);

        card.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        HBox row =
                new HBox();

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Circle circle =
                new Circle(18);

        circle.setFill(
                Color.web(accent)
        );

        row.getChildren().addAll(
                titleLabel,
                spacer,
                circle
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        card.getChildren().addAll(
                row,
                valueLabel
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        return card;
    }

    private HBox createFilters() {

        HBox filters =
                new HBox(12);

        filters.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField =
                new TextField();

        searchField.setPromptText(
                "Search customer, vehicle or problem..."
        );

        searchField.setPrefWidth(430);

        searchField.setPadding(
                new Insets(12)
        );

        searchField.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        filterRequests(newValue)
        );

        Button allButton =
                createFilterButton(
                        "All",
                        true
                );

        Button emergencyButton =
                createFilterButton(
                        "Emergency",
                        false
                );

        Button normalButton =
                createFilterButton(
                        "Normal",
                        false
                );

        allButton.setOnAction(
                e -> filterRequests("")
        );

        emergencyButton.setOnAction(
                e -> filterByType("Emergency")
        );

        normalButton.setOnAction(
                e -> filterByType("Normal")
        );

        filters.getChildren().addAll(
                searchField,
                allButton,
                emergencyButton,
                normalButton
        );

        return filters;
    }

    private Button createFilterButton(
            String text,
            boolean active
    ) {

        Button button =
                new Button(text);

        button.setPadding(
                new Insets(11, 18, 11, 18)
        );

        if (active) {

            button.setStyle(
                    "-fx-background-color: " +
                    Theme.TEXT + ";" +
                    "-fx-text-fill: " +
                    Theme.WHITE + ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: " +
                    Theme.CARD + ";" +
                    "-fx-text-fill: " +
                    Theme.TEXT + ";" +
                    "-fx-border-color: " +
                    Theme.BORDER + ";" +
                    "-fx-border-radius: 9;" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 11px;" +
                    "-fx-cursor: hand;"
            );
        }

        return button;
    }

    private VBox createRequestsCard() {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(22)
        );

        card.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label("Incoming Service Requests");

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label live =
                new Label("● LIVE");

        live.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SUCCESS + ";"
        );

        heading.getChildren().addAll(
                title,
                spacer,
                live
        );

        requestsContainer =
                new VBox(10);

        requestsContainer.getChildren().addAll(
                createRequest(
                        "Amit Sharma",
                        "Maruti Swift",
                        "Battery Issue",
                        "Emergency",
                        "2.4 km",
                        "₹450 - ₹700",
                        "5 min ago"
                ),
                createRequest(
                        "Priya Joshi",
                        "Hyundai i20",
                        "Flat Tyre",
                        "Normal",
                        "4.1 km",
                        "₹300 - ₹500",
                        "12 min ago"
                ),
                createRequest(
                        "Rahul Deshmukh",
                        "Honda City",
                        "Engine Problem",
                        "Emergency",
                        "6.8 km",
                        "₹800 - ₹1,500",
                        "18 min ago"
                ),
                createRequest(
                        "Sneha Kulkarni",
                        "Tata Nexon",
                        "Car Not Starting",
                        "Normal",
                        "3.6 km",
                        "₹500 - ₹900",
                        "25 min ago"
                )
        );

        card.getChildren().addAll(
                heading,
                requestsContainer
        );

        return card;
    }

    private HBox createRequest(
            String customer,
            String vehicle,
            String problem,
            String type,
            String distance,
            String amount,
            String time
    ) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(14)
        );

        row.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE + ";" +
                "-fx-background-radius: 12;"
        );

        Circle avatar =
                new Circle(29);

        if (type.equals("Emergency")) {
            avatar.setFill(
                    Color.web("#FEE2E2")
            );
        } else {
            avatar.setFill(
                    Color.web("#FFF7ED")
            );
        }

        VBox customerBox =
                new VBox(5);

        HBox nameRow =
                new HBox(8);

        Label customerLabel =
                new Label(customer);

        customerLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label typeLabel =
                new Label(type);

        if (type.equals("Emergency")) {

            typeLabel.setStyle(
                    "-fx-background-color: " +
                    Theme.ERROR_BG + ";" +
                    "-fx-text-fill: " +
                    Theme.ERROR + ";" +
                    "-fx-background-radius: 10;" +
                    "-fx-padding: 4 8 4 8;" +
                    "-fx-font-size: 8px;" +
                    "-fx-font-weight: bold;"
            );

        } else {

            typeLabel.setStyle(
                    "-fx-background-color: " +
                    Theme.SUCCESS_BG + ";" +
                    "-fx-text-fill: " +
                    Theme.SUCCESS + ";" +
                    "-fx-background-radius: 10;" +
                    "-fx-padding: 4 8 4 8;" +
                    "-fx-font-size: 8px;" +
                    "-fx-font-weight: bold;"
            );
        }

        nameRow.getChildren().addAll(
                customerLabel,
                typeLabel
        );

        Label problemLabel =
                new Label(
                        vehicle + "  •  " + problem
                );

        problemLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        customerBox.getChildren().addAll(
                nameRow,
                problemLabel
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
                Theme.TEXT + ";"
        );

        Label timeLabel =
                new Label(time);

        timeLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        distanceBox.getChildren().addAll(
                distanceLabel,
                timeLabel
        );

        Label amountLabel =
                new Label(amount);

        amountLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        VBox amountBox =
                new VBox(2);

        amountBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label estimated =
                new Label("Estimated");

        estimated.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        amountBox.getChildren().addAll(
                amountLabel,
                estimated
        );

        Button viewButton =
                new Button("View");

        viewButton.setPadding(
                new Insets(9, 14, 9, 14)
        );

        viewButton.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-text-fill: " +
                Theme.TEXT + ";" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        Button acceptButton =
                new Button("Accept");

        acceptButton.setPadding(
                new Insets(9, 16, 9, 16)
        );

        acceptButton.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY + ";" +
                "-fx-text-fill: " +
                Theme.WHITE + ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        acceptButton.setOnAction(
                e -> acceptRequest(
                        customer
                )
        );

        row.getChildren().addAll(
                avatar,
                customerBox,
                spacer,
                distanceBox,
                amountBox,
                viewButton,
                acceptButton
        );

        return row;
    }

    private void filterRequests(
            String search
    ) {

        if (requestsContainer == null) {
            return;
        }

        for (javafx.scene.Node node :
                requestsContainer.getChildren()) {

            if (node instanceof HBox) {

                HBox row =
                        (HBox) node;

                row.setVisible(true);
                row.setManaged(true);
            }
        }
    }

    private void filterByType(
            String type
    ) {

        if (requestsContainer == null) {
            return;
        }

        for (javafx.scene.Node node :
                requestsContainer.getChildren()) {

            if (node instanceof HBox) {

                HBox row =
                        (HBox) node;

                row.setVisible(true);
                row.setManaged(true);
            }
        }
    }

    private void acceptRequest(
            String customer
    ) {

        Label message =
                new Label(
                        "Request from " +
                        customer +
                        " accepted."
                );

        message.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SUCCESS + ";"
        );

        VBox box =
                new VBox(12);

        box.setPadding(
                new Insets(35)
        );

        box.getChildren().add(
                message
        );

        root.setCenter(box);
    }

    private VBox createPlaceholder(
            String pageName
    ) {

        VBox page =
                new VBox(15);

        page.setPadding(
                new Insets(35)
        );

        page.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND + ";"
        );

        Label title =
                new Label(pageName);

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label message =
                new Label(
                        pageName +
                        " UI will be implemented as a separate page."
                );

        message.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        page.getChildren().addAll(
                title,
                message
        );

        return page;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
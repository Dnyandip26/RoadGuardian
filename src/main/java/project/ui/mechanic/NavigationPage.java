package project.ui.mechanic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import project.util.Theme;

public class NavigationPage {

    private boolean navigationStarted = false;

    private Label navigationStatus;
    private Button navigationButton;
    private Button arrivedButton;

    // Get page content

    public ScrollPane getContent() {

        return createNavigationContent();
    }

    // Main navigation content

    private ScrollPane createNavigationContent() {

        VBox mainContent =
                new VBox(20);

        mainContent.setPadding(
                new Insets(
                        30,
                        35,
                        35,
                        35
                )
        );

        mainContent.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND +
                ";"
        );

        VBox header =
                createHeader();

        HBox infoCards =
                new HBox(15);

        infoCards.getChildren().addAll(

                createInfoCard(
                        "DISTANCE",
                        "4.8 km",
                        "To customer",
                        Theme.INFO
                ),

                createInfoCard(
                        "EST. ARRIVAL",
                        "12 min",
                        "Current traffic",
                        Theme.PRIMARY
                ),

                createInfoCard(
                        "CUSTOMER",
                        "Vikram Kulkarni",
                        "Waiting for assistance",
                        Theme.SUCCESS
                )
        );

        HBox navigationArea =
                new HBox(20);

        VBox mapContainer =
                createMapArea();

        VBox customerPanel =
                createCustomerPanel();

        HBox.setHgrow(
                mapContainer,
                Priority.ALWAYS
        );

        navigationArea.getChildren().addAll(
                mapContainer,
                customerPanel
        );

        mainContent.getChildren().addAll(
                header,
                infoCards,
                navigationArea
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        mainContent
                );

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

    // Header

    private VBox createHeader() {

        VBox header =
                new VBox(5);

        Label title =
                new Label(
                        "Navigation"
                );

        title.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label subtitle =
                new Label(
                        "Navigate to the customer and manage your arrival."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        return header;
    }

    // Information cards

    private VBox createInfoCard(
            String title,
            String value,
            String subtitle,
            String accent
    ) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(17)
        );

        card.setPrefHeight(
                100
        );

        card.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 12;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                accent +
                ";"
        );

        Label subtitleLabel =
                new Label(subtitle);

        subtitleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        card.getChildren().addAll(
                titleLabel,
                valueLabel,
                subtitleLabel
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        return card;
    }

    // Map area

    private VBox createMapArea() {

        VBox mapContainer =
                new VBox();

        mapContainer.setPrefHeight(
                430
        );

        mapContainer.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );

        StackPane map =
                new StackPane();

        map.setPrefHeight(
                430
        );

        map.setStyle(
                "-fx-background-color: #DCEBED;" +
                "-fx-background-radius: 14;"
        );

        createMapRoads(map);

        Circle mechanicMarker =
                new Circle(15);

        mechanicMarker.setFill(
                Color.web(
                        Theme.INFO
                )
        );

        mechanicMarker.setStroke(
                Color.WHITE
        );

        mechanicMarker.setStrokeWidth(
                4
        );

        StackPane.setAlignment(
                mechanicMarker,
                Pos.BOTTOM_LEFT
        );

        StackPane.setMargin(
                mechanicMarker,
                new Insets(
                        0,
                        0,
                        105,
                        95
                )
        );

        Circle customerMarker =
                new Circle(15);

        customerMarker.setFill(
                Color.web(
                        Theme.ERROR
                )
        );

        customerMarker.setStroke(
                Color.WHITE
        );

        customerMarker.setStrokeWidth(
                4
        );

        StackPane.setAlignment(
                customerMarker,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                customerMarker,
                new Insets(
                        95,
                        110,
                        0,
                        0
                )
        );

        VBox mechanicLabel =
                createMapLabel(
                        "You",
                        Theme.INFO
                );

        StackPane.setAlignment(
                mechanicLabel,
                Pos.BOTTOM_LEFT
        );

        StackPane.setMargin(
                mechanicLabel,
                new Insets(
                        0,
                        0,
                        65,
                        62
                )
        );

        VBox customerLabel =
                createMapLabel(
                        "Customer",
                        Theme.ERROR
                );

        StackPane.setAlignment(
                customerLabel,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                customerLabel,
                new Insets(
                        55,
                        72,
                        0,
                        0
                )
        );

        Label routeLabel =
                new Label(
                        "4.8 km route"
                );

        routeLabel.setPadding(
                new Insets(
                        8,
                        13,
                        8,
                        13
                )
        );

        routeLabel.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 20;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        StackPane.setAlignment(
                routeLabel,
                Pos.TOP_LEFT
        );

        StackPane.setMargin(
                routeLabel,
                new Insets(18)
        );

        map.getChildren().addAll(
                mechanicMarker,
                customerMarker,
                mechanicLabel,
                customerLabel,
                routeLabel
        );

        mapContainer.getChildren().add(
                map
        );

        return mapContainer;
    }

    private void createMapRoads(
            StackPane map
    ) {

        for (int i = 0; i < 5; i++) {

            Rectangle road =
                    new Rectangle(
                            650,
                            7
                    );

            road.setFill(
                    Color.web(
                            "#BFD5D9"
                    )
            );

            road.setRotate(
                    15 + (i * 18)
            );

            map.getChildren().add(
                    road
            );
        }

        for (int i = 0; i < 4; i++) {

            Rectangle road =
                    new Rectangle(
                            500,
                            5
                    );

            road.setFill(
                    Color.web(
                            "#C9DEE1"
                    )
            );

            road.setRotate(
                    -20 + (i * 16)
            );

            map.getChildren().add(
                    road
            );
        }

        Line route =
                new Line(
                        -170,
                        125,
                        190,
                        -105
                );

        route.setStroke(
                Color.web(
                        Theme.INFO
                )
        );

        route.setStrokeWidth(
                7
        );

        route.getStrokeDashArray().addAll(
                15.0,
                10.0
        );

        map.getChildren().add(
                route
        );
    }

    private VBox createMapLabel(
            String text,
            String color
    ) {

        VBox box =
                new VBox(3);

        box.setAlignment(
                Pos.CENTER
        );

        Label marker =
                new Label(text);

        marker.setPadding(
                new Insets(
                        5,
                        9,
                        5,
                        9
                )
        );

        marker.setStyle(
                "-fx-background-color: " +
                color +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;"
        );

        box.getChildren().add(
                marker
        );

        return box;
    }

    // Customer panel

    private VBox createCustomerPanel() {

        VBox panel =
                new VBox(16);

        panel.setPrefWidth(
                310
        );

        panel.setPadding(
                new Insets(22)
        );

        panel.setStyle(
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
                        "Customer Details"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        HBox customerRow =
                new HBox(12);

        customerRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle avatar =
                new Circle(25);

        avatar.setFill(
                Color.web(
                        "#FFF7ED"
                )
        );

        VBox customerInfo =
                new VBox(4);

        Label customerName =
                new Label(
                        "Vikram Kulkarni"
                );

        customerName.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label phone =
                new Label(
                        "+91 98765 43210"
                );

        phone.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        customerInfo.getChildren().addAll(
                customerName,
                phone
        );

        customerRow.getChildren().addAll(
                avatar,
                customerInfo
        );

        VBox vehicleBox =
                createInformationBox(
                        "VEHICLE",
                        "Toyota Innova"
                );

        VBox problemBox =
                createInformationBox(
                        "PROBLEM",
                        "Engine Issue"
                );

        VBox locationBox =
                createInformationBox(
                        "DESTINATION",
                        "Kothrud, Pune"
                );

        navigationStatus =
                new Label(
                        "Ready to start navigation"
                );

        navigationStatus.setStyle(
                "-fx-background-color: " +
                Theme.INFO_BG +
                ";" +
                "-fx-text-fill: " +
                Theme.INFO +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10 12 10 12;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        navigationButton =
                new Button(
                        "Start Navigation"
                );

        navigationButton.setMaxWidth(
                Double.MAX_VALUE
        );

        navigationButton.setPadding(
                new Insets(12)
        );

        navigationButton.setStyle(
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

        arrivedButton =
                new Button(
                        "Mark as Arrived"
                );

        arrivedButton.setMaxWidth(
                Double.MAX_VALUE
        );

        arrivedButton.setPadding(
                new Insets(12)
        );

        arrivedButton.setDisable(
                true
        );

        arrivedButton.setStyle(
                "-fx-background-color: " +
                Theme.BORDER +
                ";" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );

        navigationButton.setOnAction(
                e -> startNavigation()
        );

        arrivedButton.setOnAction(
                e -> markArrived()
        );

        Button callButton =
                new Button(
                        "☎  Call Customer"
                );

        callButton.setMaxWidth(
                Double.MAX_VALUE
        );

        callButton.setPadding(
                new Insets(10)
        );

        callButton.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        panel.getChildren().addAll(
                title,
                customerRow,
                vehicleBox,
                problemBox,
                locationBox,
                navigationStatus,
                navigationButton,
                arrivedButton,
                callButton
        );

        return panel;
    }

    private VBox createInformationBox(
            String title,
            String value
    ) {

        VBox box =
                new VBox(4);

        box.setPadding(
                new Insets(10)
        );

        box.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-background-radius: 8;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";" +
                "-fx-font-weight: bold;"
        );

        box.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return box;
    }

    // Navigation actions

    private void startNavigation() {

        navigationStarted = true;

        navigationStatus.setText(
                "Navigation started • 12 min remaining"
        );

        navigationStatus.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS_BG +
                ";" +
                "-fx-text-fill: " +
                Theme.SUCCESS +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10 12 10 12;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        navigationButton.setText(
                "Navigation In Progress"
        );

        navigationButton.setDisable(
                true
        );

        navigationButton.setStyle(
                "-fx-background-color: " +
                Theme.INFO +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );

        arrivedButton.setDisable(
                false
        );

        arrivedButton.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
    }

    private void markArrived() {

        if (!navigationStarted) {
            return;
        }

        navigationStatus.setText(
                "You have arrived at the customer location"
        );

        navigationStatus.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS_BG +
                ";" +
                "-fx-text-fill: " +
                Theme.SUCCESS +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10 12 10 12;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        arrivedButton.setText(
                "✓ Arrived"
        );

        arrivedButton.setDisable(
                true
        );
    }
}
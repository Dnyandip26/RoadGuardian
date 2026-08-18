package project.ui.user.Assistance;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.ui.user.DashBoard.UserSectionPage;

/**
 * RoadGuardian - Tow Truck Assistance
 *
 * This page is designed to be loaded inside UserDashboard.
 *
 * The common UserDashboard Header and Sidebar are intentionally
 * not created here.
 */
public class TowTruckPage extends UserSectionPage {

    private static final String BG = "#DDF5F7";
    private static final String WHITE = "#FFFFFF";
    private static final String CARD = "#F5FBFC";
    private static final String BLUE = "#2864E8";
    private static final String DARK = "#142033";
    private static final String GREY = "#667085";
    private static final String BORDER = "#C8E0E5";
    private static final String ORANGE = "#FF7A18";

    public TowTruckPage() {
        // UI is created when getView() is called.
    }

    @Override
    public VBox getView() {

        VBox content =
                createPageContent();

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";" +
                        "-fx-background: " +
                        BG +
                        ";" +
                        "-fx-border-color: transparent;"
        );

        VBox root =
                new VBox();

        root.setFillWidth(true);

        root.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        root.getChildren().add(
                scrollPane
        );

        return root;
    }

    // ============================================================
    // PAGE CONTENT
    // ============================================================

    private VBox createPageContent() {

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(
                        28,
                        30,
                        35,
                        30
                )
        );

        content.setFillWidth(true);

        content.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";"
        );

        Label title =
                new Label(
                        "Tow Truck Assistance"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );

        title.setTextFill(
                Color.web(DARK)
        );

        Label subtitle =
                new Label(
                        "Flatbed and lift recovery available around you right now"
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        subtitle.setTextFill(
                Color.web(GREY)
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        HBox main =
                new HBox(20);

        main.setAlignment(
                Pos.TOP_LEFT
        );

        VBox leftColumn =
                new VBox(20);

        HBox.setHgrow(
                leftColumn,
                Priority.ALWAYS
        );

        VBox mapBox =
                createMapBox();

        VBox destinationBox =
                createDestinationBox();

        leftColumn.getChildren().addAll(
                mapBox,
                destinationBox
        );

        VBox truckCards =
                new VBox(16);

        truckCards.setPrefWidth(
                510
        );

        truckCards.setMinWidth(
                450
        );

        truckCards.getChildren().addAll(

                createTruckCard(
                        "FlatBed 4.5T",
                        "Metro Tow Services",
                        "9 min",
                        "2 km",
                        "₹1450",
                        "4.8",
                        "Sedan / Hatchback"
                ),

                createTruckCard(
                        "Hydraulic Lift 7T",
                        "RapidLift Recovery",
                        "15 min",
                        "3.8 km",
                        "₹2100",
                        "4.6",
                        "SUV / MUV"
                ),

                createTruckCard(
                        "Wheel Lift 3T",
                        "CityGuard Towing",
                        "22 min",
                        "6.2 km",
                        "₹1750",
                        "4.4",
                        "Sedan / Hatchback"
                ),

                createTruckCard(
                        "Heavy Recovery 10T",
                        "Highway Rescue",
                        "28 min",
                        "8.5 km",
                        "₹2800",
                        "4.3",
                        "SUV / Truck"
                )
        );

        main.getChildren().addAll(
                leftColumn,
                truckCards
        );

        content.getChildren().addAll(
                heading,
                main
        );

        return content;
    }

    // ============================================================
    // MAP
    // ============================================================

    private VBox createMapBox() {

        VBox box =
                new VBox();

        box.setPrefHeight(
                420
        );

        box.setMinHeight(
                380
        );

        box.setPadding(
                new Insets(0)
        );

        StackPane map =
                new StackPane();

        VBox.setVgrow(
                map,
                Priority.ALWAYS
        );

        map.setStyle(
                "-fx-background-color: #E8E8E6;" +
                        "-fx-background-radius: 25;" +
                        "-fx-border-color: #D0D9DB;" +
                        "-fx-border-radius: 25;" +
                        "-fx-border-width: 1;"
        );

        Line road1 =
                new Line(
                        -100,
                        300,
                        850,
                        60
                );

        road1.setStroke(
                Color.WHITE
        );

        road1.setStrokeWidth(11);

        Line road2 =
                new Line(
                        40,
                        40,
                        800,
                        360
                );

        road2.setStroke(
                Color.WHITE
        );

        road2.setStrokeWidth(10);

        Line road3 =
                new Line(
                        330,
                        -30,
                        440,
                        430
                );

        road3.setStroke(
                Color.WHITE
        );

        road3.setStrokeWidth(8);

        Line blueRoad =
                new Line(
                        250,
                        370,
                        760,
                        310
                );

        blueRoad.setStroke(
                Color.web("#69B7E8")
        );

        blueRoad.setStrokeWidth(5);

        Line route =
                new Line(
                        350,
                        205,
                        520,
                        120
                );

        route.setStroke(
                Color.web(BLUE)
        );

        route.setStrokeWidth(3);

        route.getStrokeDashArray().addAll(
                8.0,
                6.0
        );

        StackPane roads =
                new StackPane(
                        road1,
                        road2,
                        road3,
                        blueRoad,
                        route
                );

        Circle vehicleCircle =
                new Circle(
                        22,
                        Color.web(BLUE)
                );

        Label vehicleIcon =
                new Label("▰");

        vehicleIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        vehicleIcon.setTextFill(
                Color.WHITE
        );

        StackPane vehicleMarker =
                new StackPane(
                        vehicleCircle,
                        vehicleIcon
                );

        Label vehicleText =
                new Label(
                        "Your vehicle"
                );

        vehicleText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        vehicleText.setTextFill(
                Color.web(DARK)
        );

        vehicleText.setPadding(
                new Insets(
                        7,
                        12,
                        7,
                        12
                )
        );

        vehicleText.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15;"
        );

        VBox vehicle =
                new VBox(
                        4,
                        vehicleMarker,
                        vehicleText
                );

        vehicle.setAlignment(
                Pos.CENTER
        );

        StackPane.setAlignment(
                vehicle,
                Pos.CENTER
        );

        StackPane.setMargin(
                vehicle,
                new Insets(
                        70,
                        0,
                        0,
                        0
                )
        );

        StackPane truck1 =
                truckMarker();

        StackPane truck2 =
                truckMarker();

        StackPane truck3 =
                truckMarker();

        StackPane.setAlignment(
                truck1,
                Pos.TOP_CENTER
        );

        StackPane.setMargin(
                truck1,
                new Insets(
                        55,
                        0,
                        0,
                        0
                )
        );

        StackPane.setAlignment(
                truck2,
                Pos.CENTER_LEFT
        );

        StackPane.setMargin(
                truck2,
                new Insets(
                        0,
                        155,
                        0,
                        0
                )
        );

        StackPane.setAlignment(
                truck3,
                Pos.CENTER_RIGHT
        );

        StackPane.setMargin(
                truck3,
                new Insets(
                        0,
                        205,
                        0,
                        0
                )
        );

        Label time1 =
                mapLabel("9 min");

        Label time2 =
                mapLabel("22 min");

        Label time3 =
                mapLabel("15 min");

        StackPane.setAlignment(
                time1,
                Pos.TOP_CENTER
        );

        StackPane.setMargin(
                time1,
                new Insets(
                        105,
                        0,
                        0,
                        0
                )
        );

        StackPane.setAlignment(
                time2,
                Pos.CENTER_LEFT
        );

        StackPane.setMargin(
                time2,
                new Insets(
                        80,
                        0,
                        0,
                        140
                )
        );

        StackPane.setAlignment(
                time3,
                Pos.CENTER_RIGHT
        );

        StackPane.setMargin(
                time3,
                new Insets(
                        80,
                        165,
                        0,
                        0
                )
        );

        map.getChildren().addAll(
                roads,
                vehicle,
                truck1,
                truck2,
                truck3,
                time1,
                time2,
                time3
        );

        box.getChildren().add(
                map
        );

        return box;
    }

    // ============================================================
    // TRUCK MARKER
    // ============================================================

    private StackPane truckMarker() {

        Circle outer =
                new Circle(27);

        outer.setFill(
                Color.TRANSPARENT
        );

        outer.setStroke(
                Color.WHITE
        );

        outer.setStrokeWidth(4);

        Circle inner =
                new Circle(
                        22,
                        Color.web(ORANGE)
                );

        Label icon =
                new Label("▱");

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        icon.setTextFill(
                Color.WHITE
        );

        return new StackPane(
                outer,
                inner,
                icon
        );
    }

    // ============================================================
    // MAP LABEL
    // ============================================================

    private Label mapLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        label.setTextFill(
                Color.web(DARK)
        );

        label.setPadding(
                new Insets(
                        7,
                        13,
                        7,
                        13
                )
        );

        label.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15;"
        );

        return label;
    }

    // ============================================================
    // DESTINATION
    // ============================================================

    private VBox createDestinationBox() {

        VBox outer =
                new VBox(14);

        outer.setPadding(
                new Insets(20)
        );

        outer.setPrefHeight(
                155
        );

        outer.setStyle(
                "-fx-background-color: " +
                        CARD +
                        ";" +
                        "-fx-background-radius: 22;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 22;" +
                        "-fx-border-width: 1;"
        );

        Label title =
                new Label(
                        "Drop-off destination"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        title.setTextFill(
                Color.web(DARK)
        );

        HBox destination =
                new HBox(12);

        destination.setAlignment(
                Pos.CENTER_LEFT
        );

        destination.setPadding(
                new Insets(14)
        );

        destination.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 22;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 22;"
        );

        Label location =
                new Label("⌖");

        location.setFont(
                Font.font(
                        "Arial",
                        24
                )
        );

        location.setTextFill(
                Color.web(BLUE)
        );

        Label place =
                new Label(
                        "SpeedFix Auto Care, Baner"
                );

        place.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        place.setTextFill(
                Color.web(DARK)
        );

        Label distance =
                new Label(
                        "7.4 km from your location · open until 11 PM"
                );

        distance.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        distance.setTextFill(
                Color.web(GREY)
        );

        VBox destinationText =
                new VBox(
                        3,
                        place,
                        distance
                );

        destination.getChildren().addAll(
                location,
                destinationText
        );

        outer.getChildren().addAll(
                title,
                destination
        );

        return outer;
    }

    // ============================================================
    // TRUCK CARD
    // ============================================================

    private VBox createTruckCard(
            String name,
            String company,
            String eta,
            String distance,
            String charge,
            String rating,
            String suitable
    ) {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefHeight(
                235
        );

        card.setStyle(
                "-fx-background-color: " +
                        CARD +
                        ";" +
                        "-fx-background-radius: 22;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 22;" +
                        "-fx-border-width: 1;"
        );

        HBox top =
                new HBox(12);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle iconCircle =
                new Circle(
                        22,
                        Color.web("#FFF0E7")
                );

        Label truckIcon =
                new Label("▱");

        truckIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        truckIcon.setTextFill(
                Color.web(ORANGE)
        );

        StackPane icon =
                new StackPane(
                        iconCircle,
                        truckIcon
                );

        Label nameLabel =
                new Label(name);

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        nameLabel.setTextFill(
                Color.web(DARK)
        );

        Label companyLabel =
                new Label(company);

        companyLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        companyLabel.setTextFill(
                Color.web(GREY)
        );

        VBox nameBox =
                new VBox(
                        3,
                        nameLabel,
                        companyLabel
                );

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Label star =
                new Label(
                        "★ " + rating
                );

        star.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        star.setTextFill(
                Color.web(ORANGE)
        );

        top.getChildren().addAll(
                icon,
                nameBox,
                space,
                star
        );

        HBox stats =
                new HBox(10);

        stats.getChildren().addAll(
                statBox(
                        "◷",
                        eta,
                        "ETA"
                ),
                statBox(
                        "⌖",
                        distance,
                        "Distance"
                ),
                statBox(
                        "₹",
                        charge,
                        "Charge"
                )
        );

        Label suitableLabel =
                new Label(
                        "Suitable for: " +
                                suitable
                );

        suitableLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        suitableLabel.setTextFill(
                Color.web(GREY)
        );

        Button book =
                new Button(
                        "Book now"
                );

        book.setMaxWidth(
                Double.MAX_VALUE
        );

        book.setPrefHeight(
                40
        );

        book.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        book.setTextFill(
                Color.WHITE
        );

        book.setStyle(
                "-fx-background-color: " +
                        BLUE +
                        ";" +
                        "-fx-background-radius: 22;" +
                        "-fx-cursor: hand;"
        );

        book.setOnAction(
                event ->
                        bookTruck(
                                book,
                                name
                        )
        );

        card.getChildren().addAll(
                top,
                stats,
                suitableLabel,
                book
        );

        return card;
    }

    // ============================================================
    // BOOK TRUCK
    // ============================================================

    private void bookTruck(
            Button button,
            String truckName
    ) {

        button.setText(
                "Booked"
        );

        button.setDisable(
                true
        );

        System.out.println(
                "Tow truck booked: " +
                        truckName
        );
    }

    // ============================================================
    // STAT BOX
    // ============================================================

    private VBox statBox(
            String icon,
            String value,
            String title
    ) {

        VBox box =
                new VBox(3);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPrefWidth(
                140
        );

        box.setPrefHeight(
                78
        );

        box.setStyle(
                "-fx-background-color: #F0F5F8;" +
                        "-fx-background-radius: 22;"
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
                Color.web(GREY)
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        valueLabel.setTextFill(
                Color.web(DARK)
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        titleLabel.setTextFill(
                Color.web(GREY)
        );

        box.getChildren().addAll(
                iconLabel,
                valueLabel,
                titleLabel
        );

        return box;
    }
}
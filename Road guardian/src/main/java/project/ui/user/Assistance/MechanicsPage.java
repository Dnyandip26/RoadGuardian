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
 * RoadGuardian - Smart Mechanic Dispatch
 *
 * This page is designed to be loaded inside UserDashboard.
 *
 * The common UserDashboard Header and Sidebar are intentionally
 * not created here.
 */
public class MechanicsPage extends UserSectionPage {

    // ============================================================
    // COLORS
    // ============================================================

    private static final String BG = "#DDF5F7";
    private static final String WHITE = "#FFFFFF";
    private static final String CARD = "#F1FAFB";
    private static final String BLUE = "#2864E8";
    private static final String DARK = "#142033";
    private static final String GREY = "#667085";
    private static final String BORDER = "#C9E2E6";
    private static final String GREEN = "#12B76A";
    private static final String ORANGE = "#FF7A18";

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public MechanicsPage() {
        // UI is created when getView() is called.
    }

    // ============================================================
    // GET VIEW
    // ============================================================

    @Override
    public VBox getView() {

        VBox content =
                createPageContent();

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

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
                        "Smart Mechanic Dispatch"
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
                        "4 verified mechanics within 5 km of NH-48, Exit 12B"
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

        HBox mainArea =
                new HBox(20);

        mainArea.setAlignment(
                Pos.TOP_LEFT
        );

        mainArea.setFillHeight(true);

        VBox mapBox =
                createMapBox();

        HBox.setHgrow(
                mapBox,
                Priority.ALWAYS
        );

        VBox mechanicsList =
                new VBox(16);

        mechanicsList.setPrefWidth(
                520
        );

        mechanicsList.setMinWidth(
                470
        );

        mechanicsList.getChildren().addAll(
                createMechanicCard(
                        "Arjun Mehta",
                        "SpeedFix Auto Care",
                        "0.2 km",
                        "6 min ETA",
                        "1,284 jobs",
                        "Petrol",
                        "Hybrid",
                        "Battery",
                        "4.9",
                        true
                ),

                createMechanicCard(
                        "Kabir Sharma",
                        "Highway Motors",
                        "2.4 km",
                        "11 min ETA",
                        "903 jobs",
                        "Diesel",
                        "SUV",
                        "",
                        "4.7",
                        false
                ),

                createMechanicCard(
                        "Neha Rao",
                        "ElectroDrive Garage",
                        "3.1 km",
                        "14 min ETA",
                        "654 jobs",
                        "EV",
                        "Electrical",
                        "",
                        "4.8",
                        false
                ),

                createMechanicCard(
                        "Imran Khan",
                        "RoadCare Service",
                        "4.2 km",
                        "19 min ETA",
                        "521 jobs",
                        "Petrol",
                        "Battery",
                        "",
                        "4.6",
                        false
                )
        );

        mainArea.getChildren().addAll(
                mapBox,
                mechanicsList
        );

        content.getChildren().addAll(
                heading,
                mainArea
        );

        return content;
    }

    // ============================================================
    // MAP BOX
    // ============================================================

    private VBox createMapBox() {

        VBox outer =
                new VBox();

        outer.setPrefWidth(
                760
        );

        outer.setMinWidth(
                600
        );

        outer.setPrefHeight(
                465
        );

        outer.setPadding(
                new Insets(10)
        );

        outer.setStyle(
                "-fx-background-color: #E8F5F6;" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 24;" +
                        "-fx-border-width: 1.5;"
        );

        StackPane map =
                new StackPane();

        VBox.setVgrow(
                map,
                Priority.ALWAYS
        );

        map.setStyle(
                "-fx-background-color: #E7E7E5;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-color: #D1D8DA;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-width: 1;"
        );

        Line road1 =
                new Line(
                        20,
                        350,
                        720,
                        90
                );

        road1.setStroke(
                Color.WHITE
        );

        road1.setStrokeWidth(
                12
        );

        Line road2 =
                new Line(
                        80,
                        70,
                        680,
                        390
                );

        road2.setStroke(
                Color.WHITE
        );

        road2.setStrokeWidth(
                10
        );

        Line road3 =
                new Line(
                        300,
                        10,
                        430,
                        450
                );

        road3.setStroke(
                Color.WHITE
        );

        road3.setStrokeWidth(
                8
        );

        Line route =
                new Line(
                        300,
                        260,
                        470,
                        190
                );

        route.setStroke(
                Color.web(BLUE)
        );

        route.setStrokeWidth(
                3
        );

        route.getStrokeDashArray().addAll(
                8.0,
                6.0
        );

        StackPane roads =
                new StackPane(
                        road1,
                        road2,
                        road3,
                        route
                );

        Circle youCircle =
                new Circle(
                        22,
                        Color.web(BLUE)
                );

        Label youIcon =
                new Label(
                        "▰"
                );

        youIcon.setTextFill(
                Color.WHITE
        );

        youIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        StackPane youMarker =
                new StackPane(
                        youCircle,
                        youIcon
                );

        Label youLabel =
                markerLabel(
                        "You"
                );

        VBox youBox =
                new VBox(
                        4,
                        youMarker,
                        youLabel
                );

        youBox.setAlignment(
                Pos.CENTER
        );

        StackPane.setAlignment(
                youBox,
                Pos.CENTER
        );

        StackPane.setMargin(
                youBox,
                new Insets(
                        90,
                        0,
                        0,
                        0
                )
        );

        StackPane arjun =
                mechanicMarker("⚒");

        StackPane kabir =
                mechanicMarker("⚒");

        StackPane neha =
                mechanicMarker("⚒");

        StackPane imran =
                mechanicMarker("⚒");

        StackPane.setAlignment(
                arjun,
                Pos.TOP_LEFT
        );

        StackPane.setMargin(
                arjun,
                new Insets(
                        120,
                        0,
                        0,
                        250
                )
        );

        StackPane.setAlignment(
                kabir,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                kabir,
                new Insets(
                        85,
                        100,
                        0,
                        0
                )
        );

        StackPane.setAlignment(
                neha,
                Pos.BOTTOM_LEFT
        );

        StackPane.setMargin(
                neha,
                new Insets(
                        0,
                        0,
                        95,
                        120
                )
        );

        StackPane.setAlignment(
                imran,
                Pos.BOTTOM_RIGHT
        );

        StackPane.setMargin(
                imran,
                new Insets(
                        0,
                        120,
                        110,
                        0
                )
        );

        Label radius =
                new Label(
                        "Live dispatch radius · 5 km"
                );

        radius.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        radius.setTextFill(
                Color.web(DARK)
        );

        radius.setPadding(
                new Insets(
                        10,
                        16,
                        10,
                        16
                )
        );

        radius.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 22;"
        );

        StackPane.setAlignment(
                radius,
                Pos.TOP_LEFT
        );

        StackPane.setMargin(
                radius,
                new Insets(18)
        );

        Label note =
                new Label(
                        "Real-time map will be connected here later"
                );

        note.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        note.setTextFill(
                Color.web(GREY)
        );

        note.setPadding(
                new Insets(
                        8,
                        13,
                        8,
                        13
                )
        );

        note.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 18;"
        );

        StackPane.setAlignment(
                note,
                Pos.BOTTOM_LEFT
        );

        StackPane.setMargin(
                note,
                new Insets(18)
        );

        map.getChildren().addAll(
                roads,
                youBox,
                arjun,
                kabir,
                neha,
                imran,
                radius,
                note
        );

        outer.getChildren().add(
                map
        );

        return outer;
    }

    // ============================================================
    // MECHANIC MARKER
    // ============================================================

    private StackPane mechanicMarker(
            String icon
    ) {

        Circle circle =
                new Circle(
                        23,
                        Color.web(GREEN)
                );

        Label label =
                new Label(
                        icon
                );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        label.setTextFill(
                Color.WHITE
        );

        StackPane marker =
                new StackPane(
                        circle,
                        label
                );

        Circle ring =
                new Circle(
                        28
                );

        ring.setFill(
                Color.TRANSPARENT
        );

        ring.setStroke(
                Color.WHITE
        );

        ring.setStrokeWidth(
                4
        );

        marker.getChildren().add(
                0,
                ring
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
                new Label(
                        text
                );

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
                        "-fx-background-radius: 16;"
        );

        return label;
    }

    // ============================================================
    // MECHANIC CARD
    // ============================================================

    private VBox createMechanicCard(
            String name,
            String garage,
            String distance,
            String eta,
            String jobs,
            String tag1,
            String tag2,
            String tag3,
            String rating,
            boolean bestMatch
    ) {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefHeight(
                205
        );

        card.setStyle(
                "-fx-background-color: #F8FCFD;" +
                        "-fx-background-radius: 22;" +
                        "-fx-border-color: " +
                        (
                                bestMatch
                                        ? BLUE
                                        : BORDER
                        ) +
                        ";" +
                        "-fx-border-radius: 22;" +
                        "-fx-border-width: " +
                        (
                                bestMatch
                                        ? "2.5"
                                        : "1"
                        ) +
                        ";"
        );

        HBox top =
                new HBox(10);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Label nameLabel =
                new Label(
                        name
                );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        nameLabel.setTextFill(
                Color.web(DARK)
        );

        top.getChildren().add(
                nameLabel
        );

        if (bestMatch) {

            Label best =
                    new Label(
                            "⚡ Best match"
                    );

            best.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            12
                    )
            );

            best.setTextFill(
                    Color.WHITE
            );

            best.setPadding(
                    new Insets(
                            5,
                            10,
                            5,
                            10
                    )
            );

            best.setStyle(
                    "-fx-background-color: " +
                            BLUE +
                            ";" +
                            "-fx-background-radius: 14;"
            );

            top.getChildren().add(
                    best
            );
        }

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
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
                        16
                )
        );

        star.setTextFill(
                Color.web(ORANGE)
        );

        top.getChildren().addAll(
                spacer,
                star
        );

        Label garageLabel =
                new Label(
                        garage
                );

        garageLabel.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        garageLabel.setTextFill(
                Color.web(GREY)
        );

        HBox details =
                new HBox(18);

        details.getChildren().addAll(
                smallInfo(
                        "⌖",
                        distance
                ),
                smallInfo(
                        "◷",
                        eta
                ),
                smallInfo(
                        "",
                        jobs
                )
        );

        HBox tags =
                new HBox(8);

        if (!tag1.isEmpty()) {
            tags.getChildren().add(
                    tag(tag1)
            );
        }

        if (!tag2.isEmpty()) {
            tags.getChildren().add(
                    tag(tag2)
            );
        }

        if (!tag3.isEmpty()) {
            tags.getChildren().add(
                    tag(tag3)
            );
        }

        Region bottomSpacer =
                new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );

        Label available =
                new Label(
                        "• Available now"
                );

        available.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        available.setTextFill(
                Color.web(GREEN)
        );

        Button assign =
                new Button(
                        "Assign mechanic"
                );

        assign.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        assign.setTextFill(
                Color.WHITE
        );

        assign.setPadding(
                new Insets(
                        10,
                        17,
                        10,
                        17
                )
        );

        assign.setStyle(
                "-fx-background-color: " +
                        BLUE +
                        ";" +
                        "-fx-background-radius: 20;" +
                        "-fx-cursor: hand;"
        );

        assign.setOnAction(
                event ->
                        assignMechanic(
                                assign,
                                name
                        )
        );

        HBox bottom =
                new HBox(
                        10,
                        available,
                        bottomSpacer,
                        assign
                );

        bottom.setAlignment(
                Pos.CENTER_LEFT
        );

        card.getChildren().addAll(
                top,
                garageLabel,
                details,
                tags,
                bottom
        );

        return card;
    }

    // ============================================================
    // ASSIGN MECHANIC
    // ============================================================

    private void assignMechanic(
            Button button,
            String mechanicName
    ) {

        button.setText(
                "Assigned"
        );

        button.setDisable(
                true
        );

        System.out.println(
                "Mechanic assigned: " +
                        mechanicName
        );
    }

    // ============================================================
    // SMALL INFO
    // ============================================================

    private HBox smallInfo(
            String icon,
            String value
    ) {

        Label label =
                new Label(
                        icon.isEmpty()
                                ? value
                                : icon + "  " + value
                );

        label.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        label.setTextFill(
                Color.web(GREY)
        );

        HBox box =
                new HBox(
                        label
                );

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        return box;
    }

    // ============================================================
    // TAG
    // ============================================================

    private Label tag(
            String text
    ) {

        Label tag =
                new Label(
                        text
                );

        tag.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        tag.setTextFill(
                Color.web(DARK)
        );

        tag.setPadding(
                new Insets(
                        6,
                        10,
                        6,
                        10
                )
        );

        tag.setStyle(
                "-fx-background-color: #F0F4F7;" +
                        "-fx-background-radius: 12;"
        );

        return tag;
    }
}
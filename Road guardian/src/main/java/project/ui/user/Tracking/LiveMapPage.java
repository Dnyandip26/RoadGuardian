package project.ui.user.Tracking;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.ui.user.DashBoard.UserSectionPage;

/**
 * RoadGuardian - Live Tracking
 *
 * This page is designed to be loaded inside UserDashboard.
 *
 * The common UserDashboard Header and Sidebar are intentionally
 * not created here.
 */
public class LiveMapPage extends UserSectionPage {

    private static final String MAIN_BACKGROUND = "#D6F0F3";
    private static final String CARD_SURFACE = "#EEF9FA";
    private static final String SECONDARY_SURFACE = "#DFF3F5";

    private static final String HEADING = "#172033";
    private static final String SECONDARY_TEXT = "#526274";

    private static final String NAV_BLUE = "#2563EB";
    private static final String SUCCESS = "#16A34A";
    private static final String EMERGENCY = "#DC2626";
    private static final String ORANGE = "#F97316";

    private static final String BORDER = "#B7D5DB";

    public LiveMapPage() {
        // UI is created when getView() is called.
    }

    // ============================================================
    // GET VIEW
    // ============================================================

    @Override
    public VBox getView() {

        VBox content =
                createLiveMapContent();

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

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
                        MAIN_BACKGROUND +
                        ";" +
                        "-fx-background: " +
                        MAIN_BACKGROUND +
                        ";" +
                        "-fx-border-color: transparent;"
        );

        VBox root =
                new VBox();

        root.setFillWidth(true);

        root.setStyle(
                "-fx-background-color: " +
                        MAIN_BACKGROUND +
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
    // CONTENT
    // ============================================================

    private VBox createLiveMapContent() {

        VBox content =
                new VBox(20);

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
                        MAIN_BACKGROUND +
                        ";"
        );

        VBox titleBox =
                new VBox(4);

        Label title =
                new Label(
                        "Live Tracking"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        title.setTextFill(
                Color.web(HEADING)
        );

        Label subtitle =
                new Label(
                        "Arjun Mehta is en route · updated 4 seconds ago"
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        subtitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        HBox mainRow =
                new HBox(20);

        mainRow.setFillHeight(true);
        mainRow.setMinWidth(0);
        mainRow.setMaxWidth(
                Double.MAX_VALUE
        );

        VBox mapCard =
                createMapCard();

        VBox infoColumn =
                new VBox(20);

        infoColumn.setPrefWidth(330);
        infoColumn.setMinWidth(300);
        infoColumn.setMaxWidth(330);

        VBox trackingCard =
                createTrackingInfoCard();

        VBox legendCard =
                createLegendCard();

        infoColumn.getChildren().addAll(
                trackingCard,
                legendCard
        );

        HBox.setHgrow(
                mapCard,
                Priority.ALWAYS
        );

        mainRow.getChildren().addAll(
                mapCard,
                infoColumn
        );

        content.getChildren().addAll(
                titleBox,
                mainRow
        );

        return content;
    }

    // ============================================================
    // MAP CARD
    // ============================================================

    private VBox createMapCard() {

        VBox card =
                new VBox();

        card.setPadding(
                new Insets(10)
        );

        card.setPrefHeight(555);
        card.setMinHeight(500);

        card.setMinWidth(0);
        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: " +
                        CARD_SURFACE +
                        ";" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 24;" +
                        "-fx-border-width: 1;"
        );

        StackPane map =
                createMap();

        VBox.setVgrow(
                map,
                Priority.ALWAYS
        );

        card.getChildren().add(
                map
        );

        return card;
    }

    // ============================================================
    // MAP
    // ============================================================

    private StackPane createMap() {

        StackPane map =
                new StackPane();

        map.setPrefHeight(535);
        map.setMinHeight(470);
        map.setMinWidth(0);
        map.setMaxWidth(
                Double.MAX_VALUE
        );

        map.setStyle(
                "-fx-background-color: #E7E7E4;" +
                        "-fx-background-radius: 22;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 22;" +
                        "-fx-border-width: 1;"
        );

        Rectangle background =
                new Rectangle();

        background.setFill(
                Color.web("#E7E7E4")
        );

        background.setArcWidth(44);
        background.setArcHeight(44);

        background.widthProperty().bind(
                map.widthProperty().subtract(2)
        );

        background.heightProperty().bind(
                map.heightProperty().subtract(2)
        );

        Rectangle clip =
                new Rectangle();

        clip.setArcWidth(44);
        clip.setArcHeight(44);

        clip.widthProperty().bind(
                map.widthProperty().subtract(2)
        );

        clip.heightProperty().bind(
                map.heightProperty().subtract(2)
        );

        map.setClip(clip);

        map.getChildren().add(
                background
        );

        // --------------------------------------------------------
        // ROADS
        // --------------------------------------------------------

        map.getChildren().addAll(
                road(
                        -100,
                        400,
                        900,
                        100,
                        13
                ),

                road(
                        60,
                        80,
                        820,
                        510,
                        11
                ),

                road(
                        -50,
                        260,
                        920,
                        315,
                        9
                ),

                road(
                        310,
                        -40,
                        500,
                        560,
                        8
                ),

                road(
                        620,
                        -30,
                        900,
                        540,
                        8
                )
        );

        // --------------------------------------------------------
        // WATER
        // --------------------------------------------------------

        Line river1 =
                new Line(
                        520,
                        560,
                        650,
                        500
                );

        river1.setStroke(
                Color.web("#73BDE8")
        );

        river1.setStrokeWidth(8);

        Line river2 =
                new Line(
                        650,
                        500,
                        850,
                        480
                );

        river2.setStroke(
                Color.web("#73BDE8")
        );

        river2.setStrokeWidth(8);

        map.getChildren().addAll(
                river1,
                river2
        );

        // --------------------------------------------------------
        // ROUTE
        // --------------------------------------------------------

        Line route =
                new Line(
                        280,
                        180,
                        520,
                        330
                );

        route.setStroke(
                Color.web(NAV_BLUE)
        );

        route.setStrokeWidth(3);

        route.getStrokeDashArray().addAll(
                7.0,
                7.0
        );

        map.getChildren().add(
                route
        );

        // --------------------------------------------------------
        // ARRIVAL BADGE
        // --------------------------------------------------------

        VBox arriving =
                new VBox(1);

        arriving.setAlignment(
                Pos.CENTER_LEFT
        );

        arriving.setPadding(
                new Insets(
                        10,
                        18,
                        10,
                        18
                )
        );

        arriving.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 22;"
        );

        Label arrivingTitle =
                new Label(
                        "ARRIVING IN"
                );

        arrivingTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        arrivingTitle.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Label arrivingTime =
                new Label(
                        "6 min"
                );

        arrivingTime.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        arrivingTime.setTextFill(
                Color.web(HEADING)
        );

        arriving.getChildren().addAll(
                arrivingTitle,
                arrivingTime
        );

        StackPane.setAlignment(
                arriving,
                Pos.TOP_LEFT
        );

        StackPane.setMargin(
                arriving,
                new Insets(18)
        );

        // --------------------------------------------------------
        // USER MARKER
        // --------------------------------------------------------

        StackPane you =
                createMarker(
                        NAV_BLUE,
                        "▰"
                );

        StackPane.setAlignment(
                you,
                Pos.CENTER
        );

        StackPane.setMargin(
                you,
                new Insets(
                        100,
                        0,
                        0,
                        220
                )
        );

        Label youLabel =
                markerLabel(
                        "You"
                );

        StackPane.setAlignment(
                youLabel,
                Pos.CENTER
        );

        StackPane.setMargin(
                youLabel,
                new Insets(
                        175,
                        0,
                        0,
                        220
                )
        );

        // --------------------------------------------------------
        // MAIN MECHANIC
        // --------------------------------------------------------

        StackPane mechanic =
                createMarker(
                        SUCCESS,
                        "⚒"
                );

        StackPane.setAlignment(
                mechanic,
                Pos.CENTER
        );

        StackPane.setMargin(
                mechanic,
                new Insets(
                        0,
                        0,
                        100,
                        0
                )
        );

        Label mechanicLabel =
                markerLabel(
                        "Arjun · 6 min"
                );

        StackPane.setAlignment(
                mechanicLabel,
                Pos.CENTER
        );

        StackPane.setMargin(
                mechanicLabel,
                new Insets(
                        60,
                        0,
                        0,
                        0
                )
        );

        // --------------------------------------------------------
        // OTHER MECHANICS
        // --------------------------------------------------------

        StackPane mechanic2 =
                createMarker(
                        SUCCESS,
                        "⚒"
                );

        StackPane.setAlignment(
                mechanic2,
                Pos.CENTER
        );

        StackPane.setMargin(
                mechanic2,
                new Insets(
                        0,
                        0,
                        0,
                        350
                )
        );

        StackPane mechanic3 =
                createMarker(
                        SUCCESS,
                        "⚒"
                );

        StackPane.setAlignment(
                mechanic3,
                Pos.CENTER
        );

        StackPane.setMargin(
                mechanic3,
                new Insets(
                        100,
                        0,
                        0,
                        430
                )
        );

        // --------------------------------------------------------
        // TOW TRUCKS
        // --------------------------------------------------------

        StackPane tow1 =
                createMarker(
                        ORANGE,
                        "▣"
                );

        StackPane.setAlignment(
                tow1,
                Pos.CENTER
        );

        StackPane.setMargin(
                tow1,
                new Insets(
                        0,
                        0,
                        190,
                        150
                )
        );

        StackPane tow2 =
                createMarker(
                        ORANGE,
                        "▣"
                );

        StackPane.setAlignment(
                tow2,
                Pos.CENTER
        );

        StackPane.setMargin(
                tow2,
                new Insets(
                        100,
                        0,
                        0,
                        500
                )
        );

        // --------------------------------------------------------
        // GPS STATUS
        // --------------------------------------------------------

        HBox gps =
                new HBox(8);

        gps.setAlignment(
                Pos.CENTER_LEFT
        );

        gps.setPadding(
                new Insets(
                        8,
                        14,
                        8,
                        14
                )
        );

        gps.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 18;"
        );

        Circle gpsDot =
                new Circle(
                        5,
                        Color.web(SUCCESS)
                );

        Label gpsText =
                new Label(
                        "Live GPS · 3.1 km remaining"
                );

        gpsText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        gpsText.setTextFill(
                Color.web(HEADING)
        );

        gps.getChildren().addAll(
                gpsDot,
                gpsText
        );

        StackPane.setAlignment(
                gps,
                Pos.BOTTOM_LEFT
        );

        StackPane.setMargin(
                gps,
                new Insets(
                        0,
                        0,
                        18,
                        18
                )
        );

        map.getChildren().addAll(
                arriving,
                you,
                youLabel,
                mechanic,
                mechanicLabel,
                mechanic2,
                mechanic3,
                tow1,
                tow2,
                gps
        );

        return map;
    }

    // ============================================================
    // ROAD
    // ============================================================

    private Line road(
            double startX,
            double startY,
            double endX,
            double endY,
            double width
    ) {

        Line line =
                new Line(
                        startX,
                        startY,
                        endX,
                        endY
                );

        line.setStroke(
                Color.WHITE
        );

        line.setStrokeWidth(
                width
        );

        return line;
    }

    // ============================================================
    // MARKER
    // ============================================================

    private StackPane createMarker(
            String color,
            String icon
    ) {

        StackPane marker =
                new StackPane();

        Circle outer =
                new Circle(
                        28,
                        Color.WHITE
                );

        Circle inner =
                new Circle(
                        22,
                        Color.web(color)
                );

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        iconLabel.setTextFill(
                Color.WHITE
        );

        marker.getChildren().addAll(
                outer,
                inner,
                iconLabel
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

        label.setPadding(
                new Insets(
                        6,
                        11,
                        6,
                        11
                )
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        label.setTextFill(
                Color.web(HEADING)
        );

        label.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15;"
        );

        return label;
    }

    // ============================================================
    // TRACKING INFO CARD
    // ============================================================

    private VBox createTrackingInfoCard() {

        VBox card =
                createCard();

        card.setPadding(
                new Insets(22)
        );

        Label status =
                new Label(
                        "En route"
                );

        status.setPadding(
                new Insets(
                        5,
                        12,
                        5,
                        12
                )
        );

        status.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        status.setTextFill(
                Color.web(SUCCESS)
        );

        status.setStyle(
                "-fx-background-color: " +
                        SECONDARY_SURFACE +
                        ";" +
                        "-fx-background-radius: 18;"
        );

        HBox mechanic =
                new HBox(12);

        mechanic.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle avatar =
                new Circle(
                        24,
                        Color.web("#E1ECFF")
                );

        Label initials =
                new Label(
                        "AM"
                );

        initials.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        initials.setTextFill(
                Color.web(NAV_BLUE)
        );

        StackPane avatarPane =
                new StackPane(
                        avatar,
                        initials
                );

        VBox mechanicText =
                new VBox(2);

        Label name =
                new Label(
                        "Arjun Mehta"
                );

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        name.setTextFill(
                Color.web(HEADING)
        );

        Label shop =
                new Label(
                        "SpeedFix Auto Care · 4.9 ★"
                );

        shop.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        shop.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        mechanicText.getChildren().addAll(
                name,
                shop
        );

        mechanic.getChildren().addAll(
                avatarPane,
                mechanicText
        );

        VBox details =
                new VBox(10);

        details.getChildren().addAll(
                infoRow(
                        "◷",
                        "ETA",
                        "6 minutes"
                ),
                infoRow(
                        "⌘",
                        "Distance left",
                        "3.1 km"
                ),
                infoRow(
                        "➤",
                        "Current road",
                        "Baner Road,\nnorthbound"
                )
        );

        Button call =
                new Button(
                        "☎   Call mechanic"
                );

        call.setMaxWidth(
                Double.MAX_VALUE
        );

        call.setPrefHeight(42);

        call.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        call.setTextFill(
                Color.WHITE
        );

        call.setStyle(
                "-fx-background-color: " +
                        NAV_BLUE +
                        ";" +
                        "-fx-background-radius: 22;" +
                        "-fx-cursor: hand;"
        );

        call.setOnAction(
                event ->
                        call.setText(
                                "Calling mechanic"
                        )
        );

        Button share =
                new Button(
                        "Share trip"
                );

        share.setMaxWidth(
                Double.MAX_VALUE
        );

        share.setPrefHeight(42);

        share.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        share.setTextFill(
                Color.web(HEADING)
        );

        share.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 22;" +
                        "-fx-background-radius: 22;" +
                        "-fx-cursor: hand;"
        );

        share.setOnAction(
                event ->
                        share.setText(
                                "Trip shared"
                        )
        );

        card.getChildren().addAll(
                status,
                mechanic,
                details,
                call,
                share
        );

        return card;
    }

    // ============================================================
    // INFO ROW
    // ============================================================

    private HBox infoRow(
            String icon,
            String title,
            String value
    ) {

        HBox row =
                new HBox(10);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        10,
                        12,
                        10,
                        12
                )
        );

        row.setStyle(
                "-fx-background-color: " +
                        SECONDARY_SURFACE +
                        ";" +
                        "-fx-background-radius: 18;"
        );

        Label iconLabel =
                new Label(
                        icon
                );

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        18
                )
        );

        iconLabel.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        titleLabel.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label valueLabel =
                new Label(
                        value
                );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        valueLabel.setTextFill(
                Color.web(HEADING)
        );

        row.getChildren().addAll(
                iconLabel,
                titleLabel,
                spacer,
                valueLabel
        );

        return row;
    }

    // ============================================================
    // LEGEND
    // ============================================================

    private VBox createLegendCard() {

        VBox card =
                createCard();

        card.setPadding(
                new Insets(22)
        );

        Label title =
                new Label(
                        "LEGEND"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        title.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        VBox items =
                new VBox(12);

        items.getChildren().addAll(
                legendItem(
                        NAV_BLUE,
                        "You"
                ),
                legendItem(
                        SUCCESS,
                        "Mechanics"
                ),
                legendItem(
                        ORANGE,
                        "Tow trucks"
                ),
                legendItem(
                        EMERGENCY,
                        "Police / emergency"
                )
        );

        card.getChildren().addAll(
                title,
                items
        );

        return card;
    }

    // ============================================================
    // LEGEND ITEM
    // ============================================================

    private HBox legendItem(
            String color,
            String text
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

        Label label =
                new Label(
                        text
                );

        label.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        label.setTextFill(
                Color.web(HEADING)
        );

        row.getChildren().addAll(
                dot,
                label
        );

        return row;
    }

    // ============================================================
    // COMMON CARD
    // ============================================================

    private VBox createCard() {

        VBox card =
                new VBox(16);

        card.setMinWidth(0);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

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

        return card;
    }
}
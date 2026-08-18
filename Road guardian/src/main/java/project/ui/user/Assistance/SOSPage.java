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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.ui.user.DashBoard.UserSectionPage;

/**
 * RoadGuardian - Emergency SOS
 *
 * This page is designed to be loaded inside UserDashboard.
 *
 * The common UserDashboard Header and Sidebar are intentionally
 * not created here.
 */
public class SOSPage extends UserSectionPage {

        private static final String MAIN_BACKGROUND = "#D6F0F3";
        private static final String CARD_SURFACE = "#EEF9FA";
        private static final String SECONDARY_SURFACE = "#DFF3F5";

        private static final String NAV_BLUE = "#2563EB";
        private static final String SUCCESS = "#16A34A";
        private static final String EMERGENCY = "#DC2626";
        private static final String BORDER = "#B8D4D9";

        private static final String HEADING = "#172033";
        private static final String SECONDARY_TEXT = "#526274";
        private static final String ORANGE_HOVER = "#EA580C";

        public SOSPage() {
                // UI is created when getView() is called.
        }

        @Override
        public VBox getView() {

                VBox content =
                        createSOSContent();

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
        // SOS CONTENT
        // ============================================================

        private VBox createSOSContent() {

                VBox content =
                        new VBox(22);

                content.setPadding(
                        new Insets(
                                28,
                                28,
                                40,
                                28
                        )
                );

                content.setFillWidth(true);

                content.setStyle(
                        "-fx-background-color: " +
                                MAIN_BACKGROUND +
                                ";"
                );

                HBox titleRow =
                        new HBox();

                titleRow.setAlignment(
                        Pos.CENTER_LEFT
                );

                VBox titleBox =
                        new VBox(4);

                Label title =
                        new Label(
                                "Emergency SOS"
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
                                "Get immediate roadside assistance and " +
                                        "track your responder."
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

                Region titleSpacer =
                        new Region();

                HBox.setHgrow(
                        titleSpacer,
                        Priority.ALWAYS
                );

                Label active =
                        new Label(
                                "●  SOS ACTIVE"
                        );

                active.setPadding(
                        new Insets(
                                8,
                                14,
                                8,
                                14
                        )
                );

                active.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                13
                        )
                );

                active.setTextFill(
                        Color.WHITE
                );

                active.setStyle(
                        "-fx-background-color: " +
                                EMERGENCY +
                                ";" +
                                "-fx-background-radius: 18;"
                );

                titleRow.getChildren().addAll(
                        titleBox,
                        titleSpacer,
                        active
                );

                HBox topCards =
                        new HBox(22);

                topCards.setFillHeight(true);

                VBox sosCard =
                        createSOSCard();

                VBox locationCard =
                        createLocationCard();

                HBox.setHgrow(
                        sosCard,
                        Priority.ALWAYS
                );

                HBox.setHgrow(
                        locationCard,
                        Priority.ALWAYS
                );

                sosCard.setMinWidth(0);
                locationCard.setMinWidth(0);

                sosCard.setMaxWidth(
                        Double.MAX_VALUE
                );

                locationCard.setMaxWidth(
                        Double.MAX_VALUE
                );

                topCards.getChildren().addAll(
                        sosCard,
                        locationCard
                );

                HBox bottomCards =
                        new HBox(22);

                bottomCards.setFillHeight(true);

                VBox timeline =
                        createTimelineCard();

                VBox contacts =
                        createContactsCard();

                VBox mechanic =
                        createMechanicCard();

                HBox.setHgrow(
                        timeline,
                        Priority.ALWAYS
                );

                HBox.setHgrow(
                        contacts,
                        Priority.ALWAYS
                );

                HBox.setHgrow(
                        mechanic,
                        Priority.ALWAYS
                );

                timeline.setMinWidth(0);
                contacts.setMinWidth(0);
                mechanic.setMinWidth(0);

                timeline.setMaxWidth(
                        Double.MAX_VALUE
                );

                contacts.setMaxWidth(
                        Double.MAX_VALUE
                );

                mechanic.setMaxWidth(
                        Double.MAX_VALUE
                );

                bottomCards.getChildren().addAll(
                        timeline,
                        contacts,
                        mechanic
                );

                content.getChildren().addAll(
                        titleRow,
                        topCards,
                        bottomCards
                );

                return content;
        }

        // ============================================================
        // SOS CARD
        // ============================================================

        private VBox createSOSCard() {

                VBox card =
                        createCard();

                Label heading =
                        new Label(
                                "Emergency assistance"
                        );

                heading.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                20
                        )
                );

                heading.setTextFill(
                        Color.web(HEADING)
                );

                Label description =
                        new Label(
                                "Your SOS request has been sent. " +
                                        "A nearby responder is on the way."
                        );

                description.setWrapText(true);

                description.setFont(
                        Font.font(
                                "Arial",
                                14
                        )
                );

                description.setTextFill(
                        Color.web(SECONDARY_TEXT)
                );

                StackPane sosCircle =
                        new StackPane();

                Circle outer =
                        new Circle(
                                105
                        );

                outer.setFill(
                        Color.web("#FDEBEC")
                );

                Circle inner =
                        new Circle(
                                82
                        );

                inner.setFill(
                        Color.web(EMERGENCY)
                );

                inner.setStroke(
                        Color.WHITE
                );

                inner.setStrokeWidth(5);

                Label sosText =
                        new Label(
                                "SOS"
                        );

                sosText.setTextFill(
                        Color.WHITE
                );

                sosText.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                30
                        )
                );

                sosCircle.getChildren().addAll(
                        outer,
                        inner,
                        sosText
                );

                Button call112 =
                        new Button(
                                "☎   Call 112 directly"
                        );

                call112.setMaxWidth(
                        Double.MAX_VALUE
                );

                call112.setPrefHeight(
                        48
                );

                call112.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                14
                        )
                );

                call112.setStyle(
                        "-fx-background-color: " +
                                EMERGENCY +
                                ";" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 24;" +
                                "-fx-cursor: hand;"
                );

                call112.setOnMouseEntered(
                        event ->
                                call112.setStyle(
                                        "-fx-background-color: " +
                                                ORANGE_HOVER +
                                                ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-background-radius: 24;" +
                                                "-fx-cursor: hand;"
                                )
                );

                call112.setOnMouseExited(
                        event ->
                                call112.setStyle(
                                        "-fx-background-color: " +
                                                EMERGENCY +
                                                ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-background-radius: 24;" +
                                                "-fx-cursor: hand;"
                                )
                );

                Label info =
                        new Label(
                                "Use 112 for immediate police, fire or " +
                                        "medical emergency assistance."
                        );

                info.setWrapText(true);

                info.setFont(
                        Font.font(
                                "Arial",
                                12
                        )
                );

                info.setTextFill(
                        Color.web(SECONDARY_TEXT)
                );

                card.getChildren().addAll(
                        heading,
                        description,
                        sosCircle,
                        call112,
                        info
                );

                return card;
        }

        // ============================================================
        // LOCATION CARD
        // ============================================================

        private VBox createLocationCard() {

                VBox card =
                        createCard();

                HBox heading =
                        new HBox();

                Label title =
                        new Label(
                                "Live location"
                        );

                title.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                20
                        )
                );

                title.setTextFill(
                        Color.web(HEADING)
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Label location =
                        new Label(
                                "NH-48 · Exit 12B"
                        );

                location.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                13
                        )
                );

                location.setTextFill(
                        Color.web(NAV_BLUE)
                );

                heading.getChildren().addAll(
                        title,
                        spacer,
                        location
                );

                StackPane map =
                        createMap();

                VBox.setVgrow(
                        map,
                        Priority.ALWAYS
                );

                HBox coordinates =
                        new HBox(10);

                coordinates.setAlignment(
                        Pos.CENTER_LEFT
                );

                Label coordinateText =
                        new Label(
                                "18.5204° N, 73.8567° E"
                        );

                coordinateText.setFont(
                        Font.font(
                                "Arial",
                                13
                        )
                );

                coordinateText.setTextFill(
                        Color.web(SECONDARY_TEXT)
                );

                coordinates.getChildren().add(
                        coordinateText
                );

                card.getChildren().addAll(
                        heading,
                        map,
                        coordinates
                );

                return card;
        }

        // ============================================================
        // MAP
        // ============================================================

        private StackPane createMap() {

                StackPane map =
                        new StackPane();

                map.setPrefHeight(
                        340
                );

                map.setMinHeight(
                        300
                );

                map.setStyle(
                        "-fx-background-color: #E7E7E4;" +
                                "-fx-background-radius: 22;" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 22;"
                );

                Rectangle mapClip =
                        new Rectangle();

                mapClip.setArcWidth(44);
                mapClip.setArcHeight(44);

                mapClip.widthProperty().bind(
                        map.widthProperty()
                );

                mapClip.heightProperty().bind(
                        map.heightProperty()
                );

                map.setClip(
                        mapClip
                );

                Line road1 =
                        new Line(
                                20,
                                260,
                                820,
                                45
                        );

                road1.setStroke(
                        Color.WHITE
                );

                road1.setStrokeWidth(13);

                Line road2 =
                        new Line(
                                180,
                                10,
                                650,
                                335
                        );

                road2.setStroke(
                        Color.WHITE
                );

                road2.setStrokeWidth(10);

                Line road3 =
                        new Line(
                                0,
                                120,
                                850,
                                210
                        );

                road3.setStroke(
                        Color.WHITE
                );

                road3.setStrokeWidth(9);

                Line road4 =
                        new Line(
                                470,
                                0,
                                520,
                                340
                        );

                road4.setStroke(
                        Color.WHITE
                );

                road4.setStrokeWidth(7);

                Line route =
                        new Line(
                                300,
                                205,
                                535,
                                130
                        );

                route.setStroke(
                        Color.web(NAV_BLUE)
                );

                route.setStrokeWidth(3);

                route.getStrokeDashArray().addAll(
                        7.0,
                        7.0
                );

                StackPane responder =
                        createMarker(
                                "⚒",
                                SUCCESS
                        );

                StackPane vehicle =
                        createMarker(
                                "▰",
                                NAV_BLUE
                        );

                StackPane.setAlignment(
                        responder,
                        Pos.CENTER
                );

                StackPane.setMargin(
                        responder,
                        new Insets(
                                0,
                                0,
                                120,
                                270
                        )
                );

                StackPane.setAlignment(
                        vehicle,
                        Pos.CENTER
                );

                StackPane.setMargin(
                        vehicle,
                        new Insets(
                                120,
                                0,
                                0,
                                520
                        )
                );

                Label responderLabel =
                        createMapLabel(
                                "Arjun · 6 min"
                        );

                StackPane.setAlignment(
                        responderLabel,
                        Pos.CENTER
                );

                StackPane.setMargin(
                        responderLabel,
                        new Insets(
                                50,
                                0,
                                0,
                                270
                        )
                );

                Label youLabel =
                        createMapLabel(
                                "You"
                        );

                StackPane.setAlignment(
                        youLabel,
                        Pos.CENTER
                );

                StackPane.setMargin(
                        youLabel,
                        new Insets(
                                195,
                                0,
                                0,
                                520
                        )
                );

                map.getChildren().addAll(
                        road1,
                        road2,
                        road3,
                        road4,
                        route,
                        responder,
                        vehicle,
                        responderLabel,
                        youLabel
                );

                return map;
        }

        // ============================================================
        // MARKER
        // ============================================================

        private StackPane createMarker(
                String icon,
                String color
        ) {

                StackPane marker =
                        new StackPane();

                Circle circle =
                        new Circle(
                                23
                        );

                circle.setFill(
                        Color.web(color)
                );

                circle.setStroke(
                        Color.WHITE
                );

                circle.setStrokeWidth(3);

                Label label =
                        new Label(
                                icon
                        );

                label.setTextFill(
                        Color.WHITE
                );

                label.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                14
                        )
                );

                marker.getChildren().addAll(
                        circle,
                        label
                );

                return marker;
        }

        // ============================================================
        // MAP LABEL
        // ============================================================

        private Label createMapLabel(
                String text
        ) {

                Label label =
                        new Label(
                                text
                        );

                label.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-text-fill: " +
                                HEADING +
                                ";" +
                                "-fx-padding: 6 11;" +
                                "-fx-background-radius: 14;" +
                                "-fx-font-weight: bold;"
                );

                return label;
        }

        // ============================================================
        // TIMELINE
        // ============================================================

        private VBox createTimelineCard() {

                VBox card =
                        createCard();

                Label title =
                        new Label(
                                "SOS status"
                        );

                title.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                20
                        )
                );

                title.setTextFill(
                        Color.web(HEADING)
                );

                card.getChildren().add(
                        title
                );

                card.getChildren().addAll(
                        createTimelineRow(
                                "SOS request received",
                                "7:18 PM",
                                SUCCESS,
                                true
                        ),
                        createTimelineRow(
                                "Emergency contacts notified",
                                "7:18 PM",
                                SUCCESS,
                                true
                        ),
                        createTimelineRow(
                                "Mechanic assigned",
                                "7:19 PM",
                                SUCCESS,
                                true
                        ),
                        createTimelineRow(
                                "Responder en route",
                                "7:20 PM",
                                NAV_BLUE,
                                true
                        ),
                        createTimelineRow(
                                "Assistance completed",
                                "Pending",
                                BORDER,
                                false
                        )
                );

                return card;
        }

        private HBox createTimelineRow(
                String titleText,
                String time,
                String color,
                boolean completed
        ) {

                HBox row =
                        new HBox(12);

                row.setAlignment(
                        Pos.CENTER_LEFT
                );

                Circle dot =
                        new Circle(
                                8,
                                Color.web(color)
                        );

                VBox textBox =
                        new VBox(2);

                Label title =
                        new Label(
                                titleText
                        );

                title.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                13
                        )
                );

                title.setTextFill(
                        Color.web(HEADING)
                );

                Label status =
                        new Label(
                                completed
                                        ? "Completed"
                                        : "Waiting"
                        );

                status.setFont(
                        Font.font(
                                "Arial",
                                11
                        )
                );

                status.setTextFill(
                        Color.web(
                                completed
                                        ? SUCCESS
                                        : SECONDARY_TEXT
                        )
                );

                textBox.getChildren().addAll(
                        title,
                        status
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Label timeLabel =
                        new Label(
                                time
                        );

                timeLabel.setFont(
                        Font.font(
                                "Arial",
                                12
                        )
                );

                timeLabel.setTextFill(
                        Color.web(SECONDARY_TEXT)
                );

                row.getChildren().addAll(
                        dot,
                        textBox,
                        spacer,
                        timeLabel
                );

                return row;
        }

        // ============================================================
        // EMERGENCY CONTACTS
        // ============================================================

        private VBox createContactsCard() {

                VBox card =
                        createCard();

                Label title =
                        new Label(
                                "Emergency contacts"
                        );

                title.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                20
                        )
                );

                title.setTextFill(
                        Color.web(HEADING)
                );

                card.getChildren().add(
                        title
                );

                card.getChildren().addAll(
                        createContactRow(
                                "PN",
                                "Priya Nair",
                                "Primary contact",
                                "+91 98765 43210"
                        ),
                        createContactRow(
                                "RN",
                                "Rahul Nair",
                                "Emergency contact",
                                "+91 99887 66554"
                        ),
                        createContactRow(
                                "112",
                                "National Emergency",
                                "Government emergency service",
                                "112"
                        )
                );

                return card;
        }

        private HBox createContactRow(
                String initials,
                String name,
                String role,
                String phone
        ) {

                HBox row =
                        new HBox(10);

                row.setAlignment(
                        Pos.CENTER_LEFT
                );

                StackPane avatar =
                        new StackPane();

                Circle circle =
                        new Circle(
                                21
                        );

                circle.setFill(
                        Color.web(SECONDARY_SURFACE)
                );

                Label initial =
                        new Label(
                                initials
                        );

                initial.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                11
                        )
                );

                initial.setTextFill(
                        Color.web(NAV_BLUE)
                );

                avatar.getChildren().addAll(
                        circle,
                        initial
                );

                VBox details =
                        new VBox(1);

                Label nameLabel =
                        new Label(
                                name
                        );

                nameLabel.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                13
                        )
                );

                nameLabel.setTextFill(
                        Color.web(HEADING)
                );

                Label roleLabel =
                        new Label(
                                role
                        );

                roleLabel.setFont(
                        Font.font(
                                "Arial",
                                11
                        )
                );

                roleLabel.setTextFill(
                        Color.web(SECONDARY_TEXT)
                );

                details.getChildren().addAll(
                        nameLabel,
                        roleLabel
                );

                Region spacer =
                        new Region();

                HBox.setHgrow(
                        spacer,
                        Priority.ALWAYS
                );

                Button call =
                        new Button(
                                "Call"
                        );

                call.setPrefHeight(
                        34
                );

                call.setPadding(
                        new Insets(
                                0,
                                14,
                                0,
                                14
                        )
                );

                call.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                12
                        )
                );

                call.setStyle(
                        "-fx-background-color: " +
                                SECONDARY_SURFACE +
                                ";" +
                                "-fx-text-fill: " +
                                NAV_BLUE +
                                ";" +
                                "-fx-background-radius: 18;" +
                                "-fx-cursor: hand;"
                );

                call.setOnAction(
                        event ->
                                call.setText(
                                        "Calling"
                                )
                );

                row.getChildren().addAll(
                        avatar,
                        details,
                        spacer,
                        call
                );

                return row;
        }

        // ============================================================
        // ASSIGNED MECHANIC
        // ============================================================

        private VBox createMechanicCard() {

                VBox card =
                        createCard();

                Label title =
                        new Label(
                                "Assigned mechanic"
                        );

                title.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                20
                        )
                );

                title.setTextFill(
                        Color.web(HEADING)
                );

                StackPane avatar =
                        new StackPane();

                Circle circle =
                        new Circle(
                                34
                        );

                circle.setFill(
                        Color.web(SECONDARY_SURFACE)
                );

                Label initials =
                        new Label(
                                "AM"
                        );

                initials.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                15
                        )
                );

                initials.setTextFill(
                        Color.web(NAV_BLUE)
                );

                avatar.getChildren().addAll(
                        circle,
                        initials
                );

                Label name =
                        new Label(
                                "Arjun Mehta"
                        );

                name.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                18
                        )
                );

                name.setTextFill(
                        Color.web(HEADING)
                );

                Label rating =
                        new Label(
                                "★ 4.9 · SpeedFix Auto Care"
                        );

                rating.setFont(
                        Font.font(
                                "Arial",
                                13
                        )
                );

                rating.setTextFill(
                        Color.web(SECONDARY_TEXT)
                );

                Label eta =
                        new Label(
                                "Estimated arrival · 6 minutes"
                        );

                eta.setFont(
                        Font.font(
                                "Arial",
                                13
                        )
                );

                eta.setTextFill(
                        Color.web(SUCCESS)
                );

                Button call =
                        new Button(
                                "☎   Call mechanic"
                        );

                call.setMaxWidth(
                        Double.MAX_VALUE
                );

                call.setPrefHeight(
                        44
                );

                call.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                13
                        )
                );

                call.setStyle(
                        "-fx-background-color: " +
                                NAV_BLUE +
                                ";" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 22;" +
                                "-fx-cursor: hand;"
                );

                call.setOnAction(
                        event ->
                                call.setText(
                                        "Calling mechanic"
                                )
                );

                card.getChildren().addAll(
                        title,
                        avatar,
                        name,
                        rating,
                        eta,
                        call
                );

                return card;
        }

        // ============================================================
        // COMMON CARD
        // ============================================================

        private VBox createCard() {

                VBox card =
                        new VBox(13);

                card.setPadding(
                        new Insets(22)
                );

                card.setMinWidth(0);

                card.setMaxWidth(
                        Double.MAX_VALUE
                );

                card.setMinHeight(
                        300
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
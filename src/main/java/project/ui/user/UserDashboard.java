package project.ui.user;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
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
import javafx.stage.Stage;
import project.ui.user.UserHeader;

public class UserDashboard extends Application {

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

        public static Stage dashboardStage;
        private Scene dashboardScene;

        // ============================================================
        // APPLICATION START
        // ============================================================

        @Override
        public void start(Stage stage) {

                dashboardStage = stage;

                dashboardScene = getDashboardScene();

                dashboardStage.setTitle("RoadGuardian - Customer Dashboard");
                dashboardStage.setMaximized(true);

                stage.setScene(dashboardScene);

                stage.show();
        }

        // ============================================================
        // DASHBOARD SCENE
        // ============================================================

        public Scene getDashboardScene() {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + MAIN_BACKGROUND + ";");

                HBox topBar = UserHeader.createHeader();

                ScrollPane sidebar = UserSideBar.createSidebar("Dashboard");

                VBox dashboardContent = createDashboardContent();

                // ========================================================
                // RIGHT / MAIN DASHBOARD VERTICAL SCROLL
                // ========================================================

                ScrollPane dashboardScroll = new ScrollPane(dashboardContent);

                dashboardScroll.setFitToWidth(true);
                dashboardScroll.setFitToHeight(false);

                // Right side vertical scrollbar
                dashboardScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.ALWAYS);

                // No horizontal scrollbar
                dashboardScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                dashboardScroll.setPannable(true);

                dashboardScroll.setStyle(
                                "-fx-background-color: " + MAIN_BACKGROUND + ";" +
                                                "-fx-border-color: transparent;");

                root.setTop(topBar);
                root.setLeft(sidebar);
                root.setCenter(dashboardScroll);

                BorderPane.setAlignment(
                                dashboardScroll,
                                Pos.TOP_LEFT);

                dashboardScene = new Scene(root, dashboardStage.getWidth(), dashboardStage.getHeight());

                addScrollbarStyle(dashboardScene);

                return dashboardScene;
        }

       
        // ============================================================
        // MAIN DASHBOARD CONTENT
        // ============================================================

        private VBox createDashboardContent() {

                VBox content = new VBox(22);

                content.setPadding(
                                new Insets(
                                                28,
                                                28,
                                                35,
                                                28));

                content.setMinWidth(0);
                content.setMaxWidth(
                                Double.MAX_VALUE);
                content.setMinHeight(900);

                content.setStyle(
                                "-fx-background-color: " + MAIN_BACKGROUND + ";");

                // ---------------- GREETING ----------------

                HBox greetingRow = new HBox();

                greetingRow.setAlignment(
                                Pos.CENTER_LEFT);

                VBox greeting = new VBox(3);

                Label title = new Label(
                                "Good evening, Aarav");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                31));

                title.setTextFill(
                                Color.web(HEADING));

                Label subtitle = new Label(
                                "Honda City · MH 12 QR 4410 · Last synced 2 minutes ago");

                subtitle.setFont(
                                Font.font(15));

                subtitle.setTextFill(
                                Color.web(SECONDARY_TEXT));

                greeting.getChildren().addAll(
                                title,
                                subtitle);

                Region greetingSpacer = new Region();

                HBox.setHgrow(
                                greetingSpacer,
                                Priority.ALWAYS);

                Button sosButton = new Button(
                                "♧   Emergency SOS");

                sosButton.setPrefHeight(40);

                sosButton.setPadding(
                                new Insets(
                                                0,
                                                20,
                                                0,
                                                20));

                sosButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                sosButton.setStyle(
                                "-fx-background-color: " + EMERGENCY + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-background-radius: 22;");

                sosButton.setOnMouseEntered(
                                e -> sosButton.setStyle(
                                                "-fx-background-color: " + ORANGE_HOVER + ";" +
                                                                "-fx-text-fill: white;" +
                                                                "-fx-background-radius: 22;"));

                sosButton.setOnMouseExited(
                                e -> sosButton.setStyle(
                                                "-fx-background-color: " + EMERGENCY + ";" +
                                                                "-fx-text-fill: white;" +
                                                                "-fx-background-radius: 22;"));

                // ========================================================
                // EMERGENCY SOS NAVIGATION
                // ========================================================

                sosButton.setOnAction(
                                e -> openSOSPageFromNode(sosButton));

                greetingRow.getChildren().addAll(
                                greeting,
                                greetingSpacer,
                                sosButton);

                content.getChildren().add(
                                greetingRow);

                // ---------------- STATUS CARDS ----------------

                content.getChildren().add(
                                createStatusCards());

                // ---------------- MAIN ROW ----------------

                HBox mainRow = new HBox(20);

                mainRow.setAlignment(
                                Pos.TOP_LEFT);

                mainRow.setMinWidth(0);
                mainRow.setMaxWidth(
                                Double.MAX_VALUE);

                VBox emergencyCard = createEmergencyCard();

                VBox quickActions = createQuickActions();

                emergencyCard.setMinWidth(0);
                emergencyCard.setMaxWidth(
                                Double.MAX_VALUE);

                quickActions.setMinWidth(320);
                quickActions.setPrefWidth(360);
                quickActions.setMaxWidth(390);

                HBox.setHgrow(
                                emergencyCard,
                                Priority.ALWAYS);

                mainRow.getChildren().addAll(
                                emergencyCard,
                                quickActions);

                content.getChildren().add(
                                mainRow);

                // ---------------- BOTTOM ROW ----------------
                // Three cards are always exactly the same width.

                GridPane bottomGrid = new GridPane();

                bottomGrid.setHgap(24);
                bottomGrid.setMinWidth(0);
                bottomGrid.setMaxWidth(Double.MAX_VALUE);

                ColumnConstraints bottomCol1 = new ColumnConstraints();

                ColumnConstraints bottomCol2 = new ColumnConstraints();

                ColumnConstraints bottomCol3 = new ColumnConstraints();

                bottomCol1.setPercentWidth(33.3333);
                bottomCol2.setPercentWidth(33.3333);
                bottomCol3.setPercentWidth(33.3334);

                bottomCol1.setHgrow(Priority.ALWAYS);
                bottomCol2.setHgrow(Priority.ALWAYS);
                bottomCol3.setHgrow(Priority.ALWAYS);

                bottomCol1.setFillWidth(true);
                bottomCol2.setFillWidth(true);
                bottomCol3.setFillWidth(true);

                bottomGrid.getColumnConstraints().addAll(
                                bottomCol1,
                                bottomCol2,
                                bottomCol3);

                VBox health = createHealthBreakdown();

                VBox repairs = createRecentRepairs();

                VBox mechanics = createTopMechanicsCard();

                health.setMaxWidth(Double.MAX_VALUE);
                repairs.setMaxWidth(Double.MAX_VALUE);
                mechanics.setMaxWidth(Double.MAX_VALUE);

                GridPane.setHgrow(health, Priority.ALWAYS);
                GridPane.setHgrow(repairs, Priority.ALWAYS);
                GridPane.setHgrow(mechanics, Priority.ALWAYS);

                bottomGrid.add(health, 0, 0);
                bottomGrid.add(repairs, 1, 0);
                bottomGrid.add(mechanics, 2, 0);

                content.getChildren().add(
                                bottomGrid);

                return content;
        }

        // ============================================================
        // STATUS CARDS
        // ============================================================

        private GridPane createStatusCards() {

                GridPane grid = new GridPane();

                grid.setHgap(20);

                grid.setMinWidth(0);

                grid.setMaxWidth(
                                Double.MAX_VALUE);

                ColumnConstraints c1 = createColumn();

                ColumnConstraints c2 = createColumn();

                ColumnConstraints c3 = createColumn();

                ColumnConstraints c4 = createColumn();

                grid.getColumnConstraints().addAll(
                                c1,
                                c2,
                                c3,
                                c4);

                grid.add(
                                createStatusCard(
                                                "VEHICLE HEALTH",
                                                "86 / 100",
                                                "Good · 2 advisories",
                                                "◔",
                                                SECONDARY_SURFACE,
                                                SUCCESS),
                                0,
                                0);

                grid.add(
                                createStatusCard(
                                                "NEXT SERVICE DUE",
                                                "480 km",
                                                "Approx. 12 Sep 2026",
                                                "◫",
                                                SECONDARY_SURFACE,
                                                NAV_BLUE),
                                1,
                                0);

                grid.add(
                                createStatusCard(
                                                "INSURANCE EXPIRY",
                                                "15 days",
                                                "Renew to stay covered",
                                                "♢",
                                                "#FFF0E7",
                                                PRIMARY_ORANGE),
                                2,
                                0);

                grid.add(
                                createStatusCard(
                                                "ACTIVE REQUEST",
                                                "In progress",
                                                "Mechanic 6 min away",
                                                "⌁",
                                                "#FDEBEC",
                                                EMERGENCY),
                                3,
                                0);

                return grid;
        }

        private ColumnConstraints createColumn() {

                ColumnConstraints column = new ColumnConstraints();

                column.setPercentWidth(25);

                column.setHgrow(
                                Priority.ALWAYS);

                return column;
        }

        private VBox createStatusCard(
                        String heading,
                        String value,
                        String subText,
                        String icon,
                        String iconBackground,
                        String iconColor) {

                VBox card = new VBox(6);

                card.setPadding(
                                new Insets(19));

                card.setMinWidth(0);
                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setMinHeight(128);

                card.setStyle(
                                "-fx-background-color: " + CARD_SURFACE + ";" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 20;" +
                                                "-fx-background-radius: 20;");

                HBox top = new HBox();

                top.setAlignment(
                                Pos.CENTER_LEFT);

                Label headingLabel = new Label(heading);

                headingLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                12));

                headingLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                StackPane iconCircle = createIconCircle(
                                icon,
                                iconBackground,
                                iconColor);

                top.getChildren().addAll(
                                headingLabel,
                                spacer,
                                iconCircle);

                Label valueLabel = new Label(value);

                valueLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                27));

                valueLabel.setTextFill(
                                Color.web(HEADING));

                Label subLabel = new Label(subText);

                subLabel.setFont(
                                Font.font(13));

                subLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                card.getChildren().addAll(
                                top,
                                valueLabel,
                                subLabel);

                return card;
        }

        private StackPane createIconCircle(
                        String icon,
                        String background,
                        String iconColor) {

                StackPane box = new StackPane();

                Circle circle = new Circle(22);

                circle.setFill(
                                Color.web(background));

                Label iconLabel = new Label(icon);

                iconLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                iconLabel.setTextFill(
                                Color.web(iconColor));

                box.getChildren().addAll(
                                circle,
                                iconLabel);

                return box;
        }

        // ============================================================
        // ACTIVE EMERGENCY CARD
        // ============================================================

        private VBox createEmergencyCard() {

                VBox card = new VBox(14);

                card.setPadding(
                                new Insets(22));

                card.setMinHeight(450);

                card.setStyle(
                                "-fx-background-color: " + CARD_SURFACE + ";" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 22;" +
                                                "-fx-background-radius: 22;");

                HBox heading = new HBox();

                heading.setAlignment(
                                Pos.CENTER_LEFT);

                VBox titles = new VBox(3);

                Label title = new Label(
                                "Active emergency request");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(HEADING));

                Label sub = new Label(
                                "Battery jump start · NH-48, Exit 12B");

                sub.setFont(
                                Font.font(14));

                sub.setTextFill(
                                Color.web(SECONDARY_TEXT));

                titles.getChildren().addAll(
                                title,
                                sub);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label route = new Label("En route");

                route.setPadding(
                                new Insets(
                                                5,
                                                13,
                                                5,
                                                13));

                route.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                route.setTextFill(
                                Color.web(SUCCESS));

                route.setStyle(
                                "-fx-background-color: " + SECONDARY_SURFACE + ";" +
                                                "-fx-background-radius: 16;");

                heading.getChildren().addAll(
                                titles,
                                spacer,
                                route);

                StackPane map = createMap();

                HBox mechanic = createMechanicInfo();

                card.getChildren().addAll(
                                heading,
                                map,
                                mechanic);

                return card;
        }

        // ============================================================
        // MAP
        // ============================================================

        private StackPane createMap() {

                StackPane map = new StackPane();

                map.setMinHeight(275);

                map.setStyle(
                                "-fx-background-color: #E7E7E4;" +
                                                "-fx-background-radius: 22;" +
                                                "-fx-border-radius: 22;" +
                                                "-fx-border-color: " + BORDER + ";");

                // Main roads
                Line road1 = new Line(
                                20,
                                210,
                                780,
                                40);

                road1.setStroke(
                                Color.WHITE);

                road1.setStrokeWidth(11);

                Line road2 = new Line(
                                160,
                                20,
                                620,
                                275);

                road2.setStroke(
                                Color.WHITE);

                road2.setStrokeWidth(9);

                Line road3 = new Line(
                                10,
                                100,
                                800,
                                175);

                road3.setStroke(
                                Color.WHITE);

                road3.setStrokeWidth(8);

                Line road4 = new Line(
                                420,
                                0,
                                500,
                                275);

                road4.setStroke(
                                Color.WHITE);

                road4.setStrokeWidth(6);

                // River
                Line river = new Line(
                                560,
                                275,
                                790,
                                215);

                river.setStroke(
                                Color.web("#73BDE8"));

                river.setStrokeWidth(5);

                map.getChildren().addAll(
                                road1,
                                road2,
                                road3,
                                road4,
                                river);

                // Mechanic marker
                StackPane mechanicPin = createMapMarker(
                                "⚒",
                                SUCCESS);

                StackPane.setAlignment(
                                mechanicPin,
                                Pos.CENTER);

                StackPane.setMargin(
                                mechanicPin,
                                new Insets(
                                                0,
                                                0,
                                                125,
                                                220));

                // User marker
                StackPane userPin = createMapMarker(
                                "▰",
                                NAV_BLUE);

                StackPane.setAlignment(
                                userPin,
                                Pos.CENTER);

                StackPane.setMargin(
                                userPin,
                                new Insets(
                                                95,
                                                0,
                                                0,
                                                360));

                // Route
                Line route = new Line(
                                240,
                                125,
                                380,
                                175);

                route.setStroke(
                                Color.web(NAV_BLUE));

                route.setStrokeWidth(2);

                route.getStrokeDashArray().addAll(
                                6.0,
                                6.0);

                map.getChildren().add(
                                route);

                // Mechanic label
                Label mechanicLabel = new Label(
                                "Arjun · 6 min");

                mechanicLabel.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: " + HEADING + ";" +
                                                "-fx-padding: 6 11;" +
                                                "-fx-background-radius: 14;" +
                                                "-fx-font-weight: bold;");

                StackPane.setAlignment(
                                mechanicLabel,
                                Pos.CENTER);

                StackPane.setMargin(
                                mechanicLabel,
                                new Insets(
                                                55,
                                                0,
                                                0,
                                                220));

                Label you = new Label("You");

                you.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: " + HEADING + ";" +
                                                "-fx-padding: 5 11;" +
                                                "-fx-background-radius: 14;" +
                                                "-fx-font-weight: bold;");

                StackPane.setAlignment(
                                you,
                                Pos.CENTER);

                StackPane.setMargin(
                                you,
                                new Insets(
                                                175,
                                                0,
                                                0,
                                                360));

                map.getChildren().addAll(
                                mechanicPin,
                                userPin,
                                mechanicLabel,
                                you);

                return map;
        }

        private StackPane createMapMarker(
                        String icon,
                        String color) {

                StackPane marker = new StackPane();

                Circle circle = new Circle(22);

                circle.setFill(
                                Color.web(color));

                circle.setStroke(
                                Color.WHITE);

                circle.setStrokeWidth(3);

                Label iconLabel = new Label(icon);

                iconLabel.setTextFill(
                                Color.WHITE);

                iconLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                marker.getChildren().addAll(
                                circle,
                                iconLabel);

                return marker;
        }

        // ============================================================
        // MECHANIC INFORMATION
        // ============================================================

        private HBox createMechanicInfo() {

                HBox box = new HBox(12);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                StackPane avatar = new StackPane();

                Circle circle = new Circle(22);

                circle.setFill(
                                Color.web(SECONDARY_SURFACE));

                Label initials = new Label("AM");

                initials.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));

                initials.setTextFill(
                                Color.web(NAV_BLUE));

                avatar.getChildren().addAll(
                                circle,
                                initials);

                VBox details = new VBox(2);

                Label name = new Label(
                                "Arjun Mehta · 4.9 ★");

                name.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));

                name.setTextFill(
                                Color.web(HEADING));

                Label info = new Label(
                                "SpeedFix Auto Care · Petrol, Battery");

                info.setFont(
                                Font.font(12));

                info.setTextFill(
                                Color.web(SECONDARY_TEXT));

                details.getChildren().addAll(
                                name,
                                info);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button track = new Button(
                                "Track live   →");

                track.setPadding(
                                new Insets(
                                                9,
                                                17,
                                                9,
                                                17));

                track.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                track.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: " + HEADING + ";" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 20;" +
                                                "-fx-background-radius: 20;");

                box.getChildren().addAll(
                                avatar,
                                details,
                                spacer,
                                track);

                return box;
        }

        // ============================================================
        // QUICK ACTIONS
        // ============================================================

        private VBox createQuickActions() {

                VBox card = new VBox(10);

                card.setPadding(
                                new Insets(22));

                card.setMinHeight(450);

                card.setStyle(
                                "-fx-background-color: " + CARD_SURFACE + ";" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 22;" +
                                                "-fx-background-radius: 22;");

                Label title = new Label("Quick actions");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(HEADING));

                card.getChildren().add(
                                title);

                card.getChildren().addAll(
                                createActionButton(
                                                "⚒",
                                                "Request Mechanic",
                                                SECONDARY_SURFACE,
                                                NAV_BLUE),
                                createActionButton(
                                                "▱",
                                                "Request Tow Truck",
                                                "#FFF0E7",
                                                PRIMARY_ORANGE),
                                createActionButton(
                                                "♧",
                                                "Emergency SOS",
                                                "#FDEBEC",
                                                EMERGENCY),
                                createActionButton(
                                                "▧",
                                                "Upload Documents",
                                                SECONDARY_SURFACE,
                                                SUCCESS),
                                createActionButton(
                                                "◇",
                                                "Nearby Mechanics",
                                                SECONDARY_SURFACE,
                                                NAV_BLUE));

                return card;
        }

        private HBox createActionButton(
                        String icon,
                        String text,
                        String iconBackground,
                        String iconColor) {

                HBox row = new HBox(13);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setPrefHeight(58);

                row.setPadding(
                                new Insets(
                                                8,
                                                13,
                                                8,
                                                12));

                row.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 29;" +
                                                "-fx-background-radius: 29;");

                StackPane iconCircle = createIconCircle(
                                icon,
                                iconBackground,
                                iconColor);

                Label textLabel = new Label(text);

                textLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                15));

                textLabel.setTextFill(
                                Color.web(HEADING));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label arrow = new Label("→");

                arrow.setFont(
                                Font.font(
                                                "Arial",
                                                20));

                arrow.setTextFill(
                                Color.web(SECONDARY_TEXT));

                row.getChildren().addAll(
                                iconCircle,
                                textLabel,
                                spacer,
                                arrow);

                row.setOnMouseEntered(
                                e -> row.setStyle(
                                                "-fx-background-color: " + SECONDARY_SURFACE + ";" +
                                                                "-fx-border-color: " + BORDER + ";" +
                                                                "-fx-border-radius: 29;" +
                                                                "-fx-background-radius: 29;"));

                row.setOnMouseExited(
                                e -> row.setStyle(
                                                "-fx-background-color: white;" +
                                                                "-fx-border-color: " + BORDER + ";" +
                                                                "-fx-border-radius: 29;" +
                                                                "-fx-background-radius: 29;"));

                // Quick Actions -> Emergency SOS
                if (text.equals("Emergency SOS")) {

                        row.setOnMouseClicked(
                                        e -> openSOSPageFromNode(row));
                }

                return row;
        }

        private void openSOSPageFromNode(javafx.scene.Node sourceNode) {

                try {

                        Stage stage = (Stage) sourceNode
                                        .getScene()
                                        .getWindow();

                        SOSPage sosPage = new SOSPage(() -> {

                                // SOS -> Dashboard
                                stage.setTitle(
                                                "RoadGuardian - Customer Dashboard");

                                stage.setScene(
                                                dashboardScene);

                                stage.show();
                        });

                        // Dashboard -> SOS
                        stage.setTitle(
                                        "RoadGuardian - Emergency SOS");

                        stage.setScene(
                                        sosPage.getSOSScene());

                        stage.show();

                } catch (Exception ex) {

                        ex.printStackTrace();
                }
        }
        // ============================================================
        // BOTTOM CARDS
        // ============================================================

        private VBox createHealthBreakdown() {

                VBox card = createBottomCard(
                                "Health breakdown");

                // Battery
                card.getChildren().add(
                                createHealthProgressRow(
                                                "Battery",
                                                "42%",
                                                0.42,
                                                EMERGENCY));

                // Tyres
                card.getChildren().add(
                                createHealthProgressRow(
                                                "Tyres",
                                                "78%",
                                                0.78,
                                                "#F59E0B"));

                // Brakes
                card.getChildren().add(
                                createHealthProgressRow(
                                                "Brakes",
                                                "91%",
                                                0.91,
                                                "#10B981"));

                // Engine oil
                card.getChildren().add(
                                createHealthProgressRow(
                                                "Engine oil",
                                                "64%",
                                                0.64,
                                                NAV_BLUE));

                return card;
        }

        private VBox createHealthProgressRow(
                        String labelText,
                        String percentage,
                        double progress,
                        String progressColor) {

                VBox row = new VBox(7);

                row.setMaxWidth(
                                Double.MAX_VALUE);

                HBox top = new HBox();

                top.setAlignment(
                                Pos.CENTER_LEFT);

                Label label = new Label(labelText);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                15));

                label.setTextFill(
                                Color.web(HEADING));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label value = new Label(percentage);

                value.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                14));

                value.setTextFill(
                                Color.web(SECONDARY_TEXT));

                top.getChildren().addAll(
                                label,
                                spacer,
                                value);

                // Track
                StackPane track = new StackPane();

                track.setPrefHeight(10);
                track.setMinHeight(10);
                track.setMaxHeight(10);

                track.setMaxWidth(
                                Double.MAX_VALUE);

                track.setStyle(
                                "-fx-background-color: #E6EEF1;" +
                                                "-fx-background-radius: 8;");

                Region fill = new Region();

                fill.setPrefHeight(10);
                fill.setMinHeight(10);
                fill.setMaxHeight(10);

                fill.setMaxWidth(
                                Double.MAX_VALUE);

                fill.setStyle(
                                "-fx-background-color: " + progressColor + ";" +
                                                "-fx-background-radius: 8;");

                StackPane.setAlignment(
                                fill,
                                Pos.CENTER_LEFT);

                // Keep the progress width proportional to the track.
                fill.prefWidthProperty().bind(
                                track.widthProperty().multiply(progress));

                track.getChildren().add(
                                fill);

                row.getChildren().addAll(
                                top,
                                track);

                return row;
        }

        private VBox createRecentRepairs() {

                VBox card = createBottomCard(
                                "Recent repairs");

                card.getChildren().addAll(
                                createRepairRow(
                                                "Full periodic service",
                                                "12 Jul 2026 · SpeedFix Auto Care",
                                                "₹6,480"),

                                createRepairRow(
                                                "Front brake pad replacement",
                                                "28 Apr 2026 · Highway Motors",
                                                "₹4,150"),

                                createRepairRow(
                                                "Battery jump start (roadside)",
                                                "05 Feb 2026 · PitStop 24×7",
                                                "₹700"),

                                createRepairRow(
                                                "AC gas refill & cabin filter",
                                                "19 Nov 2025 · SpeedFix Auto Care",
                                                "₹3,260"));

                Region spacer = new Region();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                // ========================================================
                // VIEW FULL HISTORY BUTTON
                // Normal -> transparent / text only
                // Hover -> orange filled button, like the reference UI
                // ========================================================

                Button history = new Button(
                                "View full history");

                history.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                16));

                history.setTextFill(
                                Color.web(HEADING));

                history.setAlignment(
                                Pos.CENTER);

                history.setMaxWidth(
                                Double.MAX_VALUE);

                history.setPrefHeight(46);
                history.setMinHeight(46);
                history.setMaxHeight(46);

                history.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background-radius: 24;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-padding: 0 18 0 18;");

                // Hover state: exact orange style from the reference image.
                history.setOnMouseEntered(e -> {

                        history.setStyle(
                                        "-fx-background-color: " + PRIMARY_ORANGE + ";" +
                                                        "-fx-background-radius: 24;" +
                                                        "-fx-border-color: transparent;" +
                                                        "-fx-padding: 0 18 0 18;");

                        history.setTextFill(
                                        Color.WHITE);
                });

                // Normal state.
                history.setOnMouseExited(e -> {

                        history.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-background-radius: 24;" +
                                                        "-fx-border-color: transparent;" +
                                                        "-fx-padding: 0 18 0 18;");

                        history.setTextFill(
                                        Color.web(HEADING));
                });

                card.getChildren().addAll(
                                spacer,
                                history);

                return card;
        }

        private HBox createRepairRow(
                        String titleText,
                        String subText,
                        String amountText) {

                HBox row = new HBox();

                row.setAlignment(
                                Pos.TOP_LEFT);

                row.setPadding(
                                new Insets(
                                                0,
                                                0,
                                                13,
                                                0));

                row.setBorder(
                                new javafx.scene.layout.Border(
                                                new javafx.scene.layout.BorderStroke(
                                                                Color.web(BORDER),
                                                                javafx.scene.layout.BorderStrokeStyle.SOLID,
                                                                CornerRadii.EMPTY,
                                                                new javafx.scene.layout.BorderWidths(
                                                                                0,
                                                                                0,
                                                                                1,
                                                                                0))));

                VBox details = new VBox(2);

                Label title = new Label(titleText);

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                15));

                title.setTextFill(
                                Color.web(HEADING));

                Label sub = new Label(subText);

                sub.setFont(
                                Font.font(13));

                sub.setTextFill(
                                Color.web(SECONDARY_TEXT));

                details.getChildren().addAll(
                                title,
                                sub);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label amount = new Label(amountText);

                amount.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));

                amount.setTextFill(
                                Color.web(HEADING));

                row.getChildren().addAll(
                                details,
                                spacer,
                                amount);

                return row;
        }

        private VBox createTopMechanicsCard() {

                VBox card = createBottomCard(
                                "Top mechanics near you");

                card.getChildren().addAll(
                                createMechanicRow(
                                                "AM",
                                                "Arjun Mehta",
                                                "1.2 km · 6 min · 4.9 ★",
                                                "Best"),

                                createMechanicRow(
                                                "KS",
                                                "Kabir Sharma",
                                                "2.4 km · 11 min · 4.7 ★",
                                                ""),

                                createMechanicRow(
                                                "NR",
                                                "Neha Rao",
                                                "3.1 km · 14 min · 4.8 ★",
                                                ""));

                VBox coverage = new VBox(8);

                coverage.setPadding(
                                new Insets(
                                                14,
                                                14,
                                                12,
                                                14));

                coverage.setStyle(
                                "-fx-background-color: " + SECONDARY_SURFACE + ";" +
                                                "-fx-background-radius: 20;");

                Label coverageTitle = new Label(
                                "COVERAGE THIS MONTH");

                coverageTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                coverageTitle.setTextFill(
                                Color.web(SECONDARY_TEXT));

                StackPane coverageBar = new StackPane();

                coverageBar.setPrefHeight(10);
                coverageBar.setMinHeight(10);
                coverageBar.setMaxHeight(10);

                coverageBar.setMaxWidth(
                                Double.MAX_VALUE);

                coverageBar.setStyle(
                                "-fx-background-color: #C7D9F6;" +
                                                "-fx-background-radius: 8;");

                Region used = new Region();

                used.setPrefHeight(10);
                used.setMinHeight(10);
                used.setMaxHeight(10);

                used.prefWidthProperty().bind(
                                coverageBar.widthProperty().multiply(0.75));

                used.setStyle(
                                "-fx-background-color: " + NAV_BLUE + ";" +
                                                "-fx-background-radius: 8;");

                StackPane.setAlignment(
                                used,
                                Pos.CENTER_LEFT);

                coverageBar.getChildren().add(
                                used);

                Label remaining = new Label(
                                "3 of 4 free assists remaining");

                remaining.setFont(
                                Font.font(13));

                remaining.setTextFill(
                                Color.web(SECONDARY_TEXT));

                coverage.getChildren().addAll(
                                coverageTitle,
                                coverageBar,
                                remaining);

                Region coverageSpacer = new Region();

                VBox.setVgrow(
                                coverageSpacer,
                                Priority.ALWAYS);

                card.getChildren().addAll(
                                coverageSpacer,
                                coverage);

                return card;
        }

        private VBox createBottomCard(
                        String title) {

                VBox card = new VBox(14);

                card.setPadding(
                                new Insets(22));

                // All three cards use exactly the same dimensions.
                card.setMinWidth(0);
                card.setPrefWidth(
                                0);
                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setMinHeight(450);
                card.setPrefHeight(450);
                card.setMaxHeight(450);

                card.setFillWidth(true);

                card.setStyle(
                                "-fx-background-color: " + CARD_SURFACE + ";" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 22;" +
                                                "-fx-background-radius: 22;");

                Label titleLabel = new Label(title);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                24));

                titleLabel.setTextFill(
                                Color.web(HEADING));

                card.getChildren().add(
                                titleLabel);

                return card;
        }

        private HBox createMechanicRow(
                        String initials,
                        String name,
                        String details,
                        String badge) {

                HBox row = new HBox(10);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setPrefHeight(50);
                row.setMinHeight(50);
                row.setMaxHeight(50);

                StackPane avatar = new StackPane();

                Circle circle = new Circle(22);

                circle.setFill(
                                Color.web(SECONDARY_SURFACE));

                Label initial = new Label(initials);

                initial.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                initial.setTextFill(
                                Color.web(NAV_BLUE));

                avatar.getChildren().addAll(
                                circle,
                                initial);

                VBox detailsBox = new VBox(1);

                Label nameLabel = new Label(name);

                nameLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                16));

                nameLabel.setTextFill(
                                Color.web(HEADING));

                Label detailsLabel = new Label(details);

                detailsLabel.setFont(
                                Font.font(13));

                detailsLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                detailsBox.getChildren().addAll(
                                nameLabel,
                                detailsLabel);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                row.getChildren().addAll(
                                avatar,
                                detailsBox,
                                spacer);

                if (!badge.isEmpty()) {

                        Label badgeLabel = new Label(badge);

                        badgeLabel.setPadding(
                                        new Insets(
                                                        4,
                                                        12,
                                                        4,
                                                        12));

                        badgeLabel.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.NORMAL,
                                                        12));

                        badgeLabel.setTextFill(
                                        Color.web(NAV_BLUE));

                        badgeLabel.setStyle(
                                        "-fx-background-color: " + SECONDARY_SURFACE + ";" +
                                                        "-fx-background-radius: 14;");

                        row.getChildren().add(
                                        badgeLabel);
                }

                return row;
        }

        // ============================================================
        // SCROLLBAR STYLE
        // ============================================================

        private void addScrollbarStyle(
                        Scene scene) {

                scene.getStylesheets().add(
                                "data:text/css," +
                                                ".scroll-bar:vertical {" +
                                                "-fx-background-color: #C4D9E0;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-padding: 2;" +
                                                "}" +

                                                ".scroll-bar:vertical .track {" +
                                                "-fx-background-color: #C4D9E0;" +
                                                "-fx-background-radius: 8;" +
                                                "}" +

                                                ".scroll-bar:vertical .thumb {" +
                                                "-fx-background-color: #526274;" +
                                                "-fx-background-radius: 8;" +
                                                "}" +

                                                ".scroll-bar:vertical .thumb:hover {" +
                                                "-fx-background-color: #172033;" +
                                                "-fx-background-radius: 8;" +
                                                "}" +

                                                ".scroll-bar:vertical .increment-button," +
                                                ".scroll-bar:vertical .decrement-button {" +
                                                "-fx-background-color: transparent;" +
                                                "-fx-padding: 0;" +
                                                "}" +

                                                ".scroll-bar:horizontal {" +
                                                "-fx-opacity: 0;" +
                                                "-fx-max-height: 0;" +
                                                "-fx-pref-height: 0;" +
                                                "}");
        }

        // ============================================================
        // MAIN
        // ============================================================

        public static void main(String[] args) {

                Application.launch(
                                UserDashboard.class,
                                args);
        }
}

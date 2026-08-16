package project.ui.user;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SOSPage {

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

        private Scene sosScene;

        private Runnable dashboardAction;

        public SOSPage() {
                this(null);
        }

        public SOSPage(Runnable dashboardAction) {
                this.dashboardAction = dashboardAction;
        }

        // ============================================================
        // PUBLIC SCENE
        // ============================================================

        public Scene getSOSScene() {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + MAIN_BACKGROUND + ";");

                HBox header = createHeader();
                ScrollPane sidebar = createSidebar();

                VBox content = createSOSContent();

                ScrollPane contentScroll = new ScrollPane(content);

                contentScroll.setFitToWidth(true);
                contentScroll.setFitToHeight(false);
                contentScroll.setPannable(true);

                contentScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                contentScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.ALWAYS);

                contentScroll.setStyle(
                                "-fx-background-color: " + MAIN_BACKGROUND + ";" +
                                                "-fx-border-color: transparent;");

                root.setTop(header);
                root.setLeft(sidebar);
                root.setCenter(contentScroll);

                sosScene = new Scene(
                                root,
                                1600,
                                900);

                addScrollbarStyle(sosScene);

                return sosScene;
        }

        // ============================================================
        // HEADER
        // ============================================================

        private HBox createHeader() {

                HBox header = new HBox(15);

                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(
                                new Insets(10, 28, 10, 28));
                header.setPrefHeight(76);

                header.setStyle(
                                "-fx-background-color: " + MAIN_BACKGROUND + ";" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1 0;");

                StackPane logo = new StackPane();

                Circle logoCircle = new Circle(21);
                logoCircle.setFill(Color.web(NAV_BLUE));

                Label shield = new Label("✓");
                shield.setTextFill(Color.WHITE);
                shield.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                logo.getChildren().addAll(
                                logoCircle,
                                shield);

                HBox brand = new HBox(0);
                brand.setAlignment(Pos.CENTER_LEFT);

                Label road = new Label("Road");
                road.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));
                road.setTextFill(Color.web(HEADING));

                Label guardian = new Label("Guardian");
                guardian.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));
                guardian.setTextFill(Color.web(NAV_BLUE));

                brand.getChildren().addAll(
                                road,
                                guardian);

                HBox logoSection = new HBox(
                                11,
                                logo,
                                brand);

                logoSection.setAlignment(Pos.CENTER_LEFT);

                Region spacer = new Region();
                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                HBox search = new HBox(9);

                search.setAlignment(Pos.CENTER_LEFT);
                search.setPrefWidth(420);
                search.setMaxWidth(420);
                search.setMinHeight(42);

                search.setPadding(
                                new Insets(0, 15, 0, 15));

                search.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 24;" +
                                                "-fx-background-radius: 24;");

                Label searchIcon = new Label("⌕");
                searchIcon.setFont(
                                Font.font("Arial", 24));
                searchIcon.setTextFill(
                                Color.web(SECONDARY_TEXT));

                Label searchText = new Label(
                                "Search mechanics, invoices, vehicles...");
                searchText.setFont(Font.font(15));
                searchText.setTextFill(
                                Color.web(SECONDARY_TEXT));

                search.getChildren().addAll(
                                searchIcon,
                                searchText);

                StackPane notification = new StackPane();

                Label bell = new Label("♧");
                bell.setFont(
                                Font.font("Arial", 22));
                bell.setTextFill(Color.web(HEADING));

                Circle dot = new Circle(
                                4,
                                Color.web(EMERGENCY));

                StackPane.setAlignment(
                                dot,
                                Pos.TOP_RIGHT);

                notification.getChildren().addAll(
                                bell,
                                dot);

                HBox profile = new HBox(9);
                profile.setAlignment(Pos.CENTER_LEFT);
                profile.setPadding(
                                new Insets(5, 12, 5, 6));

                profile.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 25;" +
                                                "-fx-background-radius: 25;");

                StackPane avatar = new StackPane();

                Circle avatarCircle = new Circle(19);

                avatarCircle.setFill(
                                Color.web(SECONDARY_SURFACE));

                Label initials = new Label("AN");
                initials.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
                initials.setTextFill(
                                Color.web(NAV_BLUE));

                avatar.getChildren().addAll(
                                avatarCircle,
                                initials);

                VBox profileText = new VBox(1);

                Label name = new Label("Aarav Nair");
                name.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
                name.setTextFill(
                                Color.web(HEADING));

                Label role = new Label("Customer · Pune");

                role.setFont(Font.font(11));
                role.setTextFill(
                                Color.web(SECONDARY_TEXT));

                profileText.getChildren().addAll(
                                name,
                                role);

                profile.getChildren().addAll(
                                avatar,
                                profileText);

                header.getChildren().addAll(
                                logoSection,
                                spacer,
                                search,
                                notification,
                                profile);

                return header;
        }

        // ============================================================
        // SIDEBAR
        // ============================================================

        private ScrollPane createSidebar() {

                VBox sidebarContent = new VBox(5);

                sidebarContent.setPrefWidth(270);
                sidebarContent.setMinWidth(270);

                sidebarContent.setPadding(
                                new Insets(
                                                20,
                                                12,
                                                30,
                                                12));

                sidebarContent.setStyle(
                                "-fx-background-color: " + SIDEBAR + ";");

                addSection(
                                sidebarContent,
                                "OVERVIEW");

                Button dashboardButton = createNavButton(
                                "▦",
                                "Dashboard",
                                false);
                dashboardButton.setOnAction(e -> {

                        Stage stage = (Stage) dashboardButton
                                        .getScene()
                                        .getWindow();

                        UserDashboard dashboard = new UserDashboard();

                        stage.setTitle(
                                        "RoadGuardian - Customer Dashboard");

                        stage.setScene(
                                        dashboard.getDashboardScene());

                });

                Button liveMapButton = createNavButton(
                                "◇",
                                "Live Map",
                                false);

                liveMapButton.setOnAction(e -> openLiveMapPage(liveMapButton));

                sidebarContent.getChildren().addAll(
                                dashboardButton,
                                createNavButton(
                                                "♧",
                                                "Emergency SOS",
                                                true),
                                liveMapButton);

                addSection(
                                sidebarContent,
                                "ASSISTANCE");

                sidebarContent.getChildren().addAll(
                                createNavButton(
                                                "♧",
                                                "AI Diagnosis",
                                                false),
                                createNavButton(
                                                "⚒",
                                                "Mechanics",
                                                false),
                                createNavButton(
                                                "▱",
                                                "Tow Truck",
                                                false),
                                createNavButton(
                                                "▤",
                                                "Cost Estimator",
                                                false));

                addSection(
                                sidebarContent,
                                "GARAGE");

                sidebarContent.getChildren().addAll(
                                createNavButton(
                                                "▱",
                                                "My Vehicles",
                                                false),
                                createNavButton(
                                                "◷",
                                                "Service History",
                                                false),
                                createNavButton(
                                                "▧",
                                                "Documents",
                                                false));

                addSection(
                                sidebarContent,
                                "ACCOUNT");

                sidebarContent.getChildren().addAll(
                                createNavButton(
                                                "♙",
                                                "Women Safety",
                                                false),
                                createNavButton(
                                                "♧",
                                                "Notifications",
                                                false),
                                createNavButton(
                                                "⚙",
                                                "Settings",
                                                false));

                ScrollPane scroll = new ScrollPane(sidebarContent);

                scroll.setPrefWidth(270);
                scroll.setMinWidth(270);
                scroll.setMaxWidth(270);

                scroll.setFitToWidth(true);

                scroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.ALWAYS);

                scroll.setPannable(true);

                scroll.setStyle(
                                "-fx-background-color: " + SIDEBAR + ";" +
                                                "-fx-border-color: transparent;");

                return scroll;
        }

        private void addSection(
                        VBox parent,
                        String text) {

                Label label = new Label(text);

                label.setPadding(
                                new Insets(
                                                11,
                                                12,
                                                7,
                                                12));

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                label.setTextFill(
                                Color.web(SECONDARY_TEXT));

                parent.getChildren().add(label);
        }

        private Button createNavButton(
                        String icon,
                        String text,
                        boolean selected) {

                Button button = new Button(
                                icon + "    " + text);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(44);

                button.setPadding(
                                new Insets(
                                                0,
                                                13,
                                                0,
                                                13));

                button.setFont(
                                Font.font(
                                                "Arial",
                                                selected
                                                                ? FontWeight.BOLD
                                                                : FontWeight.NORMAL,
                                                15));

                if (selected) {

                        button.setStyle(
                                        "-fx-background-color: " + NAV_BLUE + ";" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-background-radius: 24;");

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: " + HEADING + ";" +
                                                        "-fx-background-radius: 24;");

                        button.setOnMouseEntered(e -> button.setStyle(
                                        "-fx-background-color: " + NAV_BLUE + ";" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-background-radius: 24;"));

                        button.setOnMouseExited(e -> button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: " + HEADING + ";" +
                                                        "-fx-background-radius: 24;"));
                }

                return button;
        }

        // ============================================================
        // LIVE MAP NAVIGATION
        // ============================================================

        private void openLiveMapPage(Button sourceButton) {

                try {

                        LiveMapPage liveMapPage = new LiveMapPage();

                        Stage stage = (Stage) sourceButton
                                        .getScene()
                                        .getWindow();

                        stage.setTitle(
                                        "RoadGuardian - Live Tracking");

                        stage.setScene(
                                        liveMapPage.getLiveMapScene());

                } catch (Exception ex) {

                        ex.printStackTrace();
                }
        }

        // ============================================================
        // SOS CONTENT
        // ============================================================

        private VBox createSOSContent() {

                VBox content = new VBox(22);

                content.setPadding(
                                new Insets(
                                                28,
                                                28,
                                                40,
                                                28));

                content.setMinWidth(0);
                content.setMaxWidth(
                                Double.MAX_VALUE);

                content.setMinHeight(1000);

                content.setStyle(
                                "-fx-background-color: " +
                                                MAIN_BACKGROUND + ";");

                HBox titleRow = new HBox();

                titleRow.setAlignment(
                                Pos.CENTER_LEFT);

                VBox titleBox = new VBox(4);

                Label title = new Label("Emergency SOS");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                32));

                title.setTextFill(
                                Color.web(HEADING));

                Label subtitle = new Label(
                                "Get immediate roadside assistance and track your responder.");

                subtitle.setFont(Font.font(15));
                subtitle.setTextFill(
                                Color.web(SECONDARY_TEXT));

                titleBox.getChildren().addAll(
                                title,
                                subtitle);

                Region titleSpacer = new Region();

                HBox.setHgrow(
                                titleSpacer,
                                Priority.ALWAYS);

                Label active = new Label("●  SOS ACTIVE");

                active.setPadding(
                                new Insets(
                                                8,
                                                14,
                                                8,
                                                14));

                active.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                active.setTextFill(Color.WHITE);

                active.setStyle(
                                "-fx-background-color: " + EMERGENCY + ";" +
                                                "-fx-background-radius: 18;");

                titleRow.getChildren().addAll(
                                titleBox,
                                titleSpacer,
                                active);

                HBox topCards = new HBox(22);

                VBox sosCard = createSOSCard();

                VBox locationCard = createLocationCard();

                HBox.setHgrow(
                                sosCard,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                locationCard,
                                Priority.ALWAYS);

                // Use the full available dashboard width.
                // Do not use fixed widths here because they can push the
                // location card outside the visible dashboard area.
                sosCard.setMinWidth(0);
                sosCard.setMaxWidth(Double.MAX_VALUE);

                locationCard.setMinWidth(0);
                locationCard.setMaxWidth(Double.MAX_VALUE);

                topCards.setMaxWidth(Double.MAX_VALUE);

                topCards.getChildren().addAll(
                                sosCard,
                                locationCard);

                HBox bottomCards = new HBox(22);

                bottomCards.setMaxWidth(Double.MAX_VALUE);

                VBox timeline = createTimelineCard();

                VBox contacts = createContactsCard();

                VBox mechanic = createMechanicCard();

                HBox.setHgrow(
                                timeline,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                contacts,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                mechanic,
                                Priority.ALWAYS);

                timeline.setMinWidth(0);
                timeline.setMaxWidth(Double.MAX_VALUE);

                contacts.setMinWidth(0);
                contacts.setMaxWidth(Double.MAX_VALUE);

                mechanic.setMinWidth(0);
                mechanic.setMaxWidth(Double.MAX_VALUE);

                bottomCards.getChildren().addAll(
                                timeline,
                                contacts,
                                mechanic);

                content.getChildren().addAll(
                                titleRow,
                                topCards,
                                bottomCards);

                return content;
        }

        // ============================================================
        // SOS CARD
        // ============================================================

        private VBox createSOSCard() {

                VBox card = createCard();

                Label heading = new Label("Emergency assistance");

                heading.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                heading.setTextFill(
                                Color.web(HEADING));

                Label description = new Label(
                                "Your SOS request has been sent. " +
                                                "A nearby responder is on the way.");

                description.setWrapText(true);
                description.setFont(Font.font(14));
                description.setTextFill(
                                Color.web(SECONDARY_TEXT));

                StackPane sosCircle = new StackPane();

                Circle outer = new Circle(105);

                outer.setFill(
                                Color.web("#FDEBEC"));

                Circle inner = new Circle(82);

                inner.setFill(
                                Color.web(EMERGENCY));

                inner.setStroke(Color.WHITE);
                inner.setStrokeWidth(5);

                Label sosText = new Label("SOS");

                sosText.setTextFill(Color.WHITE);

                sosText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                30));

                sosCircle.getChildren().addAll(
                                outer,
                                inner,
                                sosText);

                StackPane.setMargin(
                                sosCircle,
                                new Insets(15, 0, 5, 0));

                Button call112 = new Button(
                                "☎   Call 112 directly");

                call112.setMaxWidth(
                                Double.MAX_VALUE);

                call112.setPrefHeight(48);

                call112.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                call112.setStyle(
                                "-fx-background-color: " + EMERGENCY + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-background-radius: 24;");

                call112.setOnMouseEntered(
                                e -> call112.setStyle(
                                                "-fx-background-color: " + ORANGE_HOVER + ";" +
                                                                "-fx-text-fill: white;" +
                                                                "-fx-background-radius: 24;"));

                call112.setOnMouseExited(
                                e -> call112.setStyle(
                                                "-fx-background-color: " + EMERGENCY + ";" +
                                                                "-fx-text-fill: white;" +
                                                                "-fx-background-radius: 24;"));

                Label info = new Label(
                                "Use 112 for immediate police, fire or medical emergency assistance.");

                info.setWrapText(true);
                info.setFont(Font.font(12));
                info.setTextFill(
                                Color.web(SECONDARY_TEXT));

                card.getChildren().addAll(
                                heading,
                                description,
                                sosCircle,
                                call112,
                                info);

                return card;
        }

        // ============================================================
        // LOCATION CARD
        // ============================================================

        private VBox createLocationCard() {

                VBox card = createCard();

                HBox heading = new HBox();

                Label title = new Label("Live location");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(HEADING));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label location = new Label("NH-48 · Exit 12B");

                location.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                location.setTextFill(
                                Color.web(NAV_BLUE));

                heading.getChildren().addAll(
                                title,
                                spacer,
                                location);

                StackPane map = createMap();

                map.setMinWidth(0);
                map.setMaxWidth(Double.MAX_VALUE);
                VBox.setVgrow(map, Priority.ALWAYS);

                HBox coordinates = new HBox(10);

                coordinates.setAlignment(
                                Pos.CENTER_LEFT);

                Label coordinateText = new Label(
                                "18.5204° N, 73.8567° E");

                coordinateText.setFont(Font.font(13));
                coordinateText.setTextFill(
                                Color.web(SECONDARY_TEXT));

                coordinates.getChildren().add(
                                coordinateText);

                card.getChildren().addAll(
                                heading,
                                map,
                                coordinates);

                return card;
        }

        private StackPane createMap() {

                StackPane map = new StackPane();

                map.setPrefHeight(340);
                map.setMinHeight(340);

                map.setStyle(
                                "-fx-background-color: #E7E7E4;" +
                                                "-fx-background-radius: 22;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 22;");

                // Clip every map element to the actual map/card bounds.
                // This prevents roads, route lines and markers from
                // visually going outside the Live Location card.
                Rectangle mapClip = new Rectangle();
                mapClip.setArcWidth(44);
                mapClip.setArcHeight(44);
                mapClip.widthProperty().bind(map.widthProperty());
                mapClip.heightProperty().bind(map.heightProperty());
                map.setClip(mapClip);

                Line road1 = new Line(
                                20,
                                260,
                                820,
                                45);

                road1.setStroke(Color.WHITE);
                road1.setStrokeWidth(13);

                Line road2 = new Line(
                                180,
                                10,
                                650,
                                335);

                road2.setStroke(Color.WHITE);
                road2.setStrokeWidth(10);

                Line road3 = new Line(
                                0,
                                120,
                                850,
                                210);

                road3.setStroke(Color.WHITE);
                road3.setStrokeWidth(9);

                Line road4 = new Line(
                                470,
                                0,
                                520,
                                340);

                road4.setStroke(Color.WHITE);
                road4.setStrokeWidth(7);

                Line route = new Line(
                                300,
                                205,
                                535,
                                130);

                route.setStroke(
                                Color.web(NAV_BLUE));

                route.setStrokeWidth(3);

                route.getStrokeDashArray().addAll(
                                7.0,
                                7.0);

                StackPane responder = createMarker(
                                "⚒",
                                SUCCESS);

                StackPane vehicle = createMarker(
                                "▰",
                                NAV_BLUE);

                StackPane.setAlignment(
                                responder,
                                Pos.CENTER);

                StackPane.setMargin(
                                responder,
                                new Insets(
                                                0,
                                                0,
                                                120,
                                                270));

                StackPane.setAlignment(
                                vehicle,
                                Pos.CENTER);

                StackPane.setMargin(
                                vehicle,
                                new Insets(
                                                120,
                                                0,
                                                0,
                                                520));

                Label responderLabel = createMapLabel(
                                "Arjun · 6 min");

                StackPane.setAlignment(
                                responderLabel,
                                Pos.CENTER);

                StackPane.setMargin(
                                responderLabel,
                                new Insets(
                                                50,
                                                0,
                                                0,
                                                270));

                Label youLabel = createMapLabel("You");

                StackPane.setAlignment(
                                youLabel,
                                Pos.CENTER);

                StackPane.setMargin(
                                youLabel,
                                new Insets(
                                                195,
                                                0,
                                                0,
                                                520));

                map.getChildren().addAll(
                                road1,
                                road2,
                                road3,
                                road4,
                                route,
                                responder,
                                vehicle,
                                responderLabel,
                                youLabel);

                return map;
        }

        private StackPane createMarker(
                        String icon,
                        String color) {

                StackPane marker = new StackPane();

                Circle circle = new Circle(23);

                circle.setFill(
                                Color.web(color));

                circle.setStroke(Color.WHITE);
                circle.setStrokeWidth(3);

                Label label = new Label(icon);

                label.setTextFill(Color.WHITE);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                marker.getChildren().addAll(
                                circle,
                                label);

                return marker;
        }

        private Label createMapLabel(
                        String text) {

                Label label = new Label(text);

                label.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: " + HEADING + ";" +
                                                "-fx-padding: 6 11;" +
                                                "-fx-background-radius: 14;" +
                                                "-fx-font-weight: bold;");

                return label;
        }

        // ============================================================
        // TIMELINE
        // ============================================================

        private VBox createTimelineCard() {

                VBox card = createCard();

                Label title = new Label("SOS status");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(HEADING));

                card.getChildren().add(title);

                card.getChildren().addAll(
                                createTimelineRow(
                                                "SOS request received",
                                                "7:18 PM",
                                                SUCCESS,
                                                true),
                                createTimelineRow(
                                                "Emergency contacts notified",
                                                "7:18 PM",
                                                SUCCESS,
                                                true),
                                createTimelineRow(
                                                "Mechanic assigned",
                                                "7:19 PM",
                                                SUCCESS,
                                                true),
                                createTimelineRow(
                                                "Responder en route",
                                                "7:20 PM",
                                                NAV_BLUE,
                                                true),
                                createTimelineRow(
                                                "Assistance completed",
                                                "Pending",
                                                BORDER,
                                                false));

                return card;
        }

        private HBox createTimelineRow(
                        String titleText,
                        String time,
                        String color,
                        boolean completed) {

                HBox row = new HBox(12);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                Circle dot = new Circle(
                                8,
                                Color.web(color));

                VBox textBox = new VBox(2);

                Label title = new Label(titleText);

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                title.setTextFill(
                                Color.web(HEADING));

                Label status = new Label(
                                completed
                                                ? "Completed"
                                                : "Waiting");

                status.setFont(Font.font(11));
                status.setTextFill(
                                Color.web(
                                                completed
                                                                ? SUCCESS
                                                                : SECONDARY_TEXT));

                textBox.getChildren().addAll(
                                title,
                                status);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label timeLabel = new Label(time);

                timeLabel.setFont(Font.font(12));
                timeLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                row.getChildren().addAll(
                                dot,
                                textBox,
                                spacer,
                                timeLabel);

                return row;
        }

        // ============================================================
        // CONTACTS
        // ============================================================

        private VBox createContactsCard() {

                VBox card = createCard();

                Label title = new Label("Emergency contacts");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(HEADING));

                card.getChildren().add(title);

                card.getChildren().addAll(
                                createContactRow(
                                                "PN",
                                                "Priya Nair",
                                                "Primary contact",
                                                "+91 98765 43210"),
                                createContactRow(
                                                "RN",
                                                "Rahul Nair",
                                                "Emergency contact",
                                                "+91 99887 66554"),
                                createContactRow(
                                                "112",
                                                "National Emergency",
                                                "Government emergency service",
                                                "112"));

                return card;
        }

        private HBox createContactRow(
                        String initials,
                        String name,
                        String role,
                        String phone) {

                HBox row = new HBox(10);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                StackPane avatar = new StackPane();

                Circle circle = new Circle(21);

                circle.setFill(
                                Color.web(SECONDARY_SURFACE));

                Label initial = new Label(initials);

                initial.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));

                initial.setTextFill(
                                Color.web(NAV_BLUE));

                avatar.getChildren().addAll(
                                circle,
                                initial);

                VBox details = new VBox(1);

                Label nameLabel = new Label(name);

                nameLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                nameLabel.setTextFill(
                                Color.web(HEADING));

                Label roleLabel = new Label(role);

                roleLabel.setFont(Font.font(11));
                roleLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                details.getChildren().addAll(
                                nameLabel,
                                roleLabel);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button call = new Button("Call");

                call.setPrefHeight(34);

                call.setPadding(
                                new Insets(0, 14, 0, 14));

                call.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                call.setStyle(
                                "-fx-background-color: " + SECONDARY_SURFACE + ";" +
                                                "-fx-text-fill: " + NAV_BLUE + ";" +
                                                "-fx-background-radius: 18;");

                row.getChildren().addAll(
                                avatar,
                                details,
                                spacer,
                                call);

                return row;
        }

        // ============================================================
        // MECHANIC CARD
        // ============================================================

        private VBox createMechanicCard() {

                VBox card = createCard();

                Label title = new Label("Assigned mechanic");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(HEADING));

                StackPane avatar = new StackPane();

                Circle circle = new Circle(34);

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

                Label name = new Label("Arjun Mehta");

                name.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                name.setTextFill(
                                Color.web(HEADING));

                Label rating = new Label("★ 4.9 · SpeedFix Auto Care");

                rating.setFont(Font.font(13));
                rating.setTextFill(
                                Color.web(SECONDARY_TEXT));

                Label eta = new Label(
                                "Estimated arrival · 6 minutes");

                eta.setFont(Font.font(13));
                eta.setTextFill(
                                Color.web(SUCCESS));

                Button call = new Button("☎   Call mechanic");

                call.setMaxWidth(
                                Double.MAX_VALUE);

                call.setPrefHeight(44);

                call.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                call.setStyle(
                                "-fx-background-color: " + NAV_BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-background-radius: 22;");

                card.getChildren().addAll(
                                title,
                                avatar,
                                name,
                                rating,
                                eta,
                                call);

                return card;
        }

        // ============================================================
        // COMMON CARD
        // ============================================================

        private VBox createCard() {

                VBox card = new VBox(13);

                card.setPadding(
                                new Insets(22));

                card.setMinWidth(0);
                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setMinHeight(300);

                card.setStyle(
                                "-fx-background-color: " + CARD_SURFACE + ";" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 22;" +
                                                "-fx-background-radius: 22;");

                return card;
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
}

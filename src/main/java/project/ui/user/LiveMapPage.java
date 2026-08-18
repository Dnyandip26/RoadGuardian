package project.ui.user;

import project.app.AppNavigator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import project.ui.user.UserHeader;
import javafx.stage.Stage;

public class LiveMapPage {

        // ============================================================
        // COLORS
        // ============================================================

        private static final String MAIN_BACKGROUND = "#D6F0F3";
        private static final String SIDEBAR = "#C7DDE4";
        private static final String CARD_SURFACE = "#EEF9FA";
        private static final String HEADER_BACKGROUND = "#D6F0F3";
        private static final String SECONDARY_SURFACE = "#DFF3F5";

        private static final String ORANGE = "#F97316";
        private static final String ORANGE_HOVER = "#EA580C";

        private static final String HEADING = "#172033";
        private static final String SECONDARY_TEXT = "#526274";

        private static final String NAV_BLUE = "#2563EB";
        private static final String SUCCESS = "#16A34A";
        private static final String EMERGENCY = "#DC2626";

        private static final String BORDER = "#B7D5DB";

        private Scene liveMapScene;

        // ============================================================
        // CONSTRUCTOR
        // ============================================================

        public LiveMapPage() {
        }

        // ============================================================
        // GET SCENE
        // ============================================================

        public Scene getLiveMapScene() {

                BorderPane root = new BorderPane();

                root.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(MAIN_BACKGROUND),
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                root.setStyle(
                                "-fx-background-color: " +
                                                MAIN_BACKGROUND + ";");

                // --------------------------------------------------------
                // HEADER
                // --------------------------------------------------------

                HBox header = UserHeader.createHeader();

                // --------------------------------------------------------
                // SIDEBAR
                // --------------------------------------------------------

                ScrollPane sidebar = UserSideBar.createSidebar("Live Map");

                // --------------------------------------------------------
                // CONTENT
                // --------------------------------------------------------

                VBox content = createLiveMapContent();

                ScrollPane contentScroll = new ScrollPane(content);

                contentScroll.setFitToWidth(true);
                contentScroll.setFitToHeight(true);

                contentScroll.setPannable(true);

                contentScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                contentScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                contentScroll.setStyle(
                                "-fx-background-color: " + MAIN_BACKGROUND + ";" +
                                                "-fx-background: " + MAIN_BACKGROUND + ";" +
                                                "-fx-control-inner-background: " + MAIN_BACKGROUND + ";" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-padding: 0;");

                root.setTop(header);
                root.setLeft(sidebar);
                root.setCenter(contentScroll);

                liveMapScene = new Scene(root, UserDashboard.dashboardStage.getWidth(),
                                UserDashboard.dashboardStage.getHeight());

                addScrollbarStyle(liveMapScene);

                return liveMapScene;
        }

        
        // ============================================================
        // LIVE MAP CONTENT
        // ============================================================

        private VBox createLiveMapContent() {

                VBox content = new VBox(20);

                content.setPadding(new Insets(24, 24, 28, 24));
                content.setFillWidth(true);
                content.setMinWidth(0);
                content.setMaxWidth(Double.MAX_VALUE);

                content.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(MAIN_BACKGROUND),
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                content.setStyle(
                                "-fx-background-color: " +
                                                MAIN_BACKGROUND + ";");

                // --------------------------------------------------------
                // PAGE TITLE
                // --------------------------------------------------------

                VBox titleBox = new VBox(4);

                Label title = new Label("Live Tracking");
                title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
                title.setTextFill(Color.web(HEADING));

                Label subtitle = new Label(
                                "Arjun Mehta is en route · updated 4 seconds ago");
                subtitle.setFont(Font.font("Arial", 15));
                subtitle.setTextFill(Color.web(SECONDARY_TEXT));

                titleBox.getChildren().addAll(title, subtitle);

                // --------------------------------------------------------
                // MAIN ROW
                // --------------------------------------------------------

                HBox mainRow = new HBox(20);

                mainRow.setFillHeight(true);
                mainRow.setMinWidth(0);
                mainRow.setMaxWidth(Double.MAX_VALUE);
                mainRow.setPrefHeight(555);
                mainRow.setMinHeight(555);
                mainRow.setMaxHeight(555);

                VBox mapCard = createMapCard();

                VBox infoColumn = new VBox(20);
                infoColumn.setMinWidth(300);
                infoColumn.setPrefWidth(310);
                infoColumn.setMaxWidth(310);

                VBox mechanicCard = createTrackingInfoCard();
                VBox legendCard = createLegendCard();

                infoColumn.getChildren().addAll(
                                mechanicCard,
                                legendCard);

                HBox.setHgrow(mapCard, Priority.ALWAYS);

                mainRow.getChildren().addAll(
                                mapCard,
                                infoColumn);

                content.getChildren().addAll(
                                titleBox,
                                mainRow);

                return content;
        }

        // ============================================================
        // MAP CARD
        // ============================================================

        private VBox createMapCard() {

                VBox card = new VBox();

                card.setPadding(new Insets(10));
                card.setFillWidth(true);
                card.setMinWidth(0);
                card.setPrefHeight(555);
                card.setMinHeight(555);
                card.setMaxHeight(555);
                card.setMaxWidth(Double.MAX_VALUE);

                card.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(CARD_SURFACE),
                                                                new CornerRadii(24),
                                                                Insets.EMPTY)));

                card.setStyle(
                                "-fx-background-color: " + CARD_SURFACE + ";" +
                                                "-fx-background-radius: 24;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 24;" +
                                                "-fx-border-width: 1;");

                StackPane map = createMap();

                map.setMaxWidth(Double.MAX_VALUE);
                map.setMaxHeight(Double.MAX_VALUE);

                VBox.setVgrow(map, Priority.ALWAYS);

                card.getChildren().add(map);

                return card;
        }

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
        // MAP
        // ============================================================

        // ============================================================
        // SIMPLE MAP PLACEHOLDER
        // ============================================================

        private StackPane createMap() {

                StackPane map = new StackPane();

                map.setPrefHeight(535);
                map.setMinHeight(535);
                map.setMaxHeight(Double.MAX_VALUE);
                map.setMinWidth(0);
                map.setMaxWidth(Double.MAX_VALUE);

                // ========================================================
                // MAP BACKGROUND
                // ========================================================

                Rectangle background = new Rectangle();

                background.setFill(
                                Color.web("#E7E7E4"));

                background.setArcWidth(44);
                background.setArcHeight(44);

                background.widthProperty().bind(
                                map.widthProperty());

                background.heightProperty().bind(
                                map.heightProperty());

                // ========================================================
                // MAP LAYER
                // Pane is used here so every road / marker has a
                // predictable map position.
                // ========================================================

                Pane mapLayer = new Pane();

                mapLayer.setPickOnBounds(false);

                mapLayer.prefWidthProperty().bind(
                                map.widthProperty());

                mapLayer.prefHeightProperty().bind(
                                map.heightProperty());

                // Keep every road, river and marker strictly inside
                // the map box. Nothing can overflow into the right panel.
                Rectangle mapLayerClip = new Rectangle();

                mapLayerClip.setArcWidth(44);
                mapLayerClip.setArcHeight(44);

                mapLayerClip.widthProperty().bind(
                                map.widthProperty());

                mapLayerClip.heightProperty().bind(
                                map.heightProperty());

                mapLayer.setClip(mapLayerClip);

                // ========================================================
                // CITY BLOCKS
                // ========================================================

                Rectangle block1 = mapBlock(170, 75);
                block1.setLayoutX(55);
                block1.setLayoutY(55);

                Rectangle block2 = mapBlock(145, 90);
                block2.setLayoutX(275);
                block2.setLayoutY(35);

                Rectangle block3 = mapBlock(170, 85);
                block3.setLayoutX(525);
                block3.setLayoutY(55);

                Rectangle block4 = mapBlock(130, 75);
                block4.setLayoutX(80);
                block4.setLayoutY(275);

                Rectangle block5 = mapBlock(180, 90);
                block5.setLayoutX(350);
                block5.setLayoutY(245);

                Rectangle block6 = mapBlock(150, 80);
                block6.setLayoutX(610);
                block6.setLayoutY(285);

                Rectangle block7 = mapBlock(125, 65);
                block7.setLayoutX(165);
                block7.setLayoutY(405);

                Rectangle block8 = mapBlock(165, 70);
                block8.setLayoutX(470);
                block8.setLayoutY(410);

                mapLayer.getChildren().addAll(
                                block1,
                                block2,
                                block3,
                                block4,
                                block5,
                                block6,
                                block7,
                                block8);

                // ========================================================
                // MAIN ROADS
                // ========================================================

                Line road1 = road(0, 175, 900, 120, 13);
                Line road2 = road(40, 365, 900, 270, 14);
                Line road3 = road(175, 0, 265, 535, 11);
                Line road4 = road(470, 0, 500, 535, 12);
                Line road5 = road(720, 0, 790, 535, 10);
                Line road6 = road(0, 485, 900, 350, 12);

                mapLayer.getChildren().addAll(
                                road1,
                                road2,
                                road3,
                                road4,
                                road5,
                                road6);

                // ========================================================
                // SMALL ROADS
                // ========================================================

                Line small1 = road(45, 90, 300, 170, 5);
                Line small2 = road(300, 105, 450, 185, 5);
                Line small3 = road(550, 145, 820, 215, 5);
                Line small4 = road(90, 335, 260, 425, 5);
                Line small5 = road(540, 310, 760, 400, 5);
                Line small6 = road(330, 420, 450, 500, 5);

                mapLayer.getChildren().addAll(
                                small1,
                                small2,
                                small3,
                                small4,
                                small5,
                                small6);

                // ========================================================
                // BLUE RIVER / HIGHWAY
                // ========================================================

                Line river1 = new Line(
                                0, 505,
                                155, 465);

                Line river2 = new Line(
                                155, 465,
                                300, 485);

                Line river3 = new Line(
                                300, 485,
                                455, 455);

                Line river4 = new Line(
                                455, 455,
                                610, 470);

                Line river5 = new Line(
                                610, 470,
                                770, 425);

                Line river6 = new Line(
                                770, 425,
                                885, 430);

                for (Line river : new Line[] {
                                river1,
                                river2,
                                river3,
                                river4,
                                river5,
                                river6
                }) {
                        river.setStroke(
                                        Color.web("#72BCE5"));
                        river.setStrokeWidth(8);
                }

                mapLayer.getChildren().addAll(
                                river1,
                                river2,
                                river3,
                                river4,
                                river5,
                                river6);

                // ========================================================
                // ROUTE
                // ========================================================

                Line route = new Line(
                                420, 275,
                                545, 365);

                route.setStroke(
                                Color.web(NAV_BLUE));

                route.setStrokeWidth(3);

                route.getStrokeDashArray().addAll(
                                7.0,
                                7.0);

                mapLayer.getChildren().add(route);

                // ========================================================
                // ARRIVING BADGE
                // ========================================================

                VBox arriving = new VBox(0);

                arriving.setAlignment(
                                Pos.CENTER_LEFT);

                arriving.setPadding(
                                new Insets(
                                                9,
                                                17,
                                                9,
                                                17));

                arriving.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 22;" +
                                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");

                Label arrivingTitle = new Label("ARRIVING IN");

                arrivingTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));

                arrivingTitle.setTextFill(
                                Color.web(SECONDARY_TEXT));

                Label arrivingTime = new Label("6 min");

                arrivingTime.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                23));

                arrivingTime.setTextFill(
                                Color.web(HEADING));

                arriving.getChildren().addAll(
                                arrivingTitle,
                                arrivingTime);

                arriving.setLayoutX(18);
                arriving.setLayoutY(18);

                // ========================================================
                // YOU MARKER
                // ========================================================

                StackPane you = createMarker(
                                NAV_BLUE,
                                "▰");

                you.setLayoutX(515);
                you.setLayoutY(330);

                Label youLabel = markerLabel("You");

                youLabel.setLayoutX(518);
                youLabel.setLayoutY(378);

                // ========================================================
                // MAIN MECHANIC
                // ========================================================

                StackPane arjun = createMarker(
                                SUCCESS,
                                "⚒");

                arjun.setLayoutX(360);
                arjun.setLayoutY(205);

                Label arjunLabel = markerLabel(
                                "Arjun · 6 min");

                arjunLabel.setLayoutX(330);
                arjunLabel.setLayoutY(252);

                // ========================================================
                // OTHER MECHANICS
                // ========================================================

                StackPane mechanic2 = createMarker(
                                SUCCESS,
                                "⚒");

                mechanic2.setLayoutX(675);
                mechanic2.setLayoutY(125);

                StackPane mechanic3 = createMarker(
                                SUCCESS,
                                "⚒");

                mechanic3.setLayoutX(190);
                mechanic3.setLayoutY(350);

                StackPane mechanic4 = createMarker(
                                SUCCESS,
                                "⚒");

                mechanic4.setLayoutX(755);
                mechanic4.setLayoutY(330);

                // ========================================================
                // TOW TRUCKS
                // ========================================================

                StackPane tow1 = createMarker(
                                ORANGE,
                                "▣");

                tow1.setLayoutX(430);
                tow1.setLayoutY(75);

                StackPane tow2 = createMarker(
                                ORANGE,
                                "▣");

                tow2.setLayoutX(715);
                tow2.setLayoutY(260);

                StackPane tow3 = createMarker(
                                ORANGE,
                                "▣");

                tow3.setLayoutX(105);
                tow3.setLayoutY(205);

                // ========================================================
                // LIVE GPS BADGE
                // ========================================================

                HBox gps = new HBox(8);

                gps.setAlignment(
                                Pos.CENTER_LEFT);

                gps.setPadding(
                                new Insets(
                                                8,
                                                14,
                                                8,
                                                14));

                gps.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.07), 6, 0, 0, 1);");

                Circle gpsDot = new Circle(
                                5,
                                Color.web(SUCCESS));

                Label gpsText = new Label(
                                "Live GPS · 3.1 km remaining");

                gpsText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                gpsText.setTextFill(
                                Color.web(HEADING));

                gps.getChildren().addAll(
                                gpsDot,
                                gpsText);

                gps.setLayoutX(18);
                gps.setLayoutY(475);

                // ========================================================
                // ADD FOREGROUND ELEMENTS
                // ========================================================

                mapLayer.getChildren().addAll(
                                arriving,

                                you,
                                youLabel,

                                arjun,
                                arjunLabel,

                                mechanic2,
                                mechanic3,
                                mechanic4,

                                tow1,
                                tow2,
                                tow3,

                                gps);

                map.getChildren().addAll(
                                background,
                                mapLayer);

                return map;
        }

        private Rectangle mapBlock(
                        double width,
                        double height) {

                Rectangle block = new Rectangle(
                                width,
                                height);

                block.setFill(
                                Color.web("#DEDEDB"));

                block.setArcWidth(8);
                block.setArcHeight(8);

                return block;
        }

        // ============================================================
        // ROAD
        // ============================================================

        private Line road(
                        double startX,
                        double startY,
                        double endX,
                        double endY,
                        double width) {

                Line line = new Line(
                                startX,
                                startY,
                                endX,
                                endY);

                line.setStroke(
                                Color.WHITE);

                line.setStrokeWidth(
                                width);

                return line;
        }

        // ============================================================
        // MARKER
        // ============================================================

        private StackPane createMarker(
                        String color,
                        String icon) {

                StackPane marker = new StackPane();

                Circle outer = new Circle(
                                28,
                                Color.WHITE);

                Circle inner = new Circle(
                                22,
                                Color.web(color));

                Label label = new Label(icon);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                label.setTextFill(
                                Color.WHITE);

                marker.getChildren().addAll(
                                outer,
                                inner,
                                label);

                return marker;
        }

        // ============================================================
        // MARKER LABEL
        // ============================================================

        private Label markerLabel(
                        String text) {

                Label label = new Label(text);

                label.setPadding(
                                new Insets(
                                                6,
                                                11,
                                                6,
                                                11));

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                label.setTextFill(
                                Color.web(HEADING));

                label.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 15;");

                return label;
        }

        // ============================================================
        // TRACKING INFO CARD
        // ============================================================

        private VBox createTrackingInfoCard() {

                VBox card = createCard();

                card.setPadding(
                                new Insets(22));

                Label status = new Label(
                                "En route");

                status.setPadding(
                                new Insets(
                                                5,
                                                12,
                                                5,
                                                12));

                status.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                status.setTextFill(
                                Color.web(SUCCESS));

                status.setStyle(
                                "-fx-background-color: " +
                                                SECONDARY_SURFACE + ";" +
                                                "-fx-background-radius: 18;");

                HBox mechanic = new HBox(12);

                mechanic.setAlignment(
                                Pos.CENTER_LEFT);

                Circle avatar = new Circle(
                                24,
                                Color.web("#E1ECFF"));

                Label initials = new Label("AM");

                initials.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                initials.setTextFill(
                                Color.web(NAV_BLUE));

                StackPane avatarPane = new StackPane(
                                avatar,
                                initials);

                VBox mechanicText = new VBox(2);

                Label name = new Label(
                                "Arjun Mehta");

                name.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                name.setTextFill(
                                Color.web(HEADING));

                Label shop = new Label(
                                "SpeedFix Auto Care · 4.9 ★");

                shop.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                shop.setTextFill(
                                Color.web(SECONDARY_TEXT));

                mechanicText.getChildren().addAll(
                                name,
                                shop);

                mechanic.getChildren().addAll(
                                avatarPane,
                                mechanicText);

                VBox details = new VBox(10);

                details.getChildren().addAll(
                                infoRow(
                                                "◷",
                                                "ETA",
                                                "6 minutes"),
                                infoRow(
                                                "⌘",
                                                "Distance left",
                                                "3.1 km"),
                                infoRow(
                                                "➤",
                                                "Current road",
                                                "Baner Road,\nnorthbound"));

                Button call = new Button(
                                "☎   Call mechanic");

                call.setMaxWidth(
                                Double.MAX_VALUE);

                call.setPrefHeight(42);

                call.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                call.setTextFill(
                                Color.WHITE);

                call.setStyle(
                                "-fx-background-color: " +
                                                NAV_BLUE + ";" +
                                                "-fx-background-radius: 22;");

                call.setOnMouseEntered(
                                e -> call.setStyle(
                                                "-fx-background-color: #1D4ED8;" +
                                                                "-fx-background-radius: 22;"));

                call.setOnMouseExited(
                                e -> call.setStyle(
                                                "-fx-background-color: " +
                                                                NAV_BLUE + ";" +
                                                                "-fx-background-radius: 22;"));

                Button share = new Button(
                                "Share trip");

                share.setMaxWidth(
                                Double.MAX_VALUE);

                share.setPrefHeight(42);

                share.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                share.setTextFill(
                                Color.web(HEADING));

                share.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-radius: 22;" +
                                                "-fx-background-radius: 22;");

                card.getChildren().addAll(
                                status,
                                mechanic,
                                details,
                                call,
                                share);

                return card;
        }

        // ============================================================
        // INFO ROW
        // ============================================================

        private HBox infoRow(
                        String icon,
                        String title,
                        String value) {

                HBox row = new HBox(10);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setPadding(
                                new Insets(
                                                10,
                                                12,
                                                10,
                                                12));

                row.setStyle(
                                "-fx-background-color: " +
                                                SECONDARY_SURFACE + ";" +
                                                "-fx-background-radius: 18;");

                Label iconLabel = new Label(icon);

                iconLabel.setFont(
                                Font.font(
                                                "Arial",
                                                18));

                iconLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                Label titleLabel = new Label(title);

                titleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                titleLabel.setTextFill(
                                Color.web(SECONDARY_TEXT));

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label valueLabel = new Label(value);

                valueLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                valueLabel.setTextFill(
                                Color.web(HEADING));

                row.getChildren().addAll(
                                iconLabel,
                                titleLabel,
                                spacer,
                                valueLabel);

                return row;
        }

        // ============================================================
        // LEGEND
        // ============================================================

        private VBox createLegendCard() {

                VBox card = createCard();

                card.setPadding(
                                new Insets(22));

                Label title = new Label(
                                "LEGEND");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                title.setTextFill(
                                Color.web(SECONDARY_TEXT));

                VBox items = new VBox(12);

                items.getChildren().addAll(
                                legendItem(
                                                NAV_BLUE,
                                                "You"),
                                legendItem(
                                                "#16B982",
                                                "Mechanics"),
                                legendItem(
                                                ORANGE,
                                                "Tow trucks"),
                                legendItem(
                                                EMERGENCY,
                                                "Police / emergency"));

                card.getChildren().addAll(
                                title,
                                items);

                return card;
        }

        // ============================================================
        // LEGEND ITEM
        // ============================================================

        private HBox legendItem(
                        String color,
                        String text) {

                HBox row = new HBox(10);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                Circle dot = new Circle(
                                6,
                                Color.web(color));

                Label label = new Label(text);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                label.setTextFill(
                                Color.web(HEADING));

                row.getChildren().addAll(
                                dot,
                                label);

                return row;
        }

        // ============================================================
        // GENERIC CARD
        // ============================================================

        private VBox createCard() {

                VBox card = new VBox(16);

                card.setMinWidth(0);

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(CARD_SURFACE),
                                                                new CornerRadii(22),
                                                                Insets.EMPTY)));

                card.setStyle(
                                "-fx-background-color: " +
                                                CARD_SURFACE + ";" +
                                                "-fx-border-color: " +
                                                BORDER + ";" +
                                                "-fx-border-radius: 22;" +
                                                "-fx-background-radius: 22;");

                return card;
        }

        // ============================================================
        // MAIN TEST
        // ============================================================

        public static void main(
                        String[] args) {

                javafx.application.Application.launch(
                                LiveMapApplication.class,
                                args);
        }

        // ============================================================
        // TEST APPLICATION
        // ============================================================

        public static class LiveMapApplication
                        extends javafx.application.Application {

                @Override
                public void start(
                                Stage stage) {

                        LiveMapPage page = new LiveMapPage();

                        stage.setTitle(
                                        "RoadGuardian - Live Tracking");

                        stage.setMinWidth(1000);
                        stage.setMinHeight(650);

                        stage.setScene(
                                        page.getLiveMapScene());

                        stage.show();
                }
        }
}

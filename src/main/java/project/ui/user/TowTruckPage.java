package project.ui.user;

import project.app.AppNavigator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
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
import javafx.scene.Cursor;
import javafx.scene.shape.Rectangle;
import project.ui.user.UserHeader;
import javafx.stage.Stage;

public class TowTruckPage {

        private static final String BG = "#D9F3F5";
        private static final String WHITE = "#F8FCFD";
        private static final String CARD = "#F5FBFC";
        private static final String BLUE = "#2864E8";
        private static final String DARK = "#142033";
        private static final String GREY = "#667085";
        private static final String BORDER = "#C8E0E5";
        private static final String ORANGE = "#FF7A18";

        private Scene scene;

        public TowTruckPage() {
                buildUI();
        }

        public Scene getTowTruckScene() {
                return scene;
        }

        private void buildUI() {

                // ================= HEADER =================

                HBox header = UserHeader.createHeader();
                // ================= SIDEBAR =================

                ScrollPane sidebar = UserSideBar.createSidebar("Tow Truck");

                // ================= CONTENT =================

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(24, 26, 30, 26));

                content.setFillWidth(true);

                content.setBackground(
                                bg(BG, CornerRadii.EMPTY));

                Label title = new Label("Tow Truck Assistance");
                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                34));
                title.setTextFill(Color.web(DARK));

                Label subtitle = new Label(
                                "Flatbed and lift recovery available around you right now");
                subtitle.setFont(Font.font("Arial", 16));
                subtitle.setTextFill(Color.web(GREY));

                VBox heading = new VBox(
                                4,
                                title,
                                subtitle);

                // ================= MAIN COLUMNS =================
                HBox main = new HBox(30);

                main.setMaxWidth(
                                Double.MAX_VALUE);

                main.setAlignment(
                                Pos.TOP_LEFT);

                main.setFillHeight(true);

                // =========================================================
                // LEFT COLUMN
                // =========================================================

                VBox leftColumn = new VBox(18);

                leftColumn.setPrefWidth(850);
                leftColumn.setMinWidth(850);
                leftColumn.setMaxWidth(850);

                // =========================================================
                // MAP
                // =========================================================

                VBox mapBox = createMapBox();

                mapBox.setPrefWidth(850);
                mapBox.setMinWidth(850);
                mapBox.setMaxWidth(850);

                // =========================================================
                // DESTINATION
                // =========================================================

                VBox destinationBox = createDestinationBox();

                destinationBox.setPrefWidth(850);
                destinationBox.setMinWidth(850);
                destinationBox.setMaxWidth(850);

                leftColumn.getChildren().addAll(
                                mapBox,
                                destinationBox);

                // =========================================================
                // TOW TRUCK CARDS
                // =========================================================

                VBox truckCards = new VBox(14);

                truckCards.setPrefWidth(370);
                truckCards.setMinWidth(370);
                truckCards.setMaxWidth(370);

                truckCards.setFillWidth(true);

                // =========================================================
                // CARDS
                // =========================================================

                truckCards.getChildren().add(
                                createTruckCard(
                                                "FlatBed 4.5T",
                                                "Metro Tow Services",
                                                "9 min",
                                                "2 km",
                                                "₹1450",
                                                "4.8",
                                                "Sedan / Hatchback"));

                truckCards.getChildren().add(
                                createTruckCard(
                                                "Hydraulic Lift 7T",
                                                "RapidLift Recovery",
                                                "15 min",
                                                "3.8 km",
                                                "₹2100",
                                                "4.6",
                                                "SUV / MUV"));

                truckCards.getChildren().add(
                                createTruckCard(
                                                "Wheel Lift 3T",
                                                "CityGuard Towing",
                                                "22 min",
                                                "6.2 km",
                                                "₹1750",
                                                "4.4",
                                                "Sedan / Hatchback"));

                truckCards.getChildren().add(
                                createTruckCard(
                                                "Heavy Recovery 10T",
                                                "Highway Rescue",
                                                "28 min",
                                                "8.5 km",
                                                "₹2800",
                                                "4.3",
                                                "SUV / Truck"));

                // =========================================================
                // MAIN
                // =========================================================

                main.getChildren().addAll(
                                leftColumn,
                                truckCards);

                content.getChildren().addAll(
                                heading,
                                main);

                // =========================================================
                // DASHBOARD SCROLL
                // Header stays fixed. Main dashboard content scrolls.
                // =========================================================

                ScrollPane dashboardScroll = new ScrollPane(content);

                dashboardScroll.setFitToWidth(true);

                dashboardScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                dashboardScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.ALWAYS);

                dashboardScroll.setStyle(
                                "-fx-background-color: " + BG + ";" +
                                                "-fx-background: " + BG + ";" +
                                                "-fx-control-inner-background: " + BG + ";" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-padding: 0;");

                HBox.setHgrow(
                                dashboardScroll,
                                Priority.ALWAYS);

                // =========================================================
                // BODY
                // =========================================================

                HBox body = new HBox();

                body.setFillHeight(true);

                body.setBackground(
                                bg(BG, CornerRadii.EMPTY));

                body.getChildren().addAll(
                                sidebar,
                                dashboardScroll);

                HBox.setHgrow(
                                dashboardScroll,
                                Priority.ALWAYS);

                VBox.setVgrow(
                                body,
                                Priority.ALWAYS);

                // =========================================================
                // ROOT
                // =========================================================

                VBox root = new VBox(
                                header,
                                body);

                VBox.setVgrow(
                                body,
                                Priority.ALWAYS);

                scene = new Scene(root, UserDashboard.dashboardStage.getWidth(),
                                UserDashboard.dashboardStage.getHeight());
        }

        // ================= MAP =================

        private VBox createMapBox() {

                VBox box = new VBox();

                box.setPrefHeight(430);
                box.setMinHeight(400);
                box.setMaxHeight(430);

                box.setPrefWidth(850);
                box.setMinWidth(850);
                box.setMaxWidth(850);
                box.setPadding(
                                new Insets(0));
                StackPane map = new StackPane();

                map.setPrefWidth(850);
                map.setMinWidth(850);
                map.setMaxWidth(850);

                map.setMaxHeight(
                                Double.MAX_VALUE);
                // ================= MAP CLIP =================

                Rectangle mapClip = new Rectangle();

                mapClip.widthProperty().bind(
                                map.widthProperty());

                mapClip.heightProperty().bind(
                                map.heightProperty());

                mapClip.setArcWidth(50);
                mapClip.setArcHeight(50);

                map.setClip(mapClip);

                VBox.setVgrow(
                                map,
                                Priority.ALWAYS);

                map.setBackground(
                                bg("#E8E8E6", new CornerRadii(25)));

                map.setBorder(
                                border("#D0D9DB", 25, 1));

                Line road1 = new Line(
                                -100, 300,
                                850, 60);
                road1.setStroke(Color.WHITE);
                road1.setStrokeWidth(11);

                Line road2 = new Line(
                                40, 40,
                                800, 360);
                road2.setStroke(Color.WHITE);
                road2.setStrokeWidth(10);

                Line road3 = new Line(
                                330, -30,
                                440, 430);
                road3.setStroke(Color.WHITE);
                road3.setStrokeWidth(8);

                Line blueRoad = new Line(
                                250, 370,
                                760, 310);
                blueRoad.setStroke(
                                Color.web("#69B7E8"));
                blueRoad.setStrokeWidth(5);

                Line route = new Line(
                                350, 205,
                                520, 120);
                route.setStroke(Color.web(BLUE));
                route.setStrokeWidth(3);
                route.getStrokeDashArray().addAll(
                                8.0,
                                6.0);

                StackPane roads = new StackPane(
                                road1,
                                road2,
                                road3,
                                blueRoad,
                                route);

                // YOUR VEHICLE

                Circle vehicleCircle = new Circle(
                                22,
                                Color.web(BLUE));

                Label vehicleIcon = new Label("▰");
                vehicleIcon.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));
                vehicleIcon.setTextFill(Color.WHITE);

                StackPane vehicleMarker = new StackPane(
                                vehicleCircle,
                                vehicleIcon);

                Label vehicleText = new Label(
                                "Your vehicle");
                vehicleText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
                vehicleText.setTextFill(
                                Color.web(DARK));
                vehicleText.setPadding(
                                new Insets(7, 12, 7, 12));
                vehicleText.setBackground(
                                bg(WHITE, new CornerRadii(15)));

                VBox vehicle = new VBox(
                                4,
                                vehicleMarker,
                                vehicleText);
                vehicle.setAlignment(Pos.CENTER);

                StackPane.setAlignment(
                                vehicle,
                                Pos.CENTER);

                StackPane.setMargin(
                                vehicle,
                                new Insets(70, 0, 0, 0));

                // TOW TRUCK MARKERS

                StackPane truck1 = truckMarker();
                StackPane truck2 = truckMarker();
                StackPane truck3 = truckMarker();

                StackPane.setAlignment(
                                truck1,
                                Pos.TOP_CENTER);
                StackPane.setMargin(
                                truck1,
                                new Insets(55, 0, 0, 0));

                StackPane.setAlignment(
                                truck2,
                                Pos.CENTER_LEFT);
                StackPane.setMargin(
                                truck2,
                                new Insets(0, 155, 0, 0));

                StackPane.setAlignment(
                                truck3,
                                Pos.CENTER_RIGHT);
                StackPane.setMargin(
                                truck3,
                                new Insets(0, 205, 0, 0));

                Label time1 = mapLabel("9 min");
                Label time2 = mapLabel("22 min");
                Label time3 = mapLabel("15 min");

                StackPane.setAlignment(
                                time1,
                                Pos.TOP_CENTER);
                StackPane.setMargin(
                                time1,
                                new Insets(105, 0, 0, 0));

                StackPane.setAlignment(
                                time2,
                                Pos.CENTER_LEFT);
                StackPane.setMargin(
                                time2,
                                new Insets(80, 0, 0, 140));

                StackPane.setAlignment(
                                time3,
                                Pos.CENTER_RIGHT);
                StackPane.setMargin(
                                time3,
                                new Insets(80, 165, 0, 0));

                map.getChildren().addAll(
                                roads,
                                vehicle,
                                truck1,
                                truck2,
                                truck3,
                                time1,
                                time2,
                                time3);

                VBox wrapper = new VBox(map);
                VBox.setVgrow(map, Priority.ALWAYS);

                return wrapper;
        }

        private StackPane truckMarker() {

                Circle outer = new Circle(27);
                outer.setFill(Color.TRANSPARENT);
                outer.setStroke(Color.WHITE);
                outer.setStrokeWidth(4);

                Circle inner = new Circle(
                                22,
                                Color.web(ORANGE));

                Label icon = new Label("▱");
                icon.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));
                icon.setTextFill(Color.WHITE);

                return new StackPane(
                                outer,
                                inner,
                                icon);
        }

        private Label mapLabel(String text) {

                Label label = new Label(text);
                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
                label.setTextFill(
                                Color.web(DARK));
                label.setPadding(
                                new Insets(7, 13, 7, 13));
                label.setBackground(
                                bg(WHITE, new CornerRadii(15)));

                return label;
        }

        // ================= DESTINATION =================

        private VBox createDestinationBox() {

                VBox outer = new VBox(14);
                outer.setPadding(
                                new Insets(20));
                outer.setPrefHeight(145);
                outer.setMinHeight(145);
                outer.setMaxHeight(145);

                outer.setBackground(
                                bg(CARD, new CornerRadii(22)));

                outer.setBorder(
                                border(BORDER, 22, 1));

                Label title = new Label(
                                "Drop-off destination");
                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));
                title.setTextFill(
                                Color.web(DARK));

                HBox destination = new HBox(12);
                destination.setAlignment(
                                Pos.CENTER_LEFT);
                destination.setPadding(
                                new Insets(14));

                destination.setBackground(
                                bg(WHITE, new CornerRadii(22)));

                destination.setBorder(
                                border(BORDER, 22, 1));

                Label location = new Label("⌖");
                location.setFont(
                                Font.font("Arial", 24));
                location.setTextFill(
                                Color.web(BLUE));

                Label place = new Label(
                                "SpeedFix Auto Care, Baner");
                place.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                16));
                place.setTextFill(
                                Color.web(DARK));

                Label distance = new Label(
                                "7.4 km from your location · open until 11 PM");
                distance.setFont(
                                Font.font("Arial", 13));
                distance.setTextFill(
                                Color.web(GREY));

                VBox destinationText = new VBox(
                                3,
                                place,
                                distance);

                destination.getChildren().addAll(
                                location,
                                destinationText);

                outer.getChildren().addAll(
                                title,
                                destination);

                return outer;
        }

        // ================= TRUCK CARD =================

        private VBox createTruckCard(
                        String name,
                        String company,
                        String eta,
                        String distance,
                        String charge,
                        String rating,
                        String suitable) {

                VBox card = new VBox(12);

                card.setPadding(
                                new Insets(20));

                card.setPrefHeight(220);
                card.setMinHeight(220);
                card.setMaxHeight(220);

                card.setBackground(
                                bg(CARD, new CornerRadii(22)));

                card.setBorder(
                                border(BORDER, 22, 1));

                // TOP

                HBox top = new HBox(12);
                top.setAlignment(
                                Pos.CENTER_LEFT);

                Circle iconCircle = new Circle(
                                22,
                                Color.web("#FFF0E7"));

                Label truckIcon = new Label("▱");
                truckIcon.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                16));
                truckIcon.setTextFill(
                                Color.web(ORANGE));

                StackPane icon = new StackPane(
                                iconCircle,
                                truckIcon);

                Label nameLabel = new Label(name);
                nameLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));
                nameLabel.setTextFill(
                                Color.web(DARK));

                Label companyLabel = new Label(company);
                companyLabel.setFont(
                                Font.font("Arial", 13));
                companyLabel.setTextFill(
                                Color.web(GREY));

                VBox nameBox = new VBox(
                                3,
                                nameLabel,
                                companyLabel);

                Region space = new Region();
                HBox.setHgrow(
                                space,
                                Priority.ALWAYS);

                Label star = new Label(
                                "★ " + rating);
                star.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));
                star.setTextFill(
                                Color.web(ORANGE));

                top.getChildren().addAll(
                                icon,
                                nameBox,
                                space,
                                star);

                // STATS

                HBox stats = new HBox(10);

                stats.getChildren().add(
                                statBox(
                                                "◷",
                                                eta,
                                                "ETA"));

                stats.getChildren().add(
                                statBox(
                                                "⌖",
                                                distance,
                                                "Distance"));

                stats.getChildren().add(
                                statBox(
                                                "₹",
                                                charge,
                                                "Charge"));

                Label suitableLabel = new Label(
                                "Suitable for: " + suitable);
                suitableLabel.setFont(
                                Font.font("Arial", 13));
                suitableLabel.setTextFill(
                                Color.web(GREY));

                Button book = new Button(
                                "Book now");

                book.setMaxWidth(
                                Double.MAX_VALUE);

                book.setPrefHeight(40);

                book.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                book.setTextFill(Color.WHITE);

                book.setBackground(
                                bg(BLUE, new CornerRadii(22)));

                book.setBorder(Border.EMPTY);

                book.setStyle(
                                "-fx-background-color: #2563EB;" +
                                                "-fx-background-radius: 22;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-border-radius: 22;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                book.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-background-radius: 22;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                book.setCursor(
                                Cursor.HAND);

                card.getChildren().addAll(
                                top,
                                stats,
                                suitableLabel,
                                book);

                return card;
        }

        private VBox statBox(
                        String icon,
                        String value,
                        String title) {

                VBox box = new VBox(3);
                box.setAlignment(
                                Pos.CENTER);

                box.setPrefWidth(120);
                box.setPrefHeight(72);
                box.setMinWidth(0);

                box.setBackground(
                                bg("#F0F5F8", new CornerRadii(22)));

                Label iconLabel = new Label(icon);
                iconLabel.setFont(
                                Font.font("Arial", 18));
                iconLabel.setTextFill(
                                Color.web(GREY));

                Label valueLabel = new Label(value);
                valueLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));
                valueLabel.setTextFill(
                                Color.web(DARK));

                Label titleLabel = new Label(title);
                titleLabel.setFont(
                                Font.font("Arial", 12));
                titleLabel.setTextFill(
                                Color.web(GREY));

                box.getChildren().addAll(
                                iconLabel,
                                valueLabel,
                                titleLabel);

                return box;
        }

        // ================= HELPERS =================

        private Background bg(
                        String color,
                        CornerRadii radius) {

                return new Background(
                                new BackgroundFill(
                                                Color.web(color),
                                                radius,
                                                Insets.EMPTY));
        }

        private Background bg(
                        Color color,
                        CornerRadii radius) {

                return new Background(
                                new BackgroundFill(
                                                color,
                                                radius,
                                                Insets.EMPTY));
        }

        private Border border(
                        String color,
                        double radius,
                        double width) {

                return new Border(
                                new BorderStroke(
                                                Color.web(color),
                                                BorderStrokeStyle.SOLID,
                                                new CornerRadii(radius),
                                                new BorderWidths(width)));
        }
}
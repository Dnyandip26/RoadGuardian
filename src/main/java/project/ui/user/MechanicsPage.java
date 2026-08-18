package project.ui.user;

import project.util.SizedBox;

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
import javafx.scene.text.Font;
import project.ui.user.UserHeader;
import javafx.scene.text.FontWeight;

public class MechanicsPage {

        // =========================
        // COLORS - SAME HOMEPAGE THEME
        // =========================

        private static final String BG = "#D9F3F6";
        private static final String WHITE = "#FFFFFF";
        private static final String CARD = "#F3FBFC";
        private static final String BLUE = "#2864E8";
        private static final String DARK = "#0B1F3A";
        private static final String GREY = "#64748B";
        private static final String BORDER = "#C5DDE1";
        private static final String GREEN = "#12B76A";
        private static final String ORANGE = "#FF7A18";

        private Scene scene;

        public MechanicsPage() {
                createPage();
        }

        public Scene getMechanicsScene() {
                return scene;
        }

        // =========================
        // CREATE PAGE
        // =========================

        private void createPage() {

                // =========================
                // COMMON HEADER
                // =========================

                HBox header = UserHeader.createHeader();
                // =========================
                // COMMON SIDEBAR
                // =========================

                ScrollPane sidebar = UserSideBar.createSidebar("Mechanics");

                // =========================
                // PAGE CONTENT
                // =========================

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                22,
                                                22,
                                                28,
                                                22));

                content.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(BG),
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                // =========================
                // PAGE HEADING
                // =========================

                Label title = new Label(
                                "Smart Mechanic Dispatch");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                34));

                title.setTextFill(
                                Color.web(DARK));

                Label subtitle = new Label(
                                "4 verified mechanics within 5 km of NH-48, Exit 12B");

                subtitle.setFont(
                                Font.font(
                                                "Arial",
                                                16));

                subtitle.setTextFill(
                                Color.web(GREY));

                VBox heading = new VBox(
                                3,
                                title,
                                subtitle);

                // =========================
                // MAIN AREA
                // =========================

                HBox mainArea = new HBox(18);

                mainArea.setAlignment(
                                Pos.TOP_LEFT);

                // =========================
                // MAP
                // =========================

                VBox mapBox = createMapBox();

                mapBox.setPrefWidth(690);
                mapBox.setMinWidth(650);
                mapBox.setMaxWidth(690);

                VBox mechanicsList = new VBox(14);

                mechanicsList.setFillWidth(true);
                mechanicsList.setPrefWidth(410);
                mechanicsList.setMinWidth(390);
                mechanicsList.setMaxWidth(410);

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
                                                true),

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
                                                false),

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
                                                false),

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
                                                false));

                mechanicsList.setOpacity(1.0);

                mainArea.getChildren().addAll(
                                mapBox,
                                SizedBox.width(50),
                                mechanicsList);

                content.getChildren().addAll(
                                heading,
                                mainArea);

                // =========================
                // CONTENT SCROLL
                // =========================

                ScrollPane dashboardScroll = new ScrollPane(content);

                dashboardScroll.setFitToWidth(true);

                dashboardScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                dashboardScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                dashboardScroll.setPannable(true);

                dashboardScroll.setStyle(
                                "-fx-background-color: " + BG + ";" +
                                                "-fx-background: " + BG + ";" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-padding: 0;");

                // =========================
                // BODY
                // =========================

                HBox body = new HBox();

                body.setAlignment(
                                Pos.TOP_LEFT);

                body.setFillHeight(true);

                body.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(BG),
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                // COMMON SIDEBAR + CONTENT
                body.getChildren().addAll(
                                sidebar,
                                dashboardScroll);

                HBox.setHgrow(
                                dashboardScroll,
                                Priority.ALWAYS);

                VBox.setVgrow(
                                body,
                                Priority.ALWAYS);

                // =========================
                // ROOT
                // =========================

                VBox root = new VBox(
                                header,
                                body);

                root.setFillWidth(true);

                VBox.setVgrow(
                                body,
                                Priority.ALWAYS);

                // =========================
                // SCENE
                // =========================

                scene = new Scene(
                                root,
                                UserDashboard.dashboardStage.getWidth(),
                                UserDashboard.dashboardStage.getHeight());
        }

        // =========================
        // MAP BOX
        // =========================

        private VBox createMapBox() {

                VBox outer = new VBox();

                outer.setPrefWidth(700);
                outer.setMinWidth(620);
                outer.setMaxWidth(700);

                outer.setPrefHeight(590);
                outer.setMinHeight(520);
                outer.setMaxHeight(590);

                outer.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web("#EEF9FA"),
                                                                new CornerRadii(24),
                                                                Insets.EMPTY)));

                outer.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                Color.web(BORDER),
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(24),
                                                                new BorderWidths(1.5))));

                outer.setPadding(
                                new Insets(10));

                StackPane map = new StackPane();

                VBox.setVgrow(
                                map,
                                Priority.ALWAYS);

                map.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web("#E5E5E3"),
                                                                new CornerRadii(20),
                                                                Insets.EMPTY)));

                map.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                Color.web(BORDER),
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(20),
                                                                BorderWidths.DEFAULT)));
                Line road1 = new Line(
                                20,
                                330,
                                650,
                                80);

                road1.setStroke(Color.WHITE);
                road1.setStrokeWidth(12);

                Line road2 = new Line(
                                70,
                                70,
                                640,
                                380);

                road2.setStroke(Color.WHITE);
                road2.setStrokeWidth(10);

                Line road3 = new Line(
                                300,
                                20,
                                410,
                                440);

                road3.setStroke(Color.WHITE);
                road3.setStrokeWidth(8);

                Line route = new Line(
                                280,
                                250,
                                450,
                                185);

                route.setStroke(Color.web(BLUE));
                route.setStrokeWidth(3);

                route.getStrokeDashArray().addAll(
                                8.0,
                                6.0);

                StackPane roads = new StackPane(
                                road1,
                                road2,
                                road3,
                                route);

                Circle youCircle = new Circle(
                                22,
                                Color.web(BLUE));

                Label youIcon = new Label("▰");

                youIcon.setTextFill(
                                Color.WHITE);

                youIcon.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));

                StackPane youMarker = new StackPane(
                                youCircle,
                                youIcon);

                StackPane.setMargin(
                                youMarker,
                                new Insets(70, 0, 0, 0));

                StackPane.setAlignment(
                                youMarker,
                                Pos.CENTER);

                Label youLabel = markerLabel("You");

                VBox youBox = new VBox(
                                4,
                                youMarker,
                                youLabel);

                youBox.setAlignment(
                                Pos.CENTER);

                StackPane.setAlignment(
                                youBox,
                                Pos.CENTER);

                StackPane.setMargin(
                                youBox,
                                new Insets(90, 0, 0, 0));

                StackPane arjun = mechanicMarker("⚒");

                StackPane kabir = mechanicMarker("⚒");

                StackPane neha = mechanicMarker("⚒");

                StackPane imran = mechanicMarker("⚒");

                StackPane.setAlignment(
                                arjun,
                                Pos.TOP_LEFT);

                StackPane.setMargin(
                                arjun,
                                new Insets(
                                                120,
                                                0,
                                                0,
                                                250));

                StackPane.setAlignment(
                                kabir,
                                Pos.TOP_RIGHT);

                StackPane.setMargin(
                                kabir,
                                new Insets(
                                                85,
                                                100,
                                                0,
                                                0));

                StackPane.setAlignment(
                                neha,
                                Pos.BOTTOM_LEFT);

                StackPane.setMargin(
                                neha,
                                new Insets(
                                                0,
                                                0,
                                                95,
                                                120));

                StackPane.setAlignment(
                                imran,
                                Pos.BOTTOM_RIGHT);

                StackPane.setMargin(
                                imran,
                                new Insets(
                                                0,
                                                120,
                                                110,
                                                0));

                Label radius = new Label(
                                "Live dispatch radius · 5 km");

                radius.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                radius.setTextFill(
                                Color.web(DARK));

                radius.setPadding(
                                new Insets(
                                                10,
                                                16,
                                                10,
                                                16));

                radius.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(WHITE),
                                                                new CornerRadii(22),
                                                                Insets.EMPTY)));

                StackPane.setAlignment(
                                radius,
                                Pos.TOP_LEFT);

                StackPane.setMargin(
                                radius,
                                new Insets(18));

                Label note = new Label(
                                "Real-time map will be connected here later");

                note.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                note.setTextFill(
                                Color.web(GREY));

                note.setPadding(
                                new Insets(
                                                8,
                                                13,
                                                8,
                                                13));

                note.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(WHITE),
                                                                new CornerRadii(18),
                                                                Insets.EMPTY)));

                StackPane.setAlignment(
                                note,
                                Pos.BOTTOM_LEFT);

                StackPane.setMargin(
                                note,
                                new Insets(18));

                map.getChildren().addAll(
                                roads,
                                youBox,
                                arjun,
                                kabir,
                                neha,
                                imran,
                                radius,
                                note);

                outer.getChildren().add(map);

                return outer;
        }

        private StackPane mechanicMarker(
                        String icon) {

                Circle circle = new Circle(
                                23,
                                Color.web(GREEN));

                Label label = new Label(icon);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                16));

                label.setTextFill(
                                Color.WHITE);

                StackPane marker = new StackPane(
                                circle,
                                label);

                Circle ring = new Circle(28);

                ring.setFill(
                                Color.TRANSPARENT);

                ring.setStroke(
                                Color.WHITE);

                ring.setStrokeWidth(4);

                marker.getChildren().add(
                                0,
                                ring);

                return marker;
        }

        private Label markerLabel(
                        String text) {

                Label label = new Label(text);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                label.setTextFill(
                                Color.web(DARK));

                label.setPadding(
                                new Insets(
                                                7,
                                                13,
                                                7,
                                                13));

                label.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(WHITE),
                                                                new CornerRadii(16),
                                                                Insets.EMPTY)));

                return label;
        }

        // =========================
        // MECHANIC CARD
        // =========================

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
                        boolean bestMatch) {

                VBox card = new VBox(12);

                card.setPadding(
                                new Insets(20));

                card.setPrefHeight(205);
                card.setMinHeight(205);
                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setOpacity(1.0);

                card.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web(CARD),
                                                                new CornerRadii(22),
                                                                Insets.EMPTY)));

                card.setBorder(
                                new Border(
                                                new BorderStroke(
                                                                bestMatch
                                                                                ? Color.web(BLUE)
                                                                                : Color.web(BORDER),
                                                                BorderStrokeStyle.SOLID,
                                                                new CornerRadii(22),
                                                                new BorderWidths(
                                                                                bestMatch
                                                                                                ? 2.5
                                                                                                : 1))));

                HBox top = new HBox(10);

                top.setAlignment(
                                Pos.CENTER_LEFT);

                Label nameLabel = new Label(name);

                nameLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                nameLabel.setTextFill(
                                Color.web(DARK));

                top.getChildren().add(
                                nameLabel);

                if (bestMatch) {

                        Label best = new Label(
                                        "⚡ Best match");

                        best.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        12));

                        best.setTextFill(
                                        Color.WHITE);

                        best.setPadding(
                                        new Insets(
                                                        5,
                                                        10,
                                                        5,
                                                        10));

                        best.setBackground(
                                        new Background(
                                                        new BackgroundFill(
                                                                        Color.web(BLUE),
                                                                        new CornerRadii(14),
                                                                        Insets.EMPTY)));

                        top.getChildren().add(best);
                }

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label star = new Label(
                                "★ " + rating);

                star.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                16));

                star.setTextFill(
                                Color.web(ORANGE));

                top.getChildren().addAll(
                                spacer,
                                star);

                Label garageLabel = new Label(garage);

                garageLabel.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                garageLabel.setTextFill(
                                Color.web(GREY));

                HBox details = new HBox(18);

                details.getChildren().addAll(
                                smallInfo("⌖", distance),
                                smallInfo("◷", eta),
                                smallInfo("", jobs));

                HBox tags = new HBox(8);

                if (!tag1.isEmpty())
                        tags.getChildren().add(
                                        tag(tag1));

                if (!tag2.isEmpty())
                        tags.getChildren().add(
                                        tag(tag2));

                if (!tag3.isEmpty())
                        tags.getChildren().add(
                                        tag(tag3));

                Region bottomSpacer = new Region();

                HBox.setHgrow(
                                bottomSpacer,
                                Priority.ALWAYS);

                Label available = new Label(
                                "• Available now");

                available.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                available.setTextFill(
                                Color.web(GREEN));

                Button assign = new Button(
                                "Assign mechanic");

                assign.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                assign.setTextFill(
                                Color.WHITE);

                assign.setPadding(
                                new Insets(
                                                10,
                                                17,
                                                10,
                                                17));

                assign.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-border-radius: 20;" +
                                                "-fx-text-fill: white;");

                assign.setCursor(
                                javafx.scene.Cursor.HAND);

                assign.setOnMouseEntered(
                                e -> assign.setStyle(
                                                "-fx-background-color: #1F56D8;" +
                                                                "-fx-background-radius: 20;" +
                                                                "-fx-border-color: transparent;" +
                                                                "-fx-border-radius: 20;" +
                                                                "-fx-text-fill: white;"));

                assign.setOnMouseExited(
                                e -> assign.setStyle(
                                                "-fx-background-color: " + BLUE + ";" +
                                                                "-fx-background-radius: 20;" +
                                                                "-fx-border-color: transparent;" +
                                                                "-fx-border-radius: 20;" +
                                                                "-fx-text-fill: white;"));

                HBox bottom = new HBox(
                                10,
                                available,
                                bottomSpacer,
                                assign);

                bottom.setAlignment(
                                Pos.CENTER_LEFT);

                card.getChildren().addAll(
                                top,
                                garageLabel,
                                details,
                                tags,
                                bottom);

                return card;
        }

        private HBox smallInfo(
                        String icon,
                        String value) {

                Label label = new Label(
                                icon.isEmpty()
                                                ? value
                                                : icon + "  " + value);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                label.setTextFill(
                                Color.web(GREY));

                HBox box = new HBox(label);

                box.setAlignment(
                                Pos.CENTER_LEFT);

                return box;
        }

        private Label tag(
                        String text) {

                Label tag = new Label(text);

                tag.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                tag.setTextFill(
                                Color.web(DARK));

                tag.setPadding(
                                new Insets(
                                                6,
                                                10,
                                                6,
                                                10));

                tag.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                Color.web("#EAF3F5"),
                                                                new CornerRadii(12),
                                                                Insets.EMPTY)));

                return tag;
        }
}
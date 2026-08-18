package project.ui.user;

import project.app.AppNavigator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Cursor;
import project.ui.user.UserHeader;
import javafx.stage.Stage;

public class CostEstimatorPage {

        private static final String BG = "#D9F3F5";
        private static final String CARD = "#F5FBFC";
        private static final String BLUE = "#2864E8";
        private static final String DARK = "#142033";
        private static final String GREY = "#667085";
        private static final String BORDER = "#C8E0E5";
        private static final String LIGHT_BLUE = "#EEF4FF";
        private static final String ORANGE = "#FF7A18";

        private Scene scene;

        private ComboBox<String> vehicleBox;
        private ComboBox<String> problemBox;

        private Label costLabel;
        private Label repairTimeLabel;
        private Label partsLabel;

        public CostEstimatorPage() {
                buildUI();
        }

        public Scene getCostEstimatorScene() {
                return scene;
        }

        private void buildUI() {

                // ================= HEADER =================

                HBox header = UserHeader.createHeader();
                // ================= SIDEBAR =================

                ScrollPane sidebar = UserSideBar.createSidebar("Cost Estimator");
                
                // ================= MAIN CONTENT =================

                VBox content = new VBox(22);
                content.setPadding(new Insets(28, 30, 35, 30));
                content.setBackground(bg(BG, CornerRadii.EMPTY));

                Label title = new Label("Repair Cost Estimator");
                title.setFont(Font.font("Arial", FontWeight.BOLD, 34));
                title.setTextFill(Color.web(DARK));

                Label subtitle = new Label(
                                "Real pricing bands sourced from 18,400 partner garages");
                subtitle.setFont(Font.font("Arial", 16));
                subtitle.setTextFill(Color.web(GREY));

                VBox heading = new VBox(4, title, subtitle);

                HBox main = new HBox(22);
                main.setAlignment(Pos.TOP_LEFT);

                VBox formCard = createFormCard();
                HBox.setHgrow(formCard, Priority.ALWAYS);

                VBox rightColumn = new VBox(20);
                rightColumn.setPrefWidth(800);
                HBox.setHgrow(rightColumn, Priority.ALWAYS);

                VBox estimateCard = createEstimateCard();
                VBox centersCard = createCentersCard();

                rightColumn.getChildren().addAll(
                                estimateCard,
                                centersCard);

                main.getChildren().addAll(
                                formCard,
                                rightColumn);

                content.getChildren().addAll(
                                heading,
                                main);

                HBox body = new HBox(
                                sidebar,
                                content);

                HBox.setHgrow(content, Priority.ALWAYS);

                VBox root = new VBox(
                                header,
                                body);

                VBox.setVgrow(body, Priority.ALWAYS);

                scene = new Scene(root, UserDashboard.dashboardStage.getWidth(),
                                UserDashboard.dashboardStage.getHeight());
        }

       
        // ================= FORM CARD =================

        private VBox createFormCard() {

                VBox card = new VBox(22);
                card.setPadding(new Insets(30));
                card.setPrefWidth(520);
                card.setMinWidth(450);
                card.setPrefHeight(650);

                card.setBackground(
                                bg(CARD, new CornerRadii(24)));

                card.setBorder(
                                border(BORDER, 24, 1));

                Label title = new Label("Tell us about the job");
                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));
                title.setTextFill(Color.web(DARK));

                Label vehicleLabel = fieldLabel("Vehicle type");

                vehicleBox = new ComboBox<>();
                vehicleBox.getItems().addAll(
                                "Sedan",
                                "Hatchback",
                                "SUV",
                                "MUV",
                                "Pickup Truck",
                                "Luxury Car");
                vehicleBox.setValue("Sedan");
                styleCombo(vehicleBox);

                Label problemLabel = fieldLabel("Problem");

                problemBox = new ComboBox<>();
                problemBox.getItems().addAll(
                                "Battery replacement",
                                "Engine repair",
                                "Brake repair",
                                "Flat tyre",
                                "AC repair",
                                "Clutch repair",
                                "Electrical issue");
                problemBox.setValue("Battery replacement");
                styleCombo(problemBox);

                Button recalculate = new Button(
                                "▤    Recalculate estimate");

                recalculate.setMaxWidth(
                                Double.MAX_VALUE);
                recalculate.setPrefHeight(46);
                recalculate.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));
                recalculate.setTextFill(Color.WHITE);
                recalculate.setStyle(
                                "-fx-background-color: #2864E8;" +
                                                "-fx-background-radius: 24;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");
                recalculate.setCursor(Cursor.HAND);

                recalculate.setOnAction(
                                e -> calculateEstimate());

                card.getChildren().addAll(
                                title,
                                vehicleLabel,
                                vehicleBox,
                                problemLabel,
                                problemBox,
                                recalculate);

                return card;
        }

        // ================= ESTIMATE =================

        private VBox createEstimateCard() {

                VBox card = new VBox(20);
                card.setPadding(new Insets(28));
                card.setPrefHeight(275);

                card.setBackground(
                                bg(CARD, new CornerRadii(24)));

                card.setBorder(
                                border(BORDER, 24, 1));

                Label smallTitle = new Label(
                                "ESTIMATED COST RANGE");
                smallTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                13));
                smallTitle.setTextFill(Color.web(GREY));

                costLabel = new Label(
                                "₹5,200 – ₹9,500");
                costLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                40));
                costLabel.setTextFill(Color.web(DARK));

                Region progressBackground = new Region();
                progressBackground.setPrefHeight(8);
                progressBackground.setMaxWidth(Double.MAX_VALUE);
                progressBackground.setBackground(
                                bg("#EDF1F5", new CornerRadii(8)));

                Region progress = new Region();
                progress.setPrefHeight(8);
                progress.setPrefWidth(435);
                progress.setBackground(
                                bg("#4B8CF5", new CornerRadii(8)));

                StackPane progressBar = new StackPane(
                                progressBackground,
                                progress);

                StackPane.setAlignment(
                                progress,
                                Pos.CENTER_LEFT);

                HBox details = new HBox(16);

                repairTimeLabel = new Label("45 min");
                styleDetailValueLabel(repairTimeLabel);

                partsLabel = new Label(
                                "Battery 12V 45Ah, Terminal clamps");
                styleDetailValueLabel(partsLabel);

                VBox repairTime = detailBox(
                                "◷",
                                "REPAIR TIME",
                                repairTimeLabel,
                                BLUE);

                VBox spareParts = detailBox(
                                "□",
                                "COMMON SPARE PARTS",
                                partsLabel,
                                ORANGE);

                HBox.setHgrow(repairTime, Priority.ALWAYS);
                HBox.setHgrow(spareParts, Priority.ALWAYS);

                details.getChildren().addAll(
                                repairTime,
                                spareParts);

                card.getChildren().addAll(
                                smallTitle,
                                costLabel,
                                progressBar,
                                details);

                return card;
        }

        private VBox detailBox(
                        String icon,
                        String title,
                        Label valueLabel,
                        String iconColor) {

                VBox box = new VBox(6);
                box.setPadding(new Insets(15));
                box.setPrefHeight(100);

                box.setBackground(
                                bg("#F8FCFD", new CornerRadii(22)));

                box.setBorder(
                                border(BORDER, 22, 1));

                Label iconLabel = new Label(icon);
                iconLabel.setFont(Font.font("Arial", 18));
                iconLabel.setTextFill(Color.web(iconColor));

                Label titleLabel = new Label(title);
                titleLabel.setFont(Font.font("Arial", 12));
                titleLabel.setTextFill(Color.web(GREY));

                box.getChildren().addAll(
                                iconLabel,
                                titleLabel,
                                valueLabel);

                return box;
        }

        private void styleDetailValueLabel(Label label) {
                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));
                label.setTextFill(Color.web(DARK));
                label.setWrapText(true);
        }

        // ================= SERVICE CENTERS =================

        private VBox createCentersCard() {

                VBox card = new VBox(15);
                card.setPadding(new Insets(28));

                card.setBackground(
                                bg(CARD, new CornerRadii(24)));

                card.setBorder(
                                border(BORDER, 24, 1));

                Label title = new Label(
                                "▥  Nearby service centers");
                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));
                title.setTextFill(Color.web(DARK));

                card.getChildren().add(title);

                card.getChildren().add(
                                serviceCenter(
                                                "SpeedFix Auto Care",
                                                "1.2 km",
                                                "4.9"));

                card.getChildren().add(
                                serviceCenter(
                                                "Highway Motors",
                                                "2.4 km",
                                                "4.7"));

                card.getChildren().add(
                                serviceCenter(
                                                "ElectroDrive Garage",
                                                "3.1 km",
                                                "4.8"));

                return card;
        }

        private HBox serviceCenter(
                        String name,
                        String distance,
                        String rating) {

                HBox row = new HBox(12);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(
                                new Insets(13, 14, 13, 14));
                row.setPrefHeight(72);

                row.setBackground(
                                bg("#F8FCFD", new CornerRadii(22)));

                row.setBorder(
                                border(BORDER, 22, 1));

                Label nameLabel = new Label(name);
                nameLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                16));
                nameLabel.setTextFill(Color.web(DARK));

                Label info = new Label(
                                "⌖ " + distance + "     " +
                                                "★ " + rating);
                info.setFont(
                                Font.font("Arial", 13));
                info.setTextFill(Color.web(GREY));

                VBox details = new VBox(
                                5,
                                nameLabel,
                                info);

                Region space = new Region();
                HBox.setHgrow(space, Priority.ALWAYS);

                Button book = new Button("Book slot");
                book.setPrefSize(88, 38);
                book.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                13));
                book.setTextFill(Color.web(DARK));
                book.setBackground(
                                bg("#F8FCFD", new CornerRadii(20)));
                book.setBorder(
                                border(BORDER, 20, 1));
                book.setCursor(Cursor.HAND);

                book.setOnMouseEntered(e -> book.setBackground(
                                bg(
                                                LIGHT_BLUE,
                                                new CornerRadii(20))));

                book.setOnMouseExited(e -> book.setBackground(
                                bg(
                                                WHITE(),
                                                new CornerRadii(20))));

                row.getChildren().addAll(
                                details,
                                space,
                                book);

                return row;
        }

        // ================= CALCULATION =================

        private void calculateEstimate() {

                String vehicle = vehicleBox.getValue();
                String problem = problemBox.getValue();

                if (vehicle == null || problem == null) {
                        return;
                }

                int min = 5200;
                int max = 9500;
                String time = "45 min";
                String parts = "Battery 12V 45Ah, Terminal clamps";

                if (problem.equals("Engine repair")) {
                        min = 8500;
                        max = 18000;
                        time = "2–4 hrs";
                        parts = "Spark plugs, filters, engine oil";
                } else if (problem.equals("Brake repair")) {
                        min = 3200;
                        max = 7800;
                        time = "1–2 hrs";
                        parts = "Brake pads, discs, brake fluid";
                } else if (problem.equals("Flat tyre")) {
                        min = 700;
                        max = 2200;
                        time = "20–40 min";
                        parts = "Tyre, valve, puncture kit";
                } else if (problem.equals("AC repair")) {
                        min = 2500;
                        max = 7000;
                        time = "1–2 hrs";
                        parts = "AC gas, filter, compressor parts";
                } else if (problem.equals("Clutch repair")) {
                        min = 6500;
                        max = 14000;
                        time = "3–5 hrs";
                        parts = "Clutch plate, pressure plate";
                } else if (problem.equals("Electrical issue")) {
                        min = 1800;
                        max = 5500;
                        time = "1–2 hrs";
                        parts = "Fuse, wiring, battery terminals";
                }

                // Slight vehicle adjustment.
                if (vehicle.equals("SUV") ||
                                vehicle.equals("MUV")) {

                        min += 800;
                        max += 1500;
                }

                costLabel.setText(
                                "₹" + format(min) +
                                                " – ₹" + format(max));

                if (repairTimeLabel != null) {
                        repairTimeLabel.setText(time);
                }

                if (partsLabel != null) {
                        partsLabel.setText(parts);
                }
        }

        private String format(int value) {
                return String.format(
                                java.util.Locale.US,
                                "%,d",
                                value);
        }

        /*
         * These methods locate the current labels from the estimate card.
         * They keep the UI code simple and avoid exposing extra controls.
         */
        

        private Label fieldLabel(String text) {

                Label label = new Label(text);

                label.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                16));

                label.setTextFill(
                                Color.web(DARK));

                return label;
        }

        private void styleCombo(
                        ComboBox<String> combo) {

                combo.setPrefHeight(50);
                combo.setMaxWidth(
                                Double.MAX_VALUE);

                combo.setStyle(
                                "-fx-background-color: #F8FAFC;" +
                                                "-fx-background-radius: 25;" +
                                                "-fx-border-color: #DCE4EA;" +
                                                "-fx-border-radius: 25;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 12 0 12;");
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

        private Color WHITE() {
                return Color.WHITE;
        }
}
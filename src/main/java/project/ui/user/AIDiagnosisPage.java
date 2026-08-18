package project.ui.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import project.ui.user.UserHeader;
import javafx.stage.Stage;

/**
 * RoadGuardian - AI Vehicle Breakdown Diagnosis
 *
 * UI-only page.
 * Existing navigation is intentionally not changed here.
 */
public class AIDiagnosisPage {

        // ============================================================
        // HOMEPAGE THEME
        // ============================================================

        private static final String PAGE_BG = "#D6F0F3";
        private static final String CARD_BG = "#EEF9FA";
        private static final String BORDER = "#C9E2E6";
        private static final String HEADING = "#142033";
        private static final String SECONDARY = "#667085";
        private static final String BLUE = "#2864E8";
        private static final String LIGHT_BLUE = "#DDEAFF";
        private static final String SUCCESS = "#17A95B";

        private final VBox root = new VBox();

        private final VBox resultCard = new VBox(12);

        private final Button diagnoseButton = new Button("✣   Diagnose");

        private final Button resetButton = new Button("↻   Reset");

        private final java.util.List<Button> symptomButtons = new java.util.ArrayList<>();

        // ============================================================
        // CONSTRUCTOR
        // ============================================================

        public AIDiagnosisPage() {
                buildUI();
        }

        // ============================================================
        // SCENE
        // ============================================================

        public Scene getAIDiagnosisScene() {

                Scene scene = new Scene(root, UserDashboard.dashboardStage.getWidth(),
                                UserDashboard.dashboardStage.getHeight());

                return scene;
        }

        // ============================================================
        // MAIN UI
        // ============================================================

        private void buildUI() {

                root.setSpacing(0);
                root.setFillWidth(true);
                root.setStyle(
                                "-fx-background-color: " + PAGE_BG + ";");

                HBox header = UserHeader.createHeader();

                HBox body = new HBox();
                body.setFillHeight(true);
                HBox.setHgrow(body, Priority.ALWAYS);

                ScrollPane sidebar = UserSideBar.createSidebar("AI Diagnosis");

                VBox page = createPageContent();

                ScrollPane pageScroll = new ScrollPane(
                                page);

                pageScroll.setFitToWidth(true);
                pageScroll.setFitToHeight(false);

                pageScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                pageScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                pageScroll.setStyle(
                                "-fx-background-color: " + PAGE_BG + ";" +
                                                "-fx-background: " + PAGE_BG + ";" +
                                                "-fx-control-inner-background: " + PAGE_BG + ";" +
                                                "-fx-border-color: transparent;");

                HBox.setHgrow(pageScroll, Priority.ALWAYS);

                body.getChildren().addAll(
                                sidebar,
                                pageScroll);

                VBox.setVgrow(body, Priority.ALWAYS);

                root.getChildren().addAll(
                                header,
                                body);
        }

       
        // ============================================================
        // PAGE CONTENT
        // ============================================================

        private VBox createPageContent() {

                VBox page = new VBox(24);

                page.setPadding(
                                new Insets(30, 34, 40, 34));

                page.setFillWidth(true);
                page.setMinWidth(0);
                page.setMaxWidth(Double.MAX_VALUE);

                page.setStyle(
                                "-fx-background-color: " + PAGE_BG + ";");

                VBox.setVgrow(page, Priority.ALWAYS);

                // Heading
                VBox headingBox = new VBox(4);

                Label heading = new Label(
                                "AI Vehicle Breakdown Diagnosis");

                heading.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                32));

                heading.setTextFill(
                                Color.web(HEADING));

                Label subtitle = new Label(
                                "Tell us what you're seeing, hearing or smelling — we'll narrow it down");

                subtitle.setFont(
                                Font.font("Arial", 16));

                subtitle.setTextFill(
                                Color.web(SECONDARY));

                headingBox.getChildren().addAll(
                                heading,
                                subtitle);

                // Main cards
                HBox cards = new HBox(22);
                cards.setFillHeight(true);
                cards.setMinWidth(0);
                cards.setPrefHeight(340);

                VBox symptomCard = createSymptomsCard();

                resultCard.setPadding(
                                new Insets(28));

                resultCard.setAlignment(
                                Pos.TOP_LEFT);

                resultCard.setMinWidth(0);
                resultCard.setPrefWidth(470);
                resultCard.setMaxWidth(Double.MAX_VALUE);

                resultCard.setStyle(
                                "-fx-background-color: " + CARD_BG + ";" +
                                                "-fx-background-radius: 24;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 24;" +
                                                "-fx-border-width: 1;");

                HBox.setHgrow(symptomCard, Priority.ALWAYS);
                HBox.setHgrow(resultCard, Priority.ALWAYS);

                cards.getChildren().addAll(
                                symptomCard,
                                resultCard);

                showEmptyResult();

                page.getChildren().addAll(
                                headingBox,
                                cards);

                return page;
        }

        // ============================================================
        // SYMPTOMS CARD
        // ============================================================

        private VBox createSymptomsCard() {

                VBox card = new VBox(18);

                card.setPadding(
                                new Insets(28));

                card.setMinWidth(0);
                card.setPrefWidth(650);

                card.setStyle(
                                "-fx-background-color: " + CARD_BG + ";" +
                                                "-fx-background-radius: 24;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 24;" +
                                                "-fx-border-width: 1;");

                Label title = new Label("Select symptoms");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(HEADING));

                Label description = new Label(
                                "Choose all that apply. More signals means a sharper result.");

                description.setFont(
                                Font.font("Arial", 15));

                description.setTextFill(
                                Color.web(SECONDARY));

                FlowPane symptoms = new FlowPane();
                symptoms.setHgap(10);
                symptoms.setVgap(10);
                symptoms.setPrefWrapLength(700);

                String[] symptomNames = {
                                "Engine won't start",
                                "Battery light ON",
                                "Smoke from bonnet",
                                "Fuel empty",
                                "Overheating",
                                "Flat tyre",
                                "Brake noise",
                                "Strange vibration",
                                "AC not cooling",
                                "Warning lights flashing"
                };

                for (String symptom : symptomNames) {

                        Button button = createSymptomButton(
                                        symptom);

                        symptoms.getChildren().add(button);
                        symptomButtons.add(button);
                }

                HBox actions = new HBox(18);
                actions.setAlignment(Pos.CENTER_LEFT);

                diagnoseButton.setPrefWidth(174);
                diagnoseButton.setPrefHeight(44);

                diagnoseButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));

                diagnoseButton.setTextFill(Color.WHITE);

                diagnoseButton.setStyle(
                                "-fx-background-color: #8BADEF;" +
                                                "-fx-background-radius: 24;");

                resetButton.setPrefHeight(44);
                resetButton.setPrefWidth(90);

                resetButton.setFont(
                                Font.font("Arial", 15));

                resetButton.setTextFill(
                                Color.web(HEADING));

                resetButton.setStyle(
                                "-fx-background-color: transparent;");

                diagnoseButton.setOnAction(
                                e -> diagnose());

                resetButton.setOnAction(
                                e -> reset());

                actions.getChildren().addAll(
                                diagnoseButton,
                                resetButton);

                card.getChildren().addAll(
                                title,
                                description,
                                symptoms,
                                actions);

                VBox.setVgrow(symptoms, Priority.ALWAYS);

                return card;
        }

        // ============================================================
        // SYMPTOM BUTTON
        // ============================================================

        private Button createSymptomButton(
                        String text) {

                Button button = new Button(text);

                button.setPrefHeight(42);
                button.setPadding(
                                new Insets(0, 17, 0, 17));

                button.setFont(
                                Font.font("Arial", 15));

                button.setTextFill(
                                Color.web(HEADING));

                button.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #D9E2E7;" +
                                                "-fx-border-width: 1;" +
                                                "-fx-border-radius: 22;" +
                                                "-fx-background-radius: 22;");

                button.setOnAction(
                                e -> toggleSymptom(button));

                return button;
        }

        private void toggleSymptom(
                        Button button) {

                Object selected = button.getProperties().get("selected");

                boolean isSelected = selected instanceof Boolean
                                && (Boolean) selected;

                if (!isSelected) {

                        button.getProperties().put(
                                        "selected",
                                        true);

                        button.setTextFill(Color.WHITE);

                        button.setStyle(
                                        "-fx-background-color: " + BLUE + ";" +
                                                        "-fx-border-color: " + BLUE + ";" +
                                                        "-fx-border-width: 1;" +
                                                        "-fx-border-radius: 22;" +
                                                        "-fx-background-radius: 22;");

                } else {

                        button.getProperties().put(
                                        "selected",
                                        false);

                        button.setTextFill(
                                        Color.web(HEADING));

                        button.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: #D9E2E7;" +
                                                        "-fx-border-width: 1;" +
                                                        "-fx-border-radius: 22;" +
                                                        "-fx-background-radius: 22;");
                }

                updateDiagnoseButton();
        }

        private void updateDiagnoseButton() {

                boolean selected = false;

                for (Button button : symptomButtons) {

                        Object value = button.getProperties().get("selected");

                        if (value instanceof Boolean
                                        && (Boolean) value) {

                                selected = true;
                                break;
                        }
                }

                if (selected) {

                        diagnoseButton.setStyle(
                                        "-fx-background-color: " + BLUE + ";" +
                                                        "-fx-background-radius: 24;");

                } else {

                        diagnoseButton.setStyle(
                                        "-fx-background-color: #8BADEF;" +
                                                        "-fx-background-radius: 24;");
                }
        }

        // ============================================================
        // EMPTY RESULT
        // ============================================================

        private void showEmptyResult() {

                resultCard.getChildren().clear();
                resultCard.setAlignment(Pos.CENTER);

                StackPane iconBox = new StackPane();
                Circle iconCircle = new Circle(
                                31,
                                Color.web(LIGHT_BLUE));

                Label icon = new Label("✣");

                icon.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                27));

                icon.setTextFill(
                                Color.web(BLUE));

                iconBox.getChildren().addAll(
                                iconCircle,
                                icon);

                Label title = new Label(
                                "No diagnosis yet");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                title.setTextFill(
                                Color.web(HEADING));

                Label text = new Label(
                                "Select at least one symptom and tap Diagnose to see the\n" +
                                                "probable fault and cost band.");

                text.setFont(
                                Font.font("Arial", 15));

                text.setTextFill(
                                Color.web(SECONDARY));

                text.setTextAlignment(
                                javafx.scene.text.TextAlignment.CENTER);

                text.setAlignment(Pos.CENTER);

                resultCard.getChildren().addAll(
                                iconBox,
                                title,
                                text);
        }

        // ============================================================
        // DIAGNOSIS
        // ============================================================

        private void diagnose() {

                boolean selected = false;

                for (Button button : symptomButtons) {

                        Object value = button.getProperties().get("selected");

                        if (value instanceof Boolean
                                        && (Boolean) value) {

                                selected = true;
                                break;
                        }
                }

                if (!selected) {
                        showEmptyResult();
                        return;
                }

                resultCard.getChildren().clear();
                resultCard.setAlignment(
                                Pos.TOP_LEFT);

                Label title = new Label(
                                "Probable diagnosis");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                title.setTextFill(
                                Color.web(HEADING));

                Label fault = new Label(
                                "Electrical / battery-related issue");

                fault.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                fault.setTextFill(
                                Color.web(BLUE));

                Label description = new Label(
                                "Based on the selected symptoms, the battery, " +
                                                "starter circuit or charging system may need inspection.");

                description.setWrapText(true);
                description.setFont(
                                Font.font("Arial", 14));
                description.setTextFill(
                                Color.web(SECONDARY));

                VBox costBox = new VBox(4);

                costBox.setPadding(
                                new Insets(14));

                costBox.setStyle(
                                "-fx-background-color: #E6F7EE;" +
                                                "-fx-background-radius: 14;");

                Label costTitle = new Label(
                                "Estimated cost band");

                costTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                costTitle.setTextFill(
                                Color.web(SECONDARY));

                Label cost = new Label(
                                "₹800 – ₹3,500");

                cost.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                cost.setTextFill(
                                Color.web(SUCCESS));

                costBox.getChildren().addAll(
                                costTitle,
                                cost);

                resultCard.getChildren().addAll(
                                title,
                                fault,
                                description,
                                costBox);
        }

        // ============================================================
        // RESET
        // ============================================================

        private void reset() {

                for (Button button : symptomButtons) {

                        button.getProperties().put(
                                        "selected",
                                        false);

                        button.setTextFill(
                                        Color.web(HEADING));

                        button.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: #D9E2E7;" +
                                                        "-fx-border-width: 1;" +
                                                        "-fx-border-radius: 22;" +
                                                        "-fx-background-radius: 22;");
                }

                updateDiagnoseButton();
                showEmptyResult();
        }
}
package project.ui.user.Assistance;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

import project.ui.user.DashBoard.UserSectionPage;

/**
 * RoadGuardian - AI Vehicle Breakdown Diagnosis
 *
 * UI-only page.
 *
 * This page is designed to be loaded inside
 * UserDashboard content area.
 *
 * The common UserDashboard TopBar and Sidebar
 * are intentionally not created here.
 */
public class AIDiagnosisPage extends UserSectionPage {

    // ============================================================
    // THEME
    // ============================================================

    private static final String PAGE_BG = "#D9F3F6";
    private static final String CARD_BG = "#F3FBFC";
    private static final String BORDER = "#C5DDE1";
    private static final String HEADING = "#0B1F3A";
    private static final String SECONDARY = "#64748B";
    private static final String BLUE = "#2864E8";
    private static final String LIGHT_BLUE = "#DDEAFF";
    private static final String SUCCESS = "#17A95B";

    // ============================================================
    // CONTROLS
    // ============================================================

    private final VBox root =
            new VBox();

    private final VBox resultCard =
            new VBox(12);

    private final Button diagnoseButton =
            new Button("✣   Diagnose");

    private final Button resetButton =
            new Button("↻   Reset");

    private final List<Button> symptomButtons =
            new ArrayList<>();

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public AIDiagnosisPage() {

        buildUI();
    }

    // ============================================================
    // GET VIEW
    // ============================================================

    @Override
    public VBox getView() {

        return root;
    }

    // ============================================================
    // BUILD UI
    // ============================================================

    private void buildUI() {

        root.setSpacing(0);

        root.setFillWidth(true);

        root.setStyle(
                "-fx-background-color: " +
                        PAGE_BG +
                        ";"
        );

        VBox page =
                createPageContent();

        ScrollPane scrollPane =
                new ScrollPane(
                        page
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
                        PAGE_BG +
                        ";" +
                        "-fx-background: " +
                        PAGE_BG +
                        ";" +
                        "-fx-border-color: transparent;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        root.getChildren().add(
                scrollPane
        );
    }

    // ============================================================
    // PAGE CONTENT
    // ============================================================

    private VBox createPageContent() {

        VBox page =
                new VBox(24);

        page.setPadding(
                new Insets(
                        30,
                        34,
                        40,
                        34
                )
        );

        page.setFillWidth(true);

        page.setStyle(
                "-fx-background-color: " +
                        PAGE_BG +
                        ";"
        );

        // ========================================================
        // HEADING
        // ========================================================

        VBox headingBox =
                new VBox(4);

        Label heading =
                new Label(
                        "AI Vehicle Breakdown Diagnosis"
                );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        heading.setTextFill(
                Color.web(HEADING)
        );

        Label subtitle =
                new Label(
                        "Tell us what you're seeing, hearing or " +
                                "smelling — we'll narrow it down"
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        subtitle.setTextFill(
                Color.web(SECONDARY)
        );

        headingBox.getChildren().addAll(
                heading,
                subtitle
        );

        // ========================================================
        // CARDS
        // ========================================================

        HBox cards =
                new HBox(22);

        cards.setFillHeight(true);

        cards.setMinWidth(0);

        cards.setPrefHeight(340);

        VBox symptomCard =
                createSymptomsCard();

        resultCard.setPadding(
                new Insets(28)
        );

        resultCard.setAlignment(
                Pos.TOP_LEFT
        );

        resultCard.setMinWidth(0);

        resultCard.setPrefWidth(470);

        resultCard.setMaxWidth(
                Double.MAX_VALUE
        );

        resultCard.setStyle(
                "-fx-background-color: " +
                        CARD_BG +
                        ";" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 24;" +
                        "-fx-border-width: 1;"
        );

        HBox.setHgrow(
                symptomCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                resultCard,
                Priority.ALWAYS
        );

        cards.getChildren().addAll(
                symptomCard,
                resultCard
        );

        showEmptyResult();

        page.getChildren().addAll(
                headingBox,
                cards
        );

        return page;
    }

    // ============================================================
    // SYMPTOMS CARD
    // ============================================================

    private VBox createSymptomsCard() {

        VBox card =
                new VBox(18);

        card.setPadding(
                new Insets(28)
        );

        card.setMinWidth(0);

        card.setPrefWidth(650);

        card.setStyle(
                "-fx-background-color: " +
                        CARD_BG +
                        ";" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 24;" +
                        "-fx-border-width: 1;"
        );

        Label title =
                new Label(
                        "Select symptoms"
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

        Label description =
                new Label(
                        "Choose all that apply. More signals means " +
                                "a sharper result."
                );

        description.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        description.setTextFill(
                Color.web(SECONDARY)
        );

        FlowPane symptoms =
                new FlowPane();

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

        for (
                String symptom :
                symptomNames
        ) {

            Button button =
                    createSymptomButton(
                            symptom
                    );

            symptoms.getChildren().add(
                    button
            );

            symptomButtons.add(
                    button
            );
        }

        HBox actions =
                new HBox(18);

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        diagnoseButton.setPrefWidth(
                174
        );

        diagnoseButton.setPrefHeight(
                44
        );

        diagnoseButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        diagnoseButton.setTextFill(
                Color.WHITE
        );

        diagnoseButton.setStyle(
                "-fx-background-color: #8BADEF;" +
                        "-fx-background-radius: 24;" +
                        "-fx-cursor: hand;"
        );

        resetButton.setPrefHeight(
                44
        );

        resetButton.setPrefWidth(
                90
        );

        resetButton.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        resetButton.setTextFill(
                Color.web(HEADING)
        );

        resetButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-cursor: hand;"
        );

        diagnoseButton.setOnAction(
                event ->
                        diagnose()
        );

        resetButton.setOnAction(
                event ->
                        reset()
        );

        actions.getChildren().addAll(
                diagnoseButton,
                resetButton
        );

        card.getChildren().addAll(
                title,
                description,
                symptoms,
                actions
        );

        VBox.setVgrow(
                symptoms,
                Priority.ALWAYS
        );

        return card;
    }

    // ============================================================
    // SYMPTOM BUTTON
    // ============================================================

    private Button createSymptomButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefHeight(
                42
        );

        button.setPadding(
                new Insets(
                        0,
                        17,
                        0,
                        17
                )
        );

        button.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        button.setTextFill(
                Color.web(HEADING)
        );

        setNormalSymptomStyle(
                button
        );

        button.setOnAction(
                event ->
                        toggleSymptom(
                                button
                        )
        );

        return button;
    }

    // ============================================================
    // TOGGLE SYMPTOM
    // ============================================================

    private void toggleSymptom(
            Button button
    ) {

        Object selected =
                button.getProperties()
                        .get(
                                "selected"
                        );

        boolean isSelected =
                selected instanceof Boolean &&
                        (Boolean) selected;

        if (!isSelected) {

            button.getProperties().put(
                    "selected",
                    true
            );

            button.setTextFill(
                    Color.WHITE
            );

            button.setStyle(
                    "-fx-background-color: " +
                            BLUE +
                            ";" +
                            "-fx-border-color: " +
                            BLUE +
                            ";" +
                            "-fx-border-width: 1;" +
                            "-fx-border-radius: 22;" +
                            "-fx-background-radius: 22;" +
                            "-fx-cursor: hand;"
            );

        } else {

            button.getProperties().put(
                    "selected",
                    false
            );

            button.setTextFill(
                    Color.web(HEADING)
            );

            setNormalSymptomStyle(
                    button
            );
        }

        updateDiagnoseButton();
    }

    // ============================================================
    // NORMAL SYMPTOM STYLE
    // ============================================================

    private void setNormalSymptomStyle(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #D9E2E7;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 22;" +
                        "-fx-background-radius: 22;" +
                        "-fx-cursor: hand;"
        );
    }

    // ============================================================
    // UPDATE DIAGNOSE BUTTON
    // ============================================================

    private void updateDiagnoseButton() {

        boolean selected =
                hasSelectedSymptom();

        if (selected) {

            diagnoseButton.setStyle(
                    "-fx-background-color: " +
                            BLUE +
                            ";" +
                            "-fx-background-radius: 24;" +
                            "-fx-cursor: hand;"
            );

        } else {

            diagnoseButton.setStyle(
                    "-fx-background-color: #8BADEF;" +
                            "-fx-background-radius: 24;" +
                            "-fx-cursor: hand;"
            );
        }
    }

    // ============================================================
    // CHECK SELECTED SYMPTOM
    // ============================================================

    private boolean hasSelectedSymptom() {

        for (
                Button button :
                symptomButtons
        ) {

            Object value =
                    button.getProperties()
                            .get(
                                    "selected"
                            );

            if (
                    value instanceof Boolean &&
                            (Boolean) value
            ) {

                return true;
            }
        }

        return false;
    }

    // ============================================================
    // EMPTY RESULT
    // ============================================================

    private void showEmptyResult() {

        resultCard.getChildren().clear();

        resultCard.setAlignment(
                Pos.CENTER
        );

        StackPane iconBox =
                new StackPane();

        Circle iconCircle =
                new Circle(
                        31,
                        Color.web(LIGHT_BLUE)
                );

        Label icon =
                new Label("✣");

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        icon.setTextFill(
                Color.web(BLUE)
        );

        iconBox.getChildren().addAll(
                iconCircle,
                icon
        );

        Label title =
                new Label(
                        "No diagnosis yet"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        title.setTextFill(
                Color.web(HEADING)
        );

        Label text =
                new Label(
                        "Select at least one symptom and tap Diagnose " +
                                "to see the\nprobable fault and cost band."
                );

        text.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        text.setTextFill(
                Color.web(SECONDARY)
        );

        text.setTextAlignment(
                TextAlignment.CENTER
        );

        text.setAlignment(
                Pos.CENTER
        );

        resultCard.getChildren().addAll(
                iconBox,
                title,
                text
        );
    }

    // ============================================================
    // DIAGNOSE
    // ============================================================

    private void diagnose() {

        if (!hasSelectedSymptom()) {

            showEmptyResult();

            return;
        }

        resultCard.getChildren().clear();

        resultCard.setAlignment(
                Pos.TOP_LEFT
        );

        Label title =
                new Label(
                        "Probable diagnosis"
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

        Label fault =
                new Label(
                        "Electrical / battery-related issue"
                );

        fault.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        fault.setTextFill(
                Color.web(BLUE)
        );

        Label description =
                new Label(
                        "Based on the selected symptoms, the battery, " +
                                "starter circuit or charging system may need inspection."
                );

        description.setWrapText(
                true
        );

        description.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        description.setTextFill(
                Color.web(SECONDARY)
        );

        VBox costBox =
                new VBox(4);

        costBox.setPadding(
                new Insets(14)
        );

        costBox.setStyle(
                "-fx-background-color: #E6F7EE;" +
                        "-fx-background-radius: 14;"
        );

        Label costTitle =
                new Label(
                        "Estimated cost band"
                );

        costTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        costTitle.setTextFill(
                Color.web(SECONDARY)
        );

        Label cost =
                new Label(
                        "₹800 – ₹3,500"
                );

        cost.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        cost.setTextFill(
                Color.web(SUCCESS)
        );

        costBox.getChildren().addAll(
                costTitle,
                cost
        );

        resultCard.getChildren().addAll(
                title,
                fault,
                description,
                costBox
        );
    }

    // ============================================================
    // RESET
    // ============================================================

    private void reset() {

        for (
                Button button :
                symptomButtons
        ) {

            button.getProperties().put(
                    "selected",
                    false
            );

            button.setTextFill(
                    Color.web(HEADING)
            );

            setNormalSymptomStyle(
                    button
            );
        }

        updateDiagnoseButton();

        showEmptyResult();
    }
}
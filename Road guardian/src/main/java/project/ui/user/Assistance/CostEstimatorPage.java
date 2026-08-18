package project.ui.user.Assistance;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.ui.user.DashBoard.UserSectionPage;

/**
 * RoadGuardian - Repair Cost Estimator
 *
 * UI-only page.
 *
 * This page is designed to be loaded inside
 * UserDashboard content area.
 *
 * The common UserDashboard TopBar and Sidebar
 * are intentionally not created here.
 */
public class CostEstimatorPage extends UserSectionPage {

    // ============================================================
    // THEME
    // ============================================================

    private static final String PAGE_BG = "#FFFFFF";
    private static final String CARD = "#F7FAFC";
    private static final String BLUE = "#2864E8";
    private static final String DARK = "#142033";
    private static final String GREY = "#667085";
    private static final String BORDER = "#DCE4EA";
    private static final String LIGHT_BLUE = "#EAF2FF";
    private static final String ORANGE = "#FF7A18";

    // ============================================================
    // CONTROLS
    // ============================================================

    private ComboBox<String> vehicleBox;
    private ComboBox<String> problemBox;

    private Label costLabel;
    private Label repairTimeLabel;
    private Label partsLabel;

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public CostEstimatorPage() {
        buildUI();
    }

    // ============================================================
    // GET VIEW
    // ============================================================

    @Override
    public VBox getView() {
        return createRootView();
    }

    // ============================================================
    // ROOT VIEW
    // ============================================================

    private VBox createRootView() {

        VBox page =
                createPageContent();

        ScrollPane scrollPane =
                new ScrollPane(page);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background: #FFFFFF;" +
                        "-fx-border-color: transparent;"
        );

        VBox root =
                new VBox();

        root.setFillWidth(true);

        root.setStyle(
                "-fx-background-color: #FFFFFF;"
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
    // BUILD UI
    // ============================================================

    private void buildUI() {
        // UI is created through getView().
    }

    // ============================================================
    // PAGE CONTENT
    // ============================================================

    private VBox createPageContent() {

        VBox content =
                new VBox(22);

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
                "-fx-background-color: #FFFFFF;"
        );

        Label title =
                new Label(
                        "Repair Cost Estimator"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );

        title.setTextFill(
                Color.web(DARK)
        );

        Label subtitle =
                new Label(
                        "Estimate common repair costs based on " +
                                "your vehicle and problem"
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        subtitle.setTextFill(
                Color.web(GREY)
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        HBox main =
                new HBox(22);

        main.setAlignment(
                Pos.TOP_LEFT
        );

        main.setFillHeight(true);

        VBox formCard =
                createFormCard();

        HBox.setHgrow(
                formCard,
                Priority.ALWAYS
        );

        VBox rightColumn =
                new VBox(20);

        rightColumn.setPrefWidth(
                800
        );

        HBox.setHgrow(
                rightColumn,
                Priority.ALWAYS
        );

        VBox estimateCard =
                createEstimateCard();

        VBox centersCard =
                createCentersCard();

        rightColumn.getChildren().addAll(
                estimateCard,
                centersCard
        );

        main.getChildren().addAll(
                formCard,
                rightColumn
        );

        content.getChildren().addAll(
                heading,
                main
        );

        return content;
    }

    // ============================================================
    // FORM CARD
    // ============================================================

    private VBox createFormCard() {

        VBox card =
                new VBox(22);

        card.setPadding(
                new Insets(30)
        );

        card.setPrefWidth(
                520
        );

        card.setMinWidth(
                450
        );

        card.setPrefHeight(
                650
        );

        card.setStyle(
                cardStyle()
        );

        Label title =
                new Label(
                        "Tell us about the job"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        title.setTextFill(
                Color.web(DARK)
        );

        Label vehicleLabel =
                fieldLabel(
                        "Vehicle type"
                );

        vehicleBox =
                new ComboBox<>();

        vehicleBox.getItems().addAll(
                "Sedan",
                "Hatchback",
                "SUV",
                "MUV",
                "Pickup Truck",
                "Luxury Car"
        );

        vehicleBox.setValue(
                "Sedan"
        );

        styleCombo(
                vehicleBox
        );

        Label problemLabel =
                fieldLabel(
                        "Problem"
                );

        problemBox =
                new ComboBox<>();

        problemBox.getItems().addAll(
                "Battery replacement",
                "Engine repair",
                "Brake repair",
                "Flat tyre",
                "AC repair",
                "Clutch repair",
                "Electrical issue"
        );

        problemBox.setValue(
                "Battery replacement"
        );

        styleCombo(
                problemBox
        );

        Button recalculate =
                new Button(
                        "▤    Recalculate estimate"
                );

        recalculate.setMaxWidth(
                Double.MAX_VALUE
        );

        recalculate.setPrefHeight(
                46
        );

        recalculate.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        recalculate.setTextFill(
                Color.WHITE
        );

        recalculate.setStyle(
                "-fx-background-color: " +
                        BLUE +
                        ";" +
                        "-fx-background-radius: 24;" +
                        "-fx-cursor: hand;"
        );

        recalculate.setOnAction(
                event ->
                        calculateEstimate()
        );

        card.getChildren().addAll(
                title,
                vehicleLabel,
                vehicleBox,
                problemLabel,
                problemBox,
                recalculate
        );

        return card;
    }

    // ============================================================
    // ESTIMATE CARD
    // ============================================================

    private VBox createEstimateCard() {

        VBox card =
                new VBox(20);

        card.setPadding(
                new Insets(28)
        );

        card.setPrefHeight(
                275
        );

        card.setStyle(
                cardStyle()
        );

        Label smallTitle =
                new Label(
                        "ESTIMATED COST RANGE"
                );

        smallTitle.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        smallTitle.setTextFill(
                Color.web(GREY)
        );

        costLabel =
                new Label(
                        "₹5,200 – ₹9,500"
                );

        costLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        40
                )
        );

        costLabel.setTextFill(
                Color.web(DARK)
        );

        Region progressBackground =
                new Region();

        progressBackground.setPrefHeight(
                8
        );

        progressBackground.setMaxWidth(
                Double.MAX_VALUE
        );

        progressBackground.setStyle(
                "-fx-background-color: #EDF1F5;" +
                        "-fx-background-radius: 8;"
        );

        Region progress =
                new Region();

        progress.setPrefHeight(
                8
        );

        progress.setPrefWidth(
                435
        );

        progress.setStyle(
                "-fx-background-color: #4B8CF5;" +
                        "-fx-background-radius: 8;"
        );

        HBox progressBar =
                new HBox(
                        progressBackground
                );

        progressBar.setAlignment(
                Pos.CENTER_LEFT
        );

        progressBar.getChildren().add(
                progress
        );

        HBox details =
                new HBox(16);

        repairTimeLabel =
                new Label(
                        "45 min"
                );

        styleDetailValueLabel(
                repairTimeLabel
        );

        partsLabel =
                new Label(
                        "Battery 12V 45Ah, Terminal clamps"
                );

        styleDetailValueLabel(
                partsLabel
        );

        VBox repairTime =
                detailBox(
                        "◷",
                        "REPAIR TIME",
                        repairTimeLabel,
                        BLUE
                );

        VBox spareParts =
                detailBox(
                        "□",
                        "COMMON SPARE PARTS",
                        partsLabel,
                        ORANGE
                );

        HBox.setHgrow(
                repairTime,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                spareParts,
                Priority.ALWAYS
        );

        details.getChildren().addAll(
                repairTime,
                spareParts
        );

        card.getChildren().addAll(
                smallTitle,
                costLabel,
                progressBar,
                details
        );

        return card;
    }

    // ============================================================
    // DETAIL BOX
    // ============================================================

    private VBox detailBox(
            String icon,
            String title,
            Label valueLabel,
            String iconColor
    ) {

        VBox box =
                new VBox(6);

        box.setPadding(
                new Insets(15)
        );

        box.setPrefHeight(
                100
        );

        box.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 22;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 22;"
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
                Color.web(iconColor)
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        titleLabel.setTextFill(
                Color.web(GREY)
        );

        box.getChildren().addAll(
                iconLabel,
                titleLabel,
                valueLabel
        );

        return box;
    }

    // ============================================================
    // SERVICE CENTERS
    // ============================================================

    private VBox createCentersCard() {

        VBox card =
                new VBox(15);

        card.setPadding(
                new Insets(28)
        );

        card.setStyle(
                cardStyle()
        );

        Label title =
                new Label(
                        "▥  Nearby service centers"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        title.setTextFill(
                Color.web(DARK)
        );

        card.getChildren().add(
                title
        );

        card.getChildren().add(
                serviceCenter(
                        "SpeedFix Auto Care",
                        "1.2 km",
                        "4.9"
                )
        );

        card.getChildren().add(
                serviceCenter(
                        "Highway Motors",
                        "2.4 km",
                        "4.7"
                )
        );

        card.getChildren().add(
                serviceCenter(
                        "ElectroDrive Garage",
                        "3.1 km",
                        "4.8"
                )
        );

        return card;
    }

    // ============================================================
    // SERVICE CENTER ROW
    // ============================================================

    private HBox serviceCenter(
            String name,
            String distance,
            String rating
    ) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        13,
                        14,
                        13,
                        14
                )
        );

        row.setPrefHeight(
                72
        );

        row.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 22;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 22;"
        );

        Label nameLabel =
                new Label(
                        name
                );

        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        nameLabel.setTextFill(
                Color.web(DARK)
        );

        Label info =
                new Label(
                        "⌖ " + distance +
                                "     ★ " + rating
                );

        info.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        info.setTextFill(
                Color.web(GREY)
        );

        VBox details =
                new VBox(
                        5,
                        nameLabel,
                        info
                );

        Region space =
                new Region();

        HBox.setHgrow(
                details,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button book =
                new Button(
                        "Book slot"
                );

        book.setPrefSize(
                88,
                38
        );

        book.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        book.setTextFill(
                Color.web(DARK)
        );

        book.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-cursor: hand;"
        );

        book.setOnMouseEntered(
                event ->
                        book.setStyle(
                                "-fx-background-color: " +
                                        LIGHT_BLUE +
                                        ";" +
                                        "-fx-border-color: " +
                                        BORDER +
                                        ";" +
                                        "-fx-border-radius: 20;" +
                                        "-fx-background-radius: 20;" +
                                        "-fx-cursor: hand;"
                        )
        );

        book.setOnMouseExited(
                event ->
                        book.setStyle(
                                "-fx-background-color: white;" +
                                        "-fx-border-color: " +
                                        BORDER +
                                        ";" +
                                        "-fx-border-radius: 20;" +
                                        "-fx-background-radius: 20;" +
                                        "-fx-cursor: hand;"
                        )
        );

        book.setOnAction(
                event ->
                        book.setText(
                                "Requested"
                        )
        );

        row.getChildren().addAll(
                details,
                space,
                book
        );

        return row;
    }

    // ============================================================
    // CALCULATE ESTIMATE
    // ============================================================

    private void calculateEstimate() {

        String vehicle =
                vehicleBox.getValue();

        String problem =
                problemBox.getValue();

        if (
                vehicle == null ||
                        problem == null
        ) {
            return;
        }

        int min = 5200;
        int max = 9500;

        String time =
                "45 min";

        String parts =
                "Battery 12V 45Ah, Terminal clamps";

        if (
                problem.equals(
                        "Engine repair"
                )
        ) {

            min = 8500;
            max = 18000;
            time = "2–4 hrs";
            parts =
                    "Spark plugs, filters, engine oil";

        } else if (
                problem.equals(
                        "Brake repair"
                )
        ) {

            min = 3200;
            max = 7800;
            time = "1–2 hrs";
            parts =
                    "Brake pads, discs, brake fluid";

        } else if (
                problem.equals(
                        "Flat tyre"
                )
        ) {

            min = 700;
            max = 2200;
            time = "20–40 min";
            parts =
                    "Tyre, valve, puncture kit";

        } else if (
                problem.equals(
                        "AC repair"
                )
        ) {

            min = 2500;
            max = 7000;
            time = "1–2 hrs";
            parts =
                    "AC gas, filter, compressor parts";

        } else if (
                problem.equals(
                        "Clutch repair"
                )
        ) {

            min = 6500;
            max = 14000;
            time = "3–5 hrs";
            parts =
                    "Clutch plate, pressure plate";

        } else if (
                problem.equals(
                        "Electrical issue"
                )
        ) {

            min = 1800;
            max = 5500;
            time = "1–2 hrs";
            parts =
                    "Fuse, wiring, battery terminals";
        }

        if (
                vehicle.equals("SUV") ||
                        vehicle.equals("MUV")
        ) {

            min += 800;
            max += 1500;
        }

        costLabel.setText(
                "₹" +
                        format(min) +
                        " – ₹" +
                        format(max)
        );

        repairTimeLabel.setText(
                time
        );

        partsLabel.setText(
                parts
        );
    }

    // ============================================================
    // FORMAT NUMBER
    // ============================================================

    private String format(
            int value
    ) {

        return String.format(
                java.util.Locale.US,
                "%,d",
                value
        );
    }

    // ============================================================
    // FIELD LABEL
    // ============================================================

    private Label fieldLabel(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        label.setTextFill(
                Color.web(DARK)
        );

        return label;
    }

    // ============================================================
    // COMBO BOX STYLE
    // ============================================================

    private void styleCombo(
            ComboBox<String> combo
    ) {

        combo.setPrefHeight(
                50
        );

        combo.setMaxWidth(
                Double.MAX_VALUE
        );

        combo.setStyle(
                "-fx-background-color: #F8FAFC;" +
                        "-fx-background-radius: 25;" +
                        "-fx-border-color: #DCE4EA;" +
                        "-fx-border-radius: 25;" +
                        "-fx-font-size: 15px;" +
                        "-fx-padding: 0 12 0 12;"
        );
    }

    // ============================================================
    // CARD STYLE
    // ============================================================

    private String cardStyle() {

        return
                "-fx-background-color: " +
                        CARD +
                        ";" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 24;" +
                        "-fx-border-width: 1;";
    }

    // ============================================================
    // DETAIL VALUE STYLE
    // ============================================================

    private void styleDetailValueLabel(
            Label label
    ) {

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        label.setTextFill(
                Color.web(DARK)
        );

        label.setWrapText(
                true
        );
    }
}
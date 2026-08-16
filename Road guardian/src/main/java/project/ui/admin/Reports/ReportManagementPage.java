package project.ui.admin.Reports;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ReportManagementPage {

    // =========================================================
    // ROADGUARDIAN THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String BLUE = "#2563EB";
    private static final String BLUE_HOVER = "#1D4ED8";

    private static final String GREEN = "#16A34A";

    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";

    private static final String RED = "#DC2626";
    private static final String PURPLE = "#7C3AED";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    // =========================================================
    // MAIN ROOT
    // =========================================================

    /*
     * IMPORTANT:
     *
     * This root remains the SAME VBox.
     *
     * When we navigate to another page, we only replace
     * the children of this root.
     *
     * So no new Stage.
     * No new Window.
     * No Dialog.
     */

    private final VBox root = new VBox();

    // =========================================================
    // DATA
    // =========================================================

    private final ObservableList<ReportData> reports =
            FXCollections.observableArrayList();

    private final ObservableList<ReportData> filteredReports =
            FXCollections.observableArrayList();

    private VBox reportCards;

    private TextField searchField;

    private ComboBox<String> typeFilter;

    private ComboBox<String> sortFilter;

    private Label resultCountLabel;

    private Label totalLabel;

    private Label serviceLabel;

    private Label sosLabel;

    private Label otherLabel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ReportManagementPage() {

        loadSampleReports();

        showManagementPage();
    }

    // =========================================================
    // MAIN VIEW
    // =========================================================

    public VBox getView() {

        return root;
    }

    // =========================================================
    // MANAGEMENT PAGE
    // =========================================================

    private void showManagementPage() {

        VBox managementPage =
                createManagementPage();

        root.getChildren().setAll(
                managementPage
        );
    }

    // =========================================================
    // MANAGEMENT CONTENT
    // =========================================================

    private VBox createManagementPage() {

        VBox page =
                new VBox(20);

        page.setPadding(
                new Insets(
                        30,
                        32,
                        32,
                        32
                )
        );

        page.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";"
        );

        HBox header =
                createHeader();

        HBox statistics =
                createStatistics();

        HBox toolbar =
                createToolbar();

        VBox recordsCard =
                createRecordsCard();

        page.getChildren().addAll(
                header,
                statistics,
                toolbar,
                recordsCard
        );

        VBox.setVgrow(
                recordsCard,
                Priority.ALWAYS
        );

        updateStatistics();

        applyFilters();

        return page;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private HBox createHeader() {

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox =
                new VBox(5);

        Label title =
                new Label(
                        "Reports"
                );

        title.setTextFill(
                Color.web(HEADING)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        Label subtitle =
                new Label(
                        "Track RoadGuardian activity and operational reports in one place."
                );

        subtitle.setTextFill(
                Color.web(TEXT)
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button generateButton =
                createPrimaryButton(
                        "+ Generate Report"
                );

        generateButton.setOnAction(
                event ->
                        showGenerateReportPage()
        );

        header.getChildren().addAll(
                titleBox,
                spacer,
                generateButton
        );

        return header;
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private HBox createStatistics() {

        HBox box =
                new HBox(16);

        totalLabel =
                createValueLabel(
                        BLUE
                );

        serviceLabel =
                createValueLabel(
                        GREEN
                );

        sosLabel =
                createValueLabel(
                        RED
                );

        otherLabel =
                createValueLabel(
                        PURPLE
                );

        VBox total =
                createStatCard(
                        "Total Reports",
                        "All generated reports",
                        totalLabel,
                        BLUE,
                        "TOTAL"
                );

        VBox service =
                createStatCard(
                        "Service Reports",
                        "Roadside service activity",
                        serviceLabel,
                        GREEN,
                        "SERVICE"
                );

        VBox sos =
                createStatCard(
                        "SOS Reports",
                        "Emergency activity",
                        sosLabel,
                        RED,
                        "SOS"
                );

        VBox other =
                createStatCard(
                        "Other Reports",
                        "Customer, mechanic & finance",
                        otherLabel,
                        PURPLE,
                        "OTHER"
                );

        HBox.setHgrow(
                total,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                service,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                sos,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                other,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                total,
                service,
                sos,
                other
        );

        return box;
    }

    // =========================================================
    // VALUE LABEL
    // =========================================================

    private Label createValueLabel(
            String color
    ) {

        Label label =
                new Label(
                        "0"
                );

        label.setTextFill(
                Color.web(color)
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        return label;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String title,
            String subtitle,
            Label value,
            String color,
            String tagText
    ) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(
                        18,
                        20,
                        17,
                        20
                )
        );

        card.setMinHeight(
                118
        );

        String normalStyle =
                "-fx-background-color: " +
                        SURFACE +
                        ";" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;";

        String hoverStyle =
                "-fx-background-color: #F7FCFC;" +
                        "-fx-border-color: " +
                        color +
                        ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;";

        card.setStyle(
                normalStyle
        );

        HBox top =
                new HBox();

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setTextFill(
                Color.web(HEADING)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label tag =
                new Label(
                        tagText
                );

        tag.setTextFill(
                Color.web(color)
        );

        tag.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        tag.setPadding(
                new Insets(
                        6,
                        9,
                        6,
                        9
                )
        );

        tag.setStyle(
                "-fx-background-color: " +
                        color +
                        "18;" +
                        "-fx-background-radius: 20;"
        );

        top.getChildren().addAll(
                titleLabel,
                spacer,
                tag
        );

        Label subtitleLabel =
                new Label(
                        subtitle
                );

        subtitleLabel.setTextFill(
                Color.web(TEXT)
        );

        subtitleLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        card.getChildren().addAll(
                top,
                value,
                subtitleLabel
        );

        card.setOnMouseEntered(
                event -> {

                    card.setStyle(
                            hoverStyle
                    );

                    card.setTranslateY(
                            -2
                    );
                }
        );

        card.setOnMouseExited(
                event -> {

                    card.setStyle(
                            normalStyle
                    );

                    card.setTranslateY(
                            0
                    );
                }
        );

        return card;
    }

    // =========================================================
    // TOOLBAR
    // =========================================================

    private HBox createToolbar() {

        HBox toolbar =
                new HBox(12);

        toolbar.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField =
                new TextField();

        searchField.setPromptText(
                "Search report name, ID or generated by..."
        );

        searchField.setPrefHeight(
                44
        );

        searchField.setPrefWidth(
                420
        );

        styleTextField(
                searchField
        );

        typeFilter =
                new ComboBox<>();

        typeFilter.getItems().addAll(
                "All Reports",
                "Service",
                "SOS",
                "Customer",
                "Mechanic",
                "Financial"
        );

        typeFilter.setValue(
                "All Reports"
        );

        typeFilter.setPrefHeight(
                44
        );

        typeFilter.setPrefWidth(
                155
        );

        styleComboBox(
                typeFilter
        );

        sortFilter =
                new ComboBox<>();

        sortFilter.getItems().addAll(
                "Newest First",
                "Oldest First",
                "Report A-Z",
                "Report Z-A",
                "Most Records"
        );

        sortFilter.setValue(
                "Newest First"
        );

        sortFilter.setPrefHeight(
                44
        );

        sortFilter.setPrefWidth(
                150
        );

        styleComboBox(
                sortFilter
        );

        Button refresh =
                createSecondaryButton(
                        "Refresh"
                );

        refresh.setPrefHeight(
                44
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        searchField.textProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        typeFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        sortFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        refresh.setOnAction(
                event -> {

                    updateStatistics();

                    applyFilters();
                }
        );

        toolbar.getChildren().addAll(
                searchField,
                typeFilter,
                sortFilter,
                refresh,
                spacer
        );

        return toolbar;
    }

    // =========================================================
    // RECORDS CARD
    // =========================================================

    private VBox createRecordsCard() {

        VBox card =
                new VBox(15);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: " +
                        SURFACE +
                        ";" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;"
        );

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox =
                new VBox(4);

        Label title =
                new Label(
                        "Report Records"
                );

        title.setTextFill(
                Color.web(HEADING)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        Label subtitle =
                new Label(
                        "Generated reports for RoadGuardian operations."
                );

        subtitle.setTextFill(
                Color.web(TEXT)
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        resultCountLabel =
                new Label(
                        "0 reports"
                );

        resultCountLabel.setTextFill(
                Color.web(BLUE)
        );

        resultCountLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        titleBox.getChildren().addAll(
                title,
                subtitle,
                resultCountLabel
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label live =
                new Label(
                        "● Report Center"
                );

        live.setTextFill(
                Color.web(GREEN)
        );

        live.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        live.setPadding(
                new Insets(
                        7,
                        11,
                        7,
                        11
                )
        );

        live.setStyle(
                "-fx-background-color: " +
                        GREEN +
                        "18;" +
                        "-fx-background-radius: 20;"
        );

        heading.getChildren().addAll(
                titleBox,
                spacer,
                live
        );

        reportCards =
                new VBox(12);

        reportCards.setFillWidth(
                true
        );

        reportCards.setPadding(
                new Insets(
                        3,
                        2,
                        10,
                        2
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        reportCards
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setPannable(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                heading,
                scrollPane
        );

        return card;
    }

    // =========================================================
    // REPORT CARD
    // =========================================================

    private VBox createReportCard(
            ReportData report,
            int number
    ) {

        VBox card =
                new VBox(13);

        card.setPadding(
                new Insets(
                        18,
                        20,
                        18,
                        20
                )
        );

        card.setCursor(
                Cursor.HAND
        );

        String normalStyle =
                "-fx-background-color: " +
                        SECONDARY +
                        ";" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;";

        String hoverStyle =
                "-fx-background-color: #EAF8FA;" +
                        "-fx-border-color: " +
                        BLUE +
                        ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;";

        card.setStyle(
                normalStyle
        );

        HBox top =
                new HBox(13);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox icon =
                createReportIcon(
                        report.getType()
                );

        VBox identity =
                new VBox(5);

        HBox idLine =
                new HBox(9);

        idLine.setAlignment(
                Pos.CENTER_LEFT
        );

        Label serial =
                new Label(
                        "#" + number
                );

        serial.setTextFill(
                Color.web(BLUE)
        );

        serial.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        serial.setPadding(
                new Insets(
                        4,
                        8,
                        4,
                        8
                )
        );

        serial.setStyle(
                "-fx-background-color: " +
                        BLUE +
                        "14;" +
                        "-fx-background-radius: 20;"
        );

        Label id =
                new Label(
                        report.getId()
                );

        id.setTextFill(
                Color.web(TEXT)
        );

        id.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        idLine.getChildren().addAll(
                serial,
                id
        );

        Label name =
                new Label(
                        report.getName()
                );

        name.setTextFill(
                Color.web(HEADING)
        );

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        identity.getChildren().addAll(
                idLine,
                name
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label typeBadge =
                createTypeBadge(
                        report.getType()
                );

        top.getChildren().addAll(
                icon,
                identity,
                spacer,
                typeBadge
        );

        HBox infoRow =
                new HBox(12);

        VBox generatedBy =
                createInfoBox(
                        "GENERATED BY",
                        report.getGeneratedBy()
                );

        VBox date =
                createInfoBox(
                        "GENERATED DATE",
                        report.getDate()
                );

        VBox records =
                createInfoBox(
                        "RECORDS",
                        report.getRecords()
                );

        HBox.setHgrow(
                generatedBy,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                date,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                records,
                Priority.ALWAYS
        );

        infoRow.getChildren().addAll(
                generatedBy,
                date,
                records
        );

        HBox bottom =
                new HBox(10);

        bottom.setAlignment(
                Pos.CENTER_RIGHT
        );

        Region bottomSpacer =
                new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );

        Button view =
                createActionButton(
                        "View Report",
                        BLUE
                );

        Button delete =
                createActionButton(
                        "Delete",
                        RED
                );

        view.setOnAction(
                event ->
                        showReport(
                                report
                        )
        );

        delete.setOnAction(
                event ->
                        deleteReport(
                                report
                        )
        );

        bottom.getChildren().addAll(
                bottomSpacer,
                view,
                delete
        );

        card.getChildren().addAll(
                top,
                new Separator(),
                infoRow,
                bottom
        );

        card.setOnMouseEntered(
                event -> {

                    card.setStyle(
                            hoverStyle
                    );

                    card.setTranslateY(
                            -2
                    );
                }
        );

        card.setOnMouseExited(
                event -> {

                    card.setStyle(
                            normalStyle
                    );

                    card.setTranslateY(
                            0
                    );
                }
        );

        /*
         * Double click also opens details.
         */

        card.setOnMouseClicked(
                event -> {

                    if (
                            event.getClickCount() == 2
                    ) {

                        showReport(
                                report
                        );
                    }
                }
        );

        return card;
    }

    // =========================================================
    // REPORT ICON
    // =========================================================

    private VBox createReportIcon(
            String type
    ) {

        String color;

        String iconText;

        if (
                "SOS".equalsIgnoreCase(type)
        ) {

            color = RED;
            iconText = "!";

        } else if (
                "Service".equalsIgnoreCase(type)
        ) {

            color = GREEN;
            iconText = "⚙";

        } else if (
                "Financial".equalsIgnoreCase(type)
        ) {

            color = ORANGE;
            iconText = "₹";

        } else if (
                "Mechanic".equalsIgnoreCase(type)
        ) {

            color = PURPLE;
            iconText = "M";

        } else {

            color = BLUE;
            iconText = "R";
        }

        VBox box =
                new VBox();

        box.setAlignment(
                Pos.CENTER
        );

        box.setMinSize(
                50,
                50
        );

        box.setPrefSize(
                50,
                50
        );

        box.setMaxSize(
                50,
                50
        );

        box.setStyle(
                "-fx-background-color: " +
                        color +
                        ";" +
                        "-fx-background-radius: 12;"
        );

        Label icon =
                new Label(
                        iconText
                );

        icon.setTextFill(
                Color.WHITE
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        box.getChildren().add(
                icon
        );

        return box;
    }

    // =========================================================
    // TYPE BADGE
    // =========================================================

    private Label createTypeBadge(
            String type
    ) {

        String color;

        String background;

        if (
                "SOS".equalsIgnoreCase(type)
        ) {

            color = RED;
            background = "#FEE2E2";

        } else if (
                "Service".equalsIgnoreCase(type)
        ) {

            color = GREEN;
            background = "#DCFCE7";

        } else if (
                "Financial".equalsIgnoreCase(type)
        ) {

            color = ORANGE;
            background = "#FFEDD5";

        } else if (
                "Mechanic".equalsIgnoreCase(type)
        ) {

            color = PURPLE;
            background = "#EDE9FE";

        } else {

            color = BLUE;
            background = "#DBEAFE";
        }

        Label badge =
                new Label(
                        type
                );

        badge.setTextFill(
                Color.web(color)
        );

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        badge.setPadding(
                new Insets(
                        7,
                        12,
                        7,
                        12
                )
        );

        badge.setStyle(
                "-fx-background-color: " +
                        background +
                        ";" +
                        "-fx-background-radius: 20;"
        );

        return badge;
    }

    // =========================================================
    // INFO BOX
    // =========================================================

    private VBox createInfoBox(
            String title,
            String value
    ) {

        VBox box =
                new VBox(5);

        box.setPadding(
                new Insets(
                        10,
                        12,
                        10,
                        12
                )
        );

        box.setStyle(
                "-fx-background-color: " +
                        SURFACE +
                        ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 9;"
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setTextFill(
                Color.web(TEXT)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        Label valueLabel =
                new Label(
                        value
                );

        valueLabel.setTextFill(
                Color.web(HEADING)
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        valueLabel.setWrapText(
                true
        );

        box.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return box;
    }

    // =========================================================
    // BUTTONS
    // =========================================================

    private Button createPrimaryButton(
            String text
    ) {

        Button button =
                new Button(
                        text
                );

        button.setTextFill(
                Color.WHITE
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        button.setPadding(
                new Insets(
                        11,
                        19,
                        11,
                        19
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        setButtonBackground(
                button,
                ORANGE
        );

        button.setOnMouseEntered(
                event ->
                        setButtonBackground(
                                button,
                                ORANGE_HOVER
                        )
        );

        button.setOnMouseExited(
                event ->
                        setButtonBackground(
                                button,
                                ORANGE
                        )
        );

        return button;
    }

    private Button createSecondaryButton(
            String text
    ) {

        Button button =
                new Button(
                        text
                );

        button.setTextFill(
                Color.WHITE
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        setButtonBackground(
                button,
                BLUE
        );

        button.setOnMouseEntered(
                event ->
                        setButtonBackground(
                                button,
                                BLUE_HOVER
                        )
        );

        button.setOnMouseExited(
                event ->
                        setButtonBackground(
                                button,
                                BLUE
                        )
        );

        return button;
    }

    private Button createActionButton(
            String text,
            String color
    ) {

        Button button =
                new Button(
                        text
                );

        button.setTextFill(
                Color.web(color)
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        button.setPadding(
                new Insets(
                        8,
                        15,
                        8,
                        15
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        String background =
                color.equals(RED)
                        ? "#FEE2E2"
                        : "#DBEAFE";

        String hover =
                color.equals(RED)
                        ? "#FECACA"
                        : "#BFDBFE";

        button.setStyle(
                "-fx-background-color: " +
                        background +
                        ";" +
                        "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                        hover +
                                        ";" +
                                        "-fx-background-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                        background +
                                        ";" +
                                        "-fx-background-radius: 8;"
                        )
        );

        return button;
    }

    private void setButtonBackground(
            Button button,
            String color
    ) {

        button.setStyle(
                "-fx-background-color: " +
                        color +
                        ";" +
                        "-fx-background-radius: 9;"
        );
    }

    // =========================================================
    // FILTER
    // =========================================================

    private void applyFilters() {

        String search =
                searchField == null
                        ? ""
                        : searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        String type =
                typeFilter == null
                        ? "All Reports"
                        : typeFilter.getValue();

        String sort =
                sortFilter == null
                        ? "Newest First"
                        : sortFilter.getValue();

        filteredReports.clear();

        for (
                ReportData report :
                reports
        ) {

            boolean searchMatch =
                    search.isEmpty()
                            ||
                            report.getId()
                                    .toLowerCase()
                                    .contains(search)
                            ||
                            report.getName()
                                    .toLowerCase()
                                    .contains(search)
                            ||
                            report.getGeneratedBy()
                                    .toLowerCase()
                                    .contains(search);

            boolean typeMatch =
                    type == null
                            ||
                            type.equals(
                                    "All Reports"
                            )
                            ||
                            report.getType()
                                    .equalsIgnoreCase(
                                            type
                                    );

            if (
                    searchMatch &&
                            typeMatch
            ) {

                filteredReports.add(
                        report
                );
            }
        }

        sortReports(
                sort
        );

        renderReports();
    }

    // =========================================================
    // SORT
    // =========================================================

    private void sortReports(
            String sort
    ) {

        if (
                "Report A-Z".equals(sort)
        ) {

            filteredReports.sort(
                    (a, b) ->
                            a.getName()
                                    .compareToIgnoreCase(
                                            b.getName()
                                    )
            );

        } else if (
                "Report Z-A".equals(sort)
        ) {

            filteredReports.sort(
                    (a, b) ->
                            b.getName()
                                    .compareToIgnoreCase(
                                            a.getName()
                                    )
            );

        } else if (
                "Most Records".equals(sort)
        ) {

            filteredReports.sort(
                    (a, b) ->
                            Integer.compare(
                                    getRecordNumber(
                                            b.getRecords()
                                    ),
                                    getRecordNumber(
                                            a.getRecords()
                                    )
                            )
            );

        } else if (
                "Oldest First".equals(sort)
        ) {

            filteredReports.sort(
                    (a, b) ->
                            a.getDate()
                                    .compareTo(
                                            b.getDate()
                                    )
            );

        } else {

            filteredReports.sort(
                    (a, b) ->
                            b.getDate()
                                    .compareTo(
                                            a.getDate()
                                    )
            );
        }
    }

    // =========================================================
    // RECORD NUMBER
    // =========================================================

    private int getRecordNumber(
            String records
    ) {

        try {

            return Integer.parseInt(
                    records
                            .replace(
                                    ",",
                                    ""
                            )
                            .trim()
            );

        } catch (
                NumberFormatException e
        ) {

            return 0;
        }
    }

    // =========================================================
    // RENDER
    // =========================================================

    private void renderReports() {

        if (reportCards == null) {

            return;
        }

        reportCards
                .getChildren()
                .clear();

        if (
                filteredReports.isEmpty()
        ) {

            reportCards
                    .getChildren()
                    .add(
                            createEmptyState()
                    );

            if (
                    resultCountLabel != null
            ) {

                resultCountLabel.setText(
                        "0 reports found"
                );
            }

            return;
        }

        int number = 1;

        for (
                ReportData report :
                filteredReports
        ) {

            reportCards
                    .getChildren()
                    .add(
                            createReportCard(
                                    report,
                                    number++
                            )
                    );
        }

        if (
                resultCountLabel != null
        ) {

            resultCountLabel.setText(
                    filteredReports.size() == 1
                            ? "1 report"
                            : filteredReports.size()
                            + " reports"
            );
        }
    }

    // =========================================================
    // SAMPLE DATA
    // =========================================================

    private void loadSampleReports() {

        if (!reports.isEmpty()) {

            return;
        }

        reports.addAll(

                new ReportData(
                        "R001",
                        "Monthly Service Report",
                        "Service",
                        "Admin",
                        "2026-08-13",
                        "25"
                ),

                new ReportData(
                        "R002",
                        "SOS Activity Report",
                        "SOS",
                        "Admin",
                        "2026-08-13",
                        "8"
                ),

                new ReportData(
                        "R003",
                        "Customer Activity Report",
                        "Customer",
                        "Admin",
                        "2026-08-12",
                        "42"
                ),

                new ReportData(
                        "R004",
                        "Mechanic Performance Report",
                        "Mechanic",
                        "Admin",
                        "2026-08-12",
                        "18"
                ),

                new ReportData(
                        "R005",
                        "Financial Summary",
                        "Financial",
                        "Admin",
                        "2026-08-11",
                        "30"
                ),

                new ReportData(
                        "R006",
                        "Weekly Service Summary",
                        "Service",
                        "Admin",
                        "2026-08-10",
                        "37"
                )
        );
    }

    // =========================================================
    // STATISTICS UPDATE
    // =========================================================

    private void updateStatistics() {

        if (
                totalLabel == null ||
                        serviceLabel == null ||
                        sosLabel == null ||
                        otherLabel == null
        ) {

            return;
        }

        int total =
                reports.size();

        int service = 0;

        int sos = 0;

        for (
                ReportData report :
                reports
        ) {

            if (
                    report.getType()
                            .equalsIgnoreCase(
                                    "Service"
                            )
            ) {

                service++;
            }

            if (
                    report.getType()
                            .equalsIgnoreCase(
                                    "SOS"
                            )
            ) {

                sos++;
            }
        }

        int other =
                total -
                        service -
                        sos;

        totalLabel.setText(
                String.valueOf(
                        total
                )
        );

        serviceLabel.setText(
                String.valueOf(
                        service
                )
        );

        sosLabel.setText(
                String.valueOf(
                        sos
                )
        );

        otherLabel.setText(
                String.valueOf(
                        other
                )
        );
    }

    // =========================================================
    // VIEW REPORT
    // =========================================================

    private void showReport(
            ReportData report
    ) {

        /*
         * IMPORTANT:
         *
         * No Alert.
         * No Dialog.
         * No new Stage.
         *
         * Direct navigation to ReportDetailsPage.
         */

        ReportDetailsPage page =
                new ReportDetailsPage(
                        report,
                        () ->
                                showManagementPage(),
                        () ->
                                showPreviewPage(
                                        report
                                )
                );

        root.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // PREVIEW PAGE
    // =========================================================

    private void showPreviewPage(
            ReportData report
    ) {

        ReportPreviewPage page =
                new ReportPreviewPage(
                        report,
                        () ->
                                showReport(
                                        report
                                )
                );

        root.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // GENERATE REPORT PAGE
    // =========================================================

    private void showGenerateReportPage() {

        GenerateReportPage page =
                new GenerateReportPage(
                        () ->
                                showManagementPage()
                );

        root.getChildren().setAll(
                page.getView()
        );
    }

    // =========================================================
    // DELETE REPORT
    // =========================================================

    private void deleteReport(
            ReportData report
    ) {

        Alert confirm =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirm.setTitle(
                "Delete Report"
        );

        confirm.setHeaderText(
                "Delete this report?"
        );

        confirm.setContentText(
                report.getName() +
                        "\n\nReport ID: " +
                        report.getId()
        );

        confirm.showAndWait()
                .ifPresent(
                        result -> {

                            if (
                                    result ==
                                            ButtonType.OK
                            ) {

                                reports.remove(
                                        report
                                );

                                updateStatistics();

                                applyFilters();
                            }
                        }
                );
    }

    // =========================================================
    // NEXT REPORT ID
    // =========================================================

    private String createNextReportId() {

        int max = 0;

        for (
                ReportData report :
                reports
        ) {

            String id =
                    report.getId();

            if (
                    id != null &&
                            id.startsWith("R")
            ) {

                try {

                    int number =
                            Integer.parseInt(
                                    id.substring(1)
                            );

                    max =
                            Math.max(
                                    max,
                                    number
                            );

                } catch (
                        NumberFormatException ignored
                ) {
                }
            }
        }

        return String.format(
                "R%03d",
                max + 1
        );
    }

    // =========================================================
    // EMPTY STATE
    // =========================================================

    private VBox createEmptyState() {

        VBox box =
                new VBox(9);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(45)
        );

        Label icon =
                new Label(
                        "▤"
                );

        icon.setTextFill(
                Color.web(BLUE)
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );

        Label title =
                new Label(
                        "No reports found"
                );

        title.setTextFill(
                Color.web(HEADING)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        Label subtitle =
                new Label(
                        "Try changing your search or report type filter."
                );

        subtitle.setTextFill(
                Color.web(TEXT)
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        box.getChildren().addAll(
                icon,
                title,
                subtitle
        );

        return box;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            TextField field
    ) {

        field.setStyle(
                "-fx-background-color: " +
                        SURFACE +
                        ";" +
                        "-fx-text-fill: " +
                        HEADING +
                        ";" +
                        "-fx-prompt-text-fill: " +
                        TEXT +
                        ";" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-padding: 0 14 0 14;" +
                        "-fx-font-size: 15px;"
        );
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            ComboBox<String> combo
    ) {

        combo.setStyle(
                "-fx-background-color: " +
                        SURFACE +
                        ";" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 14px;"
        );
    }

    // =========================================================
    // MODEL
    // =========================================================

    public static class ReportData {

        private String id;

        private String name;

        private String type;

        private String generatedBy;

        private String date;

        private String records;

        public ReportData(
                String id,
                String name,
                String type,
                String generatedBy,
                String date,
                String records
        ) {

            this.id =
                    id;

            this.name =
                    name;

            this.type =
                    type;

            this.generatedBy =
                    generatedBy;

            this.date =
                    date;

            this.records =
                    records;
        }

        public String getId() {

            return id;
        }

        public String getName() {

            return name;
        }

        public String getType() {

            return type;
        }

        public String getGeneratedBy() {

            return generatedBy;
        }

        public String getDate() {

            return date;
        }

        public String getRecords() {

            return records;
        }
    }
}
package project.ui.admin.Complaints;

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

public class ComplaintManagementPage {

    // =========================================================
    // ROADGUARDIAN THEME
    // =========================================================

    private static final String BG = "#D6F0F3";

    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";

    private static final String BLUE = "#2563EB";
    private static final String BLUE_HOVER = "#1D4ED8";

    private static final String GREEN = "#16A34A";
    private static final String RED = "#DC2626";
    private static final String YELLOW = "#D97706";
    private static final String PURPLE = "#7C3AED";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    // =========================================================
    // DATA
    // =========================================================

    private final ObservableList<ComplaintData> complaints =
            FXCollections.observableArrayList();

    private final ObservableList<ComplaintData> filteredComplaints =
            FXCollections.observableArrayList();

    private VBox complaintCards;

    private TextField searchField;
    private ComboBox<String> statusFilter;
    private ComboBox<String> priorityFilter;
    private ComboBox<String> sortFilter;

    private Label resultCountLabel;
    private VBox root;

    private Label totalLabel;
    private Label pendingLabel;
    private Label resolvedLabel;
    private Label highPriorityLabel;

    // =========================================================
    // MAIN VIEW
    // =========================================================

    public VBox getView() {

        if (root == null) {

            root = new VBox(20);
        }

        root.getChildren().clear();

        root.setPadding(
                new Insets(30, 32, 32, 32)
        );

        root.setStyle(
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

        root.getChildren().addAll(
                header,
                statistics,
                toolbar,
                recordsCard
        );

        VBox.setVgrow(
                recordsCard,
                Priority.ALWAYS
        );

        loadSampleComplaints();

        updateStatistics();

        applyFilters();

        return root;
    }
    // =========================================================
// OPEN COMPLAINT DETAILS
// =========================================================

    private void openComplaintDetails(
            ComplaintData data
    ) {

        ComplaintDetailsPage page =
                new ComplaintDetailsPage(

                        data,

                        // BACK
                        this::showManagementPage,

                        // UPDATE STATUS
                        () ->
                                openUpdateStatusPage(
                                        data
                                )
                );

        navigateTo(
                page.getView()
        );
    }


// =========================================================
// OPEN UPDATE STATUS PAGE
// =========================================================

    private void openUpdateStatusPage(
            ComplaintData data
    ) {

        UpdateComplaintStatusPage page =
                new UpdateComplaintStatusPage(

                        data,

                        // BACK
                        () ->
                                openComplaintDetails(
                                        data
                                ),

                        // UPDATED
                        () -> {

                            updateStatistics();

                            openComplaintDetails(
                                    data
                            );
                        }
                );

        navigateTo(
                page.getView()
        );
    }
    // =========================================================
// SHOW MANAGEMENT PAGE
// =========================================================

    private void showManagementPage() {

        getView();
    }

    // =========================================================
// NAVIGATION
// =========================================================

    private void navigateTo(
            VBox page
    ) {

        root.getChildren().clear();

        root.setPadding(
                Insets.EMPTY
        );

        root.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";"
        );

        root.getChildren().add(
                page
        );

        VBox.setVgrow(
                page,
                Priority.ALWAYS
        );
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
                        "Complaints"
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
                        "Review customer complaints and keep every issue moving toward resolution."
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

        Button refresh =
                createSecondaryButton(
                        "Refresh"
                );

        refresh.setOnAction(
                event -> {

                    loadSampleComplaints();

                    updateStatistics();

                    applyFilters();
                }
        );

        header.getChildren().addAll(
                titleBox,
                spacer,
                refresh
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

        pendingLabel =
                createValueLabel(
                        YELLOW
                );

        resolvedLabel =
                createValueLabel(
                        GREEN
                );

        highPriorityLabel =
                createValueLabel(
                        RED
                );

        VBox total =
                createStatCard(
                        "Total Complaints",
                        "All customer complaints",
                        totalLabel,
                        BLUE,
                        "TOTAL"
                );

        VBox pending =
                createStatCard(
                        "Pending",
                        "Needs attention",
                        pendingLabel,
                        YELLOW,
                        "PENDING"
                );

        VBox resolved =
                createStatCard(
                        "Resolved",
                        "Successfully closed",
                        resolvedLabel,
                        GREEN,
                        "RESOLVED"
                );

        VBox high =
                createStatCard(
                        "High Priority",
                        "Requires quick action",
                        highPriorityLabel,
                        RED,
                        "URGENT"
                );

        HBox.setHgrow(
                total,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                pending,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                resolved,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                high,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                total,
                pending,
                resolved,
                high
        );

        return box;
    }

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
                "-fx-background-radius: 13;" +
                "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.14), 10, 0.12, 0, 2);";

        card.setStyle(
                normalStyle
        );

        card.setCursor(
                Cursor.HAND
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
                "Search complaint, customer, subject or ID..."
        );

        searchField.setPrefHeight(
                44
        );

        searchField.setPrefWidth(
                390
        );

        styleTextField(
                searchField
        );

        statusFilter =
                new ComboBox<>();

        statusFilter.getItems().addAll(
                "All Status",
                "Pending",
                "In Progress",
                "Resolved",
                "Rejected"
        );

        statusFilter.setValue(
                "All Status"
        );

        statusFilter.setPrefHeight(
                44
        );

        statusFilter.setPrefWidth(
                145
        );

        styleComboBox(
                statusFilter
        );

        priorityFilter =
                new ComboBox<>();

        priorityFilter.getItems().addAll(
                "All Priority",
                "High",
                "Medium",
                "Low"
        );

        priorityFilter.setValue(
                "All Priority"
        );

        priorityFilter.setPrefHeight(
                44
        );

        priorityFilter.setPrefWidth(
                140
        );

        styleComboBox(
                priorityFilter
        );

        sortFilter =
                new ComboBox<>();

        sortFilter.getItems().addAll(
                "Newest First",
                "Oldest First",
                "High Priority",
                "Low Priority",
                "Customer A-Z"
        );

        sortFilter.setValue(
                "Newest First"
        );

        sortFilter.setPrefHeight(
                44
        );

        sortFilter.setPrefWidth(
                145
        );

        styleComboBox(
                sortFilter
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

        statusFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        priorityFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        sortFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        toolbar.getChildren().addAll(
                searchField,
                statusFilter,
                priorityFilter,
                sortFilter,
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
                        "Complaint Records"
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
                        "Customer complaints submitted through RoadGuardian."
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
                        "0 complaints"
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
                        "● Complaint Center"
                );

        live.setTextFill(
                Color.web(ORANGE)
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
                ORANGE +
                "18;" +
                "-fx-background-radius: 20;"
        );

        heading.getChildren().addAll(
                titleBox,
                spacer,
                live
        );

        complaintCards =
                new VBox(12);

        complaintCards.setFillWidth(
                true
        );

        complaintCards.setPadding(
                new Insets(
                        3,
                        2,
                        10,
                        2
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        complaintCards
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
    // COMPLAINT CARD
    // =========================================================

    private VBox createComplaintCard(
            ComplaintData data,
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
                ORANGE +
                ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;" +
                "-fx-effect: dropshadow(gaussian, rgba(249,115,22,0.17), 12, 0.15, 0, 3);";

        card.setStyle(
                normalStyle
        );

        // -----------------------------------------------------
        // TOP
        // -----------------------------------------------------

        HBox top =
                new HBox(13);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox icon =
                createComplaintIcon(
                        data.getPriority()
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
                        data.getId()
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

        Label customer =
                new Label(
                        data.getCustomer()
                );

        customer.setTextFill(
                Color.web(HEADING)
        );

        customer.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        identity.getChildren().addAll(
                idLine,
                customer
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        VBox badges =
                new VBox(
                        6
                );

        badges.setAlignment(
                Pos.CENTER_RIGHT
        );

        badges.getChildren().addAll(
                createPriorityBadge(
                        data.getPriority()
                ),
                createStatusBadge(
                        data.getStatus()
                )
        );

        top.getChildren().addAll(
                icon,
                identity,
                spacer,
                badges
        );

        // -----------------------------------------------------
        // SUBJECT
        // -----------------------------------------------------

        VBox subjectBox =
                new VBox(6);

        Label subjectTitle =
                new Label(
                        "COMPLAINT SUBJECT"
                );

        subjectTitle.setTextFill(
                Color.web(TEXT)
        );

        subjectTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        Label subject =
                new Label(
                        data.getSubject()
                );

        subject.setTextFill(
                Color.web(HEADING)
        );

        subject.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        subject.setWrapText(
                true
        );

        subjectBox.getChildren().addAll(
                subjectTitle,
                subject
        );

        // -----------------------------------------------------
        // INFO
        // -----------------------------------------------------

        HBox infoRow =
                new HBox(12);

        VBox priority =
                createInfoBox(
                        "PRIORITY",
                        data.getPriority()
                );

        VBox status =
                createInfoBox(
                        "STATUS",
                        data.getStatus()
                );

        VBox date =
                createInfoBox(
                        "DATE",
                        data.getDate()
                );

        HBox.setHgrow(
                priority,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                status,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                date,
                Priority.ALWAYS
        );

        infoRow.getChildren().addAll(
                priority,
                status,
                date
        );

        // -----------------------------------------------------
        // BOTTOM
        // -----------------------------------------------------

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
                        "View Complaint",
                        BLUE
                );

        Button update =
                createActionButton(
                        "Update Status",
                        ORANGE
                );

        view.setOnAction(
                event ->
                        openComplaintDetails(
                                data
                        )
        );

        update.setOnAction(
                event ->
                        openUpdateStatusPage(
                                data
                        )
        );

        bottom.getChildren().addAll(
                bottomSpacer,
                view,
                update
        );

        card.getChildren().addAll(
                top,
                new Separator(),
                subjectBox,
                infoRow,
                bottom
        );

        // -----------------------------------------------------
        // HOVER
        // -----------------------------------------------------

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

        // -----------------------------------------------------
        // DOUBLE CLICK
        // -----------------------------------------------------

        card.setOnMouseClicked(
                event -> {

                    if (
                            event.getClickCount() == 2
                    ) {

                        openComplaintDetails(
                                data
                        );
                    }
                }
        );
        return card;
    }

    // =========================================================
    // COMPLAINT ICON
    // =========================================================

    private VBox createComplaintIcon(
            String priority
    ) {

        String color;

        String iconText;

        if (
                "High".equalsIgnoreCase(
                        priority
                )
        ) {

            color = RED;
            iconText = "!";

        } else if (
                "Medium".equalsIgnoreCase(
                        priority
                )
        ) {

            color = YELLOW;
            iconText = "!";

        } else {

            color = BLUE;
            iconText = "C";
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
                        22
                )
        );

        box.getChildren().add(
                icon
        );

        return box;
    }

    // =========================================================
    // PRIORITY BADGE
    // =========================================================

    private Label createPriorityBadge(
            String priority
    ) {

        String color;
        String background;

        if (
                "High".equalsIgnoreCase(
                        priority
                )
        ) {

            color = RED;
            background = "#FEE2E2";

        } else if (
                "Medium".equalsIgnoreCase(
                        priority
                )
        ) {

            color = YELLOW;
            background = "#FEF3C7";

        } else {

            color = GREEN;
            background = "#DCFCE7";
        }

        Label badge =
                new Label(
                        priority +
                        " Priority"
                );

        badge.setTextFill(
                Color.web(color)
        );

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        badge.setPadding(
                new Insets(
                        6,
                        10,
                        6,
                        10
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
    // STATUS BADGE
    // =========================================================

    private Label createStatusBadge(
            String status
    ) {

        String color;
        String background;

        if (
                "Resolved".equalsIgnoreCase(
                        status
                )
        ) {

            color = GREEN;
            background = "#DCFCE7";

        } else if (
                "Pending".equalsIgnoreCase(
                        status
                )
        ) {

            color = YELLOW;
            background = "#FEF3C7";

        } else if (
                "Rejected".equalsIgnoreCase(
                        status
                )
        ) {

            color = RED;
            background = "#FEE2E2";

        } else {

            color = BLUE;
            background = "#DBEAFE";
        }

        Label badge =
                new Label(
                        status
                );

        badge.setTextFill(
                Color.web(color)
        );

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        badge.setPadding(
                new Insets(
                        6,
                        10,
                        6,
                        10
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

        box.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return box;
    }

    // =========================================================
    // BUTTONS
    // =========================================================

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
                color.equals(ORANGE)
                        ? "#FFEDD5"
                        : "#DBEAFE";

        String hover =
                color.equals(ORANGE)
                        ? "#FED7AA"
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

        String selectedStatus =
                statusFilter == null
                        ? "All Status"
                        : statusFilter.getValue();

        String selectedPriority =
                priorityFilter == null
                        ? "All Priority"
                        : priorityFilter.getValue();

        String sort =
                sortFilter == null
                        ? "Newest First"
                        : sortFilter.getValue();

        filteredComplaints.clear();

        for (
                ComplaintData data :
                complaints
        ) {

            boolean searchMatch =
                    search.isEmpty()
                    ||
                    data.getId()
                            .toLowerCase()
                            .contains(search)
                    ||
                    data.getCustomer()
                            .toLowerCase()
                            .contains(search)
                    ||
                    data.getSubject()
                            .toLowerCase()
                            .contains(search);

            boolean statusMatch =
                    selectedStatus.equals(
                            "All Status"
                    )
                    ||
                    data.getStatus()
                            .equalsIgnoreCase(
                                    selectedStatus
                            );

            boolean priorityMatch =
                    selectedPriority.equals(
                            "All Priority"
                    )
                    ||
                    data.getPriority()
                            .equalsIgnoreCase(
                                    selectedPriority
                            );

            if (
                    searchMatch &&
                    statusMatch &&
                    priorityMatch
            ) {

                filteredComplaints.add(
                        data
                );
            }
        }

        sortComplaints(
                sort
        );

        renderComplaints();
    }

    // =========================================================
    // SORT
    // =========================================================

    private void sortComplaints(
            String sort
    ) {

        if (
                "High Priority".equals(sort)
        ) {

            filteredComplaints.sort(
                    (a, b) ->
                            Integer.compare(
                                    priorityValue(
                                            b.getPriority()
                                    ),
                                    priorityValue(
                                            a.getPriority()
                                    )
                            )
            );

        } else if (
                "Low Priority".equals(sort)
        ) {

            filteredComplaints.sort(
                    (a, b) ->
                            Integer.compare(
                                    priorityValue(
                                            a.getPriority()
                                    ),
                                    priorityValue(
                                            b.getPriority()
                                    )
                            )
            );

        } else if (
                "Customer A-Z".equals(sort)
        ) {

            filteredComplaints.sort(
                    (a, b) ->
                            a.getCustomer()
                                    .compareToIgnoreCase(
                                            b.getCustomer()
                                    )
            );

        } else if (
                "Oldest First".equals(sort)
        ) {

            filteredComplaints.sort(
                    (a, b) ->
                            a.getDate()
                                    .compareTo(
                                            b.getDate()
                                    )
            );

        } else {

            filteredComplaints.sort(
                    (a, b) ->
                            b.getDate()
                                    .compareTo(
                                            a.getDate()
                                    )
            );
        }
    }

    private int priorityValue(
            String priority
    ) {

        if (
                "High".equalsIgnoreCase(
                        priority
                )
        ) {

            return 3;
        }

        if (
                "Medium".equalsIgnoreCase(
                        priority
                )
        ) {

            return 2;
        }

        return 1;
    }

    // =========================================================
    // RENDER
    // =========================================================

    private void renderComplaints() {

        complaintCards
                .getChildren()
                .clear();

        if (
                filteredComplaints.isEmpty()
        ) {

            complaintCards
                    .getChildren()
                    .add(
                            createEmptyState()
                    );

            resultCountLabel.setText(
                    "0 complaints found"
            );

            return;
        }

        int number = 1;

        for (
                ComplaintData data :
                filteredComplaints
        ) {

            complaintCards
                    .getChildren()
                    .add(
                            createComplaintCard(
                                    data,
                                    number++
                            )
                    );
        }

        resultCountLabel.setText(
                filteredComplaints.size() == 1
                        ? "1 complaint"
                        : filteredComplaints.size()
                          + " complaints"
        );
    }

    // =========================================================
    // SAMPLE DATA
    // =========================================================

    private void loadSampleComplaints() {

        complaints.clear();

        complaints.addAll(

                new ComplaintData(
                        "C001",
                        "Rahul Sharma",
                        "Delay in roadside assistance",
                        "High",
                        "Pending",
                        "2026-08-13"
                ),

                new ComplaintData(
                        "C002",
                        "Akash Patil",
                        "Mechanic arrived late",
                        "Medium",
                        "In Progress",
                        "2026-08-12"
                ),

                new ComplaintData(
                        "C003",
                        "Pooja Mehta",
                        "Incorrect service charge",
                        "High",
                        "Resolved",
                        "2026-08-12"
                ),

                new ComplaintData(
                        "C004",
                        "Vikram Joshi",
                        "Poor communication",
                        "Low",
                        "Resolved",
                        "2026-08-11"
                ),

                new ComplaintData(
                        "C005",
                        "Suresh Jadhav",
                        "Vehicle service issue",
                        "Medium",
                        "Pending",
                        "2026-08-10"
                ),

                new ComplaintData(
                        "C006",
                        "Neha Kulkarni",
                        "Service request was cancelled",
                        "Low",
                        "Rejected",
                        "2026-08-09"
                )
        );
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private void updateStatistics() {

        int total =
                complaints.size();

        int pending = 0;
        int resolved = 0;
        int high = 0;

        for (
                ComplaintData data :
                complaints
        ) {

            if (
                    data.getStatus()
                            .equalsIgnoreCase(
                                    "Pending"
                            )
            ) {

                pending++;
            }

            if (
                    data.getStatus()
                            .equalsIgnoreCase(
                                    "Resolved"
                            )
            ) {

                resolved++;
            }

            if (
                    data.getPriority()
                            .equalsIgnoreCase(
                                    "High"
                            )
            ) {

                high++;
            }
        }

        totalLabel.setText(
                String.valueOf(total)
        );

        pendingLabel.setText(
                String.valueOf(pending)
        );

        resolvedLabel.setText(
                String.valueOf(resolved)
        );

        highPriorityLabel.setText(
                String.valueOf(high)
        );
    }

    // =========================================================
    // VIEW COMPLAINT
    // =========================================================

    private void showComplaint(
            ComplaintData data
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Complaint Details"
        );

        alert.setHeaderText(
                data.getId() +
                " - " +
                data.getCustomer()
        );

        alert.setContentText(
                "Customer: " +
                data.getCustomer() +
                "\n\n" +
                "Subject: " +
                data.getSubject() +
                "\n\n" +
                "Priority: " +
                data.getPriority() +
                "\n\n" +
                "Status: " +
                data.getStatus() +
                "\n\n" +
                "Date: " +
                data.getDate()
        );

        alert.showAndWait();
    }

    // =========================================================
    // UPDATE COMPLAINT
    // =========================================================

    private void updateComplaint(
            ComplaintData data
    ) {

        ChoiceDialog<String> dialog =
                new ChoiceDialog<>(
                        data.getStatus(),
                        "Pending",
                        "In Progress",
                        "Resolved",
                        "Rejected"
                );

        dialog.setTitle(
                "Update Complaint"
        );

        dialog.setHeaderText(
                "Update complaint status"
        );

        dialog.setContentText(
                "Select new status:"
        );

        dialog.showAndWait()
                .ifPresent(
                        status -> {

                            data.setStatus(
                                    status
                            );

                            updateStatistics();

                            applyFilters();
                        }
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
                        "!"
                );

        icon.setTextFill(
                Color.web(ORANGE)
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
                        "No complaints found"
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
                        "Try changing your search, status or priority filter."
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
    // STYLING
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

    public static class ComplaintData {

        private String id;
        private String customer;
        private String subject;
        private String priority;
        private String status;
        private String date;

        public ComplaintData(
                String id,
                String customer,
                String subject,
                String priority,
                String status,
                String date
        ) {

            this.id = id;
            this.customer = customer;
            this.subject = subject;
            this.priority = priority;
            this.status = status;
            this.date = date;
        }

        public String getId() {
            return id;
        }

        public String getCustomer() {
            return customer;
        }

        public String getSubject() {
            return subject;
        }

        public String getPriority() {
            return priority;
        }

        public String getStatus() {
            return status;
        }

        public String getDate() {
            return date;
        }

        public void setStatus(
                String status
        ) {

            this.status = status;
        }
    }
}
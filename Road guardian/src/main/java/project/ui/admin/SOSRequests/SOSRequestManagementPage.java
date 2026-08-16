package project.ui.admin.SOSRequests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.cloud.firestore.Firestore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.controller.admin.SOSRequestController;
import project.firebase.FirebaseConfig;
import project.model.SOSRequest;
import project.ui.admin.DashBoard.AdminSectionPage;

public class SOSRequestManagementPage extends AdminSectionPage {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";

    private static final String BLUE = "#2563EB";
    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";
    private static final String GREEN = "#16A34A";
    private static final String RED = "#DC2626";

    private static final String BORDER = "#B8D4D9";

    // =========================================================
    // DATA
    // =========================================================

    private final ObservableList<SOSRequest> sosList =
            FXCollections.observableArrayList();

    private final List<SOSRequest> allRequests =
            new ArrayList<>();

    // =========================================================
    // CONTROLLER
    // =========================================================

    private SOSRequestController controller;

    // =========================================================
    // ROOT
    // IMPORTANT:
    // Same navigation pattern as CustomerManagementPage
    // =========================================================

    private VBox root;

    // =========================================================
    // UI
    // =========================================================

    private VBox requestCards;

    private TextField searchField;

    private ComboBox<String> statusFilter;

    private ComboBox<String> sortFilter;

    private Label resultCountLabel;

    // =========================================================
    // STATISTICS
    // =========================================================

    private Label totalLabel;

    private Label pendingLabel;

    private Label acceptedLabel;

    private Label progressLabel;

    private Label resolvedLabel;

    // =========================================================
    // GET VIEW
    // =========================================================

    @Override
    public VBox getView() {

        root = new VBox();

        root.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";"
        );

        // -----------------------------------------------------
        // FIREBASE
        // -----------------------------------------------------

        try {

            Firestore firestore =
                    FirebaseConfig.getFirestore();

            controller =
                    new SOSRequestController(
                            firestore
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return createErrorView(
                    "Unable to connect to Firestore."
            );
        }

        // -----------------------------------------------------
        // SHOW MANAGEMENT PAGE
        // -----------------------------------------------------

        showManagementPage();

        return root;
    }

    // =========================================================
    // SHOW MANAGEMENT PAGE
    // =========================================================

    private void showManagementPage() {

        root.getChildren().clear();

        root.setPadding(
                new Insets(
                        30,
                        32,
                        32,
                        32
                )
        );

        root.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";"
        );

        VBox header =
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

        loadRequests();
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header =
                new VBox(7);

        Label title =
                new Label(
                        "SOS Emergency Requests"
                );

        title.setTextFill(
                Color.web(
                        HEADING
                )
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
                        "Monitor emergency roadside assistance requests, track response status, and manage urgent mechanic assignments."
                );

        subtitle.setTextFill(
                Color.web(
                        TEXT
                )
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        return header;
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private HBox createStatistics() {

        HBox statistics =
                new HBox(16);

        totalLabel =
                createValueLabel(
                        BLUE
                );

        pendingLabel =
                createValueLabel(
                        ORANGE
                );

        acceptedLabel =
                createValueLabel(
                        BLUE
                );

        progressLabel =
                createValueLabel(
                        ORANGE
                );

        resolvedLabel =
                createValueLabel(
                        GREEN
                );

        VBox totalCard =
                createStatCard(
                        "Total SOS",
                        "All emergency requests",
                        totalLabel,
                        BLUE,
                        "TOTAL"
                );

        VBox pendingCard =
                createStatCard(
                        "Pending",
                        "Waiting for response",
                        pendingLabel,
                        ORANGE,
                        "PENDING"
                );

        VBox acceptedCard =
                createStatCard(
                        "Accepted",
                        "Mechanic assigned",
                        acceptedLabel,
                        BLUE,
                        "ACCEPTED"
                );

        VBox progressCard =
                createStatCard(
                        "In Progress",
                        "Emergency assistance",
                        progressLabel,
                        ORANGE,
                        "ACTIVE"
                );

        VBox resolvedCard =
                createStatCard(
                        "Resolved",
                        "Emergency completed",
                        resolvedLabel,
                        GREEN,
                        "DONE"
                );

        HBox.setHgrow(
                totalCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                pendingCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                acceptedCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                progressCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                resolvedCard,
                Priority.ALWAYS
        );

        statistics.getChildren().addAll(
                totalCard,
                pendingCard,
                acceptedCard,
                progressCard,
                resolvedCard
        );

        return statistics;
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
                Color.web(
                        color
                )
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
                new VBox(9);

        card.setPadding(
                new Insets(
                        20,
                        22,
                        18,
                        22
                )
        );

        card.setMinHeight(
                130
        );

        String normalStyle =
                "-fx-background-color: " +
                        SURFACE +
                        ";" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 14;" +
                        "-fx-background-radius: 14;";

        String hoverStyle =
                "-fx-background-color: #F7FCFC;" +
                        "-fx-border-color: " +
                        color +
                        ";" +
                        "-fx-border-radius: 14;" +
                        "-fx-background-radius: 14;" +
                        "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.14), 10, 0.12, 0, 2);";

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
                Color.web(
                        HEADING
                )
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
                Color.web(
                        color
                )
        );

        tag.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        tag.setPadding(
                new Insets(
                        6,
                        10,
                        6,
                        10
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
                Color.web(
                        TEXT
                )
        );

        subtitleLabel.setFont(
                Font.font(
                        "Arial",
                        14
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
                "Search SOS, customer, vehicle, emergency, location..."
        );

        searchField.setPrefWidth(
                400
        );

        searchField.setPrefHeight(
                44
        );

        styleTextField(
                searchField
        );

        statusFilter =
                new ComboBox<>();

        statusFilter.getItems().addAll(
                "All",
                "Pending",
                "Accepted",
                "In Progress",
                "Resolved",
                "Cancelled"
        );

        statusFilter.setValue(
                "All"
        );

        statusFilter.setPrefWidth(
                145
        );

        statusFilter.setPrefHeight(
                44
        );

        styleComboBox(
                statusFilter
        );

        sortFilter =
                new ComboBox<>();

        sortFilter.getItems().addAll(
                "Newest First",
                "Oldest First",
                "Customer A-Z",
                "Customer Z-A",
                "Emergency A-Z"
        );

        sortFilter.setValue(
                "Newest First"
        );

        sortFilter.setPrefWidth(
                145
        );

        sortFilter.setPrefHeight(
                44
        );

        styleComboBox(
                sortFilter
        );

        Button refresh =
                createButton(
                        "Refresh",
                        BLUE
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
                        (
                                obs,
                                oldValue,
                                newValue
                        ) ->
                                applyFilters()
                );

        statusFilter.valueProperty()
                .addListener(
                        (
                                obs,
                                oldValue,
                                newValue
                        ) ->
                                applyFilters()
                );

        sortFilter.valueProperty()
                .addListener(
                        (
                                obs,
                                oldValue,
                                newValue
                        ) ->
                                applyFilters()
                );

        refresh.setOnAction(
                event ->
                        loadRequests()
        );

        toolbar.getChildren().addAll(
                searchField,
                statusFilter,
                sortFilter,
                refresh,
                spacer
        );

        return toolbar;
    }

    // =========================================================
    // BUTTON
    // =========================================================

    private Button createButton(
            String text,
            String color
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

        button.setPrefHeight(
                42
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

        setButtonStyle(
                button,
                color
        );

        button.setOnMouseEntered(
                event ->
                        setButtonStyle(
                                button,
                                color.equals(
                                        ORANGE
                                )
                                        ? ORANGE_HOVER
                                        : "#1D4ED8"
                        )
        );

        button.setOnMouseExited(
                event ->
                        setButtonStyle(
                                button,
                                color
                        )
        );

        return button;
    }

    private void setButtonStyle(
            Button button,
            String color
    ) {

        button.setStyle(
                "-fx-background-color: " +
                        color +
                        ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // RECORDS CARD
    // =========================================================

    private VBox createRecordsCard() {

        VBox card =
                new VBox(15);

        card.setPadding(
                new Insets(
                        21
                )
        );

        card.setStyle(
                "-fx-background-color: " +
                        SURFACE +
                        ";" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 14;" +
                        "-fx-background-radius: 14;"
        );

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox headingBox =
                new VBox(4);

        Label heading =
                new Label(
                        "Emergency SOS Information"
                );

        heading.setTextFill(
                Color.web(
                        HEADING
                )
        );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        Label subtitle =
                new Label(
                        "Emergency roadside assistance requests requiring immediate attention"
                );

        subtitle.setTextFill(
                Color.web(
                        TEXT
                )
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        resultCountLabel =
                new Label(
                        "0 requests"
                );

        resultCountLabel.setTextFill(
                Color.web(
                        BLUE
                )
        );

        resultCountLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        headingBox.getChildren().addAll(
                heading,
                subtitle,
                resultCountLabel
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label emergency =
                new Label(
                        "● Emergency"
                );

        emergency.setTextFill(
                Color.web(
                        RED
                )
        );

        emergency.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        emergency.setPadding(
                new Insets(
                        7,
                        11,
                        7,
                        11
                )
        );

        emergency.setStyle(
                "-fx-background-color: " +
                        RED +
                        "18;" +
                        "-fx-background-radius: 20;"
        );

        header.getChildren().addAll(
                headingBox,
                spacer,
                emergency
        );

        requestCards =
                new VBox(12);

        requestCards.setFillWidth(
                true
        );

        requestCards.setPadding(
                new Insets(
                        3,
                        2,
                        10,
                        2
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        requestCards
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
                header,
                scrollPane
        );

        return card;
    }

    // =========================================================
    // SOS CARD
    // =========================================================

    private VBox createSOSCard(
            SOSRequest request,
            int number
    ) {

        VBox card =
                new VBox(14);

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
                        RED +
                        ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;" +
                        "-fx-effect: dropshadow(gaussian, rgba(220,38,38,0.16), 12, 0.15, 0, 3);";

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

        VBox iconBox =
                createSOSIcon();

        VBox identity =
                new VBox(4);

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
                Color.web(
                        RED
                )
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
                        RED +
                        "14;" +
                        "-fx-background-radius: 20;"
        );

        Label sosId =
                new Label(
                        safe(
                                request.getSosId()
                        )
                );

        sosId.setTextFill(
                Color.web(
                        HEADING
                )
        );

        sosId.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        idLine.getChildren().addAll(
                serial,
                sosId
        );

        Label customer =
                new Label(
                        "Customer: " +
                                safe(
                                        request.getCustomerName()
                                )
                );

        customer.setTextFill(
                Color.web(
                        TEXT
                )
        );

        customer.setFont(
                Font.font(
                        "Arial",
                        13
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

        Label status =
                createStatusBadge(
                        safe(
                                request.getStatus()
                        )
                );

        top.getChildren().addAll(
                iconBox,
                identity,
                spacer,
                status
        );

        // -----------------------------------------------------
        // INFORMATION
        // -----------------------------------------------------

        HBox infoRow =
                new HBox(12);

        VBox vehicle =
                createInfoBox(
                        "VEHICLE",
                        request.getVehicleNumber()
                );

        VBox emergency =
                createInfoBox(
                        "EMERGENCY",
                        request.getEmergencyType()
                );

        VBox mechanic =
                createInfoBox(
                        "MECHANIC",
                        isAssigned(
                                request
                        )
                                ? request.getMechanicName()
                                : "Unassigned"
                );

        VBox location =
                createInfoBox(
                        "LOCATION",
                        request.getLocation()
                );

        HBox.setHgrow(
                vehicle,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                emergency,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                mechanic,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                location,
                Priority.ALWAYS
        );

        infoRow.getChildren().addAll(
                vehicle,
                emergency,
                mechanic,
                location
        );

        // -----------------------------------------------------
        // BOTTOM
        // -----------------------------------------------------

        HBox bottom =
                new HBox(12);

        bottom.setAlignment(
                Pos.CENTER_LEFT
        );

        Label description =
                new Label(
                        shorten(
                                safe(
                                        request.getDescription()
                                ),
                                90
                        )
                );

        description.setTextFill(
                Color.web(
                        TEXT
                )
        );

        description.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        description.setWrapText(
                true
        );

        Region bottomSpacer =
                new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );

        Button view =
                new Button(
                        "View Emergency →"
                );

        styleSmallButton(
                view,
                RED
        );

        // =====================================================
        // IMPORTANT NAVIGATION
        // NO DIALOG
        // NO NEW WINDOW
        // CURRENT ROOT REPLACED
        // =====================================================

        view.setOnAction(
                event ->
                        showDetails(
                                request
                        )
        );

        bottom.getChildren().addAll(
                description,
                bottomSpacer,
                view
        );

        card.getChildren().addAll(
                top,
                new Separator(),
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
                                    &&
                                    event.getTarget() != view
                    ) {

                        showDetails(
                                request
                        );
                    }
                }
        );

        return card;
    }

    // =========================================================
    // SOS ICON
    // =========================================================

    private VBox createSOSIcon() {

        VBox box =
                new VBox();

        box.setAlignment(
                Pos.CENTER
        );

        box.setMinSize(
                48,
                48
        );

        box.setPrefSize(
                48,
                48
        );

        box.setMaxSize(
                48,
                48
        );

        box.setStyle(
                "-fx-background-color: " +
                        RED +
                        ";" +
                        "-fx-background-radius: 12;"
        );

        Label icon =
                new Label(
                        "SOS"
                );

        icon.setTextFill(
                Color.WHITE
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        box.getChildren().add(
                icon
        );

        return box;
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
                Color.web(
                        TEXT
                )
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
                        safe(
                                value
                        )
                );

        valueLabel.setTextFill(
                Color.web(
                        HEADING
                )
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
    // STATUS BADGE
    // =========================================================

    private Label createStatusBadge(
            String status
    ) {

        Label badge =
                new Label(
                        status.equals("-")
                                ? "Unknown"
                                : status
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

        String color =
                getStatusColor(
                        status
                );

        badge.setTextFill(
                Color.web(
                        color
                )
        );

        badge.setStyle(
                "-fx-background-color: " +
                        color +
                        "18;" +
                        "-fx-background-radius: 20;"
        );

        return badge;
    }

    private String getStatusColor(
            String status
    ) {

        if (
                status.equalsIgnoreCase(
                        "Resolved"
                )
        ) {

            return GREEN;
        }

        if (
                status.equalsIgnoreCase(
                        "Accepted"
                )
                        ||
                        status.equalsIgnoreCase(
                                "In Progress"
                        )
        ) {

            return BLUE;
        }

        if (
                status.equalsIgnoreCase(
                        "Cancelled"
                )
        ) {

            return RED;
        }

        return ORANGE;
    }

    // =========================================================
    // SMALL BUTTON
    // =========================================================

    private void styleSmallButton(
            Button button,
            String color
    ) {

        button.setTextFill(
                Color.WHITE
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
                        13,
                        8,
                        13
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color: " +
                        color +
                        ";" +
                        "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                        (
                                                color.equals(
                                                        RED
                                                )
                                                        ? "#B91C1C"
                                                        : "#1D4ED8"
                                        ) +
                                        ";" +
                                        "-fx-background-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                        color +
                                        ";" +
                                        "-fx-background-radius: 8;"
                        )
        );
    }

    // =========================================================
    // FILTERS
    // =========================================================

    private void applyFilters() {

        if (
                requestCards == null
        ) {

            return;
        }

        String search =
                searchField == null
                        ? ""
                        : searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        String status =
                statusFilter == null
                        ? "All"
                        : statusFilter.getValue();

        String sort =
                sortFilter == null
                        ? "Newest First"
                        : sortFilter.getValue();

        List<SOSRequest> filtered =
                new ArrayList<>();

        for (
                SOSRequest request :
                allRequests
        ) {

            if (
                    !matchesSearch(
                            request,
                            search
                    )
            ) {

                continue;
            }

            if (
                    !"All".equals(
                            status
                    )
                            &&
                            !status.equalsIgnoreCase(
                                    safe(
                                            request.getStatus()
                                    )
                            )
            ) {

                continue;
            }

            filtered.add(
                    request
            );
        }

        sortRequests(
                filtered,
                sort
        );

        sosList.setAll(
                filtered
        );

        renderRequests(
                filtered
        );
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private boolean matchesSearch(
            SOSRequest request,
            String search
    ) {

        if (
                search.isBlank()
        ) {

            return true;
        }

        return safe(
                request.getSosId()
        )
                .toLowerCase()
                .contains(search)

                ||

                safe(
                        request.getCustomerName()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        request.getVehicleNumber()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        request.getEmergencyType()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        request.getLocation()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        request.getMechanicName()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        request.getDescription()
                )
                        .toLowerCase()
                        .contains(search);
    }

    // =========================================================
    // SORT
    // =========================================================

    private void sortRequests(
            List<SOSRequest> requests,
            String sort
    ) {

        if (
                "Customer A-Z".equals(
                        sort
                )
        ) {

            requests.sort(
                    Comparator.comparing(
                            request ->
                                    safe(
                                            request.getCustomerName()
                                    )
                                            .toLowerCase()
                    )
            );

        } else if (
                "Customer Z-A".equals(
                        sort
                )
        ) {

            requests.sort(
                    Comparator.comparing(
                                    (SOSRequest request) ->
                                            safe(
                                                    request.getCustomerName()
                                            )
                                                    .toLowerCase()
                            )
                            .reversed()
            );

        } else if (
                "Emergency A-Z".equals(
                        sort
                )
        ) {

            requests.sort(
                    Comparator.comparing(
                            request ->
                                    safe(
                                            request.getEmergencyType()
                                    )
                                            .toLowerCase()
                    )
            );

        } else if (
                "Oldest First".equals(
                        sort
                )
        ) {

            requests.sort(
                    Comparator.comparing(
                            request ->
                                    safe(
                                            request.getSosId()
                                    )
                    )
            );

        } else {

            requests.sort(
                    Comparator.comparing(
                                    (SOSRequest request) ->
                                            safe(
                                                    request.getSosId()
                                            )
                            )
                            .reversed()
            );
        }
    }

    // =========================================================
    // LOAD REQUESTS
    // =========================================================

    private void loadRequests() {

        try {

            List<SOSRequest> requests =
                    controller.getAllRequests();

            allRequests.clear();

            if (
                    requests != null
            ) {

                allRequests.addAll(
                        requests
                );
            }

            updateStatistics(
                    allRequests
            );

            applyFilters();

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Unable to Load SOS Requests",
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // RENDER
    // =========================================================

    private void renderRequests(
            List<SOSRequest> requests
    ) {

        requestCards
                .getChildren()
                .clear();

        if (
                requests.isEmpty()
        ) {

            requestCards
                    .getChildren()
                    .add(
                            createEmptyState()
                    );

            resultCountLabel.setText(
                    "0 requests found"
            );

            return;
        }

        int number = 1;

        for (
                SOSRequest request :
                requests
        ) {

            requestCards
                    .getChildren()
                    .add(
                            createSOSCard(
                                    request,
                                    number++
                            )
                    );
        }

        resultCountLabel.setText(
                requests.size() == 1
                        ? "1 request"
                        : requests.size() +
                        " requests"
        );
    }

    // =========================================================
    // STATISTICS UPDATE
    // =========================================================

    private void updateStatistics(
            List<SOSRequest> requests
    ) {

        int pending = 0;

        int accepted = 0;

        int progress = 0;

        int resolved = 0;

        for (
                SOSRequest request :
                requests
        ) {

            String status =
                    safe(
                            request.getStatus()
                    );

            if (
                    status.equalsIgnoreCase(
                            "Pending"
                    )
            ) {

                pending++;

            } else if (
                    status.equalsIgnoreCase(
                            "Accepted"
                    )
            ) {

                accepted++;

            } else if (
                    status.equalsIgnoreCase(
                            "In Progress"
                    )
            ) {

                progress++;

            } else if (
                    status.equalsIgnoreCase(
                            "Resolved"
                    )
            ) {

                resolved++;
            }
        }

        totalLabel.setText(
                String.valueOf(
                        requests.size()
                )
        );

        pendingLabel.setText(
                String.valueOf(
                        pending
                )
        );

        acceptedLabel.setText(
                String.valueOf(
                        accepted
                )
        );

        progressLabel.setText(
                String.valueOf(
                        progress
                )
        );

        resolvedLabel.setText(
                String.valueOf(
                        resolved
                )
        );
    }

    // =========================================================
    // DIRECT NAVIGATION TO DETAILS PAGE
    // =========================================================

    private void showDetails(SOSRequest request) {

        SOSRequestDetailsPage page =
                new SOSRequestDetailsPage(
                        request,
                        () -> showManagementPage(),
                        () -> showAssignMechanicPage(request)
                );

        root.getChildren().clear();

        root.setPadding(new Insets(0));

        root.getChildren().add(
                page.getView()
        );
    }
    private void showAssignMechanicPage(SOSRequest request) {

        AssignSOSMechanicPage page =
                new AssignSOSMechanicPage(
                        request,
                        () -> showDetails(request)
                );

        root.getChildren().clear();

        root.setPadding(new Insets(0));

        root.getChildren().add(
                page.getView()
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
                new Insets(
                        45
                )
        );

        Label icon =
                new Label(
                        "SOS"
                );

        icon.setTextFill(
                Color.web(
                        RED
                )
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        Label title =
                new Label(
                        "No SOS requests found"
                );

        title.setTextFill(
                Color.web(
                        HEADING
                )
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
                        "Emergency roadside assistance requests will appear here."
                );

        subtitle.setTextFill(
                Color.web(
                        TEXT
                )
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
    // CHECK MECHANIC ASSIGNED
    // =========================================================

    private boolean isAssigned(
            SOSRequest request
    ) {

        String mechanic =
                safe(
                        request.getMechanicName()
                );

        return !mechanic.equals(
                "-"
        );
    }

    // =========================================================
    // SHORTEN DESCRIPTION
    // =========================================================

    private String shorten(
            String value,
            int max
    ) {

        if (
                value == null
                        ||
                        value.isBlank()
        ) {

            return "No emergency description provided.";
        }

        if (
                value.length() <= max
        ) {

            return value;
        }

        return value.substring(
                0,
                max - 3
        ) + "...";
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(
            String value
    ) {

        if (
                value == null
                        ||
                        value.isBlank()
        ) {

            return "-";
        }

        return value;
    }

    // =========================================================
    // ERROR VIEW
    // =========================================================

    private VBox createErrorView(
            String message
    ) {

        VBox box =
                new VBox(12);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(
                        30
                )
        );

        box.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";"
        );

        Label icon =
                new Label(
                        "!"
                );

        icon.setTextFill(
                Color.web(
                        RED
                )
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        Label title =
                new Label(
                        "SOS Management Error"
                );

        title.setTextFill(
                Color.web(
                        HEADING
                )
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        Label text =
                new Label(
                        message
                );

        text.setTextFill(
                Color.web(
                        TEXT
                )
        );

        text.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        text.setWrapText(
                true
        );

        box.getChildren().addAll(
                icon,
                title,
                text
        );

        return box;
    }

    // =========================================================
    // ERROR ALERT
    // =========================================================

    private void showError(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message == null
                        ? "Unknown error."
                        : message
        );

        alert.showAndWait();
    }
}
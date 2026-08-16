package project.ui.admin.Vehicles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import com.google.cloud.firestore.Firestore;

import project.controller.admin.VehicleController;
import project.firebase.FirebaseConfig;
import project.model.Vehicle;
import project.ui.admin.DashBoard.AdminSectionPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VehicleManagementPage extends AdminSectionPage {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";

    private static final String BLUE = "#2563EB";
    private static final String GREEN = "#16A34A";
    private static final String RED = "#DC2626";

    private static final String BORDER = "#B8D4D9";

    // =========================================================
    // FIELDS
    // =========================================================

    private VehicleController controller;

    private final ObservableList<Vehicle> vehicleList =
            FXCollections.observableArrayList();

    private final List<Vehicle> allVehicles =
            new ArrayList<>();

    private VBox vehicleCards;

    private TextField searchField;
    private ComboBox<String> statusFilter;
    private ComboBox<String> sortFilter;

    private Label resultCountLabel;

    private Label totalLabel;
    private Label activeLabel;
    private Label inactiveLabel;

    /*
     * IMPORTANT:
     * Root is kept as a field because we are doing
     * page navigation inside the same page.
     */
    private VBox root;

    // =========================================================
    // VIEW
    // =========================================================

    @Override
    public VBox getView() {

        root = new VBox(22);

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

        try {

            Firestore firestore =
                    FirebaseConfig.getFirestore();

            controller =
                    new VehicleController(
                            firestore
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return createErrorView(
                    "Unable to connect to Firestore."
            );
        }

        showVehicleManagement();

        return root;
    }

    // =========================================================
    // MAIN VEHICLE MANAGEMENT PAGE
    // =========================================================

    private void showVehicleManagement() {

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

        loadVehicles();
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header =
                new VBox(7);

        Label title =
                new Label(
                        "Vehicle Management"
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
                        "Manage and monitor customer vehicles registered in RoadGuardian."
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

        HBox box =
                new HBox(16);

        totalLabel =
                createValueLabel(BLUE);

        activeLabel =
                createValueLabel(GREEN);

        inactiveLabel =
                createValueLabel(RED);

        VBox total =
                createStatCard(
                        "Total Vehicles",
                        "All registered vehicles",
                        totalLabel,
                        BLUE,
                        "TOTAL"
                );

        VBox active =
                createStatCard(
                        "Active Vehicles",
                        "Currently registered",
                        activeLabel,
                        GREEN,
                        "ACTIVE"
                );

        VBox inactive =
                createStatCard(
                        "Inactive Vehicles",
                        "Currently unavailable",
                        inactiveLabel,
                        RED,
                        "INACTIVE"
                );

        HBox.setHgrow(
                total,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                active,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                inactive,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                total,
                active,
                inactive
        );

        return box;
    }

    private Label createValueLabel(
            String color
    ) {

        Label label =
                new Label("0");

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
                new VBox(9);

        card.setPadding(
                new Insets(
                        20,
                        22,
                        18,
                        22
                )
        );

        card.setMinHeight(130);

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
                BLUE +
                ";" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;" +
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
                new Label(title);

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
                new Label(tagText);

        tag.setTextFill(
                Color.web(color)
        );

        tag.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
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
                new Label(subtitle);

        subtitleLabel.setTextFill(
                Color.web(TEXT)
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

                    card.setTranslateY(-2);
                }
        );

        card.setOnMouseExited(
                event -> {

                    card.setStyle(
                            normalStyle
                    );

                    card.setTranslateY(0);
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
                "Search vehicle number, owner, brand, model..."
        );

        searchField.setPrefWidth(390);
        searchField.setPrefHeight(44);

        styleTextField(
                searchField
        );

        statusFilter =
                new ComboBox<>();

        statusFilter.getItems().addAll(
                "All",
                "Active",
                "Inactive"
        );

        statusFilter.setValue(
                "All"
        );

        statusFilter.setPrefWidth(125);
        statusFilter.setPrefHeight(44);

        styleComboBox(
                statusFilter
        );

        sortFilter =
                new ComboBox<>();

        sortFilter.getItems().addAll(
                "Newest First",
                "Oldest First",
                "Vehicle A-Z",
                "Vehicle Z-A",
                "Year Newest",
                "Year Oldest"
        );

        sortFilter.setValue(
                "Newest First"
        );

        sortFilter.setPrefWidth(150);
        sortFilter.setPrefHeight(44);

        styleComboBox(
                sortFilter
        );

        Button refresh =
                createActionButton(
                        "Refresh",
                        BLUE
                );

        refresh.setPrefHeight(44);

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button add =
                createActionButton(
                        "+ Add Vehicle",
                        ORANGE
                );

        add.setPrefHeight(44);

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

        sortFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        refresh.setOnAction(
                event ->
                        loadVehicles()
        );

        add.setOnAction(
                event ->
                        showAddDialog()
        );

        toolbar.getChildren().addAll(
                searchField,
                statusFilter,
                sortFilter,
                refresh,
                spacer,
                add
        );

        return toolbar;
    }

    // =========================================================
    // BUTTON
    // =========================================================

    private Button createActionButton(
            String text,
            String color
    ) {

        Button button =
                new Button(text);

        button.setTextFill(
                Color.WHITE
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        button.setPrefHeight(42);

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
                                color.equals(ORANGE)
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
                new Insets(21)
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
                        "Vehicle Information"
                );

        heading.setTextFill(
                Color.web(HEADING)
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
                        "Registered vehicles and their current information"
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
                        "0 vehicles"
                );

        resultCountLabel.setTextFill(
                Color.web(BLUE)
        );

        resultCountLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
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

        Label live =
                new Label(
                        "● Live Data"
                );

        live.setTextFill(
                Color.web(GREEN)
        );

        live.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
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

        header.getChildren().addAll(
                headingBox,
                spacer,
                live
        );

        vehicleCards =
                new VBox(12);

        vehicleCards.setFillWidth(
                true
        );

        vehicleCards.setPadding(
                new Insets(
                        3,
                        2,
                        10,
                        2
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        vehicleCards
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
    // VEHICLE CARD
    // =========================================================

    private VBox createVehicleCard(
            Vehicle vehicle,
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

        card.setMaxWidth(
                Double.MAX_VALUE
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
                "-fx-background-radius: 13;" +
                "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.18), 12, 0.15, 0, 3);";

        card.setStyle(
                normalStyle
        );

        // =====================================================
        // TOP
        // =====================================================

        HBox top =
                new HBox(13);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane avatar =
                createVehicleAvatar();

        VBox identity =
                new VBox(3);

        HBox numberLine =
                new HBox(9);

        numberLine.setAlignment(
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

        Label vehicleNumber =
                new Label(
                        safe(
                                vehicle.getVehicleNumber()
                        )
                );

        vehicleNumber.setTextFill(
                Color.web(HEADING)
        );

        vehicleNumber.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        numberLine.getChildren().addAll(
                serial,
                vehicleNumber
        );

        Label owner =
                new Label(
                        "Owner: " +
                        safe(
                                vehicle.getOwnerName()
                        )
                );

        owner.setTextFill(
                Color.web(TEXT)
        );

        owner.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        identity.getChildren().addAll(
                numberLine,
                owner
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label statusBadge =
                createStatusBadge(
                        safe(
                                vehicle.getStatus()
                        )
                );

        top.getChildren().addAll(
                avatar,
                identity,
                spacer,
                statusBadge
        );

        // =====================================================
        // INFORMATION
        // =====================================================

        HBox infoRow =
                new HBox(12);

        VBox brand =
                createInfoBox(
                        "BRAND",
                        safe(
                                vehicle.getBrand()
                        )
                );

        VBox model =
                createInfoBox(
                        "MODEL",
                        safe(
                                vehicle.getModel()
                        )
                );

        VBox type =
                createInfoBox(
                        "TYPE",
                        safe(
                                vehicle.getVehicleType()
                        )
                );

        VBox fuel =
                createInfoBox(
                        "FUEL",
                        safe(
                                vehicle.getFuelType()
                        )
                );

        VBox year =
                createInfoBox(
                        "YEAR",
                        safe(
                                vehicle.getYear()
                        )
                );

        HBox.setHgrow(
                brand,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                model,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                type,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                fuel,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                year,
                Priority.ALWAYS
        );

        infoRow.getChildren().addAll(
                brand,
                model,
                type,
                fuel,
                year
        );

        // =====================================================
        // BOTTOM
        // =====================================================

        HBox bottom =
                new HBox(12);

        bottom.setAlignment(
                Pos.CENTER_LEFT
        );

        Label customerId =
                new Label(
                        "Customer ID: " +
                        safe(
                                vehicle.getCustomerId()
                        )
                );

        customerId.setTextFill(
                Color.web(TEXT)
        );

        customerId.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        Region bottomSpacer =
                new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );

        Button edit =
                new Button(
                        "Edit Vehicle"
                );

        styleSmallButton(
                edit,
                ORANGE
        );

        edit.setOnAction(
                event ->
                        showEditVehiclePage(
                                vehicle
                        )
        );

        Button details =
                new Button(
                        "View Details →"
                );

        styleSmallButton(
                details,
                BLUE
        );

        details.setOnAction(
                event ->
                        showDetails(
                                vehicle
                        )
        );

        bottom.getChildren().addAll(
                customerId,
                bottomSpacer,
                edit,
                details
        );

        card.getChildren().addAll(
                top,
                new Separator(),
                infoRow,
                bottom
        );

        // =====================================================
        // HOVER
        // =====================================================

        card.setOnMouseEntered(
                event -> {

                    card.setStyle(
                            hoverStyle
                    );

                    card.setTranslateY(-2);
                }
        );

        card.setOnMouseExited(
                event -> {

                    card.setStyle(
                            normalStyle
                    );

                    card.setTranslateY(0);
                }
        );

        // =====================================================
        // DOUBLE CLICK
        // =====================================================

        card.setOnMouseClicked(
                event -> {

                    if (
                            event.getClickCount() == 2 &&
                            event.getTarget() != edit &&
                            event.getTarget() != details
                    ) {

                        showDetails(
                                vehicle
                        );
                    }
                }
        );

        return card;
    }

    // =========================================================
    // VEHICLE AVATAR
    // =========================================================

    private StackPane createVehicleAvatar() {

        Circle circle =
                new Circle(
                        24,
                        Color.web(ORANGE)
                );

        Label icon =
                new Label(
                        "▰"
                );

        icon.setTextFill(
                Color.WHITE
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        StackPane avatar =
                new StackPane(
                        circle,
                        icon
                );

        avatar.setMinSize(
                48,
                48
        );

        avatar.setPrefSize(
                48,
                48
        );

        avatar.setMaxSize(
                48,
                48
        );

        return avatar;
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
                new Label(title);

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
                new Label(value);

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

        if (
                status.equalsIgnoreCase(
                        "Active"
                )
        ) {

            badge.setTextFill(
                    Color.web(GREEN)
            );

            badge.setStyle(
                    "-fx-background-color: " +
                    GREEN +
                    "18;" +
                    "-fx-background-radius: 20;"
            );

        } else {

            badge.setTextFill(
                    Color.web(RED)
            );

            badge.setStyle(
                    "-fx-background-color: " +
                    RED +
                    "18;" +
                    "-fx-background-radius: 20;"
            );
        }

        return badge;
    }

    // =========================================================
    // SMALL BUTTON
    // =========================================================

    private void styleSmallButton(
            Button button,
            String color
    ) {

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

        String normalColor =
                color.equals(BLUE)
                        ? BLUE
                        : SURFACE;

        String hoverColor =
                color.equals(BLUE)
                        ? "#1D4ED8"
                        : SECONDARY;

        button.setTextFill(
                color.equals(BLUE)
                        ? Color.WHITE
                        : Color.web(HEADING)
        );

        button.setStyle(
                "-fx-background-color: " +
                normalColor +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: " +
                (
                        color.equals(BLUE)
                                ? BLUE
                                : BORDER
                ) +
                ";" +
                "-fx-border-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                hoverColor +
                                ";" +
                                "-fx-background-radius: 8;" +
                                "-fx-border-color: " +
                                (
                                        color.equals(BLUE)
                                                ? "#1D4ED8"
                                                : BLUE
                                ) +
                                ";" +
                                "-fx-border-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                normalColor +
                                ";" +
                                "-fx-background-radius: 8;" +
                                "-fx-border-color: " +
                                (
                                        color.equals(BLUE)
                                                ? BLUE
                                                : BORDER
                                ) +
                                ";" +
                                "-fx-border-radius: 8;"
                        )
        );
    }

    // =========================================================
    // TEXT FIELD
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
    // LOAD
    // =========================================================

    private void loadVehicles() {

        try {

            List<Vehicle> vehicles =
                    controller.getAllVehicles();

            allVehicles.clear();

            allVehicles.addAll(
                    vehicles
            );

            updateStatistics(
                    allVehicles
            );

            applyFilters();

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Unable to Load Vehicles",
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // FILTER
    // =========================================================

    private void applyFilters() {

        if (
                vehicleCards == null
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

        List<Vehicle> filtered =
                new ArrayList<>();

        for (
                Vehicle vehicle :
                allVehicles
        ) {

            if (
                    !matchesSearch(
                            vehicle,
                            search
                    )
            ) {

                continue;
            }

            if (
                    !"All".equals(status) &&
                    !status.equalsIgnoreCase(
                            safe(
                                    vehicle.getStatus()
                            )
                    )
            ) {

                continue;
            }

            filtered.add(
                    vehicle
            );
        }

        sortVehicles(
                filtered,
                sort
        );

        vehicleList.setAll(
                filtered
        );

        renderVehicles(
                filtered
        );
    }

    private boolean matchesSearch(
            Vehicle vehicle,
            String search
    ) {

        if (
                search.isBlank()
        ) {

            return true;
        }

        return safe(
                vehicle.getVehicleNumber()
        )
                .toLowerCase()
                .contains(search)

                ||

                safe(
                        vehicle.getOwnerName()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        vehicle.getBrand()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        vehicle.getModel()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        vehicle.getVehicleType()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        vehicle.getFuelType()
                )
                        .toLowerCase()
                        .contains(search);
    }

    // =========================================================
    // SORT
    // =========================================================

    private void sortVehicles(
            List<Vehicle> vehicles,
            String sort
    ) {

        if (
                "Vehicle A-Z".equals(sort)
        ) {

            vehicles.sort(
                    Comparator.comparing(
                            vehicle ->
                                    safe(
                                            vehicle.getVehicleNumber()
                                    ).toLowerCase()
                    )
            );

        } else if (
                "Vehicle Z-A".equals(sort)
        ) {

            vehicles.sort(
                    Comparator.comparing(
                            (Vehicle vehicle) ->
                                    safe(
                                            vehicle.getVehicleNumber()
                                    ).toLowerCase()
                    ).reversed()
            );

        } else if (
                "Year Newest".equals(sort)
        ) {

            vehicles.sort(
                    Comparator.comparingInt(
                            this::getYearNumber
                    ).reversed()
            );

        } else if (
                "Year Oldest".equals(sort)
        ) {

            vehicles.sort(
                    Comparator.comparingInt(
                            this::getYearNumber
                    )
            );

        } else if (
                "Oldest First".equals(sort)
        ) {

            vehicles.sort(
                    Comparator.comparing(
                            vehicle ->
                                    safe(
                                            vehicle.getCreatedAt()
                                    )
                    )
            );

        } else {

            vehicles.sort(
                    Comparator.comparing(
                            (Vehicle vehicle) ->
                                    safe(
                                            vehicle.getCreatedAt()
                                    )
                    ).reversed()
            );
        }
    }

    private int getYearNumber(
            Vehicle vehicle
    ) {

        try {

            return Integer.parseInt(
                    safe(
                            vehicle.getYear()
                    )
            );

        } catch (Exception e) {

            return 0;
        }
    }

    // =========================================================
    // RENDER
    // =========================================================

    private void renderVehicles(
            List<Vehicle> vehicles
    ) {

        vehicleCards
                .getChildren()
                .clear();

        if (
                vehicles.isEmpty()
        ) {

            vehicleCards
                    .getChildren()
                    .add(
                            createEmptyState()
                    );

            resultCountLabel.setText(
                    "0 vehicles found"
            );

            return;
        }

        int number = 1;

        for (
                Vehicle vehicle :
                vehicles
        ) {

            vehicleCards
                    .getChildren()
                    .add(
                            createVehicleCard(
                                    vehicle,
                                    number++
                            )
                    );
        }

        resultCountLabel.setText(
                vehicles.size() == 1
                        ? "1 vehicle"
                        : vehicles.size() +
                          " vehicles"
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
                new Label("▰");

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
                        "No vehicles found"
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
                        "Try changing your search or filter."
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

        Button add =
                createActionButton(
                        "+ Add Vehicle",
                        ORANGE
                );

        add.setOnAction(
                event ->
                        showAddDialog()
        );

        box.getChildren().addAll(
                icon,
                title,
                subtitle,
                add
        );

        return box;
    }

    // =========================================================
    // STATISTICS UPDATE
    // =========================================================

    private void updateStatistics(
            List<Vehicle> vehicles
    ) {

        int active = 0;
        int inactive = 0;

        for (
                Vehicle vehicle :
                vehicles
        ) {

            if (
                    "Active".equalsIgnoreCase(
                            safe(
                                    vehicle.getStatus()
                            )
                    )
            ) {

                active++;

            } else {

                inactive++;
            }
        }

        totalLabel.setText(
                String.valueOf(
                        vehicles.size()
                )
        );

        activeLabel.setText(
                String.valueOf(
                        active
                )
        );

        inactiveLabel.setText(
                String.valueOf(
                        inactive
                )
        );
    }

    // =========================================================
    // ADD VEHICLE
    // =========================================================

    private void showAddDialog() {

        Dialog<Vehicle> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Add Vehicle"
        );

        dialog.setHeaderText(
                "Register New Vehicle"
        );

        ButtonType saveButton =
                new ButtonType(
                        "Save",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        saveButton,
                        ButtonType.CANCEL
                );

        GridPane form =
                createForm();

        TextField customerId =
                field();

        TextField ownerName =
                field();

        TextField vehicleNumber =
                field();

        TextField brand =
                field();

        TextField model =
                field();

        TextField year =
                field();

        ComboBox<String> vehicleType =
                createCombo(
                        "Car",
                        "Bike",
                        "Scooter",
                        "SUV",
                        "Truck",
                        "Other"
                );

        ComboBox<String> fuelType =
                createCombo(
                        "Petrol",
                        "Diesel",
                        "CNG",
                        "Electric",
                        "Hybrid"
                );

        ComboBox<String> status =
                createCombo(
                        "Active",
                        "Inactive"
                );

        addFormRow(
                form,
                "Customer ID",
                customerId,
                0
        );

        addFormRow(
                form,
                "Owner Name",
                ownerName,
                1
        );

        addFormRow(
                form,
                "Vehicle Number",
                vehicleNumber,
                2
        );

        addFormRow(
                form,
                "Brand",
                brand,
                3
        );

        addFormRow(
                form,
                "Model",
                model,
                4
        );

        addFormRow(
                form,
                "Vehicle Type",
                vehicleType,
                5
        );

        addFormRow(
                form,
                "Fuel Type",
                fuelType,
                6
        );

        addFormRow(
                form,
                "Year",
                year,
                7
        );

        addFormRow(
                form,
                "Status",
                status,
                8
        );

        dialog.getDialogPane()
                .setContent(form);

        styleDialog(
                dialog
        );

        dialog.setResultConverter(
                button -> {

                    if (
                            button != saveButton
                    ) {

                        return null;
                    }

                    if (
                            vehicleNumber
                                    .getText()
                                    .isBlank()
                            ||
                            ownerName
                                    .getText()
                                    .isBlank()
                    ) {

                        showError(
                                "Invalid Data",
                                "Vehicle number and owner name are required."
                        );

                        return null;
                    }

                    return new Vehicle(
                            "",
                            customerId
                                    .getText()
                                    .trim(),
                            ownerName
                                    .getText()
                                    .trim(),
                            vehicleNumber
                                    .getText()
                                    .trim(),
                            brand
                                    .getText()
                                    .trim(),
                            model
                                    .getText()
                                    .trim(),
                            vehicleType
                                    .getValue(),
                            fuelType
                                    .getValue(),
                            year
                                    .getText()
                                    .trim(),
                            status
                                    .getValue(),
                            String.valueOf(
                                    System.currentTimeMillis()
                            )
                    );
                }
        );

        dialog.showAndWait()
                .ifPresent(
                        vehicle -> {

                            try {

                                if (
                                        controller.addVehicle(
                                                vehicle
                                        )
                                ) {

                                    loadVehicles();

                                    showInfo(
                                            "Success",
                                            "Vehicle added successfully."
                                    );

                                } else {

                                    showError(
                                            "Failed",
                                            "Vehicle could not be added."
                                    );
                                }

                            } catch (Exception e) {

                                showError(
                                        "Failed",
                                        e.getMessage()
                                );
                            }
                        }
                );
    }

    // =========================================================
    // FORM
    // =========================================================

    private GridPane createForm() {

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(13);

        form.setPadding(
                new Insets(20)
        );

        return form;
    }

    private TextField field() {

        TextField field =
                new TextField();

        field.setPrefWidth(280);
        field.setPrefHeight(40);

        styleTextField(
                field
        );

        return field;
    }

    private ComboBox<String> createCombo(
            String... values
    ) {

        ComboBox<String> combo =
                new ComboBox<>();

        combo.getItems()
                .addAll(
                        values
                );

        if (
                values.length > 0
        ) {

            combo.setValue(
                    values[0]
            );
        }

        combo.setPrefWidth(280);
        combo.setPrefHeight(40);

        styleComboBox(
                combo
        );

        return combo;
    }

    private void addFormRow(
            GridPane form,
            String labelText,
            Control control,
            int row
    ) {

        Label label =
                new Label(
                        labelText
                );

        label.setTextFill(
                Color.web(HEADING)
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        form.add(
                label,
                0,
                row
        );

        form.add(
                control,
                1,
                row
        );
    }

    private void styleDialog(
            Dialog<?> dialog
    ) {

        dialog.getDialogPane()
                .setStyle(
                        "-fx-background-color: " +
                        BG +
                        ";"
                );
    }

    // =========================================================
    // EDIT VEHICLE PAGE NAVIGATION
    // =========================================================

    private void showEditVehiclePage(
            Vehicle vehicle
    ) {

        EditVehiclePage editPage =
                new EditVehiclePage(
                        vehicle,
                        this::showVehicleManagement
                );

        root.getChildren().clear();

        root.setPadding(
                new Insets(0)
        );

        root.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        VBox editView =
                editPage.getView();

        root.getChildren().add(
                editView
        );

        VBox.setVgrow(
                editView,
                Priority.ALWAYS
        );
    }

    // =========================================================
    // DETAILS PAGE NAVIGATION
    // =========================================================

    private void showDetails(
            Vehicle vehicle
    ) {

        VehicleDetailsPage detailsPage =
                new VehicleDetailsPage(
                        vehicle,
                        this::showVehicleManagement
                );

        root.getChildren().clear();

        root.setPadding(
                new Insets(0)
        );

        root.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        VBox detailsView =
                detailsPage.getView();

        root.getChildren().add(
                detailsView
        );

        VBox.setVgrow(
                detailsView,
                Priority.ALWAYS
        );
    }

    // =========================================================
    // SAFE
    // =========================================================

    private String safe(
            String value
    ) {

        return value == null ||
               value.isBlank()
                ? "-"
                : value;
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
                new Insets(30)
        );

        box.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        Label icon =
                new Label("!");

        icon.setTextFill(
                Color.web(RED)
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
                        "Vehicle Management Error"
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

        Label text =
                new Label(
                        message
                );

        text.setTextFill(
                Color.web(TEXT)
        );

        text.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        box.getChildren().addAll(
                icon,
                title,
                text
        );

        return box;
    }

    // =========================================================
    // ALERTS
    // =========================================================

    private void showInfo(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showError(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(title);
        alert.setHeaderText(null);

        alert.setContentText(
                message == null
                        ? "Unknown error."
                        : message
        );

        alert.showAndWait();
    }
}
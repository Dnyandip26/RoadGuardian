package project.ui.admin;

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

import project.controller.admin.MechanicController;
import project.firebase.FirebaseConfig;
import project.model.Mechanic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MechanicManagementPage extends AdminSectionPage {

    // =========================================================
    // AQUA MIST THEME
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
    // DATA
    // =========================================================

    private MechanicController controller;

    private final ObservableList<Mechanic> mechanicList =
            FXCollections.observableArrayList();

    private final List<Mechanic> allMechanics =
            new ArrayList<>();

    // =========================================================
    // CONTROLS
    // =========================================================

    private VBox mechanicCards;
    private TextField searchField;
    private ComboBox<String> statusFilter;
    private ComboBox<String> sortFilter;
    private Label resultCountLabel;

    private Label totalLabel;
    private Label activeLabel;
    private Label inactiveLabel;

    // =========================================================
    // VIEW
    // =========================================================

    @Override
    public VBox getView() {

        VBox root = new VBox(22);
        root.setPadding(new Insets(30, 32, 32, 32));
        root.setStyle("-fx-background-color: " + BG + ";");

        try {
            Firestore firestore = FirebaseConfig.getFirestore();
            controller = new MechanicController(firestore);
        } catch (Exception e) {
            e.printStackTrace();
            return createErrorView("Unable to connect to Firestore.");
        }

        VBox header = createHeader();
        HBox statistics = createStatistics();
        HBox toolbar = createToolbar();
        VBox recordsCard = createMechanicRecordsCard();

        root.getChildren().addAll(
                header,
                statistics,
                toolbar,
                recordsCard
        );

        VBox.setVgrow(recordsCard, Priority.ALWAYS);

        loadMechanics();

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header = new VBox(7);

        Label title = new Label("Mechanics");
        title.setTextFill(Color.web(HEADING));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Label subtitle = new Label(
                "Manage RoadGuardian mechanics and their service availability."
        );
        subtitle.setTextFill(Color.web(TEXT));
        subtitle.setFont(Font.font("Arial", 16));

        header.getChildren().addAll(title, subtitle);

        return header;
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private HBox createStatistics() {

        HBox box = new HBox(16);

        totalLabel = createValueLabel(BLUE);
        activeLabel = createValueLabel(GREEN);
        inactiveLabel = createValueLabel(RED);

        VBox total = createStatCard(
                "Total Mechanics",
                "All registered mechanics",
                totalLabel,
                BLUE,
                "TOTAL"
        );

        VBox active = createStatCard(
                "Active Mechanics",
                "Currently available",
                activeLabel,
                GREEN,
                "ACTIVE"
        );

        VBox inactive = createStatCard(
                "Inactive Mechanics",
                "Currently unavailable",
                inactiveLabel,
                RED,
                "INACTIVE"
        );

        HBox.setHgrow(total, Priority.ALWAYS);
        HBox.setHgrow(active, Priority.ALWAYS);
        HBox.setHgrow(inactive, Priority.ALWAYS);

        box.getChildren().addAll(total, active, inactive);

        return box;
    }

    private Label createValueLabel(String color) {

        Label label = new Label("0");
        label.setTextFill(Color.web(color));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        return label;
    }

    private VBox createStatCard(
            String title,
            String subtitle,
            Label value,
            String color,
            String tagText
    ) {

        VBox card = new VBox(9);
        card.setPadding(new Insets(20, 22, 18, 22));
        card.setMinHeight(130);

        String normalStyle =
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;";

        String hoverStyle =
                "-fx-background-color: #F7FCFC;" +
                "-fx-border-color: " + color + ";" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.12), 10, 0.12, 0, 2);";

        card.setStyle(normalStyle);
        card.setCursor(Cursor.HAND);

        HBox top = new HBox();
        top.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.web(HEADING));
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label tag = new Label(tagText);
        tag.setTextFill(Color.web(color));
        tag.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        tag.setPadding(new Insets(6, 10, 6, 10));
        tag.setStyle(
                "-fx-background-color: " + color + "18;" +
                "-fx-background-radius: 20;"
        );

        top.getChildren().addAll(titleLabel, spacer, tag);

        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.setTextFill(Color.web(TEXT));
        subtitleLabel.setFont(Font.font("Arial", 14));

        card.getChildren().addAll(top, value, subtitleLabel);

        card.setOnMouseEntered(event -> {
            card.setStyle(hoverStyle);
            card.setTranslateY(-2);
        });

        card.setOnMouseExited(event -> {
            card.setStyle(normalStyle);
            card.setTranslateY(0);
        });

        return card;
    }

    // =========================================================
    // TOOLBAR
    // =========================================================

    private HBox createToolbar() {

        HBox toolbar = new HBox(12);
        toolbar.setAlignment(Pos.CENTER_LEFT);

        searchField = new TextField();
        searchField.setPromptText(
                "Search by name, email, phone, city or specialization..."
        );
        searchField.setPrefWidth(410);
        searchField.setPrefHeight(44);
        styleTextField(searchField);

        statusFilter = new ComboBox<>();
        statusFilter.getItems().addAll(
                "All",
                "Active",
                "Inactive"
        );
        statusFilter.setValue("All");
        statusFilter.setPrefWidth(120);
        statusFilter.setPrefHeight(44);
        styleComboBox(statusFilter);

        sortFilter = new ComboBox<>();
        sortFilter.getItems().addAll(
                "Newest First",
                "Oldest First",
                "Name A-Z",
                "Name Z-A",
                "Experience High-Low",
                "Experience Low-High"
        );
        sortFilter.setValue("Newest First");
        sortFilter.setPrefWidth(160);
        sortFilter.setPrefHeight(44);
        styleComboBox(sortFilter);

        Button refresh = createActionButton("Refresh", BLUE);
        refresh.setPrefHeight(44);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button add = createActionButton("+ Add Mechanic", ORANGE);
        add.setPrefHeight(44);
        add.setPadding(new Insets(0, 20, 0, 20));

        searchField.textProperty().addListener(
                (obs, oldValue, newValue) -> applyFilters()
        );

        statusFilter.valueProperty().addListener(
                (obs, oldValue, newValue) -> applyFilters()
        );

        sortFilter.valueProperty().addListener(
                (obs, oldValue, newValue) -> applyFilters()
        );

        refresh.setOnAction(event -> loadMechanics());
        add.setOnAction(event -> showAddDialog());

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
    // ACTION BUTTON
    // =========================================================

    private Button createActionButton(
            String text,
            String color
    ) {

        Button button = new Button(text);
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setPrefHeight(42);
        button.setPadding(new Insets(0, 18, 0, 18));
        button.setCursor(Cursor.HAND);

        setButtonStyle(button, color);

        button.setOnMouseEntered(event ->
                setButtonStyle(
                        button,
                        color.equals(ORANGE)
                                ? ORANGE_HOVER
                                : "#1D4ED8"
                )
        );

        button.setOnMouseExited(event ->
                setButtonStyle(button, color)
        );

        return button;
    }

    private void setButtonStyle(Button button, String color) {

        button.setStyle(
                "-fx-background-color: " + color + ";" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // RECORDS CARD
    // =========================================================

    private VBox createMechanicRecordsCard() {

        VBox card = new VBox(15);
        card.setPadding(new Insets(21));
        card.setMinHeight(430);

        card.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;"
        );

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        VBox headingBox = new VBox(4);

        Label heading = new Label("Mechanic Information");
        heading.setTextFill(Color.web(HEADING));
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label subtitle = new Label(
                "Registered mechanics, skills and current service availability"
        );
        subtitle.setTextFill(Color.web(TEXT));
        subtitle.setFont(Font.font("Arial", 14));

        resultCountLabel = new Label("0 mechanics");
        resultCountLabel.setTextFill(Color.web(BLUE));
        resultCountLabel.setFont(
                Font.font("Arial", FontWeight.BOLD, 14)
        );

        headingBox.getChildren().addAll(
                heading,
                subtitle,
                resultCountLabel
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label live = new Label("● Live Data");
        live.setTextFill(Color.web(GREEN));
        live.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        live.setPadding(new Insets(7, 11, 7, 11));
        live.setStyle(
                "-fx-background-color: " + GREEN + "18;" +
                "-fx-background-radius: 20;"
        );

        header.getChildren().addAll(
                headingBox,
                spacer,
                live
        );

        mechanicCards = new VBox(12);
        mechanicCards.setFillWidth(true);
        mechanicCards.setPadding(new Insets(3, 2, 10, 2));

        ScrollPane scrollPane = new ScrollPane(mechanicCards);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;"
        );

        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        card.getChildren().addAll(header, scrollPane);

        return card;
    }

    // =========================================================
    // MECHANIC CARD
    // =========================================================

    private VBox createMechanicCard(
            Mechanic mechanic,
            int number
    ) {

        VBox card = new VBox(14);
        card.setPadding(new Insets(18, 20, 18, 20));
        card.setMaxWidth(Double.MAX_VALUE);
        card.setCursor(Cursor.HAND);

        String normalStyle =
                "-fx-background-color: " + SECONDARY + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;";

        String hoverStyle =
                "-fx-background-color: #EAF8FA;" +
                "-fx-border-color: " + BLUE + ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;" +
                "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.18), 12, 0.15, 0, 3);";

        card.setStyle(normalStyle);

        // -----------------------------------------------------
        // TOP
        // -----------------------------------------------------

        HBox top = new HBox(13);
        top.setAlignment(Pos.CENTER_LEFT);

        StackPane avatar = createAvatar(
                safe(mechanic.getName())
        );

        VBox identity = new VBox(3);

        HBox nameLine = new HBox(9);
        nameLine.setAlignment(Pos.CENTER_LEFT);

        Label numberLabel = new Label("#" + number);
        numberLabel.setTextFill(Color.web(BLUE));
        numberLabel.setFont(
                Font.font("Arial", FontWeight.BOLD, 12)
        );
        numberLabel.setPadding(new Insets(4, 8, 4, 8));
        numberLabel.setStyle(
                "-fx-background-color: " + BLUE + "14;" +
                "-fx-background-radius: 20;"
        );

        Label name = new Label(
                safe(mechanic.getName())
        );
        name.setTextFill(Color.web(HEADING));
        name.setFont(
                Font.font("Arial", FontWeight.BOLD, 18)
        );

        nameLine.getChildren().addAll(
                numberLabel,
                name
        );

        Label role = new Label(
                safe(mechanic.getSpecialization())
        );
        role.setTextFill(Color.web(TEXT));
        role.setFont(Font.font("Arial", 13));

        identity.getChildren().addAll(nameLine, role);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label statusBadge = createStatusBadge(
                safe(mechanic.getStatus())
        );

        top.getChildren().addAll(
                avatar,
                identity,
                spacer,
                statusBadge
        );

        // -----------------------------------------------------
        // INFORMATION
        // -----------------------------------------------------

        HBox infoRow = new HBox(12);
        infoRow.setFillHeight(true);

        VBox phone = createInfoBox(
                "PHONE",
                safe(mechanic.getPhone())
        );

        VBox email = createInfoBox(
                "EMAIL",
                safe(mechanic.getEmail())
        );

        VBox city = createInfoBox(
                "CITY",
                safe(mechanic.getCity())
        );

        VBox specialization = createInfoBox(
                "SPECIALIZATION",
                safe(mechanic.getSpecialization())
        );

        VBox experience = createInfoBox(
                "EXPERIENCE",
                safe(mechanic.getExperience())
        );

        HBox.setHgrow(phone, Priority.ALWAYS);
        HBox.setHgrow(email, Priority.ALWAYS);
        HBox.setHgrow(city, Priority.ALWAYS);
        HBox.setHgrow(specialization, Priority.ALWAYS);
        HBox.setHgrow(experience, Priority.ALWAYS);

        infoRow.getChildren().addAll(
                phone,
                email,
                city,
                specialization,
                experience
        );

        // -----------------------------------------------------
        // BOTTOM
        // -----------------------------------------------------

        HBox bottom = new HBox(12);
        bottom.setAlignment(Pos.CENTER_LEFT);

        Label idLabel = new Label(
                "Database ID: " + safe(mechanic.getMechanicId())
        );
        idLabel.setTextFill(Color.web(TEXT));
        idLabel.setFont(Font.font("Arial", 12));

        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        Button viewButton = new Button("View Details →");
        styleSmallButton(viewButton, BLUE);

        viewButton.setOnAction(
                event -> showDetails(mechanic)
        );

        bottom.getChildren().addAll(
                idLabel,
                bottomSpacer,
                viewButton
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

        card.setOnMouseEntered(event -> {
            card.setStyle(hoverStyle);
            card.setTranslateY(-2);
        });

        card.setOnMouseExited(event -> {
            card.setStyle(normalStyle);
            card.setTranslateY(0);
        });

        // -----------------------------------------------------
        // DOUBLE CLICK
        // -----------------------------------------------------

        card.setOnMouseClicked(event -> {
            if (
                    event.getClickCount() == 2 &&
                    event.getTarget() != viewButton
            ) {
                showDetails(mechanic);
            }
        });

        return card;
    }

    // =========================================================
    // AVATAR
    // =========================================================

    private StackPane createAvatar(String name) {

        Circle circle = new Circle(
                24,
                Color.web(BLUE)
        );

        Label initials = new Label(
                getInitials(name)
        );

        initials.setTextFill(Color.WHITE);
        initials.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        StackPane avatar = new StackPane(
                circle,
                initials
        );

        avatar.setMinSize(48, 48);
        avatar.setPrefSize(48, 48);
        avatar.setMaxSize(48, 48);

        return avatar;
    }

    private String getInitials(String name) {

        if (name == null || name.isBlank()) {
            return "?";
        }

        String[] parts = name.trim().split("\\s+");

        if (parts.length == 1) {
            return parts[0]
                    .substring(0, Math.min(2, parts[0].length()))
                    .toUpperCase();
        }

        return (
                parts[0].substring(0, 1) +
                parts[parts.length - 1].substring(0, 1)
        ).toUpperCase();
    }

    // =========================================================
    // INFO BOX
    // =========================================================

    private VBox createInfoBox(
            String title,
            String value
    ) {

        VBox box = new VBox(5);
        box.setPadding(new Insets(10, 12, 10, 12));
        box.setMinWidth(120);

        box.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-background-radius: 9;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 9;"
        );

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.web(TEXT));
        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        Label valueLabel = new Label(value);
        valueLabel.setTextFill(Color.web(HEADING));
        valueLabel.setFont(Font.font("Arial", 14));
        valueLabel.setWrapText(true);

        box.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return box;
    }

    // =========================================================
    // STATUS BADGE
    // =========================================================

    private Label createStatusBadge(String status) {

        Label badge = new Label(
                status.equals("-") ? "Unknown" : status
        );

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        badge.setPadding(
                new Insets(7, 12, 7, 12)
        );

        if (status.equalsIgnoreCase("Active")) {
            badge.setTextFill(Color.web(GREEN));
            badge.setStyle(
                    "-fx-background-color: " + GREEN + "18;" +
                    "-fx-background-radius: 20;"
            );
        } else {
            badge.setTextFill(Color.web(RED));
            badge.setStyle(
                    "-fx-background-color: " + RED + "18;" +
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

        button.setTextFill(
                color.equals(BLUE)
                        ? Color.WHITE
                        : Color.web(HEADING)
        );

        button.setPadding(
                new Insets(8, 13, 8, 13)
        );

        button.setCursor(Cursor.HAND);

        String normalColor =
                color.equals(BLUE) ? BLUE : SURFACE;

        String hoverColor =
                color.equals(BLUE) ? "#1D4ED8" : SECONDARY;

        button.setStyle(
                "-fx-background-color: " + normalColor + ";" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: " +
                (color.equals(BLUE) ? BLUE : BORDER) + ";" +
                "-fx-border-radius: 8;"
        );

        button.setOnMouseEntered(event ->
                button.setStyle(
                        "-fx-background-color: " + hoverColor + ";" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: " +
                        (color.equals(BLUE) ? "#1D4ED8" : BLUE) + ";" +
                        "-fx-border-radius: 8;"
                )
        );

        button.setOnMouseExited(event ->
                button.setStyle(
                        "-fx-background-color: " + normalColor + ";" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: " +
                        (color.equals(BLUE) ? BLUE : BORDER) + ";" +
                        "-fx-border-radius: 8;"
                )
        );
    }

    // =========================================================
    // TEXT / COMBO STYLE
    // =========================================================

    private void styleTextField(TextField field) {

        field.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-text-fill: " + HEADING + ";" +
                "-fx-prompt-text-fill: " + TEXT + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-padding: 0 14 0 14;" +
                "-fx-font-size: 15px;"
        );
    }

    private void styleComboBox(ComboBox<String> comboBox) {

        comboBox.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 14px;"
        );
    }

    // =========================================================
    // LOAD
    // =========================================================

    private void loadMechanics() {

        try {

            List<Mechanic> mechanics =
                    controller.getAllMechanics();

            allMechanics.clear();
            allMechanics.addAll(mechanics);

            updateStatistics(allMechanics);
            applyFilters();

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Unable to Load Mechanics",
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // FILTER + SEARCH + SORT
    // =========================================================

    private void applyFilters() {

        if (mechanicCards == null) {
            return;
        }

        String search =
                searchField == null
                        ? ""
                        : searchField.getText()
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

        List<Mechanic> filtered =
                new ArrayList<>();

        for (Mechanic mechanic : allMechanics) {

            if (!matchesSearch(mechanic, search)) {
                continue;
            }

            if (
                    !"All".equals(status) &&
                    !status.equalsIgnoreCase(
                            safe(mechanic.getStatus())
                    )
            ) {
                continue;
            }

            filtered.add(mechanic);
        }

        sortMechanics(filtered, sort);

        mechanicList.setAll(filtered);
        renderCards(filtered);
    }

    private boolean matchesSearch(
            Mechanic mechanic,
            String search
    ) {

        if (search.isBlank()) {
            return true;
        }

        return safe(mechanic.getName())
                .toLowerCase()
                .contains(search)
                ||
                safe(mechanic.getEmail())
                        .toLowerCase()
                        .contains(search)
                ||
                safe(mechanic.getPhone())
                        .toLowerCase()
                        .contains(search)
                ||
                safe(mechanic.getCity())
                        .toLowerCase()
                        .contains(search)
                ||
                safe(mechanic.getSpecialization())
                        .toLowerCase()
                        .contains(search);
    }

    private void sortMechanics(
            List<Mechanic> mechanics,
            String sort
    ) {

        if ("Name A-Z".equals(sort)) {

            mechanics.sort(
                    Comparator.comparing(
                            mechanic ->
                                    safe(mechanic.getName())
                                            .toLowerCase()
                    )
            );

        } else if ("Name Z-A".equals(sort)) {

            mechanics.sort(
                    Comparator.comparing(
                            (Mechanic mechanic) ->
                                    safe(mechanic.getName())
                                            .toLowerCase()
                    ).reversed()
            );

        } else if ("Experience High-Low".equals(sort)) {

            mechanics.sort(
                    Comparator.comparingInt(
                            this::experienceNumber
                    ).reversed()
            );

        } else if ("Experience Low-High".equals(sort)) {

            mechanics.sort(
                    Comparator.comparingInt(
                            this::experienceNumber
                    )
            );

        } else if ("Oldest First".equals(sort)) {

            mechanics.sort(
                    Comparator.comparing(
                            mechanic ->
                                    safe(mechanic.getCreatedAt())
                    )
            );

        } else {

            mechanics.sort(
                    Comparator.comparing(
                            (Mechanic mechanic) ->
                                    safe(mechanic.getCreatedAt())
                    ).reversed()
            );
        }
    }

    private int experienceNumber(Mechanic mechanic) {

        try {
            return Integer.parseInt(
                    safe(mechanic.getExperience())
                            .replaceAll("[^0-9]", "")
            );
        } catch (Exception e) {
            return 0;
        }
    }

    // =========================================================
    // RENDER
    // =========================================================

    private void renderCards(
            List<Mechanic> mechanics
    ) {

        mechanicCards.getChildren().clear();

        if (mechanics.isEmpty()) {

            mechanicCards.getChildren().add(
                    createEmptyState()
            );

            resultCountLabel.setText(
                    "0 mechanics found"
            );

            return;
        }

        int number = 1;

        for (Mechanic mechanic : mechanics) {

            mechanicCards.getChildren().add(
                    createMechanicCard(
                            mechanic,
                            number++
                    )
            );
        }

        resultCountLabel.setText(
                mechanics.size() == 1
                        ? "1 mechanic"
                        : mechanics.size() + " mechanics"
        );
    }

    // =========================================================
    // EMPTY STATE
    // =========================================================

    private VBox createEmptyState() {

        VBox box = new VBox(9);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(45));

        Label icon = new Label("⚒");
        icon.setTextFill(Color.web(BLUE));
        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );

        Label title = new Label("No mechanics found");
        title.setTextFill(Color.web(HEADING));
        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        Label subtitle = new Label(
                "Try changing your search or filter."
        );
        subtitle.setTextFill(Color.web(TEXT));
        subtitle.setFont(Font.font("Arial", 14));

        Button add = createActionButton(
                "+ Add Mechanic",
                ORANGE
        );

        add.setOnAction(event -> showAddDialog());

        box.getChildren().addAll(
                icon,
                title,
                subtitle,
                add
        );

        return box;
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private void updateStatistics(
            List<Mechanic> mechanics
    ) {

        int active = 0;
        int inactive = 0;

        for (Mechanic mechanic : mechanics) {

            if (
                    "Active".equalsIgnoreCase(
                            safe(mechanic.getStatus())
                    )
            ) {
                active++;
            } else {
                inactive++;
            }
        }

        totalLabel.setText(
                String.valueOf(mechanics.size())
        );

        activeLabel.setText(
                String.valueOf(active)
        );

        inactiveLabel.setText(
                String.valueOf(inactive)
        );
    }

    // =========================================================
    // ADD DIALOG
    // =========================================================

    private void showAddDialog() {

        Dialog<Mechanic> dialog = new Dialog<>();

        dialog.setTitle("Add Mechanic");
        dialog.setHeaderText("Create New Mechanic");

        ButtonType saveButton = new ButtonType(
                "Save",
                ButtonBar.ButtonData.OK_DONE
        );

        dialog.getDialogPane().getButtonTypes().addAll(
                saveButton,
                ButtonType.CANCEL
        );

        GridPane form = createForm();

        TextField name = field();
        TextField email = field();
        TextField phone = field();
        TextField address = field();
        TextField city = field();
        TextField specialization = field();
        TextField experience = field();

        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll(
                "Active",
                "Inactive"
        );
        status.setValue("Active");
        status.setPrefWidth(280);

        addFormRow(form, "Name", name, 0);
        addFormRow(form, "Email", email, 1);
        addFormRow(form, "Phone", phone, 2);
        addFormRow(form, "Address", address, 3);
        addFormRow(form, "City", city, 4);
        addFormRow(form, "Specialization", specialization, 5);
        addFormRow(form, "Experience", experience, 6);
        addFormRow(form, "Status", status, 7);

        dialog.getDialogPane().setContent(form);

        dialog.setResultConverter(button -> {

            if (button != saveButton) {
                return null;
            }

            return new Mechanic(
                    "",
                    name.getText().trim(),
                    email.getText().trim(),
                    phone.getText().trim(),
                    address.getText().trim(),
                    city.getText().trim(),
                    specialization.getText().trim(),
                    status.getValue(),
                    experience.getText().trim(),
                    String.valueOf(
                            System.currentTimeMillis()
                    )
            );
        });

        dialog.showAndWait().ifPresent(mechanic -> {

            try {

                if (controller.addMechanic(mechanic)) {

                    loadMechanics();

                    showInfo(
                            "Success",
                            "Mechanic added successfully."
                    );

                } else {

                    showError(
                            "Failed",
                            "Mechanic could not be added."
                    );
                }

            } catch (Exception e) {

                e.printStackTrace();

                showError(
                        "Failed",
                        e.getMessage()
                );
            }
        });
    }

    // =========================================================
    // FORM
    // =========================================================

    private GridPane createForm() {

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(13);
        form.setPadding(new Insets(20));

        return form;
    }

    private TextField field() {

        TextField field = new TextField();

        field.setPrefWidth(280);
        field.setPrefHeight(40);

        styleTextField(field);

        return field;
    }

    private void addFormRow(
            GridPane form,
            String labelText,
            Control control,
            int row
    ) {

        Label label = new Label(labelText);

        label.setTextFill(Color.web(HEADING));
        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        form.add(label, 0, row);
        form.add(control, 1, row);
    }

    // =========================================================
    // DETAILS
    // =========================================================

    private void showDetails(
            Mechanic mechanic
    ) {

        Dialog<Void> dialog = new Dialog<>();

        dialog.setTitle("Mechanic Details");
        dialog.setHeaderText(
                safe(mechanic.getName())
        );

        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType(
                        "Close",
                        ButtonBar.ButtonData.CANCEL_CLOSE
                )
        );

        VBox content = new VBox(12);
        content.setPadding(new Insets(20));
        content.setPrefWidth(480);

        HBox profile = new HBox(13);
        profile.setAlignment(Pos.CENTER_LEFT);

        StackPane avatar = createAvatar(
                safe(mechanic.getName())
        );

        VBox identity = new VBox(3);

        Label name = new Label(
                safe(mechanic.getName())
        );
        name.setTextFill(Color.web(HEADING));
        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        Label specialization = new Label(
                safe(mechanic.getSpecialization())
        );
        specialization.setTextFill(Color.web(TEXT));
        specialization.setFont(Font.font("Arial", 14));

        identity.getChildren().addAll(
                name,
                specialization
        );

        profile.getChildren().addAll(
                avatar,
                identity
        );

        content.getChildren().addAll(
                profile,
                new Separator(),
                createDetailRow(
                        "Mechanic ID",
                        mechanic.getMechanicId()
                ),
                createDetailRow(
                        "Email",
                        mechanic.getEmail()
                ),
                createDetailRow(
                        "Phone",
                        mechanic.getPhone()
                ),
                createDetailRow(
                        "Address",
                        mechanic.getAddress()
                ),
                createDetailRow(
                        "City",
                        mechanic.getCity()
                ),
                createDetailRow(
                        "Specialization",
                        mechanic.getSpecialization()
                ),
                createDetailRow(
                        "Experience",
                        mechanic.getExperience()
                ),
                createDetailRow(
                        "Status",
                        mechanic.getStatus()
                ),
                createDetailRow(
                        "Registered",
                        mechanic.getCreatedAt()
                )
        );

        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().setStyle(
                "-fx-background-color: " + BG + ";"
        );

        dialog.showAndWait();
    }

    private HBox createDetailRow(
            String labelText,
            String valueText
    ) {

        HBox row = new HBox(15);
        row.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(labelText);
        label.setPrefWidth(110);
        label.setTextFill(Color.web(TEXT));
        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        Label value = new Label(safe(valueText));
        value.setTextFill(Color.web(HEADING));
        value.setFont(Font.font("Arial", 15));
        value.setWrapText(true);

        HBox.setHgrow(value, Priority.ALWAYS);

        row.getChildren().addAll(label, value);

        return row;
    }

    // =========================================================
    // SAFE
    // =========================================================

    private String safe(String value) {

        return value == null || value.isBlank()
                ? "-"
                : value;
    }

    // =========================================================
    // ERROR VIEW
    // =========================================================

    private VBox createErrorView(String message) {

        VBox box = new VBox(12);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30));
        box.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        Label icon = new Label("!");
        icon.setTextFill(Color.web(RED));
        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        Label title = new Label(
                "Mechanic Management Error"
        );
        title.setTextFill(Color.web(HEADING));
        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        Label text = new Label(message);
        text.setTextFill(Color.web(TEXT));
        text.setFont(Font.font("Arial", 15));

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
                new Alert(Alert.AlertType.INFORMATION);

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
                new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(
                message == null
                        ? "Unknown error"
                        : message
        );
        alert.showAndWait();
    }
}
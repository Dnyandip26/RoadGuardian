package project.ui.admin;

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

public class ServiceManagementPage {

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

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    // =========================================================
    // DATA
    // =========================================================

    private final ObservableList<ServiceData> serviceList =
            FXCollections.observableArrayList();

    private final ObservableList<ServiceData> filteredList =
            FXCollections.observableArrayList();

    private VBox serviceCards;

    private TextField searchField;
    private ComboBox<String> statusFilter;
    private ComboBox<String> sortFilter;

    private Label resultCountLabel;

    // =========================================================
    // STATISTICS
    // =========================================================

    private Label totalLabel;
    private Label activeLabel;
    private Label inactiveLabel;

    // =========================================================
    // GET VIEW
    // =========================================================

    public VBox getView() {

        VBox root = new VBox(20);

        root.setPadding(
                new Insets(30, 32, 32, 32)
        );

        root.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        HBox header = createHeader();

        HBox statistics = createStatistics();

        HBox toolbar = createToolbar();

        VBox recordsCard = createRecordsCard();

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

        loadSampleServices();

        updateStatistics();

        applyFilters();

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private HBox createHeader() {

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(5);

        Label title =
                new Label("Services");

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
                        "Manage RoadGuardian roadside assistance services."
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

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button addButton =
                createPrimaryButton(
                        "+ Add Service"
                );

        addButton.setOnAction(
                event ->
                        showAddServiceDialog()
        );

        header.getChildren().addAll(
                titleBox,
                spacer,
                addButton
        );

        return header;
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private HBox createStatistics() {

        HBox box = new HBox(16);

        totalLabel =
                createValueLabel(BLUE);

        activeLabel =
                createValueLabel(GREEN);

        inactiveLabel =
                createValueLabel(RED);

        VBox totalCard =
                createStatCard(
                        "Total Services",
                        "All available services",
                        totalLabel,
                        BLUE,
                        "TOTAL"
                );

        VBox activeCard =
                createStatCard(
                        "Active",
                        "Currently available",
                        activeLabel,
                        GREEN,
                        "LIVE"
                );

        VBox inactiveCard =
                createStatCard(
                        "Inactive",
                        "Currently disabled",
                        inactiveLabel,
                        RED,
                        "OFF"
                );

        HBox.setHgrow(
                totalCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                activeCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                inactiveCard,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                totalCard,
                activeCard,
                inactiveCard
        );

        return box;
    }

    private Label createValueLabel(
            String color
    ) {

        Label label = new Label("0");

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

        VBox card = new VBox(8);

        card.setPadding(
                new Insets(18, 20, 17, 20)
        );

        card.setMinHeight(118);

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

        card.setStyle(normalStyle);

        card.setCursor(
                Cursor.HAND
        );

        HBox top = new HBox();

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

        Region spacer = new Region();

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
                        11
                )
        );

        tag.setPadding(
                new Insets(6, 10, 6, 10)
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

        HBox toolbar = new HBox(12);

        toolbar.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField =
                new TextField();

        searchField.setPromptText(
                "Search service name, ID or description..."
        );

        searchField.setPrefWidth(430);

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

        statusFilter.setPrefWidth(145);

        statusFilter.setPrefHeight(44);

        styleComboBox(
                statusFilter
        );

        sortFilter =
                new ComboBox<>();

        sortFilter.getItems().addAll(
                "Service A-Z",
                "Service Z-A",
                "Price Low-High",
                "Price High-Low"
        );

        sortFilter.setValue(
                "Service A-Z"
        );

        sortFilter.setPrefWidth(160);

        sortFilter.setPrefHeight(44);

        styleComboBox(
                sortFilter
        );

        Button refresh =
                createSecondaryButton(
                        "Refresh"
                );

        refresh.setPrefHeight(44);

        Region spacer = new Region();

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

        sortFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        refresh.setOnAction(
                event -> {

                    searchField.clear();

                    statusFilter.setValue(
                            "All"
                    );

                    sortFilter.setValue(
                            "Service A-Z"
                    );

                    applyFilters();
                }
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
    // RECORDS CARD
    // =========================================================

    private VBox createRecordsCard() {

        VBox card = new VBox(15);

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
                        "Service Information"
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
                        "RoadGuardian services available for customers."
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
                        "0 services"
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

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label live =
                new Label(
                        "● Service Catalog"
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

        serviceCards =
                new VBox(12);

        serviceCards.setFillWidth(
                true
        );

        serviceCards.setPadding(
                new Insets(
                        3,
                        2,
                        10,
                        2
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        serviceCards
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
    // SERVICE CARD
    // =========================================================

    private VBox createServiceCard(
            ServiceData service,
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
                BLUE +
                ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;" +
                "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.17), 12, 0.15, 0, 3);";

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
                createServiceIcon();

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
                        service.getId()
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
                        service.getName()
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

        Label status =
                createStatusBadge(
                        service.getStatus()
                );

        top.getChildren().addAll(
                icon,
                identity,
                spacer,
                status
        );

        // -----------------------------------------------------
        // INFORMATION
        // -----------------------------------------------------

        HBox infoRow =
                new HBox(12);

        VBox description =
                createInfoBox(
                        "DESCRIPTION",
                        service.getDescription()
                );

        VBox price =
                createInfoBox(
                        "PRICE",
                        service.getPrice()
                );

        VBox availability =
                createInfoBox(
                        "AVAILABILITY",
                        service.getStatus()
                                .equals("Active")
                                ? "Available to customers"
                                : "Currently disabled"
                );

        HBox.setHgrow(
                description,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                price,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                availability,
                Priority.ALWAYS
        );

        infoRow.getChildren().addAll(
                description,
                price,
                availability
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

        Button edit =
                createActionButton(
                        "Edit",
                        BLUE
                );

        Button delete =
                createActionButton(
                        "Delete",
                        RED
                );

        edit.setOnAction(
                event ->
                        showEditServiceDialog(
                                service
                        )
        );

        delete.setOnAction(
                event ->
                        deleteService(
                                service
                        )
        );

        bottom.getChildren().addAll(
                bottomSpacer,
                edit,
                delete
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

        // -----------------------------------------------------
        // DOUBLE CLICK
        // -----------------------------------------------------

        card.setOnMouseClicked(
                event -> {

                    if (
                            event.getClickCount() == 2
                    ) {

                        showServiceDetails(
                                service
                        );
                    }
                }
        );

        return card;
    }

    // =========================================================
    // SERVICE ICON
    // =========================================================

    private VBox createServiceIcon() {

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
                ORANGE +
                ";" +
                "-fx-background-radius: 12;"
        );

        Label icon =
                new Label(
                        "⚙"
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
                        safe(value)
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
    // STATUS BADGE
    // =========================================================

    private Label createStatusBadge(
            String status
    ) {

        Label badge =
                new Label(
                        status
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
                status.equals("Active")
                        ? GREEN
                        : RED;

        badge.setTextFill(
                Color.web(color)
        );

        badge.setStyle(
                "-fx-background-color: " +
                color +
                "18;" +
                "-fx-background-radius: 20;"
        );

        return badge;
    }

    // =========================================================
    // BUTTONS
    // =========================================================

    private Button createPrimaryButton(
            String text
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
                new Button(text);

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
                new Button(text);

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

        String status =
                statusFilter == null
                        ? "All"
                        : statusFilter.getValue();

        String sort =
                sortFilter == null
                        ? "Service A-Z"
                        : sortFilter.getValue();

        filteredList.clear();

        for (
                ServiceData service :
                serviceList
        ) {

            boolean matchesSearch =
                    search.isEmpty()
                    ||
                    service.getId()
                            .toLowerCase()
                            .contains(search)
                    ||
                    service.getName()
                            .toLowerCase()
                            .contains(search)
                    ||
                    service.getDescription()
                            .toLowerCase()
                            .contains(search);

            boolean matchesStatus =
                    status == null
                    ||
                    status.equals("All")
                    ||
                    service.getStatus()
                            .equalsIgnoreCase(status);

            if (
                    matchesSearch &&
                    matchesStatus
            ) {

                filteredList.add(
                        service
                );
            }
        }

        sortServices(
                filteredList,
                sort
        );

        renderServices();
    }

    // =========================================================
    // SORT
    // =========================================================

    private void sortServices(
            ObservableList<ServiceData> services,
            String sort
    ) {

        if (
                "Service Z-A".equals(sort)
        ) {

            services.sort(
                    (a, b) ->
                            b.getName()
                                    .compareToIgnoreCase(
                                            a.getName()
                                    )
            );

        } else if (
                "Price Low-High".equals(sort)
        ) {

            services.sort(
                    (a, b) ->
                            Double.compare(
                                    getPriceNumber(
                                            a.getPrice()
                                    ),
                                    getPriceNumber(
                                            b.getPrice()
                                    )
                            )
            );

        } else if (
                "Price High-Low".equals(sort)
        ) {

            services.sort(
                    (a, b) ->
                            Double.compare(
                                    getPriceNumber(
                                            b.getPrice()
                                    ),
                                    getPriceNumber(
                                            a.getPrice()
                                    )
                            )
            );

        } else {

            services.sort(
                    (a, b) ->
                            a.getName()
                                    .compareToIgnoreCase(
                                            b.getName()
                                    )
            );
        }
    }

    private double getPriceNumber(
            String price
    ) {

        if (
                price == null ||
                price.isBlank()
        ) {

            return 0;
        }

        String number =
                price
                        .replace(
                                "₹",
                                ""
                        )
                        .replace(
                                ",",
                                ""
                        )
                        .trim();

        try {

            return Double.parseDouble(
                    number
            );

        } catch (NumberFormatException e) {

            return 0;
        }
    }

    // =========================================================
    // RENDER
    // =========================================================

    private void renderServices() {

        serviceCards
                .getChildren()
                .clear();

        if (
                filteredList.isEmpty()
        ) {

            serviceCards
                    .getChildren()
                    .add(
                            createEmptyState()
                    );

            resultCountLabel.setText(
                    "0 services found"
            );

            return;
        }

        int number = 1;

        for (
                ServiceData service :
                filteredList
        ) {

            serviceCards
                    .getChildren()
                    .add(
                            createServiceCard(
                                    service,
                                    number++
                            )
                    );
        }

        resultCountLabel.setText(
                filteredList.size() == 1
                        ? "1 service"
                        : filteredList.size()
                          + " services"
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
                        "⚙"
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
                        "No services found"
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
                        "Try changing your search or status filter."
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
    // SAMPLE DATA
    // =========================================================

    private void loadSampleServices() {

        serviceList.clear();

        serviceList.addAll(

                new ServiceData(
                        "S001",
                        "Battery Assistance",
                        "Battery jump start and replacement support.",
                        "₹500",
                        "Active"
                ),

                new ServiceData(
                        "S002",
                        "Tyre Repair",
                        "Flat tyre repair and tyre replacement.",
                        "₹300",
                        "Active"
                ),

                new ServiceData(
                        "S003",
                        "Fuel Delivery",
                        "Emergency fuel delivery at roadside.",
                        "₹400",
                        "Active"
                ),

                new ServiceData(
                        "S004",
                        "Engine Breakdown",
                        "Engine inspection and roadside repair.",
                        "₹800",
                        "Active"
                ),

                new ServiceData(
                        "S005",
                        "Towing Service",
                        "Vehicle towing to the nearest service center.",
                        "₹1200",
                        "Active"
                ),

                new ServiceData(
                        "S006",
                        "Lockout Assistance",
                        "Vehicle lockout and key assistance.",
                        "₹600",
                        "Inactive"
                )
        );
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private void updateStatistics() {

        int total =
                serviceList.size();

        int active = 0;
        int inactive = 0;

        for (
                ServiceData service :
                serviceList
        ) {

            if (
                    service.getStatus()
                            .equalsIgnoreCase(
                                    "Active"
                            )
            ) {

                active++;

            } else {

                inactive++;
            }
        }

        totalLabel.setText(
                String.valueOf(total)
        );

        activeLabel.setText(
                String.valueOf(active)
        );

        inactiveLabel.setText(
                String.valueOf(inactive)
        );
    }

    // =========================================================
    // ADD SERVICE
    // =========================================================

    private void showAddServiceDialog() {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Add Service"
        );

        dialog.setHeaderText(
                "Create a new RoadGuardian service"
        );

        GridPane grid =
                createDialogGrid();

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Service name"
        );

        TextField descriptionField =
                new TextField();

        descriptionField.setPromptText(
                "Service description"
        );

        TextField priceField =
                new TextField();

        priceField.setPromptText(
                "Example: ₹500"
        );

        ComboBox<String> statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "Active",
                "Inactive"
        );

        statusBox.setValue(
                "Active"
        );

        addDialogRow(
                grid,
                "Service Name",
                nameField,
                0
        );

        addDialogRow(
                grid,
                "Description",
                descriptionField,
                1
        );

        addDialogRow(
                grid,
                "Price",
                priceField,
                2
        );

        addDialogRow(
                grid,
                "Status",
                statusBox,
                3
        );

        dialog.getDialogPane()
                .setContent(
                        grid
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        ButtonType.OK,
                        ButtonType.CANCEL
                );

        dialog.setResultConverter(
                button -> {

                    if (
                            button ==
                            ButtonType.OK
                    ) {

                        String name =
                                nameField
                                        .getText()
                                        .trim();

                        if (
                                name.isEmpty()
                        ) {

                            showAlert(
                                    "Validation",
                                    "Please enter service name."
                            );

                            return null;
                        }

                        String id =
                                createNextServiceId();

                        serviceList.add(
                                new ServiceData(
                                        id,
                                        name,
                                        descriptionField
                                                .getText()
                                                .trim(),
                                        priceField
                                                .getText()
                                                .trim(),
                                        statusBox
                                                .getValue()
                                )
                        );

                        updateStatistics();

                        applyFilters();
                    }

                    return button;
                }
        );

        dialog.showAndWait();
    }

    // =========================================================
    // EDIT SERVICE
    // =========================================================

    private void showEditServiceDialog(
            ServiceData service
    ) {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Edit Service"
        );

        dialog.setHeaderText(
                "Update service details"
        );

        GridPane grid =
                createDialogGrid();

        TextField nameField =
                new TextField(
                        service.getName()
                );

        TextField descriptionField =
                new TextField(
                        service.getDescription()
                );

        TextField priceField =
                new TextField(
                        service.getPrice()
                );

        ComboBox<String> statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "Active",
                "Inactive"
        );

        statusBox.setValue(
                service.getStatus()
        );

        addDialogRow(
                grid,
                "Service Name",
                nameField,
                0
        );

        addDialogRow(
                grid,
                "Description",
                descriptionField,
                1
        );

        addDialogRow(
                grid,
                "Price",
                priceField,
                2
        );

        addDialogRow(
                grid,
                "Status",
                statusBox,
                3
        );

        dialog.getDialogPane()
                .setContent(
                        grid
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        ButtonType.OK,
                        ButtonType.CANCEL
                );

        dialog.setResultConverter(
                button -> {

                    if (
                            button ==
                            ButtonType.OK
                    ) {

                        String name =
                                nameField
                                        .getText()
                                        .trim();

                        if (
                                name.isEmpty()
                        ) {

                            showAlert(
                                    "Validation",
                                    "Please enter service name."
                            );

                            return null;
                        }

                        service.setName(
                                name
                        );

                        service.setDescription(
                                descriptionField
                                        .getText()
                                        .trim()
                        );

                        service.setPrice(
                                priceField
                                        .getText()
                                        .trim()
                        );

                        service.setStatus(
                                statusBox
                                        .getValue()
                        );

                        updateStatistics();

                        applyFilters();
                    }

                    return button;
                }
        );

        dialog.showAndWait();
    }

    // =========================================================
    // DELETE SERVICE
    // =========================================================

    private void deleteService(
            ServiceData service
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        alert.setTitle(
                "Delete Service"
        );

        alert.setHeaderText(
                "Delete " +
                service.getName() +
                "?"
        );

        alert.setContentText(
                "Service ID: " +
                service.getId() +
                "\n\nThis action cannot be undone."
        );

        ButtonType deleteButton =
                new ButtonType(
                        "Delete",
                        ButtonBar.ButtonData.OK_DONE
                );

        ButtonType cancelButton =
                new ButtonType(
                        "Cancel",
                        ButtonBar.ButtonData.CANCEL_CLOSE
                );

        alert.getButtonTypes().setAll(
                deleteButton,
                cancelButton
        );

        alert.showAndWait()
                .ifPresent(
                        result -> {

                            if (
                                    result ==
                                    deleteButton
                            ) {

                                serviceList.remove(
                                        service
                                );

                                updateStatistics();

                                applyFilters();
                            }
                        }
                );
    }

    // =========================================================
    // DETAILS
    // =========================================================

    private void showServiceDetails(
            ServiceData service
    ) {

        Dialog<Void> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Service Details"
        );

        dialog.setHeaderText(
                service.getName()
        );

        ButtonType editButton =
                new ButtonType(
                        "Edit",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        editButton,
                        ButtonType.CLOSE
                );

        VBox content =
                new VBox(13);

        content.setPadding(
                new Insets(20)
        );

        content.setPrefWidth(
                430
        );

        Label heading =
                new Label(
                        "Service Information"
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

        content.getChildren().addAll(
                heading,
                new Separator(),
                createDetailRow(
                        "Service ID",
                        service.getId()
                ),
                createDetailRow(
                        "Service Name",
                        service.getName()
                ),
                createDetailRow(
                        "Description",
                        service.getDescription()
                ),
                createDetailRow(
                        "Price",
                        service.getPrice()
                ),
                createDetailRow(
                        "Status",
                        service.getStatus()
                )
        );

        dialog.getDialogPane()
                .setContent(
                        content
                );

        dialog.getDialogPane()
                .setStyle(
                        "-fx-background-color: " +
                        BG +
                        ";"
                );

        dialog.setResultConverter(
                button -> {

                    if (
                            button ==
                            editButton
                    ) {

                        showEditServiceDialog(
                                service
                        );
                    }

                    return null;
                }
        );

        dialog.showAndWait();
    }

    private HBox createDetailRow(
            String title,
            String value
    ) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.TOP_LEFT
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setMinWidth(
                100
        );

        titleLabel.setTextFill(
                Color.web(TEXT)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        Label valueLabel =
                new Label(
                        safe(value)
                );

        valueLabel.setTextFill(
                Color.web(HEADING)
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        valueLabel.setWrapText(
                true
        );

        HBox.setHgrow(
                valueLabel,
                Priority.ALWAYS
        );

        row.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return row;
    }

    // =========================================================
    // DIALOG HELPERS
    // =========================================================

    private GridPane createDialogGrid() {

        GridPane grid =
                new GridPane();

        grid.setHgap(12);
        grid.setVgap(14);

        grid.setPadding(
                new Insets(20)
        );

        return grid;
    }

    private void addDialogRow(
            GridPane grid,
            String labelText,
            Control control,
            int row
    ) {

        Label label =
                new Label(
                        labelText
                );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        label.setTextFill(
                Color.web(HEADING)
        );

        control.setPrefWidth(
                280
        );

        grid.add(
                label,
                0,
                row
        );

        grid.add(
                control,
                1,
                row
        );
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
    // HELPERS
    // =========================================================

    private String createNextServiceId() {

        int max = 0;

        for (
                ServiceData service :
                serviceList
        ) {

            String id =
                    service.getId();

            if (
                    id != null &&
                    id.startsWith("S")
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
                "S%03d",
                max + 1
        );
    }

    private String safe(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {

            return "-";
        }

        return value;
    }

    private void showAlert(
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

    // =========================================================
    // SERVICE DATA
    // =========================================================

    public static class ServiceData {

        private String id;
        private String name;
        private String description;
        private String price;
        private String status;

        public ServiceData(
                String id,
                String name,
                String description,
                String price,
                String status
        ) {

            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getPrice() {
            return price;
        }

        public String getStatus() {
            return status;
        }

        public void setName(
                String name
        ) {

            this.name = name;
        }

        public void setDescription(
                String description
        ) {

            this.description = description;
        }

        public void setPrice(
                String price
        ) {

            this.price = price;
        }

        public void setStatus(
                String status
        ) {

            this.status = status;
        }
    }
}
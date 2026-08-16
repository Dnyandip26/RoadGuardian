package project.ui.admin.ServiceRequests;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import com.google.cloud.firestore.Firestore;

import project.controller.admin.MechanicController;
import project.controller.admin.ServiceRequestController;
import project.firebase.FirebaseConfig;
import project.model.Mechanic;
import project.model.ServiceRequest;
import project.ui.admin.DashBoard.AdminSectionPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceRequestManagementPage extends AdminSectionPage {

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
    // CONTROLLERS
    // =========================================================

    private ServiceRequestController controller;
    private MechanicController mechanicController;

    // =========================================================
    // DATA
    // =========================================================

    private final ObservableList<ServiceRequest> requestList =
            FXCollections.observableArrayList();

    private final List<ServiceRequest> allRequests =
            new ArrayList<>();

    // =========================================================
    // UI
    // =========================================================

    private StackPane pageContainer;

    private VBox managementView;

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
    private Label completedLabel;

    // =========================================================
    // GET VIEW
    // =========================================================

    @Override
    public VBox getView() {

        pageContainer =
                new StackPane();

        pageContainer.setStyle(
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
                    new ServiceRequestController(
                            firestore
                    );

            mechanicController =
                    new MechanicController(
                            firestore
                    );

        } catch (Exception e) {

            e.printStackTrace();

            pageContainer.getChildren().setAll(
                    createErrorView(
                            "Unable to connect to Firestore."
                    )
            );

            VBox wrapper =
                    new VBox(
                            pageContainer
                    );

            wrapper.setStyle(
                    "-fx-background-color: " +
                    BG +
                    ";"
            );

            return wrapper;
        }

        // -----------------------------------------------------
        // CREATE MANAGEMENT VIEW
        // -----------------------------------------------------

        managementView =
                createManagementView();

        pageContainer.getChildren().setAll(
                managementView
        );

        // -----------------------------------------------------
        // LOAD DATA
        // -----------------------------------------------------

        loadRequests();

        VBox wrapper =
                new VBox(
                        pageContainer
                );

        wrapper.setFillWidth(
                true
        );

        wrapper.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        VBox.setVgrow(
                pageContainer,
                Priority.ALWAYS
        );

        return wrapper;
    }

    // =========================================================
    // MANAGEMENT VIEW
    // =========================================================

    private VBox createManagementView() {

        VBox root =
                new VBox(22);

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

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header =
                new VBox(7);

        Label title =
                new Label(
                        "Service Requests"
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
                        "Monitor roadside assistance requests, track progress, and manage mechanic assignments."
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

        pendingLabel =
                createValueLabel(ORANGE);

        acceptedLabel =
                createValueLabel(BLUE);

        progressLabel =
                createValueLabel(ORANGE);

        completedLabel =
                createValueLabel(GREEN);

        VBox total =
                createStatCard(
                        "Total Requests",
                        "All service requests",
                        totalLabel,
                        BLUE,
                        "TOTAL"
                );

        VBox pending =
                createStatCard(
                        "Pending",
                        "Waiting for action",
                        pendingLabel,
                        ORANGE,
                        "PENDING"
                );

        VBox accepted =
                createStatCard(
                        "Accepted",
                        "Mechanic assigned",
                        acceptedLabel,
                        BLUE,
                        "ACCEPTED"
                );

        VBox progress =
                createStatCard(
                        "In Progress",
                        "Service underway",
                        progressLabel,
                        ORANGE,
                        "ACTIVE"
                );

        VBox completed =
                createStatCard(
                        "Completed",
                        "Successfully finished",
                        completedLabel,
                        GREEN,
                        "DONE"
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
                accepted,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                progress,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                completed,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                total,
                pending,
                accepted,
                progress,
                completed
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
                "Search request, customer, vehicle, mechanic..."
        );

        searchField.setPrefWidth(
                390
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
                "Completed",
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
                "Status A-Z"
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

        searchField.textProperty().addListener(
                (obs, oldValue, newValue) ->
                        applyFilters()
        );

        statusFilter.valueProperty().addListener(
                (obs, oldValue, newValue) ->
                        applyFilters()
        );

        sortFilter.valueProperty().addListener(
                (obs, oldValue, newValue) ->
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
                        "Service Request Information"
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
                        "Roadside assistance requests and their current progress"
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
                        "0 requests"
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
    // REQUEST CARD
    // =========================================================

    private VBox createRequestCard(
            ServiceRequest request,
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
                "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.18), 12, 0.15, 0, 3);";

        card.setStyle(
                normalStyle
        );

        HBox top =
                new HBox(13);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox iconBox =
                createRequestIcon();

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

        Label requestId =
                new Label(
                        safe(
                                request.getRequestId()
                        )
                );

        requestId.setTextFill(
                Color.web(HEADING)
        );

        requestId.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        idLine.getChildren().addAll(
                serial,
                requestId
        );

        Label customer =
                new Label(
                        "Customer: " +
                        safe(
                                request.getCustomerName()
                        )
                );

        customer.setTextFill(
                Color.web(TEXT)
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

        HBox infoRow =
                new HBox(12);

        VBox vehicle =
                createInfoBox(
                        "VEHICLE",
                        request.getVehicleNumber()
                );

        VBox service =
                createInfoBox(
                        "SERVICE",
                        request.getServiceType()
                );

        VBox mechanic =
                createInfoBox(
                        "MECHANIC",
                        isAssigned(request)
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
                service,
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
                service,
                mechanic,
                location
        );

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
                                80
                        )
                );

        description.setTextFill(
                Color.web(TEXT)
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
                        "View Details →"
                );

        styleSmallButton(
                view,
                BLUE
        );

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

        card.setOnMouseClicked(
                event -> {

                    if (
                            event.getClickCount() == 2
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
    // REQUEST ICON
    // =========================================================

    private VBox createRequestIcon() {

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
                ORANGE +
                ";" +
                "-fx-background-radius: 12;"
        );

        Label icon =
                new Label("⚙");

        icon.setTextFill(
                Color.WHITE
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
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

    private String getStatusColor(
            String status
    ) {

        if (
                status.equalsIgnoreCase(
                        "Completed"
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
                                "-fx-background-color: #1D4ED8;" +
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

        List<ServiceRequest> filtered =
                new ArrayList<>();

        for (
                ServiceRequest request :
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
                    !"All".equals(status)
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

        requestList.setAll(
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
            ServiceRequest request,
            String search
    ) {

        if (
                search.isBlank()
        ) {
            return true;
        }

        return safe(
                request.getRequestId()
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
                        request.getMechanicName()
                )
                        .toLowerCase()
                        .contains(search)

                ||

                safe(
                        request.getServiceType()
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
                        request.getDescription()
                )
                        .toLowerCase()
                        .contains(search);
    }

    // =========================================================
    // SORT
    // =========================================================

    private void sortRequests(
            List<ServiceRequest> requests,
            String sort
    ) {

        if (
                "Customer A-Z".equals(sort)
        ) {

            requests.sort(
                    Comparator.comparing(
                            request ->
                                    safe(
                                            request.getCustomerName()
                                    ).toLowerCase()
                    )
            );

        } else if (
                "Customer Z-A".equals(sort)
        ) {

            requests.sort(
                    Comparator.comparing(
                            (ServiceRequest request) ->
                                    safe(
                                            request.getCustomerName()
                                    ).toLowerCase()
                    ).reversed()
            );

        } else if (
                "Status A-Z".equals(sort)
        ) {

            requests.sort(
                    Comparator.comparing(
                            request ->
                                    safe(
                                            request.getStatus()
                                    ).toLowerCase()
                    )
            );

        } else if (
                "Oldest First".equals(sort)
        ) {

            requests.sort(
                    Comparator.comparing(
                            request ->
                                    safe(
                                            request.getRequestId()
                                    )
                    )
            );

        } else {

            requests.sort(
                    Comparator.comparing(
                            (ServiceRequest request) ->
                                    safe(
                                            request.getRequestId()
                                    )
                    ).reversed()
            );
        }
    }

    // =========================================================
    // LOAD REQUESTS
    // =========================================================

    private void loadRequests() {

        try {

            List<ServiceRequest> requests =
                    controller.getAllRequests();

            if (
                    requests == null
            ) {

                requests =
                        new ArrayList<>();
            }

            // -------------------------------------------------
            // SAMPLE RECORD
            // -------------------------------------------------

            if (
                    requests.isEmpty()
            ) {

                ServiceRequest sample =
                        new ServiceRequest();

                sample.setRequestId(
                        "SR-001"
                );

                sample.setCustomerName(
                        "Rahul Patil"
                );

                sample.setVehicleNumber(
                        "MH12AB1234"
                );

                sample.setServiceType(
                        "Engine Breakdown"
                );

                sample.setMechanicName(
                        "Amit Mechanic"
                );

                sample.setLocation(
                        "Pune"
                );

                sample.setDescription(
                        "Vehicle stopped suddenly and needs roadside assistance."
                );

                sample.setStatus(
                        "Pending"
                );

                requests.add(
                        sample
                );
            }

            allRequests.clear();

            allRequests.addAll(
                    requests
            );

            updateStatistics(
                    allRequests
            );

            applyFilters();

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Unable to Load Requests",
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // RENDER
    // =========================================================

    private void renderRequests(
            List<ServiceRequest> requests
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
                ServiceRequest request :
                requests
        ) {

            requestCards
                    .getChildren()
                    .add(
                            createRequestCard(
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
            List<ServiceRequest> requests
    ) {

        int pending = 0;
        int accepted = 0;
        int progress = 0;
        int completed = 0;

        for (
                ServiceRequest request :
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
                            "Completed"
                    )
            ) {

                completed++;
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

        completedLabel.setText(
                String.valueOf(
                        completed
                )
        );
    }

    // =========================================================
    // DETAILS NAVIGATION
    // =========================================================

    private void showDetails(ServiceRequest request) {

        // SAME WINDOW NAVIGATION:
        // Do NOT create a new Stage or Dialog here.
        ServiceRequestDetailsPage page =
                new ServiceRequestDetailsPage(
                        request,
                        this::showManagementPage,
                        () -> showAssignMechanicPage(request),
                        () -> showUpdateStatus(request)
                );

        VBox root = new VBox();
        root.setFillWidth(true);
        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        Button backButton =
                new Button("← Back to Service Requests");

        styleBackButton(backButton);

        backButton.setOnAction(
                event -> showManagementPage()
        );

        HBox topBar =
                new HBox(backButton);

        topBar.setPadding(
                new Insets(18, 28, 0, 28)
        );

        VBox detailView =
                page.getView();

        VBox.setVgrow(
                detailView,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                topBar,
                detailView
        );

        // Replace the current content in the same Admin window.
        pageContainer.getChildren().setAll(root);
    }

    private HBox createDetailRow(
        String title,
        String value
) {

    Label titleLabel =
            new Label(title);

    titleLabel.setPrefWidth(105);

    titleLabel.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    14
            )
    );

    titleLabel.setTextFill(
            Color.web(TEXT)
    );

    Label valueLabel =
            new Label(
                    safe(value)
            );

    valueLabel.setFont(
            Font.font(
                    "Arial",
                    14
            )
    );

    valueLabel.setTextFill(
            Color.web(HEADING)
    );

    valueLabel.setWrapText(true);

    HBox row =
            new HBox(10);

    row.setAlignment(
            Pos.TOP_LEFT
    );

    row.getChildren().addAll(
            titleLabel,
            valueLabel
    );

    return row;
}

    // =========================================================
    // DETAIL NAVIGATION WRAPPER
    // =========================================================

    private VBox createDetailNavigationView(
            ServiceRequestDetailsPage page,
            ServiceRequest request
    ) {

        VBox root =
                new VBox();

        root.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        Button backButton =
                new Button(
                        "← Back to Service Requests"
                );

        styleBackButton(
                backButton
        );

        backButton.setOnAction(
                event ->
                        showManagementPage()
        );

        HBox topBar =
                new HBox(
                        backButton
                );

        topBar.setPadding(
                new Insets(
                        18,
                        28,
                        0,
                        28
                )
        );

        VBox detailView =
                page.getView();

        VBox.setVgrow(
                detailView,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                topBar,
                detailView
        );

        return root;
    }

    // =========================================================
    // BACK TO MANAGEMENT
    // =========================================================

    private void showManagementPage() {

        loadRequests();

        pageContainer
                .getChildren()
                .setAll(
                        managementView
                );
    }

    // =========================================================
    // ASSIGN MECHANIC NAVIGATION - SAME WINDOW
    // =========================================================

    private void showAssignMechanicPage(ServiceRequest request) {

        AssignMechanicPage page =
                new AssignMechanicPage(
                        request,
                        () -> showDetails(request)
                );

        VBox root = new VBox();
        root.setStyle("-fx-background-color: " + BG + ";");

        Button back = new Button("← Back to Request Details");
        styleBackButton(back);
        back.setOnAction(event -> showDetails(request));

        HBox top = new HBox(back);
        top.setPadding(new Insets(18, 28, 0, 28));

        VBox view = page.getView();
        VBox.setVgrow(view, Priority.ALWAYS);

        root.getChildren().addAll(top, view);
        pageContainer.getChildren().setAll(root);
    }

    // Keep this public method for existing callers.
    public void showAssignMechanic(ServiceRequest request) {
        showAssignMechanicPage(request);
    }

    // =========================================================
    // DIRECT UPDATE STATUS NAVIGATION
    // =========================================================

    public void showUpdateStatus(
            ServiceRequest request
    ) {

        UpdateRequestStatusPage page =
                new UpdateRequestStatusPage(
                        request,
                        () -> showDetails(request)
                );

        VBox root =
                new VBox();

        root.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        Button back =
                new Button(
                        "← Back to Request Details"
                );

        styleBackButton(
                back
        );

        back.setOnAction(
                event ->
                        showDetails(
                                request
                        )
        );

        HBox top =
                new HBox(
                        back
                );

        top.setPadding(
                new Insets(
                        18,
                        28,
                        0,
                        28
                )
        );

        VBox view =
                page.getView();

        root.getChildren().addAll(
                top,
                view
        );

        pageContainer
                .getChildren()
                .setAll(
                        root
                );
    }
    // =========================================================
    // BACK BUTTON STYLE
    // =========================================================

    private void styleBackButton(
            Button button
    ) {

        button.setTextFill(
                Color.web(HEADING)
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
                        9,
                        15,
                        9,
                        15
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                SECONDARY +
                                ";" +
                                "-fx-border-color: " +
                                BLUE +
                                ";" +
                                "-fx-border-radius: 8;" +
                                "-fx-background-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                SURFACE +
                                ";" +
                                "-fx-border-color: " +
                                BORDER +
                                ";" +
                                "-fx-border-radius: 8;" +
                                "-fx-background-radius: 8;"
                        )
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
                new Label("⚙");

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
                        "No service requests found"
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
                        "Customer roadside assistance requests will appear here."
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
    // CHECK MECHANIC
    // =========================================================

    private boolean isAssigned(
            ServiceRequest request
    ) {

        String mechanic =
                safe(
                        request.getMechanicName()
                );

        return !mechanic.equals("-");
    }

    // =========================================================
    // SHORTEN
    // =========================================================

    private String shorten(
            String value,
            int max
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {

            return "No description provided.";
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
    // SAFE
    // =========================================================

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

    // =========================================================
    // INFO ALERT
    // =========================================================

    private void showInfo(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
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
                        "Service Request Error"
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
}
package project.ui.mechanic;

import com.google.cloud.firestore.Firestore;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import project.controller.mechanic.RequestsController;
import project.firebase.FirebaseConfig;
import project.model.ServiceRequest;
import project.util.Theme;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestsPage {

    // =====================================================
    // UI COMPONENTS
    // =====================================================

    private VBox requestsContainer;
    private TextField searchField;
    private VBox pageContainer;

    // =====================================================
    // CONTROLLERS / PAGES
    // =====================================================

    private final RequestsController controller;
    private final ActiveJobPage activeJobPage;

    private final Runnable openActiveJob;
    private final Runnable openDashboard;

    // =====================================================
    // REQUEST CACHE
    // =====================================================

    private volatile List<ServiceRequest> cachedRequests =
            new ArrayList<>();

    private volatile boolean requestsLoaded = false;
    private volatile boolean loadingRequests = false;

    // =====================================================
    // LOGGED-IN MECHANIC
    // =====================================================

    private final String mechanicId = "mechanic001";
    private final String mechanicName = "Rajesh Patil";

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public RequestsPage(
            ActiveJobPage activeJobPage,
            Runnable openActiveJob,
            Runnable openDashboard
    ) {

        this.activeJobPage = activeJobPage;
        this.openActiveJob = openActiveJob;
        this.openDashboard = openDashboard;

        try {

            Firestore firestore =
                    FirebaseConfig.getFirestore();

            this.controller =
                    new RequestsController(
                            firestore
                    );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to initialize Firebase",
                    e
            );
        }
    }

    // =====================================================
    // GET CONTENT
    // =====================================================

    public ScrollPane getContent() {

        return createContent();
    }

    // =====================================================
    // CREATE PAGE CONTENT
    // =====================================================

    private ScrollPane createContent() {

        pageContainer =
                new VBox(20);

        pageContainer.setPadding(
                new Insets(
                        30,
                        35,
                        35,
                        35
                )
        );

        pageContainer.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND +
                ";"
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        pageContainer
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        buildRequestsPage(
                cachedRequests
        );

        if (!requestsLoaded) {

            preloadRequests();
        }

        return scrollPane;
    }

    // =====================================================
    // PRELOAD REQUESTS
    // =====================================================

    public void preloadRequests() {

        if (
                requestsLoaded ||
                loadingRequests
        ) {

            return;
        }

        loadingRequests = true;

        Task<List<ServiceRequest>> task =
                new Task<>() {

                    @Override
                    protected List<ServiceRequest> call() {

                        return controller
                                .getPendingRequests();
                    }
                };

        task.setOnSucceeded(
                e -> {

                    List<ServiceRequest> result =
                            task.getValue();

                    if (result == null) {

                        result =
                                Collections.emptyList();
                    }

                    cachedRequests =
                            new ArrayList<>(
                                    result
                            );

                    requestsLoaded = true;
                    loadingRequests = false;

                    if (pageContainer != null) {

                        buildRequestsPage(
                                cachedRequests
                        );
                    }
                }
        );

        task.setOnFailed(
                e -> {

                    if (
                            task.getException() != null
                    ) {

                        task.getException()
                                .printStackTrace();
                    }

                    cachedRequests =
                            new ArrayList<>();

                    requestsLoaded = true;
                    loadingRequests = false;

                    if (pageContainer != null) {

                        buildRequestsPage(
                                cachedRequests
                        );
                    }
                }
        );

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.setName(
                "LoadServiceRequests"
        );

        thread.start();
    }

    // =====================================================
    // BUILD REQUEST PAGE
    // =====================================================

    private void buildRequestsPage(
            List<ServiceRequest> pendingRequests
    ) {

        if (
                !Platform.isFxApplicationThread()
        ) {

            Platform.runLater(
                    () -> buildRequestsPage(
                            pendingRequests
                    )
            );

            return;
        }

        if (pageContainer == null) {

            return;
        }

        pageContainer
                .getChildren()
                .clear();

        // =================================================
        // HEADER
        // =================================================

        VBox header =
                new VBox(5);

        Label title =
                new Label(
                        "Service Requests"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label subtitle =
                new Label(
                        "Review and manage roadside assistance requests."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        // =================================================
        // BACK BUTTON
        // =================================================

        Button backButton =
                createBackButton(
                        "← Back to Dashboard",
                        openDashboard
                );

        // =================================================
        // STATISTICS
        // =================================================

        long emergencyCount =
                pendingRequests
                        .stream()
                        .filter(
                                request ->
                                        "Emergency"
                                                .equalsIgnoreCase(
                                                        safe(
                                                                request.getServiceType()
                                                        )
                                                )
                        )
                        .count();

        long normalCount =
                pendingRequests
                        .stream()
                        .filter(
                                request ->
                                        "Normal"
                                                .equalsIgnoreCase(
                                                        safe(
                                                                request.getServiceType()
                                                        )
                                                )
                        )
                        .count();

        HBox stats =
                new HBox(18);

        stats.getChildren().addAll(

                createStatCard(
                        "Total Requests",
                        String.valueOf(
                                pendingRequests.size()
                        ),
                        Theme.INFO
                ),

                createStatCard(
                        "Emergency",
                        String.valueOf(
                                emergencyCount
                        ),
                        Theme.ERROR
                ),

                createStatCard(
                        "Normal",
                        String.valueOf(
                                normalCount
                        ),
                        Theme.SUCCESS
                )
        );

        // =================================================
        // FILTERS
        // =================================================

        HBox filters =
                createFilters();

        // =================================================
        // REQUEST CARD
        // =================================================

        VBox requestsCard =
                createRequestsCard(
                        pendingRequests
                );

        pageContainer
                .getChildren()
                .addAll(
                        backButton,
                        header,
                        stats,
                        filters,
                        requestsCard
                );
    }

    // =====================================================
    // BACK BUTTON
    // =====================================================

    private Button createBackButton(
            String text,
            Runnable action
    ) {

        Button button =
                new Button(text);

        button.setPadding(
                new Insets(
                        10,
                        18,
                        10,
                        18
                )
        );

        button.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        button.setOnAction(
                e -> {

                    if (action != null) {

                        action.run();
                    }
                }
        );

        return button;
    }

    // =====================================================
    // STAT CARD
    // =====================================================

    private VBox createStatCard(
            String title,
            String value,
            String accent
    ) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(18)
        );

        card.setPrefHeight(
                110
        );

        card.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );

        HBox row =
                new HBox();

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Circle circle =
                new Circle(18);

        circle.setFill(
                Color.web(accent)
        );

        row.getChildren().addAll(
                titleLabel,
                spacer,
                circle
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        card.getChildren().addAll(
                row,
                valueLabel
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        return card;
    }

    // =====================================================
    // FILTERS
    // =====================================================

    private HBox createFilters() {

        HBox filters =
                new HBox(12);

        filters.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField =
                new TextField();

        searchField.setPromptText(
                "Search customer, vehicle or problem..."
        );

        searchField.setPrefWidth(
                430
        );

        searchField.setPadding(
                new Insets(12)
        );

        searchField.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        searchField
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                filterRequests(
                                        newValue
                                )
                );

        Button allButton =
                createFilterButton(
                        "All",
                        true
                );

        Button emergencyButton =
                createFilterButton(
                        "Emergency",
                        false
                );

        Button normalButton =
                createFilterButton(
                        "Normal",
                        false
                );

        allButton.setOnAction(
                e -> {

                    searchField.clear();

                    showAllRequests();
                }
        );

        emergencyButton.setOnAction(
                e -> {

                    searchField.clear();

                    filterByType(
                            "Emergency"
                    );
                }
        );

        normalButton.setOnAction(
                e -> {

                    searchField.clear();

                    filterByType(
                            "Normal"
                    );
                }
        );

        filters.getChildren().addAll(
                searchField,
                allButton,
                emergencyButton,
                normalButton
        );

        return filters;
    }

    // =====================================================
    // FILTER BUTTON
    // =====================================================

    private Button createFilterButton(
            String text,
            boolean active
    ) {

        Button button =
                new Button(text);

        button.setPadding(
                new Insets(
                        11,
                        18,
                        11,
                        18
                )
        );

        if (active) {

            button.setStyle(
                    "-fx-background-color: " +
                    Theme.TEXT +
                    ";" +
                    "-fx-text-fill: " +
                    Theme.WHITE +
                    ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: " +
                    Theme.CARD +
                    ";" +
                    "-fx-text-fill: " +
                    Theme.TEXT +
                    ";" +
                    "-fx-border-color: " +
                    Theme.BORDER +
                    ";" +
                    "-fx-border-radius: 9;" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 11px;" +
                    "-fx-cursor: hand;"
            );
        }

        return button;
    }

    // =====================================================
    // REQUESTS CARD
    // =====================================================

    private VBox createRequestsCard(
            List<ServiceRequest> requests
    ) {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(22)
        );

        card.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );

        // =================================================
        // HEADING
        // =================================================

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label(
                        "Incoming Service Requests"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label live =
                new Label(
                        "● LIVE"
                );

        live.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SUCCESS +
                ";"
        );

        heading.getChildren().addAll(
                title,
                spacer,
                live
        );

        // =================================================
        // REQUEST CONTAINER
        // =================================================

        requestsContainer =
                new VBox(10);

        for (
                ServiceRequest request :
                requests
        ) {

            String customer =
                    safe(
                            request.getCustomerName()
                    );

            String vehicle =
                    safe(
                            request.getVehicleNumber()
                    );

            String problem =
                    safe(
                            request.getDescription()
                    );

            String type =
                    safe(
                            request.getServiceType()
                    );

            String location =
                    safe(
                            request.getLocation()
                    );

            String time =
                    formatRequestDate(
                            request.getRequestDate()
                    );

            String amount =
                    formatAmount(
                            request.getTotalAmount()
                    );

            HBox row =
                    createRequest(
                            request,
                            customer,
                            vehicle,
                            problem,
                            type,
                            location,
                            amount,
                            time
                    );

            requestsContainer
                    .getChildren()
                    .add(
                            row
                    );
        }

        // =================================================
        // EMPTY MESSAGE
        // =================================================

        if (requests.isEmpty()) {

            Label emptyLabel =
                    new Label(
                            "No pending service requests found."
                    );

            emptyLabel.setStyle(
                    "-fx-font-size: 13px;" +
                    "-fx-text-fill: " +
                    Theme.SECONDARY_TEXT +
                    ";"
            );

            VBox emptyBox =
                    new VBox(
                            emptyLabel
                    );

            emptyBox.setAlignment(
                    Pos.CENTER
            );

            emptyBox.setPadding(
                    new Insets(30)
            );

            requestsContainer
                    .getChildren()
                    .add(
                            emptyBox
                    );
        }

        card.getChildren().addAll(
                heading,
                requestsContainer
        );

        return card;
    }

    // =====================================================
    // CREATE SINGLE REQUEST
    // =====================================================

    private HBox createRequest(
            ServiceRequest request,
            String customer,
            String vehicle,
            String problem,
            String type,
            String location,
            String amount,
            String time
    ) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(14)
        );

        row.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-background-radius: 12;"
        );

        // =================================================
        // SEARCH DATA
        // =================================================

        row.setUserData(
                (
                        customer +
                        " " +
                        vehicle +
                        " " +
                        problem +
                        " " +
                        type +
                        " " +
                        location
                ).toLowerCase()
        );

        // =================================================
        // VEHICLE IMAGE
        // =================================================

        ImageView vehicleImageView =
                createVehicleImage();

        // =================================================
        // CUSTOMER DETAILS
        // =================================================

        VBox customerBox =
                new VBox(5);

        HBox nameRow =
                new HBox(8);

        nameRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label customerLabel =
                new Label(customer);

        customerLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label typeLabel =
                new Label(type);

        if (
                "Emergency"
                        .equalsIgnoreCase(type)
        ) {

            typeLabel.setStyle(
                    "-fx-background-color: " +
                    Theme.ERROR_BG +
                    ";" +
                    "-fx-text-fill: " +
                    Theme.ERROR +
                    ";" +
                    "-fx-background-radius: 10;" +
                    "-fx-padding: 4 8 4 8;" +
                    "-fx-font-size: 8px;" +
                    "-fx-font-weight: bold;"
            );

        } else {

            typeLabel.setStyle(
                    "-fx-background-color: " +
                    Theme.SUCCESS_BG +
                    ";" +
                    "-fx-text-fill: " +
                    Theme.SUCCESS +
                    ";" +
                    "-fx-background-radius: 10;" +
                    "-fx-padding: 4 8 4 8;" +
                    "-fx-font-size: 8px;" +
                    "-fx-font-weight: bold;"
            );
        }

        nameRow.getChildren().addAll(
                customerLabel,
                typeLabel
        );

        Label problemLabel =
                new Label(
                        vehicle +
                        " • " +
                        problem
                );

        problemLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        customerBox.getChildren().addAll(
                nameRow,
                problemLabel
        );

        // =================================================
        // SPACER
        // =================================================

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // =================================================
        // LOCATION
        // =================================================

        VBox locationBox =
                new VBox(3);

        locationBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label locationLabel =
                new Label(location);

        locationLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label timeLabel =
                new Label(time);

        timeLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        locationBox.getChildren().addAll(
                locationLabel,
                timeLabel
        );

        // =================================================
        // AMOUNT
        // =================================================

        VBox amountBox =
                new VBox(2);

        amountBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label amountLabel =
                new Label(amount);

        amountLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label estimated =
                new Label(
                        "Estimated"
                );

        estimated.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        amountBox.getChildren().addAll(
                amountLabel,
                estimated
        );

        // =================================================
        // VIEW BUTTON
        // =================================================

        Button viewButton =
                new Button(
                        "View"
                );

        viewButton.setPadding(
                new Insets(
                        9,
                        14,
                        9,
                        14
                )
        );

        viewButton.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        viewButton.setOnAction(
                e -> showRequestDetails(
                        request
                )
        );

        // =================================================
        // ACCEPT BUTTON
        // =================================================

        Button acceptButton =
                new Button(
                        "Accept"
                );

        acceptButton.setPadding(
                new Insets(
                        9,
                        16,
                        9,
                        16
                )
        );

        acceptButton.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        acceptButton.setOnAction(
                e -> acceptRequest(
                        request,
                        row
                )
        );

        // =================================================
        // FINAL REQUEST ROW
        // =================================================

        row.getChildren().addAll(
                vehicleImageView,
                customerBox,
                spacer,
                locationBox,
                amountBox,
                viewButton,
                acceptButton
        );

        return row;
    }

    // =====================================================
    // CREATE VEHICLE IMAGE
    // =====================================================

    private ImageView createVehicleImage() {

        ImageView imageView =
                new ImageView();

        try {

            var imageStream =
                    getClass()
                            .getResourceAsStream(
                                    "/images/default_vehicle.png"
                            );

            if (imageStream != null) {

                Image image =
                        new Image(
                                imageStream
                        );

                imageView.setImage(
                        image
                );

                imageView.setFitWidth(
                        80
                );

                imageView.setFitHeight(
                        50
                );

                imageView.setPreserveRatio(
                        true
                );

                imageView.setSmooth(
                        true
                );

            } else {

                System.out.println(
                        "WARNING: default_vehicle.png not found!"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return imageView;
    }

    // =====================================================
    // ACCEPT REQUEST
    // =====================================================

    private void acceptRequest(
            ServiceRequest request,
            HBox row
    ) {

        String requestId =
                request.getRequestId();

        Task<Boolean> task =
                new Task<>() {

                    @Override
                    protected Boolean call() {

                        return controller.acceptRequest(
                                requestId,
                                mechanicId,
                                mechanicName
                        );
                    }
                };

        task.setOnSucceeded(
                e -> {

                    boolean accepted =
                            task.getValue();

                    if (!accepted) {

                        return;
                    }

                    cachedRequests.remove(
                            request
                    );

                    if (
                            requestsContainer != null
                    ) {

                        requestsContainer
                                .getChildren()
                                .remove(row);
                    }

                    activeJobPage.setJob(
                            request
                    );

                    if (
                            openActiveJob != null
                    ) {

                        openActiveJob.run();
                    }
                }
        );

        task.setOnFailed(
                e -> {

                    if (
                            task.getException() != null
                    ) {

                        task.getException()
                                .printStackTrace();
                    }
                }
        );

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.setName(
                "AcceptRequest"
        );

        thread.start();
    }

    // =====================================================
    // REQUEST DETAILS
    // =====================================================

    private void showRequestDetails(
            ServiceRequest request
    ) {

        pageContainer
                .getChildren()
                .clear();

        VBox detailsPage =
                new VBox(18);

        detailsPage.setPadding(
                new Insets(35)
        );

        // =================================================
        // BACK
        // =================================================

        Button backButton =
                createBackButton(
                        "← Back to Requests",
                        () -> buildRequestsPage(
                                cachedRequests
                        )
                );

        // =================================================
        // TITLE
        // =================================================

        Label title =
                new Label(
                        "Request Details"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label subtitle =
                new Label(
                        "Review the request before accepting it."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        // =================================================
        // DETAILS CARD
        // =================================================

        VBox card =
                new VBox(14);

        card.setPadding(
                new Insets(25)
        );

        card.setMaxWidth(
                650
        );

        card.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );

        card.getChildren().addAll(

                createDetailLabel(
                        "CUSTOMER",
                        request.getCustomerName()
                ),

                createDetailLabel(
                        "VEHICLE",
                        request.getVehicleNumber()
                ),

                createDetailLabel(
                        "PROBLEM",
                        request.getDescription()
                ),

                createDetailLabel(
                        "REQUEST TYPE",
                        request.getServiceType()
                ),

                createDetailLabel(
                        "LOCATION",
                        request.getLocation()
                ),

                createDetailLabel(
                        "ESTIMATED AMOUNT",
                        formatAmount(
                                request.getTotalAmount()
                        )
                ),

                createDetailLabel(
                        "CUSTOMER MOBILE",
                        request.getCustomerMobile()
                ),

                createDetailLabel(
                        "REQUESTED",
                        formatRequestDate(
                                request.getRequestDate()
                        )
                )
        );

        // =================================================
        // BUTTONS
        // =================================================

        HBox buttons =
                new HBox(10);

        Button backToRequests =
                createBackButton(
                        "Back",
                        () -> buildRequestsPage(
                                cachedRequests
                        )
                );

        Button acceptButton =
                new Button(
                        "Accept Request"
                );

        acceptButton.setPadding(
                new Insets(
                        12,
                        25,
                        12,
                        25
                )
        );

        acceptButton.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        acceptButton.setOnAction(
                e -> acceptDetailRequest(
                        request,
                        acceptButton
                )
        );

        buttons.getChildren().addAll(
                backToRequests,
                acceptButton
        );

        detailsPage.getChildren().addAll(
                backButton,
                title,
                subtitle,
                card,
                buttons
        );

        pageContainer
                .getChildren()
                .add(
                        detailsPage
                );
    }

    // =====================================================
    // ACCEPT DETAIL REQUEST
    // =====================================================

    private void acceptDetailRequest(
            ServiceRequest request,
            Button acceptButton
    ) {

        acceptButton.setDisable(
                true
        );

        acceptButton.setText(
                "Accepting..."
        );

        Task<Boolean> task =
                new Task<>() {

                    @Override
                    protected Boolean call() {

                        return controller.acceptRequest(
                                request.getRequestId(),
                                mechanicId,
                                mechanicName
                        );
                    }
                };

        task.setOnSucceeded(
                e -> {

                    boolean accepted =
                            task.getValue();

                    if (!accepted) {

                        acceptButton.setDisable(
                                false
                        );

                        acceptButton.setText(
                                "Accept Request"
                        );

                        return;
                    }

                    cachedRequests.remove(
                            request
                    );

                    activeJobPage.setJob(
                            request
                    );

                    if (
                            openActiveJob != null
                    ) {

                        openActiveJob.run();
                    }
                }
        );

        task.setOnFailed(
                e -> {

                    acceptButton.setDisable(
                            false
                    );

                    acceptButton.setText(
                            "Accept Request"
                    );

                    if (
                            task.getException() != null
                    ) {

                        task.getException()
                                .printStackTrace();
                    }
                }
        );

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);

        thread.setName(
                "AcceptDetailRequest"
        );

        thread.start();
    }

    // =====================================================
    // DETAIL LABEL
    // =====================================================

    private Label createDetailLabel(
            String title,
            String value
    ) {

        Label label =
                new Label(
                        title +
                        "\n" +
                        safe(value)
                );

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        label.setWrapText(
                true
        );

        return label;
    }

    // =====================================================
    // SEARCH FILTER
    // =====================================================

    private void filterRequests(
            String search
    ) {

        if (
                requestsContainer == null
        ) {

            return;
        }

        String keyword =
                search == null
                        ? ""
                        : search
                                .trim()
                                .toLowerCase();

        for (
                javafx.scene.Node node :
                requestsContainer.getChildren()
        ) {

            if (
                    !(node instanceof HBox)
            ) {

                continue;
            }

            HBox row =
                    (HBox) node;

            String rowData =
                    String.valueOf(
                            row.getUserData()
                    ).toLowerCase();

            boolean matches =
                    keyword.isEmpty()
                    ||
                    rowData.contains(
                            keyword
                    );

            row.setVisible(
                    matches
            );

            row.setManaged(
                    matches
            );
        }
    }

    // =====================================================
    // FILTER BY TYPE
    // =====================================================

    private void filterByType(
            String type
    ) {

        if (
                requestsContainer == null
        ) {

            return;
        }

        String filter =
                type.toLowerCase();

        for (
                javafx.scene.Node node :
                requestsContainer.getChildren()
        ) {

            if (
                    !(node instanceof HBox)
            ) {

                continue;
            }

            HBox row =
                    (HBox) node;

            String rowData =
                    String.valueOf(
                            row.getUserData()
                    ).toLowerCase();

            boolean matches =
                    rowData.contains(
                            filter
                    );

            row.setVisible(
                    matches
            );

            row.setManaged(
                    matches
            );
        }
    }

    // =====================================================
    // SHOW ALL
    // =====================================================

    private void showAllRequests() {

        if (
                requestsContainer == null
        ) {

            return;
        }

        for (
                javafx.scene.Node node :
                requestsContainer.getChildren()
        ) {

            node.setVisible(
                    true
            );

            node.setManaged(
                    true
            );
        }
    }

    // =====================================================
    // SAFE STRING
    // =====================================================

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

    // =====================================================
    // FORMAT AMOUNT
    // =====================================================

    private String formatAmount(
            double amount
    ) {

        return "₹" +
                String.format(
                        "%.0f",
                        amount
                );
    }

    // =====================================================
    // FORMAT DATE
    // =====================================================

    private String formatRequestDate(
            String requestDate
    ) {

        if (
                requestDate == null ||
                requestDate.isBlank()
        ) {

            return "-";
        }

        try {

            long timestamp =
                    Long.parseLong(
                            requestDate
                    );

            LocalDateTime dateTime =
                    LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(
                                    timestamp
                            ),
                            ZoneId.systemDefault()
                    );

            return dateTime.format(
                    DateTimeFormatter.ofPattern(
                            "dd MMM yyyy, hh:mm a"
                    )
            );

        } catch (Exception e) {

            return requestDate;
        }
    }
}
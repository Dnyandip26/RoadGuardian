package project.ui.mechanic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import project.util.Theme;

public class RequestsPage {

    private VBox requestsContainer;
    private TextField searchField;
    private VBox pageContainer;

    private final ActiveJobPage activeJobPage;
    private final Runnable openActiveJob;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public RequestsPage(
            ActiveJobPage activeJobPage,
            Runnable openActiveJob
    ) {
        this.activeJobPage = activeJobPage;
        this.openActiveJob = openActiveJob;
    }

    // =========================================================
    // GET CONTENT
    // =========================================================

    public ScrollPane getContent() {
        return createContent();
    }

    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private ScrollPane createContent() {

        pageContainer = new VBox(20);

        pageContainer.setPadding(
                new Insets(30, 35, 35, 35)
        );

        pageContainer.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND +
                ";"
        );

        buildRequestsPage();

        ScrollPane scrollPane =
                new ScrollPane(pageContainer);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        return scrollPane;
    }

    // =========================================================
    // REQUESTS PAGE
    // =========================================================

    private void buildRequestsPage() {

        pageContainer.getChildren().clear();

        VBox header = new VBox(5);

        Label title =
                new Label("Service Requests");

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

        HBox stats = new HBox(18);

        stats.getChildren().addAll(
                createStatCard(
                        "Total Requests",
                        "05",
                        Theme.INFO
                ),
                createStatCard(
                        "Emergency",
                        "02",
                        Theme.ERROR
                ),
                createStatCard(
                        "Normal",
                        "03",
                        Theme.SUCCESS
                )
        );

        HBox filters = createFilters();

        VBox requestsCard = createRequestsCard();

        pageContainer.getChildren().addAll(
                header,
                stats,
                filters,
                requestsCard
        );
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String title,
            String value,
            String accent
    ) {

        VBox card = new VBox(8);

        card.setPadding(new Insets(18));
        card.setPrefHeight(110);

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

        HBox row = new HBox();

        row.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Circle circle = new Circle(18);

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

    // =========================================================
    // FILTERS
    // =========================================================

    private HBox createFilters() {

        HBox filters = new HBox(12);

        filters.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField = new TextField();

        searchField.setPromptText(
                "Search customer, vehicle or problem..."
        );

        searchField.setPrefWidth(430);

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

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        filterRequests(newValue)
        );

        Button allButton =
                createFilterButton("All", true);

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
                    filterByType("Emergency");
                }
        );

        normalButton.setOnAction(
                e -> {
                    searchField.clear();
                    filterByType("Normal");
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

    // =========================================================
    // FILTER BUTTON
    // =========================================================

    private Button createFilterButton(
            String text,
            boolean active
    ) {

        Button button = new Button(text);

        button.setPadding(
                new Insets(11, 18, 11, 18)
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

    // =========================================================
    // REQUEST CARD
    // =========================================================

    private VBox createRequestsCard() {

        VBox card = new VBox(12);

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

        HBox heading = new HBox();

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

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label live =
                new Label("● LIVE");

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

        requestsContainer = new VBox(10);

        requestsContainer.getChildren().addAll(

                createRequest(
                        "Rohit Najan",
                        "Maruti Swift",
                        "Battery Issue",
                        "Emergency",
                        "2.4 km",
                        "₹450 - ₹700",
                        "5 min ago"
                ),

                createRequest(
                        "Dnyandip Vadane",
                        "Hyundai i20",
                        "Flat Tyre",
                        "Normal",
                        "4.1 km",
                        "₹300 - ₹500",
                        "12 min ago"
                ),

                createRequest(
                        "Akash Patil",
                        "Honda City",
                        "Engine Problem",
                        "Emergency",
                        "6.8 km",
                        "₹800 - ₹1,500",
                        "18 min ago"
                ),

                createRequest(
                        "Vishal Vadane",
                        "Tata Nexon",
                        "Car Not Starting",
                        "Normal",
                        "3.6 km",
                        "₹500 - ₹900",
                        "25 min ago"
                )
        );

        card.getChildren().addAll(
                heading,
                requestsContainer
        );

        return card;
    }

    // =========================================================
    // REQUEST ROW
    // =========================================================

    private HBox createRequest(
            String customer,
            String vehicle,
            String problem,
            String type,
            String distance,
            String amount,
            String time
    ) {

        HBox row = new HBox(12);

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

        row.setUserData(
                (
                        customer +
                        " " +
                        vehicle +
                        " " +
                        problem +
                        " " +
                        type
                ).toLowerCase()
        );

        Circle avatar = new Circle(29);

        if (type.equals("Emergency")) {

            avatar.setFill(
                    Color.web("#FEE2E2")
            );

        } else {

            avatar.setFill(
                    Color.web("#FFF7ED")
            );
        }

        VBox customerBox = new VBox(5);

        HBox nameRow = new HBox(8);

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

        if (type.equals("Emergency")) {

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
                        "  •  " +
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

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        VBox distanceBox = new VBox(3);

        distanceBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label distanceLabel =
                new Label(distance);

        distanceLabel.setStyle(
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

        distanceBox.getChildren().addAll(
                distanceLabel,
                timeLabel
        );

        VBox amountBox = new VBox(2);

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
                new Label("Estimated");

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

        Button viewButton =
                new Button("View");

        viewButton.setPadding(
                new Insets(9, 14, 9, 14)
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
                        customer,
                        vehicle,
                        problem,
                        type,
                        distance,
                        amount,
                        time
                )
        );

        Button acceptButton =
                new Button("Accept");

        acceptButton.setPadding(
                new Insets(9, 16, 9, 16)
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
                        row,
                        customer,
                        vehicle,
                        problem,
                        type,
                        distance,
                        amount
                )
        );

        row.getChildren().addAll(
                avatar,
                customerBox,
                spacer,
                distanceBox,
                amountBox,
                viewButton,
                acceptButton
        );

        return row;
    }

    // =========================================================
    // ACCEPT REQUEST
    // =========================================================

    private void acceptRequest(
            HBox row,
            String customer,
            String vehicle,
            String problem,
            String type,
            String distance,
            String amount
    ) {

        if (requestsContainer != null) {

            requestsContainer
                    .getChildren()
                    .remove(row);
        }

        activeJobPage.setJob(
                customer,
                vehicle,
                problem,
                type,
                distance,
                amount
        );

        if (openActiveJob != null) {
            openActiveJob.run();
        }
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void filterRequests(
            String search
    ) {

        if (requestsContainer == null) {
            return;
        }

        String keyword =
                search == null
                        ? ""
                        : search.trim().toLowerCase();

        for (
                javafx.scene.Node node :
                requestsContainer.getChildren()
        ) {

            if (!(node instanceof HBox)) {
                continue;
            }

            HBox row = (HBox) node;

            String rowData =
                    String.valueOf(
                            row.getUserData()
                    ).toLowerCase();

            boolean matches =
                    keyword.isEmpty() ||
                    rowData.contains(keyword);

            row.setVisible(matches);
            row.setManaged(matches);
        }
    }

    // =========================================================
    // TYPE FILTER
    // =========================================================

    private void filterByType(
            String type
    ) {

        if (requestsContainer == null) {
            return;
        }

        String filter =
                type.toLowerCase();

        for (
                javafx.scene.Node node :
                requestsContainer.getChildren()
        ) {

            if (!(node instanceof HBox)) {
                continue;
            }

            HBox row = (HBox) node;

            String rowData =
                    String.valueOf(
                            row.getUserData()
                    ).toLowerCase();

            boolean matches =
                    rowData.contains(filter);

            row.setVisible(matches);
            row.setManaged(matches);
        }
    }

    // =========================================================
    // SHOW ALL
    // =========================================================

    private void showAllRequests() {

        if (requestsContainer == null) {
            return;
        }

        for (
                javafx.scene.Node node :
                requestsContainer.getChildren()
        ) {

            node.setVisible(true);
            node.setManaged(true);
        }
    }

    // =========================================================
    // REQUEST DETAILS
    // =========================================================

    private void showRequestDetails(
            String customer,
            String vehicle,
            String problem,
            String type,
            String distance,
            String amount,
            String time
    ) {

        pageContainer.getChildren().clear();

        VBox detailsPage = new VBox(18);

        detailsPage.setPadding(
                new Insets(35)
        );

        Label title =
                new Label("Request Details");

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

        VBox card = new VBox(14);

        card.setPadding(
                new Insets(25)
        );

        card.setMaxWidth(650);

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
                        customer
                ),

                createDetailLabel(
                        "VEHICLE",
                        vehicle
                ),

                createDetailLabel(
                        "PROBLEM",
                        problem
                ),

                createDetailLabel(
                        "REQUEST TYPE",
                        type
                ),

                createDetailLabel(
                        "DISTANCE",
                        distance
                ),

                createDetailLabel(
                        "ESTIMATED AMOUNT",
                        amount
                ),

                createDetailLabel(
                        "REQUESTED",
                        time
                )
        );

        HBox buttons = new HBox(10);

        Button acceptButton =
                new Button("Accept Request");

        acceptButton.setPadding(
                new Insets(12, 25, 12, 25)
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
                e -> {

                    activeJobPage.setJob(
                            customer,
                            vehicle,
                            problem,
                            type,
                            distance,
                            amount
                    );

                    if (openActiveJob != null) {
                        openActiveJob.run();
                    }
                }
        );

        Button backButton =
                new Button("Back to Requests");

        backButton.setPadding(
                new Insets(12, 25, 12, 25)
        );

        backButton.setStyle(
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
                "-fx-font-size: 12px;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnAction(
                e -> buildRequestsPage()
        );

        buttons.getChildren().addAll(
                acceptButton,
                backButton
        );

        card.getChildren().add(buttons);

        detailsPage.getChildren().addAll(
                title,
                subtitle,
                card
        );

        pageContainer.getChildren().add(
                detailsPage
        );
    }

    // =========================================================
    // DETAIL LABEL
    // =========================================================

    private Label createDetailLabel(
            String title,
            String value
    ) {

        Label label =
                new Label(
                        title +
                        "\n" +
                        value
                );

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        return label;
    }
}
package project.ui.mechanic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import project.util.Theme;

public class JobHistoryPage extends Application {

    private BorderPane root;
    private VBox jobsContainer;
    private TextField searchField;
    private ComboBox<String> statusFilter;

    private final ObservableList<JobData> jobs =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        loadDemoData();

        root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND + ";"
        );

        root.setLeft(createSidebar());
        root.setCenter(createHistoryContent());

        Scene scene =
                new Scene(root, 1280, 800);

        stage.setTitle(
                "RoadGuardian - Job History"
        );

        stage.setScene(scene);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.show();
    }

    private void loadDemoData() {

        jobs.clear();

        jobs.add(new JobData(
                "Vikram Kulkarni",
                "Toyota Innova",
                "Engine Issue",
                "Kothrud, Pune",
                "12 Aug 2026",
                "₹2,850",
                "Completed"
        ));

        jobs.add(new JobData(
                "Amit Sharma",
                "Hyundai Creta",
                "Battery Failure",
                "Baner, Pune",
                "10 Aug 2026",
                "₹1,450",
                "Completed"
        ));

        jobs.add(new JobData(
                "Priya Joshi",
                "Maruti Suzuki Swift",
                "Flat Tyre",
                "Wakad, Pune",
                "08 Aug 2026",
                "₹850",
                "Completed"
        ));

        jobs.add(new JobData(
                "Rahul Deshmukh",
                "Tata Nexon",
                "Brake Problem",
                "Aundh, Pune",
                "05 Aug 2026",
                "₹3,200",
                "Completed"
        ));

        jobs.add(new JobData(
                "Sneha Patil",
                "Honda City",
                "Engine Overheating",
                "Shivajinagar, Pune",
                "02 Aug 2026",
                "₹2,100",
                "Cancelled"
        ));
    }

    private VBox createSidebar() {

        VBox sidebar =
                new VBox();

        sidebar.setPrefWidth(245);
        sidebar.setSpacing(10);

        sidebar.setPadding(
                new Insets(28, 18, 25, 18)
        );

        sidebar.setStyle(
                "-fx-background-color: " +
                Theme.SIDEBAR + ";"
        );

        HBox logoBox =
                new HBox(10);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        logoBox.setPadding(
                new Insets(0, 8, 28, 8)
        );

        Circle logoCircle =
                new Circle(20);

        logoCircle.setFill(
                Color.web(Theme.PRIMARY)
        );

        Label logoText =
                new Label("RoadGuardian");

        logoText.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        logoBox.getChildren().addAll(
                logoCircle,
                logoText
        );

        Label roleLabel =
                new Label("MECHANIC PORTAL");

        roleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        roleLabel.setPadding(
                new Insets(0, 8, 8, 8)
        );

        Button dashboardButton =
                createMenuButton(
                        "⌂   Dashboard",
                        false
                );

        Button requestsButton =
                createMenuButton(
                        "▣   Service Requests",
                        false
                );

        Button navigationButton =
                createMenuButton(
                        "➤   Navigation",
                        false
                );

        Button activeJobButton =
                createMenuButton(
                        "⚙   Active Job",
                        false
                );

        Button historyButton =
                createMenuButton(
                        "◷   Job History",
                        true
                );

        dashboardButton.setOnAction(
                e -> showMessage("Dashboard")
        );

        requestsButton.setOnAction(
                e -> showMessage("Service Requests")
        );

        navigationButton.setOnAction(
                e -> showMessage("Navigation")
        );

        activeJobButton.setOnAction(
                e -> showMessage("Active Job")
        );

        historyButton.setOnAction(
                e -> root.setCenter(
                        createHistoryContent()
                )
        );

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        VBox profileBox =
                new VBox(4);

        profileBox.setPadding(
                new Insets(14)
        );

        profileBox.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE + ";" +
                "-fx-background-radius: 12;"
        );

        Label mechanicName =
                new Label("Rajesh Patil");

        mechanicName.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label mechanicRole =
                new Label("Verified Mechanic");

        mechanicRole.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        profileBox.getChildren().addAll(
                mechanicName,
                mechanicRole
        );

        Button logoutButton =
                createMenuButton(
                        "↪   Logout",
                        false
                );

        sidebar.getChildren().addAll(
                logoBox,
                roleLabel,
                dashboardButton,
                requestsButton,
                navigationButton,
                activeJobButton,
                historyButton,
                spacer,
                profileBox,
                logoutButton
        );

        return sidebar;
    }

    private Button createMenuButton(
            String text,
            boolean active
    ) {

        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(13, 15, 13, 15)
        );

        if (active) {

            button.setStyle(
                    "-fx-background-color: " +
                    Theme.PRIMARY + ";" +
                    "-fx-text-fill: " +
                    Theme.WHITE + ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " +
                    Theme.TEXT + ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-size: 13px;" +
                    "-fx-cursor: hand;"
            );
        }

        button.setOnMouseEntered(e -> {

            if (!active) {

                button.setStyle(
                        "-fx-background-color: " +
                        Theme.SURFACE + ";" +
                        "-fx-text-fill: " +
                        Theme.TEXT + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 13px;" +
                        "-fx-cursor: hand;"
                );
            }
        });

        button.setOnMouseExited(e -> {

            if (!active) {

                button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: " +
                        Theme.TEXT + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 13px;" +
                        "-fx-cursor: hand;"
                );
            }
        });

        return button;
    }

    private ScrollPane createHistoryContent() {

        VBox mainContent =
                new VBox(20);

        mainContent.setPadding(
                new Insets(30, 35, 35, 35)
        );

        VBox header =
                createHeader();

        HBox stats =
                createStatistics();

        HBox filters =
                createFilters();

        VBox historyCard =
                createHistoryCard();

        mainContent.getChildren().addAll(
                header,
                stats,
                filters,
                historyCard
        );

        ScrollPane scrollPane =
                new ScrollPane(mainContent);

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        return scrollPane;
    }

    private VBox createHeader() {

        VBox header =
                new VBox(5);

        Label title =
                new Label("Job History");

        title.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "View and review your completed roadside service jobs."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        return header;
    }

    private HBox createStatistics() {

        HBox stats =
                new HBox(15);

        stats.getChildren().addAll(
                createStatCard(
                        "TOTAL JOBS",
                        "24",
                        "All service jobs",
                        Theme.INFO
                ),
                createStatCard(
                        "THIS MONTH",
                        "12",
                        "Jobs completed",
                        Theme.SUCCESS
                ),
                createStatCard(
                        "TOTAL EARNINGS",
                        "₹38,450",
                        "From completed jobs",
                        Theme.PRIMARY
                ),
                createStatCard(
                        "AVG. RATING",
                        "4.8 ★",
                        "Customer rating",
                        "#7C3AED"
                )
        );

        return stats;
    }

    private VBox createStatCard(
            String title,
            String value,
            String subtitle,
            String accent
    ) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(18)
        );

        card.setPrefHeight(105);

        card.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 12;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                accent + ";"
        );

        Label subtitleLabel =
                new Label(subtitle);

        subtitleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        card.getChildren().addAll(
                titleLabel,
                valueLabel,
                subtitleLabel
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        return card;
    }

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

        searchField.setPrefWidth(340);

        searchField.setPadding(
                new Insets(11, 14, 11, 14)
        );

        searchField.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        refreshJobs()
        );

        statusFilter =
                new ComboBox<>();

        statusFilter.getItems().addAll(
                "All Status",
                "Completed",
                "Cancelled"
        );

        statusFilter.setValue(
                "All Status"
        );

        statusFilter.setPrefWidth(150);

        statusFilter.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;"
        );

        statusFilter.setOnAction(
                e -> refreshJobs()
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button refreshButton =
                new Button("↻  Refresh");

        refreshButton.setPadding(
                new Insets(10, 16, 10, 16)
        );

        refreshButton.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-text-fill: " +
                Theme.TEXT + ";" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        refreshButton.setOnAction(
                e -> refreshJobs()
        );

        filters.getChildren().addAll(
                searchField,
                statusFilter,
                spacer,
                refreshButton
        );

        return filters;
    }

    private VBox createHistoryCard() {

        VBox card =
                new VBox(0);

        card.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        HBox tableHeader =
                createTableHeader();

        jobsContainer =
                new VBox();

        card.getChildren().addAll(
                tableHeader,
                jobsContainer
        );

        refreshJobs();

        return card;
    }

    private HBox createTableHeader() {

        HBox header =
                new HBox();

        header.setPadding(
                new Insets(15, 18, 15, 18)
        );

        header.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE + ";" +
                "-fx-background-radius: 14 14 0 0;"
        );

        header.getChildren().addAll(
                createHeaderCell("CUSTOMER", 170),
                createHeaderCell("VEHICLE", 155),
                createHeaderCell("PROBLEM", 150),
                createHeaderCell("LOCATION", 155),
                createHeaderCell("DATE", 105),
                createHeaderCell("AMOUNT", 95),
                createHeaderCell("STATUS", 105)
        );

        return header;
    }

    private Label createHeaderCell(
            String text,
            double width
    ) {

        Label label =
                new Label(text);

        label.setPrefWidth(width);

        label.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        return label;
    }

    private void refreshJobs() {

        if (jobsContainer == null) {
            return;
        }

        jobsContainer.getChildren().clear();

        String search =
                searchField == null
                        ? ""
                        : searchField.getText()
                                .trim()
                                .toLowerCase();

        String selectedStatus =
                statusFilter == null
                        ? "All Status"
                        : statusFilter.getValue();

        boolean found = false;

        for (JobData job : jobs) {

            boolean matchesSearch =
                    search.isEmpty()
                            || job.customer
                            .toLowerCase()
                            .contains(search)
                            || job.vehicle
                            .toLowerCase()
                            .contains(search)
                            || job.problem
                            .toLowerCase()
                            .contains(search)
                            || job.location
                            .toLowerCase()
                            .contains(search);

            boolean matchesStatus =
                    selectedStatus.equals("All Status")
                            || job.status.equals(
                            selectedStatus
                    );

            if (matchesSearch &&
                    matchesStatus) {

                jobsContainer.getChildren().add(
                        createJobRow(job)
                );

                found = true;
            }
        }

        if (!found) {

            VBox empty =
                    new VBox(8);

            empty.setAlignment(
                    Pos.CENTER
            );

            empty.setPadding(
                    new Insets(35)
            );

            Label title =
                    new Label("No jobs found");

            title.setStyle(
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " +
                    Theme.TEXT + ";"
            );

            Label subtitle =
                    new Label(
                            "Try changing your search or filter."
                    );

            subtitle.setStyle(
                    "-fx-font-size: 10px;" +
                    "-fx-text-fill: " +
                    Theme.SECONDARY_TEXT + ";"
            );

            empty.getChildren().addAll(
                    title,
                    subtitle
            );

            jobsContainer.getChildren().add(
                    empty
            );
        }
    }

    private HBox createJobRow(
            JobData job
    ) {

        HBox row =
                new HBox();

        row.setPadding(
                new Insets(15, 18, 15, 18)
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";"
        );

        VBox customer =
                createCustomerCell(
                        job.customer
                );

        customer.setPrefWidth(170);

        VBox vehicle =
                createSimpleCell(
                        job.vehicle,
                        155
                );

        VBox problem =
                createSimpleCell(
                        job.problem,
                        150
                );

        VBox location =
                createSimpleCell(
                        job.location,
                        155
                );

        VBox date =
                createSimpleCell(
                        job.date,
                        105
                );

        VBox amount =
                createSimpleCell(
                        job.amount,
                        95
                );

        Label status =
                createStatusLabel(
                        job.status
                );

        HBox statusBox =
                new HBox(status);

        statusBox.setPrefWidth(105);

        statusBox.setAlignment(
                Pos.CENTER_LEFT
        );

        row.getChildren().addAll(
                customer,
                vehicle,
                problem,
                location,
                date,
                amount,
                statusBox
        );

        row.setOnMouseEntered(
                e -> row.setStyle(
                        "-fx-background-color: " +
                        Theme.SURFACE + ";"
                )
        );

        row.setOnMouseExited(
                e -> row.setStyle(
                        "-fx-background-color: " +
                        Theme.CARD + ";"
                )
        );

        Separator separator =
                new Separator();

        VBox wrapper =
                new VBox();

        wrapper.getChildren().addAll(
                row,
                separator
        );

        HBox result =
                new HBox(wrapper);

        return result;
    }

    private VBox createCustomerCell(
            String name
    ) {

        HBox row =
                new HBox(9);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle avatar =
                new Circle(17);

        avatar.setFill(
                Color.web(Theme.SURFACE)
        );

        Label initial =
                new Label(
                        name.substring(0, 1)
                );

        initial.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.INFO + ";"
        );

        StackPane avatarPane =
                new StackPane(
                        avatar,
                        initial
                );

        Label nameLabel =
                new Label(name);

        nameLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        row.getChildren().addAll(
                avatarPane,
                nameLabel
        );

        return new VBox(row);
    }

    private VBox createSimpleCell(
            String text,
            double width
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        label.setWrapText(true);

        VBox box =
                new VBox(label);

        box.setPrefWidth(width);

        return box;
    }

    private Label createStatusLabel(
            String status
    ) {

        Label label =
                new Label(status);

        label.setPadding(
                new Insets(5, 10, 5, 10)
        );

        if (status.equals("Completed")) {

            label.setStyle(
                    "-fx-background-color: " +
                    Theme.SUCCESS_BG + ";" +
                    "-fx-text-fill: " +
                    Theme.SUCCESS + ";" +
                    "-fx-background-radius: 12;" +
                    "-fx-font-size: 9px;" +
                    "-fx-font-weight: bold;"
            );

        } else {

            label.setStyle(
                    "-fx-background-color: " +
                    Theme.ERROR_BG + ";" +
                    "-fx-text-fill: " +
                    Theme.ERROR + ";" +
                    "-fx-background-radius: 12;" +
                    "-fx-font-size: 9px;" +
                    "-fx-font-weight: bold;"
            );
        }

        return label;
    }

    private void showMessage(
            String pageName
    ) {

        VBox message =
                new VBox(12);

        message.setPadding(
                new Insets(35)
        );

        message.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND + ";"
        );

        Label title =
                new Label(pageName);

        title.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label text =
                new Label(
                        pageName +
                        " will be implemented as a separate page."
                );

        text.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        message.getChildren().addAll(
                title,
                text
        );

        root.setCenter(message);
    }

    private static class JobData {

        private final String customer;
        private final String vehicle;
        private final String problem;
        private final String location;
        private final String date;
        private final String amount;
        private final String status;

        public JobData(
                String customer,
                String vehicle,
                String problem,
                String location,
                String date,
                String amount,
                String status
        ) {

            this.customer = customer;
            this.vehicle = vehicle;
            this.problem = problem;
            this.location = location;
            this.date = date;
            this.amount = amount;
            this.status = status;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
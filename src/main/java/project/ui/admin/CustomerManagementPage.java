package project.ui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Cursor;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import project.controller.admin.CustomerController;
import project.model.Customer;

import java.util.List;

public class CustomerManagementPage extends AdminSectionPage {

    // =========================================================
    // AQUA MIST THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SIDEBAR = "#C4D9E0";
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

    private final ObservableList<Customer> customerList =
            FXCollections.observableArrayList();

    private VBox customerCards;
    private TextField searchField;
    private ComboBox<String> statusFilter;
    private ComboBox<String> sortFilter;
    private Label resultCountLabel;

    private CustomerController controller;

    // =========================================================
    // STATISTICS
    // =========================================================

    private Label totalCustomersLabel;
    private Label activeCustomersLabel;
    private Label inactiveCustomersLabel;

    // =========================================================
    // GET VIEW
    // =========================================================

    @Override
    public VBox getView() {

        VBox root = new VBox(22);

        root.setPadding(
                new Insets(30, 32, 32, 32)
        );

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        try {

            controller = new CustomerController();

        } catch (Exception e) {

            showError(
                    "Firebase Connection Failed",
                    e.getMessage()
            );

            return createErrorView();
        }

        // HEADER
        VBox header = createHeader();

        // STATISTICS
        HBox statistics = createStatistics();

        // TOOLBAR
        HBox toolbar = createToolbar();

        // CUSTOMER CARDS
        VBox tableCard = createCustomerRecordsCard();

        root.getChildren().addAll(
                header,
                statistics,
                toolbar,
                tableCard
        );

        VBox.setVgrow(
                tableCard,
                Priority.ALWAYS
        );

        loadCustomers();

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header = new VBox(7);

        Label title = new Label(
                "Customers"
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

        Label subtitle = new Label(
                "Manage and monitor customers registered in RoadGuardian."
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

        HBox statistics =
                new HBox(16);

        statistics.setFillHeight(true);

        totalCustomersLabel =
                createValueLabel(BLUE);

        activeCustomersLabel =
                createValueLabel(GREEN);

        inactiveCustomersLabel =
                createValueLabel(RED);

        VBox totalCard =
                createStatCard(
                        "Total Customers",
                        "All registered customers",
                        totalCustomersLabel,
                        BLUE,
                        "CUSTOMERS"
                );

        VBox activeCard =
                createStatCard(
                        "Active Customers",
                        "Currently active",
                        activeCustomersLabel,
                        GREEN,
                        "ACTIVE"
                );

        VBox inactiveCard =
                createStatCard(
                        "Inactive Customers",
                        "Currently inactive",
                        inactiveCustomersLabel,
                        RED,
                        "INACTIVE"
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

        statistics.getChildren().addAll(
                totalCard,
                activeCard,
                inactiveCard
        );

        return statistics;
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
                new Insets(20, 22, 18, 22)
        );

        card.setMinHeight(130);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;"
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
                "Search customers by name, email, phone or city..."
        );
        searchField.setPrefWidth(380);
        searchField.setPrefHeight(44);
        styleTextField(searchField);

        statusFilter = new ComboBox<>();
        statusFilter.getItems().addAll("All", "Active", "Inactive");
        statusFilter.setValue("All");
        statusFilter.setPrefWidth(125);
        statusFilter.setPrefHeight(44);
        styleComboBox(statusFilter);

        sortFilter = new ComboBox<>();
        sortFilter.getItems().addAll(
                "Newest First", "Oldest First", "Name A-Z", "Name Z-A"
        );
        sortFilter.setValue("Newest First");
        sortFilter.setPrefWidth(145);
        sortFilter.setPrefHeight(44);
        styleComboBox(sortFilter);

        Button refreshButton = createActionButton("Refresh", BLUE);
        refreshButton.setPrefHeight(44);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button addButton = createActionButton("+ Add Customer", ORANGE);
        addButton.setPrefHeight(44);
        addButton.setPadding(new Insets(0, 20, 0, 20));

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> applyFilters()
        );

        statusFilter.valueProperty().addListener(
                (observable, oldValue, newValue) -> applyFilters()
        );

        sortFilter.valueProperty().addListener(
                (observable, oldValue, newValue) -> applyFilters()
        );

        refreshButton.setOnAction(event -> loadCustomers());
        addButton.setOnAction(event -> showAddCustomerDialog());

        toolbar.getChildren().addAll(
                searchField, statusFilter, sortFilter, refreshButton, spacer, addButton
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
                        16,
                        0,
                        16
                )
        );

        button.setStyle(
                "-fx-background-color: " +
                color +
                ";" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(
                event -> {

                    if (
                            color.equals(ORANGE)
                    ) {

                        button.setStyle(
                                "-fx-background-color: " +
                                ORANGE_HOVER +
                                ";" +
                                "-fx-background-radius: 9;" +
                                "-fx-cursor: hand;"
                        );

                    } else {

                        button.setStyle(
                                "-fx-background-color: #1D4ED8;" +
                                "-fx-background-radius: 9;" +
                                "-fx-cursor: hand;"
                        );
                    }
                }
        );

        button.setOnMouseExited(
                event -> {

                    button.setStyle(
                            "-fx-background-color: " +
                            color +
                            ";" +
                            "-fx-background-radius: 9;" +
                            "-fx-cursor: hand;"
                    );
                }
        );

        return button;
    }

    // =========================================================
    // CUSTOMER RECORDS CARD
    // =========================================================

    private VBox createCustomerRecordsCard() {

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
        Label heading = new Label("Customer Information");
        heading.setTextFill(Color.web(HEADING));
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label helper = new Label(
                "Registered customers and their account information"
        );
        helper.setTextFill(Color.web(TEXT));
        helper.setFont(Font.font("Arial", 14));

        resultCountLabel = new Label("0 customers");
        resultCountLabel.setTextFill(Color.web(BLUE));
        resultCountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        headingBox.getChildren().addAll(heading, helper, resultCountLabel);

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

        header.getChildren().addAll(headingBox, spacer, live);

        customerCards = new VBox(12);
        customerCards.setFillWidth(true);
        customerCards.setPadding(new Insets(3, 2, 10, 2));

        ScrollPane scrollPane = new ScrollPane(customerCards);
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
    // CUSTOMER CARD
    // =========================================================

    private VBox createCustomerCard(Customer customer, int number) {

        VBox card = new VBox(14);
        card.setPadding(new Insets(18, 20, 18, 20));
        card.setMaxWidth(Double.MAX_VALUE);
        card.setCursor(Cursor.HAND);

        String normal =
                "-fx-background-color: " + SECONDARY + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;";

        String hover =
                "-fx-background-color: #EAF8FA;" +
                "-fx-border-color: " + BLUE + ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;" +
                "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.18), 12, 0.15, 0, 3);";

        card.setStyle(normal);

        HBox top = new HBox(13);
        top.setAlignment(Pos.CENTER_LEFT);

        StackPane avatar = createAvatar(safe(customer.getName()));
        VBox identity = new VBox(3);

        HBox nameLine = new HBox(9);
        nameLine.setAlignment(Pos.CENTER_LEFT);

        Label numberLabel = new Label("#" + number);
        numberLabel.setTextFill(Color.web(BLUE));
        numberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        numberLabel.setPadding(new Insets(4, 8, 4, 8));
        numberLabel.setStyle(
                "-fx-background-color: " + BLUE + "14;" +
                "-fx-background-radius: 20;"
        );

        Label name = new Label(safe(customer.getName()));
        name.setTextFill(Color.web(HEADING));
        name.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        nameLine.getChildren().addAll(numberLabel, name);

        Label role = new Label("Customer");
        role.setTextFill(Color.web(TEXT));
        role.setFont(Font.font("Arial", 13));
        identity.getChildren().addAll(nameLine, role);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label statusBadge = createStatusBadge(safe(customer.getStatus()));
        top.getChildren().addAll(avatar, identity, spacer, statusBadge);

        HBox infoRow = new HBox(14);
        VBox emailBox = createInfoBox("EMAIL", safe(customer.getEmail()));
        VBox phoneBox = createInfoBox("PHONE", safe(customer.getPhone()));
        VBox cityBox = createInfoBox("CITY", safe(customer.getCity()));
        HBox.setHgrow(emailBox, Priority.ALWAYS);
        HBox.setHgrow(phoneBox, Priority.ALWAYS);
        HBox.setHgrow(cityBox, Priority.ALWAYS);
        infoRow.getChildren().addAll(emailBox, phoneBox, cityBox);

        HBox bottom = new HBox(12);
        bottom.setAlignment(Pos.CENTER_LEFT);

        Label idLabel = new Label(
                "Customer #" + number
        );
        idLabel.setTextFill(Color.web(TEXT));
        idLabel.setFont(Font.font("Arial", 12));

        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        Button editButton = new Button("Edit");
        styleSmallButton(editButton, TEXT);
        editButton.setOnAction(event -> showEditCustomerDialog(customer));

        Button viewButton = new Button("View Details →");
        styleSmallButton(viewButton, BLUE);
        viewButton.setOnAction(event -> showCustomerDetails(customer, number));

        bottom.getChildren().addAll(
                idLabel, bottomSpacer, editButton, viewButton
        );

        card.getChildren().addAll(
                top, new Separator(), infoRow, bottom
        );

        card.setOnMouseEntered(event -> {
            card.setStyle(hover);
            card.setTranslateY(-2);
        });
        card.setOnMouseExited(event -> {
            card.setStyle(normal);
            card.setTranslateY(0);
        });

        card.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                showCustomerDetails(customer, number);
            }
        });

        return card;
    }

    private StackPane createAvatar(String name) {

        Circle circle = new Circle(24, Color.web(BLUE));
        Label initials = new Label(getInitials(name));
        initials.setTextFill(Color.WHITE);
        initials.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        StackPane avatar = new StackPane(circle, initials);
        avatar.setMinSize(48, 48);
        avatar.setPrefSize(48, 48);
        avatar.setMaxSize(48, 48);
        return avatar;
    }

    private String getInitials(String name) {

        if (name == null || name.isBlank()) return "?";

        String[] parts = name.trim().split("\\s+");
        if (parts.length == 1) {
            return parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
        }

        return (
                parts[0].substring(0, 1) +
                parts[parts.length - 1].substring(0, 1)
        ).toUpperCase();
    }

    private VBox createInfoBox(String title, String value) {

        VBox box = new VBox(5);
        box.setPadding(new Insets(10, 12, 10, 12));
        box.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-background-radius: 9;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 9;"
        );

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.web(TEXT));
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        Label valueLabel = new Label(value);
        valueLabel.setTextFill(Color.web(HEADING));
        valueLabel.setFont(Font.font("Arial", 14));
        valueLabel.setWrapText(true);

        box.getChildren().addAll(titleLabel, valueLabel);
        return box;
    }

    private Label createStatusBadge(String status) {

        Label badge = new Label(
                status.equals("-") ? "Unknown" : status
        );
        badge.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        badge.setPadding(new Insets(7, 12, 7, 12));

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

    private void styleSmallButton(Button button, String color) {

        button.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        button.setTextFill(
                color.equals(BLUE) ? Color.WHITE : Color.web(HEADING)
        );
        button.setPadding(new Insets(8, 13, 8, 13));
        button.setCursor(Cursor.HAND);

        String normal = color.equals(BLUE) ? BLUE : SURFACE;
        String hover = color.equals(BLUE) ? "#1D4ED8" : SECONDARY;
        String border = color.equals(BLUE) ? BLUE : BORDER;

        button.setStyle(
                "-fx-background-color: " + normal + ";" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 8;"
        );

        button.setOnMouseEntered(event -> button.setStyle(
                "-fx-background-color: " + hover + ";" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: " + (color.equals(BLUE) ? "#1D4ED8" : BLUE) + ";" +
                "-fx-border-radius: 8;"
        ));

        button.setOnMouseExited(event -> button.setStyle(
                "-fx-background-color: " + normal + ";" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 8;"
        ));
    }

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
    // APPLY FILTERS
    // =========================================================

    private void applyFilters() {

        if (customerCards == null) return;

        String search = searchField == null
                ? ""
                : searchField.getText().trim().toLowerCase();

        String status = statusFilter == null
                ? "All"
                : statusFilter.getValue();

        String sort = sortFilter == null
                ? "Newest First"
                : sortFilter.getValue();

        List<Customer> filtered = new ArrayList<>();

        for (Customer customer : customerList) {

            if (!matchesSearch(customer, search)) continue;

            if (
                    !"All".equals(status) &&
                    !status.equalsIgnoreCase(safe(customer.getStatus()))
            ) continue;

            filtered.add(customer);
        }

        sortCustomers(filtered, sort);
        renderCustomerCards(filtered);
    }

    private boolean matchesSearch(Customer customer, String search) {

        if (search.isBlank()) return true;

        return safe(customer.getName()).toLowerCase().contains(search)
                || safe(customer.getEmail()).toLowerCase().contains(search)
                || safe(customer.getPhone()).toLowerCase().contains(search)
                || safe(customer.getCity()).toLowerCase().contains(search);
    }

    private void sortCustomers(List<Customer> customers, String sort) {

        if ("Name A-Z".equals(sort)) {
            customers.sort((a, b) -> safe(a.getName()).compareToIgnoreCase(safe(b.getName())));
        } else if ("Name Z-A".equals(sort)) {
            customers.sort((a, b) -> safe(b.getName()).compareToIgnoreCase(safe(a.getName())));
        } else if ("Oldest First".equals(sort)) {
            java.util.Collections.reverse(customers);
        }
    }

    private void renderCustomerCards(List<Customer> customers) {

        customerCards.getChildren().clear();

        if (customers.isEmpty()) {
            customerCards.getChildren().add(createEmptyState());
            resultCountLabel.setText("0 customers found");
            return;
        }

        int number = 1;
        for (Customer customer : customers) {
            customerCards.getChildren().add(
                    createCustomerCard(customer, number++)
            );
        }

        resultCountLabel.setText(
                customers.size() == 1
                        ? "1 customer"
                        : customers.size() + " customers"
        );
    }

    private VBox createEmptyState() {

        VBox box = new VBox(9);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(45));

        Label icon = new Label("♙");
        icon.setTextFill(Color.web(BLUE));
        icon.setFont(Font.font("Arial", FontWeight.BOLD, 34));

        Label title = new Label("No customers found");
        title.setTextFill(Color.web(HEADING));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label subtitle = new Label(
                "Try changing your search or filter."
        );
        subtitle.setTextFill(Color.web(TEXT));
        subtitle.setFont(Font.font("Arial", 14));

        Button addButton = createActionButton("+ Add Customer", ORANGE);
        addButton.setOnAction(event -> showAddCustomerDialog());

        box.getChildren().addAll(icon, title, subtitle, addButton);
        return box;
    }

    // =========================================================
    // LOAD CUSTOMERS
    // =========================================================

    private void loadCustomers() {

        try {
            List<Customer> customers = controller.getAllCustomers();
            customerList.setAll(customers);
            updateStatistics(customers);
            applyFilters();
        } catch (Exception e) {
            showError("Unable to Load Customers", e.getMessage());
        }
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchCustomers(String searchText) {
        applyFilters();
    }

    // =========================================================
    // FILTER
    // =========================================================

    private void filterByStatus(String status) {
        applyFilters();
    }

    // =========================================================
    // STATISTICS UPDATE
    // =========================================================

    private void updateStatistics(
            List<Customer> customers
    ) {

        int active = 0;
        int inactive = 0;

        for (
                Customer customer :
                customers
        ) {

            if (
                    customer.getStatus() != null &&
                    customer.getStatus()
                            .equalsIgnoreCase(
                                    "Active"
                            )
            ) {

                active++;

            } else {

                inactive++;
            }
        }

        totalCustomersLabel.setText(
                String.valueOf(
                        customers.size()
                )
        );

        activeCustomersLabel.setText(
                String.valueOf(
                        active
                )
        );

        inactiveCustomersLabel.setText(
                String.valueOf(
                        inactive
                )
        );
    }

    // =========================================================
    // ADD CUSTOMER
    // =========================================================

    private void showAddCustomerDialog() {

        Dialog<Customer> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Add Customer"
        );

        dialog.setHeaderText(
                "Create New Customer"
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
                createFormGrid();

        TextField name =
                createFormField("");

        TextField email =
                createFormField("");

        TextField phone =
                createFormField("");

        TextField address =
                createFormField("");

        TextField city =
                createFormField("");

        ComboBox<String> status =
                new ComboBox<>();

        status.getItems().addAll(
                "Active",
                "Inactive"
        );

        status.setValue(
                "Active"
        );

        status.setPrefWidth(260);

        form.add(
                createFormLabel("Name"),
                0,
                0
        );

        form.add(
                name,
                1,
                0
        );

        form.add(
                createFormLabel("Email"),
                0,
                1
        );

        form.add(
                email,
                1,
                1
        );

        form.add(
                createFormLabel("Phone"),
                0,
                2
        );

        form.add(
                phone,
                1,
                2
        );

        form.add(
                createFormLabel("Address"),
                0,
                3
        );

        form.add(
                address,
                1,
                3
        );

        form.add(
                createFormLabel("City"),
                0,
                4
        );

        form.add(
                city,
                1,
                4
        );

        form.add(
                createFormLabel("Status"),
                0,
                5
        );

        form.add(
                status,
                1,
                5
        );

        dialog.getDialogPane()
                .setContent(form);

        styleDialog(dialog);

        dialog.setResultConverter(
                button -> {

                    if (
                            button == saveButton
                    ) {

                        if (
                                name.getText().isBlank() ||
                                email.getText().isBlank() ||
                                phone.getText().isBlank()
                        ) {

                            showError(
                                    "Invalid Data",
                                    "Name, email and phone are required."
                            );

                            return null;
                        }

                        return new Customer(
                                "",
                                name.getText().trim(),
                                email.getText().trim(),
                                phone.getText().trim(),
                                address.getText().trim(),
                                city.getText().trim(),
                                "",
                                status.getValue(),
                                String.valueOf(
                                        System.currentTimeMillis()
                                )
                        );
                    }

                    return null;
                }
        );

        dialog.showAndWait()
                .ifPresent(
                        customer -> {

                            boolean success =
                                    controller.addCustomer(
                                            customer
                                    );

                            if (success) {

                                loadCustomers();

                                showInformation(
                                        "Success",
                                        "Customer added successfully."
                                );

                            } else {

                                showError(
                                        "Failed",
                                        "Customer could not be added."
                                );
                            }
                        }
                );
    }

    // =========================================================
    // EDIT CUSTOMER
    // =========================================================

    private void showEditCustomerDialog(
            Customer customer
    ) {

        Dialog<Customer> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Edit Customer"
        );

        dialog.setHeaderText(
                "Update Customer Information"
        );

        ButtonType updateButton =
                new ButtonType(
                        "Update",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        updateButton,
                        ButtonType.CANCEL
                );

        GridPane form =
                createFormGrid();

        TextField name =
                createFormField(
                        safe(customer.getName())
                );

        TextField email =
                createFormField(
                        safe(customer.getEmail())
                );

        TextField phone =
                createFormField(
                        safe(customer.getPhone())
                );

        TextField address =
                createFormField(
                        safe(customer.getAddress())
                );

        TextField city =
                createFormField(
                        safe(customer.getCity())
                );

        ComboBox<String> status =
                new ComboBox<>();

        status.getItems().addAll(
                "Active",
                "Inactive"
        );

        status.setValue(
                customer.getStatus() == null
                        ? "Active"
                        : customer.getStatus()
        );

        status.setPrefWidth(260);

        form.add(
                createFormLabel("Name"),
                0,
                0
        );

        form.add(
                name,
                1,
                0
        );

        form.add(
                createFormLabel("Email"),
                0,
                1
        );

        form.add(
                email,
                1,
                1
        );

        form.add(
                createFormLabel("Phone"),
                0,
                2
        );

        form.add(
                phone,
                1,
                2
        );

        form.add(
                createFormLabel("Address"),
                0,
                3
        );

        form.add(
                address,
                1,
                3
        );

        form.add(
                createFormLabel("City"),
                0,
                4
        );

        form.add(
                city,
                1,
                4
        );

        form.add(
                createFormLabel("Status"),
                0,
                5
        );

        form.add(
                status,
                1,
                5
        );

        dialog.getDialogPane()
                .setContent(form);

        styleDialog(dialog);

        dialog.setResultConverter(
                button -> {

                    if (
                            button == updateButton
                    ) {

                        customer.setName(
                                name.getText().trim()
                        );

                        customer.setEmail(
                                email.getText().trim()
                        );

                        customer.setPhone(
                                phone.getText().trim()
                        );

                        customer.setAddress(
                                address.getText().trim()
                        );

                        customer.setCity(
                                city.getText().trim()
                        );

                        customer.setStatus(
                                status.getValue()
                        );

                        return customer;
                    }

                    return null;
                }
        );

        dialog.showAndWait()
                .ifPresent(
                        updatedCustomer -> {

                            boolean success =
                                    controller.updateCustomer(
                                            updatedCustomer
                                    );

                            if (success) {

                                loadCustomers();

                                showInformation(
                                        "Updated",
                                        "Customer updated successfully."
                                );

                            } else {

                                showError(
                                        "Update Failed",
                                        "Customer could not be updated."
                                );
                            }
                        }
                );
    }

    // =========================================================
    // DELETE
    // =========================================================

    private void deleteCustomer(
            Customer customer
    ) {

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Delete Customer"
        );

        confirmation.setHeaderText(
                "Delete " +
                safe(customer.getName()) +
                "?"
        );

        confirmation.setContentText(
                "This customer will be removed from Firestore."
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

        confirmation.getButtonTypes().setAll(
                deleteButton,
                cancelButton
        );

        confirmation.showAndWait()
                .ifPresent(
                        result -> {

                            if (
                                    result == deleteButton
                            ) {

                                boolean success =
                                        controller.deleteCustomer(
                                                customer.getCustomerId()
                                        );

                                if (success) {

                                    loadCustomers();

                                    showInformation(
                                            "Deleted",
                                            "Customer deleted successfully."
                                    );

                                } else {

                                    showError(
                                            "Delete Failed",
                                            "Customer could not be deleted."
                                    );
                                }
                            }
                        }
                );
    }

    // =========================================================
    // CUSTOMER DETAILS
    // =========================================================

    private void showCustomerDetails(
            Customer customer,
            int number
    ) {

        Dialog<Void> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Customer Details"
        );

        dialog.setHeaderText(
                safe(customer.getName())
        );

        ButtonType closeButton =
                new ButtonType(
                        "Close",
                        ButtonBar.ButtonData.CANCEL_CLOSE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .add(
                        closeButton
                );

        VBox content =
                new VBox(12);

        content.setPadding(
                new Insets(20)
        );

        content.setPrefWidth(
                430
        );

        content.getChildren().addAll(

                createDetailRow(
                        "Customer ID",
                        "#" + number
                ),

                createDetailRow(
                        "Email",
                        customer.getEmail()
                ),

                createDetailRow(
                        "Phone",
                        customer.getPhone()
                ),

                createDetailRow(
                        "Address",
                        customer.getAddress()
                ),

                createDetailRow(
                        "City",
                        customer.getCity()
                ),

                createDetailRow(
                        "Status",
                        customer.getStatus()
                )
        );

        dialog.getDialogPane()
                .setContent(content);

        styleDialog(dialog);

        dialog.showAndWait();
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private HBox createDetailRow(
            String labelText,
            String valueText
    ) {

        HBox row =
                new HBox(15);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label label =
                new Label(
                        labelText
                );

        label.setPrefWidth(100);

        label.setTextFill(
                Color.web(TEXT)
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        Label value =
                new Label(
                        safe(valueText)
                );

        value.setTextFill(
                Color.web(HEADING)
        );

        value.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        row.getChildren().addAll(
                label,
                value
        );

        return row;
    }

    // =========================================================
    // FORM
    // =========================================================

    private GridPane createFormGrid() {

        GridPane form =
                new GridPane();

        form.setHgap(15);

        form.setVgap(14);

        form.setPadding(
                new Insets(20)
        );

        return form;
    }

    private TextField createFormField(
            String value
    ) {

        TextField field =
                new TextField(value);

        field.setPrefWidth(270);

        field.setPrefHeight(40);

        field.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-text-fill: " + HEADING + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 0 10 0 10;" +
                "-fx-font-size: 15px;"
        );

        return field;
    }

    private Label createFormLabel(
            String text
    ) {

        Label label =
                new Label(text);

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

        return label;
    }

    // =========================================================
    // DIALOG STYLE
    // =========================================================

    private void styleDialog(
            Dialog<?> dialog
    ) {

        dialog.getDialogPane().setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );
    }

    // =========================================================
    // SAFE STRING
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

    private VBox createErrorView() {

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

        Label error =
                new Label(
                        "Unable to initialize Customer Management."
                );

        error.setTextFill(
                Color.web(HEADING)
        );

        error.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        box.getChildren().addAll(
                icon,
                error
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

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(
                message == null
                        ? "Unknown error"
                        : message
        );

        alert.showAndWait();
    }

    // =========================================================
    // INFORMATION ALERT
    // =========================================================

    private void showInformation(
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
}
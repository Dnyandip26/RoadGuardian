package project.ui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

    private TableView<Customer> customerTable;

    private final ObservableList<Customer> customerList =
            FXCollections.observableArrayList();

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

        // TABLE
        VBox tableCard = createTableCard();

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

        HBox toolbar =
                new HBox(12);

        toolbar.setAlignment(
                Pos.CENTER_LEFT
        );

        // SEARCH
        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search customers by name, email, phone or city..."
        );

        searchField.setPrefWidth(390);

        searchField.setPrefHeight(44);

        searchField.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-text-fill: " + HEADING + ";" +
                "-fx-prompt-text-fill: " + TEXT + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-padding: 0 14 0 14;" +
                "-fx-font-size: 16px;"
        );

        // FILTER
        ComboBox<String> statusFilter =
                new ComboBox<>();

        statusFilter.getItems().addAll(
                "All",
                "Active",
                "Inactive"
        );

        statusFilter.setValue("All");

        statusFilter.setPrefWidth(130);

        statusFilter.setPrefHeight(44);

        statusFilter.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 16px;"
        );

        // REFRESH
        Button refreshButton =
                createActionButton(
                        "Refresh",
                        BLUE
                );

        refreshButton.setPrefHeight(44);

        // SPACER
        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // ADD
        Button addButton =
                createActionButton(
                        "+ Add Customer",
                        ORANGE
                );

        addButton.setPrefHeight(44);

        addButton.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        // SEARCH
        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (
                            newValue == null ||
                            newValue.isBlank()
                    ) {

                        loadCustomers();

                    } else {

                        searchCustomers(
                                newValue.trim()
                        );
                    }
                }
        );

        // FILTER
        statusFilter.valueProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (
                            newValue == null ||
                            newValue.equals("All")
                    ) {

                        loadCustomers();

                    } else {

                        filterByStatus(
                                newValue
                        );
                    }
                }
        );

        refreshButton.setOnAction(
                event -> loadCustomers()
        );

        addButton.setOnAction(
                event -> showAddCustomerDialog()
        );

        toolbar.getChildren().addAll(
                searchField,
                statusFilter,
                refreshButton,
                spacer,
                addButton
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
    // TABLE CARD
    // =========================================================

    private VBox createTableCard() {

        VBox card =
                new VBox(15);

        card.setPadding(
                new Insets(21)
        );

        card.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;"
        );

        // HEADER
        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox headingBox =
                new VBox(4);

        Label heading =
                new Label(
                        "Customer Records"
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

        Label helper =
                new Label(
                        "Registered customers and their current account status"
                );

        helper.setTextFill(
                Color.web(TEXT)
        );

        helper.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        headingBox.getChildren().addAll(
                heading,
                helper
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

        // TABLE
        customerTable =
                new TableView<>();

        customerTable.setItems(
                customerList
        );

        customerTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        customerTable.setFixedCellSize(
                55
        );

        customerTable.setPrefHeight(
                420
        );

        customerTable.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                "-fx-control-inner-background: " + SURFACE + ";" +
                "-fx-table-cell-border-color: " + BORDER + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        // =====================================================
        // COLUMNS
        // =====================================================

        TableColumn<Customer, String> idColumn =
                new TableColumn<>(
                        "CUSTOMER ID"
                );

        TableColumn<Customer, String> nameColumn =
                new TableColumn<>(
                        "CUSTOMER"
                );

        TableColumn<Customer, String> emailColumn =
                new TableColumn<>(
                        "EMAIL"
                );

        TableColumn<Customer, String> phoneColumn =
                new TableColumn<>(
                        "PHONE"
                );

        TableColumn<Customer, String> cityColumn =
                new TableColumn<>(
                        "CITY"
                );

        TableColumn<Customer, String> statusColumn =
                new TableColumn<>(
                        "STATUS"
                );

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "customerId"
                )
        );

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "name"
                )
        );

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "email"
                )
        );

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "phone"
                )
        );

        cityColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "city"
                )
        );

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );

        // WIDTHS
        idColumn.setMinWidth(160);
        idColumn.setPrefWidth(180);

        nameColumn.setMinWidth(150);
        nameColumn.setPrefWidth(170);

        emailColumn.setMinWidth(200);
        emailColumn.setPrefWidth(220);

        phoneColumn.setMinWidth(140);
        phoneColumn.setPrefWidth(150);

        cityColumn.setMinWidth(110);
        cityColumn.setPrefWidth(125);

        statusColumn.setMinWidth(120);
        statusColumn.setPrefWidth(130);

        // =====================================================
        // ID
        // =====================================================

        idColumn.setCellFactory(
                column ->
                        new TableCell<Customer, String>() {

                            @Override
                            protected void updateItem(
                                    String item,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        item,
                                        empty
                                );

                                if (
                                        empty ||
                                        item == null
                                ) {

                                    setText(null);

                                } else {

                                    setText(item);

                                    setTextFill(
                                            Color.web(TEXT)
                                    );

                                    setFont(
                                            Font.font(
                                                    "Arial",
                                                    14
                                            )
                                    );
                                }
                            }
                        }
        );

        // =====================================================
        // NAME
        // =====================================================

        nameColumn.setCellFactory(
                column ->
                        new TableCell<Customer, String>() {

                            @Override
                            protected void updateItem(
                                    String item,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        item,
                                        empty
                                );

                                if (
                                        empty ||
                                        item == null
                                ) {

                                    setText(null);

                                } else {

                                    setText(item);

                                    setTextFill(
                                            Color.web(HEADING)
                                    );

                                    setFont(
                                            Font.font(
                                                    "Arial",
                                                    FontWeight.BOLD,
                                                    16
                                            )
                                    );
                                }
                            }
                        }
        );

        // =====================================================
        // EMAIL
        // =====================================================

        emailColumn.setCellFactory(
                column ->
                        createTextCell()
        );

        // PHONE
        phoneColumn.setCellFactory(
                column ->
                        createTextCell()
        );

        // CITY
        cityColumn.setCellFactory(
                column ->
                        createTextCell()
        );

        // =====================================================
        // STATUS
        // =====================================================

        statusColumn.setCellFactory(
                column ->
                        new TableCell<Customer, String>() {

                            @Override
                            protected void updateItem(
                                    String item,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        item,
                                        empty
                                );

                                if (
                                        empty ||
                                        item == null
                                ) {

                                    setGraphic(null);
                                    setText(null);

                                    return;
                                }

                                Label badge =
                                        new Label(item);

                                badge.setPadding(
                                        new Insets(
                                                7,
                                                13,
                                                7,
                                                13
                                        )
                                );

                                badge.setFont(
                                        Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14
                                        )
                                );

                                if (
                                        item.equalsIgnoreCase(
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

                                setAlignment(
                                        Pos.CENTER_LEFT
                                );

                                setGraphic(
                                        badge
                                );
                            }
                        }
        );

        customerTable.getColumns().addAll(
                idColumn,
                nameColumn,
                emailColumn,
                phoneColumn,
                cityColumn,
                statusColumn
        );

        // =====================================================
        // EMPTY STATE
        // =====================================================

        customerTable.setPlaceholder(
                createEmptyState()
        );

        // =====================================================
        // ROW
        // =====================================================

        customerTable.setRowFactory(
                tableView -> {

                    TableRow<Customer> row =
                            new TableRow<>();

                    row.setStyle(
                            "-fx-background-color: " +
                            SURFACE +
                            ";"
                    );

                    row.setOnMouseEntered(
                            event -> {

                                if (!row.isEmpty()) {

                                    row.setStyle(
                                            "-fx-background-color: " +
                                            SECONDARY +
                                            ";"
                                    );
                                }
                            }
                    );

                    row.setOnMouseExited(
                            event -> {

                                row.setStyle(
                                        "-fx-background-color: " +
                                        SURFACE +
                                        ";"
                                );
                            }
                    );

                    // DOUBLE CLICK
                    row.setOnMouseClicked(
                            event -> {

                                if (
                                        event.getClickCount() == 2 &&
                                        !row.isEmpty()
                                ) {

                                    showCustomerDetails(
                                            row.getItem()
                                    );
                                }
                            }
                    );

                    // CONTEXT MENU
                    ContextMenu contextMenu =
                            new ContextMenu();

                    MenuItem viewItem =
                            new MenuItem(
                                    "View Details"
                            );

                    MenuItem editItem =
                            new MenuItem(
                                    "Edit Customer"
                            );

                    MenuItem deleteItem =
                            new MenuItem(
                                    "Delete Customer"
                            );

                    viewItem.setOnAction(
                            event -> {

                                if (!row.isEmpty()) {

                                    showCustomerDetails(
                                            row.getItem()
                                    );
                                }
                            }
                    );

                    editItem.setOnAction(
                            event -> {

                                if (!row.isEmpty()) {

                                    showEditCustomerDialog(
                                            row.getItem()
                                    );
                                }
                            }
                    );

                    deleteItem.setOnAction(
                            event -> {

                                if (!row.isEmpty()) {

                                    deleteCustomer(
                                            row.getItem()
                                    );
                                }
                            }
                    );

                    contextMenu.getItems().addAll(
                            viewItem,
                            editItem,
                            new SeparatorMenuItem(),
                            deleteItem
                    );

                    row.contextMenuProperty().bind(
                            javafx.beans.binding.Bindings
                                    .when(
                                            row.emptyProperty()
                                    )
                                    .then(
                                            (ContextMenu) null
                                    )
                                    .otherwise(
                                            contextMenu
                                    )
                    );

                    return row;
                }
        );

        VBox.setVgrow(
                customerTable,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                header,
                customerTable
        );

        return card;
    }

    // =========================================================
    // NORMAL TABLE CELL
    // =========================================================

    private TableCell<Customer, String>
    createTextCell() {

        return new TableCell<Customer, String>() {

            @Override
            protected void updateItem(
                    String item,
                    boolean empty
            ) {

                super.updateItem(
                        item,
                        empty
                );

                if (
                        empty ||
                        item == null
                ) {

                    setText(null);

                } else {

                    setText(item);

                    setTextFill(
                            Color.web(TEXT)
                    );

                    setFont(
                            Font.font(
                                    "Arial",
                                    15
                            )
                    );
                }
            }
        };
    }

    // =========================================================
    // EMPTY STATE
    // =========================================================

    private VBox createEmptyState() {

        VBox box =
                new VBox(8);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(30)
        );

        Label icon =
                new Label("♙");

        icon.setTextFill(
                Color.web(BLUE)
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
                        "No customers found"
                );

        title.setTextFill(
                Color.web(HEADING)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Label subtitle =
                new Label(
                        "Customers registered in RoadGuardian will appear here."
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
    // LOAD CUSTOMERS
    // =========================================================

    private void loadCustomers() {

        try {

            List<Customer> customers =
                    controller.getAllCustomers();

            customerList.setAll(
                    customers
            );

            updateStatistics(
                    customers
            );

        } catch (Exception e) {

            showError(
                    "Unable to Load Customers",
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchCustomers(
            String searchText
    ) {

        try {

            List<Customer> customers =
                    controller.searchCustomers(
                            searchText
                    );

            customerList.setAll(
                    customers
            );

        } catch (Exception e) {

            showError(
                    "Search Failed",
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // FILTER
    // =========================================================

    private void filterByStatus(
            String status
    ) {

        try {

            List<Customer> customers =
                    controller.getCustomersByStatus(
                            status
                    );

            customerList.setAll(
                    customers
            );

        } catch (Exception e) {

            showError(
                    "Filter Failed",
                    e.getMessage()
            );
        }
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
            Customer customer
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
                        customer.getCustomerId()
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
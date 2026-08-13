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

import com.google.cloud.firestore.Firestore;

import project.controller.admin.VehicleController;
import project.firebase.FirebaseConfig;
import project.model.Vehicle;

import java.util.List;

public class VehicleManagementPage extends AdminSectionPage {

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
    // FIELDS
    // =========================================================

    private VehicleController controller;

    private TableView<Vehicle> vehicleTable;

    private final ObservableList<Vehicle> vehicleList =
            FXCollections.observableArrayList();

    private Label totalLabel;
    private Label activeLabel;
    private Label inactiveLabel;

    // =========================================================
    // VIEW
    // =========================================================

    @Override
    public VBox getView() {

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

        VBox header =
                createHeader();

        HBox statistics =
                createStatistics();

        HBox toolbar =
                createToolbar();

        VBox tableCard =
                createTableCard();

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

        loadVehicles();

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
                        "Manage customer vehicles registered with RoadGuardian."
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
                createValueLabel(
                        BLUE
                );

        activeLabel =
                createValueLabel(
                        GREEN
                );

        inactiveLabel =
                createValueLabel(
                        RED
                );

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

        card.setMinHeight(
                130
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

        TextField search =
                new TextField();

        search.setPromptText(
                "Search vehicle number, owner, brand, model..."
        );

        search.setPrefWidth(
                410
        );

        search.setPrefHeight(
                44
        );

        search.setStyle(
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
                "-fx-font-size: 16px;"
        );

        ComboBox<String> statusFilter =
                new ComboBox<>();

        statusFilter.getItems().addAll(
                "All",
                "Active",
                "Inactive"
        );

        statusFilter.setValue(
                "All"
        );

        statusFilter.setPrefWidth(
                125
        );

        statusFilter.setPrefHeight(
                44
        );

        statusFilter.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 16px;"
        );

        Button refresh =
                createActionButton(
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

        Button add =
                createActionButton(
                        "+ Add Vehicle",
                        ORANGE
                );

        add.setPrefHeight(
                44
        );

        search.textProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            if (
                                    newValue == null ||
                                    newValue.isBlank()
                            ) {

                                loadVehicles();

                            } else {

                                searchVehicles(
                                        newValue.trim()
                                );
                            }
                        }
                );

        statusFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            if (
                                    newValue == null ||
                                    newValue.equals("All")
                            ) {

                                loadVehicles();

                            } else {

                                filterVehicles(
                                        newValue
                                );
                            }
                        }
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
                search,
                statusFilter,
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

        button.setStyle(
                "-fx-background-color: " +
                color +
                ";" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(
                event -> {

                    String hoverColor =
                            color.equals(ORANGE)
                                    ? ORANGE_HOVER
                                    : "#1D4ED8";

                    button.setStyle(
                            "-fx-background-color: " +
                            hoverColor +
                            ";" +
                            "-fx-background-radius: 9;" +
                            "-fx-cursor: hand;"
                    );
                }
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                color +
                                ";" +
                                "-fx-background-radius: 9;" +
                                "-fx-cursor: hand;"
                        )
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
                        "Vehicle Records"
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
                        "Customer vehicles registered in RoadGuardian"
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

        headingBox.getChildren().addAll(
                heading,
                subtitle
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

        vehicleTable =
                new TableView<>();

        vehicleTable.setItems(
                vehicleList
        );

        vehicleTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        vehicleTable.setFixedCellSize(
                58
        );

        vehicleTable.setPrefHeight(
                430
        );

        vehicleTable.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-control-inner-background: " +
                SURFACE +
                ";" +
                "-fx-table-cell-border-color: " +
                BORDER +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";"
        );

        // =====================================================
        // COLUMNS
        // =====================================================

        TableColumn<Vehicle, String> idColumn =
                new TableColumn<>(
                        "VEHICLE ID"
                );

        TableColumn<Vehicle, String> numberColumn =
                new TableColumn<>(
                        "VEHICLE NUMBER"
                );

        TableColumn<Vehicle, String> ownerColumn =
                new TableColumn<>(
                        "OWNER"
                );

        TableColumn<Vehicle, String> brandColumn =
                new TableColumn<>(
                        "BRAND"
                );

        TableColumn<Vehicle, String> modelColumn =
                new TableColumn<>(
                        "MODEL"
                );

        TableColumn<Vehicle, String> typeColumn =
                new TableColumn<>(
                        "TYPE"
                );

        TableColumn<Vehicle, String> fuelColumn =
                new TableColumn<>(
                        "FUEL"
                );

        TableColumn<Vehicle, String> yearColumn =
                new TableColumn<>(
                        "YEAR"
                );

        TableColumn<Vehicle, String> statusColumn =
                new TableColumn<>(
                        "STATUS"
                );

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "vehicleId"
                )
        );

        numberColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "vehicleNumber"
                )
        );

        ownerColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "ownerName"
                )
        );

        brandColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "brand"
                )
        );

        modelColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "model"
                )
        );

        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "vehicleType"
                )
        );

        fuelColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "fuelType"
                )
        );

        yearColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "year"
                )
        );

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );

        idColumn.setMinWidth(150);
        numberColumn.setMinWidth(140);
        ownerColumn.setMinWidth(150);
        brandColumn.setMinWidth(110);
        modelColumn.setMinWidth(110);
        typeColumn.setMinWidth(110);
        fuelColumn.setMinWidth(90);
        yearColumn.setMinWidth(80);
        statusColumn.setMinWidth(110);

        // =====================================================
        // NORMAL CELLS
        // =====================================================

        idColumn.setCellFactory(
                column ->
                        createNormalCell(13)
        );

        brandColumn.setCellFactory(
                column ->
                        createNormalCell(15)
        );

        modelColumn.setCellFactory(
                column ->
                        createNormalCell(15)
        );

        typeColumn.setCellFactory(
                column ->
                        createNormalCell(15)
        );

        fuelColumn.setCellFactory(
                column ->
                        createNormalCell(15)
        );

        yearColumn.setCellFactory(
                column ->
                        createNormalCell(15)
        );

        // =====================================================
        // VEHICLE NUMBER
        // =====================================================

        numberColumn.setCellFactory(
                column ->
                        new TableCell<Vehicle, String>() {

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
                                            Color.web(
                                                    HEADING
                                            )
                                    );

                                    setFont(
                                            Font.font(
                                                    "Arial",
                                                    FontWeight.BOLD,
                                                    15
                                            )
                                    );
                                }
                            }
                        }
        );

        // =====================================================
        // OWNER
        // =====================================================

        ownerColumn.setCellFactory(
                column ->
                        new TableCell<Vehicle, String>() {

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
                                            Color.web(
                                                    HEADING
                                            )
                                    );

                                    setFont(
                                            Font.font(
                                                    "Arial",
                                                    FontWeight.BOLD,
                                                    15
                                            )
                                    );
                                }
                            }
                        }
        );

        // =====================================================
        // STATUS
        // =====================================================

        statusColumn.setCellFactory(
                column ->
                        new TableCell<Vehicle, String>() {

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
                                    setGraphic(null);

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
                                            Color.web(
                                                    GREEN
                                            )
                                    );

                                    badge.setStyle(
                                            "-fx-background-color: " +
                                            GREEN +
                                            "18;" +
                                            "-fx-background-radius: 20;"
                                    );

                                } else {

                                    badge.setTextFill(
                                            Color.web(
                                                    RED
                                            )
                                    );

                                    badge.setStyle(
                                            "-fx-background-color: " +
                                            RED +
                                            "18;" +
                                            "-fx-background-radius: 20;"
                                    );
                                }

                                setGraphic(badge);
                            }
                        }
        );

        vehicleTable.getColumns().addAll(
                idColumn,
                numberColumn,
                ownerColumn,
                brandColumn,
                modelColumn,
                typeColumn,
                fuelColumn,
                yearColumn,
                statusColumn
        );

        vehicleTable.setPlaceholder(
                createEmptyState()
        );

        // =====================================================
        // DOUBLE CLICK
        // =====================================================

        vehicleTable.setRowFactory(
                tableView -> {

                    TableRow<Vehicle> row =
                            new TableRow<>();

                    row.setOnMouseClicked(
                            event -> {

                                if (
                                        event.getClickCount() == 2 &&
                                        !row.isEmpty()
                                ) {

                                    showDetails(
                                            row.getItem()
                                    );
                                }
                            }
                    );

                    return row;
                }
        );

        VBox.setVgrow(
                vehicleTable,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                header,
                vehicleTable
        );

        return card;
    }

    // =========================================================
    // NORMAL CELL
    // =========================================================

    private TableCell<Vehicle, String>
    createNormalCell(
            int size
    ) {

        return new TableCell<Vehicle, String>() {

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
                                    size
                            )
                    );
                }
            }
        };
    }

    // =====================================================
    // EMPTY STATE
    // =====================================================

    private VBox createEmptyState() {

        VBox box =
                new VBox(8);

        box.setAlignment(
                Pos.CENTER
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
                        32
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
                        17
                )
        );

        Label subtitle =
                new Label(
                        "Registered customer vehicles will appear here."
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

    // =====================================================
    // LOAD VEHICLES
    // =====================================================

    private void loadVehicles() {

        List<Vehicle> vehicles =
                controller.getAllVehicles();

        vehicleList.setAll(
                vehicles
        );

        updateStatistics(
                vehicles
        );
    }

    // =====================================================
    // SEARCH
    // =====================================================

    private void searchVehicles(
            String text
    ) {

        List<Vehicle> result =
                controller.searchVehicles(
                        text
                );

        vehicleList.setAll(
                result
        );
    }

    // =====================================================
    // FILTER
    // =====================================================

    private void filterVehicles(
            String status
    ) {

        List<Vehicle> result =
                controller.getVehiclesByStatus(
                        status
                );

        vehicleList.setAll(
                result
        );
    }

    // =====================================================
    // STATISTICS
    // =====================================================

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
                    vehicle.getStatus() != null &&
                    vehicle.getStatus()
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

    // =====================================================
    // ADD VEHICLE
    // =====================================================

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

        ComboBox<String> vehicleType =
                new ComboBox<>();

        vehicleType.getItems().addAll(
                "Car",
                "Bike",
                "Scooter",
                "SUV",
                "Truck",
                "Other"
        );

        vehicleType.setValue(
                "Car"
        );

        vehicleType.setPrefWidth(
                280
        );

        ComboBox<String> fuelType =
                new ComboBox<>();

        fuelType.getItems().addAll(
                "Petrol",
                "Diesel",
                "CNG",
                "Electric",
                "Hybrid"
        );

        fuelType.setValue(
                "Petrol"
        );

        fuelType.setPrefWidth(
                280
        );

        TextField year =
                field();

        ComboBox<String> status =
                new ComboBox<>();

        status.getItems().addAll(
                "Active",
                "Inactive"
        );

        status.setValue(
                "Active"
        );

        status.setPrefWidth(
                280
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
                .setContent(
                        form
                );

        dialog.setResultConverter(
                button -> {

                    if (
                            button == saveButton
                    ) {

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
                                vehicleType.getValue(),
                                fuelType.getValue(),
                                year
                                        .getText()
                                        .trim(),
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
                        vehicle -> {

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
                        }
                );
    }

    // =====================================================
    // FORM
    // =====================================================

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

        field.setPrefWidth(
                280
        );

        field.setPrefHeight(
                40
        );

        field.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-font-size: 15px;"
        );

        return field;
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

    // =====================================================
    // DETAILS
    // =====================================================

    private void showDetails(
            Vehicle vehicle
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Vehicle Details"
        );

        alert.setHeaderText(
                safe(
                        vehicle.getVehicleNumber()
                )
        );

        alert.setContentText(
                "Vehicle ID: " +
                safe(
                        vehicle.getVehicleId()
                ) +

                "\n\nCustomer ID: " +
                safe(
                        vehicle.getCustomerId()
                ) +

                "\n\nOwner: " +
                safe(
                        vehicle.getOwnerName()
                ) +

                "\n\nBrand: " +
                safe(
                        vehicle.getBrand()
                ) +

                "\n\nModel: " +
                safe(
                        vehicle.getModel()
                ) +

                "\n\nVehicle Type: " +
                safe(
                        vehicle.getVehicleType()
                ) +

                "\n\nFuel Type: " +
                safe(
                        vehicle.getFuelType()
                ) +

                "\n\nYear: " +
                safe(
                        vehicle.getYear()
                ) +

                "\n\nStatus: " +
                safe(
                        vehicle.getStatus()
                )
        );

        alert.showAndWait();
    }

    // =====================================================
    // SAFE
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
    // ERROR VIEW
    // =====================================================

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

    // =====================================================
    // ALERTS
    // =====================================================

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
        alert.setContentText(message);

        alert.showAndWait();
    }
}
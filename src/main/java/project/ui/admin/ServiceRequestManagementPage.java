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

import project.controller.admin.ServiceRequestController;
import project.controller.admin.MechanicController;
import project.firebase.FirebaseConfig;
import project.model.ServiceRequest;
import project.model.Mechanic;

import java.util.List;

public class ServiceRequestManagementPage extends AdminSectionPage {

    // =========================================================
    // AQUA MIST THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
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

    private ServiceRequestController controller;
    private MechanicController mechanicController;

    private TableView<ServiceRequest> requestTable;

    private final ObservableList<ServiceRequest> requestList =
            FXCollections.observableArrayList();

    private Label totalLabel;
    private Label pendingLabel;
    private Label acceptedLabel;
    private Label progressLabel;
    private Label completedLabel;

    // =========================================================
    // VIEW
    // =========================================================

    @Override
    public VBox getView() {

        VBox root = new VBox(20);

        root.setPadding(
                new Insets(28, 30, 30, 30)
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
                    new ServiceRequestController(
                            firestore
                    );

            mechanicController =
                    new MechanicController(
                            firestore
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return createErrorView(
                    "Unable to connect to Firestore."
            );
        }

        VBox header = createHeader();

        HBox statistics = createStatistics();

        HBox toolbar = createToolbar();

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

        loadRequests();

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header = new VBox(6);

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
                        "Monitor service requests and assign mechanics."
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

        HBox box = new HBox(14);

        totalLabel = createValueLabel(BLUE);
        pendingLabel = createValueLabel(ORANGE);
        acceptedLabel = createValueLabel(BLUE);
        progressLabel = createValueLabel(ORANGE);
        completedLabel = createValueLabel(GREEN);

        VBox total =
                createStatCard(
                        "Total",
                        "All requests",
                        totalLabel,
                        BLUE
                );

        VBox pending =
                createStatCard(
                        "Pending",
                        "Waiting for action",
                        pendingLabel,
                        ORANGE
                );

        VBox accepted =
                createStatCard(
                        "Accepted",
                        "Mechanic assigned",
                        acceptedLabel,
                        BLUE
                );

        VBox progress =
                createStatCard(
                        "In Progress",
                        "Service underway",
                        progressLabel,
                        ORANGE
                );

        VBox completed =
                createStatCard(
                        "Completed",
                        "Service completed",
                        completedLabel,
                        GREEN
                );

        for (
                VBox card :
                new VBox[]{
                        total,
                        pending,
                        accepted,
                        progress,
                        completed
                }
        ) {

            HBox.setHgrow(
                    card,
                    Priority.ALWAYS
            );
        }

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
                        28
                )
        );

        return label;
    }

    private VBox createStatCard(
            String title,
            String subtitle,
            Label value,
            String color
    ) {

        VBox card =
                new VBox(7);

        card.setPadding(
                new Insets(
                        16,
                        18,
                        16,
                        18
                )
        );

        card.setMinHeight(112);

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

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web(HEADING)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
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
                titleLabel,
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
                "Search request, customer, vehicle, mechanic..."
        );

        search.setPrefWidth(420);
        search.setPrefHeight(44);

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
                "-fx-font-size: 16px;" +
                "-fx-padding: 0 14 0 14;"
        );

        ComboBox<String> status =
                new ComboBox<>();

        status.getItems().addAll(
                "All",
                "Pending",
                "Accepted",
                "In Progress",
                "Completed",
                "Cancelled"
        );

        status.setValue("All");

        status.setPrefWidth(150);
        status.setPrefHeight(44);

        status.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 15px;"
        );

        Button refresh =
                createButton(
                        "Refresh",
                        BLUE
                );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        search.textProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            if (
                                    newValue == null ||
                                    newValue.isBlank()
                            ) {

                                loadRequests();

                            } else {

                                searchRequests(
                                        newValue.trim()
                                );
                            }
                        }
                );

        status.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            if (
                                    newValue == null ||
                                    newValue.equals("All")
                            ) {

                                loadRequests();

                            } else {

                                filterRequests(
                                        newValue
                                );
                            }
                        }
                );

        refresh.setOnAction(
                event ->
                        loadRequests()
        );

        toolbar.getChildren().addAll(
                search,
                status,
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

        button.setPrefHeight(42);

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

                    String hover =
                            color.equals(ORANGE)
                                    ? ORANGE_HOVER
                                    : "#1D4ED8";

                    button.setStyle(
                            "-fx-background-color: " +
                            hover +
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
    // TABLE
    // =========================================================

    private VBox createTableCard() {

        VBox card =
                new VBox(14);

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
                        "Service Request Records"
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
                        "Customer roadside assistance requests"
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

        Region spacer = new Region();

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

        requestTable =
                new TableView<>();

        requestTable.setItems(
                requestList
        );

        requestTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        requestTable.setFixedCellSize(58);

        requestTable.setPrefHeight(430);

        requestTable.setStyle(
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

        TableColumn<ServiceRequest, String> requestId =
                new TableColumn<>(
                        "REQUEST ID"
                );

        TableColumn<ServiceRequest, String> customer =
                new TableColumn<>(
                        "CUSTOMER"
                );

        TableColumn<ServiceRequest, String> vehicle =
                new TableColumn<>(
                        "VEHICLE"
                );

        TableColumn<ServiceRequest, String> mechanic =
                new TableColumn<>(
                        "MECHANIC"
                );

        TableColumn<ServiceRequest, String> service =
                new TableColumn<>(
                        "SERVICE"
                );

        TableColumn<ServiceRequest, String> location =
                new TableColumn<>(
                        "LOCATION"
                );

        TableColumn<ServiceRequest, String> status =
                new TableColumn<>(
                        "STATUS"
                );

        requestId.setCellValueFactory(
                new PropertyValueFactory<>(
                        "requestId"
                )
        );

        customer.setCellValueFactory(
                new PropertyValueFactory<>(
                        "customerName"
                )
        );

        vehicle.setCellValueFactory(
                new PropertyValueFactory<>(
                        "vehicleNumber"
                )
        );

        mechanic.setCellValueFactory(
                new PropertyValueFactory<>(
                        "mechanicName"
                )
        );

        service.setCellValueFactory(
                new PropertyValueFactory<>(
                        "serviceType"
                )
        );

        location.setCellValueFactory(
                new PropertyValueFactory<>(
                        "location"
                )
        );

        status.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );

        requestId.setMinWidth(145);
        customer.setMinWidth(145);
        vehicle.setMinWidth(130);
        mechanic.setMinWidth(140);
        service.setMinWidth(135);
        location.setMinWidth(150);
        status.setMinWidth(120);

        requestId.setCellFactory(
                column ->
                        createNormalCell(13)
        );

        vehicle.setCellFactory(
                column ->
                        createNormalCell(15)
        );

        service.setCellFactory(
                column ->
                        createNormalCell(15)
        );

        location.setCellFactory(
                column ->
                        createNormalCell(14)
        );

        customer.setCellFactory(
                column ->
                        createBoldCell()
        );

        mechanic.setCellFactory(
                column ->
                        createMechanicCell()
        );

        status.setCellFactory(
                column ->
                        createStatusCell()
        );

        requestTable.getColumns().addAll(
                requestId,
                customer,
                vehicle,
                mechanic,
                service,
                location,
                status
        );

        requestTable.setPlaceholder(
                createEmptyState()
        );

        requestTable.setRowFactory(
                tableView -> {

                    TableRow<ServiceRequest> row =
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
                requestTable,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                header,
                requestTable
        );

        return card;
    }

    // =========================================================
    // NORMAL CELL
    // =========================================================

    private TableCell<ServiceRequest, String>
    createNormalCell(
            int size
    ) {

        return new TableCell<ServiceRequest, String>() {

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

    // =========================================================
    // BOLD CELL
    // =========================================================

    private TableCell<ServiceRequest, String>
    createBoldCell() {

        return new TableCell<ServiceRequest, String>() {

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
                                    15
                            )
                    );
                }
            }
        };
    }

    // =========================================================
    // MECHANIC CELL
    // =========================================================

    private TableCell<ServiceRequest, String>
    createMechanicCell() {

        return new TableCell<ServiceRequest, String>() {

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
                        item == null ||
                        item.isBlank()
                ) {

                    setText(
                            "Unassigned"
                    );

                    setTextFill(
                            Color.web(ORANGE)
                    );

                    setFont(
                            Font.font(
                                    "Arial",
                                    FontWeight.BOLD,
                                    14
                            )
                    );

                } else {

                    setText(item);

                    setTextFill(
                            Color.web(BLUE)
                    );

                    setFont(
                            Font.font(
                                    "Arial",
                                    FontWeight.BOLD,
                                    14
                            )
                    );
                }
            }
        };
    }

    // =========================================================
    // STATUS CELL
    // =========================================================

    private TableCell<ServiceRequest, String> createStatusCell() {

    return new TableCell<ServiceRequest, String>() {

        private final ComboBox<String> statusBox =
                new ComboBox<>();

        {
            statusBox.getItems().addAll(
                    "Pending",
                    "Accepted",
                    "In Progress",
                    "Completed",
                    "Cancelled"
            );

            statusBox.setPrefWidth(125);

            statusBox.setStyle(
                    "-fx-font-size: 14px;" +
                    "-fx-background-radius: 15;" +
                    "-fx-border-radius: 15;"
            );

            statusBox.setOnAction(event -> {

                ServiceRequest request =
                        getTableView()
                                .getItems()
                                .get(getIndex());

                String newStatus =
                        statusBox.getValue();

                if (request == null ||
                        newStatus == null) {
                    return;
                }

                boolean success =
                        controller.updateStatus(
                                request.getRequestId(),
                                newStatus
                        );

                if (success) {

                    request.setStatus(newStatus);

                    requestTable.refresh();

                    loadRequests();

                    showInfo(
                            "Status Updated",
                            "Request status updated to "
                                    + newStatus
                    );

                } else {

                    showError(
                            "Update Failed",
                            "Unable to update request status."
                    );

                    statusBox.setValue(
                            request.getStatus()
                    );
                }
            });
        }

        @Override
        protected void updateItem(
                String item,
                boolean empty
        ) {

            super.updateItem(
                    item,
                    empty
            );

            if (empty || item == null) {

                setGraphic(null);

            } else {

                statusBox.setValue(item);

                setGraphic(statusBox);
            }
        }
    };
}

    // =========================================================
    // LOAD
    // =========================================================

    private void loadRequests() {

        List<ServiceRequest> requests =
                controller.getAllRequests();

        requestList.setAll(
                requests
        );

        updateStatistics(
                requests
        );
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchRequests(
            String text
    ) {

        List<ServiceRequest> result =
                controller.searchRequests(
                        text
                );

        requestList.setAll(
                result
        );
    }

    // =========================================================
    // FILTER
    // =========================================================

    private void filterRequests(
            String status
    ) {

        List<ServiceRequest> result =
                controller.getRequestsByStatus(
                        status
                );

        requestList.setAll(
                result
        );
    }

    // =========================================================
    // STATISTICS
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
                    request.getStatus();

            if (status == null) {
                continue;
            }

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
                String.valueOf(pending)
        );

        acceptedLabel.setText(
                String.valueOf(accepted)
        );

        progressLabel.setText(
                String.valueOf(progress)
        );

        completedLabel.setText(
                String.valueOf(completed)
        );
    }

    // =========================================================
    // DETAILS
    // =========================================================

    private void showDetails(
            ServiceRequest request
    ) {

        Dialog<Void> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Service Request Details"
        );

        dialog.setHeaderText(
                "Request: " +
                safe(
                        request.getRequestId()
                )
        );

        ButtonType close =
                new ButtonType(
                        "Close",
                        ButtonBar.ButtonData.CANCEL_CLOSE
                );

        ButtonType assign =
                new ButtonType(
                        "Assign Mechanic",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        assign,
                        close
                );

        VBox content =
                new VBox(12);

        content.setPadding(
                new Insets(18)
        );

        content.getChildren().addAll(
                detail(
                        "Customer",
                        request.getCustomerName()
                ),
                detail(
                        "Vehicle",
                        request.getVehicleNumber()
                ),
                detail(
                        "Service",
                        request.getServiceType()
                ),
                detail(
                        "Location",
                        request.getLocation()
                ),
                detail(
                        "Description",
                        request.getDescription()
                ),
                detail(
                        "Mechanic",
                        request.getMechanicName()
                ),
                detail(
                        "Status",
                        request.getStatus()
                )
        );

        dialog.getDialogPane()
                .setContent(
                        content
                );

        dialog.setResultConverter(
                button -> {

                    if (
                            button == assign
                    ) {

                        assignMechanic(
                                request
                        );
                    }

                    return null;
                }
        );

        dialog.showAndWait();
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private HBox detail(
            String title,
            String value
    ) {

        HBox row =
                new HBox(10);

        Label label =
                new Label(
                        title + ":"
                );

        label.setMinWidth(110);

        label.setTextFill(
                Color.web(HEADING)
        );

        label.setFont(
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
                Color.web(TEXT)
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        14
                )
                );

        valueLabel.setWrapText(true);

        row.getChildren().addAll(
                label,
                valueLabel
        );

        return row;
    }

    // =========================================================
    // ASSIGN MECHANIC
    // =========================================================

    private void assignMechanic(
            ServiceRequest request
    ) {

        List<Mechanic> mechanics =
                mechanicController
                        .getAllMechanics();

        if (
                mechanics.isEmpty()
        ) {

            showError(
                    "No Mechanics",
                    "No mechanics are available."
            );

            return;
        }

        Dialog<Mechanic> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Assign Mechanic"
        );

        dialog.setHeaderText(
                "Select mechanic for this request"
        );

        ButtonType assignButton =
                new ButtonType(
                        "Assign",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        assignButton,
                        ButtonType.CANCEL
                );

        ComboBox<Mechanic> mechanicBox =
                new ComboBox<>();

        mechanicBox.getItems().addAll(
                mechanics
        );

        mechanicBox.setPrefWidth(320);

        mechanicBox.setCellFactory(
                list ->
                        new ListCell<Mechanic>() {

                            @Override
                            protected void updateItem(
                                    Mechanic item,
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

                                    setText(
                                            item.getName()
                                            +
                                            " - "
                                            +
                                            safe(
                                                    item.getSpecialization()
                                            )
                                    );
                                }
                            }
                        }
        );

        mechanicBox.setButtonCell(
                new ListCell<Mechanic>() {

                    @Override
                    protected void updateItem(
                            Mechanic item,
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

                            setText(
                                    item.getName()
                            );
                        }
                    }
                }
        );

        VBox box =
                new VBox(12);

        box.setPadding(
                new Insets(20)
        );

        Label label =
                new Label(
                        "Available Mechanics"
                );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        label.setTextFill(
                Color.web(HEADING)
        );

        box.getChildren().addAll(
                label,
                mechanicBox
        );

        dialog.getDialogPane()
                .setContent(box);

        dialog.setResultConverter(
                button -> {

                    if (
                            button == assignButton
                    ) {

                        return mechanicBox.getValue();
                    }

                    return null;
                }
        );

        dialog.showAndWait()
                .ifPresent(
                        mechanic -> {

                            boolean success =
                                    controller
                                            .assignMechanic(
                                                    request.getRequestId(),
                                                    mechanic.getMechanicId(),
                                                    mechanic.getName()
                                            );

                            if (success) {

                                loadRequests();

                                showInfo(
                                        "Success",
                                        "Mechanic assigned successfully."
                                );

                            } else {

                                showError(
                                        "Failed",
                                        "Mechanic could not be assigned."
                                );
                            }
                        }
                );
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

        Label icon =
                new Label("⚙");

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
                        "No service requests found"
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
                        "Customer requests will appear here."
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
                new Label(message);

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
        alert.setContentText(message);

        alert.showAndWait();
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
}
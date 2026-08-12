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

import project.controller.admin.MechanicController;
import project.firebase.FirebaseConfig;
import project.model.Mechanic;

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
    // FIELDS
    // =========================================================

    private MechanicController controller;

    private TableView<Mechanic> mechanicTable;

    private final ObservableList<Mechanic> mechanicList =
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
                    new MechanicController(
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

        loadMechanics();

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
                        "Mechanics"
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
                        "Manage RoadGuardian mechanics and their service availability."
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
                        "Total Mechanics",
                        "All registered mechanics",
                        totalLabel,
                        BLUE,
                        "TOTAL"
                );

        VBox active =
                createStatCard(
                        "Active Mechanics",
                        "Currently available",
                        activeLabel,
                        GREEN,
                        "ACTIVE"
                );

        VBox inactive =
                createStatCard(
                        "Inactive Mechanics",
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
                new Label(
                        "0"
                );

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
                new Label(
                        title
                );

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
                new Label(
                        tagText
                );

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
                new Label(
                        subtitle
                );

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
                "Search by name, email, phone, city or specialization..."
        );

        search.setPrefWidth(
                420
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

        ComboBox<String> filter =
                new ComboBox<>();

        filter.getItems().addAll(
                "All",
                "Active",
                "Inactive"
        );

        filter.setValue(
                "All"
        );

        filter.setPrefWidth(
                130
        );

        filter.setPrefHeight(
                44
        );

        filter.setStyle(
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
                        "+ Add Mechanic",
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

                                loadMechanics();

                            } else {

                                searchMechanics(
                                        newValue.trim()
                                );
                            }
                        }
                );

        filter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            if (
                                    newValue == null ||
                                    newValue.equals("All")
                            ) {

                                loadMechanics();

                            } else {

                                filterMechanics(
                                        newValue
                                );
                            }
                        }
                );

        refresh.setOnAction(
                event ->
                        loadMechanics()
        );

        add.setOnAction(
                event ->
                        showAddDialog()
        );

        toolbar.getChildren().addAll(
                search,
                filter,
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
                new Button(
                        text
                );

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

                    if (
                            color.equals(
                                    ORANGE
                            )
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
                new Insets(
                        21
                )
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
                        "Mechanic Records"
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
                        "Registered mechanics and their current service status"
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

        mechanicTable =
                new TableView<>();

        mechanicTable.setItems(
                mechanicList
        );

        mechanicTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        mechanicTable.setFixedCellSize(
                58
        );

        mechanicTable.setPrefHeight(
                430
        );

        mechanicTable.setStyle(
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

        TableColumn<Mechanic, String> idColumn =
                new TableColumn<>(
                        "MECHANIC ID"
                );

        TableColumn<Mechanic, String> nameColumn =
                new TableColumn<>(
                        "MECHANIC"
                );

        TableColumn<Mechanic, String> phoneColumn =
                new TableColumn<>(
                        "PHONE"
                );

        TableColumn<Mechanic, String> emailColumn =
                new TableColumn<>(
                        "EMAIL"
                );

        TableColumn<Mechanic, String> specializationColumn =
                new TableColumn<>(
                        "SPECIALIZATION"
                );

        TableColumn<Mechanic, String> cityColumn =
                new TableColumn<>(
                        "CITY"
                );

        TableColumn<Mechanic, String> experienceColumn =
                new TableColumn<>(
                        "EXPERIENCE"
                );

        TableColumn<Mechanic, String> statusColumn =
                new TableColumn<>(
                        "STATUS"
                );

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "mechanicId"
                )
        );

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "name"
                )
        );

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "phone"
                )
        );

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "email"
                )
        );

        specializationColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "specialization"
                )
        );

        cityColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "city"
                )
        );

        experienceColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "experience"
                )
        );

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );

        idColumn.setMinWidth(150);
        nameColumn.setMinWidth(150);
        phoneColumn.setMinWidth(130);
        emailColumn.setMinWidth(180);
        specializationColumn.setMinWidth(150);
        cityColumn.setMinWidth(100);
        experienceColumn.setMinWidth(100);
        statusColumn.setMinWidth(110);

        // =====================================================
        // TEXT CELLS
        // =====================================================

        idColumn.setCellFactory(
                column ->
                        createNormalCell(
                                14
                        )
        );

        phoneColumn.setCellFactory(
                column ->
                        createNormalCell(
                                15
                        )
        );

        emailColumn.setCellFactory(
                column ->
                        createNormalCell(
                                15
                        )
        );

        specializationColumn.setCellFactory(
                column ->
                        createNormalCell(
                                15
                        )
        );

        cityColumn.setCellFactory(
                column ->
                        createNormalCell(
                                15
                        )
        );

        experienceColumn.setCellFactory(
                column ->
                        createNormalCell(
                                15
                        )
        );

        // =====================================================
        // NAME CELL
        // =====================================================

        nameColumn.setCellFactory(
                column ->
                        new TableCell<Mechanic, String>() {

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

                                    setText(
                                            null
                                    );

                                } else {

                                    setText(
                                            item
                                    );

                                    setTextFill(
                                            Color.web(
                                                    HEADING
                                            )
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
        // STATUS CELL
        // =====================================================

        statusColumn.setCellFactory(
                column ->
                        new TableCell<Mechanic, String>() {

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

                                    setText(
                                            null
                                    );

                                    setGraphic(
                                            null
                                    );

                                    return;
                                }

                                Label badge =
                                        new Label(
                                                item
                                        );

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

                                setGraphic(
                                        badge
                                );
                            }
                        }
        );

        mechanicTable.getColumns().addAll(
                idColumn,
                nameColumn,
                phoneColumn,
                emailColumn,
                specializationColumn,
                cityColumn,
                experienceColumn,
                statusColumn
        );

        mechanicTable.setPlaceholder(
                createEmptyState()
        );

        // =====================================================
        // DOUBLE CLICK
        // =====================================================

        mechanicTable.setRowFactory(
                tableView -> {

                    TableRow<Mechanic> row =
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
                mechanicTable,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                header,
                mechanicTable
        );

        return card;
    }

    // =========================================================
    // NORMAL CELL
    // =========================================================

    private TableCell<Mechanic, String>
    createNormalCell(
            int size
    ) {

        return new TableCell<Mechanic, String>() {

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

                    setText(
                            null
                    );

                } else {

                    setText(
                            item
                    );

                    setTextFill(
                            Color.web(
                                    TEXT
                            )
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
    // EMPTY STATE
    // =========================================================

    private VBox createEmptyState() {

        VBox box =
                new VBox(8);

        box.setAlignment(
                Pos.CENTER
        );

        Label icon =
                new Label(
                        "⚒"
                );

        icon.setTextFill(
                Color.web(
                        BLUE
                )
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
                        "No mechanics found"
                );

        title.setTextFill(
                Color.web(
                        HEADING
                )
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
                        "Registered mechanics will appear here."
                );

        subtitle.setTextFill(
                Color.web(
                        TEXT
                )
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
    // LOAD
    // =========================================================

    private void loadMechanics() {

        List<Mechanic> mechanics =
                controller.getAllMechanics();

        mechanicList.setAll(
                mechanics
        );

        updateStatistics(
                mechanics
        );
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchMechanics(
            String text
    ) {

        List<Mechanic> result =
                controller.searchMechanics(
                        text
                );

        mechanicList.setAll(
                result
        );
    }

    // =========================================================
    // FILTER
    // =========================================================

    private void filterMechanics(
            String status
    ) {

        List<Mechanic> result =
                controller.getMechanicsByStatus(
                        status
                );

        mechanicList.setAll(
                result
        );
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private void updateStatistics(
            List<Mechanic> mechanics
    ) {

        int active = 0;
        int inactive = 0;

        for (
                Mechanic mechanic :
                mechanics
        ) {

            if (
                    mechanic.getStatus() != null &&
                    mechanic.getStatus()
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
                        mechanics.size()
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

    // =========================================================
    // ADD DIALOG
    // =========================================================

    private void showAddDialog() {

        Dialog<Mechanic> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Add Mechanic"
        );

        dialog.setHeaderText(
                "Create New Mechanic"
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

        TextField name =
                field();

        TextField email =
                field();

        TextField phone =
                field();

        TextField address =
                field();

        TextField city =
                field();

        TextField specialization =
                field();

        TextField experience =
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
                "Name",
                name,
                0
        );

        addFormRow(
                form,
                "Email",
                email,
                1
        );

        addFormRow(
                form,
                "Phone",
                phone,
                2
        );

        addFormRow(
                form,
                "Address",
                address,
                3
        );

        addFormRow(
                form,
                "City",
                city,
                4
        );

        addFormRow(
                form,
                "Specialization",
                specialization,
                5
        );

        addFormRow(
                form,
                "Experience",
                experience,
                6
        );

        addFormRow(
                form,
                "Status",
                status,
                7
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
                    }

                    return null;
                }
        );

        dialog.showAndWait()
                .ifPresent(
                        mechanic -> {

                            if (
                                    controller.addMechanic(
                                            mechanic
                                    )
                            ) {

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
                        }
                );
    }

    // =========================================================
    // FORM
    // =========================================================

    private GridPane createForm() {

        GridPane form =
                new GridPane();

        form.setHgap(
                15
        );

        form.setVgap(
                13
        );

        form.setPadding(
                new Insets(
                        20
                )
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
                Color.web(
                        HEADING
                )
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

    // =========================================================
    // DETAILS
    // =========================================================

    private void showDetails(
            Mechanic mechanic
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Mechanic Details"
        );

        alert.setHeaderText(
                safe(
                        mechanic.getName()
                )
        );

        alert.setContentText(
                "Mechanic ID: " +
                safe(
                        mechanic.getMechanicId()
                ) +
                "\n\n" +

                "Email: " +
                safe(
                        mechanic.getEmail()
                ) +
                "\n\n" +

                "Phone: " +
                safe(
                        mechanic.getPhone()
                ) +
                "\n\n" +

                "Address: " +
                safe(
                        mechanic.getAddress()
                ) +
                "\n\n" +

                "City: " +
                safe(
                        mechanic.getCity()
                ) +
                "\n\n" +

                "Specialization: " +
                safe(
                        mechanic.getSpecialization()
                ) +
                "\n\n" +

                "Experience: " +
                safe(
                        mechanic.getExperience()
                ) +
                "\n\n" +

                "Status: " +
                safe(
                        mechanic.getStatus()
                )
        );

        alert.showAndWait();
    }

    // =========================================================
    // SAFE STRING
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
                new Insets(
                        30
                )
        );

        Label icon =
                new Label(
                        "!"
                );

        icon.setTextFill(
                Color.web(
                        RED
                )
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
                        "Mechanic Management Error"
                );

        title.setTextFill(
                Color.web(
                        HEADING
                )
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
                Color.web(
                        TEXT
                )
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
                message
        );

        alert.showAndWait();
    }
}
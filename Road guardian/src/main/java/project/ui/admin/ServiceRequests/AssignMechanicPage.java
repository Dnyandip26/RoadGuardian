package project.ui.admin.ServiceRequests;

import java.util.List;

import com.google.cloud.firestore.Firestore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.controller.admin.MechanicController;
import project.controller.admin.ServiceRequestController;
import project.firebase.FirebaseConfig;
import project.model.Mechanic;
import project.model.ServiceRequest;

public class AssignMechanicPage {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

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

    private final ServiceRequest request;
    private final Runnable backAction;

    // =========================================================
    // CONTROLLERS
    // =========================================================

    private ServiceRequestController requestController;

    private MechanicController mechanicController;

    // =========================================================
    // UI
    // =========================================================

    private ComboBox<Mechanic> mechanicComboBox;

    private Label selectedMechanicLabel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AssignMechanicPage(
            ServiceRequest request,
            Runnable backAction
    ) {
        this.request = request;
        this.backAction = backAction;
    }

    public AssignMechanicPage(ServiceRequest request) {
        this(request, () -> {});
    }

    // =========================================================
    // GET VIEW
    // =========================================================

    public VBox getView() {

        VBox root =
                new VBox(18);

        root.setPadding(
                new Insets(
                        24,
                        26,
                        24,
                        26
                )
        );

        root.setPrefWidth(560);

        root.setStyle(
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

            requestController =
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

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        VBox header =
                createHeader();

        // -----------------------------------------------------
        // REQUEST CARD
        // -----------------------------------------------------

        VBox requestCard =
                createRequestCard();

        // -----------------------------------------------------
        // MECHANIC CARD
        // -----------------------------------------------------

        VBox mechanicCard =
                createMechanicCard();

        // -----------------------------------------------------
        // ACTIONS
        // -----------------------------------------------------

        HBox actions =
                createActions();

        root.getChildren().addAll(
                header,
                requestCard,
                mechanicCard,
                actions
        );

        // -----------------------------------------------------
        // LOAD MECHANICS
        // -----------------------------------------------------

        loadMechanics();

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox box =
                new VBox(5);

        Label title =
                new Label(
                        "Assign Mechanic"
                );

        title.setTextFill(
                Color.web(HEADING)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        Label subtitle =
                new Label(
                        "Assign a mechanic to this roadside service request."
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
                title,
                subtitle
        );

        return box;
    }

    // =========================================================
    // REQUEST CARD
    // =========================================================

    private VBox createRequestCard() {

        VBox card =
                createCard();

        Label heading =
                new Label(
                        "Request Information"
                );

        heading.setTextFill(
                Color.web(HEADING)
        );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        VBox details =
                new VBox(9);

        details.getChildren().addAll(

                createDetailRow(
                        "Request ID",
                        request.getRequestId()
                ),

                createDetailRow(
                        "Customer",
                        request.getCustomerName()
                ),

                createDetailRow(
                        "Vehicle",
                        request.getVehicleNumber()
                ),

                createDetailRow(
                        "Service",
                        request.getServiceType()
                ),

                createDetailRow(
                        "Location",
                        request.getLocation()
                )
        );

        card.getChildren().addAll(
                heading,
                details
        );

        return card;
    }

    // =========================================================
    // MECHANIC CARD
    // =========================================================

    private VBox createMechanicCard() {

        VBox card =
                createCard();

        Label heading =
                new Label(
                        "Select Mechanic"
                );

        heading.setTextFill(
                Color.web(HEADING)
        );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        Label instruction =
                new Label(
                        "Choose an available mechanic from the list."
                );

        instruction.setTextFill(
                Color.web(TEXT)
        );

        instruction.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        mechanicComboBox =
                new ComboBox<>();

        mechanicComboBox.setPromptText(
                "Select mechanic"
        );

        mechanicComboBox.setPrefHeight(
                44
        );

        mechanicComboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        styleComboBox(
                mechanicComboBox
        );

        // -----------------------------------------------------
        // DROPDOWN CELL
        // -----------------------------------------------------

        mechanicComboBox.setCellFactory(
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
                                            safe(
                                                    item.getName()
                                            )
                                            +
                                            "  •  "
                                            +
                                            safe(
                                                    item.getSpecialization()
                                            )
                                    );
                                }
                            }
                        }
        );

        // -----------------------------------------------------
        // SELECTED CELL
        // -----------------------------------------------------

        mechanicComboBox.setButtonCell(
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

                            setText(
                                    "Select mechanic"
                            );

                        } else {

                            setText(
                                    safe(
                                            item.getName()
                                    )
                            );
                        }
                    }
                }
        );

        // -----------------------------------------------------
        // SELECTED MECHANIC LABEL
        // -----------------------------------------------------

        selectedMechanicLabel =
                new Label(
                        "No mechanic selected"
                );

        selectedMechanicLabel.setTextFill(
                Color.web(TEXT)
        );

        selectedMechanicLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        mechanicComboBox.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            if (
                                    newValue == null
                            ) {

                                selectedMechanicLabel.setText(
                                        "No mechanic selected"
                                );

                            } else {

                                selectedMechanicLabel.setText(
                                        "Selected: " +
                                        safe(
                                                newValue.getName()
                                        )
                                );

                                selectedMechanicLabel.setTextFill(
                                        Color.web(GREEN)
                                );
                            }
                        }
                );

        card.getChildren().addAll(
                heading,
                instruction,
                mechanicComboBox,
                selectedMechanicLabel
        );

        return card;
    }

    // =========================================================
    // ACTIONS
    // =========================================================

    private HBox createActions() {

        HBox box =
                new HBox(10);

        box.setAlignment(
                Pos.CENTER_RIGHT
        );

        // -----------------------------------------------------
        // CANCEL
        // -----------------------------------------------------

        Button cancel =
                new Button(
                        "Back"
                );

        styleSecondaryButton(
                cancel
        );

        cancel.setOnAction(
                event ->
                        backAction.run()
        );

        // -----------------------------------------------------
        // ASSIGN
        // -----------------------------------------------------

        Button assign =
                new Button(
                        "Assign Mechanic"
                );

        stylePrimaryButton(
                assign
        );

        assign.setOnAction(
                event ->
                        assignSelectedMechanic()
        );

        box.getChildren().addAll(
                cancel,
                assign
        );

        return box;
    }

    // =========================================================
    // LOAD MECHANICS
    // =========================================================

    private void loadMechanics() {

        try {

            List<Mechanic> mechanics =
                    mechanicController.getAllMechanics();

            mechanicComboBox
                    .getItems()
                    .clear();

            if (
                    mechanics == null ||
                    mechanics.isEmpty()
            ) {

                selectedMechanicLabel.setText(
                        "No mechanics available."
                );

                selectedMechanicLabel.setTextFill(
                        Color.web(RED)
                );

                return;
            }

            mechanicComboBox
                    .getItems()
                    .addAll(
                            mechanics
                    );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Unable to Load Mechanics",
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // ASSIGN SELECTED MECHANIC
    // =========================================================

    private void assignSelectedMechanic() {

        Mechanic mechanic =
                mechanicComboBox.getValue();

        if (
                mechanic == null
        ) {

            showError(
                    "No Mechanic Selected",
                    "Please select a mechanic first."
            );

            return;
        }

        try {

            boolean success =
                    requestController.assignMechanic(
                            request.getRequestId(),
                            mechanic.getMechanicId(),
                            mechanic.getName()
                    );

            if (success) {

                request.setMechanicName(
                        mechanic.getName()
                );

                showInfo(
                        "Success",
                        "Mechanic " +
                        safe(
                                mechanic.getName()
                        ) +
                        " assigned successfully."
                );
                backAction.run();

            } else {

                showError(
                        "Assignment Failed",
                        "The mechanic could not be assigned."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Assignment Failed",
                    e.getMessage()
            );
        }
    }


    // =========================================================
    // CARD
    // =========================================================

    private VBox createCard() {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(17)
        );

        card.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );

        return card;
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private HBox createDetailRow(
            String title,
            String value
    ) {

        HBox row =
                new HBox(14);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setMinWidth(
                90
        );

        titleLabel.setTextFill(
                Color.web(TEXT)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
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
    // PRIMARY BUTTON
    // =========================================================

    private void stylePrimaryButton(
            Button button
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
                        9,
                        17,
                        9,
                        17
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color: " +
                BLUE +
                ";" +
                "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                BLUE_HOVER +
                                ";" +
                                "-fx-background-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                BLUE +
                                ";" +
                                "-fx-background-radius: 8;"
                        )
        );
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private void styleSecondaryButton(
            Button button
    ) {

        button.setTextFill(
                Color.web(HEADING)
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
                        9,
                        17,
                        9,
                        17
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
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            ComboBox<?> combo
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
                        "Assign Mechanic Error"
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
                        14
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
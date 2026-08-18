package project.ui.admin.ServiceRequests;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import com.google.cloud.firestore.Firestore;

import project.controller.admin.ServiceRequestController;
import project.firebase.FirebaseConfig;
import project.model.ServiceRequest;

public class UpdateRequestStatusPage {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";

    private static final String BLUE = "#2563EB";
    private static final String BLUE_HOVER = "#1D4ED8";

    private static final String GREEN = "#16A34A";
    private static final String ORANGE = "#F97316";
    private static final String RED = "#DC2626";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    // =========================================================
    // DATA
    // =========================================================

    private final ServiceRequest request;
    private final Runnable backAction;

    private ServiceRequestController requestController;

    // =========================================================
    // UI
    // =========================================================

    private ComboBox<String> statusComboBox;

    private Label currentStatusLabel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public UpdateRequestStatusPage(
            ServiceRequest request,
            Runnable backAction
    ) {
        this.request = request;
        this.backAction = backAction;
    }

    public UpdateRequestStatusPage(ServiceRequest request) {
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
        // REQUEST INFORMATION
        // -----------------------------------------------------

        VBox requestCard =
                createRequestCard();

        // -----------------------------------------------------
        // STATUS CARD
        // -----------------------------------------------------

        VBox statusCard =
                createStatusCard();

        // -----------------------------------------------------
        // ACTIONS
        // -----------------------------------------------------

        HBox actions =
                createActions();

        root.getChildren().addAll(
                header,
                requestCard,
                statusCard,
                actions
        );

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
                        "Update Request Status"
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
                        "Change the current status of this service request."
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
                new VBox(10);

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
                new Separator(),
                details
        );

        return card;
    }

    // =========================================================
    // STATUS CARD
    // =========================================================

    private VBox createStatusCard() {

        VBox card =
                createCard();

        Label heading =
                new Label(
                        "Status Update"
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

        // -----------------------------------------------------
        // CURRENT STATUS
        // -----------------------------------------------------

        HBox currentRow =
                new HBox(12);

        currentRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label currentTitle =
                new Label(
                        "Current Status"
                );

        currentTitle.setMinWidth(
                110
        );

        currentTitle.setTextFill(
                Color.web(TEXT)
        );

        currentTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        currentStatusLabel =
                createStatusBadge(
                        safe(
                                request.getStatus()
                        )
                );

        currentRow.getChildren().addAll(
                currentTitle,
                currentStatusLabel
        );

        // -----------------------------------------------------
        // NEW STATUS
        // -----------------------------------------------------

        Label newStatusTitle =
                new Label(
                        "New Status"
                );

        newStatusTitle.setTextFill(
                Color.web(TEXT)
        );

        newStatusTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        statusComboBox =
                new ComboBox<>();

        statusComboBox.setPrefHeight(
                44
        );

        statusComboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        statusComboBox.getItems().addAll(
                "Pending",
                "Accepted",
                "In Progress",
                "Completed",
                "Cancelled"
        );

        statusComboBox.setPromptText(
                "Select new status"
        );

        styleComboBox(
                statusComboBox
        );

        // -----------------------------------------------------
        // STATUS CHANGE LISTENER
        // -----------------------------------------------------

        statusComboBox.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {

                            if (
                                    newValue != null
                            ) {

                                currentStatusLabel
                                        .setText(
                                                "Change to: " +
                                                newValue
                                        );

                                currentStatusLabel
                                        .setTextFill(
                                                Color.web(
                                                        getStatusColor(
                                                                newValue
                                                        )
                                                )
                                        );
                            }
                        }
                );

        card.getChildren().addAll(
                heading,
                new Separator(),
                currentRow,
                newStatusTitle,
                statusComboBox
        );

        return card;
    }

    // =========================================================
    // ACTION BUTTONS
    // =========================================================

    private HBox createActions() {

        HBox box =
                new HBox(10);

        box.setAlignment(
                Pos.CENTER_RIGHT
        );

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

        Button update =
                new Button(
                        "Update Status"
                );

        stylePrimaryButton(
                update
        );

        update.setOnAction(
                event ->
                        updateStatus()
        );

        box.getChildren().addAll(
                cancel,
                update
        );

        return box;
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    private void updateStatus() {

        String newStatus =
                statusComboBox.getValue();

        if (
                newStatus == null ||
                newStatus.isBlank()
        ) {

            showError(
                    "No Status Selected",
                    "Please select a new status."
            );

            return;
        }

        String oldStatus =
                safe(
                        request.getStatus()
                );

        if (
                newStatus.equalsIgnoreCase(
                        oldStatus
                )
        ) {

            showError(
                    "Same Status",
                    "Please select a different status."
            );

            return;
        }

        try {

            boolean success =
                    requestController.updateStatus(
                            request.getRequestId(),
                            newStatus
                    );

            if (success) {

                // Update local object too
                request.setStatus(
                        newStatus
                );

                showInfo(
                        "Status Updated",
                        "Request " +
                        safe(
                                request.getRequestId()
                        ) +
                        " status changed to " +
                        newStatus +
                        "."
                );
                backAction.run();

            } else {

                showError(
                        "Update Failed",
                        "Unable to update the request status."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Update Failed",
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // STATUS COLOR
    // =========================================================

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
    // STATUS BADGE
    // =========================================================

    private Label createStatusBadge(
            String status
    ) {

        Label badge =
                new Label(
                        status
                );

        badge.setTextFill(
                Color.web(
                        getStatusColor(
                                status
                        )
                )
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
                        13,
                        7,
                        13
                )
        );

        badge.setStyle(
                "-fx-background-color: " +
                getStatusColor(
                        status
                ) +
                "18;" +
                "-fx-background-radius: 20;"
        );

        return badge;
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
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            ComboBox<?> comboBox
    ) {

        comboBox.setStyle(
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
    

        if (
                statusComboBox.getScene() == null
        ) {

            return;
        }

        javafx.stage.Window window =
                statusComboBox
                        .getScene()
                        .getWindow();

        if (
                window != null
        ) {

            window.hide();
        }
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

        Label title =
                new Label(
                        "Update Status Error"
                );

        title.setTextFill(
                Color.web(RED)
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
                title,
                text
        );

        return box;
    }
}
package project.ui.admin.Complaints;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.ui.admin.DashBoard.AdminSectionPage;

public class UpdateComplaintStatusPage extends AdminSectionPage {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String BLUE = "#2563EB";
    private static final String BLUE_HOVER = "#1D4ED8";

    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";

    private static final String GREEN = "#16A34A";
    private static final String RED = "#DC2626";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    // =========================================================
    // DATA
    // =========================================================

    private final ComplaintManagementPage.ComplaintData complaint;

    // =========================================================
    // CALLBACKS
    // =========================================================

    private final Runnable onBack;

    private final Runnable onUpdated;

    // =========================================================
    // FIELD
    // =========================================================

    private ComboBox<String> statusBox;

    private Label messageLabel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public UpdateComplaintStatusPage(
            ComplaintManagementPage.ComplaintData complaint,
            Runnable onBack,
            Runnable onUpdated
    ) {

        this.complaint = complaint;

        this.onBack = onBack;

        this.onUpdated = onUpdated;
    }

    // =========================================================
    // VIEW
    // =========================================================

    @Override
    public VBox getView() {

        VBox root =
                new VBox(20);

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

        // -----------------------------------------------------
        // BACK
        // -----------------------------------------------------

        Button backButton =
                new Button(
                        "← Back to Complaint Details"
                );

        styleOutlineButton(
                backButton
        );

        backButton.setOnAction(
                event -> goBack()
        );

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        VBox header =
                createHeader();

        // -----------------------------------------------------
        // FORM
        // -----------------------------------------------------

        VBox form =
                createForm();

        VBox.setVgrow(
                form,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                backButton,
                header,
                form
        );

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header =
                new VBox(6);

        Label title =
                new Label(
                        "Update Complaint Status"
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
                        "Change the current status of this complaint."
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

        Label complaintId =
                new Label(
                        "Complaint ID: " +
                                complaint.getId()
                );

        complaintId.setTextFill(
                Color.web(BLUE)
        );

        complaintId.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        header.getChildren().addAll(
                title,
                subtitle,
                complaintId
        );

        return header;
    }

    // =========================================================
    // FORM
    // =========================================================

    private VBox createForm() {

        VBox card =
                new VBox(18);

        card.setPadding(
                new Insets(24)
        );

        card.setMaxWidth(
                850
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

        Label title =
                new Label(
                        "Complaint Status"
                );

        title.setTextFill(
                Color.web(HEADING)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        // -----------------------------------------------------
        // COMPLAINT SUMMARY
        // -----------------------------------------------------

        VBox complaintBox =
                createSummaryBox();

        // -----------------------------------------------------
        // STATUS LABEL
        // -----------------------------------------------------

        Label statusLabel =
                new Label(
                        "Select New Status"
                );

        statusLabel.setTextFill(
                Color.web(HEADING)
        );

        statusLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        // -----------------------------------------------------
        // STATUS COMBO
        // -----------------------------------------------------

        statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "Pending",
                "In Progress",
                "Resolved",
                "Rejected"
        );

        statusBox.setValue(
                complaint.getStatus()
        );

        statusBox.setPrefHeight(
                46
        );

        statusBox.setMaxWidth(
                Double.MAX_VALUE
        );

        styleComboBox(
                statusBox
        );

        // -----------------------------------------------------
        // MESSAGE
        // -----------------------------------------------------

        messageLabel =
                new Label();

        messageLabel.setWrapText(
                true
        );

        messageLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        // -----------------------------------------------------
        // ACTIONS
        // -----------------------------------------------------

        HBox actions =
                new HBox(12);

        actions.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button cancel =
                new Button(
                        "Cancel"
                );

        styleOutlineButton(
                cancel
        );

        cancel.setOnAction(
                event -> goBack()
        );

        Button update =
                new Button(
                        "Update Status"
                );

        stylePrimaryButton(
                update
        );

        update.setOnAction(
                event -> updateStatus()
        );

        actions.getChildren().addAll(
                cancel,
                update
        );

        card.getChildren().addAll(
                title,
                complaintBox,
                statusLabel,
                statusBox,
                messageLabel,
                actions
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        card
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setPannable(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;"
        );

        VBox wrapper =
                new VBox();

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        wrapper.getChildren().add(
                scrollPane
        );

        return wrapper;
    }

    // =========================================================
    // SUMMARY BOX
    // =========================================================

    private VBox createSummaryBox() {

        VBox box =
                new VBox(10);

        box.setPadding(
                new Insets(15)
        );

        box.setStyle(
                "-fx-background-color: " +
                        SECONDARY +
                        ";" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;"
        );

        Label customer =
                new Label(
                        "Customer: " +
                                complaint.getCustomer()
                );

        Label subject =
                new Label(
                        "Subject: " +
                                complaint.getSubject()
                );

        Label priority =
                new Label(
                        "Priority: " +
                                complaint.getPriority()
                );

        Label currentStatus =
                new Label(
                        "Current Status: " +
                                complaint.getStatus()
                );

        styleSummaryLabel(
                customer
        );

        styleSummaryLabel(
                subject
        );

        styleSummaryLabel(
                priority
        );

        styleSummaryLabel(
                currentStatus
        );

        box.getChildren().addAll(
                customer,
                subject,
                priority,
                currentStatus
        );

        return box;
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    private void updateStatus() {

        String newStatus =
                statusBox.getValue();

        if (
                newStatus == null ||
                        newStatus.isBlank()
        ) {

            messageLabel.setText(
                    "Please select a complaint status."
            );

            messageLabel.setTextFill(
                    Color.web(RED)
            );

            return;
        }

        complaint.setStatus(
                newStatus
        );

        messageLabel.setText(
                "Complaint status updated successfully."
        );

        messageLabel.setTextFill(
                Color.web(GREEN)
        );

        if (onUpdated != null) {

            onUpdated.run();
        }
    }

    // =========================================================
    // BACK
    // =========================================================

    private void goBack() {

        if (onBack != null) {

            onBack.run();
        }
    }

    // =========================================================
    // SUMMARY LABEL
    // =========================================================

    private void styleSummaryLabel(
            Label label
    ) {

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

        label.setWrapText(
                true
        );
    }

    // =========================================================
    // COMBOBOX
    // =========================================================

    private void styleComboBox(
            ComboBox<String> combo
    ) {

        combo.setStyle(
                "-fx-background-color: " +
                        SECONDARY +
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
    // OUTLINE BUTTON
    // =========================================================

    private void styleOutlineButton(
            Button button
    ) {

        button.setTextFill(
                Color.web(BLUE)
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setPadding(
                new Insets(
                        9,
                        16,
                        9,
                        16
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
                        BLUE +
                        ";" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;"
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
                        14
                )
        );

        button.setPadding(
                new Insets(
                        10,
                        18,
                        10,
                        18
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color: " +
                        ORANGE +
                        ";" +
                        "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                        ORANGE_HOVER +
                                        ";" +
                                        "-fx-background-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                        ORANGE +
                                        ";" +
                                        "-fx-background-radius: 8;"
                        )
        );
    }
}
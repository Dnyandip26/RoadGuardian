package project.ui.mechanic;

import com.google.cloud.firestore.Firestore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import project.controller.mechanic.RequestsController;
import project.firebase.FirebaseConfig;
import project.model.ServiceRequest;
import project.util.Theme;

import java.time.Instant;

public class ActiveJobPage {

    // =========================================================
    // CURRENT JOB DATA
    // =========================================================

    private String currentRequestId = "";
    private String customerName = "";
    private String vehicleName = "";
    private String problemName = "";
    private String jobDistance = "";
    private String estimatedAmount = "";

    // =========================================================
    // CURRENT JOB
    // =========================================================

    private ServiceRequest currentJob;

    // =========================================================
    // CONTROLLER
    // =========================================================

    private RequestsController controller;

    // =========================================================
    // UI COMPONENTS
    // =========================================================

    private Label jobStatus;
    private Label progressText;

    private Button startRepairButton;
    private Button completeJobButton;

    private TextArea diagnosisField;
    private TextArea repairField;

    private TextField partsCostField;
    private TextField labourCostField;

    private Label totalAmountLabel;
    private Label partsAmountLabel;
    private Label labourAmountLabel;

    private Label customerValueLabel;
    private Label vehicleValueLabel;
    private Label problemValueLabel;
    private Label distanceValueLabel;
    private Label amountValueLabel;

    // =========================================================
    // JOB STATE
    // =========================================================

    private boolean repairStarted = false;
    private boolean jobCompleted = false;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ActiveJobPage() {

        try {

            Firestore firestore =
                    FirebaseConfig.getFirestore();

            controller =
                    new RequestsController(
                            firestore
                    );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to initialize Firebase",
                    e
            );
        }
    }

    // =========================================================
    // GET CONTENT
    // =========================================================

    public ScrollPane getContent() {

        return createActiveJobContent();
    }

    // =========================================================
    // MAIN ACTIVE JOB CONTENT
    // =========================================================

    private ScrollPane createActiveJobContent() {

        VBox mainContent =
                new VBox(20);

        mainContent.setPadding(
                new Insets(
                        30,
                        35,
                        35,
                        35
                )
        );

        mainContent.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND +
                ";"
        );

        VBox header =
                createHeader();

        HBox jobSummary =
                createJobSummary();

        HBox progressBox =
                createProgressSection();

        HBox workArea =
                new HBox(20);

        VBox diagnosisPanel =
                createDiagnosisPanel();

        VBox costPanel =
                createCostPanel();

        HBox.setHgrow(
                diagnosisPanel,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                costPanel,
                Priority.ALWAYS
        );

        workArea.getChildren().addAll(
                diagnosisPanel,
                costPanel
        );

        VBox completionPanel =
                createCompletionPanel();

        mainContent.getChildren().addAll(
                header,
                jobSummary,
                progressBox,
                workArea,
                completionPanel
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        mainContent
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );

        return scrollPane;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox header =
                new VBox(5);

        Label title =
                new Label(
                        "Active Job"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label subtitle =
                new Label(
                        "Manage the current service request and complete the repair."
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

        return header;
    }

    // =========================================================
    // JOB SUMMARY
    // =========================================================

    private HBox createJobSummary() {

        HBox container =
                new HBox(15);

        container.setPadding(
                new Insets(20)
        );

        container.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );

        VBox customerBox =
                createSummaryItem(
                        "CUSTOMER",
                        customerName
                );

        VBox vehicleBox =
                createSummaryItem(
                        "VEHICLE",
                        vehicleName
                );

        VBox problemBox =
                createSummaryItem(
                        "PROBLEM",
                        problemName
                );

        VBox distanceBox =
                createSummaryItem(
                        "DISTANCE",
                        jobDistance
                );

        VBox amountBox =
                createSummaryItem(
                        "ESTIMATED AMOUNT",
                        estimatedAmount
                );

        customerValueLabel =
                (Label) customerBox
                        .getChildren()
                        .get(1);

        vehicleValueLabel =
                (Label) vehicleBox
                        .getChildren()
                        .get(1);

        problemValueLabel =
                (Label) problemBox
                        .getChildren()
                        .get(1);

        distanceValueLabel =
                (Label) distanceBox
                        .getChildren()
                        .get(1);

        amountValueLabel =
                (Label) amountBox
                        .getChildren()
                        .get(1);

        container.getChildren().addAll(
                customerBox,
                createVerticalSeparator(),
                vehicleBox,
                createVerticalSeparator(),
                problemBox,
                createVerticalSeparator(),
                distanceBox,
                createVerticalSeparator(),
                amountBox
        );

        return container;
    }

    // =========================================================
    // SUMMARY ITEM
    // =========================================================

    private VBox createSummaryItem(
            String title,
            String value
    ) {

        VBox box =
                new VBox(5);

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Label valueLabel =
                new Label(
                        value
                );

        valueLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        box.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        HBox.setHgrow(
                box,
                Priority.ALWAYS
        );

        return box;
    }

    // =========================================================
    // VERTICAL SEPARATOR
    // =========================================================

    private Region createVerticalSeparator() {

        Region separator =
                new Region();

        separator.setPrefWidth(1);
        separator.setPrefHeight(35);

        separator.setStyle(
                "-fx-background-color: " +
                Theme.BORDER +
                ";"
        );

        return separator;
    }

    // =========================================================
    // JOB PROGRESS
    // =========================================================

    private HBox createProgressSection() {

        HBox container =
                new HBox(15);

        container.setAlignment(
                Pos.CENTER_LEFT
        );

        container.setPadding(
                new Insets(18)
        );

        container.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );

        VBox statusBox =
                new VBox(5);

        HBox statusRow =
                new HBox(8);

        statusRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle statusCircle =
                new Circle(6);

        statusCircle.setFill(
                Color.web(
                        Theme.INFO
                )
        );

        jobStatus =
                new Label(
                        "Job Accepted"
                );

        jobStatus.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.INFO +
                ";"
        );

        statusRow.getChildren().addAll(
                statusCircle,
                jobStatus
        );

        progressText =
                new Label(
                        "Request accepted. Ready to start repair."
                );

        progressText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        statusBox.getChildren().addAll(
                statusRow,
                progressText
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        startRepairButton =
                new Button(
                        "Start Repair"
                );

        startRepairButton.setPadding(
                new Insets(
                        10,
                        18,
                        10,
                        18
                )
        );

        startRepairButton.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        startRepairButton.setOnAction(
                e -> startRepair()
        );

        container.getChildren().addAll(
                statusBox,
                spacer,
                startRepairButton
        );

        return container;
    }

    // =========================================================
    // DIAGNOSIS AND REPAIR
    // =========================================================

    private VBox createDiagnosisPanel() {

        VBox panel =
                new VBox(14);

        panel.setPadding(
                new Insets(22)
        );

        panel.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );

        Label title =
                new Label(
                        "Diagnosis & Repair"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label subtitle =
                new Label(
                        "Record the issue and work performed."
                );

        subtitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Label diagnosisLabel =
                new Label(
                        "Diagnosis"
                );

        diagnosisLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        diagnosisField =
                new TextArea();

        diagnosisField.setPromptText(
                "Describe the problem diagnosed..."
        );

        diagnosisField.setPrefRowCount(4);

        diagnosisField.setWrapText(
                true
        );

        diagnosisField.setStyle(
                "-fx-control-inner-background: " +
                Theme.SURFACE +
                ";" +
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label repairLabel =
                new Label(
                        "Repair Details"
                );

        repairLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        repairField =
                new TextArea();

        repairField.setPromptText(
                "Describe the repair or service performed..."
        );

        repairField.setPrefRowCount(4);

        repairField.setWrapText(
                true
        );

        repairField.setStyle(
                "-fx-control-inner-background: " +
                Theme.SURFACE +
                ";" +
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        HBox partsRow =
                createCostInput(
                        "Parts Cost",
                        "0",
                        true
                );

        HBox labourRow =
                createCostInput(
                        "Labour Cost",
                        "0",
                        false
                );

        panel.getChildren().addAll(
                title,
                subtitle,
                diagnosisLabel,
                diagnosisField,
                repairLabel,
                repairField,
                partsRow,
                labourRow
        );

        return panel;
    }

    // =========================================================
    // COST INPUT
    // =========================================================

    private HBox createCostInput(
            String label,
            String defaultValue,
            boolean parts
    ) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label labelText =
                new Label(
                        label
                );

        labelText.setPrefWidth(90);

        labelText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        TextField field =
                new TextField(
                        defaultValue
                );

        field.setPrefWidth(130);

        field.setPromptText(
                "₹ Amount"
        );

        field.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE +
                ";" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        if (parts) {

            partsCostField = field;

        } else {

            labourCostField = field;
        }

        field.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        updateTotal()
        );

        row.getChildren().addAll(
                labelText,
                field
        );

        return row;
    }

    // =========================================================
    // SERVICE SUMMARY
    // =========================================================

    private VBox createCostPanel() {

        VBox panel =
                new VBox(15);

        panel.setPadding(
                new Insets(22)
        );

        panel.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );

        Label title =
                new Label(
                        "Service Summary"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label subtitle =
                new Label(
                        "Review service charges before completion."
                );

        subtitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        VBox partsBox =
                createAmountRow(
                        "Parts",
                        "₹0"
                );

        partsAmountLabel =
                (Label) ((HBox) partsBox
                        .getChildren()
                        .get(0))
                        .getChildren()
                        .get(2);

        VBox labourBox =
                createAmountRow(
                        "Labour",
                        "₹0"
                );

        labourAmountLabel =
                (Label) ((HBox) labourBox
                        .getChildren()
                        .get(0))
                        .getChildren()
                        .get(2);

        Separator separator =
                new Separator();

        HBox totalRow =
                new HBox();

        Label totalLabel =
                new Label(
                        "Total Amount"
                );

        totalLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        totalAmountLabel =
                new Label(
                        "₹0"
                );

        totalAmountLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.PRIMARY +
                ";"
        );

        totalRow.getChildren().addAll(
                totalLabel,
                spacer,
                totalAmountLabel
        );

        VBox paymentBox =
                new VBox(6);

        Label paymentTitle =
                new Label(
                        "PAYMENT STATUS"
                );

        paymentTitle.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Label paymentStatus =
                new Label(
                        "Pending"
                );

        paymentStatus.setStyle(
                "-fx-background-color: " +
                Theme.WARNING_BG +
                ";" +
                "-fx-text-fill: " +
                Theme.WARNING +
                ";" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 5 10 5 10;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;"
        );

        paymentBox.getChildren().addAll(
                paymentTitle,
                paymentStatus
        );

        panel.getChildren().addAll(
                title,
                subtitle,
                partsBox,
                labourBox,
                separator,
                totalRow,
                paymentBox
        );

        return panel;
    }

    // =========================================================
    // AMOUNT ROW
    // =========================================================

    private VBox createAmountRow(
            String title,
            String amount
    ) {

        VBox box =
                new VBox(4);

        HBox row =
                new HBox();

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label amountLabel =
                new Label(
                        amount
                );

        amountLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        row.getChildren().addAll(
                titleLabel,
                spacer,
                amountLabel
        );

        box.getChildren().add(
                row
        );

        return box;
    }

    // =========================================================
    // COMPLETE SERVICE
    // =========================================================

    private VBox createCompletionPanel() {

        VBox panel =
                new VBox(13);

        panel.setPadding(
                new Insets(22)
        );

        panel.setStyle(
                "-fx-background-color: " +
                Theme.CARD +
                ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER +
                ";" +
                "-fx-border-radius: 14;"
        );

        HBox header =
                new HBox();

        VBox titleBox =
                new VBox(4);

        Label title =
                new Label(
                        "Complete Service"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        Label subtitle =
                new Label(
                        "Make sure all repair details are recorded before completing the job."
                );

        subtitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT +
                ";"
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        completeJobButton =
                new Button(
                        "Complete Job"
                );

        completeJobButton.setPadding(
                new Insets(
                        11,
                        20,
                        11,
                        20
                )
        );

        completeJobButton.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS +
                ";" +
                "-fx-text-fill: " +
                Theme.WHITE +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        completeJobButton.setOnAction(
                e -> completeJob()
        );

        header.getChildren().addAll(
                titleBox,
                spacer,
                completeJobButton
        );

        HBox checklist =
                new HBox(20);

        checklist.getChildren().addAll(
                createChecklistItem(
                        "✓",
                        "Diagnosis added"
                ),
                createChecklistItem(
                        "✓",
                        "Repair details added"
                ),
                createChecklistItem(
                        "✓",
                        "Cost reviewed"
                ),
                createChecklistItem(
                        "✓",
                        "Customer informed"
                )
        );

        panel.getChildren().addAll(
                header,
                checklist
        );

        return panel;
    }

    // =========================================================
    // CHECKLIST ITEM
    // =========================================================

    private HBox createChecklistItem(
            String icon,
            String text
    ) {

        HBox item =
                new HBox(7);

        item.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle circle =
                new Circle(9);

        circle.setFill(
                Color.web(
                        Theme.SUCCESS_BG
                )
        );

        Label iconLabel =
                new Label(
                        icon
                );

        iconLabel.setStyle(
                "-fx-text-fill: " +
                Theme.SUCCESS +
                ";" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;"
        );

        StackPane iconBox =
                new StackPane(
                        circle,
                        iconLabel
                );

        Label textLabel =
                new Label(
                        text
                );

        textLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.TEXT +
                ";"
        );

        item.getChildren().addAll(
                iconBox,
                textLabel
        );

        return item;
    }

    // =========================================================
    // START REPAIR
    // =========================================================

    private void startRepair() {

        if (
                currentRequestId == null ||
                currentRequestId.isBlank()
        ) {

            showAlert(
                    "No Active Job",
                    "No active service request is selected."
            );

            return;
        }

        boolean updated =
                controller.updateStatus(
                        currentRequestId,
                        "In Progress"
                );

        if (!updated) {

            showAlert(
                    "Update Failed",
                    "Unable to start the repair. Please try again."
            );

            return;
        }

        repairStarted = true;

        if (currentJob != null) {

            currentJob.setStatus(
                    "In Progress"
            );
        }

        jobStatus.setText(
                "Repair In Progress"
        );

        progressText.setText(
                "Vehicle repair is currently in progress."
        );

        jobStatus.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.PRIMARY +
                ";"
        );

        startRepairButton.setText(
                "Repair In Progress"
        );

        startRepairButton.setDisable(
                true
        );

        startRepairButton.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY_LIGHT +
                ";" +
                "-fx-text-fill: " +
                Theme.PRIMARY_DARK +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );
    }

    // =========================================================
    // UPDATE TOTAL
    // =========================================================

    private void updateTotal() {

        double parts =
                parseAmount(
                        partsCostField
                );

        double labour =
                parseAmount(
                        labourCostField
                );

        double total =
                parts + labour;

        if (partsAmountLabel != null) {

            partsAmountLabel.setText(
                    "₹" +
                    String.format(
                            "%.0f",
                            parts
                    )
            );
        }

        if (labourAmountLabel != null) {

            labourAmountLabel.setText(
                    "₹" +
                    String.format(
                            "%.0f",
                            labour
                    )
            );
        }

        if (totalAmountLabel != null) {

            totalAmountLabel.setText(
                    "₹" +
                    String.format(
                            "%.0f",
                            total
                    )
            );
        }
    }

    // =========================================================
    // PARSE AMOUNT
    // =========================================================

    private double parseAmount(
            TextField field
    ) {

        if (field == null) {
            return 0;
        }

        try {

            String value =
                    field.getText()
                            .trim()
                            .replace(
                                    "₹",
                                    ""
                            )
                            .replace(
                                    ",",
                                    ""
                            );

            if (value.isEmpty()) {
                return 0;
            }

            return Double.parseDouble(
                    value
            );

        } catch (Exception e) {

            return 0;
        }
    }

    // =========================================================
    // COMPLETE JOB
    // =========================================================

    private void completeJob() {

        // -----------------------------------------------------
        // 1. Repair must be started
        // -----------------------------------------------------

        if (!repairStarted) {

            showAlert(
                    "Start Repair",
                    "Please click 'Start Repair' before completing the job."
            );

            return;
        }

        // -----------------------------------------------------
        // 2. Diagnosis validation
        // -----------------------------------------------------

        if (
                diagnosisField == null ||
                diagnosisField
                        .getText()
                        .trim()
                        .isEmpty()
        ) {

            showAlert(
                    "Diagnosis Required",
                    "Please enter the diagnosis before completing the job."
            );

            diagnosisField.requestFocus();

            return;
        }

        // -----------------------------------------------------
        // 3. Repair details validation
        // -----------------------------------------------------

        if (
                repairField == null ||
                repairField
                        .getText()
                        .trim()
                        .isEmpty()
        ) {

            showAlert(
                    "Repair Details Required",
                    "Please enter the repair details before completing the job."
            );

            repairField.requestFocus();

            return;
        }

        // -----------------------------------------------------
        // 4. Request ID validation
        // -----------------------------------------------------

        if (
                currentRequestId == null ||
                currentRequestId.isBlank()
        ) {

            showAlert(
                    "No Active Job",
                    "No active service request is selected."
            );

            return;
        }

        // -----------------------------------------------------
        // 5. Controller validation
        // -----------------------------------------------------

        if (controller == null) {

            showAlert(
                    "System Error",
                    "Firebase controller is not initialized."
            );

            return;
        }

        // -----------------------------------------------------
        // 6. Read costs
        // -----------------------------------------------------

        double partsCost =
                parseAmount(
                        partsCostField
                );

        double labourCost =
                parseAmount(
                        labourCostField
                );

        double totalAmount =
                partsCost +
                labourCost;

        // -----------------------------------------------------
        // 7. Read diagnosis and repair
        // -----------------------------------------------------

        String diagnosis =
                diagnosisField
                        .getText()
                        .trim();

        String repairDetails =
                repairField
                        .getText()
                        .trim();

        // -----------------------------------------------------
        // 8. Completion date
        // -----------------------------------------------------

        String completedDate =
                String.valueOf(
                        Instant.now()
                                .toEpochMilli()
                );

        // -----------------------------------------------------
        // 9. Disable button while saving
        // -----------------------------------------------------

        completeJobButton.setDisable(
                true
        );

        completeJobButton.setText(
                "Completing..."
        );

        // -----------------------------------------------------
        // 10. SAVE TO FIREBASE
        // -----------------------------------------------------

        boolean completed =
                controller.completeJob(
                        currentRequestId,
                        diagnosis,
                        repairDetails,
                        partsCost,
                        labourCost,
                        totalAmount,
                        completedDate
                );

        // -----------------------------------------------------
        // 11. Firebase update failed
        // -----------------------------------------------------

        if (!completed) {

            completeJobButton.setDisable(
                    false
            );

            completeJobButton.setText(
                    "Complete Job"
            );

            showAlert(
                    "Completion Failed",
                    "Unable to complete the service. Please try again."
            );

            return;
        }

        // -----------------------------------------------------
        // 12. Update local object
        // -----------------------------------------------------

        if (currentJob != null) {

            currentJob.setDiagnosis(
                    diagnosis
            );

            currentJob.setRepairDetails(
                    repairDetails
            );

            currentJob.setPartsCost(
                    partsCost
            );

            currentJob.setLabourCost(
                    labourCost
            );

            currentJob.setTotalAmount(
                    totalAmount
            );

            currentJob.setCompletedDate(
                    completedDate
            );

            currentJob.setStatus(
                    "Completed"
            );
        }

        // -----------------------------------------------------
        // 13. Update UI
        // -----------------------------------------------------

        jobCompleted = true;

        jobStatus.setText(
                "Job Completed"
        );

        progressText.setText(
                "Service completed successfully."
        );

        jobStatus.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SUCCESS +
                ";"
        );

        completeJobButton.setText(
                "✓ Job Completed"
        );

        completeJobButton.setDisable(
                true
        );

        completeJobButton.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS_BG +
                ";" +
                "-fx-text-fill: " +
                Theme.SUCCESS +
                ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        // -----------------------------------------------------
        // 14. Success message
        // -----------------------------------------------------

        showAlert(
                "Job Completed",
                "Service for " +
                customerName +
                " has been completed successfully."
        );
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
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
    // SET ACCEPTED JOB
    // =========================================================

    public void setJob(
            ServiceRequest request
    ) {

        if (request == null) {
            return;
        }

        // -----------------------------------------------------
        // Store current job
        // -----------------------------------------------------

        this.currentJob =
                request;

        this.currentRequestId =
                request.getRequestId();

        this.customerName =
                safe(
                        request.getCustomerName()
                );

        this.vehicleName =
                safe(
                        request.getVehicleNumber()
                );

        this.problemName =
                safe(
                        request.getDescription()
                );

        this.jobDistance =
                safe(
                        request.getLocation()
                );

        this.estimatedAmount =
                "₹" +
                String.format(
                        "%.0f",
                        request.getTotalAmount()
                );

        // -----------------------------------------------------
        // Update summary
        // -----------------------------------------------------

        if (customerValueLabel != null) {

            customerValueLabel.setText(
                    customerName
            );
        }

        if (vehicleValueLabel != null) {

            vehicleValueLabel.setText(
                    vehicleName
            );
        }

        if (problemValueLabel != null) {

            problemValueLabel.setText(
                    problemName
            );
        }

        if (distanceValueLabel != null) {

            distanceValueLabel.setText(
                    jobDistance
            );
        }

        if (amountValueLabel != null) {

            amountValueLabel.setText(
                    estimatedAmount
            );
        }

        // -----------------------------------------------------
        // Reset state according to current Firebase status
        // -----------------------------------------------------

        String status =
                safe(
                        request.getStatus()
                );

        if (
                "Completed"
                        .equalsIgnoreCase(
                                status
                        )
        ) {

            repairStarted = true;
            jobCompleted = true;

            if (jobStatus != null) {

                jobStatus.setText(
                        "Job Completed"
                );

                jobStatus.setStyle(
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        Theme.SUCCESS +
                        ";"
                );
            }

            if (progressText != null) {

                progressText.setText(
                        "Service completed successfully."
                );
            }

            if (startRepairButton != null) {

                startRepairButton.setText(
                        "Repair Completed"
                );

                startRepairButton.setDisable(
                        true
                );
            }

            if (completeJobButton != null) {

                completeJobButton.setText(
                        "✓ Job Completed"
                );

                completeJobButton.setDisable(
                        true
                );
            }

        } else if (
                "In Progress"
                        .equalsIgnoreCase(
                                status
                        )
        ) {

            repairStarted = true;
            jobCompleted = false;

            if (jobStatus != null) {

                jobStatus.setText(
                        "Repair In Progress"
                );

                jobStatus.setStyle(
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        Theme.PRIMARY +
                        ";"
                );
            }

            if (progressText != null) {

                progressText.setText(
                        "Vehicle repair is currently in progress."
                );
            }

            if (startRepairButton != null) {

                startRepairButton.setText(
                        "Repair In Progress"
                );

                startRepairButton.setDisable(
                        true
                );
            }

            if (completeJobButton != null) {

                completeJobButton.setText(
                        "Complete Job"
                );

                completeJobButton.setDisable(
                        false
                );
            }

        } else {

            repairStarted = false;
            jobCompleted = false;

            if (jobStatus != null) {

                jobStatus.setText(
                        "Job Accepted"
                );

                jobStatus.setStyle(
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        Theme.INFO +
                        ";"
                );
            }

            if (progressText != null) {

                progressText.setText(
                        "Request accepted. Ready to start repair."
                );
            }

            if (startRepairButton != null) {

                startRepairButton.setText(
                        "Start Repair"
                );

                startRepairButton.setDisable(
                        false
                );
            }

            if (completeJobButton != null) {

                completeJobButton.setText(
                        "Complete Job"
                );

                completeJobButton.setDisable(
                        false
                );
            }
        }

        // -----------------------------------------------------
        // Existing diagnosis
        // -----------------------------------------------------

        if (diagnosisField != null) {

            String diagnosis =
                    request.getDiagnosis();

            diagnosisField.setText(
                    diagnosis == null
                            ? ""
                            : diagnosis
            );
        }

        // -----------------------------------------------------
        // Existing repair details
        // -----------------------------------------------------

        if (repairField != null) {

            String repairDetails =
                    request.getRepairDetails();

            repairField.setText(
                    repairDetails == null
                            ? ""
                            : repairDetails
            );
        }

        // -----------------------------------------------------
        // Existing parts cost
        // -----------------------------------------------------

        if (partsCostField != null) {

            partsCostField.setText(
                    String.valueOf(
                            request.getPartsCost()
                    )
            );
        }

        // -----------------------------------------------------
        // Existing labour cost
        // -----------------------------------------------------

        if (labourCostField != null) {

            labourCostField.setText(
                    String.valueOf(
                            request.getLabourCost()
                    )
            );
        }

        // -----------------------------------------------------
        // Update total
        // -----------------------------------------------------

        updateTotal();
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
}
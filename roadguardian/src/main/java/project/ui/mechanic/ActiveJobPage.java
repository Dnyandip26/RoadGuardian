package project.ui.mechanic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
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

public class ActiveJobPage extends Application {

    private BorderPane root;

    private Label jobStatus;
    private Label progressText;

    private Button startRepairButton;
    private Button completeJobButton;

    private TextArea diagnosisField;
    private TextArea repairField;

    private TextField partsCostField;
    private TextField labourCostField;
    private Label totalAmountLabel;

    @Override
    public void start(Stage stage) {

        root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                Theme.BACKGROUND + ";"
        );

        root.setLeft(createSidebar());
        root.setCenter(createActiveJobContent());

        Scene scene = new Scene(root, 1280, 800);

        stage.setTitle("RoadGuardian - Active Job");
        stage.setScene(scene);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.show();
    }

    private VBox createSidebar() {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(245);
        sidebar.setSpacing(10);
        sidebar.setPadding(
                new Insets(28, 18, 25, 18)
        );

        sidebar.setStyle(
                "-fx-background-color: " +
                Theme.SIDEBAR + ";"
        );

        HBox logoBox = new HBox(10);

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

        logoText.setTextFill(
                Color.web(Theme.TEXT)
        );

        logoText.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        logoBox.getChildren().addAll(
                logoCircle,
                logoText
        );

        Label roleLabel =
                new Label("MECHANIC PORTAL");

        roleLabel.setTextFill(
                Color.web(Theme.SECONDARY_TEXT)
        );

        roleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
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
                        true
                );

        Button historyButton =
                createMenuButton(
                        "◷   Job History",
                        false
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
                e -> root.setCenter(
                        createActiveJobContent()
                )
        );

        historyButton.setOnAction(
                e -> showMessage("Job History")
        );

        Region spacer = new Region();

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

        mechanicName.setTextFill(
                Color.web(Theme.TEXT)
        );

        mechanicName.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        Label mechanicRole =
                new Label("Verified Mechanic");

        mechanicRole.setTextFill(
                Color.web(Theme.SECONDARY_TEXT)
        );

        mechanicRole.setStyle(
                "-fx-font-size: 11px;"
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

    private ScrollPane createActiveJobContent() {

        VBox mainContent =
                new VBox(20);

        mainContent.setPadding(
                new Insets(30, 35, 35, 35)
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
                new Label("Active Job");

        title.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Manage the current service request and complete the repair."
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

    private HBox createJobSummary() {

        HBox container =
                new HBox(15);

        container.setPadding(
                new Insets(20)
        );

        container.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        VBox customerBox =
                createSummaryItem(
                        "CUSTOMER",
                        "Vikram Kulkarni"
                );

        VBox vehicleBox =
                createSummaryItem(
                        "VEHICLE",
                        "Toyota Innova"
                );

        VBox problemBox =
                createSummaryItem(
                        "PROBLEM",
                        "Engine Issue"
                );

        VBox locationBox =
                createSummaryItem(
                        "LOCATION",
                        "Kothrud, Pune"
                );

        container.getChildren().addAll(
                customerBox,
                createVerticalSeparator(),
                vehicleBox,
                createVerticalSeparator(),
                problemBox,
                createVerticalSeparator(),
                locationBox
        );

        return container;
    }

    private VBox createSummaryItem(
            String title,
            String value
    ) {

        VBox box =
                new VBox(5);

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
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

    private Region createVerticalSeparator() {

        Region separator =
                new Region();

        separator.setPrefWidth(1);
        separator.setPrefHeight(35);

        separator.setStyle(
                "-fx-background-color: " +
                Theme.BORDER + ";"
        );

        return separator;
    }

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
                Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
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
                Color.web(Theme.INFO)
        );

        jobStatus =
                new Label("Job In Progress");

        jobStatus.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.INFO + ";"
        );

        statusRow.getChildren().addAll(
                statusCircle,
                jobStatus
        );

        progressText =
                new Label(
                        "Vehicle inspection is pending"
                );

        progressText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
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
                new Button("Start Repair");

        startRepairButton.setPadding(
                new Insets(10, 18, 10, 18)
        );

        startRepairButton.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY + ";" +
                "-fx-text-fill: " +
                Theme.WHITE + ";" +
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

    private VBox createDiagnosisPanel() {

        VBox panel =
                new VBox(14);

        panel.setPadding(
                new Insets(22)
        );

        panel.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        Label title =
                new Label("Diagnosis & Repair");

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Record the issue and work performed."
                );

        subtitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        Label diagnosisLabel =
                new Label("Diagnosis");

        diagnosisLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        diagnosisField =
                new TextArea();

        diagnosisField.setPromptText(
                "Describe the problem diagnosed..."
        );

        diagnosisField.setPrefRowCount(4);
        diagnosisField.setWrapText(true);

        diagnosisField.setStyle(
                "-fx-control-inner-background: " +
                Theme.SURFACE + ";" +
                "-fx-background-color: " +
                Theme.SURFACE + ";" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;"
        );

        Label repairLabel =
                new Label("Repair Details");

        repairLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        repairField =
                new TextArea();

        repairField.setPromptText(
                "Describe the repair or service performed..."
        );

        repairField.setPrefRowCount(4);
        repairField.setWrapText(true);

        repairField.setStyle(
                "-fx-control-inner-background: " +
                Theme.SURFACE + ";" +
                "-fx-background-color: " +
                Theme.SURFACE + ";" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;"
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
                new Label(label);

        labelText.setPrefWidth(90);

        labelText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        TextField field =
                new TextField(defaultValue);

        field.setPrefWidth(130);

        field.setPromptText(
                "₹ Amount"
        );

        field.setStyle(
                "-fx-background-color: " +
                Theme.SURFACE + ";" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-font-size: 11px;"
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

    private VBox createCostPanel() {

        VBox panel =
                new VBox(15);

        panel.setPadding(
                new Insets(22)
        );

        panel.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        Label title =
                new Label("Service Summary");

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Review service charges before completion."
                );

        subtitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        VBox partsBox =
                createAmountRow(
                        "Parts",
                        "₹0"
                );

        VBox labourBox =
                createAmountRow(
                        "Labour",
                        "₹0"
                );

        Separator separator =
                new Separator();

        HBox totalRow =
                new HBox();

        Label totalLabel =
                new Label("Total Amount");

        totalLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        totalAmountLabel =
                new Label("₹0");

        totalAmountLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.PRIMARY + ";"
        );

        totalRow.getChildren().addAll(
                totalLabel,
                spacer,
                totalAmountLabel
        );

        VBox paymentBox =
                new VBox(6);

        Label paymentTitle =
                new Label("PAYMENT STATUS");

        paymentTitle.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        Label paymentStatus =
                new Label("Pending");

        paymentStatus.setStyle(
                "-fx-background-color: " +
                Theme.WARNING_BG + ";" +
                "-fx-text-fill: " +
                Theme.WARNING + ";" +
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

    private VBox createAmountRow(
            String title,
            String amount
    ) {

        VBox box =
                new VBox(4);

        HBox row =
                new HBox();

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label amountLabel =
                new Label(amount);

        amountLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        row.getChildren().addAll(
                titleLabel,
                spacer,
                amountLabel
        );

        box.getChildren().add(row);

        return box;
    }

    private VBox createCompletionPanel() {

        VBox panel =
                new VBox(13);

        panel.setPadding(
                new Insets(22)
        );

        panel.setStyle(
                "-fx-background-color: " +
                Theme.CARD + ";" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: " +
                Theme.BORDER + ";" +
                "-fx-border-radius: 14;"
        );

        HBox header =
                new HBox();

        VBox titleBox =
                new VBox(4);

        Label title =
                new Label("Complete Service");

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Make sure all repair details are recorded before completing the job."
                );

        subtitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.SECONDARY_TEXT + ";"
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
                new Button("Complete Job");

        completeJobButton.setPadding(
                new Insets(11, 20, 11, 20)
        );

        completeJobButton.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS + ";" +
                "-fx-text-fill: " +
                Theme.WHITE + ";" +
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
                Color.web(Theme.SUCCESS_BG)
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setStyle(
                "-fx-text-fill: " +
                Theme.SUCCESS + ";" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;"
        );

        StackPane iconBox =
                new StackPane(
                        circle,
                        iconLabel
                );

        Label textLabel =
                new Label(text);

        textLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " +
                Theme.TEXT + ";"
        );

        item.getChildren().addAll(
                iconBox,
                textLabel
        );

        return item;
    }

    private void startRepair() {

        jobStatus.setText(
                "Repair In Progress"
        );

        progressText.setText(
                "Vehicle repair is currently in progress"
        );

        jobStatus.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.PRIMARY + ";"
        );

        startRepairButton.setText(
                "Repair In Progress"
        );

        startRepairButton.setDisable(true);

        startRepairButton.setStyle(
                "-fx-background-color: " +
                Theme.PRIMARY_LIGHT + ";" +
                "-fx-text-fill: " +
                Theme.PRIMARY_DARK + ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );
    }

    private void updateTotal() {

        double parts =
                parseAmount(partsCostField);

        double labour =
                parseAmount(labourCostField);

        double total =
                parts + labour;

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

    private double parseAmount(
            TextField field
    ) {

        if (field == null) {
            return 0;
        }

        try {

            return Double.parseDouble(
                    field.getText().trim()
            );

        } catch (Exception e) {

            return 0;
        }
    }

    private void completeJob() {

        jobStatus.setText(
                "Job Completed"
        );

        progressText.setText(
                "Service completed successfully"
        );

        jobStatus.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                Theme.SUCCESS + ";"
        );

        completeJobButton.setText(
                "✓ Job Completed"
        );

        completeJobButton.setDisable(true);

        completeJobButton.setStyle(
                "-fx-background-color: " +
                Theme.SUCCESS_BG + ";" +
                "-fx-text-fill: " +
                Theme.SUCCESS + ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );
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

    public static void main(String[] args) {
        launch(args);
    }
}
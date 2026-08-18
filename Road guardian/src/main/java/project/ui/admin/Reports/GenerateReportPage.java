package project.ui.admin.Reports;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.ui.admin.DashBoard.AdminSectionPage;

public class GenerateReportPage extends AdminSectionPage {

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String BLUE = "#2563EB";
    private static final String ORANGE = "#F97316";
    private static final String RED = "#DC2626";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    private final Runnable onBack;

    private TextField nameField;
    private ComboBox<String> typeBox;
    private Label errorLabel;

    public GenerateReportPage(
            Runnable onBack
    ) {
        this.onBack = onBack;
    }

    @Override
    public VBox getView() {

        VBox root =
                new VBox(20);

        root.setPadding(
                new Insets(30, 32, 32, 32)
        );

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        Button backButton =
                new Button(
                        "← Back to Reports"
                );

        styleOutlineButton(
                backButton
        );

        backButton.setOnAction(event -> {

            if (onBack != null) {
                onBack.run();
            }
        });

        Label title =
                new Label(
                        "Generate Report"
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
                        "Create a new RoadGuardian operational report."
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

        VBox header =
                new VBox(5);

        header.getChildren().addAll(
                title,
                subtitle
        );

        VBox card =
                createForm();

        VBox.setVgrow(
                card,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                backButton,
                header,
                card
        );

        return root;
    }

    private VBox createForm() {

        VBox card =
                new VBox(17);

        card.setPadding(
                new Insets(25)
        );

        card.setMaxWidth(850);

        card.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 14;" +
                        "-fx-background-radius: 14;"
        );

        Label heading =
                new Label(
                        "Report Information"
                );

        heading.setTextFill(
                Color.web(HEADING)
        );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        Label nameLabel =
                createLabel(
                        "Report Name"
                );

        nameField =
                new TextField();

        nameField.setPromptText(
                "Enter report name"
        );

        nameField.setPrefHeight(44);

        styleTextField(
                nameField
        );

        Label typeLabel =
                createLabel(
                        "Report Type"
                );

        typeBox =
                new ComboBox<>();

        typeBox.getItems().addAll(
                "Service",
                "SOS",
                "Customer",
                "Mechanic",
                "Financial"
        );

        typeBox.setValue(
                "Service"
        );

        typeBox.setPrefHeight(44);

        typeBox.setMaxWidth(
                Double.MAX_VALUE
        );

        styleComboBox(
                typeBox
        );

        errorLabel =
                new Label();

        errorLabel.setTextFill(
                Color.web(RED)
        );

        errorLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        HBox buttons =
                new HBox(12);

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button cancel =
                new Button(
                        "Cancel"
                );

        styleOutlineButton(cancel);

        cancel.setOnAction(event -> {

            if (onBack != null) {
                onBack.run();
            }
        });

        Button generate =
                new Button(
                        "Generate Report"
                );

        stylePrimaryButton(generate);

        generate.setOnAction(
                event ->
                        generateReport()
        );

        buttons.getChildren().addAll(
                cancel,
                generate
        );

        card.getChildren().addAll(
                heading,
                nameLabel,
                nameField,
                typeLabel,
                typeBox,
                errorLabel,
                buttons
        );

        return card;
    }

    private void generateReport() {

        String name =
                nameField
                        .getText()
                        .trim();

        String type =
                typeBox.getValue();

        if (name.isEmpty()) {

            errorLabel.setText(
                    "Please enter report name."
            );

            return;
        }

        if (type == null) {

            errorLabel.setText(
                    "Please select report type."
            );

            return;
        }

        /*
         * Actual report creation/navigation will be connected
         * from ReportManagementPage.
         *
         * This page only collects report information.
         */

        errorLabel.setText(
                "Report information is ready."
        );
    }

    private Label createLabel(
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
                        14
                )
        );

        return label;
    }

    private void styleTextField(
            TextField field
    ) {

        field.setStyle(
                "-fx-background-color: " + SECONDARY + ";" +
                        "-fx-text-fill: " + HEADING + ";" +
                        "-fx-prompt-text-fill: " + TEXT + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-padding: 0 14 0 14;"
        );
    }

    private void styleComboBox(
            ComboBox<String> combo
    ) {

        combo.setStyle(
                "-fx-background-color: " + SECONDARY + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;"
        );
    }

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
                new Insets(10, 18, 10, 18)
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                        "-fx-border-color: " + BLUE + ";" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;"
        );
    }

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
                new Insets(10, 18, 10, 18)
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                        "-fx-background-radius: 8;"
        );
    }
}
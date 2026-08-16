package project.ui.admin.Services;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.function.Consumer;

import project.ui.admin.DashBoard.AdminSectionPage;

public class AddServicePage extends AdminSectionPage {

    // =====================================================
    // THEME
    // =====================================================

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

    // =====================================================
    // CALLBACK
    // =====================================================

    private final Runnable onBack;

    private final Consumer<ServiceManagementPage.ServiceData> onSave;

    // =====================================================
    // FIELDS
    // =====================================================

    private TextField nameField;

    private TextArea descriptionField;

    private TextField priceField;

    private ComboBox<String> statusBox;

    private Label statusMessage;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AddServicePage(
            Runnable onBack,
            Consumer<ServiceManagementPage.ServiceData> onSave
    ) {

        this.onBack = onBack;

        this.onSave = onSave;
    }

    // =====================================================
    // VIEW
    // =====================================================

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

        // -------------------------------------------------
        // BACK
        // -------------------------------------------------

        Button backButton =
                new Button(
                        "← Back to Services"
                );

        styleOutlineButton(
                backButton
        );

        backButton.setOnAction(
                event -> goBack()
        );

        // -------------------------------------------------
        // HEADER
        // -------------------------------------------------

        VBox header =
                createHeader();

        // -------------------------------------------------
        // FORM
        // -------------------------------------------------

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

    // =====================================================
    // HEADER
    // =====================================================

    private VBox createHeader() {

        VBox header =
                new VBox(6);

        Label title =
                new Label(
                        "Add Service"
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
                        "Create a new roadside assistance service for RoadGuardian."
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

    // =====================================================
    // FORM
    // =====================================================

    private VBox createForm() {

        VBox card =
                new VBox(18);

        card.setPadding(
                new Insets(22)
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
                        "Service Information"
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

        // -------------------------------------------------
        // NAME
        // -------------------------------------------------

        Label nameLabel =
                createLabel(
                        "Service Name"
                );

        nameField =
                new TextField();

        nameField.setPromptText(
                "Example: Battery Assistance"
        );

        styleTextField(
                nameField
        );

        // -------------------------------------------------
        // DESCRIPTION
        // -------------------------------------------------

        Label descriptionLabel =
                createLabel(
                        "Description"
                );

        descriptionField =
                new TextArea();

        descriptionField.setPromptText(
                "Enter service description..."
        );

        descriptionField.setPrefRowCount(
                4
        );

        descriptionField.setWrapText(
                true
        );

        styleTextArea(
                descriptionField
        );

        // -------------------------------------------------
        // PRICE
        // -------------------------------------------------

        Label priceLabel =
                createLabel(
                        "Price"
                );

        priceField =
                new TextField();

        priceField.setPromptText(
                "Example: ₹500"
        );

        styleTextField(
                priceField
        );

        // -------------------------------------------------
        // STATUS
        // -------------------------------------------------

        Label statusLabel =
                createLabel(
                        "Status"
                );

        statusBox =
                new ComboBox<>();

        statusBox.getItems().addAll(
                "Active",
                "Inactive"
        );

        statusBox.setValue(
                "Active"
        );

        statusBox.setPrefHeight(
                45
        );

        statusBox.setMaxWidth(
                Double.MAX_VALUE
        );

        styleComboBox(
                statusBox
        );

        // -------------------------------------------------
        // STATUS MESSAGE
        // -------------------------------------------------

        statusMessage =
                new Label();

        statusMessage.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        statusMessage.setWrapText(
                true
        );

        // -------------------------------------------------
        // ACTIONS
        // -------------------------------------------------

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

        Button save =
                new Button(
                        "Add Service"
                );

        stylePrimaryButton(
                save
        );

        save.setOnAction(
                event -> saveService()
        );

        actions.getChildren().addAll(
                cancel,
                save
        );

        card.getChildren().addAll(
                title,

                nameLabel,
                nameField,

                descriptionLabel,
                descriptionField,

                priceLabel,
                priceField,

                statusLabel,
                statusBox,

                statusMessage,

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

    // =====================================================
    // SAVE
    // =====================================================

    private void saveService() {

        String name =
                nameField
                        .getText()
                        .trim();

        String description =
                descriptionField
                        .getText()
                        .trim();

        String price =
                priceField
                        .getText()
                        .trim();

        String status =
                statusBox.getValue();

        if (name.isEmpty()) {

            setError(
                    "Please enter service name."
            );

            return;
        }

        if (description.isEmpty()) {

            setError(
                    "Please enter service description."
            );

            return;
        }

        if (price.isEmpty()) {

            setError(
                    "Please enter service price."
            );

            return;
        }

        String id =
                createTemporaryId();

        ServiceManagementPage.ServiceData service =
                new ServiceManagementPage.ServiceData(
                        id,
                        name,
                        description,
                        price,
                        status
                );

        if (onSave != null) {

            onSave.accept(
                    service
            );
        }
    }

    // =====================================================
    // TEMP ID
    // =====================================================

    private String createTemporaryId() {

        return "S" +
                String.format(
                        "%03d",
                        System.currentTimeMillis()
                                % 1000
                );
    }

    // =====================================================
    // BACK
    // =====================================================

    private void goBack() {

        if (onBack != null) {

            onBack.run();
        }
    }

    // =====================================================
    // LABEL
    // =====================================================

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

    // =====================================================
    // STATUS
    // =====================================================

    private void setError(
            String message
    ) {

        statusMessage.setText(
                message
        );

        statusMessage.setTextFill(
                Color.web(RED)
        );
    }

    // =====================================================
    // TEXT FIELD
    // =====================================================

    private void styleTextField(
            TextField field
    ) {

        field.setPrefHeight(
                45
        );

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setStyle(
                "-fx-background-color: " +
                        SECONDARY +
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
                        "-fx-font-size: 14px;"
        );
    }

    // =====================================================
    // TEXT AREA
    // =====================================================

    private void styleTextArea(
            TextArea area
    ) {

        area.setStyle(
                "-fx-control-inner-background: " +
                        SECONDARY +
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
                        "-fx-font-size: 14px;"
        );
    }

    // =====================================================
    // COMBO
    // =====================================================

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

    // =====================================================
    // OUTLINE BUTTON
    // =====================================================

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

    // =====================================================
    // PRIMARY BUTTON
    // =====================================================

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
                        BLUE +
                        ";" +
                        "-fx-background-radius: 8;"
        );
    }
}
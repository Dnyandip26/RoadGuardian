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

public class EditServicePage extends AdminSectionPage {

    // =====================================================
    // THEME
    // =====================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String BLUE = "#2563EB";
    private static final String GREEN = "#16A34A";
    private static final String RED = "#DC2626";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    // =====================================================
    // DATA
    // =====================================================

    private final ServiceManagementPage.ServiceData service;

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

    public EditServicePage(
            ServiceManagementPage.ServiceData service,
            Runnable onBack,
            Consumer<ServiceManagementPage.ServiceData> onSave
    ) {

        this.service = service;

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

        Button back =
                new Button(
                        "← Back to Service Details"
                );

        styleOutlineButton(
                back
        );

        back.setOnAction(
                event -> goBack()
        );

        VBox header =
                createHeader();

        VBox form =
                createForm();

        VBox.setVgrow(
                form,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                back,
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
                        "Edit Service"
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
                        "Update the RoadGuardian service information."
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

        Label id =
                new Label(
                        "Service ID: " +
                                service.getId()
                );

        id.setTextFill(
                Color.web(BLUE)
        );

        id.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        header.getChildren().addAll(
                title,
                subtitle,
                id
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

        Label nameLabel =
                createLabel(
                        "Service Name"
                );

        nameField =
                new TextField(
                        service.getName()
                );

        styleTextField(
                nameField
        );

        Label descriptionLabel =
                createLabel(
                        "Description"
                );

        descriptionField =
                new TextArea(
                        service.getDescription()
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

        Label priceLabel =
                createLabel(
                        "Price"
                );

        priceField =
                new TextField(
                        service.getPrice()
                );

        styleTextField(
                priceField
        );

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
                service.getStatus()
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

        statusMessage =
                new Label();

        statusMessage.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

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
                        "Update Service"
                );

        stylePrimaryButton(
                update
        );

        update.setOnAction(
                event -> updateService()
        );

        actions.getChildren().addAll(
                cancel,
                update
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

        ScrollPane scroll =
                new ScrollPane(
                        card
                );

        scroll.setFitToWidth(
                true
        );

        scroll.setPannable(
                true
        );

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;"
        );

        VBox wrapper =
                new VBox();

        VBox.setVgrow(
                scroll,
                Priority.ALWAYS
        );

        wrapper.getChildren().add(
                scroll
        );

        return wrapper;
    }

    // =====================================================
    // UPDATE
    // =====================================================

    private void updateService() {

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

        service.setName(
                name
        );

        service.setDescription(
                description
        );

        service.setPrice(
                price
        );

        service.setStatus(
                status
        );

        if (onSave != null) {

            onSave.accept(
                    service
            );
        }
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
    // HELPERS
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

    private void setError(
            String text
    ) {

        statusMessage.setText(
                text
        );

        statusMessage.setTextFill(
                Color.web(RED)
        );
    }

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
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-padding: 0 14 0 14;" +
                        "-fx-font-size: 14px;"
        );
    }

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
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 14px;"
        );
    }

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
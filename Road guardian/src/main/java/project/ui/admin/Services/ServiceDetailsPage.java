package project.ui.admin.Services;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.ui.admin.DashBoard.AdminSectionPage;

public class ServiceDetailsPage extends AdminSectionPage {

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

    private final Runnable onEdit;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ServiceDetailsPage(
            ServiceManagementPage.ServiceData service,
            Runnable onBack,
            Runnable onEdit
    ) {

        this.service = service;

        this.onBack = onBack;

        this.onEdit = onEdit;
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

        Button back =
                new Button(
                        "← Back to Services"
                );

        styleOutlineButton(
                back
        );

        back.setOnAction(
                event -> goBack()
        );

        // -------------------------------------------------
        // HEADER
        // -------------------------------------------------

        VBox header =
                createHeader();

        // -------------------------------------------------
        // CONTENT
        // -------------------------------------------------

        VBox content =
                createDetails();

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                back,
                header,
                content
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
                        service.getName()
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
                        "Service Details"
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
    // DETAILS
    // =====================================================

    private VBox createDetails() {

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
                        21
                )
        );

        // -------------------------------------------------
        // ID
        // -------------------------------------------------

        VBox idBox =
                createInfoBox(
                        "SERVICE ID",
                        service.getId()
                );

        // -------------------------------------------------
        // NAME
        // -------------------------------------------------

        VBox nameBox =
                createInfoBox(
                        "SERVICE NAME",
                        service.getName()
                );

        // -------------------------------------------------
        // DESCRIPTION
        // -------------------------------------------------

        VBox descriptionBox =
                createInfoBox(
                        "DESCRIPTION",
                        service.getDescription()
                );

        // -------------------------------------------------
        // PRICE
        // -------------------------------------------------

        VBox priceBox =
                createInfoBox(
                        "PRICE",
                        service.getPrice()
                );

        // -------------------------------------------------
        // STATUS
        // -------------------------------------------------

        VBox statusBox =
                createInfoBox(
                        "STATUS",
                        service.getStatus()
                );

        // -------------------------------------------------
        // ROW 1
        // -------------------------------------------------

        HBox row1 =
                new HBox(15);

        HBox.setHgrow(
                idBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                nameBox,
                Priority.ALWAYS
        );

        row1.getChildren().addAll(
                idBox,
                nameBox
        );

        // -------------------------------------------------
        // ROW 2
        // -------------------------------------------------

        HBox row2 =
                new HBox(15);

        HBox.setHgrow(
                priceBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                statusBox,
                Priority.ALWAYS
        );

        row2.getChildren().addAll(
                priceBox,
                statusBox
        );

        // -------------------------------------------------
        // DESCRIPTION
        // -------------------------------------------------

        // -------------------------------------------------
        // ACTIONS
        // -------------------------------------------------

        HBox actions =
                new HBox(12);

        actions.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button edit =
                new Button(
                        "Edit Service"
                );

        stylePrimaryButton(
                edit
        );

        edit.setOnAction(
                event -> goEdit()
        );

        actions.getChildren().add(
                edit
        );

        card.getChildren().addAll(
                title,
                new Separator(),
                row1,
                descriptionBox,
                row2,
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
    // INFO BOX
    // =====================================================

    private VBox createInfoBox(
            String title,
            String value
    ) {

        VBox box =
                new VBox(6);

        box.setPadding(
                new Insets(14)
        );

        box.setStyle(
                "-fx-background-color: " +
                        SECONDARY +
                        ";" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;"
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setTextFill(
                Color.web(TEXT)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
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
                        FontWeight.BOLD,
                        15
                )
        );

        valueLabel.setWrapText(
                true
        );

        box.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return box;
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
    // EDIT
    // =====================================================

    private void goEdit() {

        if (onEdit != null) {

            onEdit.run();
        }
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

    // =====================================================
    // SAFE
    // =====================================================

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
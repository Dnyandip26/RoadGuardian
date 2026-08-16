package project.ui.admin.Complaints;

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

public class ComplaintDetailsPage extends AdminSectionPage {

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
    private static final String YELLOW = "#D97706";

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

    private final Runnable onUpdateStatus;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ComplaintDetailsPage(
            ComplaintManagementPage.ComplaintData complaint,
            Runnable onBack,
            Runnable onUpdateStatus
    ) {

        this.complaint = complaint;

        this.onBack = onBack;

        this.onUpdateStatus = onUpdateStatus;
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
        // BACK BUTTON
        // -----------------------------------------------------

        Button backButton =
                new Button(
                        "← Back to Complaints"
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
        // DETAILS
        // -----------------------------------------------------

        VBox details =
                createDetailsCard();

        VBox.setVgrow(
                details,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                backButton,
                header,
                details
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
                        "Complaint Details"
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
                        "Review the complete complaint information and update its status."
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
    // DETAILS CARD
    // =========================================================

    private VBox createDetailsCard() {

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

        Label cardTitle =
                new Label(
                        "Complaint Information"
                );

        cardTitle.setTextFill(
                Color.web(HEADING)
        );

        cardTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        // -----------------------------------------------------
        // ID + CUSTOMER
        // -----------------------------------------------------

        HBox rowOne =
                new HBox(15);

        VBox idBox =
                createInfoBox(
                        "COMPLAINT ID",
                        complaint.getId()
                );

        VBox customerBox =
                createInfoBox(
                        "CUSTOMER",
                        complaint.getCustomer()
                );

        HBox.setHgrow(
                idBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                customerBox,
                Priority.ALWAYS
        );

        rowOne.getChildren().addAll(
                idBox,
                customerBox
        );

        // -----------------------------------------------------
        // SUBJECT
        // -----------------------------------------------------

        VBox subjectBox =
                createInfoBox(
                        "COMPLAINT SUBJECT",
                        complaint.getSubject()
                );

        // -----------------------------------------------------
        // PRIORITY + STATUS
        // -----------------------------------------------------

        HBox rowTwo =
                new HBox(15);

        VBox priorityBox =
                createPriorityBox(
                        complaint.getPriority()
                );

        VBox statusBox =
                createStatusBox(
                        complaint.getStatus()
                );

        HBox.setHgrow(
                priorityBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                statusBox,
                Priority.ALWAYS
        );

        rowTwo.getChildren().addAll(
                priorityBox,
                statusBox
        );

        // -----------------------------------------------------
        // DATE
        // -----------------------------------------------------

        VBox dateBox =
                createInfoBox(
                        "COMPLAINT DATE",
                        complaint.getDate()
                );

        // -----------------------------------------------------
        // ACTIONS
        // -----------------------------------------------------

        HBox actions =
                new HBox(12);

        actions.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button updateButton =
                new Button(
                        "Update Status"
                );

        stylePrimaryButton(
                updateButton
        );

        updateButton.setOnAction(
                event -> goUpdateStatus()
        );

        actions.getChildren().add(
                updateButton
        );

        // -----------------------------------------------------
        // CARD
        // -----------------------------------------------------

        card.getChildren().addAll(
                cardTitle,
                new Separator(),
                rowOne,
                subjectBox,
                rowTwo,
                dateBox,
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
    // NORMAL INFO BOX
    // =========================================================

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

    // =========================================================
    // PRIORITY BOX
    // =========================================================

    private VBox createPriorityBox(
            String priority
    ) {

        VBox box =
                createInfoBox(
                        "PRIORITY",
                        priority
                );

        String color;

        if (
                "High".equalsIgnoreCase(
                        priority
                )
        ) {

            color = RED;

        } else if (
                "Medium".equalsIgnoreCase(
                        priority
                )
        ) {

            color = YELLOW;

        } else {

            color = GREEN;
        }

        box.setStyle(
                "-fx-background-color: " +
                        SECONDARY +
                        ";" +
                        "-fx-border-color: " +
                        color +
                        ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;"
        );

        return box;
    }

    // =========================================================
    // STATUS BOX
    // =========================================================

    private VBox createStatusBox(
            String status
    ) {

        VBox box =
                createInfoBox(
                        "STATUS",
                        status
                );

        String color;

        if (
                "Resolved".equalsIgnoreCase(
                        status
                )
        ) {

            color = GREEN;

        } else if (
                "Pending".equalsIgnoreCase(
                        status
                )
        ) {

            color = YELLOW;

        } else if (
                "Rejected".equalsIgnoreCase(
                        status
                )
        ) {

            color = RED;

        } else {

            color = BLUE;
        }

        box.setStyle(
                "-fx-background-color: " +
                        SECONDARY +
                        ";" +
                        "-fx-border-color: " +
                        color +
                        ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;"
        );

        return box;
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
    // UPDATE STATUS
    // =========================================================

    private void goUpdateStatus() {

        if (onUpdateStatus != null) {

            onUpdateStatus.run();
        }
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

    // =========================================================
    // SAFE
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
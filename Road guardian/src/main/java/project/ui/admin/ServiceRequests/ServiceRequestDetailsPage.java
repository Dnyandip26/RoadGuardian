package project.ui.admin.ServiceRequests;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.model.ServiceRequest;

public class ServiceRequestDetailsPage {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";

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
    private final Runnable assignAction;
    private final Runnable statusAction;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ServiceRequestDetailsPage(
            ServiceRequest request,
            Runnable backAction,
            Runnable assignAction,
            Runnable statusAction
    ) {
        this.request = request;
        this.backAction = backAction;
        this.assignAction = assignAction;
        this.statusAction = statusAction;
    }

    public ServiceRequestDetailsPage(ServiceRequest request) {
        this(request, () -> {}, () -> {}, () -> {});
    }

    // =========================================================
    // GET VIEW
    // =========================================================

    public VBox getView() {

        VBox root =
                new VBox(18);

        root.setPadding(
                new Insets(
                        22,
                        24,
                        22,
                        24
                )
        );

        root.setPrefWidth(650);

        root.setStyle(
                "-fx-background-color: " +
                BG +
                ";"
        );

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        VBox header =
                createHeader();

        // -----------------------------------------------------
        // REQUEST INFORMATION
        // -----------------------------------------------------

        VBox requestInfo =
                createRequestInformation();

        // -----------------------------------------------------
        // DESCRIPTION
        // -----------------------------------------------------

        VBox description =
                createDescription();

        // -----------------------------------------------------
        // ASSIGNMENT INFORMATION
        // -----------------------------------------------------

        VBox assignment =
                createAssignmentInformation();

        // -----------------------------------------------------
        // ACTION BUTTONS
        // -----------------------------------------------------

        HBox actions =
                createActionButtons();

        root.getChildren().addAll(
                header,
                requestInfo,
                description,
                assignment,
                actions
        );

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private VBox createHeader() {

        VBox box =
                new VBox(6);

        Label title =
                new Label(
                        "Service Request Details"
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

        Label requestId =
                new Label(
                        "Request ID: " +
                        safe(
                                request.getRequestId()
                        )
                );

        requestId.setTextFill(
                Color.web(BLUE)
        );

        requestId.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        HBox statusRow =
                new HBox();

        statusRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label statusTitle =
                new Label(
                        "Current Status"
                );

        statusTitle.setTextFill(
                Color.web(TEXT)
        );

        statusTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label status =
                createStatusBadge(
                        safe(
                                request.getStatus()
                        )
                );

        statusRow.getChildren().addAll(
                statusTitle,
                spacer,
                status
        );

        box.getChildren().addAll(
                title,
                requestId,
                statusRow
        );

        return box;
    }

    // =========================================================
    // REQUEST INFORMATION
    // =========================================================

    private VBox createRequestInformation() {

        VBox card =
                createCard();

        Label heading =
                createSectionHeading(
                        "Request Information"
                );

        VBox rows =
                new VBox(12);

        rows.getChildren().addAll(

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
                rows
        );

        return card;
    }

    // =========================================================
    // DESCRIPTION
    // =========================================================

    private VBox createDescription() {

        VBox card =
                createCard();

        Label heading =
                createSectionHeading(
                        "Problem Description"
                );

        Label description =
                new Label(
                        safe(
                                request.getDescription()
                        )
                );

        description.setTextFill(
                Color.web(TEXT)
        );

        description.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        description.setWrapText(
                true
        );

        description.setMaxWidth(
                580
        );

        card.getChildren().addAll(
                heading,
                new Separator(),
                description
        );

        return card;
    }

    // =========================================================
    // ASSIGNMENT INFORMATION
    // =========================================================

    private VBox createAssignmentInformation() {

        VBox card =
                createCard();

        Label heading =
                createSectionHeading(
                        "Mechanic Assignment"
                );

        String mechanicName =
                safe(
                        request.getMechanicName()
                );

        if (
                mechanicName.equals("-")
        ) {

            mechanicName =
                    "Unassigned";
        }

        HBox row =
                createDetailRow(
                        "Mechanic",
                        mechanicName
                );

        card.getChildren().addAll(
                heading,
                new Separator(),
                row
        );

        return card;
    }

    private HBox createActionButtons() {

        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER_RIGHT);

        Button assignButton = new Button("Assign Mechanic");
        styleButton(assignButton, BLUE);

        assignButton.setOnAction(
                event -> assignAction.run()
        );

        Button statusButton = new Button("Update Status");
        styleButton(statusButton, ORANGE);

        statusButton.setOnAction(
                event -> statusAction.run()
        );

        box.getChildren().addAll(
                assignButton,
                statusButton
        );

        return box;
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
    // SECTION HEADING
    // =========================================================

    private Label createSectionHeading(
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
                        17
                )
        );

        return label;
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private HBox createDetailRow(
            String title,
            String value
    ) {

        HBox row =
                new HBox(15);

        row.setAlignment(
                Pos.TOP_LEFT
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setMinWidth(
                100
        );

        titleLabel.setTextFill(
                Color.web(TEXT)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
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
                        FontWeight.NORMAL,
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
    // STATUS BADGE
    // =========================================================

    private Label createStatusBadge(
            String status
    ) {

        Label badge =
                new Label(
                        status
                );

        String color =
                getStatusColor(
                        status
                );

        badge.setTextFill(
                Color.web(color)
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
                color +
                "18;" +
                "-fx-background-radius: 20;"
        );

        return badge;
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
    // BUTTON STYLE
    // =========================================================

    private void styleButton(
            Button button,
            String color
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
                color +
                ";" +
                "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event -> {

                    String hover =
                            color.equals(ORANGE)
                                    ? ORANGE_HOVER
                                    : BLUE_HOVER;

                    button.setStyle(
                            "-fx-background-color: " +
                            hover +
                            ";" +
                            "-fx-background-radius: 8;"
                    );
                }
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                color +
                                ";" +
                                "-fx-background-radius: 8;"
                        )
        );
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(
            String value
    ) {

        if (
                value == null
                ||
                value.isBlank()
        ) {

            return "-";
        }

        return value;
    }
}
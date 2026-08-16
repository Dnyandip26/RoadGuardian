package project.ui.admin.SOSRequests;

import java.util.List;

import com.google.cloud.firestore.Firestore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import project.controller.admin.MechanicController;
import project.controller.admin.SOSRequestController;
import project.firebase.FirebaseConfig;
import project.model.Mechanic;
import project.model.SOSRequest;
import project.ui.admin.DashBoard.AdminSectionPage;

public class AssignSOSMechanicPage extends AdminSectionPage {

    // =====================================================
    // THEME
    // =====================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";

    private static final String BLUE = "#2563EB";
    private static final String GREEN = "#16A34A";
    private static final String ORANGE = "#F97316";
    private static final String RED = "#DC2626";

    private static final String BORDER = "#B8D4D9";

    // =====================================================
    // DATA
    // =====================================================

    private final SOSRequest request;

    private SOSRequestController sosController;

    private MechanicController mechanicController;

    private ComboBox<Mechanic> mechanicComboBox;

    // =====================================================
    // NAVIGATION CALLBACKS
    // =====================================================

    private Runnable onBack;

    private Runnable onSuccess;

    // =====================================================
    // STATUS MESSAGE
    // =====================================================

    private Label statusMessage;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AssignSOSMechanicPage(
            SOSRequest request
    ) {

        this.request = request;
    }

    // =====================================================
    // CONSTRUCTOR - BACK
    // =====================================================

    public AssignSOSMechanicPage(
            SOSRequest request,
            Runnable onBack
    ) {

        this.request = request;

        this.onBack = onBack;
    }

    // =====================================================
    // CONSTRUCTOR - BACK + SUCCESS
    // =====================================================

    public AssignSOSMechanicPage(
            SOSRequest request,
            Runnable onBack,
            Runnable onSuccess
    ) {

        this.request = request;

        this.onBack = onBack;

        this.onSuccess = onSuccess;
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
        // FIREBASE
        // -------------------------------------------------

        try {

            Firestore firestore =
                    FirebaseConfig.getFirestore();

            sosController =
                    new SOSRequestController(
                            firestore
                    );

            mechanicController =
                    new MechanicController(
                            firestore
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return createErrorView(
                    "Unable to connect to Firebase."
            );
        }

        // -------------------------------------------------
        // BACK BUTTON
        // -------------------------------------------------

        Button backButton =
                new Button(
                        "← Back to SOS Details"
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
        // CONTENT
        // -------------------------------------------------

        VBox content =
                createAssignmentContent();

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        // -------------------------------------------------
        // ROOT
        // -------------------------------------------------

        root.getChildren().addAll(
                backButton,
                header,
                content
        );

        // -------------------------------------------------
        // LOAD MECHANICS
        // -------------------------------------------------

        loadMechanics();

        return root;
    }

    // =====================================================
    // BACK NAVIGATION
    // =====================================================

    private void goBack() {

        if (onBack != null) {

            onBack.run();
        }
    }

    // =====================================================
    // HEADER
    // =====================================================

    private VBox createHeader() {

        VBox header =
                new VBox(6);

        Label title =
                new Label(
                        "Assign SOS Mechanic"
                );

        title.setTextFill(
                Color.web(
                        HEADING
                )
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
                        "Assign an available mechanic to this emergency roadside assistance request."
                );

        subtitle.setTextFill(
                Color.web(
                        TEXT
                )
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
    // MAIN CONTENT
    // =====================================================

    private VBox createAssignmentContent() {

        VBox content =
                new VBox(18);

        // -------------------------------------------------
        // REQUEST CARD
        // -------------------------------------------------

        VBox requestCard =
                createRequestCard();

        // -------------------------------------------------
        // MECHANIC CARD
        // -------------------------------------------------

        VBox mechanicCard =
                createMechanicSelectionCard();

        // -------------------------------------------------
        // ACTIONS
        // -------------------------------------------------

        HBox actions =
                createActions();

        // -------------------------------------------------
        // STATUS
        // -------------------------------------------------

        statusMessage =
                new Label();

        statusMessage.setWrapText(
                true
        );

        statusMessage.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        content.getChildren().addAll(
                requestCard,
                mechanicCard,
                actions,
                statusMessage
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        content
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

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
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
    // REQUEST CARD
    // =====================================================

    private VBox createRequestCard() {

        VBox card =
                createCard();

        Label title =
                createSectionTitle(
                        "Request Information"
                );

        HBox row1 =
                new HBox(15);

        VBox requestId =
                createInfoBox(
                        "REQUEST ID",
                        request.getSosId()
                );

        VBox customer =
                createInfoBox(
                        "CUSTOMER",
                        request.getCustomerName()
                );

        HBox.setHgrow(
                requestId,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                customer,
                Priority.ALWAYS
        );

        row1.getChildren().addAll(
                requestId,
                customer
        );

        HBox row2 =
                new HBox(15);

        VBox vehicle =
                createInfoBox(
                        "VEHICLE",
                        request.getVehicleNumber()
                );

        VBox emergency =
                createInfoBox(
                        "EMERGENCY",
                        request.getEmergencyType()
                );

        VBox location =
                createInfoBox(
                        "LOCATION",
                        request.getLocation()
                );

        HBox.setHgrow(
                vehicle,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                emergency,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                location,
                Priority.ALWAYS
        );

        row2.getChildren().addAll(
                vehicle,
                emergency,
                location
        );

        card.getChildren().addAll(
                title,
                new Separator(),
                row1,
                row2
        );

        return card;
    }

    // =====================================================
    // MECHANIC SELECTION CARD
    // =====================================================

    private VBox createMechanicSelectionCard() {

        VBox card =
                createCard();

        Label title =
                createSectionTitle(
                        "Select Mechanic"
                );

        Label description =
                new Label(
                        "Choose an available mechanic from the list."
                );

        description.setTextFill(
                Color.web(
                        TEXT
                )
        );

        description.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        mechanicComboBox =
                new ComboBox<>();

        mechanicComboBox.setPrefHeight(
                45
        );

        mechanicComboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        mechanicComboBox.setPromptText(
                "Select a mechanic"
        );

        styleComboBox(
                mechanicComboBox
        );

        // -------------------------------------------------
        // DROPDOWN
        // -------------------------------------------------

        mechanicComboBox.setCellFactory(
                list ->
                        new ListCell<Mechanic>() {

                            @Override
                            protected void updateItem(
                                    Mechanic item,
                                    boolean empty
                            ) {

                                super.updateItem(
                                        item,
                                        empty
                                );

                                if (
                                        empty ||
                                                item == null
                                ) {

                                    setText(
                                            null
                                    );

                                } else {

                                    setText(
                                            safe(
                                                    item.getName()
                                            )
                                                    +
                                                    "  •  "
                                                    +
                                                    safe(
                                                            item.getSpecialization()
                                                    )
                                    );
                                }
                            }
                        }
        );

        // -------------------------------------------------
        // SELECTED VALUE
        // -------------------------------------------------

        mechanicComboBox.setButtonCell(
                new ListCell<Mechanic>() {

                    @Override
                    protected void updateItem(
                            Mechanic item,
                            boolean empty
                    ) {

                        super.updateItem(
                                item,
                                empty
                        );

                        if (
                                empty ||
                                        item == null
                        ) {

                            setText(
                                    "Select a mechanic"
                            );

                        } else {

                            setText(
                                    safe(
                                            item.getName()
                                    )
                            );
                        }
                    }
                }
        );

        // -------------------------------------------------
        // SELECTED LABEL
        // -------------------------------------------------

        Label selectedLabel =
                new Label(
                        "No mechanic selected"
                );

        selectedLabel.setTextFill(
                Color.web(
                        TEXT
                )
        );

        selectedLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        mechanicComboBox.valueProperty()
                .addListener(
                        (
                                obs,
                                oldValue,
                                newValue
                        ) -> {

                            if (
                                    newValue == null
                            ) {

                                selectedLabel.setText(
                                        "No mechanic selected"
                                );

                                selectedLabel.setTextFill(
                                        Color.web(
                                                TEXT
                                        )
                                );

                            } else {

                                selectedLabel.setText(
                                        "Selected: " +
                                                safe(
                                                        newValue.getName()
                                                )
                                );

                                selectedLabel.setTextFill(
                                        Color.web(
                                                GREEN
                                        )
                                );
                            }
                        }
                );

        card.getChildren().addAll(
                title,
                description,
                new Separator(),
                mechanicComboBox,
                selectedLabel
        );

        return card;
    }

    // =====================================================
    // ACTIONS
    // =====================================================

    private HBox createActions() {

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
                event ->
                        goBack()
        );

        Button assign =
                new Button(
                        "Assign SOS Mechanic"
                );

        stylePrimaryButton(
                assign,
                BLUE
        );

        assign.setOnAction(
                event ->
                        assignMechanic()
        );

        actions.getChildren().addAll(
                cancel,
                assign
        );

        return actions;
    }

    // =====================================================
    // LOAD MECHANICS
    // =====================================================

    private void loadMechanics() {

        try {

            List<Mechanic> mechanics =
                    mechanicController
                            .getAllMechanics();

            mechanicComboBox
                    .getItems()
                    .clear();

            if (
                    mechanics == null ||
                            mechanics.isEmpty()
            ) {

                setStatus(
                        "No mechanics are available.",
                        RED
                );

                return;
            }

            mechanicComboBox
                    .getItems()
                    .addAll(
                            mechanics
                    );

        } catch (Exception e) {

            e.printStackTrace();

            setStatus(
                    "Unable to load mechanics: " +
                            safe(
                                    e.getMessage()
                            ),
                    RED
            );
        }
    }

    // =====================================================
    // ASSIGN MECHANIC
    // =====================================================

    private void assignMechanic() {

        Mechanic selected =
                mechanicComboBox.getValue();

        if (
                selected == null
        ) {

            setStatus(
                    "Please select a mechanic before assigning.",
                    RED
            );

            return;
        }

        try {

            boolean success =
                    sosController.assignMechanic(
                            request.getSosId(),
                            selected.getMechanicId(),
                            selected.getName()
                    );

            if (
                    success
            ) {

                // -----------------------------------------
                // NO DIALOG
                // DIRECT NAVIGATION
                // -----------------------------------------

                if (
                        onSuccess != null
                ) {

                    onSuccess.run();

                } else if (
                        onBack != null
                ) {

                    onBack.run();

                } else {

                    setStatus(
                            "Mechanic assigned successfully.",
                            GREEN
                    );
                }

            } else {

                setStatus(
                        "The mechanic could not be assigned.",
                        RED
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            setStatus(
                    "Assignment failed: " +
                            safe(
                                    e.getMessage()
                            ),
                    RED
            );
        }
    }

    // =====================================================
    // STATUS MESSAGE
    // =====================================================

    private void setStatus(
            String message,
            String color
    ) {

        if (
                statusMessage == null
        ) {

            return;
        }

        statusMessage.setText(
                message
        );

        statusMessage.setTextFill(
                Color.web(
                        color
                )
        );
    }

    // =====================================================
    // CARD
    // =====================================================

    private VBox createCard() {

        VBox card =
                new VBox(13);

        card.setPadding(
                new Insets(
                        20
                )
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

        return card;
    }

    // =====================================================
    // SECTION TITLE
    // =====================================================

    private Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setTextFill(
                Color.web(
                        HEADING
                )
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        return label;
    }

    // =====================================================
    // INFO BOX
    // =====================================================

    private VBox createInfoBox(
            String title,
            String value
    ) {

        VBox box =
                new VBox(5);

        box.setPadding(
                new Insets(
                        12
                )
        );

        box.setStyle(
                "-fx-background-color: " +
                        SECONDARY +
                        ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 9;"
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setTextFill(
                Color.web(
                        TEXT
                )
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
                        safe(
                                value
                        )
                );

        valueLabel.setTextFill(
                Color.web(
                        HEADING
                )
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
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
    // COMBOBOX STYLE
    // =====================================================

    private void styleComboBox(
            ComboBox<Mechanic> combo
    ) {

        combo.setStyle(
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

    // =====================================================
    // OUTLINE BUTTON
    // =====================================================

    private void styleOutlineButton(
            Button button
    ) {

        button.setTextFill(
                Color.web(
                        BLUE
                )
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

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: #DBEAFE;" +
                                        "-fx-border-color: " +
                                        BLUE +
                                        ";" +
                                        "-fx-border-radius: 8;" +
                                        "-fx-background-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                        SURFACE +
                                        ";" +
                                        "-fx-border-color: " +
                                        BLUE +
                                        ";" +
                                        "-fx-border-radius: 8;" +
                                        "-fx-background-radius: 8;"
                        )
        );
    }

    // =====================================================
    // PRIMARY BUTTON
    // =====================================================

    private void stylePrimaryButton(
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
                        14
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
                        color +
                        ";" +
                        "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: #1D4ED8;" +
                                        "-fx-background-radius: 8;"
                        )
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

    // =====================================================
    // ERROR VIEW
    // =====================================================

    private VBox createErrorView(
            String message
    ) {

        VBox box =
                new VBox(12);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(
                        30
                )
        );

        box.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";"
        );

        Label icon =
                new Label(
                        "!"
                );

        icon.setTextFill(
                Color.web(
                        RED
                )
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        Label title =
                new Label(
                        "SOS Assignment Error"
                );

        title.setTextFill(
                Color.web(
                        HEADING
                )
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
                Color.web(
                        TEXT
                )
        );

        text.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        text.setWrapText(
                true
        );

        box.getChildren().addAll(
                icon,
                title,
                text
        );

        return box;
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
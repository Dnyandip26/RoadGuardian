
package project.ui.admin.Notifications;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;

public class NotificationManagementPage {

    // =====================================================
    // THEME
    // =====================================================

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String SECONDARY = "#DFF3F5";

    private static final String BLUE = "#2563EB";
    private static final String BLUE_HOVER = "#1D4ED8";
    private static final String GREEN = "#16A34A";
    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";
    private static final String RED = "#DC2626";
    private static final String PURPLE = "#7C3AED";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    // =====================================================
    // DATA
    // =====================================================

    private final ObservableList<NotificationData> notifications =
            FXCollections.observableArrayList();

    private final ObservableList<NotificationData> filteredNotifications =
            FXCollections.observableArrayList();

    private VBox notificationCards;

    private TextField searchField;
    private ComboBox<String> statusFilter;
    private ComboBox<String> typeFilter;
    private ComboBox<String> sortFilter;

    private Label resultCountLabel;

    private Label totalLabel;
    private Label unreadLabel;
    private Label sentLabel;
    private Label readLabel;

    // =====================================================
    // MAIN VIEW
    // =====================================================

    public VBox getView() {

        VBox root = new VBox(20);

        root.setPadding(
                new Insets(30, 32, 32, 32)
        );

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        HBox header = createHeader();
        HBox statistics = createStatistics();
        HBox toolbar = createToolbar();
        VBox recordsCard = createRecordsCard();

        root.getChildren().addAll(
                header,
                statistics,
                toolbar,
                recordsCard
        );

        VBox.setVgrow(
                recordsCard,
                Priority.ALWAYS
        );

        loadSampleNotifications();
        updateStatistics();
        applyFilters();

        return root;
    }

    // =====================================================
    // HEADER
    // =====================================================

    private HBox createHeader() {

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(5);

        Label title = new Label(
                "Notifications"
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

        Label subtitle = new Label(
                "Manage system notifications and customer alerts from one place."
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

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button addButton = createPrimaryButton(
                "+ New Notification"
        );

        addButton.setOnAction(
                event ->
                        showCreateNotificationDialog()
        );

        header.getChildren().addAll(
                titleBox,
                spacer,
                addButton
        );

        return header;
    }

    // =====================================================
    // STATISTICS
    // =====================================================

    private HBox createStatistics() {

        HBox box = new HBox(16);

        totalLabel = createValueLabel(BLUE);
        unreadLabel = createValueLabel(ORANGE);
        sentLabel = createValueLabel(GREEN);
        readLabel = createValueLabel(PURPLE);

        VBox total = createStatCard(
                "Total Notifications",
                "All system notifications",
                totalLabel,
                BLUE,
                "TOTAL"
        );

        VBox unread = createStatCard(
                "Unread",
                "Needs recipient attention",
                unreadLabel,
                ORANGE,
                "UNREAD"
        );

        VBox sent = createStatCard(
                "Sent",
                "Successfully delivered",
                sentLabel,
                GREEN,
                "SENT"
        );

        VBox read = createStatCard(
                "Read",
                "Already viewed",
                readLabel,
                PURPLE,
                "READ"
        );

        HBox.setHgrow(total, Priority.ALWAYS);
        HBox.setHgrow(unread, Priority.ALWAYS);
        HBox.setHgrow(sent, Priority.ALWAYS);
        HBox.setHgrow(read, Priority.ALWAYS);

        box.getChildren().addAll(
                total,
                unread,
                sent,
                read
        );

        return box;
    }

    private Label createValueLabel(
            String color
    ) {

        Label label = new Label("0");

        label.setTextFill(
                Color.web(color)
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        return label;
    }

    private VBox createStatCard(
            String title,
            String subtitle,
            Label value,
            String color,
            String tagText
    ) {

        VBox card = new VBox(8);

        card.setPadding(
                new Insets(
                        18,
                        20,
                        17,
                        20
                )
        );

        card.setMinHeight(118);

        String normalStyle =
                "-fx-background-color: " + SURFACE + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;";

        String hoverStyle =
                "-fx-background-color: #F7FCFC;" +
                        "-fx-border-color: " + color + ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;" +
                        "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.14), 10, 0.12, 0, 2);";

        card.setStyle(normalStyle);
        card.setCursor(Cursor.HAND);

        HBox top = new HBox();

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel = new Label(title);

        titleLabel.setTextFill(
                Color.web(HEADING)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label tag = new Label(tagText);

        tag.setTextFill(
                Color.web(color)
        );

        tag.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        tag.setPadding(
                new Insets(
                        6,
                        9,
                        6,
                        9
                )
        );

        tag.setStyle(
                "-fx-background-color: " +
                        color +
                        "18;" +
                        "-fx-background-radius: 20;"
        );

        top.getChildren().addAll(
                titleLabel,
                spacer,
                tag
        );

        Label subtitleLabel = new Label(subtitle);

        subtitleLabel.setTextFill(
                Color.web(TEXT)
        );

        subtitleLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        card.getChildren().addAll(
                top,
                value,
                subtitleLabel
        );

        card.setOnMouseEntered(
                event -> {

                    card.setStyle(hoverStyle);
                    card.setTranslateY(-2);
                }
        );

        card.setOnMouseExited(
                event -> {

                    card.setStyle(normalStyle);
                    card.setTranslateY(0);
                }
        );

        return card;
    }

    // =====================================================
    // TOOLBAR
    // =====================================================

    private HBox createToolbar() {

        HBox toolbar = new HBox(12);

        toolbar.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField = new TextField();

        searchField.setPromptText(
                "Search notification, title or recipient..."
        );

        searchField.setPrefHeight(44);
        searchField.setPrefWidth(380);

        styleTextField(searchField);

        statusFilter = new ComboBox<>();

        statusFilter.getItems().addAll(
                "All Status",
                "Unread",
                "Read",
                "Sent"
        );

        statusFilter.setValue(
                "All Status"
        );

        statusFilter.setPrefHeight(44);
        statusFilter.setPrefWidth(135);

        styleComboBox(statusFilter);

        typeFilter = new ComboBox<>();

        typeFilter.getItems().addAll(
                "All Types",
                "Service Request",
                "SOS",
                "Mechanic",
                "Service",
                "Complaint",
                "System"
        );

        typeFilter.setValue(
                "All Types"
        );

        typeFilter.setPrefHeight(44);
        typeFilter.setPrefWidth(155);

        styleComboBox(typeFilter);

        sortFilter = new ComboBox<>();

        sortFilter.getItems().addAll(
                "Newest First",
                "Oldest First",
                "Title A-Z",
                "Title Z-A"
        );

        sortFilter.setValue(
                "Newest First"
        );

        sortFilter.setPrefHeight(44);
        sortFilter.setPrefWidth(145);

        styleComboBox(sortFilter);

        Button refresh = createSecondaryButton(
                "Refresh"
        );

        refresh.setPrefHeight(44);

        searchField.textProperty().addListener(
                (obs, oldValue, newValue) ->
                        applyFilters()
        );

        statusFilter.valueProperty().addListener(
                (obs, oldValue, newValue) ->
                        applyFilters()
        );

        typeFilter.valueProperty().addListener(
                (obs, oldValue, newValue) ->
                        applyFilters()
        );

        sortFilter.valueProperty().addListener(
                (obs, oldValue, newValue) ->
                        applyFilters()
        );

        refresh.setOnAction(
                event -> {

                    loadSampleNotifications();
                    updateStatistics();
                    applyFilters();
                }
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        toolbar.getChildren().addAll(
                searchField,
                statusFilter,
                typeFilter,
                sortFilter,
                refresh,
                spacer
        );

        return toolbar;
    }

    // =====================================================
    // RECORDS CARD
    // =====================================================

    private VBox createRecordsCard() {

        VBox card = new VBox(15);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;"
        );

        HBox heading = new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(4);

        Label title = new Label(
                "Notification Records"
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

        Label subtitle = new Label(
                "View and manage RoadGuardian system notifications."
        );

        subtitle.setTextFill(
                Color.web(TEXT)
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        resultCountLabel = new Label(
                "0 notifications"
        );

        resultCountLabel.setTextFill(
                Color.web(BLUE)
        );

        resultCountLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        titleBox.getChildren().addAll(
                title,
                subtitle,
                resultCountLabel
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label live = new Label(
                "● Notification Center"
        );

        live.setTextFill(
                Color.web(BLUE)
        );

        live.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        live.setPadding(
                new Insets(
                        7,
                        11,
                        7,
                        11
                )
        );

        live.setStyle(
                "-fx-background-color: " +
                        BLUE +
                        "18;" +
                        "-fx-background-radius: 20;"
        );

        heading.getChildren().addAll(
                titleBox,
                spacer,
                live
        );

        notificationCards = new VBox(12);

        notificationCards.setFillWidth(true);

        notificationCards.setPadding(
                new Insets(
                        3,
                        2,
                        10,
                        2
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(notificationCards);

        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

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

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                heading,
                scrollPane
        );

        return card;
    }

    // =====================================================
    // NOTIFICATION CARD
    // =====================================================

    private VBox createNotificationCard(
            NotificationData data,
            int number
    ) {

        VBox card = new VBox(13);

        card.setPadding(
                new Insets(
                        18,
                        20,
                        18,
                        20
                )
        );

        card.setCursor(Cursor.HAND);

        String normalStyle =
                "-fx-background-color: " + SECONDARY + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;";

        String hoverStyle =
                "-fx-background-color: #EAF8FA;" +
                        "-fx-border-color: " + BLUE + ";" +
                        "-fx-border-radius: 13;" +
                        "-fx-background-radius: 13;" +
                        "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.17), 12, 0.15, 0, 3);";

        card.setStyle(normalStyle);

        HBox top = new HBox(13);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox icon = createNotificationIcon(
                data.getType()
        );

        VBox identity = new VBox(5);

        HBox idLine = new HBox(9);

        idLine.setAlignment(
                Pos.CENTER_LEFT
        );

        Label serial = new Label(
                "#" + number
        );

        serial.setTextFill(
                Color.web(BLUE)
        );

        serial.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        serial.setPadding(
                new Insets(
                        4,
                        8,
                        4,
                        8
                )
        );

        serial.setStyle(
                "-fx-background-color: " +
                        BLUE +
                        "14;" +
                        "-fx-background-radius: 20;"
        );

        Label id = new Label(
                data.getId()
        );

        id.setTextFill(
                Color.web(TEXT)
        );

        id.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        idLine.getChildren().addAll(
                serial,
                id
        );

        Label title = new Label(
                data.getTitle()
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

        title.setWrapText(true);

        identity.getChildren().addAll(
                idLine,
                title
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        VBox badges = new VBox(6);

        badges.setAlignment(
                Pos.CENTER_RIGHT
        );

        badges.getChildren().addAll(
                createTypeBadge(
                        data.getType()
                ),
                createStatusBadge(
                        data.getStatus()
                )
        );

        top.getChildren().addAll(
                icon,
                identity,
                spacer,
                badges
        );

        HBox infoRow = new HBox(12);

        VBox recipient = createInfoBox(
                "RECIPIENT",
                data.getRecipient()
        );

        VBox type = createInfoBox(
                "TYPE",
                data.getType()
        );

        VBox date = createInfoBox(
                "DATE",
                data.getDate()
        );

        HBox.setHgrow(
                recipient,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                type,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                date,
                Priority.ALWAYS
        );

        infoRow.getChildren().addAll(
                recipient,
                type,
                date
        );

        HBox bottom = new HBox(10);

        bottom.setAlignment(
                Pos.CENTER_RIGHT
        );

        Region bottomSpacer = new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );

        Button markRead = createActionButton(
                "Mark Read",
                GREEN
        );

        Button delete = createActionButton(
                "Delete",
                RED
        );

        markRead.setOnAction(
                event -> {

                    data.setStatus("Read");

                    updateStatistics();
                    applyFilters();
                }
        );

        delete.setOnAction(
                event ->
                        deleteNotification(data)
        );

        bottom.getChildren().addAll(
                bottomSpacer,
                markRead,
                delete
        );

        card.getChildren().addAll(
                top,
                new Separator(),
                infoRow,
                bottom
        );

        card.setOnMouseEntered(
                event -> {

                    card.setStyle(hoverStyle);
                    card.setTranslateY(-2);
                }
        );

        card.setOnMouseExited(
                event -> {

                    card.setStyle(normalStyle);
                    card.setTranslateY(0);
                }
        );

        return card;
    }

    // =====================================================
    // ICON
    // =====================================================

    private VBox createNotificationIcon(
            String type
    ) {

        String color;
        String iconText;

        if ("SOS".equalsIgnoreCase(type)) {

            color = RED;
            iconText = "!";

        } else if (
                "Service".equalsIgnoreCase(type) ||
                        "Service Request".equalsIgnoreCase(type)
        ) {

            color = GREEN;
            iconText = "S";

        } else if (
                "Mechanic".equalsIgnoreCase(type)
        ) {

            color = PURPLE;
            iconText = "M";

        } else if (
                "Complaint".equalsIgnoreCase(type)
        ) {

            color = ORANGE;
            iconText = "C";

        } else {

            color = BLUE;
            iconText = "N";
        }

        VBox box = new VBox();

        box.setAlignment(
                Pos.CENTER
        );

        box.setMinSize(50, 50);
        box.setPrefSize(50, 50);
        box.setMaxSize(50, 50);

        box.setStyle(
                "-fx-background-color: " +
                        color +
                        ";" +
                        "-fx-background-radius: 12;"
        );

        Label icon = new Label(
                iconText
        );

        icon.setTextFill(
                Color.WHITE
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        box.getChildren().add(icon);

        return box;
    }

    // =====================================================
    // TYPE BADGE
    // =====================================================

    private Label createTypeBadge(
            String type
    ) {

        String color;
        String background;

        if ("SOS".equalsIgnoreCase(type)) {

            color = RED;
            background = "#FEE2E2";

        } else if (
                "Service".equalsIgnoreCase(type) ||
                        "Service Request".equalsIgnoreCase(type)
        ) {

            color = GREEN;
            background = "#DCFCE7";

        } else if (
                "Mechanic".equalsIgnoreCase(type)
        ) {

            color = PURPLE;
            background = "#EDE9FE";

        } else if (
                "Complaint".equalsIgnoreCase(type)
        ) {

            color = ORANGE;
            background = "#FFEDD5";

        } else {

            color = BLUE;
            background = "#DBEAFE";
        }

        Label badge = new Label(type);

        badge.setTextFill(
                Color.web(color)
        );

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        badge.setPadding(
                new Insets(
                        6,
                        10,
                        6,
                        10
                )
        );

        badge.setStyle(
                "-fx-background-color: " +
                        background +
                        ";" +
                        "-fx-background-radius: 20;"
        );

        return badge;
    }

    // =====================================================
    // STATUS BADGE
    // =====================================================

    private Label createStatusBadge(
            String status
    ) {

        String color;
        String background;

        if ("Sent".equalsIgnoreCase(status)) {

            color = GREEN;
            background = "#DCFCE7";

        } else if ("Unread".equalsIgnoreCase(status)) {

            color = ORANGE;
            background = "#FFEDD5";

        } else {

            color = BLUE;
            background = "#DBEAFE";
        }

        Label badge = new Label(status);

        badge.setTextFill(
                Color.web(color)
        );

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        badge.setPadding(
                new Insets(
                        6,
                        10,
                        6,
                        10
                )
        );

        badge.setStyle(
                "-fx-background-color: " +
                        background +
                        ";" +
                        "-fx-background-radius: 20;"
        );

        return badge;
    }

    // =====================================================
    // INFO BOX
    // =====================================================

    private VBox createInfoBox(
            String title,
            String value
    ) {

        VBox box = new VBox(5);

        box.setPadding(
                new Insets(
                        10,
                        12,
                        10,
                        12
                )
        );

        box.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                        "-fx-background-radius: 9;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 9;"
        );

        Label titleLabel = new Label(title);

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

        Label valueLabel = new Label(value);

        valueLabel.setTextFill(
                Color.web(HEADING)
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        valueLabel.setWrapText(true);

        box.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return box;
    }

    // =====================================================
    // BUTTONS
    // =====================================================

    private Button createPrimaryButton(
            String text
    ) {

        Button button = new Button(text);

        button.setTextFill(
                Color.WHITE
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        button.setPadding(
                new Insets(
                        11,
                        19,
                        11,
                        19
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        setButtonBackground(
                button,
                ORANGE
        );

        button.setOnMouseEntered(
                event ->
                        setButtonBackground(
                                button,
                                ORANGE_HOVER
                        )
        );

        button.setOnMouseExited(
                event ->
                        setButtonBackground(
                                button,
                                ORANGE
                        )
        );

        return button;
    }

    private Button createSecondaryButton(
            String text
    ) {

        Button button = new Button(text);

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
                        0,
                        18,
                        0,
                        18
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        setButtonBackground(
                button,
                BLUE
        );

        button.setOnMouseEntered(
                event ->
                        setButtonBackground(
                                button,
                                BLUE_HOVER
                        )
        );

        button.setOnMouseExited(
                event ->
                        setButtonBackground(
                                button,
                                BLUE
                        )
        );

        return button;
    }

    private Button createActionButton(
            String text,
            String color
    ) {

        Button button = new Button(text);

        button.setTextFill(
                Color.web(color)
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
                        8,
                        15,
                        8,
                        15
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        String background;
        String hover;

        if (color.equals(RED)) {

            background = "#FEE2E2";
            hover = "#FECACA";

        } else if (color.equals(GREEN)) {

            background = "#DCFCE7";
            hover = "#BBF7D0";

        } else {

            background = "#DBEAFE";
            hover = "#BFDBFE";
        }

        button.setStyle(
                "-fx-background-color: " +
                        background +
                        ";" +
                        "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                        hover +
                                        ";" +
                                        "-fx-background-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: " +
                                        background +
                                        ";" +
                                        "-fx-background-radius: 8;"
                        )
        );

        return button;
    }

    private void setButtonBackground(
            Button button,
            String color
    ) {

        button.setStyle(
                "-fx-background-color: " +
                        color +
                        ";" +
                        "-fx-background-radius: 9;"
        );
    }

    // =====================================================
    // FILTER
    // =====================================================

    private void applyFilters() {

        String search =
                searchField == null
                        ? ""
                        : searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        String selectedStatus =
                statusFilter == null
                        ? "All Status"
                        : statusFilter.getValue();

        String selectedType =
                typeFilter == null
                        ? "All Types"
                        : typeFilter.getValue();

        String sort =
                sortFilter == null
                        ? "Newest First"
                        : sortFilter.getValue();

        filteredNotifications.clear();

        for (
                NotificationData data :
                notifications
        ) {

            boolean searchMatch =
                    search.isEmpty()
                            ||
                            data.getId()
                                    .toLowerCase()
                                    .contains(search)
                            ||
                            data.getTitle()
                                    .toLowerCase()
                                    .contains(search)
                            ||
                            data.getRecipient()
                                    .toLowerCase()
                                    .contains(search)
                            ||
                            data.getType()
                                    .toLowerCase()
                                    .contains(search);

            boolean statusMatch =
                    selectedStatus.equals("All Status")
                            ||
                            data.getStatus()
                                    .equalsIgnoreCase(
                                            selectedStatus
                                    );

            boolean typeMatch =
                    selectedType.equals("All Types")
                            ||
                            data.getType()
                                    .equalsIgnoreCase(
                                            selectedType
                                    );

            if (
                    searchMatch &&
                            statusMatch &&
                            typeMatch
            ) {

                filteredNotifications.add(data);
            }
        }

        sortNotifications(sort);
        renderNotifications();
    }

    // =====================================================
    // SORT
    // =====================================================

    private void sortNotifications(
            String sort
    ) {

        if ("Title A-Z".equals(sort)) {

            filteredNotifications.sort(
                    (a, b) ->
                            a.getTitle()
                                    .compareToIgnoreCase(
                                            b.getTitle()
                                    )
            );

        } else if ("Title Z-A".equals(sort)) {

            filteredNotifications.sort(
                    (a, b) ->
                            b.getTitle()
                                    .compareToIgnoreCase(
                                            a.getTitle()
                                    )
            );

        } else if ("Oldest First".equals(sort)) {

            filteredNotifications.sort(
                    (a, b) ->
                            a.getDate()
                                    .compareTo(
                                            b.getDate()
                                    )
            );

        } else {

            filteredNotifications.sort(
                    (a, b) ->
                            b.getDate()
                                    .compareTo(
                                            a.getDate()
                                    )
            );
        }
    }

    // =====================================================
    // RENDER
    // =====================================================

    private void renderNotifications() {

        notificationCards
                .getChildren()
                .clear();

        if (filteredNotifications.isEmpty()) {

            notificationCards
                    .getChildren()
                    .add(
                            createEmptyState()
                    );

            resultCountLabel.setText(
                    "0 notifications found"
            );

            return;
        }

        int number = 1;

        for (
                NotificationData data :
                filteredNotifications
        ) {

            notificationCards
                    .getChildren()
                    .add(
                            createNotificationCard(
                                    data,
                                    number++
                            )
                    );
        }

        resultCountLabel.setText(
                filteredNotifications.size() == 1
                        ? "1 notification"
                        : filteredNotifications.size()
                        + " notifications"
        );
    }

    // =====================================================
    // SAMPLE DATA
    // =====================================================

    private void loadSampleNotifications() {

        notifications.clear();

        notifications.addAll(

                new NotificationData(
                        "N001",
                        "Service Request Accepted",
                        "Rahul Sharma",
                        "Service Request",
                        "Sent",
                        "2026-08-13"
                ),

                new NotificationData(
                        "N002",
                        "Mechanic Assigned",
                        "Akash Patil",
                        "Mechanic",
                        "Unread",
                        "2026-08-13"
                ),

                new NotificationData(
                        "N003",
                        "SOS Request Received",
                        "Pooja Mehta",
                        "SOS",
                        "Sent",
                        "2026-08-12"
                ),

                new NotificationData(
                        "N004",
                        "Service Completed",
                        "Vikram Joshi",
                        "Service",
                        "Read",
                        "2026-08-12"
                ),

                new NotificationData(
                        "N005",
                        "Complaint Updated",
                        "Suresh Jadhav",
                        "Complaint",
                        "Sent",
                        "2026-08-11"
                )
        );
    }

    // =====================================================
    // STATISTICS
    // =====================================================

    private void updateStatistics() {

        int total = notifications.size();
        int unread = 0;
        int sent = 0;
        int read = 0;

        for (
                NotificationData data :
                notifications
        ) {

            if (
                    data.getStatus()
                            .equalsIgnoreCase("Unread")
            ) {

                unread++;

            } else if (
                    data.getStatus()
                            .equalsIgnoreCase("Sent")
            ) {

                sent++;

            } else if (
                    data.getStatus()
                            .equalsIgnoreCase("Read")
            ) {

                read++;
            }
        }

        totalLabel.setText(
                String.valueOf(total)
        );

        unreadLabel.setText(
                String.valueOf(unread)
        );

        sentLabel.setText(
                String.valueOf(sent)
        );

        readLabel.setText(
                String.valueOf(read)
        );
    }

    // =====================================================
    // DETAIL INFO ROW
    // =====================================================

    private HBox createDetailInfoRow(
            String labelText,
            String valueText
    ) {

        HBox row = new HBox(15);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label label = new Label(
                labelText
        );

        label.setPrefWidth(85);

        label.setTextFill(
                Color.web(TEXT)
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        Label value = new Label(
                valueText
        );

        value.setTextFill(
                Color.web(HEADING)
        );

        value.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        value.setWrapText(true);

        HBox.setHgrow(
                value,
                Priority.ALWAYS
        );

        row.getChildren().addAll(
                label,
                value
        );

        return row;
    }

    // =====================================================
    // DELETE
    // =====================================================

    private void deleteNotification(
            NotificationData data
    ) {

        Alert confirm =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirm.setTitle(
                "Delete Notification"
        );

        confirm.setHeaderText(
                "Delete this notification?"
        );

        confirm.setContentText(
                data.getTitle()
        );

        confirm.showAndWait()
                .ifPresent(
                        result -> {

                            if (
                                    result ==
                                            ButtonType.OK
                            ) {

                                notifications.remove(data);

                                updateStatistics();
                                applyFilters();
                            }
                        }
                );
    }

    // =====================================================
    // CREATE NOTIFICATION
    // =====================================================

    private void showCreateNotificationDialog() {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "New Notification"
        );

        dialog.setHeaderText(
                "Create a system notification"
        );

        GridPane grid = new GridPane();

        grid.setHgap(12);
        grid.setVgap(14);

        grid.setPadding(
                new Insets(20)
        );

        TextField titleField = new TextField();

        titleField.setPromptText(
                "Notification title"
        );

        TextField recipientField = new TextField();

        recipientField.setPromptText(
                "Customer / recipient"
        );

        ComboBox<String> typeBox = new ComboBox<>();

        typeBox.getItems().addAll(
                "Service Request",
                "SOS",
                "Mechanic",
                "Service",
                "Complaint",
                "System"
        );

        typeBox.setValue(
                "System"
        );

        grid.add(
                new Label("Title"),
                0,
                0
        );

        grid.add(
                titleField,
                1,
                0
        );

        grid.add(
                new Label("Recipient"),
                0,
                1
        );

        grid.add(
                recipientField,
                1,
                1
        );

        grid.add(
                new Label("Type"),
                0,
                2
        );

        grid.add(
                typeBox,
                1,
                2
        );

        dialog.getDialogPane()
                .setContent(grid);

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        ButtonType.OK,
                        ButtonType.CANCEL
                );

        dialog.setResultConverter(
                button -> {

                    if (
                            button ==
                                    ButtonType.OK
                    ) {

                        String title =
                                titleField
                                        .getText()
                                        .trim();

                        if (title.isEmpty()) {

                            showAlert(
                                    "Validation",
                                    "Please enter notification title."
                            );

                            return null;
                        }

                        String recipient =
                                recipientField
                                        .getText()
                                        .trim();

                        if (recipient.isEmpty()) {

                            recipient = "All Customers";
                        }

                        notifications.add(
                                new NotificationData(
                                        createNextNotificationId(),
                                        title,
                                        recipient,
                                        typeBox.getValue(),
                                        "Unread",
                                        "2026-08-13"
                                )
                        );

                        updateStatistics();
                        applyFilters();
                    }

                    return button;
                }
        );

        dialog.showAndWait();
    }

    private String createNextNotificationId() {

        int max = 0;

        for (
                NotificationData data :
                notifications
        ) {

            String id = data.getId();

            if (
                    id != null &&
                            id.startsWith("N")
            ) {

                try {

                    int number =
                            Integer.parseInt(
                                    id.substring(1)
                            );

                    max =
                            Math.max(
                                    max,
                                    number
                            );

                } catch (
                        NumberFormatException ignored
                ) {
                }
            }
        }

        return String.format(
                "N%03d",
                max + 1
        );
    }

    // =====================================================
    // EMPTY STATE
    // =====================================================

    private VBox createEmptyState() {

        VBox box = new VBox(9);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(45)
        );

        Label icon = new Label(
                "N"
        );

        icon.setTextFill(
                Color.web(BLUE)
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );

        Label title = new Label(
                "No notifications found"
        );

        title.setTextFill(
                Color.web(HEADING)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        Label subtitle = new Label(
                "Try changing your search, status or type filter."
        );

        subtitle.setTextFill(
                Color.web(TEXT)
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        box.getChildren().addAll(
                icon,
                title,
                subtitle
        );

        return box;
    }

    // =====================================================
    // STYLING
    // =====================================================

    private void styleTextField(
            TextField field
    ) {

        field.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                        "-fx-text-fill: " + HEADING + ";" +
                        "-fx-prompt-text-fill: " + TEXT + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-padding: 0 14 0 14;" +
                        "-fx-font-size: 15px;"
        );
    }

    private void styleComboBox(
            ComboBox<String> combo
    ) {

        combo.setStyle(
                "-fx-background-color: " + SURFACE + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 14px;"
        );
    }

    // =====================================================
    // ALERT
    // =====================================================

    private void showAlert(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // =====================================================
    // MODEL
    // =====================================================

    public static class NotificationData {

        private String id;
        private String title;
        private String recipient;
        private String type;
        private String status;
        private String date;

        public NotificationData(
                String id,
                String title,
                String recipient,
                String type,
                String status,
                String date
        ) {

            this.id = id;
            this.title = title;
            this.recipient = recipient;
            this.type = type;
            this.status = status;
            this.date = date;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getRecipient() {
            return recipient;
        }

        public String getType() {
            return type;
        }

        public String getStatus() {
            return status;
        }

        public String getDate() {
            return date;
        }

        public void setStatus(
                String status
        ) {

            this.status = status;
        }
    }
}
 
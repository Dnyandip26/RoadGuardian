package project.ui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ReviewManagementPage {

    // =========================================================
    // ROADGUARDIAN THEME
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

    private final ObservableList<ReviewData> reviews =
            FXCollections.observableArrayList();

    private final ObservableList<ReviewData> filteredReviews =
            FXCollections.observableArrayList();

    private VBox reviewCards;

    private TextField searchField;
    private ComboBox<String> ratingFilter;
    private ComboBox<String> sortFilter;

    private Label resultCountLabel;

    private Label totalLabel;
    private Label fiveStarLabel;
    private Label averageLabel;

    // =========================================================
    // VIEW
    // =========================================================

    public VBox getView() {

        VBox root = new VBox(20);

        root.setPadding(
                new Insets(30, 32, 32, 32)
        );

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        HBox header = createHeader();

        HBox stats = createStatistics();

        HBox toolbar = createToolbar();

        VBox recordsCard = createRecordsCard();

        root.getChildren().addAll(
                header,
                stats,
                toolbar,
                recordsCard
        );

        VBox.setVgrow(
                recordsCard,
                Priority.ALWAYS
        );

        loadSampleReviews();

        updateStatistics();

        applyFilters();

        return root;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private HBox createHeader() {

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox = new VBox(5);

        Label title =
                new Label("Reviews");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        title.setTextFill(
                Color.web(HEADING)
        );

        Label subtitle =
                new Label(
                        "Monitor customer feedback, ratings, and service experience."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        subtitle.setTextFill(
                Color.web(TEXT)
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

        Button refresh =
                createPrimaryButton(
                        "Refresh"
                );

        refresh.setOnAction(
                event -> {

                    loadSampleReviews();

                    updateStatistics();

                    applyFilters();
                }
        );

        header.getChildren().addAll(
                titleBox,
                spacer,
                refresh
        );

        return header;
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private HBox createStatistics() {

        HBox stats = new HBox(16);

        totalLabel =
                createValueLabel(BLUE);

        fiveStarLabel =
                createValueLabel(ORANGE);

        averageLabel =
                createValueLabel(GREEN);

        VBox total =
                createStatCard(
                        "Total Reviews",
                        "All customer feedback",
                        totalLabel,
                        BLUE,
                        "TOTAL"
                );

        VBox fiveStar =
                createStatCard(
                        "5 ★ Reviews",
                        "Excellent experiences",
                        fiveStarLabel,
                        ORANGE,
                        "5 STAR"
                );

        VBox average =
                createStatCard(
                        "Average Rating",
                        "Overall customer score",
                        averageLabel,
                        GREEN,
                        "RATING"
                );

        HBox.setHgrow(
                total,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                fiveStar,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                average,
                Priority.ALWAYS
        );

        stats.getChildren().addAll(
                total,
                fiveStar,
                average
        );

        return stats;
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
                new Insets(18, 20, 17, 20)
        );

        card.setMinHeight(118);

        String normalStyle =
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;";

        String hoverStyle =
                "-fx-background-color: #F7FCFC;" +
                "-fx-border-color: " +
                color +
                ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;" +
                "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.14), 10, 0.12, 0, 2);";

        card.setStyle(
                normalStyle
        );

        card.setCursor(
                Cursor.HAND
        );

        HBox top = new HBox();

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel =
                new Label(title);

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

        Label tag =
                new Label(tagText);

        tag.setTextFill(
                Color.web(color)
        );

        tag.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        tag.setPadding(
                new Insets(6, 10, 6, 10)
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

        Label subtitleLabel =
                new Label(subtitle);

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

                    card.setStyle(
                            hoverStyle
                    );

                    card.setTranslateY(-2);
                }
        );

        card.setOnMouseExited(
                event -> {

                    card.setStyle(
                            normalStyle
                    );

                    card.setTranslateY(0);
                }
        );

        return card;
    }

    // =========================================================
    // TOOLBAR
    // =========================================================

    private HBox createToolbar() {

        HBox toolbar = new HBox(12);

        toolbar.setAlignment(
                Pos.CENTER_LEFT
        );

        searchField =
                new TextField();

        searchField.setPromptText(
                "Search customer, service or review..."
        );

        searchField.setPrefHeight(44);

        searchField.setPrefWidth(430);

        styleTextField(
                searchField
        );

        ratingFilter =
                new ComboBox<>();

        ratingFilter.getItems().addAll(
                "All Ratings",
                "5 Stars",
                "4 Stars",
                "3 Stars",
                "2 Stars",
                "1 Star"
        );

        ratingFilter.setValue(
                "All Ratings"
        );

        ratingFilter.setPrefHeight(44);

        ratingFilter.setPrefWidth(145);

        styleComboBox(
                ratingFilter
        );

        sortFilter =
                new ComboBox<>();

        sortFilter.getItems().addAll(
                "Newest First",
                "Oldest First",
                "Highest Rating",
                "Lowest Rating",
                "Customer A-Z"
        );

        sortFilter.setValue(
                "Newest First"
        );

        sortFilter.setPrefHeight(44);

        sortFilter.setPrefWidth(155);

        styleComboBox(
                sortFilter
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        searchField.textProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        ratingFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        sortFilter.valueProperty()
                .addListener(
                        (obs, oldValue, newValue) ->
                                applyFilters()
                );

        toolbar.getChildren().addAll(
                searchField,
                ratingFilter,
                sortFilter,
                spacer
        );

        return toolbar;
    }

    // =========================================================
    // RECORDS CARD
    // =========================================================

    private VBox createRecordsCard() {

        VBox card = new VBox(15);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: " +
                SURFACE +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;"
        );

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox =
                new VBox(4);

        Label title =
                new Label(
                        "Customer Reviews"
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

        Label subtitle =
                new Label(
                        "Customer feedback received for RoadGuardian services."
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

        resultCountLabel =
                new Label(
                        "0 reviews"
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

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label live =
                new Label(
                        "● Customer Feedback"
                );

        live.setTextFill(
                Color.web(GREEN)
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
                GREEN +
                "18;" +
                "-fx-background-radius: 20;"
        );

        heading.getChildren().addAll(
                titleBox,
                spacer,
                live
        );

        reviewCards =
                new VBox(12);

        reviewCards.setFillWidth(
                true
        );

        reviewCards.setPadding(
                new Insets(
                        3,
                        2,
                        10,
                        2
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        reviewCards
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

    // =========================================================
    // REVIEW CARD
    // =========================================================

    private VBox createReviewCard(
            ReviewData data,
            int number
    ) {

        VBox card =
                new VBox(13);

        card.setPadding(
                new Insets(
                        18,
                        20,
                        18,
                        20
                )
        );

        card.setCursor(
                Cursor.HAND
        );

        String normalStyle =
                "-fx-background-color: " +
                SECONDARY +
                ";" +
                "-fx-border-color: " +
                BORDER +
                ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;";

        String hoverStyle =
                "-fx-background-color: #EAF8FA;" +
                "-fx-border-color: " +
                ORANGE +
                ";" +
                "-fx-border-radius: 13;" +
                "-fx-background-radius: 13;" +
                "-fx-effect: dropshadow(gaussian, rgba(249,115,22,0.17), 12, 0.15, 0, 3);";

        card.setStyle(
                normalStyle
        );

        // -----------------------------------------------------
        // TOP
        // -----------------------------------------------------

        HBox top =
                new HBox(13);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox avatar =
                createAvatar(
                        data.getCustomer()
                );

        VBox identity =
                new VBox(5);

        HBox idLine =
                new HBox(9);

        idLine.setAlignment(
                Pos.CENTER_LEFT
        );

        Label serial =
                new Label(
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

        Label customer =
                new Label(
                        data.getCustomer()
                );

        customer.setTextFill(
                Color.web(HEADING)
        );

        customer.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        idLine.getChildren().addAll(
                serial,
                customer
        );

        Label service =
                new Label(
                        "Service: " +
                        data.getService()
                );

        service.setTextFill(
                Color.web(TEXT)
        );

        service.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        identity.getChildren().addAll(
                idLine,
                service
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        VBox ratingBox =
                createRatingBox(
                        data.getRating()
                );

        top.getChildren().addAll(
                avatar,
                identity,
                spacer,
                ratingBox
        );

        // -----------------------------------------------------
        // REVIEW
        // -----------------------------------------------------

        VBox reviewBox =
                new VBox(6);

        Label reviewTitle =
                new Label(
                        "CUSTOMER REVIEW"
                );

        reviewTitle.setTextFill(
                Color.web(TEXT)
        );

        reviewTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        Label reviewText =
                new Label(
                        "\"" +
                        data.getReview() +
                        "\""
                );

        reviewText.setTextFill(
                Color.web(HEADING)
        );

        reviewText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        15
                )
        );

        reviewText.setWrapText(
                true
        );

        reviewBox.getChildren().addAll(
                reviewTitle,
                reviewText
        );

        // -----------------------------------------------------
        // INFO
        // -----------------------------------------------------

        HBox infoRow =
                new HBox(12);

        VBox ratingInfo =
                createInfoBox(
                        "RATING",
                        data.getRating() +
                        " / 5"
                );

        VBox dateInfo =
                createInfoBox(
                        "DATE",
                        data.getDate()
                );

        VBox serviceInfo =
                createInfoBox(
                        "SERVICE",
                        data.getService()
                );

        HBox.setHgrow(
                ratingInfo,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                dateInfo,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                serviceInfo,
                Priority.ALWAYS
        );

        infoRow.getChildren().addAll(
                ratingInfo,
                dateInfo,
                serviceInfo
        );

        // -----------------------------------------------------
        // BOTTOM
        // -----------------------------------------------------

        HBox bottom =
                new HBox(10);

        bottom.setAlignment(
                Pos.CENTER_RIGHT
        );

        Region bottomSpacer =
                new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );

        Button view =
                createActionButton(
                        "View Review",
                        BLUE
                );

        Button delete =
                createActionButton(
                        "Delete",
                        RED
                );

        view.setOnAction(
                event ->
                        showReview(
                                data
                        )
        );

        delete.setOnAction(
                event ->
                        deleteReview(
                                data
                        )
        );

        bottom.getChildren().addAll(
                bottomSpacer,
                view,
                delete
        );

        card.getChildren().addAll(
                top,
                new Separator(),
                reviewBox,
                infoRow,
                bottom
        );

        // -----------------------------------------------------
        // HOVER
        // -----------------------------------------------------

        card.setOnMouseEntered(
                event -> {

                    card.setStyle(
                            hoverStyle
                    );

                    card.setTranslateY(-2);
                }
        );

        card.setOnMouseExited(
                event -> {

                    card.setStyle(
                            normalStyle
                    );

                    card.setTranslateY(0);
                }
        );

        // -----------------------------------------------------
        // DOUBLE CLICK
        // -----------------------------------------------------

        card.setOnMouseClicked(
                event -> {

                    if (
                            event.getClickCount() == 2
                    ) {

                        showReview(
                                data
                        );
                    }
                }
        );

        return card;
    }

    // =========================================================
    // AVATAR
    // =========================================================

    private VBox createAvatar(
            String customer
    ) {

        VBox avatar =
                new VBox();

        avatar.setAlignment(
                Pos.CENTER
        );

        avatar.setMinSize(
                50,
                50
        );

        avatar.setPrefSize(
                50,
                50
        );

        avatar.setMaxSize(
                50,
                50
        );

        avatar.setStyle(
                "-fx-background-color: " +
                BLUE +
                ";" +
                "-fx-background-radius: 25;"
        );

        String letter =
                customer == null ||
                customer.isBlank()
                        ? "?"
                        : customer
                                .trim()
                                .substring(
                                        0,
                                        1
                                )
                                .toUpperCase();

        Label label =
                new Label(
                        letter
                );

        label.setTextFill(
                Color.WHITE
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        avatar.getChildren().add(
                label
        );

        return avatar;
    }

    // =========================================================
    // RATING BOX
    // =========================================================

    private VBox createRatingBox(
            int rating
    ) {

        VBox box =
                new VBox(2);

        box.setAlignment(
                Pos.CENTER_RIGHT
        );

        StringBuilder stars =
                new StringBuilder();

        for (
                int i = 1;
                i <= 5;
                i++
        ) {

            stars.append(
                    i <= rating
                            ? "★"
                            : "☆"
            );
        }

        Label starsLabel =
                new Label(
                        stars.toString()
                );

        starsLabel.setTextFill(
                Color.web(ORANGE)
        );

        starsLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        Label score =
                new Label(
                        rating + " / 5"
                );

        score.setTextFill(
                Color.web(TEXT)
        );

        score.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        box.getChildren().addAll(
                starsLabel,
                score
        );

        return box;
    }

    // =========================================================
    // INFO BOX
    // =========================================================

    private VBox createInfoBox(
            String title,
            String value
    ) {

        VBox box =
                new VBox(5);

        box.setPadding(
                new Insets(
                        10,
                        12,
                        10,
                        12
                )
        );

        box.setStyle(
                "-fx-background-color: " +
                SURFACE +
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
                        value
                );

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
    // BUTTON
    // =========================================================

    private Button createPrimaryButton(
            String text
    ) {

        Button button =
                new Button(
                        text
                );

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

        setButtonStyle(
                button,
                BLUE
        );

        button.setOnMouseEntered(
                event ->
                        setButtonStyle(
                                button,
                                BLUE_HOVER
                        )
        );

        button.setOnMouseExited(
                event ->
                        setButtonStyle(
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

        Button button =
                new Button(
                        text
                );

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

        String background =
                color.equals(RED)
                        ? "#FEE2E2"
                        : "#DBEAFE";

        String hover =
                color.equals(RED)
                        ? "#FECACA"
                        : "#BFDBFE";

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

    private void setButtonStyle(
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

    // =========================================================
    // FILTER
    // =========================================================

    private void applyFilters() {

        String search =
                searchField == null
                        ? ""
                        : searchField
                                .getText()
                                .trim()
                                .toLowerCase();

        String selectedRating =
                ratingFilter == null
                        ? "All Ratings"
                        : ratingFilter.getValue();

        String sort =
                sortFilter == null
                        ? "Newest First"
                        : sortFilter.getValue();

        filteredReviews.clear();

        for (
                ReviewData data :
                reviews
        ) {

            boolean searchMatch =
                    search.isEmpty()
                    ||
                    data.getCustomer()
                            .toLowerCase()
                            .contains(search)
                    ||
                    data.getService()
                            .toLowerCase()
                            .contains(search)
                    ||
                    data.getReview()
                            .toLowerCase()
                            .contains(search);

            boolean ratingMatch =
                    selectedRating.equals(
                            "All Ratings"
                    )
                    ||
                    selectedRating.startsWith(
                            String.valueOf(
                                    data.getRating()
                            )
                    );

            if (
                    searchMatch &&
                    ratingMatch
            ) {

                filteredReviews.add(
                        data
                );
            }
        }

        sortReviews(
                sort
        );

        renderReviews();
    }

    // =========================================================
    // SORT
    // =========================================================

    private void sortReviews(
            String sort
    ) {

        if (
                "Highest Rating".equals(sort)
        ) {

            filteredReviews.sort(
                    (a, b) ->
                            Integer.compare(
                                    b.getRating(),
                                    a.getRating()
                            )
            );

        } else if (
                "Lowest Rating".equals(sort)
        ) {

            filteredReviews.sort(
                    (a, b) ->
                            Integer.compare(
                                    a.getRating(),
                                    b.getRating()
                            )
            );

        } else if (
                "Customer A-Z".equals(sort)
        ) {

            filteredReviews.sort(
                    (a, b) ->
                            a.getCustomer()
                                    .compareToIgnoreCase(
                                            b.getCustomer()
                                    )
            );

        } else if (
                "Oldest First".equals(sort)
        ) {

            filteredReviews.sort(
                    (a, b) ->
                            a.getDate()
                                    .compareTo(
                                            b.getDate()
                                    )
            );

        } else {

            filteredReviews.sort(
                    (a, b) ->
                            b.getDate()
                                    .compareTo(
                                            a.getDate()
                                    )
            );
        }
    }

    // =========================================================
    // RENDER
    // =========================================================

    private void renderReviews() {

        reviewCards
                .getChildren()
                .clear();

        if (
                filteredReviews.isEmpty()
        ) {

            reviewCards
                    .getChildren()
                    .add(
                            createEmptyState()
                    );

            resultCountLabel.setText(
                    "0 reviews found"
            );

            return;
        }

        int number = 1;

        for (
                ReviewData data :
                filteredReviews
        ) {

            reviewCards
                    .getChildren()
                    .add(
                            createReviewCard(
                                    data,
                                    number++
                            )
                    );
        }

        resultCountLabel.setText(
                filteredReviews.size() == 1
                        ? "1 review"
                        : filteredReviews.size()
                          + " reviews"
        );
    }

    // =========================================================
    // SAMPLE DATA
    // =========================================================

    private void loadSampleReviews() {

        reviews.clear();

        reviews.addAll(

                new ReviewData(
                        "Rahul Sharma",
                        "Battery Assistance",
                        5,
                        "Excellent and very quick service.",
                        "2026-08-13"
                ),

                new ReviewData(
                        "Akash Patil",
                        "Tyre Repair",
                        4,
                        "Mechanic arrived on time and repaired the tyre properly.",
                        "2026-08-12"
                ),

                new ReviewData(
                        "Pooja Mehta",
                        "Towing Service",
                        5,
                        "Very helpful during emergency. The mechanic was professional.",
                        "2026-08-12"
                ),

                new ReviewData(
                        "Vikram Joshi",
                        "Fuel Delivery",
                        3,
                        "Service was okay but response time could be better.",
                        "2026-08-11"
                ),

                new ReviewData(
                        "Suresh Jadhav",
                        "Engine Breakdown",
                        5,
                        "Professional mechanic and excellent roadside support.",
                        "2026-08-10"
                ),

                new ReviewData(
                        "Neha Kulkarni",
                        "Battery Assistance",
                        4,
                        "Good service and friendly mechanic.",
                        "2026-08-09"
                ),

                new ReviewData(
                        "Amit Deshmukh",
                        "Lockout Assistance",
                        5,
                        "Solved my problem very quickly.",
                        "2026-08-08"
                )
        );
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private void updateStatistics() {

        int total =
                reviews.size();

        int fiveStar = 0;

        int ratingSum = 0;

        for (
                ReviewData data :
                reviews
        ) {

            ratingSum +=
                    data.getRating();

            if (
                    data.getRating() == 5
            ) {

                fiveStar++;
            }
        }

        double average =
                total == 0
                        ? 0
                        : (double) ratingSum / total;

        totalLabel.setText(
                String.valueOf(total)
        );

        fiveStarLabel.setText(
                String.valueOf(fiveStar)
        );

        averageLabel.setText(
                String.format(
                        "%.1f ★",
                        average
                )
        );
    }

    // =========================================================
    // VIEW
    // =========================================================

    private void showReview(
            ReviewData data
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Review Details"
        );

        alert.setHeaderText(
                data.getCustomer()
        );

        alert.setContentText(
                "Service: " +
                data.getService() +
                "\n\n" +
                "Rating: " +
                data.getRating() +
                " / 5" +
                "\n\n" +
                "Review:\n" +
                data.getReview() +
                "\n\n" +
                "Date: " +
                data.getDate()
        );

        alert.showAndWait();
    }

    // =========================================================
    // DELETE
    // =========================================================

    private void deleteReview(
            ReviewData data
    ) {

        Alert confirm =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirm.setTitle(
                "Delete Review"
        );

        confirm.setHeaderText(
                "Delete this review?"
        );

        confirm.setContentText(
                "Review by " +
                data.getCustomer() +
                " will be removed."
        );

        confirm.showAndWait()
                .ifPresent(
                        result -> {

                            if (
                                    result ==
                                    ButtonType.OK
                            ) {

                                reviews.remove(
                                        data
                                );

                                updateStatistics();

                                applyFilters();
                            }
                        }
                );
    }

    // =========================================================
    // EMPTY STATE
    // =========================================================

    private VBox createEmptyState() {

        VBox box =
                new VBox(9);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(45)
        );

        Label icon =
                new Label("★");

        icon.setTextFill(
                Color.web(ORANGE)
        );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );

        Label title =
                new Label(
                        "No reviews found"
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

        Label subtitle =
                new Label(
                        "Try changing your search or rating filter."
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

    // =========================================================
    // STYLING
    // =========================================================

    private void styleTextField(
            TextField field
    ) {

        field.setStyle(
                "-fx-background-color: " +
                SURFACE +
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
                "-fx-font-size: 15px;"
        );
    }

    private void styleComboBox(
            ComboBox<String> combo
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

    // =========================================================
    // MODEL
    // =========================================================

    public static class ReviewData {

        private String customer;
        private String service;
        private int rating;
        private String review;
        private String date;

        public ReviewData(
                String customer,
                String service,
                int rating,
                String review,
                String date
        ) {

            this.customer = customer;
            this.service = service;
            this.rating = rating;
            this.review = review;
            this.date = date;
        }

        public String getCustomer() {
            return customer;
        }

        public String getService() {
            return service;
        }

        public int getRating() {
            return rating;
        }

        public String getReview() {
            return review;
        }

        public String getDate() {
            return date;
        }
    }
}
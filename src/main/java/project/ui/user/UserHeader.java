package project.ui.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public final class UserHeader {

    private static final String MAIN_BACKGROUND = "#D6F0F3";
    private static final String BORDER = "#D5E0E7";
    private static final String HEADING = "#172033";
    private static final String NAV_BLUE = "#2869E8";
    private static final String SECONDARY_TEXT = "#737D8D";
    private static final String SECONDARY_SURFACE = "#E8EFFF";
    private static final String EMERGENCY = "#F04444";

    private UserHeader() {
    }

    public static HBox createHeader() {

        // ========================================================
        // TOP HEADER
        // ========================================================

        HBox topBar = new HBox();

        topBar.setAlignment(Pos.CENTER_LEFT);

        topBar.setPadding(
                new Insets(10, 28, 10, 28)
        );

        topBar.setSpacing(18);

        // IMPORTANT: Dashboard exact height
        topBar.setMinHeight(76);
        topBar.setPrefHeight(76);
        topBar.setMaxHeight(76);

        topBar.setStyle(
                "-fx-background-color: " + MAIN_BACKGROUND + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        // ========================================================
        // LOGO
        // ========================================================

        StackPane logo = new StackPane();

        Circle logoCircle =
                new Circle(21);

        logoCircle.setFill(
                Color.web(NAV_BLUE)
        );

        Label shield =
                new Label("✓");

        shield.setTextFill(
                Color.WHITE
        );

        shield.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        shield.setTranslateY(-1);

        logo.getChildren().addAll(
                logoCircle,
                shield
        );

        logo.setMinSize(44, 44);
        logo.setPrefSize(44, 44);
        logo.setMaxSize(44, 44);

        // ========================================================
        // BRAND
        // ========================================================

        HBox brand =
                new HBox(0);

        brand.setAlignment(
                Pos.CENTER_LEFT
        );

        Label road =
                new Label("Road");

        road.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        road.setTextFill(
                Color.web(HEADING)
        );

        road.setPadding(
                new Insets(0)
        );

        Label guardian =
                new Label("Guardian");

        guardian.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        guardian.setTextFill(
                Color.web(NAV_BLUE)
        );

        guardian.setPadding(
                new Insets(0)
        );

        brand.getChildren().addAll(
                road,
                guardian
        );

        HBox logoSection =
                new HBox(
                        11,
                        logo,
                        brand
                );

        logoSection.setAlignment(
                Pos.CENTER_LEFT
        );

        logoSection.setMinHeight(46);
        logoSection.setPrefHeight(46);

        // ========================================================
        // SPACER
        // ========================================================

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // ========================================================
        // SEARCH
        // ========================================================

        HBox searchBox =
                new HBox();

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.setPrefWidth(420);
        searchBox.setMinWidth(420);
        searchBox.setMaxWidth(420);

        searchBox.setMinHeight(42);
        searchBox.setPrefHeight(42);
        searchBox.setMaxHeight(42);

        searchBox.setPadding(
                new Insets(0, 15, 0, 15)
        );

        searchBox.setSpacing(9);

        searchBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 24;" +
                "-fx-background-radius: 24;"
        );

        Label searchIcon =
                new Label("⌕");

        searchIcon.setFont(
                Font.font(
                        "Arial",
                        24
                )
        );

        searchIcon.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        Label searchText =
                new Label(
                        "Search mechanics, invoices, vehicles..."
                );

        searchText.setFont(
                Font.font(15)
        );

        searchText.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        searchBox.getChildren().addAll(
                searchIcon,
                searchText
        );

        // ========================================================
        // NOTIFICATION
        // ========================================================

        StackPane notification =
                new StackPane();

        notification.setPrefSize(
                28,
                42
        );

        Label bell =
                new Label("🔔");

        bell.setFont(
                Font.font(
                        "Arial",
                        22
                )
        );

        bell.setTextFill(
                Color.web(HEADING)
        );

        Circle notificationDot =
                new Circle(
                        5,
                        Color.web(EMERGENCY)
                );

        StackPane.setAlignment(
                notificationDot,
                Pos.TOP_RIGHT
        );

        notificationDot.setTranslateX(-1);
        notificationDot.setTranslateY(10);

        notification.getChildren().addAll(
                bell,
                notificationDot
        );

        // ========================================================
        // PROFILE
        // ========================================================

        HBox profile =
                new HBox(9);

        profile.setAlignment(
                Pos.CENTER_LEFT
        );

        profile.setPadding(
                new Insets(
                        5,
                        12,
                        5,
                        6
                )
        );

        profile.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 25;" +
                "-fx-background-radius: 25;"
        );

        StackPane avatar =
                new StackPane();

        Circle avatarCircle =
                new Circle(19);

        avatarCircle.setFill(
                Color.web(SECONDARY_SURFACE)
        );

        Label initials =
                new Label("AN");

        initials.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        initials.setTextFill(
                Color.web(NAV_BLUE)
        );

        avatar.getChildren().addAll(
                avatarCircle,
                initials
        );

        VBox profileText =
                new VBox(1);

        Label name =
                new Label("Aarav Nair");

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        name.setTextFill(
                Color.web(HEADING)
        );

        Label role =
                new Label(
                        "Customer · Pune"
                );

        role.setFont(
                Font.font(11)
        );

        role.setTextFill(
                Color.web(SECONDARY_TEXT)
        );

        profileText.getChildren().addAll(
                name,
                role
        );

        profile.getChildren().addAll(
                avatar,
                profileText
        );

        // ========================================================
        // ADD EVERYTHING
        // ========================================================

        topBar.getChildren().addAll(
                logoSection,
                spacer,
                searchBox,
                notification,
                profile
        );

        return topBar;
    }
}
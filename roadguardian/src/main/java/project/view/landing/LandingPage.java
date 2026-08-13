package project.view.landing;

import project.controller.LandingController;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LandingPage {

    private final BorderPane root;

    private final Stage stage;

    public LandingPage(Stage stage) {

        this.stage = stage;
        this.root = new BorderPane();

        LandingController controller =
                new LandingController(stage);

        createPage(controller);
    }

    private void createPage(LandingController controller) {

        // =====================================================
        // FULL PAGE BACKGROUND IMAGE
        // =====================================================

        Image backgroundImage =
                new Image(
                        "file:roadguardian\\src\\main\\resources\\images\\carBg.png"
                );

        System.out.println(
                "Background Image Error: " +
                backgroundImage.isError()
        );

        BackgroundImage background =
                new BackgroundImage(
                        backgroundImage,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(
                                1.0,
                                1.0,
                                true,
                                true,
                                false,
                                true
                        )
                );

        root.setBackground(
                new Background(background)
        );

        // =====================================================
        // NAVBAR
        // =====================================================

        Button loginButton =
                new Button("Login");

        loginButton.setStyle(
                "-fx-background-color: rgba(238,249,250,0.88);" +
                "-fx-border-color: #2563EB;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: #2563EB;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 9px 22px;" +
                "-fx-cursor: hand;"
        );

        Button registerButton =
                new Button("Create Account");

        registerButton.setStyle(
                "-fx-background-color: #F97316;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 9px 22px;" +
                "-fx-cursor: hand;"
        );

        // =====================================================
        // NAVIGATION BUTTONS - LEFT
        // =====================================================

        HBox navigationButtons =
                new HBox(
                        12,
                        loginButton,
                        registerButton
                );

        navigationButtons.setAlignment(
                Pos.CENTER_RIGHT
        );

        // =====================================================
        // ROADGUARDIAN LOGO - RIGHT
        // =====================================================

        Text roadText =
                new Text("Road");

        roadText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #172033;"
        );

        Text guardianText =
                new Text("Guardian");

        guardianText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #2563EB;"
        );

        HBox logo =
                new HBox(
                        roadText,
                        guardianText
                );

        logo.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // NAVBAR SPACER
        // =====================================================

        Region navbarSpacer =
                new Region();

        HBox.setHgrow(
                navbarSpacer,
                javafx.scene.layout.Priority.ALWAYS
        );

        // =====================================================
        // NAVBAR
        // =====================================================

        HBox navbar =
                new HBox(
                        logo,
                        navbarSpacer,
                        navigationButtons
                );

        navbar.setAlignment(
                Pos.CENTER
        );

        navbar.setPadding(
                new Insets(
                        13,
                        30,
                        13,
                        30
                )
        );

        /*
         * IMPORTANT:
         * Navbar is now transparent.
         * Background image will be visible behind navbar.
         */
        // navbar.setStyle(
        //         "-fx-background-color: rgba(238,249,250,0.35);" +
        //         "-fx-background-radius: 12px;" +
        //         "-fx-border-color: rgba(184,212,217,0.55);" +
        //         "-fx-border-radius: 12px;"
        // );

        // =====================================================
        // HERO HEADING
        // =====================================================

        Text heading =
                new Text(
                        "Your Roadside Safety\nCompanion"
                );

        heading.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 44px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #172033;"
        );

        // =====================================================
        // SMALL BLUE LINE
        // =====================================================

        Region headingLine =
                new Region();

        headingLine.setPrefWidth(65);
        headingLine.setPrefHeight(3);

        headingLine.setStyle(
                "-fx-background-color: #2563EB;" +
                "-fx-background-radius: 5px;"
        );

        // =====================================================
        // HERO DESCRIPTION
        // =====================================================

        Text description =
                new Text(
                        "RoadGuardian helps you get assistance when " +
                        "your vehicle breaks down, connects you with " +
                        "nearby mechanics and keeps your journey safer."
                );

        description.setWrappingWidth(
                500
        );

        description.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 17px;" +
                "-fx-fill: #526274;"
        );

        // =====================================================
        // GET STARTED BUTTON
        // =====================================================

        Button getStartedButton =
                new Button("Get Started");

        getStartedButton.setStyle(
                "-fx-background-color: #F97316;" +
                "-fx-background-radius: 8px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12px 30px;" +
                "-fx-cursor: hand;"
        );

        // =====================================================
        // BECOME MECHANIC BUTTON
        // =====================================================

        Button becomeMechanicButton =
                new Button("Become a Mechanic");

        becomeMechanicButton.setStyle(
                "-fx-background-color: rgba(238,249,250,0.82);" +
                "-fx-border-color: #2563EB;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-text-fill: #2563EB;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12px 30px;" +
                "-fx-cursor: hand;"
        );

        // =====================================================
        // HERO BUTTONS
        // =====================================================

        HBox heroButtons =
                new HBox(
                        15,
                        getStartedButton,
                        becomeMechanicButton
                );

        heroButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // HERO TEXT
        // =====================================================

        VBox heroText =
                new VBox(
                        20,
                        heading,
                        headingLine,
                        description,
                        heroButtons
                );

        heroText.setAlignment(
                Pos.CENTER_LEFT
        );

        heroText.setPrefWidth(
                600
        );

        // =====================================================
        // HERO SECTION
        // =====================================================

        HBox heroSection =
                new HBox(
                        heroText
                );

        heroSection.setAlignment(
                Pos.CENTER_LEFT
        );

        /*
         * Transparent so background image remains visible.
         */
        heroSection.setStyle(
                "-fx-background-color: transparent;"
        );

        heroSection.setPadding(
                new Insets(
                        55,
                        80,
                        40,
                        80
                )
        );

        // =====================================================
        // WHY ROADGUARDIAN
        // =====================================================

        Text featureSectionTitle =
                new Text(
                        "Why RoadGuardian?"
                );

        featureSectionTitle.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #172033;"
        );

        // =====================================================
        // FEATURES
        // =====================================================

        VBox sosFeature =
                createFeature(
                        "Emergency Assistance",
                        "Get roadside help when you need it.",
                        "#DC2626"
                );

        VBox diagnosisFeature =
                createFeature(
                        
                        "AI Breakdown Diagnosis",
                        "Understand possible vehicle problems.",
                        "#2563EB"
                );

        VBox mechanicFeature =
                createFeature(
                        "Smart Mechanic Dispatch",
                        "Connect with nearby mechanics.",
                        "#16A34A"
                );

        VBox trackingFeature =
                createFeature(
                        "Live Tracking",
                        "Track your assigned mechanic.",
                        "#2563EB"
                );

        // =====================================================
        // FEATURES BOX
        // =====================================================

        HBox features =
                new HBox(
                        25,
                        sosFeature,
                        diagnosisFeature,
                        mechanicFeature,
                        trackingFeature
                );

        features.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // FEATURE SECTION
        // =====================================================

        VBox featureSection =
                new VBox(
                        20,
                        featureSectionTitle,
                        features
                );

        featureSection.setAlignment(
                Pos.CENTER_LEFT
        );

        featureSection.setPadding(
                new Insets(
                        85,
                        30,
                        0,
                        30
                )
        );

        /*
         * IMPORTANT:
         * Only a LIGHT translucent overlay.
         *
         * This means:
         * - Cards remain readable
         * - Background image remains visible
         * - Car will NOT disappear
         */
        // featureSection.setStyle(
        //         "-fx-background-color: rgba(214,240,243,0.58);" +
        //         "-fx-border-color: rgba(184,212,217,0.35);" +
        //         "-fx-border-width: 1 0 1 0;"
        // );

        // =====================================================
        // FOOTER
        // =====================================================

        Text footerText =
                new Text(
                        "© 2026 RoadGuardian. All rights reserved."
                );

        footerText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 13px;" +
                "-fx-fill: #526274;"
        );

        HBox footer =
                new HBox(
                        footerText
                );

        footer.setAlignment(
                Pos.CENTER
        );

        footer.setPadding(
                new Insets(10)
        );

        /*
         * IMPORTANT:
         * Footer also transparent.
         * Background image remains visible.
         */
        footer.setStyle(
                "-fx-background-color: rgba(223,243,245,0.38);" +
                "-fx-border-color: rgba(184,212,217,0.45);" +
                "-fx-border-width: 1 0 0 0;"
        );

        // =====================================================
        // BUTTON HOVER
        // =====================================================

        addHover(
                loginButton,
                false,
                14
        );

        addHover(
                registerButton,
                true,
                14
        );

        addHover(
                getStartedButton,
                true,
                16
        );

        addHover(
                becomeMechanicButton,
                false,
                16
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        loginButton.setOnAction(
                e -> controller.openLoginPage()
        );

        registerButton.setOnAction(
                e -> controller.openRegisterPage()
        );

        getStartedButton.setOnAction(
                e -> controller.openRegisterPage()
        );

        becomeMechanicButton.setOnAction(
                e -> controller.openMechanicRegisterPage()
        );

        // =====================================================
        // PAGE LAYOUT
        // =====================================================

        root.setTop(
                navbar
        );

        VBox centerContent =
                new VBox(
                        heroSection,
                        featureSection
                );

        /*
         * Transparent center.
         */
        centerContent.setStyle(
                "-fx-background-color: transparent;"
        );

        root.setCenter(
                centerContent
        );

        root.setBottom(
                footer
        );
    }

    // =====================================================
    // HOVER METHOD
    // =====================================================

    private void addHover(
            Button button,
            boolean orangeButton,
            int fontSize
    ) {

        button.setOnMouseEntered(
                e -> {

                    if (orangeButton) {

                        button.setStyle(
                                "-fx-background-color: #EA580C;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-family: 'Segoe UI';" +
                                "-fx-font-size: " +
                                fontSize +
                                "px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 12px 30px;" +
                                "-fx-cursor: hand;"
                        );

                    } else {

                        button.setStyle(
                                "-fx-background-color: #2563EB;" +
                                "-fx-border-color: #2563EB;" +
                                "-fx-border-width: 1.5px;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-family: 'Segoe UI';" +
                                "-fx-font-size: " +
                                fontSize +
                                "px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 12px 30px;" +
                                "-fx-cursor: hand;"
                        );
                    }

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(150),
                                    button
                            );

                    scale.setToX(1.05);
                    scale.setToY(1.05);

                    scale.play();
                }
        );

        button.setOnMouseExited(
                e -> {

                    if (orangeButton) {

                        button.setStyle(
                                "-fx-background-color: #F97316;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-family: 'Segoe UI';" +
                                "-fx-font-size: " +
                                fontSize +
                                "px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 12px 30px;" +
                                "-fx-cursor: hand;"
                        );

                    } else {

                        button.setStyle(
                                "-fx-background-color: rgba(238,249,250,0.82);" +
                                "-fx-border-color: #2563EB;" +
                                "-fx-border-width: 1.5px;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-text-fill: #2563EB;" +
                                "-fx-font-family: 'Segoe UI';" +
                                "-fx-font-size: " +
                                fontSize +
                                "px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 12px 30px;" +
                                "-fx-cursor: hand;"
                        );
                    }

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(150),
                                    button
                            );

                    scale.setToX(1.0);
                    scale.setToY(1.0);

                    scale.play();
                }
        );
    }

    // =====================================================
    // FEATURE CARD METHOD
    // =====================================================

    private VBox createFeature(
            String title,
            String description,
            String accentColor
    ) {

        Label featureTitle =
                new Label(title);

        featureTitle.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label featureDescription =
                new Label(description);

        featureDescription.setWrapText(
                true
        );

        featureDescription.setMaxWidth(
                220
        );

        // featureDescription.setStyle(
        //         "-fx-font-family: 'Segoe UI';" +
        //         "-fx-font-size: 13px;" +
        //         "-fx-text-fill: #526274;"
        // );

        Text accent =
                new Text(
                        "━━━━"
                );

        accent.setStyle(
                "-fx-fill: " +
                accentColor +
                ";" +
                "-fx-font-weight: bold;"
        );

        VBox box =
                new VBox(
                        10,
                        featureTitle,
                        accent,
                        featureDescription
                );

        box.setAlignment(
                Pos.CENTER
        );

        box.setPrefWidth(
                230
        );

        box.setMinHeight(
                140
        );

        box.setPadding(
                new Insets(
                        18
                )
        );

        /*
         * Card itself is slightly transparent.
         * Therefore car/background remains visible.
         */
        box.setStyle(
                "-fx-background-color: rgba(238,249,250,0.84);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(184,212,217,0.75);" +
                "-fx-border-radius: 12px;"
        );

        return box;
    }

    // =====================================================
    // GET VIEW
    // =====================================================

    public Parent getView() {

        return root;
    }
}
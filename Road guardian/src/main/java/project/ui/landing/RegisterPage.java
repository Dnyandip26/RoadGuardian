package project.ui.landing;

import project.controller.login.RegisterController;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RegisterPage {

    private final BorderPane root;
    private final Stage stage;

    public RegisterPage(Stage stage) {

        this.stage = stage;
        this.root = new BorderPane();

        RegisterController controller =
                new RegisterController(stage);

        createPage(controller);
    }

    private void createPage(RegisterController controller) {

        // =====================================================
        // BACKGROUND IMAGE
        // =====================================================

        Image backgroundImage =
                new Image(
                        getClass()
                                .getResource(
                                        "/images/registerBg.png"
                                )
                                .toExternalForm()
                );

        System.out.println(
                "Register Background Error: "
                        + backgroundImage.isError()
        );

        BackgroundImage background =
                new BackgroundImage(
                        backgroundImage,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(
                                100,
                                100,
                                false,
                                false,
                                false,
                                true
                        )
                );

        root.setBackground(
                new Background(
                        background
                )
        );

        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backButton =
                new Button("← Back");

        backButton.setPrefWidth(105);
        backButton.setPrefHeight(40);

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #17345F;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
        );

        addBackHover(
                backButton
        );

        HBox topBar =
                new HBox(
                        backButton
                );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setPadding(
                new Insets(
                        18,
                        30,
                        5,
                        30
                )
        );

        topBar.setStyle(
                "-fx-background-color: transparent;"
        );

        // =====================================================
        // LOGO
        // =====================================================

        Text roadText =
                new Text("Road");

        roadText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 30px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #172033;"
        );

        Text guardianText =
                new Text("Guardian");

        guardianText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 30px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #2563EB;"
        );

        HBox logo =
                new HBox(
                        0,
                        roadText,
                        guardianText
                );

        logo.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // HEADING
        // =====================================================

        Text heading =
                new Text(
                        "Create Your Account"
                );

        heading.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 30px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #172033;"
        );

        // =====================================================
        // SUBTITLE
        // =====================================================

        Text subtitle =
                new Text(
                        "Join RoadGuardian and make every journey safer."
                );

        subtitle.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 14px;" +
                        "-fx-fill: #526274;"
        );

        // =====================================================
        // NAME
        // =====================================================

        Label nameLabel =
                createLabel(
                        "Full Name"
                );

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Enter your full name"
        );

        styleField(
                nameField
        );

        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel =
                createLabel(
                        "Email Address"
                );

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Enter your email"
        );

        styleField(
                emailField
        );

        // =====================================================
        // PHONE
        // =====================================================

        Label phoneLabel =
                createLabel(
                        "Phone Number"
                );

        TextField phoneField =
                new TextField();

        phoneField.setPromptText(
                "Enter your phone number"
        );

        styleField(
                phoneField
        );

        // =====================================================
        // ACCOUNT TYPE / ROLE
        // =====================================================

        Label roleLabel =
                createLabel(
                        "Account Type"
                );

        ComboBox<String> roleComboBox =
                new ComboBox<>();

        roleComboBox.getItems().addAll(
                "Customer",
                "Mechanic",
                "Admin"
        );

        roleComboBox.setValue(
                "Customer"
        );

        roleComboBox.setPrefWidth(
                520
        );

        roleComboBox.setMaxWidth(
                520
        );

        roleComboBox.setPrefHeight(
                39
        );

        roleComboBox.setStyle(
                "-fx-background-color: rgba(255,255,255,0.92);" +
                        "-fx-background-radius: 8px;" +
                        "-fx-border-color: #B8D4D9;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 14px;" +
                        "-fx-text-fill: #172033;"
        );

        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel =
                createLabel(
                        "Password"
                );

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Create a password"
        );

        styleField(
                passwordField
        );

        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        Label confirmPasswordLabel =
                createLabel(
                        "Confirm Password"
                );

        PasswordField confirmPasswordField =
                new PasswordField();

        confirmPasswordField.setPromptText(
                "Confirm your password"
        );

        styleField(
                confirmPasswordField
        );

        // =====================================================
        // REGISTER BUTTON
        // =====================================================

        Button registerButton =
                new Button(
                        "Create Account"
                );

        registerButton.setPrefWidth(
                520
        );

        registerButton.setMaxWidth(
                520
        );

        registerButton.setPrefHeight(
                46
        );

        registerButton.setStyle(
                "-fx-background-color: #F97316;" +
                        "-fx-background-radius: 9px;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
        );

        addRegisterHover(
                registerButton
        );

        // =====================================================
        // LOGIN LINK
        // =====================================================

        Text loginText =
                new Text(
                        "Already have an account?"
                );

        loginText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 13px;" +
                        "-fx-fill: #526274;"
        );

        Button loginButton =
                new Button(
                        "Login"
                );

        loginButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: #2563EB;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
        );

        addLinkHover(
                loginButton
        );

        HBox loginBox =
                new HBox(
                        5,
                        loginText,
                        loginButton
                );

        loginBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // FORM
        // =====================================================

        VBox form =
                new VBox(
                        6,

                        nameLabel,
                        nameField,

                        emailLabel,
                        emailField,

                        phoneLabel,
                        phoneField,

                        roleLabel,
                        roleComboBox,

                        passwordLabel,
                        passwordField,

                        confirmPasswordLabel,
                        confirmPasswordField,

                        registerButton,

                        loginBox
                );

        form.setAlignment(
                Pos.CENTER
        );

        form.setPrefWidth(
                520
        );

        form.setMaxWidth(
                520
        );

        // =====================================================
        // CARD
        // =====================================================

        VBox card =
                new VBox(
                        12,

                        logo,
                        heading,
                        subtitle,
                        form
                );

        card.setAlignment(
                Pos.CENTER
        );

        card.setPrefWidth(
                650
        );

        card.setMinWidth(
                650
        );

        card.setMaxWidth(
                650
        );

        card.setPrefHeight(
                720
        );

        card.setMinHeight(
                720
        );

        card.setMaxHeight(
                720
        );

        card.setPadding(
                new Insets(
                        25,
                        55,
                        22,
                        55
                )
        );

        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.92);" +
                        "-fx-background-radius: 24px;"
        );

        // =====================================================
        // CARD SHADOW
        // =====================================================

        DropShadow cardShadow =
                new DropShadow();

        cardShadow.setColor(
                Color.rgb(
                        37,
                        99,
                        235,
                        0.18
                )
        );

        cardShadow.setRadius(
                25
        );

        cardShadow.setSpread(
                0.08
        );

        card.setEffect(
                cardShadow
        );

        // =====================================================
        // ANIMATED CARD BORDER
        // =====================================================

        Rectangle animatedBorder =
                new Rectangle(
                        650,
                        720
                );

        animatedBorder.setArcWidth(
                48
        );

        animatedBorder.setArcHeight(
                48
        );

        animatedBorder.setFill(
                Color.TRANSPARENT
        );

        animatedBorder.setStroke(
                Color.rgb(
                        37,
                        99,
                        235,
                        0.75
                )
        );

        animatedBorder.setStrokeWidth(
                2.5
        );

        animatedBorder.getStrokeDashArray()
                .setAll(
                        18.0,
                        9.0,
                        4.0,
                        9.0
                );

        animatedBorder.setMouseTransparent(
                true
        );

        // =====================================================
        // BORDER GLOW
        // =====================================================

        DropShadow borderGlow =
                new DropShadow();

        borderGlow.setColor(
                Color.rgb(
                        37,
                        99,
                        235,
                        0.65
                )
        );

        borderGlow.setRadius(
                10
        );

        borderGlow.setSpread(
                0.08
        );

        animatedBorder.setEffect(
                borderGlow
        );

        // =====================================================
        // BORDER RUNNING ANIMATION
        // =====================================================

        Timeline borderAnimation =
                new Timeline(

                        new KeyFrame(
                                Duration.ZERO,

                                new KeyValue(
                                        animatedBorder
                                                .strokeDashOffsetProperty(),
                                        0
                                )
                        ),

                        new KeyFrame(
                                Duration.seconds(2.2),

                                new KeyValue(
                                        animatedBorder
                                                .strokeDashOffsetProperty(),
                                        -60
                                )
                        )
                );

        borderAnimation.setCycleCount(
                Timeline.INDEFINITE
        );

        borderAnimation.play();

        // =====================================================
        // BORDER GLOW PULSE
        // =====================================================

        Timeline borderGlowAnimation =
                new Timeline(

                        new KeyFrame(
                                Duration.ZERO,

                                new KeyValue(
                                        borderGlow.radiusProperty(),
                                        8
                                )
                        ),

                        new KeyFrame(
                                Duration.seconds(1.4),

                                new KeyValue(
                                        borderGlow.radiusProperty(),
                                        22
                                )
                        ),

                        new KeyFrame(
                                Duration.seconds(2.8),

                                new KeyValue(
                                        borderGlow.radiusProperty(),
                                        8
                                )
                        )
                );

        borderGlowAnimation.setCycleCount(
                Timeline.INDEFINITE
        );

        borderGlowAnimation.play();

        // =====================================================
        // CARD STACK
        // =====================================================

        StackPane cardStack =
                new StackPane();

        cardStack.setPrefWidth(
                650
        );

        cardStack.setMinWidth(
                650
        );

        cardStack.setMaxWidth(
                650
        );

        cardStack.setPrefHeight(
                720
        );

        cardStack.setMinHeight(
                720
        );

        cardStack.setMaxHeight(
                720
        );

        cardStack.getChildren().addAll(
                animatedBorder,
                card
        );

        // =====================================================
        // SAFETY TITLE
        // =====================================================

        Text safetyTitle =
                new Text(
                        "Your Safety. Our Priority."
                );

        safetyTitle.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 27px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #172033;"
        );

        Region leftLine =
                createLine();

        Region rightLine =
                createLine();

        HBox safetyHeader =
                new HBox(
                        15,
                        leftLine,
                        safetyTitle,
                        rightLine
                );

        safetyHeader.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // SAFETY CARDS
        // =====================================================

        VBox safety =
                createSafetyCard(
                        "✓",
                        "Your Safety",
                        "Our Priority"
                );

        VBox mechanics =
                createSafetyCard(
                        "⚙",
                        "Expert",
                        "Mechanics"
                );

        VBox support =
                createSafetyCard(
                        "◉",
                        "24/7",
                        "Support"
                );

        VBox reliable =
                createSafetyCard(
                        "◆",
                        "Safe &",
                        "Reliable"
                );

        VBox safetyCards =
                new VBox(
                        15,
                        safety,
                        mechanics,
                        support,
                        reliable
                );

        safetyCards.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // RIGHT INFO PANEL
        // =====================================================

        VBox infoPanel =
                new VBox(
                        25,
                        safetyHeader,
                        safetyCards
                );

        infoPanel.setAlignment(
                Pos.TOP_CENTER
        );

        infoPanel.setPrefWidth(
                260
        );

        infoPanel.setMaxWidth(
                260
        );

        infoPanel.setPadding(
                new Insets(
                        20,
                        10,
                        20,
                        10
                )
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        HBox mainContent =
                new HBox(
                        55,
                        infoPanel,
                        cardStack
                );

        mainContent.setAlignment(
                Pos.CENTER
        );

        mainContent.setPadding(
                new Insets(
                        5,
                        40,
                        5,
                        40
                )
        );

        // =====================================================
        // FOOTER
        // =====================================================

        Text footerText =
                new Text(
                        "© 2026 RoadGuardian. All rights reserved."
                );

        footerText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 12px;" +
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
                new Insets(
                        9
                )
        );

        footer.setStyle(
                "-fx-background-color: rgba(223,243,245,0.80);" +
                        "-fx-border-color: rgba(184,212,217,0.70);" +
                        "-fx-border-width: 1 0 0 0;"
        );

        // =====================================================
        // REGISTER ACTION
        // =====================================================

        registerButton.setOnAction(
                e -> controller.register(
                        nameField.getText(),
                        emailField.getText(),
                        phoneField.getText(),
                        roleComboBox.getValue(),
                        passwordField.getText(),
                        confirmPasswordField.getText()
                )
        );

        // =====================================================
        // LOGIN ACTION
        // =====================================================

        loginButton.setOnAction(
                e -> controller.openLoginPage()
        );

        // =====================================================
        // BACK ACTION
        // =====================================================

        backButton.setOnAction(
                e -> controller.goBack()
        );

        // =====================================================
        // ROOT LAYOUT
        // =====================================================

        root.setTop(
                topBar
        );

        root.setCenter(
                mainContent
        );

        root.setBottom(
                footer
        );
    }

    // =====================================================
    // SAFETY CARD
    // =====================================================

    private VBox createSafetyCard(
            String iconText,
            String line1,
            String line2
    ) {

        Circle iconCircle =
                new Circle(
                        27
                );

        iconCircle.setFill(
                Color.rgb(
                        235,
                        247,
                        255,
                        0.75
                )
        );

        iconCircle.setStroke(
                Color.rgb(
                        37,
                        99,
                        235,
                        0.65
                )
        );

        iconCircle.setStrokeWidth(
                1.8
        );

        Text icon =
                new Text(
                        iconText
                );

        icon.setStyle(
                "-fx-font-family: 'Segoe UI Symbol';" +
                        "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #2563EB;"
        );

        StackPane iconContainer =
                new StackPane(
                        iconCircle,
                        icon
                );

        DropShadow glow =
                new DropShadow();

        glow.setColor(
                Color.rgb(
                        37,
                        99,
                        235,
                        0.75
                )
        );

        glow.setRadius(
                7
        );

        glow.setSpread(
                0.10
        );

        iconCircle.setEffect(
                glow
        );

        Timeline glowAnimation =
                new Timeline(

                        new KeyFrame(
                                Duration.ZERO,

                                new KeyValue(
                                        glow.radiusProperty(),
                                        6
                                ),

                                new KeyValue(
                                        iconCircle.opacityProperty(),
                                        0.65
                                )
                        ),

                        new KeyFrame(
                                Duration.seconds(1.2),

                                new KeyValue(
                                        glow.radiusProperty(),
                                        19
                                ),

                                new KeyValue(
                                        iconCircle.opacityProperty(),
                                        1.0
                                )
                        ),

                        new KeyFrame(
                                Duration.seconds(2.4),

                                new KeyValue(
                                        glow.radiusProperty(),
                                        6
                                ),

                                new KeyValue(
                                        iconCircle.opacityProperty(),
                                        0.65
                                )
                        )
                );

        glowAnimation.setCycleCount(
                Timeline.INDEFINITE
        );

        glowAnimation.play();

        TranslateTransition floating =
                new TranslateTransition(
                        Duration.seconds(2.2),
                        iconContainer
                );

        floating.setFromY(
                0
        );

        floating.setToY(
                -4
        );

        floating.setAutoReverse(
                true
        );

        floating.setCycleCount(
                TranslateTransition.INDEFINITE
        );

        floating.play();

        Text firstText =
                new Text(
                        line1
                );

        firstText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #172033;"
        );

        Text secondText =
                new Text(
                        line2
                );

        secondText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #2563EB;"
        );

        VBox textBox =
                new VBox(
                        1,
                        firstText,
                        secondText
                );

        textBox.setAlignment(
                Pos.CENTER
        );

        VBox card =
                new VBox(
                        14,
                        iconContainer,
                        textBox
                );

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setPrefWidth(
                240
        );

        card.setMaxWidth(
                240
        );

        card.setPrefHeight(
                70
        );

        card.setPadding(
                new Insets(
                        8,
                        12,
                        8,
                        12
                )
        );

        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.42);" +
                        "-fx-background-radius: 14px;" +
                        "-fx-border-color: rgba(37,99,235,0.35);" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 14px;"
        );

        card.setOnMouseEntered(
                e -> {

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(180),
                                    card
                            );

                    scale.setToX(
                            1.04
                    );

                    scale.setToY(
                            1.04
                    );

                    scale.play();
                }
        );

        card.setOnMouseExited(
                e -> {

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(180),
                                    card
                            );

                    scale.setToX(
                            1.0
                    );

                    scale.setToY(
                            1.0
                    );

                    scale.play();
                }
        );

        return card;
    }

    // =====================================================
    // ANIMATED TITLE LINE
    // =====================================================

    private Region createLine() {

        Region line =
                new Region();

        line.setPrefWidth(
                45
        );

        line.setMinWidth(
                45
        );

        line.setMaxWidth(
                45
        );

        line.setPrefHeight(
                2
        );

        line.setMinHeight(
                2
        );

        line.setMaxHeight(
                2
        );

        line.setStyle(
                "-fx-background-color: #2563EB;" +
                        "-fx-background-radius: 5px;"
        );

        ScaleTransition scale =
                new ScaleTransition(
                        Duration.seconds(1.4),
                        line
                );

        scale.setFromX(
                0.35
        );

        scale.setToX(
                1.0
        );

        scale.setAutoReverse(
                true
        );

        scale.setCycleCount(
                ScaleTransition.INDEFINITE
        );

        scale.play();

        return line;
    }

    // =====================================================
    // LABEL
    // =====================================================

    private Label createLabel(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #172033;"
        );

        return label;
    }

    // =====================================================
    // FIELD STYLE
    // =====================================================

    private void styleField(
            TextField field
    ) {

        field.setPrefWidth(
                520
        );

        field.setMaxWidth(
                520
        );

        field.setPrefHeight(
                39
        );

        field.setStyle(
                "-fx-background-color: rgba(255,255,255,0.92);" +
                        "-fx-background-radius: 8px;" +
                        "-fx-border-color: #B8D4D9;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 14px;" +
                        "-fx-text-fill: #172033;" +
                        "-fx-prompt-text-fill: #8A99A8;" +
                        "-fx-padding: 0 12px;"
        );
    }

    // =====================================================
    // REGISTER BUTTON HOVER
    // =====================================================

    private void addRegisterHover(
            Button button
    ) {

        button.setOnMouseEntered(
                e -> {

                    button.setStyle(
                            "-fx-background-color: #EA580C;" +
                                    "-fx-background-radius: 9px;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-font-family: 'Segoe UI';" +
                                    "-fx-font-size: 16px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-cursor: hand;"
                    );

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(150),
                                    button
                            );

                    scale.setToX(
                            1.03
                    );

                    scale.setToY(
                            1.03
                    );

                    scale.play();
                }
        );

        button.setOnMouseExited(
                e -> {

                    button.setStyle(
                            "-fx-background-color: #F97316;" +
                                    "-fx-background-radius: 9px;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-font-family: 'Segoe UI';" +
                                    "-fx-font-size: 16px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-cursor: hand;"
                    );

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(150),
                                    button
                            );

                    scale.setToX(
                            1.0
                    );

                    scale.setToY(
                            1.0
                    );

                    scale.play();
                }
        );
    }

    // =====================================================
    // LOGIN LINK HOVER
    // =====================================================

    private void addLinkHover(
            Button button
    ) {

        button.setOnMouseEntered(
                e -> {

                    button.setStyle(
                            "-fx-background-color: transparent;" +
                                    "-fx-text-fill: #1D4ED8;" +
                                    "-fx-font-family: 'Segoe UI';" +
                                    "-fx-font-size: 13px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-cursor: hand;"
                    );

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(120),
                                    button
                            );

                    scale.setToX(
                            1.05
                    );

                    scale.setToY(
                            1.05
                    );

                    scale.play();
                }
        );

        button.setOnMouseExited(
                e -> {

                    button.setStyle(
                            "-fx-background-color: transparent;" +
                                    "-fx-text-fill: #2563EB;" +
                                    "-fx-font-family: 'Segoe UI';" +
                                    "-fx-font-size: 13px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-cursor: hand;"
                    );

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(120),
                                    button
                            );

                    scale.setToX(
                            1.0
                    );

                    scale.setToY(
                            1.0
                    );

                    scale.play();
                }
        );
    }

    // =====================================================
    // BACK HOVER
    // =====================================================

    private void addBackHover(
            Button button
    ) {

        button.setOnMouseEntered(
                e -> {

                    button.setStyle(
                            "-fx-background-color: rgba(37,99,235,0.10);" +
                                    "-fx-background-radius: 8px;" +
                                    "-fx-text-fill: #2563EB;" +
                                    "-fx-font-family: 'Segoe UI';" +
                                    "-fx-font-size: 14px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-cursor: hand;"
                    );

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(150),
                                    button
                            );

                    scale.setToX(
                            1.05
                    );

                    scale.setToY(
                            1.05
                    );

                    scale.play();
                }
        );

        button.setOnMouseExited(
                e -> {

                    button.setStyle(
                            "-fx-background-color: transparent;" +
                                    "-fx-text-fill: #17345F;" +
                                    "-fx-font-family: 'Segoe UI';" +
                                    "-fx-font-size: 14px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-cursor: hand;"
                    );

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(150),
                                    button
                            );

                    scale.setToX(
                            1.0
                    );

                    scale.setToY(
                            1.0
                    );

                    scale.play();
                }
        );
    }

    // =====================================================
    // GET VIEW
    // =====================================================

    public Parent getView() {

        return root;
    }
}
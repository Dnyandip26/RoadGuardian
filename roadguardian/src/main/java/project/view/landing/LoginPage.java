package project.view.landing;

import project.controller.LoginController;

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

public class LoginPage {

    private final BorderPane root;
    private final Stage stage;

    public LoginPage(Stage stage) {

        this.stage = stage;
        this.root = new BorderPane();

        LoginController controller =
                new LoginController(stage);

        createPage(controller);
    }

    private void createPage(LoginController controller) {

        // =====================================================
        // BACKGROUND IMAGE
        // =====================================================

        Image backgroundImage =
                new Image(
                        "file:roadguardian\\src\\main\\resources\\images\\carBg.png"
                );

        System.out.println(
                "Login Background Error: "
                        + backgroundImage.isError()
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
        // BACK BUTTON
        // =====================================================

        Button backButton =
                new Button("← Back");

        backButton.setPrefWidth(115);
        backButton.setPrefHeight(44);

        backButton.setStyle(
                "-fx-background-color: #F97316;" +
                "-fx-background-radius: 10px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        HBox topBar =
                new HBox(
                        backButton
                );

        topBar.setAlignment(
                Pos.TOP_LEFT
        );

        topBar.setPadding(
                new Insets(
                        20,
                        25,
                        10,
                        25
                )
        );

        topBar.setStyle(
                "-fx-background-color: transparent;"
        );

        // =====================================================
        // ROADGUARDIAN LOGO
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
        // WELCOME HEADING
        // =====================================================

        Text heading =
                new Text("Welcome Back");

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
                        "Login to continue your journey with RoadGuardian"
                );

        subtitle.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-fill: #526274;"
        );

        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel =
                new Label("Email Address");

        emailLabel.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Enter your email"
        );

        emailField.setPrefWidth(380);
        emailField.setMaxWidth(380);
        emailField.setPrefHeight(42);

        emailField.setStyle(
                "-fx-background-color: rgba(255,255,255,0.97);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-radius: 8px;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #172033;" +
                "-fx-prompt-text-fill: #8A99A8;" +
                "-fx-padding: 0 12px;"
        );

        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel =
                new Label("Password");

        passwordLabel.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter your password"
        );

        passwordField.setPrefWidth(380);
        passwordField.setMaxWidth(380);
        passwordField.setPrefHeight(42);

        passwordField.setStyle(
                "-fx-background-color: rgba(255,255,255,0.97);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-radius: 8px;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #172033;" +
                "-fx-prompt-text-fill: #8A99A8;" +
                "-fx-padding: 0 12px;"
        );

        // =====================================================
        // LOGIN AS
        // =====================================================

        Label roleLabel =
                new Label("Login As");

        roleLabel.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        ComboBox<String> roleBox =
                new ComboBox<>();

        roleBox.getItems().addAll(
                "User",
                "Mechanic"
        );

        roleBox.setValue("User");

        roleBox.setPrefWidth(380);
        roleBox.setMaxWidth(380);
        roleBox.setPrefHeight(42);

        roleBox.setStyle(
                "-fx-background-color: rgba(255,255,255,0.97);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-radius: 8px;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        // =====================================================
        // FORGOT PASSWORD
        // =====================================================

        Button forgotPasswordButton =
                new Button("Forgot Password?");

        forgotPasswordButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #2563EB;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        HBox forgotBox =
                new HBox(
                        forgotPasswordButton
                );

        forgotBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        forgotBox.setPrefWidth(380);
        forgotBox.setMaxWidth(380);

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button loginButton =
                new Button("Login");

        loginButton.setPrefWidth(380);
        loginButton.setMaxWidth(380);
        loginButton.setPrefHeight(46);

        loginButton.setStyle(
                "-fx-background-color: #F97316;" +
                "-fx-background-radius: 9px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        // =====================================================
        // CREATE ACCOUNT
        // =====================================================

        Text accountText =
                new Text(
                        "Don't have an account?"
                );

        accountText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 13px;" +
                "-fx-fill: #526274;"
        );

        Button registerButton =
                new Button("Create Account");

        registerButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #2563EB;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        HBox registerBox =
                new HBox(
                        5,
                        accountText,
                        registerButton
                );

        registerBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // LOGIN FORM
        // =====================================================

        VBox loginForm =
                new VBox(
                        8,

                        emailLabel,
                        emailField,

                        passwordLabel,
                        passwordField,

                        roleLabel,
                        roleBox,

                        forgotBox,

                        loginButton,

                        registerBox
                );

        loginForm.setAlignment(
                Pos.CENTER_LEFT
        );

        loginForm.setPrefWidth(380);
        loginForm.setMaxWidth(380);

        // =====================================================
        // LOGIN CARD
        // =====================================================

        VBox loginCard =
                new VBox(
                        18,
                        logo,
                        heading,
                        subtitle,
                        loginForm
                );

        loginCard.setAlignment(
                Pos.CENTER
        );

        loginCard.setPrefWidth(480);
        loginCard.setMinWidth(480);
        loginCard.setMaxWidth(480);

        loginCard.setPrefHeight(590);
        loginCard.setMinHeight(590);
        loginCard.setMaxHeight(590);

        loginCard.setPadding(
                new Insets(
                        32,
                        45,
                        32,
                        45
                )
        );

        loginCard.setStyle(
                "-fx-background-color: rgba(249,249,249,0.90);" +
                "-fx-background-radius: 20px;"
        );

        // =====================================================
        // ANIMATED BORDER
        // =====================================================

        Rectangle animatedBorder =
                new Rectangle(
                        480,
                        590
                );

        animatedBorder.setArcWidth(40);
        animatedBorder.setArcHeight(40);

        animatedBorder.setFill(
                Color.TRANSPARENT
        );

        animatedBorder.setStroke(
                Color.rgb(
                        27,
                        199,
                        195,
                        0.80
                )
        );

        animatedBorder.setStrokeWidth(2.5);

        animatedBorder.getStrokeDashArray().setAll(
                18.0,
                9.0,
                4.0,
                9.0
        );

        animatedBorder.setMouseTransparent(true);

        // =====================================================
        // BORDER GLOW
        // =====================================================

        DropShadow borderGlow =
                new DropShadow();

        borderGlow.setColor(
                Color.rgb(
                        227,
                        99,
                        15,
                        0.65
                )
        );

        borderGlow.setRadius(10);
        borderGlow.setSpread(0.10);

        animatedBorder.setEffect(
                borderGlow
        );

        // =====================================================
        // RUNNING BORDER ANIMATION
        // =====================================================

        Timeline borderAnimation =
                new Timeline(
                        new KeyFrame(
                                Duration.ZERO,
                                new KeyValue(
                                        animatedBorder.strokeDashOffsetProperty(),
                                        0
                                )
                        ),
                        new KeyFrame(
                                Duration.seconds(2.2),
                                new KeyValue(
                                        animatedBorder.strokeDashOffsetProperty(),
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

        Timeline cardGlowAnimation =
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
                                        20
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

        cardGlowAnimation.setCycleCount(
                Timeline.INDEFINITE
        );

        cardGlowAnimation.play();

        // =====================================================
        // LOGIN CARD STACK
        // =====================================================

        StackPane loginCardStack =
                new StackPane();

        loginCardStack.setPrefWidth(480);
        loginCardStack.setMinWidth(480);
        loginCardStack.setMaxWidth(480);

        loginCardStack.setPrefHeight(590);
        loginCardStack.setMinHeight(590);
        loginCardStack.setMaxHeight(590);

        loginCardStack.getChildren().addAll(
                animatedBorder,
                loginCard
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
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #172033;"
        );

        // =====================================================
        // TITLE LINES
        // =====================================================

        Region leftLine =
                createLine();

        Region rightLine =
                createLine();

        HBox safetyHeader =
                new HBox(
                        18,
                        leftLine,
                        safetyTitle,
                        rightLine
                );

        safetyHeader.setAlignment(
                Pos.CENTER
        );

        safetyHeader.setFillHeight(
                false
        );

        // =====================================================
        // SERVICE CARDS
        // =====================================================

        VBox roadside =
                createServiceCard(
                        "⚒",
                        "Roadside",
                        "Assistance"
                );

        VBox mechanics =
                createServiceCard(
                        "⚙",
                        "Expert",
                        "Mechanics"
                );

        VBox safe =
                createServiceCard(
                        "✓",
                        "Safe &",
                        "Reliable"
                );

        VBox support =
                createServiceCard(
                        "◉",
                        "24/7",
                        "Support"
                );

        HBox serviceCards =
                new HBox(
                        18,
                        roadside,
                        mechanics,
                        safe,
                        support
                );

        serviceCards.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // RIGHT CONTENT
        // =====================================================

        VBox rightContent =
                new VBox(
                        28,
                        safetyHeader,
                        serviceCards
                );

        rightContent.setAlignment(
                Pos.TOP_CENTER
        );

        rightContent.setPrefWidth(700);
        rightContent.setMaxWidth(700);

        /*
         * Move complete safety section toward top.
         */
        rightContent.setTranslateY(-35);

        rightContent.setPadding(
                new Insets(
                        10,
                        20,
                        20,
                        20
                )
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        HBox mainContent =
                new HBox(
                        70,
                        loginCardStack,
                        rightContent
                );

        mainContent.setAlignment(
                Pos.CENTER
        );

        mainContent.setPadding(
                new Insets(
                        10,
                        45,
                        10,
                        45
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
                new Insets(10)
        );

        footer.setStyle(
                "-fx-background-color: rgba(223,243,245,0.82);" +
                "-fx-border-color: rgba(184,212,217,0.65);" +
                "-fx-border-width: 1 0 0 0;"
        );

        // =====================================================
        // HOVER EFFECTS
        // =====================================================

        addLoginHover(
                loginButton
        );

        addBackHover(
                backButton
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        loginButton.setOnAction(
                e -> controller.login(
                        emailField.getText(),
                        passwordField.getText(),
                        roleBox.getValue()
                )
        );

        registerButton.setOnAction(
                e -> controller.openRegisterPage()
        );

        forgotPasswordButton.setOnAction(
                e -> controller.openForgotPasswordPage()
        );

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
    // SERVICE CARD
    // =====================================================

    private VBox createServiceCard(
            String iconText,
            String line1,
            String line2
    ) {

        // =====================================================
        // ICON CIRCLE
        // =====================================================

        Circle iconCircle =
                new Circle(42);

        iconCircle.setFill(
                Color.rgb(
                        235,
                        247,
                        255,
                        0.65
                )
        );

        iconCircle.setStroke(
                Color.rgb(
                        37,
                        99,
                        235,
                        0.75
                )
        );

        iconCircle.setStrokeWidth(2);

        // =====================================================
        // ICON
        // =====================================================

        Text icon =
                new Text(iconText);

        icon.setStyle(
                "-fx-font-family: 'Segoe UI Symbol';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #2563EB;"
        );

        StackPane iconContainer =
                new StackPane(
                        iconCircle,
                        icon
                );

        // =====================================================
        // ICON GLOW
        // =====================================================

        DropShadow glow =
                new DropShadow();

        glow.setColor(
                Color.rgb(
                        37,
                        99,
                        235,
                        0.85
                )
        );

        glow.setRadius(8);
        glow.setSpread(0.15);

        iconCircle.setEffect(
                glow
        );

        // =====================================================
        // GLOW ANIMATION
        // =====================================================

        Timeline glowAnimation =
                new Timeline(
                        new KeyFrame(
                                Duration.ZERO,
                                new KeyValue(
                                        glow.radiusProperty(),
                                        7
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
                                        20
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
                                        7
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

        // =====================================================
        // FLOATING ANIMATION
        // =====================================================

        TranslateTransition floating =
                new TranslateTransition(
                        Duration.seconds(2.2),
                        iconContainer
                );

        floating.setFromY(0);
        floating.setToY(-5);

        floating.setAutoReverse(true);

        floating.setCycleCount(
                TranslateTransition.INDEFINITE
        );

        floating.play();

        // =====================================================
        // SERVICE TEXT
        // =====================================================

        Text firstText =
                new Text(line1);

        firstText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #172033;"
        );

        Text secondText =
                new Text(line2);

        secondText.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #172033;"
        );

        VBox textBox =
                new VBox(
                        2,
                        firstText,
                        secondText
                );

        textBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // SERVICE CARD
        // =====================================================

        VBox card =
                new VBox(
                        14,
                        iconContainer,
                        textBox
                );

        card.setAlignment(
                Pos.CENTER
        );

        card.setPrefWidth(125);
        card.setMinWidth(125);
        card.setMaxWidth(125);

        card.setPrefHeight(190);
        card.setMinHeight(190);
        card.setMaxHeight(190);

        card.setPadding(
                new Insets(
                        10,
                        8,
                        10,
                        8
                )
        );

        /*
         * No translate here.
         * The complete safety section is moved
         * using rightContent.setTranslateY().
         */

        card.setTranslateY(0);

        card.setStyle(
                "-fx-background-color: rgba(240,249,255,0.18);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(37,99,235,0.45);" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 16px;"
        );

        // =====================================================
        // CARD HOVER
        // =====================================================

        card.setOnMouseEntered(
                e -> {

                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(180),
                                    card
                            );

                    scale.setToX(1.06);
                    scale.setToY(1.06);

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

                    scale.setToX(1.0);
                    scale.setToY(1.0);

                    scale.play();
                }
        );

        return card;
    }

    // =====================================================
    // TITLE LINE
    // =====================================================

    private Region createLine() {

        Region line =
                new Region();

        line.setPrefWidth(70);
        line.setMinWidth(70);
        line.setMaxWidth(70);

        line.setPrefHeight(2);
        line.setMinHeight(2);
        line.setMaxHeight(2);

        line.setStyle(
                "-fx-background-color: #2563EB;" +
                "-fx-background-radius: 5px;"
        );

        // =====================================================
        // LINE ANIMATION
        // =====================================================

        ScaleTransition scale =
                new ScaleTransition(
                        Duration.seconds(1.4),
                        line
                );

        scale.setFromX(0.45);
        scale.setToX(1.0);

        scale.setAutoReverse(true);

        scale.setCycleCount(
                ScaleTransition.INDEFINITE
        );

        scale.play();

        return line;
    }

    // =====================================================
    // LOGIN BUTTON HOVER
    // =====================================================

    private void addLoginHover(
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

                    scale.setToX(1.03);
                    scale.setToY(1.03);

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

                    scale.setToX(1.0);
                    scale.setToY(1.0);

                    scale.play();
                }
        );
    }

    // =====================================================
    // BACK BUTTON HOVER
    // =====================================================

    private void addBackHover(
            Button button
    ) {

        button.setOnMouseEntered(
                e -> {

                    button.setStyle(
                            "-fx-background-color: #EA580C;" +
                            "-fx-background-radius: 9px;" +
                            "-fx-text-fill: white;" +
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

                    scale.setToX(1.05);
                    scale.setToY(1.05);

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
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-cursor: hand;"
                    );

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
    // GET VIEW
    // =====================================================

    public Parent getView() {

        return root;
    }
}
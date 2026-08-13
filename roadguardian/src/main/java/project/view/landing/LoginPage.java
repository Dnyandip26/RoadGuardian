package project.view.landing;

import project.controller.LoginController;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        // MAIN BACKGROUND
        // =====================================================

        root.setStyle(
                "-fx-background-color: #D6F0F3;"
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
                        roadText,
                        guardianText
                );

        logo.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // WELCOME TEXT
        // =====================================================

        Text heading =
                new Text(
                        "Welcome Back"
                );

        heading.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #172033;"
        );

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
        // EMAIL LABEL
        // =====================================================

        Label emailLabel =
                new Label("Email Address");

        emailLabel.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );


        // =====================================================
        // EMAIL FIELD
        // =====================================================

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Enter your email"
        );

        emailField.setPrefHeight(
                42
        );

        emailField.setStyle(
                "-fx-background-color: #F7FCFD;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-radius: 7px;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;"
        );


        // =====================================================
        // PASSWORD LABEL
        // =====================================================

        Label passwordLabel =
                new Label("Password");

        passwordLabel.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );


        // =====================================================
        // PASSWORD FIELD
        // =====================================================

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter your password"
        );

        passwordField.setPrefHeight(
                42
        );

        passwordField.setStyle(
                "-fx-background-color: #F7FCFD;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-radius: 7px;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;"
        );


        // =====================================================
        // LOGIN AS LABEL
        // =====================================================

        Label roleLabel =
                new Label("Login As");

        roleLabel.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );


        // =====================================================
        // ROLE SELECTION
        // =====================================================

        ComboBox<String> roleBox =
                new ComboBox<>();

        roleBox.getItems().addAll(
                "User",
                "Mechanic"
        );

        // Default User
        roleBox.setValue(
                "User"
        );

        roleBox.setPrefWidth(
                360
        );

        roleBox.setPrefHeight(
                42
        );

        roleBox.setStyle(
                "-fx-background-color: #F7FCFD;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-radius: 7px;" +
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


        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button loginButton =
                new Button("Login");

        loginButton.setPrefWidth(
                360
        );

        loginButton.setPrefHeight(
                45
        );

        loginButton.setStyle(
                "-fx-background-color: #F97316;" +
                "-fx-background-radius: 8px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );


        // =====================================================
        // REGISTER TEXT
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

        loginForm.setPrefWidth(
                360
        );


        // =====================================================
        // CARD
        // =====================================================

        VBox loginCard =
                new VBox(
                        22,
                        logo,
                        heading,
                        subtitle,
                        loginForm
                );

        loginCard.setAlignment(
                Pos.CENTER
        );

        loginCard.setPrefWidth(
                460
        );

        loginCard.setPadding(
                new Insets(
                        40,
                        50,
                        40,
                        50
                )
        );

        loginCard.setStyle(
                "-fx-background-color: rgba(238,249,250,0.96);" +
                "-fx-background-radius: 18px;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-radius: 18px;"
        );


        // =====================================================
        // CENTER AREA
        // =====================================================

        VBox centerContent =
                new VBox(
                        loginCard
                );

        centerContent.setAlignment(
                Pos.CENTER
        );

        centerContent.setPadding(
                new Insets(
                        30
                )
        );


        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backButton =
                new Button("← Back");

        backButton.setStyle(
                "-fx-background-color: #F97316;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 9px 22px;" +
                "-fx-cursor: hand;"
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
                        25,
                        5,
                        25
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
                        12
                )
        );

        footer.setStyle(
                "-fx-background-color: #DFF3F5;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-width: 1 0 0 0;"
        );


        // =====================================================
        // HOVER
        // =====================================================

        addLoginHover(
                loginButton
                
        );

        backButtonHover(
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
        // PAGE LAYOUT
        // =====================================================

        root.setTop(
                topBar
        );

        root.setCenter(
                centerContent
        );

        root.setBottom(
                footer
        );
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
                            "-fx-background-radius: 8px;" +
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
                            "-fx-background-radius: 8px;" +
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

        private void backButtonHover(
            Button button
    ) {

        button.setOnMouseEntered(
                e -> {

                    button.setStyle(
                            "-fx-background-color: #EA580C;" +
                            "-fx-background-radius: 8px;" +
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
                            "-fx-background-radius: 8px;" +
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
    // GET VIEW
    // =====================================================

    public Parent getView() {

        return root;
    }
}
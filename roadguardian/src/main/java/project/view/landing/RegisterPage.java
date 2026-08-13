package project.view.landing;
import project.controller.RegisterController;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        // BACKGROUND
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

        logo.setAlignment(Pos.CENTER);

        // =====================================================
        // HEADING
        // =====================================================

        Text heading =
                new Text("Create Your Account");

        heading.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #172033;"
        );

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
                createLabel("Full Name");

        TextField nameField =
                new TextField();

        nameField.setPromptText("Enter your full name");

        styleField(nameField);

        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel =
                createLabel("Email Address");

        TextField emailField =
                new TextField();

        emailField.setPromptText("Enter your email");

        styleField(emailField);

        // =====================================================
        // PHONE
        // =====================================================

        Label phoneLabel =
                createLabel("Phone Number");

        TextField phoneField =
                new TextField();

        phoneField.setPromptText("Enter your phone number");

        styleField(phoneField);

        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel =
                createLabel("Password");

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText("Create a password");

        styleField(passwordField);

        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        Label confirmPasswordLabel =
                createLabel("Confirm Password");

        PasswordField confirmPasswordField =
                new PasswordField();

        confirmPasswordField.setPromptText(
                "Confirm your password"
        );

        styleField(confirmPasswordField);

        // =====================================================
        // REGISTER BUTTON
        // =====================================================

        Button registerButton =
                new Button("Create Account");

        registerButton.setPrefWidth(360);
        registerButton.setPrefHeight(45);

        registerButton.setStyle(
                "-fx-background-color: #F97316;" +
                "-fx-background-radius: 8px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        addHover(registerButton);

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
                new Button("Login");

        loginButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #2563EB;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        HBox loginBox =
                new HBox(
                        5,
                        loginText,
                        loginButton
                );

        loginBox.setAlignment(Pos.CENTER);

        // =====================================================
        // FORM
        // =====================================================

        VBox form =
                new VBox(
                        8,
                        nameLabel,
                        nameField,

                        emailLabel,
                        emailField,

                        phoneLabel,
                        phoneField,

                        passwordLabel,
                        passwordField,

                        confirmPasswordLabel,
                        confirmPasswordField,

                        registerButton,
                        loginBox
                );

        form.setPrefWidth(360);

        // =====================================================
        // CARD
        // =====================================================

        VBox card =
                new VBox(
                        20,
                        logo,
                        heading,
                        subtitle,
                        form
                );

        card.setAlignment(Pos.CENTER);

        card.setPrefWidth(460);

        card.setPadding(
                new Insets(
                        35,
                        50,
                        35,
                        50
                )
        );

        card.setStyle(
                "-fx-background-color: rgba(238,249,250,0.97);" +
                "-fx-background-radius: 18px;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-radius: 18px;"
        );

        // =====================================================
        // CENTER
        // =====================================================

        VBox center =
                new VBox(card);

        center.setAlignment(Pos.CENTER);

        center.setPadding(
                new Insets(25)
        );

        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backButton =
                new Button("← Back");

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #526274;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        HBox topBar =
                new HBox(backButton);

        topBar.setAlignment(Pos.CENTER_LEFT);

        topBar.setPadding(
                new Insets(18, 25, 5, 25)
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
                new HBox(footerText);

        footer.setAlignment(Pos.CENTER);

        footer.setPadding(new Insets(12));

        footer.setStyle(
                "-fx-background-color: #DFF3F5;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-width: 1 0 0 0;"
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        registerButton.setOnAction(
                e -> controller.register(
                        nameField.getText(),
                        emailField.getText(),
                        phoneField.getText(),
                        passwordField.getText(),
                        confirmPasswordField.getText()
                )
        );

        loginButton.setOnAction(
                e -> controller.openLoginPage()
        );

        backButton.setOnAction(
                e -> controller.goBack()
        );

        // =====================================================
        // LAYOUT
        // =====================================================

        root.setTop(topBar);

        root.setCenter(center);

        root.setBottom(footer);
    }

    // =====================================================
    // LABEL METHOD
    // =====================================================

    private Label createLabel(String text) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
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

        field.setPrefHeight(40);

        field.setStyle(
                "-fx-background-color: #F7FCFD;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: #B8D4D9;" +
                "-fx-border-radius: 7px;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 0 12px;"
        );
    }

    // =====================================================
    // BUTTON HOVER
    // =====================================================

    private void addHover(Button button) {

        button.setOnMouseEntered(e -> {

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
        });

        button.setOnMouseExited(e -> {

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
        });
    }

    // =====================================================
    // GET VIEW
    // =====================================================

    public Parent getView() {

        return root;
    }
}
package project.ui.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SettingsPage {

    private static final String BG = "#D6F0F3";
    private static final String SURFACE = "#EEF9FA";
    private static final String BLUE = "#2563EB";
    private static final String GREEN = "#16A34A";
    private static final String ORANGE = "#F97316";
    private static final String RED = "#DC2626";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    public VBox getView() {

        VBox root = new VBox(20);

        root.setPadding(new Insets(30));

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        Label title = new Label("Settings");

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
                        "Manage RoadGuardian admin and application settings."
                );

        subtitle.setFont(
                Font.font("Arial", 16)
        );

        subtitle.setTextFill(
                Color.web(TEXT)
        );

        VBox header =
                new VBox(5);

        header.getChildren().addAll(
                title,
                subtitle
        );

        VBox profileCard =
                createProfileSection();

        VBox applicationCard =
                createApplicationSection();

        VBox notificationCard =
                createNotificationSection();

        VBox securityCard =
                createSecuritySection();

        root.getChildren().addAll(
                header,
                profileCard,
                applicationCard,
                notificationCard,
                securityCard
        );

        return root;
    }

    // =====================================================
    // PROFILE
    // =====================================================

    private VBox createProfileSection() {

        VBox card =
                createCard();

        Label heading =
                createSectionTitle(
                        "Admin Profile"
                );

        GridPane grid =
                createGrid();

        TextField nameField =
                new TextField(
                        "RoadGuardian Admin"
                );

        TextField emailField =
                new TextField(
                        "admin@roadguardian.com"
                );

        TextField phoneField =
                new TextField(
                        "+91 98765 43210"
                );

        grid.add(
                createLabel("Admin Name"),
                0,
                0
        );

        grid.add(
                nameField,
                1,
                0
        );

        grid.add(
                createLabel("Email"),
                0,
                1
        );

        grid.add(
                emailField,
                1,
                1
        );

        grid.add(
                createLabel("Phone"),
                0,
                2
        );

        grid.add(
                phoneField,
                1,
                2
        );

        Button saveButton =
                createPrimaryButton(
                        "Save Profile"
                );

        saveButton.setOnAction(
                e ->
                        showMessage(
                                "Profile",
                                "Profile settings saved successfully."
                        )
        );

        card.getChildren().addAll(
                heading,
                grid,
                saveButton
        );

        return card;
    }

    // =====================================================
    // APPLICATION
    // =====================================================

    private VBox createApplicationSection() {

        VBox card =
                createCard();

        Label heading =
                createSectionTitle(
                        "Application Settings"
                );

        GridPane grid =
                createGrid();

        ComboBox<String> language =
                new ComboBox<>();

        language.getItems().addAll(
                "English",
                "Marathi",
                "Hindi"
        );

        language.setValue(
                "English"
        );

        ComboBox<String> timezone =
                new ComboBox<>();

        timezone.getItems().addAll(
                "Asia/Kolkata",
                "UTC"
        );

        timezone.setValue(
                "Asia/Kolkata"
        );

        grid.add(
                createLabel("Language"),
                0,
                0
        );

        grid.add(
                language,
                1,
                0
        );

        grid.add(
                createLabel("Timezone"),
                0,
                1
        );

        grid.add(
                timezone,
                1,
                1
        );

        CheckBox maintenance =
                new CheckBox(
                        "Enable maintenance mode"
                );

        maintenance.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        maintenance.setTextFill(
                Color.web(TEXT)
        );

        grid.add(
                createLabel("System"),
                0,
                2
        );

        grid.add(
                maintenance,
                1,
                2
        );

        Button saveButton =
                createPrimaryButton(
                        "Save Settings"
                );

        saveButton.setOnAction(
                e ->
                        showMessage(
                                "Settings",
                                "Application settings saved successfully."
                        )
        );

        card.getChildren().addAll(
                heading,
                grid,
                saveButton
        );

        return card;
    }

    // =====================================================
    // NOTIFICATIONS
    // =====================================================

    private VBox createNotificationSection() {

        VBox card =
                createCard();

        Label heading =
                createSectionTitle(
                        "Notification Preferences"
                );

        CheckBox serviceNotifications =
                new CheckBox(
                        "Service request notifications"
                );

        CheckBox sosNotifications =
                new CheckBox(
                        "SOS notifications"
                );

        CheckBox complaintNotifications =
                new CheckBox(
                        "Complaint notifications"
                );

        CheckBox reviewNotifications =
                new CheckBox(
                        "Review notifications"
                );

        serviceNotifications.setSelected(true);
        sosNotifications.setSelected(true);
        complaintNotifications.setSelected(true);
        reviewNotifications.setSelected(true);

        VBox options =
                new VBox(
                        12,
                        serviceNotifications,
                        sosNotifications,
                        complaintNotifications,
                        reviewNotifications
                );

        for (
                javafx.scene.Node node :
                options.getChildren()
        ) {

            ((CheckBox) node).setFont(
                    Font.font(
                            "Arial",
                            16
                    )
            );

            ((CheckBox) node).setTextFill(
                    Color.web(TEXT)
            );
        }

        Button saveButton =
                createPrimaryButton(
                        "Save Notification Preferences"
                );

        saveButton.setOnAction(
                e ->
                        showMessage(
                                "Notifications",
                                "Notification preferences saved."
                        )
        );

        card.getChildren().addAll(
                heading,
                options,
                saveButton
        );

        return card;
    }

    // =====================================================
    // SECURITY
    // =====================================================

    private VBox createSecuritySection() {

        VBox card =
                createCard();

        Label heading =
                createSectionTitle(
                        "Security"
                );

        GridPane grid =
                createGrid();

        PasswordField currentPassword =
                new PasswordField();

        currentPassword.setPromptText(
                "Current password"
        );

        PasswordField newPassword =
                new PasswordField();

        newPassword.setPromptText(
                "New password"
        );

        PasswordField confirmPassword =
                new PasswordField();

        confirmPassword.setPromptText(
                "Confirm new password"
        );

        grid.add(
                createLabel("Current Password"),
                0,
                0
        );

        grid.add(
                currentPassword,
                1,
                0
        );

        grid.add(
                createLabel("New Password"),
                0,
                1
        );

        grid.add(
                newPassword,
                1,
                1
        );

        grid.add(
                createLabel("Confirm Password"),
                0,
                2
        );

        grid.add(
                confirmPassword,
                1,
                2
        );

        Button changeButton =
                createPrimaryButton(
                        "Change Password"
                );

        changeButton.setOnAction(
                e -> {

                    if (
                            newPassword
                                    .getText()
                                    .isEmpty()
                    ) {

                        showMessage(
                                "Security",
                                "Please enter a new password."
                        );

                        return;
                    }

                    if (
                            !newPassword
                                    .getText()
                                    .equals(
                                            confirmPassword
                                                    .getText()
                                    )
                    ) {

                        showMessage(
                                "Security",
                                "New password and confirm password do not match."
                        );

                        return;
                    }

                    showMessage(
                            "Security",
                            "Password change request completed."
                    );
                }
        );

        card.getChildren().addAll(
                heading,
                grid,
                changeButton
        );

        return card;
    }

    // =====================================================
    // CARD
    // =====================================================

    private VBox createCard() {

        VBox card =
                new VBox(16);

        card.setPadding(
                new Insets(22)
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

        return card;
    }

    // =====================================================
    // SECTION TITLE
    // =====================================================

    private Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        label.setTextFill(
                Color.web(HEADING)
        );

        return label;
    }

    // =====================================================
    // GRID
    // =====================================================

    private GridPane createGrid() {

        GridPane grid =
                new GridPane();

        grid.setHgap(15);
        grid.setVgap(14);

        return grid;
    }

    // =====================================================
    // LABEL
    // =====================================================

    private Label createLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        label.setTextFill(
                Color.web(TEXT)
        );

        return label;
    }

    // =====================================================
    // BUTTON
    // =====================================================

    private Button createPrimaryButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefHeight(44);

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        button.setTextFill(
                Color.WHITE
        );

        button.setStyle(
                "-fx-background-color: " +
                BLUE +
                ";" +
                "-fx-background-radius: 8;"
        );

        return button;
    }

    // =====================================================
    // MESSAGE
    // =====================================================

    private void showMessage(
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
}
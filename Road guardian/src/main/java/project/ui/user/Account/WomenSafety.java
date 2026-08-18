package project.ui.user.Account;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import project.ui.user.DashBoard.UserSectionPage;

public class WomenSafety extends UserSectionPage {

    private static final String MAIN_BACKGROUND =
            "#D6F0F3";

    private boolean sosActivated = false;

    // =====================================================
    // GET VIEW
    // =====================================================

    @Override
    public VBox getView() {

        VBox content =
                createContent();

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color: " +
                        MAIN_BACKGROUND +
                        ";" +
                        "-fx-background: " +
                        MAIN_BACKGROUND +
                        ";" +
                        "-fx-border-color: transparent;"
        );

        VBox root =
                new VBox();

        root.setFillWidth(true);

        root.setStyle(
                "-fx-background-color: " +
                        MAIN_BACKGROUND +
                        ";"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        root.getChildren().add(
                scrollPane
        );

        return root;
    }

    // =====================================================
    // MAIN CONTENT
    // =====================================================

    private VBox createContent() {

        VBox content =
                new VBox(20);

        content.setPadding(
                new Insets(
                        30,
                        35,
                        30,
                        35
                )
        );

        content.setFillWidth(true);

        content.setStyle(
                "-fx-background-color: " +
                        MAIN_BACKGROUND +
                        ";"
        );

        VBox heading =
                createHeading();

        HBox safetyCard =
                createSafetyCard();

        HBox lowerSection =
                createLowerSection();

        content.getChildren().addAll(
                heading,
                safetyCard,
                lowerSection
        );

        return content;
    }

    // =====================================================
    // HEADING
    // =====================================================

    private VBox createHeading() {

        VBox heading =
                new VBox(3);

        Label title =
                new Label(
                        "Women Safety"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #172033;"
        );

        Label subtitle =
                new Label(
                        "Extra protection and emergency assistance " +
                                "for safer journeys"
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #737d8d;"
        );

        heading.getChildren().addAll(
                title,
                subtitle
        );

        return heading;
    }

    // =====================================================
    // MAIN SAFETY CARD
    // =====================================================

    private HBox createSafetyCard() {

        HBox card =
                new HBox(25);

        card.setPadding(
                new Insets(25)
        );

        card.setPrefHeight(
                220
        );

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setStyle(
                "-fx-background-color: #eef3f8;" +
                        "-fx-border-color: #d7e0e8;" +
                        "-fx-border-radius: 22;" +
                        "-fx-background-radius: 22;"
        );

        VBox left =
                new VBox(12);

        HBox.setHgrow(
                left,
                Priority.ALWAYS
        );

        Label icon =
                new Label(
                        "♙"
                );

        icon.setPrefWidth(
                55
        );

        icon.setPrefHeight(
                55
        );

        icon.setAlignment(
                Pos.CENTER
        );

        icon.setStyle(
                "-fx-background-color: #fde7e9;" +
                        "-fx-background-radius: 35;" +
                        "-fx-text-fill: #f04444;" +
                        "-fx-font-size: 25px;"
        );

        Label title =
                new Label(
                        "Women Safety Mode"
                );

        title.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #172033;"
        );

        Label description =
                new Label(
                        "Your safety features are ready whenever " +
                                "you need them."
                );

        description.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #737d8d;"
        );

        Label status =
                new Label(
                        "Safety mode is ON"
                );

        status.setPadding(
                new Insets(
                        6,
                        12,
                        6,
                        12
                )
        );

        status.setStyle(
                "-fx-background-color: #d7f3eb;" +
                        "-fx-background-radius: 20;" +
                        "-fx-text-fill: #00a987;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;"
        );

        left.getChildren().addAll(
                icon,
                title,
                description,
                status
        );

        VBox right =
                new VBox(12);

        right.setPrefWidth(
                280
        );

        right.setAlignment(
                Pos.CENTER
        );

        right.setPadding(
                new Insets(20)
        );

        right.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #dfe5ec;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;"
        );

        Label sosTitle =
                new Label(
                        "Emergency SOS"
                );

        sosTitle.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #172033;"
        );

        Label sosText =
                new Label(
                        "Press SOS if you feel unsafe"
                );

        sosText.setStyle(
                "-fx-font-size: 11px;" +
                        "-fx-text-fill: #737d8d;"
        );

        Button sosButton =
                new Button(
                        "SOS Emergency"
                );

        sosButton.setPrefHeight(
                42
        );

        sosButton.setPrefWidth(
                190
        );

        sosButton.setStyle(
                "-fx-background-color: #2563EB;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 25;" +
                        "-fx-cursor: hand;"
        );

        sosButton.setOnAction(
                event ->
                        handleEmergencySos(
                                sosButton,
                                sosText,
                                status
                        )
        );

        right.getChildren().addAll(
                sosTitle,
                sosText,
                sosButton
        );

        card.getChildren().addAll(
                left,
                right
        );

        return card;
    }

    // =====================================================
    // SOS ACTION
    // =====================================================

    private void handleEmergencySos(
            Button sosButton,
            Label sosText,
            Label safetyStatus
    ) {

        if (sosActivated) {
            return;
        }

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        if (
                sosButton.getScene() != null &&
                        sosButton.getScene().getWindow() != null
        ) {

            confirmation.initOwner(
                    sosButton
                            .getScene()
                            .getWindow()
            );
        }

        confirmation.setTitle(
                "Confirm emergency SOS"
        );

        confirmation.setHeaderText(
                "Do you want to activate the emergency SOS?"
        );

        confirmation.setContentText(
                "This demo will mark an alert as sent " +
                        "to your emergency contacts."
        );

        if (
                confirmation.showAndWait()
                        .orElse(
                                ButtonType.CANCEL
                        )
                        == ButtonType.OK
        ) {

            sosActivated = true;

            sosText.setText(
                    "Emergency alert sent to your trusted contacts"
            );

            safetyStatus.setText(
                    "SOS alert is active"
            );

            safetyStatus.setStyle(
                    "-fx-background-color: #fde1e1;" +
                            "-fx-background-radius: 20;" +
                            "-fx-text-fill: #f04444;" +
                            "-fx-font-size: 11px;" +
                            "-fx-font-weight: bold;"
            );

            sosButton.setText(
                    "SOS activated"
            );

            sosButton.setDisable(
                    true
            );

            Alert sent =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            if (
                    sosButton.getScene() != null &&
                            sosButton.getScene().getWindow() != null
            ) {

                sent.initOwner(
                        sosButton
                                .getScene()
                                .getWindow()
                );
            }

            sent.setTitle(
                    "SOS activated"
            );

            sent.setHeaderText(
                    null
            );

            sent.setContentText(
                    "Emergency alert has been activated " +
                            "for your trusted contacts."
            );

            sent.showAndWait();
        }
    }

    // =====================================================
    // LOWER SECTION
    // =====================================================

    private HBox createLowerSection() {

        HBox section =
                new HBox(18);

        VBox contacts =
                createEmergencyContacts();

        VBox features =
                createSafetyFeatures();

        HBox.setHgrow(
                contacts,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                features,
                Priority.ALWAYS
        );

        section.getChildren().addAll(
                contacts,
                features
        );

        return section;
    }

    // =====================================================
    // EMERGENCY CONTACTS
    // =====================================================

    private VBox createEmergencyContacts() {

        VBox card =
                new VBox(14);

        card.setPadding(
                new Insets(20)
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        card.setStyle(
                cardStyle()
        );

        Label title =
                new Label(
                        "Emergency Contacts"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #172033;"
        );

        card.getChildren().add(
                title
        );

        card.getChildren().add(
                contact(
                        "Family",
                        "Primary emergency contact",
                        "Call"
                )
        );

        card.getChildren().add(
                contact(
                        "RoadGuardian Support",
                        "24 × 7 emergency assistance",
                        "Call"
                )
        );

        card.getChildren().add(
                contact(
                        "Local Emergency Services",
                        "Emergency response",
                        "SOS"
                )
        );

        return card;
    }

    // =====================================================
    // CONTACT
    // =====================================================

    private HBox contact(
            String name,
            String description,
            String buttonText
    ) {

        HBox row =
                new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(10)
        );

        row.setStyle(
                "-fx-background-color: #f3f6f9;" +
                        "-fx-background-radius: 18;"
        );

        VBox text =
                new VBox(2);

        HBox.setHgrow(
                text,
                Priority.ALWAYS
        );

        Label nameLabel =
                new Label(
                        name
                );

        nameLabel.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #263142;"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setStyle(
                "-fx-font-size: 10px;" +
                        "-fx-text-fill: #737d8d;"
        );

        text.getChildren().addAll(
                nameLabel,
                descriptionLabel
        );

        Button actionButton =
                new Button(
                        buttonText
                );

        actionButton.setPrefHeight(
                32
        );

        actionButton.setStyle(
                "-fx-background-color: #2869e8;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 20;" +
                        "-fx-cursor: hand;"
        );

        actionButton.setOnAction(
                event ->
                        handleContactAction(
                                name,
                                buttonText,
                                actionButton
                        )
        );

        row.getChildren().addAll(
                text,
                actionButton
        );

        return row;
    }

    // =====================================================
    // CONTACT ACTION
    // =====================================================

    private void handleContactAction(
            String contactName,
            String action,
            Button actionButton
    ) {

        boolean isSos =
                "SOS".equalsIgnoreCase(
                        action
                );

        String actionName =
                isSos
                        ? "send an SOS to"
                        : "call";

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        if (
                actionButton.getScene() != null &&
                        actionButton.getScene().getWindow() != null
        ) {

            confirmation.initOwner(
                    actionButton
                            .getScene()
                            .getWindow()
            );
        }

        confirmation.setTitle(
                isSos
                        ? "Confirm SOS"
                        : "Confirm call"
        );

        confirmation.setHeaderText(
                "Do you want to " +
                        actionName +
                        " " +
                        contactName +
                        "?"
        );

        confirmation.setContentText(
                "This is a RoadGuardian demo action; " +
                        "no real call or message will be sent."
        );

        if (
                confirmation.showAndWait()
                        .orElse(
                                ButtonType.CANCEL
                        )
                        == ButtonType.OK
        ) {

            actionButton.setText(
                    isSos
                            ? "SOS sent"
                            : "Call requested"
            );

            actionButton.setDisable(
                    true
            );

            Alert completed =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            if (
                    actionButton.getScene() != null &&
                            actionButton.getScene().getWindow() != null
            ) {

                completed.initOwner(
                        actionButton
                                .getScene()
                                .getWindow()
                );
            }

            completed.setTitle(
                    isSos
                            ? "SOS sent"
                            : "Call requested"
            );

            completed.setHeaderText(
                    null
            );

            completed.setContentText(
                    isSos
                            ? "SOS alert has been prepared for " +
                            contactName +
                            "."
                            : "Call request has been initiated for " +
                            contactName +
                            "."
            );

            completed.showAndWait();
        }
    }

    // =====================================================
    // SAFETY FEATURES
    // =====================================================

    private VBox createSafetyFeatures() {

        VBox card =
                new VBox(14);

        card.setPadding(
                new Insets(20)
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        card.setStyle(
                cardStyle()
        );

        Label title =
                new Label(
                        "Safety Features"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #172033;"
        );

        card.getChildren().add(
                title
        );

        card.getChildren().add(
                feature(
                        "Live Location Sharing",
                        "Share your current location with trusted contacts."
                )
        );

        card.getChildren().add(
                feature(
                        "Trip Monitoring",
                        "Keep your journey monitored until you reach safely."
                )
        );

        card.getChildren().add(
                feature(
                        "Emergency Alerts",
                        "Send an alert to your selected emergency contacts."
                )
        );

        return card;
    }

    // =====================================================
    // FEATURE
    // =====================================================

    private HBox feature(
            String title,
            String description
    ) {

        HBox row =
                new HBox(10);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon =
                new Label(
                        "✓"
                );

        icon.setPrefWidth(
                32
        );

        icon.setPrefHeight(
                32
        );

        icon.setAlignment(
                Pos.CENTER
        );

        icon.setStyle(
                "-fx-background-color: #d7f3eb;" +
                        "-fx-background-radius: 20;" +
                        "-fx-text-fill: #00a987;" +
                        "-fx-font-weight: bold;"
        );

        VBox text =
                new VBox(2);

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #263142;"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setStyle(
                "-fx-font-size: 10px;" +
                        "-fx-text-fill: #737d8d;"
        );

        text.getChildren().addAll(
                titleLabel,
                descriptionLabel
        );

        row.getChildren().addAll(
                icon,
                text
        );

        return row;
    }

    // =====================================================
    // CARD STYLE
    // =====================================================

    private String cardStyle() {

        return
                "-fx-background-color: #f9fcfd;" +
                        "-fx-border-color: #d5e0e7;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;";
    }
}
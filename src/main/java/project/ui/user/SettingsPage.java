package project.ui.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import project.ui.user.UserHeader;

import java.util.IdentityHashMap;
import java.util.Map;

public class SettingsPage {

    private boolean darkModeEnabled = false;

    private final Map<Node, String> lightStyles =
            new IdentityHashMap<>();

    private Scene settingsScene;

    // ============================================================
    // PUBLIC SCENE
    // ============================================================

    public Scene getSettingsScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #D6F0F3;"
        );

        HBox header = UserHeader.createHeader();
        ScrollPane sidebar =
                UserSideBar.createSidebar("Settings");

        VBox content =
                createContent();

        ScrollPane contentScroll =
                new ScrollPane(content);

        contentScroll.setFitToWidth(true);
        contentScroll.setFitToHeight(false);
        contentScroll.setPannable(true);

        contentScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        contentScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.ALWAYS
        );

        contentScroll.setStyle(
                "-fx-background-color: #D6F0F3;" +
                "-fx-border-color: transparent;"
        );

        root.setTop(header);
        root.setLeft(sidebar);
        root.setCenter(contentScroll);

        settingsScene =new Scene(root,UserDashboard.dashboardStage.getWidth(),UserDashboard.dashboardStage.getHeight());

        return settingsScene;
    }

    
    // ============================================================
    // CONTENT
    // ============================================================

    private VBox createContent() {

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(25, 30, 30, 30)
        );

        content.setStyle(
                "-fx-background-color: #D6F0F3;"
        );

        VBox heading =
                createHeading();

        HBox topCards =
                new HBox(18);

        VBox appearance =
                createAppearanceCard();

        VBox notifications =
                createNotificationCard();

        HBox.setHgrow(
                appearance,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                notifications,
                Priority.ALWAYS
        );

        topCards.getChildren().addAll(
                appearance,
                notifications
        );

        HBox bottomCards =
                new HBox(18);

        VBox contacts =
                createEmergencyContactsCard();

        VBox vehicle =
                createVehicleDetailsCard();

        HBox.setHgrow(
                contacts,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                vehicle,
                Priority.ALWAYS
        );

        bottomCards.getChildren().addAll(
                contacts,
                vehicle
        );

        content.getChildren().addAll(
                heading,
                topCards,
                bottomCards
        );

        return content;
    }

    // ============================================================
    // HEADING
    // ============================================================

    private VBox createHeading() {

        VBox heading =
                new VBox(2);

        Label title =
                new Label("Settings");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label subtitle =
                new Label(
                        "Preferences apply across web and mobile"
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

    // ============================================================
    // APPEARANCE
    // ============================================================

    private VBox createAppearanceCard() {

        VBox card =
                new VBox(14);

        card.setPrefHeight(315);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        Label title =
                new Label("Appearance & language");

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        HBox darkMode =
                new HBox();

        darkMode.setPrefHeight(66);

        darkMode.setPadding(
                new Insets(12, 15, 12, 15)
        );

        darkMode.setAlignment(
                Pos.CENTER_LEFT
        );

        darkMode.setStyle(
                "-fx-background-color: #f3f6f9;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;"
        );

        VBox darkText =
                new VBox(2);

        Label darkTitle =
                new Label("Dark mode");

        darkTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #263142;"
        );

        Label darkDescription =
                new Label(
                        "Easier on the eyes during night-time assists."
                );

        darkDescription.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #737d8d;"
        );

        darkText.getChildren().addAll(
                darkTitle,
                darkDescription
        );

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button darkToggle =
                toggleButton(darkModeEnabled);

        darkToggle.setOnAction(
                event -> {

                    darkModeEnabled =
                            !darkModeEnabled;

                    applyTheme(
                            (Parent) darkToggle
                                    .getScene()
                                    .getRoot()
                    );

                    setToggleState(
                            darkToggle,
                            darkModeEnabled
                    );
                }
        );

        darkMode.getChildren().addAll(
                darkText,
                space,
                darkToggle
        );

        Label languageTitle =
                new Label("Language");

        languageTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #263142;"
        );

        ComboBox<String> language =
                new ComboBox<>();

        language.getItems().addAll(
                "English",
                "Hindi",
                "Marathi"
        );

        language.setValue("English");

        language.setMaxWidth(
                Double.MAX_VALUE
        );

        language.setPrefHeight(42);

        language.setStyle(
                "-fx-background-color: #f3f6f9;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;" +
                "-fx-font-size: 12px;"
        );

        language.setOnAction(
                event ->
                        showInfo(
                                language,
                                "Language selected",
                                "Language preference set to " +
                                        language.getValue() +
                                        "."
                        )
        );

        card.getChildren().addAll(
                title,
                darkMode,
                languageTitle,
                language
        );

        return card;
    }

    // ============================================================
    // NOTIFICATION PREFERENCES
    // ============================================================

    private VBox createNotificationCard() {

        VBox card =
                new VBox(10);

        card.setPrefHeight(315);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        Label title =
                new Label(
                        "Notification preferences"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        card.getChildren().addAll(
                title,

                preferenceRow(
                        "Mechanic status updates",
                        true
                ),

                preferenceRow(
                        "Document expiry reminders",
                        true
                ),

                preferenceRow(
                        "Service due alerts",
                        true
                ),

                preferenceRow(
                        "Offers and partner deals",
                        false
                )
        );

        return card;
    }

    // ============================================================
    // PREFERENCE ROW
    // ============================================================

    private HBox preferenceRow(
            String text,
            boolean active
    ) {

        HBox row =
                new HBox();

        row.setPrefHeight(50);

        row.setPadding(
                new Insets(0, 15, 0, 15)
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setStyle(
                "-fx-background-color: #f3f6f9;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;"
        );

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #263142;"
        );

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button toggle =
                toggleButton(active);

        toggle.setOnAction(
                event ->
                        toggleState(toggle)
        );

        row.getChildren().addAll(
                label,
                space,
                toggle
        );

        return row;
    }

    // ============================================================
    // TOGGLE
    // ============================================================

    private Button toggleButton(
            boolean active
    ) {

        Button toggle =
                new Button();

        toggle.setPrefWidth(38);
        toggle.setPrefHeight(22);

        toggle.setPadding(
                Insets.EMPTY
        );

        setToggleState(
                toggle,
                active
        );

        return toggle;
    }

    private void toggleState(
            Button toggle
    ) {

        boolean active =
                !Boolean.TRUE.equals(
                        toggle.getUserData()
                );

        setToggleState(
                toggle,
                active
        );
    }

    private void setToggleState(
            Button toggle,
            boolean active
    ) {

        toggle.setUserData(active);

        toggle.setText(
                active ? "●" : "○"
        );

        toggle.setStyle(
                "-fx-background-color: " +
                        (active
                                ? "#2869e8"
                                : "#dfe5eb") +
                        ";" +
                        "-fx-background-radius: 15;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;"
        );
    }

    // ============================================================
    // EMERGENCY CONTACTS
    // ============================================================

    private VBox createEmergencyContactsCard() {

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        Label title =
                new Label(
                        "Emergency contacts"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Button addContact =
                new Button("Add contact");

        addContact.setMaxWidth(
                Double.MAX_VALUE
        );

        addContact.setPrefHeight(38);

        addContact.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;" +
                "-fx-text-fill: #263142;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );

        addContact.setOnAction(
                event ->
                        card.getChildren().add(
                                card.getChildren().size() - 1,
                                contactRow(
                                        "New contact",
                                        ""
                                )
                        )
        );

        card.getChildren().addAll(
                title,

                contactRow(
                        "Priya Nair",
                        "+91 98200 11223"
                ),

                contactRow(
                        "Rahul Nair",
                        "+91 99870 44518"
                ),

                contactRow(
                        "National Emergency",
                        "112"
                ),

                addContact
        );

        return card;
    }

    // ============================================================
    // CONTACT ROW
    // ============================================================

    private HBox contactRow(
            String name,
            String number
    ) {

        HBox row =
                new HBox(8);

        row.setPrefHeight(52);

        TextField nameField =
                new TextField(name);

        nameField.setPrefHeight(38);

        nameField.setStyle(
                "-fx-background-color: #f3f6f9;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;" +
                "-fx-font-size: 12px;"
        );

        HBox.setHgrow(
                nameField,
                Priority.ALWAYS
        );

        TextField numberField =
                new TextField(number);

        numberField.setPrefHeight(38);

        numberField.setStyle(
                "-fx-background-color: #f3f6f9;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;" +
                "-fx-font-size: 12px;"
        );

        HBox.setHgrow(
                numberField,
                Priority.ALWAYS
        );

        row.getChildren().addAll(
                nameField,
                numberField
        );

        return row;
    }

    // ============================================================
    // VEHICLE DETAILS
    // ============================================================

    private VBox createVehicleDetailsCard() {

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        Label title =
                new Label("Vehicle details");

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        HBox row1 =
                new HBox(10);

        VBox make =
                vehicleField(
                        "Make & model",
                        "Honda City VX CVT"
                );

        VBox registration =
                vehicleField(
                        "Registration",
                        "MH 12 QR 4410"
                );

        HBox.setHgrow(
                make,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                registration,
                Priority.ALWAYS
        );

        row1.getChildren().addAll(
                make,
                registration
        );

        HBox row2 =
                new HBox(10);

        VBox fuel =
                vehicleField(
                        "Fuel type",
                        "Petrol"
                );

        VBox year =
                vehicleField(
                        "Year",
                        "2022"
                );

        HBox.setHgrow(
                fuel,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                year,
                Priority.ALWAYS
        );

        row2.getChildren().addAll(
                fuel,
                year
        );

        Button save =
                new Button("Save changes");

        save.setMaxWidth(
                Double.MAX_VALUE
        );

        save.setPrefHeight(38);

        save.setStyle(
                "-fx-background-color: #2869e8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;"
        );

        save.setOnAction(
                event ->
                        showInfo(
                                save,
                                "Settings saved",
                                "Your vehicle details and current preferences have been saved."
                        )
        );

        card.getChildren().addAll(
                title,
                row1,
                row2,
                save
        );

        return card;
    }

    // ============================================================
    // VEHICLE FIELD
    // ============================================================

    private VBox vehicleField(
            String title,
            String value
    ) {

        VBox box =
                new VBox(4);

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #263142;"
        );

        TextField field =
                new TextField(value);

        field.setPrefHeight(40);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setStyle(
                "-fx-background-color: #f3f6f9;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;" +
                "-fx-font-size: 12px;"
        );

        box.getChildren().addAll(
                titleLabel,
                field
        );

        return box;
    }

    // ============================================================
    // DARK MODE
    // ============================================================

    private void applyTheme(Parent root) {

        if (darkModeEnabled) {

            lightStyles.clear();

            applyDarkTheme(root);

        } else {

            restoreLightTheme(root);

            lightStyles.clear();
        }
    }

    private void applyDarkTheme(Node node) {

        lightStyles.putIfAbsent(
                node,
                node.getStyle()
        );

        node.setStyle(
                toDarkStyle(
                        lightStyles.get(node)
                )
        );

        if (node instanceof Parent parent) {

            for (
                    Node child :
                    parent.getChildrenUnmodifiable()
            ) {

                applyDarkTheme(child);
            }
        }
    }

    private void restoreLightTheme(Node node) {

        String style =
                lightStyles.get(node);

        if (style != null) {
            node.setStyle(style);
        }

        if (node instanceof Parent parent) {

            for (
                    Node child :
                    parent.getChildrenUnmodifiable()
            ) {

                restoreLightTheme(child);
            }
        }
    }

    private String toDarkStyle(
            String style
    ) {

        return style
                .replace("#172033", "#f8fafc")
                .replace("#263142", "#e2e8f0")
                .replace("#737d8d", "#aebfd3")
                .replace("#65717d", "#aebfd3")
                .replace(
                        "-fx-background-color: white",
                        "-fx-background-color: #172033"
                )
                .replace(
                        "#D6F0F3",
                        "#0f172a"
                )
                .replace("#eef3f8", "#172033")
                .replace("#f9fcfd", "#172033")
                .replace("#f3f6f9", "#1f2937")
                .replace("#e8efff", "#263a56")
                .replace("#dfe5eb", "#334155")
                .replace("#d5e0e7", "#334155")
                .replace("#bddfe5", "#334155")
                .replace("#cfd9e3", "#334155");
    }

    // ============================================================
    // INFO ALERT
    // ============================================================

    private void showInfo(
            Node source,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.initOwner(
                source.getScene().getWindow()
        );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // ============================================================
    // CARD STYLE
    // ============================================================

    private String cardStyle() {

        return
                "-fx-background-color: #eef3f8;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;";
    }
}
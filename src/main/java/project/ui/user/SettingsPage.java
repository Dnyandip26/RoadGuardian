package project.ui.user;

import project.app.AppNavigator;
import javafx.application.Application;
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
import javafx.stage.Stage;

import java.util.IdentityHashMap;
import java.util.Map;

public class SettingsPage extends Application {

    private boolean darkModeEnabled;
    private final Map<Node, String> lightStyles = new IdentityHashMap<>();

    @Override
    public void start(Stage stage) {

        BorderPane main = new BorderPane();

        main.setStyle(
                "-fx-background-color: #d8f3f7;"
        );

        // =====================================================
        // TOP BAR
        // =====================================================

        HBox topBar = createTopBar();

        main.setTop(topBar);

        // =====================================================
        // SIDEBAR
        // =====================================================

        VBox sidebar = createSidebar();

        ScrollPane sidebarScroll = new ScrollPane(sidebar);

        sidebarScroll.setPrefWidth(280);
        sidebarScroll.setFitToWidth(true);

        sidebarScroll.setStyle(
                "-fx-background-color: #f3efef;" +
                "-fx-background: #060707;" +
                "-fx-fill:black"
        );

        main.setLeft(sidebarScroll);

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = createContent();

        ScrollPane contentScroll = new ScrollPane(content);

        contentScroll.setFitToWidth(true);

        contentScroll.setStyle(
                "-fx-background-color: #d8f3f7;" +
                "-fx-background: #d8f3f7;" +
                "-fx-fill:black"
        );

        main.setCenter(contentScroll);

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene = new Scene(
                main,
                1500,
                800
        );

        if (darkModeEnabled) {
            applyDarkTheme(main);
        }

        stage.setTitle(
                "RoadGuardian - Settings"
        );

        stage.setScene(scene);

        stage.show();
    }

    // =====================================================
    // TOP BAR
    // =====================================================

    private HBox createTopBar() {

        HBox topBar = new HBox();

        topBar.setPrefHeight(68);
        topBar.setAlignment(Pos.CENTER_LEFT);

        topBar.setPadding(
                new Insets(0, 18, 0, 20)
        );

        topBar.setSpacing(20);

        topBar.setStyle(
                "-fx-background-color: #F4F4F4;" +
                "-fx-border-color: #bddfe5;" +
                "-fx-border-width: 0 0 1 0;"
        );

        // Logo

        Label logo = new Label("✓");

        logo.setPrefWidth(44);
        logo.setPrefHeight(44);

        logo.setAlignment(
                Pos.CENTER
        );

        logo.setStyle(
                "-fx-background-color: #2869e8;" +
                "-fx-background-radius: 30;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;"
        );

        // RoadGuardian

        Label roadGuardian = new Label(
                "RoadGuardian"
        );

        roadGuardian.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        HBox brand = new HBox(
                10,
                logo,
                roadGuardian
        );

        brand.setAlignment(
                Pos.CENTER_LEFT
        );

        // Space

        Region space = new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        // Search

        HBox searchBox = new HBox(8);

        searchBox.setPrefWidth(440);
        searchBox.setPrefHeight(46);

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.setPadding(
                new Insets(0, 16, 0, 16)
        );

        searchBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #cfd9e3;" +
                "-fx-border-radius: 25;" +
                "-fx-background-radius: 25;"
        );

        Label searchIcon = new Label("⌕");

        searchIcon.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-text-fill: #64748b;"
        );

        TextField searchField = new TextField();
        searchField.setPromptText("Search mechanics, invoices, vehicles...");
        searchField.setPrefWidth(360);
        searchField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #263142;" +
                "-fx-prompt-text-fill: #737d8d;" +
                "-fx-border-width: 0;"
        );

        searchBox.setOnMouseClicked(event -> searchField.requestFocus());

        searchBox.getChildren().addAll(
                searchIcon,
                searchField
        );

        // Notification

        Label notification = new Label("🔔");

        notification.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-text-fill: #525350;"
        );

        // Profile

        Label userInitial = new Label("AN");

        userInitial.setPrefWidth(34);
        userInitial.setPrefHeight(34);

        userInitial.setAlignment(
                Pos.CENTER
        );

        userInitial.setStyle(
                "-fx-background-color: #e8efff;" +
                "-fx-background-radius: 30;" +
                "-fx-text-fill: #2869e8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Label userName = new Label(
                "Aarav Nair"
        );

        userName.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #263142;"
        );

        Label userLocation = new Label(
                "Customer · Pune"
        );

        userLocation.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #737d8d;"
        );

        VBox userDetails = new VBox(
                1,
                userName,
                userLocation
        );

        userDetails.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox profile = new HBox(
                8,
                userInitial,
                userDetails
        );

        profile.setPrefHeight(48);

        profile.setAlignment(
                Pos.CENTER_LEFT
        );

        profile.setPadding(
                new Insets(4, 12, 4, 6)
        );

        profile.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #cfd9e3;" +
                "-fx-border-radius: 25;" +
                "-fx-background-radius: 25;"
        );

        topBar.getChildren().addAll(
                brand,
                space,
                searchBox,
                notification,
                profile
        );

        return topBar;
    }

    // =====================================================
    // SIDEBAR
    // =====================================================

    private VBox createSidebar() {

        VBox sidebar = new VBox(5);

        sidebar.setPrefWidth(280);

        sidebar.setPadding(
                new Insets(25, 15, 15, 15)
        );

        sidebar.setStyle(
                "-fx-background-color: #C4D9E0;" +
                "-fx-border-color: #b9dce2;" +
                "-fx-border-width: 0 1 0 0;"
        );

        // OVERVIEW

        sidebar.getChildren().add(
                sectionTitle("OVERVIEW")
        );

        sidebar.getChildren().add(
                sideButton("≡", "Dashboard")
        );

        sidebar.getChildren().add(
                sideButton("♧", "Emergency SOS")
        );

        sidebar.getChildren().add(
                sideButton("◇", "Live Map")
        );

        // ASSISTANCE

        Label assistance = sectionTitle(
                "ASSISTANCE"
        );

        VBox.setMargin(
                assistance,
                new Insets(12, 0, 0, 0)
        );

        sidebar.getChildren().add(
                assistance
        );

        sidebar.getChildren().add(
                sideButton("♧", "AI Diagnosis")
        );

        sidebar.getChildren().add(
                sideButton("⚒", "Mechanics")
        );

        sidebar.getChildren().add(
                sideButton("▱", "Tow Truck")
        );

        sidebar.getChildren().add(
                sideButton("≡", "Cost Estimator")
        );

        // GARAGE

        Label garage = sectionTitle(
                "GARAGE"
        );

        VBox.setMargin(
                garage,
                new Insets(12, 0, 0, 0)
        );

        sidebar.getChildren().add(
                garage
        );

        sidebar.getChildren().add(
                sideButton(
                        "▱",
                        "My Vehicles"
                )
        );

        sidebar.getChildren().add(
                sideButton(
                        "◷",
                        "Service History"
                )
        );

        sidebar.getChildren().add(
                sideButton(
                        "▤",
                        "Documents"
                )
        );

        // ACCOUNT

        Label account = sectionTitle(
                "ACCOUNT"
        );

        VBox.setMargin(
                account,
                new Insets(12, 0, 0, 0)
        );

        sidebar.getChildren().add(
                account
        );

        sidebar.getChildren().add(
                sideButton(
                        "♙",
                        "Women Safety"
                )
        );

        sidebar.getChildren().add(
                sideButton(
                        "♧",
                        "Notifications"
                )
        );

        // SETTINGS ACTIVE

        sidebar.getChildren().add(
                activeSideButton(
                        "⚙",
                        "Settings"
                )
        );

        return sidebar;
    }

    // =====================================================
    // MAIN CONTENT
    // =====================================================

    private VBox createContent() {

        VBox content = new VBox(18);

        content.setPadding(
                new Insets(25, 30, 30, 30)
        );

        content.setStyle("-fx-background-color: #F4F4F4;");
        // Heading

        VBox heading = createHeading();

        // Top Cards

        HBox topCards = new HBox(18);

        VBox appearanceCard =
                createAppearanceCard();

        VBox notificationCard =
                createNotificationCard();

        HBox.setHgrow(
                appearanceCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                notificationCard,
                Priority.ALWAYS
        );

        topCards.getChildren().addAll(
                appearanceCard,
                notificationCard
        );

        // Bottom Cards

        HBox bottomCards = new HBox(18);

        VBox contactsCard =
                createEmergencyContactsCard();

        VBox vehicleCard =
                createVehicleDetailsCard();

        HBox.setHgrow(
                contactsCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                vehicleCard,
                Priority.ALWAYS
        );

        bottomCards.getChildren().addAll(
                contactsCard,
                vehicleCard
        );

        content.getChildren().addAll(
                heading,
                topCards,
                bottomCards
        );

        return content;
    }

    // =====================================================
    // HEADING
    // =====================================================

    private VBox createHeading() {

        VBox heading = new VBox(2);

        Label title = new Label(
                "Settings"
        );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label subtitle = new Label(
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

    // =====================================================
    // APPEARANCE CARD
    // =====================================================

    private VBox createAppearanceCard() {

        VBox card = new VBox(14);

        card.setPrefHeight(315);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        Label title = new Label(
                "Appearance & language"
        );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        // Dark Mode

        HBox darkMode = new HBox();

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

        VBox darkText = new VBox(2);

        Label darkTitle = new Label(
                "Dark mode"
        );

        darkTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #263142;"
        );

        Label darkDescription = new Label(
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

        Region space = new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button darkToggle = toggleButton(darkModeEnabled);

        darkToggle.setOnAction(
                event -> {
                    darkModeEnabled = !darkModeEnabled;
                    applyTheme((Parent) darkToggle.getScene().getRoot());
                    setToggleState(darkToggle, darkModeEnabled);
                }
        );

        darkMode.getChildren().addAll(
                darkText,
                space,
                darkToggle
        );

        // Language

        Label languageTitle = new Label(
                "Language"
        );

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

        language.setValue(
                "English"
        );

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

        language.setOnAction(event -> showInfo(language, "Language selected",
                "Language preference set to " + language.getValue() + "."));

        card.getChildren().addAll(
                title,
                darkMode,
                languageTitle,
                language
        );

        return card;
    }

    // =====================================================
    // NOTIFICATION PREFERENCES
    // =====================================================

    private VBox createNotificationCard() {

        VBox card = new VBox(10);

        card.setPrefHeight(315);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        Label title = new Label(
                "Notification preferences"
        );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        HBox mechanic =
                preferenceRow(
                        "Mechanic status updates",
                        true
                );

        HBox document =
                preferenceRow(
                        "Document expiry reminders",
                        true
                );

        HBox service =
                preferenceRow(
                        "Service due alerts",
                        true
                );

        HBox offers =
                preferenceRow(
                        "Offers and partner deals",
                        false
                );

        card.getChildren().addAll(
                title,
                mechanic,
                document,
                service,
                offers
        );

        return card;
    }

    // =====================================================
    // PREFERENCE ROW
    // =====================================================

    private HBox preferenceRow(
            String text,
            boolean active) {

        HBox row = new HBox();

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

        Label label = new Label(
                text
        );

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #263142;"
        );

        Region space = new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button toggle =
                toggleButton(active);

        toggle.setOnAction(
                event -> toggleState(toggle)
        );

        row.getChildren().addAll(
                label,
                space,
                toggle
        );

        return row;
    }

    // =====================================================
    // TOGGLE BUTTON
    // =====================================================

    private Button toggleButton(
            boolean active) {

        Button toggle = new Button();

        toggle.setPrefWidth(38);
        toggle.setPrefHeight(22);

        toggle.setPadding(
                Insets.EMPTY
        );

        setToggleState(toggle, active);

        return toggle;
    }

    private void toggleState(Button toggle) {
        boolean active = !Boolean.TRUE.equals(toggle.getUserData());
        setToggleState(toggle, active);
    }

    private void setToggleState(Button toggle, boolean active) {
        toggle.setUserData(active);
        toggle.setText(active ? "●" : "○");
        toggle.setStyle(
                "-fx-background-color: " + (active ? "#2869e8" : "#dfe5eb") + ";" +
                "-fx-background-radius: 15;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;"
        );
    }

    private void applyTheme(Parent root) {
        if (darkModeEnabled) {
            applyDarkTheme(root);
        } else {
            restoreLightTheme(root);
            lightStyles.clear();
        }
    }

    private void applyDarkTheme(Node node) {
        lightStyles.putIfAbsent(node, node.getStyle());
        node.setStyle(toDarkStyle(lightStyles.get(node)));

        if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                applyDarkTheme(child);
            }
        }
    }

    private void restoreLightTheme(Node node) {
        String lightStyle = lightStyles.get(node);
        if (lightStyle != null) {
            node.setStyle(lightStyle);
        }

        if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                restoreLightTheme(child);
            }
        }
    }

    private String toDarkStyle(String style) {
        return style
                .replace("#172033", "#f8fafc")
                .replace("#263142", "#e2e8f0")
                .replace("#737d8d", "#aebfd3")
                .replace("#65717d", "#aebfd3")
                .replace("-fx-background-color: white", "-fx-background-color: #172033")
                .replace("#d8f3f7", "#0f172a")
                .replace("#afbbbf", "#0f172a")
                .replace("#eef3f8", "#172033")
                .replace("#f9fcfd", "#172033")
                .replace("#f3f6f9", "#1f2937")
                .replace("#e8efff", "#263a56")
                .replace("#dfe9ff", "#263a56")
                .replace("#d7f3eb", "#163b35")
                .replace("#fde1e1", "#4a2025")
                .replace("#fde7e9", "#4a2025")
                .replace("#d5e0e7", "#334155")
                .replace("#d7e0e8", "#334155")
                .replace("#bddfe5", "#334155")
                .replace("#b9dce2", "#334155")
                .replace("#cfd9e3", "#334155")
                .replace("#dfe5ec", "#334155");
    }

    private void showInfo(Node source, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(source.getScene().getWindow());
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // =====================================================
    // EMERGENCY CONTACTS
    // =====================================================

    private VBox createEmergencyContactsCard() {

        VBox card = new VBox(10);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        Label title = new Label(
                "Emergency contacts"
        );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        HBox contact1 =
                contactRow(
                        "Priya Nair",
                        "+91 98200 11223"
                );

        HBox contact2 =
                contactRow(
                        "Rahul Nair",
                        "+91 99870 44518"
                );

        HBox contact3 =
                contactRow(
                        "National Emergency",
                        "112"
                );

        Button addContact = new Button(
                "Add contact"
        );

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

        addContact.setOnAction(event ->
                card.getChildren().add(card.getChildren().size() - 1, contactRow("New contact", ""))
        );

        card.getChildren().addAll(
                title,
                contact1,
                contact2,
                contact3,
                addContact
        );

        return card;
    }

    // =====================================================
    // CONTACT ROW
    // =====================================================

    private HBox contactRow(
            String name,
            String number) {

        HBox row = new HBox(8);

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

    // =====================================================
    // VEHICLE DETAILS
    // =====================================================

    private VBox createVehicleDetailsCard() {

        VBox card = new VBox(10);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        Label title = new Label(
                "Vehicle details"
        );

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        // First row

        HBox row1 = new HBox(10);

        VBox makeBox =
                vehicleField(
                        "Make & model",
                        "Honda City VX CVT"
                );

        VBox registrationBox =
                vehicleField(
                        "Registration",
                        "MH 12 QR 4410"
                );

        HBox.setHgrow(
                makeBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                registrationBox,
                Priority.ALWAYS
        );

        row1.getChildren().addAll(
                makeBox,
                registrationBox
        );

        // Second row

        HBox row2 = new HBox(10);

        VBox fuelBox =
                vehicleField(
                        "Fuel type",
                        "Petrol"
                );

        VBox yearBox =
                vehicleField(
                        "Year",
                        "2022"
                );

        HBox.setHgrow(
                fuelBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                yearBox,
                Priority.ALWAYS
        );

        row2.getChildren().addAll(
                fuelBox,
                yearBox
        );

        Button saveButton = new Button(
                "Save changes"
        );

        saveButton.setMaxWidth(
                Double.MAX_VALUE
        );

        saveButton.setPrefHeight(38);

        saveButton.setStyle(
                "-fx-background-color: #2869e8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;"
        );

        saveButton.setOnAction(event -> showInfo(saveButton, "Settings saved",
                "Your vehicle details and current preferences have been saved."));

        card.getChildren().addAll(
                title,
                row1,
                row2,
                saveButton
        );

        return card;
    }

    // =====================================================
    // VEHICLE FIELD
    // =====================================================

    private VBox vehicleField(
            String title,
            String value) {

        VBox box = new VBox(4);

        Label titleLabel = new Label(
                title
        );

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

    // =====================================================
    // CARD STYLE
    // =====================================================

    private String cardStyle() {

        return
                "-fx-background-color: #eef3f8;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;";
    }

    // =====================================================
    // SIDEBAR BUTTON
    // =====================================================

    private HBox sideButton(
            String icon,
            String text) {

        Label iconLabel = new Label(
                icon
        );

        iconLabel.setPrefWidth(25);

        iconLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #334155;"
        );

        Label textLabel = new Label(
                text
        );

        textLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #263142;"
        );

        HBox button = new HBox(
                8,
                iconLabel,
                textLabel
        );

        button.setPrefHeight(43);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(0, 10, 0, 10)
        );

        button.setOnMouseClicked(event ->
                AppNavigator.navigate((Stage) button.getScene().getWindow(), text)
        );

        return button;
    }

    // =====================================================
    // ACTIVE SIDEBAR BUTTON
    // =====================================================

    private HBox activeSideButton(
            String icon,
            String text) {

        Label iconLabel = new Label(
                icon
        );

        iconLabel.setPrefWidth(25);

        iconLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: white;"
        );

        Label textLabel = new Label(
                text
        );

        textLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        HBox button = new HBox(
                8,
                iconLabel,
                textLabel
        );

        button.setPrefHeight(43);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPadding(
                new Insets(0, 10, 0, 10)
        );

        button.setStyle(
                "-fx-background-color: #f45b0a;" +
                "-fx-background-radius: 25;"
        );

        return button;
    }

    // =====================================================
    // SECTION TITLE
    // =====================================================

    private Label sectionTitle(
            String text) {

        Label label = new Label(
                text
        );

        label.setPadding(
                new Insets(5, 0, 5, 5)
        );

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65717d;"
        );

        return label;
    }
}

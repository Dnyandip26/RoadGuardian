package project.ui.user;

import project.app.AppNavigator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VehiclePage extends Application {

        public static Stage VehiclepageStage;
        private Scene vehiclepagescene;
        private final List<Vehicle> vehicles = new ArrayList<>(List.of(
                new Vehicle("BMW", "X1", "MH 12 QR 4510", "Petrol", "Automatic",
                        2022, 48_210, "White", "", "", "", "", "")
        ));
        private int selectedVehicleIndex = 0;

    @Override
    public void start(Stage stage) {
        VehiclepageStage=stage;
        show(stage);
    }
    public void show(Stage stage){

        BorderPane main = new BorderPane();

        main.setStyle( "-fx-background-color: #afbbbf;"  );

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

        sidebarScroll.setStyle(  "-fx-background-color: #f3efef;" + "-fx-background: #060707;"+"-fx-fill:black" );

        main.setLeft(sidebarScroll);

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = createContent();

        ScrollPane contentScroll = new ScrollPane(content);

        contentScroll.setFitToWidth(true);

        contentScroll.setStyle( "-fx-background-color: #caf4f4;" + "-fx-background: #d8f3f7;"+"-fx-fill:black" );


        main.setCenter(contentScroll);

        // =====================================================
        // SCENE
        // =====================================================

        
        Scene scene = new Scene( main,   1500,  800 );
        vehiclepagescene=scene;

        stage.setTitle(
                "RoadGuardian - My Vehicles"
        );

        stage.setScene(scene);

        stage.show();
    }
    public void backToHomepage(){
       VehiclepageStage.setScene(vehiclepagescene);
    }

    private Vehicle selectedVehicle() {
        return vehicles.get(selectedVehicleIndex);
    }

    /** Opens the form used by the Add vehicle button. */
    private void showAddVehicleDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add vehicle");
        dialog.setHeaderText("Enter your vehicle details");
        dialog.initOwner(VehiclepageStage);

        TextField makeField = new TextField();
        makeField.setPromptText("e.g. Honda");

        TextField modelField = new TextField();
        modelField.setPromptText("e.g. City ZX");

        TextField registrationField = new TextField();
        registrationField.setPromptText("e.g. MH 12 AB 1234");

        TextField vinField = new TextField();
        vinField.setPromptText(" VIN number");

        ComboBox<String> fuelTypeBox = new ComboBox<>();
        fuelTypeBox.getItems().addAll("Petrol", "Diesel", "Electric", "CNG", "Hybrid");
        fuelTypeBox.setValue("Petrol");
        fuelTypeBox.setMaxWidth(Double.MAX_VALUE);

        ComboBox<String> transmissionBox = new ComboBox<>();
        transmissionBox.getItems().addAll("Manual", "Automatic", "AMT", "CVT");
        transmissionBox.setValue("Manual");
        transmissionBox.setMaxWidth(Double.MAX_VALUE);

        TextField yearField = new TextField();
        yearField.setPromptText("e.g. 2024");

        TextField kmField = new TextField();
        kmField.setPromptText("e.g. 12000");

        TextField colourField = new TextField();
        colourField.setPromptText("e.g. Pearl White");

        TextField ownerField = new TextField();
        ownerField.setPromptText("Registered owner name");

        TextField insuranceField = new TextField();
        insuranceField.setPromptText("Policy number");

        TextField insuranceExpiryField = new TextField();
        insuranceExpiryField.setPromptText("e.g. 31 Dec 2026");

        TextField lastServiceField = new TextField();
        lastServiceField.setPromptText("e.g. 15 Jul 2026");

        GridPane form = new GridPane();
      //  form.setStyle("-fx-background-color: #a0ddf1;");
        form.setHgap(18);
        form.setVgap(12);
        form.setPadding(new Insets(15, 5, 5, 5));
        form.addRow(0, new Label("Make *"), makeField, new Label("Model *"), modelField);
        form.addRow(1, new Label("Registration number *"), registrationField, new Label("VIN /  number"), vinField);
        form.addRow(2, new Label("Fuel type *"), fuelTypeBox, new Label("Transmission"), transmissionBox);
        form.addRow(3, new Label("Manufacturing year *"), yearField, new Label("Current  (km) *"), kmField);
        form.addRow(4, new Label("Colour"), colourField, new Label("Registered owner"), ownerField);
        form.addRow(5, new Label("Insurance policy number"), insuranceField, new Label("Insurance expiry"), insuranceExpiryField);
        form.addRow(6, new Label("Last service date"), lastServiceField);

        dialog.getDialogPane().setContent(form);
        dialog.getDialogPane().setPrefWidth(820);
        dialog.getDialogPane().setPrefHeight(520);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

        dialog.showAndWait().filter(ButtonType.OK::equals).ifPresent(result -> {
            try {
                String make = makeField.getText().trim();
                String model = modelField.getText().trim();
                String registrationNumber = registrationField.getText().trim().toUpperCase();
                int year = Integer.parseInt(yearField.getText().trim());
                int km= Integer.parseInt(kmField.getText().trim());

                if (make.isEmpty() || model.isEmpty() || registrationNumber.isEmpty() || year < 1900
                        || year > 2100 || km < 0) {
                    throw new IllegalArgumentException();
                }

                vehicles.add(new Vehicle(make, model, registrationNumber, fuelTypeBox.getValue(),
                        transmissionBox.getValue(), year, km, colourField.getText().trim(),
                        vinField.getText().trim(), ownerField.getText().trim(),
                        insuranceField.getText().trim(), insuranceExpiryField.getText().trim(),
                        lastServiceField.getText().trim()));
                selectedVehicleIndex = vehicles.size() - 1;
                show(VehiclepageStage);
            } catch (IllegalArgumentException exception) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.initOwner(VehiclepageStage);
                error.setTitle("Invalid vehicle details");
                error.setHeaderText(null);
                error.setContentText("Please enter make, model, registration number, valid year, and non-negative km.");
                error.showAndWait();
            }
        });
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

        topBar.setStyle( "-fx-background-color: #F4F4F4;" +"-fx-border-color: #bddfe5;" + "-fx-border-width: 0 0 1 0;" );

        // Logo

        Label logo = new Label("✓");

        logo.setPrefWidth(44);
        logo.setPrefHeight(44);
        logo.setAlignment(Pos.CENTER);

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

        Label userInitial = new Label("vv");

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
                "vishal"
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
                activeSideButton(
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

        sidebar.getChildren().add(
                sideButton(
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

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(25, 30, 30, 30)
        );
        content.setStyle("-fx-background-color: #F4F4F4;");

        // Heading

        HBox heading = createHeading();

        // Upper section

        HBox upperSection = createUpperSection();

        // Lower section

        HBox lowerSection = createLowerSection();

        content.getChildren().addAll(
                heading,
                upperSection,
                lowerSection
        );

        return content;
    }

    // =====================================================
    // HEADING
    // =====================================================

    private HBox createHeading() {

        HBox heading = new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox headingText = new VBox(2);

        Label title = new Label(
                "My Vehicles"
        );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label subtitle = new Label(
                selectedVehicle().name + " " + selectedVehicle().model + " · "
                        + selectedVehicle().registrationNumber
        );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #737d8d;"
        );

        headingText.getChildren().addAll(
                title,
                subtitle
        );

        HBox.setHgrow(
                headingText,
                Priority.ALWAYS
        );

        Button addVehicle = new Button(
                "Add vehicle"
        );

        addVehicle.setPrefHeight(38);

        addVehicle.setPadding(
                new Insets(0, 18, 0, 18)
        );

        addVehicle.setStyle(
                "-fx-background-color: #2869e8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 25;"
        );

        addVehicle.setOnAction(event -> showAddVehicleDialog());

        heading.getChildren().addAll(
                headingText,
                addVehicle
        );

        return heading;
    }

    // =====================================================
    // UPPER SECTION
    // =====================================================

    private HBox createUpperSection() {

        HBox upperSection = new HBox(18);

        VBox vehicleCard = createVehicleCard();

        VBox rightCards = createRightCards();

        upperSection.getChildren().addAll(
                vehicleCard,
                rightCards
        );

        return upperSection;
    }

    // =====================================================
    // VEHICLE CARD
    // =====================================================

    private VBox createVehicleCard() {

        Vehicle vehicle = selectedVehicle();

        VBox vehicleCard = new VBox();

        vehicleCard.setStyle(
                "-fx-background-color: #eef3f8;" +
                "-fx-background-radius: 22;" +
                "-fx-border-color: #d7e0e8;" +
                "-fx-border-radius: 22;"
        );

        HBox.setHgrow(
                vehicleCard,
                Priority.ALWAYS
        );

        // Image

        Image image = new Image(
                getClass()
                        .getResource("")
                        .toExternalForm()
        );

        ImageView carImage = new ImageView(
                image
        );

        carImage.setFitWidth(600);
        carImage.setFitHeight(300);
        carImage.setPreserveRatio(false);

        VBox imageBox = new VBox(
                carImage
        );

        imageBox.setPrefHeight(205);

        imageBox.setAlignment(
                Pos.CENTER
        );

        imageBox.setStyle(
                "-fx-background-color: #e5e5e5;" +
                "-fx-background-radius: 20 20 0 0;"
        );

        vehicleCard.getChildren().add(
                imageBox
        );

        // Vehicle information

        VBox vehicleInfo = new VBox(13);

        vehicleInfo.setPadding(
                new Insets(18)
        );

        HBox vehicleNameRow = new HBox();

        vehicleNameRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox vehicleNameBox = new VBox(2);

        Label vehicleName = new Label(
                vehicle.name + " " + vehicle.model
        );

        vehicleName.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label vehicleDetails = new Label(
                vehicle.fuelType + " · " + vehicle.year + " · "
                        + String.format("%,d", vehicle.mileage) + " km"
        );

        vehicleDetails.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #737d8d;"
        );

        vehicleNameBox.getChildren().addAll(
                vehicleName,
                vehicleDetails
        );

        HBox.setHgrow(
                vehicleNameBox,
                Priority.ALWAYS
        );

        Label health = new Label(
                "Health 86 / 100"
        );

        health.setPadding(
                new Insets(5, 11, 5, 11)
        );

        health.setStyle(
                "-fx-background-color: #d7f3eb;" +
                "-fx-background-radius: 15;" +
                "-fx-text-fill: #0c9d77;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        vehicleNameRow.getChildren().addAll(
                vehicleNameBox,
                health
        );

        // Documents

        HBox documentCards = new HBox(8);

        documentCards.getChildren().addAll(
                smallInfoCard(
                        "▱",
                        "RC",
                        "Valid till 2031",
                        "#00a987"
                ),

                smallInfoCard(
                        "♧",
                        "INSURANCE",
                        "Expires in 15 days",
                        "#f04444"
                ),

                smallInfoCard(
                        "♨",
                        "PUC",
                        "Valid till Dec 2026",
                        "#00a987"
                ),

                smallInfoCard(
                        "⚒",
                        "WARRANTY",
                        "2 yrs remaining",
                        "#2869e8"
                )
        );

        vehicleInfo.getChildren().addAll(
                vehicleNameRow,
                documentCards
        );

        vehicleCard.getChildren().add(
                vehicleInfo
        );

        return vehicleCard;
    }

    // =====================================================
    // RIGHT CARDS
    // =====================================================

    private VBox createRightCards() {

        VBox rightCards = new VBox(18);

        rightCards.setPrefWidth(350);

        rightCards.getChildren().addAll(
                createBatteryCard(),
                createTyreCard()
        );

        return rightCards;
    }

    // =====================================================
    // BATTERY CARD
    // =====================================================

    private VBox createBatteryCard() {

        VBox batteryCard = new VBox(9);

        batteryCard.setPadding(
                new Insets(20)
        );

        batteryCard.setPrefHeight(140);

        batteryCard.setStyle(
                cardStyle()
        );

        HBox title = new HBox(8);

        title.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon = new Label(
                "♧"
        );

        icon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-text-fill: #2869e8;"
        );

        Label text = new Label(
                "Battery"
        );

        text.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        title.getChildren().addAll(
                icon,
                text
        );

        Label percentage = new Label(
                "42%"
        );

        percentage.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label details = new Label(
                "Exide 12V 45Ah · installed Mar 2023"
        );

        details.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #737d8d;"
        );

        HBox progress = new HBox();

        progress.setPrefWidth(190);
        progress.setPrefHeight(7);

        progress.setStyle(
                "-fx-background-color: #f04444;" +
                "-fx-background-radius: 10;"
        );

        batteryCard.getChildren().addAll(
                title,
                percentage,
                details,
                progress
        );

        return batteryCard;
    }

    // =====================================================
    // TYRE CARD
    // =====================================================

    private VBox createTyreCard() {

        VBox tyreCard = new VBox(14);

        tyreCard.setPadding(
                new Insets(20)
        );

        tyreCard.setPrefHeight(200);

        tyreCard.setStyle(
                cardStyle()
        );

        HBox title = new HBox(8);

        title.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon = new Label(
                "◉"
        );

        icon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-text-fill: #ff7200;"
        );

        Label text = new Label(
                "Tyres"
        );

        text.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        title.getChildren().addAll(
                icon,
                text
        );

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(
                tyreBox("Front left", "32 PSI"),
                0,
                0
        );

        grid.add(
                tyreBox("Front right", "31 PSI"),
                1,
                0
        );

        grid.add(
                tyreBox("Rear left", "30 PSI"),
                0,
                1
        );

        grid.add(
                tyreBox("Rear right", "28 PSI"),
                1,
                1
        );

        tyreCard.getChildren().addAll(
                title,
                grid
        );

        return tyreCard;
    }

    // =====================================================
    // LOWER SECTION
    // =====================================================

    private HBox createLowerSection() {

        HBox lowerSection = new HBox(18);

        VBox maintenanceCard = createMaintenanceCard();

        VBox serviceCard = createServiceCard();

        lowerSection.getChildren().addAll(
                maintenanceCard,
                serviceCard
        );

        return lowerSection;
    }

    // =====================================================
    // MAINTENANCE CARD
    // =====================================================

    private VBox createMaintenanceCard() {

        VBox card = new VBox(15);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        Label title = new Label(
                "Maintenance reminders"
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
                reminder(
                        "Insurance expires in 15 days",
                        "Renew before 19 Aug 2026 to avoid a lapse.",
                        "#fde7e9",
                        "#f04444"
                )
        );

        card.getChildren().add(
                reminder(
                        "Oil change due",
                        "Recommended within the next 480 km.",
                        "#f8f1dc",
                        "#e8a400"
                )
        );

        card.getChildren().add(
                reminder(
                        "Brake check due",
                        "Front pads at 34% — inspect this month.",
                        "#f9e7df",
                        "#f06a27"
                )
        );

        return card;
    }

    // =====================================================
    // SERVICE CARD
    // =====================================================

    private VBox createServiceCard() {

        VBox card = new VBox(13);

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                cardStyle()
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        Label title = new Label(
                "Service history timeline"
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
                serviceItem(
                        "Full periodic service",
                        "12 Jul 2026 · SpeedFix Auto Care · ₹6,480"
                )
        );

        card.getChildren().add(
                serviceItem(
                        "Front brake pad replacement",
                        "28 Apr 2026 · Highway Motors · ₹4,150"
                )
        );

        card.getChildren().add(
                serviceItem(
                        "Battery jump start (roadside)",
                        "05 Feb 2026 · PitStop 24x7 · ₹700"
                )
        );

        card.getChildren().add(
                serviceItem(
                        "AC gas refill & cabin filter",
                        "19 Nov 2025 · SpeedFix Auto Care · ₹3,260"
                )
        );

        card.getChildren().add(
                serviceItem(
                        "Tyre rotation & alignment",
                        "02 Sep 2025 · TyreZone · ₹1,900"
                )
        );

        return card;
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

    // =====================================================
    // SMALL INFORMATION CARD
    // =====================================================

    private VBox smallInfoCard(
            String icon,
            String title,
            String value,
            String iconColor) {

        VBox card = new VBox(4);

        card.setPadding(
                new Insets(10)
        );

        card.setPrefHeight(75);

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #dfe5ec;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;"
        );

        Label iconLabel = new Label(
                icon
        );

        iconLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: " +
                iconColor +
                ";"
        );

        Label titleLabel = new Label(
                title
        );

        titleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #737d8d;"
        );

        Label valueLabel = new Label(
                value
        );

        valueLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #263142;"
        );

        card.getChildren().addAll(
                iconLabel,
                titleLabel,
                valueLabel
        );

        return card;
    }

    // =====================================================
    // TYRE BOX
    // =====================================================

    private VBox tyreBox(
            String position,
            String psi) {

        VBox box = new VBox(2);

        box.setPrefWidth(145);
        box.setPrefHeight(55);

        box.setPadding(
                new Insets(9)
        );

        box.setStyle(
                "-fx-background-color: #f3f6f9;" +
                "-fx-background-radius: 18;"
        );

        Label positionLabel = new Label(
                position
        );

        positionLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #737d8d;"
        );

        Label psiLabel = new Label(
                psi
        );

        psiLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #263142;"
        );

        box.getChildren().addAll(
                positionLabel,
                psiLabel
        );

        return box;
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

    // =====================================================
    // REMINDER
    // =====================================================

    private VBox reminder(
            String title,
            String description,
            String background,
            String textColor) {

        VBox box = new VBox(3);

        box.setPadding(
                new Insets(12, 15, 12, 15)
        );

        box.setStyle(
                "-fx-background-color: " +
                background +
                ";" +
                "-fx-background-radius: 20;"
        );

        Label titleLabel = new Label(
                title
        );

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                textColor +
                ";"
        );

        Label descriptionLabel = new Label(
                description
        );

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " +
                textColor +
                ";"
        );

        box.getChildren().addAll(
                titleLabel,
                descriptionLabel
        );

        return box;
    }

    // =====================================================
    // SERVICE ITEM
    // =====================================================

    private HBox serviceItem(
            String title,
            String details) {

        Label icon = new Label(
                "●"
        );

        icon.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #2874f0;"
        );

        HBox iconBox = new HBox(
                icon
        );

        iconBox.setAlignment(
                Pos.CENTER
        );

        iconBox.setPrefWidth(28);

        VBox textBox = new VBox(2);

        Label titleLabel = new Label(
                title
        );

        titleLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #263142;"
        );

        Label detailLabel = new Label(
                details
        );

        detailLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #737d8d;"
        );

        textBox.getChildren().addAll(
                titleLabel,
                detailLabel
        );

        HBox item = new HBox(
                10,
                iconBox,
                textBox
        );

        item.setAlignment(
                Pos.CENTER_LEFT
        );

        return item;
    }

    private static class Vehicle {
        private final String name;
        private final String model;
        private final String registrationNumber;
        private final String fuelType;
        private final String transmission;
        private final int year;
        private final int mileage;
        private final String colour;
        private final String vin;
        private final String owner;
        private final String insurancePolicyNumber;
        private final String insuranceExpiry;
        private final String lastServiceDate;

        private Vehicle(String name, String model, String registrationNumber, String fuelType,
                        String transmission, int year, int mileage, String colour, String vin,
                        String owner, String insurancePolicyNumber, String insuranceExpiry,
                        String lastServiceDate) {
            this.name = name;
            this.model = model;
            this.registrationNumber = registrationNumber;
            this.fuelType = fuelType;
            this.transmission = transmission;
            this.year = year;
            this.mileage = mileage;
            this.colour = colour;
            this.vin = vin;
            this.owner = owner;
            this.insurancePolicyNumber = insurancePolicyNumber;
            this.insuranceExpiry = insuranceExpiry;
            this.lastServiceDate = lastServiceDate;
        }
    }

}

package project.ui.user.Garage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import project.ui.user.DashBoard.UserSectionPage;

import java.util.ArrayList;
import java.util.List;

public class VehiclePage extends UserSectionPage {

    // =====================================================
    // COLORS
    // =====================================================

    private static final String MAIN_BACKGROUND = "#D6F0F3";
    private static final String HEADING = "#172033";
    private static final String SECONDARY_TEXT = "#737d8d";
    private static final String NAV_BLUE = "#2563EB";

    // =====================================================
    // VEHICLES
    // =====================================================

    private final List<Vehicle> vehicles = new ArrayList<>(
            List.of(
                    new Vehicle(
                            "BMW",
                            "X1",
                            "MH 12 QR 4510",
                            "Petrol",
                            "Automatic",
                            2022,
                            48_210,
                            "White",
                            "",
                            "",
                            "",
                            "",
                            ""
                    )
            )
    );

    private int selectedVehicleIndex = 0;

    // =====================================================
    // GET VIEW
    // =====================================================

    @Override
    public VBox getView() {

        VBox content = createContent();

        ScrollPane scrollPane = new ScrollPane(
                content
        );

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);

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

        VBox root = new VBox();

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
    // SELECTED VEHICLE
    // =====================================================

    private Vehicle selectedVehicle() {

        return vehicles.get(
                selectedVehicleIndex
        );
    }

    // =====================================================
    // ADD VEHICLE DIALOG
    // =====================================================

    private void showAddVehicleDialog() {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Add Vehicle"
        );

        dialog.setHeaderText(
                "Enter your vehicle details"
        );

        // -------------------------------------------------
        // FIELDS
        // -------------------------------------------------

        TextField makeField =
                new TextField();

        makeField.setPromptText(
                "e.g. Honda"
        );

        TextField modelField =
                new TextField();

        modelField.setPromptText(
                "e.g. City ZX"
        );

        TextField registrationField =
                new TextField();

        registrationField.setPromptText(
                "e.g. MH 12 AB 1234"
        );

        TextField vinField =
                new TextField();

        vinField.setPromptText(
                "VIN number"
        );

        ComboBox<String> fuelTypeBox =
                new ComboBox<>();

        fuelTypeBox.getItems().addAll(
                "Petrol",
                "Diesel",
                "Electric",
                "CNG",
                "Hybrid"
        );

        fuelTypeBox.setValue(
                "Petrol"
        );

        fuelTypeBox.setMaxWidth(
                Double.MAX_VALUE
        );

        ComboBox<String> transmissionBox =
                new ComboBox<>();

        transmissionBox.getItems().addAll(
                "Manual",
                "Automatic",
                "AMT",
                "CVT"
        );

        transmissionBox.setValue(
                "Manual"
        );

        transmissionBox.setMaxWidth(
                Double.MAX_VALUE
        );

        TextField yearField =
                new TextField();

        yearField.setPromptText(
                "e.g. 2024"
        );

        TextField kmField =
                new TextField();

        kmField.setPromptText(
                "e.g. 12000"
        );

        TextField colourField =
                new TextField();

        colourField.setPromptText(
                "e.g. Pearl White"
        );

        TextField ownerField =
                new TextField();

        ownerField.setPromptText(
                "Registered owner name"
        );

        TextField insuranceField =
                new TextField();

        insuranceField.setPromptText(
                "Policy number"
        );

        TextField insuranceExpiryField =
                new TextField();

        insuranceExpiryField.setPromptText(
                "e.g. 31 Dec 2026"
        );

        TextField lastServiceField =
                new TextField();

        lastServiceField.setPromptText(
                "e.g. 15 Jul 2026"
        );

        // -------------------------------------------------
        // FORM
        // -------------------------------------------------

        GridPane form =
                new GridPane();

        form.setHgap(18);
        form.setVgap(12);

        form.setPadding(
                new Insets(
                        15,
                        5,
                        5,
                        5
                )
        );

        form.addRow(
                0,
                new Label("Make *"),
                makeField,
                new Label("Model *"),
                modelField
        );

        form.addRow(
                1,
                new Label("Registration number *"),
                registrationField,
                new Label("VIN number"),
                vinField
        );

        form.addRow(
                2,
                new Label("Fuel type *"),
                fuelTypeBox,
                new Label("Transmission"),
                transmissionBox
        );

        form.addRow(
                3,
                new Label("Manufacturing year *"),
                yearField,
                new Label("Current km *"),
                kmField
        );

        form.addRow(
                4,
                new Label("Colour"),
                colourField,
                new Label("Registered owner"),
                ownerField
        );

        form.addRow(
                5,
                new Label("Insurance policy number"),
                insuranceField,
                new Label("Insurance expiry"),
                insuranceExpiryField
        );

        form.addRow(
                6,
                new Label("Last service date"),
                lastServiceField
        );

        dialog.getDialogPane().setContent(
                form
        );

        dialog.getDialogPane().setPrefWidth(
                820
        );

        dialog.getDialogPane().setPrefHeight(
                520
        );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        ButtonType.CANCEL,
                        ButtonType.OK
                );

        // -------------------------------------------------
        // SAVE
        // -------------------------------------------------

        dialog.showAndWait()
                .filter(
                        ButtonType.OK::equals
                )
                .ifPresent(
                        result -> {

                            try {

                                String make =
                                        makeField
                                                .getText()
                                                .trim();

                                String model =
                                        modelField
                                                .getText()
                                                .trim();

                                String registrationNumber =
                                        registrationField
                                                .getText()
                                                .trim()
                                                .toUpperCase();

                                int year =
                                        Integer.parseInt(
                                                yearField
                                                        .getText()
                                                        .trim()
                                        );

                                int km =
                                        Integer.parseInt(
                                                kmField
                                                        .getText()
                                                        .trim()
                                        );

                                // -----------------------------------------
                                // VALIDATION
                                // -----------------------------------------

                                if (
                                        make.isEmpty()
                                                ||
                                                model.isEmpty()
                                                ||
                                                registrationNumber.isEmpty()
                                                ||
                                                year < 1900
                                                ||
                                                year > 2100
                                                ||
                                                km < 0
                                ) {

                                    throw new IllegalArgumentException();
                                }

                                // -----------------------------------------
                                // ADD VEHICLE
                                // -----------------------------------------

                                vehicles.add(
                                        new Vehicle(
                                                make,
                                                model,
                                                registrationNumber,
                                                fuelTypeBox.getValue(),
                                                transmissionBox.getValue(),
                                                year,
                                                km,
                                                colourField
                                                        .getText()
                                                        .trim(),
                                                vinField
                                                        .getText()
                                                        .trim(),
                                                ownerField
                                                        .getText()
                                                        .trim(),
                                                insuranceField
                                                        .getText()
                                                        .trim(),
                                                insuranceExpiryField
                                                        .getText()
                                                        .trim(),
                                                lastServiceField
                                                        .getText()
                                                        .trim()
                                        )
                                );

                                selectedVehicleIndex =
                                        vehicles.size() - 1;

                            } catch (
                                    IllegalArgumentException exception
                            ) {

                                Alert error =
                                        new Alert(
                                                Alert.AlertType.ERROR
                                        );

                                error.setTitle(
                                        "Invalid vehicle details"
                                );

                                error.setHeaderText(
                                        null
                                );

                                error.setContentText(
                                        "Please enter make, model, " +
                                                "registration number, " +
                                                "valid year, and " +
                                                "non-negative km."
                                );

                                error.showAndWait();
                            }
                        }
                );
    }

    // =====================================================
    // MAIN CONTENT
    // =====================================================

    private VBox createContent() {

        VBox content =
                new VBox(20);

        content.setPadding(
                new Insets(
                        25,
                        30,
                        30,
                        30
                )
        );

        content.setFillWidth(true);

        content.setStyle(
                "-fx-background-color: " +
                        MAIN_BACKGROUND +
                        ";"
        );

        // -------------------------------------------------
        // HEADING
        // -------------------------------------------------

        HBox heading =
                createHeading();

        // -------------------------------------------------
        // UPPER SECTION
        // -------------------------------------------------

        HBox upperSection =
                createUpperSection();

        // -------------------------------------------------
        // LOWER SECTION
        // -------------------------------------------------

        HBox lowerSection =
                createLowerSection();

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

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox headingText =
                new VBox(2);

        Label title =
                new Label(
                        "My Vehicles"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        HEADING +
                        ";"
        );

        Label subtitle =
                new Label(
                        selectedVehicle().name +
                                " " +
                                selectedVehicle().model +
                                " · " +
                                selectedVehicle().registrationNumber
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: " +
                        SECONDARY_TEXT +
                        ";"
        );

        headingText.getChildren().addAll(
                title,
                subtitle
        );

        HBox.setHgrow(
                headingText,
                Priority.ALWAYS
        );

        Button addVehicle =
                new Button(
                        "Add vehicle"
                );

        addVehicle.setPrefHeight(
                38
        );

        addVehicle.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        addVehicle.setStyle(
                "-fx-background-color: " +
                        NAV_BLUE +
                        ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 25;" +
                        "-fx-cursor: hand;"
        );

        addVehicle.setOnAction(
                event ->
                        showAddVehicleDialog()
        );

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

        HBox upperSection =
                new HBox(18);

        VBox vehicleCard =
                createVehicleCard();

        VBox rightCards =
                createRightCards();

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

        Vehicle vehicle =
                selectedVehicle();

        VBox vehicleCard =
                new VBox();

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

        // -------------------------------------------------
        // IMAGE AREA
        // -------------------------------------------------

        VBox imageBox =
                new VBox();

        imageBox.setPrefHeight(
                205
        );

        imageBox.setMinHeight(
                205
        );

        imageBox.setAlignment(
                Pos.CENTER
        );

        imageBox.setStyle(
                "-fx-background-color: #e5e5e5;" +
                        "-fx-background-radius: 20 20 0 0;"
        );

        Label vehicleImageText =
                new Label(
                        vehicle.name +
                                " " +
                                vehicle.model
                );

        vehicleImageText.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #737d8d;"
        );

        imageBox.getChildren().add(
                vehicleImageText
        );

        vehicleCard.getChildren().add(
                imageBox
        );

        // -------------------------------------------------
        // VEHICLE INFORMATION
        // -------------------------------------------------

        VBox vehicleInfo =
                new VBox(13);

        vehicleInfo.setPadding(
                new Insets(18)
        );

        HBox vehicleNameRow =
                new HBox();

        vehicleNameRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox vehicleNameBox =
                new VBox(2);

        Label vehicleName =
                new Label(
                        vehicle.name +
                                " " +
                                vehicle.model
                );

        vehicleName.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        HEADING +
                        ";"
        );

        Label vehicleDetails =
                new Label(
                        vehicle.fuelType +
                                " · " +
                                vehicle.year +
                                " · " +
                                String.format(
                                        "%,d",
                                        vehicle.mileage
                                ) +
                                " km"
                );

        vehicleDetails.setStyle(
                "-fx-font-size: 12px;" +
                        "-fx-text-fill: " +
                        SECONDARY_TEXT +
                        ";"
        );

        vehicleNameBox.getChildren().addAll(
                vehicleName,
                vehicleDetails
        );

        HBox.setHgrow(
                vehicleNameBox,
                Priority.ALWAYS
        );

        Label health =
                new Label(
                        "Health 86 / 100"
                );

        health.setPadding(
                new Insets(
                        5,
                        11,
                        5,
                        11
                )
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

        // -------------------------------------------------
        // DOCUMENTS
        // -------------------------------------------------

        HBox documentCards =
                new HBox(8);

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
                        NAV_BLUE
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

        VBox rightCards =
                new VBox(18);

        rightCards.setPrefWidth(
                350
        );

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

        VBox batteryCard =
                new VBox(9);

        batteryCard.setPadding(
                new Insets(20)
        );

        batteryCard.setPrefHeight(
                140
        );

        batteryCard.setStyle(
                cardStyle()
        );

        HBox title =
                new HBox(8);

        title.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon =
                new Label("♧");

        icon.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-text-fill: " +
                        NAV_BLUE +
                        ";"
        );

        Label text =
                new Label(
                        "Battery"
                );

        text.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        HEADING +
                        ";"
        );

        title.getChildren().addAll(
                icon,
                text
        );

        Label percentage =
                new Label(
                        "42%"
                );

        percentage.setStyle(
                "-fx-font-size: 27px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        HEADING +
                        ";"
        );

        Label details =
                new Label(
                        "Exide 12V 45Ah · installed Mar 2023"
                );

        details.setStyle(
                "-fx-font-size: 11px;" +
                        "-fx-text-fill: " +
                        SECONDARY_TEXT +
                        ";"
        );

        HBox progress =
                new HBox();

        progress.setPrefWidth(
                190
        );

        progress.setPrefHeight(
                7
        );

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

        VBox tyreCard =
                new VBox(14);

        tyreCard.setPadding(
                new Insets(20)
        );

        tyreCard.setPrefHeight(
                200
        );

        tyreCard.setStyle(
                cardStyle()
        );

        HBox title =
                new HBox(8);

        title.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon =
                new Label("◉");

        icon.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-text-fill: #ff7200;"
        );

        Label text =
                new Label(
                        "Tyres"
                );

        text.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        HEADING +
                        ";"
        );

        title.getChildren().addAll(
                icon,
                text
        );

        GridPane grid =
                new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(
                tyreBox(
                        "Front left",
                        "32 PSI"
                ),
                0,
                0
        );

        grid.add(
                tyreBox(
                        "Front right",
                        "31 PSI"
                ),
                1,
                0
        );

        grid.add(
                tyreBox(
                        "Rear left",
                        "30 PSI"
                ),
                0,
                1
        );

        grid.add(
                tyreBox(
                        "Rear right",
                        "28 PSI"
                ),
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

        HBox lowerSection =
                new HBox(18);

        VBox maintenanceCard =
                createMaintenanceCard();

        VBox serviceCard =
                createServiceCard();

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

        VBox card =
                new VBox(15);

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

        Label title =
                new Label(
                        "Maintenance reminders"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        HEADING +
                        ";"
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

        VBox card =
                new VBox(13);

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

        Label title =
                new Label(
                        "Service history timeline"
                );

        title.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        HEADING +
                        ";"
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
    // SMALL INFORMATION CARD
    // =====================================================

    private VBox smallInfoCard(
            String icon,
            String title,
            String value,
            String iconColor
    ) {

        VBox card =
                new VBox(4);

        card.setPadding(
                new Insets(10)
        );

        card.setPrefHeight(
                75
        );

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

        Label iconLabel =
                new Label(
                        icon
                );

        iconLabel.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-text-fill: " +
                        iconColor +
                        ";"
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 10px;" +
                        "-fx-text-fill: " +
                        SECONDARY_TEXT +
                        ";"
        );

        Label valueLabel =
                new Label(
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
            String psi
    ) {

        VBox box =
                new VBox(2);

        box.setPrefWidth(
                145
        );

        box.setPrefHeight(
                55
        );

        box.setPadding(
                new Insets(9)
        );

        box.setStyle(
                "-fx-background-color: #f3f6f9;" +
                        "-fx-background-radius: 18;"
        );

        Label positionLabel =
                new Label(
                        position
                );

        positionLabel.setStyle(
                "-fx-font-size: 10px;" +
                        "-fx-text-fill: " +
                        SECONDARY_TEXT +
                        ";"
        );

        Label psiLabel =
                new Label(
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
            String textColor
    ) {

        VBox box =
                new VBox(3);

        box.setPadding(
                new Insets(
                        12,
                        15,
                        12,
                        15
                )
        );

        box.setStyle(
                "-fx-background-color: " +
                        background +
                        ";" +
                        "-fx-background-radius: 20;"
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " +
                        textColor +
                        ";"
        );

        Label descriptionLabel =
                new Label(
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
            String details
    ) {

        Label icon =
                new Label(
                        "●"
                );

        icon.setStyle(
                "-fx-font-size: 8px;" +
                        "-fx-text-fill: #2874f0;"
        );

        HBox iconBox =
                new HBox(
                        icon
                );

        iconBox.setAlignment(
                Pos.CENTER
        );

        iconBox.setPrefWidth(
                28
        );

        VBox textBox =
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

        Label detailLabel =
                new Label(
                        details
                );

        detailLabel.setStyle(
                "-fx-font-size: 10px;" +
                        "-fx-text-fill: " +
                        SECONDARY_TEXT +
                        ";"
        );

        textBox.getChildren().addAll(
                titleLabel,
                detailLabel
        );

        HBox item =
                new HBox(
                        10,
                        iconBox,
                        textBox
                );

        item.setAlignment(
                Pos.CENTER_LEFT
        );

        return item;
    }

    // =====================================================
    // VEHICLE MODEL
    // =====================================================

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

        private Vehicle(
                String name,
                String model,
                String registrationNumber,
                String fuelType,
                String transmission,
                int year,
                int mileage,
                String colour,
                String vin,
                String owner,
                String insurancePolicyNumber,
                String insuranceExpiry,
                String lastServiceDate
        ) {

            this.name =
                    name;

            this.model =
                    model;

            this.registrationNumber =
                    registrationNumber;

            this.fuelType =
                    fuelType;

            this.transmission =
                    transmission;

            this.year =
                    year;

            this.mileage =
                    mileage;

            this.colour =
                    colour;

            this.vin =
                    vin;

            this.owner =
                    owner;

            this.insurancePolicyNumber =
                    insurancePolicyNumber;

            this.insuranceExpiry =
                    insuranceExpiry;

            this.lastServiceDate =
                    lastServiceDate;
        }
    }
}
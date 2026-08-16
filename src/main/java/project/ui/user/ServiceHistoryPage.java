package project.ui.user;

import project.app.AppNavigator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServiceHistoryPage extends Application {

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

        stage.setTitle(
                "RoadGuardian - Service History"
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

        Label searchText = new Label(
                "Search mechanics, invoices, vehicles..."
        );

        searchText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #737d8d;"
        );

        searchBox.getChildren().addAll(
                searchIcon,
                searchText
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

        // ACTIVE SERVICE HISTORY

        sidebar.getChildren().add(
                activeSideButton(
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

        // Summary

        HBox summaryCards = createSummaryCards();

        // Invoice

        VBox invoiceCard = createInvoiceCard();

        content.getChildren().addAll(
                heading,
                summaryCards,
                invoiceCard
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
                "Service History"
        );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label subtitle = new Label(
                "All repairs, assists and invoices for MH 12 QR 4410"
        );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #737d8d;"
        );

        headingText.getChildren().addAll(
                title,
                subtitle
        );

        heading.getChildren().add(
                headingText
        );

        return heading;
    }

    // =====================================================
    // SUMMARY CARDS
    // =====================================================

    private HBox createSummaryCards() {

        HBox summaryCards = new HBox(18);

        VBox totalServices = createSummaryCard(
                "⚒",
                "TOTAL SERVICES",
                "18",
                "Since Mar 2022",
                "#2869e8",
                "#e2ecff"
        );

        VBox lifetimeSpend = createSummaryCard(
                "▣",
                "LIFETIME SPEND",
                "₹64,280",
                "",
                "#f06a27",
                "#fceddf"
        );

        VBox averageDowntime = createSummaryCard(
                "◷",
                "AVG. DOWNTIME",
                "2.4 hrs",
                "",
                "#00a987",
                "#def5ee"
        );

        summaryCards.getChildren().addAll(
                totalServices,
                lifetimeSpend,
                averageDowntime
        );

        return summaryCards;
    }

    // =====================================================
    // SUMMARY CARD
    // =====================================================

    private VBox createSummaryCard(
            String icon,
            String heading,
            String value,
            String bottomText,
            String iconColor,
            String iconBackground) {

        VBox card = new VBox(5);

        card.setPrefHeight(125);

        card.setPadding(
                new Insets(20)
        );

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        card.setStyle(
                "-fx-background-color: #eef3f8;" +
                "-fx-border-color: #d7e0e8;" +
                "-fx-border-radius: 22;" +
                "-fx-background-radius: 22;"
        );

        HBox top = new HBox();

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Label headingLabel = new Label(
                heading
        );

        headingLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #687482;"
        );

        Region space = new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Label iconLabel = new Label(
                icon
        );

        iconLabel.setPrefWidth(42);
        iconLabel.setPrefHeight(42);

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setStyle(
                "-fx-background-color: " +
                iconBackground +
                ";" +
                "-fx-background-radius: 30;" +
                "-fx-text-fill: " +
                iconColor +
                ";" +
                "-fx-font-size: 18px;"
        );

        top.getChildren().addAll(
                headingLabel,
                space,
                iconLabel
        );

        Label valueLabel = new Label(
                value
        );

        valueLabel.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        card.getChildren().addAll(
                top,
                valueLabel
        );

        if (!bottomText.equals("")) {

            Label bottom = new Label(
                    bottomText
            );

            bottom.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: #737d8d;"
            );

            card.getChildren().add(
                    bottom
            );
        }

        return card;
    }

    // =====================================================
    // INVOICE CARD
    // =====================================================

    private VBox createInvoiceCard() {

        VBox card = new VBox();

        card.setStyle(
                "-fx-background-color: #eef3f8;" +
                "-fx-border-color: #d7e0e8;" +
                "-fx-border-radius: 22;" +
                "-fx-background-radius: 22;"
        );

        // Invoice heading

        HBox invoiceHeading = new HBox();

        invoiceHeading.setPrefHeight(82);

        invoiceHeading.setPadding(
                new Insets(0, 22, 0, 22)
        );

        invoiceHeading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title = new Label(
                "Invoices"
        );

        title.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Region space = new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button exportButton = new Button(
                "⇩   Export all"
        );

        exportButton.setPrefHeight(40);

        exportButton.setPadding(
                new Insets(0, 17, 0, 17)
        );

        exportButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #d5dee6;" +
                "-fx-border-radius: 22;" +
                "-fx-background-radius: 22;" +
                "-fx-text-fill: #172033;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        invoiceHeading.getChildren().addAll(
                title,
                space,
                exportButton
        );

        card.getChildren().add(
                invoiceHeading
        );

        // Table heading

        card.getChildren().add(
                createTableHeading()
        );

        // Invoice rows

        card.getChildren().add(
                invoiceRow(
                        "12 Jul 2026",
                        "Full periodic service",
                        "SpeedFix Auto Care",
                        "₹6,480"
                )
        );

        card.getChildren().add(
                invoiceRow(
                        "28 Apr 2026",
                        "Front brake pad replacement",
                        "Highway Motors",
                        "₹4,150"
                )
        );

        card.getChildren().add(
                invoiceRow(
                        "05 Feb 2026",
                        "Battery jump start (roadside)",
                        "PitStop 24x7",
                        "₹700"
                )
        );

        card.getChildren().add(
                invoiceRow(
                        "19 Nov 2025",
                        "AC gas refill & cabin filter",
                        "SpeedFix Auto Care",
                        "₹3,260"
                )
        );

        card.getChildren().add(
                invoiceRow(
                        "02 Sep 2025",
                        "Tyre rotation & alignment",
                        "TyreZone",
                        "₹1,900"
                )
        );

        return card;
    }

    // =====================================================
    // TABLE HEADING
    // =====================================================

    private HBox createTableHeading() {

        HBox row = new HBox();

        row.setPrefHeight(45);

        row.setPadding(
                new Insets(0, 20, 0, 20)
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setStyle(
                "-fx-background-color: #e5edf2;" +
                "-fx-border-color: #d7e0e8;" +
                "-fx-border-width: 1 0 1 0;"
        );

        Label date = tableLabel(
                "DATE",
                200
        );

        Label service = tableLabel(
                "SERVICE",
                378
        );

        Label garage = tableLabel(
                "GARAGE",
                278
        );

        Label amount = tableLabel(
                "AMOUNT",
                158
        );

        Label status = tableLabel(
                "STATUS",
                200
        );

        Label invoice = tableLabel(
                "INVOICE",
                100
        );

        row.getChildren().addAll(
                date,
                service,
                garage,
                amount,
                status,
                invoice
        );

        return row;
    }

    // =====================================================
    // INVOICE ROW
    // =====================================================

    private HBox invoiceRow(
            String dateText,
            String serviceText,
            String garageText,
            String amountText) {

        HBox row = new HBox();

        row.setPrefHeight(70);

        row.setPadding(
                new Insets(0, 20, 0, 20)
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setStyle(
                "-fx-border-color: #d7e0e8;" +
                "-fx-border-width: 0 0 1 0;"
        );

        Label date = normalLabel(
                dateText,
                200
        );

        Label service = normalLabel(
                serviceText,
                378
        );

        service.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label garage = normalLabel(
                garageText,
                278
        );

        Label amount = normalLabel(
                amountText,
                158
        );

        amount.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label completed = new Label(
                "Completed"
        );

        completed.setPadding(
                new Insets(5, 12, 5, 12)
        );

        completed.setStyle(
                "-fx-background-color: #d2f1e9;" +
                "-fx-background-radius: 15;" +
                "-fx-text-fill: #00a987;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        HBox statusBox = new HBox(
                completed
        );

        statusBox.setPrefWidth(200);

        statusBox.setAlignment(
                Pos.CENTER_LEFT
        );

        Label pdfIcon = new Label(
                "▣"
        );

        pdfIcon.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #172033;"
        );

        Label pdfText = new Label(
                "PDF"
        );

        pdfText.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #172033;"
        );

        HBox pdf = new HBox(
                10,
                pdfIcon,
                pdfText
        );

        pdf.setPrefWidth(100);

        pdf.setAlignment(
                Pos.CENTER_LEFT
        );

        row.getChildren().addAll(
                date,
                service,
                garage,
                amount,
                statusBox,
                pdf
        );

        return row;
    }

    // =====================================================
    // TABLE LABEL
    // =====================================================

    private Label tableLabel(
            String text,
            double width) {

        Label label = new Label(
                text
        );

        label.setPrefWidth(
                width
        );

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65717d;"
        );

        return label;
    }

    // =====================================================
    // NORMAL LABEL
    // =====================================================

    private Label normalLabel(
            String text,
            double width) {

        Label label = new Label(
                text
        );

        label.setPrefWidth(
                width
        );

        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #687482;"
        );

        return label;
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

        // Same active color as VehiclePage

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

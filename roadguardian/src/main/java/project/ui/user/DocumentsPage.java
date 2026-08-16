package project.ui.user;

import project.app.AppNavigator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class DocumentsPage extends Application {

    private static final String[] REQUIRED_DOCUMENTS = {
            "Registration Certificate",
            "Motor Insurance Policy",
            "Pollution Under Control Certificate",
            "Extended Warranty Certificate",
            "Driving Licence"
    };
    private final String[] uploadedFileNames = new String[REQUIRED_DOCUMENTS.length];
    private int uploadStep = 0;
    private String lastUploadStatus = "";

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
        content.setStyle("-fx-background-color: #F4F4F4;");

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
                "RoadGuardian - Documents"
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
        activeSideButton(
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

        // Upload Box

        VBox uploadBox = createUploadBox();

        // Documents

        GridPane documentsGrid = createDocumentsGrid();

        content.getChildren().addAll(
                heading,
                uploadBox,
                documentsGrid
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
                "Documents"
        );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label subtitle = new Label(
                "Encrypted vault · shared automatically with responders during an SOS"
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
    // UPLOAD BOX
    // =====================================================

    private VBox createUploadBox() {

        boolean allDocumentsUploaded = uploadStep >= REQUIRED_DOCUMENTS.length;

        VBox uploadBox = new VBox(12);

        uploadBox.setPrefHeight(280);

        uploadBox.setAlignment(
                Pos.CENTER
        );

        uploadBox.setPadding(
                new Insets(25)
        );

        uploadBox.setStyle(
                "-fx-background-color: #eef3f8;" +
                "-fx-border-color: #d7e0e8;" +
                "-fx-border-radius: 22;" +
                "-fx-background-radius: 22;"
        );

        // Upload Icon

        Label uploadIcon = new Label(
                "⇧"
        );

        uploadIcon.setPrefWidth(60);
        uploadIcon.setPrefHeight(60);

        uploadIcon.setAlignment(
                Pos.CENTER
        );

        uploadIcon.setStyle(
                "-fx-background-color: #dfe9ff;" +
                "-fx-background-radius: 40;" +
                "-fx-text-fill: #2869e8;" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;"
        );

        // Main Text

        Label uploadTitle = new Label(
                allDocumentsUploaded
                        ? "All required documents uploaded"
                        : "Step " + (uploadStep + 1) + " of " + REQUIRED_DOCUMENTS.length
                                + ": Upload " + REQUIRED_DOCUMENTS[uploadStep]
        );

        uploadTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        // Description

        Label uploadDescription = new Label(
                allDocumentsUploaded
                        ? "Your document vault is complete."
                        : "Upload your " + REQUIRED_DOCUMENTS[uploadStep]
                                + " (PDF, JPG or PNG up to 10 MB)."
        );

        uploadDescription.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #737d8d;"
        );

        // Browse Button

        Button browseButton = new Button(
                allDocumentsUploaded ? "Upload complete" : "Browse files"
        );

        browseButton.setPrefHeight(40);

        browseButton.setPadding(
                new Insets(0, 20, 0, 20)
        );

        browseButton.setStyle(
                "-fx-background-color: #2869e8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 25;"
        );
        browseButton.setDisable(allDocumentsUploaded);

        Label uploadStatus = new Label();
        uploadStatus.setText(lastUploadStatus);
        uploadStatus.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #00a987;"
        );

        browseButton.setOnAction(
                event -> {

                    FileChooser fileChooser =
                            new FileChooser();

                    fileChooser.setTitle("Upload " + REQUIRED_DOCUMENTS[uploadStep]);
                    fileChooser.getExtensionFilters().add(
                            new FileChooser.ExtensionFilter("Documents (PDF, JPG, PNG)", "*.pdf", "*.jpg", "*.jpeg", "*.png")
                    );

                    File file =
                            fileChooser.showOpenDialog(
                                    browseButton.getScene().getWindow()
                            );

                    if (file != null) {
                        uploadedFileNames[uploadStep] = file.getName();
                        lastUploadStatus = "File uploaded successfully: " + file.getName();
                        uploadStep++;
                        start((Stage) browseButton.getScene().getWindow());
                    }
                }
        );

        uploadBox.getChildren().addAll(
                uploadIcon,
                uploadTitle,
                uploadDescription,
                browseButton,
                uploadStatus
        );

        return uploadBox;
    }

    // =====================================================
    // DOCUMENT GRID
    // =====================================================

    private GridPane createDocumentsGrid() {

        GridPane grid = new GridPane();

        grid.setHgap(18);
        grid.setVgap(18);

        grid.add(
                documentCard(
                        "▤",
                        "Registration Certificate",
                        uploadedFileName(0),
                        "",
                        isUploaded(0)
                ),
                0,
                0
        );

        grid.add(
                documentCard(
                        "▤",
                        "Motor Insurance Policy",
                        uploadedFileName(1),
                        "",
                        isUploaded(1)
                ),
                1,
                0
        );

        grid.add(
                documentCard(
                        "▤",
                        "Pollution Under Control",
                        uploadedFileName(2),
                        "",
                        isUploaded(2)
                ),
                2,
                0
        );

        grid.add(
                documentCard(
                        "▤",
                        "Extended Warranty",
                        uploadedFileName(3),
                        "",
                        isUploaded(3)
                ),
                0,
                1
        );

        grid.add(
                documentCard(
                        "▤",
                        "Driving Licence",
                        uploadedFileName(4),
                        "",
                        isUploaded(4)
                ),
                1,
                1
        );

        return grid;
    }

    private String uploadedFileName(int documentIndex) {
        String fileName = uploadedFileNames[documentIndex];
        return fileName == null ? "Not uploaded yet" : fileName;
    }

    private boolean isUploaded(int documentIndex) {
        return uploadedFileNames[documentIndex] != null;
    }

    // =====================================================
    // DOCUMENT CARD
    // =====================================================

    private VBox documentCard(
            String icon,
            String title,
            String details,
            String validity,
            boolean verified) {

        VBox card = new VBox(7);

        card.setPrefWidth(430);
        card.setPrefHeight(155);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: #eef3f8;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;"
        );

        // Top Row

        HBox topRow = new HBox();

        topRow.setAlignment(
                Pos.CENTER_LEFT
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
                "-fx-background-color: #f3f6f9;" +
                "-fx-background-radius: 30;" +
                "-fx-text-fill: #65717d;" +
                "-fx-font-size: 18px;"
        );

        Region space = new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Label status = new Label();

        if (verified) {

            status.setText(
                    "✓ Uploaded"
            );

            status.setStyle(
                    "-fx-background-color: #d7f3eb;" +
                    "-fx-background-radius: 20;" +
                    "-fx-text-fill: #00a987;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;"
            );

        } else {

            status.setText(
                    "Pending upload"
            );

            status.setStyle(
                    "-fx-background-color: #fde1e1;" +
                    "-fx-background-radius: 20;" +
                    "-fx-text-fill: #f04444;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;"
            );
        }

        status.setPadding(
                new Insets(5, 11, 5, 11)
        );

        topRow.getChildren().addAll(
                iconLabel,
                space,
                status
        );

        // Title

        Label titleLabel = new Label(
                title
        );

        titleLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        // Details

        Label detailsLabel = new Label(
                details
        );

        detailsLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #737d8d;"
        );

        // Validity

        Label validityLabel = new Label(
                validity
        );

        validityLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #263142;"
        );

        card.getChildren().addAll(
                topRow,
                titleLabel,
                detailsLabel,
                validityLabel
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

}

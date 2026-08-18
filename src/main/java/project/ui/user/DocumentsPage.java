package project.ui.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import project.ui.user.UserHeader;

import java.io.File;

public class DocumentsPage {

    // ============================================================
    // DOCUMENT DATA
    // ============================================================

    private static final String[] REQUIRED_DOCUMENTS = {
            "Registration Certificate",
            "Motor Insurance Policy",
            "Pollution Under Control Certificate",
            "Extended Warranty Certificate",
            "Driving Licence"
    };

    private final String[] uploadedFileNames =
            new String[REQUIRED_DOCUMENTS.length];

    private int uploadStep = 0;

    private String lastUploadStatus = "";

    private Scene documentsScene;

    // ============================================================
    // PUBLIC SCENE
    // ============================================================

    public Scene getDocumentsScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #D6F0F3;"
        );

        // Common Header
        HBox header = UserHeader.createHeader();

        // Common Sidebar
        ScrollPane sidebar =
                UserSideBar.createSidebar("Documents");

        // Main Content
        VBox content = createContent();

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

        documentsScene = new Scene(root,UserDashboard.dashboardStage.getWidth(),UserDashboard.dashboardStage.getHeight());

        return documentsScene;
    }

    
    // ============================================================
    // MAIN CONTENT
    // ============================================================

    private VBox createContent() {

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(25, 30, 30, 30)
        );

        content.setStyle(
                "-fx-background-color: #D6F0F3;"
        );

        HBox heading =
                createHeading();

        VBox uploadBox =
                createUploadBox();

        GridPane documentsGrid =
                createDocumentsGrid();

        content.getChildren().addAll(
                heading,
                uploadBox,
                documentsGrid
        );

        return content;
    }

    // ============================================================
    // HEADING
    // ============================================================

    private HBox createHeading() {

        HBox heading = new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox headingText =
                new VBox(2);

        Label title =
                new Label("Documents");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label subtitle =
                new Label(
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

    // ============================================================
    // UPLOAD BOX
    // ============================================================

    private VBox createUploadBox() {

        boolean allDocumentsUploaded =
                uploadStep >= REQUIRED_DOCUMENTS.length;

        VBox uploadBox =
                new VBox(12);

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

        // Upload icon

        Label uploadIcon =
                new Label("⇧");

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

        // Title

        Label uploadTitle =
                new Label(
                        allDocumentsUploaded
                                ? "All required documents uploaded"
                                : "Step " +
                                  (uploadStep + 1) +
                                  " of " +
                                  REQUIRED_DOCUMENTS.length +
                                  ": Upload " +
                                  REQUIRED_DOCUMENTS[uploadStep]
                );

        uploadTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        // Description

        Label uploadDescription =
                new Label(
                        allDocumentsUploaded
                                ? "Your document vault is complete."
                                : "Upload your " +
                                  REQUIRED_DOCUMENTS[uploadStep] +
                                  " (PDF, JPG or PNG up to 10 MB)."
                );

        uploadDescription.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #737d8d;"
        );

        // Browse button

        Button browseButton =
                new Button(
                        allDocumentsUploaded
                                ? "Upload complete"
                                : "Browse files"
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

        browseButton.setDisable(
                allDocumentsUploaded
        );

        Label uploadStatus =
                new Label(lastUploadStatus);

        uploadStatus.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #00a987;"
        );

        browseButton.setOnAction(
                event -> uploadDocument(browseButton)
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

    // ============================================================
    // UPLOAD DOCUMENT
    // ============================================================

    private void uploadDocument(
            Button browseButton
    ) {

        if (uploadStep >= REQUIRED_DOCUMENTS.length) {
            return;
        }

        FileChooser fileChooser =
                new FileChooser();

        fileChooser.setTitle(
                "Upload " +
                REQUIRED_DOCUMENTS[uploadStep]
        );

        fileChooser.getExtensionFilters()
                .add(
                        new FileChooser.ExtensionFilter(
                                "Documents (PDF, JPG, PNG)",
                                "*.pdf",
                                "*.jpg",
                                "*.jpeg",
                                "*.png"
                        )
                );

        File file =
                fileChooser.showOpenDialog(
                        browseButton
                                .getScene()
                                .getWindow()
                );

        if (file != null) {

            uploadedFileNames[uploadStep] =
                    file.getName();

            lastUploadStatus =
                    "File uploaded successfully: " +
                    file.getName();

            uploadStep++;

            refreshScene();
        }
    }

    // ============================================================
    // DOCUMENT GRID
    // ============================================================

    private GridPane createDocumentsGrid() {

        GridPane grid =
                new GridPane();

        grid.setHgap(18);
        grid.setVgap(18);

        grid.add(
                documentCard(
                        "▤",
                        "Registration Certificate",
                        uploadedFileName(0),
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
                        isUploaded(4)
                ),
                1,
                1
        );

        return grid;
    }

    // ============================================================
    // FILE NAME
    // ============================================================

    private String uploadedFileName(
            int documentIndex
    ) {

        String fileName =
                uploadedFileNames[documentIndex];

        return fileName == null
                ? "Not uploaded yet"
                : fileName;
    }

    // ============================================================
    // UPLOADED CHECK
    // ============================================================

    private boolean isUploaded(
            int documentIndex
    ) {

        return uploadedFileNames[documentIndex] != null;
    }

    // ============================================================
    // DOCUMENT CARD
    // ============================================================

    private VBox documentCard(
            String icon,
            String title,
            String details,
            boolean verified
    ) {

        VBox card =
                new VBox(7);

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

        HBox topRow =
                new HBox();

        topRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

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

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Label status =
                new Label();

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

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        // Details

        Label detailsLabel =
                new Label(details);

        detailsLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #737d8d;"
        );

        // Validity / status

        Label validityLabel =
                new Label(
                        verified
                                ? "Uploaded successfully"
                                : "Required document"
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

    // ============================================================
    // REFRESH SCENE
    // ============================================================

    private void refreshScene() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: #D6F0F3;"
        );

        HBox header =
                createTopBar();

        ScrollPane sidebar =
                UserSideBar.createSidebar(
                        "Documents"
                );

        VBox content =
                createContent();

        ScrollPane contentScroll =
                new ScrollPane(content);

        contentScroll.setFitToWidth(true);
        contentScroll.setFitToHeight(false);

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

        documentsScene.setRoot(root);
    }
}
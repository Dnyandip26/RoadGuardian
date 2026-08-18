package project.ui.user;

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
import project.ui.user.UserHeader;

public class ServiceHistoryPage {

    // ============================================================
    // COLORS
    // ============================================================

    private static final String MAIN_BACKGROUND = "#D6F0F3";
    private static final String HEADING = "#172033";
    private static final String SECONDARY_TEXT = "#737d8d";
    private static final String BORDER = "#d7e0e8";

    private Scene serviceHistoryScene;

    // ============================================================
    // PUBLIC SCENE
    // ============================================================

    public Scene getServiceHistoryScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                MAIN_BACKGROUND + ";"
        );

        // Common Header
        HBox header = UserHeader.createHeader();

        // Common Sidebar
        ScrollPane sidebar =
                UserSideBar.createSidebar("Service History");

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
                "-fx-background-color: " +
                MAIN_BACKGROUND + ";" +
                "-fx-border-color: transparent;"
        );

        root.setTop(header);
        root.setLeft(sidebar);
        root.setCenter(contentScroll);

        serviceHistoryScene = new Scene(root,UserDashboard.dashboardStage.getWidth(),UserDashboard.dashboardStage.getHeight());

        return serviceHistoryScene;
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
                "-fx-background-color: " +
                MAIN_BACKGROUND + ";"
        );

        HBox heading =
                createHeading();

        HBox summaryCards =
                createSummaryCards();

        VBox invoiceCard =
                createInvoiceCard();

        content.getChildren().addAll(
                heading,
                summaryCards,
                invoiceCard
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
                new Label("Service History");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label subtitle =
                new Label(
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

    // ============================================================
    // SUMMARY CARDS
    // ============================================================

    private HBox createSummaryCards() {

        HBox summaryCards =
                new HBox(18);

        VBox totalServices =
                createSummaryCard(
                        "⚒",
                        "TOTAL SERVICES",
                        "18",
                        "Since Mar 2022",
                        "#2869e8",
                        "#e2ecff"
                );

        VBox lifetimeSpend =
                createSummaryCard(
                        "▣",
                        "LIFETIME SPEND",
                        "₹64,280",
                        "",
                        "#f06a27",
                        "#fceddf"
                );

        VBox averageDowntime =
                createSummaryCard(
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

    // ============================================================
    // SUMMARY CARD
    // ============================================================

    private VBox createSummaryCard(
            String icon,
            String heading,
            String value,
            String bottomText,
            String iconColor,
            String iconBackground
    ) {

        VBox card =
                new VBox(5);

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

        Label headingLabel =
                new Label(heading);

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

        Label iconLabel =
                new Label(icon);

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

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        card.getChildren().addAll(
                top,
                valueLabel
        );

        if (!bottomText.isEmpty()) {

            Label bottom =
                    new Label(bottomText);

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

    // ============================================================
    // INVOICE CARD
    // ============================================================

    private VBox createInvoiceCard() {

        VBox card = new VBox();

        card.setStyle(
                "-fx-background-color: #eef3f8;" +
                "-fx-border-color: #d7e0e8;" +
                "-fx-border-radius: 22;" +
                "-fx-background-radius: 22;"
        );

        // Invoice heading

        HBox invoiceHeading =
                new HBox();

        invoiceHeading.setPrefHeight(82);

        invoiceHeading.setPadding(
                new Insets(0, 22, 0, 22)
        );

        invoiceHeading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label("Invoices");

        title.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button exportButton =
                new Button("⇩   Export all");

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

        // Table Heading

        card.getChildren().add(
                createTableHeading()
        );

        // Rows

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

    // ============================================================
    // TABLE HEADING
    // ============================================================

    private HBox createTableHeading() {

        HBox row =
                new HBox();

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

        Label date =
                tableLabel("DATE", 200);

        Label service =
                tableLabel("SERVICE", 378);

        Label garage =
                tableLabel("GARAGE", 278);

        Label amount =
                tableLabel("AMOUNT", 158);

        Label status =
                tableLabel("STATUS", 200);

        Label invoice =
                tableLabel("INVOICE", 100);

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

    // ============================================================
    // INVOICE ROW
    // ============================================================

    private HBox invoiceRow(
            String dateText,
            String serviceText,
            String garageText,
            String amountText
    ) {

        HBox row =
                new HBox();

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

        Label date =
                normalLabel(
                        dateText,
                        200
                );

        Label service =
                normalLabel(
                        serviceText,
                        378
                );

        service.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label garage =
                normalLabel(
                        garageText,
                        278
                );

        Label amount =
                normalLabel(
                        amountText,
                        158
                );

        amount.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        // Status

        Label completed =
                new Label("Completed");

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

        HBox statusBox =
                new HBox(completed);

        statusBox.setPrefWidth(200);

        statusBox.setAlignment(
                Pos.CENTER_LEFT
        );

        // PDF

        Label pdfIcon =
                new Label("▣");

        pdfIcon.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #172033;"
        );

        Label pdfText =
                new Label("PDF");

        pdfText.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #172033;"
        );

        HBox pdf =
                new HBox(
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

    // ============================================================
    // TABLE LABEL
    // ============================================================

    private Label tableLabel(
            String text,
            double width
    ) {

        Label label =
                new Label(text);

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

    // ============================================================
    // NORMAL LABEL
    // ============================================================

    private Label normalLabel(
            String text,
            double width
    ) {

        Label label =
                new Label(text);

        label.setPrefWidth(
                width
        );

        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #687482;"
        );

        return label;
    }
}
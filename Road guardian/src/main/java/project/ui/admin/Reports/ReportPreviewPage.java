package project.ui.admin.Reports;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReportPreviewPage {

    // =========================================================
    // THEME
    // =========================================================

    private static final String BG = "#D6F0F3";
    private static final String WHITE = "#FFFFFF";

    private static final String BLUE = "#2563EB";
    private static final String BLUE_HOVER = "#1D4ED8";

    private static final String ORANGE = "#F97316";
    private static final String ORANGE_HOVER = "#EA580C";

    private static final String GREEN = "#16A34A";

    private static final String HEADING = "#172033";
    private static final String TEXT = "#526274";
    private static final String BORDER = "#B8D4D9";

    // =========================================================
    // DATA
    // =========================================================

    private final ReportManagementPage.ReportData report;

    private final Runnable backAction;

    private final Label statusLabel = new Label();
    private boolean pdfGenerated = false;
    private VBox rootView;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ReportPreviewPage(
            ReportManagementPage.ReportData report,
            Runnable backAction
    ) {

        this.report = report;

        this.backAction = backAction;
    }

    // =========================================================
    // VIEW
    // =========================================================

    public VBox getView() {

        // Reuse the same view so the PDF success message is not reset.
        if (rootView != null) {
            return rootView;
        }

        VBox root =
                new VBox(20);

        root.setPadding(
                new Insets(
                        28,
                        30,
                        30,
                        30
                )
        );

        root.setStyle(
                "-fx-background-color: " +
                        BG +
                        ";"
        );

        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backButton =
                new Button(
                        "← Back to Report Details"
                );

        styleBackButton(
                backButton
        );

        backButton.setOnAction(
                event -> {

                    if (
                            backAction != null
                    ) {

                        backAction.run();
                    }
                }
        );

        // =====================================================
        // HEADER
        // =====================================================

        Label title =
                new Label(
                        "Report Preview"
                );

        title.setTextFill(
                Color.web(HEADING)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        Label subtitle =
                new Label(
                        "Preview the selected report before generating the PDF."
                );

        subtitle.setTextFill(
                Color.web(TEXT)
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        VBox heading =
                new VBox(
                        5
                );

        heading.getChildren().addAll(
                title,
                subtitle
        );

        // =====================================================
        // REPORT PREVIEW
        // =====================================================

        VBox previewCard =
                createPreviewCard();

        VBox.setVgrow(
                previewCard,
                Priority.ALWAYS
        );

        root.getChildren().addAll(
                backButton,
                heading,
                previewCard
        );

        rootView = root;
        return rootView;
    }

    // =========================================================
    // PREVIEW CARD
    // =========================================================

    private VBox createPreviewCard() {

        VBox card =
                new VBox(22);

        card.setPadding(
                new Insets(
                        40,
                        36,
                        32,
                        36
                )
        );

        card.setStyle(
                "-fx-background-color: " +
                        WHITE +
                        ";" +
                        "-fx-background-radius: 5;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 5;"
        );

        // =====================================================
        // BRAND
        // =====================================================

        Label brand =
                new Label(
                        "ROADGUARDIAN"
                );

        brand.setTextFill(
                Color.web(BLUE)
        );

        brand.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        // =====================================================
        // REPORT NAME
        // =====================================================

        Label reportName =
                new Label(
                        report.getName()
                );

        reportName.setTextFill(
                Color.web(HEADING)
        );

        reportName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        // =====================================================
        // LINE
        // =====================================================

        Region line =
                new Region();

        line.setMinHeight(
                2
        );

        line.setMaxHeight(
                2
        );

        line.setStyle(
                "-fx-background-color: " +
                        BORDER +
                        ";"
        );

        // =====================================================
        // INFORMATION
        // =====================================================

        GridPane information =
                new GridPane();

        information.setHgap(
                40
        );

        information.setVgap(
                13
        );

        addInformationRow(
                information,
                0,
                "Report ID",
                report.getId()
        );

        addInformationRow(
                information,
                1,
                "Report Type",
                report.getType()
        );

        addInformationRow(
                information,
                2,
                "Generated By",
                report.getGeneratedBy()
        );

        addInformationRow(
                information,
                3,
                "Generated Date",
                report.getDate()
        );

        addInformationRow(
                information,
                4,
                "Total Records",
                report.getRecords()
        );

        // =====================================================
        // SUMMARY
        // =====================================================

        Label summaryTitle =
                new Label(
                        "Report Summary"
                );

        summaryTitle.setTextFill(
                Color.web(HEADING)
        );

        summaryTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        Label summary =
                new Label(
                        createSummary()
                );

        summary.setTextFill(
                Color.web(TEXT)
        );

        summary.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        summary.setWrapText(
                true
        );

        // =====================================================
        // BOTTOM
        // =====================================================

        HBox bottom =
                new HBox(
                        15
                );

        bottom.setAlignment(
                Pos.CENTER_RIGHT
        );

        statusLabel.setTextFill(
                Color.web(GREEN)
        );

        statusLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button generateButton =
                new Button(
                        "Generate PDF"
                );

        styleGenerateButton(
                generateButton
        );

        generateButton.setOnAction(
                event ->
                        generatePdf()
        );

        bottom.getChildren().addAll(
                statusLabel,
                spacer,
                generateButton
        );

        card.getChildren().addAll(
                brand,
                reportName,
                line,
                information,
                summaryTitle,
                summary,
                bottom
        );

        return card;
    }

    // =========================================================
    // INFORMATION ROW
    // =========================================================

    private void addInformationRow(
            GridPane grid,
            int row,
            String title,
            String value
    ) {

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setTextFill(
                Color.web(TEXT)
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        Label valueLabel =
                new Label(
                        value
                );

        valueLabel.setTextFill(
                Color.web(HEADING)
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        grid.add(
                titleLabel,
                0,
                row
        );

        grid.add(
                valueLabel,
                1,
                row
        );
    }

    // =========================================================
    // SUMMARY
    // =========================================================

    private String createSummary() {

        return "RoadGuardian "
                + report.getType()
                + " report containing "
                + report.getRecords()
                + " records.";
    }

    // =========================================================
    // GENERATE PDF
    // =========================================================

    private void generatePdf() {

        try {

            String userHome =
                    System.getProperty("user.home");

            File documentsFolder =
                    new File(
                            userHome,
                            "Documents"
                    );

            File roadGuardianFolder =
                    new File(
                            documentsFolder,
                            "RoadGuardian"
                    );

            File reportsFolder =
                    new File(
                            roadGuardianFolder,
                            "Reports"
                    );

            if (!reportsFolder.exists()) {

                if (!reportsFolder.mkdirs()) {

                    throw new IOException(
                            "Unable to create Reports folder."
                    );
                }
            }

            String fileName =
                    report.getId()
                            + "_"
                            + cleanFileName(
                            report.getName()
                    )
                            + ".pdf";

            File pdfFile =
                    new File(
                            reportsFolder,
                            fileName
                    );

            createPdfFile(pdfFile);

            // =========================================
            // SUCCESS STATE
            // =========================================

            pdfGenerated = true;

            statusLabel.setText(
                    "✓ PDF generated successfully"
            );

            statusLabel.setTextFill(
                    Color.web("#16A34A")
            );

            statusLabel.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            13
                    )
            );

            // =========================================
            // KEEP MESSAGE VISIBLE
            // =========================================

            statusLabel.setVisible(true);
            statusLabel.setManaged(true);

            System.out.println(
                    "PDF generated at:"
            );

            System.out.println(
                    pdfFile.getAbsolutePath()
            );

        } catch (Exception e) {

            e.printStackTrace();

            pdfGenerated = false;

            statusLabel.setText(
                    "✗ PDF generation failed"
            );

            statusLabel.setTextFill(
                    Color.web("#DC2626")
            );

            statusLabel.setVisible(true);
            statusLabel.setManaged(true);
        }
    }

    // =========================================================
    // CREATE PDF FILE
    // =========================================================

    private void createPdfFile(
            File file
    ) throws IOException {

        StringBuilder pdf =
                new StringBuilder();

        /*
         * PDF Header
         */

        pdf.append(
                "%PDF-1.4\n"
        );

        pdf.append(
                "%\u00e2\u00e3\u00cf\u00d3\n"
        );

        long[] offsets =
                new long[6];

        // =====================================================
        // OBJECT 1 - CATALOG
        // =====================================================

        offsets[1] =
                pdf.length();

        pdf.append(
                "1 0 obj\n"
        );

        pdf.append(
                "<< /Type /Catalog /Pages 2 0 R >>\n"
        );

        pdf.append(
                "endobj\n"
        );

        // =====================================================
        // OBJECT 2 - PAGES
        // =====================================================

        offsets[2] =
                pdf.length();

        pdf.append(
                "2 0 obj\n"
        );

        pdf.append(
                "<< /Type /Pages /Kids [3 0 R] /Count 1 >>\n"
        );

        pdf.append(
                "endobj\n"
        );

        // =====================================================
        // OBJECT 3 - PAGE
        // =====================================================

        offsets[3] =
                pdf.length();

        pdf.append(
                "3 0 obj\n"
        );

        pdf.append(
                "<< /Type /Page "
                        + "/Parent 2 0 R "
                        + "/MediaBox [0 0 595 842] "
                        + "/Resources << "
                        + "/Font << /F1 4 0 R >> "
                        + ">> "
                        + "/Contents 5 0 R >>\n"
        );

        pdf.append(
                "endobj\n"
        );

        // =====================================================
        // OBJECT 4 - FONT
        // =====================================================

        offsets[4] =
                pdf.length();

        pdf.append(
                "4 0 obj\n"
        );

        pdf.append(
                "<< /Type /Font "
                        + "/Subtype /Type1 "
                        + "/BaseFont /Helvetica >>\n"
        );

        pdf.append(
                "endobj\n"
        );

        // =====================================================
        // PAGE CONTENT
        // =====================================================

        StringBuilder content =
                new StringBuilder();

        content.append(
                "BT\n"
        );

        content.append(
                "/F1 24 Tf\n"
        );

        content.append(
                "50 780 Td\n"
        );

        content.append(
                "("
                        + pdfText(
                        "ROADGUARDIAN"
                )
                        + ") Tj\n"
        );

        content.append(
                "0 -45 Td\n"
        );

        content.append(
                "/F1 18 Tf\n"
        );

        content.append(
                "("
                        + pdfText(
                        report.getName()
                )
                        + ") Tj\n"
        );

        content.append(
                "0 -50 Td\n"
        );

        content.append(
                "/F1 12 Tf\n"
        );

        addPdfLine(
                content,
                "Report ID: "
                        + report.getId()
        );

        addPdfLine(
                content,
                "Report Type: "
                        + report.getType()
        );

        addPdfLine(
                content,
                "Generated By: "
                        + report.getGeneratedBy()
        );

        addPdfLine(
                content,
                "Generated Date: "
                        + report.getDate()
        );

        addPdfLine(
                content,
                "Total Records: "
                        + report.getRecords()
        );

        content.append(
                "0 -40 Td\n"
        );

        content.append(
                "/F1 15 Tf\n"
        );

        content.append(
                "("
                        + pdfText(
                        "Report Summary"
                )
                        + ") Tj\n"
        );

        content.append(
                "0 -30 Td\n"
        );

        content.append(
                "/F1 11 Tf\n"
        );

        content.append(
                "("
                        + pdfText(
                        createSummary()
                )
                        + ") Tj\n"
        );

        content.append(
                "ET\n"
        );

        byte[] contentBytes =
                content.toString()
                        .getBytes(
                                StandardCharsets.ISO_8859_1
                        );

        // =====================================================
        // OBJECT 5 - CONTENT
        // =====================================================

        offsets[5] =
                pdf.length();

        pdf.append(
                "5 0 obj\n"
        );

        pdf.append(
                "<< /Length "
                        + contentBytes.length
                        + " >>\n"
        );

        pdf.append(
                "stream\n"
        );

        pdf.append(
                content
        );

        pdf.append(
                "\nendstream\n"
        );

        pdf.append(
                "endobj\n"
        );

        // =====================================================
        // XREF
        // =====================================================

        long xrefPosition =
                pdf.length();

        pdf.append(
                "xref\n"
        );

        pdf.append(
                "0 6\n"
        );

        pdf.append(
                "0000000000 65535 f \n"
        );

        for (
                int i = 1;
                i <= 5;
                i++
        ) {

            pdf.append(
                    String.format(
                            "%010d 00000 n \n",
                            offsets[i]
                    )
            );
        }

        // =====================================================
        // TRAILER
        // =====================================================

        pdf.append(
                "trailer\n"
        );

        pdf.append(
                "<< /Size 6 "
                        + "/Root 1 0 R >>\n"
        );

        pdf.append(
                "startxref\n"
        );

        pdf.append(
                xrefPosition
        );

        pdf.append(
                "\n%%EOF\n"
        );

        // =====================================================
        // WRITE FILE
        // =====================================================

        try (
                FileOutputStream output =
                        new FileOutputStream(
                                file
                        )
        ) {

            output.write(
                    pdf.toString()
                            .getBytes(
                                    StandardCharsets.ISO_8859_1
                            )
            );
        }
    }

    // =========================================================
    // PDF LINE
    // =========================================================

    private void addPdfLine(
            StringBuilder content,
            String text
    ) {

        content.append(
                "("
                        + pdfText(text)
                        + ") Tj\n"
        );

        content.append(
                "0 -25 Td\n"
        );
    }

    // =========================================================
    // PDF TEXT ESCAPE
    // =========================================================

    private String pdfText(
            String text
    ) {

        if (
                text == null
        ) {

            return "";
        }

        return text
                .replace(
                        "\\",
                        "\\\\"
                )
                .replace(
                        "(",
                        "\\("
                )
                .replace(
                        ")",
                        "\\)"
                )
                .replace(
                        "\n",
                        " "
                )
                .replace(
                        "\r",
                        " "
                );
    }

    // =========================================================
    // CLEAN FILE NAME
    // =========================================================

    private String cleanFileName(
            String name
    ) {

        if (
                name == null ||
                        name.isBlank()
        ) {

            return "Report";
        }

        return name
                .replaceAll(
                        "[\\\\/:*?\"<>|]",
                        "_"
                )
                .replaceAll(
                        "\\s+",
                        "_"
                );
    }

    // =========================================================
    // BACK BUTTON STYLE
    // =========================================================

    private void styleBackButton(
            Button button
    ) {

        button.setTextFill(
                Color.web(BLUE)
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setPadding(
                new Insets(
                        9,
                        14,
                        9,
                        14
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color: #FFFFFF;"
                        + "-fx-border-color: "
                        + BLUE
                        + ";"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: #DBEAFE;"
                                        + "-fx-border-color: "
                                        + BLUE_HOVER
                                        + ";"
                                        + "-fx-border-radius: 8;"
                                        + "-fx-background-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                        + "-fx-border-color: "
                                        + BLUE
                                        + ";"
                                        + "-fx-border-radius: 8;"
                                        + "-fx-background-radius: 8;"
                        )
        );
    }

    // =========================================================
    // GENERATE BUTTON STYLE
    // =========================================================

    private void styleGenerateButton(
            Button button
    ) {

        button.setTextFill(
                Color.WHITE
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setPadding(
                new Insets(
                        10,
                        18,
                        10,
                        18
                )
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color: "
                        + ORANGE
                        + ";"
                        + "-fx-background-radius: 8;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: "
                                        + ORANGE_HOVER
                                        + ";"
                                        + "-fx-background-radius: 8;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: "
                                        + ORANGE
                                        + ";"
                                        + "-fx-background-radius: 8;"
                        )
        );
    }
}

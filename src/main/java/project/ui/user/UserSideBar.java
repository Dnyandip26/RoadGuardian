package project.ui.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import project.app.AppNavigator;

public class UserSideBar {
        // ============================================================
        // ROADGUARDIAN COLOR PALETTE
        // ============================================================

        private static final String MAIN_BACKGROUND = "#D6F0F3";
        private static final String SIDEBAR = "#C4D9E0";
        private static final String CARD_SURFACE = "#EEF9FA";
        private static final String SECONDARY_SURFACE = "#DFF3F5";

        private static final String PRIMARY_ORANGE = "#F97316";
        private static final String ORANGE_HOVER = "#EA580C";

        private static final String HEADING = "#172033";
        private static final String SECONDARY_TEXT = "#526274";

        private static final String NAV_BLUE = "#2563EB";
        private static final String SUCCESS = "#16A34A";
        private static final String EMERGENCY = "#DC2626";

        private static final String BORDER = "#B8D4D9";

        public static ScrollPane createSidebar(String selectedPage) {

                VBox sidebarContent = new VBox();

                sidebarContent.setPrefWidth(270);
                sidebarContent.setMinWidth(270);

                sidebarContent.setPadding(
                                new Insets(
                                                20,
                                                12,
                                                30,
                                                12));

                sidebarContent.setSpacing(5);

                sidebarContent.setStyle(
                                "-fx-background-color: " + SIDEBAR + ";");

                addSidebarSection(
                                sidebarContent,
                                "OVERVIEW");

                Button dashboardButton = createSidebarButton(
                                "▦",
                                "Dashboard",
                                "Dashboard".equals(selectedPage));

                Button sosNavButton = createSidebarButton(
                                "♧",
                                "Emergency SOS",
                                "Emergency SOS".equals(selectedPage));

                Button liveMapButton = createSidebarButton(
                                "◇",
                                "Live Map",
                                "Live Map".equals(selectedPage));

                sidebarContent.getChildren().addAll(
                                dashboardButton,
                                sosNavButton,
                                liveMapButton);

                addSidebarSection(
                                sidebarContent,
                                "ASSISTANCE");

                Button aiDiagnosisButton = createSidebarButton(
                                "♧",
                                "AI Diagnosis",

                                "AI Diagnosis".equals(selectedPage));

                Button mechanicsButton = createSidebarButton(
                                "⚒",
                                "Mechanics",
                                "Mechanics".equals(selectedPage));
                ;

                Button towTruckButton = createSidebarButton(
                                "▱",
                                "Tow Truck",
                                "Tow Truck".equals(selectedPage));

                Button costEstimatorButton = createSidebarButton(
                                "▤",
                                "Cost Estimator",
                                "Cost Estimator".equals(selectedPage));

                sidebarContent.getChildren().addAll(
                                aiDiagnosisButton,
                                mechanicsButton,
                                towTruckButton,
                                costEstimatorButton);

                // ========================================================
                // NAVIGATION ONLY
                // ========================================================

                dashboardButton.setOnAction(e -> navigateTo(dashboardButton, "Dashboard"));
                sosNavButton.setOnAction(e -> navigateTo(sosNavButton, "Emergency SOS"));
                liveMapButton.setOnAction(e -> navigateTo(liveMapButton, "Live Map"));
                aiDiagnosisButton.setOnAction(e -> navigateTo(aiDiagnosisButton, "AI Diagnosis"));
                mechanicsButton.setOnAction(e -> navigateTo(mechanicsButton, "Mechanics"));
                towTruckButton.setOnAction(e -> navigateTo(towTruckButton, "Tow Truck"));
                costEstimatorButton.setOnAction(e -> navigateTo(costEstimatorButton, "Cost Estimator"));

                addSidebarSection(
                                sidebarContent,
                                "GARAGE");

                sidebarContent.getChildren().addAll(
                                createSidebarButton(
                                                "▱",
                                                "My Vehicles",
                                                "My Vehicles".equals(selectedPage)),
                                createSidebarButton(
                                                "◷",
                                                "Service History",
                                                "Service History".equals(selectedPage)),
                                createSidebarButton(
                                                "▧",
                                                "Documents",
                                                "Documents".equals(selectedPage)));

                addSidebarSection(
                                sidebarContent,
                                "ACCOUNT");

                sidebarContent.getChildren().addAll(
                                createSidebarButton(
                                                "♙",
                                                "Women Safety",
                                                "Women Safety".equals(selectedPage)),
                                createSidebarButton(
                                                "♧",
                                                "Notifications",
                                                "Notifications".equals(selectedPage)),
                                createSidebarButton(
                                                "⚙",
                                                "Settings",
                                                "Settings".equals(selectedPage)));

                for (javafx.scene.Node node : sidebarContent.getChildren()) {
                        if (node instanceof Button button
                                        && button.getUserData() instanceof String pageName
                                        && isNavigatorPage(pageName)) {
                                button.setOnAction(e -> navigateTo(button, pageName));
                        }
                }

                ScrollPane sidebarScroll = new ScrollPane(
                                sidebarContent);

                sidebarScroll.setPrefWidth(270);
                sidebarScroll.setMinWidth(270);
                sidebarScroll.setMaxWidth(270);

                sidebarScroll.setFitToWidth(true);

                // Vertical scrolling ON
                sidebarScroll.setFitToHeight(false);

                // Horizontal scrolling OFF
                sidebarScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                // Always show vertical scrollbar
                sidebarScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.ALWAYS);

                sidebarScroll.setPannable(true);

                sidebarScroll.setStyle(
                                "-fx-background-color: " + SIDEBAR + ";" +
                                                "-fx-border-color: transparent;");

                sidebarScroll.applyCss();

                sidebarScroll.lookupAll(".scroll-bar:vertical").forEach(node -> {
                        node.setStyle(
                                        "-fx-background-color: #1E3A5F;" +
                                                        "-fx-background-radius: 8;");
                });

                sidebarScroll.lookupAll(".scroll-bar:vertical .thumb").forEach(node -> {
                        node.setStyle(
                                        "-fx-background-color: #0F2747;" +
                                                        "-fx-background-radius: 8;");
                });

                return sidebarScroll;
        }

        private static boolean isNavigatorPage(String pageName) {
                return pageName.equals("Dashboard")
                                || pageName.equals("Emergency SOS")
                                || pageName.equals("Live Map")
                                || pageName.equals("AI Diagnosis")
                                || pageName.equals("Mechanics")
                                || pageName.equals("Tow Truck")
                                || pageName.equals("Cost Estimator")
                                || pageName.equals("My Vehicles")
                                || pageName.equals("Service History")
                                || pageName.equals("Documents")
                                || pageName.equals("Women Safety")
                                || pageName.equals("Notifications")
                                || pageName.equals("Settings");
        }

        private static void navigateTo(Button sourceButton, String pageName) {
                Stage stage = (Stage) sourceButton.getScene().getWindow();
                AppNavigator.navigate(stage, pageName);
        }

        private static void addSidebarSection(
                        VBox sidebar,
                        String title) {

                Label section = new Label(title);

                section.setPadding(
                                new Insets(
                                                11,
                                                12,
                                                7,
                                                12));

                section.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                section.setTextFill(
                                Color.web(SECONDARY_TEXT));

                sidebar.getChildren().add(
                                section);
        }

        private static Button createSidebarButton(
                        String icon,
                        String text,
                        boolean selected) {

                Button button = new Button(
                                icon + "    " + text);

                button.setUserData(text);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(44);

                button.setPadding(
                                new Insets(
                                                0,
                                                13,
                                                0,
                                                13));

                button.setFont(
                                Font.font(
                                                "Arial",
                                                selected
                                                                ? FontWeight.BOLD
                                                                : FontWeight.NORMAL,
                                                15));

                if (selected) {

                        button.setStyle(
                                        "-fx-background-color: " + NAV_BLUE + ";" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-background-radius: 24;");

                } else {

                        button.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: " + HEADING + ";" +
                                                        "-fx-background-radius: 24;");

                        button.setOnMouseEntered(
                                        e -> button.setStyle(
                                                        "-fx-background-color: " + SECONDARY_SURFACE + ";" +
                                                                        "-fx-text-fill: " + HEADING + ";" +
                                                                        "-fx-background-radius: 24;"));

                        button.setOnMouseExited(
                                        e -> button.setStyle(
                                                        "-fx-background-color: transparent;" +
                                                                        "-fx-text-fill: " + HEADING + ";" +
                                                                        "-fx-background-radius: 24;"));
                }

                // Emergency SOS opens the dedicated SOS page.
                if (text.equals("Emergency SOS")) {
                        button.setOnAction(
                                        e -> openSOSPage(button));
                }

                // Live Map opens inside the SAME window.
                if (text.equals("Live Map")) {
                        button.setOnAction(
                                        e -> openLiveMapPage(button));
                }

                return button;
        }

        // ============================================================
        // SOS PAGE NAVIGATION
        // ============================================================

        private static void openSOSPage(Button sourceButton) {

                System.out.println("Navigated to SOS PAGE");
                SOSPage sosPage = new SOSPage();
                UserDashboard.dashboardStage.setScene(sosPage.getSOSScene());
        }

        // ============================================================
        // LIVE MAP PAGE NAVIGATION
        // ============================================================

        private static void openLiveMapPage(Button sourceButton) {

                try {

                        LiveMapPage liveMapPage = new LiveMapPage();

                        Stage stage = (Stage) sourceButton
                                        .getScene()
                                        .getWindow();

                        stage.setTitle(
                                        "RoadGuardian - Live Tracking");

                        stage.setScene(
                                        liveMapPage.getLiveMapScene());

                } catch (Exception ex) {

                        ex.printStackTrace();
                }
        }

        // ============================================================
        // AI DIAGNOSIS PAGE NAVIGATION
        // ============================================================

        private static void openAIDiagnosisPage(Button sourceButton) {

                try {

                        AIDiagnosisPage aiDiagnosisPage = new AIDiagnosisPage();

                        Stage stage = (Stage) sourceButton
                                        .getScene()
                                        .getWindow();

                        stage.setTitle(
                                        "RoadGuardian - AI Diagnosis");

                        stage.setScene(
                                        aiDiagnosisPage.getAIDiagnosisScene());

                        stage.show();

                } catch (Exception ex) {

                        ex.printStackTrace();
                }
        }

        // ============================================================
        // MECHANICS PAGE NAVIGATION
        // ============================================================

        private static void openMechanicsPage(Button sourceButton) {

                try {

                        MechanicsPage mechanicsPage = new MechanicsPage();

                        Stage stage = (Stage) sourceButton
                                        .getScene()
                                        .getWindow();

                        stage.setTitle(
                                        "RoadGuardian - Mechanics");

                        stage.setScene(
                                        mechanicsPage.getMechanicsScene());

                        stage.show();

                } catch (Exception ex) {

                        ex.printStackTrace();
                }
        }

        // ============================================================
        // TOW TRUCK PAGE NAVIGATION
        // ============================================================

        private static void openTowTruckPage(Button sourceButton) {

                try {

                        TowTruckPage towTruckPage = new TowTruckPage();

                        Stage stage = (Stage) sourceButton
                                        .getScene()
                                        .getWindow();

                        stage.setTitle(
                                        "RoadGuardian - Tow Truck");

                        stage.setScene(
                                        towTruckPage.getTowTruckScene());

                        stage.show();

                } catch (Exception ex) {

                        ex.printStackTrace();
                }
        }

        // ============================================================
        // COST ESTIMATOR PAGE NAVIGATION
        // ============================================================

        private static void openCostEstimatorPage(Button sourceButton) {

                try {

                        CostEstimatorPage costEstimatorPage = new CostEstimatorPage();

                        Stage stage = (Stage) sourceButton
                                        .getScene()
                                        .getWindow();

                        stage.setTitle(
                                        "RoadGuardian - Cost Estimator");

                        stage.setScene(
                                        costEstimatorPage.getCostEstimatorScene());

                        stage.show();

                } catch (Exception ex) {

                        ex.printStackTrace();
                }
        }

}

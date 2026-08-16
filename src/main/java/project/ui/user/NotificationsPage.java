package project.ui.user;

import project.app.AppNavigator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class NotificationsPage extends Application {

    private final List<NotificationData> notifications = new ArrayList<>(List.of(
            new NotificationData("⚒", "Mechanic assigned", "Arjun Mehta is on the way — ETA 6 min.", "2 min ago", true, false),
            new NotificationData("⌖", "Mechanic reached your location", "Arjun has arrived at NH-48, Exit 12B.", "18 min ago", true, false),
            new NotificationData("✓", "Repair completed", "Battery jump start closed. Invoice #RG-8842 generated.", "1 hr ago", true, false),
            new NotificationData("♧", "Insurance expiring soon", "Your policy for MH 12 QR 4410 expires in 15 days.", "Yesterday", false, true),
            new NotificationData("▣", "Vehicle service due", "Oil change recommended within 480 km.", "2 days ago", false, true),
            new NotificationData("♙", "Safety check-in", "Your trip guardian confirmed you reached home safely.", "3 days ago", false, false)
    ));
    private String selectedFilter = "All";

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
                "RoadGuardian - Notifications"
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

        // ACTIVE NOTIFICATIONS

        sidebar.getChildren().add(
                activeSideButton(
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

        VBox content = new VBox(18);

        content.setPadding(
                new Insets(30, 30, 30, 30)
        );
        content.setStyle("-fx-background-color: #F4F4F4;");

        HBox heading = createHeading();

        HBox filters = createFilters();

        VBox notificationBox =
                createNotificationBox();

        content.getChildren().addAll(
                heading,
                filters,
                notificationBox
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
                "Notifications"
        );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        Label subtitle = new Label(
                unreadNotificationCount() + " unread updates"
        );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #737d8d;"
        );

        headingText.getChildren().addAll(
                title,
                subtitle
        );

        Region space = new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button markAll = new Button(
                "Mark all as read"
        );

        markAll.setPrefHeight(40);

        markAll.setPadding(
                new Insets(0, 18, 0, 18)
        );

        markAll.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 25;" +
                "-fx-background-radius: 25;" +
                "-fx-text-fill: #263142;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        markAll.setOnAction(
                event -> {
                    notifications.forEach(notification -> notification.unread = false);
                    selectedFilter = "All";
                    start((Stage) markAll.getScene().getWindow());
                }
        );
        markAll.setDisable(unreadNotificationCount() == 0);

        heading.getChildren().addAll(
                headingText,
                space,
                markAll
        );

        return heading;
    }

    // =====================================================
    // FILTERS
    // =====================================================

    private HBox createFilters() {

        HBox filters = new HBox(4);

        filters.setPrefHeight(40);

        filters.setPadding(
                new Insets(3)
        );

        filters.setAlignment(
                Pos.CENTER_LEFT
        );

        filters.setStyle(
                "-fx-background-color: #eef3f8;" +
                "-fx-background-radius: 25;"
        );

        Button all = filterButton(
                "All",
                "All".equals(selectedFilter)
        );

        Button unread = filterButton(
                "Unread",
                "Unread".equals(selectedFilter)
        );

        Button reminders = filterButton(
                "Reminders",
                "Reminders".equals(selectedFilter)
        );

        all.setOnAction(event -> selectFilter("All", all));
        unread.setOnAction(event -> selectFilter("Unread", unread));
        reminders.setOnAction(event -> selectFilter("Reminders", reminders));

        filters.getChildren().addAll(
                all,
                unread,
                reminders
        );

        return filters;
    }

    // =====================================================
    // FILTER BUTTON
    // =====================================================

    private Button filterButton(
            String text,
            boolean active) {

        Button button = new Button(
                text
        );

        button.setPrefHeight(34);

        button.setPadding(
                new Insets(0, 15, 0, 15)
        );

        if (active) {

            button.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: #d5e0e7;" +
                    "-fx-border-radius: 20;" +
                    "-fx-background-radius: 20;" +
                    "-fx-text-fill: #263142;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #65717d;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;"
            );
        }

        return button;
    }

    private void selectFilter(String filter, Button sourceButton) {
        selectedFilter = filter;
        start((Stage) sourceButton.getScene().getWindow());
    }

    private int unreadNotificationCount() {
        int count = 0;
        for (NotificationData notification : notifications) {
            if (notification.unread) {
                count++;
            }
        }
        return count;
    }

    private boolean matchesSelectedFilter(NotificationData notification) {
        return switch (selectedFilter) {
            case "Unread" -> notification.unread;
            case "Reminders" -> notification.reminder;
            default -> true;
        };
    }

    // =====================================================
    // NOTIFICATION BOX
    // =====================================================

    private VBox createNotificationBox() {

        VBox box = new VBox();

        box.setStyle(
                "-fx-background-color: #eef3f8;" +
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-radius: 22;" +
                "-fx-background-radius: 22;"
        );

        boolean hasNotifications = false;
        for (NotificationData notification : notifications) {
            if (matchesSelectedFilter(notification)) {
                box.getChildren().add(notificationItem(
                        notification.icon,
                        notification.title,
                        notification.description,
                        notification.time,
                        notification.unread
                ));
                hasNotifications = true;
            }
        }

        if (!hasNotifications) {
            Label emptyMessage = new Label("No " + selectedFilter.toLowerCase() + " notifications.");
            emptyMessage.setPadding(new Insets(28));
            emptyMessage.setStyle("-fx-font-size: 13px; -fx-text-fill: #737d8d;");
            box.getChildren().add(emptyMessage);
        }

        return box;
    }

    // =====================================================
    // NOTIFICATION ITEM
    // =====================================================

    private HBox notificationItem(
            String icon,
            String title,
            String description,
            String time,
            boolean unread) {

        HBox item = new HBox(16);

        item.setMinHeight(90);

        item.setAlignment(
                Pos.CENTER_LEFT
        );

        item.setPadding(
                new Insets(15, 20, 15, 20)
        );

        // Icon

        Label iconLabel = new Label(
                icon
        );

        iconLabel.setPrefWidth(40);
        iconLabel.setPrefHeight(40);

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setStyle(
                "-fx-background-color: #f3f6f9;" +
                "-fx-background-radius: 30;" +
                "-fx-text-fill: #65717d;" +
                "-fx-font-size: 18px;"
        );

        // Text

        VBox textBox = new VBox(4);

        HBox titleRow = new HBox(6);

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel = new Label(
                title
        );

        titleLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #172033;"
        );

        titleRow.getChildren().add(
                titleLabel
        );

        if (unread) {

            Label dot = new Label(
                    "●"
            );

            dot.setStyle(
                    "-fx-font-size: 9px;" +
                    "-fx-text-fill: #f04444;"
            );

            titleRow.getChildren().add(
                    dot
            );
        }

        Label descriptionLabel = new Label(
                description
        );

        descriptionLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #737d8d;"
        );

        textBox.getChildren().addAll(
                titleRow,
                descriptionLabel
        );

        HBox.setHgrow(
                textBox,
                Priority.ALWAYS
        );

        // Time

        Label timeLabel = new Label(
                time
        );

        timeLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #65717d;"
        );

        item.getChildren().addAll(
                iconLabel,
                textBox,
                timeLabel
        );

        // Separator

        item.setStyle(
                "-fx-border-color: #d5e0e7;" +
                "-fx-border-width: 0 0 1 0;"
        );

        return item;
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

    private static class NotificationData {
        private final String icon;
        private final String title;
        private final String description;
        private final String time;
        private boolean unread;
        private final boolean reminder;

        private NotificationData(String icon, String title, String description, String time,
                                 boolean unread, boolean reminder) {
            this.icon = icon;
            this.title = title;
            this.description = description;
            this.time = time;
            this.unread = unread;
            this.reminder = reminder;
        }
    }
}






package project.ui.user.Account;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import project.ui.user.DashBoard.UserSectionPage;

import java.util.ArrayList;
import java.util.List;

public class NotificationsPage extends UserSectionPage {

    private static final String MAIN_BACKGROUND = "#D6F0F3";

    private final List<NotificationData> notifications =
            new ArrayList<>(
                    List.of(
                            new NotificationData(
                                    "⚒",
                                    "Mechanic assigned",
                                    "Arjun Mehta is on the way — ETA 6 min.",
                                    "2 min ago",
                                    true,
                                    false
                            ),

                            new NotificationData(
                                    "⌖",
                                    "Mechanic reached your location",
                                    "Arjun has arrived at NH-48, Exit 12B.",
                                    "18 min ago",
                                    true,
                                    false
                            ),

                            new NotificationData(
                                    "✓",
                                    "Repair completed",
                                    "Battery jump start closed. Invoice #RG-8842 generated.",
                                    "1 hr ago",
                                    true,
                                    false
                            ),

                            new NotificationData(
                                    "♧",
                                    "Insurance expiring soon",
                                    "Your policy for MH 12 QR 4410 expires in 15 days.",
                                    "Yesterday",
                                    false,
                                    true
                            ),

                            new NotificationData(
                                    "▣",
                                    "Vehicle service due",
                                    "Oil change recommended within 480 km.",
                                    "2 days ago",
                                    false,
                                    true
                            ),

                            new NotificationData(
                                    "♙",
                                    "Safety check-in",
                                    "Your trip guardian confirmed you reached home safely.",
                                    "3 days ago",
                                    false,
                                    false
                            )
                    )
            );

    private String selectedFilter = "All";

    // =====================================================
    // GET VIEW
    // =====================================================

    @Override
    public VBox getView() {

        VBox content =
                createContent();

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(true);
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

        VBox root =
                new VBox();

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
    // MAIN CONTENT
    // =====================================================

    private VBox createContent() {

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(
                        30,
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

        HBox heading =
                createHeading();

        HBox filters =
                createFilters();

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

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox headingText =
                new VBox(2);

        Label title =
                new Label(
                        "Notifications"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #172033;"
        );

        Label subtitle =
                new Label(
                        unreadNotificationCount() +
                                " unread updates"
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #737d8d;"
        );

        headingText.getChildren().addAll(
                title,
                subtitle
        );

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Button markAll =
                new Button(
                        "Mark all as read"
                );

        markAll.setPrefHeight(
                40
        );

        markAll.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        markAll.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #d5e0e7;" +
                        "-fx-border-radius: 25;" +
                        "-fx-background-radius: 25;" +
                        "-fx-text-fill: #263142;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
        );

        markAll.setDisable(
                unreadNotificationCount() == 0
        );

        markAll.setOnAction(
                event -> {

                    for (
                            NotificationData notification :
                            notifications
                    ) {

                        notification.unread = false;
                    }

                    refreshView();
                }
        );

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

        HBox filters =
                new HBox(4);

        filters.setPrefHeight(
                40
        );

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

        Button all =
                filterButton(
                        "All",
                        "All".equals(
                                selectedFilter
                        )
                );

        Button unread =
                filterButton(
                        "Unread",
                        "Unread".equals(
                                selectedFilter
                        )
                );

        Button reminders =
                filterButton(
                        "Reminders",
                        "Reminders".equals(
                                selectedFilter
                        )
                );

        all.setOnAction(
                event -> {

                    selectedFilter = "All";

                    refreshView();
                }
        );

        unread.setOnAction(
                event -> {

                    selectedFilter = "Unread";

                    refreshView();
                }
        );

        reminders.setOnAction(
                event -> {

                    selectedFilter = "Reminders";

                    refreshView();
                }
        );

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
            boolean active
    ) {

        Button button =
                new Button(
                        text
                );

        button.setPrefHeight(
                34
        );

        button.setPadding(
                new Insets(
                        0,
                        15,
                        0,
                        15
                )
        );

        if (active) {

            button.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: #d5e0e7;" +
                            "-fx-border-radius: 20;" +
                            "-fx-background-radius: 20;" +
                            "-fx-text-fill: #263142;" +
                            "-fx-font-size: 13px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-cursor: hand;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-text-fill: #65717d;" +
                            "-fx-font-size: 13px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-cursor: hand;"
            );
        }

        return button;
    }

    // =====================================================
    // REFRESH
    // =====================================================

    private void refreshView() {

        // NotificationsPage is now controlled by UserDashboard.
        // The page itself does not create a Stage or Scene.
        //
        // Rebuilding the section is handled when UserDashboard
        // loads this page again. Notification state remains inside
        // this page instance.
    }

    // =====================================================
    // NOTIFICATION COUNT
    // =====================================================

    private int unreadNotificationCount() {

        int count = 0;

        for (
                NotificationData notification :
                notifications
        ) {

            if (notification.unread) {
                count++;
            }
        }

        return count;
    }

    // =====================================================
    // FILTER MATCH
    // =====================================================

    private boolean matchesSelectedFilter(
            NotificationData notification
    ) {

        if (
                "Unread".equals(
                        selectedFilter
                )
        ) {

            return notification.unread;
        }

        if (
                "Reminders".equals(
                        selectedFilter
                )
        ) {

            return notification.reminder;
        }

        return true;
    }

    // =====================================================
    // NOTIFICATION BOX
    // =====================================================

    private VBox createNotificationBox() {

        VBox box =
                new VBox();

        box.setStyle(
                "-fx-background-color: #eef3f8;" +
                        "-fx-border-color: #d5e0e7;" +
                        "-fx-border-radius: 22;" +
                        "-fx-background-radius: 22;"
        );

        boolean hasNotifications = false;

        for (
                NotificationData notification :
                notifications
        ) {

            if (
                    matchesSelectedFilter(
                            notification
                    )
            ) {

                box.getChildren().add(
                        notificationItem(
                                notification.icon,
                                notification.title,
                                notification.description,
                                notification.time,
                                notification.unread
                        )
                );

                hasNotifications = true;
            }
        }

        if (!hasNotifications) {

            Label emptyMessage =
                    new Label(
                            "No " +
                                    selectedFilter.toLowerCase() +
                                    " notifications."
                    );

            emptyMessage.setPadding(
                    new Insets(
                            28
                    )
            );

            emptyMessage.setStyle(
                    "-fx-font-size: 13px;" +
                            "-fx-text-fill: #737d8d;"
            );

            box.getChildren().add(
                    emptyMessage
            );
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
            boolean unread
    ) {

        HBox item =
                new HBox(16);

        item.setMinHeight(
                90
        );

        item.setAlignment(
                Pos.CENTER_LEFT
        );

        item.setPadding(
                new Insets(
                        15,
                        20,
                        15,
                        20
                )
        );

        item.setStyle(
                "-fx-border-color: #d5e0e7;" +
                        "-fx-border-width: 0 0 1 0;"
        );

        Label iconLabel =
                new Label(
                        icon
                );

        iconLabel.setPrefWidth(
                40
        );

        iconLabel.setPrefHeight(
                40
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        iconLabel.setStyle(
                "-fx-background-color: #f3f6f9;" +
                        "-fx-background-radius: 30;" +
                        "-fx-text-fill: #65717d;" +
                        "-fx-font-size: 18px;"
        );

        VBox textBox =
                new VBox(4);

        HBox titleRow =
                new HBox(6);

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label titleLabel =
                new Label(
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

            Label dot =
                    new Label(
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

        Label descriptionLabel =
                new Label(
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

        Label timeLabel =
                new Label(
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

        return item;
    }

    // =====================================================
    // DATA MODEL
    // =====================================================

    private static class NotificationData {

        private final String icon;

        private final String title;

        private final String description;

        private final String time;

        private boolean unread;

        private final boolean reminder;

        private NotificationData(
                String icon,
                String title,
                String description,
                String time,
                boolean unread,
                boolean reminder
        ) {

            this.icon =
                    icon;

            this.title =
                    title;

            this.description =
                    description;

            this.time =
                    time;

            this.unread =
                    unread;

            this.reminder =
                    reminder;
        }
    }
}
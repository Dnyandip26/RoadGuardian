package project.ui.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Cursor;

public class TowTruckPage {

    private static final String BG = "#DDF5F7";
    private static final String WHITE = "#FFFFFF";
    private static final String CARD = "#F5FBFC";
    private static final String BLUE = "#2864E8";
    private static final String DARK = "#142033";
    private static final String GREY = "#667085";
    private static final String BORDER = "#C8E0E5";
    private static final String ORANGE = "#FF7A18";

    private Scene scene;

    public TowTruckPage() {
        buildUI();
    }

    public Scene getTowTruckScene() {
        return scene;
    }

    private void buildUI() {

        // ================= HEADER =================

        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 28, 15, 28));
        header.setPrefHeight(78);
        header.setBackground(bg(WHITE, CornerRadii.EMPTY));

        Circle logoCircle = new Circle(21, Color.web(BLUE));

        Label check = new Label("✓");
        check.setTextFill(Color.WHITE);
        check.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        StackPane logo = new StackPane(logoCircle, check);

        Label road = new Label("Road");
        road.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        road.setTextFill(Color.web(DARK));

        Label guardian = new Label("Guardian");
        guardian.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        guardian.setTextFill(Color.web(BLUE));

        HBox brandText = new HBox(road, guardian);
        brandText.setAlignment(Pos.CENTER_LEFT);

        HBox brand = new HBox(10, logo, brandText);
        brand.setAlignment(Pos.CENTER_LEFT);

        Region headerSpace = new Region();
        HBox.setHgrow(headerSpace, Priority.ALWAYS);

        HBox search = new HBox(10);
        search.setAlignment(Pos.CENTER_LEFT);
        search.setPrefSize(390, 48);
        search.setPadding(new Insets(0, 18, 0, 18));
        search.setBackground(bg(WHITE, new CornerRadii(25)));
        search.setBorder(border(BORDER, 25, 1));

        Label searchIcon = new Label("⌕");
        searchIcon.setFont(Font.font("Arial", 24));
        searchIcon.setTextFill(Color.web(GREY));

        Label searchText = new Label(
                "Search mechanics, invoices, vehicles..."
        );
        searchText.setFont(Font.font("Arial", 15));
        searchText.setTextFill(Color.web(GREY));

        search.getChildren().addAll(searchIcon, searchText);

        Label notificationIcon = new Label("♧");
        notificationIcon.setFont(Font.font("Arial", 23));
        notificationIcon.setTextFill(Color.web(DARK));

        Circle notificationDot = new Circle(4, Color.web("#EF233C"));

        StackPane notification = new StackPane(
                notificationIcon,
                notificationDot
        );
        StackPane.setAlignment(
                notificationDot,
                Pos.TOP_RIGHT
        );
        notification.setPrefWidth(35);

        Circle avatarCircle = new Circle(
                21,
                Color.web("#E7EEFF")
        );

        Label avatarText = new Label("AN");
        avatarText.setFont(
                Font.font("Arial", FontWeight.BOLD, 14)
        );
        avatarText.setTextFill(Color.web(BLUE));

        StackPane avatar = new StackPane(
                avatarCircle,
                avatarText
        );

        Label userName = new Label("Aarav Nair");
        userName.setFont(
                Font.font("Arial", FontWeight.BOLD, 14)
        );
        userName.setTextFill(Color.web(DARK));

        Label userRole = new Label("Customer · Pune");
        userRole.setFont(Font.font("Arial", 12));
        userRole.setTextFill(Color.web(GREY));

        VBox userDetails = new VBox(
                2,
                userName,
                userRole
        );

        HBox profile = new HBox(
                10,
                avatar,
                userDetails
        );
        profile.setAlignment(Pos.CENTER_LEFT);
        profile.setPadding(
                new Insets(6, 14, 6, 8)
        );
        profile.setBackground(
                bg(WHITE, new CornerRadii(25))
        );
        profile.setBorder(
                border(BORDER, 25, 1)
        );

        header.getChildren().addAll(
                brand,
                headerSpace,
                search,
                notification,
                profile
        );

        // ================= SIDEBAR =================

        VBox sidebar = new VBox(7);
        sidebar.setPadding(
                new Insets(22, 12, 20, 12)
        );
        sidebar.setPrefWidth(275);
        sidebar.setBackground(
                bg(WHITE, CornerRadii.EMPTY)
        );

        sidebar.getChildren().add(section("OVERVIEW"));

        sidebar.getChildren().add(
                sideButton("▦", "Dashboard", false)
        );
        sidebar.getChildren().add(
                sideButton("♙", "Emergency SOS", false)
        );
        sidebar.getChildren().add(
                sideButton("◇", "Live Map", false)
        );

        sidebar.getChildren().add(
                section("ASSISTANCE")
        );

        sidebar.getChildren().add(
                sideButton("♧", "AI Diagnosis", false)
        );
        sidebar.getChildren().add(
                sideButton("⚒", "Mechanics", false)
        );
        sidebar.getChildren().add(
                sideButton("▱", "Tow Truck", true)
        );
        sidebar.getChildren().add(
                sideButton("▤", "Cost Estimator", false)
        );

        sidebar.getChildren().add(
                section("GARAGE")
        );

        sidebar.getChildren().add(
                sideButton("▱", "My Vehicles", false)
        );
        sidebar.getChildren().add(
                sideButton("◷", "Service History", false)
        );
        sidebar.getChildren().add(
                sideButton("▤", "Documents", false)
        );

        sidebar.getChildren().add(
                section("ACCOUNT")
        );

        sidebar.getChildren().add(
                sideButton("♙", "Women Safety", false)
        );
        sidebar.getChildren().add(
                sideButton("♧", "Notifications", false)
        );
        sidebar.getChildren().add(
                sideButton("⚙", "Settings", false)
        );

        ScrollPane sideScroll = new ScrollPane(sidebar);
        sideScroll.setPrefWidth(290);
        sideScroll.setMinWidth(290);
        sideScroll.setFitToWidth(true);
        sideScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );
        sideScroll.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: transparent;"
        );

        // ================= CONTENT =================

        VBox content = new VBox(18);
        content.setPadding(
                new Insets(28, 30, 35, 30)
        );
        content.setBackground(
                bg(BG, CornerRadii.EMPTY)
        );

        Label title = new Label("Tow Truck Assistance");
        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );
        title.setTextFill(Color.web(DARK));

        Label subtitle = new Label(
                "Flatbed and lift recovery available around you right now"
        );
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextFill(Color.web(GREY));

        VBox heading = new VBox(
                4,
                title,
                subtitle
        );

        // ================= MAIN COLUMNS =================

        HBox main = new HBox(20);
        main.setAlignment(Pos.TOP_LEFT);

        VBox leftColumn = new VBox(20);
        HBox.setHgrow(leftColumn, Priority.ALWAYS);

        VBox mapBox = createMapBox();

        VBox destinationBox = createDestinationBox();

        leftColumn.getChildren().addAll(
                mapBox,
                destinationBox
        );

        VBox truckCards = new VBox(16);
        truckCards.setPrefWidth(510);
        truckCards.setMinWidth(450);

        truckCards.getChildren().add(
                createTruckCard(
                        "FlatBed 4.5T",
                        "Metro Tow Services",
                        "9 min",
                        "2 km",
                        "₹1450",
                        "4.8",
                        "Sedan / Hatchback"
                )
        );

        truckCards.getChildren().add(
                createTruckCard(
                        "Hydraulic Lift 7T",
                        "RapidLift Recovery",
                        "15 min",
                        "3.8 km",
                        "₹2100",
                        "4.6",
                        "SUV / MUV"
                )
        );

        truckCards.getChildren().add(
                createTruckCard(
                        "Wheel Lift 3T",
                        "CityGuard Towing",
                        "22 min",
                        "6.2 km",
                        "₹1750",
                        "4.4",
                        "Sedan / Hatchback"
                )
        );

        truckCards.getChildren().add(
                createTruckCard(
                        "Heavy Recovery 10T",
                        "Highway Rescue",
                        "28 min",
                        "8.5 km",
                        "₹2800",
                        "4.3",
                        "SUV / Truck"
                )
        );

        main.getChildren().addAll(
                leftColumn,
                truckCards
        );

        content.getChildren().addAll(
                heading,
                main
        );

        HBox body = new HBox(
                sideScroll,
                content
        );

        HBox.setHgrow(
                content,
                Priority.ALWAYS
        );

        VBox root = new VBox(
                header,
                body
        );

        VBox.setVgrow(
                body,
                Priority.ALWAYS
        );

        scene = new Scene(
                root,
                1500,
                900
        );
    }

    // ================= MAP =================

    private VBox createMapBox() {

        VBox box = new VBox();
        box.setPrefHeight(420);
        box.setMinHeight(380);
        box.setPadding(new Insets(0));

        StackPane map = new StackPane();
        VBox.setVgrow(map, Priority.ALWAYS);

        map.setBackground(
                bg("#E8E8E6", new CornerRadii(25))
        );

        map.setBorder(
                border("#D0D9DB", 25, 1)
        );

        Line road1 = new Line(
                -100, 300,
                850, 60
        );
        road1.setStroke(Color.WHITE);
        road1.setStrokeWidth(11);

        Line road2 = new Line(
                40, 40,
                800, 360
        );
        road2.setStroke(Color.WHITE);
        road2.setStrokeWidth(10);

        Line road3 = new Line(
                330, -30,
                440, 430
        );
        road3.setStroke(Color.WHITE);
        road3.setStrokeWidth(8);

        Line blueRoad = new Line(
                250, 370,
                760, 310
        );
        blueRoad.setStroke(
                Color.web("#69B7E8")
        );
        blueRoad.setStrokeWidth(5);

        Line route = new Line(
                350, 205,
                520, 120
        );
        route.setStroke(Color.web(BLUE));
        route.setStrokeWidth(3);
        route.getStrokeDashArray().addAll(
                8.0,
                6.0
        );

        StackPane roads = new StackPane(
                road1,
                road2,
                road3,
                blueRoad,
                route
        );

        // YOUR VEHICLE

        Circle vehicleCircle = new Circle(
                22,
                Color.web(BLUE)
        );

        Label vehicleIcon = new Label("▰");
        vehicleIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );
        vehicleIcon.setTextFill(Color.WHITE);

        StackPane vehicleMarker = new StackPane(
                vehicleCircle,
                vehicleIcon
        );

        Label vehicleText = new Label(
                "Your vehicle"
        );
        vehicleText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );
        vehicleText.setTextFill(
                Color.web(DARK)
        );
        vehicleText.setPadding(
                new Insets(7, 12, 7, 12)
        );
        vehicleText.setBackground(
                bg(WHITE, new CornerRadii(15))
        );

        VBox vehicle = new VBox(
                4,
                vehicleMarker,
                vehicleText
        );
        vehicle.setAlignment(Pos.CENTER);

        StackPane.setAlignment(
                vehicle,
                Pos.CENTER
        );

        StackPane.setMargin(
                vehicle,
                new Insets(70, 0, 0, 0)
        );

        // TOW TRUCK MARKERS

        StackPane truck1 = truckMarker();
        StackPane truck2 = truckMarker();
        StackPane truck3 = truckMarker();

        StackPane.setAlignment(
                truck1,
                Pos.TOP_CENTER
        );
        StackPane.setMargin(
                truck1,
                new Insets(55, 0, 0, 0)
        );

        StackPane.setAlignment(
                truck2,
                Pos.CENTER_LEFT
        );
        StackPane.setMargin(
                truck2,
                new Insets(0, 155, 0, 0)
        );

        StackPane.setAlignment(
                truck3,
                Pos.CENTER_RIGHT
        );
        StackPane.setMargin(
                truck3,
                new Insets(0, 205, 0, 0)
        );

        Label time1 = mapLabel("9 min");
        Label time2 = mapLabel("22 min");
        Label time3 = mapLabel("15 min");

        StackPane.setAlignment(
                time1,
                Pos.TOP_CENTER
        );
        StackPane.setMargin(
                time1,
                new Insets(105, 0, 0, 0)
        );

        StackPane.setAlignment(
                time2,
                Pos.CENTER_LEFT
        );
        StackPane.setMargin(
                time2,
                new Insets(80, 0, 0, 140)
        );

        StackPane.setAlignment(
                time3,
                Pos.CENTER_RIGHT
        );
        StackPane.setMargin(
                time3,
                new Insets(80, 165, 0, 0)
        );

        map.getChildren().addAll(
                roads,
                vehicle,
                truck1,
                truck2,
                truck3,
                time1,
                time2,
                time3
        );

        VBox wrapper = new VBox(map);
        VBox.setVgrow(map, Priority.ALWAYS);

        return wrapper;
    }

    private StackPane truckMarker() {

        Circle outer = new Circle(27);
        outer.setFill(Color.TRANSPARENT);
        outer.setStroke(Color.WHITE);
        outer.setStrokeWidth(4);

        Circle inner = new Circle(
                22,
                Color.web(ORANGE)
        );

        Label icon = new Label("▱");
        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );
        icon.setTextFill(Color.WHITE);

        return new StackPane(
                outer,
                inner,
                icon
        );
    }

    private Label mapLabel(String text) {

        Label label = new Label(text);
        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );
        label.setTextFill(
                Color.web(DARK)
        );
        label.setPadding(
                new Insets(7, 13, 7, 13)
        );
        label.setBackground(
                bg(WHITE, new CornerRadii(15))
        );

        return label;
    }

    // ================= DESTINATION =================

    private VBox createDestinationBox() {

        VBox outer = new VBox(14);
        outer.setPadding(
                new Insets(20)
        );
        outer.setPrefHeight(155);

        outer.setBackground(
                bg(CARD, new CornerRadii(22))
        );

        outer.setBorder(
                border(BORDER, 22, 1)
        );

        Label title = new Label(
                "Drop-off destination"
        );
        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );
        title.setTextFill(
                Color.web(DARK)
        );

        HBox destination = new HBox(12);
        destination.setAlignment(
                Pos.CENTER_LEFT
        );
        destination.setPadding(
                new Insets(14)
        );

        destination.setBackground(
                bg(WHITE, new CornerRadii(22))
        );

        destination.setBorder(
                border(BORDER, 22, 1)
        );

        Label location = new Label("⌖");
        location.setFont(
                Font.font("Arial", 24)
        );
        location.setTextFill(
                Color.web(BLUE)
        );

        Label place = new Label(
                "SpeedFix Auto Care, Baner"
        );
        place.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );
        place.setTextFill(
                Color.web(DARK)
        );

        Label distance = new Label(
                "7.4 km from your location · open until 11 PM"
        );
        distance.setFont(
                Font.font("Arial", 13)
        );
        distance.setTextFill(
                Color.web(GREY)
        );

        VBox destinationText = new VBox(
                3,
                place,
                distance
        );

        destination.getChildren().addAll(
                location,
                destinationText
        );

        outer.getChildren().addAll(
                title,
                destination
        );

        return outer;
    }

    // ================= TRUCK CARD =================

    private VBox createTruckCard(
            String name,
            String company,
            String eta,
            String distance,
            String charge,
            String rating,
            String suitable
    ) {

        VBox card = new VBox(12);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefHeight(235);

        card.setBackground(
                bg(CARD, new CornerRadii(22))
        );

        card.setBorder(
                border(BORDER, 22, 1)
        );

        // TOP

        HBox top = new HBox(12);
        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle iconCircle = new Circle(
                22,
                Color.web("#FFF0E7")
        );

        Label truckIcon = new Label("▱");
        truckIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );
        truckIcon.setTextFill(
                Color.web(ORANGE)
        );

        StackPane icon = new StackPane(
                iconCircle,
                truckIcon
        );

        Label nameLabel = new Label(name);
        nameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );
        nameLabel.setTextFill(
                Color.web(DARK)
        );

        Label companyLabel = new Label(company);
        companyLabel.setFont(
                Font.font("Arial", 13)
        );
        companyLabel.setTextFill(
                Color.web(GREY)
        );

        VBox nameBox = new VBox(
                3,
                nameLabel,
                companyLabel
        );

        Region space = new Region();
        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Label star = new Label(
                "★ " + rating
        );
        star.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );
        star.setTextFill(
                Color.web(ORANGE)
        );

        top.getChildren().addAll(
                icon,
                nameBox,
                space,
                star
        );

        // STATS

        HBox stats = new HBox(10);

        stats.getChildren().add(
                statBox(
                        "◷",
                        eta,
                        "ETA"
                )
        );

        stats.getChildren().add(
                statBox(
                        "⌖",
                        distance,
                        "Distance"
                )
        );

        stats.getChildren().add(
                statBox(
                        "₹",
                        charge,
                        "Charge"
                )
        );

        Label suitableLabel = new Label(
                "Suitable for: " + suitable
        );
        suitableLabel.setFont(
                Font.font("Arial", 13)
        );
        suitableLabel.setTextFill(
                Color.web(GREY)
        );

        Button book = new Button(
                "Book now"
        );

        book.setMaxWidth(
                Double.MAX_VALUE
        );

        book.setPrefHeight(40);

        book.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        book.setTextFill(
                Color.WHITE
        );

        book.setBackground(
                bg(BLUE, new CornerRadii(22))
        );

        book.setCursor(
                Cursor.HAND
        );

        card.getChildren().addAll(
                top,
                stats,
                suitableLabel,
                book
        );

        return card;
    }

    private VBox statBox(
            String icon,
            String value,
            String title
    ) {

        VBox box = new VBox(3);
        box.setAlignment(
                Pos.CENTER
        );

        box.setPrefWidth(140);
        box.setPrefHeight(78);

        box.setBackground(
                bg("#F0F5F8", new CornerRadii(22))
        );

        Label iconLabel = new Label(icon);
        iconLabel.setFont(
                Font.font("Arial", 18)
        );
        iconLabel.setTextFill(
                Color.web(GREY)
        );

        Label valueLabel = new Label(value);
        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );
        valueLabel.setTextFill(
                Color.web(DARK)
        );

        Label titleLabel = new Label(title);
        titleLabel.setFont(
                Font.font("Arial", 12)
        );
        titleLabel.setTextFill(
                Color.web(GREY)
        );

        box.getChildren().addAll(
                iconLabel,
                valueLabel,
                titleLabel
        );

        return box;
    }

    // ================= SIDEBAR BUTTON =================

    private Button sideButton(
            String icon,
            String text,
            boolean selected
    ) {

        Label iconLabel = new Label(icon);
        iconLabel.setFont(
                Font.font("Arial", 16)
        );

        Label textLabel = new Label(text);
        textLabel.setFont(
                Font.font(
                        "Arial",
                        selected
                                ? FontWeight.BOLD
                                : FontWeight.NORMAL,
                        16
                )
        );

        HBox row = new HBox(
                14,
                iconLabel,
                textLabel
        );
        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Button button = new Button();
        button.setGraphic(row);
        button.setMaxWidth(
                Double.MAX_VALUE
        );
        button.setPrefHeight(47);
        button.setAlignment(
                Pos.CENTER_LEFT
        );
        button.setPadding(
                new Insets(0, 14, 0, 14)
        );
        button.setCursor(
                Cursor.HAND
        );

        if (selected) {

            button.setBackground(
                    bg(
                            BLUE,
                            new CornerRadii(25)
                    )
            );

            iconLabel.setTextFill(
                    Color.WHITE
            );
            textLabel.setTextFill(
                    Color.WHITE
            );

        } else {

            button.setBackground(
                    bg(
                            Color.TRANSPARENT,
                            new CornerRadii(25)
                    )
            );

            iconLabel.setTextFill(
                    Color.web(DARK)
            );
            textLabel.setTextFill(
                    Color.web(DARK)
            );

            button.setOnMouseEntered(e -> {
                button.setBackground(
                        bg(
                                "#EEF4FF",
                                new CornerRadii(25)
                        )
                );
            });

            button.setOnMouseExited(e -> {
                button.setBackground(
                        bg(
                                Color.TRANSPARENT,
                                new CornerRadii(25)
                        )
                );
            });
        }

        return button;
    }

    private Label section(String text) {

        Label label = new Label(text);
        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );
        label.setTextFill(
                Color.web(GREY)
        );
        label.setPadding(
                new Insets(12, 14, 5, 14)
        );

        return label;
    }

    // ================= HELPERS =================

    private Background bg(
            String color,
            CornerRadii radius
    ) {

        return new Background(
                new BackgroundFill(
                        Color.web(color),
                        radius,
                        Insets.EMPTY
                )
        );
    }

    private Background bg(
            Color color,
            CornerRadii radius
    ) {

        return new Background(
                new BackgroundFill(
                        color,
                        radius,
                        Insets.EMPTY
                )
        );
    }

    private Border border(
            String color,
            double radius,
            double width
    ) {

        return new Border(
                new BorderStroke(
                        Color.web(color),
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(radius),
                        new BorderWidths(width)
                )
        );
    }
}

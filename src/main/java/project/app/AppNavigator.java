package project.app;

import javafx.stage.Stage;

import project.ui.user.AIDiagnosisPage;
import project.ui.user.CostEstimatorPage;
import project.ui.user.DocumentsPage;
import project.ui.user.LiveMapPage;
import project.ui.user.MechanicsPage;
import project.ui.user.NotificationsPage;
import project.ui.user.ServiceHistoryPage;
import project.ui.user.SettingsPage;
import project.ui.user.SOSPage;
import project.ui.user.TowTruckPage;
import project.ui.user.UserDashboard;
import project.ui.user.VehiclePage;
import project.ui.user.WomenSafety;

public final class AppNavigator {

    private AppNavigator() {
    }

    public static void navigate(Stage dashboardStage, String pageName) {

        switch (pageName) {

            // 1. Dashboard
            case "Dashboard" ->
                    new UserDashboard().start(dashboardStage);

            // 2. Emergency SOS
            case "Emergency SOS" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Emergency SOS",
                            new SOSPage(() ->
                                    navigate(dashboardStage, "Dashboard")
                            ).getSOSScene()
                    );

            // 3. Live Map
            case "Live Map" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Live Tracking",
                            new LiveMapPage().getLiveMapScene()
                    );

            // 4. AI Diagnosis
            case "AI Diagnosis" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - AI Diagnosis",
                            new AIDiagnosisPage().getAIDiagnosisScene()
                    );

            // 5. Mechanics
            case "Mechanics" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Mechanics",
                            new MechanicsPage().getMechanicsScene()
                    );

            // 6. Tow Truck
            case "Tow Truck" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Tow Truck",
                            new TowTruckPage().getTowTruckScene()
                    );

            // 7. Cost Estimator
            case "Cost Estimator" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Cost Estimator",
                            new CostEstimatorPage().getCostEstimatorScene()
                    );

            // 8. My Vehicles
            case "My Vehicles" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - My Vehicles",
                            new VehiclePage().getMyVehiclesScene()
                    );

            // 9. Service History
            case "Service History" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Service History",
                            new ServiceHistoryPage().getServiceHistoryScene()
                    );

            // 10. Documents
            case "Documents" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Documents",
                            new DocumentsPage().getDocumentsScene()
                    );

            // 11. Women Safety
            case "Women Safety" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Women Safety",
                            new WomenSafety().getWomenSafetyScene()
                    );

            // 12. Notifications
            case "Notifications" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Notifications",
                            new NotificationsPage().getNotificationsScene()
                    );

            // 13. Settings
            case "Settings" ->
                    show(
                            dashboardStage,
                            "RoadGuardian - Settings",
                            new SettingsPage().getSettingsScene()
                    );

            default ->
                    throw new IllegalArgumentException(
                            "Unknown page: " + pageName
                    );
        }
    }

    private static void show(
            Stage dashboardStage,
            String title,
            javafx.scene.Scene scene
    ) {
        dashboardStage.setTitle(title);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }
}
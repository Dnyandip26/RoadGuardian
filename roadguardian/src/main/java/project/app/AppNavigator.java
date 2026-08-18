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

/** Switches pages inside the application's existing window. */
public final class AppNavigator {

    private AppNavigator() {
    }

    public static void navigate(Stage stage, String pageName) {
        switch (pageName) {
            case "Dashboard" -> new UserDashboard().start(stage);
            case "Emergency SOS" -> show(stage, "RoadGuardian - Emergency SOS",
                    new SOSPage(() -> navigate(stage, "Dashboard")).getSOSScene());
            case "Live Map" -> show(stage, "RoadGuardian - Live Tracking",
                    new LiveMapPage().getLiveMapScene());
            case "AI Diagnosis" -> show(stage, "RoadGuardian - AI Diagnosis",
                    new AIDiagnosisPage().getAIDiagnosisScene());
            case "Mechanics" -> show(stage, "RoadGuardian - Mechanics",
                    new MechanicsPage().getMechanicsScene());
            case "Tow Truck" -> show(stage, "RoadGuardian - Tow Truck",
                    new TowTruckPage().getTowTruckScene());
            case "Cost Estimator" -> show(stage, "RoadGuardian - Cost Estimator",
                    new CostEstimatorPage().getCostEstimatorScene());
            case "My Vehicles" -> new VehiclePage().start(stage);
            case "Service History" -> new ServiceHistoryPage().start(stage);
            case "Documents" -> new DocumentsPage().start(stage);
            case "Women Safety" -> new WomenSafety().start(stage);
            case "Notifications" -> new NotificationsPage().start(stage);
            case "Settings" -> new SettingsPage().start(stage);
            default -> throw new IllegalArgumentException("Unknown page: " + pageName);
        }
    }

    private static void show(Stage stage, String title, javafx.scene.Scene scene) {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}

package project.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.ui.mechanic.MechanicDashboard;

public class RoadGuardianApp extends Application {

    @Override
    public void start(Stage stage) {

        MechanicDashboard dashboard =
                new MechanicDashboard();

        Scene scene =
                dashboard.getDashboardScene();

        stage.setTitle(
                "RoadGuardian - Mechanic Dashboard"
        );

        stage.setScene(scene);

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
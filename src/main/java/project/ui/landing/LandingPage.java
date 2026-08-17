package project.ui.landing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import project.ui.mechanic.MechanicDashboard;

public class LandingPage extends Application {

    public static Stage appMainStage;
    private Scene landingPageScene;
    
    public void start(Stage stage) {
        appMainStage = stage;

        BorderPane root = new BorderPane();
        Button mechanicBtn = new Button("Go to Mechanic Page.");
        mechanicBtn.setOnAction(e->{
            MechanicDashboard mechanicDashboard = new MechanicDashboard();
            appMainStage.setScene(mechanicDashboard.getDashboardScene());
        });

        root.setCenter(mechanicBtn);

        landingPageScene = new Scene(root, LandingPage.appMainStage.getWidth(), LandingPage.appMainStage.getHeight());

        appMainStage.setScene(landingPageScene);
        appMainStage.setMaximized(true);
        
        appMainStage.show();
    }
}

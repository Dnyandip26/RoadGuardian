package project;
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.ui.admin.AdminDashboard;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        AdminDashboard dashboard =
                new AdminDashboard(stage);

        Scene scene =
                new Scene(
                        dashboard.getView(),
                        1450,
                        850
                );

        stage.setTitle(
                "RoadGuardian - Admin Panel"
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

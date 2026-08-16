package project;

import javafx.application.Application;
import javafx.stage.Stage;
import project.ui.user.UserDashboard;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        UserDashboard userDashboard =
                new UserDashboard();

        stage.setTitle("RoadGuardian - Customer Dashboard");

        stage.setScene(
                userDashboard.getDashboardScene()
        );

        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
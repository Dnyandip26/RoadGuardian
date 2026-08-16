package project;

import javafx.application.Application;
import javafx.stage.Stage;
import project.ui.user.UserDashboard;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        new UserDashboard().start(stage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

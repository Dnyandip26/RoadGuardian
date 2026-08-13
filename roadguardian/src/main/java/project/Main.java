package project;
// import project.view.landing.LoginPage;
import project.view.landing.LandingPage;
// import project.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        LandingPage landingPage = new LandingPage(primaryStage);

        Scene scene = new Scene(landingPage.getView());

        primaryStage.setTitle("RoadGuardian");
        primaryStage.setScene(scene);
        // primaryStage.setResizable(true);
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(800);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
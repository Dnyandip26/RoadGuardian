package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.ui.landing.LandingPage;
import project.ui.mechanic.MechanicDashboard;

public class Main {

    public static void main(String[] args) {
        Application.launch(LandingPage.class, args);
    }
}
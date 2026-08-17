package project;

import javafx.application.Application;
import project.ui.landing.LandingPage;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello world");
        Application.launch(LandingPage.class, args);
    }
}
package project.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import project.view.landing.LandingPage;
import project.view.landing.LoginPage;
public class RegisterController {

    private final Stage stage;

    public RegisterController(Stage stage) {

        this.stage = stage;
    }

    // =====================================================
    // REGISTER
    // =====================================================

    public void register(
            String name,
            String email,
            String phone,
            String password,
            String confirmPassword
    ) {

        System.out.println(
                "Create Account clicked"
        );

        System.out.println(
                "Name: " + name
        );

        System.out.println(
                "Email: " + email
        );

        System.out.println(
                "Phone: " + phone
        );

        /*
         * Firebase registration later add karenge.
         */
    }

    // =====================================================
    // OPEN LOGIN
    // =====================================================

    public void openLoginPage() {

        LoginPage loginPage =
                new LoginPage(stage);

        Scene scene =
                new Scene(
                        loginPage.getView(),
                        1400,
                        800
                );

        stage.setScene(scene);

        stage.setTitle(
                "RoadGuardian - Login"
        );

        stage.show();
    }

    // =====================================================
    // BACK TO LANDING
    // =====================================================

    public void goBack() {

        LandingPage landingPage =
                new LandingPage(stage);

        Scene scene =
                new Scene(
                        landingPage.getView(),
                        1400,
                        800
                );

        stage.setScene(scene);

        stage.setTitle(
                "RoadGuardian"
        );

        stage.show();
    }
}
package project.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

import project.view.landing.LandingPage;
import project.view.landing.RegisterPage;

// User Dashboard
import project.view.user.UserDashboard;

// Mechanic Dashboard
import project.view.mechanic.MechanicDashboard;


public class LoginController {

    private final Stage stage;


    public LoginController(Stage stage) {

        this.stage = stage;
    }


    // =====================================================
    // LOGIN
    // =====================================================

    public void login(
            String email,
            String password,
            String role
    ) {

        System.out.println(
                "Login button clicked"
        );

        System.out.println(
                "Email: " + email
        );

        System.out.println(
                "Role: " + role
        );


        // =================================================
        // BASIC VALIDATION
        // =================================================

        if (email == null ||
                email.trim().isEmpty()) {

            System.out.println(
                    "Please enter email."
            );

            return;
        }


        if (password == null ||
                password.trim().isEmpty()) {

            System.out.println(
                    "Please enter password."
            );

            return;
        }


        // =================================================
        // ROLE BASED LOGIN
        // =================================================

        if (role == null ||
                role.trim().isEmpty()) {

            System.out.println(
                    "Please select login type."
            );

            return;
        }


        // =================================================
        // USER LOGIN
        // =================================================

        if (role.equals("User")) {

            System.out.println(
                    "Opening User Dashboard..."
            );

            openUserDashboard();

        }


        // =================================================
        // MECHANIC LOGIN
        // =================================================

        else if (role.equals("Mechanic")) {

            System.out.println(
                    "Opening Mechanic Dashboard..."
            );

            openMechanicDashboard();

        }


        // =================================================
        // INVALID ROLE
        // =================================================

        else {

            System.out.println(
                    "Invalid role selected."
            );
        }
    }


    // =====================================================
    // OPEN USER DASHBOARD
    // =====================================================

    private void openUserDashboard() {

        // UserDashboard userDashboard =
        //         new UserDashboard();


        // // Scene scene =
        // //         new Scene(
        // //                 // userDashboard.getDashboardScene()
        // //         );


        // stage.setScene(
        //         scene
        // );


        stage.setTitle(
                "RoadGuardian - User Dashboard"
        );


        stage.setMaximized(true);

        stage.show();
    }


    // =====================================================
    // OPEN MECHANIC DASHBOARD
    // =====================================================

    private void openMechanicDashboard() {

        MechanicDashboard mechanicDashboard =
                new MechanicDashboard();


        // Scene scene =
        //         new Scene(
        //                 mechanicDashboard.getDashboardScene()
        //         );


        // stage.setScene(
        //         scene
        // );


        stage.setTitle(
                "RoadGuardian - Mechanic Dashboard"
        );


        stage.setMaximized(true);

        stage.show();
    }


    // =====================================================
    // OPEN USER REGISTER PAGE
    // =====================================================

    public void openRegisterPage() {

        RegisterPage registerPage =
                new RegisterPage(stage);


        Scene scene =
                new Scene(
                        registerPage.getView(),
                        1400,
                        800
                );


        stage.setScene(
                scene
        );


        stage.setTitle(
                "RoadGuardian - Create Account"
        );


        stage.show();
    }


    // =====================================================
    // FORGOT PASSWORD
    // =====================================================

    public void openForgotPasswordPage() {

        System.out.println(
                "Forgot Password clicked"
        );

        // Firebase ke time implement karenge.
    }


    // =====================================================
    // GO BACK TO LANDING PAGE
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


        stage.setScene(
                scene
        );


        stage.setTitle(
                "RoadGuardian"
        );


        stage.show();
    }
}
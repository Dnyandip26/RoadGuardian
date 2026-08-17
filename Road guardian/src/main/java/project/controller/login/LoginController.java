package project.controller.login;

import javafx.scene.Scene;
import javafx.stage.Stage;

import project.firebase.FirebaseConfig;
import project.ui.admin.DashBoard.AdminDashboard;
import project.ui.landing.LandingPage;
import project.ui.landing.RegisterPage;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import project.ui.mechanic.MechanicDashboard;
import project.ui.user.UserDashboard;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class LoginController {

    private final Stage stage;

    // =====================================================
    // FIREBASE WEB API KEY
    // =====================================================

    private static final String FIREBASE_API_KEY =
            "AIzaSyCuwUp1_Yjbm-wF5CwPgBlpM5HM5WzqwEA";

    // =====================================================
    // FIREBASE LOGIN URL
    // =====================================================

    private static final String LOGIN_URL =
            "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                    + FIREBASE_API_KEY;

    // =====================================================
    // HTTP CLIENT
    // =====================================================

    private final HttpClient httpClient =
            HttpClient.newHttpClient();

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public LoginController(Stage stage) {

        this.stage = stage;
    }

    // =====================================================
    // LOGIN
    // =====================================================

    public void login(
            String email,
            String password
    ) {

        System.out.println("Login button clicked");

        // =================================================
        // VALIDATION
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

        email = email.trim();

        try {

            // =================================================
            // FIREBASE AUTH REQUEST
            // =================================================

            String requestBody =
                    "{"
                            + "\"email\":\""
                            + escapeJson(email)
                            + "\","
                            + "\"password\":\""
                            + escapeJson(password)
                            + "\","
                            + "\"returnSecureToken\":true"
                            + "}";

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            LOGIN_URL
                                    )
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            requestBody,
                                            StandardCharsets.UTF_8
                                    )
                            )
                            .build();

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "Firebase Login Response: "
                            + response.statusCode()
            );

            // =================================================
            // LOGIN SUCCESS
            // =================================================

            if (response.statusCode() == 200) {

                String responseBody =
                        response.body();

                // Firebase UID
                String uid =
                        extractJsonValue(
                                responseBody,
                                "localId"
                        );

                if (uid == null ||
                        uid.isEmpty()) {

                    System.out.println(
                            "Firebase UID not found."
                    );

                    return;
                }

                System.out.println(
                        "Firebase Authentication Successful."
                );

                System.out.println(
                        "UID: " + uid
                );

                // =================================================
                // CHECK ROLE FROM FIRESTORE
                // =================================================

                String role =
                        getUserRole(uid);

                if (role == null) {

                    System.out.println(
                            "User role not found."
                    );

                    return;
                }

                System.out.println(
                        "Role from Firestore: "
                                + role
                );

                // =================================================
                // ONLY ADMIN LOGIN
                // =================================================

                // =================================================
// ROLE BASED LOGIN
// =================================================

                if (role.equalsIgnoreCase("admin")) {

                    System.out.println(
                            "Admin account detected."
                    );

                    System.out.println(
                            "Opening Admin Dashboard..."
                    );

                    openAdminDashboard();

                }

                else if (role.equalsIgnoreCase("mechanic")) {

                    System.out.println(
                            "Mechanic account detected."
                    );

                    System.out.println(
                            "Opening Mechanic Dashboard..."
                    );

                    openMechanicDashboard();

                }

                else if (role.equalsIgnoreCase("user")) {

                    System.out.println(
                            "Customer account detected."
                    );

                    System.out.println(
                            "Opening Customer Dashboard..."
                    );
//
//                    openUserDashboard();

                }

                else {

                    System.out.println(
                            "Access denied."
                    );

                    System.out.println(
                            "Invalid account role: "
                                    + role
                    );
                }

            } else {

                // =================================================
                // LOGIN FAILED
                // =================================================

                String error =
                        extractFirebaseError(
                                response.body()
                        );

                System.out.println(
                        "Login failed: "
                                + error
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Login Error: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }
    }
    // =====================================================
// OPEN CUSTOMER DASHBOARD
// =====================================================

//    private void openUserDashboard() {
//
//        System.out.println(
//                "Opening Customer Dashboard..."
//        );
//
//        UserDashboard userDashboard =
//                new UserDashboard();
//
//        Scene scene =
//                userDashboard.getDashboardScene();
//
//        stage.setScene(
//                scene
//        );
//
//        stage.setTitle(
//                "RoadGuardian - Customer Dashboard"
//        );
//
//        stage.setMaximized(
//                true
//        );
//
//        stage.show();
//    }
    // =====================================================
// OPEN MECHANIC DASHBOARD
// =====================================================

    private void openMechanicDashboard() {

        System.out.println(
                "Opening Mechanic Dashboard..."
        );

        MechanicDashboard mechanicDashboard =
                new MechanicDashboard();

        Scene scene =
                mechanicDashboard.getDashboardScene();

        stage.setScene(
                scene
        );

        stage.setTitle(
                "RoadGuardian - Mechanic Dashboard"
        );

        stage.setMaximized(
                true
        );

        stage.show();
    }

    // =====================================================
    // GET USER ROLE FROM FIRESTORE
    // =====================================================

    private String getUserRole(
            String uid
    ) {

        try {

            Firestore firestore =
                    FirebaseConfig.getFirestore();

            DocumentSnapshot document =
                    firestore
                            .collection("users")
                            .document(uid)
                            .get()
                            .get();

            // -------------------------------------------------
            // USER DOCUMENT NOT FOUND
            // -------------------------------------------------

            if (!document.exists()) {

                System.out.println(
                        "User document does not exist."
                );

                return null;
            }

            // -------------------------------------------------
            // GET ROLE
            // -------------------------------------------------

            String role =
                    document.getString("role");

            if (role == null) {

                return null;
            }

            return role.trim();

        } catch (
                IOException |
                InterruptedException |
                ExecutionException e
        ) {

            System.out.println(
                    "Firestore Error: "
                            + e.getMessage()
            );

            return null;
        }
    }

    // =====================================================
    // OPEN ADMIN DASHBOARD
    // =====================================================

    private void openAdminDashboard() {

        System.out.println(
                "Opening Admin Dashboard..."
        );

        // =================================================
        // YOUR EXACT ADMIN DASHBOARD
        // =================================================

        AdminDashboard adminDashboard =
                new AdminDashboard(stage);

        // =================================================
        // YOUR AdminDashboard HAS getView()
        // =================================================

        Scene scene =
                new Scene(
                        adminDashboard.getView()
                );

        // =================================================
        // SET SCENE
        // =================================================

        stage.setScene(
                scene
        );

        stage.setTitle(
                "RoadGuardian - Admin Panel"
        );

        stage.setMaximized(
                true
        );

        stage.show();
    }

    // =====================================================
    // OPEN REGISTER PAGE
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

        System.out.println(
                "Firebase password reset will be added later."
        );
    }

    // =====================================================
    // BACK TO LANDING PAGE
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

    // =====================================================
    // JSON ESCAPE
    // =====================================================

    private String escapeJson(
            String value
    ) {

        return value
                .replace(
                        "\\",
                        "\\\\"
                )
                .replace(
                        "\"",
                        "\\\""
                );
    }

    // =====================================================
    // EXTRACT JSON VALUE
    // =====================================================

    private String extractJsonValue(
            String json,
            String key
    ) {

        if (json == null || json.isEmpty()) {
            return null;
        }

        String search =
                "\"" + key + "\"";

        int keyPosition =
                json.indexOf(search);

        if (keyPosition == -1) {
            return null;
        }

        int colonPosition =
                json.indexOf(
                        ":",
                        keyPosition + search.length()
                );

        if (colonPosition == -1) {
            return null;
        }

        int firstQuote =
                json.indexOf(
                        "\"",
                        colonPosition + 1
                );

        if (firstQuote == -1) {
            return null;
        }

        int secondQuote =
                json.indexOf(
                        "\"",
                        firstQuote + 1
                );

        if (secondQuote == -1) {
            return null;
        }

        return json.substring(
                firstQuote + 1,
                secondQuote
        );
    }

    // =====================================================
    // FIREBASE ERROR
    // =====================================================

    private String extractFirebaseError(
            String json
    ) {

        String message =
                extractJsonValue(
                        json,
                        "message"
                );

        if (message == null) {

            return "Invalid email or password.";
        }

        switch (message) {

            case "INVALID_LOGIN_CREDENTIALS":
                return "Invalid email or password.";

            case "EMAIL_NOT_FOUND":
                return "Email address not found.";

            case "INVALID_PASSWORD":
                return "Incorrect password.";

            case "USER_DISABLED":
                return "This account has been disabled.";

            case "TOO_MANY_ATTEMPTS_TRY_LATER":
                return "Too many login attempts. Try again later.";

            default:
                return message;
        }
    }
}
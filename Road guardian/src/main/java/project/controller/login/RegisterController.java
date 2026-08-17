package project.controller.login;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import project.firebase.FirebaseConfig;
import project.ui.landing.LandingPage;
import project.ui.landing.LoginPage;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import java.util.HashMap;
import java.util.Map;

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
            String role,
            String password,
            String confirmPassword
    ) {

        System.out.println(
                "Create Account clicked"
        );

        // =================================================
        // CLEAN INPUT
        // =================================================

        name =
                name == null
                        ? ""
                        : name.trim();

        email =
                email == null
                        ? ""
                        : email.trim();

        phone =
                phone == null
                        ? ""
                        : phone.trim();

        role =
                role == null
                        ? ""
                        : role.trim();

        password =
                password == null
                        ? ""
                        : password;

        confirmPassword =
                confirmPassword == null
                        ? ""
                        : confirmPassword;

        // =================================================
        // VALIDATION
        // =================================================

        if (name.isEmpty()) {

            showError(
                    "Registration Error",
                    "Please enter your full name."
            );

            return;
        }

        if (email.isEmpty()) {

            showError(
                    "Registration Error",
                    "Please enter your email address."
            );

            return;
        }

        if (!isValidEmail(email)) {

            showError(
                    "Registration Error",
                    "Please enter a valid email address."
            );

            return;
        }

        if (phone.isEmpty()) {

            showError(
                    "Registration Error",
                    "Please enter your phone number."
            );

            return;
        }

        // =================================================
        // ROLE VALIDATION
        // =================================================

        if (role.isEmpty()) {

            showError(
                    "Registration Error",
                    "Please select an account type."
            );

            return;
        }

        // =================================================
        // CUSTOMER / MECHANIC / ADMIN
        // =================================================

        if (!role.equals("Customer")
                && !role.equals("Mechanic")
                && !role.equals("Admin")) {

            showError(
                    "Registration Error",
                    "Invalid account type selected."
            );

            return;
        }

        // =================================================
        // PASSWORD
        // =================================================

        if (password.isEmpty()) {

            showError(
                    "Registration Error",
                    "Please enter a password."
            );

            return;
        }

        if (password.length() < 6) {

            showError(
                    "Registration Error",
                    "Password must contain at least 6 characters."
            );

            return;
        }

        if (!password.equals(confirmPassword)) {

            showError(
                    "Registration Error",
                    "Password and Confirm Password do not match."
            );

            return;
        }

        // =================================================
        // FINAL VALUES
        // =================================================

        final String finalName =
                name;

        final String finalEmail =
                email;

        final String finalPhone =
                phone;

        // Convert UI role to Firestore role
        //
        // Customer -> user
        // Mechanic -> mechanic
        // Admin    -> admin

        final String finalRole;

        if (role.equals("Admin")) {

            finalRole =
                    "admin";

        } else if (role.equals("Mechanic")) {

            finalRole =
                    "mechanic";

        } else {

            finalRole =
                    "user";
        }

        final String finalPassword =
                password;

        System.out.println(
                "Selected Role: "
                        + finalRole
        );

        // =================================================
        // FIREBASE REGISTRATION
        // =================================================

        Task<Void> registrationTask =
                new Task<>() {

                    @Override
                    protected Void call()
                            throws Exception {

                        // =================================
                        // FIREBASE
                        // =================================

                        Firestore firestore =
                                FirebaseConfig.getFirestore();

                        // =================================
                        // CREATE AUTH USER
                        // =================================

                        UserRecord.CreateRequest request =
                                new UserRecord.CreateRequest()
                                        .setEmail(
                                                finalEmail
                                        )
                                        .setPassword(
                                                finalPassword
                                        )
                                        .setDisplayName(
                                                finalName
                                        )
                                        .setPhoneNumber(
                                                finalPhone
                                        )
                                        .setEmailVerified(
                                                false
                                        )
                                        .setDisabled(
                                                false
                                        );

                        UserRecord userRecord;

                        try {

                            userRecord =
                                    FirebaseAuth
                                            .getInstance()
                                            .createUser(
                                                    request
                                            );

                        } catch (
                                FirebaseAuthException e
                        ) {

                            throw new Exception(
                                    getFirebaseAuthMessage(e),
                                    e
                            );
                        }

                        // =================================
                        // UID
                        // =================================

                        String uid =
                                userRecord.getUid();

                        System.out.println(
                                "Firebase User Created"
                        );

                        System.out.println(
                                "UID: "
                                        + uid
                        );

                        // =================================
                        // USER DATA
                        // =================================

                        Map<String, Object> userData =
                                new HashMap<>();

                        userData.put(
                                "name",
                                finalName
                        );

                        userData.put(
                                "email",
                                finalEmail
                        );

                        userData.put(
                                "phone",
                                finalPhone
                        );

                        userData.put(
                                "role",
                                finalRole
                        );

                        // =================================
                        // FIRESTORE
                        // users/{UID}
                        // =================================

                        try {

                            firestore
                                    .collection(
                                            "users"
                                    )
                                    .document(
                                            uid
                                    )
                                    .set(
                                            userData
                                    )
                                    .get();

                        } catch (
                                Exception firestoreError
                        ) {

                            // =============================
                            // ROLLBACK AUTH USER
                            // =============================

                            try {

                                FirebaseAuth
                                        .getInstance()
                                        .deleteUser(
                                                uid
                                        );

                            } catch (
                                    Exception rollbackError
                            ) {

                                System.out.println(
                                        "Rollback failed: "
                                                + rollbackError
                                                .getMessage()
                                );
                            }

                            throw new Exception(
                                    "Account could not be saved in Firestore.",
                                    firestoreError
                            );
                        }

                        // =================================
                        // SUCCESS LOG
                        // =================================

                        System.out.println(
                                "User document created:"
                        );

                        System.out.println(
                                "users/"
                                        + uid
                        );

                        System.out.println(
                                "Saved Role: "
                                        + finalRole
                        );

                        return null;
                    }
                };

        // =================================================
        // SUCCESS
        // =================================================

        registrationTask.setOnSucceeded(
                event -> {

                    showSuccess(
                            "Account Created",
                            "Your RoadGuardian account has been created successfully."
                    );

                    System.out.println(
                            "Registration successful."
                    );

                    openLoginPage();
                }
        );

        // =================================================
        // FAILURE
        // =================================================

        registrationTask.setOnFailed(
                event -> {

                    Throwable exception =
                            registrationTask
                                    .getException();

                    String message =
                            exception != null
                                    ? exception.getMessage()
                                    : "Registration failed.";

                    System.out.println(
                            "Registration failed:"
                    );

                    if (exception != null) {

                        exception.printStackTrace();
                    }

                    showError(
                            "Registration Failed",
                            message
                    );
                }
        );

        // =================================================
        // BACKGROUND THREAD
        // =================================================

        Thread registrationThread =
                new Thread(
                        registrationTask
                );

        registrationThread.setDaemon(
                true
        );

        registrationThread.start();
    }

    // =====================================================
    // FIREBASE AUTH ERROR
    // =====================================================

    private String getFirebaseAuthMessage(
            FirebaseAuthException exception
    ) {

        String message =
                exception.getMessage();

        if (message == null
                || message.isBlank()) {

            return "Unable to create account.";
        }

        String lower =
                message.toLowerCase();

        if (lower.contains("email")) {

            if (lower.contains("already")) {

                return "This email address is already registered.";
            }

            if (lower.contains("invalid")) {

                return "Please enter a valid email address.";
            }
        }

        if (lower.contains("password")) {

            return "Password must contain at least 6 characters.";
        }

        if (lower.contains("phone")) {

            return "Please enter a valid phone number.";
        }

        return message;
    }

    // =====================================================
    // EMAIL VALIDATION
    // =====================================================

    private boolean isValidEmail(
            String email
    ) {

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
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

        stage.setScene(
                scene
        );

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

        stage.setScene(
                scene
        );

        stage.setTitle(
                "RoadGuardian"
        );

        stage.show();
    }

    // =====================================================
    // ERROR ALERT
    // =====================================================

    private void showError(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

    // =====================================================
    // SUCCESS ALERT
    // =====================================================

    private void showSuccess(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}
package project.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {

    private static Firestore firestore;

    private FirebaseConfig() {
    }

    public static synchronized Firestore getFirestore()
            throws IOException {

        if (firestore != null) {
            return firestore;
        }

        if (FirebaseApp.getApps().isEmpty()) {

            FileInputStream serviceAccount =
                    new FileInputStream(
                            "guardian.json"
                    );

            FirebaseOptions options =
                    FirebaseOptions.builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(
                                            serviceAccount
                                    )
                            )
                            .build();

            FirebaseApp.initializeApp(
                    options
            );
        }

        firestore =
                FirestoreClient.getFirestore();

        return firestore;
    }
}
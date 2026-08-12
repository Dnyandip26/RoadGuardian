package project.dao.admin;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardDAO {

    private final Firestore firestore;

    public DashboardDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    /*
     * Get total number of documents
     */
    private int getCollectionCount(
            String collectionName
    ) throws Exception {

        CollectionReference collection =
                firestore.collection(collectionName);

        ApiFuture<QuerySnapshot> future =
                collection.get();

        QuerySnapshot snapshot =
                future.get();

        return snapshot.size();
    }

    /*
     * Get count based on status field
     */
    private int getStatusCount(
            String collectionName,
            String status
    ) throws Exception {

        CollectionReference collection =
                firestore.collection(collectionName);

        ApiFuture<QuerySnapshot> future =
                collection
                        .whereEqualTo("status", status)
                        .get();

        QuerySnapshot snapshot =
                future.get();

        return snapshot.size();
    }

    /*
     * Dashboard statistics
     */
    public Map<String, Integer> getStatistics()
            throws Exception {

        Map<String, Integer> statistics =
                new HashMap<>();

        // Customers
        int customers =
                getCollectionCount(
                        "customers"
                );

        int activeCustomers =
                getStatusCount(
                        "customers",
                        "Active"
                );

        int inactiveCustomers =
                getStatusCount(
                        "customers",
                        "Inactive"
                );

        // Mechanics
        int mechanics =
                getCollectionCount(
                        "mechanics"
                );

        // Vehicles
        int vehicles =
                getCollectionCount(
                        "vehicles"
                );

        // Service Requests
        int serviceRequests =
                getCollectionCount(
                        "serviceRequests"
                );

        int pendingRequests =
                getStatusCount(
                        "serviceRequests",
                        "Pending"
                );

        int inProgressRequests =
                getStatusCount(
                        "serviceRequests",
                        "In Progress"
                );

        int completedRequests =
                getStatusCount(
                        "serviceRequests",
                        "Completed"
                );

        int cancelledRequests =
                getStatusCount(
                        "serviceRequests",
                        "Cancelled"
                );

        // SOS
        int sosRequests =
                getCollectionCount(
                        "sosRequests"
                );

        int activeSOS =
                getStatusCount(
                        "sosRequests",
                        "Active"
                );

        statistics.put(
                "customers",
                customers
        );

        statistics.put(
                "activeCustomers",
                activeCustomers
        );

        statistics.put(
                "inactiveCustomers",
                inactiveCustomers
        );

        statistics.put(
                "mechanics",
                mechanics
        );

        statistics.put(
                "vehicles",
                vehicles
        );

        statistics.put(
                "serviceRequests",
                serviceRequests
        );

        statistics.put(
                "pendingRequests",
                pendingRequests
        );

        statistics.put(
                "inProgressRequests",
                inProgressRequests
        );

        statistics.put(
                "completedRequests",
                completedRequests
        );

        statistics.put(
                "cancelledRequests",
                cancelledRequests
        );

        statistics.put(
                "sosRequests",
                sosRequests
        );

        statistics.put(
                "activeSOS",
                activeSOS
        );

        return statistics;
    }
}
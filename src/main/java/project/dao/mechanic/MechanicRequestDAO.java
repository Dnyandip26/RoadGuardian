package project.dao.mechanic;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import project.model.ServiceRequest;

import java.util.ArrayList;
import java.util.List;

public class MechanicRequestDAO {

    private final Firestore firestore;

    private static final String COLLECTION =
            "serviceRequests";

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public MechanicRequestDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    // =====================================================
    // GET PENDING REQUESTS
    // =====================================================

    public List<ServiceRequest> getPendingRequests()
            throws Exception {

        List<ServiceRequest> requests =
                new ArrayList<>();

        ApiFuture<QuerySnapshot> future =
                firestore
                        .collection(COLLECTION)
                        .whereEqualTo(
                                "status",
                                "Pending"
                        )
                        .get();

        QuerySnapshot snapshot =
                future.get();
                System.out.println(
        "========== FIREBASE DEBUG =========="
    );

    System.out.println(
            "COLLECTION = " +
            COLLECTION
    );

    System.out.println(
            "DOCUMENT COUNT = " +
            snapshot.size()
    );

    for (
            DocumentSnapshot document :
            snapshot.getDocuments()
    ) {

        System.out.println(
                "DOCUMENT ID = " +
                document.getId()
        );

        System.out.println(
                "DOCUMENT DATA = " +
                document.getData()
        );
    }

    System.out.println(
            "===================================="
    );

        for (
                DocumentSnapshot document :
                snapshot.getDocuments()
        ) {

            ServiceRequest request =
                    document.toObject(
                            ServiceRequest.class
                    );

            if (request != null) {

                if (
                        request.getRequestId() == null ||
                        request.getRequestId().isBlank()
                ) {

                    request.setRequestId(
                            document.getId()
                    );
                }

                requests.add(request);
            }
        }

        return requests;
    }
        public List<ServiceRequest> getAllRequests()
            throws Exception {

        List<ServiceRequest> requests =
                new ArrayList<>();

        ApiFuture<QuerySnapshot> future =
                firestore
                        .collection(COLLECTION)
                        .get();

        QuerySnapshot snapshot =
                future.get();

        for (
                DocumentSnapshot document :
                snapshot.getDocuments()
        ) {

            ServiceRequest request =
                    document.toObject(
                            ServiceRequest.class
                    );

            if (request != null) {

                if (
                        request.getRequestId() == null ||
                        request.getRequestId().isBlank()
                ) {

                    request.setRequestId(
                            document.getId()
                    );
                }

                requests.add(request);
            }
        }

        return requests;
    }

    // =====================================================
    // GET REQUESTS BY MECHANIC
    // =====================================================

    public List<ServiceRequest> getRequestsByMechanicId(
            String mechanicId
    ) throws Exception {

        List<ServiceRequest> requests =
                new ArrayList<>();

        if (
                mechanicId == null ||
                mechanicId.isBlank()
        ) {

            return requests;
        }

        ApiFuture<QuerySnapshot> future =
                firestore
                        .collection(COLLECTION)
                        .whereEqualTo(
                                "mechanicId",
                                mechanicId
                        )
                        .get();

        QuerySnapshot snapshot =
                future.get();

        for (
                DocumentSnapshot document :
                snapshot.getDocuments()
        ) {

            ServiceRequest request =
                    document.toObject(
                            ServiceRequest.class
                    );

            if (request != null) {

                if (
                        request.getRequestId() == null ||
                        request.getRequestId().isBlank()
                ) {

                    request.setRequestId(
                            document.getId()
                    );
                }

                requests.add(request);
            }
        }

        return requests;
    }
    public boolean acceptRequest(String requestId,String mechanicId,String mechanicName) throws Exception {

    if (
            requestId == null ||
            requestId.isBlank() ||
            mechanicId == null ||
            mechanicId.isBlank()
    ) {
        return false;
    }

    firestore
            .collection(COLLECTION)
            .document(requestId)
            .update(
                    "mechanicId",
                    mechanicId,
                    "mechanicName",
                    mechanicName,
                    "status",
                    "Accepted"
            )
            .get();

    return true;
}
    public boolean updateStatus(
            String requestId,
            String status
    ) throws Exception {

    if (
            requestId == null ||
            requestId.isBlank() ||
            status == null ||
            status.isBlank()
    ) {
        return false;
    }

    firestore
            .collection(COLLECTION)
            .document(requestId)
            .update(
                    "status",
                    status
            )
            .get();

    return true;
}
        // =====================================================
        // COMPLETE SERVICE JOB
        // =====================================================

        public boolean completeJob(
                String requestId,
                String diagnosis,
                String repairDetails,
                double partsCost,
                double labourCost,
                double totalAmount,
                String completedDate
        ) throws Exception {

        if (
                requestId == null ||
                requestId.isBlank()
        ) {
                return false;
        }

        firestore
                .collection(COLLECTION)
                .document(requestId)
                .update(
                        "diagnosis",
                        diagnosis == null ? "" : diagnosis,

                        "repairDetails",
                        repairDetails == null ? "" : repairDetails,

                        "partsCost",
                        partsCost,

                        "labourCost",
                        labourCost,

                        "totalAmount",
                        totalAmount,

                        "completedDate",
                        completedDate,

                        "status",
                        "Completed"
                )
                .get();

        return true;
}
        public boolean completeRequest(
                String requestId,
                String diagnosis,
                String repairDetails,
                double partsCost,
                double labourCost,
                double totalAmount
        ) throws Exception {

        if (
                requestId == null ||
                requestId.isBlank()
        ) {
                return false;
        }

        firestore
                .collection(COLLECTION)
                .document(requestId)
                .update(
                        "diagnosis",
                        diagnosis,
                        "repairDetails",
                        repairDetails,
                        "partsCost",
                        partsCost,
                        "labourCost",
                        labourCost,
                        "totalAmount",
                        totalAmount,
                        "status",
                        "Completed",
                        "completedDate",
                        String.valueOf(
                                System.currentTimeMillis()
                        )
                )
                .get();

        return true;
        }
}

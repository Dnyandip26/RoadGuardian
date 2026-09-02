package project.dao.mechanic;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import project.model.ServiceRequest;

public class ActiveJobDAO {

    private final Firestore firestore;

    private static final String COLLECTION =
            "serviceRequests";

    public ActiveJobDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    // Get active job
    public ServiceRequest getActiveJob(
            String mechanicId
    ) throws Exception {

        if (
                mechanicId == null ||
                mechanicId.isBlank()
        ) {
            return null;
        }

        ApiFuture<QuerySnapshot> future =
                firestore
                        .collection(COLLECTION)
                        .whereEqualTo(
                                "mechanicId",
                                mechanicId
                        )
                        .whereEqualTo(
                                "status",
                                "In Progress"
                        )
                        .limit(1)
                        .get();

        QuerySnapshot snapshot =
                future.get();

        if (snapshot.isEmpty()) {
            return null;
        }

        DocumentSnapshot document =
                snapshot.getDocuments().get(0);

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
        }

        return request;
    }

    // Start repair
    public boolean startRepair(
            String requestId
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
                        "status",
                        "In Progress"
                )
                .get();

        return true;
    }

    // Complete job
    public boolean completeJob(
            String requestId,
            String diagnosis,
            String repairDetails,
            double partsCost,
            double labourCost,
            double totalAmount,
            String invoiceId,
            String paymentStatus
    ) throws Exception {

        if (
                requestId == null ||
                requestId.isBlank()
        ) {
            return false;
        }

        if (partsCost < 0) {
            return false;
        }

        if (labourCost < 0) {
            return false;
        }

        if (totalAmount < 0) {
            return false;
        }

        if (
                diagnosis == null ||
                diagnosis.isBlank()
        ) {
            return false;
        }

        if (
                repairDetails == null ||
                repairDetails.isBlank()
        ) {
            return false;
        }

        if (
                invoiceId == null ||
                invoiceId.isBlank()
        ) {
            return false;
        }

        if (
                paymentStatus == null ||
                paymentStatus.isBlank()
        ) {
            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(requestId)
                .update(
                        "status",
                        "Completed",
                        "diagnosis",
                        diagnosis,
                        "description",
                        repairDetails,
                        "partsCost",
                        partsCost,
                        "labourCost",
                        labourCost,
                        "totalAmount",
                        totalAmount,
                        "invoiceId",
                        invoiceId,
                        "paymentStatus",
                        paymentStatus,
                        "completedDate",
                        String.valueOf(
                                System.currentTimeMillis()
                        )
                )
                .get();

        return true;
    }

    // Update payment status
    public boolean updatePaymentStatus(
            String requestId,
            String paymentStatus
    ) throws Exception {

        if (
                requestId == null ||
                requestId.isBlank()
        ) {
            return false;
        }

        if (
                paymentStatus == null ||
                paymentStatus.isBlank()
        ) {
            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(requestId)
                .update(
                        "paymentStatus",
                        paymentStatus
                )
                .get();

        return true;
    }
}
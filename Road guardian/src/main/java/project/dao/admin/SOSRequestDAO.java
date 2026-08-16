package project.dao.admin;

 

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import project.model.SOSRequest;

import java.util.ArrayList;
import java.util.List;

public class SOSRequestDAO {

    private static final String COLLECTION =
            "SOSRequests";

    private final Firestore firestore;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public SOSRequestDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    // =====================================================
    // GET ALL SOS REQUESTS
    // =====================================================

    public List<SOSRequest> getAllRequests()
            throws Exception {

        List<SOSRequest> requests =
                new ArrayList<>();

        ApiFuture<QuerySnapshot> future =
                firestore
                        .collection(COLLECTION)
                        .get();

        QuerySnapshot snapshot =
                future.get();

        for (DocumentSnapshot document :
                snapshot.getDocuments()) {

            SOSRequest request =
                    document.toObject(
                            SOSRequest.class
                    );

            if (request != null) {

                if (request.getSosId() == null ||
                        request.getSosId().isBlank()) {

                    request.setSosId(
                            document.getId()
                    );
                }

                requests.add(request);
            }
        }

        return requests;
    }

    // =====================================================
    // GET SOS BY ID
    // =====================================================

    public SOSRequest getRequestById(
            String sosId
    ) throws Exception {

        if (sosId == null || sosId.isBlank()) {
            return null;
        }

        DocumentSnapshot document =
                firestore
                        .collection(COLLECTION)
                        .document(sosId)
                        .get()
                        .get();

        if (!document.exists()) {
            return null;
        }

        SOSRequest request =
                document.toObject(
                        SOSRequest.class
                );

        if (request != null &&
                (request.getSosId() == null ||
                        request.getSosId().isBlank())) {

            request.setSosId(
                    document.getId()
            );
        }

        return request;
    }

    // =====================================================
    // ADD SOS REQUEST
    // =====================================================

    public boolean addRequest(
            SOSRequest request
    ) throws Exception {

        if (request == null) {
            return false;
        }

        DocumentReference document =
                firestore
                        .collection(COLLECTION)
                        .document();

        request.setSosId(
                document.getId()
        );

        document.set(request).get();

        return true;
    }

    // =====================================================
    // UPDATE SOS REQUEST
    // =====================================================

    public boolean updateRequest(
            SOSRequest request
    ) throws Exception {

        if (request == null ||
                request.getSosId() == null ||
                request.getSosId().isBlank()) {

            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(request.getSosId())
                .set(
                        request,
                        SetOptions.merge()
                )
                .get();

        return true;
    }

    // =====================================================
    // UPDATE STATUS
    // =====================================================

    public boolean updateStatus(
            String sosId,
            String status
    ) throws Exception {

        if (sosId == null ||
                sosId.isBlank() ||
                status == null ||
                status.isBlank()) {

            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(sosId)
                .update(
                        "status",
                        status
                )
                .get();

        return true;
    }

    // =====================================================
    // ASSIGN MECHANIC
    // =====================================================

    public boolean assignMechanic(
            String sosId,
            String mechanicId,
            String mechanicName
    ) throws Exception {

        if (sosId == null ||
                sosId.isBlank() ||
                mechanicId == null ||
                mechanicId.isBlank()) {

            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(sosId)
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

    // =====================================================
    // GET CUSTOMER SOS REQUESTS
    // =====================================================

    public List<SOSRequest> getRequestsByCustomerId(
            String customerId
    ) throws Exception {

        List<SOSRequest> requests =
                new ArrayList<>();

        if (customerId == null ||
                customerId.isBlank()) {

            return requests;
        }

        ApiFuture<QuerySnapshot> future =
                firestore
                        .collection(COLLECTION)
                        .whereEqualTo(
                                "customerId",
                                customerId
                        )
                        .get();

        QuerySnapshot snapshot =
                future.get();

        for (DocumentSnapshot document :
                snapshot.getDocuments()) {

            SOSRequest request =
                    document.toObject(
                            SOSRequest.class
                    );

            if (request != null) {

                if (request.getSosId() == null ||
                        request.getSosId().isBlank()) {

                    request.setSosId(
                            document.getId()
                    );
                }

                requests.add(request);
            }
        }

        return requests;
    }

    // =====================================================
    // GET MECHANIC SOS REQUESTS
    // =====================================================

    public List<SOSRequest> getRequestsByMechanicId(
            String mechanicId
    ) throws Exception {

        List<SOSRequest> requests =
                new ArrayList<>();

        if (mechanicId == null ||
                mechanicId.isBlank()) {

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

        for (DocumentSnapshot document :
                snapshot.getDocuments()) {

            SOSRequest request =
                    document.toObject(
                            SOSRequest.class
                    );

            if (request != null) {

                if (request.getSosId() == null ||
                        request.getSosId().isBlank()) {

                    request.setSosId(
                            document.getId()
                    );
                }

                requests.add(request);
            }
        }

        return requests;
    }

    // =====================================================
    // GET REQUESTS BY STATUS
    // =====================================================

    public List<SOSRequest> getRequestsByStatus(
            String status
    ) throws Exception {

        List<SOSRequest> all =
                getAllRequests();

        List<SOSRequest> result =
                new ArrayList<>();

        if (status == null ||
                status.isBlank() ||
                status.equalsIgnoreCase("All")) {

            return all;
        }

        for (SOSRequest request : all) {

            if (request.getStatus() != null &&
                    request.getStatus()
                            .equalsIgnoreCase(status)) {

                result.add(request);
            }
        }

        return result;
    }

    // =====================================================
    // SEARCH
    // =====================================================

    public List<SOSRequest> searchRequests(
            String searchText
    ) throws Exception {

        List<SOSRequest> all =
                getAllRequests();

        List<SOSRequest> result =
                new ArrayList<>();

        if (searchText == null ||
                searchText.isBlank()) {

            return all;
        }

        String search =
                searchText.trim().toLowerCase();

        for (SOSRequest request : all) {

            if (
                    contains(
                            request.getSosId(),
                            search
                    ) ||

                    contains(
                            request.getCustomerName(),
                            search
                    ) ||

                    contains(
                            request.getVehicleNumber(),
                            search
                    ) ||

                    contains(
                            request.getMechanicName(),
                            search
                    ) ||

                    contains(
                            request.getEmergencyType(),
                            search
                    ) ||

                    contains(
                            request.getLocation(),
                            search
                    ) ||

                    contains(
                            request.getStatus(),
                            search
                    )
            ) {

                result.add(request);
            }
        }

        return result;
    }

    // =====================================================
    // DELETE
    // =====================================================

    public boolean deleteRequest(
            String sosId
    ) throws Exception {

        if (sosId == null ||
                sosId.isBlank()) {

            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(sosId)
                .delete()
                .get();

        return true;
    }

    // =====================================================
    // HELPER
    // =====================================================

    private boolean contains(
            String value,
            String search
    ) {

        return value != null &&
                value.toLowerCase()
                        .contains(search);
    }
}
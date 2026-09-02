package project.dao.admin;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import project.model.ServiceRequest;

import java.util.ArrayList;
import java.util.List;

public class ServiceRequestDAO {

    private final Firestore firestore;

    private static final String COLLECTION =
            "serviceRequests";

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ServiceRequestDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    // =====================================================
    // GET ALL REQUESTS
    // =====================================================

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
                System.out.println(
        "FIRESTORE DOCUMENT COUNT = " +
        snapshot.size()
);

        for (DocumentSnapshot document : snapshot.getDocuments()) {

        System.out.println(
                "DOCUMENT ID = " +
                document.getId()
        );

        System.out.println(
                "DOCUMENT DATA = " +
                document.getData()
        );
        }

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
    // GET REQUEST BY ID
    // =====================================================

    public ServiceRequest getRequestById(
            String requestId
    ) throws Exception {

        if (
                requestId == null ||
                requestId.isBlank()
        ) {

            return null;
        }

        DocumentSnapshot document =
                firestore
                        .collection(COLLECTION)
                        .document(requestId)
                        .get()
                        .get();

        if (!document.exists()) {

            return null;
        }

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

    // =====================================================
    // GET REQUESTS BY CUSTOMER
    // =====================================================

    public List<ServiceRequest> getRequestsByCustomerId(
            String customerId
    ) throws Exception {

        List<ServiceRequest> requests =
                new ArrayList<>();

        if (
                customerId == null ||
                customerId.isBlank()
        ) {

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

    // =====================================================
    // ADD REQUEST
    // =====================================================

    public boolean addRequest(
            ServiceRequest request
    ) throws Exception {

        if (request == null) {

            return false;
        }

        DocumentReference document =
                firestore
                        .collection(COLLECTION)
                        .document();

        String requestId =
                document.getId();

        request.setRequestId(
                requestId
        );

        document.set(
                request
        ).get();

        return true;
    }

    // =====================================================
    // UPDATE REQUEST
    // =====================================================

    public boolean updateRequest(
            ServiceRequest request
    ) throws Exception {

        if (
                request == null ||
                request.getRequestId() == null ||
                request.getRequestId().isBlank()
        ) {

            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(
                        request.getRequestId()
                )
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
    // ASSIGN MECHANIC
    // =====================================================

    public boolean assignMechanic(
            String requestId,
            String mechanicId,
            String mechanicName
    ) throws Exception {

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

    // =====================================================
    // DELETE REQUEST
    // =====================================================

    public boolean deleteRequest(
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
                .delete()
                .get();

        return true;
    }

    // =====================================================
    // SEARCH REQUESTS
    // =====================================================

    public List<ServiceRequest> searchRequests(
            String searchText
    ) throws Exception {

        List<ServiceRequest> allRequests =
                getAllRequests();

        List<ServiceRequest> result =
                new ArrayList<>();

        if (
                searchText == null ||
                searchText.isBlank()
        ) {

            return allRequests;
        }

        String search =
                searchText
                        .trim()
                        .toLowerCase();

        for (
                ServiceRequest request :
                allRequests
        ) {

            if (
                    contains(
                            request.getRequestId(),
                            search
                    ) ||

                    contains(
                            request.getCustomerId(),
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
                            request.getServiceType(),
                            search
                    ) ||

                    contains(
                            request.getLocation(),
                            search
                    )
            ) {

                result.add(request);
            }
        }

        return result;
    }

    // =====================================================
    // FILTER BY STATUS
    // =====================================================

    public List<ServiceRequest> getRequestsByStatus(
            String status
    ) throws Exception {

        List<ServiceRequest> allRequests =
                getAllRequests();

        List<ServiceRequest> result =
                new ArrayList<>();

        if (
                status == null ||
                status.isBlank() ||
                status.equalsIgnoreCase("All")
        ) {

            return allRequests;
        }

        for (
                ServiceRequest request :
                allRequests
        ) {

            if (
                    request.getStatus() != null &&
                    request.getStatus()
                            .equalsIgnoreCase(status)
            ) {

                result.add(request);
            }
        }

        return result;
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
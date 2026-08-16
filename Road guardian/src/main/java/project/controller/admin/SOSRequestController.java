package project.controller.admin;

import com.google.cloud.firestore.Firestore;
import project.dao.admin.SOSRequestDAO;
import project.model.SOSRequest;

import java.util.ArrayList;
import java.util.List;

public class SOSRequestController {

    private final SOSRequestDAO sosRequestDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public SOSRequestController(Firestore firestore) {

        this.sosRequestDAO =
                new SOSRequestDAO(firestore);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    public List<SOSRequest> getAllRequests() {

        try {

            return sosRequestDAO
                    .getAllRequests();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    public SOSRequest getRequestById(
            String sosId
    ) {

        try {

            if (
                    sosId == null ||
                    sosId.isBlank()
            ) {
                return null;
            }

            return sosRequestDAO
                    .getRequestById(sosId);

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // GET CUSTOMER REQUESTS
    // =====================================================

    public List<SOSRequest> getRequestsByCustomerId(
            String customerId
    ) {

        try {

            if (
                    customerId == null ||
                    customerId.isBlank()
            ) {
                return new ArrayList<>();
            }

            return sosRequestDAO
                    .getRequestsByCustomerId(
                            customerId
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // GET MECHANIC REQUESTS
    // =====================================================

    public List<SOSRequest> getRequestsByMechanicId(
            String mechanicId
    ) {

        try {

            if (
                    mechanicId == null ||
                    mechanicId.isBlank()
            ) {
                return new ArrayList<>();
            }

            return sosRequestDAO
                    .getRequestsByMechanicId(
                            mechanicId
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // ADD SOS REQUEST
    // =====================================================

    public boolean addRequest(
            SOSRequest request
    ) {

        try {

            if (request == null) {
                return false;
            }

            // Customer must exist
            if (
                    request.getCustomerId() == null ||
                    request.getCustomerId().isBlank()
            ) {
                return false;
            }

            // Vehicle must exist
            if (
                    request.getVehicleId() == null ||
                    request.getVehicleId().isBlank()
            ) {
                return false;
            }

            // Emergency type required
            if (
                    request.getEmergencyType() == null ||
                    request.getEmergencyType().isBlank()
            ) {
                return false;
            }

            // Default status
            if (
                    request.getStatus() == null ||
                    request.getStatus().isBlank()
            ) {

                request.setStatus(
                        "Pending"
                );
            }

            // Request date
            if (
                    request.getRequestDate() == null ||
                    request.getRequestDate().isBlank()
            ) {

                request.setRequestDate(
                        String.valueOf(
                                System.currentTimeMillis()
                        )
                );
            }

            // New SOS has no resolved date
            if (
                    request.getResolvedDate() == null
            ) {

                request.setResolvedDate("");
            }

            return sosRequestDAO
                    .addRequest(request);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // UPDATE REQUEST
    // =====================================================

    public boolean updateRequest(
            SOSRequest request
    ) {

        try {

            if (
                    request == null ||
                    request.getSosId() == null ||
                    request.getSosId().isBlank()
            ) {
                return false;
            }

            return sosRequestDAO
                    .updateRequest(request);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // UPDATE STATUS
    // =====================================================

    public boolean updateStatus(
            String sosId,
            String status
    ) {

        try {

            if (
                    sosId == null ||
                    sosId.isBlank()
            ) {
                return false;
            }

            if (
                    status == null ||
                    status.isBlank()
            ) {
                return false;
            }

            boolean updated =
                    sosRequestDAO.updateStatus(
                            sosId,
                            status
                    );

            // When SOS is resolved,
            // store resolved date.
            if (
                    updated &&
                    status.equalsIgnoreCase(
                            "Resolved"
                    )
            ) {

                SOSRequest request =
                        sosRequestDAO
                                .getRequestById(
                                        sosId
                                );

                if (request != null) {

                    request.setResolvedDate(
                            String.valueOf(
                                    System.currentTimeMillis()
                            )
                    );

                    sosRequestDAO
                            .updateRequest(
                                    request
                            );
                }
            }

            return updated;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // ASSIGN MECHANIC
    // =====================================================

    public boolean assignMechanic(
            String sosId,
            String mechanicId,
            String mechanicName
    ) {

        try {

            if (
                    sosId == null ||
                    sosId.isBlank()
            ) {
                return false;
            }

            if (
                    mechanicId == null ||
                    mechanicId.isBlank()
            ) {
                return false;
            }

            if (
                    mechanicName == null ||
                    mechanicName.isBlank()
            ) {
                return false;
            }

            return sosRequestDAO
                    .assignMechanic(
                            sosId,
                            mechanicId,
                            mechanicName
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // DELETE
    // =====================================================

    public boolean deleteRequest(
            String sosId
    ) {

        try {

            if (
                    sosId == null ||
                    sosId.isBlank()
            ) {
                return false;
            }

            return sosRequestDAO
                    .deleteRequest(sosId);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // SEARCH
    // =====================================================

    public List<SOSRequest> searchRequests(
            String searchText
    ) {

        try {

            return sosRequestDAO
                    .searchRequests(searchText);

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // FILTER BY STATUS
    // =====================================================

    public List<SOSRequest> getRequestsByStatus(
            String status
    ) {

        try {

            return sosRequestDAO
                    .getRequestsByStatus(status);

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // STATISTICS
    // =====================================================

    public int getTotalRequests() {

        return getAllRequests().size();
    }

    public int getPendingRequests() {

        return countByStatus("Pending");
    }

    public int getAcceptedRequests() {

        return countByStatus("Accepted");
    }

    public int getInProgressRequests() {

        return countByStatus("In Progress");
    }

    public int getResolvedRequests() {

        return countByStatus("Resolved");
    }

    public int getCancelledRequests() {

        return countByStatus("Cancelled");
    }

    // =====================================================
    // COUNT BY STATUS
    // =====================================================

    private int countByStatus(
            String status
    ) {

        List<SOSRequest> requests =
                getAllRequests();

        int count = 0;

        for (
                SOSRequest request :
                requests
        ) {

            if (
                    request.getStatus() != null &&
                    request.getStatus()
                            .equalsIgnoreCase(
                                    status
                            )
            ) {

                count++;
            }
        }

        return count;
    }
}
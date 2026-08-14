package project.controller.admin;

import com.google.cloud.firestore.Firestore;
import project.dao.admin.ServiceRequestDAO;
import project.model.ServiceRequest;

import java.util.ArrayList;
import java.util.List;

public class ServiceRequestController {

    private final ServiceRequestDAO serviceRequestDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ServiceRequestController(
            Firestore firestore
    ) {

        this.serviceRequestDAO =
                new ServiceRequestDAO(firestore);
    }

    // =====================================================
    // GET ALL REQUESTS
    // =====================================================

    public List<ServiceRequest> getAllRequests() {

        try {

            return serviceRequestDAO
                    .getAllRequests();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // GET REQUEST BY ID
    // =====================================================

    public ServiceRequest getRequestById(
            String requestId
    ) {

        try {

            if (
                    requestId == null ||
                    requestId.isBlank()
            ) {

                return null;
            }

            return serviceRequestDAO
                    .getRequestById(
                            requestId
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // GET CUSTOMER REQUESTS
    // =====================================================

    public List<ServiceRequest> getRequestsByCustomerId(
            String customerId
    ) {

        try {

            if (
                    customerId == null ||
                    customerId.isBlank()
            ) {

                return new ArrayList<>();
            }

            return serviceRequestDAO
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

    public List<ServiceRequest> getRequestsByMechanicId(
            String mechanicId
    ) {

        try {

            if (
                    mechanicId == null ||
                    mechanicId.isBlank()
            ) {

                return new ArrayList<>();
            }

            return serviceRequestDAO
                    .getRequestsByMechanicId(
                            mechanicId
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // ADD REQUEST
    // =====================================================

    public boolean addRequest(
            ServiceRequest request
    ) {

        try {

            if (
                    request == null
            ) {

                return false;
            }

            // Customer validation
            if (
                    request.getCustomerId() == null ||
                    request.getCustomerId().isBlank()
            ) {

                return false;
            }

            // Vehicle validation
            if (
                    request.getVehicleId() == null ||
                    request.getVehicleId().isBlank()
            ) {

                return false;
            }

            // Service type validation
            if (
                    request.getServiceType() == null ||
                    request.getServiceType().isBlank()
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

            // New request has no completion date
            if (
                    request.getCompletedDate() == null
            ) {

                request.setCompletedDate(
                        ""
                );
            }

            return serviceRequestDAO
                    .addRequest(
                            request
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // UPDATE REQUEST
    // =====================================================

    public boolean updateRequest(
            ServiceRequest request
    ) {

        try {

            if (
                    request == null ||
                    request.getRequestId() == null ||
                    request.getRequestId().isBlank()
            ) {

                return false;
            }

            return serviceRequestDAO
                    .updateRequest(
                            request
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // UPDATE STATUS
    // =====================================================

    public boolean updateStatus(
            String requestId,
            String status
    ) {

        try {

            if (
                    requestId == null ||
                    requestId.isBlank()
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
                    serviceRequestDAO
                            .updateStatus(
                                    requestId,
                                    status
                            );

            // Completed date
            if (
                    updated &&
                    status.equalsIgnoreCase(
                            "Completed"
                    )
            ) {

                ServiceRequest request =
                        serviceRequestDAO
                                .getRequestById(
                                        requestId
                                );

                if (request != null) {

                    request.setCompletedDate(
                            String.valueOf(
                                    System.currentTimeMillis()
                            )
                    );

                    serviceRequestDAO
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
            String requestId,
            String mechanicId,
            String mechanicName
    ) {

        try {

            if (
                    requestId == null ||
                    requestId.isBlank()
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

            return serviceRequestDAO
                    .assignMechanic(
                            requestId,
                            mechanicId,
                            mechanicName
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // DELETE REQUEST
    // =====================================================

    public boolean deleteRequest(
            String requestId
    ) {

        try {

            if (
                    requestId == null ||
                    requestId.isBlank()
            ) {

                return false;
            }

            return serviceRequestDAO
                    .deleteRequest(
                            requestId
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // SEARCH
    // =====================================================

    public List<ServiceRequest> searchRequests(
            String searchText
    ) {

        try {

            return serviceRequestDAO
                    .searchRequests(
                            searchText
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // FILTER BY STATUS
    // =====================================================

    public List<ServiceRequest> getRequestsByStatus(
            String status
    ) {

        try {

            return serviceRequestDAO
                    .getRequestsByStatus(
                            status
                    );

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

        return countByStatus(
                "Pending"
        );
    }

    public int getAcceptedRequests() {

        return countByStatus(
                "Accepted"
        );
    }

    public int getInProgressRequests() {

        return countByStatus(
                "In Progress"
        );
    }

    public int getCompletedRequests() {

        return countByStatus(
                "Completed"
        );
    }

    public int getCancelledRequests() {

        return countByStatus(
                "Cancelled"
        );
    }

    // =====================================================
    // COUNT BY STATUS
    // =====================================================

    private int countByStatus(
            String status
    ) {

        List<ServiceRequest> requests =
                getAllRequests();

        int count = 0;

        for (
                ServiceRequest request :
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
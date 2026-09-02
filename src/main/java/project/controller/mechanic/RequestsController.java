package project.controller.mechanic;

import com.google.cloud.firestore.Firestore;
import project.dao.mechanic.MechanicRequestDAO;
import project.model.ServiceRequest;

import java.util.ArrayList;
import java.util.List;

public class RequestsController {

    private final MechanicRequestDAO mechanicRequestDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public RequestsController(
            Firestore firestore
    ) {

        this.mechanicRequestDAO =
                new MechanicRequestDAO(firestore);
    }

    // =====================================================
    // GET PENDING REQUESTS
    // =====================================================

    public List<ServiceRequest> getPendingRequests() {

        try {

            return mechanicRequestDAO
                    .getPendingRequests();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // GET ALL REQUESTS
    // =====================================================

    public List<ServiceRequest> getAllRequests() {

        try {

            return mechanicRequestDAO
                    .getAllRequests();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // GET REQUESTS BY MECHANIC
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

            return mechanicRequestDAO
                    .getRequestsByMechanicId(
                            mechanicId
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // ACCEPT REQUEST
    // =====================================================

    public boolean acceptRequest(
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

            return mechanicRequestDAO
                    .acceptRequest(
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

            return mechanicRequestDAO
                    .updateStatus(
                            requestId,
                            status
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
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
    ) {

        try {

            if (
                    requestId == null ||
                    requestId.isBlank()
            ) {
                return false;
            }

            if (partsCost < 0 || labourCost < 0) {
                return false;
            }

            return mechanicRequestDAO
                    .completeJob(
                            requestId,
                            diagnosis,
                            repairDetails,
                            partsCost,
                            labourCost,
                            totalAmount,
                            completedDate
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    public boolean completeRequest(
        String requestId,
        String diagnosis,
        String repairDetails,
        double partsCost,
        double labourCost,
        double totalAmount
    ) {

        try {

            if (
                    requestId == null ||
                    requestId.isBlank()
            ) {
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

            return mechanicRequestDAO
                    .completeRequest(
                            requestId,
                            diagnosis,
                            repairDetails,
                            partsCost,
                            labourCost,
                            totalAmount
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}

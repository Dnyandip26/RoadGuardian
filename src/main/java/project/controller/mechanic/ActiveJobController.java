package project.controller.mechanic;

import com.google.cloud.firestore.Firestore;
import project.dao.mechanic.ActiveJobDAO;
import project.model.ServiceRequest;

public class ActiveJobController {

    private final ActiveJobDAO activeJobDAO;

    public ActiveJobController(Firestore firestore) {
        this.activeJobDAO =
                new ActiveJobDAO(firestore);
    }

    // Get active job
    public ServiceRequest getActiveJob(
            String mechanicId
    ) {

        try {

            if (
                    mechanicId == null ||
                    mechanicId.isBlank()
            ) {
                return null;
            }

            return activeJobDAO.getActiveJob(
                    mechanicId
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // Start repair
    public boolean startRepair(
            String requestId
    ) {

        try {

            if (
                    requestId == null ||
                    requestId.isBlank()
            ) {
                return false;
            }

            return activeJobDAO.startRepair(
                    requestId
            );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
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

            if (partsCost < 0 || labourCost < 0) {
                return false;
            }

            if (totalAmount < 0) {
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

            return activeJobDAO.completeJob(
                    requestId,
                    diagnosis,
                    repairDetails,
                    partsCost,
                    labourCost,
                    totalAmount,
                    invoiceId,
                    paymentStatus
            );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // Update payment status
    public boolean updatePaymentStatus(
            String requestId,
            String paymentStatus
    ) {

        try {

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

            return activeJobDAO.updatePaymentStatus(
                    requestId,
                    paymentStatus
            );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}
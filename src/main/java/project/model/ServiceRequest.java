package project.model;

public class ServiceRequest {

    // =====================================================
    // BASIC REQUEST DETAILS
    // =====================================================

    private String requestId;

    // Customer
    private String customerId;
    private String customerName;
    private String customerMobile;

    // Vehicle
    private String vehicleId;
    private String vehicleNumber;

    // Mechanic
    private String mechanicId;
    private String mechanicName;

    // Service
    private String serviceType;
    private String description;
    private String location;

    // Status
    private String status;

    // Dates
    private String requestDate;
    private String completedDate;

    // Repair details
    private String diagnosis;
    private String repairDetails;

    // Amount
    private double partsCost;
    private double labourCost;
    private double totalAmount;

    // Invoice / Payment
    private String invoiceId;
    private String paymentStatus;


    // =====================================================
    // FIRESTORE EMPTY CONSTRUCTOR
    // =====================================================

    public ServiceRequest() {
    }


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ServiceRequest(
            String requestId,
            String customerId,
            String customerName,
            String vehicleId,
            String vehicleNumber,
            String mechanicId,
            String mechanicName,
            String serviceType,
            String description,
            String location,
            String status,
            String requestDate,
            String completedDate
    ) {

        this.requestId = requestId;

        this.customerId = customerId;
        this.customerName = customerName;

        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;

        this.mechanicId = mechanicId;
        this.mechanicName = mechanicName;

        this.serviceType = serviceType;
        this.description = description;
        this.location = location;

        this.status = status;

        this.requestDate = requestDate;
        this.completedDate = completedDate;
    }


    // =====================================================
    // REQUEST ID
    // =====================================================

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    // =====================================================
    // CUSTOMER
    // =====================================================

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }


    // =====================================================
    // VEHICLE
    // =====================================================

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }


    // =====================================================
    // MECHANIC
    // =====================================================

    public String getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(String mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }


    // =====================================================
    // SERVICE
    // =====================================================

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    // =====================================================
    // STATUS
    // =====================================================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // =====================================================
    // DATES
    // =====================================================

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }


    // =====================================================
    // REPAIR DETAILS
    // =====================================================

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRepairDetails() {
        return repairDetails;
    }

    public void setRepairDetails(String repairDetails) {
        this.repairDetails = repairDetails;
    }


    // =====================================================
    // AMOUNT
    // =====================================================

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        this.partsCost = partsCost;
    }

    public double getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(double labourCost) {
        this.labourCost = labourCost;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    // =====================================================
    // INVOICE
    // =====================================================

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }


    // =====================================================
    // PAYMENT
    // =====================================================

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    // =====================================================
    // TO STRING
    // =====================================================

    @Override
    public String toString() {

        return "ServiceRequest{" +
                "requestId='" + requestId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", mechanicId='" + mechanicId + '\'' +
                ", mechanicName='" + mechanicName + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", completedDate='" + completedDate + '\'' +
                ", totalAmount=" + totalAmount +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
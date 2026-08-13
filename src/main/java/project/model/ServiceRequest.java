package project.model;

public class ServiceRequest {

    private String requestId;

    private String customerId;
    private String customerName;

    private String vehicleId;
    private String vehicleNumber;

    private String mechanicId;
    private String mechanicName;

    private String serviceType;
    private String description;
    private String location;

    private String status;

    private String requestDate;
    private String completedDate;

    // Firestore साठी mandatory empty constructor
    public ServiceRequest() {
    }

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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
                ", status='" + status + '\'' +
                '}';
    }
}
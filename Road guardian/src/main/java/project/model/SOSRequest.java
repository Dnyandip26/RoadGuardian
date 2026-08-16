package project.model;

public class SOSRequest {

    private String sosId;

    private String customerId;
    private String customerName;

    private String vehicleId;
    private String vehicleNumber;

    private String mechanicId;
    private String mechanicName;

    private String emergencyType;
    private String description;

    private String location;
    private String latitude;
    private String longitude;

    private String status;

    private String requestDate;
    private String resolvedDate;


    // Firestore requires empty constructor
    public SOSRequest() {
    }


    public SOSRequest(
            String sosId,
            String customerId,
            String customerName,
            String vehicleId,
            String vehicleNumber,
            String mechanicId,
            String mechanicName,
            String emergencyType,
            String description,
            String location,
            String latitude,
            String longitude,
            String status,
            String requestDate,
            String resolvedDate
    ) {

        this.sosId = sosId;
        this.customerId = customerId;
        this.customerName = customerName;

        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;

        this.mechanicId = mechanicId;
        this.mechanicName = mechanicName;

        this.emergencyType = emergencyType;
        this.description = description;

        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;

        this.status = status;

        this.requestDate = requestDate;
        this.resolvedDate = resolvedDate;
    }


    public String getSosId() {
        return sosId;
    }

    public void setSosId(String sosId) {
        this.sosId = sosId;
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


    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
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


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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


    public String getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(String resolvedDate) {
        this.resolvedDate = resolvedDate;
    }


    @Override
    public String toString() {

        return "SOSRequest{" +
                "sosId='" + sosId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", emergencyType='" + emergencyType + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
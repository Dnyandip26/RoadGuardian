package project.model;

public class Mechanic {

    private String mechanicId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String specialization;
    private String status;
    private String experience;
    private String createdAt;

    public Mechanic() {
    }

    public Mechanic(
            String mechanicId,
            String name,
            String email,
            String phone,
            String address,
            String city,
            String specialization,
            String status,
            String experience,
            String createdAt
    ) {

        this.mechanicId = mechanicId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.specialization = specialization;
        this.status = status;
        this.experience = experience;
        this.createdAt = createdAt;
    }

    public String getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(String mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {

        return "Mechanic{" +
                "mechanicId='" + mechanicId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", specialization='" + specialization + '\'' +
                ", status='" + status + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
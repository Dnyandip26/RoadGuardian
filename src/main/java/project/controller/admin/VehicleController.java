package project.controller.admin;

import project.dao.admin.VehicleDAO;
import project.model.Vehicle;

import com.google.cloud.firestore.Firestore;

import java.util.ArrayList;
import java.util.List;

public class VehicleController {

    private final VehicleDAO vehicleDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public VehicleController(
            Firestore firestore
    ) {

        this.vehicleDAO =
                new VehicleDAO(firestore);
    }

    // =====================================================
    // GET ALL VEHICLES
    // =====================================================

    public List<Vehicle> getAllVehicles() {

        try {

            return vehicleDAO
                    .getAllVehicles();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // GET VEHICLE BY ID
    // =====================================================

    public Vehicle getVehicleById(
            String vehicleId
    ) {

        try {

            return vehicleDAO
                    .getVehicleById(
                            vehicleId
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // GET VEHICLES BY CUSTOMER
    // =====================================================

    public List<Vehicle> getVehiclesByCustomerId(
            String customerId
    ) {

        try {

            return vehicleDAO
                    .getVehiclesByCustomerId(
                            customerId
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =====================================================
    // ADD VEHICLE
    // =====================================================

    public boolean addVehicle(
            Vehicle vehicle
    ) {

        try {

            if (
                    vehicle == null
            ) {

                return false;
            }

            // ---------------------------------------------
            // BASIC VALIDATION
            // ---------------------------------------------

            if (
                    vehicle.getCustomerId() == null ||
                    vehicle.getCustomerId().isBlank()
            ) {

                return false;
            }

            if (
                    vehicle.getVehicleNumber() == null ||
                    vehicle.getVehicleNumber().isBlank()
            ) {

                return false;
            }

            if (
                    vehicle.getBrand() == null ||
                    vehicle.getBrand().isBlank()
            ) {

                return false;
            }

            if (
                    vehicle.getModel() == null ||
                    vehicle.getModel().isBlank()
            ) {

                return false;
            }

            // ---------------------------------------------
            // DEFAULT STATUS
            // ---------------------------------------------

            if (
                    vehicle.getStatus() == null ||
                    vehicle.getStatus().isBlank()
            ) {

                vehicle.setStatus(
                        "Active"
                );
            }

            // ---------------------------------------------
            // CREATED TIME
            // ---------------------------------------------

            if (
                    vehicle.getCreatedAt() == null ||
                    vehicle.getCreatedAt().isBlank()
            ) {

                vehicle.setCreatedAt(
                        String.valueOf(
                                System.currentTimeMillis()
                        )
                );
            }

            return vehicleDAO
                    .addVehicle(
                            vehicle
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // UPDATE VEHICLE
    // =====================================================

    public boolean updateVehicle(
            Vehicle vehicle
    ) {

        try {

            if (
                    vehicle == null ||
                    vehicle.getVehicleId() == null ||
                    vehicle.getVehicleId().isBlank()
            ) {

                return false;
            }

            return vehicleDAO
                    .updateVehicle(
                            vehicle
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // DELETE VEHICLE
    // =====================================================

    public boolean deleteVehicle(
            String vehicleId
    ) {

        try {

            if (
                    vehicleId == null ||
                    vehicleId.isBlank()
            ) {

                return false;
            }

            return vehicleDAO
                    .deleteVehicle(
                            vehicleId
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // SEARCH VEHICLES
    // =====================================================

    public List<Vehicle> searchVehicles(
            String searchText
    ) {

        try {

            return vehicleDAO
                    .searchVehicles(
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

    public List<Vehicle> getVehiclesByStatus(
            String status
    ) {

        try {

            return vehicleDAO
                    .getVehiclesByStatus(
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

    public int getTotalVehicles() {

        return getAllVehicles()
                .size();
    }

    public int getActiveVehicles() {

        List<Vehicle> vehicles =
                getAllVehicles();

        int count = 0;

        for (
                Vehicle vehicle :
                vehicles
        ) {

            if (
                    vehicle.getStatus() != null &&
                    vehicle.getStatus()
                            .equalsIgnoreCase(
                                    "Active"
                            )
            ) {

                count++;
            }
        }

        return count;
    }

    public int getInactiveVehicles() {

        List<Vehicle> vehicles =
                getAllVehicles();

        int count = 0;

        for (
                Vehicle vehicle :
                vehicles
        ) {

            if (
                    vehicle.getStatus() == null ||
                    !vehicle.getStatus()
                            .equalsIgnoreCase(
                                    "Active"
                            )
            ) {

                count++;
            }
        }

        return count;
    }
}
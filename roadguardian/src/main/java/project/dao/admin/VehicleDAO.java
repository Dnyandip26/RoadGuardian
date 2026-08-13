package project.dao.admin;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import project.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    private final Firestore firestore;

    private static final String COLLECTION =
            "vehicles";

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public VehicleDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    // =====================================================
    // GET ALL VEHICLES
    // =====================================================

    public List<Vehicle> getAllVehicles()
            throws Exception {

        List<Vehicle> vehicles =
                new ArrayList<>();

        ApiFuture<QuerySnapshot> future =
                firestore
                        .collection(COLLECTION)
                        .get();

        QuerySnapshot snapshot =
                future.get();

        for (
                DocumentSnapshot document :
                snapshot.getDocuments()
        ) {

            Vehicle vehicle =
                    document.toObject(
                            Vehicle.class
                    );

            if (vehicle != null) {

                if (
                        vehicle.getVehicleId() == null ||
                        vehicle.getVehicleId().isBlank()
                ) {

                    vehicle.setVehicleId(
                            document.getId()
                    );
                }

                vehicles.add(
                        vehicle
                );
            }
        }

        return vehicles;
    }

    // =====================================================
    // GET VEHICLE BY ID
    // =====================================================

    public Vehicle getVehicleById(
            String vehicleId
    ) throws Exception {

        if (
                vehicleId == null ||
                vehicleId.isBlank()
        ) {

            return null;
        }

        DocumentSnapshot document =
                firestore
                        .collection(COLLECTION)
                        .document(vehicleId)
                        .get()
                        .get();

        if (!document.exists()) {

            return null;
        }

        Vehicle vehicle =
                document.toObject(
                        Vehicle.class
                );

        if (vehicle != null) {

            if (
                    vehicle.getVehicleId() == null ||
                    vehicle.getVehicleId().isBlank()
            ) {

                vehicle.setVehicleId(
                        document.getId()
                );
            }
        }

        return vehicle;
    }

    // =====================================================
    // GET VEHICLES BY CUSTOMER
    // =====================================================

    public List<Vehicle> getVehiclesByCustomerId(
            String customerId
    ) throws Exception {

        List<Vehicle> vehicles =
                new ArrayList<>();

        if (
                customerId == null ||
                customerId.isBlank()
        ) {

            return vehicles;
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

            Vehicle vehicle =
                    document.toObject(
                            Vehicle.class
                    );

            if (vehicle != null) {

                if (
                        vehicle.getVehicleId() == null ||
                        vehicle.getVehicleId().isBlank()
                ) {

                    vehicle.setVehicleId(
                            document.getId()
                    );
                }

                vehicles.add(
                        vehicle
                );
            }
        }

        return vehicles;
    }

    // =====================================================
    // ADD VEHICLE
    // =====================================================

    public boolean addVehicle(
            Vehicle vehicle
    ) throws Exception {

        if (vehicle == null) {

            return false;
        }

        DocumentReference document =
                firestore
                        .collection(COLLECTION)
                        .document();

        String vehicleId =
                document.getId();

        vehicle.setVehicleId(
                vehicleId
        );

        document.set(
                vehicle
        ).get();

        return true;
    }

    // =====================================================
    // UPDATE VEHICLE
    // =====================================================

    public boolean updateVehicle(
            Vehicle vehicle
    ) throws Exception {

        if (
                vehicle == null ||
                vehicle.getVehicleId() == null ||
                vehicle.getVehicleId().isBlank()
        ) {

            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(
                        vehicle.getVehicleId()
                )
                .set(
                        vehicle,
                        SetOptions.merge()
                )
                .get();

        return true;
    }

    // =====================================================
    // DELETE VEHICLE
    // =====================================================

    public boolean deleteVehicle(
            String vehicleId
    ) throws Exception {

        if (
                vehicleId == null ||
                vehicleId.isBlank()
        ) {

            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(
                        vehicleId
                )
                .delete()
                .get();

        return true;
    }

    // =====================================================
    // SEARCH VEHICLES
    // =====================================================

    public List<Vehicle> searchVehicles(
            String searchText
    ) throws Exception {

        List<Vehicle> allVehicles =
                getAllVehicles();

        List<Vehicle> result =
                new ArrayList<>();

        if (
                searchText == null ||
                searchText.isBlank()
        ) {

            return allVehicles;
        }

        String search =
                searchText
                        .trim()
                        .toLowerCase();

        for (
                Vehicle vehicle :
                allVehicles
        ) {

            if (
                    contains(
                            vehicle.getVehicleId(),
                            search
                    ) ||

                    contains(
                            vehicle.getCustomerId(),
                            search
                    ) ||

                    contains(
                            vehicle.getOwnerName(),
                            search
                    ) ||

                    contains(
                            vehicle.getVehicleNumber(),
                            search
                    ) ||

                    contains(
                            vehicle.getBrand(),
                            search
                    ) ||

                    contains(
                            vehicle.getModel(),
                            search
                    ) ||

                    contains(
                            vehicle.getVehicleType(),
                            search
                    ) ||

                    contains(
                            vehicle.getFuelType(),
                            search
                    )
            ) {

                result.add(
                        vehicle
                );
            }
        }

        return result;
    }

    // =====================================================
    // FILTER BY STATUS
    // =====================================================

    public List<Vehicle> getVehiclesByStatus(
            String status
    ) throws Exception {

        List<Vehicle> allVehicles =
                getAllVehicles();

        List<Vehicle> result =
                new ArrayList<>();

        if (
                status == null ||
                status.isBlank() ||
                status.equalsIgnoreCase("All")
        ) {

            return allVehicles;
        }

        for (
                Vehicle vehicle :
                allVehicles
        ) {

            if (
                    vehicle.getStatus() != null &&
                    vehicle.getStatus()
                            .equalsIgnoreCase(
                                    status
                            )
            ) {

                result.add(
                        vehicle
                );
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
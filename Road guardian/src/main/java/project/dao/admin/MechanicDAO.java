package project.dao.admin;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import project.model.Mechanic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MechanicDAO {

    private final Firestore firestore;

    private static final String COLLECTION =
            "mechanics";

    public MechanicDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    // =====================================================
    // GET ALL MECHANICS
    // =====================================================

    public List<Mechanic> getAllMechanics()
            throws Exception {

        List<Mechanic> mechanics =
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

            Mechanic mechanic =
                    document.toObject(
                            Mechanic.class
                    );

            if (mechanic != null) {

                // If mechanicId is not stored,
                // use Firestore document ID.
                if (
                        mechanic.getMechanicId() == null ||
                        mechanic.getMechanicId().isBlank()
                ) {

                    mechanic.setMechanicId(
                            document.getId()
                    );
                }

                mechanics.add(
                        mechanic
                );
            }
        }

        return mechanics;
    }

    // =====================================================
    // GET SINGLE MECHANIC
    // =====================================================

    public Mechanic getMechanicById(
            String mechanicId
    ) throws Exception {

        DocumentSnapshot document =
                firestore
                        .collection(COLLECTION)
                        .document(mechanicId)
                        .get()
                        .get();

        if (!document.exists()) {

            return null;
        }

        Mechanic mechanic =
                document.toObject(
                        Mechanic.class
                );

        if (mechanic != null) {

            if (
                    mechanic.getMechanicId() == null ||
                    mechanic.getMechanicId().isBlank()
            ) {

                mechanic.setMechanicId(
                        document.getId()
                );
            }
        }

        return mechanic;
    }

    // =====================================================
    // ADD MECHANIC
    // =====================================================

    public boolean addMechanic(
            Mechanic mechanic
    ) throws Exception {

        if (mechanic == null) {

            return false;
        }

        DocumentReference document =
                firestore
                        .collection(COLLECTION)
                        .document();

        String mechanicId =
                document.getId();

        mechanic.setMechanicId(
                mechanicId
        );

        document.set(
                mechanic
        ).get();

        return true;
    }

    // =====================================================
    // UPDATE MECHANIC
    // =====================================================

    public boolean updateMechanic(
            Mechanic mechanic
    ) throws Exception {

        if (
                mechanic == null ||
                mechanic.getMechanicId() == null ||
                mechanic.getMechanicId().isBlank()
        ) {

            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(
                        mechanic.getMechanicId()
                )
                .set(
                        mechanic,
                        SetOptions.merge()
                )
                .get();

        return true;
    }

    // =====================================================
    // DELETE MECHANIC
    // =====================================================

    public boolean deleteMechanic(
            String mechanicId
    ) throws Exception {

        if (
                mechanicId == null ||
                mechanicId.isBlank()
        ) {

            return false;
        }

        firestore
                .collection(COLLECTION)
                .document(
                        mechanicId
                )
                .delete()
                .get();

        return true;
    }

    // =====================================================
    // SEARCH MECHANICS
    // =====================================================

    public List<Mechanic> searchMechanics(
            String searchText
    ) throws Exception {

        List<Mechanic> allMechanics =
                getAllMechanics();

        List<Mechanic> result =
                new ArrayList<>();

        if (
                searchText == null ||
                searchText.isBlank()
        ) {

            return allMechanics;
        }

        String search =
                searchText
                        .trim()
                        .toLowerCase();

        for (
                Mechanic mechanic :
                allMechanics
        ) {

            if (
                    contains(
                            mechanic.getMechanicId(),
                            search
                    ) ||

                    contains(
                            mechanic.getName(),
                            search
                    ) ||

                    contains(
                            mechanic.getEmail(),
                            search
                    ) ||

                    contains(
                            mechanic.getPhone(),
                            search
                    ) ||

                    contains(
                            mechanic.getCity(),
                            search
                    ) ||

                    contains(
                            mechanic.getSpecialization(),
                            search
                    )
            ) {

                result.add(
                        mechanic
                );
            }
        }

        return result;
    }

    // =====================================================
    // FILTER BY STATUS
    // =====================================================

    public List<Mechanic> getMechanicsByStatus(
            String status
    ) throws Exception {

        List<Mechanic> allMechanics =
                getAllMechanics();

        List<Mechanic> result =
                new ArrayList<>();

        if (
                status == null ||
                status.isBlank() ||
                status.equalsIgnoreCase("All")
        ) {

            return allMechanics;
        }

        for (
                Mechanic mechanic :
                allMechanics
        ) {

            if (
                    mechanic.getStatus() != null &&
                    mechanic.getStatus()
                            .equalsIgnoreCase(
                                    status
                            )
            ) {

                result.add(
                        mechanic
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
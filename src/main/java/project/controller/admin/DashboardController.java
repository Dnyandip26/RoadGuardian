package project.controller.admin;

import com.google.cloud.firestore.Firestore;
import project.dao.admin.DashboardDAO;

import java.util.HashMap;
import java.util.Map;

public class DashboardController {

    private final DashboardDAO dashboardDAO;

    public DashboardController(
            Firestore firestore
    ) {

        this.dashboardDAO =
                new DashboardDAO(
                        firestore
                );
    }

    public Map<String, Integer>
    getDashboardStatistics() {

        try {

            return dashboardDAO
                    .getStatistics();

        } catch (Exception e) {

            e.printStackTrace();

            return new HashMap<>();
        }
    }

    public int getValue(
            Map<String, Integer> data,
            String key
    ) {

        return data.getOrDefault(
                key,
                0
        );
    }
}
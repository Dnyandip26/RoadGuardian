package project.dao.admin;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import project.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CustomerDAO {

    private static final String COLLECTION = "customers";

    private final Firestore firestore;

    public CustomerDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    public void addCustomer(Customer customer)
            throws ExecutionException, InterruptedException {

        CollectionReference collection =
                firestore.collection(COLLECTION);

        DocumentReference document;

        if (
                customer.getCustomerId() == null ||
                customer.getCustomerId().isBlank()
        ) {
            document = collection.document();
            customer.setCustomerId(
                    document.getId()
            );
        } else {
            document =
                    collection.document(
                            customer.getCustomerId()
                    );
        }

        document.set(customer).get();
    }

    public Customer getCustomerById(
            String customerId
    ) throws ExecutionException, InterruptedException {

        DocumentSnapshot document =
                firestore
                        .collection(COLLECTION)
                        .document(customerId)
                        .get()
                        .get();

        if (!document.exists()) {
            return null;
        }

        return document.toObject(
                Customer.class
        );
    }

    public List<Customer> getAllCustomers()
            throws ExecutionException, InterruptedException {

        List<Customer> customers =
                new ArrayList<>();

        ApiFuture<QuerySnapshot> future =
                firestore
                        .collection(COLLECTION)
                        .get();

        List<QueryDocumentSnapshot> documents =
                future.get()
                        .getDocuments();

        for (
                QueryDocumentSnapshot document :
                documents
        ) {

            Customer customer =
                    document.toObject(
                            Customer.class
                    );

            if (
                    customer.getCustomerId() == null ||
                    customer.getCustomerId().isBlank()
            ) {
                customer.setCustomerId(
                        document.getId()
                );
            }

            customers.add(
                    customer
            );
        }

        return customers;
    }

    public void updateCustomer(
            Customer customer
    ) throws ExecutionException, InterruptedException {

        if (
                customer.getCustomerId() == null ||
                customer.getCustomerId().isBlank()
        ) {
            throw new IllegalArgumentException(
                    "Customer ID is required for update."
            );
        }

        firestore
                .collection(COLLECTION)
                .document(
                        customer.getCustomerId()
                )
                .set(customer)
                .get();
    }

    public void deleteCustomer(
            String customerId
    ) throws ExecutionException, InterruptedException {

        if (
                customerId == null ||
                customerId.isBlank()
        ) {
            throw new IllegalArgumentException(
                    "Customer ID is required for delete."
            );
        }

        firestore
                .collection(COLLECTION)
                .document(customerId)
                .delete()
                .get();
    }

    public List<Customer> searchCustomers(
            String searchText
    ) throws ExecutionException, InterruptedException {

        List<Customer> allCustomers =
                getAllCustomers();

        List<Customer> results =
                new ArrayList<>();

        if (
                searchText == null ||
                searchText.isBlank()
        ) {
            return allCustomers;
        }

        String search =
                searchText
                        .trim()
                        .toLowerCase();

        for (
                Customer customer :
                allCustomers
        ) {

            boolean matchesName =
                    customer.getName() != null &&
                    customer.getName()
                            .toLowerCase()
                            .contains(search);

            boolean matchesEmail =
                    customer.getEmail() != null &&
                    customer.getEmail()
                            .toLowerCase()
                            .contains(search);

            boolean matchesPhone =
                    customer.getPhone() != null &&
                    customer.getPhone()
                            .toLowerCase()
                            .contains(search);

            boolean matchesCity =
                    customer.getCity() != null &&
                    customer.getCity()
                            .toLowerCase()
                            .contains(search);

            if (
                    matchesName ||
                    matchesEmail ||
                    matchesPhone ||
                    matchesCity
            ) {
                results.add(
                        customer
                );
            }
        }

        return results;
    }

    public List<Customer> getCustomersByStatus(
            String status
    ) throws ExecutionException, InterruptedException {

        List<Customer> customers =
                new ArrayList<>();

        ApiFuture<QuerySnapshot> future =
                firestore
                        .collection(COLLECTION)
                        .whereEqualTo(
                                "status",
                                status
                        )
                        .get();

        List<QueryDocumentSnapshot> documents =
                future.get()
                        .getDocuments();

        for (
                QueryDocumentSnapshot document :
                documents
        ) {

            Customer customer =
                    document.toObject(
                            Customer.class
                    );

            if (
                    customer.getCustomerId() == null ||
                    customer.getCustomerId().isBlank()
            ) {
                customer.setCustomerId(
                        document.getId()
                );
            }

            customers.add(
                    customer
            );
        }

        return customers;
    }
}
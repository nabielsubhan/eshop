package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {return null;}
    public Payment getPayment(String paymentId) {return null;}
    public List<Payment> getAllPayments() {return null;}
}
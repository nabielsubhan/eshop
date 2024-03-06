package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    List<Payment> payments;
    List<Product> products;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), orders.getFirst(), paymentDataVoucher);
        payments.add(payment1);

        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "Citayam Raya Street No. 71");
        paymentDataCashOnDelivery.put("deliveryFee", "100000");
        Payment payment2 = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.CASH_ON_DELIBERY.getValue(), orders.getFirst(), paymentDataCashOnDelivery);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        paymentService.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());

        verify(paymentRepository, times(1)).addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
    }
    @Test
    void testGetPaymentIfFound() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());

        Payment findResult = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());

        verify(paymentRepository, times(1)).getPayment(payment.getId());
    }
    @Test
    void testGetPaymentIfNotFound() {
        doReturn(null).when(paymentRepository).getPayment("zczc");

        Payment payment = paymentService.getPayment("zczc");
        assertNull(payment);
    }
    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> findResult = paymentService.getAllPayments();

        verify(paymentRepository, times(1)).getAllPayments();
        assertNotNull(findResult);
        assertEquals(payments.size(), findResult.size());
        assertTrue(findResult.containsAll(payments));
    }
    @Test
    void testSetStatusSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), orders.getFirst(), paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentService.setStatus(payment,PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
    @Test
    void testSetStatusFailed() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payments.getFirst(), "EITS"));
    }
}

package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;
    private List<Product> products;
    private Order order;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();

        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);

        this.products.add(product);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreatePaymentIfOrderNull() {
        order = null;
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData);
        });
    }
    @Test
    void testCreatePaymentWithNoMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234567-asdfghj-89-kl", "", order, paymentData);
        });
    }
    @Test
    void testCreatePaymentWithValidMethod() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData);

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertSame(order, payment.getOrder());
    }
    @Test
    void testCreatePaymentWithValidMethodCashOnDelivery() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("9876543-qwertyu-21-io", "CASH_ON_DELIVERY", order, paymentData);

        assertEquals("9876543-qwertyu-21-io", payment.getId());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertSame(order, payment.getOrder());
    }
    @Test
    void testCreatePaymentWithInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234567-asdfghj-89-kl", "NGUTANG_DULU", order, paymentData);
        });
    }
    @Test
    void testCreatePaymentVoucherCodeWithSuccessStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData, "SUCCESS");

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testCreatePaymentVoucherCodeWithRejectedStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData, "REJECTED");

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testCreatePaymentVoucherCodeWithInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData, "EITS");
        });
    }
    @Test
    void testCreatePaymentCashOnDeliveryWithSuccessStatus() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "CASH_ON_DELIVERY", order, paymentData, "SUCCESS");

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testCreatePaymentCashOnDeliveryWithRejectedStatus() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "CASH_ON_DELIVERY", order, paymentData, "REJECTED");

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testCreatePaymentCashOnDeliveryWithInvalidStatus() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234567-asdfghj-89-kl", "CASH_ON_DELIVERY", order, paymentData, "EITS");
        });
    }
    @Test
    void testSetPaymentStatusVoucherCodeToSuccess() {
        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData);
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }
    @Test
    void testSetPaymentVoucherCodeStatusToRejected() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData);
        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }
    @Test
    void testSetPaymentVoucherCodeStatusToInvalid() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("EITS"));
    }
    @Test
    void testSetPaymentStatusCashOnDeliveryToSuccess() {
        Payment payment = new Payment("1234567-asdfghj-89-kl", "CASH_ON_DELIVERY", order, paymentData);
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }
    @Test
    void testSetPaymentCashOnDeliveryStatusToRejected() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "CASH_ON_DELIVERY", order, paymentData);
        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }
    @Test
    void testSetPaymentCashOnDeliveryStatusToInvalid() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "CASH_ON_DELIVERY", order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("EITS"));
    }
    @Test
    void testCreatePaymentWithValidVoucherCodePaymentData() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData);

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }
    @Test
    void testCreatePaymentWithVoucherCodePaymentDataNot16Characters() {
        paymentData.put("voucherCode", "ESHOP1234ABC56789");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }
    @Test
    void testCreatePaymentWithVoucherCodePaymentDataNotEshopFirst() {
        paymentData.put("voucherCode", "ASDFG1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }
    @Test
    void testCreatePaymentWithVoucherCodePaymentDataNot8Numerical() {
        paymentData.put("voucherCode", "ESHOP1234ABC567A");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "VOUCHER_CODE", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }
    @Test
    void testCreatePaymentWithValidCashOnDeliveryPaymentData() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "CASH_ON_DELIVERY", order, paymentData);

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }
    @Test
    void testCreatePaymentWithEmptyStringCashOnDeliveryPaymentData() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "");

        Payment payment = new Payment("1234567-asdfghj-89-kl", "CASH_ON_DELIVERY", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }
    @Test
    void testCreatePaymentWithNullCashOnDeliveryPaymentData() {
        paymentData.put("address", null);
        paymentData.put("deliveryFee", null);

        Payment payment = new Payment("1234567-asdfghj-89-kl", "CASH_ON_DELIVERY", order, paymentData);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

}
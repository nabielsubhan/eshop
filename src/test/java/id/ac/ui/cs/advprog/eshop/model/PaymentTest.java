package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
            Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
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

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertSame(order, payment.getOrder());
    }
    @Test
    void testCreatePaymentWithValidMethodCashOnDelivery() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("9876543-qwertyu-21-io", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData);

        assertEquals("9876543-qwertyu-21-io", payment.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIBERY.getValue(), payment.getMethod());
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

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }
    @Test
    void testCreatePaymentVoucherCodeWithRejectedStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, PaymentStatus.REJECTED.getValue());

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
    @Test
    void testCreatePaymentVoucherCodeWithInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, "EITS");
        });
    }
    @Test
    void testCreatePaymentCashOnDeliveryWithSuccessStatus() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIBERY.getValue(), payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }
    @Test
    void testCreatePaymentCashOnDeliveryWithRejectedStatus() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData, PaymentStatus.REJECTED.getValue());

        assertEquals("1234567-asdfghj-89-kl", payment.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIBERY.getValue(), payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
    @Test
    void testCreatePaymentCashOnDeliveryWithInvalidStatus() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData, "EITS");
        });
    }
    @Test
    void testSetPaymentStatusVoucherCodeToSuccess() {
        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }
    @Test
    void testSetPaymentVoucherCodeStatusToRejected() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }
    @Test
    void testSetPaymentVoucherCodeStatusToInvalid() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("EITS"));
    }
    @Test
    void testSetPaymentStatusCashOnDeliveryToSuccess() {
        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }
    @Test
    void testSetPaymentCashOnDeliveryStatusToRejected() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }
    @Test
    void testSetPaymentCashOnDeliveryStatusToInvalid() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("EITS"));
    }
    @Test
    void testCreatePaymentWithValidVoucherCodePaymentData() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }
    @Test
    void testCreatePaymentWithVoucherCodePaymentDataNot16Characters() {
        paymentData.put("voucherCode", "ESHOP1234ABC56789");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }
    @Test
    void testCreatePaymentWithVoucherCodePaymentDataNotEshopFirst() {
        paymentData.put("voucherCode", "ASDFG1234ABC5678");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }
    @Test
    void testCreatePaymentWithVoucherCodePaymentDataNot8Numerical() {
        paymentData.put("voucherCode", "ESHOP1234ABC567A");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }
    @Test
    void testCreatePaymentWithValidCashOnDeliveryPaymentData() {
        paymentData.put("address", "Citayam Raya Street No. 71");
        paymentData.put("deliveryFee", "100000");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }
    @Test
    void testCreatePaymentWithEmptyStringCashOnDeliveryPaymentData() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "");

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }
    @Test
    void testCreatePaymentWithNullCashOnDeliveryPaymentData() {
        paymentData.put("address", null);
        paymentData.put("deliveryFee", null);

        Payment payment = new Payment("1234567-asdfghj-89-kl", PaymentMethod.CASH_ON_DELIBERY.getValue(), order, paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

}
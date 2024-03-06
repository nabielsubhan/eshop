package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.setMethod(method);

        if (order == null) {
            throw new IllegalArgumentException();
        } else {
            this.order = order;
        }

        this.setPaymentData(paymentData);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status){
        this.id = id;
        this.setMethod(method);
        this.paymentData = paymentData;

        if (order == null) {
            throw new IllegalArgumentException();
        } else {
            this.order = order;
        }

        this.setStatus(status);
    }

    public void setStatus(String status) {
        String[] statusList = {"SUCCESS", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else if (status.equals("SUCCESS")) {
            this.status = status;
            this.order.setStatus("SUCCESS");
        } else if (status.equals("REJECTED")) {
            this.status = status;
            this.order.setStatus("FAILED");
        }
    }

    public void setMethod(String method) {
        String[] methodList = {"VOUCHER_CODE", "CASH_ON_DELIVERY"};
        if (Arrays.stream(methodList).noneMatch(item -> (item.equals(method)))) {
            throw new IllegalArgumentException();
        } else {
            this.method = method;
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        boolean valid = true;
        if (paymentData.size() == 1) {
            valid = checkPaymentDataVoucherCode(paymentData);
        } else {
            valid = checkPaymentDataCashOnDelivery(paymentData);
        }

        if (valid) {
            this.paymentData = paymentData;
            this.setStatus("SUCCESS");
        } else {
            this.setStatus("REJECTED");
            this.order.setStatus("FAILED");
        }
    }

    public boolean checkPaymentDataVoucherCode(Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
            int numericalCount = 0;
            for (int i = 0; i < voucherCode.length(); i++) {
                if (Character.isDigit(voucherCode.charAt(i))) {
                    numericalCount++;
                }
            }
            return numericalCount == 8;
        } else {
            return false;
        }
    }

    public boolean checkPaymentDataCashOnDelivery(Map<String, String> paymentData) {
        return paymentData.get("address") != null && paymentData.get("deliveryFee") != null &&
                !paymentData.get("address").isEmpty() && !paymentData.get("deliveryFee").isEmpty();
    }
}
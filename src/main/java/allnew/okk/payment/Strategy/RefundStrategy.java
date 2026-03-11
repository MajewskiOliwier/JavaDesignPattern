package allnew.okk.payment.Strategy;

import allnew.okk.payment.Adapter.PaymentGateway;

public interface RefundStrategy {
    boolean executeRefund(PaymentGateway gateway, String transactionID);
    float getTotalPayedAmount();
    void addTotalPayedAmount(float amount);
}

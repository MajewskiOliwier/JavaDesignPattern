package allnew.okk.payment.Strategy;

import allnew.okk.payment.Adapter.PaymentGateway;

public class FullRefundStrategy implements RefundStrategy{
    float fullAmount = 0;

    public FullRefundStrategy(float payedAmount){
        fullAmount = payedAmount;
    }

    @Override
    public boolean executeRefund(PaymentGateway gateway, String transactionID) {
        System.out.println("Proceding with full refund for transaction with ID: "+transactionID);
        return gateway.refund(transactionID, this);
    }

    @Override
    public float getTotalPayedAmount() {
        return fullAmount;
    }

    @Override
    public void addTotalPayedAmount(float amount) {
        fullAmount += amount;
    }
}

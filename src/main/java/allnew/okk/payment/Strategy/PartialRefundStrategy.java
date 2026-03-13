package allnew.okk.payment.Strategy;

import allnew.okk.payment.Adapter.PaymentGateway;

//Week 6, Pattern  Strategy
public class PartialRefundStrategy implements RefundStrategy {
    float totalAmountPayed = 0;
    float amountToRefund = 0;

    public PartialRefundStrategy(float amountToRefund){
        this.amountToRefund = amountToRefund;
    }

    @Override
    public boolean executeRefund(PaymentGateway gateway, String transactionID) {
        System.out.println("Executing partial refund for "+amountToRefund+" for transaction with ID: "+transactionID);

        return gateway.refund(transactionID, this);
    }

    @Override
    public float getTotalPayedAmount() {
        return amountToRefund;
    }

    @Override
    public void addTotalPayedAmount(float amount) {
        totalAmountPayed += amount;
    }
}
//End Week 6, Pattern  Strategy
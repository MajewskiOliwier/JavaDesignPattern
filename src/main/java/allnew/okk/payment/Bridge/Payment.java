package allnew.okk.payment.Bridge;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.payment.Adapter.PaymentGateway;
import allnew.okk.payment.Strategy.RefundStrategy;

abstract class Payment {
    protected PaymentGateway gateway;
    protected RefundStrategy refundStrategy;

    protected Payment(PaymentGateway paymentGateway, RefundStrategy refundStrategy){
        this.gateway = paymentGateway;
        this.refundStrategy = refundStrategy;
    }

    public abstract boolean pay(float amount,String currency, AccountDisplayable customer);

    public boolean refund(String transactionID, float amount){
        return refundStrategy.executeRefund(gateway, transactionID);
    };

    public void setNewRefundStrategy(RefundStrategy refundStrategy){
        this.refundStrategy = refundStrategy;
    }

    public void switchPaymentGateway(PaymentGateway newPaymentGateway){
        this.gateway = newPaymentGateway;
    }

    public boolean refund(String transactionID) {
        return refundStrategy.executeRefund(gateway, transactionID);
    }
}

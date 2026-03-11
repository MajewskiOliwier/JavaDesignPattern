package allnew.okk.payment.Adapter;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.payment.Strategy.RefundStrategy;

//Interfejs adaptera dla systemu płatnosci
public interface PaymentGateway {
    boolean processPayment(float amount, AccountDisplayable customer, String currency);
    boolean refund(String transactionID, RefundStrategy refundStrategy);
    boolean refund(String transactionID, RefundStrategy refundStrategy, float amount);
    String getTransactionStatus(String transactionID);
}

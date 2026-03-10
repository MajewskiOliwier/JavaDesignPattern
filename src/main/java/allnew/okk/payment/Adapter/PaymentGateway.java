package allnew.okk.payment.Adapter;

import allnew.okk.account.Adapter.AccountDisplayable;

//Interfejs adaptera dla systemu płatnosci
public interface PaymentGateway {
    boolean processPayment(float amount, AccountDisplayable customer, String currency);
    boolean refund(String transactionID, float amount);
    String getTransactionStatus(String transactionID);
}

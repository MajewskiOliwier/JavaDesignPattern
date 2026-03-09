package allnew.okk.payment.Adapter;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.account.Prototype.BaseAccount;

public interface PaymentGateway {
    boolean processPayment(float amount, AccountDisplayable customer, String currency);
    boolean refund(String transactionID, float amount);
    String getTransactionStatus(String transactionID);
}

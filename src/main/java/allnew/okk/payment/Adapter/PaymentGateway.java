package allnew.okk.payment.Adapter;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.payment.Strategy.RefundStrategy;


// Week 3, Wzorzec Adapter 1
// Week 8 Dependency Inversion Oliwier
//Interfejs adaptera dla systemu płatnosci
public interface PaymentGateway {
    // interface defining the abstraction for all payment gateways high-level modules depend on this and  not on concrete classes
    boolean processPayment(float amount, AccountDisplayable customer, String currency);
    boolean refund(String transactionID, RefundStrategy refundStrategy);
    boolean refund(String transactionID, RefundStrategy refundStrategy, float amount);
    String getTransactionStatus(String transactionID);
}
//End Week 8 Dependency Inversion Oliwier
//End Week 3, Wzorzec Composite 3
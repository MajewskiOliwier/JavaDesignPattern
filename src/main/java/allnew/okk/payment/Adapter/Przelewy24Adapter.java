package allnew.okk.payment.Adapter;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.payment.Service.Przelewy24Service;
import allnew.okk.payment.Strategy.RefundStrategy;

// Week 3, Pattern  Adapter 1
//konkretna implementacja adaptera dla PAYU
public class Przelewy24Adapter implements PaymentGateway {
    private Przelewy24Service przelewy24Service;
    private String lastTranscationID;

    public Przelewy24Adapter(){
        this.przelewy24Service = new Przelewy24Service();
    }

    //dla Przelewy24 przekazuje po interfejsie cały Adapter AccountDisplayable i sam serwis wywoła potrzebne metody
    @Override
    public boolean processPayment(float amount, AccountDisplayable customer, String currency) {

        //dla przelewy24 przekazuje po interfejsie Adapter AccountDisplayable po czym serwis robi resztę
        String transactionID = przelewy24Service.makeTransaction(
                amount,
                currency,
                customer
        );

        this.lastTranscationID = transactionID;
        return transactionID != null && !transactionID.isEmpty();
    }

    @Override
    public boolean refund(String transactionID, RefundStrategy refundStrategy) {
        System.out.println("Przelewy24: Full refund for transaction: " + transactionID);

        return przelewy24Service.returnMoney(transactionID, refundStrategy.getTotalPayedAmount());
    }

    @Override
    public boolean refund(String transactionID, RefundStrategy refundStrategy, float amount) {
        amount = Math.min(amount, refundStrategy.getTotalPayedAmount());

        System.out.println("PayU: Partial refund of " + amount + " for transaction: " + transactionID);

        return przelewy24Service.returnMoney(transactionID, amount);
    }

    @Override
    public String getTransactionStatus(String transactionID) {
        return przelewy24Service.checkStatus(transactionID);
    }
}
//End Week 3, Pattern  Adapter 1
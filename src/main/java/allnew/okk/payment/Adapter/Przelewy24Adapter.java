package allnew.okk.payment.Adapter;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.payment.Adapter.Service.PayUService;
import allnew.okk.payment.Adapter.Service.Przelewy24Service;

public class Przelewy24Adapter implements PaymentGateway {
    private Przelewy24Service przelewy24Service;
    private String lastTranscationID;

    public Przelewy24Adapter(){
        this.przelewy24Service = new Przelewy24Service();
    }

    @Override
    public boolean processPayment(float amount, AccountDisplayable customer, String currency) {
        String transactionID = przelewy24Service.makeTransaction(
                amount,
                currency,
                customer
        );

        this.lastTranscationID = transactionID;
        return transactionID != null && !transactionID.isEmpty();
    }

    @Override
    public boolean refund(String transactionID, float amount) {
        return przelewy24Service.returnMoney(transactionID, amount);
    }

    @Override
    public String getTransactionStatus(String transactionID) {
        return przelewy24Service.checkStatus(transactionID);
    }
}

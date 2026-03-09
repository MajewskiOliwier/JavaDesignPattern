package allnew.okk.payment.Adapter;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.payment.Adapter.Service.PayUService;


public class PayUAdapter implements  PaymentGateway{
    private PayUService payUService;
    private String lastTranscationID;

    public PayUAdapter(){
        this.payUService = new PayUService();
    }

    @Override
    public boolean processPayment(float amount, AccountDisplayable customer, String currency) {
        String transactionID = payUService.makeTransaction(
                amount,
                currency,
                customer.GetDisplayName(),
                customer.GetIdentifier()
        );

        this.lastTranscationID = transactionID;
        return transactionID != null && !transactionID.isEmpty();
    }

    @Override
    public boolean refund(String transactionID, float amount) {
        return payUService.returnMoney(transactionID, amount);
    }

    @Override
    public String getTransactionStatus(String transactionID) {
        return payUService.checkStatus(transactionID);
    }
}

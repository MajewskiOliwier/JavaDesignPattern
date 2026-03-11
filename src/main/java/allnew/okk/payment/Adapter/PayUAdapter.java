package allnew.okk.payment.Adapter;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.payment.Service.PayUService;
import allnew.okk.payment.Strategy.RefundStrategy;

//konkretna implementacja adaptera dla PAYU
public class PayUAdapter implements PaymentGateway {
    private PayUService payUService;
    private String lastTranscationID;

    public PayUAdapter(){
        this.payUService = new PayUService();
    }

    //metoda implementujaca proces platnosci
    @Override
    public boolean processPayment(float amount, AccountDisplayable customer, String currency) {

        //dla PAYU przekazuje po interfejsie Adaptera AccountDisplayable nazwe i identyfikator dla tego serwisu
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
    public boolean refund(String transactionID, RefundStrategy refundStrategy) {
        System.out.println("PayU: Full refund for transaction: " + transactionID);
        return payUService.returnMoney(transactionID, refundStrategy.getTotalPayedAmount());
    }

    @Override
    public boolean refund(String transactionID, RefundStrategy refundStrategy, float amount) {
        amount = Math.min(amount, refundStrategy.getTotalPayedAmount());

        System.out.println("PayU: Partial refund of " + amount + " for transaction: " + transactionID);
        return payUService.returnMoney(transactionID, amount);
    }

    @Override
    public String getTransactionStatus(String transactionID) {
        return payUService.checkStatus(transactionID);
    }
}

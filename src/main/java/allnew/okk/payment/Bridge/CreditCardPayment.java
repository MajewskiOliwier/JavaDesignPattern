package allnew.okk.payment.Bridge;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.payment.Adapter.PaymentGateway;
import allnew.okk.payment.Strategy.RefundStrategy;

// Week 3, Pattern Bridge 1
public class CreditCardPayment extends Payment{
    String cardNumber;
    String cvv;

    public CreditCardPayment(PaymentGateway paymentGateway, RefundStrategy refundStrategy, String cardNumber, String cvv){
        super(paymentGateway, refundStrategy);
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(float amount, String currency, AccountDisplayable customer) {
        System.out.println("Processing credit card payment");
        refundStrategy.addTotalPayedAmount(amount);
        return gateway.processPayment(amount,customer, currency);
    }
}
//End Week 3, Pattern Bridge 1
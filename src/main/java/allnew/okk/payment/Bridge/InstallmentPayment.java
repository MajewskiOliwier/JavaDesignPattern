package allnew.okk.payment.Bridge;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.payment.Adapter.PaymentGateway;
import allnew.okk.payment.Strategy.RefundStrategy;

import java.sql.Ref;

// Tydzień 3, Wzorzec Most 1
public class InstallmentPayment extends Payment{
    float amountLeft;
    float originalAmount;
    int installmentsLeft;
    int originalInstallmentsLeft;

    public InstallmentPayment(PaymentGateway paymentGateway, RefundStrategy refundStrategy, float originalAmount, int originalInstallmentsLeft){
        super(paymentGateway, refundStrategy);

        refundStrategy.addTotalPayedAmount(originalAmount);
        this.originalAmount = originalAmount;
        this.amountLeft = originalAmount;

        this.installmentsLeft = originalInstallmentsLeft;
        this.originalInstallmentsLeft = originalInstallmentsLeft;
    }

    //Po utworzeniu InstallmentPayment i określeniu orginalnej kwoty do zapłaty obliczana jest pozostała kwota do zapłaty
    @Override
    public boolean pay(float amount, String currency, AccountDisplayable customer) {
        amountLeft -= amount;
        System.out.println("Processing installment nr "+getCurrentInstallment());
        return gateway.processPayment(amount,customer, currency);
    }

    private int getCurrentInstallment(){
        return originalInstallmentsLeft-installmentsLeft;
    }

    private float getPayedAmount(){
        return originalAmount - amountLeft;
    }
}
//Koniec Tydzień 3, Wzorzec Most 1

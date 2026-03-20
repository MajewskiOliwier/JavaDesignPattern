package allnew.okk.account.Prototype;

import allnew.okk.account.State.AccountState;
import allnew.okk.account.State.ActiveState;
import allnew.okk.account.State.BannedState;
import allnew.okk.account.State.SuspendedState;
import allnew.okk.payment.Adapter.PaymentGateway;

// Week 2, Pattern Prototype 1
//Jest to klasa abstrakcji a nie interfejs z uwagi na istnienie wspólnych pol i metod dla oby typów kont
public abstract class BaseAccount {
    protected String email;
    protected String password;
    protected String adress;
    protected String phone;

    // Week 6, Pattern State 1 Oliwier Majewski
    private AccountState accountState = new ActiveState();

    public AccountState getAccountState() { return accountState; }

    public void activate()  { this.accountState = new ActiveState(); }
    public void suspend()   { this.accountState = new SuspendedState(); }
    public void ban()       { this.accountState = new BannedState(); }

    public boolean canPlaceOrder() { return accountState.canPlaceOrder(); }
    public boolean canLogin()      { return accountState.canLogin(); }
    // End Week 6, Pattern State 5 Oliwier Majewski

    //Week 4, Pattern Proxy 1
    protected PaymentGateway paymentGateway;
    public PaymentGateway getPaymentGateway() { return paymentGateway; }

    public void setPaymentGateway(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    //End Week 4, Pattern Proxy 1

    protected BaseAccount(String email, String password, String adress, String phone){
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.phone = phone;
    }

    public String GetEmail() { return email; }
    public String GetPassword() { return password; }
    public String GetAdress() { return adress; }
    public String GetPhone() { return phone; }
    // End Week 2, Pattern Prototype 1

    @Override
    public String toString(){
        return "email: "+email+", password: "+password;
    }
}
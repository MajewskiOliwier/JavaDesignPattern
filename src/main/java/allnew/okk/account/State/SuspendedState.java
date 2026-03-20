package allnew.okk.account.State;

// Week 6, Pattern State 1 Oliwier Majewski
// SuspendedState blocks placing order by the Account in that state
public class SuspendedState implements AccountState {

    @Override
    public boolean canPlaceOrder() {
        System.out.println("State: account is suspended — order blocked");
        return false;
    }

    @Override
    public boolean canLogin() {
        return true;
    }

    @Override
    public String getStateName() {
        return "SUSPENDED";
    }
}
// End Week 6, Pattern State 1 Oliwier Majewski
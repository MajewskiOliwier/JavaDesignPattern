package allnew.okk.account.State;

// Week 6, Pattern State 1 Oliwier Majewski
// ActiveState provides all the Actions for the account
public class ActiveState implements AccountState {

    @Override
    public boolean canPlaceOrder() {
        return true;
    }

    @Override
    public boolean canLogin() {
        return true;
    }

    @Override
    public String getStateName() {
        return "ACTIVE";
    }
}
// End Week 6, Pattern State 1 Oliwier Majewski
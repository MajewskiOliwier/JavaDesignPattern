package allnew.okk.account.State;

// Week 6, Pattern State 1 Oliwier Majewski
// BannedState blocks all actions for the user
public class BannedState implements AccountState {

    @Override
    public boolean canPlaceOrder() {
        System.out.println("State: account is banned — order blocked");
        return false;
    }

    @Override
    public boolean canLogin() {
        System.out.println("State: account is banned — login blocked");
        return false;
    }

    @Override
    public String getStateName() {
        return "BANNED";
    }
}
// End Week 6, Pattern State 1 Oliwier Majewski
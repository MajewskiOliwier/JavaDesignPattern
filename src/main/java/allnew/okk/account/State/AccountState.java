package allnew.okk.account.State;

// Week 6, Pattern State 1 Oliwier Majewski
// AccountState interface defines what each state allows
public interface AccountState {
    boolean canPlaceOrder();
    boolean canLogin();
    String getStateName();
}
// End Week 6, Pattern State 1 Oliwier Majewski
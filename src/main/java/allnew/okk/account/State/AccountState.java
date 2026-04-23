package allnew.okk.account.State;

// Week 6, Pattern State 1 Oliwier Majewski
// AccountState interface defines what each state allows
// Week 8, Segragated Interface 2 Oliwier Majewski
// Segregated interface nr.2 from Bloated AccountManager
public interface AccountState {
    boolean canPlaceOrder();
    boolean canLogin();
    String getStateName();
}
// End Week 8, Segragated Interface 2 Oliwier Majewski
// End Week 6, Pattern State 1 Oliwier Majewski
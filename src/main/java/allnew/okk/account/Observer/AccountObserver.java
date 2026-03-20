package allnew.okk.account.Observer;

import allnew.okk.account.Prototype.BaseAccount;

// Week 6, Pattern Observer 1 Oliwier Majewski
// Interface that enables the listeners of the events for Account to respond to them
public interface AccountObserver {
    void OnAccountEvent(BaseAccount account, AccountEvent event);
}
// End Week 6, Pattern Observer 1 Oliwier Majewski

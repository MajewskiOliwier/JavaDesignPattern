package allnew.okk.account.Observer;

import allnew.okk.account.Prototype.BaseAccount;

// Week 6, Pattern Observer 1 Oliwier Majewski
// Interface that enables the listeners of the events for Account to respond to them
// Week 8, Segragated Interface 1 Oliwier Majewski
// Segregated interface nr.1 from Bloated AccountManager
public interface AccountObserver {
    void OnAccountEvent(BaseAccount account, AccountEvent event);
}
// End Week 8, Segragated Interface 1 Oliwier Majewski
// End Week 6, Pattern Observer 1 Oliwier Majewski

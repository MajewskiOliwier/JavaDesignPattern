package allnew.okk.account.Observer;


import allnew.okk.account.Prototype.BaseAccount;

// Week 10, Functional Interface 1 Oliwier Majewski
// functional Interface to filter which account should receive the event notificiation
@FunctionalInterface
public interface AccountEventFilter {
    boolean test(BaseAccount account, AccountEvent event);
}
// End Week 10, Functional Interface 1 Oliwier Majewski
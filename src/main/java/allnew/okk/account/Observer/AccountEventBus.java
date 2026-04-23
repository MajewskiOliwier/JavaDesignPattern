package allnew.okk.account.Observer;

import allnew.okk.account.Prototype.BaseAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

// Week 6, Pattern Observer 1 Oliwier Majewski
// AccountEventBus is a Singleton class that holds observer list in order to publish events
public class AccountEventBus {
    private static AccountEventBus instance;
    private final List<AccountObserver> observers = new ArrayList<>();

    // Week 10, Functional Interface 1 Oliwier Majewski
    // Lambda implementation of AccountEventFilter - passes only ONBAN events for non-null accounts
    private AccountEventFilter activeFilter = (account, event) -> account != null && event == AccountEvent.ONBAN;
    // End Week 10, Functional Interface 1 Oliwier Majewski

    private AccountEventBus() {}

    public static AccountEventBus GetInstance() {
        if (instance == null) {
            instance = new AccountEventBus();
        }

        return instance;
    }

    public void Register(AccountObserver observer) {
        observers.add(observer);
    }

    public void Unregister(AccountObserver observer) {
        observers.remove(observer);
    }

    // Week 10, Stream processing 1 Oliwier Majewski
    // Publish notifies all observers using stream pipeline with filter and forEach
    public void Publish(BaseAccount account, AccountEvent event) {
        observers.stream()
                .filter(obs -> obs != null)
                .forEach(obs -> obs.OnAccountEvent(account, event));
    }
    // End Week 10, Stream processing 1 Oliwier Majewski

    // Week 10, Functional Interface 1 Oliwier Majewski
    // PublishFiltered uses the AccountEventFilter functional interface (lambda) to decide
    // whether the event should be dispatched at all before notifying observers
    public void PublishFiltered(BaseAccount account, AccountEvent event) {
        if (activeFilter.test(account, event)) {
            observers.stream()
                    .filter(obs -> obs != null)
                    .forEach(obs -> obs.OnAccountEvent(account, event));
        }
    }

    // Allows replacing the filter at runtime with any lambda matching AccountEventFilter
    public void SetFilter(AccountEventFilter filter) {
        this.activeFilter = filter;
    }
    // End Week 10, Functional Interface 1 Oliwier Majewski

    // Week 10, Predicate and Function 1 Oliwier Majewski
    // Predicate checks whether a given observer list has any registered observers
    public static final Predicate<List<AccountObserver>> HasObservers =
            list -> list != null && !list.isEmpty();

    // Function maps a BaseAccount to a loggable string summary of the account
    public static final Function<BaseAccount, String> AccountToLogString =
            account -> account == null
                    ? "[null account]"
                    : "Account[email=" + account.getEmail() + ", status banned =" + account.getIsBanned() + "]";

    // Example usage of Predicate and Function together:
    // Logs account info only when there are active observers
    public void LogPublishAttempt(BaseAccount account, AccountEvent event) {
        if (HasObservers.test(observers)) {
            String summary = AccountToLogString.apply(account);
            System.out.println("Publishing event " + event + " for " + summary
                    + " to " + observers.size() + " observer(s).");
        } else {
            System.out.println("No observers registered, skipping publish.");
        }
    }
    // End Week 10, Predicate and Function 1 Oliwier Majewski

    public void ClearObservers() {
        observers.clear();
    }

    public int GetObserversCount() {
        return observers.size();
    }
}
// End Week 6, Pattern Observer 1 Oliwier Majewski
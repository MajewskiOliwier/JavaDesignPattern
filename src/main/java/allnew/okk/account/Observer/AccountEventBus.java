package allnew.okk.account.Observer;

import allnew.okk.account.Prototype.BaseAccount;

import java.util.ArrayList;
import java.util.List;

// Week 6, Pattern Observer 1 Oliwier Majewski
// AccountEventBus is a Singleton class that holds observer list in order to publish events
public class AccountEventBus {
    private static AccountEventBus instance;
    private final List<AccountObserver> observers = new ArrayList<>();

    private AccountEventBus(){}

    public static AccountEventBus GetInstance(){
        if(instance == null){
            instance = new AccountEventBus();
        }

        return instance;
    }

    //this method adds the class that implements the AccountObserver interface to the list, that list will later be used to send notification to
    public void Register(AccountObserver observer){
        observers.add(observer);
    }

    //Removes specific observer from the list
    public void Unregister(AccountObserver observer){
        observers.remove(observer);
    }

    //this method send notification to the observing class
    public void Publish(BaseAccount account, AccountEvent event){
        observers.forEach(obs -> obs.OnAccountEvent(account, event));
    }

    //method used for testing purposes
    public void ClearObservers(){
        observers.clear();
    }

    public int GetObserversCount(){
        return observers.size();
    }
}
// Week 6, Pattern Observer 1 Oliwier Majewski

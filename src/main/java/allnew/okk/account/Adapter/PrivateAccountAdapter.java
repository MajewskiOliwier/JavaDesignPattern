package allnew.okk.account.Adapter;

import allnew.okk.account.Mediator.LoyaltyEligilble;
import allnew.okk.account.Prototype.PrivateAccount;

// Tydzień 3, Wzorzec Adapter 2
public class PrivateAccountAdapter implements AccountDisplayable, LoyaltyEligilble {
    private PrivateAccount privateAccount;

    public PrivateAccountAdapter(PrivateAccount account){
        this.privateAccount = account;
    }

    //implementacje metod wykorzystanych w adapterze platnosci w celu unikniecia użycia instanceOf w celu wywołania metody unikalnej dla tej klasy
    @Override
    public String GetDisplayName() {
        return privateAccount.GetName() + " "+privateAccount.GetSurname();
    }

    @Override
    public String GetIdentifier() {
        return privateAccount.GetEmail();
    }

    @Override
    public boolean hasValidPaymentMethod() {
        return privateAccount.getPaymentGateway() != null;
    }

    @Override
    public void addLoyaltyPoints(int points) {
        privateAccount.addLoyaltyPoints(points);
    }

    @Override
    public int getLoyaltyPoints() {
        return privateAccount.getLoyaltyPoints();
    }
}
//Koniec Tydzień 3, Wzorzec Adapter 2
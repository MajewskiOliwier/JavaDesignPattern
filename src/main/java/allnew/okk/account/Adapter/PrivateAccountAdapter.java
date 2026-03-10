package allnew.okk.account.Adapter;

import allnew.okk.account.Prototype.PrivateAccount;

public class PrivateAccountAdapter implements AccountDisplayable{
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
    public String GetAccountType() {
        return "PRIVATE";
    }
}

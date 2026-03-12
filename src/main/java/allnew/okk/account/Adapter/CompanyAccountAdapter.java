package allnew.okk.account.Adapter;

import allnew.okk.account.Prototype.CompanyAccount;

// Tydzień 3, Wzorzec Adapter 2
//klasa implementujaca interfejs adaptera
public class CompanyAccountAdapter implements AccountDisplayable{
    private CompanyAccount companyAccount;

    public CompanyAccountAdapter(CompanyAccount companyAccount){
        this.companyAccount = companyAccount;
    }

    //implementacje metod wykorzystanych w adapterze platnosci w celu unikniecia użycia instanceOf w celu wywołania metody unikalnej dla tej klasy
    @Override
    public String GetDisplayName() {
        return companyAccount.GetLegalName();
    }

    @Override
    public String GetIdentifier() {
        return "NIP: "+companyAccount.GetVatNumber();
    }

    @Override
    public String GetAccountType() {
        return "COMPANY";
    }
}
// Koniec Tydzień 3, Wzorzec Adapter 2
package allnew.okk.account.Adapter;

import allnew.okk.account.Prototype.CompanyAccount;

public class CompanyAccountAdapter implements AccountDisplayable{
    private CompanyAccount companyAccount;

    public CompanyAccountAdapter(CompanyAccount companyAccount){
        this.companyAccount = companyAccount;
    }

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

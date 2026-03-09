package allnew.okk.account.Builder;

import allnew.okk.account.Prototype.CompanyAccount;

public class CompanyAccountBuilder extends AccountBuilder<CompanyAccountBuilder>{
    private String legalName;
    private String vatNumber;

    @Override
    protected CompanyAccountBuilder Self() {
        return this;
    }

    public CompanyAccountBuilder SetLegalName(String legalName){
        this.legalName = legalName;
        return this;
    }

    public CompanyAccountBuilder SetVatNumber(String vatNumber){
        this.vatNumber = vatNumber;
        return this;
    }
    
    public CompanyAccount Build(){
        //to validate
       return new CompanyAccount(email, password, legalName, vatNumber, adress, phone);
    }
}
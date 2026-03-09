package allnew.okk.account.Builder;

import allnew.okk.account.Prototype.CompanyAccount;

public class CompanyAccountBuilder extends AccountBuilder<CompanyAccountBuilder>{
    private String legalName;
    private String vatNumber;

    @Override
    protected CompanyAccountBuilder self() {
        return this;
    }

    public CompanyAccountBuilder setLegalName(String legalName){
        this.legalName = legalName;
        return this;
    }

    public CompanyAccountBuilder setVatNumber(String vatNumber){
        this.vatNumber = vatNumber;
        return this;
    }
    
    public CompanyAccount build(){
        //to validate
       return new CompanyAccount(email, password, legalName, vatNumber);
    }
}
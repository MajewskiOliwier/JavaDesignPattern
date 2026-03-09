package allnew.okk.account.Prototype;

import allnew.okk.account.Builder.CompanyAccountBuilder;

public class CompanyAccount extends BaseAccount {
    private String legalName;
    private String vatNumber;

    public String GetLegalName() { return legalName; }
    public String GetVatNumber() { return vatNumber; }

    public CompanyAccount(String email, String password, String legalName, String vatNumber){
        super(email, password);
        this.legalName = legalName;
        this.vatNumber = vatNumber;
    }

    @Override
    public String toString(){
        return "email: "+email+" password: "+password+" legalname: "+legalName+" vatNumber:"+vatNumber;
    }

    @Override
    public CompanyAccountBuilder clone() {
        CompanyAccountBuilder builder = new CompanyAccountBuilder();
        builder.SetEmail(this.email);
        builder.SetPassword(this.password);
        builder.setLegalName(this.legalName);
        builder.setVatNumber(this.vatNumber);
        return builder;
    }
}
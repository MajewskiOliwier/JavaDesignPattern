package allnew.okk;

public class CompanyAccount extends BaseAccount{
    private String legalName;
    private String vatNumber;

    public String getLegalName() { return legalName; }
    public String getVatNumber() { return vatNumber; }

    public CompanyAccount(String email, String password, String legalName, String vatNumber){
        super(email, password);
        this.legalName = legalName;
        this.vatNumber = vatNumber;
    }

    public CompanyAccount(CompanyAccount ca){
        super(ca.email, ca.password);
        this.legalName = ca.legalName;
        this.vatNumber = ca.vatNumber;
    }

    @Override
    public String toString(){
        return "email: "+email+" password: "+password+" legalname: "+legalName+" vatNumber:"+vatNumber;
    }

    @Override
    public CompanyAccountBuilder clone() {
        CompanyAccountBuilder builder = new CompanyAccountBuilder();
        builder.email = this.email;
        builder.password = this.password;
        builder.setLegalName(this.legalName);
        builder.setVatNumber(this.vatNumber);
        return builder;
    }
}
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

    @Override
    public String toString(){
        return "email: "+email+" password: "+password+" legalname: "+legalName+" vatNumber:"+vatNumber;
    }
}
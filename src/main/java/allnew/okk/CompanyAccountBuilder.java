public class CompanyAccountBuilder extends AccountBuilder{
    private String legalName;
    private String vatNumber;
    
    public CompanyAccountBuilder(){
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
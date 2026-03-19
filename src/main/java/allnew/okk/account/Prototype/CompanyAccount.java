package allnew.okk.account.Prototype;

import allnew.okk.account.Builder.CompanyAccountBuilder;

//

// Week 2, Pattern Prototype 2
//klasa reprezentujaca konto firmowe
public class CompanyAccount extends BaseAccount {
    private String legalName;
    private String vatNumber;

    public String GetLegalName() { return legalName; }
    public String GetVatNumber() { return vatNumber; }

    public CompanyAccount(String email, String password, String legalName, String vatNumber, String adress, String phone){
        super(email, password, adress, phone);
        this.legalName = legalName;
        this.vatNumber = vatNumber;
    }
    // End Week 2, Pattern Prototype 2

    @Override
    public String toString(){
        return "email: "+email+" password: "+password+" legalname: "+legalName+" vatNumber:"+vatNumber;
    }

    // Week 2, Pattern Prototype 3
    //Metoda ta udostepnia metoda umozliwiajaca sklonowanie Konta firmowego tzn. np zrobienie podkonta z innym email i hasłem, zwraca builder który umozliwa nam zamiane tych poszczególnych danych
    @Override
    public CompanyAccountBuilder clone() {
        CompanyAccountBuilder builder = new CompanyAccountBuilder();
        builder.SetEmail(this.email);
        builder.SetPassword(this.password);
        builder.SetAdress(this.adress);
        builder.SetPhone(this.phone);
        builder.SetLegalName(this.legalName);
        builder.SetVatNumber(this.vatNumber);
        return builder;
    }
    // End Week 2, Pattern Prototype 3
}
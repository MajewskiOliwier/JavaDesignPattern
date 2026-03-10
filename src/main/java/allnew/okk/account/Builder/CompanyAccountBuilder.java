package allnew.okk.account.Builder;

import allnew.okk.account.Prototype.CompanyAccount;

//klasa implementujaca klase abstrakcji dla której parametr generyczny T jest CompanyAccountBuilder (ta klasa)
public class CompanyAccountBuilder extends AccountBuilder<CompanyAccountBuilder>{
    private String legalName;
    private String vatNumber;

    //metoda zwracająca tą klase, jest wykorzystywana w metodach bazowych klasy AccountBuilder by zwracały one klase CompanyAccountBuilder
    @Override
    protected CompanyAccountBuilder Self() {
        return this;
    }

    //metoda typu setter zwracajaca tą klase
    public CompanyAccountBuilder SetLegalName(String legalName){
        this.legalName = legalName;
        return this;
    }

    //metoda typu setter zwracajaca tą klase
    public CompanyAccountBuilder SetVatNumber(String vatNumber){
        this.vatNumber = vatNumber;
        return this;
    }

    //metoda finalizujaca budowanie przy uzyciu wzorca builder, zwraca utworzone nowe konto firmowe
    public CompanyAccount Build(){
       return new CompanyAccount(email, password, legalName, vatNumber, adress, phone);
    }
}
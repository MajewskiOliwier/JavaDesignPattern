package allnew.okk.account.Builder;

import allnew.okk.account.Prototype.PrivateAccount;

// Week 2, Pattern Builder 2
//klasa implementujaca klase abstrakcji dla której parametr generyczny T jest PrivateAccountBuilder (ta klasa)
public class PrivateAccountBuilder extends AccountBuilder<PrivateAccountBuilder>{
    private String name;
    private String surname;

    //metoda zwracająca tą klase, jest wykorzystywana w metodach bazowych klasy AccountBuilder by zwracały one klase PrivateAccountBuilder
    @Override
    protected PrivateAccountBuilder Self() {
        return this;
    }

    //metoda typu setter zwracajaca tą klase
    public PrivateAccountBuilder Name(String name){
        this.name = name;
        return this;
    }

    //metoda typu setter zwracajaca tą klase
    public PrivateAccountBuilder Surname(String surname){
        this.surname = surname;
        return this;
    }

    //metoda finalizujaca budowanie przy uzyciu wzorca builder, zwraca utworzone nowe konto prywatne
    public PrivateAccount build(){
        //to validate
       return new PrivateAccount(email, password, name, surname, adress, phone);
    }
}
// Week 2, Pattern Builder 2
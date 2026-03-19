package allnew.okk.account.Builder;

// Week 2, Pattern Builder 3
//Abstrakcyjna klasa builder
public abstract class AccountBuilder <T extends  AccountBuilder<T>>{
    protected String email;
    protected String password;
    protected String adress;
    protected String phone;

    //metoda abstrakcyjna potrzebna do zwrócenia konkretnego typu buildera (CompanyAccountBuilder lub PrivateAccountBuilder)
    protected abstract T Self();

    //metoda ustawiająca Email dla konkretnego buildera, zwraca generyczne T w celu umożliwienia wywołania metod unikalnych dla typu T
    public T Email(String email){
        this.email = email;
        return Self();
    }

    //metoda ustawiająca Hasło dla konkretnego buildera, zwraca generyczne T w celu umożliwienia wywołania metod unikalnych dla typu T
    public T Password(String password){
        this.password = password;
        return Self();
    }

    //metoda ustawiająca Adres dla konkretnego buildera, zwraca generyczne T w celu umożliwienia wywołania metod unikalnych dla typu T
    public T Adress(String adress){
        this.adress = adress;
        return Self();
    }

    //metoda ustawiająca Telefon dla konkretnego buildera, zwraca generyczne T w celu umożliwienia wywołania metod unikalnych dla typu T
    public T Phone(String phone){
        this.phone = phone;
        return Self();
    }

    //Metoda typu setter dla emaila
    public void SetEmail(String newEmail){
        this.email = newEmail;
    }

    //Metoda typu setter dla hasla
    public void SetPassword(String newPassword){
        this.password = newPassword;
    }

    //Metoda typu setter dla adrres
    public void SetAdress(String newAdress){
        this.adress = newAdress;
    }

    //Metoda typu setter dla phone
    public void SetPhone(String newPhone){
        this.phone = newPhone;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
// End Week 2, Pattern Builder 3
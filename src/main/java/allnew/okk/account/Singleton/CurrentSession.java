package allnew.okk.account.Singleton;

import allnew.okk.account.Prototype.BaseAccount;
import allnew.okk.account.Prototype.CompanyAccount;
import allnew.okk.account.Prototype.PrivateAccount;

// Week 2, Pattern Singleton 1
// klasa zarzązdzajaca logowaniem, rejestracaja i sesja dla użytkownika
// jest ona singletonem z uwagi na prywatny konstruktor i jej jedną statyczna instancje
public class CurrentSession {
    // Singleton - jedna instancja klasy dla całej aplikacji, prywatny konstruktor oraz metoda do uzyskania tej instancji
    private static CurrentSession instance;
    private BaseAccount loggedAccount;

    private CurrentSession(){}

    //metoda do uzyskania instancji
    public static CurrentSession getInstance(){
        if(instance == null){
            instance = new CurrentSession();
        }

        return instance;
    }

    public void register(BaseAccount account){
        this.loggedAccount = account;
    }


    public boolean login(BaseAccount account){
        // Week 6, Pattern State 1 Oliwier Majewski
        //Now during login there is verification if the user can log in for that particular account
        if (!account.canLogin()) {
            System.out.println("Session signleton login is blocked for " + account.GetEmail());
            return false;
        }
        // Week 6, Pattern State 1 Oliwier Majewski


        this.loggedAccount = account;
        return true;
    }

    public void logout(){
        this.loggedAccount = null;
    }

    //Zwraca flage czy zalogowane konto jest kontem prywatnym
    public boolean isPrivateAccount() {
        return loggedAccount instanceof PrivateAccount;
    }

    //Zwraca flage czy zalogowane konto jest kontem firmowym
    public boolean isCompanyAccount() {
        return loggedAccount instanceof CompanyAccount;
    }

    //metoda zwracajaca konto prywatne dla uzytkownika który jest zalogowany na tego typu konto
    public PrivateAccount getAsPrivate() {
        if (loggedAccount instanceof PrivateAccount p) return p;
        throw new IllegalStateException("Logged account is not a PrivateAccount");
    }

    //metoda zwracajaca konto firmowe dla uzytkownika który jest zalogowany na tego typu konto
    public CompanyAccount getAsCompany() {
        if (loggedAccount instanceof CompanyAccount c) return c;
        throw new IllegalStateException("Logged account is not a CompanyAccount");
    }
}
//End Week 2, Pattern Singleton 1
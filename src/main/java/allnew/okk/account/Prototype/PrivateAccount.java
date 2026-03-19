package allnew.okk.account.Prototype;


// Week 2, Pattern Prototype 4
//klasa reprezentujaca konto prywatne
public class PrivateAccount extends BaseAccount{
    private String name;
    private String surname;
    private float savedMoney = 0;

    public String GetName() { return name; }
    public String GetSurname() { return surname; }
    public float GetSavedMoney() { return savedMoney; }

    public PrivateAccount(String email, String password, String name, String surname, String adress, String phone){
        super(email, password, adress, phone);
        this.name = name;
        this.surname = surname;
        this.savedMoney = 0;
    }
    // End Week 2, Pattern Prototype 4


    @Override
    public String toString(){
        return "email: "+email+" password: "+password+" name: "+name+" surname: "+surname+" savedMoney: "+savedMoney;
    }
}
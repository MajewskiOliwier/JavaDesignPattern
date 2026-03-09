package allnew.okk.account.Prototype;

public class PrivateAccount extends BaseAccount{
    private String name;
    private String surname;
    private float savedMoney = 0;

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public float getSavedMoney() { return savedMoney; }

    public PrivateAccount(String email, String password, String name, String surname){
        super(email, password);
        this.name = name;
        this.surname = surname;
        this.savedMoney = 0;
    }

    @Override
    public String toString(){
        return "email: "+email+" password: "+password+" name: "+name+" surname: "+surname+" savedMoney: "+savedMoney;
    }
}
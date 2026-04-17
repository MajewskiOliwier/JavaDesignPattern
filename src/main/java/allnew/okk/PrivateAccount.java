public class PrivateAccount extends BaseAccount{
    private String name;
    private String surname;
    private float savedMoney = 0;

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
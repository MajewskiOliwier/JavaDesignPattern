package allnew.okk.account.Prototype;

//Jest to klasa abstrakcji a nie interfejs z uwagi na istnienie wspólnych pol i metod dla oby typów kont
public abstract class BaseAccount {
    protected String email;
    protected String password;
    protected String adress;
    protected String phone;

    public String GetEmail() { return email; }
    public String GetPassword() { return password; }
    public String GetAdress() { return adress; }
    public String GetPhone() { return phone; }

    protected BaseAccount(String email, String password, String adress, String phone){
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "email: "+email+", password: "+password;
    }
}
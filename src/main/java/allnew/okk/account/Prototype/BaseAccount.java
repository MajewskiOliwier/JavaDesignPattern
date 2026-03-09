package allnew.okk.account.Prototype;

public abstract class BaseAccount {
    protected String email;
    protected String password;

    public String GetEmail() { return email; }
    public String GetPassword() { return password; }

    protected BaseAccount(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString(){
        return "email: "+email+", password: "+password;
    }
}
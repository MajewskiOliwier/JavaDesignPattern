public abstract class BaseAccount{
    protected String email;
    protected String password;

    protected BaseAccount(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString(){
        return "email: "+email+", password: "+password;
    }
}
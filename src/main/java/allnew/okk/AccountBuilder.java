package allnew.okk;

public abstract class AccountBuilder <T extends  AccountBuilder<T>>{
    protected String email;
    protected String password;

    protected abstract T self();

    public T Email(String email){
        this.email = email;
        return self();
    }

    public T Password(String password){
        this.password = password;
        return self();
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
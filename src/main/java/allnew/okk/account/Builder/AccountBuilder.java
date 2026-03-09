package allnew.okk.account.Builder;

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

    public void SetEmail(String newEmail){
        this.email = newEmail;
    }

    public void SetPassword(String newPassword){
        this.password = newPassword;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
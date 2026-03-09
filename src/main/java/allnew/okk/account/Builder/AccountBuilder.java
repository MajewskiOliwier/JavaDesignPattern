package allnew.okk.account.Builder;

public abstract class AccountBuilder <T extends  AccountBuilder<T>>{
    protected String email;
    protected String password;
    protected String adress;
    protected String phone;

    protected abstract T Self();

    public T Email(String email){
        this.email = email;
        return Self();
    }

    public T Password(String password){
        this.password = password;
        return Self();
    }

    public T Adress(String adress){
        this.adress = adress;
        return Self();
    }

    public T Phone(String phone){
        this.phone = phone;
        return Self();
    }

    public void SetEmail(String newEmail){
        this.email = newEmail;
    }

    public void SetPassword(String newPassword){
        this.password = newPassword;
    }

    public void SetAdress(String newAdress){
        this.adress = newAdress;
    }

    public void SetPhone(String newPhone){
        this.phone = newPhone;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
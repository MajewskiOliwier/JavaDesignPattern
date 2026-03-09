package allnew.okk.account.Singleton;

import allnew.okk.account.Prototype.BaseAccount;
import allnew.okk.account.Prototype.CompanyAccount;
import allnew.okk.account.Prototype.PrivateAccount;

public class CurrentSession {
    private static CurrentSession instance;
    private BaseAccount loggedAccount;

    private CurrentSession(){}

    public static CurrentSession getInstance(){
        if(instance == null){
            instance = new CurrentSession();
        }

        return instance;
    }

    public void register(BaseAccount account){
        this.loggedAccount = account;
    }

    public void login(BaseAccount account){
        this.loggedAccount = account;
    }

    public void logout(){
        this.loggedAccount = null;
    }

    public PrivateAccount getAsPrivate() {
        if (loggedAccount instanceof PrivateAccount p) return p;
        throw new IllegalStateException("Logged account is not a PrivateAccount");
    }

    public CompanyAccount getAsCompany() {
        if (loggedAccount instanceof CompanyAccount c) return c;
        throw new IllegalStateException("Logged account is not a CompanyAccount");
    }

    public boolean isPrivateAccount() {
        return loggedAccount instanceof PrivateAccount;
    }

    public boolean isCompanyAccount() {
        return loggedAccount instanceof CompanyAccount;
    }
}
package allnew.okk.account.Factory;

import allnew.okk.account.Builder.CompanyAccountBuilder;
import allnew.okk.account.Builder.PrivateAccountBuilder;

public class AccountFactory{
    public static PrivateAccountBuilder createPrivateAccount(){
        return new PrivateAccountBuilder();
    }
    
    public static CompanyAccountBuilder createCompanyAccount(){
        return new CompanyAccountBuilder();
    }
}
package allnew.okk;

public class AccountFactory{
    public static PrivateAccountBuilder createPrivateAccount(){
        return new PrivateAccountBuilder();
    }
    
    public static CompanyAccountBuilder createCompanyAccount(){
        return new CompanyAccountBuilder();
    }
}
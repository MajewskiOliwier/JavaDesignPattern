package allnew.okk.account.Factory;

import allnew.okk.account.Builder.CompanyAccountBuilder;
import allnew.okk.account.Builder.PrivateAccountBuilder;

//Klasa Factory udostępniająca 2 metody umożliwiające utworzenie konta prywatnego lub firmowego
public class AccountFactory{

    // Tydzień 2, Wzorzec Factory 2
    public static PrivateAccountBuilder createPrivateAccount(){
        return new PrivateAccountBuilder();
    }
    
    public static CompanyAccountBuilder createCompanyAccount(){
        return new CompanyAccountBuilder();
    }
    // Koniec, Tydzień 2, Wzorzec Factory 2
}
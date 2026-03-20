package allnew.okk.account.Template;

import allnew.okk.account.Prototype.BaseAccount;
import allnew.okk.account.Prototype.PrivateAccount;

// Week 6, Pattern Template 1 Oliwier Majewski
public class PrivateRegistrationTemplate extends RegistrationTemplate{

    @Override
    protected boolean ValidateAccount(BaseAccount account)
    {
        if (!(account instanceof PrivateAccount privateAccount)) {
            System.out.println("Template: expected PrivateAccount");
            return false;
        }

        if (privateAccount.GetEmail() == null || privateAccount.GetEmail().isBlank()) {
            System.out.println("Template: email cannot be empty");
            return false;
        }

        if (privateAccount.GetPassword() == null || privateAccount.GetPassword().length() < 6) {
            System.out.println("Template: password must be at least 6 characters");
            return false;
        }

        if (privateAccount.GetName() == null || privateAccount.GetName().isBlank()) {
            System.out.println("Template: name cannot be empty");
            return false;
        }

        if (privateAccount.GetSurname() == null || privateAccount.GetSurname().isBlank()) {
            System.out.println("Template: surname cannot be empty");
            return false;
        }

        System.out.println("Template: private account validation passed for " + privateAccount.GetEmail());
        return true;
    }
}
//End Week 6, Pattern Template 1 Oliwier Majewski

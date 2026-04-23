package allnew.okk.account.Template;

import allnew.okk.account.Prototype.BaseAccount;
import allnew.okk.account.Singleton.CurrentSession;

// Week 6, Pattern Template 1 Oliwier Majewski
// Abstract registration template which defines the basic skeleton
public abstract class RegistrationTemplate {

    public final boolean Register(BaseAccount account) {
        if (!ValidateAccount(account)) {
            System.out.println("Template: registration failed — validation did not pass");
            return false;
        }

        SaveToSession(account);
        return true;
    }

    protected abstract boolean ValidateAccount(BaseAccount account);

    private void SaveToSession(BaseAccount account) {
        CurrentSession.getInstance().register(account);
        System.out.println("Template: account registered — " + account.getEmail());
    }
}
//End Week 6, Pattern Template 1 Oliwier Majewski

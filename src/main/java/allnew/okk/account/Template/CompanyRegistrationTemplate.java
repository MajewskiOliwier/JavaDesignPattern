package allnew.okk.account.Template;

import allnew.okk.account.Prototype.BaseAccount;
import allnew.okk.account.Prototype.CompanyAccount;

public class CompanyRegistrationTemplate extends RegistrationTemplate{

    private static final int VAT_NUMBER_LENGTH = 10;

    @Override
    protected boolean ValidateAccount(BaseAccount account) {
        if (!(account instanceof CompanyAccount companyAccount)) {
            System.out.println("Template: expected CompanyAccount");
            return false;
        }

        if (companyAccount.GetEmail() == null || companyAccount.GetEmail().isBlank()) {
            System.out.println("Template: email cannot be empty");
            return false;
        }

        if (companyAccount.GetPassword() == null || companyAccount.GetPassword().length() < 6) {
            System.out.println("Template: password must be at least 6 characters");
            return false;
        }

        if (companyAccount.GetLegalName() == null || companyAccount.GetLegalName().isBlank()) {
            System.out.println("Template: company name cannot be empty");
            return false;
        }

        if (companyAccount.GetVatNumber() == null || !companyAccount.GetVatNumber().matches("\\d{" + VAT_NUMBER_LENGTH + "}")) {
            System.out.println("Template: VAT number must be exactly 10 digits");
            return false;
        }

        System.out.println("Template: company account validation passed for " + companyAccount.GetEmail());
        return true;
    }
}

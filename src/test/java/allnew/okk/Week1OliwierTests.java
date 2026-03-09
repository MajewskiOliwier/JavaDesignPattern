package allnew.okk;

import allnew.okk.account.Factory.AccountFactory;
import allnew.okk.account.Prototype.CompanyAccount;
import allnew.okk.account.Prototype.PrivateAccount;
import allnew.okk.account.Singleton.CurrentSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Week1OliwierTests {
    @Test
    void testBuildAndFactoryForCreatingPrivateAccount(){
        PrivateAccount account = AccountFactory.createPrivateAccount()
                .Email("week1@gmail.com")
                .Password("pass123!")
                .Name("Oliwier")
                .Surname("Majewski")
                .build();

        assertNotNull(account);
        assertEquals("week1@gmail.com", account.GetEmail());
        assertEquals("pass123!", account.GetPassword());
        assertEquals("Oliwier", account.getName());
        assertEquals("Majewski", account.getSurname());
        assertEquals(0, account.getSavedMoney());
    }

    @Test
    void testBuildAndFactoryForCreatingCompanyAccount(){
        CompanyAccount account = AccountFactory.createCompanyAccount()
                .Email("week1@gmail.com")
                .Password("pass123!")
                .setLegalName("COMPANY1WEEK")
                .setVatNumber("1234567890")
                .build();

        assertNotNull(account);
        assertEquals("week1@gmail.com", account.GetEmail());
        assertEquals("pass123!", account.GetPassword());
        assertEquals("COMPANY1WEEK", account.GetLegalName());
        assertEquals("1234567890", account.GetVatNumber());
    }

    @Test
    void TestSingletonForPrivateAccount(){
        PrivateAccount account = AccountFactory.createPrivateAccount()
                .Email("week1@gmail.com")
                .Password("pass123!")
                .Name("Oliwier")
                .Surname("Majewski")
                .build();

        CurrentSession.getInstance().login(account);

        assertNotNull(account);
        assertTrue(CurrentSession.getInstance().isPrivateAccount());
        if (CurrentSession.getInstance().isPrivateAccount()) {

            PrivateAccount p = CurrentSession.getInstance().getAsPrivate();

            assertEquals("week1@gmail.com", p.GetEmail());
            assertEquals("pass123!", p.GetPassword());
            assertEquals("Oliwier", p.getName());
            assertEquals("Majewski", p.getSurname());
            assertEquals(0, p.getSavedMoney());
        }
    }
    
    @Test
    void TestSingletonForCompanyAccount(){
        CompanyAccount account = AccountFactory.createCompanyAccount()
                .Email("week1@gmail.com")
                .Password("pass123!")
                .setLegalName("COMPANY1WEEK")
                .setVatNumber("1234567890")
                .build();

        CurrentSession.getInstance().login(account);

        assertNotNull(account);
        assertTrue(CurrentSession.getInstance().isCompanyAccount());
        if (CurrentSession.getInstance().isCompanyAccount()) {

            CompanyAccount c = CurrentSession.getInstance().getAsCompany();

            assertEquals("week1@gmail.com", c.GetEmail());
            assertEquals("pass123!", c.GetPassword());
            assertEquals("COMPANY1WEEK", c.GetLegalName());
            assertEquals("1234567890", c.GetVatNumber());
        }
    }

    @Test
    void TestPrototypeCompanyAccount(){
        CompanyAccount template = AccountFactory.createCompanyAccount()
                .Email("week1@gmail.com")
                .Password("pass123!")
                .setLegalName("COMPANY1WEEK")
                .setVatNumber("1234567890")
                .build();

        CompanyAccount subAccount = (CompanyAccount) template.clone()
                .Email("week1_2@gmail.com")
                .Password("pass321!")
                .build();

        assertNotNull(template);
        assertEquals("week1@gmail.com", template.GetEmail());
        assertEquals("pass123!", template.GetPassword());
        assertEquals("COMPANY1WEEK", template.GetLegalName());
        assertEquals("1234567890", template.GetVatNumber());

        assertNotNull(subAccount);
        assertEquals("week1_2@gmail.com", subAccount.GetEmail());
        assertEquals("pass321!", subAccount.GetPassword());
        assertEquals("COMPANY1WEEK", subAccount.GetLegalName());
        assertEquals("1234567890", subAccount.GetVatNumber());
    }
}
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
        assertEquals("week1@gmail.com", account.getEmail());
        assertEquals("pass123!", account.getPassword());
        assertEquals("Oliwier", account.GetName());
        assertEquals("Majewski", account.GetSurname());
        assertEquals(0, account.GetSavedMoney());
    }

    @Test
    void testBuildAndFactoryForCreatingCompanyAccount(){
        CompanyAccount account = AccountFactory.createCompanyAccount()
                .Email("week1@gmail.com")
                .Password("pass123!")
                .SetLegalName("COMPANY1WEEK")
                .SetVatNumber("1234567890")
                .Build();

        assertNotNull(account);
        assertEquals("week1@gmail.com", account.getEmail());
        assertEquals("pass123!", account.getPassword());
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

            assertEquals("week1@gmail.com", p.getEmail());
            assertEquals("pass123!", p.getPassword());
            assertEquals("Oliwier", p.GetName());
            assertEquals("Majewski", p.GetSurname());
            assertEquals(0, p.GetSavedMoney());
        }
    }
    
    @Test
    void TestSingletonForCompanyAccount(){
        CompanyAccount account = AccountFactory.createCompanyAccount()
                .Email("week1@gmail.com")
                .Password("pass123!")
                .SetLegalName("COMPANY1WEEK")
                .SetVatNumber("1234567890")
                .Build();

        CurrentSession.getInstance().login(account);

        assertNotNull(account);
        assertTrue(CurrentSession.getInstance().isCompanyAccount());
        if (CurrentSession.getInstance().isCompanyAccount()) {

            CompanyAccount c = CurrentSession.getInstance().getAsCompany();

            assertEquals("week1@gmail.com", c.getEmail());
            assertEquals("pass123!", c.getPassword());
            assertEquals("COMPANY1WEEK", c.GetLegalName());
            assertEquals("1234567890", c.GetVatNumber());
        }
    }

    @Test
    void TestPrototypeCompanyAccount(){
        CompanyAccount template = AccountFactory.createCompanyAccount()
                .Email("week1@gmail.com")
                .Password("pass123!")
                .SetLegalName("COMPANY1WEEK")
                .SetVatNumber("1234567890")
                .Build();

        CompanyAccount subAccount = (CompanyAccount) template.clone()
                .Email("week1_2@gmail.com")
                .Password("pass321!")
                .Build();

        assertNotNull(template);
        assertEquals("week1@gmail.com", template.getEmail());
        assertEquals("pass123!", template.getPassword());
        assertEquals("COMPANY1WEEK", template.GetLegalName());
        assertEquals("1234567890", template.GetVatNumber());

        assertNotNull(subAccount);
        assertEquals("week1_2@gmail.com", subAccount.getEmail());
        assertEquals("pass321!", subAccount.getPassword());
        assertEquals("COMPANY1WEEK", subAccount.GetLegalName());
        assertEquals("1234567890", subAccount.GetVatNumber());
    }
}
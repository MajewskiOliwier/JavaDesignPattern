package allnew.okk;

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
        assertEquals("week1@gmail.com", account.email);
        assertEquals("pass123!", account.password);
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
        assertEquals("week1@gmail.com", account.email);
        assertEquals("pass123!", account.password);
        assertEquals("COMPANY1WEEK", account.getLegalName());
        assertEquals("1234567890", account.getVatNumber());
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

            assertEquals("week1@gmail.com", p.email);
            assertEquals("pass123!", p.password);
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

            assertEquals("week1@gmail.com", c.email);
            assertEquals("pass123!", c.password);
            assertEquals("COMPANY1WEEK", c.getLegalName());
            assertEquals("1234567890", c.getVatNumber());
        }
    }
}
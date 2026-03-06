package allnew.okk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
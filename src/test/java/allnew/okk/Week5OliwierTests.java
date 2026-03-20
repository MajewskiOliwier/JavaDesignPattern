package allnew.okk;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.account.Adapter.CompanyAccountAdapter;
import allnew.okk.account.Adapter.PrivateAccountAdapter;
import allnew.okk.account.Factory.AccountFactory;
import allnew.okk.account.Observer.AccountEvent;
import allnew.okk.account.Observer.AccountEventBus;
import allnew.okk.account.Prototype.CompanyAccount;
import allnew.okk.account.Prototype.PrivateAccount;
import allnew.okk.account.Singleton.CurrentSession;
import allnew.okk.account.Template.CompanyRegistrationTemplate;
import allnew.okk.account.Template.PrivateRegistrationTemplate;
import allnew.okk.basket.Visitor.TaxCalculatorVisitor;
import allnew.okk.basket.composite.ShoppingBasket;
import allnew.okk.product.Decorator.GiftWrapDecorator;
import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class Week5OliwierTests {
    CompanyAccount companyAccount = AccountFactory.createCompanyAccount()
            .Email("asus@gmail.com")
            .SetLegalName("Asus")
            .Password("zxcvbnm")
            .SetVatNumber("1234567890")
            .Build();

    PrivateAccount privateAccount = AccountFactory.createPrivateAccount()
            .Name("Oliwier")
            .Surname("Majewski")
            .Email("test@gmail.com")
            .build();

    AccountDisplayable companyAccountAdapter = new CompanyAccountAdapter(companyAccount);
    AccountDisplayable privateAccountAdapter = new PrivateAccountAdapter(privateAccount);

    CompanyProduct laptop;
    CompanyProduct laptop2;
    PrivateProduct chleb;
    ShoppingBasket basket;

    @BeforeEach
    void setUp() {
        basket = new ShoppingBasket();
        privateAccount.activate();
        companyAccount.activate();
        AccountEventBus.GetInstance().ClearObservers();
        CurrentSession.getInstance().logout();

        AccountEventBus.GetInstance().Register(CurrentSession.getInstance());

        laptop = new CompanyProduct.Builder()
                .setName("Laptop1")
                .setPrice(199.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        laptop2 = new CompanyProduct.Builder()
                .setName("Laptop2")
                .setPrice(51.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        chleb = new PrivateProduct.Builder()
                .setName("Chleb")
                .setPrice(50.00)
                .setSellerName("Oliwier")
                .setAccountDisplayable(privateAccountAdapter)
                .build();
    }


    // ---------------------------------------------------------------
    // Visitor Tests
    // ---------------------------------------------------------------

    @Test
    void testTaxCalculatorVisitorCompanyProductHas23PercentTax() {
        basket.addItem(laptop); // 199.00 CompanyProduct

        TaxCalculatorVisitor visitor = new TaxCalculatorVisitor();
        basket.accept(visitor);

        assertEquals(199.00 * 0.23, visitor.getTotalTax(), 0.01, "CompanyProduct should have 23% tax");
    }

    @Test
    void testTaxCalculatorVisitorPrivateProductHasZeroTax() {
        basket.addItem(chleb); // 50.00 PrivateProduct

        TaxCalculatorVisitor visitor = new TaxCalculatorVisitor();
        basket.accept(visitor);

        assertEquals(5, visitor.getTotalTax(), 0.01, "PrivateProduct should have 10% tax");
    }

    @Test
    void testTaxCalculatorVisitorMixedBasket() {
        basket.addItem(laptop); // 199.00 CompanyProduct → 45.77 tax
        basket.addItem(chleb);  // 50.00 PrivateProduct  → 0 tax

        TaxCalculatorVisitor visitor = new TaxCalculatorVisitor();
        basket.accept(visitor);

        assertEquals(199.00 * 0.23 + 50 * 0.1, visitor.getTotalTax(), 0.01, "Only CompanyProduct tax should be counted in mixed basket");
    }

    @Test
    void testTaxCalculatorVisitorGiftWrappedPrivateProductHasZeroTax() {
        basket.addItem(new GiftWrapDecorator(chleb));

        TaxCalculatorVisitor visitor = new TaxCalculatorVisitor();
        basket.accept(visitor);

        assertEquals(5f, visitor.getTotalTax(), 0.01, "GiftWrapped PrivateProduct should still have 10% tax");
    }

    @Test
    void testTaxCalculatorVisitorGiftWrappedCompanyProductHas23PercentTax() {
        basket.addItem(new GiftWrapDecorator(laptop));

        TaxCalculatorVisitor visitor = new TaxCalculatorVisitor();
        basket.accept(visitor);

        assertEquals(199.00 * 0.23, visitor.getTotalTax(), 0.01, "GiftWrapped CompanyProduct should still have 23% tax on base price");
    }

    @Test
    void testTaxCalculatorVisitorEmptyBasketHasZeroTax() {
        TaxCalculatorVisitor visitor = new TaxCalculatorVisitor();
        basket.accept(visitor);

        assertEquals(0.0, visitor.getTotalTax(), 0.01,
                "Empty basket should have zero tax");
    }

    @Test
    void testTaxCalculatorVisitorMultipleCompanyProducts() {
        basket.addItem(laptop);  // 199.00
        basket.addItem(laptop2); // 51.00

        TaxCalculatorVisitor visitor = new TaxCalculatorVisitor();
        basket.accept(visitor);

        assertEquals((199.00 + 51.00) * 0.23, visitor.getTotalTax(), 0.01, "Tax should be sum of 23% across all CompanyProducts");
    }

    @Test
    void testTaxCalculatorVisitorIsReusableOnDifferentBaskets() {
        ShoppingBasket basket2 = new ShoppingBasket();
        basket.addItem(laptop);
        basket2.addItem(laptop);

        TaxCalculatorVisitor visitor1 = new TaxCalculatorVisitor();
        TaxCalculatorVisitor visitor2 = new TaxCalculatorVisitor();

        basket.accept(visitor1);
        basket2.accept(visitor2);

        assertEquals(visitor1.getTotalTax(), visitor2.getTotalTax(), 0.01, "Same basket contents should produce same tax with fresh visitor instances");
    }

    // ---------------------------------------------------------------
    // State Tests
    // ---------------------------------------------------------------

    @Test
    void testAccountStartsAsActive() {
        assertEquals("ACTIVE", privateAccount.getAccountState().getStateName(), "Account should start in ActiveState");
    }

    @Test
    void testCompanyAccountStartsAsActive() {
        assertEquals("ACTIVE", companyAccount.getAccountState().getStateName(), "Company account should start in ActiveState");
    }

    @Test
    void testActiveAccountCanPlaceOrder() {
        assertTrue(privateAccount.canPlaceOrder(), "Active account should be allowed to place orders");
    }

    @Test
    void testSuspendedAccountCannotPlaceOrder() {
        privateAccount.suspend();
        assertFalse(privateAccount.canPlaceOrder(),"Suspended account should not be allowed to place orders");
    }

    @Test
    void testBannedAccountCannotPlaceOrder() {
        privateAccount.ban();
        assertFalse(privateAccount.canPlaceOrder(),"Banned account should not be allowed to place orders");
    }

    @Test
    void testLoginSucceedsForActiveAccount() {
        boolean result = CurrentSession.getInstance().login(privateAccount);
        assertTrue(result, "Login should succeed for active account");
    }

    @Test
    void testLoginSucceedsForSuspendedAccount() {
        privateAccount.suspend();
        boolean result = CurrentSession.getInstance().login(privateAccount);
        assertTrue(result, "Login should succeed for suspended account");
    }

    @Test
    void testLoginFailsForBannedAccount() {
        privateAccount.ban();
        boolean result = CurrentSession.getInstance().login(privateAccount);
        assertFalse(result, "Login should fail for banned account");
    }

    @Test
    void testBannedAccountSessionIsNullAfterFailedLogin() {
        CurrentSession.getInstance().logout();
        privateAccount.ban();
        CurrentSession.getInstance().login(privateAccount);
        assertFalse(CurrentSession.getInstance().isPrivateAccount(),"Session should not hold banned account after failed login");
    }

    // ---------------------------------------------------------------
    // Observer Tests
    // ---------------------------------------------------------------

    @Test
    void testEventBusHasOneObserverAfterSetup() {
        assertEquals(1, AccountEventBus.GetInstance().GetObserversCount(),
                "EventBus should have exactly one observer registered after setup");
    }

    @Test
    void testRegisterAddsObserver() {
        int before = AccountEventBus.GetInstance().GetObserversCount();
        AccountEventBus.GetInstance().Register(CurrentSession.getInstance());
        assertEquals(before + 1, AccountEventBus.GetInstance().GetObserversCount(),
                "Registering an observer should increase observer count by 1");
    }

    @Test
    void testUnregisterRemovesObserver() {
        AccountEventBus.GetInstance().Unregister(CurrentSession.getInstance());
        assertEquals(0, AccountEventBus.GetInstance().GetObserversCount(),
                "Unregistering CurrentSession should leave bus empty");
    }

    @Test
    void testClearRemovesAllObservers() {
        AccountEventBus.GetInstance().ClearObservers();
        assertEquals(0, AccountEventBus.GetInstance().GetObserversCount(),
                "Clear should remove all observers from the bus");
    }

    @Test
    void testMultipleBansPublishMultipleEvents() {
        var receivedEvents = new java.util.ArrayList<AccountEvent>();
        AccountEventBus.GetInstance().Register((account, event) -> receivedEvents.add(event));

        privateAccount.ban();
        privateAccount.activate();
        privateAccount.ban();

        assertEquals(2, receivedEvents.size(),"Two ban() calls should publish two ON_BAN events");
    }

    // ---------------------------------------------------------------
    // Template Tests
    // ---------------------------------------------------------------

    PrivateRegistrationTemplate privateTemplate = new PrivateRegistrationTemplate();
    CompanyRegistrationTemplate companyTemplate = new CompanyRegistrationTemplate();

    PrivateAccount buildValidPrivateAccount() {
        return AccountFactory.createPrivateAccount()
                .Name("Oliwier")
                .Surname("Majewski")
                .Email("oliwier@gmail.com")
                .Password("password123")
                .build();
    }

    CompanyAccount buildValidCompanyAccount() {
        return AccountFactory.createCompanyAccount()
                .SetLegalName("Asus")
                .Email("asus@gmail.com")
                .Password("password123")
                .SetVatNumber("1234567890")
                .Build();
    }

    @Test
    void testPrivateRegistrationSucceedsWithValidAccount() {
        boolean result = privateTemplate.Register(buildValidPrivateAccount());
        assertTrue(result, "Registration should succeed with valid private account");
    }

    @Test
    void testPrivateRegistrationSavesToSession() {
        privateTemplate.Register(buildValidPrivateAccount());
        assertTrue(CurrentSession.getInstance().isPrivateAccount(),
                "CurrentSession should hold the registered private account");
    }

    @Test
    void testPrivateRegistrationFailsWithEmptyEmail() {
        PrivateAccount account = AccountFactory.createPrivateAccount()
                .Name("Oliwier")
                .Surname("Majewski")
                .Email("")
                .Password("password123")
                .build();

        assertFalse(privateTemplate.Register(account),
                "Registration should fail when email is empty");
    }

    @Test
    void testPrivateRegistrationFailsWithShortPassword() {
        PrivateAccount account = AccountFactory.createPrivateAccount()
                .Name("Oliwier")
                .Surname("Majewski")
                .Email("oliwier@gmail.com")
                .Password("abc")
                .build();

        assertFalse(privateTemplate.Register(account), "Registration should fail when password is shorter than 6 characters");
    }

    @Test
    void testPrivateRegistrationFailsWithEmptyName() {
        PrivateAccount account = AccountFactory.createPrivateAccount()
                .Name("")
                .Surname("Majewski")
                .Email("oliwier@gmail.com")
                .Password("password123")
                .build();

        assertFalse(privateTemplate.Register(account), "Registration should fail when name is empty");
    }

    @Test
    void testPrivateTemplateRejectsCompanyAccount() {
        assertFalse(privateTemplate.Register(buildValidCompanyAccount()),"PrivateRegistrationTemplate should reject a CompanyAccount");
    }

    @Test
    void testCompanyRegistrationFailsWithInvalidVatNumber() {
        CompanyAccount account = AccountFactory.createCompanyAccount()
                .SetLegalName("Asus")
                .Email("asus@gmail.com")
                .Password("password123")
                .SetVatNumber("123") //vat numbe is too short
                .Build();

        assertFalse(companyTemplate.Register(account), "Registration should fail when VAT number is not 10 digits");
    }
}

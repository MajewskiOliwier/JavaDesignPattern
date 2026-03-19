package allnew.okk;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.account.Adapter.CompanyAccountAdapter;
import allnew.okk.account.Adapter.PrivateAccountAdapter;
import allnew.okk.account.Factory.AccountFactory;
import allnew.okk.account.Prototype.CompanyAccount;
import allnew.okk.account.Prototype.PrivateAccount;
import allnew.okk.basket.Visitor.TaxCalculatorVisitor;
import allnew.okk.basket.composite.ShoppingBasket;
import allnew.okk.product.Decorator.GiftWrapDecorator;
import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

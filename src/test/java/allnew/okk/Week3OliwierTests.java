package allnew.okk;

import allnew.okk.Order.Facade.OrderFacade;
import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.account.Adapter.CompanyAccountAdapter;
import allnew.okk.account.Adapter.PrivateAccountAdapter;
import allnew.okk.account.Factory.AccountFactory;
import allnew.okk.account.Prototype.CompanyAccount;
import allnew.okk.account.Prototype.PrivateAccount;
import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.basket.composite.ShoppingBasket;
import allnew.okk.payment.Adapter.PayUAdapter;
import allnew.okk.payment.Adapter.Przelewy24Adapter;
import allnew.okk.product.Decorator.GiftWrapDecorator;
import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Week3OliwierTests {
    PayUAdapter payUAdapter = new PayUAdapter();
    Przelewy24Adapter przelewy24Adapter = new Przelewy24Adapter();

    PrivateAccount privateAccount = AccountFactory.createPrivateAccount()
            .Name("Oliwier")
            .Surname("Majewski")
            .Email("test@gmail.com")
            .build();

    CompanyAccount companyAccount = AccountFactory.createCompanyAccount()
            .Email("asus@gmail.com")
            .SetLegalName("Asus")
            .Password("zxcvbnm")
            .SetVatNumber("1234567890")
            .Build();

    AccountDisplayable privateAccountAdapter = new PrivateAccountAdapter(privateAccount);
    AccountDisplayable companyAccountAdapter = new CompanyAccountAdapter(companyAccount);

    @Test
    void testPlaceOrderFacadeWithPayUReturnsTrueWhenAllPaymentsSucceed() {
        CompanyProduct companyProduct1 = new CompanyProduct.Builder()
                .setName("Laptop1")
                .setPrice(199.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        CompanyProduct companyProduct2 = new CompanyProduct.Builder()
                .setName("Laptop2")
                .setPrice(51.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        PrivateProduct privateProduct1 = new PrivateProduct.Builder()
                .setName("Chleb")
                .setPrice(50.00)
                .setSellerName("Oliwier")
                .setAccountDisplayable(privateAccountAdapter)
                .build();

        PurchasableItem privateProduct1WithWrap = new GiftWrapDecorator(privateProduct1); //Gift extra price is 5

        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem(companyProduct1);
        basket.addItem(companyProduct2);
        basket.addItem(privateProduct1WithWrap);

        OrderFacade facade = new OrderFacade(new PayUAdapter());

        boolean result = facade.placeOrder(basket, "PLN");

        assertTrue(result, "placeOrder should return true when all PayU payments succeed");
    }

    @Test
    void testPlaceOrderFacadeWithPrzelewy24ReturnsTrueWhenAllPaymentsSucceed() {
        CompanyProduct companyProduct1 = new CompanyProduct.Builder()
                .setName("Laptop1")
                .setPrice(199.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        CompanyProduct companyProduct2 = new CompanyProduct.Builder()
                .setName("Laptop2")
                .setPrice(51.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        PrivateProduct privateProduct1 = new PrivateProduct.Builder()
                .setName("Chleb")
                .setPrice(50.00)
                .setSellerName("Oliwier")
                .setAccountDisplayable(privateAccountAdapter)
                .build();

        PurchasableItem privateProduct1WithWrap = new GiftWrapDecorator(privateProduct1); //Gift extra price is 5

        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem(companyProduct1);
        basket.addItem(companyProduct2);
        basket.addItem(privateProduct1WithWrap);

        OrderFacade facade = new OrderFacade(new Przelewy24Adapter());

        boolean result = facade.placeOrder(basket, "PLN");

        assertTrue(result, "placeOrder should return true when all Przelewy24 payments succeed");
    }

    @Test
    void testPlaceOrderWithEmptyBasketReturnsTrue() {
        ShoppingBasket basket = new ShoppingBasket();

        OrderFacade facade = new OrderFacade(new PayUAdapter());

        boolean result = facade.placeOrder(basket, "PLN");

        assertTrue(result, "placeOrder should return true for an empty basket");
    }

    @Test
    void testPlaceOrderWorksWithDifferentCurrencies() {
        CompanyProduct companyProduct1 = new CompanyProduct.Builder()
                .setName("Laptop1")
                .setPrice(199.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        CompanyProduct companyProduct2 = new CompanyProduct.Builder()
                .setName("Laptop2")
                .setPrice(51.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        PrivateProduct privateProduct1 = new PrivateProduct.Builder()
                .setName("Chleb")
                .setPrice(50.00)
                .setSellerName("Oliwier")
                .setAccountDisplayable(privateAccountAdapter)
                .build();

        PurchasableItem privateProduct1WithWrap = new GiftWrapDecorator(privateProduct1); //Gift extra price is 5

        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem(companyProduct1);
        basket.addItem(companyProduct2);
        basket.addItem(privateProduct1WithWrap);

        assertTrue(new OrderFacade(new PayUAdapter()).placeOrder(basket, "PLN"));
        assertTrue(new OrderFacade(new PayUAdapter()).placeOrder(basket, "EUR"));
        assertTrue(new OrderFacade(new PayUAdapter()).placeOrder(basket, "USD"));
    }

    @Test
    void testBothAdaptersProduceSameResultForSameBasket() {
        CompanyProduct companyProduct1 = new CompanyProduct.Builder()
                .setName("Laptop1")
                .setPrice(199.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        CompanyProduct companyProduct2 = new CompanyProduct.Builder()
                .setName("Laptop2")
                .setPrice(51.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        PrivateProduct privateProduct1 = new PrivateProduct.Builder()
                .setName("Chleb")
                .setPrice(50.00)
                .setSellerName("Oliwier")
                .setAccountDisplayable(privateAccountAdapter)
                .build();

        PurchasableItem privateProduct1WithWrap = new GiftWrapDecorator(privateProduct1); //Gift extra price is 5

        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem(companyProduct1);
        basket.addItem(companyProduct2);
        basket.addItem(privateProduct1WithWrap);

        boolean payuResult       = new OrderFacade(new PayUAdapter()).placeOrder(basket, "PLN");
        boolean przelewy24Result = new OrderFacade(new Przelewy24Adapter()).placeOrder(basket, "PLN");

        assertEquals(payuResult, przelewy24Result, "Both adapters should produce the same result for the same basket");
    }
}
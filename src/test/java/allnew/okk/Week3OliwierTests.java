package allnew.okk;

import allnew.okk.Currency.Flyweight.CurrencyRegistry;
import allnew.okk.Currency.Flyweight.CurrencyType;
import allnew.okk.Order.Facade.OrderFacade;
import allnew.okk.Order.Proxy.OrderFacadeProxy;
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

    ShoppingBasket buildBasket() {
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

        PurchasableItem privateProduct1WithWrap = new GiftWrapDecorator(privateProduct1);

        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem(companyProduct1);
        basket.addItem(companyProduct2);
        basket.addItem(privateProduct1WithWrap);
        return basket;
    }
    // ---------------------------------------------------------------
    // Facade Tests
    // ---------------------------------------------------------------

    @Test
    void testPlaceOrderFacadeWithPayUReturnsTrueWhenAllPaymentsSucceed() {
        OrderFacade facade = new OrderFacade(new PayUAdapter());

        boolean result = facade.placeOrder(buildBasket(), "PLN");

        assertTrue(result, "placeOrder should return true when all PayU payments succeed");
    }

    @Test
    void testPlaceOrderFacadeWithPrzelewy24ReturnsTrueWhenAllPaymentsSucceed() {

        OrderFacade facade = new OrderFacade(new Przelewy24Adapter());

        boolean result = facade.placeOrder(buildBasket(), "PLN");

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
        assertTrue(new OrderFacade(new PayUAdapter()).placeOrder(buildBasket(), "PLN"));
        assertTrue(new OrderFacade(new PayUAdapter()).placeOrder(buildBasket(), "EUR"));
        assertTrue(new OrderFacade(new PayUAdapter()).placeOrder(buildBasket(), "USD"));
    }

    @Test
    void testBothAdaptersProduceSameResultForSameBasket() {

        boolean payuResult       = new OrderFacade(new PayUAdapter()).placeOrder(buildBasket(), "PLN");
        boolean przelewy24Result = new OrderFacade(new Przelewy24Adapter()).placeOrder(buildBasket(), "PLN");

        assertEquals(payuResult, przelewy24Result, "Both adapters should produce the same result for the same basket");
    }

    // ---------------------------------------------------------------
    // Flyweight Tests
    // ---------------------------------------------------------------

    @Test
    void testCurrencyTypeFromStringReturnsPLN() {
        CurrencyType result = CurrencyType.fromString("PLN");
        assertNotNull(result, "fromString should resolve PLN");
        assertEquals(CurrencyType.PLN, result);
    }

    @Test
    void testCurrencyTypeFromStringReturnsEUR() {
        CurrencyType result = CurrencyType.fromString("EUR");
        assertNotNull(result, "fromString should resolve EUR");
        assertEquals(CurrencyType.EUR, result);
    }

    @Test
    void testCurrencyTypeFromStringReturnsNullForUnsupported() {
        CurrencyType result = CurrencyType.fromString("JPY");
        assertNull(result, "fromString should return null for unsupported currency");
    }

    @Test
    void testPLNRateIsOne() {
        assertEquals(1.0f, CurrencyType.PLN.getRateFromPLN(),
                "PLN base rate should always be 1.0");
    }

    @Test
    void testCurrencyRegistryReturnsSameInstanceForSameCurrency() {
        var first  = CurrencyRegistry.get(CurrencyType.EUR);
        var second = CurrencyRegistry.get(CurrencyType.EUR);
        assertSame(first, second,"CurrencyRegistry should return the same flyweight instance for EUR");
    }

    @Test
    void testCurrencyRegistryReturnsDifferentInstancesForDifferentCurrencies() {
        var eur = CurrencyRegistry.get(CurrencyType.EUR);
        var usd = CurrencyRegistry.get(CurrencyType.USD);
        assertNotSame(eur, usd,"CurrencyRegistry should return different instances for EUR and USD");
    }

    @Test
    void testPLNConversionDoesNotChangeAmount() {
        var flyweight = CurrencyRegistry.get(CurrencyType.PLN);
        assertEquals(100.0f, flyweight.convert(100.0f), 0.01f,"Converting PLN to PLN should not change the amount");
    }

    @Test
    void testEURConversionReducesAmount() {
        var flyweight = CurrencyRegistry.get(CurrencyType.EUR);
        float converted = flyweight.convert(425.0f);
        assertEquals(100.0f, converted, 0.01f, "425 PLN should convert to ~100 EUR at rate 4.25");
    }

    @Test
    void testUSDConversionReducesAmount() {
        var flyweight = CurrencyRegistry.get(CurrencyType.USD);
        float converted = flyweight.convert(395.0f);
        assertEquals(100.0f, converted, 0.01f, "395 PLN should convert to ~100 USD at rate 3.95");
    }


    // ---------------------------------------------------------------
    // Proxy Tests
    // ---------------------------------------------------------------

    OrderFacadeProxy proxy = new OrderFacadeProxy(new OrderFacade(new PayUAdapter()));
    OrderFacadeProxy proxyPrzelewy = new OrderFacadeProxy(new OrderFacade(new Przelewy24Adapter()));

    PrivateAccount accountWithPayment = AccountFactory.createPrivateAccount()
            .Name("Oliwier")
            .Surname("Majewski")
            .Email("test@gmail.com")
            .build();
    { //this is instance Initializer block that will run after the Account factory gives us the private account
        accountWithPayment.setPaymentGateway(new PayUAdapter());
    }
    AccountDisplayable validAccount = new PrivateAccountAdapter(accountWithPayment);

    PrivateAccount accountWithoutPayment = AccountFactory.createPrivateAccount()
            .Name("Jan")
            .Surname("Kowalski")
            .Email("jan@gmail.com")
            .build();
    AccountDisplayable invalidAccount = new PrivateAccountAdapter(accountWithoutPayment); //it is invalid as the PaymentGateway is not specified which should return false

    @Test
    void testProxyAllowsOrderWhenAccountHasValidPaymentMethod() {
        boolean result = proxy.placeOrder(buildBasket(), validAccount, "PLN");
        assertTrue(result, "Proxy should allow placeOrder when account has a valid payment method");
    }

    @Test
    void testProxyAllowsOrderWithPrzelewy24WhenAccountHasValidPaymentMethod() {
        boolean result = proxyPrzelewy.placeOrder(buildBasket(), validAccount, "PLN");
        assertTrue(result, "Proxy should allow placeOrder with Przelewy24 when account has a valid payment method");
    }

    @Test
    void testProxyAllowsOrderWithEUR() {
        boolean result = proxy.placeOrder(buildBasket(), validAccount, "EUR");
        assertTrue(result, "Proxy should allow placeOrder with EUR currency");
    }

    @Test
    void testProxyAllowsOrderWithUSD() {
        boolean result = proxy.placeOrder(buildBasket(), validAccount, "USD");
        assertTrue(result, "Proxy should allow placeOrder with USD currency");
    }
}
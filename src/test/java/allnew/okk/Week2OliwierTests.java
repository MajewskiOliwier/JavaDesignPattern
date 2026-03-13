package allnew.okk;

import allnew.okk.account.Adapter.PrivateAccountAdapter;
import allnew.okk.account.Factory.AccountFactory;
import allnew.okk.account.Prototype.CompanyAccount;
import allnew.okk.account.Prototype.PrivateAccount;
import allnew.okk.account.Singleton.CurrentSession;
import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.basket.composite.ShoppingBasket;
import allnew.okk.payment.Adapter.PayUAdapter;
import allnew.okk.payment.Adapter.PaymentGateway;
import allnew.okk.payment.Bridge.CreditCardPayment;
import allnew.okk.payment.Strategy.FullRefundStrategy;
import allnew.okk.payment.Strategy.RefundStrategy;
import allnew.okk.product.Decorator.ExtraDeliveryInsuranceDecorator;
import allnew.okk.product.Decorator.GiftWrapDecorator;
import allnew.okk.product.model.BaseProduct;
import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Week2OliwierTests {
    @Test
    void TestCompositeBasketWithDecorator(){
        CompanyProduct companyProduct1 = new CompanyProduct.Builder()
                .setName("Laptop1")
                .setPrice(199.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .build();

        CompanyProduct companyProduct2 = new CompanyProduct.Builder()
                .setName("Laptop2")
                .setPrice(51.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .build();

        PrivateProduct privateProduct1 = new PrivateProduct.Builder()
                .setName("Chleb")
                .setPrice(50.00)
                .setSellerName("Oliwier")
                .build();

        PurchasableItem privateProduct1WithWrap = new GiftWrapDecorator(privateProduct1); //Gift extra price is 5

        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem(companyProduct1);
        basket.addItem(companyProduct2);
        basket.addItem(privateProduct1WithWrap);

        basket.OrganizeBySeller();

        assertEquals(305.0, basket.getPrice());
    }

    @Test
    void TestDecoratorExtraDeliveryInsurance() {
        assertEquals(115.0, new ExtraDeliveryInsuranceDecorator(new PrivateProduct.Builder().setName("Camera").setPrice(100).build()).getPrice(), 0.001);
    }

    @Test
    void TestDecoratorGiftWrap() {
        assertEquals(105.0, new GiftWrapDecorator(new PrivateProduct.Builder().setName("Camera").setPrice(100).build()).getPrice(), 0.001);
    }

    @Test
    void TestAdapterPayUPayment(){
        PayUAdapter adapter = new PayUAdapter();
        PrivateAccount privateAccount = AccountFactory.createPrivateAccount().Name("Oliwier").Surname("Majewski").Email("test@gmail.com").build();

        PrivateAccountAdapter privateAccountAdapter = new PrivateAccountAdapter(privateAccount);

        assertTrue(adapter.processPayment(100f,privateAccountAdapter, "PLN"));
    }

    @Test
    void TestBridge(){
        PrivateAccount privateAccount = AccountFactory.createPrivateAccount().Name("Oliwier").Surname("Majewski").Email("test@gmail.com").build();
        RefundStrategy strategy = new FullRefundStrategy(0f);

        PaymentGateway paymentGateway = new PayUAdapter();
        CreditCardPayment payment = new CreditCardPayment(paymentGateway, strategy, "4111111111111111", "123");
        PrivateAccountAdapter privateAccountAdapter = new PrivateAccountAdapter(privateAccount);

        assertTrue(payment.pay(250f, "PLN", privateAccountAdapter));
        assertTrue(paymentGateway.processPayment(250f, privateAccountAdapter, "PLN"));
    }
}
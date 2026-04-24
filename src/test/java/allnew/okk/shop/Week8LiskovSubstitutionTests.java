package allnew.okk.shop;

import allnew.okk.shop.decorator.*;
import allnew.okk.shop.model.*;
import allnew.okk.shop.template.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Week 8 - Liskov Substitution Principle Test
public class Week8LiskovSubstitutionTests {

    @Test
    public void testBaseShopSubstitution() {
        // obiekty klas pochodnych, ale przypisujemy je do referencji klasy bazowej
        BaseShop physicalShop = new PhysicalShop.Builder().setName("Sklep Stacjonarny").build();
        BaseShop onlineShop = new OnlineShop.Builder().setName("Sklep Online").build();

        // uniwersalna metoda zadziała dla obu bez rzutowania
        assertTrue(processShop(physicalShop).contains("Sklep Stacjonarny"));
        assertTrue(processShop(onlineShop).contains("Sklep Online"));
    }

    private String processShop(BaseShop shop) {
        return shop.getDetails();
    }

  @Test
    public void testReportTemplateSubstitution() {
        BaseShop dummyShop = new OnlineShop.Builder().setName("TestShop").build();

        ShopReportTemplate htmlReport = new HtmlShopReport();
        ShopReportTemplate csvReport = new CsvShopReport();

        assertDoesNotThrow(() -> generateReportForClient(htmlReport, dummyShop));
        assertDoesNotThrow(() -> generateReportForClient(csvReport, dummyShop));
    }

    private void generateReportForClient(ShopReportTemplate template, BaseShop shop) {
        template.generateReport(shop);
    }


    @Test
    public void testDecoratorSubstitution() {
        ShopDisplay rawShop = new PhysicalShop.Builder()
                .setName("Biedronka")
                .setDescription("Dyskont")
                .build();

        ShopDisplay verifiedShop = new VerifiedBadgeDecorator(rawShop);
        ShopDisplay premiumVerifiedShop = new PremiumBadgeDecorator(verifiedShop);

        assertNotNull(rawShop.getDisplayName());

        assertTrue(premiumVerifiedShop.getDisplayName().contains("Biedronka"));
        assertTrue(premiumVerifiedShop.getDisplayName().contains("⭐"));
        assertTrue(premiumVerifiedShop.getDisplayName().contains("✔️"));
    }
}
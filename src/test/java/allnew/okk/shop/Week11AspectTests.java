package allnew.okk.shop;

import allnew.okk.shop.command.ShopCommand;
import allnew.okk.shop.command.UpdateShopDescriptionCommand;
import allnew.okk.shop.facade.ShopManagementFacade;
import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.model.PhysicalShop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Week11AspectTests {

    @TestConfiguration
    @EnableAspectJAutoProxy
    static class AopTestConfig {

        @Bean
        public ShopManagementFacade springShopFacade() {
            return new ShopManagementFacade();
        }

        @Bean
        public ShopCommand springShopCommand() {
            BaseShop shop = new PhysicalShop.Builder().setName("Sklep Do Zmiany").build();
            return new UpdateShopDescriptionCommand(shop, "Nowy Opis");
        }
    }

    @Autowired
    private ShopManagementFacade shopFacade;

    @Autowired
    private ShopCommand shopCommand;

    private final ByteArrayOutputStream outCaptor = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    private final PrintStream standardErr = System.err;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outCaptor));
        System.setErr(new PrintStream(errCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setErr(standardErr);
    }

    // Verify the aspect responsible for logging (Aspect 1)
    @Test
    void testFacadeLoggingAspectTriggered() {
        // Wywołujemy Fasadę (Bean), więc zadziała Aspekt 1
        shopFacade.registerStandardOnlineShop("Testowy Internetowy", "Opis", "www.test.pl");

        String consoleOutput = outCaptor.toString();
        assertTrue(consoleOutput.contains("[AOP LOG] Entering facade method: registerStandardOnlineShop"),
                "Aspekt logujący nie przechwycił wywołania metody fasady!");
    }

    // Verify the aspect of exception handling (Aspect 2)
    @Test
    void testExceptionAspectCapturing() {
        // Próba podania pustej nazwy do Fasady wyrzuci IllegalArgumentException
        // Aspekt 2 przechwyci to i wypisze na System.err
        assertThrows(IllegalArgumentException.class, () -> {
            shopFacade.registerStandardOnlineShop(null, "Opis", "url");
        });

        String errorOutput = errCaptor.toString();
        assertTrue(errorOutput.contains("[AOP ERROR] Exception in"),
                "Aspekt obsługi błędów nie zalogował rzuconego wyjątku!");
    }

    // Verify the aspect of protection before the command (Aspect 3)
    @Test
    void testCommandGuardAspectTriggered() {
        shopCommand.execute();

        String consoleOutput = outCaptor.toString();
        assertTrue(consoleOutput.contains("[AOP GUARD] Validating security context for command"),
                "Aspekt strażnika (Guard) nie zadziałał przed wykonaniem komendy!");
    }
}
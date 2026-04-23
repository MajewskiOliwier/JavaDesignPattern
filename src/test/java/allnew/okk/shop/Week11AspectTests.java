//package allnew.okk.shop;
//
//import allnew.okk.shop.command.ShopCommand;
//import allnew.okk.shop.command.UpdateShopDescriptionCommand;
//import allnew.okk.shop.model.BaseShop;
//import allnew.okk.shop.model.PhysicalShop;
//import allnew.okk.shop.repository.ShopRepository;
//import allnew.okk.shop.service.ShopService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//public class Week11AspectTests {
//
//    // --- Konfiguracja wstrzykiwania proxy (Rozwiązanie problemu AOP) ---
//    // Zamieniamy nasze tradycyjne obiekty (Singletons/Commands) na obiekty zarządzane przez Springa (Beans),
//    // dzięki czemu Spring nałoży na nie swoje warstwy AOP (aspekty).
//    @TestConfiguration
//    @EnableAspectJAutoProxy
//    static class AopTestConfig {
//
//        @Bean
//        public ShopService springShopService() {
//            return ShopService.getInstance();
//        }
//
//        @Bean
//        public ShopRepository springShopRepository() {
//            return ShopRepository.getInstance();
//        }
//
//        @Bean
//        public ShopCommand springShopCommand() {
//            BaseShop shop = new PhysicalShop.Builder().setName("Sklep Do Zmiany").build();
//            return new UpdateShopDescriptionCommand(shop, "Nowy Opis");
//        }
//    }
//
//    // Używamy autowired, aby pobrać obiekty z aktywnymi Aspektami
//    @Autowired
//    private ShopService shopService;
//
//    @Autowired
//    private ShopRepository shopRepository;
//
//    @Autowired
//    private ShopCommand shopCommand;
//
//    // --- Narzędzia do weryfikacji konsoli ---
//    private final ByteArrayOutputStream outCaptor = new ByteArrayOutputStream();
//    private final ByteArrayOutputStream errCaptor = new ByteArrayOutputStream();
//    private final PrintStream standardOut = System.out;
//    private final PrintStream standardErr = System.err;
//
//    @BeforeEach
//    public void setUp() {
//        System.setOut(new PrintStream(outCaptor));
//        System.setErr(new PrintStream(errCaptor));
//        shopRepository.clear();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        System.setOut(standardOut);
//        System.setErr(standardErr);
//    }
//
//    // --- TEST 1: Weryfikacja aspektu odpowiedzialnego za logowanie (Aspect 1) ---
//    @Test
//    void testServiceLoggingAspectTriggered() {
//        shopRepository.addShop(new PhysicalShop.Builder().setName("Testowy").build());
//
//        // Używamy fasady (Bean), a nie bezpośredniego Singletona, dzięki czemu Aspekt przechwyci to wywołanie
//        shopService.duplicateShop("SHOP-1");
//
//        String consoleOutput = outCaptor.toString();
//        assertTrue(consoleOutput.contains("[AOP LOG] Entering service method: duplicateShop"),
//                "Aspekt logujący nie przechwycił wywołania metody serwisu!");
//    }
//
//    // --- TEST 2: Weryfikacja aspektu obsługi wyjątków (Aspect 2) ---
//    @Test
//    void testExceptionAspectCapturing() {
//        // Próba dodania null wyrzuci wyjątek, Aspekt powinien to wyłapać i zrzucić na System.err
//        assertThrows(IllegalArgumentException.class, () -> {
//            shopRepository.addShop(null);
//        });
//
//        String errorOutput = errCaptor.toString();
//        assertTrue(errorOutput.contains("[AOP ERROR] Exception in"),
//                "Aspekt obsługi błędów nie zalogował rzuconego wyjątku!");
//    }
//
//    // --- TEST 3: Weryfikacja aspektu ochronnego przed komendą (Aspect 3) ---
//    @Test
//    void testCommandGuardAspectTriggered() {
//        // Wykonujemy testową komendę, wcześniej upewniając się, że korzysta z Bean-a
//        shopCommand.execute();
//
//        String consoleOutput = outCaptor.toString();
//        assertTrue(consoleOutput.contains("[AOP GUARD] Validating security context for command"),
//                "Aspekt strażnika (Guard) nie zadziałał przed wykonaniem komendy!");
//    }
//}
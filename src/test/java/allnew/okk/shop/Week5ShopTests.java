package allnew.okk.shop;

import allnew.okk.shop.command.ShopCommand;
import allnew.okk.shop.command.ShopCommandInvoker;
import allnew.okk.shop.command.UpdateShopDescriptionCommand;
import allnew.okk.shop.composite.ShopComponent;
import allnew.okk.shop.composite.ShopNetwork;
import allnew.okk.shop.flyweight.ShopCategory;
import allnew.okk.shop.interpreter.ShopAndExpression;
import allnew.okk.shop.interpreter.ShopCategoryExpression;
import allnew.okk.shop.interpreter.ShopNameContainsExpression;
import allnew.okk.shop.interpreter.ShopSearchExpression;
import allnew.okk.shop.mediator.ShopCacheMediator;
import allnew.okk.shop.mediator.ShopEvent;
import allnew.okk.shop.memento.ShopProfileHistory;
import allnew.okk.shop.memento.ShopProfileMemento;
import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import allnew.okk.shop.proxy.ShopRepositoryProxy;
import allnew.okk.shop.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Week5ShopTests {

    // Przed każdym testem czyścimy Singleton, aby testy nie wpływały na siebie nawzajem
    @BeforeEach
    void setUp() {
        ShopRepository.getInstance().clear();
    }

    // 1. TEST MEMENTO
    @Test
    void testShopProfileMemento() {
        BaseShop shop = new PhysicalShop.Builder()
                .setName("Sklep A")
                .setDescription("Stary Opis")
                .build();

        ShopProfileHistory history = new ShopProfileHistory();

        history.save(shop.saveProfileMemento());

        shop.setDescription("Błędny Opis");
        assertEquals("Błędny Opis", shop.getDescription());

        ShopProfileMemento lastSnapshot = history.undo();
        shop.restoreProfileMemento(lastSnapshot);

        assertEquals("Stary Opis", shop.getDescription(), "Memento nie przywróciło starego opisu!");
    }

    // 2. TEST ITERATOR
    @Test
    void testShopNetworkIterator() {
        ShopNetwork rootNetwork = new ShopNetwork("Globalna Sieć");
        ShopNetwork localNetwork = new ShopNetwork("Lokalna Sieć");

        BaseShop shop1 = new PhysicalShop.Builder().setName("Sklep 1").build();
        BaseShop shop2 = new OnlineShop.Builder().setName("Sklep 2").build();
        BaseShop shop3 = new PhysicalShop.Builder().setName("Sklep 3").build();

        localNetwork.addShopComponent(shop2);
        localNetwork.addShopComponent(shop3);
        rootNetwork.addShopComponent(shop1);
        rootNetwork.addShopComponent(localNetwork); // sieć w sieci

        int shopCount = 0;

        for (ShopComponent component : rootNetwork) {
            assertTrue(component instanceof BaseShop, "Iterator powinien zwracać tylko pojedyncze sklepy (liście)!");
            shopCount++;
        }

        assertEquals(3, shopCount, "Iterator nie odwiedził wszystkich sklepów w drzewie!");
    }

    // 3. TEST COMMAND
    @Test
    void testShopCommandAndUndo() {
        BaseShop shop = new PhysicalShop.Builder().setDescription("Opis A").build();

        ShopCommandInvoker invoker = new ShopCommandInvoker(null);
        ShopCommand updateCommand = new UpdateShopDescriptionCommand(shop, "Opis B");

        invoker.executeCommand(updateCommand, ShopEvent.SHOP_UPDATED, shop);
        assertEquals("Opis B", shop.getDescription());
        assertEquals(1, invoker.getHistorySize());

        invoker.undoLastCommand();
        assertEquals("Opis A", shop.getDescription(), "Command nie cofnął opisu sklepu!");
    }

    // 4. TEST MEDIATOR
    @Test
    void testShopCacheMediator() {
        ShopRepositoryProxy proxy = new ShopRepositoryProxy();
        proxy.clear();

        BaseShop shop = new PhysicalShop.Builder().setName("Sklep Testowy").build();
        proxy.addShop(shop);

        List<BaseShop> firstCall = proxy.getAllShops();

        ShopCacheMediator mediator = new ShopCacheMediator(proxy);
        ShopCommandInvoker invoker = new ShopCommandInvoker(mediator);

        ShopCommand command = new UpdateShopDescriptionCommand(shop, "Zmieniono");
        invoker.executeCommand(command, ShopEvent.SHOP_UPDATED, shop);

        List<BaseShop> secondCall = proxy.getAllShops();

        assertNotSame(firstCall, secondCall, "Mediator nie wyczyścił pamięci Cache po modyfikacji sklepu!");
    }

    // 5. TEST INTERPRETER
    @Test
    void testShopSearchInterpreter() {
        ShopRepository repo = ShopRepository.getInstance();

        repo.addShop(new PhysicalShop.Builder().setName("Media Expert").setCategory(ShopCategory.ELECTRONICS).build());
        repo.addShop(new PhysicalShop.Builder().setName("Media Markt").setCategory(ShopCategory.ELECTRONICS).build());
        repo.addShop(new OnlineShop.Builder().setName("Zalando").setCategory(ShopCategory.CLOTHING).build());
        repo.addShop(new PhysicalShop.Builder().setName("Biedronka").setCategory(ShopCategory.GROCERY).build());

        ShopSearchExpression isElectronics = new ShopCategoryExpression(ShopCategory.ELECTRONICS);
        ShopSearchExpression hasMediaInName = new ShopNameContainsExpression("Media");

        ShopSearchExpression complexQuery = new ShopAndExpression(isElectronics, hasMediaInName);

        List<BaseShop> results = repo.filter(complexQuery);

        assertEquals(2, results.size(), "Interpreter nie przefiltrował poprawnie bazy danych!");
        assertTrue(results.get(0).getName().contains("Media"));
        assertTrue(results.get(1).getName().contains("Media"));
    }

    @Test
    void testShopProfileHistorySizeAndTimestamp() {
        BaseShop shop = new PhysicalShop.Builder().setName("Sklep do Memento").build();
        ShopProfileHistory history = new ShopProfileHistory();

        assertEquals(0, history.getHistorySize());

        ShopProfileMemento memento1 = shop.saveProfileMemento();
        history.save(memento1);

        assertEquals(1, history.getHistorySize());
        assertNotNull(memento1.getTimestamp(), "Timestamp w Memento nie powinien być null po utworzeniu.");

        ShopProfileMemento memento2 = shop.saveProfileMemento();
        history.save(memento2);

        assertEquals(2, history.getHistorySize());

        history.undo();
        assertEquals(1, history.getHistorySize());
    }
}
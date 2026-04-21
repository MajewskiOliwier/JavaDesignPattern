package allnew.okk.shop.visitor;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;

import java.util.Map;
// Week 9 - Maintaining Clean Code Principles
// Week 7 - Open-Closed Principle (OCP)
// Week 6, Pattern Visitor 2
// Concrete visitor that calculates the total maintenance cost
// of all shops in the network based on their specific type.
public class MaintenanceCostVisitor implements ShopVisitor {

    private static final double INITIAL_COST = 0.0;
    private static final double DEFAULT_RATE = 0.0;

    private static final String RATE_PHYSICAL_BASE = "physical_base";
    private static final String RATE_PHYSICAL_DROPOFF = "physical_dropoff";
    private static final String RATE_ONLINE_BASE = "online_base";

    private static final String LOG_TYPE_PHYSICAL = "Sklep stacjonarny";
    private static final String LOG_TYPE_ONLINE = "Sklep internetowy";
    private static final String LOG_FORMAT = "[KOSZTY] %s [%s]: %s PLN";

    private double totalCost = INITIAL_COST;

    // Sterowanie danymi - cenniki nie są wpisane na sztywno, lecz wstrzykiwane z zewnątrz
    private final Map<String, Double> costRates;

    public MaintenanceCostVisitor(Map<String, Double> costRates) {
        if (costRates == null) {
            throw new IllegalArgumentException("Mapa stawek kosztów nie może być pusta.");
        }
        this.costRates = costRates;
    }

    @Override
    public void visit(PhysicalShop physicalShop) {
        validatePhysicalShop(physicalShop);
        double cost = calculatePhysicalShopCost(physicalShop);
        logCalculatedCost(LOG_TYPE_PHYSICAL, physicalShop.getName(), cost);
        accumulateTotalCost(cost);
    }

    @Override
    public void visit(OnlineShop onlineShop) {
        validateOnlineShop(onlineShop);
        double cost = calculateOnlineShopCost();
        logCalculatedCost(LOG_TYPE_ONLINE, onlineShop.getName(), cost);
        accumulateTotalCost(cost);
    }

    private void validatePhysicalShop(PhysicalShop physicalShop) {
        if (physicalShop == null) {
            throw new IllegalArgumentException("Odwiedzany sklep stacjonarny nie może być pusty.");
        }
    }

    private void validateOnlineShop(OnlineShop onlineShop) {
        if (onlineShop == null) {
            throw new IllegalArgumentException("Odwiedzany sklep internetowy nie może być pusty.");
        }
    }

    private double calculatePhysicalShopCost(PhysicalShop physicalShop) {
        double cost = fetchRate(RATE_PHYSICAL_BASE);
        if (physicalShop.isDropOffAvailable()) {
            cost += fetchRate(RATE_PHYSICAL_DROPOFF);
        }
        return cost;
    }

    private double calculateOnlineShopCost() {
        return fetchRate(RATE_ONLINE_BASE);
    }

    private double fetchRate(String rateKey) {
        return costRates.getOrDefault(rateKey, DEFAULT_RATE);
    }

    private void logCalculatedCost(String shopType, String shopName, double cost) {
        System.out.println(String.format(LOG_FORMAT, shopType, shopName, cost));
    }

    private void accumulateTotalCost(double cost) {
        this.totalCost += cost;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
// End Week 6, Pattern Visitor 2
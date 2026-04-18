package allnew.okk.shop.visitor;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;

import java.util.Map;

// Week 7 - Open-Closed Principle (OCP)
// Week 6, Pattern Visitor 2
// Concrete visitor that calculates the total maintenance cost
// of all shops in the network based on their specific type.
public class MaintenanceCostVisitor implements ShopVisitor {
    private double totalCost = 0.0;

    // Sterowanie danymi - cenniki nie są wpisane na sztywno, lecz wstrzykiwane z zewnątrz
    private final Map<String, Double> costRates;

    public MaintenanceCostVisitor(Map<String, Double> costRates) {
        this.costRates = costRates;
    }

    @Override
    public void visit(PhysicalShop physicalShop) {
        double cost = costRates.getOrDefault("physical_base", 0.0);

        if (physicalShop.isDropOffAvailable()) {
            cost += costRates.getOrDefault("physical_dropoff", 0.0);
        }

        System.out.println("Cost for Physical Shop [" + physicalShop.getName() + "]: " + cost + " PLN");
        totalCost += cost;
    }

    @Override
    public void visit(OnlineShop onlineShop) {
        double cost = costRates.getOrDefault("online_base", 0.0);

        System.out.println("Cost for Online Shop [" + onlineShop.getName() + "]: " + cost + " PLN");
        totalCost += cost;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
// End Week 6, Pattern Visitor 2
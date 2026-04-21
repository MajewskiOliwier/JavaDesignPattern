package allnew.okk.shop.adapter;

import allnew.okk.shop.model.PhysicalShop;

// Week 3, Pattern Adapter 1
// Adapter pozwalający traktować sklep stacjonarny jako punkt odbioru paczek dla zewnętrznego systemu kuriera.
// Tłumaczy metody niekompatybilnego obiektu PhysicalShop na interfejs wymagany przez CourierShippingPoint.
public class PhysicalShopShippingAdapter implements CourierShippingPoint {

    private static final String DROP_OFF_PREFIX = "PUNKT ODBIORU: ";

    // Nasza europejska wtyczka (Adaptee) - obiekt, który przystosowujemy
    private final PhysicalShop physicalShop;

    public PhysicalShopShippingAdapter(PhysicalShop physicalShop) {
        if (physicalShop == null) {
            throw new IllegalArgumentException("Sklep stacjonarny nie może być pusty (null).");
        }
        this.physicalShop = physicalShop;
    }

    // Tłumaczenie metod z systemu kuriera na metody naszego sklepu
    @Override
    public String getName() {
        return DROP_OFF_PREFIX + physicalShop.getName();
    }

    @Override
    public String getAddress() {
        return physicalShop.getAddress();
    }

    @Override
    public boolean isDropOffAvailable() {
        return physicalShop.isDropOffAvailable();
    }
}
// End Week 3, Pattern Adapter 1
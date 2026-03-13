package allnew.okk.shop.adapter;

import allnew.okk.shop.model.PhysicalShop;

// Tydzień 3, Wzorzec Adapter 1
// Adapter pozwalający traktować sklep stacjonarny jako punkt odbioru paczek dla zewnętrznego systemu kuriera.
// Tłumaczy metody niekompatybilnego obiektu PhysicalShop na interfejs wymagany przez CourierShippingPoint.
public class PhysicalShopShippingAdapter implements CourierShippingPoint {

    // Nasza europejska wtyczka (Adaptee) - obiekt, który przystosowujemy
    private PhysicalShop physicalShop;

    public PhysicalShopShippingAdapter(PhysicalShop physicalShop) {
        this.physicalShop = physicalShop;
    }

    // Tłumaczenie metod z systemu kuriera na metody naszego sklepu
    @Override
    public String getPointName() {
        return "PUNKT ODBIORU: " + physicalShop.getName();
    }

    @Override
    public String getFullAddress() {
        return physicalShop.getAddress();
    }

    @Override
    public boolean isAvailableForDropOff() {
        return physicalShop.isDropOffAvailable();    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 1
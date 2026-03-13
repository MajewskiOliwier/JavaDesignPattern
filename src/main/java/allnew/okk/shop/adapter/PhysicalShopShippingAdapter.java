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
        // Kurier wymaga nazwy punktu, więc bierzemy nazwę sklepu i dodajemy przedrostek
        return "PUNKT ODBIORU: " + physicalShop.getName();
    }

    @Override
    public String getFullAddress() {
        // Kurier wymaga pełnego adresu, więc przekazujemy adres ze sklepu stacjonarnego
        return physicalShop.getAddress();
    }

    @Override
    public boolean isAvailableForDropOff() {
        // Zakładamy, że z każdego naszego sklepu stacjonarnego kurier może odebrać paczkę
        return physicalShop.isDropOffAvailable();    }
}
// Koniec, Tydzień 3, Wzorzec Adapter 1
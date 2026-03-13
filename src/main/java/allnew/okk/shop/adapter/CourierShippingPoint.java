package allnew.okk.shop.adapter;

// Symulacja zewnętrznego interfejsu firmy kurierskiej (np. InPost, DPD)
// System kuriera wymaga, aby każdy punkt na mapie posiadał te 3 metody.
public interface CourierShippingPoint {
    String getPointName();
    String getFullAddress();
    boolean isAvailableForDropOff();
}
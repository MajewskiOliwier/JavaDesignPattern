package allnew.okk.shop.adapter;

import allnew.okk.shop.model.PhysicalShop;

// Week 3, Adapter Pattern 1
// Adapter that allows a physical shop to be treated as a parcel pickup point for an external courier system.
// It maps the methods of the incompatible PhysicalShop object to the interface required by CourierShippingPoint.
public class PhysicalShopShippingAdapter implements CourierShippingPoint {

    private PhysicalShop physicalShop;

    public PhysicalShopShippingAdapter(PhysicalShop physicalShop) {
        this.physicalShop = physicalShop;
    }

    @Override
    public String getPointName() {
        return "PUNKT ODBIORU: " + physicalShop.getDisplayName();
    }

    @Override
    public String getFullAddress() {
        return physicalShop.getAddress();
    }

    @Override
    public boolean isAvailableForDropOff() {
        return physicalShop.isDropOffAvailable();    }
}
// End of Week 3: Adapter Pattern 1
package allnew.okk.shop.adapter;

// Week 3, Adapter Pattern 1
// Simulates an external courier service interface (e.g., InPost, DPD).
// The courier system requires every map point to implement these three methods.
public interface CourierShippingPoint {
    String getPointName();
    String getFullAddress();
    boolean isAvailableForDropOff();
}
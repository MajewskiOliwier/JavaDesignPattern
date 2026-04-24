package allnew.okk.shop.model;
// Week 9 - Maintaining Clean Code Principles
// Week 8 - Liskov Substitution Principle 1 (Derived Class)
// The PhysicalShop class can replace BaseShop anywhere in the application.

// Week 2, Pattern Prototype 3
// Implement cloning for the PhysicalShop class.
public class PhysicalShop extends BaseShop {
    private String address;
    private boolean isDropOffAvailable;

    public PhysicalShop(Builder builder) {
        super(builder);
        this.address = builder.address;
        this.isDropOffAvailable = builder.isDropOffAvailable;
    }

    public String getAddress() { return address; }
    public boolean isDropOffAvailable() { return isDropOffAvailable; }

    // Week 2, Pattern  Builder 3
    public static class Builder extends BaseShop.Builder<Builder> {
        private String address;
        private boolean isDropOffAvailable = true;

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setDropOffAvailable(boolean dropOffAvailable) {
            this.isDropOffAvailable = dropOffAvailable;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public PhysicalShop build() {
            return new PhysicalShop(this);
        }
    }
    // End Week 2, Pattern  Builder 3

    @Override
    public PhysicalShop clone() {
        return (PhysicalShop) super.clone();
    }

    // Week 6, Pattern Visitor 6
    @Override
    public void accept(allnew.okk.shop.visitor.ShopVisitor visitor) {
        visitor.visit(this);
    }
}
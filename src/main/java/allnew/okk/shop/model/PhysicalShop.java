package allnew.okk.shop.model;

// Week 2, Prototype Pattern 3
// Implementation of cloning for the PhysicalShop class.
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

    // Week 2, Builder Pattern 3
    // Extension of the builder with the address field specific to a physical shop.
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
    // End of Week 2, Builder Pattern 3

    @Override
    public PhysicalShop clone() {
        try {
            return (PhysicalShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

// End of Week 2, Prototype Pattern 3
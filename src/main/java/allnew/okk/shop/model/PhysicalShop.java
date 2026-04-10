package allnew.okk.shop.model;

// Week 2, Pattern  Prototype 3
// Implementacja klonowania dla klasy PhysicalShop.
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
    // Rozszerzenie buildera o pole address specyficzne dla sklepu stacjonarnego.
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
        try {
            return (PhysicalShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    // Week 6, Pattern Visitor 6
    @Override
    public void accept(allnew.okk.shop.visitor.ShopVisitor visitor) {
        visitor.visit(this);
    }
}

// End Week 2, Pattern Prototype 3
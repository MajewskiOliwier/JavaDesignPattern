package allnew.okk.shop.model;

public class PhysicalShop extends BaseShop {
    private String address;

    public PhysicalShop(Builder builder) {
        super(builder);
        this.address = builder.address;
    }

    public String getAddress() { return address; }

    // Builder dla sklepu fizycznego dziedziczący po bazowym Builderze
    public static class Builder extends BaseShop.Builder<Builder> {
        private String address;

        public Builder setAddress(String address) {
            this.address = address;
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

    @Override
    public PhysicalShop clone() {
        try {
            return (PhysicalShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
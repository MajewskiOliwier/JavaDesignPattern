package allnew.okk.shop.model;

// Tydzień 2, Wzorzec Prototype 3
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

    // Tydzień 2, Wzorzec Builder 3
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
    // Koniec, Tydzień 2, Wzorzec Builder 3

    @Override
    public PhysicalShop clone() {
        try {
            return (PhysicalShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

// Koniec, Tydzień 2, Wzorzec Prototype 3
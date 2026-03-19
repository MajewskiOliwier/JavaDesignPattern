package allnew.okk.product.model;

import lombok.Getter;
import lombok.Setter;


// Klasa reprezentująca produkt prywatny, dziedzicząca po BaseProduct
@Getter
public class PrivateProduct extends BaseProduct {
    private final String sellerName;
    @Setter
    private String sellerContact;

    public PrivateProduct(Builder b) {
        super(b);
        this.sellerName = b.sellerName;
        this.sellerContact = b.sellerContact;
    }

    // Tydzień 3, Wzorzec Composite 3
    @Override
    public String getSellerName() {
        return sellerName;
    }

    @Override
    public String getSellerID() {
        return sellerName;
    }
    // Koniec, Tydzień 3, Wzorzec Composite 3

    // Tydzień 2, Wzorzec Builder 3
    // Implementacja wzorca Builder dla produktów
    public static class Builder extends BaseProduct.Builder<Builder> {
        private String sellerName;
        private String sellerContact;

        public Builder setSellerName(String sellerName) {
            this.sellerName = sellerName;
            return this;
        }

        public Builder setSellerContact(String sellerContact) {
            this.sellerContact = sellerContact;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public PrivateProduct build() {
            return new PrivateProduct(this);
        }
    }
    // Koniec, Tydzień 2, Wzorzec Builder 3

    public Builder toBuilder() {
        return new Builder()
                .setID(getID())
                .setName(getName())
                .setDescription(getDescription())
                .setPrice(getPrice())
                .setCategory(getCategory())
                .setCondition(getCondition())
                .setSellerName(sellerName)
                .setSellerContact(sellerContact);
    }

    // Tydzień 2, Wzorzec Prototype 3
    @Override
    public PrivateProduct clone() {
        try {
            // Zrzutowany na PrivateProduct
            return (PrivateProduct) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    // Koniec, Tydzień 2, Wzorzec Prototype 3

    @Override
    public String toString() {
        return super.toString() + "\n" +
                ", sellerName: " + sellerName + "\n" +
                ", sellerContact: " + sellerContact;
    }
}


package allnew.okk.shop.model;

// Tydzień 2 , Wzorzec Prototype 2
// Implementacja klonowania dla klasy OnlineShop.
public class OnlineShop extends BaseShop {
    private String websiteUrl;

    public OnlineShop(Builder builder) {
        super(builder);
        this.websiteUrl = builder.websiteUrl;
    }

    public String getWebsiteUrl() { return websiteUrl; }

    // Tydzień 2, Wzorzec Builder 2
    // Rozszerzenie buildera o pole websiteUrl specyficzne dla sklepu internetowego.
    public static class Builder extends BaseShop.Builder<Builder> {
        private String websiteUrl;

        public Builder setWebsiteUrl(String websiteUrl) {
            this.websiteUrl = websiteUrl;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public OnlineShop build() {
            return new OnlineShop(this);
        }
    }
    // Koniec, Tydzień 2, Wzorzec Builder 2

    @Override
    public OnlineShop clone() {
        try {
            return (OnlineShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
// Koniec, Tydzień 2, Wzorzec Prototype 2
package allnew.okk.shop.model;
// Week 9 - Maintaining Clean Code Principles
// Week 8 - Liskov Substitution Principle 1 (Klasa pochodna)
// Klasa OnlineShop może zastąpić BaseShop w każdym miejscu aplikacji

// Week 2, Pattern Prototype 2
// Implementacja klonowania dla klasy OnlineShop.
public class OnlineShop extends BaseShop {
    private String websiteUrl;

    public OnlineShop(Builder builder) {
        super(builder);
        this.websiteUrl = builder.websiteUrl;
    }

    public String getWebsiteUrl() { return websiteUrl; }

    // Tydzień 2, Wzorzec Builder 2
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
        return (OnlineShop) super.clone();
    }

    // Week 6, Pattern Visitor 6
    @Override
    public void accept(allnew.okk.shop.visitor.ShopVisitor visitor) {
        visitor.visit(this);
    }
}
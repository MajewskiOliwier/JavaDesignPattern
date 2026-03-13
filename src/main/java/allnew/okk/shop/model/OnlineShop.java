package allnew.okk.shop.model;

// Week 2, Prototype Pattern 2
// Implementation of cloning for the OnlineShop class.
public class OnlineShop extends BaseShop {
    private String websiteUrl;

    public OnlineShop(Builder builder) {
        super(builder);
        this.websiteUrl = builder.websiteUrl;
    }

    public String getWebsiteUrl() { return websiteUrl; }

    // Week 2, Builder Pattern 2
    // Extension of the builder with the websiteUrl field specific to an online shop.
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
    // End of Week 2, Builder Pattern 2

    @Override
    public OnlineShop clone() {
        try {
            return (OnlineShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
// End of Week 2, Prototype Pattern 2
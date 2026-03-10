package allnew.okk.shop.model;

public class OnlineShop extends BaseShop {
    private String websiteUrl;

    public OnlineShop(Builder builder) {
        super(builder);
        this.websiteUrl = builder.websiteUrl;
    }

    public String getWebsiteUrl() { return websiteUrl; }

    // Builder dla sklepu internetowego dziedziczący po bazowym Builderze
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

    @Override
    public OnlineShop clone() {
        try {
            return (OnlineShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
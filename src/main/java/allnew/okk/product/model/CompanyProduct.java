package allnew.okk.product.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CompanyProduct extends BaseProduct {
    private final String companyName;
    private final String companyAddress;
    @Setter
    private String companyContact;
    private final String companyEmail;
    private final String NIP;
    private final String REGON;

    public CompanyProduct(Builder b){
        super(b);
        this.companyName = b.companyName;
        this.companyAddress = b.companyAddress;
        this.companyContact = b.companyContact;
        this.companyEmail = b.companyEmail;
        this.NIP = b.NIP;
        this.REGON = b.REGON;
    }


    public static class Builder extends BaseProduct.Builder<Builder> {
        private String companyName;
        private String companyAddress;
        private String companyContact;
        private String companyEmail;
        private String NIP;
        private String REGON;

        public Builder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
            return this;
        }

        public Builder setCompanyContact(String companyContact) {
            this.companyContact = companyContact;
            return this;
        }

        public Builder setCompanyEmail(String companyEmail) {
            this.companyEmail = companyEmail;
            return this;
        }

        public Builder setNIP(String NIP) {
            this.NIP = NIP;
            return this;
        }

        public Builder setREGON(String REGON) {
            this.REGON = REGON;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public CompanyProduct build() {
            //to validate
            return new CompanyProduct(this);
        }
    }

    public Builder toBuilder() {
        return new Builder()
                .setName(getName())
                .setDescription(getDescription())
                .setPrice(getPrice())
                .setCategory(getCategory())
                .setCondition(getCondition())
                .setCompanyName(companyName)
                .setCompanyAddress(companyAddress)
                .setCompanyContact(companyContact)
                .setCompanyEmail(companyEmail)
                .setNIP(NIP)
                .setREGON(REGON);
    }

    @Override
    public CompanyProduct clone() {
        try {
            // zrzutowany obiekt na CompanyProduct
            return (CompanyProduct) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                ", companyName: " + companyName + "\n" +
                ", companyAddress: " + companyAddress + "\n" +
                ", companyContact: " + companyContact + "\n" +
                ", companyEmail: " + companyEmail + "\n" +
                ", NIP: " + NIP + "\n" +
                ", REGON: " + REGON;
    }

}

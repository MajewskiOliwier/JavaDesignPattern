package allnew.okk.product.service;

import allnew.okk.product.model.BaseProduct;

// Week 6, Open-Closed Principle, implementation
public class NameSuffixModifier implements DuplicationModifier {
    private final String suffix;

    public NameSuffixModifier(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void modify(BaseProduct product) {
        product.setName(product.getName() + " " + suffix);
    }
}
// End Week 6, Open-Closed Principle, implementation
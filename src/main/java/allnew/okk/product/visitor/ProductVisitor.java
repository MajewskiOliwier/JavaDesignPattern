package allnew.okk.product.visitor;

import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;

// Week 6, pattern visitor Marciniuk
public interface ProductVisitor {
    void visit(PrivateProduct product);
    void visit(CompanyProduct product);
}
// End Week 6, pattern visitor Marciniuk
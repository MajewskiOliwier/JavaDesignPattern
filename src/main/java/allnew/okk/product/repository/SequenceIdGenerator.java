package allnew.okk.product.repository;

// Week 7, open-closed principle (OCP), implementation, jakub marciniuk
public class SequenceIdGenerator implements IdGenerator {
    private final String prefix;
    private int counter = 1;
    public SequenceIdGenerator(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String generateId() {
        return prefix + counter++;
    }

}
// End Week 7, open-closed principle, implementation
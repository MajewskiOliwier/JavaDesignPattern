package allnew.okk.product.repository;

// Week 6, open-closed principle, implementation
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
// End Week 6, open-closed principle, implementation
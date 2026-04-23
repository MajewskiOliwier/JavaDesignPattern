package allnew.okk.product.exception;

// Week 9, custom exceptions, jakub marciniuk
public class ProductExceptions {
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) { super(message); }
    }
    public static class ProductRemovalException extends RuntimeException {
        public ProductRemovalException(String message) { super(message); }
    }
    public static class ProductDuplicationException extends RuntimeException {
        public ProductDuplicationException(String message, Throwable cause) { super(message, cause); }
    }
}
// End Week 9, custom exceptions, jakub marciniuk
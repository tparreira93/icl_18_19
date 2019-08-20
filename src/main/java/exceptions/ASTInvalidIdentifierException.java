package exceptions;

public class ASTInvalidIdentifierException extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTInvalidIdentifierException(String message) {
        super(message);
    }
}

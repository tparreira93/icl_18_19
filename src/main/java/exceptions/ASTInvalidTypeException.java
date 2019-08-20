package exceptions;

public class ASTInvalidTypeException extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTInvalidTypeException(String message) {
        super(message);
    }
}

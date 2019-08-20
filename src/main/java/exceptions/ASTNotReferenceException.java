package exceptions;

public class ASTNotReferenceException extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTNotReferenceException(String message) {
        super(message);
    }
}

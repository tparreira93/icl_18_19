package exceptions;

public class ASTNonComparableException extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTNonComparableException(String message) {
        super(message);
    }
}

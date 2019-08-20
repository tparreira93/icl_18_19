package exceptions;

public class ASTNotNumberException extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTNotNumberException(String message) {
        super(message);
    }
}

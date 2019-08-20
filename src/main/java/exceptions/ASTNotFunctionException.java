package exceptions;

public class ASTNotFunctionException extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTNotFunctionException(String message) {
        super(message);
    }
}

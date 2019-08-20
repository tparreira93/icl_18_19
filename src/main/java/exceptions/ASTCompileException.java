package exceptions;

public abstract class ASTCompileException extends ASTException {
    private static final long serialVersionUID = 1L;

    public ASTCompileException(String message) {
        super(message);
    }
}

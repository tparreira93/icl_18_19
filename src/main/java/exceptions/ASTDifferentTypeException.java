package exceptions;

public class ASTDifferentTypeException extends ASTCompileException {
    private static final long serialVersionUID = 1L;

    public ASTDifferentTypeException(String message) {
        super(message);
    }
}

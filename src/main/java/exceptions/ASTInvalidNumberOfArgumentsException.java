package exceptions;

public class ASTInvalidNumberOfArgumentsException extends ASTCompileException {
    private static final long serialVersionUID = 1L;

    public ASTInvalidNumberOfArgumentsException(String message) {
        super(message);
    }
}

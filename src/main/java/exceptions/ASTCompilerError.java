package exceptions;

public class ASTCompilerError extends ASTCompileException {
    private static final long serialVersionUID = 1L;

    public ASTCompilerError(String message) {
        super(message);
    }
}

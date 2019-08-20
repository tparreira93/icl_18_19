package exceptions;

public class ASTNotValueType extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTNotValueType(String message) {
        super(message);
    }
}

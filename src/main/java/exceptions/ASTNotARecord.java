package exceptions;

public class ASTNotARecord extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTNotARecord(String message) {
        super(message);
    }
}

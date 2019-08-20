package exceptions;

public class ASTNotAMember extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTNotAMember(String message) {
        super(message);
    }
}

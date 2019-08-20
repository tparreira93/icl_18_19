package exceptions;

public class ASTDuplicateNameException extends ASTCompileException {
    private static final long serialVersionUID = 1L;
    
    public ASTDuplicateNameException(String message) {
        super(message);
    }

}

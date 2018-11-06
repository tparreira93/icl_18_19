package AST.Exceptions;

import AST.ASTEnvironment;

public class ASTDuplicateNameException extends ASTException {
    public ASTDuplicateNameException(String message) {
        super(message);
    }

}

package AST.Exceptions;

import AST.ASTEnvironment;

public abstract class ASTException extends Exception {
    public ASTException(String message) {
        super(message);
    }
}

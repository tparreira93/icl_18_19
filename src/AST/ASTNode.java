package AST;

import values.IValue;

public interface ASTNode {
    IValue eval(ASTEnvironment environment);
}

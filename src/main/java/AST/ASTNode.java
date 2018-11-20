package AST;

import AST.types.IType;
import AST.values.IValue;

public interface ASTNode {
    IValue eval(ASTEnvironment<IValue> environment) throws Exception;
    IType typecheck(ASTEnvironment<IType> environment) throws Exception;
}

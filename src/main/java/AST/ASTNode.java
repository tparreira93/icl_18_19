package AST;

import AST.types.IType;
import AST.values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public interface ASTNode {
    IValue eval(ASTEnvironment<IValue> environment) throws Exception;
    IType typecheck(ASTEnvironment<IType> environment) throws Exception;

    Code compile(CompilerEnvironment environment);
}

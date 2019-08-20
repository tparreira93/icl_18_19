package ast;

import compiler.Code;
import compiler.CompilerEnvironment;
import types.IType;
import utils.Environment;
import values.IValue;

public interface ASTNode {
    IValue<?> eval(Environment<IValue<?>> environment) throws Exception;
    IType typecheck(Environment<IType> environment) throws Exception;

    Code compile(CompilerEnvironment environment);
}

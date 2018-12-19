package ast;

import exceptions.ASTDifferentTypeException;
import exceptions.ASTNonLogicalException;
import types.BoolType;
import types.IType;
import utils.Environment;
import values.BoolValue;
import values.IValue;
import compiler.Code;
import compiler.Compiler;
import compiler.CompilerEnvironment;

public class ASTIfThenElse implements ASTNode {
    private final ASTNode node_if;
    private final ASTNode node_then;
    private final ASTNode node_else;

    public ASTIfThenElse(ASTNode node_if, ASTNode node_then, ASTNode node_else) {
        this.node_if = node_if;
        this.node_then = node_then;
        this.node_else = node_else;
    }

    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        IValue if_value = node_if.eval(environment);
        IValue result;

        BoolValue ifb = (BoolValue) if_value;

        if (ifb.getValue())
        {
            Environment<IValue> localScope = environment.beginScope();
            result = node_then.eval(localScope);
            localScope.endScope();
        }
        else
        {
            Environment<IValue> localScope = environment.beginScope();
            result = node_else.eval(localScope);
            localScope.endScope();
        }

        return result;
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        IType if_type = node_if.typecheck(environment);

        if (!(if_type instanceof BoolType))
            throw new ASTNonLogicalException("If condition should be a boolean value! " + "(it is " + if_type + ")");

        IType then_type = node_then.typecheck(environment);
        IType else_type = node_else.typecheck(environment);

        if (!(then_type.equals(else_type)))
            throw new ASTDifferentTypeException("If-then-else return type should be the same for then and else clause. Then clauae is of type " + then_type + " and else clause else is of type " + else_type + ".");

        return then_type;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        Compiler compiler = Compiler.getInstance();
        String l1 = compiler.generateLabel();
        String l2 = compiler.generateLabel();
        return new Code()
                .addCode("; --- BEGIN ASTIfThenElse ---")
                .addCode(node_if.compile(environment))
                .addCode("ifeq " + l1)
                .addCode(node_then.compile(environment))
                .addCode("goto " + l2)
                .addCode(l1 + ": ")
                .addCode(node_else.compile(environment))
                .addCode(l2 + ": ")
                .addCode("; --- END ASTIfThenElse ---");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

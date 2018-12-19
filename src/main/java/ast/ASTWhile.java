package ast;

import exceptions.ASTNonLogicalException;
import types.BoolType;
import types.IType;
import utils.Environment;
import values.IValue;
import compiler.Code;
import compiler.Compiler;
import compiler.CompilerEnvironment;

public class ASTWhile implements ASTNode {
    private final ASTNode condition;
    private final ASTNode action;

    public ASTWhile(ASTNode condition, ASTNode action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        IValue condition_value = condition.eval(environment);

        Environment<IValue> localScope = environment.beginScope();
        while ((boolean)condition_value.getValue())
        {
            action.eval(localScope);
            condition_value = condition.eval(environment);
        }
        localScope.endScope();

        return condition_value;
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        IType condition_type = condition.typecheck(environment);

        if (!(condition_type instanceof BoolType))
            throw new ASTNonLogicalException("While condition should evaluate to a bool type! " + "(it is " + condition_type + ")");

        Environment<IType> localScope = environment.beginScope();

        action.typecheck(localScope);

        localScope.endScope();

        return condition_type;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {

        Compiler compiler = Compiler.getInstance();
        String l1 = compiler.generateLabel();
        String l2 = compiler.generateLabel();
        return new Code()
                .addCode("; --- BEGIN ASTWhile ---")
                .addCode(l1 + ":")
                .addCode(condition.compile(environment))
                .addCode("ifeq " + l2)
                .addCode(action.compile(environment))
                .addCode("goto " + l1)
                .addCode(l2 + ":")
                .addCode("; --- END ASTWhile ---");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

package AST;

import AST.Exceptions.ASTDifferentTypeException;
import AST.Exceptions.ASTDuplicateNameException;
import AST.types.IType;
import AST.values.IValue;
import compiler.Code;
import compiler.CompilerEnvironment;

import java.util.List;

public class ASTLet implements ASTNode {
    private final List<Binding> identifiers;
    private final ASTNode body;

    public ASTLet(List<Binding> identifiers, ASTNode body) {
        this.identifiers = identifiers;
        this.body = body;
    }
    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        ASTEnvironment<IValue> localScope = environment.beginScope();

        for (Binding identifier : identifiers) {
            ASTNode n = identifier.getExpression();

            ASTEnvironment<IValue> bindingScope = environment.beginScope();
            IValue v = n.eval(bindingScope);

            bindingScope.assoc(identifier.getId(), v);
            bindingScope.endScope();

            localScope.assoc(identifier.getId(), v);
        }

        IValue bodyResult = body.eval(localScope);

        localScope.endScope();
        return bodyResult;
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        ASTEnvironment<IType> localScope = environment.beginScope();

        for (Binding identifier : identifiers) {
            ASTNode n = identifier.getExpression();

            if (localScope.find(identifier.getId()) != null)
                throw new ASTDuplicateNameException("More than one identifier with the same name is not allowed.");

            ASTEnvironment<IType> bindingScope = environment.beginScope();
            bindingScope.assoc(identifier.getId(), identifier.getType());

            IType v = n.typecheck(bindingScope);
            if (!identifier.getType().equals(v))
                throw new ASTDifferentTypeException("Identifier type and binding type do not match. " + "Expected " + identifier.getType() + " but got " + v + "!");

            bindingScope.endScope();

            localScope.assoc(identifier.getId(), identifier.getType());
        }

        IType bodyType = body.typecheck(localScope);

        localScope.endScope();

        return bodyType;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

package AST;

import AST.Exceptions.ASTDuplicateNameException;
import values.IValue;

import java.util.List;

public class ASTLet implements ASTNode {
    private final List<Binding> identifiers;
    private final ASTNode body;

    public ASTLet(List<Binding> identifiers, ASTNode body) {
        this.identifiers = identifiers;
        this.body = body;
    }
    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        ASTEnvironment localScope = environment.beginScope();

        for (Binding identifier : identifiers) {
            ASTNode n = identifier.getExpression();

            if (localScope.find(identifier.getId()) != null)
                throw new ASTDuplicateNameException("More than one identifier with the same name is not allowed.");

            ASTEnvironment bindingScope = environment.beginScope();
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
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

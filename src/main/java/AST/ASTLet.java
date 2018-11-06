package AST;

import AST.Exceptions.ASTDuplicateNameException;
import values.FunctionValue;
import values.IValue;

import java.util.List;

public class ASTLet implements ASTNode {
    private List<Binding> identifiers;
    private ASTNode body;

    public ASTLet(List<Binding> identifiers, ASTNode body) {
        this.identifiers = identifiers;
        this.body = body;
    }
    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        ASTEnvironment localScope = environment.beginScope();

        for (Binding identifier : identifiers) {
            ASTNode n = identifier.getExpression();
            IValue v = null;

            if (localScope.find(identifier.getId()) != null)
                throw new ASTDuplicateNameException("More than one identifier with the same name is not allowed.");

            ASTEnvironment bindingScope = environment.beginScope();
            v = n.eval(bindingScope);
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
        return this.getClass().getSimpleName() + ": " + " - " + String.join(", ", identifiers.toString());
    }
}

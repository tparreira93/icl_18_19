package AST;

import values.IValue;

import java.util.List;

public class ASTLet implements ASTNode {
    List<Var> identifiers;
    private ASTNode body;

    public ASTLet(List<Var> identifiers, ASTNode body) {
        this.identifiers = identifiers;
        this.body = body;
    }
    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        ASTEnvironment localScope = environment.beginScope();

        for (int i = 0; i < identifiers.size(); i++) {
            localScope.assoc(identifiers.get(i).getId(), identifiers.get(i).getExpression().eval(environment));
        }

        IValue bodyResult = body.eval(localScope);

        localScope.endScope();
        return bodyResult;
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + ": " + " - " + String.join(", ", identifiers.toString());
    }
}

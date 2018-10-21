package AST;

import values.IValue;

import java.util.ArrayList;
import java.util.List;

public class ASTLet implements ASTNode {
    List<Assignment> identifiers;
    private ASTNode body;

    public ASTLet(List<Assignment> identifiers, ASTNode body) {
        this.identifiers = identifiers;
        this.body = body;
    }
    @Override
    public IValue eval(ASTEnvironment environment) throws Exception {
        List<IValue> values = new ArrayList<>();

        for (Assignment assignment: identifiers) {
            IValue value = assignment.getExpression().eval(environment);

            if (value == null)
                throw new Exception("There is no definition for \"" + assignment.getExpression() + "\".");

            values.add(value);
        }
        ASTEnvironment localScope = environment.beginScope();

        for (int i = 0; i < values.size(); i++) {
            localScope.assoc(identifiers.get(i).getId(), values.get(i));
        }

        IValue bodyResult = body.eval(localScope);

        localScope.endScope();
        return bodyResult;
    }
}

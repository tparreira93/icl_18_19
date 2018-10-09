package AST;

import values.IValue;

public class ASTLet implements ASTNode {

    private String identifier;
    private ASTNode body;
    private ASTNode expr_int;

    public ASTLet(String identifier, ASTNode expr_int, ASTNode body) {
        this.identifier = identifier;
        this.body = body;
        this.expr_int = expr_int;
    }
    @Override
    public IValue eval(ASTEnvironment environment) {
        IValue valInit = expr_int.eval(environment);
        ASTEnvironment localScope = environment.beginScope();
        localScope.assoc(identifier, new ASTNum((int)valInit.getValue()));
        IValue bodyResult = body.eval(localScope);
        localScope.endScope();
        return bodyResult;
    }
}

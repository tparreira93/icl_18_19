package AST.types;

import AST.ASTEnvironment;
import AST.ASTNode;
import AST.Exceptions.ASTNotNumberException;

import java.lang.reflect.Method;

public abstract class NumericType {
    public static IType typecheck(ASTEnvironment<IType> environment, ASTNode left, ASTNode right) throws Exception {
        IType v1 = left.typecheck(environment);
        IType v2 = right.typecheck(environment);

        if (!v1.equals(v2) || !(v1 instanceof NumericType && v2 instanceof NumericType))
            throw new ASTNotNumberException("This operation is not supported between " + v1 + " and " + v2 + ".");

        Method method = v1.getClass().getMethod("getInstance");

        return (IType)method.invoke(null);
    }
}

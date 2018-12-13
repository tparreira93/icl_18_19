package AST;

import AST.types.IType;
import AST.types.RecordType;
import AST.values.IValue;
import AST.values.RecordValue;
import compiler.Code;
import compiler.CompilerEnvironment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ASTRecord implements ASTNode {
    private List<SimpleBinding<ASTNode>> bindings;

    public ASTRecord(List<SimpleBinding<ASTNode>> bindings) {
        this.bindings = bindings;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        HashMap<String, IValue> values = new HashMap<>();
        for (SimpleBinding<ASTNode> b : bindings)
            values.put(b.id, b.expression.eval(environment));

        return new RecordValue(values);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        List<SimpleBinding<IType>> bindingTypes = new ArrayList<>();
        for (SimpleBinding<ASTNode> b : bindings){
            bindingTypes.add(new SimpleBinding<>(b.id, b.expression.typecheck(environment)));
        }
        return new RecordType(bindingTypes);
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

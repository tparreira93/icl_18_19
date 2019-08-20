package ast;

import compiler.ClassField;
import types.IType;
import types.RecordType;
import utils.Environment;
import values.IValue;
import values.RecordValue;
import compiler.Code;
import compiler.CompilerEnvironment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ASTRecord implements ASTNode {
    private List<SimpleBinding<ASTNode>> bindings;
    private RecordType record;
    public ASTRecord(List<SimpleBinding<ASTNode>> bindings) {
        this.bindings = bindings;
    }

    @Override
    public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
        HashMap<String, IValue<?>> values = new HashMap<>();
        for (SimpleBinding<ASTNode> b : bindings)
            values.put(b.id, b.expression.eval(environment));

        return new RecordValue(values);
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        List<SimpleBinding<IType>> bindingTypes = new ArrayList<>();
        for (SimpleBinding<ASTNode> b : bindings){
            bindingTypes.add(new SimpleBinding<>(b.id, b.expression.typecheck(environment)));
        }
        record = new RecordType(bindingTypes);
        return record;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        Code finalCode = new Code()
                .addCode("; BEGIN ASTRecord")
                .addCode("new " + record.getClassName())
                .addCode("dup")
                .addCode("invokespecial " + record.getClassName() + "/<init>()V");

        List<ClassField> classFields = record.getClassFields();
        for (int i = 0; i < classFields.size(); i++) {
            ClassField field = classFields.get(i);
            finalCode.addCode("dup")
                    .addCode(bindings.get(i).getExpression().compile(environment))
                    .addCode("putfield " + record.getClassName() + "/" + field.getFieldName() + " " + field.getCompiledType());
        }

        return finalCode.addCode("; END ASTRecord");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

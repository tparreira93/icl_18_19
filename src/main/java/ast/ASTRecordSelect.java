package ast;

import compiler.ClassField;
import exceptions.ASTNotAMember;
import exceptions.ASTNotARecord;
import types.AnonymousType;
import types.IType;
import types.RecordType;
import utils.Environment;
import values.IValue;
import values.RecordValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTRecordSelect implements ASTNode {
    private final ASTNode record;
    private final String id;
    private RecordType recordType;

    public ASTRecordSelect(ASTNode record, String id) {
        this.record = record;
        this.id = id;
    }

    @Override
    public IValue eval(Environment<IValue> environment) throws Exception {
        RecordValue r = (RecordValue) record.eval(environment);
        return r.find(id);
    }

    @Override
    public IType typecheck(Environment<IType> environment) throws Exception {
        IType r = record.typecheck(environment);

        if (!(r instanceof RecordType))
            throw new ASTNotARecord(r + " is not a record!");

        recordType = (RecordType)r;
        IType t = recordType.find(id);

        if (t == null)
            throw new ASTNotAMember(id + " is not a member of record " + record);

        return t;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        ClassField field = recordType.getBindingClassField(id);

        return new Code()
                .addCode(record.compile(environment))
                .addCode()
                .addCode("getfield " + recordType.getClassName() + "/" + field.getFieldName() + " " + field.getCompiledType());
    }
}

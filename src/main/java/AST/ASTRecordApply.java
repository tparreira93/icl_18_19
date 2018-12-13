package AST;

import AST.Exceptions.ASTNotAMember;
import AST.Exceptions.ASTNotARecord;
import AST.types.IType;
import AST.types.RecordType;
import AST.values.IValue;
import AST.values.RecordValue;
import compiler.Code;
import compiler.CompilerEnvironment;

public class ASTRecordApply implements ASTNode {
    private final ASTNode record;
    private final String id;

    public ASTRecordApply(ASTNode record, String id) {
        this.record = record;
        this.id = id;
    }

    @Override
    public IValue eval(ASTEnvironment<IValue> environment) throws Exception {
        RecordValue r = (RecordValue) record.eval(environment);
        return r.find(id);
    }

    @Override
    public IType typecheck(ASTEnvironment<IType> environment) throws Exception {
        IType r = record.typecheck(environment);

        if (!(r instanceof RecordType))
            throw new ASTNotARecord(r + " is not a record!");

        RecordType recordType = (RecordType)r;
        IType t = recordType.find(id);

        if (t == null)
            throw new ASTNotAMember(id + " is not a member of record " + record);

        return t;
    }

    @Override
    public Code compile(CompilerEnvironment environment) {
        return null;
    }
}

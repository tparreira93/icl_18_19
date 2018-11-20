package AST.types;

public class RefType implements IType {

    private IType reference;

    public RefType(IType reference) {
        this.reference = reference;
    }

    public IType getReferenceType() {
        return reference;
    }

    public void setReference(IType reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "ref(" + reference + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RefType)
            return ((RefType) obj).getReferenceType().equals(getReferenceType());

        return true;
    }
}

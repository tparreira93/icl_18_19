package types;

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
        return getTypeName() + "(" + reference + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RefType)
            return ((RefType) obj).getReferenceType().equals(getReferenceType());

        return false;
    }

    @Override
    public String getClassName() {
        if (reference instanceof RefType)
            return "Reference_reference";
        return "Reference_" + reference.getTypeName();
    }

    public String getContentClassReference() {
        if (reference instanceof RefType)
            return "Ljava/lang/Object;";
        return reference.getClassReference();
    }

    @Override
    public String getClassReference() {
        return "L" + getClassName() + ";";
    }

    @Override
    public String getTypeName() {
        return "reference";
    }
}

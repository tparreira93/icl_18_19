package AST.types;

import AST.SimpleBinding;

import java.util.List;

public class RecordType implements IType {
    private List<SimpleBinding<IType>> bindingTypes;

    public RecordType(List<SimpleBinding<IType>> bindingTypes) {
        this.bindingTypes = bindingTypes;
    }

    public IType find(String name) {
        for (SimpleBinding<IType> bindingType : bindingTypes) {
            if (bindingType.getId().equals(name)) {
                return bindingType.getExpression();
            }
        }
        return null;
    }

    private List<SimpleBinding<IType>> getBindingTypes() {
        return bindingTypes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RecordType) {
            RecordType other = (RecordType) obj;

            for (SimpleBinding<IType> t : bindingTypes) {
                IType otherT = other.find(t.getId());

                if (otherT == null)
                    return false;

                if (!otherT.equals(t.getExpression()))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String getClassName() {
        return getClassReference();
    }

    @Override
    public String getClassReference() {
        return null;
    }

    @Override
    public String getTypeName() {
        return "record";
    }

    @Override
    public String toString() {
        return getTypeName();
    }
}

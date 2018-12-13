package AST.types;

import AST.SimpleBinding;
import AST.values.IValue;
import AST.values.RecordValue;

import java.util.List;

public class RecordType implements IType, IValueType {
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

}

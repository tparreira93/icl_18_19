package types;

import ast.SimpleBinding;
import compiler.ClassField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RecordType implements IType, IAnonymousType {
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

    public ClassField getBindingClassField(String id) {
        ClassField field = null;
        List<ClassField> allFields = getClassFields();
        for (int i = 0; i < bindingTypes.size(); i++) {
            SimpleBinding<IType> b = bindingTypes.get(i);
            if (b.getId().equals(id)) {
                field = allFields.get(i);
                break;
            }
        }

        return field;
    }

    public List<SimpleBinding<IType>> getBindingTypes() {
        return bindingTypes;
    }

    public List<ClassField> getClassFields() {
        List<ClassField> recordFields = new ArrayList<>();
        IntStream.range(0, getBindingTypes().size()).forEach(j -> {
            SimpleBinding<IType> binding = getBindingTypes().get(j);
            ClassField c = new ClassField(j, binding.getExpression());
            recordFields.add(c);
        });

        return recordFields;
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


    public static String getClassName(RecordType record) {
        String className;
        StringBuilder tmp = new StringBuilder();
        for (SimpleBinding<IType> t : record.bindingTypes) {
            if (!tmp.toString().equals(""))
                tmp.append("_");
            tmp.append(t.getExpression().getTypeName());
        }
        className = "anonymous_type_" + tmp.toString();

        return className;
    }

    @Override
    public String getClassName() {
        return getClassName(this);
    }

    @Override
    public String getClassReference() {
        return "L" + getClassName() + ";";
    }

    @Override
    public String getTypeName() {
        return "record";
    }

    @Override
    public String getReturnKeywork() {
        return "areturn";
    }

    @Override
    public String getLoadKeyword() {
        return "aload";
    }

    @Override
    public String toString() {
        return getTypeName();
    }
}

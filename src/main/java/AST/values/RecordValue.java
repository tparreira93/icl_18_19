package AST.values;

import AST.Exceptions.ASTNonComparableException;

import java.util.HashMap;
import java.util.Map;

public class RecordValue  implements IValue{
    private HashMap<String, IValue> values;

    public RecordValue(HashMap<String, IValue> values) {
        this.values = values;
    }

    @Override
    public String getName() {
        return "record";
    }

    @Override
    public Object getValue() {
        return null;
    }

    public IValue find(String id) {
        return values.get(id);
    }

    @Override
    public int compareTo(IValue v) throws Exception {
        throw new ASTNonComparableException(this + " is not comparable!");
    }

    @Override
    public boolean equals(IValue v) throws Exception {
        RecordValue recordV = (RecordValue) v;

        for (Map.Entry<String, IValue> val: values.entrySet()){
            IValue t = recordV.find(val.getKey());
            if (t == null || !t.equals(val.getValue()))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        for (Map.Entry<String, IValue> val: values.entrySet()){
            if (build.length() > 0)
                build.append(", ");
            build.append(val.getKey());
        }
        return "record(" + build.toString() + ")";
    }
}

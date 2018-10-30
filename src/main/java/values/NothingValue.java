package values;

public class NothingValue implements IValue {
    @Override
    public String getName() {
        return "nothing";
    }

    @Override
    public Object getValue() {
        return "";
    }

    @Override
    public int compareTo(IValue v) throws Exception {
        return 0;
    }

    @Override
    public boolean equals(IValue v) throws Exception {
        return false;
    }
}

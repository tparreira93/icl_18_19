package compiler;

public class Frame {
    private String frameName;

    public Frame(String frameName) {
        this.frameName = frameName;
    }

    public String getFrameName() {
        return frameName;
    }

    public String getFrameReference() {
        if (getFrameName().length() == 0)
            return "Ljava/lang/Object;";

        return "L" + getFrameName() + ";";
    }
}

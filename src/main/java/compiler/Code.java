package compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Code {
    private List<String> code;
    private static String[] tabStart = new String[] {
            ".method"
    };
    private static String[] tabEnd = new String[] {
            ".end"
    };

    public Code() {
        code = new ArrayList<>();
    }

    public Code(List<String> codeDump) {
        this.code = codeDump;
    }

    public Code addCode(String code) {
        this.code.add(code);
        return this;
    }
    public Code addCode(Code code) {
        this.addCode("");
        this.code.addAll(code.getCode());
        this.addCode("");
        return this;
    }
    public List<String> getCode() {
        return code;
    }

    public String Dump(){
        return Dump(0);
    }

    public String Dump(int initialTab) {
        StringBuilder builder = new StringBuilder();
        String t = "\t";
        String tabs = IntStream.range(0, initialTab).mapToObj(j -> t).collect(Collectors.joining());
        //String tabs = "";

        for (String c : code) {
            if (Arrays.stream(tabEnd).anyMatch(c::contains))
                tabs = tabs.replaceFirst(t, "");

            builder.append(tabs).append(c).append(System.getProperty("line.separator")).append(" ");

            if (Arrays.stream(tabStart).anyMatch(c::contains))
                tabs += "\t";
        }

        return builder.toString();
    }
}

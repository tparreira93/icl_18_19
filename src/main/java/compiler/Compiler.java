package compiler;

import exceptions.ASTCompileException;
import exceptions.ASTCompilerError;
import exceptions.ASTRuntimeException;
import types.FunctionType;
import types.RefType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Compiler {
    private static Compiler instance = null;
    private int nextFrameID;
    private String currentFrame;
    private int stackSize;
    private int SL;
    private int nextLabel;
    private List<IClassFile> files;
    private String jasminPath;

    public static Compiler getInstance() {
        return instance;
    }

    public static Compiler getInstance(String jasminPath) {
        if (instance ==null)
            reset(jasminPath);
        return instance;
    }

    public static Compiler reset(String jasminPath) {
        instance = new Compiler(jasminPath);
        return instance;
    }

    private Compiler(String jasminPath) {
        files = new ArrayList<>();
        currentFrame = "";
        stackSize = 256;
        SL = 4;
        this.jasminPath = jasminPath;
        nextLabel = 0;
    }

    public String generateLabel() {
        return "LABEL" + nextLabel++;
    }

    public int generateID() {
        return nextFrameID++;
    }

    public int getStackSize() {
        return stackSize;
    }

    private List<String> generateFiles(String path) throws IOException {
        List<String> filesPath = new ArrayList<>();
        for (IClassFile f : files){
            String content = f.getCode().Dump();
            Path finalPath = Paths.get(path, f.getFileName());
            File file = new File(finalPath.toString());
            file.getParentFile().mkdirs();
            FileOutputStream stream = new FileOutputStream(file, false);
            stream.write(content.getBytes(StandardCharsets.UTF_8));
            stream.close();

            filesPath.add(finalPath.toString());
        }

        return filesPath;
    }

    private MainClassFile findMain() {
        for (IClassFile classFile : files) {
            if (classFile instanceof MainClassFile)
                return (MainClassFile) classFile;
        }

        return null;
    }

    public String assembleFiles(String path) throws IOException, InterruptedException, ASTRuntimeException {
        File workingDirectory = new File(path);
        List<String> filesPath = generateFiles(path);
        Runtime rt = Runtime.getRuntime();

        Process process = rt.exec("java -jar " + jasminPath + " " + String.join(" ", filesPath), null, workingDirectory);
        process.waitFor();

        return parseResult(process);
    }

    public String execute(String workingDirectory) throws ASTCompileException, IOException, InterruptedException, ASTRuntimeException {
        MainClassFile main = findMain();
        if (main == null)
            throw new ASTCompilerError("Main class not found");

        Process process = Runtime.getRuntime().exec("java " + main.getClassName(), null, new File(workingDirectory));
        process.waitFor();

        return parseResult(process);
    }

    private String parseResult(Process process) throws IOException, ASTRuntimeException {
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder strBuilder = new StringBuilder();
        String tmp;

        while ((tmp = stdError.readLine()) != null) {
            if (strBuilder.length() != 0)
                strBuilder.append(System.getProperty("line.separator"));
            strBuilder.append("\t").append(tmp);
        }

        if (strBuilder.length() != 0)
            throw new ASTRuntimeException(strBuilder.toString());

        while ((tmp = stdInput.readLine()) != null) {
            if (strBuilder.length() != 0)
                strBuilder.append(System.getProperty("line.separator"));
            strBuilder.append("\t").append(tmp);
        }

        return strBuilder.toString();
    }
    public void addClassFile(IClassFile file) {
        for (IClassFile f : files) {
            if (f.getFileName().equals(file.getFileName()))
                return;
        }
        this.files.add(file);
    }

    public ClosureClass newClosure(FunctionType functionType, ClosureInterfaceClass interfaceClass, FrameClass frame, Code closureBody) {
        ClosureClass closure = new ClosureClass(generateID(), frame, interfaceClass, functionType, closureBody);
        addClassFile(closure);
        return closure;
    }

    public FrameClass newFrame(String name, List<FrameField> frameFields, Frame previousFrame) {
        FrameClass frame = new FrameClass(name + generateID(), frameFields, previousFrame);
        addClassFile(frame);
        return frame;
    }

    public void addFrame(FrameClass file) {
        addClassFile(file);
        currentFrame = file.getClassName();
    }

    public void addMainClass(String fileName, Code code) {
        addClassFile(new MainClassFile(fileName, code, stackSize, SL));
    }

    public ReferenceClass newReference(RefType value_type) {
        ReferenceClass ref = new ReferenceClass(value_type);
        addClassFile(ref);
        return ref;
    }

    public ClosureInterfaceClass newClosureInterface(FunctionType functionType) {
        boolean exists = false;
        ClosureInterfaceClass closureInterfaceClass = new ClosureInterfaceClass(functionType);
        for (IClassFile f : files) {
            if (f instanceof ClosureInterfaceClass) {
                ClosureInterfaceClass closureInterface = (ClosureInterfaceClass) f;
                if (closureInterfaceClass.equals(closureInterface)) {
                    closureInterfaceClass = closureInterface;
                    exists = true;
                    break;
                }
            }
        }
        if (!exists)
            addClassFile(closureInterfaceClass);

        return closureInterfaceClass;
    }

    public int getSL() {
        return SL;
    }
}

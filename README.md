# ICL 18/19
## Developers
- Thales Parreira 41835
- Eduardo Silva 41798

## Usage
To build execute the following command:
```
mvn verify
```

There is a interpreter and a Compiler.

To execute the interpreter run *TestInterpreter*, commands are interpreted and typechecked directly from the console.

To compile files execute CompilerTest class, this class will compile and execute the files passed as argument. 
The compiler expects:
 - Location of the jasmin.jar file
 - List of files.
 
If files are not specified it will search for files that are located in the folder *src/main/java/resources/compiler_test*.

## Development
Still in development.

Examples in src/main/resources.

## Already supported:
- Variables
- Functions
- Recursive functions
- Arithmetic (+, -, *, /)
- Identifiers
- Numeric and boolean values
- Equality (== and <>)
- Comparison (>, >=, <, <=)
- If else
- While do
- Reference (new)
- Dereference (!)
- Logical operators (||, && and ~)
- Typechecking
- Compiling to jasmin syntax
- Strings
- Records

## Example:
- Fibonacci:
```
let
    fibonacci:(int)int = function x:int =>
        let
            result:ref int = new 1
        in
            if x <= 1 then
                x
            else
                fibonacci(x - 1) + fibonacci(x - 2)
            end
        end
    end
in
    fibonacci(10)
end;;
```

- Factorial:
```
let
    factorial:(int)int = function x:int =>
        let
            result:ref int = new 1
        in
            if x > 1 then
                x * factorial(x - 1)
            else
                x
            end
        end
    end
in
    factorial(12)
end;;
```
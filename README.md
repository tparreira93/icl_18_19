# ICL 18/19
## Developers
- Thales Parreira 41835
- Eduardo Silva 41798

## Usage
To build execute the following command:
```
mvn verify
```

There is a interpreter and a file parser.

To execute the interpreter run *TestInterpreter*, commands are interpreted directly from console.

The file parser searches for files that are in the folder *src/main/java/resources*, just add new files and they will be interpreted when the *FileParser* is executed .

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
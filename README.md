# ICL 18/19
## Developers
- Thales Parreira 41835
- Eduardo Silva 41798

# Development
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

## Example:
- Fibonacci:
```
		let
			fibonacci = function x ->
				let
					result = new 1
				in
					if x <= 1 then
						result := x
					else
						result := fibonacci(x - 1) + fibonacci(x - 2)
					end;
					!result
				end
			end
		in
			fibonacci(10)
		end;;
```

- Factorial:
```
		let
			factorial = function x ->
				let
					result = new 1
				in
					if x > 1 then
						result := x * factorial(x - 1)
					else
						x
					end;
					!result
				end
			end
		in
			factorial(12)
		end;;
```
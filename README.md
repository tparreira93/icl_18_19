# ICL 18/19

Still in development.

Examples in src/main/resources.

# Already supported:
- Variables
- Functions
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

## Variables
Example:

- let x = 1, y = 2 in x + y end;;
	- Result: 3
 
## Functions
- let f = function => 1 end in f() end;;
	 - Result: 1
- let f = function x, y => x * y end in f(5, 5) end;;
	 - Result: 25

## References
- let x = new 5 in x end;;
	 - Result: Reference of 5

## Dereference
- let x = new 6 in !x end;;
	 - Result: 6
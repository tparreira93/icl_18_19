let
    factorial = function x =>
        let
            fact = new x,
            result = new 1
        in
            while !fact > 0
            do
                result := !result * !fact;
                fact := !fact - 1
            end;
            !result
        end
    end
in
    factorial(12)
end;;
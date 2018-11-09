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
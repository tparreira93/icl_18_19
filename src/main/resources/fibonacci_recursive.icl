let
    fibonacci = function x =>
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
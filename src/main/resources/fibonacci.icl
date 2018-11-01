let
    fibonacci = function x =>
        let
            i = new 2,
            first = new 0,
            second = new 1,
            result = new 1
        in
            if x == 0 then
                result := 0
            else
                while !i <= x
                do
                    result := !first + !second;
                    first := !second;
                    second := !result;

                    i := !i + 1
                end
            end;

            !result
        end
    end
in
    fibonacci(10)
end;;
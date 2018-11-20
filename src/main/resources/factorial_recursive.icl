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
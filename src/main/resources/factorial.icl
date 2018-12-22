let
    factorial:(int)int = fun x:int ->
        let
            fact:ref int = new x,
            result: ref int = new 1
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
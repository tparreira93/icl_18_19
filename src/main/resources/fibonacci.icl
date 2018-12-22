let
    fibonacci:(int)int = fun x:int ->
        let
            i:ref int = new 2,
            first:ref int = new 0,
            second:ref int = new 1,
            result:ref int = new 1
        in
            if x == 0 then
                result := 0;
                !result
            else
                while !i <= x
                do
                    result := !first + !second;
                    first := !second;
                    second := !result;

                    i := !i + 1
                end;
                !result
            end
        end
    end
in
    fibonacci(10)
end;;


let f:int->int = fun x:int -> x end
in
f(1)
end
;;
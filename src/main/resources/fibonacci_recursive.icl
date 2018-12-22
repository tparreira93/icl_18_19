let
    fibonacci:(int)int = fun x:int ->
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
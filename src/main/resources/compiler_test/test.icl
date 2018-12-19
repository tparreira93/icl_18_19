let
    x:ref int = new 1
in
    while !x > 0
    do
        x := !x - 1
    end;
    println(!x)
end;
let
    fact:ref int = new 12,
    result: ref int = new 1
in
    println("Calulo do factorial de 12...");
    while !fact > 0
    do
        result := !result * !fact;
        fact := !fact - 1
    end;
    println(!result);
    println("Fim do factorial de 12...")
end;
let
    x:ref int = new 12,
    y:ref int = new 7
in
    let
        k:int = !x,
        i:int = !y
    in
        println(!x + !y - k - i + 1)
    end
end;
println(let x:int = 1 in x end);
println(let k:int = 6 in (let x:int = 1, y:int = 2 in let z:int = 1 in x + y + z end end) + k end);
println(!!!(new (new (new 1))));
println(!!(new (new 1)));
println(!(new 1));
println(!(new true));
println(true);
println(true && true);
println(false || true);
println(1 > 0);
println(1 >= 0);
println(if true then 1 else 0 end);
println(if false then 0 else 1 end);
println(1 == 1);
println(true == true);
println(false <> true);
println(0 <> 1);
println(false == true);
println(0 == 1);
println(1 < 0);
println(1 <= 0);
println(false);
println(1+(-1));
println(1-1);
println(1*0);
println(0/1);
println(true && false);
println(false || false);
println("Teste de strings");
println(!(new "Teste referencia para string"));;

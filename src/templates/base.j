.source test.j
.class public Test
.super java/lang/Object

; default constructor
.method public <init>()V
   aload_0 ; push this
   invokespecial java/lang/Object/<init>()V ; call super
   return
.end method

.method public static main([Ljava/lang/String;)V

   ; allocate stack big enough to hold 2 items
   .limit stack STACK_SIZE
   ; .limit locals 0

   ; push java.lang.System.out (type PrintStream)
   getstatic java/lang/System/out Ljava/io/PrintStream;


REPLACE_THIS_WITH_CODE

   ; invoke println
   invokevirtual java/io/PrintStream/println(I)V

   ; terminate main
   return

.end method
public class MyClass {

    //2 публичных метода
    public void publicMethod1() {
        System.out.println("publicMethod1 called");
    }

    public void publicMethod2() {
        System.out.println("publicMethod2 called");
    }

     //2 защищенных метода с аннотацией
    @Repeat(times = 2)
    protected void protectedMethod1() {
        System.out.println("protectedMethod1 called");
    }

    @Repeat(times = 3)
    protected void protectedMethod2() {
        System.out.println("protectedMethod2 called");
    }

    //2 приватных метода с параметрами и аннотацией
    @Repeat(times = 1)
    private void privateMethod1(String arg) {
        System.out.println("privateMethod1 called with arg: " + arg);
    }

    @Repeat(times = 2)
    private void privateMethod2(int x, int y) {
        System.out.println("privateMethod2 called with args: " + x + ", " + y);
    }
}
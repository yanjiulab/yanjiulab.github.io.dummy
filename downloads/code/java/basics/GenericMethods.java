public class GenericMethods {
    public <T> void foo(T x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.foo("string");   //java.lang.String
        gm.foo(47); // java.lang.Integer
        gm.foo(1.0);    // java.lang.Double
        gm.foo('c');    // java.lang.Character
        gm.foo(gm); // GenericMethods
    }
}
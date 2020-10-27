public class Holder<T> {
    private T a;
    public void set(T a) { this.a = a; }
    public T get() { return a; }

    public static void main(String[] args) {
        Holder<String> h = new Holder<String>();
        h.set("Generic holder class");
        System.out.println(h.get());
    }
}
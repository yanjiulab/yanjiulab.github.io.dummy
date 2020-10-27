/**
 * initialization/Leaf.java
 * Simple use of the "this" keyword.
 */
public class Leaf {
    int i = 0;

    Leaf increment() {
        i++;
        return this;
    }

    void print() {
        System.out.println("i = " + i);
    }

    public static void main(String[] args) {
        Leaf leaf = new Leaf();
        leaf.increment().print(); // now i = 1

        Leaf refLeaf;
        refLeaf = leaf.increment();
        refLeaf.print();      // now i = 2

        leaf.increment().print();   // now i = 3

        refLeaf.increment().increment().print();  // now i = 5, because refLeaf is the reference of leaf
        leaf.print();
    }
}

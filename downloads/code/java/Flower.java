/**
 * initialization/Flower.java
 * Calling constructors with "this" in another constructor.
 */
public class Flower {
    int petalCount = 0;
    String s = "initial value";

    Flower(int petals) {
        petalCount = petals;
        System.out.println("Constructor with int arg only. petalCount = " + petalCount);
    }

    Flower(String ss) {
        s = ss;
        System.out.println("Constructor with String arg only. s = " + s);
    }

    Flower(String s, int petals) {
        this(petals);   // Call to 'this()' must be first statement in constructor body
//!       this(s);     // Can't call two;
        this.s = s;     // Another use of this
        System.out.println("String and int args");
    }

    Flower() {
        this("rose", 7);
        System.out.println("Default constructor (no args)");
    }

    void printPetalCount() {
//!        this(11);    // Not inside non-constructor!
        System.out.println("petalCount = " + petalCount + ", s = " + s);
    }

    public static void main(String[] args) {
        Flower flower = new Flower();
        flower.printPetalCount();
    }

}

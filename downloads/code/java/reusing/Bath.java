package reusing;

/**
 * Constructor initialization with composition.
 */

class Soup {
    private String s;
    Soup() {
        System.out.println("Soup()");
        s = "Constructed";
    }

    public String toString() {
        return s;
    }
}

public class Bath {
    // Initializing at point of definition (s1 and s2)
    private String
        s1 = "Happy",
        s2 = "Happy",
        s3, s4;
    private Soup castile;
    private int i;
    private float toy;
    public Bath() {
        System.out.println("Inside Bath()");
        // Initializing in the constructor (s3, toy and castile)
        s3 = "Joy";
        toy = 3.14f;
        castile = new Soup();
    }
    // Instance initialization (i)
    {  i = 47; }

    public String toString() {
        // Delayed initialization (s4)
        if (s4 == null) {
            s4 = "Joy";
        }
        return
            "s1 = " + s1 + "\n" +
            "s2 = " + s2 + "\n" +
            "s3 = " + s3 + "\n" +
            "s4 = " + s4 + "\n" +
            "i = " + i + "\n" +
            "toy = " + toy + "\n" +
            "castile = " + castile;
    }

    public static void main(String[] args) {
        Bath bath = new Bath();
        System.out.println(bath);
    }
}

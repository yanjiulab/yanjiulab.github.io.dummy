package reusing;

/**
 * Constructor calls during inheritance.
 */

class Art {
    Art(int i) {
        System.out.println("Art Constructor " + i);
    }
}

class Drawing extends Art {
    Drawing(int i) {
        super(i);
        System.out.println("Drawing Constructor " + i);
    }
}

public class Cartoon extends Drawing {
    public Cartoon(int i) {
        super(i);
        System.out.println("Cartoon constructor " + i);
    }
    public static void main(String[] args) {
        Cartoon x = new Cartoon(1);
    }
}

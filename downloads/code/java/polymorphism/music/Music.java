package polymorphism.music;

/**
 * Inheritance & upcasting.
 * tune: 调子
 * flute: 长笛
 */
public class Music {
    public static void tune(Instrument i) {
    // ...
        i.play(Note.MIDDLE_C);
    }

    public static void main(String[] args) {
        Wind flute = new Wind();
        tune(flute);    // Upcasting
    }
} /* Output:
Wind.play() MIDDLE_C
*/

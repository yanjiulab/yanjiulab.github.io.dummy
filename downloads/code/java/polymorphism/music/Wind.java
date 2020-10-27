package polymorphism.music;

/**
 * Wind (管乐器) objects are instruments (乐器) because they have the same interface:
 */
public class Wind extends Instrument {
    public void play(Note n) {
        System.out.println("Wind.play() " + n);
    }
}

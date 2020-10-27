public class EqualExample {
    int x;
    double y;
    String z;

    EqualExample(int x, double y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EqualExample)) return false;

        EqualExample o = (EqualExample) obj;

        // Using == for primitives except float and double
        if (x != o.x) return false;

        // Using Float.compare or Double.compare for float or double
        if (Double.compare(y, o.y) != 0) return false;

        // Using equals() for reference types
        // Style 1: return Objects.equals(z, o.z); // need import java.util.Objects;
        // Style 2: return z == null ? o.z == null : z.equals(o.z);
        // Style 3:
        return z == o.z || (z != null && z.equals(o.z));
    }

    public static void main(String[] args) {
        EqualExample a = new EqualExample(0, Double.NaN, null);
        EqualExample b = new EqualExample(0, Double.NaN, null);
        System.out.println(a.equals(b));    // true
    }
}
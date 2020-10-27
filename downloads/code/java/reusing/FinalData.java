package reusing;
import java.util.*;

/**
 * The effect of final on fields.
 */

class Value  {
    int i;
    public Value(int i) { this.i = i; }
    public String toString() { return "" + this.i; }
}

public class FinalData {
    private static Random rand = new Random(47);
    private String id;
    public FinalData(String id) { this.id = id; }

    // Can be compile-time constants:
    private final int valueOne = 9;
    private static final int VALUE_TWO = 99;
    public static final int VALUE_THREE = 39;   // Typical public constant:

    // Cannot be compile-time constants:
    private final int i4 = rand.nextInt(20);
    static final int INT_5 = rand.nextInt(20);
    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private static final Value VAL_3 = new Value(33);
    // Arrays:
    private final int[] a = { 1, 2, 3, 4, 5, 6 };

    public String toString() {
        return id + ": " + "valueOne = " + valueOne + ", VALUE_TWO = " + VALUE_TWO + ", VALUE_THREE = " + VALUE_THREE + ",\n\t " +
                "i4 = " + i4 + ", INT_5 = " + INT_5  + ", v1= "+ v1 + ", v2 = " + v2 + ", VAL_3 = " + VAL_3;
    }

    public static void main(String[] args) {
        FinalData fd1 = new FinalData("fd1");
        //! fd1.valueOne++; // Error: can’t change value
        fd1.v2.i++; // Object isn’t constant!
        fd1.v1 = new Value(9); // OK -- not final
        for(int i = 0; i < fd1.a.length; i++)
            fd1.a[i]++; // Object isn’t constant!
        //! fd1.v2 = new Value(0); // Error: Can’t
        //! fd1.VAL_3 = new Value(1); // change reference
        //! fd1.a = new int[3];
        System.out.println(fd1);

        System.out.println("Creating new FinalData");
        FinalData fd2 = new FinalData("fd2");
        System.out.println(fd2);
    }
}/* Output:
fd1: valueOne = 9, VALUE_TWO = 99, VALUE_THREE = 39,
	 i4 = 15, INT_5 = 18, v1= 9, v2 = 23, VAL_3 = 33
Creating new FinalData
fd2: valueOne = 9, VALUE_TWO = 99, VALUE_THREE = 39,
	 i4 = 13, INT_5 = 18, v1= 11, v2 = 22, VAL_3 = 33
*/

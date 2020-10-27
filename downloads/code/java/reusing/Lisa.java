package reusing;

public class Lisa extends Homer {
    /* If we added the keyword @Override, the compiler will produce an error message
    * because we accidentally overload instead of overriding.
    * */
    // @Override
    Milhouse doh(Milhouse m) {
        System.out.println("doh(Milhouse) of Lisa");
        return m;
    }

    @Override
    char doh(char c) {
        System.out.println("doh(char) of Lisa");
        return c;
    }

    public static void main(String[] args) {
        Lisa l = new Lisa();
        l.doh(1);
        l.doh('x');
        l.doh(1.0f);
        l.doh(new Milhouse());
    }
}/* Output:
doh(float)
doh(char)
doh(float)
doh(Milhouse)*/


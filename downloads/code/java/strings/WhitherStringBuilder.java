package strings;

public class WhitherStringBuilder {
    public String implicit(String[] fields) {
        String result = "";
        for(int i = 0; i < fields.length; i++)
            result += fields[i];
        return result;
    }

    public String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < fields.length; i++)
            result.append(fields[i]);
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("Test StringBuilder:");
        StringBuilder stringBuilder = new StringBuilder("abc");
        System.out.println(stringBuilder);
        stringBuilder.append("def");
        System.out.println(stringBuilder);
        stringBuilder.insert(3,"APPLE");
        System.out.println(stringBuilder);
        stringBuilder.replace(1, 3, "ORANGE");
        System.out.println(stringBuilder);
        String string = stringBuilder.substring(3);
        System.out.println(string);
        stringBuilder.delete(1,3);
        System.out.println(stringBuilder);
        stringBuilder.reverse();
        System.out.println(stringBuilder);
    }
}/* Output:
Test StringBuilder:
abc
abcdef
abcAPPLEdef
aORANGEAPPLEdef
ANGEAPPLEdef
aANGEAPPLEdef
fedELPPAEGNAa
*/

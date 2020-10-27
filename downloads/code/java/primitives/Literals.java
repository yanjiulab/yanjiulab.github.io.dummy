package primitives;

/**
 * Test literals for various primitive types
 */
public class Literals {
    public static void main(String[] args) {
        String name = "Tan Ah Teck"; // String is double-quoted
        char gender = 'm';           // char is single-quoted
        boolean isMarried = true;    // boolean of either true or false
        byte numChildren = 8;        // Range of byte is [-127, 128]
        short yearOfBirth = 1949;    // Range of short is [-32767, 32768]. Beyond byte
        int salary = 88000;          // Beyond the ranges of byte and short
        long netAsset = 8234567890L; // Need suffix 'L' for long. Beyond int
        double weight = 88.88;       // With fractional part
        float gpa = 3.88f;           // Need suffix 'f' for float

        // println() can be used to print value of any type
        System.out.println("Name is " + name);
        System.out.println("Gender is " + gender);
        System.out.println("Is married is " + isMarried);
        System.out.println("Number of children is " + numChildren);
        System.out.println("Year of birth is " + yearOfBirth);
        System.out.println("Salary is " + salary);
        System.out.println("Net Asset is " + netAsset);
        System.out.println("Weight is " + weight);
        System.out.println("GPA is " + gpa);
    }
}

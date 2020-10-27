package concurrency;

public class BasicThreadDemo extends Thread {
    private int count = 5;

    public BasicThreadDemo(String a) {
        super();
        this.setName(a);
    }

    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.print("#" + currentThread().getName() + "(" + count + ") ");
        }
    }

    public static void main(String[] args) {
        System.out.println("Main thread starts");
        BasicThreadDemo a = new BasicThreadDemo("A");
        BasicThreadDemo b = new BasicThreadDemo("B");
        BasicThreadDemo c = new BasicThreadDemo("C");
        a.start();
        b.start();
        c.start();
        System.out.println("Main thread ends");
    }
}/* Output(*):
Main thread starts
Main thread ends
#A(4) #C(4) #B(4) #C(3) #A(3) #C(2) #B(3) #C(1) #A(2) #C(0) #B(2) #A(1) #B(1) #A(0) #B(0)
*/

package concurrency;

public class RunnableDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new TaskWithoutResult()).start();
        }
        new TaskWithoutResult().run();
    }
}/* Output(*)
#Thread-0(2) #Thread-2(2) #Thread-1(2) #main(2) #Thread-1(1) #Thread-2(1)
#Thread-0(1) #Thread-2(0) #Thread-1(0) #main(1) #Thread-0(0) #main(0)
*/


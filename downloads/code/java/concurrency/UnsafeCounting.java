package concurrency;
import java.util.concurrent.*;

class UnsafeCounter implements Counter {
    private int count = 0;
    public void increase() { count++; }
    public int getCount() { return count; }
}

public class UnsafeCounting {
    public static void main(String[] args) {
        Counter unsafeCounter = new UnsafeCounter();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            exec.execute(new CountingTask(unsafeCounter));
        }
        exec.shutdown();
        while (true) {
            if (exec.isTerminated()) {
                System.out.println("Unsafe Counter: " + unsafeCounter.getCount());
                break;
            }
        }
    }
}/*Output(*):
Safe Counter: 17986
*/

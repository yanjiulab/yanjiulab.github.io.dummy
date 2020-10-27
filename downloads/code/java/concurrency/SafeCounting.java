package concurrency;
import java.util.concurrent.*;

class SafeCounter implements Counter {
    private int count = 0;
    public synchronized void increase() { count++; }
    public int getCount() { return count; }
}

public class SafeCounting {
    public static void main(String[] args) {
        Counter safeCounter = new SafeCounter();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            exec.execute(new CountingTask(safeCounter));
        }
        exec.shutdown();
        while (true) {
            if (exec.isTerminated()) {
                System.out.println("Safe Counter: " + safeCounter.getCount());
                break;
            }
        }
    }
}/*Output:
Safe Counter: 30000
*/

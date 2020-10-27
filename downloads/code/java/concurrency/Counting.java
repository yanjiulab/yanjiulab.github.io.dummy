package concurrency;
import java.util.concurrent.*;

interface Counter {
    int count = 0;
    void increase();
    int getCount();
}

class UnsafeCounter implements Counter {
    int count = 0;
    public void increase() { count++; }
    public int getCount() { return count; }
}

class SafeCounter implements Counter {
    private int count = 0;
    public synchronized void increase() { count++; }
    public int getCount() { return count; }
}

class CountingTask implements Runnable {
    Counter counter;
    CountingTask(Counter counter) { this.counter = counter; }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increase();
        }
    }
}

public class Counting {
    public static void main(String[] args) {
        Counter unsafeCounter = new UnsafeCounter();
        Counter safeCounter = new SafeCounter();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            exec.execute(new CountingTask(unsafeCounter));
            exec.execute(new CountingTask(safeCounter));
        }
        exec.shutdown();
        while (true) {
            if (exec.isTerminated()) {
                System.out.println("Unsafe Counter: " + unsafeCounter.getCount());
                System.out.println("Safe Counter: " + safeCounter.getCount());
                break;
            }
        }
    }
}/* Output:
Unsafe Counter: 27975
Safe Counter: 30000
*/

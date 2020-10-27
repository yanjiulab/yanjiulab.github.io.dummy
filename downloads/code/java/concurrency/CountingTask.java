package concurrency;

public class CountingTask implements Runnable {
    private Counter counter;
    CountingTask(Counter counter) { this.counter = counter; }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increase();
        }
    }
}

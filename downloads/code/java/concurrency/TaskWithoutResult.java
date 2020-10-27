package concurrency;

public class TaskWithoutResult implements Runnable {
    private int count = 3;

    TaskWithoutResult() { }

    TaskWithoutResult(int count) { this.count = count; }

    @Override
    public void run() {
        while (count > 0) {
            count--;
            System.out.print("#" + Thread.currentThread().getName() + "(" + count + ") ");
        }
    }
}

package concurrency;

import java.util.Random;
import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<Integer> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public Integer call() {
        Random random = new Random(id);
        return random.nextInt(100);
    }
}

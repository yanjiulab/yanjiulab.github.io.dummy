package concurrency;
import java.util.concurrent.*;

public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 1; i < 4; i++) {
            executorService.execute(new TaskWithoutResult(i));
        }
        executorService.shutdown();
    }
}/*Output:
#pool-1-thread-1(0)
#pool-1-thread-1(1) #pool-1-thread-1(0)
#pool-1-thread-1(2) #pool-1-thread-1(1) #pool-1-thread-1(0)
*/

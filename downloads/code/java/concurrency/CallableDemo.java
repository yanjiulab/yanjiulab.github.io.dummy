package concurrency;

import java.util.*;
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<Integer> fs : results) {
            try {
                // get() blocks until completion:
                System.out.print(fs.get() + " ");
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
        }
    }
}/*Output:
60 85 8 34 62 87 11 36 64 89
*/

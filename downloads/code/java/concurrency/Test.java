package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        thread.suspend();
        Thread.sleep(10);
        ExecutorService service = Executors.newCachedThreadPool();
//        service.execute();
//        service.submit();
    }
}

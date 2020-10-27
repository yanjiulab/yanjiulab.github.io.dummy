package concurrency;

public interface Counter {
    int count = 0;
    void increase();
    int getCount();
}

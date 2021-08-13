package task1;

public class ThreadHello extends java.lang.Thread {
    int threadNumber;
    public ThreadHello(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public void run() {
        System.out.println("Hello from thread " + threadNumber);
    }
}
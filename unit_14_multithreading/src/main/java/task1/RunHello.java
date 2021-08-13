package task1;

public class RunHello {
    static ThreadHello mFirstThreadHello;

    public void customStartHello() {
        String TASK = "------------------------------------------------ Task 1 ------------------------------------------------";
        System.out.println(TASK);
        for (int i = 49; i >= 0; i--) {
            mFirstThreadHello = new ThreadHello(i);
            mFirstThreadHello.start();
            try {
                ThreadHello.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
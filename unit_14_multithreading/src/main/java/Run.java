import task1.RunHello;
import task2.RunCallable;

public class Run {
    public static void main(String[] args) {
        RunHello runHello = new RunHello();
        runHello.customStartHello();

        RunCallable mainSecond = new RunCallable();
        mainSecond.customStartCallable();
    }
}
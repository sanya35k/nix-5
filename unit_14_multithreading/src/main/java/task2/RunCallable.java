package task2;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RunCallable {
    public void customStartCallable() {
        String TASK = "------------------------------------------------ Task 2 ------------------------------------------------";
        String SEPARATOR = "--------------------------------------------------------------------------------------------------------";
        String FINISH = "Thank you for your attention!";

        System.out.println(TASK);
        List<Integer> primeNumbers = Stream.of(0, 3, 0, 5, 1, 9, 9, 9, 9, 22, 20, 19).collect(Collectors.toList());
        FutureTask<Integer> left = new FutureTask<>(new ThreadCallable(primeNumbers.subList(0, primeNumbers.size() / 2)));
        FutureTask<Integer> right = new FutureTask<>(new ThreadCallable(primeNumbers.subList(primeNumbers.size() / 2, primeNumbers.size())));

        Thread firstThread = new Thread(left);
        Thread secondThread = new Thread(right);

        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int result = 0;
        try {
            Integer leftResult = left.get();
            Integer rightResult = right.get();
            System.out.println("Left = " + leftResult);
            System.out.println("Right = " + rightResult);
            result = rightResult + leftResult;
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Total = " + result + "\n" + SEPARATOR + "\n" + FINISH);
    }
}
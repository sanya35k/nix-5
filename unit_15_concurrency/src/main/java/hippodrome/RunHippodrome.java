package hippodrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunHippodrome {
    public static void main(String[] args) {
        RunHippodrome runHippodrome = new RunHippodrome();
        runHippodrome.start();
    }

    public static void start() {
        String TASK = "---------------------------------------------- HIPPODROME ----------------------------------------------";
        System.out.println(TASK);
        System.out.print("This is a hippodrome emulator. The race involves 10 horses numbered from 1 to 10.\nEnter the number of the horse you want to bet on:\n>");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer input = null;
        try {
            input = Integer.parseInt(reader.readLine());
        } catch (IOException e) { e.printStackTrace(); }

        System.out.println("The race has begun!");

        CopyOnWriteArrayList<Integer> winners = new CopyOnWriteArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
            service.submit(new Horse(winners, i));
        }
        service.shutdown();
        boolean finished = false;
        while (!finished) {
            try {
                finished = service.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Error! Please, keep calm!" + e.getMessage());
            }
        }

        System.out.println("The race is over!\n");

        if (Objects.equals(winners.get(0), input)) {
            System.out.println("Congratulations! Your horse came to the finish line - " + (winners.indexOf(input) + 1) + "!");
        } else {
            System.out.println("I'm very sorry! Your horse came to the finish line - " + (winners.indexOf(input) + 1) + "!");
        }
    }
}
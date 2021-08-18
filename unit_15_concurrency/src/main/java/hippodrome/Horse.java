package hippodrome;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static hippodrome.ConstantsHippodrome.*;

public class Horse extends Thread {
    private Long distance = 0L;
    private final CopyOnWriteArrayList<Integer> winners;
    private final Integer horseNumber;
    private static final int differenceDistance = MAX_DISTANCE - MIN_DISTANCE;
    private static final int differenceTimeSleep = MAX_TIME_SLEEP - MIN_TIME_SLEEP;

    public Horse(CopyOnWriteArrayList<Integer> winner, Integer horseNumber) {
        this.winners = winner;
        this.horseNumber = horseNumber;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (this.distance <= FINISH_DISTANCE) {
            this.distance += random.nextInt(differenceDistance + SIGN_INCLUSIVE) + MIN_DISTANCE;
            try {
                Thread.sleep(random.nextInt(differenceTimeSleep + SIGN_INCLUSIVE) + MIN_TIME_SLEEP);
            } catch (InterruptedException e) {
                System.out.println("Error! Please, keep calm!" + e.getMessage());
            }
        }
        this.winners.add(this.horseNumber);
        System.out.println("Horse number " + horseNumber + " crossed the finish line!");
    }
}
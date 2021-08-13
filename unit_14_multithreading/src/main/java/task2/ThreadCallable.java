package task2;

import java.util.List;
import java.util.concurrent.Callable;

public record ThreadCallable(List<Integer> numbers) implements Callable<Integer> {

    @Override
    public Integer call() {
        int mainNumbers = 0;
        for (int i : numbers) {
            if (isMain(i)) {
                mainNumbers++;
            }
        }
        return mainNumbers;
    }

    private boolean isMain(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
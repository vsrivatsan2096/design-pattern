package common.helper.calculator;

import java.util.Arrays;
import java.util.Random;

// Server
public class ServerCalculator implements Calculator {
    private static final ServerCalculator serverCalculator = new ServerCalculator();

    private final Random random = new Random();

    private ServerCalculator() { /* Singleton */ }

    public static ServerCalculator getInstance() {
        return serverCalculator;
    }

    @Override
    public long sum(long... a) {
        pause();

        return Arrays.stream(a).sum();
    }

    @Override
    public long product(long... a) {
        pause();

        return Arrays.stream(a).reduce(1, (b, c) -> b * c);
    }

    private void pause() {
        try {
            if (random.nextInt(0, 2) == 0) {
                throw new RuntimeException("Remote server busy, try again in sometime");
            }

            Thread.sleep(random.nextLong(1000, 3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

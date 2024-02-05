package design.pattern.structural;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

/**
 * {@link Ambassador} is used to offload the common functionality like logging, retry, etc., away from the
 *          shared legacy application into the client code itself.
 * */
public interface Ambassador {}

interface Calculator {
    public long sum(long ...a);

    public long product(long ...a);
}

// Server
class ServerCalculator implements Calculator {
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
            if (random.nextInt(0,2) == 0) {
                throw new RuntimeException("Remote server busy, try again in sometime");
            }

            Thread.sleep(random.nextLong(1000, 3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

// Client
class ClientAmbassador implements Calculator, Ambassador {

    private final static int RETRIES = 3;

    private final static int DELAY_MS = 1000;

    private final static PrintStream LOGGER = System.out;

    @Override
    public long sum(long... a) {
        return ambassadorSum(a);
    }

    @Override
    public long product(long... a) {
        return ambassadorProduct(a);
    }

    private long ambassadorSum(long... a) {
        long result = Long.MIN_VALUE;
        long startTime;

        for (int index = 0; index < RETRIES; index ++) {
            try {
                LOGGER.println("Calling the remote function for the " + (index + 1) + " th time");

                startTime = System.currentTimeMillis();

                result = ServerCalculator.getInstance().sum(a);

                LOGGER.println("Remote call took " + (System.currentTimeMillis() - startTime) + " ms");

                break;
            } catch (Exception e) {
                LOGGER.println("Remote call failed of error - " + e.getMessage());

                pause();
            }
        }

        return result;
    }

    private long ambassadorProduct(long... a) {
        long result = Long.MIN_VALUE;
        long startTime;

        for (int index = 0; index < RETRIES; index ++) {
            try {
                LOGGER.println("Calling the remote function for the " + (index + 1) + " th time");

                startTime = System.currentTimeMillis();

                result = ServerCalculator.getInstance().product(a);

                LOGGER.println("Remote call took " + (System.currentTimeMillis() - startTime) + " ms");

                break;
            } catch (Exception e) {
                LOGGER.println("Remote call failed of error - " + e.getMessage());

                pause();
            }
        }

        return result;
    }

    private void pause() {
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ClientAmbassador clientAmbassador = new ClientAmbassador();

        System.out.println("Sum is = " + clientAmbassador.sum(1, 2, 3, 4, 5));

        System.out.println("Product is = " + clientAmbassador.product(1, 2, 3, 4, 5));
    }
}
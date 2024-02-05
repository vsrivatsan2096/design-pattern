package design.pattern.structural;

import common.helper.calculator.Calculator;
import common.helper.calculator.ServerCalculator;

import java.io.PrintStream;

/**
 * {@link Ambassador} is used to offload the common functionality like logging, retry, etc., away from the
 *          shared legacy application into the client code itself.
 * */
public interface Ambassador {}

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
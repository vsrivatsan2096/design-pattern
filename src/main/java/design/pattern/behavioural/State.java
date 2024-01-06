package design.pattern.behavioural;

import common.helper.order.Order;

/**
 * {@link State} is used when the behaviour of the object is completely depend on the internal state of the object
 * */
public interface State {}

interface OrderState extends State {
    Double handleCancellation();
}

class NewOrder implements OrderState {

    @Override
    public Double handleCancellation() {
        System.out.println("Order is new so no processing done");
        return 0.0;
    }
}


class PaidOrder implements OrderState {

    @Override
    public Double handleCancellation() {
        System.out.println("Order is paid so informing payment gateway to rollback");
        return 10.0;
    }
}

class InTransitOrder implements OrderState {

    @Override
    public Double handleCancellation() {
        System.out.println("Order is in transit so informing courier service");
        System.out.println("Also need to inform payment gateway to rollback");
        return 20.0;
    }
}

class DeliveredOrder implements OrderState {
    @Override
    public Double handleCancellation() {
        System.out.println("Order is delivered so informing courier service to pick up");
        System.out.println("Also need to inform payment gateway to rollback");
        return 50.0;
    }
}

class CancelledOrder implements OrderState {
    public Double handleCancellation() {
        throw new IllegalStateException("Order is already cancelled so can't cancel it again");
    }
}

class OrderContext extends Order {
    private OrderState currentState;

    public OrderContext(String name, Integer quantity, Double price) {
        super(name, quantity, price);

        currentState = new NewOrder();
    }

    public void paymentSuccessful() {
        this.currentState = new PaidOrder();
    }

    public void courierPickedUp() {
        this.currentState = new InTransitOrder();
    }

    public void delivered() {
        this.currentState = new DeliveredOrder();
    }

    public Double cancel() {
        Double cancellationCharges = this.currentState.handleCancellation();

        this.currentState = new CancelledOrder();

        return cancellationCharges;
    }

    public static void main(String[] args) {
        OrderContext orderContext = new OrderContext("Tea", 5, 100.00);

        orderContext.paymentSuccessful();
        System.out.println("Cancellation charges :" + orderContext.cancel());

        OrderContext orderContext2 = new OrderContext("Coffee", 5, 120.00);

        orderContext2.paymentSuccessful();
        orderContext2.courierPickedUp();
        System.out.println("Cancellation charges :" + orderContext2.cancel());

        OrderContext orderContext3 = new OrderContext("Apple", 5, 80.00);

        orderContext3.paymentSuccessful();
        orderContext3.courierPickedUp();
        orderContext3.delivered();
        System.out.println("Cancellation charges :" + orderContext3.cancel());
    }
}
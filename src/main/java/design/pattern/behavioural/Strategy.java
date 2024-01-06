package design.pattern.behavioural;

import common.helper.order.Order;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * {@link Strategy} is used to encapsulate the algorithms in different classes which can be configured in the context class
 * */
public interface Strategy {}

interface OrderPrinter extends Strategy {
    void printOrder(List<Order> orders);
}

class SummaryOrderPrinter implements OrderPrinter {

    @Override
    public void printOrder(List<Order> orders) {
        System.out.println("******************************************");

        for (int i = 0; i < orders.size(); i ++) {
            System.out.println((i+1) + "\t"+ orders.get(i).getName() + "\t" + orders.get(i).getTotalCost());
        }

        System.out.println("*******************************************");

        AtomicReference<Double> totalCost = new AtomicReference<>(0.0);
        orders.forEach(o -> totalCost.updateAndGet(v -> v + o.getTotalCost()));

        System.out.println("\t\t\t\t\t\tTotal " + totalCost);

        System.out.println("********************************************");
    }
}

class DetailOrderPrinter implements OrderPrinter {

    @Override
    public void printOrder(List<Order> orders) {
        System.out.println("************************************************************");

        for (Order order: orders) {
            System.out.println(order);
        }

        System.out.println("************************************************************");

        AtomicReference<Double> totalCost = new AtomicReference<>(0.0);
        orders.forEach(o -> totalCost.updateAndGet(v -> v + o.getTotalCost()));

        System.out.println("\t\t\t\t\t\tTotal " + totalCost);

        System.out.println("************************************************************");
    }
}

class ContextPrinter {
    private final OrderPrinter orderPrinter;

    public ContextPrinter(OrderPrinter orderPrinter) {
        this.orderPrinter = orderPrinter;
    }

    public void printOrder(List<Order> orders) {
        this.orderPrinter.printOrder(orders);
    }

    public static void main(String[] args) {
        Order order1 = new Order("Tea", 5, 100.00);
        Order order2 = new Order("Coffee", 5, 120.00);
        Order order3 = new Order("Biscuit", 5, 80.00);

        List<Order> orderList = Arrays.asList(order1, order2, order3);

        ContextPrinter contextPrinter1 = new ContextPrinter(new SummaryOrderPrinter());
        contextPrinter1.printOrder(orderList);

        ContextPrinter contextPrinter2 = new ContextPrinter(new DetailOrderPrinter());
        contextPrinter2.printOrder(orderList);
    }
}